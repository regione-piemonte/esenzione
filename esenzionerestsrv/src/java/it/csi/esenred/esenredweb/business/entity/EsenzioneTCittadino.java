/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_T_CITTADINO" database table.
 * 
 */
@Entity
@Table(name = "\"ESENZIONE_T_CITTADINO\"")
@NamedQueries({ @NamedQuery(name = "EsenzioneTCittadino.findAll", query = "SELECT e FROM EsenzioneTCittadino e"),
    @NamedQuery(name = "EsenzioneTCittadino.findCittadino", query = "SELECT e FROM EsenzioneTCittadino e where e.codiceFiscale = :cf"),
    @NamedQuery(name = "EsenzioneTCittadino.findCittadinoByUserId", query = "SELECT e FROM EsenzioneTCittadino e where e.idUser = :idUser")
})
public class EsenzioneTCittadino implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "\"CODICE_FISCALE\"")
  private String codiceFiscale;

  @Column(name = "\"COGNOME\"")
  private String cognome;

  @Column(name = "\"COMUNE_DI_NASCITA\"")
  private String comuneDiNascita;

  @Column(name = "\"DAT_CANCELLAZIONE\"")
  private Timestamp datCancellazione;

  @Column(name = "\"DAT_CREAZIONE\"")
  private Timestamp datCreazione;

  @Column(name = "\"DAT_MODIFICA\"")
  private Timestamp datModifica;

  @Column(name = "\"DATA_DI_NASCITA\"")
  private Timestamp dataDiNascita;

  @Column(name = "\"ID AURA\"")
  private String id_aura;

  @Column(name = "\"ID_AZIENDA\"")
  private String idAzienda;

  @Column(name = "\"ID_USER\"")
  private Long idUser;

  @Column(name = "\"NOME\"")
  private String nome;

  @Column(name = "\"SESSO\"")
  private String sesso;

  //bi-directional many-to-one association to EsenzioneTDocumento
  @OneToMany(mappedBy = "esenzioneTCittadino")
  private Set<EsenzioneTDocumento> esenzioneTDocumentos;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneTCittadino1")
  private Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones1;

  //bi-directional many-to-one association to EsenzioneTPraticaEsenzione
  @OneToMany(mappedBy = "esenzioneTCittadino2")
  private Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones2;

  public EsenzioneTCittadino() {
  }

	public EsenzioneTCittadino(EsenzioneTCittadino cit) {
		this.codiceFiscale = cit.getCodiceFiscale();
		this.cognome = cit.getCognome();
		this.comuneDiNascita = cit.getComuneDiNascita();
		this.datCancellazione = cit.getDatCancellazione();
		this.datCreazione = cit.getDatCreazione();
		this.datModifica = cit.getDatModifica();
		this.dataDiNascita = cit.getDataDiNascita();
		this.id_aura = cit.getId_aura();
		this.idAzienda = cit.getIdAzienda();
		this.idUser = cit.getIdUser();
		this.nome = cit.getNome();
		this.sesso = cit.getSesso();

		this.esenzioneTDocumentos = cit.getEsenzioneTDocumentos();
		this.esenzioneTPraticaEsenziones1 = cit.getEsenzioneTPraticaEsenziones1();
		this.esenzioneTPraticaEsenziones2 = cit.getEsenzioneTPraticaEsenziones2();
	}

	public String getCodiceFiscale() {
    return this.codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getCognome() {
    return this.cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getComuneDiNascita() {
    return this.comuneDiNascita;
  }

  public void setComuneDiNascita(String comuneDiNascita) {
    this.comuneDiNascita = comuneDiNascita;
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

  public Timestamp getDatModifica() {
    return this.datModifica;
  }

  public void setDatModifica(Timestamp datModifica) {
    this.datModifica = datModifica;
  }

  public Timestamp getDataDiNascita() {
    return this.dataDiNascita;
  }

  public void setDataDiNascita(Timestamp dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  public String getId_aura() {
    return this.id_aura;
  }

  public void setId_aura(String id_aura) {
    this.id_aura = id_aura;
  }

  public String getIdAzienda() {
    return this.idAzienda;
  }

  public void setIdAzienda(String idAzienda) {
    this.idAzienda = idAzienda;
  }

  public Long getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSesso() {
    return this.sesso;
  }

  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  public Set<EsenzioneTDocumento> getEsenzioneTDocumentos() {
    return this.esenzioneTDocumentos;
  }

  public void setEsenzioneTDocumentos(Set<EsenzioneTDocumento> esenzioneTDocumentos) {
    this.esenzioneTDocumentos = esenzioneTDocumentos;
  }

  public EsenzioneTDocumento addEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    getEsenzioneTDocumentos().add(esenzioneTDocumento);
    esenzioneTDocumento.setEsenzioneTCittadino(this);

    return esenzioneTDocumento;
  }

  public EsenzioneTDocumento removeEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    getEsenzioneTDocumentos().remove(esenzioneTDocumento);
    esenzioneTDocumento.setEsenzioneTCittadino(null);

    return esenzioneTDocumento;
  }

  public Set<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones1() {
    return this.esenzioneTPraticaEsenziones1;
  }

  public void setEsenzioneTPraticaEsenziones1(Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones1) {
    this.esenzioneTPraticaEsenziones1 = esenzioneTPraticaEsenziones1;
  }

  public EsenzioneTPraticaEsenzione addEsenzioneTPraticaEsenziones1(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenziones1) {
    getEsenzioneTPraticaEsenziones1().add(esenzioneTPraticaEsenziones1);
    esenzioneTPraticaEsenziones1.setEsenzioneTCittadino1(this);

    return esenzioneTPraticaEsenziones1;
  }

  public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenziones1(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenziones1) {
    getEsenzioneTPraticaEsenziones1().remove(esenzioneTPraticaEsenziones1);
    esenzioneTPraticaEsenziones1.setEsenzioneTCittadino1(null);

    return esenzioneTPraticaEsenziones1;
  }

  public Set<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones2() {
    return this.esenzioneTPraticaEsenziones2;
  }

  public void setEsenzioneTPraticaEsenziones2(Set<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones2) {
    this.esenzioneTPraticaEsenziones2 = esenzioneTPraticaEsenziones2;
  }

  public EsenzioneTPraticaEsenzione addEsenzioneTPraticaEsenziones2(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenziones2) {
    getEsenzioneTPraticaEsenziones2().add(esenzioneTPraticaEsenziones2);
    esenzioneTPraticaEsenziones2.setEsenzioneTCittadino2(this);

    return esenzioneTPraticaEsenziones2;
  }

  public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenziones2(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenziones2) {
    getEsenzioneTPraticaEsenziones2().remove(esenzioneTPraticaEsenziones2);
    esenzioneTPraticaEsenziones2.setEsenzioneTCittadino2(null);

    return esenzioneTPraticaEsenziones2;
  }

}