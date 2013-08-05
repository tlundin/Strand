package com.teraim.strand;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;

import com.teraim.strand.dataobjekt.TableHabitat;

public class ActivityHabitat extends M_Activity {


	String habitat[] = {"ej habitat","sten och grusvallar",
			"havsklippor","glas�rtsstr�nder","salta strand�ngar",
			"strand�ngar vid �stersj�n","sandstr�nder vid �stersj�n","mor�nstrand","dynhabitat",
			"rissandhedar","gr�ssandhedar","St�rre vattendrag","Fukthedar",
			"Torra hedar","Enbuskmark p� hed","Enbuskmark kalkgr�smark",
			"Basiska bergh�llar","Sandst�pp","Kalkgr�smark","Orkid�kalkgr�smark",
			"Stagg-gr�smarker","Silikatgr�smarker","Alvar","Kalkfukt�ng",
			"Fukt�ng","H�g�rt�ngar","Sv�m�ngar","Sl�tter�ngar i l�glandet",
			"H�gl�nta sl�tter�ngar","L�v�ngar","�ppen kultiverad betesmark",
			"�ppen kultiverad sl�tter�ng","Tr�db�rande kultiverad betesmark",
			"Tuvt�tel�ng","Buskrik utmark","H�gmossar","�ppna myrar","K�lla",
			"K�llk�rr","Agk�rr","Kalktuffk�llor","Rikk�rr","K�lla i rikk�rr",
			"H�llmarkstorr�ng","Karsth�llmarker","Taiga","Nordlig �dell�vskog",
			"Landh�jningsskog","N�ringsrik granskog","�sbarrskog","Tr�dkl�dd betesmark",
			"L�vsumpskog","N�ringsfattig bokskog","N�ringsrik bokskog","N�ringsrik ekskog",
			"�dell�vskog i branter","N�ringsfattig ekskog","Skogsbevuxen myr","Sv�ml�vskog",
	"Sv�m�dell�vskog"};


	String[] hKoder = {"9999","1220","1230","1310","1330","1630","1640","1952","2100","2320","2330",
			"3210","4010","4030","5131","5132","6110","6120","6210","6211",
			"6230","6270","6280","6411","6412","6430","6450","6510","6520","6530",
			"6911",	"6912",	"6913",	"6915",	"6916",	"7110",	"7140",	"7161",	"7162",	"7210",	"7220",	"7230",
			"7234",	"8230",	"8240",	"9010",	"9020",	"9030",	"9050",	"9060",	"9070",	"9080",	"9110",	"9130",
			"9160",	"9180",	"9190",	"9740",	"9750",	"9760"};

	String[] oKoder;
	String[] hUtbredning = {"0,1 ha","0,1 ha","0,1 ha","0,1 ha","0,1 ha","0,1 ha","0,1 ha","0,1 ha","...","0,1 ha","0,1 ha","0,1 ha/0,25 skog"};
	String dynHabitat[] = {"f�rdyner","vita dyner","gr� dyner","risdyner",
			"sandvide-dyner","tr�dkl�dda dyner","dynv�tmarker","ej habitat"};
	String[] dynHabitatKoder = {"2110","2120","2130","2140","2170","2180","2190","9999"};
	String[] dUtbredning = {"","","","","","0,25 ha","0,1 ha","0,1 ha �v 0,25 skog"};

	//Detta v�rde m�ste �ndras om ovanst�ende kolumner �ndras.
	public final static int SLUT_KOLUMN_NO = 4;
	public final static int START_KOLUMN_NO = 3;
	protected static final int TABLE_VISIBLE = 1;
	protected static final int DYN_TABLE_VISIBLE = 1;
	protected static final int DYN_SPINNER_VISIBLE = 2;

	private static final int STATE_INITIAL = 0;
	public static final String KOD_DYNHABITAT = "2100";
	public static final Object KOD_9999 = "9999";


	private int state = STATE_INITIAL;
	private int stateDyn = STATE_INITIAL;
	private TableHabitat tableH;
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
		final Spinner spinnerO = (Spinner)this.findViewById(R.id.habitatOvanS);
		final Spinner noHabS = (Spinner)this.findViewById(R.id.noHabS);
		final Spinner markanvOvanS = (Spinner)this.findViewById(R.id.markanvOvanS);
		
		final View noHabL = this.findViewById(R.id.noHabL);

