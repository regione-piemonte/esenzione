/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.HashMap;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "certificato_id", "file_base64", "data_rilascio", "data_fine_validita", "note", "tipologia", "malattia", "tipologia_documento" })
public class Certificato {

    
  private String certificato_id;
  private String file_base64;
  private String data_rilascio;
  @JsonProperty("data_fine_validita")
  private String data_fine_validita;
  @JsonProperty("note")
  private String note;
  @JsonProperty("malattia")
  private Malattia malattia;
  @JsonProperty("tipologia")
  private Tipologia tipologia;
  @JsonProperty("tipologia_documento")
  private Tipologia tipologia_documento;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  

  public String getCertificato_id() {
    return certificato_id;
  }

  public void setCertificato_id(String certificato_id) {
    this.certificato_id = certificato_id;
  }

  public String getFile_base64() {
    return file_base64;
  }

  public void setFile_base64(String file_base64) {
    this.file_base64 = file_base64;
  }

  public String getData_rilascio() {
    return data_rilascio;
  }

  public void setData_rilascio(String data_rilascio) {
    this.data_rilascio = data_rilascio;
  }

  public String getData_fine_validita() {
    return data_fine_validita;
  }

  public void setData_fine_validita(String data_fine_validita) {
    this.data_fine_validita = data_fine_validita;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Malattia getMalattia() {
    return malattia;
  }

  public void setMalattia(Malattia malattia) {
    this.malattia = malattia;
  }

  public Tipologia getTipologia() {
    return tipologia;
  }

  public void setTipologia(Tipologia tipologia) {
    this.tipologia = tipologia;
  }

  public Tipologia getTipologia_documento() {
    return tipologia_documento;
  }

  public void setTipologia_documento(Tipologia tipologia_documento) {
    this.tipologia_documento = tipologia_documento;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
  
  public Certificato() {}

}

