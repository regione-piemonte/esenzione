/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import it.csi.esenred.esenredweb.util.Converter;

public class ListaBeneficiari {

  protected String codice_fiscale;

  protected String nome;

  protected String cognome;

  protected String sesso;

  protected String data_nascita;

  protected LuogoNascita luogo_nascita;

  public ListaBeneficiari() {
  }

  public ListaBeneficiari(Cittadino c) {
    this.codice_fiscale = c.getCodFiscale();
    this.nome = c.getNome();
    this.cognome = c.getCognome();
    this.sesso = c.getCodSesso();
    this.data_nascita = Converter.getDataISO(Converter.getData(c.getDataNascita()));
    this.luogo_nascita = new LuogoNascita(c);

  }

  public String getCodice_fiscale() {
    return codice_fiscale;
  }

  public void setCodice_fiscale(String codice_fiscale) {
    this.codice_fiscale = codice_fiscale;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getSesso() {
    return sesso;
  }

  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  public String getData_nascita() {
    return data_nascita;
  }

  public void setData_nascita(String data_nascita) {
    this.data_nascita = data_nascita;
  }

  public LuogoNascita getLuogo_nascita() {
    return luogo_nascita;
  }

  public void setLuogo_nascita(LuogoNascita luogo_nascita) {
    this.luogo_nascita = luogo_nascita;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }
}