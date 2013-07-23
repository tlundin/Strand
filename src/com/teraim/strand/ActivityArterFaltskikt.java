package com.teraim.strand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.teraim.strand.dataobjekt.ArtListEntry;
import com.teraim.strand.dataobjekt.ArtListaProvider;

/**
 * 
 * @author Terje
 *
 */
public class ActivityArterFaltskikt extends Activity {


	private LinearLayout buttonPanel,contentPane;
	
	private ArtListaProvider ap;

	private final static String[] alfabet = {
		"*","A","B","C","D","E","F",
		"G","H","I","J","K","L",
		"M","N","O","P","Q","R",
		"S","T","U","V","W","X",
		"Y","Z","Å","Ä","Ö"};

	//keeps currently selected char if any
	String currC = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arter_faltskikt);


		buttonPanel = (LinearLayout) this.findViewById(R.id.buttonPanel);
		contentPane = (LinearLayout) this.findViewById(R.id.contentPane);
		//Generate buttons..A-Ö.

		final OnClickListener cl = new OnClickListener(){

			@Override
			public void onClick(View v) {

				String ch = ((Button)v).getText().toString();
				Log.d("Strand","Button "+ch);
				//generate List selector for this character.
				//if equals current - do nothing.
				
				if (ch!=currC) {
					currC = ch;
					contentPane.removeAllViews();
					contentPane.addView(getHeader(ch));
					contentPane.addView(getList(ch));
				}
			}

			private TextView getHeader(String ch) {
				TextView tv = (TextView)LayoutInflater.from(getBaseContext()).inflate(R.layout.header,null);
				tv.setText("Arter "+ch);
				return tv;
			}

			private ListView getList(String ch) {
				final String[] columnTags = new String[] {"Släkte", "Familj", "Svenskt namn"};
				final int[] columnIds = new int[] {R.id.column1, R.id.column2, R.id.column3};

				ArrayList<HashMap<String, String>> mylistData = ap.getArter(ch);
				ListView list = new ListView(ActivityArterFaltskikt.this);
				ListView.LayoutParams lp = new ListView.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

				TableAdapter arrayAdapter =
						new TableAdapter(ActivityArterFaltskikt.this, mylistData, R.layout.list_row,
								columnTags , columnIds);
				list.setAdapter(arrayAdapter);

				return list;


			}};
			Button b;
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			for (String c:alfabet) {
				b = new Button(this);
				b.setLayoutParams(lp);
				b.setText(c);
				b.setOnClickListener(cl);
				buttonPanel.addView(b);
			}

			//Create a provider for Örter.
			ap = new ArtListaProvider(this,R.raw.herbs);
			

	}


}
