package com.teraim.strand.dataobjekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class ArtListaProvider {

	private List<ArtListEntry> artLista = new ArrayList<ArtListEntry>();

	//Wrap input resource.
	//TODO: NEEDS to be off the main UI thread if large files..!
	public ArtListaProvider(Context c, int resourceId) {
		InputStream is = c.getResources().openRawResource(resourceId);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String readLine = null;

		try {
			// While the BufferedReader readLine is not null 
			while ((readLine = br.readLine()) != null) {
				Log.d("TEXT", readLine);
				if (readLine !=null) {
					String[] temp = readLine.split(",");
					if (temp!=null) {
						if (temp.length!=3) 
							Log.e("Strand","Suspicious number of elements in row.");
						else {
							artLista.add(new ArtListEntry(temp[0],temp[1],temp[2]));
						}
					}
				}

			}

			// Close the InputStream and BufferedReader
			is.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Get all species starting with provided character 

	public ArrayList<HashMap<String, String>> getArter(String character) {

		//returnvalue
		ArrayList<HashMap<String, String>> mylistData =
				new ArrayList<HashMap<String, String>>();
		List<ArtListEntry> sorted = new ArrayList<ArtListEntry>();


		String[] columnTags = new String[] {"Sl�kte", "Familj", "Svenskt namn"};

		//Get Species from file.
		HashMap<String,String> map;

		//Add header
		map = new HashMap<String, String>();
		for(int j=0; j<3; j++)
			map.put(columnTags[j],columnTags[j]);

		for(ArtListEntry e:artLista)

			//Select only matching..
			if (character.equals("*")||
					(e.getSvensktNamn()!=null && 
					(e.getSvensktNamn().startsWith(character)||
							e.getSvensktNamn().startsWith(character.toLowerCase()))
							)) 
				sorted.add(e);
			else
				Log.d("Strand", "Svenskt namn: "+e.getSvensktNamn()+ 
						"Starts with "+character+" ? "+e.getSvensktNamn().startsWith(character));

		//Sort.
		Collections.sort(sorted);

		for(ArtListEntry s:sorted) {

			map = new HashMap<String, String>();		
			//initialize row data
			map.put("Sl�kte", s.getSl�kte());
			map.put("Familj", s.getFamilj());
			map.put("Svenskt namn",s.getSvensktNamn());
			mylistData.add(map);
		}


		return mylistData;
	}

}
