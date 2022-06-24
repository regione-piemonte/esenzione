/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

public class TitolareIns {

	protected String codice_fiscale;
	protected String nome;
	protected String cognome;
	protected String sesso;
	protected String data_nascita;
	protected LuogoNascita luogo_nascita;

	public LuogoNascita getLuogo_nascita() {
		return luogo_nascita;
	}

	public void setLuogo_nascita(LuogoNascita luogo_nascita) {
		this.luogo_nascita = luogo_nascita;
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

	public TitolareIns() {
	}

	public TitolareIns(Cittadino c) {
		this.codice_fiscale = c.getCodFiscale();
		this.nome = c.getNome();
		this.cognome = c.getCognome();
		this.data_nascita = c.getDataNascita();
		this.sesso = c.getCodSesso();
		this.luogo_nascita = new LuogoNascita(c);
	}

	@Override
	public String toString() {
		return "TitolareIns [codice_fiscale=" + codice_fiscale + ", nome=" + nome + ", cognome=" + cognome + ", sesso="
				+ sesso + ", data_nascita=" + data_nascita + ", luogo_nascita=" + luogo_nascita + "]";
	}
}