package com.teraim.strand;

import java.io.Serializable;
import java.util.Calendar;

import com.teraim.strand.dataobjekt.Table;

public class Provyta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483220563308165825L;
	/**
	 * 
	 */
	
	String pyID, provyta,ruta,year,lagnummer,inventerare,inventeringstyp,orsak,strandtyp,kusttyp,trädförekomst,exponering,gpseast,gpsnorth,
	riktning,slutlengeo,slutlenorange,slutlenkal,slutlensupra,slutlenovan,lutninggeo,lutningsupra,vattendjup,marktypgeo,marktypsupra,
	marktypovan,rekreation,rojning,rojningtid,brygga,stangsel,tacknfaltgeo,tacknfaltsupra,tacknbuskgeo,tacknbusksupra,tackntradgeo,
	tackntradsupra,vasslen,vasstathet,kriteriestrand,kriterieovan,klippamax,klippalutning,lutningextra;
	
	
	//Sparar kommentarer.
	String blålapp;

	//Räknare för default.
	int driftVallsC = 1;
	
	//Sweref koordinater markerad start.
	double startPEast,startPNorth;
	
	//Tabeller
	Table träd,buskar,arter,vallar,habitat,dyner;	
	//Matris
	String[][] substrat;
	
	//Flagga om ändring gjorts.
	boolean saved = false;
	
	

	public boolean isSaved() {
		return saved;
	}
	
	public void setSaved(boolean s) {
		saved = s;
	}
	
	//Toggla lås för ändringar.
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isLocked() {
		return isLocked;
	}
	
	//This is true if provyta is set as 'inventeras ej'
	public boolean isNormal() {
		return isNormal;
	}
	


	/**
	 * @return the blålapp
	 */
	public String getBlålapp() {
		return blålapp;
	}




	/**
	 * @param blålapp the blålapp to set
	 */
	public void setBlålapp(String blålapp) {
		this.blålapp = blålapp;
		saved = false;
	}

	/**
	 * @return the driftVallsC..increase
	 */
	public int getDriftVallsC() {
		return driftVallsC++;
	}
	
	/**
	 * @return the substrat
	 */
	public String[][] getSubstrat() {
		return substrat;
	}


	/**
	 * @param substrat the substrat to set
	 */
	public void setSubstrat(String[][] substrat) {
		this.substrat = substrat;
		saved = false;
	}



	private boolean isLocked=false;
	private boolean isNormal=true;

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


	
	/**
	 * @return the träd
	 */
	public Table getTräd() {
		return träd;
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



	public Provyta(String pyID, String provyta, String ruta, String year,
			String lagnummer, String inventerare, Calendar datum,
			String inventeringstyp, String orsak, String strandtyp,
			String kusttyp, String trädförekomst, String exponering,
			String gpseast, String gpsnorth, String riktning,
			String slutlengeo, String slutlenorange, String slutlenkal,
			String slutlensupra, String slutlenovan, String lutninggeo,
			String lutningsupra, String vattendjup, String marktypgeo,
			String marktypsupra, String marktypovan, String rekreation,
			String rojning, String rojningtid, String brygga, String stangsel,
			String tacknfaltgeo, String tacknfaltsupra, String tacknbuskgeo,
			String tacknbusksupra, String tackntradgeo, String tackntradsupra,
			String vasslen, String vasstathet, String kriteriestrand,
			String kriterieovan, String klippamax, String klippalutning) {
		super();
		saved = false;
		this.pyID = pyID;
		this.provyta = provyta;
		this.ruta = ruta;
		this.year = year;
		this.lagnummer = lagnummer;
		this.inventerare = inventerare;
		this.inventeringstyp = inventeringstyp;
		this.orsak = orsak;
		this.strandtyp = strandtyp;
		this.kusttyp = kusttyp;
		this.trädförekomst = trädförekomst;
		this.exponering = exponering;
		this.gpseast = gpseast;
		this.gpsnorth = gpsnorth;
		this.riktning = riktning;
		this.slutlengeo = slutlengeo;
		this.slutlenorange = slutlenorange;
		this.slutlenkal = slutlenkal;
		this.slutlensupra = slutlensupra;
		this.slutlenovan = slutlenovan;
		this.lutninggeo = lutninggeo;
		this.lutningsupra = lutningsupra;
		this.vattendjup = vattendjup;
		this.marktypgeo = marktypgeo;
		this.marktypsupra = marktypsupra;
		this.marktypovan = marktypovan;
		this.rekreation = rekreation;
		this.rojning = rojning;
		this.rojningtid = rojningtid;
		this.brygga = brygga;
		this.stangsel = stangsel;
		this.tacknfaltgeo = tacknfaltgeo;
		this.tacknfaltsupra = tacknfaltsupra;
		this.tacknbuskgeo = tacknbuskgeo;
		this.tacknbusksupra = tacknbusksupra;
		this.tackntradgeo = tackntradgeo;
		this.tackntradsupra = tackntradsupra;
		this.vasslen = vasslen;
		this.vasstathet = vasstathet;
		this.kriteriestrand = kriteriestrand;
		this.kriterieovan = kriterieovan;
		this.klippamax = klippamax;
		this.klippalutning = klippalutning;
	}


	public void setPyID(String pyID) {
		this.pyID = pyID;
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



	public void setTrädförekomst(String trädförekomst) {
		this.trädförekomst = trädförekomst;
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



	public void setSlutlenorange(String slutlenorange) {
		this.slutlenorange = slutlenorange;
		saved = false;
	}
	



	public void setSlutlenkal(String slutlenkal) {
		this.slutlenkal = slutlenkal;
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



	public void setTacknfaltgeo(String tacknfaltgeo) {
		this.tacknfaltgeo = tacknfaltgeo;
		saved = false;
	}



	public void setTacknfaltsupra(String tacknfaltsupra) {
		this.tacknfaltsupra = tacknfaltsupra;
		saved = false;
	}



	public void setTacknbuskgeo(String tacknbuskgeo) {
		this.tacknbuskgeo = tacknbuskgeo;
		saved = false;
	}



	public void setTacknbusksupra(String tacknbusksupra) {
		this.tacknbusksupra = tacknbusksupra;
		saved = false;
	}



	public void setTackntradgeo(String tackntradgeo) {
		this.tackntradgeo = tackntradgeo;
		saved = false;
	}



	public void setTackntradsupra(String tackntradsupra) {
		this.tackntradsupra = tackntradsupra;
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



	public void setKlippalutning(String klippalutning) {
		this.klippalutning = klippalutning;
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



	public String getTrädförekomst() {
		return trädförekomst;
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



	public String getSlutlenorange() {
		return slutlenorange;
	}



	public String getSlutlenkal() {
		return slutlenkal;
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



	public String getTacknfaltgeo() {
		return tacknfaltgeo;
	}



	public String getTacknfaltsupra() {
		return tacknfaltsupra;
	}



	public String getTacknbuskgeo() {
		return tacknbuskgeo;
	}



	public String getTacknbusksupra() {
		return tacknbusksupra;
	}



	public String getTackntradgeo() {
		return tackntradgeo;
	}



	public String getTackntradsupra() {
		return tackntradsupra;
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



	public String getKlippalutning() {
		return klippalutning;
	}

	public void setStartPEast(double e) {
		startPEast = e;
		saved = false;
	}

	public void setStartPNorth(double n) {
		startPEast = n;
		saved = false;
	}

	

	//Used if 'inventeras ej'	
	public Provyta(String _pyID, boolean isNormal) {
		pyID = _pyID;
		this.isNormal = isNormal;
		blålapp = "";
		saved = false;
	}

	public Provyta(String _pyID) {
		pyID = _pyID;
		//Create empty tables..
		träd = new Table(4,this);
		arter = new Table(4,this);
		buskar = new Table(5,this);
		vallar = new Table(12,this);
		habitat = new Table(4,this);
		dyner = new Table(4,this);
		
		blålapp="";
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


}
