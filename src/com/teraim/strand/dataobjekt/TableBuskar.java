package com.teraim.strand.dataobjekt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.teraim.strand.R;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

public class TableBuskar extends TableBase {

//	protected TextView avstT,artT,breddT,l�ngdT,t�thetT;
	protected EditText avstE,breddE,l�ngdE,t�thetE;
	protected int rowC = 0;
	protected final static int[] columnIds = new int[] {R.id.avst, R.id.art, R.id.bredd,R.id.langd,R.id.tathet};
	protected final static String[] columnName = new String[] {"Avst�nd","Art","Bredd","L�ngd","T�thet"};


	public TableBuskar(Context c, Table data) {
		super(c,data);
		init(R.layout.row_buske_table,columnIds,columnName);
	}


	//Creates a row. Will also (side effect) create a new row in the Data Table.
	@Override
	public void addRow(String name) {
		String[] entries = new String[5];
		entries[0]="";
		entries[1]=name;
		entries[2]="";
		entries[3]="";
		entries[4]="";
		String myID = myData.getNextId();
		addRow(myID,entries).performClick();
		myData.saveRow(myID, "",name,"","","");
	}

	
	//creates a row. Will return the row number of the created row.

	protected TableRow addRow(final String myID, final String[] entries) {


		final TableRow row = super.createRow(R.layout.row_buske_table);
		assert(row!=null);

		int i=0;
		assert(entries.length==5);
		for(int id:columnIds) 
			((TextView)row.findViewById(id)).setText(entries[i++]);

		Log.d("Strand", "S�tter avstT onclick");


		row.setTag(myID);		
		row.setOnClickListener(

				InputAlertBuilder.createAlert(-1, "Buskar","Inmatning f�r "+entries[1]+".", 
						new AlertBuildHelper(TableBuskar.this.getContext()){

					@Override
					public View createView() {
						View inputView = LayoutInflater.from(c).inflate(R.layout.buske_table_popup,null);
						avstE = (EditText)inputView.findViewById(R.id.avst);
						breddE = (EditText)inputView.findViewById(R.id.bredd);
						l�ngdE = (EditText)inputView.findViewById(R.id.langd);	
						t�thetE = (EditText)inputView.findViewById(R.id.tathet);	
						return inputView;
					}

					@Override
					public void setResult(int resultId, View inputView,
							View outputView) {
						TextView avstT,breddT,l�ngdT,t�thetT;
						avstT = (TextView)outputView.findViewById(R.id.avst);
						breddT = (TextView)outputView.findViewById(R.id.bredd);
						l�ngdT = (TextView)outputView.findViewById(R.id.langd);						
						t�thetT = (TextView)outputView.findViewById(R.id.tathet);

						avstT.setText(avstE.getText());
						breddT.setText(breddE.getText());
						l�ngdT.setText(l�ngdE.getText());
						t�thetT.setText(t�thetE.getText());
						String art="",avst = "",bredd="",langd="",tathet="";
						if(avstE.getText()!=null)
							avst = avstE.getText().toString();
						if(breddE.getText()!=null)
							bredd = breddE.getText().toString();
						if(l�ngdE.getText()!=null)
							langd = l�ngdE.getText().toString();
						if(t�thetE.getText()!=null)
							langd = t�thetE.getText().toString();

						art = entries[1];
						myData.saveRow(myID,avst,art,bredd,langd,tathet);

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
