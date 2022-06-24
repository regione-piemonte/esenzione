/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.Base64;

//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;


//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "documentazione_allegata"})
public class AttestatoEsenzione {
	
	

  @JsonProperty("documentazione_allegata")
  private byte[] documentazioneAllegata;
  
  public AttestatoEsenzione(EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale) {
	this.documentazioneAllegata = Base64.getDecoder().decode(esenzioneTRepositoryDocumentale.getFile());
  }
 
  @JsonProperty("documentazione_allegata")
  public byte[] getDocumentazioneAllegata() {
    return documentazioneAllegata;
  }
  @JsonProperty("documentazione_allegata")
  public void setDocumentazioneAllegata(byte[] documentazioneAllegata) {
    this.documentazioneAllegata = documentazioneAllegata;
  }
  
}
