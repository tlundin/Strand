package com.teraim.strand;

import java.io.Serializable;
import java.util.Calendar;

import com.teraim.strand.dataobjekt.Table;

public class Provyta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483220563308165820L;
	/**
	 * 
	 */
	
	String pyID, provyta,ruta,year,lagnummer,inventerare,inventeringstyp,orsak,strandtyp,kusttyp,trädförekomst,exponering,gpseast,gpsnorth,
	riktning,slutlengeo,slutlenorange,slutlenkal,slutlensupra,slutlenovan,lutninggeo,lutningsupra,vattendjup,marktypgeo,marktypsupra,
	marktypovan,rekreation,rojning,rojningtid,brygga,stangsel,tacknfaltgeo,tacknfaltsupra,tacknbuskgeo,tacknbusksupra,tackntradgeo,
	tackntradsupra,vasslen,vasstathet,kriteriestrand,kriterieovan,klippamax,klippalutning,lutningextra;
	
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
	}



	Calendar datum;
	
	double startPEast,startPNorth;
	
	/*List <TrädListEntry>träd;
	
	List <BuskListEntry>buskar;
	
	List <ArtListEntry>arter;
	
	*/
	Table träd,buskar,arter;
	
	
	
	private boolean isLocked=false;

	public String getpyID() {
		return pyID;
	}
	
	
	
	/**
	 * @return the träd
	 */
	public Table getTräd() {
		return träd;
	}



	/**
	 * @param träd the träd to set
	 */
	public void setTräd(Table träd) {
		this.träd = träd;
	}



	/**
	 * @return the buskar
	 */
	public Table getBuskar() {
		return buskar;
	}



	/**
	 * @param buskar the buskar to set
	 */
	public void setBuskar(Table buskar) {
		this.buskar = buskar;
	}



	/**
	 * @return the arter
	 */
	public Table getArter() {
		return arter;
	}



	/**
	 * @param arter the arter to set
	 */
	public void setArter(Table arter) {
		this.arter = arter;
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
		this.pyID = pyID;
		this.provyta = provyta;
		this.ruta = ruta;
		this.year = year;
		this.lagnummer = lagnummer;
		this.inventerare = inventerare;
		this.datum = datum;
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
	}



	public void setProvyta(String provyta) {
		this.provyta = provyta;
	}



	public void setRuta(String ruta) {
		this.ruta = ruta;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public void setLagnummer(String lagnummer) {
		this.lagnummer = lagnummer;
	}



	public void setInventerare(String inventerare) {
		this.inventerare = inventerare;
	}



	public void setDatum(Calendar datum) {
		this.datum = datum;
	}



	public void setInventeringstyp(String inventeringstyp) {
		this.inventeringstyp = inventeringstyp;
	}



	public void setOrsak(String orsak) {
		this.orsak = orsak;
	}



	public void setStrandtyp(String strandtyp) {
		this.strandtyp = strandtyp;
	}



	public void setKusttyp(String kusttyp) {
		this.kusttyp = kusttyp;
	}



	public void setTrädförekomst(String trädförekomst) {
		this.trädförekomst = trädförekomst;
	}



	public void setExponering(String exponering) {
		this.exponering = exponering;
	}



	public void setGpseast(String gpseast) {
		this.gpseast = gpseast;
	}



	public void setGpsnorth(String gpsnorth) {
		this.gpsnorth = gpsnorth;
	}



	public void setRiktning(String riktning) {
		this.riktning = riktning;
	}



	public void setSlutlengeo(String slutlengeo) {
		this.slutlengeo = slutlengeo;
	}



	public void setSlutlenorange(String slutlenorange) {
		this.slutlenorange = slutlenorange;
	}



	public void setSlutlenkal(String slutlenkal) {
		this.slutlenkal = slutlenkal;
	}



	public void setSlutlensupra(String slutlensupra) {
		this.slutlensupra = slutlensupra;
	}



	public void setSlutlenovan(String slutlenovan) {
		this.slutlenovan = slutlenovan;
	}



	public void setLutninggeo(String lutninggeo) {
		this.lutninggeo = lutninggeo;
	}



	public void setLutningsupra(String lutningsupra) {
		this.lutningsupra = lutningsupra;
	}



	public void setVattendjup(String vattendjup) {
		this.vattendjup = vattendjup;
	}



	public void setMarktypgeo(String marktypgeo) {
		this.marktypgeo = marktypgeo;
	}



	public void setMarktypsupra(String marktypsupra) {
		this.marktypsupra = marktypsupra;
	}



	public void setMarktypovan(String marktypovan) {
		this.marktypovan = marktypovan;
	}



	public void setRekreation(String rekreation) {
		this.rekreation = rekreation;
	}



	public void setRojning(String rojning) {
		this.rojning = rojning;
	}



	public void setRojningtid(String rojningtid) {
		this.rojningtid = rojningtid;
	}



	public void setBrygga(String brygga) {
		this.brygga = brygga;
	}



	public void setStangsel(String stangsel) {
		this.stangsel = stangsel;
	}



	public void setTacknfaltgeo(String tacknfaltgeo) {
		this.tacknfaltgeo = tacknfaltgeo;
	}



	public void setTacknfaltsupra(String tacknfaltsupra) {
		this.tacknfaltsupra = tacknfaltsupra;
	}



	public void setTacknbuskgeo(String tacknbuskgeo) {
		this.tacknbuskgeo = tacknbuskgeo;
	}



	public void setTacknbusksupra(String tacknbusksupra) {
		this.tacknbusksupra = tacknbusksupra;
	}



	public void setTackntradgeo(String tackntradgeo) {
		this.tackntradgeo = tackntradgeo;
	}



	public void setTackntradsupra(String tackntradsupra) {
		this.tackntradsupra = tackntradsupra;
	}



	public void setVasslen(String vasslen) {
		this.vasslen = vasslen;
	}



	public void setVasstathet(String vasstathet) {
		this.vasstathet = vasstathet;
	}



	public void setKriteriestrand(String kriteriestrand) {
		this.kriteriestrand = kriteriestrand;
	}



	public void setKriterieovan(String kriterieovan) {
		this.kriterieovan = kriterieovan;
	}



	public void setKlippamax(String klippamax) {
		this.klippamax = klippamax;
	}



	public void setKlippalutning(String klippalutning) {
		this.klippalutning = klippalutning;
	}



	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
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



	public Calendar getDatum() {
		return datum;
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
	}

	public void setStartPNorth(double n) {
		startPEast = n;
	}

	public boolean isLocked() {
		return isLocked;
	}



	public Provyta(String _pyID) {
		pyID = _pyID;
		datum = Calendar.getInstance();
		//Create empty tables..
		träd = new Table(4);
		arter = new Table(4);
		buskar = new Table(5);
	}
}
