package com.teraim.strand.dataobjekt;

public class ArtListEntry implements Comparable<ArtListEntry> {

	private String sl�kte,familj,svensktNamn;

	public ArtListEntry(String sl�kte, String familj, String svensktNamn) {
		super();
		this.sl�kte = sl�kte;
		this.familj = familj;
		this.svensktNamn = svensktNamn;
	}

	/**
	 * @return the sl�kte
	 */
	public String getSl�kte() {
		return sl�kte;
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
