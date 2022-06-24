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
 * The persistent class for the "ESENZIONE_S_PRATICA_ESENZIONE" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_S_PRATICA_ESENZIONE\"")
@SequenceGenerator(name="\"ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq\"",  sequenceName="\"ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq\"", initialValue=1, allocationSize=1)
@NamedQuery(name = "EsenzioneSPraticaEsenzione.findAll", query = "SELECT e FROM EsenzioneSPraticaEsenzione e")
public class EsenzioneSPraticaEsenzione implements Serializable, Comparable<EsenzioneSPraticaEsenzione> {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq\"")
  @Column(name = "\"SK_CRONOLOGIA\"", unique = true, nullable = false)
  private Integer skCronologia;

  @Column(name = "\"CODICE_FISCALE_BENEFICIARIO\"", nullable = false)
  private String codFiscaleCittadinoBeneficiario;

  @Column(name = "\"CODICE_FISCALE_DELEGATO\"")
  private String codFiscaleCittadinoDelegato;

	@Column(name = "\"CODICE_FISCALE_OPERATORE\"", length = 16)
  private String codFiscaleOperatore;

  @Column(name = "\"COD_RUOLO_OPERATORE\"", length = 2147483647)
  private String codRuoloOperatore;

	@Column(name = "\"COD_TIPO_USER\"", length = 2147483647)
  private String codTipoUser;

  @Column(name = "\"DAT_CANCELLAZIONE\"")
  private Timestamp datCancellazione;

  @Column(name = "\"DAT_CREAZIONE\"", nullable = false)
  private Timestamp datCreazione;

  @Column(name = "\"DAT_FINE_VALIDITA\"")
  private Timestamp datFineValidita;

	@Column(name = "\"DAT_INIZIO_VALIDITA\"")
  private Timestamp datInizioValidita;

  @Column(name = "\"DAT_MODIFICA\"")
  private Timestamp datModifica;

  @Column(name = "\"DESC_NOTA\"", length = 2147483647)
  private String descNota;

  @Column(name = "\"DESC_NOTA_BENEFICIARIO\"", length = 2147483647)
  private String descNotaBeneficiario;

  @Column(name = "\"DESC_NOTA_INTERNA\"", length = 2147483647)
  private String descNotaInterna;

  @Column(name = "\"DESC_NOTA_OPERATORE\"", length = 2147483647)
  private String descNotaOperatore;

  @Column(name = "\"ID_AZIENDA\"", length = 6)
  private String idAzienda;

	@Column(name = "\"ID_USER\"")
  private Integer idUser;

  @Column(name = "\"NUM_PRATICA\"", nullable = false)
  private Long numPratica;

  @Column(name = "\"SK_DIAGNOSI\"", nullable = false)
  private Long skDiagnosi;

  @Column(name = "\"SK_DISTRETTO_SOCIO_SANITARIO\"")
  private Long skDistrettoSocioSanitario;

  @Column(name = "\"SK_GRUPPO\"", nullable = false)
  private Long skGruppo;

  @Column(name = "\"SK_PRATICA_ESENZIONE\"", nullable = false)
  private Long skPraticaEsenzione;

  @Column(name = "\"SK_INVALIDITA_TIPO\"")
  private Long skInvaliditaTipo;

	@Column(name = "\"SK_TIPO_MOTIVAZIONE\"")
  private Long skTipoMotivazione;

  @Column(name = "\"SK_TIPOLOGIA_STATO_PRATICA\"", nullable = false)
  private Long skTipologiaStatoPratica;

  @Column(name = "\"FLAG_DICHIARAZIONE\"")
  private Boolean flagDichiarazione;

  @Column(name = "\"ID_DICHIARAZIONE\"")
  private String idDichiarazione;

  //bi-directional many-to-one association to EsenredDAziendasanitaria
  @ManyToOne
  @JoinColumn(name = "\"ID_AZIENDA\"", insertable = false, updatable = false)
  private EsenredDAziendasanitaria esenredDAziendasanitaria;

  //bi-directional many-to-one association to EsenredTAslOperatore
  @ManyToOne
  @JoinColumn(name = "\"CODICE_FISCALE_OPERATORE\"", insertable = false, updatable = false)
  private EsenredTAslOperatore esenredTAslOperatore;

  //bi-directional many-to-one association to EsenzioneDDiagnosi
  @ManyToOne
  @JoinColumn(name = "\"SK_DIAGNOSI\"", insertable = false, updatable = false)
  private EsenzioneDDiagnosi esenzioneDDiagnosi;

  //bi-directional many-to-one association to EsenzioneDDistrettoSocioSanitario
  @ManyToOne
  @JoinColumn(name = "\"SK_DISTRETTO_SOCIO_SANITARIO\"", insertable = false, updatable = false)
  private EsenzioneDDistrettoSocioSanitario esenzioneDDistrettoSocioSanitario;

  //bi-directional many-to-one association to EsenzioneDGruppoEsenzioni
  @ManyToOne
  @JoinColumn(name = "\"SK_GRUPPO\"", insertable = false, updatable = false)
  private EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni;

  //bi-directional many-to-one association to EsenzioneDInvaliditaTipo
  @ManyToOne
  @JoinColumn(name = "\"SK_INVALIDITA_TIPO\"", insertable = false, updatable = false)
  private EsenzioneDInvaliditaTipo esenzioneDInvaliditaTipo;

  //bi-directional many-to-one association to EsenzioneDMotivazioneTipo
  @ManyToOne
  @JoinColumn(name = "\"SK_TIPO_MOTIVAZIONE\"", insertable = false, updatable = false)
  private EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo;

  //bi-directional many-to-one association to EsenzioneDPraticaStato
  @ManyToOne
  @JoinColumn(name = "\"SK_TIPOLOGIA_STATO_PRATICA\"", insertable = false, updatable = false)
  private EsenzioneDPraticaStato esenzioneDPraticaStato;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @ManyToOne
  @JoinColumn(name = "\"SK_PRATICA_ESENZIONE\"", insertable = false, updatable = false)
  private EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione;



  public EsenzioneSPraticaEsenzione() {
  }



  public Integer getSkCronologia() {
    return this.skCronologia;
  }

  public void setSkCronologia(Integer skCronologia) {
    this.skCronologia = skCronologia;
  }

  public String getCodFiscaleCittadinoBeneficiario() {
    return this.codFiscaleCittadinoBeneficiario;
  }

  public void setCodFiscaleCittadinoBeneficiario(String codFiscaleCittadinoBeneficiario) {
    this.codFiscaleCittadinoBeneficiario = codFiscaleCittadinoBeneficiario;
  }

  public String getCodFiscaleCittadinoDelegato() {
    return this.codFiscaleCittadinoDelegato;
  }

  public void setCodFiscaleCittadinoDelegato(String codFiscaleCittadinoDelegato) {
    this.codFiscaleCittadinoDelegato = codFiscaleCittadinoDelegato;
  }

  public String getCodFiscaleOperatore() {
    return this.codFiscaleOperatore;
  }

  public void setCodFiscaleOperatore(String codFiscaleOperatore) {
    this.codFiscaleOperatore = codFiscaleOperatore;
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

  public String getDescNota() {
    return this.descNota;
  }

  public void setDescNota(String descNota) {
    this.descNota = descNota;
  }

  public String getDescNotaBeneficiario() {
    return this.descNotaBeneficiario;
  }

  public void setDescNotaBeneficiario(String descNotaBeneficiario) {
    this.descNotaBeneficiario = descNotaBeneficiario;
  }

  public String getDescNotaInterna() {
    return this.descNotaInterna;
  }

  public void setDescNotaInterna(String descNotaInterna) {
    this.descNotaInterna = descNotaInterna;
  }

  public String getDescNotaOperatore() {
    return this.descNotaOperatore;
  }

  public void setDescNotaOperatore(String descNotaOperatore) {
    this.descNotaOperatore = descNotaOperatore;
  }

  public String getIdAzienda() {
    return this.idAzienda;
  }

  public void setIdAzienda(String idAzienda) {
    this.idAzienda = idAzienda;
  }

  public Integer getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public Long getNumPratica() {
    return this.numPratica;
  }

  public void setNumPratica(Long numPratica) {
    this.numPratica = numPratica;
  }

  public Long getSkDiagnosi() {
    return this.skDiagnosi;
  }

  public void setSkDiagnosi(Long skDiagnosi) {
    this.skDiagnosi = skDiagnosi;
  }

  public Long getSkDistrettoSocioSanitario() {
    return this.skDistrettoSocioSanitario;
  }

  public void setSkDistrettoSocioSanitario(Long skDistrettoSocioSanitario) {
    this.skDistrettoSocioSanitario = skDistrettoSocioSanitario;
  }

  public Long getSkGruppo() {
    return this.skGruppo;
  }

  public void setSkGruppo(Long skGruppo) {
    this.skGruppo = skGruppo;
  }

  public Long getSkIdEsenzione() {
    return this.skPraticaEsenzione;
  }

  public void setSkIdEsenzione(Long skPraticaEsenzione) {
    this.skPraticaEsenzione = skPraticaEsenzione;
  }

  public Long getSkInvaliditaTipo() {
    return this.skInvaliditaTipo;
  }

  public void setSkInvaliditaTipo(Long skInvaliditaTipo) {
    this.skInvaliditaTipo = skInvaliditaTipo;
  }

  public Long getSkTipoMotivazione() {
    return this.skTipoMotivazione;
  }

  public void setSkTipoMotivazione(Long skTipoMotivazione) {
    this.skTipoMotivazione = skTipoMotivazione;
  }

  public Long getSkTipologiaStatoPratica() {
    return this.skTipologiaStatoPratica;
  }

  public void setSkTipologiaStatoPratica(Long skTipologiaStatoPratica) {
    this.skTipologiaStatoPratica = skTipologiaStatoPratica;
  }

  public EsenredDAziendasanitaria getEsenredDAziendasanitaria() {
    return this.esenredDAziendasanitaria;
  }

  public void setEsenredDAziendasanitaria(EsenredDAziendasanitaria esenredDAziendasanitaria) {
    this.esenredDAziendasanitaria = esenredDAziendasanitaria;
  }

  public EsenredTAslOperatore getEsenredTAslOperatore() {
    return this.esenredTAslOperatore;
  }

  public void setEsenredTAslOperatore(EsenredTAslOperatore esenredTAslOperatore) {
    this.esenredTAslOperatore = esenredTAslOperatore;
  }

  public EsenzioneDDiagnosi getEsenzioneDDiagnosi() {
    return this.esenzioneDDiagnosi;
  }

  public void setEsenzioneDDiagnosi(EsenzioneDDiagnosi esenzioneDDiagnosi) {
    this.esenzioneDDiagnosi = esenzioneDDiagnosi;
  }

  public EsenzioneDDistrettoSocioSanitario getEsenzioneDDistrettoSocioSanitario() {
    return this.esenzioneDDistrettoSocioSanitario;
  }

  public void setEsenzioneDDistrettoSocioSanitario(EsenzioneDDistrettoSocioSanitario esenzioneDDistrettoSocioSanitario) {
    this.esenzioneDDistrettoSocioSanitario = esenzioneDDistrettoSocioSanitario;
  }

  public EsenzioneDGruppoEsenzioni getEsenzioneDGruppoEsenzioni() {
    return this.esenzioneDGruppoEsenzioni;
  }

  public void setEsenzioneDGruppoEsenzioni(EsenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni) {
    this.esenzioneDGruppoEsenzioni = esenzioneDGruppoEsenzioni;
  }

  public EsenzioneDInvaliditaTipo getEsenzioneDInvaliditaTipo() {
    return this.esenzioneDInvaliditaTipo;
  }

  public void setEsenzioneDInvaliditaTipo(EsenzioneDInvaliditaTipo esenzioneDInvaliditaTipo) {
    this.esenzioneDInvaliditaTipo = esenzioneDInvaliditaTipo;
  }

  public EsenzioneDMotivazioneTipo getEsenzioneDMotivazioneTipo() {
    return this.esenzioneDMotivazioneTipo;
  }

  public void setEsenzioneDMotivazioneTipo(EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo) {
    this.esenzioneDMotivazioneTipo = esenzioneDMotivazioneTipo;
  }

  public EsenzioneDPraticaStato getEsenzioneDPraticaStato() {
    return this.esenzioneDPraticaStato;
  }

  public void setEsenzioneDPraticaStato(EsenzioneDPraticaStato esenzioneDPraticaStato) {
    this.esenzioneDPraticaStato = esenzioneDPraticaStato;
  }

  public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzione() {
    return this.esenzioneTPraticaEsenzione;
  }

  public void setEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    this.esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzione;
  }

  public Boolean getFlagDichiarazione() {
    return flagDichiarazione;
  }

  public void setFlagDichiarazione(Boolean flagDichiarazione) {
    this.flagDichiarazione = flagDichiarazione;
  }

  public String getIdDichiarazione() {
    return idDichiarazione;
  }

  public void setIdDichiarazione(String idDichiarazione) {
    this.idDichiarazione = idDichiarazione;
  }

  @Override
  public int compareTo(EsenzioneSPraticaEsenzione o) {
    return this.getSkCronologia().compareTo(o.getSkCronologia());
  }


}