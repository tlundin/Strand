package com.teraim.strand;

import android.os.Environment;

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
	
	
}
