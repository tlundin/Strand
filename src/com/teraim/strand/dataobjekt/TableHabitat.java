package com.teraim.strand.dataobjekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.teraim.strand.ActivityHabitat;
import com.teraim.strand.R;
import com.teraim.strand.Strand;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

public class TableHabitat extends TableBase {


	int[] textviews = {R.id.kod,R.id.namn, R.id.utbredning, R.id.start,R.id.slut};
	int[] editviews = {R.id.e1,R.id.e2,R.id.e3,R.id.e4,R.id.e5};
	
	protected final static int[] columnIds = new int[] {R.id.kod,R.id.namn, R.id.utbredning, R.id.start,R.id.slut,R.id.kriterie};
	protected final static String[] columnName = new String[] {"Kod","Namn","M.K.","Start (m)","Slut (m)","Kriterie"};

	
	public final static String[] noEntries = {"Bebyggd strand","P�verkad av gr�v/pirbygge/muddring",
			"Avverkning kraftig utglesning av tr�d","Hydrologi p�verkad (ex. reglering)",
			"Omr�det exploaterat eller bebyggt","Ej naturlig skog"};
	List<String> values = new ArrayList<String>(Arrays.asList("13","14","15","16","17","18"));

	
	//	private boolean hasListener = true;

	private TableRow dynHabitatRow =null;
	private String dynHabitatId;
	private Spinner sp_9999 ;
	private ArrayAdapter<String> altArrayAdapter;

