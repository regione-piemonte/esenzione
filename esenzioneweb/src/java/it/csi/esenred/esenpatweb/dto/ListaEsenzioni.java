/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;

@JsonPropertyOrder({
  "gruppo",
  "codice_esenzione",
  "descrizione_esenzione",
})
public class ListaEsenzioni {
  @JsonProperty("gruppo")
  private String gruppo;
  @JsonProperty("codice_esenzione")
	private String codiceEsenzione;
  @JsonProperty("descrizione_esenzione")
	private String descrizioneEsenzione;
	
	 
	 public ListaEsenzioni(EsenzioneDEsenzione ede) {
     this.gruppo = ede.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo();
     this.codiceEsenzione = ede.getCodEsenzione();
     this.descrizioneEsenzione = ede.getDescEsenzione();
   }
	 
   public ListaEsenzioni(String skGruppo, String codEsenzione, String descEsenzione) {
     this.gruppo = skGruppo;
     this.codiceEsenzione = codEsenzione;
     this.descrizioneEsenzione = descEsenzione;
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
