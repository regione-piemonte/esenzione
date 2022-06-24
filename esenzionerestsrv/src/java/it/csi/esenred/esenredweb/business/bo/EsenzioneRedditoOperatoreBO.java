/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class EsenzioneRedditoOperatoreBO {
	
	private String codFiscaleOperatore;
	
	private String codTitolo;
	
	private String codFiscaleDichiarante;
	private String cognomeDichiarante;
    private String nomeDichiarante;
    private String sessoDichiarante;
    private String dataNascitaDichiarante;
    private String comuneNascitaDichiarante;
    private String provinciaNascitaDichiarante;
    
    private String codFiscaleBeneficiario;
	private String cognomeBeneficiario;
    private String nomeBeneficiario;
    private String sessoBeneficiario;
    private String dataNascitaBeneficiario;
    private String comuneNascitaBeneficiario;
    private String provinciaNascitaBeneficiario;

    private String codEsenzioneBeneficiario;
    private String dataRichiesta;
    private String dataInizioValidita;
    private String dataScadenzaValidita;
    private String nota;

    private String codFiscaleTitolare;
    private String cognomeTitolare;
    private String nomeTitolare;
    private String comuneNascitaTitolare;
	private String provinciaNascitaTitolare;
    private String dataNascitaTitolare;
    private String sessoTitolare;
    private String sititolare;
    private String datascadenzaSSN;
    
	public String getDatascadenzaSSN() {
		return datascadenzaSSN;
	}
	public void setDatascadenzaSSN(String datascadenzaSSN) {
		this.datascadenzaSSN = datascadenzaSSN;
	}
	public String getSititolare() {
		return sititolare;
	}
	public void setSititolare(String sititolare) {
		this.sititolare = sititolare;
	}
	public String getCodFiscaleOperatore() {
		return codFiscaleOperatore;
	}
	public void setCodFiscaleOperatore(String codFiscaleOperatore) {
		this.codFiscaleOperatore = codFiscaleOperatore;
	}
	public String getCodTitolo() {
		return codTitolo;
	}
	public void setCodTitolo(String codTitolo) {
		this.codTitolo = codTitolo;
	}
	public String getCodFiscaleDichiarante() {
		return codFiscaleDichiarante;
	}
	public void setCodFiscaleDichiarante(String codFiscaleDichiarante) {
		this.codFiscaleDichiarante = codFiscaleDichiarante;
	}
	public String getCognomeDichiarante() {
		return cognomeDichiarante;
	}
	public void setCognomeDichiarante(String cognomeDichiarante) {
		this.cognomeDichiarante = cognomeDichiarante;
	}
	public String getNomeDichiarante() {
		return nomeDichiarante;
	}
	public void setNomeDichiarante(String nomeDichiarante) {
		this.nomeDichiarante = nomeDichiarante;
	}
	public String getSessoDichiarante() {
		return sessoDichiarante;
	}
	public void setSessoDichiarante(String sessoDichiarante) {
		this.sessoDichiarante = sessoDichiarante;
	}
	public String getDataNascitaDichiarante() {
		return dataNascitaDichiarante;
	}
	public void setDataNascitaDichiarante(String dataNascitaDichiarante) {
		this.dataNascitaDichiarante = dataNascitaDichiarante;
	}
	public String getComuneNascitaDichiarante() {
		return comuneNascitaDichiarante;
	}
	public void setComuneNascitaDichiarante(String comuneNascitaDichiarante) {
		this.comuneNascitaDichiarante = comuneNascitaDichiarante;
	}
	public String getProvinciaNascitaDichiarante() {
		return provinciaNascitaDichiarante;
	}
	public void setProvinciaNascitaDichiarante(String provinciaNascitaDichiarante) {
		this.provinciaNascitaDichiarante = provinciaNascitaDichiarante;
	}
	public String getDataRichiesta() {
		return dataRichiesta;
	}
	public void setDataRichiesta(String dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}
	public String getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public String getDataScadenzaValidita() {
		return dataScadenzaValidita;
	}
	public void setDataScadenzaValidita(String dataScadenzaValidita) {
		this.dataScadenzaValidita = dataScadenzaValidita;
	}
	public String getCodFiscaleBeneficiario() {
		return codFiscaleBeneficiario;
	}
	public void setCodFiscaleBeneficiario(String codFiscaleBeneficiario) {
		this.codFiscaleBeneficiario = codFiscaleBeneficiario;
	}
	public String getCognomeBeneficiario() {
		return cognomeBeneficiario;
	}
	public void setCognomeBeneficiario(String cognomeBeneficiario) {
		this.cognomeBeneficiario = cognomeBeneficiario;
	}
	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}
	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	public String getSessoBeneficiario() {
		return sessoBeneficiario;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public void setSessoBeneficiario(String sessoBeneficiario) {
		this.sessoBeneficiario = sessoBeneficiario;
	}
	public String getDataNascitaBeneficiario() {
		return dataNascitaBeneficiario;
	}
	public void setDataNascitaBeneficiario(String dataNascitaBeneficiario) {
		this.dataNascitaBeneficiario = dataNascitaBeneficiario;
	}
	public String getComuneNascitaBeneficiario() {
		return comuneNascitaBeneficiario;
	}
	public void setComuneNascitaBeneficiario(String comuneNascitaBeneficiario) {
		this.comuneNascitaBeneficiario = comuneNascitaBeneficiario;
	}
	public String getProvinciaNascitaBeneficiario() {
		return provinciaNascitaBeneficiario;
	}
	public void setProvinciaNascitaBeneficiario(String provinciaNascitaBeneficiario) {
		this.provinciaNascitaBeneficiario = provinciaNascitaBeneficiario;
	}
	public String getCodEsenzioneBeneficiario() {
		return codEsenzioneBeneficiario;
	}
	public void setCodEsenzioneBeneficiario(String codEsenzioneBeneficiario) {
		this.codEsenzioneBeneficiario = codEsenzioneBeneficiario;
	}
	public String getCodFiscaleTitolare() {
		return codFiscaleTitolare;
	}
	public void setCodFiscaleTitolare(String codFiscaleTitolare) {
		this.codFiscaleTitolare = codFiscaleTitolare;
	}
	public String getCognomeTitolare() {
		return cognomeTitolare;
	}
	public void setCognomeTitolare(String cognomeTitolare) {
		this.cognomeTitolare = cognomeTitolare;
	}
	public String getNomeTitolare() {
		return nomeTitolare;
	}
	public void setNomeTitolare(String nomeTitolare) {
		this.nomeTitolare = nomeTitolare;
	}
	public String getComuneNascitaTitolare() {
		return comuneNascitaTitolare;
	}
	public void setComuneNascitaTitolare(String comuneNascitaTitolare) {
		this.comuneNascitaTitolare = comuneNascitaTitolare;
	}
	public String getProvinciaNascitaTitolare() {
		return provinciaNascitaTitolare;
	}
	public void setProvinciaNascitaTitolare(String provinciaNascitaTitolare) {
		this.provinciaNascitaTitolare = provinciaNascitaTitolare;
	}
	public String getDataNascitaTitolare() {
		return dataNascitaTitolare;
	}
	public void setDataNascitaTitolare(String dataNascitaTitolare) {
		this.dataNascitaTitolare = dataNascitaTitolare;
	}
	public String getSessoTitolare() {
		return sessoTitolare;
	}
	public void setSessoTitolare(String sessoTitolare) {
		this.sessoTitolare = sessoTitolare;
	}
	
	
}