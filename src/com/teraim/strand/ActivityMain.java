package com.teraim.strand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.teraim.strand.Strand.PersistenceHelper;
import com.teraim.strand.dataobjekt.StrandInputData;
import com.teraim.strand.dataobjekt.StrandInputData.Entry;
import com.teraim.strand.utils.JSONify;

/**
 * 
 * @author Terje
 *
 * This is the entry point class for the App.
 */
public class ActivityMain extends Activity {

	private Activity mother;
	private PersistenceHelper ph;
	private String selectedRuta=PersistenceHelper.UNDEFINED;
	private LinearLayout exprDia;
	private ArrayAdapter<String> provyteArrayAdapter;
	//Arraylist for provytor.
	private final List<String> provyteArray = new ArrayList<String>();
	private final static String påbörjad = "Fortsätt inmatning";
	private final static String ny = "Ny inmatning";
	private final static String klar = "Avslutad yta";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		exprDia = (LinearLayout)this.findViewById(R.id.exprdia);

		//Reference to class
		mother = this;
		ph = new PersistenceHelper(this);
		//Check if I am running for the first time. If so, perform initial init.
		initIfFirstTime();
		//Load the input data.
		//For now, load from resources.
		InputStream is = getResources().openRawResource(R.raw.data);
		assert(is !=null);
		//This call will parse the input file and create a singleton data object that can be used statically.
		StrandInputData.parseInputFile(is);

