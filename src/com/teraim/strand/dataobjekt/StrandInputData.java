package com.teraim.strand.dataobjekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * 
 * @author Terje
 *
 * Det här objektet enkapsulerar den CSV inputfil som finns för Strand.
 * 
 */

public class StrandInputData {
	
	private static List<Entry> myTable = new ArrayList<Entry>();
	private final static int ColumnCount = 12;	
	//internal row counter used for error message.
	private static int x = 0;

	
	//Fetches and parses the input file data. For now, this is stored as a dev. file.
	public static boolean parseInputFile(InputStream is) {
		 BufferedReader bufferreader = new BufferedReader(new
		            InputStreamReader(is));
		    String bufferLine;

		    try{
		        while ((bufferLine = bufferreader.readLine()) != null){
		            addRow(bufferLine);
		             }
		            } catch (IOException e){
		            	return false;
		            }
		    return true;
	}
	
	//Separate columns by ","
	private static void addRow(String row) {
		assert(row!=null);
		String[] c = row.split(",");
		//TODO: Add lat, long
		String missing = "";
		if (c.length!=ColumnCount) {
			Log.e("Strand", "Wrong number of column in input row "+x+" :"+c.length);
			
		}
		myTable.add(new Entry(c[0],c[1],c[2],c[3],c[4],c[5],c[6],c[7],c[8],c[9],c[10],c[11],c[12],c[13],c[14],c[15],missing,missing));
		x++;
	}
	
	
	public static List<Entry> getEntries() {
		return myTable;
	}
	
	public static class Entry {
		
		private String pyid,year,ruta,provyta,vattendistrikt,namn,biogeo,urvalsklass,exploatering,strandtyp,kusttyp,vägexponering,transketlangd,transektriktning,east,north,longitude,latitude;

		public Entry(String pyid, String year, String ruta, String provyta,
				String vattendistrikt, String namn, String biogeo,
				String urvalsklass, String exploatering, String strandtyp,
				String kusttyp, String vägexponering, String transketlangd,
				String transektriktning, String east, String north,
				String longitude, String latitude) {
			super();
			this.pyid = pyid;
			this.year = year;
			this.ruta = ruta;
			this.provyta = provyta;
			this.vattendistrikt = vattendistrikt;
			this.namn = namn;
			this.biogeo = biogeo;
			this.urvalsklass = urvalsklass;
			this.exploatering = exploatering;
			this.strandtyp = strandtyp;
			this.kusttyp = kusttyp;
			this.vägexponering = vägexponering;
			this.transketlangd = transketlangd;
			this.transektriktning = transektriktning;
			this.east = east;
			this.north = north;
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public String getPyid() {
			return pyid;
		}

		public String getYear() {
			return year;
		}

		public String getRuta() {
			return ruta;
		}

		public String getProvyta() {
			return provyta;
		}

		public String getVattendistrikt() {
			return vattendistrikt;
		}

		public String getNamn() {
			return namn;
		}

		public String getBiogeo() {
			return biogeo;
		}

		public String getUrvalsklass() {
			return urvalsklass;
		}

		public String getExploatering() {
			return exploatering;
		}

		public String getStrandtyp() {
			return strandtyp;
		}

		public String getKusttyp() {
			return kusttyp;
		}

		public String getVägexponering() {
			return vägexponering;
		}

		public String getTransketlangd() {
			return transketlangd;
		}

		public String getTransektriktning() {
			return transektriktning;
		}

		public String getEast() {
			return east;
		}

		public String getNorth() {
			return north;
		}

		public String getLongitude() {
			return longitude;
		}

		public String getLatitude() {
			return latitude;
		}



		
	}
	
	
}
