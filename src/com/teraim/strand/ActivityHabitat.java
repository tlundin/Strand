package com.teraim.strand;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.teraim.strand.dataobjekt.TableHabitat;

public class ActivityHabitat extends M_Activity {


	String habitat[] = {"driftvallar","sten och grusvallar",
			"havsklippor","glasörtsstränder","salta strandängar",
			"åsöar i Östersjön","skär i Östersjön","strandängar vid Östersjön",
			"sandstränder vid Östersjön","moränstrand","dynhabitat",
			"rissandhedar","grässandhedar","ej habitat"};
	String[] hKoder = {"1210","1220","1230","1310","1330","1610","1620","1630","1640","1952","2100","2320","2330","9999"};
	String[] hUtbredning = {"0,1 ha","?","0,1 ha","0,1 ha","0,25 ha","0,1 ha","0,1 ha","0,1 ha","0,1 ha","...","0,1 ha","0,1 ha"};
	String dynHabitat[] = {"fördyner","vita dyner","grå dyner","risdyner",
			"sandvide-dyner","trädklädda dyner","dynvåtmarker"};
	String[] dynHabitatKoder = {"2110","2120","2130","2140","2170"};
	String[] dUtbredning = {"","","","","","0,25 ha","0,1 ha"};

	protected final static String[] columnName = new String[] {"Namn","Utbredning","Start","Slut"};
	//Detta värde måste ändras om ovanstående kolumner ändras.
	protected final static int SLUT_KOLUMN_NO = 3;
	protected final static int[] columnIds = new int[] {R.id.namn, R.id.utbredning, R.id.start,R.id.slut};
	protected static final int TABLE_VISIBLE = 1;
	private static final int STATE_INITIAL = 0;

	private int state = STATE_INITIAL;
	TableHabitat tableH;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			state = savedInstanceState.getInt(Strand.KEY_HABITAT_DISPLAY_STATE);
		}

		this.setContentView(R.layout.activity_habitat);

		tableH = new TableHabitat(this,py.getHabitat(),columnIds,columnName);

		final Spinner spinnerH = (Spinner)this.findViewById(R.id.habitatS);
		Button habitatB = (Button)this.findViewById(R.id.habitatB);
		final ScrollView sv = (ScrollView)this.findViewById(R.id.contentPane);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, habitat);
		spinnerH.setAdapter(arrayAdapter);

		if (py.getHabitat().getRowCount()!=0)
			state = TABLE_VISIBLE;

		if (state == TABLE_VISIBLE)
			sv.addView(tableH);



		habitatB.setOnClickListener(new OnClickListener() {
			String start = null;
			@Override
			public void onClick(View v) {
				if (state == STATE_INITIAL) {
					sv.addView(tableH);
					state = TABLE_VISIBLE;
					start = "0";
				}
				String namn = (String)spinnerH.getSelectedItem();
				int sel = spinnerH.getSelectedItemPosition();
				//if this is not the first row, get start value from previous end.

				String prevRowId =py.getHabitat().getLastKey();
				
				if (prevRowId != null)
						start = py.getHabitat().getRow(prevRowId)[SLUT_KOLUMN_NO];
				else
					start ="";			
				tableH.addRow(namn,hUtbredning[sel],start);

			}});

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(Strand.KEY_HABITAT_DISPLAY_STATE, state);
	}

}
