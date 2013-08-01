package com.teraim.strand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.teraim.strand.dataobjekt.InputAlertBuilder;
import com.teraim.strand.dataobjekt.InputAlertBuilder.AlertBuildHelper;

/**
 * 
 * @author Terje
 *
 * Class that allows dividing beach into zones.
 */
public class ActivityZoneSplit extends Activity {



	//HYDRO
	private static final int ID_StrandTyp = 0;
	private static final int ID_KustTyp = 1;
	private static final int ID_VågExponering = 2;
	private static final int ID_Vattendjup = 3;

	//EXTRA
	private static final int ID_TradForekomst = 10;
	private static final int ID_SlutlenOvan = 11;
	private static final int ID_LutningExtra = 12;

	//GEO	
	private static final int ID_SlutlenGeo= 20;
	private static final int ID_LutningGeo= 21;

	//SUPRA
	private static final int ID_SlutlenSupra= 30;
	private static final int ID_LutningSupra= 31;



	private static final int STATE_EXTRA = 1;
	private static final int STATE_SUPRA = 2;
	private static final int STATE_GEO = 3;
	private static final int STATE_HYDRO = 4;





	protected static final String TypeDigit = "DIGIT";
	TextView extraT,supraT,geoT,hydroT;
	LinearLayout bg;

