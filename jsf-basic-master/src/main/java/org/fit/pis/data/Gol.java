package org.fit.pis.data;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.sql.Timestamp;


/**
 * The persistent class for the gol database table.
 * 
 */
@Entity
@Table(name="Gol")
@NamedQuery(name="Gol.findAll", query="SELECT g FROM Gol g")
public class Gol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int gol_cas;

	private String gol_typ;

	private String polovina_zapasu;

	//bi-directional many-to-one association to Zapa
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="ZapasId")
	private Zapa zapa;

	//bi-directional many-to-one association to Hrac
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="HracId_assist")
	private Hrac hrac1;

	//bi-directional many-to-one association to Hrac
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="HracId")
	private Hrac hrac2;

	public Gol() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGol_cas() {
		return this.gol_cas;
	}

	public void setGol_cas(int gol_cas) {
		this.gol_cas = gol_cas;
	}

	public String getGol_typ() {
		return this.gol_typ;
	}

	public void setGol_typ(String gol_typ) {
		this.gol_typ = gol_typ;
	}

	public String getPolovina_zapasu() {
		return this.polovina_zapasu;
	}

	public void setPolovina_zapasu(String polovina_zapasu) {
		this.polovina_zapasu = polovina_zapasu;
	}

	@JsonIgnore
	public Zapa getZapa() {
		return this.zapa;
	}

	public void setZapa(Zapa zapa) {
		this.zapa = zapa;
	}
	
	@JsonIgnore
	public Hrac getHrac1() {
		return this.hrac1;
	}

	public void setHrac1(Hrac hrac1) {
		this.hrac1 = hrac1;
	}

	@JsonIgnore
	public Hrac getHrac2() {
		return this.hrac2;
	}

	public void setHrac2(Hrac hrac2) {
		this.hrac2 = hrac2;
	}

}