/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_D_DURATA_TIPO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_DURATA_TIPO\"")
@NamedQueries({ 
@NamedQuery(name="EsenzioneDDurataTipo.findAll", query="SELECT e FROM EsenzioneDDurataTipo e"),
@NamedQuery(name="EsenzioneDDurataTipo.findperskduratatipo", query="SELECT e FROM EsenzioneDDurataTipo e where e.skDurataTipo = :skduratatipo")
})
public class EsenzioneDDurataTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_DURATA_TIPO\"", unique=true, nullable=false)
	private Integer skDurataTipo;

	@Column(name="\"COD_DURATA_TIPO\"", nullable=false, length=2147483647)
	private String codDurataTipo;

	@Column(name="\"DESC_DURATA_TIPO\"", nullable=false, length=2147483647)
	private String descDurataTipo;

	//bi-directional many-to-one association to EsenzioneDDiagnosi
	@OneToMany(mappedBy="esenzioneDDurataTipo")
	private List<EsenzioneDDiagnosi> esenzioneDDiagnosis;

	public EsenzioneDDurataTipo() {
	}

	public Integer getSkDurataTipo() {
		return this.skDurataTipo;
	}

	public void setSkDurataTipo(Integer skDurataTipo) {
		this.skDurataTipo = skDurataTipo;
	}

	public String getCodDurataTipo() {
		return this.codDurataTipo;
	}

	public void setCodDurataTipo(String codDurataTipo) {
		this.codDurataTipo = codDurataTipo;
	}

	public String getDescDurataTipo() {
		return this.descDurataTipo;
	}

	public void setDescDurataTipo(String descDurataTipo) {
		this.descDurataTipo = descDurataTipo;
	}

	public List<EsenzioneDDiagnosi> getEsenzioneDDiagnosis() {
		return this.esenzioneDDiagnosis;
	}

	public void setEsenzioneDDiagnosis(List<EsenzioneDDiagnosi> esenzioneDDiagnosis) {
		this.esenzioneDDiagnosis = esenzioneDDiagnosis;
	}

	public EsenzioneDDiagnosi addEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
		getEsenzioneDDiagnosis().add(esenzioneDDiagnosi);
		esenzioneDDiagnosi.setEsenzioneDDurataTipo(this);

		return esenzioneDDiagnosi;
	}

	public EsenzioneDDiagnosi removeEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
		getEsenzioneDDiagnosis().remove(esenzioneDDiagnosi);
		esenzioneDDiagnosi.setEsenzioneDDurataTipo(null);

		return esenzioneDDiagnosi;
	}

}