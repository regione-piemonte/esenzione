/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class FiltriListaCertificati {

  private Filtri utilizzabile;
  private Filtri tipologia_esenzione;

  public Filtri getUtilizzabile() {
    return utilizzabile;
  }

  public void setUtilizzabile(Filtri utilizzabile) {
    this.utilizzabile = utilizzabile;
  }

  public Filtri getTipologia_esenzione() {
    return tipologia_esenzione;
  }

  public void setTipologia_esenzione(Filtri tipologia_esenzione) {
    this.tipologia_esenzione = tipologia_esenzione;
  }

}
