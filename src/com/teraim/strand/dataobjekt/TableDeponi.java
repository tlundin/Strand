package com.teraim.strand.dataobjekt;

	import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.teraim.strand.R;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

	public class TableDeponi extends TableBase {


		//protected EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
		protected int rowC = 0;
		protected final static int[] columnIds = {R.id.c1,R.id.c2};

		protected final static String[] columnName = new String[] {"Deponityp","Area"};

		protected final static int NO_OF_COLS = 2;

		public TableDeponi(Context c, Table data) {
			super(c,data);
			redraw(R.layout.row_deponi_table,columnIds,columnName);		
		}



		
		//creates a row. Will return the row number of the created row.

		protected TableRow addRow(final String myID, final String[] entries) {


			final TableRow row = createRow(R.layout.row_deponi_table);
			assert(row!=null);
			//Load
			int i=0;
			for(int id:columnIds) 
				((TextView)row.findViewById(id)).setText(entries[i++]);

			row.setTag(myID);		
			row.setOnClickListener(

					InputAlertBuilder.createAlert(-1, "Deponi",null, 
							new AlertBuildHelper(TableDeponi.this.getContext()){

						@Override
						public View createView() {
							View inputView = LayoutInflater.from(c).inflate(R.layout.deponi_table_popup,null);
							EditText et = (EditText)inputView.findViewById(R.id.e1);
							CharSequence val = ((TextView)row.findViewById(R.id.c2)).getText();
							et.setText(val!=null?val.toString():"");
							return inputView;
						}

						@Override
						public void setResult(int resultId, View inputView,
								View outputView) {
							Log.d("Strand","Nu ska jag minsann spara!");
							final EditText et = (EditText)inputView.findViewById(R.id.e1);
							final TextView c1 = (TextView)row.findViewById(R.id.c1);
							final TextView c2 = (TextView)row.findViewById(R.id.c2);
							c2.setText(et.getText());
							myData.saveRow(myID,c1.getText().toString(),et.getText().toString());

						}}, row)
					);		

			addView(row);
			return row;
			
		}




		@Override
		public void addRow(String name) {
			//Not used..
		}










		


	}

