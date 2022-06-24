/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;

public class InvioEsenzioneAuraResponse {

  private String codice;

  private String descrizione;

  private String numProtocolloSogei;

  private String esito;

  public InvioEsenzioneAuraResponse(Response r) {
    this.codice = r.getBody().getCodice();
    this.descrizione = r.getBody().getDescrizione();
    this.numProtocolloSogei = r.getBody().getNumProtMef();
    this.esito = r.getBody().getEsito();
  }

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

  public String getNumProtocolloSogei() {
    return numProtocolloSogei;
  }

  public void setNumProtocolloSogei(String numProtocolloSogei) {
    this.numProtocolloSogei = numProtocolloSogei;
  }

  public String getEsito() {
    return esito;
  }

  public void setEsito(String esito) {
    this.esito = esito;
  }

}
