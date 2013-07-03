package com.teraim.strand;

import java.io.Serializable;
import java.util.ArrayList;

public class Provyta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483220563308165817L;
	/**
	 * 
	 */
	int fjofor;
	public ArrayList<Delyta> dong;
	
	class Delyta implements Serializable {
		private static final long serialVersionUID = 2266651148032922262L;
		/**
		 * 
		 */
		public String[] a = new String[10];
		
		public Delyta() {
			a[0]="a";
			a[1]="b";
			a[2]="c";
			a[3]="d";
			a[4]="e";
			
		}
	}
	
	public Provyta() {
		dong = new ArrayList<Delyta>();
		dong.add(new Delyta());
		dong.add(new Delyta());
		dong.add(new Delyta());		
	}
}
