package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the zapas database table.
 * 
 */
@Entity
@Table(name="Zapas")
@NamedQuery(name="Zapa.findAll", query="SELECT z FROM Zapa z")
public class Zapa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	private int domaci_tym_skore;

	private int host_tym_skore;

	private int pocet_divaku;

	private String stadion;
	
	private int skupina;

	//bi-directional many-to-one association to Gol
	@OneToMany(mappedBy="zapa")
	private List<Gol> gols;

	//bi-directional many-to-one association to RozhodciZapa
	@OneToMany(mappedBy="zapa")
	private List<RozhodciZapa> rozhodciZapas;

	//bi-directional many-to-one association to Sestava
	@OneToMany(mappedBy="zapa")
	private List<Sestava> sestavas;

	//bi-directional many-to-one association to Stridani
	@OneToMany(mappedBy="zapa")
	private List<Stridani> stridanis;

	public Zapa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getDomaci_tym_skore() {
		return this.domaci_tym_skore;
	}

	public void setDomaci_tym_skore(int domaci_tym_skore) {
		this.domaci_tym_skore = domaci_tym_skore;
	}

	public int getHost_tym_skore() {
		return this.host_tym_skore;
	}

	public void setHost_tym_skore(int host_tym_skore) {
		this.host_tym_skore = host_tym_skore;
	}

	public int getPocet_divaku() {
		return this.pocet_divaku;
	}

	public void setPocet_divaku(int pocet_divaku) {
		this.pocet_divaku = pocet_divaku;
	}

	public String getStadion() {
		return this.stadion;
	}

	public void setStadion(String stadion) {
		this.stadion = stadion;
	}

	@JsonIgnore
	public List<Gol> getGols() {
		return this.gols;
	}

	public void setGols(List<Gol> gols) {
		this.gols = gols;
	}

	public Gol addGol(Gol gol) {
		getGols().add(gol);
		gol.setZapa(this);

		return gol;
	}

	public Gol removeGol(Gol gol) {
		getGols().remove(gol);
		gol.setZapa(null);

		return gol;
	}

	@JsonIgnore
	public List<RozhodciZapa> getRozhodciZapas() {
		return this.rozhodciZapas;
	}

	public void setRozhodciZapas(List<RozhodciZapa> rozhodciZapas) {
		this.rozhodciZapas = rozhodciZapas;
	}

	public RozhodciZapa addRozhodciZapa(RozhodciZapa rozhodciZapa) {
		getRozhodciZapas().add(rozhodciZapa);
		rozhodciZapa.setZapa(this);

		return rozhodciZapa;
	}

	public RozhodciZapa removeRozhodciZapa(RozhodciZapa rozhodciZapa) {
		getRozhodciZapas().remove(rozhodciZapa);
		rozhodciZapa.setZapa(null);

		return rozhodciZapa;
	}

	@JsonIgnore
	public List<Sestava> getSestavas() {
		return this.sestavas;
	}

	public void setSestavas(List<Sestava> sestavas) {
		this.sestavas = sestavas;
	}

	public Sestava addSestava(Sestava sestava) {
		getSestavas().add(sestava);
		sestava.setZapa(this);

		return sestava;
	}

	public Sestava removeSestava(Sestava sestava) {
		getSestavas().remove(sestava);
		sestava.setZapa(null);

		return sestava;
	}

	@JsonIgnore
	public List<Stridani> getStridanis() {
		return this.stridanis;
	}

	public void setStridanis(List<Stridani> stridanis) {
		this.stridanis = stridanis;
	}

	public Stridani addStridani(Stridani stridani) {
		getStridanis().add(stridani);
		stridani.setZapa(this);

		return stridani;
	}

	public Stridani removeStridani(Stridani stridani) {
		getStridanis().remove(stridani);
		stridani.setZapa(null);

		return stridani;
	}

	public int getSkupina() {
		return skupina;
	}

	public void setSkupina(int skupina) {
		this.skupina = skupina;
	}

}