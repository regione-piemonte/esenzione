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
@JsonPropertyOrder({ "documentoId", "filename", "fileBase64", "dataRilascio", "dataFineValidita", "tipologia",
		"descrizione" })
public class Documenti {

  @JsonProperty("documentoId")
  private String documentoId;
  
  @JsonProperty("filename")
  private String filename;
  
  @JsonProperty("fileBase64")
  private String fileBase64;
  
  @JsonProperty("dataRilascio")
  private String dataRilascio;
  
  @JsonProperty("dataFineValidita")
  private String dataFineValidita;

  @JsonProperty("tipologia")
  private String tipologia;
  
	@JsonProperty("descrizione")
	private String descrizione;
  
  public Documenti() {}


  public String getFilename() {
    return filename;
  }


  public void setFilename(String filename) {
    this.filename = filename;
  }


  public String getFileBase64() {
    return fileBase64;
  }


  public void setFileBase64(String fileBase64) {
    this.fileBase64 = fileBase64;
  }


  public String getDataRilascio() {
    return dataRilascio;
  }


  public void setDataRilascio(String dataRilascio) {
    this.dataRilascio = dataRilascio;
  }


  public String getDataFineValidita() {
    return dataFineValidita;
  }


  public void setDataFineValidita(String dataFineValidita) {
    this.dataFineValidita = dataFineValidita;
  }


  public String getTipologia() {
    return tipologia;
  }


  public void setTipologia(String tipologia) {
    this.tipologia = tipologia;
  }


  public String getDocumentoId() {
    return documentoId;
  }


  public void setDocumentoId(String documentoId) {
    this.documentoId = documentoId;
  }

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
  
  
  
  

}

