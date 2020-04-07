package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rozhodci_zapas database table.
 * 
 */
@Entity
@Table(name="rozhodci_zapas")
@NamedQuery(name="RozhodciZapa.findAll", query="SELECT r FROM RozhodciZapa r")
public class RozhodciZapa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Zapa
	@ManyToOne
	@JoinColumn(name="ZapasID")
	private Zapa zapa;

	//bi-directional many-to-one association to Rozhodci
	@ManyToOne
	@JoinColumn(name="RozhodciID")
	private Rozhodci rozhodci;

	public RozhodciZapa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Zapa getZapa() {
		return this.zapa;
	}

	public void setZapa(Zapa zapa) {
		this.zapa = zapa;
	}

	public Rozhodci getRozhodci() {
		return this.rozhodci;
	}

	public void setRozhodci(Rozhodci rozhodci) {
		this.rozhodci = rozhodci;
	}

}