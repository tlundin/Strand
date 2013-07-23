package com.teraim.strand.dataobjekt;

public class ArtListEntry implements Comparable<ArtListEntry> {

	private String släkte,familj,svensktNamn;

	public ArtListEntry(String släkte, String familj, String svensktNamn) {
		super();
		this.släkte = släkte;
		this.familj = familj;
		this.svensktNamn = svensktNamn;
	}

	/**
	 * @return the släkte
	 */
	public String getSläkte() {
		return släkte;
	}

	/**
	 * @return the familj
	 */
	public String getFamilj() {
		return familj;
	}

	/**
	 * @return the svensktNamn
	 */
	public String getSvensktNamn() {
		return svensktNamn;
	}

	@Override
	public int compareTo(ArtListEntry ae) {
		return svensktNamn.compareToIgnoreCase(ae.getSvensktNamn());
	}
	
	
}
