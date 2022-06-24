/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_D_ESENZIONE_AURA_ARCHIVIO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_ESENZIONE_AURA_ARCHIVIO\"")
@NamedQueries({ 
  @NamedQuery(name="EsenzioneDEsenzioneAuraArchivio.findAll", query="SELECT e FROM EsenzioneDEsenzioneAuraArchivio e"),
  @NamedQuery(name="EsenzioneDEsenzioneAuraArchivio.findByCodEsenzioneAndCodDiagnosi", query="SELECT e FROM EsenzioneDEsenzioneAuraArchivio e WHERE e.codEsenzione = :codEsenzione AND e.codDiagnosi = :codDiagnosi")
  })
public class EsenzioneDEsenzioneAuraArchivio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_ESENZIONE_ARCHIVIO\"", unique=true, nullable=false)
	private Integer skEsenzioneArchivio;

	@Column(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"", nullable = false)
	private Long skTipologiaEsenzioneAura;
	
	@Column(name="\"COD_ESENZIONE\"", nullable=false, length=2147483647)
	private String codEsenzione;

	@Column(name="\"DESC_ESENZIONE\"", length=2147483647)
	private String descEsenzione;
	
	@Column(name="\"COD_DIAGNOSI\"", nullable=false, length=2147483647)
	private String codDiagnosi;

	@Column(name="\"DESC_DIAGNOSI\"", length=2147483647)
	private String descDiagnosi;
	
	@Column(name="\"DAT_INIZIO_VALIDITA\"", nullable=false)
	private Timestamp datInizioValidita;
	
	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;
	
	@Column(name="\"PRESTAZIONE_ESENTE\"", length=2147483647)
	private String prestazioneEsente;

	// bi-directional many-to-one association to EsenzioneDGruppoEsenzioni
	@ManyToOne
	@JoinColumn(name = "\"SK_TIPOLOGIA_ESENZIONE_AURA\"", insertable = false, updatable = false)
	private EsenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo;

	public EsenzioneDEsenzioneAuraArchivio() {
	}

	public Integer getSkEsenzioneArchivio() {
		return skEsenzioneArchivio;
	}

	public void setSkEsenzioneArchivio(Integer skEsenzioneArchivio) {
		this.skEsenzioneArchivio = skEsenzioneArchivio;
	}

	public Long getSkTipologiaEsenzioneAura() {
		return skTipologiaEsenzioneAura;
	}
	
	public void setSkTipologiaEsenzioneAura(Long skTipologiaEsenzioneAura) {
		this.skTipologiaEsenzioneAura = skTipologiaEsenzioneAura;
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
	
	public String getCodDiagnosi() {
		return codDiagnosi;
	}

	public void setCodDiagnosi(String codDiagnosi) {
		this.codDiagnosi = codDiagnosi;
	}

	public String getDescDiagnosi() {
		return descDiagnosi;
	}

	public void setDescDiagnosi(String descDiagnosi) {
		this.descDiagnosi = descDiagnosi;
	}

	public Timestamp getDatInizioValidita() {
		return datInizioValidita;
	}

	public void setDatInizioValidita(Timestamp datInizioValidita) {
		this.datInizioValidita = datInizioValidita;
	}

	public Timestamp getDatFineValidita() {
		return datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public String getPrestazioneEsente() {
		return prestazioneEsente;
	}

	public void setPrestazioneEsente(String prestazioneEsente) {
		this.prestazioneEsente = prestazioneEsente;
	}

	public EsenzioneDEsenzioneAuraTipo getEsenzioneDEsenzioneAuraTipo() {
		return esenzioneDEsenzioneAuraTipo;
	}

	public void setEsenzioneDEsenzioneAuraTipo(EsenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo) {
		this.esenzioneDEsenzioneAuraTipo = esenzioneDEsenzioneAuraTipo;
	}

}