/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "gruppo",
  "codice_esenzione",
  "codice_diagnosi",
  "descrizione_diagnosi",
})
public class ListaDiagnosi {

    @JsonProperty("gruppo")
    private String gruppo;
    @JsonProperty("codice_esenzione")
    private String codiceEsenzione;
    @JsonProperty("codice_diagnosi")
    private String codiceDiagnosi;
    @JsonProperty("descrizione_diagnosi")
    private String descrizioneDiagnosi;

    
    public ListaDiagnosi(EsenzioneDDiagnosi diagnosi) {
      this.gruppo = diagnosi.getEsenzioneDEsenzione().getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo(); 
      this.codiceEsenzione = diagnosi.getEsenzioneDEsenzione().getCodEsenzione(); 
      this.codiceDiagnosi = diagnosi.getCodDiagnosi(); 
      this.descrizioneDiagnosi = diagnosi.getDescDiagnosi(); 
    }
  
    
    public ListaDiagnosi() {}




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




    public String getCodiceDiagnosi() {
      return codiceDiagnosi;
    }




    public void setCodiceDiagnosi(String codiceDiagnosi) {
      this.codiceDiagnosi = codiceDiagnosi;
    }




    public String getDescrizioneDiagnosi() {
      return descrizioneDiagnosi;
    }




    public void setDescrizioneDiagnosi(String descrizioneDiagnosi) {
      this.descrizioneDiagnosi = descrizioneDiagnosi;
    }

    
}
