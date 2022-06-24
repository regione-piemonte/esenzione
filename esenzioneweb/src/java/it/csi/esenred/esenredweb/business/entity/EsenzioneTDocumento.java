/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_T_DOCUMENTO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_T_DOCUMENTO\"")
@SequenceGenerator(name = "\"ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq\"", sequenceName = "\"ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq\"", initialValue = 1, allocationSize = 1)
@NamedQuery(name = "EsenzioneTDocumento.findAll", query = "SELECT e FROM EsenzioneTDocumento e")
public class EsenzioneTDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq\"")
	@Column(name = "\"SK_DOCUMENTO\"")
	private Integer skDocumento;

	@Column(name = "\"COD_RUOLO_OPERATORE\"")
	private String codRuoloOperatore;

	@Column(name = "\"COD_TIPO_USER\"")
	private String codTipoUser;

	@Column(name = "\"CODICE_FISCALE_CITTADINO\"")
	private String codiceFiscaleCittadino;

	@Column(name = "\"DAT_CANCELLAZIONE\"")
	private Timestamp datCancellazione;

	@Column(name = "\"DAT_CREAZIONE\"")
	private Timestamp datCreazione;

	@Column(name = "\"DAT_DOCUMENTO\"")
	private Timestamp datDocumento;

	@Column(name = "\"DAT_FINE_VALIDITA\"")
	private Timestamp datFineValidita;

	@Column(name = "\"DAT_INIZIO_VALIDITA\"")
	private Timestamp datInizioValidita;

	@Column(name = "\"DAT_MODIFICA\"")
	private Timestamp datModifica;

	@Column(name = "\"DESC_ESTESA_PATOLOGIA_CERTIFICATO\"")
	private String descEstesaPatologiaCertificato;

	@Column(name = "\"DESC_NOTE\"")
	private String descNote;

	@Column(name = "\"FLAG_CONFORMITA_DOCUMENTO\"")
	private Boolean flagConformitaDocumento;

	@Column(name = "\"ID_AURA_ATTESTATO\"")
	private String idAuraAttestato;

	@Column(name = "\"ID_USERID\"")
	private Long idUserid;

	@Column(name = "\"OID_DOCUMENTO\"")
	private String oidDocumento;

	@Column(name = "\"SK_DIAGNOSI\"")
	private Long skDiagnosi;

	@Column(name = "\"SK_REPOSITORY\"")
	private Long skRepository;

	@Column(name = "\"SK_TIPO_DOCUMENTO\"")
	private Long skTipoDocumento;

	@Column(name = "\"SK_TIPOLOGIA_STATO_DOCUMENTO\"")
	private Long skTipologiaStatoDocumento;

	// bi-directional many-to-one association to EsenzioneRPraticaEsenzioneDocumento
	@OneToMany(mappedBy = "esenzioneTDocumento")
	private Set<EsenzioneRPraticaEsenzioneDocumento> esenzioneRPraticaEsenzioneDocumentos;

	// bi-directional many-to-one association to EsenzioneSDocumento
	@OneToMany(mappedBy = "esenzioneTDocumento")
	private Set<EsenzioneSDocumento> esenzioneSDocumentos;

	// bi-directional many-to-one association to EsenzioneDDiagnosi
	@ManyToOne
	@JoinColumn(name = "\"SK_DIAGNOSI\"", insertable = false, updatable = false)
	private EsenzioneDDiagnosi esenzioneDDiagnosi;

	// bi-directional many-to-one association to EsenzioneDDocumentoStato
	@ManyToOne
	@JoinColumn(name = "\"SK_TIPOLOGIA_STATO_DOCUMENTO\"", insertable = false, updatable = false)
	private EsenzioneDDocumentoStato esenzioneDDocumentoStato;

	// bi-directional many-to-one association to EsenzioneDDocumentoTipo
	@ManyToOne
	@JoinColumn(name = "\"SK_TIPO_DOCUMENTO\"", insertable = false, updatable = false)
	private EsenzioneDDocumentoTipo esenzioneDDocumentoTipo;

	// bi-directional many-to-one association to EsenzioneTCittadino
	@ManyToOne
	@JoinColumn(name = "\"CODICE_FISCALE_CITTADINO\"", insertable = false, updatable = false)
	private EsenzioneTCittadino esenzioneTCittadino;

	// bi-directional many-to-one association to EsenzioneTRepositoryDocumentale
	@ManyToOne
	@JoinColumn(name = "\"SK_REPOSITORY\"", insertable = false, updatable = false)
	private EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale;

	// bi-directional many-to-one association to EsenzioneTMetadatiDocumento
	@OneToOne(mappedBy = "esenzioneTDocumento")
	private EsenzioneTMetadatiDocumento esenzioneTMetadatiDocumento;

	public EsenzioneTDocumento() {
	}

	public EsenzioneTDocumento(EsenzioneTDocumento doc) {
		this.skDocumento = doc.skDocumento;
		this.codRuoloOperatore = doc.codRuoloOperatore;
		this.codTipoUser = doc.codTipoUser;
		this.codiceFiscaleCittadino = doc.codiceFiscaleCittadino;
		this.datCancellazione = doc.datCancellazione;
		this.datCreazione = doc.datCreazione;
		this.datDocumento = doc.datDocumento;
		this.datFineValidita = doc.datFineValidita;
		this.datInizioValidita = doc.datInizioValidita;
		this.datModifica = doc.datModifica;
		this.descEstesaPatologiaCertificato = doc.descEstesaPatologiaCertificato;
		this.descNote = doc.descNote;
		this.flagConformitaDocumento = doc.flagConformitaDocumento;
		this.idAuraAttestato = doc.idAuraAttestato;
		this.idUserid = doc.idUserid;
		this.oidDocumento = doc.oidDocumento;
		this.skDiagnosi = doc.skDiagnosi;
		this.skRepository = doc.skRepository;
		this.skTipoDocumento = doc.skTipoDocumento;
		this.skTipologiaStatoDocumento = doc.skTipologiaStatoDocumento;
		this.esenzioneRPraticaEsenzioneDocumentos = doc.esenzioneRPraticaEsenzioneDocumentos;
		this.esenzioneSDocumentos = doc.esenzioneSDocumentos;
		this.esenzioneDDiagnosi = doc.esenzioneDDiagnosi;
		this.esenzioneDDocumentoStato = doc.esenzioneDDocumentoStato;
		this.esenzioneDDocumentoTipo = doc.esenzioneDDocumentoTipo;
		this.esenzioneTCittadino = doc.esenzioneTCittadino;
		this.esenzioneTRepositoryDocumentale = doc.esenzioneTRepositoryDocumentale;
		this.esenzioneTMetadatiDocumento = doc.esenzioneTMetadatiDocumento;
	}

	public Integer getSkDocumento() {
		return this.skDocumento;
	}

	public void setSkDocumento(Integer skDocumento) {
		this.skDocumento = skDocumento;
	}

	public String getCodRuoloOperatore() {
		return this.codRuoloOperatore;
	}

	public void setCodRuoloOperatore(String codRuoloOperatore) {
		this.codRuoloOperatore = codRuoloOperatore;
	}

	public String getCodTipoUser() {
		return this.codTipoUser;
	}

	public void setCodTipoUser(String codTipoUser) {
		this.codTipoUser = codTipoUser;
	}

	public String getCodiceFiscaleCittadino() {
		return this.codiceFiscaleCittadino;
	}

	public void setCodiceFiscaleCittadino(String codiceFiscaleCittadino) {
		this.codiceFiscaleCittadino = codiceFiscaleCittadino;
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

	public Timestamp getDatDocumento() {
		return this.datDocumento;
	}

	public void setDatDocumento(Timestamp datDocumento) {
		this.datDocumento = datDocumento;
	}

	public Timestamp getDatFineValidita() {
		return this.datFineValidita;
	}

	public void setDatFineValidita(Timestamp datFineValidita) {
		this.datFineValidita = datFineValidita;
	}

	public Timestamp getDatInizioValidita() {
		return this.datInizioValidita;
	}

	public void setDatInizioValidita(Timestamp datInizioValidita) {
		this.datInizioValidita = datInizioValidita;
	}

	public Timestamp getDatModifica() {
		return this.datModifica;
	}

	public void setDatModifica(Timestamp datModifica) {
		this.datModifica = datModifica;
	}

	public String getDescEstesaPatologiaCertificato() {
		return this.descEstesaPatologiaCertificato;
	}

	public void setDescEstesaPatologiaCertificato(String descEstesaPatologiaCertificato) {
		this.descEstesaPatologiaCertificato = descEstesaPatologiaCertificato;
	}

	public String getDescNote() {
		return this.descNote;
	}

	public void setDescNote(String descNote) {
		this.descNote = descNote;
	}

	public Boolean getFlagConformitaDocumento() {
		return this.flagConformitaDocumento;
	}

	public void setFlagConformitaDocumento(Boolean flagConformitaDocumento) {
		this.flagConformitaDocumento = flagConformitaDocumento;
	}

	public String getIdAuraAttestato() {
		return this.idAuraAttestato;
	}

	public void setIdAuraAttestato(String idAuraAttestato) {
		this.idAuraAttestato = idAuraAttestato;
	}

	public Long getIdUserid() {
		return this.idUserid;
	}

	public void setIdUserid(Long idUserid) {
		this.idUserid = idUserid;
	}

	public String getOidDocumento() {
		return this.oidDocumento;
	}

	public void setOidDocumento(String oidDocumento) {
		this.oidDocumento = oidDocumento;
	}

	public Long getSkDiagnosi() {
		return this.skDiagnosi;
	}

	public void setSkDiagnosi(Long skDiagnosi) {
		this.skDiagnosi = skDiagnosi;
	}

	public Long getSkRepository() {
		return this.skRepository;
	}

	public void setSkRepository(Long skRepository) {
		this.skRepository = skRepository;
	}

	public Long getSkTipoDocumento() {
		return this.skTipoDocumento;
	}

	public void setSkTipoDocumento(Long skTipoDocumento) {
		this.skTipoDocumento = skTipoDocumento;
	}

	public Long getSkTipologiaStatoDocumento() {
		return this.skTipologiaStatoDocumento;
	}

	public void setSkTipologiaStatoDocumento(Long skTipologiaStatoDocumento) {
		this.skTipologiaStatoDocumento = skTipologiaStatoDocumento;
	}

	public Set<EsenzioneRPraticaEsenzioneDocumento> getEsenzioneRPraticaEsenzioneDocumentos() {
		return this.esenzioneRPraticaEsenzioneDocumentos;
	}

	public void setEsenzioneRPraticaEsenzioneDocumentos(
			Set<EsenzioneRPraticaEsenzioneDocumento> esenzioneRPraticaEsenzioneDocumentos) {
		this.esenzioneRPraticaEsenzioneDocumentos = esenzioneRPraticaEsenzioneDocumentos;
	}

	public EsenzioneRPraticaEsenzioneDocumento addEsenzioneRPraticaEsenzioneDocumento(
			EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaEsenzioneDocumento) {
		getEsenzioneRPraticaEsenzioneDocumentos().add(esenzioneRPraticaEsenzioneDocumento);
		esenzioneRPraticaEsenzioneDocumento.setEsenzioneTDocumento(this);

		return esenzioneRPraticaEsenzioneDocumento;
	}

	public EsenzioneRPraticaEsenzioneDocumento removeEsenzioneRPraticaEsenzioneDocumento(
			EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaEsenzioneDocumento) {
		getEsenzioneRPraticaEsenzioneDocumentos().remove(esenzioneRPraticaEsenzioneDocumento);
		esenzioneRPraticaEsenzioneDocumento.setEsenzioneTDocumento(null);

		return esenzioneRPraticaEsenzioneDocumento;
	}

	public Set<EsenzioneSDocumento> getEsenzioneSDocumentos() {
		return this.esenzioneSDocumentos;
	}

	public void setEsenzioneSDocumentos(Set<EsenzioneSDocumento> esenzioneSDocumentos) {
		this.esenzioneSDocumentos = esenzioneSDocumentos;
	}

	public EsenzioneSDocumento addEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
		getEsenzioneSDocumentos().add(esenzioneSDocumento);
		esenzioneSDocumento.setEsenzioneTDocumento(this);

		return esenzioneSDocumento;
	}

	public EsenzioneSDocumento removeEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
		getEsenzioneSDocumentos().remove(esenzioneSDocumento);
		esenzioneSDocumento.setEsenzioneTDocumento(null);

		return esenzioneSDocumento;
	}

	public EsenzioneDDiagnosi getEsenzioneDDiagnosi() {
		return this.esenzioneDDiagnosi;
	}

	public void setEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
		this.esenzioneDDiagnosi = esenzioneDDiagnosi;
	}

	public EsenzioneDDocumentoStato getEsenzioneDDocumentoStato() {
		return this.esenzioneDDocumentoStato;
	}

	public void setEsenzioneDDocumentoStato(EsenzioneDDocumentoStato esenzioneDDocumentoStato) {
		this.esenzioneDDocumentoStato = esenzioneDDocumentoStato;
	}

	public EsenzioneDDocumentoTipo getEsenzioneDDocumentoTipo() {
		return this.esenzioneDDocumentoTipo;
	}

	public void setEsenzioneDDocumentoTipo(EsenzioneDDocumentoTipo esenzioneDDocumentoTipo) {
		this.esenzioneDDocumentoTipo = esenzioneDDocumentoTipo;
	}

	public EsenzioneTCittadino getEsenzioneTCittadino() {
		return this.esenzioneTCittadino;
	}

	public void setEsenzioneTCittadino(EsenzioneTCittadino esenzioneTCittadino) {
		this.esenzioneTCittadino = esenzioneTCittadino;
	}

	public EsenzioneTRepositoryDocumentale getEsenzioneTRepositoryDocumentale() {
		return this.esenzioneTRepositoryDocumentale;
	}

	public void setEsenzioneTRepositoryDocumentale(EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale) {
		this.esenzioneTRepositoryDocumentale = esenzioneTRepositoryDocumentale;
	}

	public EsenzioneTMetadatiDocumento getEsenzioneTMetadatiDocumento() {
		return this.esenzioneTMetadatiDocumento;
	}

	public void setEsenzioneTMetadatiDocumento(EsenzioneTMetadatiDocumento esenzioneTMetadatiDocumento) {
		this.esenzioneTMetadatiDocumento = esenzioneTMetadatiDocumento;
	}

//	public EsenzioneTMetadatiDocumento addEsenzioneTMetadatiDocumento(
//			EsenzioneTMetadatiDocumento esenzioneTMetadatiDocumento) {
//		getesenzioneTMetadatiDocumento().add(esenzioneTMetadatiDocumento);
//		esenzioneTMetadatiDocumento.setEsenzioneTDocumento(this);
//
//		return esenzioneTMetadatiDocumento;
//	}
//
//	public EsenzioneTMetadatiDocumento removeEsenzioneTMetadatiDocumento(
//			EsenzioneTMetadatiDocumento esenzioneTMetadatiDocumento) {
//		getesenzioneTMetadatiDocumento().remove(esenzioneTMetadatiDocumento);
//		esenzioneTMetadatiDocumento.setEsenzioneTDocumento(null);
//
//		return esenzioneTMetadatiDocumento;
//	}

}