/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

public class CreatoPer {

	protected String codice_fiscale;
	protected String nome;
	protected String cognome;
	protected String sesso;
	protected String data_nascita;

	protected String luogo_nascita;

	public String getLuogo_nascita() {
		return luogo_nascita;
	}

	public void setLuogo_nascita(String luogo_nascita) {
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

	public CreatoPer() {
	}



	public CreatoPer(String codice_fiscale, String nome, String cognome, String sesso, String data_nascita,
			String luogo_nascita) {
		this.codice_fiscale = codice_fiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.sesso = sesso;
		this.luogo_nascita = luogo_nascita;
	}

	@Override
	public String toString() {
		return "CreatoPer [codice_fiscale=" + codice_fiscale + ", nome=" + nome + ", cognome=" + cognome + ", sesso="
				+ sesso + ", data_nascita=" + data_nascita + ", luogo_nascita=" + luogo_nascita + "]";
	}
}