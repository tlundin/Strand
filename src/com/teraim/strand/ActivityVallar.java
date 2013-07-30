package com.teraim.strand;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.teraim.strand.dataobjekt.Table;
import com.teraim.strand.dataobjekt.TableVallar;
import com.teraim.strand.utils.ImageHandler;

public class ActivityVallar extends Activity {

	private final Provyta py = Strand.getCurrentProvyta(this);
	private TableVallar tv;
	private ImageButton pic_drift1;
	private ImageButton pic_drift2;
	private ImageHandler imgHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		this.setContentView(R.layout.activity_vallar);
		
//		HorizontalScrollView cp = (HorizontalScrollView)this.findViewById(R.id.contentPane);
		LinearLayout cp = (LinearLayout)this.findViewById(R.id.contentPane);
		
		Table myData = py.getVallar();
		
		tv = new TableVallar(this,myData);
		tv.setStretchAllColumns(true);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		tv.setLayoutParams(lp);
		
		cp.addView(tv);
		
		imgHandler = new ImageHandler(this);
		
		pic_drift1 = (ImageButton)this.findViewById(R.id.pic_drift1);
		pic_drift2 = (ImageButton)this.findViewById(R.id.pic_drift2);

		//Create buttons.
		initPic(pic_drift1,"drift1");
		initPic(pic_drift2,"drift2");
	
	}
	
	private void initPic(final ImageButton b, final String name) {
		imgHandler.drawButton(b,name);
		imgHandler.addListener(b,name);
	}
	public void onAddRowButtonClicked(View v) {
		//Add a row with default driftvallsnummer..
		tv.addRow(Integer.toString(py.getDriftVallsC()));
	}

}
