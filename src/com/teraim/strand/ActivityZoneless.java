package com.teraim.strand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityZoneless extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_zoneless);
		
		OnClickListener cl = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActivityZoneless.this, ActivityArterFaltskikt.class);
				int typ=-1;
				switch(v.getId()) {
				case R.id.arterB:
					typ = Strand.ARTER;
					break;
				case R.id.tradB:
					typ = Strand.TRÄD;
					break;
				case R.id.buskB:
					typ = Strand.BUSKAR;
					break;
				}
				intent.putExtra(Strand.KEY_CURRENT_TABLE, typ);
				startActivity(intent);
			}
			
		};
		
		
		((Button)this.findViewById(R.id.arterB)).setOnClickListener(cl);
		((Button)this.findViewById(R.id.tradB)).setOnClickListener(cl);
		((Button)this.findViewById(R.id.buskB)).setOnClickListener(cl);
		
		((Button)this.findViewById(R.id.substratB)).setOnClickListener(new OnClickListener() {
			Intent intent = new Intent(ActivityZoneless.this, ActivitySubstratSelection.class);

			@Override
			public void onClick(View v) {
				startActivity(intent);
			}});
		((Button)this.findViewById(R.id.strandVallarB)).setOnClickListener(new OnClickListener() {
			Intent intent = new Intent(ActivityZoneless.this, ActivityVallar.class);

			@Override
			public void onClick(View v) {
				startActivity(intent);
			}});
		
		
	}

}
