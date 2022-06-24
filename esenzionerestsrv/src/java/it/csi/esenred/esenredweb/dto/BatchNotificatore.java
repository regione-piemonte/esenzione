/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.dto;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cit_id",
    "esenzione_id",
})
public class BatchNotificatore {

    @JsonProperty("cit_id")
    private String citId;
    @JsonProperty("esenzione_id")
    private String esenzioneId;
    @JsonProperty("codice_batch")
    private String codBatch;
    @JsonProperty("batch_id")
    private String batchId;
    @JsonProperty("parametro_batch")
    private String parametroBatch;
    
    public BatchNotificatore() {}
    
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

	public String getParametroBatch() {
		return parametroBatch;
	}

	public void setParametroBatch(String parametroBatch) {
		this.parametroBatch = parametroBatch;
	}

	
    
  
}
