/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.List;

import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDAzione;

public class UserInfo implements Comparable<UserInfo>{
	
	private String cognome;
	
	private String nome;
	
	private String codFisc;
	
	private String codASL;
	
	private String idAura;
	
	private List<EsenzioneDAzione> azioni;
	
	private List<Ruolo> ruoli;
		
	/**
	 * Restituisce la lista delle azioni associate all'utente loggato. NB: PROVIENE DA FRONTEND, DA NON UTILIZZARE PER I CONTROLLI
	 * @return
	 */
	@Deprecated
	public List<EsenzioneDAzione> getAzioni() {
		return azioni;
	}

	public void setAzioni(List<EsenzioneDAzione> list) {
		this.azioni = list;
	}

	/**
	 * Restituisce la lista dei ruoli associati all'utente loggato. NB: PROVIENE DA FRONTEND, DA NON UTILIZZARE PER I CONTROLLI
	 * @return
	 */
	@Deprecated
	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	
	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	public String getCodASL() {
		return codASL;
	}

	public void setCodASL(String codASL) {
		this.codASL = codASL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFisc == null) ? 0 : codFisc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (codFisc == null) {
			if (other.codFisc != null)
				return false;
		} else if (!codFisc.equals(other.codFisc))
			return false;
		return true;
	}

	@Override
	public int compareTo(UserInfo o) {
		if (this.equals(o))
		      return 0;
		    return 1;
	}
	
	
}
