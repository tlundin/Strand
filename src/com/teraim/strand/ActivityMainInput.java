package com.teraim.strand;

import android.app.Activity;
import android.os.Bundle;

public class ActivityMainInput extends Activity {

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.activity_input);

		//Find the corresponding data object if any.
		Provyta py;
		    if(b!=null)
		    	py = (Provyta) b.getSerializable(Strand.PY_PARCEL_KEY);
	}

	
}
