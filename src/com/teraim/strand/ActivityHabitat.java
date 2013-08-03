package com.teraim.strand;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;

import com.teraim.strand.dataobjekt.TableHabitat;

public class ActivityHabitat extends M_Activity {


	String habitat[] = {"driftvallar","sten och grusvallar",
			"havsklippor","glasörtsstränder","salta strandängar",
			"åsöar i Östersjön","skär i Östersjön","strandängar vid Östersjön",
			"sandstränder vid Östersjön","moränstrand","dynhabitat",
			"rissandhedar","grässandhedar","ej habitat"};
	String[] hKoder = {"1210","1220","1230","1310","1330","1610","1620","1630","1640","1952","2100","2320","2330","9999"};
	String[] hUtbredning = {"30 m","0,1 ha","0,1 ha","0,1 ha","0,25 ha","0,1 ha","0,1 ha","0,1 ha","0,1 ha","...","0,1 ha","0,1 ha","0,1 ha öv 0,25 skog"};
	String dynHabitat[] = {"fördyner","vita dyner","grå dyner","risdyner",
			"sandvide-dyner","trädklädda dyner","dynvåtmarker","ej habitat"};
	String[] dynHabitatKoder = {"2110","2120","2130","2140","2170","2180","2190","9999"};
	String[] dUtbredning = {"","","","","","0,25 ha","0,1 ha","0,1 ha öv 0,25 skog"};

	//Detta värde måste ändras om ovanstående kolumner ändras.
	public final static int SLUT_KOLUMN_NO = 4;
	public final static int START_KOLUMN_NO = 3;
	protected static final int TABLE_VISIBLE = 1;
	protected static final int DYN_TABLE_VISIBLE = 1;
	protected static final int DYN_SPINNER_VISIBLE = 2;

	private static final int STATE_INITIAL = 0;
	public static final String KOD_DYNHABITAT = "2100";


	private int state = STATE_INITIAL;
	private int stateDyn = STATE_INITIAL;
	TableHabitat tableH;
	private TableDynHabitat tableD;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			state = savedInstanceState.getInt(Strand.KEY_HABITAT_DISPLAY_STATE);
			stateDyn = savedInstanceState.getInt(Strand.KEY_HABITAT_DISPLAY_STATE_DYN);

		}

		this.setContentView(R.layout.activity_habitat);

		tableH = new TableHabitat(this,py.getHabitat());
		tableD = new TableDynHabitat(this,py.getDyner(),tableH);

		final Spinner spinnerH = (Spinner)this.findViewById(R.id.habitatS);
		final Spinner spinnerD = (Spinner)this.findViewById(R.id.dynS);
		Button habitatB = (Button)this.findViewById(R.id.habitatB);
		Button dynB = (Button)this.findViewById(R.id.dynB);
		final LinearLayout sv = (LinearLayout)this.findViewById(R.id.contentPane);
		final FrameLayout dynFrame = (FrameLayout)this.findViewById(R.id.dynFrame);
		final FrameLayout habitatFrame = (FrameLayout)this.findViewById(R.id.habitatFrame);

		final String[] habitatAndCode = new String[habitat.length];
		for (int i=0;i<habitat.length;i++)
			habitatAndCode[i] = hKoder[i]+" "+habitat[i];

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, habitatAndCode);
		spinnerH.setAdapter(arrayAdapter);


		final String[] dynHabitatAndCode = new String[dynHabitat.length];
		for (int i=0;i<dynHabitat.length;i++)
			dynHabitatAndCode[i] = dynHabitatKoder[i]+" "+dynHabitat[i];

		ArrayAdapter<String> dArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dynHabitatAndCode);
		spinnerD.setAdapter(dArrayAdapter);





		habitatB.setOnClickListener(new OnClickListener() {
			String start = "";
			@Override
			public void onClick(View v) {
				if (state == STATE_INITIAL) {
					Log.d("Strand","State initial habitat.. ");
					habitatFrame.addView(tableH);
					state = TABLE_VISIBLE;
					start = "0";
				}
				int sel = spinnerH.getSelectedItemPosition();
				String namn = habitat[sel];
				String kod = hKoder[sel];
				//if this is not the first row, get start value from previous end.

				String prevRowId =py.getHabitat().getLastKey();

				if (prevRowId != null)
					start = py.getHabitat().getRow(prevRowId)[SLUT_KOLUMN_NO];
				if (hKoder[sel].equals(KOD_DYNHABITAT)) {
					if(stateDyn == STATE_INITIAL ) {
						String[] e = new String[5];
						e[0]=kod;e[1]=namn;e[2]=hUtbredning[sel];e[3]=start;e[4]=start;
						//add row.
						final TableRow row = tableH.addDynHabitatRow(e);
						//add special listener
						addDynListener(sv,dynFrame);
						//Display spinner
						sv.setVisibility(View.VISIBLE);
						stateDyn = DYN_SPINNER_VISIBLE;
					}
				} else
					tableH.addRow(kod,namn,hUtbredning[sel],start);
			}});

		dynB.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (stateDyn == DYN_SPINNER_VISIBLE) {
					Log.d("Strand","State initial dyn.. ");
					dynFrame.addView(tableD);
					stateDyn = TABLE_VISIBLE;
					
				}
				int sel = spinnerD.getSelectedItemPosition();
				String namn = dynHabitat[sel];
				String kod = dynHabitatKoder[sel];

				//if this is not the first row, get start value from previous end.

