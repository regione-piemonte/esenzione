/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;


//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "gruppoEsenzione",
  "codEsenzione",
  "codDiagnosi"
  })
public class MalattiaEsenzione {

    @JsonProperty("gruppoEsenzione")
    private String gruppoEsenzione;
    @JsonProperty("codEsenzione")
    private String codEsenzione;
    @JsonProperty("codDiagnosi")
    private String codDiagnosi;
    
    public MalattiaEsenzione() {}

    
    public String getCodEsenzione() {
      return codEsenzione;
    }

    public void setCodEsenzione(String codEsenzione) {
      this.codEsenzione = codEsenzione;
    }

    public String getCodDiagnosi() {
      return codDiagnosi;
    }

    public void setCodDiagnosi(String codDiagnosi) {
      this.codDiagnosi = codDiagnosi;
    }

    public String getGruppoEsenzione() {
      return gruppoEsenzione;
    }

    public void setGruppoEsenzione(String gruppoEsenzione) {
      this.gruppoEsenzione = gruppoEsenzione;
    }
    
}
