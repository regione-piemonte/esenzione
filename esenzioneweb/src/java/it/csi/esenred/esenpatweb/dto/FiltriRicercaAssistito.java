/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class FiltriRicercaAssistito {

  private UserInfo utente;

  private UserInfo assistito;

  public UserInfo getUtente() {
    return utente;
  }

  public void setUtente(UserInfo utente) {
    this.utente = utente;
  }

  public UserInfo getAssistito() {
    return assistito;
  }

  public void setAssistito(UserInfo assistito) {
    this.assistito = assistito;
  }


}
