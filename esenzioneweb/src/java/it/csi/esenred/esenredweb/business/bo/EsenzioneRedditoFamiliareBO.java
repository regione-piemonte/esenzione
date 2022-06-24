/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class EsenzioneRedditoFamiliareBO {
    private String titolo;

    private String codFiscaleDichiarante;
    
    private String codFiscaleFamiliare;
    private String cognomeFamiliare;
    private String nomeFamiliare;
    private String comuneNascitaFamiliare;
    private String provinciaNascitaFamiliare;
    private String dataNascitaFamiliare;
    private String sessoFamiliare;

    private String codEsenzioneFamiliare;
    private String dataInizioValiditaFamiliare;
    private String dataScadenzaValiditaFamiliare;

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
    
   

    public EsenzioneRedditoFamiliareBO() {
    }

    public EsenzioneRedditoFamiliareBO(String titolo, String codFiscaleDichiarante,  String codFiscaleFamiliare, String cognomeFamiliare, String nomeFamiliare, String comuneNascitaFamiliare, String provinciaNascitaFamiliare, String dataNascitaFamiliare, String sessoFamiliare, String codEsenzioneFamiliare, String dataInizioValiditaFamiliare, String dataScadenzaValiditaFamiliare, Boolean accettaInformativa,String codFiscaleTitolare,String cognomeTitolare,String nomeTitolare, String comuneNascitaTitolare,String provinciaNascitaTitolare,String dataNascitaTitolare, String sessoTitolare, String sititolare) {
        this.titolo = titolo;
        this.codFiscaleDichiarante = codFiscaleDichiarante;
        this.codFiscaleFamiliare = codFiscaleFamiliare;
        this.cognomeFamiliare = cognomeFamiliare;
        this.nomeFamiliare = nomeFamiliare;
        this.comuneNascitaFamiliare = comuneNascitaFamiliare;
        this.provinciaNascitaFamiliare = provinciaNascitaFamiliare;
        this.dataNascitaFamiliare = dataNascitaFamiliare;
        this.sessoFamiliare = sessoFamiliare;
        this.codFiscaleTitolare = codFiscaleTitolare;
        this.cognomeTitolare = cognomeTitolare;
        this.nomeTitolare = nomeTitolare;
        this.comuneNascitaTitolare = comuneNascitaTitolare;
        this.provinciaNascitaTitolare = provinciaNascitaTitolare;
        this.dataNascitaTitolare = dataNascitaTitolare;
        this.sessoTitolare = sessoTitolare;
        this.codEsenzioneFamiliare = codEsenzioneFamiliare;
        this.dataInizioValiditaFamiliare = dataInizioValiditaFamiliare;
        this.dataScadenzaValiditaFamiliare = dataScadenzaValiditaFamiliare;
        this.accettaInformativa = accettaInformativa;
        this.sititolare = sititolare;
    }
    
    public String getSititolare() {
		return sititolare;
	}

	public void setSititolare(String sititolare) {
		this.sititolare = sititolare;
	}


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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

    public String getCodFiscaleFamiliare() {
        return codFiscaleFamiliare;
    }

    public void setCodFiscaleFamiliare(String codFiscaleFamiliare) {
        this.codFiscaleFamiliare = codFiscaleFamiliare;
    }

    public String getCognomeFamiliare() {
        return cognomeFamiliare;
    }

    public void setCognomeFamiliare(String cognomeFamiliare) {
        this.cognomeFamiliare = cognomeFamiliare;
    }

    public String getNomeFamiliare() {
        return nomeFamiliare;
    }

    public void setNomeFamiliare(String nomeFamiliare) {
        this.nomeFamiliare = nomeFamiliare;
    }

    public String getComuneNascitaFamiliare() {
        return comuneNascitaFamiliare;
    }

    public void setComuneNascitaFamiliare(String comuneNascitaFamiliare) {
        this.comuneNascitaFamiliare = comuneNascitaFamiliare;
    }

    public String getDataNascitaFamiliare() {
        return dataNascitaFamiliare;
    }

    public void setDataNascitaFamiliare(String dataNascitaFamiliare) {
        this.dataNascitaFamiliare = dataNascitaFamiliare;
    }

    public String getSessoFamiliare() {
        return sessoFamiliare;
    }

    public void setSessoFamiliare(String sessoFamiliare) {
        this.sessoFamiliare = sessoFamiliare;
    }

    public String getCodEsenzioneFamiliare() {
        return codEsenzioneFamiliare;
    }

    public void setCodEsenzioneFamiliare(String codEsenzioneFamiliare) {
        this.codEsenzioneFamiliare = codEsenzioneFamiliare;
    }

    public String getDataInizioValiditaFamiliare() {
        return dataInizioValiditaFamiliare;
    }

    public void setDataInizioValiditaFamiliare(String dataInizioValiditaFamiliare) {
        this.dataInizioValiditaFamiliare = dataInizioValiditaFamiliare;
    }

    public String getDataScadenzaValiditaFamiliare() {
        return dataScadenzaValiditaFamiliare;
    }

    public void setDataScadenzaValiditaFamiliare(String dataScadenzaValiditaFamiliare) {
        this.dataScadenzaValiditaFamiliare = dataScadenzaValiditaFamiliare;
    }

    public Boolean getAccettaInformativa() {
        return accettaInformativa;
    }

    public void setAccettaInformativa(Boolean accettaInformativa) {
        this.accettaInformativa = accettaInformativa;
    }

    public String getProvinciaNascitaFamiliare() {
        return provinciaNascitaFamiliare;
    }

    public void setProvinciaNascitaFamiliare(String provinciaNascitaFamiliare) {
        this.provinciaNascitaFamiliare = provinciaNascitaFamiliare;
    }

	public String getCodFiscaleDichiarante() {
		return codFiscaleDichiarante;
	}

	public void setCodFiscaleDichiarante(String codFiscaleDichiarante) {
		this.codFiscaleDichiarante = codFiscaleDichiarante;
	}
}
