/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.*;

@JsonPropertyOrder({ "data_invio", "gruppoEsenzione", "codDiagnosi", "codEsenzione", "notePatologia", "noteAsl" })
public class CertificatoEsenpat {
  @JsonProperty("data_invio")
  private String data_invio;
  @JsonProperty("gruppoEsenzione")
  private String gruppoEsenzione;
  @JsonProperty("codDiagnosi")
  private String codDiagnosi;
  @JsonProperty("codEsenzione")
  private String codEsenzione;
  @JsonProperty("notePatologia")
  private String notePatologia;
  @JsonProperty("noteAsl")
  private String noteAsl;
  
  public CertificatoEsenpat() {
  }
  
  public String getData_invio() {
    return data_invio;
  }
  public void setData_invio(String data_invio) {
    this.data_invio = data_invio;
  }
  public String getGruppoEsenzione() {
    return gruppoEsenzione;
  }
  public void setGruppoEsenzione(String gruppoEsenzione) {
    this.gruppoEsenzione = gruppoEsenzione;
  }
  public String getCodDiagnosi() {
    return codDiagnosi;
  }
  public void setCodDiagnosi(String codDiagnosi) {
    this.codDiagnosi = codDiagnosi;
  }
  public String getCodEsenzione() {
    return codEsenzione;
  }
  public void setCodEsenzione(String codEsenzione) {
    this.codEsenzione = codEsenzione;
  }
  public String getNotePatologia() {
    return notePatologia;
  }
  public void setNotePatologia(String notePatologia) {
    this.notePatologia = notePatologia;
  }
  public String getNoteAsl() {
    return noteAsl;
  }
  public void setNoteAsl(String noteAsl) {
    this.noteAsl = noteAsl;
  }

  
}

