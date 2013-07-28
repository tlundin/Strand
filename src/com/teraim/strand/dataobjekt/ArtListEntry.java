package com.teraim.strand.dataobjekt;

public class ArtListEntry  implements Comparable<ArtListEntry> {

	private String släkte,familj,svensktNamn;
	private ArtListaProvider ap;




	public ArtListEntry(String släkte, String familj, String svensktNamn, ArtListaProvider ap) {
		super();
		this.släkte = släkte;
		this.familj = familj;
		this.svensktNamn = svensktNamn;
		this.ap=ap;
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
		int retVal = 0;
		switch (ap.getSortColumn()) {
		case ArtListaProvider.SVENSK_F:
			retVal = svensktNamn.compareToIgnoreCase(ae.getSvensktNamn());	
			break;
		case ArtListaProvider.SLÄKTE_F:
			retVal = släkte.compareToIgnoreCase(ae.getSläkte());
			break;
		case ArtListaProvider.FAMILJ_F:
			retVal = familj.compareToIgnoreCase(ae.getFamilj());
			break;
		}
		return retVal;
	}
	

}
