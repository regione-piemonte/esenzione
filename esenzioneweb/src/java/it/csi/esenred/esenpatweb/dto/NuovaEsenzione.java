/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.List;

import org.codehaus.jackson.annotate.*;
import org.jboss.resteasy.util.DateUtil;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.util.Constants;

@JsonPropertyOrder({ "assistito", "malattia", "documenti", "noteServizio", "noteInterne" })
public class NuovaEsenzione {

  @JsonProperty("assistito")
  private Assistito assistito;
  
  @JsonProperty("malattia")
  private MalattiaEsenzione malattia;
 
  @JsonProperty("documenti")
  private List<Documenti> documenti = null;

  @JsonProperty("noteServizio")
  private String noteServizio;
  
  @JsonProperty("noteInterne")
  private String noteInterne;
  
  public NuovaEsenzione() {}
  
  public Assistito getAssistito() {
    return assistito;
  }

  public void setAssistito(Assistito assistito) {
    this.assistito = assistito;
  }
  

  public MalattiaEsenzione getMalattia() {
    return malattia;
  }

  public void setMalattia(MalattiaEsenzione malattia) {
    this.malattia = malattia;
  }

  public String getNoteServizio() {
    return noteServizio;
  }

  public void setNoteServizio(String noteServizio) {
    this.noteServizio = noteServizio;
  }

  public String getNoteInterne() {
    return noteInterne;
  }

  public void setNoteInterne(String noteInterne) {
    this.noteInterne = noteInterne;
  }

  @JsonProperty("documenti")
  public List<Documenti> getDocumenti() {
      return documenti;
  }

  @JsonProperty("documenti")
  public void setDocumenti(List<Documenti> documenti) {
      this.documenti = documenti;
  }
  
}
