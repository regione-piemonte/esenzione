/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class EsenzioneRedditoAutocertificazioneBO {

    private String codEsenzione;
    private String dataInizioValidita;
    private String dataScadenza;
    private String codFiscale;
    private String cognome;
    private String nome;
    private String sesso;
    private String dataNascita;
    private String comuneNascita;
    private String provinciaNascita;
    private Boolean accettaInformativa;
    //aggiunta jira 111 titolare
    private String codFiscaleTitolare;
    private String cognomeTitolare;
    private String nomeTitolare;
    private String comuneNascitaTitolare;
	private String provinciaNascitaTitolare;
    private String dataNascitaTitolare;
    private String sessoTitolare;
    private String sititolare;
    

	public EsenzioneRedditoAutocertificazioneBO() {}

    public EsenzioneRedditoAutocertificazioneBO(String codEsenzione, String dataInizioValidita, String dataScadenza, String codFiscale, String cognome, String nome, String sesso, String dataNascita, String comuneNascita, String provinciaNascita, Boolean accettaInformativa,String codFiscaleTitolare,String cognomeTitolare,String nomeTitolare, String comuneNascitaTitolare,String provinciaNascitaTitolare,String dataNascitaTitolare, String sessoTitolare, String sititolare) {
        this.codEsenzione = codEsenzione;
        this.dataInizioValidita = dataInizioValidita;
        this.dataScadenza = dataScadenza;
        this.codFiscale = codFiscale;
        this.cognome = cognome;
        this.nome = nome;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
        this.comuneNascita = comuneNascita;
        this.provinciaNascita = provinciaNascita;
        this.accettaInformativa = accettaInformativa;
        this.codFiscaleTitolare = codFiscaleTitolare;
        this.cognomeTitolare = cognomeTitolare;
        this.nomeTitolare = nomeTitolare;
        this.comuneNascitaTitolare = comuneNascitaTitolare;
        this.provinciaNascitaTitolare = provinciaNascitaTitolare;
        this.dataNascitaTitolare = dataNascitaTitolare;
        this.sessoTitolare = sessoTitolare;
        this.sititolare = sititolare;
    }

   
    
    public String getSititolare() {
		return sititolare;
	}

	public void setSititolare(String sititolare) {
		this.sititolare = sititolare;
	}

	public String getCodEsenzione() {
        return codEsenzione;
    }

    public void setCodEsenzione(String codEsenzione) {
        this.codEsenzione = codEsenzione;
    }

    public String getDataInizioValidita() {
        return dataInizioValidita;
    }

    public void setDataInizioValidita(String dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
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

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
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

    public Boolean getAccettaInformativa() {
        return accettaInformativa;
    }

    public void setAccettaInformativa(Boolean accettaInformativa) {
        this.accettaInformativa = accettaInformativa;
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
