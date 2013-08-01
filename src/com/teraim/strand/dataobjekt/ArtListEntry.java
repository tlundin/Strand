package com.teraim.strand.dataobjekt;

public class ArtListEntry  implements Comparable<ArtListEntry> {

	private String sl�kte,familj,svensktNamn;
	private ArtListaProvider ap;
	private String cType;



	public ArtListEntry(String sl�kte, String familj, String svensktNamn, String cType,ArtListaProvider ap) {
		super();
		this.sl�kte = sl�kte;
		this.familj = familj;
		this.svensktNamn = svensktNamn;
		//Set cType to "Existence (x) by default if not set in call.
		this.cType = cType!=null?cType.toLowerCase():"x";
		this.ap=ap;
	}

	public boolean isExistence() {
		return cType.equals("x");
	}
	
	public boolean isCount() {
		return cType.equals("r")||cType.equals("rt");
	}
	
	public boolean isCoverage() {
		return cType.equals("t");
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
