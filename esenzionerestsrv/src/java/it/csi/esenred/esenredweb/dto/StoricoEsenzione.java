/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.util.DateUtil;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.entity.EsenzioneRDiagnosiPrestazione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.util.Constants;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id","data_creazione",
    "nuovo_stato", "utente",
    "nota"})

public class StoricoEsenzione {

  @JsonProperty("id")
  private String id;
  @JsonProperty("data_creazione")
  private String dataCreazione;
  @JsonProperty("nuovo_stato")
  private Stato stato;
  @JsonProperty("utente")
  private Utente utente;
  @JsonProperty("nota")
  private String nota;

  public <T> StoricoEsenzione(T esenzioneGeneric) {

    if(esenzioneGeneric instanceof EsenzioneTPraticaEsenzione) {
      EsenzioneTPraticaEsenzione esenzione = (EsenzioneTPraticaEsenzione) esenzioneGeneric; 
      this.id = esenzione.getSkPraticaEsenzione().toString();
      this.dataCreazione = esenzione.getDatCreazione() == null ? null : DateUtil.formatDate(esenzione.getDatCreazione(), Constants.DATE_FORMAT);
      
      this.stato = new Stato();
      this.stato.setCodice(esenzione.getEsenzioneDPraticaStato().getCodStato());
      this.stato.setDescrizione(esenzione.getEsenzioneDPraticaStato().getDescStato());

      this.utente = new Utente();
      if (esenzione.getCodiceFiscaleOperatore() != null && !esenzione.getCodiceFiscaleOperatore().isEmpty()) {
        this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleOperatore());
      } else if (esenzione.getCodiceFiscaleDelegato() != null && !esenzione.getCodiceFiscaleDelegato().isEmpty()) {
        this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleDelegato());
      } else {
        this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleBeneficiario());
      }

      if (esenzione.getDescNotaBeneficiario() != null && !esenzione.getDescNotaBeneficiario().isEmpty()) {
        this.nota = esenzione.getDescNotaBeneficiario();
      } else if (esenzione.getDescNotaOperatore() != null && !esenzione.getDescNotaOperatore().isEmpty()) {
        this.nota = esenzione.getDescNotaOperatore();
      } else {
        this.nota = null;
      }
    } 
    else
    {
      EsenzioneSPraticaEsenzione esenzione = (EsenzioneSPraticaEsenzione) esenzioneGeneric;  
      this.id = esenzione.getSkIdEsenzione().toString();
      this.dataCreazione = esenzione.getDatCreazione() == null ? null : DateUtil.formatDate(esenzione.getDatCreazione(), Constants.DATE_FORMAT);
      
      this.stato = new Stato();
      this.stato.setCodice(esenzione.getEsenzioneDPraticaStato().getCodStato());
      this.stato.setDescrizione(esenzione.getEsenzioneDPraticaStato().getDescStato());

      this.utente = new Utente();
      if (esenzione.getCodFiscaleOperatore() != null && !esenzione.getCodFiscaleOperatore().isEmpty()) {
        this.utente.setCodiceFiscale(esenzione.getCodFiscaleOperatore());
      } else if (esenzione.getCodFiscaleCittadinoDelegato() != null && !esenzione.getCodFiscaleCittadinoDelegato().isEmpty()) {
        this.utente.setCodiceFiscale(esenzione.getCodFiscaleCittadinoDelegato());
      } else {
        this.utente.setCodiceFiscale(esenzione.getCodFiscaleCittadinoBeneficiario());
      }

      this.nota = esenzione.getDescNota();
      if (esenzione.getDescNotaOperatore() != null && !esenzione.getDescNotaOperatore().isEmpty()) {
        this.nota = esenzione.getDescNotaOperatore();
      } else if (esenzione.getDescNotaBeneficiario() != null && !esenzione.getDescNotaBeneficiario().isEmpty()) {
        this.nota = esenzione.getDescNotaBeneficiario();
      } else {
        this.nota = null;
      }
    }
 
    
  }
  


  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }



  @JsonProperty("data_creazione")
  public String getDataCreazione() {
    return dataCreazione;
  }

  @JsonProperty("data_creazione")
  public void setDataCreazione(String dataCreazione) {
    this.dataCreazione = dataCreazione;
  }

  @JsonProperty("stato")
  public Stato getStato() {
    return stato;
  }

  @JsonProperty("stato")
  public void setStato(Stato stato) {
    this.stato = stato;
  }

  @JsonProperty("utente")
  public Utente getUtente() {
    return utente;
  }

  @JsonProperty("utente")
  public void setUtente(Utente utente) {
    this.utente = utente;
  }

  @JsonProperty("nota")
  public String getNota() {
    return nota;
  }
  
  @JsonProperty("nota")
  public void setNota(String nota) {
    this.nota = nota;
  }
  
}
