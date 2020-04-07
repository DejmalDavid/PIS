package org.fit.pis.models;

import java.sql.Timestamp;

public class GolOutput
{
	private int id;
	private Timestamp gol_cas;
	private String gol_typ;
	private String polovina_zapasu;
	private int zapa;
	private String hrac1;
	private String hrac2;
	
	public GolOutput(	 int id,
	 Timestamp gol_cas,
	 String gol_typ,
	 String polovina_zapasu,
	 int zapa,
	 String hrac1,
	 String hrac2)
	{
		this.id=id;
		this.gol_cas=gol_cas;
		this.gol_typ=gol_typ;
		this.polovina_zapasu=polovina_zapasu;
		this.zapa=zapa;
		this.hrac1=hrac1;
		this.hrac2=hrac2;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getGol_cas() {
		return gol_cas;
	}

	public void setGol_cas(Timestamp gol_cas) {
		this.gol_cas = gol_cas;
	}

	public String getGol_typ() {
		return gol_typ;
	}

	public void setGol_typ(String gol_typ) {
		this.gol_typ = gol_typ;
	}

	public String getPolovina_zapasu() {
		return polovina_zapasu;
	}

	public void setPolovina_zapasu(String polovina_zapasu) {
		this.polovina_zapasu = polovina_zapasu;
	}

	public int getZapa() {
		return zapa;
	}

	public void setZapa(int zapa) {
		this.zapa = zapa;
	}

	public String getHrac1() {
		return hrac1;
	}

	public void setHrac1(String hrac1) {
		this.hrac1 = hrac1;
	}

	public String getHrac2() {
		return hrac2;
	}

	public void setHrac2(String hrac2) {
		this.hrac2 = hrac2;
	}

}