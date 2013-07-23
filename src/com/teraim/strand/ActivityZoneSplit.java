package com.teraim.strand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
	private static final int ID_VägExponering = 2;
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
	






	protected static final String TypeDigit = "DIGIT";
	TextView extraT,supraT,geoT,hydroT;
	LinearLayout bg;

	//convenience..
	Provyta py = Strand.getCurrentProvyta(this);



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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


				bg.removeAllViews();

				//SlutLängd Extralitoral
				addNormalInput(bg,"Slutlängd Extra","Avstånd från medelvattenlinjen till där hela transekten slutar",py.getSlutlenovan(),ID_SlutlenOvan,InputType.TYPE_CLASS_NUMBER);

				
				//Trädförekomst
				addBooleanInput(bg,"Trädförekomst","Ifall det inte är fastland, finns det ett skogsbetsånd > 0.25 ha (1/0)?",py.getTrädförekomst(),ID_TradForekomst);

				
				//Lutning

				addNormalInput(bg,"Lutning Supra","Mätt lutning i Extralittoralen (grader)",py.getLutningextra(),ID_LutningExtra,InputType.TYPE_CLASS_NUMBER);

				
			}});


		supraT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				bg.removeAllViews();

				//SlutLängd Extralitoral
				addNormalInput(bg,"Slutlängd Supra","Avstånd från medelvattenlinjen till där Supralittoralen slutar",py.getSlutlensupra(),ID_SlutlenSupra,InputType.TYPE_CLASS_NUMBER);
						
				//Lutning 
				
				addNormalInput(bg,"Lutning Supra","Mätt lutning i supralittralen (grader)",py.getLutningsupra(),ID_LutningSupra,InputType.TYPE_CLASS_NUMBER);

			
			
			}});


		hydroT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				bg.removeAllViews();

				//Strandtyp
				List<String> values = new ArrayList<String>(Arrays.asList("1","2","3","4","5"));
				List<String> entries = new ArrayList<String>(Arrays.asList("Klippa/Häll","Block/Grus","Sand","Strandäng/Våtmark","Konstruerad"));
				addSpinnerInput(bg,"Strandtyp","Klassning 1-5",py.getStrandtyp(),ID_StrandTyp,entries,values);

				//Kusttyp
				values = new ArrayList<String>(Arrays.asList("fastland","öar","skär","grund"));
				entries = values;
				addSpinnerInput(bg,"Kusttyp","Välj bland nedanstående",py.getKusttyp(),ID_KustTyp,entries,values);

				//Vägexponering
				addNormalInput(bg,"Vägexponering","Bedöm exponeringklass. Jämför med utdata och ändra om det (uppenbart) ej stämmer",py.getExponering(),ID_VägExponering,InputType.TYPE_CLASS_NUMBER);

				//Vattendjup
				addNormalInput(bg,"Vattendjup","Mätt vattendjup 3 m utanför medelvattenlinjen",py.getExponering(),ID_Vattendjup,InputType.TYPE_CLASS_NUMBER);
				
			}});

		
		geoT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				bg.removeAllViews();

				
				//SlutLängd 
				addNormalInput(bg,"Slutlängd Geo","Avstånd längs transekten från medelvattenlinjen till där geolittoralen slutar (dm)",py.getSlutlengeo(),ID_SlutlenGeo,InputType.TYPE_CLASS_NUMBER);
			
				//Lutning 
				
				addNormalInput(bg,"Lutning Geo","Mätt lutning i geolittralen (grader)",py.getLutninggeo(),ID_LutningGeo,InputType.TYPE_CLASS_NUMBER);
			
			
			}});
	}




	private View createClickableField(ViewGroup bg,String headerT,String defValue) {

		View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.editfield,null);
		bg.addView(view);
		TextView header = (TextView)view.findViewById(R.id.editfieldtext);
		SpannableString content = new SpannableString(headerT);
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		header.setText(content);
		final TextView et = (TextView)view.findViewById(R.id.editfieldinput);
		et.setText(defValue);
		view.setClickable(true);
		return view;	
	}

	private interface AlertBuildHelper {

		public View createView(Context c);

		public void setResult(View inputView);		

	}

	private View addBooleanInput(ViewGroup bg,final String headerT,final String bodyT,final String currValue,final int id) {
		final View v = createClickableField(bg,headerT,currValue);

		final AlertBuildHelper abh = new AlertBuildHelper() {
			@Override
			public View createView(Context c) {
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
			public void setResult(View inputView) {
				setStringValue(id,((RadioButton)inputView.findViewById(R.id.ja)).isChecked()?"1":"0",v);
			}};		

			v.setOnClickListener(createAlert(headerT,bodyT,abh));
			return v;		

	}

	private View addSpinnerInput(ViewGroup bg,final String headerT,final String bodyT,final String currValue,final int id, final List<String>entries, final List<String>values) {

		final View v = createClickableField(bg,headerT,currValue);

		final AlertBuildHelper abh = new AlertBuildHelper() {
			@Override
			public View createView(Context c) {
				// Set an EditText view to get user input 
				final Spinner spinner = new Spinner(c);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, entries);		
				spinner.setAdapter(adapter);

				return spinner;
			}
			@Override
			public void setResult(View inputView) {
				Log.d("Strand","Value for spinner: "+values.get(((Spinner)inputView).getSelectedItemPosition()));
				setStringValue(id,values.get(((Spinner)inputView).getSelectedItemPosition()),v);
			}};


			v.setOnClickListener(createAlert(headerT,bodyT,abh));
			return v;		
	}

	private View addNormalInput(ViewGroup bg,final String headerT,final String bodyT,final String currValue, final int id, final int inputType) {
		final View v = createClickableField(bg, headerT,currValue);
		final TextView et = (TextView)v.findViewById(R.id.editfieldinput);
		final AlertBuildHelper abh = new AlertBuildHelper() {
			@Override
			public View createView(Context c) {
				// Set an EditText view to get user input 
				final EditText input = new EditText(c);

				input.setText(et.getText());
				//input.setInputType(inputType);

				return input;
			}

			@Override
			public void setResult(View inputView) {
				setStringValue(id,((EditText)inputView).getText().toString(),v);
			}};

			v.setOnClickListener(createAlert(headerT,bodyT,abh));

			return v;		

	}


	private OnClickListener createAlert(final String headerT, final String bodyT, final AlertBuildHelper abh) {

		final Context c = this;

		return new OnClickListener() {

			@Override
			public void onClick(View v) {

				//On click, create dialog 			
				AlertDialog.Builder alert = new AlertDialog.Builder(c);
				alert.setTitle(headerT);
				alert.setMessage(bodyT);
				final View inputView = abh.createView(c);
				alert.setView(inputView);
				alert.setPositiveButton("Spara", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {				  
						abh.setResult(inputView);
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
		
		case ID_VägExponering:
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
		TextView tv = (TextView)v.findViewById(R.id.editfieldinput);
		tv.setText(value);
	}

}
