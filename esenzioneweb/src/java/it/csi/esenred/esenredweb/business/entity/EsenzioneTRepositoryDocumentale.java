/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the "ESENZIONE_T_REPOSITORY_DOCUMENTALE" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_T_REPOSITORY_DOCUMENTALE\"")
@SequenceGenerator(name="\"ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq\"",  sequenceName="\"ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq\"", initialValue=1, allocationSize=1)
@NamedQuery(name="EsenzioneTRepositoryDocumentale.findAll", query="SELECT e FROM EsenzioneTRepositoryDocumentale e")
public class EsenzioneTRepositoryDocumentale implements Serializable {
  private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq\"")
	@Column(name="\"SK_REPOSITORY\"", unique=true, nullable=false)
	private Integer skRepository;

  @Column(name = "\"COD_RUOLO_OPERATORE\"", length = 2147483647)
  private String codRuoloOperatore;

  @Column(name = "\"DAT_ARCHIVIAZIONE\"", nullable = false)
  private Timestamp datArchiviazione;

  @Column(name = "\"DAT_CANCELLAZIONE\"")
  private Timestamp datCancellazione;

  @Column(name = "\"DAT_CREAZIONE\"")
  private Timestamp datCreazione;

  @Column(name = "\"DAT_MODIFICA\"")
  private Timestamp datModifica;

  @Column(name = "\"DESC_FILE\"", nullable = false, length = 2147483647)
  private String descFile;

  @Column(name = "\"FILE\"")
  private byte[] file;

  @Column(name = "\"FILE_NAME\"", nullable = false, length = 2147483647)
  private String fileName;

  @Column(name = "\"ID_USER\"")
  private Long idUser;

  //bi-directional many-to-one association to EsenzioneSDocumento
  @OneToMany(mappedBy = "esenzioneTRepositoryDocumentale")
  private List<EsenzioneSDocumento> esenzioneSDocumentos;

  //bi-directional many-to-one association to EsenzioneTDocumento
  @OneToMany(mappedBy = "esenzioneTRepositoryDocumentale")
  private List<EsenzioneTDocumento> esenzioneTDocumentos;

  public EsenzioneTRepositoryDocumentale() {
  }

	public EsenzioneTRepositoryDocumentale(EsenzioneTRepositoryDocumentale repo) {
		this.skRepository = repo.skRepository;
		this.codRuoloOperatore = repo.codRuoloOperatore;
		this.datArchiviazione = repo.datArchiviazione;
		this.datCancellazione = repo.datCancellazione;
		this.datCreazione = repo.datCreazione;
		this.datModifica = repo.datModifica;
		this.descFile = repo.descFile;
		this.file = repo.file;
		this.fileName = repo.fileName;
		this.idUser = repo.idUser;
		this.esenzioneSDocumentos = repo.esenzioneSDocumentos;
		this.esenzioneTDocumentos = repo.esenzioneTDocumentos;
	}

	public Integer getSkRepository() {
    return this.skRepository;
  }

  public void setSkRepository(Integer skRepository) {
    this.skRepository = skRepository;
  }

  public String getCodRuoloOperatore() {
    return this.codRuoloOperatore;
  }

  public void setCodRuoloOperatore(String codRuoloOperatore) {
    this.codRuoloOperatore = codRuoloOperatore;
  }

  public Timestamp getDatArchiviazione() {
    return this.datArchiviazione;
  }

  public void setDatArchiviazione(Timestamp datArchiviazione) {
    this.datArchiviazione = datArchiviazione;
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

  public String getDescFile() {
    return this.descFile;
  }

  public void setDescFile(String descFile) {
    this.descFile = descFile;
  }

  public byte[] getFile() {
    return this.file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Long getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public List<EsenzioneSDocumento> getEsenzioneSDocumentos() {
    return this.esenzioneSDocumentos;
  }

  public void setEsenzioneSDocumentos(List<EsenzioneSDocumento> esenzioneSDocumentos) {
    this.esenzioneSDocumentos = esenzioneSDocumentos;
  }

  public EsenzioneSDocumento addEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
    getEsenzioneSDocumentos().add(esenzioneSDocumento);
    esenzioneSDocumento.setEsenzioneTRepositoryDocumentale(this);

    return esenzioneSDocumento;
  }

  public EsenzioneSDocumento removeEsenzioneSDocumento(EsenzioneSDocumento esenzioneSDocumento) {
    getEsenzioneSDocumentos().remove(esenzioneSDocumento);
    esenzioneSDocumento.setEsenzioneTRepositoryDocumentale(null);

    return esenzioneSDocumento;
  }

  public List<EsenzioneTDocumento> getEsenzioneTDocumentos() {
    return this.esenzioneTDocumentos;
  }

  public void setEsenzioneTDocumentos(List<EsenzioneTDocumento> esenzioneTDocumentos) {
    this.esenzioneTDocumentos = esenzioneTDocumentos;
  }

  public EsenzioneTDocumento addEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    getEsenzioneTDocumentos().add(esenzioneTDocumento);
    esenzioneTDocumento.setEsenzioneTRepositoryDocumentale(this);

    return esenzioneTDocumento;
  }

  public EsenzioneTDocumento removeEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
    getEsenzioneTDocumentos().remove(esenzioneTDocumento);
    esenzioneTDocumento.setEsenzioneTRepositoryDocumentale(null);

    return esenzioneTDocumento;
  }

}