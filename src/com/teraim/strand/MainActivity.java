package com.teraim.strand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Check if I am running for the first time. If so, perform initial init.
		initIfFirstTime();
		//Load the input data.
		//For now, load from resources.
		InputStream is = getResources().openRawResource(R.raw.data);
		assert(is !=null);
		//This call will parse the input file and create a singleton data object that can be used.
		StrandInputData.parseInputFile(is);

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
			        if (position!=0)
			        	showProvyteSpinner(spinnerArray.get(position));
			    }

	
				@Override
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }

			});
	}

    private void showProvyteSpinner(String selectedRuta) {		
    	//TODO: IF ONLY ONE PROVYTA, no selection required.
    	
		//fill spinners with Ruta and Provyta IDs.		
		 Spinner ytSpinner = (Spinner)this.findViewById(R.id.spinner2); 	
			//fill spinners with Ruta and Provyta IDs.		
		 TextView tv = (TextView)this.findViewById(R.id.textView4); 	

		 tv.setVisibility(View.VISIBLE);
		 ytSpinner.setVisibility(View.VISIBLE);
		 List<String> provyteArray = new ArrayList<String>();
		 List<Entry> es = StrandInputData.getEntries();
		 //Lägg till alla provytor för den valda rutan.
		 for (Entry e:es) 
			 if(e.getRuta().equals(selectedRuta))
				 provyteArray.add(e.getProvyta());
		 //Then, create an adaptor and link to the array.
		 ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, provyteArray);
		 ytSpinner.setAdapter(spinnerArrayAdapter);
		 
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

	public void onSave (View v) {
		Log.d("Strand","Save");
		
		Provyta py = new Provyta();
		// Write to disk with FileOutputStream
		FileOutputStream f_out=null;
		try {
			f_out = new 
				FileOutputStream(Strand.DATA_ROOT_DIR +"myobject.data");
		} catch (FileNotFoundException e) {
			//Most likely get here if the folder is missing. Try this..
				e.printStackTrace();
			}

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = null;
		try {
			obj_out = new
				ObjectOutputStream (f_out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Write object out to disk
		try {
			obj_out.writeObject ( py );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onLoad (View v) {
		Log.d("Strand","Load");
		// Read from disk using FileInputStream
		FileInputStream f_in = null;
		try {
			f_in = new 
				FileInputStream(Strand.DATA_ROOT_DIR+"myobject.data");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = null;
		try {
			obj_in = new ObjectInputStream (f_in);
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Read an object
		Object obj = null;
		try {
			obj = obj_in.readObject();
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (obj instanceof Provyta)
		{
			Provyta py = (Provyta) obj;
		}
	}
	
	
}
