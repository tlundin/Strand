package com.teraim.strand.dataobjekt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.teraim.strand.R;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

public class TableArter extends TableBase {
	
	protected final static int[] columnIds = new int[] {R.id.namn, R.id.supra, R.id.geo,R.id.extra};
	protected final static String[] columnName = new String[] {"NAMN","SUPRA","GEO","EXTRA"};
	private CheckBox supraCB,geoCB,extraCB;
	private TextView artT,supraT,geoT,extraT;

	
	public TableArter(Context c, Table data) {
		super(c,data);
		init(R.layout.row_arter_table,columnIds,columnName);
		
	}

	@Override
	public void addRow(String name) {
		
		String[] entries = new String[4];
		entries[0]=name;
		entries[1]="";
		entries[2]="";
		entries[3]="";
		String myID = myData.getNextId();
		addRow(myID,entries);
		myData.saveRow(myID, name,"","","");


	}
	
	/*
		TableRow row = super.createRow(R.layout.row_arter_table);
		assert(row!=null);
		((TextView)row.findViewById(R.id.namn)).setText(name);;
		addView(row)	 
	*/

	@Override
	public void addRow(final String myID, final String[] entries) {
		final TableRow row = super.createRow(R.layout.row_arter_table);
		assert(row!=null);

		artT = (TextView)row.findViewById(R.id.namn);
		supraT = (TextView)row.findViewById(R.id.supra);
		geoT = (TextView)row.findViewById(R.id.geo);
		extraT = (TextView)row.findViewById(R.id.extra);

		assert(entries.length==4);
		//Load
		artT.setText(entries[0]);
		supraT.setText(entries[1]);
		geoT.setText(entries[2]);
		extraT.setText(entries[3]);
		
		Log.d("Strand", "Skapar nu Alert för Tabell arter");


		row.setTag(myID);		
		row.setOnClickListener(

				InputAlertBuilder.createAlert(-1, "Fältskikt","Inmatning för "+entries[0]+". Kryssa för litoral där art återfunnits.", 
						new AlertBuildHelper(TableArter.this.getContext()){

					@Override
					public View createView() {
						View inputView = LayoutInflater.from(c).inflate(R.layout.arter_table_popup,null);
						supraCB = (CheckBox)inputView.findViewById(R.id.supraCB);
						geoCB = (CheckBox)inputView.findViewById(R.id.geoCB);
						extraCB = (CheckBox)inputView.findViewById(R.id.extraCB);							
						return inputView;
					}

					@Override
					public void setResult(int resultId, View inputView,
							View outputView) {
						TextView supraT,geoT,extraT;

						supraT = (TextView)outputView.findViewById(R.id.supra);
						geoT = (TextView)outputView.findViewById(R.id.geo);
						extraT = (TextView)outputView.findViewById(R.id.extra);
						String sup=checkBoxToString(supraCB.isChecked());
						String geo=checkBoxToString(geoCB.isChecked());
						String ext=checkBoxToString(extraCB.isChecked());
						supraT.setText(sup);
						geoT.setText(geo);
						extraT.setText(ext);					
						myData.saveRow(myID,entries[0],sup,geo,ext);
					}
					private String checkBoxToString(boolean checked) {
						return (checked?"X":"");
						
					}}, row)
				);		
		
		row.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View disc) {
				removeRow(row);
				return true;
			}});


		addView(row);
		
	}
	

	
	
	
}
