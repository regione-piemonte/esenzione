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
@JsonPropertyOrder({
    "codice_fiscale",
    "nome",
    "cognome",
    "data_nascita",
    "comune_nascita",
    "sesso",
    "id_aura"
})
public class AnnullatoDa {

    @JsonProperty("codice_fiscale")
    private String codiceFiscale;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cognome")
    private String cognome;
    @JsonProperty("data_nascita")
    private String dataNascita;
    @JsonProperty("comune_nascita")
    private String comuneNascita;
    @JsonProperty("sesso")
    private String sesso;
    @JsonProperty("id_aura")
    private Integer idAura;

    @JsonProperty("codice_fiscale")
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    @JsonProperty("codice_fiscale")
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("cognome")
    public String getCognome() {
        return cognome;
    }

    @JsonProperty("cognome")
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @JsonProperty("data_nascita")
    public String getDataNascita() {
        return dataNascita;
    }

    @JsonProperty("data_nascita")
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    @JsonProperty("comune_nascita")
    public String getComuneNascita() {
        return comuneNascita;
    }

    @JsonProperty("comune_nascita")
    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    @JsonProperty("sesso")
    public String getSesso() {
        return sesso;
    }

    @JsonProperty("sesso")
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    @JsonProperty("id_aura")
    public Integer getIdAura() {
        return idAura;
    }

    @JsonProperty("id_aura")
    public void setIdAura(Integer idAura) {
        this.idAura = idAura;
    }
}
