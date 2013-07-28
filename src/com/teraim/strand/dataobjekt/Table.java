package com.teraim.strand.dataobjekt;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.util.Log;

public class Table implements Serializable {


	private static final long serialVersionUID = 660162787850970002L;

	private int noOfCols;
	private Map<String,String[]> rowlist;
	private int myID = 0;

	public Table(int noOfCols) {
		this.noOfCols = noOfCols;
		rowlist = new HashMap<String,String[]>();
	}


	public void saveRow (String id, String ... entries)
	{

		if (entries.length == noOfCols) {
			rowlist.put(id, entries);
			Log.d("Strand","Added row of data to table ");
		}
		else
			Log.e("Strand","Wrong number of columns ("+entries.length+"). Expected "+noOfCols);

	}

	public Set<Entry<String, String[]>> getTable() {
		return rowlist.entrySet();
	}


	public void deleteRow(String id) {
		String[] r = rowlist.remove(id);
		if (r!=null)
			Log.d("Strand","Removed row");
		else
			Log.e("Strand","Ups...couldnt find row in RemoveRow");
	}


	public String getNextId() {
		return Integer.toString(myID++);
	}



}