		rutSpinner = (Spinner)this.findViewById(R.id.rutaspinner);
		//There can be many entries with the same ID, but we want only one of each.
		Set<String>s=new HashSet<String>();
		List<Entry> es = StrandInputData.getEntries();
		for (Entry e:es) 
			s.add(e.getRuta());
		//Fill with the list of strings from the set. Sorting order not relevant.
		final List<String> rutArray = new ArrayList<String>(s);
		//..or maybe it is? Let's sort just for fun.
		java.util.Collections.sort(rutArray);
		//Use standard adapter.
		ArrayAdapter<String> rutArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, rutArray);
		rutSpinner.setAdapter(rutArrayAdapter);

		ytSpinner = (Spinner)this.findViewById(R.id.provytaspinner); 	
		//Then, create an adaptor and link to the array.
		provyteArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, provyteArray);
		ytSpinner.setAdapter(provyteArrayAdapter);

		//Create a listener for event when ruta is selected.
		//If new ruta, re-generate the list of available provytor.	 
		rutSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

				selectedRuta = rutArray.get(position);
				refreshProvyteSpinner(selectedRuta);
				Log.d("Strand","Rutspinner onitemselected");
				provyteArrayAdapter.notifyDataSetChanged();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}

		});

		//Check if a provyta has been selected.
		ytSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				String selectedProvyta = provyteArray.get(position);
				if (position!=0) {
					Log.d("Strand","ytspinner onselected, pos: "+position+" sr: "+selectedRuta+" sp"+selectedProvyta);
					showProvyteStatus(selectedRuta,selectedProvyta);
				}
				else
					exprDia.setVisibility(View.INVISIBLE);

			}


			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Gör inget
			}

		});

		etLag = (EditText)this.findViewById(R.id.edit_lagnr);
		etInv = (EditText)this.findViewById(R.id.edit_invent);

		//check if there is a current provvyta being worked on.

		py = Strand.getCurrentProvyta(this);
		
		if (py!=null) {
			Log.d("Strand","loaded current provyta with py"+py.getProvyta());
			etLag.setText(py.getLagnummer());
			etInv.setText(py.getInventerare());
			setSpinner(rutSpinner,py.getRuta());
			
		}
		else {
			rutArray.add(0,"-");
			provyteArray.clear();
			provyteArray.add(0, "-");
			provyteArrayAdapter.notifyDataSetChanged();	
			rutArrayAdapter.notifyDataSetChanged();		 
		}
		
		

	}
	private EditText etLag;
	private EditText etInv;
	private Spinner rutSpinner;
	private Spinner ytSpinner;
	private Provyta py = null;
	private String pyID = null;

	private void showProvyteStatus(String selectedRuta, String selectedProvyta) {
		TextView expressionMark = (TextView)this.findViewById(R.id.exprmark);
		Button exprMessage = (Button)this.findViewById(R.id.textmessage);
		TextView pyIDt = (TextView)this.findViewById(R.id.pyID);

		//check if user selected other py than currently selected.
		if (py!=null) {
			//if so, trigger search for selected.
			if (!selectedRuta.equals(py.getRuta())||!selectedProvyta.equals(py.getProvyta())) {
				Log.d("Strand","selected ruta/provyta does not match py");
				Log.d("Strand","selectedRuta: "+selectedRuta+" selectedProvyta: "+selectedProvyta+" py.ruta: "+py.getRuta()+" py.py "+py.getProvyta());
				py=null;
			}
		}
		//if new provyta selected, do something cool.
		if (py==null) {
			List<Entry> es = StrandInputData.getEntries();	    				
			for(Entry e:es) {
				if (e.getRuta().equals(selectedRuta) &&
						e.getProvyta().equals(selectedProvyta)) {
					pyID = e.getPyid();
					//We now have the  PyID. Fetch the object (if any) from persistent storage.		        		
					py = Persistent.onLoad(pyID);
					break;
				}
			}
		} else
			pyID = py.getpyID();
		pyIDt.setText("Provytans ID: "+pyID);
		exprDia.setVisibility(View.VISIBLE);
		//If object already exist, offer Edit button. Else, offer Create


		if (py !=null) {        			
			if(py.isLocked()) {
				expressionMark.setTextColor(Color.RED);
				expressionMark.setText("!");
				exprMessage.setText(klar);

			}
			else {
				expressionMark.setTextColor(Color.YELLOW);       				
				expressionMark.setText("!");
				exprMessage.setText(påbörjad);
			}
		} else {
			expressionMark.setTextColor(Color.GREEN);
			expressionMark.setText("\u2713");
			exprMessage.setText(ny);     				    			
		}


	}




	@Override
	protected void onResume() {
		super.onResume();
		//check if user made changes..refresh..
		Log.d("Strand","on resume..");
		if(py!=null) {
			showProvyteStatus(py.getRuta(),py.getProvyta());			
		}

	}

	//Called when button pressed to start insamling.
	public void startCollect(View view) {
		if (py == null) {
			assert(pyID != null);
			py = new Provyta(pyID);	 
			//save globals into the new provyta.
			py.setLagnummer(etLag.getText().toString());
			Log.d("Strand","Lagnummer set to "+etLag.getText().toString());
			py.setRuta((String)rutSpinner.getSelectedItem());
			py.setProvyta( (String)ytSpinner.getSelectedItem());
			py.setInventerare( etInv.getText().toString());
			py.setPyID(pyID);

		}
		//If the provyta is locked, check first with user if he really wants to change it.
		if (py.isLocked()) {
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						begin();
						break;

					case DialogInterface.BUTTON_NEGATIVE:
						break;
					}
				}
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Ytan är markerad som klar!")
			.setMessage("Vill du verkligen ändra värden?").setPositiveButton("Ja", dialogClickListener)
			.setNegativeButton("Nej", dialogClickListener).show();

		} else 
			begin();


	}

	private void begin() {

		Intent intent = new Intent(this, ActivityTakePicture.class);
		//Save all values for default when starting up next time..
		ph.put(Strand.KEY_CURRENT_PY,py.getpyID());
		Log.d("Strand","Saved provyta: "+ (String)ytSpinner.getSelectedItem());
		//Buffer the py object.
		Strand.setCurrentProvyta(py);
		startActivity(intent);

	}


	private void setSpinner(Spinner mySpinner, String selected) {
		ArrayAdapter myAdap = (ArrayAdapter) mySpinner.getAdapter(); //cast to an ArrayAdapter
		int spinnerPosition = myAdap.getPosition(selected);
		//set the default according to value
		Log.d("Strand","setspinner called with "+selected+" position of selected is "+spinnerPosition);

		mySpinner.setSelection(spinnerPosition);
	}

	private void refreshProvyteSpinner(String selectedRuta) {
		//fill spinner		
		List<Entry> es = StrandInputData.getEntries();
		provyteArray.clear();
		//Add all ytor for selected
		for (Entry e:es) 
			if(e.getRuta().equals(selectedRuta)) {
				provyteArray.add(e.getProvyta());
			}
		provyteArray.add(0,"-");
		if (py!=null) {
			setSpinner(ytSpinner,py.getProvyta());
		} else
			ytSpinner.setSelection(0);
	}


	private void initIfFirstTime() {
		//If testFile doesnt exist it will be created and found next time.
		String t = Strand.STRAND_ROOT_DIR +
				"ifiexistthenallisfine.txt";
		File f = new File(t);
		Log.d("Strand","Checking if this is first time use...");
		boolean exists = f.exists(); 

		if (!exists) {
			Log.d("Strand","Yes..executing  first time init");
			initialize();   
			//create token file to stop further calls to init.
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
			Log.d("Strand","..Not first time");

	}

	private void initialize() {
		//create data folder. This will also create the ROOT folder for the Strand app.
		File folder = new File(Strand.DATA_ROOT_DIR);
		folder.mkdirs();
		folder = new File(Strand.PIC_ROOT_DIR);
		if(!folder.mkdirs())
			Log.d("Strand","failed...");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}






}
