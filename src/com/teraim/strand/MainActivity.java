package com.teraim.strand;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.teraim.strand.dataobjekt.StrandInputData;
import com.teraim.strand.dataobjekt.StrandInputData.Entry;

/**
 * 
 * @author Terje
 *
 * This is the entry point class for the App.
 */
public class MainActivity extends Activity {

	private Button create,edit,delete;
	private LinearLayout buttonRow;
	private Activity mother;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mother = this;
		//Check if I am running for the first time. If so, perform initial init.
		initIfFirstTime();
		//Load the input data.
		//For now, load from resources.
		InputStream is = getResources().openRawResource(R.raw.data);
		assert(is !=null);
		create=new Button(this);edit=new Button(this);delete=new Button(this);
		create.setText("Börja ny inmatning");
		edit.setText("Gör ändringar");
		delete.setText("Ta bort och börja om");
		//This call will parse the input file and create a singleton data object that can be used.
		StrandInputData.parseInputFile(is);
		 
		//Buttonrow.
		buttonRow = (LinearLayout)this.findViewById(R.id.buttons);

		//fill spinners with Ruta and Provyta IDs.		
		 Spinner rutSpinner = (Spinner)this.findViewById(R.id.spinner1);
		 //There can be many entries with the same ID, but we want only one of each.
		 Set<String>s=new HashSet<String>();
		 List<Entry> es = StrandInputData.getEntries();
		 for (Entry e:es) 
			 s.add(e.getRuta());
		 //Fill with the list of strings from the set. Sorting order not relevant.
		 final List<String> spinnerArray = new ArrayList<String>(s);
		 //..or maybe it is? Let's sort just for fun.
		 java.util.Collections.sort(spinnerArray);
		 spinnerArray.add(0, "-");
		 //Use standard adapter.
		 ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		 rutSpinner.setAdapter(spinnerArrayAdapter);
		 
		 //When ruta is selected, show Provyta spinner.
		 
		 rutSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			    @Override
			    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    		buttonRow.setVisibility(View.INVISIBLE);
			        if (position!=0) {
			        	showProvyteSpinner(spinnerArray.get(position));
			        }
			    }

	
				@Override
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }

			});
	}

    private void showProvyteSpinner(final String selectedRuta) {		
    	//TODO: IF ONLY ONE PROVYTA, no selection required.
    	
		//fill spinners with Ruta and Provyta IDs.		
		 Spinner ytSpinner = (Spinner)this.findViewById(R.id.spinner2); 	
			//fill spinners with Ruta and Provyta IDs.		
		 TextView tv = (TextView)this.findViewById(R.id.textView4); 	

		 tv.setVisibility(View.VISIBLE);
		 ytSpinner.setVisibility(View.VISIBLE);
		 final List<String> provyteArray = new ArrayList<String>();
		 List<Entry> es = StrandInputData.getEntries();
		 //Lägg till alla provytor för den valda rutan.
		 for (Entry e:es) 
			 if(e.getRuta().equals(selectedRuta))
				 provyteArray.add(e.getProvyta());
		 //Then, create an adaptor and link to the array.
		 ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, provyteArray);
		 ytSpinner.setAdapter(spinnerArrayAdapter);
		 
		 ytSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			    @Override
			    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			        String selectedYta = provyteArray.get(position);
			        List<Entry> es = StrandInputData.getEntries();
		    		buttonRow.removeAllViews();
			        for(Entry e:es) {
			        	if (e.getRuta().equals(selectedRuta) &&
			        			e.getProvyta().equals(selectedYta)) {
			        		String pyID = e.getPyid();
			        		Log.d("Strand","We have a winner. PyID is: "+pyID);
			        		//We now have the  PyID. Fetch the object (if any) from persistent storage.		        		
			        		Provyta py = Persistent.onLoad(pyID);
			        		
			        		//If object already exist, offer Edit button. Else, offer Create
			        		if (py !=null) {
			        			buttonRow.addView(edit);
			        			buttonRow.addView(delete);
			        			//Serialize and store to input activity.
			        			Bundle b = new Bundle();
			        			b.putSerializable(Strand.PY_PARCEL_KEY, py);
			        			Intent i = new Intent();
			        			i.putExtras(b);
			        			i.setClass(mother, ActivityMainInput.class);
			        			startActivity(i);
			        		}
			        		else
			        			buttonRow.addView(create);
			        		buttonRow.setVisibility(View.VISIBLE);
			        	}
			        		
			        	
			        }
			    }

	
				@Override
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // Gör inget
			    }

			});
		 
    }

	
	
	private void initIfFirstTime() {
		//If testFile doesnt exist it will be created and found next time.
		String testFile = Strand.STRAND_ROOT_DIR +
                "/ifiexistthenallisfine.txt";
		Log.d("Strand","Checking if this is first time use...");
		boolean exists = (new File(testFile)).exists(); 

		if (!exists) {
			Log.d("Strand","Yes..executing first time init");
			initialize();                                  
		}
		else 
			Log.d("Strand","..Not first time");
		
	}

	private void initialize() {
		//create data folder. This will also create the ROOT folder for the Strand app.
		File folder = new File(Strand.DATA_ROOT_DIR);
		folder.mkdirs();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	

	
	
}
