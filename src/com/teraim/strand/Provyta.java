package com.teraim.strand;

import java.io.Serializable;
import java.util.Calendar;

import com.teraim.strand.dataobjekt.Table;

public class Provyta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483220563308165832L;
	/**
	 * 
	 */
	
	String brygga,dynerblottadsand,exponering,gpseast,gpsnorth,inventerare,inventeringstyp,orsak,kriteriestrand,kriterieovan,klippamax,
	kusttyp,lagnummer,lutningextra,lutninggeo,lutningsupra,marktypextra,marktypgeo,marktypsupra,
	marktypovan,ovanhabitat,provyta,pyID,rekreation,rojning,rojningtid,ruta,riktning,
	slutlengeo,slutlensupra,slutlenovan,strandtyp,vattendjuppyID, stangsel,tradforekomst,tradtackninggeo,
	tradtackningsupra,tradtackningextra,vegtackningfaltgeo,vegtackningfaltsupra,
	vegtackningfaltextra,vasslen,vattendjup,vasstathet,year;
	
	/**
	 * @return the dynerblottadsand
	 */
	public String getDynerblottadsand() {
		return dynerblottadsand;
	}

	/**
	 * @param dynerblottadsand the dynerblottadsand to set
	 */
	public void setDynerblottadsand(String dynerblottadsand) {
		saved = false;
		this.dynerblottadsand = dynerblottadsand;
	}

	/**
	 * @return the tradforekomst
	 */
	public String getTradforekomst() {
		return tradforekomst;
	}

	/**
	 * @param tradforekomst the tradforekomst to set
	 */
	public void setTradforekomst(String tradforekomst) {
		saved = false;
		this.tradforekomst = tradforekomst;
	}

	/**
	 * @return the tradtackningextra
	 */
	public String getTradtackningextra() {
		return tradtackningextra;
	}

	/**
	 * @param tradtackningextra the tradtackningextra to set
	 */
	public void setTradtackningextra(String tradtackningextra) {
		saved = false;
		this.tradtackningextra = tradtackningextra;
	}

	/**
	 * @return the vegtackningfaltextra
	 */
	public String getVegtackningfaltextra() {
		return vegtackningfaltextra;
	}

	/**
	 * @param vegtackningfaltextra the vegtackningfaltextra to set
	 */
	public void setVegtackningfaltextra(String vegtackningfaltextra) {
		saved = false;
		this.vegtackningfaltextra = vegtackningfaltextra;
	}

	/**
	 * @return the marktypextra
	 */
	public String getMarktypextra() {
		return marktypextra;
	}

	/**
	 * @param marktypextra the marktypextra to set
	 */
	public void setMarktypextra(String marktypextra) {
		saved = false;
		this.marktypextra = marktypextra;
	}

	/**
	 * @return the tradtackninggeo
	 */
	public String getTradtackninggeo() {
		return tradtackninggeo;
	}

	/**
	 * @param tradtackninggeo the tradtackninggeo to set
	 */
	public void setTradtackninggeo(String tradtackninggeo) {
		saved = false;
		this.tradtackninggeo = tradtackninggeo;
	}

	/**
	 * @return the vegtackningfaltgeo
	 */
	public String getVegtackningfaltgeo() {
		return vegtackningfaltgeo;
	}

	/**
	 * @param vegtackningfaltgeo the vegtackningfaltgeo to set
	 */
	public void setVegtackningfaltgeo(String vegtackningfaltgeo) {
		saved = false;
		this.vegtackningfaltgeo = vegtackningfaltgeo;
	}

	/**
	 * @return the tradtackningsupra
	 */
	public String getTradtackningsupra() {
		return tradtackningsupra;
	}

	/**
	 * @param tradtackningsupra the tradtackningsupra to set
	 */
	public void setTradtackningsupra(String tradtackningsupra) {
		saved = false;
		this.tradtackningsupra = tradtackningsupra;
	}

	private boolean isLocked=false;
	private boolean isNormal=true;


	//Sparar kommentarer.
	String bl�lapp;

	/**
	 * @return the bl�lapp
	 */
	public String getBl�lapp() {
		return bl�lapp;
	}

	/**
	 * @param bl�lapp the bl�lapp to set
	 */
	public void setBl�lapp(String bl�lapp) {
		saved = false;
		this.bl�lapp = bl�lapp;
	}

	//R�knare f�r default.
	int driftVallsC = 1;

	public int getDriftVallsC() {
		return driftVallsC++;
	}

	//Sweref koordinater markerad start.
	double startPEast,startPNorth;
	
	//Tabeller
	Table tr�d,buskar,arter,vallar,habitat,dyner;	
	/**
	 * @return the ovanHabitat
	 */
	public String getOvanHabitat() {
		return ovanhabitat;
	}

	/**
	 * @param ovanHabitat the ovanHabitat to set
	 */
	public void setOvanHabitat(String ovanHabitat) {
		this.ovanhabitat = ovanhabitat;
	}

	//Matris
	String[][] substrat;
	
	/**
	 * @return the substrat
	 */
	public String[][] getSubstrat() {
		return substrat;
	}

	//Flagga om �ndring gjorts.
	boolean saved = false;
	
	
	public boolean isLocked() {
		return isLocked;
	}
	
	public boolean isSaved() {
		return saved;
	}
	
	//This is true if provyta is set as 'inventeras ej'
		public boolean isNormal() {
			return isNormal;
	}
		
	
	
	public void setSaved(boolean s) {
		saved = s;
	}
	
	//Toggla l�s f�r �ndringar.
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	


	
	public String getpyID() {
		return pyID;
	}
	
	
	/**
	 * @return the lutningextra
	 */
	public String getLutningextra() {
		return lutningextra;
	}


	
	/**
	 * @param lutningextra the lutningextra to set
	 */
	public void setLutningextra(String lutningextra) {
		this.lutningextra = lutningextra;
		saved = false;
	}



	public void setStartPEast(double e) {
		startPEast = e;
		saved = false;
	}

	public void setStartPNorth(double n) {
		startPEast = n;
		saved = false;
	}

	
	
	/**
	 * @return the tr�d
	 */
	public Table getTr�d() {
		return tr�d;
	}




	/**
	 * @return the buskar
	 */
	public Table getBuskar() {
		return buskar;
	}




	/**
	 * @return the arter
	 */
	public Table getArter() {
		return arter;
	}



	/**
	 * @return the vallar
	 */
	public Table getVallar() {
		return vallar;
	}





	public void setPyID(String pyID) {
		this.pyID = pyID;
		saved = false;
	}


	public void setVegtackningfaltsupra(String vegtackningfaltsupra) {
		this.vegtackningfaltsupra = vegtackningfaltsupra;
		saved = false;
	}

	public void setSubstrat(String[][] substrat) {
		this.substrat = substrat;
		saved = false;
	}

	public void setProvyta(String provyta) {
		this.provyta = provyta;
		saved = false;
	}



	public void setRuta(String ruta) {
		this.ruta = ruta;
		saved = false;
	}



	public void setYear(String year) {
		this.year = year;
		saved = false;
	}



	public void setLagnummer(String lagnummer) {
		this.lagnummer = lagnummer;
		saved = false;
	}



	public void setInventerare(String inventerare) {
		this.inventerare = inventerare;
		saved = false;
	}





	public void setInventeringstyp(String inventeringstyp) {
		this.inventeringstyp = inventeringstyp;
		saved = false;
	}



	public void setOrsak(String orsak) {
		this.orsak = orsak;
		saved = false;
	}



	public void setStrandtyp(String strandtyp) {
		this.strandtyp = strandtyp;
		saved = false;
	}



	public void setKusttyp(String kusttyp) {
		this.kusttyp = kusttyp;
		saved = false;
	}



	
	public void setExponering(String exponering) {
		this.exponering = exponering;
		saved = false;
	}



	public void setGpseast(String gpseast) {
		this.gpseast = gpseast;
		saved = false;
	}



	public void setGpsnorth(String gpsnorth) {
		this.gpsnorth = gpsnorth;
		saved = false;
	}



	public void setRiktning(String riktning) {
		this.riktning = riktning;
		saved = false;
	}



	public void setSlutlengeo(String slutlengeo) {
		this.slutlengeo = slutlengeo;
		saved = false;
	}





	public void setSlutlensupra(String slutlensupra) {
		this.slutlensupra = slutlensupra;
		saved = false;
	}



	public void setSlutlenovan(String slutlenovan) {
		this.slutlenovan = slutlenovan;
		saved = false;
	}



	public void setLutninggeo(String lutninggeo) {
		this.lutninggeo = lutninggeo;
		saved = false;
	}



	public void setLutningsupra(String lutningsupra) {
		this.lutningsupra = lutningsupra;
		saved = false;
	}



	public void setVattendjup(String vattendjup) {
		this.vattendjup = vattendjup;
		saved = false;
	}



	public void setMarktypgeo(String marktypgeo) {
		this.marktypgeo = marktypgeo;
		saved = false;
	}



	public void setMarktypsupra(String marktypsupra) {
		this.marktypsupra = marktypsupra;
		saved = false;
	}



	public void setMarktypovan(String marktypovan) {
		this.marktypovan = marktypovan;
		saved = false;
	}



	public void setRekreation(String rekreation) {
		this.rekreation = rekreation;
		saved = false;
	}



	public void setRojning(String rojning) {
		this.rojning = rojning;
		saved = false;
	}



	public void setRojningtid(String rojningtid) {
		this.rojningtid = rojningtid;
		saved = false;
	}



	public void setBrygga(String brygga) {
		this.brygga = brygga;
		saved = false;
	}



	public void setStangsel(String stangsel) {
		this.stangsel = stangsel;
		saved = false;
	}







	public void setVasslen(String vasslen) {
		this.vasslen = vasslen;
		saved = false;
	}



	public void setVasstathet(String vasstathet) {
		this.vasstathet = vasstathet;
		saved = false;
	}



	public void setKriteriestrand(String kriteriestrand) {
		this.kriteriestrand = kriteriestrand;
		saved = false;
	}



	public void setKriterieovan(String kriterieovan) {
		this.kriterieovan = kriterieovan;
		saved = false;
	}



	public void setKlippamax(String klippamax) {
		this.klippamax = klippamax;
		saved = false;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getPyID() {
		return pyID;
	}



	public String getProvyta() {
		return provyta;
	}



	public String getRuta() {
		return ruta;
	}



	public String getYear() {
		return year;
	}



	public String getLagnummer() {
		return lagnummer;
	}



	public String getInventerare() {
		return inventerare;
	}



	

	public String getInventeringstyp() {
		return inventeringstyp;
	}



	public String getOrsak() {
		return orsak;
	}



	public String getStrandtyp() {
		return strandtyp;
	}



	public String getKusttyp() {
		return kusttyp;
	}




	public String getExponering() {
		return exponering;
	}



	public String getGpseast() {
		return gpseast;
	}



	public String getGpsnorth() {
		return gpsnorth;
	}



	public String getRiktning() {
		return riktning;
	}



	public String getSlutlengeo() {
		return slutlengeo;
	}




	public String getSlutlensupra() {
		return slutlensupra;
	}



	public String getSlutlenovan() {
		return slutlenovan;
	}



	public String getLutninggeo() {
		return lutninggeo;
	}



	public String getLutningsupra() {
		return lutningsupra;
	}



	public String getVattendjup() {
		return vattendjup;
	}



	public String getMarktypgeo() {
		return marktypgeo;
	}



	public String getMarktypsupra() {
		return marktypsupra;
	}



	public String getMarktypovan() {
		return marktypovan;
	}



	public String getRekreation() {
		return rekreation;
	}



	public String getRojning() {
		return rojning;
	}



	public String getRojningtid() {
		return rojningtid;
	}



	public String getBrygga() {
		return brygga;
	}



	public String getStangsel() {
		return stangsel;
	}



	public String getVasslen() {
		return vasslen;
	}



	public String getVasstathet() {
		return vasstathet;
	}



	public String getKriteriestrand() {
		return kriteriestrand;
	}



	public String getKriterieovan() {
		return kriterieovan;
	}



	public String getKlippamax() {
		return klippamax;
	}



	//Used if 'inventeras ej'	
	public Provyta(String _pyID, boolean isNormal) {
		pyID = _pyID;
		this.isNormal = isNormal;
		bl�lapp = "";
		saved = false;
	}

	public Provyta(String _pyID) {
		pyID = _pyID;
		//Create empty tables..
		tr�d = new Table(4,this);
		arter = new Table(4,this);
		buskar = new Table(5,this);
		vallar = new Table(12,this);
		habitat = new Table(6,this);
		dyner = new Table(5,this);
		
		bl�lapp="";
		saved = false;
		isNormal = true;
	}

	/**
	 * @return the startPEast
	 */
	public double getStartPEast() {
		return startPEast;
	}

	/**
	 * @return the startPNorth
	 */
	public double getStartPNorth() {
		return startPNorth;
	}

	/**
	 * @return the habitat
	 */
	public Table getHabitat() {
		return habitat;
	}

	/**
	 * @return the dyner
	 */
	public Table getDyner() {
		return dyner;
	}

	public String getVegtackningfaltsupra() {
		return vegtackningfaltsupra;
	}


}
