package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the uzivatel database table.
 * 
 */
@Entity
@Table(name="Uzivatel")
@NamedQuery(name="Uzivatel.findAll", query="SELECT u FROM Uzivatel u")
public class Uzivatel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Timestamp datum_reg;

	private String email;

	private String heslo;

	private String jmeno;

	private int opravneni;

	private String prijmeni;

	//bi-directional many-to-one association to OblibeneTymy
	@OneToMany(mappedBy="uzivatel",cascade=CascadeType.REFRESH, orphanRemoval=true)
	private List<OblibeneTymy> oblibeneTymies;

	public Uzivatel() {
	}

	public Uzivatel(String email, String heslo, String jmeno, String prijmeni, int opravneni) {
	      this.email = email;
	      this.heslo = heslo;
	      this.jmeno = jmeno;
	      this.prijmeni = prijmeni;
	      this.opravneni = opravneni;
	}
	//kala
	@JsonIgnore
	public int getId() {
		return this.id;
	}
	//kala
	@JsonProperty
	public void setId(int id) {
		this.id = id;
	}
	//kala
	@JsonIgnore
	public Timestamp getDatum_reg() {
		return this.datum_reg;
	}

	public void setDatum_reg(Timestamp datum_reg) {
		this.datum_reg = datum_reg;
	}
	//kala
	@JsonIgnore
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	//kala
	@JsonIgnore
	public String getHeslo() {
		return this.heslo;
	}

	public void setHeslo(String heslo) {
		this.heslo = heslo;
	}
	//kala
	@JsonIgnore
	public String getJmeno() {
		return this.jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
	//kala
	@JsonIgnore
	public int getOpravneni() {
		return this.opravneni;
	}

	public void setOpravneni(int opravneni) {
		this.opravneni = opravneni;
	}
	//kala
	@JsonIgnore
	public String getPrijmeni() {
		return this.prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
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
		oblibeneTymy.setUzivatel(this);

		return oblibeneTymy;
	}

	public OblibeneTymy removeOblibeneTymy(OblibeneTymy oblibeneTymy) {
		getOblibeneTymies().remove(oblibeneTymy);
		oblibeneTymy.setUzivatel(null);

		return oblibeneTymy;
	}

}