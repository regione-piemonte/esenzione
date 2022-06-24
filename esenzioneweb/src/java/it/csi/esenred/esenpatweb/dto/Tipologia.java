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

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "codice",
    "descrizione"
})
public class Tipologia {

    @JsonProperty("codice")
    private String codice;
    @JsonProperty("descrizione")
    private String descrizione;
    
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public Tipologia() {
    }

    public Tipologia(EsenzioneDDocumentoTipo eddt)
    {
      this.codice = eddt.getCodDocumentoTipo();
      this.descrizione = eddt.getDescDocumentoTipo();
    }
    
}
