package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the rozhodci database table.
 * 
 */
@Entity
@Table(name="Rozhodci")
@NamedQuery(name="Rozhodci.findAll", query="SELECT r FROM Rozhodci r")
public class Rozhodci implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String jmeno;

	private String prijmeni;

	private String zeme;

	//bi-directional many-to-one association to RozhodciZapa
	@OneToMany(mappedBy="rozhodci")
	private List<RozhodciZapa> rozhodciZapas;

	public Rozhodci() {
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

	public String getPrijmeni() {
		return this.prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public String getZeme() {
		return this.zeme;
	}

	public void setZeme(String zeme) {
		this.zeme = zeme;
	}

	@JsonIgnore
	public List<RozhodciZapa> getRozhodciZapas() {
		return this.rozhodciZapas;
	}

    @JsonIgnore
	public void setRozhodciZapas(List<RozhodciZapa> rozhodciZapas) {
		this.rozhodciZapas = rozhodciZapas;
	}

	@JsonIgnore
	public RozhodciZapa addRozhodciZapa(RozhodciZapa rozhodciZapa) {
		getRozhodciZapas().add(rozhodciZapa);
		rozhodciZapa.setRozhodci(this);

		return rozhodciZapa;
	}

	@JsonIgnore
	public RozhodciZapa removeRozhodciZapa(RozhodciZapa rozhodciZapa) {
		getRozhodciZapas().remove(rozhodciZapa);
		rozhodciZapa.setRozhodci(null);

		return rozhodciZapa;
	}

}