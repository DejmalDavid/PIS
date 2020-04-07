package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sestava_hrac database table.
 * 
 */
@Entity
@Table(name="sestava_hrac")
@NamedQuery(name="SestavaHrac.findAll", query="SELECT s FROM SestavaHrac s")
public class SestavaHrac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Sestava
	@ManyToOne
	@JoinColumn(name="SestavaZapasId")
	private Sestava sestava1;

	//bi-directional many-to-one association to Sestava
	@ManyToOne
	@JoinColumn(name="SestavaTymId")
	private Sestava sestava2;

	//bi-directional many-to-one association to Hrac
	@ManyToOne
	@JoinColumn(name="HracId")
	private Hrac hrac;

	public SestavaHrac() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sestava getSestava1() {
		return this.sestava1;
	}

	public void setSestava1(Sestava sestava1) {
		this.sestava1 = sestava1;
	}

	public Sestava getSestava2() {
		return this.sestava2;
	}

	public void setSestava2(Sestava sestava2) {
		this.sestava2 = sestava2;
	}

	public Hrac getHrac() {
		return this.hrac;
	}

	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}

}