		Button habitatB = (Button)this.findViewById(R.id.habitatB);
		Button dynB = (Button)this.findViewById(R.id.dynB);
		

		
		final LinearLayout sv = (LinearLayout)this.findViewById(R.id.contentPane);
		final EditText sandblotta = (EditText)sv.findViewById(R.id.sandblottadyn);
		final FrameLayout dynFrame = (FrameLayout)this.findViewById(R.id.dynFrame);
		final FrameLayout habitatFrame = (FrameLayout)this.findViewById(R.id.habitatFrame);

		final String[] habitatAndCode = new String[habitat.length];
		for (int i=0;i<habitat.length;i++)
			habitatAndCode[i] = hKoder[i]+" "+habitat[i];

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, habitatAndCode);
		spinnerH.setAdapter(arrayAdapter);
		spinnerO.setAdapter(arrayAdapter);

		ArrayAdapter<String> arrayHabA= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, TableHabitat.noEntries);
		noHabS.setAdapter(arrayHabA);

		ArrayAdapter<String> arrayMo= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ActivityZoneSplit.markTyper);
		markanvOvanS.setAdapter(arrayMo);

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
						String[] e = new String[6];
						e[0]=kod;e[1]=namn;e[2]=hUtbredning[sel];e[3]=start;e[4]=start;e[5]="";
						//add row.
						tableH.addDynHabitatRow(e);
						//add special listener
						addDynListener(sv,dynFrame);
						//Display spinner
						sv.setVisibility(View.VISIBLE);
						stateDyn = DYN_SPINNER_VISIBLE;
					}
				} else{
					Log.d("Strand","Selected: "+sel);

					tableH.addRow(kod,namn,hUtbredning[sel],start);
				}
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

		spinnerO.post(new Runnable() {
			public void run() {
		spinnerO.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int sel = spinnerO.getSelectedItemPosition();
				String kod = hKoder[sel];
				py.setOvanHabitat(kod);

				if(kod.equals(KOD_9999)) {
					noHabL.setVisibility(View.VISIBLE);
				} else
					noHabL.setVisibility(View.GONE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}});
			}
		});

		noHabS.post(new Runnable() {
			public void run() {
				noHabS.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						py.setKriterieovan((String)noHabS.getSelectedItem());
				}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}});			
				}
			});

		markanvOvanS.post(new Runnable() {
			public void run() {
				markanvOvanS.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						py.setMarktypovan((String)markanvOvanS.getSelectedItem());
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}});			}
			});

		


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




			sandblotta.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					Log.d("Strand","sandblotta changed");
					py.setDynerblottadsand(s.toString());
				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void beforeTextChanged(CharSequence arg0,
						int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub

				}
			});


		}

		if (state == STATE_INITIAL) {
			if(py.getDynerblottadsand()!=null) {
				sandblotta.setText(py.getDynerblottadsand());
			}
				
			String oh = py.getOvanHabitat();
			if (oh!=null) {
				Log.d("Strand","getOvanHabitat: "+py.getOvanHabitat());
				for(int i=0;i<hKoder.length;i++)
					if(oh.equals(hKoder[i])) {
						spinnerO.setSelection(i);
						break;
					}
						
			} else {
				Log.d("Strand","found no value for getOvanHabitat");
				spinnerO.setSelection(1);
			}
			String mt = py.getMarktypovan();
			if (mt!=null) {
				Log.d("Strand","marktypovan: "+py.getMarktypovan());
				for(int i=0;i<ActivityZoneSplit.markTyper.size();i++)
					if(mt.equals(ActivityZoneSplit.markTyper.get(i))) {
						markanvOvanS.setSelection(i);
						break;
					}
						
			} else {
				Log.d("Strand","found no value for marktypovan");
				markanvOvanS.setSelection(1);
			}
			
			if (py.getHabitat().getRowCount()!=0) {
				Log.d("Strand","Rows in tableHabitat. Show table. "+py.getHabitat().getRowCount());
				state = TABLE_VISIBLE;
				habitatFrame.addView(tableH);
			}
		}
		if(tableH!=null) 
			addDynListener(sv,dynFrame);

		if(py.getOvanHabitat()!=null&&py.getOvanHabitat().equals(KOD_9999)) {
			noHabL.setVisibility(View.VISIBLE);
			
		} else
			noHabL.setVisibility(View.GONE);

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
