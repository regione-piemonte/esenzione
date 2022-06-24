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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.esenred.esenredweb.business.entity.EsenredTAslOperatore;

/**
 * The persistent class for the "ESENZIONE_T_PRATICA_ESENZIONE" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_T_PRATICA_ESENZIONE\"")
@SequenceGenerator(name = "\"ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq\"", sequenceName = "\"ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq\"", initialValue = 1, allocationSize = 1)
@NamedQueries({ @NamedQuery(name = "EsenzioneTPraticaEsenzione.findAll", query = "SELECT e FROM EsenzioneTPraticaEsenzione e"),
    @NamedQuery(name = "EsenzioneTPraticaEsenzione.findPerNumPratica", query = "SELECT e FROM EsenzioneTPraticaEsenzione e where e.numPratica = :numPratica"),
    @NamedQuery(name = "EsenzioneTPraticaEsenzione.findByStatus", query = "SELECT e FROM EsenzioneTPraticaEsenzione e WHERE e.codiceFiscaleBeneficiario = :codFiscale AND e.esenzioneDDiagnosi.skDiagnosi = :codMalattia AND e.esenzioneDInvaliditaTipo.skInvaliditaTipo = :codTipo AND e.esenzioneDPraticaStato.codStato in :status"),
    @NamedQuery(name = "EsenzioneTPraticaEsenzione.findBySkEsenzione", query = "SELECT e FROM EsenzioneTPraticaEsenzione e WHERE e.codiceFiscaleBeneficiario = :codFiscale AND e.esenzioneDPraticaStato.codStato = :codStato AND e.esenzioneDDiagnosi.skDiagnosi in :elencoSkDiagnosi"),
    @NamedQuery(name = "EsenzioneTPraticaEsenzione.findBySkPraticaEsenzione", query = "SELECT e FROM EsenzioneTPraticaEsenzione e WHERE e.skPraticaEsenzione = :skPraticaEsenzione"),
    @NamedQuery(name = "EsenzioneTPraticaEsenzione.findByCf", query = "SELECT e FROM EsenzioneTPraticaEsenzione e WHERE e.codiceFiscaleBeneficiario = :codFiscale AND e.datInizioValidita IS NOT NULL") })
public class EsenzioneTPraticaEsenzione implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq\"")
  @Column(name = "\"SK_PRATICA_ESENZIONE\"", unique = true, nullable = false)
  private Integer skPraticaEsenzione;

	@Column(name = "\"CODICE_FISCALE_OPERATORE\"", length = 16)
  private String codiceFiscaleOperatore;

  @Column(name = "\"COD_RUOLO_OPERATORE\"", length = 2147483647)
  private String codRuoloOperatore;

  @Column(name = "\"CODICE_FISCALE_BENEFICIARIO\"", nullable = false)
  private String codiceFiscaleBeneficiario;

  @Column(name = "\"CODICE_FISCALE_DELEGATO\"")
  private String codiceFiscaleDelegato;

  @Column(name = "\"DAT_CANCELLAZIONE\"")
  private Timestamp datCancellazione;

  @Column(name = "\"DAT_CREAZIONE\"")
  private Timestamp datCreazione;

  @Column(name = "\"DAT_FINE_VALIDITA\"")
  private Timestamp datFineValidita;

  @Column(name = "\"DAT_INIZIO_VALIDITA\"")
  private Timestamp datInizioValidita;

  @Column(name = "\"DAT_MODIFICA\"")
  private Timestamp datModifica;

  @Column(name = "\"DESC_NOTA_BENEFICIARIO\"")
  private String descNotaBeneficiario;

  @Column(name = "\"DESC_NOTA_INTERNA\"")
  private String descNotaInterna;

  @Column(name = "\"DESC_NOTA_OPERATORE\"")
  private String descNotaOperatore;

  @Column(name = "\"DESC_NOTA\"")
  private String descNota;

  @Column(name = "\"FLAG_DICHIARAZIONE\"")
  private Boolean flagDichiarazione;

  @Column(name = "\"ID_AZIENDA\"")
  private String idAzienda;

  @Column(name = "\"ID_DICHIARAZIONE\"")
  private String idDichiarazione;

  @Column(name = "\"ID_USER\"")
  private Long idUser;

  @Column(name = "\"NUM_PRATICA\"")
  private Long numPratica;

  @Column(name = "\"SK_DISTRETTO_SOCIO_SANITARIO\"")
  private Long skDistrettoSocioSanitario;

  @Column(name = "\"COD_TIPO_USER\"")
  private String codTipoUser;
  
  @Column(name = "\"SK_DIAGNOSI\"", nullable = false)
  private Long skDiagnosi;
  
  @Column(name = "\"SK_GRUPPO\"", nullable = false)
  private Long skGruppo;
  
  @Column(name = "\"SK_TIPOLOGIA_STATO_PRATICA\"", nullable = false)
  private Long skTipologiaStatoPratica;
  
  //bi-directional many-to-one association to EsenzioneRPraticaEsenzioneDocumento
  @OneToMany(mappedBy = "esenzioneTPraticaEsenzione")
  private Set<EsenzioneRPraticaEsenzioneDocumento> esenzioneRPraticaEsenzioneDocumentos;

  //bi-directional many-to-one association to EsenzioneSPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneTPraticaEsenzione")
  private Set<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

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
  @JoinColumn(name = "\"SK_INVALIDITA_TIPO\"")
  private EsenzioneDInvaliditaTipo esenzioneDInvaliditaTipo;

  //bi-directional many-to-one association to EsenzioneDMotivazioneTipo
  @ManyToOne
  @JoinColumn(name = "\"SK_TIPO_MOTIVAZIONE\"")
  private EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo;

  //bi-directional many-to-one association to EsenzioneDPraticaStato
  @ManyToOne
  @JoinColumn(name = "\"SK_TIPOLOGIA_STATO_PRATICA\"", insertable = false, updatable = false)
  private EsenzioneDPraticaStato esenzioneDPraticaStato;

  //bi-directional many-to-one association to EsenzioneTCittadino
  @ManyToOne
  @JoinColumn(name = "\"CODICE_FISCALE_BENEFICIARIO\"", insertable = false, updatable = false)
  private EsenzioneTCittadino esenzioneTCittadino1;

  //bi-directional many-to-one association to EsenzioneTCittadino
  @ManyToOne
  @JoinColumn(name = "\"CODICE_FISCALE_DELEGATO\"", insertable = false, updatable = false)
  private EsenzioneTCittadino esenzioneTCittadino2;

  public EsenzioneTPraticaEsenzione() {
  }

	public EsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzione) {
		super();
		this.skPraticaEsenzione = esenzione.skPraticaEsenzione;
		this.codiceFiscaleOperatore = esenzione.codiceFiscaleOperatore;
		this.codRuoloOperatore = esenzione.codRuoloOperatore;
		this.codiceFiscaleBeneficiario = esenzione.codiceFiscaleBeneficiario;
		this.codiceFiscaleDelegato = esenzione.codiceFiscaleDelegato;
		this.datCancellazione = esenzione.datCancellazione;
		this.datCreazione = esenzione.datCreazione;
		this.datFineValidita = esenzione.datFineValidita;
		this.datInizioValidita = esenzione.datInizioValidita;
		this.datModifica = esenzione.datModifica;
		this.descNotaBeneficiario = esenzione.descNotaBeneficiario;
		this.descNotaInterna = esenzione.descNotaInterna;
		this.descNotaOperatore = esenzione.descNotaOperatore;
		this.descNota = esenzione.descNota;
		this.flagDichiarazione = esenzione.flagDichiarazione;
		this.idAzienda = esenzione.idAzienda;
		this.idDichiarazione = esenzione.idDichiarazione;
		this.idUser = esenzione.idUser;
		this.numPratica = esenzione.numPratica;
		this.skDistrettoSocioSanitario = esenzione.skDistrettoSocioSanitario;
		this.codTipoUser = esenzione.codTipoUser;
		this.skDiagnosi = esenzione.skDiagnosi;
		this.skGruppo = esenzione.skGruppo;
		this.skTipologiaStatoPratica = esenzione.skTipologiaStatoPratica;
		this.esenzioneRPraticaEsenzioneDocumentos = esenzione.esenzioneRPraticaEsenzioneDocumentos;
		this.esenzioneSPraticaEsenziones = esenzione.esenzioneSPraticaEsenziones;
		this.esenredDAziendasanitaria = esenzione.esenredDAziendasanitaria;
		this.esenredTAslOperatore = esenzione.esenredTAslOperatore;
		this.esenzioneDDiagnosi = esenzione.esenzioneDDiagnosi;
		this.esenzioneDDistrettoSocioSanitario = esenzione.esenzioneDDistrettoSocioSanitario;
		this.esenzioneDGruppoEsenzioni = esenzione.esenzioneDGruppoEsenzioni;
		this.esenzioneDInvaliditaTipo = esenzione.esenzioneDInvaliditaTipo;
		this.esenzioneDMotivazioneTipo = esenzione.esenzioneDMotivazioneTipo;
		this.esenzioneDPraticaStato = esenzione.esenzioneDPraticaStato;
		this.esenzioneTCittadino1 = esenzione.esenzioneTCittadino1;
		this.esenzioneTCittadino2 = esenzione.esenzioneTCittadino2;
	}

  public Integer getSkPraticaEsenzione() {
    return this.skPraticaEsenzione;
  }

  public void setSkPraticaEsenzione(Integer skPraticaEsenzione) {
    this.skPraticaEsenzione = skPraticaEsenzione;
  }

  public String getCodiceFiscaleOperatore() {
    return this.codiceFiscaleOperatore;
  }

  public void setCodiceFiscaleOperatore(String codFiscaleOperatore) {
    this.codiceFiscaleOperatore = codFiscaleOperatore;
  }

  public String getCodRuoloOperatore() {
    return this.codRuoloOperatore;
  }

  public void setCodRuoloOperatore(String codRuoloOperatore) {
    this.codRuoloOperatore = codRuoloOperatore;
  }

  public String getCodiceFiscaleBeneficiario() {
    return this.codiceFiscaleBeneficiario;
  }

  public void setCodiceFiscaleBeneficiario(String codiceFiscaleBeneficiario) {
    this.codiceFiscaleBeneficiario = codiceFiscaleBeneficiario;
  }

  public String getCodiceFiscaleDelegato() {
    return this.codiceFiscaleDelegato;
  }

  public void setCodiceFiscaleDelegato(String codiceFiscaleDelegato) {
    this.codiceFiscaleDelegato = codiceFiscaleDelegato;
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

  public String getDescNota() {
    return this.descNota;
  }

  public void setDescNota(String descNota) {
    this.descNota = descNota;
  }

  public Boolean getFlagDichiarazione() {
    return this.flagDichiarazione;
  }

  public void setFlagDichiarazione(Boolean flagDichiarazione) {
    this.flagDichiarazione = flagDichiarazione;
  }

  public String getIdAzienda() {
    return this.idAzienda;
  }

  public void setIdAzienda(String idAzienda) {
    this.idAzienda = idAzienda;
  }

  public String getIdDichiarazione() {
    return this.idDichiarazione;
  }

  public void setIdDichiarazione(String idDichiarazione) {
    this.idDichiarazione = idDichiarazione;
  }

  public Long getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public Long getNumPratica() {
    return this.numPratica;
  }

  public void setNumPratica(Long numPratica) {
    this.numPratica = numPratica;
  }

  public Long getSkDistrettoSocioSanitario() {
    return this.skDistrettoSocioSanitario;
  }

  public void setSkDistrettoSocioSanitario(Long skDistrettoSocioSanitario) {
    this.skDistrettoSocioSanitario = skDistrettoSocioSanitario;
  }

  public Long getSkDiagnosi() {
	return skDiagnosi;
}

public void setSkDiagnosi(Long skDiagnosi) {
	this.skDiagnosi = skDiagnosi;
}

public Long getSkGruppo() {
	return skGruppo;
}

public void setSkGruppo(Long skGruppo) {
	this.skGruppo = skGruppo;
}

public Long getSkTipologiaStatoPratica() {
	return skTipologiaStatoPratica;
}

public void setSkTipologiaStatoPratica(Long skTipologiaStatoPratica) {
	this.skTipologiaStatoPratica = skTipologiaStatoPratica;
}

public Set<EsenzioneRPraticaEsenzioneDocumento> getEsenzioneRPraticaEsenzioneDocumentos() {
    return this.esenzioneRPraticaEsenzioneDocumentos;
  }

  public void setEsenzioneRPraticaEsenzioneDocumentos(Set<EsenzioneRPraticaEsenzioneDocumento> esenzioneRPraticaEsenzioneDocumentos) {
    this.esenzioneRPraticaEsenzioneDocumentos = esenzioneRPraticaEsenzioneDocumentos;
  }

  public EsenzioneRPraticaEsenzioneDocumento addEsenzioneRPraticaEsenzioneDocumento(EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaEsenzioneDocumento) {
    getEsenzioneRPraticaEsenzioneDocumentos().add(esenzioneRPraticaEsenzioneDocumento);
    esenzioneRPraticaEsenzioneDocumento.setEsenzioneTPraticaEsenzione(this);

    return esenzioneRPraticaEsenzioneDocumento;
  }

  public EsenzioneRPraticaEsenzioneDocumento removeEsenzioneRPraticaEsenzioneDocumento(EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaEsenzioneDocumento) {
    getEsenzioneRPraticaEsenzioneDocumentos().remove(esenzioneRPraticaEsenzioneDocumento);
    esenzioneRPraticaEsenzioneDocumento.setEsenzioneTPraticaEsenzione(null);

    return esenzioneRPraticaEsenzioneDocumento;
  }

  public Set<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
    return this.esenzioneSPraticaEsenziones;
  }

  public void setEsenzioneSPraticaEsenziones(Set<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
    this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
  }

  public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneTPraticaEsenzione(this);

    return esenzioneSPraticaEsenzione;
  }

  public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
    getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
    esenzioneSPraticaEsenzione.setEsenzioneTPraticaEsenzione(null);

    return esenzioneSPraticaEsenzione;
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

  public EsenzioneTCittadino getEsenzioneTCittadino1() {
    return this.esenzioneTCittadino1;
  }

  public void setEsenzioneTCittadino1(EsenzioneTCittadino esenzioneTCittadino1) {
    this.esenzioneTCittadino1 = esenzioneTCittadino1;
  }

  public EsenzioneTCittadino getEsenzioneTCittadino2() {
    return this.esenzioneTCittadino2;
  }

  public void setEsenzioneTCittadino2(EsenzioneTCittadino esenzioneTCittadino2) {
    this.esenzioneTCittadino2 = esenzioneTCittadino2;
  }

  public void setEsenredDAziendasanitaria(EsenredDAziendasanitaria esenredDAziendasanitaria2) {
    this.esenredDAziendasanitaria = esenredDAziendasanitaria2;
  }

  public EsenredDAziendasanitaria getEsenredDAziendasanitaria() {
    return this.esenredDAziendasanitaria;
  }
  
  public String getCodTipoUser() {
    return this.codTipoUser;
  }

  public void setCodTipoUser(String codTipoUser) {
    this.codTipoUser = codTipoUser;
  }

}