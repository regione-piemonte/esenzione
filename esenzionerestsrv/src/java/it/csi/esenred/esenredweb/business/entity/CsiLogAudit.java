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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the csi_log_audit database table.
 * 
 */
@Entity
@Table(name = "csi_log_audit")
@SequenceGenerator(name = "\"csi_log_audit_audit_id_seq\"", sequenceName = "\"csi_log_audit_audit_id_seq\"", initialValue = 1, allocationSize = 1)

@NamedQueries({ @NamedQuery(name = "CsiLogAudit.findAll", query = "SELECT c FROM CsiLogAudit c"),
    @NamedQuery(name = "CsiLogAudit.findAudit", query = "SELECT c FROM CsiLogAudit c where c.auditId = :auditId ") })

public class CsiLogAudit implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"csi_log_audit_audit_id_seq\"")
  @Column(name = "audit_id")
  private Integer auditId;

  @Column(name = "data_ora")
  private Timestamp dataOra;

  @Column(name = "id_app")
  private String idApp;

  /**
   * xrequestid
   */
  @Column(name = "idrequest")
  private String idrequest;

  @Column(name = "xcodservizio")
  private String xcodservizio;

  @Column(name = "ip_address")
  private String ipAddress;

  /**
   * Questo campo dovra' contenere l''identificativo univoco dell''oggetto dell''operazione oppure 
   * nel caso di aggiornamenti multipli del valore che caratterizza l''insieme di oggetti 
   * (es: modifica di un dato in tutta una categoria merceologica
   */
  @Column(name = "key_oper")
  private String keyOper;

  /**
   * Questa campo consentira' di identificare i dati e le informazioni trattati dall''operazione. 
   * Se la funzionalita' lo permette inserire il nome delle tabelle 
   * (o in alternativa degli oggetti/entita') su cui viene eseguita l''operazione;
   * l''indicazione della colonna e' opzionale e andra' indicata nel formato tabella.colonna. 
   * Se l''applicativo prevede accessi a schemi dati esterni premettere
   * il nome dello schema proprietario al nome della tabella.
   */
  @Column(name = "ogg_oper")
  private String oggOper;

  /**
   * 'Questo campo dovra' contenere l''informazione circa l''operazione effettuata; 
   * utilizzare uno dei seguenti valori: login / logout / read / insert / update / delete / merge 
   * Nei casi in cui il nome dell''operazione di business sia significativo e non riconducibile all''elenco di cui sopra,
   *  e' possibile indicare il nome dell''operazione.
   */
  @Column(name = "operazione")
  private String operazione;

  /**
   * Shib
   */
  @Column(name = "utente")
  private String utente;

  @Column(name = "uuid")
  private String uuid;

  @Column(name = "\"utente_ben\"", length = 2147483647)
  private String utenteBen;

  @Column(name = "\"utente_del\"", length = 2147483647)
  private String utenteDel;

  public CsiLogAudit() {
  }

  public CsiLogAudit(String ipAddress, String keyOper, String oggOper, String operazione, String utente, String idRequest, String uuid, String xcodservizio) throws Exception {
    if (utente == null || idRequest == null || xcodservizio == null)
      throw new Exception("Parametri header non inseriti");
    this.ipAddress = ipAddress;
    this.keyOper = keyOper;
    this.oggOper = oggOper;
    this.operazione = operazione;
    this.utente = utente;
    this.idrequest = idRequest;
    this.uuid = uuid;
    this.xcodservizio = xcodservizio;
  }

  public CsiLogAudit(String idApp, String ipAddress, String keyOper, String oggOper, String operazione, String utente, String idRequest, String uuId, String xcodServizio, String utenteBen,
      String utenteDel) throws Exception {
    if (utente == null || idRequest == null || xcodServizio == null)
      throw new Exception("Parametri header non inseriti");
    this.idApp = idApp;
    this.ipAddress = ipAddress;
    this.keyOper = keyOper;
    this.oggOper = oggOper;
    this.operazione = operazione;
    this.utente = utente;
    this.idrequest = idRequest;
    this.uuid = uuId;
    this.xcodservizio = xcodServizio;
    this.utenteBen = utenteBen;
    this.utenteDel = utenteDel;
  }

  /*
   * Chiamata diretta da AURA
   */
  public CsiLogAudit(boolean fromAura, String idApp, String ipAddress, String keyOper, String oggOper, String operazione, String utente, String idRequest, String uuId, String xcodServizio, String utenteBen,
		  String utenteDel) throws Exception {
	  if (utente == null)
		  throw new Exception("Parametro utente non inserito");
	  this.idApp = idApp;
	  this.ipAddress = ipAddress;
	  this.keyOper = keyOper;
	  this.oggOper = oggOper;
	  this.operazione = operazione;
	  this.utente = utente;
	  this.idrequest = idRequest;
	  this.uuid = uuId;
	  this.xcodservizio = xcodServizio;
	  this.utenteBen = utenteBen;
	  this.utenteDel = utenteDel;
  }

  public Integer getAuditId() {
    return this.auditId;
  }

  public String getXcodservizio() {
    return xcodservizio;
  }

  public void setXcodservizio(String xcodservizio) {
    this.xcodservizio = xcodservizio;
  }

  public void setAuditId(Integer auditId) {
    this.auditId = auditId;
  }

  public Timestamp getDataOra() {
    return this.dataOra;
  }

  public void setDataOra(Timestamp dataOra) {
    this.dataOra = dataOra;
  }

  public String getIdApp() {
    return this.idApp;
  }

  public void setIdApp(String idApp) {
    this.idApp = idApp;
  }

  public String getIdrequest() {
    return this.idrequest;
  }

  public void setIdrequest(String idrequest) {
    this.idrequest = idrequest;
  }

  public String getIpAddress() {
    return this.ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getKeyOper() {
    return this.keyOper;
  }

  public void setKeyOper(String keyOper) {
    this.keyOper = keyOper;
  }

  public String getOggOper() {
    return this.oggOper;
  }

  public void setOggOper(String oggOper) {
    this.oggOper = oggOper;
  }

  public String getOperazione() {
    return this.operazione;
  }

  public void setOperazione(String operazione) {
    this.operazione = operazione;
  }

  public String getUtente() {
    return this.utente;
  }

  public void setUtente(String utente) {
    this.utente = utente;
  }

  public Object getUuid() {
    return this.uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getUtenteBen() {
    return utenteBen;
  }

  public void setUtenteBen(String utenteBen) {
    this.utenteBen = utenteBen;
  }

  public String getUtenteDel() {
    return utenteDel;
  }

  public void setUtenteDel(String utenteDel) {
    this.utenteDel = utenteDel;
  }

}