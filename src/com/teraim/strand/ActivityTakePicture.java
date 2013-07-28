package com.teraim.strand;

import java.io.File;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTakePicture extends Activity implements LocationListener {

	ImageButton slut,sup,upp,ut,left,right;
	Button gpsB,vidare;
	TextView gpsT,startP_text;
	//convenience..
	Provyta py = Strand.getCurrentProvyta(this);
	//pictures stored as: /strand/bilder/pyID_NAME
	String imgPath = Strand.PIC_ROOT_DIR+py.getpyID()+"_";
	//Map name to button
	HashMap<String, ImageButton> buttonM = new HashMap<String,ImageButton>();
	private LocationManager lm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_take_picture);

		vidare = (Button)this.findViewById(R.id.vidare_take_pic);
		gpsB = (Button)this.findViewById(R.id.gpsButton);
		gpsT = (TextView)this.findViewById(R.id.gpsText);
		startP_text = (TextView)this.findViewById(R.id.startP_text);

		gpsT.setVisibility(View.VISIBLE);
		gpsB.setVisibility(View.GONE);


		slut = (ImageButton)this.findViewById(R.id.pic_slut);
		sup = (ImageButton)this.findViewById(R.id.pic_sup);
		upp= (ImageButton)this.findViewById(R.id.pic_upp);
		ut = (ImageButton)this.findViewById(R.id.pic_ut);
		left= (ImageButton)this.findViewById(R.id.pic_left);
		right = (ImageButton)this.findViewById(R.id.pic_right);		
		buttonM.clear();
		initPic(slut,"slut");
		initPic(sup,"sup");
		initPic(upp,"upp");
		initPic(ut,"ut");
		initPic(left,"left");
		initPic(right,"right");


		//Init geoupdate
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

		if (lm==null) {
			Log.e("Strand","Startup of GPS tracking failed in ProvYtaGeoUpdater");
		}

		final Intent i = new Intent(this, ActivityZoneSplit.class);

		vidare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(i);
			}});

	}

	@Override
	public void onPause() {
		super.onPause();
		lm.removeUpdates(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		//---request for location updates---
		lm.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				0,
				1,
				this);

	}
	/**
	 * 
	 * 1. set image for each button
	 * 2. add click listener.
	 */
	private void initPic(final ImageButton b, final String name) {
		buttonM.put(name, b);
		drawButton(b,name);
		addListener(b,name);
	}


	private void drawButton(ImageButton b, String name) {
		// TODO Auto-generated method stub


		//Try to load pic from disk, if any.
		//To avoid memory issues, we need to figure out how big bitmap to allocate, approximately
		//Picture is in landscape & should be approx half the screen width, and 1/5th of the height.

		//First get the ration between h and w of the pic.
		final BitmapFactory.Options options = new BitmapFactory.Options();

		final String imgFileName = imgPath+name+".png";
		options.inJustDecodeBounds=true;
		Bitmap bip = BitmapFactory.decodeFile(imgFileName,options);		

		//there is a picture..
		int realW = options.outWidth;
		int realH = options.outHeight;


		//check if file exists
		if (realW>0) {
			double ratio = realH/realW;
			//Height should not be higher than width.
			if (ratio >0) {
				Log.d("Strand", "picture is not landscape. its portrait..");
			}
			Log.d("Strand", "realW realH"+realW+" "+realH);

			//Find out screen size.
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int sWidth = size.x;

			//Target width should be about half the screen width.

			double tWidth = sWidth/2;
			//height is then the ratio times this..
			int tHeight = (int) (tWidth*ratio);

			//use target values to calculate the correct inSampleSize
			options.inSampleSize = calculateInSampleSize(options, (int)tWidth, tHeight);

			Log.d("Strand"," Calculated insamplesize "+options.inSampleSize);
			//now create real bitmap using insampleSize

			options.inJustDecodeBounds = false;
			bip = BitmapFactory.decodeFile(imgFileName,options);
			if (bip!=null) {
				b.setImageBitmap(bip);
			}

		}
		else {
			Log.d("Strand","Did not find picture "+imgFileName);
			//need to set the width equal to the height...

		}
	}

	private void addListener(ImageButton b, final String name) {
		// TODO Auto-generated method stub


		b.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getBaseContext(),
						"pic" + name + " selected",
						Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				File file = new File(Strand.PIC_ROOT_DIR, py.getpyID()+"_"+name+".png");

				Log.d("Strand","Saving pic "+name);
				currSaving=name;
				Uri outputFileUri = Uri.fromFile(file);

				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				//				intent.putExtra(Strand.KEY_PIC_NAME, name);
				startActivityForResult(intent, TAKE_PICTURE);


			}

		});


	}
	final int TAKE_PICTURE = 133;
	private String currSaving = null;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent myI){

		if (requestCode == TAKE_PICTURE){
			if (resultCode == Activity.RESULT_OK) 
			{
				Log.d("Strand","picture was taken, result ok");
				//				String name = myI.getStringExtra(Strand.KEY_PIC_NAME);
				if (currSaving!=null) {
					ImageButton b = buttonM.get(currSaving);
					drawButton(b,currSaving);
					Log.d("Strand","Drew button!");
				} else
					Log.e("Strand","Did not find pic with name "+currSaving+" in onActRes in TakePic Activity");
			} else {
				Log.d("Strand","picture was NOT taken, result NOT ok");
			}

		}
		currSaving=null;
	}

	public static int calculateInSampleSize(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}
	double[] cords = null;

	@Override
	public void onLocationChanged(Location location) {
		//If no startpunkt set, update button
		if (!set) {
			cords = Geomatte.convertToSweRef(location.getLatitude(),location.getLongitude());
			if (gpsT.isShown()) {
				gpsT.setVisibility(View.GONE);
				gpsB.setVisibility(View.VISIBLE);
			}

			gpsB.setText("Sätt startpunkt\n(N: "+cords[0]+" \nÖ: "+cords[1]+")");
		}
	}
	//Flag set if startpunkt set.
	boolean set = false;

	public void setStartPoint(View v) {	
		
		if (set == true) {
			set = false;
			startP_text.setVisibility(View.GONE);
			gpsB.setVisibility(View.GONE);
			gpsT.setVisibility(View.VISIBLE);
			gpsT.setText("Ett ögonblick..");

		}
		else {
			if(cords!=null) {
				py.setStartPNorth(cords[0]);
				py.setStartPEast(cords[1]);	
				gpsB.setText("Sätt ny startpunkt");
				startP_text.setText("Startpunkt satt till:\nN: "+cords[0]+" Ö: "+cords[1]);
				startP_text.setVisibility(View.VISIBLE);
				gpsT.setVisibility(View.GONE);
				set=true;

			} else {
				startP_text.setText("! Inget värde !");
			}
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		gpsB.setVisibility(View.GONE);
		gpsT.setVisibility(View.VISIBLE);
		gpsT.setText("GPS avslagen..");
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle arg2) {
		if (status == LocationProvider.AVAILABLE) {

		} else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
			gpsB.setVisibility(View.GONE);
			gpsT.setVisibility(View.VISIBLE);
			gpsT.setText("Ett ögonblick..");


		} else if (status == LocationProvider.OUT_OF_SERVICE) {
			gpsB.setVisibility(View.GONE);
			gpsT.setVisibility(View.VISIBLE);
			gpsT.setText("Förlorade GPS..");

		}

	}
}
