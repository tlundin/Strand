package com.teraim.strand.dataobjekt;

import java.util.ArrayList;
import java.util.List;

import com.teraim.strand.R;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class TableHabitat extends TableBase {

	
	int[] textviews = {R.id.namn, R.id.utbredning, R.id.start,R.id.slut};
	int[] editviews = {R.id.e1,R.id.e2,R.id.e3,R.id.e4};
	
	public TableHabitat(Context c, Table data, int[] columnIds, String[] columnName) {
		super(c,data);
		init(R.layout.row_habitat_table,columnIds,columnName);

	}
	
	
	
	@Override
	public void addRow(String name) {
		//not used.
	}
	
	public void addRow(String name, String utbredning, String start) {
		String[] entries = new String[4];
		entries[0]=name;
		entries[1]=utbredning;
		entries[2]=start;
		entries[3]="";	
		String myID = myData.getNextId();
		TableRow row = addRow(myID,entries);		
		row.performClick();		
		myData.saveRow(myID, name,utbredning,start,"");
	}
	
	

	@Override
	protected TableRow addRow(final String myID, String[] entries) {
		final TableRow row = createRow(R.layout.row_habitat_table);
		assert(row!=null);
		//Load
		int i=0;
		if (textviews == null) 
			Log.e("Strand","wtf");
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
				removeRow(row);
				return true;
			}});


		addView(row);
		return row;
	}



	
}
