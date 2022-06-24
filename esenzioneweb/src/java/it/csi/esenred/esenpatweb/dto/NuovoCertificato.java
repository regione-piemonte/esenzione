/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.*;
import org.jboss.resteasy.util.DateUtil;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.util.Constants;

@JsonPropertyOrder({ "assistito", "certificato"})
public class NuovoCertificato {

  @JsonProperty("assistito")
  private Assistito assistito;
  
  @JsonProperty("certificato")
  private CertificatoEsenpat certificatoEsenpat;

  
  public NuovoCertificato() {}
  
  public Assistito getAssistito() {
    return assistito;
  }

  public void setAssistito(Assistito assistito) {
    this.assistito = assistito;
  }

  public CertificatoEsenpat getCertificatoEsenpat() {
    return certificatoEsenpat;
  }

  public void setCertificatoEsenpat(CertificatoEsenpat certificatoEsenpat) {
    this.certificatoEsenpat = certificatoEsenpat;
  }

  
  
}
