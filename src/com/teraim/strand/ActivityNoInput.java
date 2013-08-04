package com.teraim.strand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.teraim.strand.utils.ImageHandler;


public class ActivityNoInput extends Activity {
	
	Provyta py = Strand.getCurrentProvyta(this);
	
	String[] spin = new String[] {
			"5  Tillf�lligt vattent�ckt >30 cm",
			"6  otillg�nglig v�tmark",
			"7  �kermark annuell gr�da",
			"8  sl�ttermark",
			"9  otillg�nglig �kerholme",
			"10 � mindre �n 0,1 ha",
			"11 otillg�nglig brant mark",
			"12 otillg�nglig rasrisk",
			"13 tomt bebyggt industri",
			"14 betr�dnadsf�rbud",
			"15 annan orsak ange skriftligt p� bl� lapp",
			"16 utanf�r �BO (endast �BO)",
			"17 utanf�r aktuellt markslag (endast lillNills myr)"};
	
	
	ImageHandler imgHandler;
	ImageButton img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_no_input);
		img = (ImageButton)this.findViewById(R.id.pic_ingen_inmatning);
		final Spinner sp = (Spinner)this.findViewById(R.id.spinner_ingen_inmatning);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spin);
		sp.setAdapter(arrayAdapter);

		imgHandler = new ImageHandler(this);

		imgHandler.drawButton(img,"ingen_inventering");
		imgHandler.addListener(img,"ingen_inventering");
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		       py.setOrsak((String)sp.getSelectedItem());
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});

	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent myI){

		if (requestCode == ImageHandler.TAKE_PICTURE){
			if (resultCode == Activity.RESULT_OK) 
			{
				Log.d("Strand","picture was taken, result ok");
				//				String name = myI.getStringExtra(Strand.KEY_PIC_NAME);
				String currSaving = imgHandler.getCurrentlySaving();
				if (currSaving!=null) {
					imgHandler.drawButton(img,currSaving);
					Log.d("Strand","Drew button!");
				} else
					Log.e("Strand","Did not find pic with name "+currSaving+" in onActRes in TakePic Activity");
			} else {
				Log.d("Strand","picture was NOT taken, result NOT ok");
			}

		}
		
	}
}
