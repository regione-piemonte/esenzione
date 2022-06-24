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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzioneAuraTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;

/**
 * The persistent class for the "ESENZIONE_D_ESENZIONE" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_ESENZIONE\"")
@NamedQueries({ 
  @NamedQuery(name="EsenzioneDEsenzione.findAll", query="SELECT e FROM EsenzioneDEsenzione e"),
  @NamedQuery(name = "EsenzioneDEsenzione.findByDiagnosiId", query = "SELECT ee FROM EsenzioneDEsenzione ee, EsenzioneDDiagnosi ed  WHERE ed.skEsenzione = ee.skEsenzione AND ed.skDiagnosi = :idDiagnosi")
  })
public class EsenzioneDEsenzione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_ESENZIONE\"", unique=true, nullable=false)
	private Integer skEsenzione;

	@Column(name="\"COD_ESENZIONE\"", nullable=false, length=2147483647)
	private String codEsenzione;

	@Column(name="\"DESC_ESENZIONE\"", nullable=false, length=2147483647)
	private String descEsenzione;

	@Column(name="\"FLAG_ESENZIONE_048\"", nullable=false)
	private Boolean flagEsenzione048;

	@Column(name="\"SK_GRUPPO\"", nullable=false)
	private Long skGruppo;

	@Column(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"", nullable = false)
	private Long skTipologiaEsenzioneAura;

	@Column(name = "\"RIFERIMENTO_LEGISLATIVO\"")
	private String riferimentoLegislativo;

	//bi-directional many-to-one association to EsenzioneDDiagnosi
	@OneToMany(mappedBy="esenzioneDEsenzione")
	private List<EsenzioneDDiagnosi> esenzioneDDiagnosis;

	//bi-directional many-to-one association to EsenzioneDGruppoEsenzioni
	@ManyToOne
	@JoinColumn(name = "\"SK_GRUPPO\"", insertable = false, updatable = false)
	private EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni;

	// bi-directional many-to-one association to EsenzioneDGruppoEsenzioni
	@ManyToOne
	@JoinColumn(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"", insertable = false, updatable = false)
	private EsenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo;

	public EsenzioneDEsenzione() {
	}

	public Integer getSkEsenzione() {
		return this.skEsenzione;
	}

	public void setSkEsenzione(Integer skEsenzione) {
		this.skEsenzione = skEsenzione;
	}

	public String getCodEsenzione() {
		return this.codEsenzione;
	}

	public void setCodEsenzione(String codEsenzione) {
		this.codEsenzione = codEsenzione;
	}

	public String getDescEsenzione() {
		return this.descEsenzione;
	}

	public void setDescEsenzione(String descEsenzione) {
		this.descEsenzione = descEsenzione;
	}

	public Boolean getFlagEsenzione048() {
		return this.flagEsenzione048;
	}

	public void setFlagEsenzione048(Boolean flagEsenzione048) {
		this.flagEsenzione048 = flagEsenzione048;
	}

	public Long getSkGruppo() {
		return this.skGruppo;
	}

	public void setSkGruppo(Long skGruppo) {
		this.skGruppo = skGruppo;
	}

	public String getRiferimentoLegislativo() {
		return riferimentoLegislativo;
	}

	public void setRiferimentoLegislativo(String riferimentoLegislativo) {
		this.riferimentoLegislativo = riferimentoLegislativo;
	}

	public List<EsenzioneDDiagnosi> getEsenzioneDDiagnosis() {
		return this.esenzioneDDiagnosis;
	}

	public void setEsenzioneDDiagnosis(List<EsenzioneDDiagnosi> esenzioneDDiagnosis) {
		this.esenzioneDDiagnosis = esenzioneDDiagnosis;
	}

	public EsenzioneDDiagnosi addEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
		getEsenzioneDDiagnosis().add(esenzioneDDiagnosi);
		esenzioneDDiagnosi.setEsenzioneDEsenzione(this);

		return esenzioneDDiagnosi;
	}

	public EsenzioneDDiagnosi removeEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
		getEsenzioneDDiagnosis().remove(esenzioneDDiagnosi);
		esenzioneDDiagnosi.setEsenzioneDEsenzione(null);

		return esenzioneDDiagnosi;
	}

	public EsenzioneDGruppoEsenzioni getEsenzioneDGruppoEsenzioni() {
		return this.esenzioneDGruppoEsenzioni;
	}

	public void setEsenzioneDGruppoEsenzioni(EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni) {
		this.esenzioneDGruppoEsenzioni = esenzioneDGruppoEsenzioni;
	}

	public Long getSkTipologiaEsenzioneAura() {
		return skTipologiaEsenzioneAura;
	}

	public void setSkTipologiaEsenzioneAura(Long skTipologiaEsenzioneAura) {
		this.skTipologiaEsenzioneAura = skTipologiaEsenzioneAura;
	}

	public EsenzioneDEsenzioneAuraTipo getEsenzioneDEsenzioneAuraTipo() {
		return esenzioneDEsenzioneAuraTipo;
	}

	public void setEsenzioneDEsenzioneAuraTipo(EsenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo) {
		this.esenzioneDEsenzioneAuraTipo = esenzioneDEsenzioneAuraTipo;
	}

}