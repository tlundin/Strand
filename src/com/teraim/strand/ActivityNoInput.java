package com.teraim.strand;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.teraim.strand.utils.ImageHandler;

public class ActivityNoInput extends Activity {
	
	
	String[] spin = new String[] {"Orsak1","Orsak2","Orsak3","Orsak4"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_no_input);
		ImageButton img = (ImageButton)this.findViewById(R.id.pic_ingen_inmatning);
		Spinner sp = (Spinner)this.findViewById(R.id.spinner_ingen_inmatning);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spin);
		sp.setAdapter(arrayAdapter);

		ImageHandler imgHandler = new ImageHandler(this);

		imgHandler.drawButton(img,"ingen_inventering");

	}

	
	
}
