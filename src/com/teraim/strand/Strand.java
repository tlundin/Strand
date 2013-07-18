package com.teraim.strand;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

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

	public static final String KEY_PY_PARCEL = "com.teraim.strand.py_object";
	
	public static final String KEY_RUTA_ID= "ruta_id";
	
	public static final String KEY_PROVYTA_ID = "provyta_id";
	
	public static final String KEY_LAG_ID = "lag_id";
	
	public static final String KEY_INVENTERARE = "inventerare";
	
	public static class PersistenceHelper {
		public static final String UNDEFINED = "";
		SharedPreferences sp;
		public PersistenceHelper(Context ctx) {
			sp = PreferenceManager.getDefaultSharedPreferences(ctx);
		}
	
		public String get(String key) {
		return sp.getString(key,UNDEFINED);
		}
	
		public void put(String key, String value) {
		sp.edit().putString(key,value).commit();
		}
		
	}
	
}
