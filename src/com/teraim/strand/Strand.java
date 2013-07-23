package com.teraim.strand;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * 
 * @author Terje
 *
 * Class saving constants etc needed for this App globally.
 */
public class Strand {
	
	//The root folder for the SD card is in the global Environment.
	private final static String path = Environment.getExternalStorageDirectory().getPath();
	//Remember to always add system root path before any app specific path!
	
	//Root for Strand
	public final static String STRAND_ROOT_DIR = path+"/strand/";
	
	
	//Root for the data objects storing data per provyta.
	public final static String DATA_ROOT_DIR = path+"/strand/data/";
	//Root for pictures.
	public static final String PIC_ROOT_DIR = path+"/strand/bilder/";
	

	public static final String KEY_PY_PARCEL = "com.teraim.strand.py_object";
	
	public static final String KEY_RUTA_ID= "ruta_id";
	
	public static final String KEY_PROVYTA_ID = "provyta_id";
	
	public static final String KEY_LAG_ID = "lag_id";
	
	public static final String KEY_INVENTERARE = "inventerare";

	public static final String KEY_CURRENT_PY = "py_id";

	protected static final String KEY_PIC_NAME = "pic_name";

	public static class PersistenceHelper {
		public static final String UNDEFINED = "";
		SharedPreferences sp;
		public PersistenceHelper(Context ctx) {
			sp = PreferenceManager.getDefaultSharedPreferences(ctx);
			if (ctx == null)
				Log.e("Strand","Context null in getdefaultsharedpreferences!");
		}
	
		public String get(String key) {
		return sp.getString(key,UNDEFINED);
		}
	
		public void put(String key, String value) {
		sp.edit().putString(key,value).commit();
		}
		
	}

	private static Provyta currentProvyta=null;

	public static void setCurrentProvyta(Provyta py) {
		currentProvyta = py;
	}

	public static Provyta getCurrentProvyta(Context c) {
		if(currentProvyta==null) {
			PersistenceHelper ph = new PersistenceHelper(c);
			Log.e("Strand","Provyta null in Stran.getCurrent..");
			//Try to load from saved PY_ID.
			String pycID = ph.get(Strand.KEY_CURRENT_PY);
			//load if exists..
			if (pycID!=PersistenceHelper.UNDEFINED)
				currentProvyta = Persistent.onLoad(pycID);

		} 
		return currentProvyta;
	}
	
}
