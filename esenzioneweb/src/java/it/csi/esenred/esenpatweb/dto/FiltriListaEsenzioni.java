/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class FiltriListaEsenzioni {

  private Filtri archiviata;
  private Filtri stato;
  private Filtri tipologia_esenzione;

  public Filtri getArchiviata() {
    return archiviata;
  }

  public void setArchiviata(Filtri archiviata) {
    this.archiviata = archiviata;
  }

  public Filtri getStato() {
    return stato;
  }

  public void setStato(Filtri stato) {
    this.stato = stato;
  }

  public Filtri getTipologia_esenzione() {
    return tipologia_esenzione;
  }

  public void setTipologia_esenzione(Filtri tipologia_esenzione) {
    this.tipologia_esenzione = tipologia_esenzione;
  }
}
