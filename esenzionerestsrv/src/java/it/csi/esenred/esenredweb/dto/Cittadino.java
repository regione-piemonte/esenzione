/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import java.util.List;

import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

public class Cittadino implements Comparable<Cittadino>{
	
	private String idAura;
	
	private String indirizzoResidenza;
	
	private String codFiscale;
	
	private String cognome;

	private String capResidenza;

	private String cittaResidenza;

	private String stato;

	private String datascadenzaSSN;

	private String nome;

	private String dataNascita;

	private String codSesso;

	private String codComuneNascita;

	private String comuneNascita;

	private String provinciaNascita;

	private String statoProfiloAnagrafico;

	private String codASL;

	private String codDistrettoSocioSanitario;

	private String codiceTesseraRegionale;
	
	private List<InfoEsenzioneNew> esenzioniAuraValide;
	private List<InfoEsenzioneNew> esenzioniAuraScadute;

	public Cittadino() {
	}

	public Cittadino(String cfInput) {
		this.codFiscale = cfInput;
	}

	public Cittadino(it.csi.esenred.esenredweb.business.deleghebe.Cittadino cittadinoDel) {
		this.codFiscale = cittadinoDel.getCodiceFiscale();
		this.nome = cittadinoDel.getNome();
		this.cognome = cittadinoDel.getCognome();
		this.idAura = cittadinoDel.getIdAura().toString();
		this.dataNascita = cittadinoDel.getDataNascita().toString();
		this.comuneNascita = cittadinoDel.getComuneNascita();
	}
	
	public Cittadino(EsenzioneTCittadino cittadino) {
		this.nome = cittadino.getNome();
		this.cognome = cittadino.getCognome();
		this.codFiscale = cittadino.getCodiceFiscale();
		this.codSesso = cittadino.getSesso();
		this.dataNascita = Converter.getStringDataFromTimeStamp(cittadino.getDataDiNascita(),
				Constants.DATE_FORMAT_ITALIAN);
		this.comuneNascita = cittadino.getComuneDiNascita();
		this.idAura = cittadino.getId_aura();
	}


	public String getDatascadenzaSSN() {
		return datascadenzaSSN;
	}

	public void setDatascadenzaSSN(String datascadenzaSSN) {
		this.datascadenzaSSN = datascadenzaSSN;
	}

	public String getCittaResidenza() {
		return cittaResidenza;
	}

	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String cap) {
		this.capResidenza = cap;
	}

	public String getCodASL() {
		return codASL;
	}

	public void setCodASL(String codASL) {
		this.codASL = codASL;
	}

	public String getStatoProfiloAnagrafico() {
		return statoProfiloAnagrafico;
	}

	public void setStatoProfiloAnagrafico(String statoProfiloAnagrafico) {
		this.statoProfiloAnagrafico = statoProfiloAnagrafico;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzo) {
		this.indirizzoResidenza = indirizzo;
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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodSesso() {
		return codSesso;
	}

	public void setCodSesso(String codSesso) {
		this.codSesso = codSesso;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public String getCodComuneNascita() {
		return codComuneNascita;
	}

	public void setCodComuneNascita(String codComuneNascita) {
		this.codComuneNascita = codComuneNascita;
	}

	public String getCodDistrettoSocioSanitario() {
		return codDistrettoSocioSanitario;
	}

	public void setCodDistrettoSocioSanitario(String codDistrettoSocioSanitario) {
		this.codDistrettoSocioSanitario = codDistrettoSocioSanitario;
	}

	public String getCodiceTesseraRegionale() {
		return codiceTesseraRegionale;
	}

	public void setCodiceTesseraRegionale(String codiceTesseraRegionale) {
		this.codiceTesseraRegionale = codiceTesseraRegionale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAura == null) ? 0 : idAura.hashCode());
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
		Cittadino other = (Cittadino) obj;
		if (idAura == null) {
			if (other.idAura != null)
				return false;
		} else if (!idAura.equals(other.idAura))
			return false;
		return true;
	}

	@Override
	public int compareTo(Cittadino o) {
		String parametroSort = this.cognome + " " + this.nome;
		String parametroSortC = o.cognome + " " + o.nome;
		return parametroSort.compareTo(parametroSortC);
	}
	
	@Override
	public String toString() {
		return "Cittadino [idAura=" + idAura + ", indirizzoResidenza=" + indirizzoResidenza + ", codFiscale="
				+ codFiscale + ", cognome=" + cognome + ", capResidenza=" + capResidenza + ", cittaResidenza="
				+ cittaResidenza + ", stato=" + stato + ", nome=" + nome + ", dataNascita=" + dataNascita
				+ ", codSesso=" + codSesso + ", codComuneNascita=" + codComuneNascita + ", comuneNascita="
				+ comuneNascita + ", provinciaNascita=" + provinciaNascita + ", statoProfiloAnagrafico="
				+ statoProfiloAnagrafico + ", codASL=" + codASL + "]";
	}

	public List<InfoEsenzioneNew> getEsenzioniAuraValide() {
		return esenzioniAuraValide;
	}

	public void setEsenzioniAuraValide(List<InfoEsenzioneNew> esenzioniAuraValide) {
		this.esenzioniAuraValide = esenzioniAuraValide;
	}

	public List<InfoEsenzioneNew> getEsenzioniAuraScadute() {
		return esenzioniAuraScadute;
	}

	public void setEsenzioniAuraScadute(List<InfoEsenzioneNew> esenzioniAuraScadute) {
		this.esenzioniAuraScadute = esenzioniAuraScadute;
	}
	
}
