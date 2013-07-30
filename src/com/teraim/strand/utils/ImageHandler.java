package com.teraim.strand.utils;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.teraim.strand.Provyta;
import com.teraim.strand.Strand;

public class ImageHandler {

	Activity c;
	Provyta py;
	
	public ImageHandler(Activity c) {
		this.c=c;
		py = Strand.getCurrentProvyta(c.getBaseContext());

	}
	
	
	public void drawButton(ImageButton b, String name) {
		
		String imgPath = Strand.PIC_ROOT_DIR+py.getpyID()+"_";



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
			Display display = c.getWindowManager().getDefaultDisplay();
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

	public void addListener(ImageButton b, final String name) {
		// TODO Auto-generated method stub


		b.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(c,
						"pic" + name + " selected",
						Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				File file = new File(Strand.PIC_ROOT_DIR, py.getpyID()+"_"+name+".png");

				Log.d("Strand","Saving pic "+name);
				currSaving=name;
				Uri outputFileUri = Uri.fromFile(file);

				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				//				intent.putExtra(Strand.KEY_PIC_NAME, name);
				c.startActivityForResult(intent, TAKE_PICTURE);


			}

		});


	}
	public final static int TAKE_PICTURE = 133;
	
	private String currSaving=null;
	
	public String getCurrentlySaving() {
		return currSaving;
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

}
