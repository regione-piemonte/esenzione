/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.dto;

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
@JsonPropertyOrder({
    "codice",
    "descrizione"
})
public class Stato {

    @JsonProperty("codice")
    private String codice;
    @JsonProperty("descrizione")
    private String descrizione;

    @JsonProperty("codice")
    public String getCodice() {
        return codice;
    }

    @JsonProperty("codice")
    public void setCodice(String codice) {
        this.codice = codice;
    }

    @JsonProperty("descrizione")
    public String getDescrizione() {
        return descrizione;
    }

    @JsonProperty("descrizione")
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
