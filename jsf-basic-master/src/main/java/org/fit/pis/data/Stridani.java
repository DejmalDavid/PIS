package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the stridani database table.
 * 
 */
@Entity
@Table(name="Stridani")
@NamedQuery(name="Stridani.findAll", query="SELECT s FROM Stridani s")
public class Stridani implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cas;

	private int hrac_id_in;

	private int hrac_id_out;

	//bi-directional many-to-one association to Zapa
	@ManyToOne
	@JoinColumn(name="Zapas_id")
	private Zapa zapa;

	public Stridani() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCas() {
		return this.cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}

	public int getHrac_id_in() {
		return this.hrac_id_in;
	}

	public void setHrac_id_in(int hrac_id_in) {
		this.hrac_id_in = hrac_id_in;
	}

	public int getHrac_id_out() {
		return this.hrac_id_out;
	}

	public void setHrac_id_out(int hrac_id_out) {
		this.hrac_id_out = hrac_id_out;
	}

	public Zapa getZapa() {
		return this.zapa;
	}

	public void setZapa(Zapa zapa) {
		this.zapa = zapa;
	}

}