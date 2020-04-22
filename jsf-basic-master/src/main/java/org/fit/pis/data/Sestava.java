package org.fit.pis.data;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the sestava database table.
 * 
 */
@Entity
@Table(name="Sestava")
@NamedQuery(name="Sestava.findAll", query="SELECT s FROM Sestava s")
public class Sestava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int hostujici;

	private int kapitan_id;

	//bi-directional many-to-one association to Zapa
	@ManyToOne
	@JoinColumn(name="ZapasID")
	private Zapa zapa;

	//bi-directional many-to-one association to Tym
	@ManyToOne
	@JoinColumn(name="TymID")
	private Tym tym;

	//bi-directional many-to-one association to SestavaHrac
	@OneToMany(mappedBy="sestava1",cascade=CascadeType.REFRESH)
	private List<SestavaHrac> sestavaHracs1;

	//bi-directional many-to-one association to SestavaHrac
	@OneToMany(mappedBy="sestava2",cascade=CascadeType.REFRESH)
	private List<SestavaHrac> sestavaHracs2;

	public Sestava() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHostujici() {
		return this.hostujici;
	}

	public void setHostujici(int hostujici) {
		this.hostujici = hostujici;
	}

	public int getKapitan_id() {
		return this.kapitan_id;
	}

	public void setKapitan_id(int kapitan_id) {
		this.kapitan_id = kapitan_id;
	}

	public Zapa getZapa() {
		return this.zapa;
	}

	public void setZapa(Zapa zapa) {
		this.zapa = zapa;
	}

	public Tym getTym() {
		return this.tym;
	}

	public void setTym(Tym tym) {
		this.tym = tym;
	}

	@JsonIgnore
	public List<SestavaHrac> getSestavaHracs1() {
		return this.sestavaHracs1;
	}

	public void setSestavaHracs1(List<SestavaHrac> sestavaHracs1) {
		this.sestavaHracs1 = sestavaHracs1;
	}

	public SestavaHrac addSestavaHracs1(SestavaHrac sestavaHracs1) {
		getSestavaHracs1().add(sestavaHracs1);
		sestavaHracs1.setSestava1(this);

		return sestavaHracs1;
	}

	public SestavaHrac removeSestavaHracs1(SestavaHrac sestavaHracs1) {
		getSestavaHracs1().remove(sestavaHracs1);
		sestavaHracs1.setSestava1(null);

		return sestavaHracs1;
	}

	@JsonIgnore
	public List<SestavaHrac> getSestavaHracs2() {
		return this.sestavaHracs2;
	}

	public void setSestavaHracs2(List<SestavaHrac> sestavaHracs2) {
		this.sestavaHracs2 = sestavaHracs2;
	}

	public SestavaHrac addSestavaHracs2(SestavaHrac sestavaHracs2) {
		getSestavaHracs2().add(sestavaHracs2);
		sestavaHracs2.setSestava2(this);

		return sestavaHracs2;
	}

	public SestavaHrac removeSestavaHracs2(SestavaHrac sestavaHracs2) {
		getSestavaHracs2().remove(sestavaHracs2);
		sestavaHracs2.setSestava2(null);

		return sestavaHracs2;
	}

}