//				String prevRowId =py.getDyner().getLastKey();

//				if (prevRowId != null)
//					start = py.getDyner().getRow(prevRowId)[SLUT_KOLUMN_NO];
				tableD.addRow(kod,namn,dUtbredning[sel]);

			}});		

		Log.d("Strand","My state is "+((state==TABLE_VISIBLE)?"TABLE_VISIBLE":((state==STATE_INITIAL)?"STATE_INITIAL":"STATE_UNKNOWN")));

		if (state == TABLE_VISIBLE) {
			habitatFrame.addView(tableH);
		}
		TableRow dynRow = tableH.getDynHabitatRow();
		if (stateDyn == STATE_INITIAL && dynRow!=null) {
			//check if dyntable has content
				//no rows? show only spinner.
				if(py.getDyner().getRowCount()==0) 
					stateDyn = DYN_SPINNER_VISIBLE;
					else 
						stateDyn = TABLE_VISIBLE;
					
					
				
		}

		if (stateDyn == DYN_SPINNER_VISIBLE) 
			sv.setVisibility(View.VISIBLE);		
		if (stateDyn == TABLE_VISIBLE)	{
			sv.setVisibility(View.VISIBLE);
			dynFrame.addView(tableD);
		}


		if (state == STATE_INITIAL) {
			if (py.getHabitat().getRowCount()!=0) {
				Log.d("Strand","Rows in tableHabitat. Show table. "+py.getHabitat().getRowCount());
				state = TABLE_VISIBLE;
				habitatFrame.addView(tableH);
			}
		}
		if(tableH!=null) 
			addDynListener(sv,dynFrame);

	}

	private void addDynListener(final LinearLayout sv, final FrameLayout dynFrame) {
		final TableRow row = tableH.getDynHabitatRow();
		if (row!=null)
			//Add a listener to check if row is deleted. If so, hide tableD.
			row.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					tableD.removeAllViews();
					tableH.removeDynHabitatRow();
					dynFrame.removeAllViews();
					py.getDyner().clear();
					stateDyn = STATE_INITIAL;
					sv.setVisibility(View.GONE);
					tableH.removeRow(row);
					tableH.recalculateDistances();
					return true;
				}});
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(Strand.KEY_HABITAT_DISPLAY_STATE, state);
		outState.putInt(Strand.KEY_HABITAT_DISPLAY_STATE_DYN, stateDyn);
	}

}