	//convenience..
	Provyta py = Strand.getCurrentProvyta(this);
	private int myDisplayState;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState !=null) {
			myDisplayState = savedInstanceState.getInt(Strand.KEY_ZONE_DISPLAY_STATE);
		}
		setContentView(R.layout.activity_zone_split);

		extraT = (TextView)this.findViewById(R.id.extraT);
		supraT = (TextView)this.findViewById(R.id.supraT);
		geoT = (TextView)this.findViewById(R.id.geoT);
		hydroT = (TextView)this.findViewById(R.id.hydroT);


		//Main view that will be filled with variables depending on zone.
		bg = (LinearLayout)this.findViewById(R.id.contentPane);




		extraT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				myDisplayState = STATE_EXTRA;
				goExtra();

			}});


		supraT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myDisplayState = STATE_SUPRA;
				goSupra();


			}});


		hydroT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myDisplayState = STATE_HYDRO;
				goHydro();
			}});


		geoT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myDisplayState = STATE_GEO;
				goGeo();
			}});
		
		
		switch (myDisplayState) {
		case STATE_GEO:
			goGeo();
			break;
		case STATE_EXTRA:
			goExtra();
			break;
		case STATE_HYDRO:
			goHydro();
			break;
		case STATE_SUPRA:
			goSupra();
			break;
		}
		
	}




	protected void goSupra() {
		bg.removeAllViews();

		//SlutLängd Extralitoral
		addNormalInput(bg,"Slutlängd Supra","Avstånd från medelvattenlinjen till där Supralittoralen slutar",py.getSlutlensupra(),ID_SlutlenSupra,InputType.TYPE_CLASS_NUMBER);

		//Lutning 

		addNormalInput(bg,"Lutning Supra","Mätt lutning i supralittralen (grader)",py.getLutningsupra(),ID_LutningSupra,InputType.TYPE_CLASS_NUMBER);
	
	}




	protected void goGeo() {
		bg.removeAllViews();


		//SlutLängd 
		addNormalInput(bg,"Slutlängd Geo","Avstånd längs transekten från medelvattenlinjen till där geolittoralen slutar (dm)",py.getSlutlengeo(),ID_SlutlenGeo,InputType.TYPE_CLASS_NUMBER);

		//Lutning 

		addNormalInput(bg,"Lutning Geo","Mätt lutning i geolittralen (grader)",py.getLutninggeo(),ID_LutningGeo,InputType.TYPE_CLASS_NUMBER);


		
	}




	protected void goHydro() {
		bg.removeAllViews();

		//Strandtyp
		List<String> values;// = new ArrayList<String>(Arrays.asList("1","2","3","4","5"));
		List<String> entries = new ArrayList<String>(Arrays.asList("Klippa/Häll","Block/Grus","Sand","Strandäng/Våtmark","Konstruerad"));
		values = entries;
		addSpinnerInput(bg,"Strandtyp","Klassning 1-5",py.getStrandtyp(),ID_StrandTyp,entries,values);

		//Kusttyp
		values = new ArrayList<String>(Arrays.asList("fastland","öar","skär","grund"));
		entries = values;
		addSpinnerInput(bg,"Kusttyp","Välj bland nedanstående",py.getKusttyp(),ID_KustTyp,entries,values);

		//Vågexponering
		addNormalInput(bg,"Vågexponering","Bedöm exponeringklass. Jämför med utdata och ändra om det (uppenbart) ej stämmer",py.getExponering(),ID_VågExponering,InputType.TYPE_CLASS_NUMBER);

		//Vattendjup
		addNormalInput(bg,"Vattendjup","Mätt vattendjup 3 m utanför medelvattenlinjen",py.getExponering(),ID_Vattendjup,InputType.TYPE_CLASS_NUMBER);

	}




	protected void goExtra() {
		bg.removeAllViews();

		//SlutLängd Extralitoral
		addNormalInput(bg,"Slutlängd Extra","Avstånd från medelvattenlinjen till där hela transekten slutar",py.getSlutlenovan(),ID_SlutlenOvan,InputType.TYPE_CLASS_NUMBER);


		//Trädförekomst
		addBooleanInput(bg,"Trädförekomst","Ifall det inte är fastland, finns det ett skogsbetsånd > 0.25 ha (1/0)?",py.getTrädförekomst(),ID_TradForekomst);


		//Lutning

		addNormalInput(bg,"Lutning Supra","Mätt lutning i Extralittoralen (grader)",py.getLutningextra(),ID_LutningExtra,InputType.TYPE_CLASS_NUMBER);

	}




	public TextView getTextView(View v) {
		return (TextView)v.findViewById(R.id.editfieldinput);
	}
	
	private View createClickableField(ViewGroup bg,String headerT,String defValue) {
		return createClickableField(bg,headerT,defValue,R.layout.clickable_field_normal);
	}
	private View createClickableListField(ViewGroup bg,String headerT,String defValue) {
		return createClickableField(bg,headerT,defValue,R.layout.clickable_field_list);
	}
	private View createClickableYesNoField(ViewGroup bg,String headerT,String defValue) {
		return createClickableField(bg,headerT,defValue,R.layout.clickable_field_yes_no);
	}

	private View createClickableField(ViewGroup bg,String headerT,String defValue, int res) {
		View view = LayoutInflater.from(getBaseContext()).inflate(res,null);
		bg.addView(view);
		TextView header = (TextView)view.findViewById(R.id.editfieldtext);
		SpannableString content = new SpannableString(headerT);
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		header.setText(content);
		getTextView(view).setText(defValue);
		view.setClickable(true);
		return view;	
	}



	private View addBooleanInput(ViewGroup bg,final String headerT,final String bodyT,final String currValue,final int id) {
		final View v = createClickableYesNoField(bg,headerT,currValue);

		final AlertBuildHelper abh = new AlertBuildHelper(getBaseContext()) {
			@Override
			public View createView() {
				// Set an EditText view to get user input 
				View view = LayoutInflater.from(c).inflate(R.layout.ja_nej_radiogroup,null);
				RadioButton ja = (RadioButton)view.findViewById(R.id.ja);
				RadioButton nej = (RadioButton)view.findViewById(R.id.nej);
				if(currValue!=null) {
					if(currValue.equals("1"))
						ja.setEnabled(true);
					else
						nej.setEnabled(true);
					ja.setChecked(true);
				}
				return view;
			}
			@Override
			public void setResult(int id, View inputView,View outputView) {
				setStringValue(id,((RadioButton)inputView.findViewById(R.id.ja)).isChecked()?"1":"0",outputView);
			}};		

			v.setOnClickListener(InputAlertBuilder.createAlert(id,headerT,bodyT,abh,v));
			return v;		

	}

	private View addSpinnerInput(ViewGroup bg,final String headerT,final String bodyT,final String currValue,final int id, final List<String>entries, final List<String>values) {

		final View v = createClickableListField(bg,headerT,currValue);

		final AlertBuildHelper abh = new AlertBuildHelper(getBaseContext()) {
			@Override
			public View createView() {
				// Set an EditText view to get user input 
				final Spinner spinner = (Spinner)LayoutInflater.from(c).inflate(R.layout.edit_field_spinner, null);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, entries);		
				spinner.setAdapter(adapter);

				return spinner;
			}
			@Override
			public void setResult(int id, View inputView,View outputView) {
				Log.d("Strand","Value for spinner: "+values.get(((Spinner)inputView).getSelectedItemPosition()));
				setStringValue(id,values.get(((Spinner)inputView).getSelectedItemPosition()),outputView);
			}};


			v.setOnClickListener(InputAlertBuilder.createAlert(id,headerT,bodyT,abh,v));
			return v;		
	}

	private View addNormalInput(ViewGroup bg,final String headerT,final String bodyT,final String currValue, final int id, final int inputType) {
		final View v = createClickableField(bg, headerT,currValue);
		final TextView et = (TextView)v.findViewById(R.id.editfieldinput);
		final InputAlertBuilder.AlertBuildHelper abh = new AlertBuildHelper(this.getBaseContext()) {
			@Override
			public View createView() {
				// Set an EditText view to get user input 
				final EditText input = (EditText)LayoutInflater.from(c).inflate(R.layout.edit_field, null);

				input.setText(et.getText());
				//input.setInputType(inputType);

				return input;
			}

			@Override
			public void setResult(int id,View inputView,View outputView) {
				setStringValue(id,((EditText)inputView).getText().toString(),outputView);
			}};

			v.setOnClickListener(InputAlertBuilder.createAlert(id,headerT,bodyT,abh,v));

			return v;		

	}


	private void setStringValue(int id, String value, View v) { 
		switch(id) {
		case ID_SlutlenOvan:
			py.setSlutlenovan(value);
			break;

		case ID_SlutlenGeo:
			py.setSlutlengeo(value);
			break;
		case ID_StrandTyp:
			py.setStrandtyp(value);
			break;

		case ID_KustTyp:
			py.setKusttyp(value);
			break;

		case ID_TradForekomst:
			py.setTrädförekomst(value);
			break;

		case ID_VågExponering:
			py.setExponering(value);
			break;

		case ID_SlutlenSupra:
			py.setSlutlensupra(value);
			break;

		case ID_LutningGeo:
			py.setLutninggeo(value);
			break;
		case ID_LutningSupra:
			py.setLutningsupra(value);
			break;
		case ID_LutningExtra:
			py.setLutningextra(value);
			break;
		case ID_Vattendjup:
			py.setVattendjup(value);
			break;


		}
		getTextView(v).setText(value);

	}



	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(Strand.KEY_ZONE_DISPLAY_STATE, myDisplayState);
		Log.d("Strand","onsaveinstance called");
		//Spara värden
		Persistent.onSave(py);
		super.onSaveInstanceState(savedInstanceState);
	}

	public void onVidare(View v) {
		Log.d("Strand","onvidare called");
		Intent i = new Intent(this, ActivityZoneless.class);
		startActivity(i);
	}

}
