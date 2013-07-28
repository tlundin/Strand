package com.teraim.strand.dataobjekt;

public class ArtListEntry  implements Comparable<ArtListEntry> {

	private String sl�kte,familj,svensktNamn;
	private ArtListaProvider ap;




	public ArtListEntry(String sl�kte, String familj, String svensktNamn, ArtListaProvider ap) {
		super();
		this.sl�kte = sl�kte;
		this.familj = familj;
		this.svensktNamn = svensktNamn;
		this.ap=ap;
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
		int retVal = 0;
		switch (ap.getSortColumn()) {
		case ArtListaProvider.SVENSK_F:
			retVal = svensktNamn.compareToIgnoreCase(ae.getSvensktNamn());	
			break;
		case ArtListaProvider.SL�KTE_F:
			retVal = sl�kte.compareToIgnoreCase(ae.getSl�kte());
			break;
		case ArtListaProvider.FAMILJ_F:
			retVal = familj.compareToIgnoreCase(ae.getFamilj());
			break;
		}
		return retVal;
	}
	

}
