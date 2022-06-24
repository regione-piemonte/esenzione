/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_S_DOCUMENTO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_S_DOCUMENTO\"")
@SequenceGenerator(name = "\"ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq\"", sequenceName = "\"ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq\"", initialValue = 1, allocationSize = 1)
@NamedQuery(name = "EsenzioneSDocumento.findAll", query = "SELECT e FROM EsenzioneSDocumento e")
public class EsenzioneSDocumento implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq\"")
  @Column(name = "\"SK_STORICO_DOUMENTO\"", unique = true, nullable = false)
  private Integer skStoricoDoumento;

  @Column(name = "\"COD_RUOLO_OPERATORE\"", length = 2147483647)
  private String codRuoloOperatore;

  @Column(name = "\"COD_TIPO_USER\"", nullable = false, length = 2147483647)
  private String codTipoUser;

  @Column(name = "\"CODICE_FISCALE_CITTADINO\"", nullable = false, length = 16)
  private String codiceFiscaleCittadino;

  @Column(name = "\"DAT_CANCELLAZIONE\"")
  private Timestamp datCancellazione;

  @Column(name = "\"DAT_CREAZIONE\"", nullable = false)
  private Timestamp datCreazione;

  @Column(name = "\"DAT_DOCUMENTO\"", nullable = false)
  private Timestamp datDocumento;

  @Column(name = "\"DAT_FINE_VALIDITA\"")
  private Timestamp datFineValidita;

  @Column(name = "\"DAT_INIZIO_VALIDITA\"", nullable = false)
  private Timestamp datInizioValidita;

  @Column(name = "\"DAT_MODIFICA\"")
  private Timestamp datModifica;

  @Column(name = "\"DESC_ESTESA_PATOLOGIA_CERTIFICATO\"", length = 2147483647)
  private String descEstesaPatologiaCertificato;

  @Column(name = "\"DESC_NOTE\"", length = 2147483647)
  private String descNote;

  @Column(name = "\"FLAG_CONFORMITA_DOCUMENTO\"", nullable = false)
  private Boolean flagConformitaDocumento;

  @Column(name = "\"ID_AURA_ATTESTATO\"", length = 2147483647)
  private String idAuraAttestato;

  @Column(name = "\"ID_USERID\"", nullable = false)
  private Long idUserid;

  @Column(name = "\"OID_DOCUMENTO\"", length = 2147483647)
  private String oidDocumento;

  @Column(name = "\"SK_DIAGNOSI\"", nullable = false)
  private Long skDiagnosi;

  @Column(name = "\"SK_DOCUMENTO\"", nullable = false)
  private Long skDocumento;

  @Column(name = "\"SK_REPOSITORY\"", nullable = false)
  private Long skRepository;

  @Column(name = "\"SK_TIPO_DOCUMENTO\"", nullable = false)
  private Long skTipoDocumento;

  @Column(name = "\"SK_TIPOLOGIA_STATO_DOCUMENTO\"", nullable = false)
  private Long skTipologiaStatoDocumento;

  //bi-directional many-to-one association to EsenzioneDDiagnosi
  @ManyToOne
  @JoinColumn(name = "\"SK_DIAGNOSI\"", insertable = false, updatable = false)
  private EsenzioneDDiagnosi esenzioneDDiagnosi;

  //bi-directional many-to-one association to EsenzioneDDocumentoStato
  @ManyToOne
  @JoinColumn(name = "\"SK_TIPOLOGIA_STATO_DOCUMENTO\"", insertable = false, updatable = false)
  private EsenzioneDDocumentoStato esenzioneDDocumentoStato;

  //bi-directional many-to-one association to EsenzioneDDocumentoTipo
  @ManyToOne
  @JoinColumn(name = "\"SK_TIPO_DOCUMENTO\"", insertable = false, updatable = false)
  private EsenzioneDDocumentoTipo esenzioneDDocumentoTipo;

  //bi-directional many-to-one association to EsenzioneTDocumento
  @ManyToOne
  @JoinColumn(name = "\"SK_DOCUMENTO\"", insertable = false, updatable = false)
  private EsenzioneTDocumento esenzioneTDocumento;

  //bi-directional many-to-one association to EsenzioneTRepositoryDocumentale
  @ManyToOne
  @JoinColumn(name = "\"SK_REPOSITORY\"", insertable = false, updatable = false)
  private EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale;

  //bi-directional many-to-one association to EsenzioneTCittadino
  @ManyToOne
  @JoinColumn(name = "\"CODICE_FISCALE_CITTADINO\"", insertable = false, updatable = false)
  private EsenzioneTCittadino esenzioneTCittadino;


  public EsenzioneSDocumento() {
  }

	public EsenzioneSDocumento(EsenzioneTDocumento doc) {
		this.codiceFiscaleCittadino = doc.getCodiceFiscaleCittadino();
		this.codRuoloOperatore = doc.getCodRuoloOperatore();
		this.codTipoUser = doc.getCodTipoUser();
		this.datCancellazione = doc.getDatCancellazione();
		this.datCreazione = doc.getDatCreazione();
		this.datDocumento = doc.getDatDocumento();
		this.datFineValidita = doc.getDatFineValidita();
		this.datInizioValidita = doc.getDatInizioValidita();
		this.datModifica = doc.getDatModifica();
		this.descEstesaPatologiaCertificato = doc.getDescEstesaPatologiaCertificato();
		this.descNote = doc.getDescNote();
		this.esenzioneTRepositoryDocumentale = doc.getEsenzioneTRepositoryDocumentale();
		this.flagConformitaDocumento = doc.getFlagConformitaDocumento();
		this.idAuraAttestato = doc.getIdAuraAttestato();
		this.idUserid = doc.getIdUserid();
		this.oidDocumento = doc.getOidDocumento();
		this.skDiagnosi = doc.getSkDiagnosi();
		this.skDocumento = doc.getSkDocumento().longValue();
		this.skRepository = doc.getSkRepository();
		this.skTipoDocumento = doc.getSkTipoDocumento();
		this.skTipologiaStatoDocumento = doc.getSkTipologiaStatoDocumento();
	}

  public Integer getSkStoricoDoumento() {
    return this.skStoricoDoumento;
  }

  public void setSkStoricoDoumento(Integer skStoricoDoumento) {
    this.skStoricoDoumento = skStoricoDoumento;
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

  public Long getSkDocumento() {
    return this.skDocumento;
  }

  public void setSkDocumento(Long skDocumento) {
    this.skDocumento = skDocumento;
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

  public EsenzioneTDocumento getEsenzioneTDocumento() {
    return this.esenzioneTDocumento;
  }

  public void setEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    this.esenzioneTDocumento = esenzioneTDocumento;
  }

  public EsenzioneTRepositoryDocumentale getEsenzioneTRepositoryDocumentale() {
    return this.esenzioneTRepositoryDocumentale;
  }

  public void setEsenzioneTRepositoryDocumentale(EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale) {
    this.esenzioneTRepositoryDocumentale = esenzioneTRepositoryDocumentale;
  }

  public EsenzioneTCittadino getEsenzioneTCittadino() {
    return this.esenzioneTCittadino;
  }

  public void setEsenzioneTCittadino(EsenzioneTCittadino esenzioneTCittadino) {
    this.esenzioneTCittadino = esenzioneTCittadino;
  }
  


}