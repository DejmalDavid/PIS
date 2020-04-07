package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oblibene_tymy database table.
 * 
 */
@Entity
@Table(name="oblibene_tymy")
@NamedQuery(name="OblibeneTymy.findAll", query="SELECT o FROM OblibeneTymy o")
public class OblibeneTymy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Uzivatel
	@ManyToOne
	private Uzivatel uzivatel;

	//bi-directional many-to-one association to Tym
	@ManyToOne
	@JoinColumn(name="Tymid")
	private Tym tym;

	public OblibeneTymy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Uzivatel getUzivatel() {
		return this.uzivatel;
	}

	public void setUzivatel(Uzivatel uzivatel) {
		this.uzivatel = uzivatel;
	}

	public Tym getTym() {
		return this.tym;
	}

	public void setTym(Tym tym) {
		this.tym = tym;
	}

}