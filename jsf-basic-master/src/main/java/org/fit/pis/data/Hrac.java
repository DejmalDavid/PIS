package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the hrac database table.
 * 
 */

@Entity
@Table(name="Hrac")
@NamedQuery(name="Hrac.findAll", query="SELECT h FROM Hrac h")
public class Hrac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String jmeno;

	private int pozice;

	private String prijmeni;

	private String skill;

	private int vek;
	
	//bi-directional many-to-one association to Tym
	@ManyToOne
	@JoinColumn(name="Tym_id")
	private Tym tym;

	//bi-directional many-to-one association to Gol
	@OneToMany(mappedBy="hrac1")
	private List<Gol> gols1;

	//bi-directional many-to-one association to Gol
	@OneToMany(mappedBy="hrac2")
	private List<Gol> gols2;

	//bi-directional many-to-one association to SestavaHrac
	@OneToMany(mappedBy="hrac")
	private List<SestavaHrac> sestavaHracs;

	public Hrac() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJmeno() {
		return this.jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public int getPozice() {
		return this.pozice;
	}

	public void setPozice(int pozice) {
		this.pozice = pozice;
	}

	public String getPrijmeni() {
		return this.prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getVek() {
		return this.vek;
	}

	public void setVek(int vek) {
		this.vek = vek;
	}

	@JsonIgnore
	public List<Gol> getGols1() {
		return this.gols1;
	}

	public void setGols1(List<Gol> gols1) {
		this.gols1 = gols1;
	}

	public Gol addGols1(Gol gols1) {
		getGols1().add(gols1);
		gols1.setHrac1(this);

		return gols1;
	}

	public Gol removeGols1(Gol gols1) {
		getGols1().remove(gols1);
		gols1.setHrac1(null);

		return gols1;
	}

	@JsonIgnore
	public List<Gol> getGols2() {
		return this.gols2;
	}

	public void setGols2(List<Gol> gols2) {
		this.gols2 = gols2;
	}

	public Gol addGols2(Gol gols2) {
		getGols2().add(gols2);
		gols2.setHrac2(this);

		return gols2;
	}

	public Gol removeGols2(Gol gols2) {
		getGols2().remove(gols2);
		gols2.setHrac2(null);

		return gols2;
	}

	@JsonIgnore
	public List<SestavaHrac> getSestavaHracs() {
		return this.sestavaHracs;
	}

	public void setSestavaHracs(List<SestavaHrac> sestavaHracs) {
		this.sestavaHracs = sestavaHracs;
	}

	public SestavaHrac addSestavaHrac(SestavaHrac sestavaHrac) {
		getSestavaHracs().add(sestavaHrac);
		sestavaHrac.setHrac(this);

		return sestavaHrac;
	}

	public SestavaHrac removeSestavaHrac(SestavaHrac sestavaHrac) {
		getSestavaHracs().remove(sestavaHrac);
		sestavaHrac.setHrac(null);

		return sestavaHrac;
	}

}