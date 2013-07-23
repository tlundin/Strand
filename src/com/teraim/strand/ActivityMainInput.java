package com.teraim.strand;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.Toast;

import com.teraim.strand.Strand.PersistenceHelper;

public class ActivityMainInput extends Activity {

	private EditText et1,et3,et4;
	DatePicker et2;
	private CheckBox lock;

	private PersistenceHelper ph;
	private Provyta py=null;
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.activity_input);
		
		ph = new PersistenceHelper(this);

		lock = (CheckBox)this.findViewById(R.id.lock);

		et1 = (EditText) this.findViewById(R.id.et1);
		et2 = (DatePicker) this.findViewById(R.id.et2);
		et3 = (EditText) this.findViewById(R.id.et3);
		et4 = (EditText) this.findViewById(R.id.et4);
		
		//Find the corresponding data object if any.
		
//		if(b!=null)
//		    	py = (Provyta) b.getSerializable(Strand.KEY_PY_PARCEL);
		py = Strand.getCurrentProvyta(this);
		
		if (py!=null) {
		
			et1.setText(py.getLagnummer());
			et3.setText(py.getInventeringstyp());
			et4.setText(py.getStrandtyp());
			lock.setChecked(py.isLocked());
			
			Calendar c = py.getDatum();
			et2.init(
				    c.get(Calendar.YEAR), 
				    c.get(Calendar.MONTH), 
				    c.get(Calendar.DAY_OF_MONTH), 
				    new OnDateChangedListener(){

				        @Override
				        public void onDateChanged(DatePicker view, 
				          int year, int month,int day) {
				         Toast.makeText(getApplicationContext(), 
				           "onDateChanged", Toast.LENGTH_SHORT).show();
				         
				         Calendar c = Calendar.getInstance();
				         c.set(year, month, day);
				         py.setDatum(c);
				        }});
		}  
		if (py==null)
			Log.e("Strand","Provyta NULL!!!!");
	}
	public void onSave(View view) {
		
		Log.d("Strand","Gettext result: "+et1.getText());
		py.setLagnummer(et1.getText().toString());
		Log.d("Strand","Gettext result: "+et1.getText());
		py.setInventeringstyp(et3.getText().toString());
		py.setStrandtyp(et4.getText().toString());	
		
		py.setLocked(lock.isChecked());
		
		Persistent.onSave(py);
		finish();
	}

	
}
