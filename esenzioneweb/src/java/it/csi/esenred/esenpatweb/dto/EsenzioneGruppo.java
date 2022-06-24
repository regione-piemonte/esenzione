/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;;

@JsonPropertyOrder({
  "gruppo",
  "codice_esenzione",
  "descrizione_esenzione",
})
public class EsenzioneGruppo {
  @JsonProperty("gruppo")
  private String gruppo;
  @JsonProperty("codice_esenzione")
	private String codiceEsenzione;
  @JsonProperty("descrizione_esenzione")
	private String descrizioneEsenzione;
	
	
	 public EsenzioneGruppo(EsenzioneDEsenzione ede, String gruppo) {
	   this.gruppo = gruppo;
	   this.codiceEsenzione = ede.getCodEsenzione();
	   this.descrizioneEsenzione = ede.getDescEsenzione();
	 }
	 
	 public EsenzioneGruppo(EsenzioneDEsenzione ede) {
     this.gruppo = ede.getSkGruppo().toString();
     this.codiceEsenzione = ede.getCodEsenzione();
     this.descrizioneEsenzione = ede.getDescEsenzione();
   }


  public String getGruppo() {
    return gruppo;
  }


  public void setGruppo(String gruppo) {
    this.gruppo = gruppo;
  }


  public String getCodiceEsenzione() {
    return codiceEsenzione;
  }


  public void setCodiceEsenzione(String codiceEsenzione) {
    this.codiceEsenzione = codiceEsenzione;
  }


  public String getDescrizioneEsenzione() {
    return descrizioneEsenzione;
  }


  public void setDescrizioneEsenzione(String descrizioneEsenzione) {
    this.descrizioneEsenzione = descrizioneEsenzione;
  }

	
}