	public TableHabitat(Context c, Table data) {
		super(c,data);
		redraw(R.layout.row_habitat_table,columnIds,columnName);
		
		altArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, noEntries);
	}



	@Override
	public void addRow(String name) {
		//not used.
	}


	//Has listener field indicates in reality DynHabitat.
	public TableRow addRow(String kod, String name, String utbredning, String start) {
		String[] entries = new String[6];
		entries[0]=kod;
		//The special case..
		entries[1]=name;
		entries[2]=utbredning;
		entries[3]=start;
		entries[4]="";
		//Kriterie...
		entries[5]="";
		String myID = myData.getNextId();
		TableRow row = addRow(myID,entries);		
		row.performClick();		
		myData.saveRow(myID, kod,name,utbredning,start,"","");
		return row;
	}


	
	
	@Override
	protected TableRow addRow(final String myID, final String[] entries) {

		//checkfirst if this is special case.
		
		

		if (entries[0].equals(ActivityHabitat.KOD_DYNHABITAT))
			return addDynHabitatRow(myID,entries);
		else {
			final TableRow row = createRow(R.layout.row_habitat_table);
			assert(row!=null);
			//Load
			int i=0;

			for(int id:columnIds) 
				((TextView)row.findViewById(id)).setText(entries[i++]);

			row.setTag(myID);	
			row.setOnClickListener(

					InputAlertBuilder.createAlert(-1, "Habitat",null, 
							new AlertBuildHelper(TableHabitat.this.getContext()){

						@Override
						public View createView() {
							boolean kriteria = entries[0].equals(ActivityHabitat.KOD_9999);
							int rowLayoutId = (kriteria)?R.layout.habitat_table_popup_9999:R.layout.habitat_table_popup;
							LinearLayout inputView = (LinearLayout)LayoutInflater.from(c).inflate(rowLayoutId,null);
							int i = 0;
							for(int id:textviews) 
								((EditText)inputView.findViewById(editviews[i++])).setText(((TextView)row.findViewById(id)).getText());
							
							if(kriteria) {
								sp_9999 = (Spinner)inputView.findViewById(R.id.e6);
								sp_9999.setAdapter(altArrayAdapter);								
								sp_9999.setSelection(Strand.getInt(entries[5]), true);
								Log.d("Strand","no 5 was "+Strand.getInt(entries[5]));
							}
							return inputView;
						}

						@Override
						public void setResult(int resultId, View inputView,
								View outputView) {
							Log.d("Strand","Nu ska jag minsann spara!");
							List<String> ets = new ArrayList<String>();
							for(int id:editviews)
								ets.add(((EditText)inputView.findViewById(id)).getText().toString());
							int i = 0;
							//Add spinner if any.
							if (entries[0].equals(ActivityHabitat.KOD_9999)) 
								ets.add((String)sp_9999.getSelectedItem());
							else
								ets.add("");
							for(int id:columnIds)  {
								((TextView)row.findViewById(id)).setText(ets.get(i));
								Log.d("Strand","S�tter v�rde "+ets.get(i));
								i++;
							}
							myData.saveRow(myID,ets);

						}}, row)
					);
			row.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					Log.d("Strand","This gets fired");
					removeRow(row);
					//need to also subtract the distance from all other rows.
					recalculateDistances();

					return true;
				}});


			addView(row);
			return row;
		}
	}


	public void recalculateDistances() {
		Set<Entry<String, String[]>> t = myData.getTable();
		int prevSlut = 0;
		for(Entry<String, String[]>e:t) {
			String[] val = e.getValue();
			if (val!=null) {
				String slut = val[ActivityHabitat.SLUT_KOLUMN_NO];
				String start = val[ActivityHabitat.START_KOLUMN_NO];
				int sluti = (slut!=null&&slut.length()>0)?Integer.parseInt(slut):0;
				int starti = (start!=null&&start.length()>0)?Integer.parseInt(start):0;
				if (prevSlut!=starti){
					//Shift if wrong
					Log.d("Strand","Found row where SlutPrev != Startcurrent: "+prevSlut+" "+starti);
					int dif = sluti-starti;
					//if start bigger than slut, try using old prevslut.
					if (dif<0) {
						starti = prevSlut;
						dif = sluti - starti;
					}
					Log.d("Strand","Diff: "+dif+" sluti: "+sluti);
					starti = prevSlut;
					sluti = prevSlut+dif;
					Log.d("Strand","New  start "+starti+" newslut "+sluti);
					if (starti>sluti) {
						Log.d("Strand","Start i was bigger than sluti...");
						continue;
					}
					val[ActivityHabitat.SLUT_KOLUMN_NO]=Integer.toString(sluti);
					val[ActivityHabitat.START_KOLUMN_NO]=Integer.toString(starti);

				}
				prevSlut = sluti;
			} else
				Log.e("Strand","Oops...found null entry in myData table in recalcDistance");

		}

		redraw(R.layout.row_habitat_table,columnIds,columnName);
		//this.invalidate();
	}



	public TableRow getDynHabitatRow() {
		return dynHabitatRow;
	}

	public void removeDynHabitatRow() {
		Log.d("Strand","User removed dynRow");
		dynHabitatRow=null;
		dynHabitatId = null;
	}

	//Case first time
	public TableRow addDynHabitatRow(String[] entries) {
		return addDynHabitatRow(myData.getNextId(),entries);
	}

	
	public void setDynHabLen(String length) {
		String slut = getDynHabSlut();
		int s = (slut!=null && slut.length()>0)?Integer.parseInt(slut):0;
		int l = (length!=null && length.length()>0)?Integer.parseInt(length):0;
		setDynHabSlut(String.valueOf(s+l));
	}
	//Dynhablength is the sum of all lengths in the dynTable.
	public void setDynHabSlut(String newSum) {
		final TextView slut = ((TextView)dynHabitatRow.findViewById(R.id.slut));
		slut.setText(newSum);
		String[] row = myData.getRow(dynHabitatId);
		row[ActivityHabitat.SLUT_KOLUMN_NO] = newSum;
	}

	//Dynhablength is the sum of all lengths in the dynTable.
	public String getDynHabSlut() {
		Log.d("Strand","Checking dynhabitatrow with id "+dynHabitatId);

		String[] row = myData.getRow(dynHabitatId);
		if (row!=null)
			return row[ActivityHabitat.SLUT_KOLUMN_NO];
		else
			return "oops";
	}


	//Case  rebuild of table.
	public TableRow addDynHabitatRow(String myID, String[] entries) {
		//refuse to add if already exist
		final TableRow row = createRow(R.layout.row_habitat_table);
		int i=0;
		for(int id:columnIds) 
			((TextView)row.findViewById(id)).setText(entries[i++]);
		row.setTag(myID);	

		dynHabitatRow = row;
		dynHabitatId = myID;		
		addView(row);

		myData.saveRow(myID, entries);
		Log.d("Strand","Added dynhabitatrow. dynRowId set to "+myID);
		return row;


	}






}
