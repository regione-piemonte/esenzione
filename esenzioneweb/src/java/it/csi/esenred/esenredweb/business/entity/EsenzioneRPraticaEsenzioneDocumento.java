/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO\"")
@NamedQueries({ 
  @NamedQuery(name="EsenzioneRPraticaEsenzioneDocumento.findAll", query="SELECT e FROM EsenzioneRPraticaEsenzioneDocumento e"),
  @NamedQuery(name = "EsenzioneRPraticaEsenzioneDocumento.findByDocumentoId", query = "SELECT e FROM EsenzioneRPraticaEsenzioneDocumento e where e.id.skDocumento = :documentoId"),
  @NamedQuery(name = "EsenzioneRPraticaEsenzioneDocumento.findByPraticaId", query = "SELECT e FROM EsenzioneRPraticaEsenzioneDocumento e where e.id.skPraticaEsenzione = :praticaEsenzioneId")
  })
public class EsenzioneRPraticaEsenzioneDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EsenzioneRPraticaEsenzioneDocumentoPK id;

	@Column(name="\"COD_RUOLO_OPERATORE\"", length=2147483647)
	private String codRuoloOperatore;

	@Column(name="\"DAT_CANCELLAZIONE\"")
	private Timestamp datCancellazione;

	@Column(name="\"DAT_CREAZIONE\"")
	private Timestamp datCreazione;

	@Column(name="\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	@Column(name="\"DAT_MODIFICA\"")
	private Timestamp datModifica;

	@Column(name="\"ID_USER\"")
	private Long idUser;

	//bi-directional many-to-one association to EsenzioneTDocumento
	@ManyToOne
	@JoinColumn(name = "\"SK_DOCUMENTO\"", insertable = false, updatable = false)
	private EsenzioneTDocumento esenzioneTDocumento;

	//bi-directional many-to-one association to EsenzioneTPraticaEsenzione
	@ManyToOne
	@JoinColumn(name = "\"SK_PRATICA_ESENZIONE\"", insertable = false, updatable = false)
	private EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione;

	public EsenzioneRPraticaEsenzioneDocumento() {
	}

	public EsenzioneRPraticaEsenzioneDocumentoPK getId() {
		return this.id;
	}

	public void setId(EsenzioneRPraticaEsenzioneDocumentoPK id) {
		this.id = id;
	}

	public String getCodRuoloOperatore() {
		return this.codRuoloOperatore;
	}

	public void setCodRuoloOperatore(String codRuoloOperatore) {
		this.codRuoloOperatore = codRuoloOperatore;
	}

	public Timestamp getDatCancellazione() {
		return this.datCancellazione;
	}

	public void setDatCancellazione(Timestamp datCancellazione) {
		this.datCancellazione = datCancellazione;
	}

	public Timestamp getDatCreazione() {
		return this.datCreazione;
	}

	public void setDatCreazione(Timestamp datCreazione) {
		this.datCreazione = datCreazione;
	}

	public Timestamp getDatFineValidita() {
		return this.datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public Timestamp getDatModifica() {
		return this.datModifica;
	}

	public void setDatModifica(Timestamp datModifica) {
		this.datModifica = datModifica;
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public EsenzioneTDocumento getEsenzioneTDocumento() {
		return this.esenzioneTDocumento;
	}

	public void setEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		this.esenzioneTDocumento = esenzioneTDocumento;
	}

	public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzione() {
		return this.esenzioneTPraticaEsenzione;
	}

	public void setEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		this.esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzione;
	}

}