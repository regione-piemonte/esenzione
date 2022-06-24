/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Util;

public class LuogoNascita {

  protected String codice;

  protected String descrizione;

  public LuogoNascita() {
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public LuogoNascita(Cittadino c) {
    String codComuneNascitaEsenred = null;
    //calcolo il codice istat del comune
    if (!c.getComuneNascita().isEmpty()) {
      ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
      EsenredCComuni elencoComuni = comuneIf.getElencoComuniPerCodIstat(c.getCodComuneNascita());
      codComuneNascitaEsenred = elencoComuni.getCodiceFiscale();
    }

    this.codice = codComuneNascitaEsenred;
    this.descrizione = c.getComuneNascita();
  }

  public LuogoNascita(String codice, String descrizione) {
    String codComuneNascitaEsenred = null;
    //calcolo il codice istat del comune
    if (!codice.isEmpty()) {
      ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
      EsenredCComuni elencoComuni = comuneIf.getElencoComuniPerCodIstat(codice);
      codComuneNascitaEsenred = elencoComuni.getCodiceFiscale();
    }
    this.codice = codComuneNascitaEsenred;
    this.descrizione = descrizione;
  }
}