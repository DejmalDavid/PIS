package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tym database table.
 * 
 */
@Entity
@Table(name="Tym")
@NamedQuery(name="Tym.findAll", query="SELECT t FROM Tym t")
public class Tym implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String kouc;

	private String mesto;

	private String nazev;

	private int skupina;

	//bi-directional many-to-one association to Gol
	@OneToMany(mappedBy="tym",cascade=CascadeType.REFRESH)
	private List<Hrac> hracs;
		
	//bi-directional many-to-one association to OblibeneTymy
	@OneToMany(mappedBy="tym",cascade=CascadeType.REFRESH)
	private List<OblibeneTymy> oblibeneTymies;

	//bi-directional many-to-one association to Sestava
	@OneToMany(mappedBy="tym",cascade=CascadeType.REFRESH)
	private List<Sestava> sestavas;

	public Tym() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKouc() {
		return this.kouc;
	}

	public void setKouc(String kouc) {
		this.kouc = kouc;
	}

	public String getMesto() {
		return this.mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getNazev() {
		return this.nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public int getSkupina() {
		return this.skupina;
	}

	public void setSkupina(int skupina) {
		this.skupina = skupina;
	}

	@JsonIgnore
	public List<OblibeneTymy> getOblibeneTymies() {
		return this.oblibeneTymies;
	}

	public void setOblibeneTymies(List<OblibeneTymy> oblibeneTymies) {
		this.oblibeneTymies = oblibeneTymies;
	}

	public OblibeneTymy addOblibeneTymy(OblibeneTymy oblibeneTymy) {
		getOblibeneTymies().add(oblibeneTymy);
		oblibeneTymy.setTym(this);

		return oblibeneTymy;
	}

	public OblibeneTymy removeOblibeneTymy(OblibeneTymy oblibeneTymy) {
		getOblibeneTymies().remove(oblibeneTymy);
		oblibeneTymy.setTym(null);

		return oblibeneTymy;
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
		sestava.setTym(this);

		return sestava;
	}

	public Sestava removeSestava(Sestava sestava) {
		getSestavas().remove(sestava);
		sestava.setTym(null);

		return sestava;
	}

}