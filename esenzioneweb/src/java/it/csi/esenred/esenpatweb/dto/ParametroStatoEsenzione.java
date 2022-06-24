/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;

public class ParametroStatoEsenzione {

  protected String codice;

  protected String descrizione;

  public ParametroStatoEsenzione() {
  }

  public ParametroStatoEsenzione(Parametro pDB) {
    this.codice = pDB.getCodParametro();
    this.descrizione = pDB.getValoreParametro();
  }

  public ParametroStatoEsenzione(EsenredCParametri pDB) {
    this.codice = pDB.getCodice().substring(pDB.getCodice().length() - 1);
    this.descrizione = pDB.getValore();
  }

  public ParametroStatoEsenzione(String codice, String descrizione) {
    this.codice = codice;
    this.descrizione = descrizione;
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

  public void setDescrizione(String Descrizione) {
    this.descrizione = Descrizione;
  }

  @Override
  public String toString() {
    return "ParametroStatoEsenzione [codice=" + codice + ", descrizione=" + descrizione + "]";
  }
}