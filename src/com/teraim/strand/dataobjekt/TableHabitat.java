package com.teraim.strand.dataobjekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.teraim.strand.ActivityHabitat;
import com.teraim.strand.R;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableHabitat extends TableBase {


	int[] textviews = {R.id.kod,R.id.namn, R.id.utbredning, R.id.start,R.id.slut};
	int[] editviews = {R.id.e1,R.id.e2,R.id.e3,R.id.e4,R.id.e5};
	protected final static int[] columnIds = new int[] {R.id.kod,R.id.namn, R.id.utbredning, R.id.start,R.id.slut};
	protected final static String[] columnName = new String[] {"Kod","Namn","Utbredning","Start (m)","Slut (m)"};

	//	private boolean hasListener = true;

	private boolean dynHabitatExists = false;
	private TableRow dynHabitatRow =null;
	private String dynHabitatId;

	public TableHabitat(Context c, Table data) {
		super(c,data);
		redraw(R.layout.row_habitat_table,columnIds,columnName);

	}



	@Override
	public void addRow(String name) {
		//not used.
	}


	//Has listener field indicates in reality DynHabitat.
	public TableRow addRow(String kod, String name, String utbredning, String start) {
		String[] entries = new String[5];
		entries[0]=kod;
		//The special case..
		entries[1]=name;
		entries[2]=utbredning;
		entries[3]=start;
		entries[4]="";	
		String myID = myData.getNextId();
		TableRow row = addRow(myID,entries);		
		row.performClick();		
		myData.saveRow(myID, kod,name,utbredning,start,"");
		return row;
	}


	@Override
	protected TableRow addRow(final String myID, String[] entries) {

		//checkfirst if this is special case.

		if (entries[0].equals(ActivityHabitat.KOD_DYNHABITAT))
			return addDynHabitatRow(myID,entries);
		else {
			final TableRow row = createRow(R.layout.row_habitat_table);
			assert(row!=null);
			//Load
			int i=0;

			for(int id:textviews) 
				((TextView)row.findViewById(id)).setText(entries[i++]);

			row.setTag(myID);	
			row.setOnClickListener(

					InputAlertBuilder.createAlert(-1, "Habitat",null, 
							new AlertBuildHelper(TableHabitat.this.getContext()){

						@Override
						public View createView() {
							View inputView = LayoutInflater.from(c).inflate(R.layout.habitat_table_popup,null);
							int i = 0;
							for(int id:textviews) 
								((EditText)inputView.findViewById(editviews[i++])).setText(((TextView)row.findViewById(id)).getText());

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
							for(int id:textviews)  {
								((TextView)row.findViewById(id)).setText(ets.get(i));
								Log.d("Strand","Sätter värde "+ets.get(i));
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
		for(int id:textviews) 
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
