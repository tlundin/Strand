package com.teraim.strand.dataobjekt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.teraim.strand.R;

public class InputAlertBuilder {

	public static OnClickListener createAlert(final int id,final String headerT, final String bodyT, final AlertBuildHelper abh, final View outputView) {

	
		return new OnClickListener() {

			@Override
			public void onClick(View v) {

				//On click, create dialog 			
				AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
				alert.setTitle(headerT);
				alert.setMessage(bodyT);
				final View inputView = abh.createView();
				alert.setView(inputView);
				alert.setPositiveButton("Spara", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {				  
						abh.setResult(id,inputView,outputView);
					}

				});
				alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});	

				alert.show();
			}		
		};	

	}
	public static abstract class AlertBuildHelper {

		public Context c;
		
		public AlertBuildHelper(Context c) {
			this.c = c;
		}
		public abstract View createView();

		public abstract void setResult(int resultId, View inputView,View outputView);	
	}
	
	


}
	
	

