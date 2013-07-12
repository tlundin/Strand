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
	
	String pyID, provyta,ruta,year,lagnummer,inventerare,datum,inventeringstyp,orsak,strandtyp,kusttyp,trädförekomst,exponering,gpseast,gpsnorth,
	riktning,slutlengeo,slutlenorange,slutlenkal,slutlensupra,slutlenovan,lutninggeo,lutningsupra,vattendjup,marktypgeo,marktypsupra,
	marktypovan,rekreation,rojning,rojningtid,brygga,stangsel,tacknfaltgeo,tacknfaltsupra,tacknbuskgeo,tacknbusksupra,tackntradgeo,
	tackntradsupra,vasslen,vasstathet,kriteriestrand,kriterieovan,klippamax,klippalutning;

	public String getpyID() {
		return pyID;
	}
	
	
	
	public Provyta(String pyID, String provyta, String ruta, String year,
			String lagnummer, String inventerare, String datum,
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


	//TODO: Ta bort.
	//Temporär konstruktor
	public Provyta(String _pyID) {
		pyID = _pyID;
	}
}
