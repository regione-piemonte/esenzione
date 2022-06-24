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
    "cit_id",
    "esenzione_id",
})
public class AggiornaAttestato {

    @JsonProperty("cit_id")
    private String citId;
    @JsonProperty("esenzione_id")
    private String esenzioneId;
    @JsonProperty("codice_batch")
    private String codBatch;
    @JsonProperty("batch_id")
    private String batchId;
    
    public AggiornaAttestato() {}
    
	public String getCitId() {
		return citId;
	}
	public void setCitId(String citId) {
		this.citId = citId;
	}
	public String getEsenzioneId() {
		return esenzioneId;
	}
	public void setEsenzioneId(String esenzioneId) {
		this.esenzioneId = esenzioneId;
	}

	public String getCodBatch() {
		return codBatch;
	}

	public void setCodBatch(String codBatch) {
		this.codBatch = codBatch;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	
    
  
}
