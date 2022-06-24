/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;


import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.util.Converter;

public class EsenzioneCittadino {

	protected Long idEsenzione;

    protected String codTitolo;

    protected String descTitolo;
    
    protected String idAuraBeneficiario;

    protected String nomeBeneficiario;

    protected String cognomeBeneficiario;

    protected String codFiscaleBeneficiario;

    protected String codSessoBeneficiario;

    protected String indirizzoResidenza;
    
    protected String IdUserInsert;
    
    public String getIdUserInsert() {
		return IdUserInsert;
	}

	public void setIdUserInsert(String idUserInsert) {
		IdUserInsert = idUserInsert;
	}


	protected Cittadino titolare;

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	public String getCittaResidenza() {
		return cittaResidenza;
	}

	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}


	protected String dataNascitaBeneficiario;

    protected String comuneNascitaBeneficiario;

    protected String codcomuneNascitaBeneficiario;

	private String capResidenza;

	private String cittaResidenza;

	protected String provinciaNascitaBeneficiario;

    protected String stato;
    
    protected String codStato;

    protected String codEsenzione;

    protected String descEsenzione;

    protected String dataRichiesta;

    protected String inizioValidita;

    protected String fineValidita;

    protected Boolean revocabile;
    
    protected Boolean stampabile;
    
    protected String numProtocolloSogei;
    
    protected String motivoRevoca;

    protected String dataRevoca;
    
    protected Boolean sititolare;
    
    
	public Boolean getSititolare() {
		return sititolare;
	}

	public void setSititolare(Boolean sititolare) {
		this.sititolare = sititolare;
	}

	public String getDataRevoca() {
		return dataRevoca;
	}

	public void setDataRevoca(String dataRevoca) {
		this.dataRevoca = dataRevoca;
	}

	public String getProvinciaNascitaBeneficiario() {
		return provinciaNascitaBeneficiario;
	}

	public void setProvinciaNascitaBeneficiario(String provinciaNascitaBeneficiario) {
		this.provinciaNascitaBeneficiario = provinciaNascitaBeneficiario;
	}

	public String getNumProtocolloSogei() {
		return numProtocolloSogei;
	}

	public void setNumProtocolloSogei(String numProtocolloSogei) {
		this.numProtocolloSogei = numProtocolloSogei;
	}

	public String getMotivoRevoca() {
		return motivoRevoca;
	}

	public void setMotivoRevoca(String motivoRevoca) {
		this.motivoRevoca = motivoRevoca;
	}

	public EsenzioneCittadino() {}
    
    public String getIdAuraBeneficiario() {
		return idAuraBeneficiario;
	}

	public void setIdAuraBeneficiario(String idAuraBeneficiario) {
		this.idAuraBeneficiario = idAuraBeneficiario;
	}

	public String getCodStato() {
		return codStato;
	}

	public void setCodStato(String codStato) {
		this.codStato = codStato;
	}

	public Boolean getStampabile() {
		return stampabile;
	}

	public void setStampabile(Boolean stampabile) {
		this.stampabile = stampabile;
	}

	public String getCodSessoBeneficiario() {
        return codSessoBeneficiario;
    }

    public void setCodSessoBeneficiario(String codSessoBeneficiario) {
        this.codSessoBeneficiario = codSessoBeneficiario;
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
    
    public String getCodComuneNascitaBeneficiario() {
        return codcomuneNascitaBeneficiario;
    }

    public void setCodComuneNascitaBeneficiario(String codcomuneNascitaBeneficiario) {
        this.codcomuneNascitaBeneficiario = codcomuneNascitaBeneficiario;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public String getCognomeBeneficiario() {
        return cognomeBeneficiario;
    }

    public void setCognomeBeneficiario(String cognomeBeneficiario) {
        this.cognomeBeneficiario = cognomeBeneficiario;
    }

    public String getCodFiscaleBeneficiario() {
        return codFiscaleBeneficiario;
    }

    public void setCodFiscaleBeneficiario(String codFiscaleBeneficiario) {
        this.codFiscaleBeneficiario = codFiscaleBeneficiario;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(String dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getInizioValidita() {
        return inizioValidita;
    }

    public void setInizioValidita(String inizioValidita) {
        this.inizioValidita = inizioValidita;
    }

    public String getFineValidita() {
        return fineValidita;
    }

    public void setFineValidita(String fineValidita) {
        this.fineValidita = fineValidita;
    }

    public String getCodTitolo() {
        return codTitolo;
    }

    public void setCodTitolo(String codTitolo) {
        this.codTitolo = codTitolo;
    }

    public String getDescTitolo() {
        return descTitolo;
    }

    public void setDescTitolo(String descTitolo) {
        this.descTitolo = descTitolo;
    }

    public String getCodEsenzione() {
        return codEsenzione;
    }

    public void setCodEsenzione(String codEsenzione) {
        this.codEsenzione = codEsenzione;
    }

    public String getDescEsenzione() {
        return descEsenzione;
    }
   
    public void setDescEsenzione(String descEsenzione) {
        this.descEsenzione = descEsenzione;
    }

    public Boolean getRevocabile() {
        return revocabile;
    }

    public void setRevocabile(Boolean revocabile) {
        this.revocabile = revocabile;
    }

    public Long getIdEsenzione() {
        return idEsenzione;
    }

    public void setIdEsenzione(Long idEsenzione) {
        this.idEsenzione = idEsenzione;
    }


    public Cittadino getTitolare() {
		return titolare;
	}

	public void setTitolare(Cittadino titolare) {
		this.titolare = titolare;
	}

	public String getCodcomuneNascitaBeneficiario() {
		return codcomuneNascitaBeneficiario;
	}

	public void setCodcomuneNascitaBeneficiario(String codcomuneNascitaBeneficiario) {
		this.codcomuneNascitaBeneficiario = codcomuneNascitaBeneficiario;
	}

	public EsenzioneCittadino(EsenredTEsenzioniReddito esenred, Cittadino c) {
		this(esenred, c, null,null);
	}
	public EsenzioneCittadino(EsenredTEsenzioniReddito esenred, Cittadino c, Cittadino t, Cittadino d) {
        this.codTitolo = esenred.getCodTitoloDichiarante();
        this.descTitolo = esenred.getEsenredCTitoloDichiarante().getDescrizione();

        this.idAuraBeneficiario = c.getIdAura();
        this.nomeBeneficiario = c.getNome();
        this.cognomeBeneficiario = c.getCognome();
        this.codFiscaleBeneficiario = c.getCodFiscale();
        this.codSessoBeneficiario = c.getCodSesso();
        this.dataNascitaBeneficiario = c.getDataNascita();
        this.comuneNascitaBeneficiario = c.getComuneNascita();
        this.codcomuneNascitaBeneficiario=c.getCodComuneNascita();
        this.provinciaNascitaBeneficiario = c.getProvinciaNascita();
        
        this.indirizzoResidenza = c.getIndirizzoResidenza();
        this.capResidenza = c.getCapResidenza();
        this.cittaResidenza = c.getCittaResidenza();
        		
        this.stato = StatoEsenzione.getDescrizione(esenred.getCodStato());
        this.codStato = esenred.getCodStato();
        this.codEsenzione = esenred.getCodEsenzione();
        this.descEsenzione = esenred.getTipoEsenzione().getDescMotivo();
        this.dataRichiesta = Converter.getData(esenred.getDataRichiesta());
        this.inizioValidita = Converter.getData(esenred.getDataInizio());
        this.fineValidita = Converter.getData(esenred.getDataFine());

        this.revocabile = esenred.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice());
        this.stampabile = esenred.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice());
        
        this.idEsenzione = esenred.getIdEsenzione();
        
        this.numProtocolloSogei = esenred.getNumProtocolloSogei() != null ? esenred.getNumProtocolloSogei().toString() : null;
        this.motivoRevoca = esenred.getDescMotivoRevoca();
        this.IdUserInsert = esenred.getIdUserInsert().toString();
        this.dataRevoca = Converter.getData(esenred.getDataRevoca());
        Cittadino dichiarante = null;
        if (esenred.getIdCittadinoTitolare()!=null) {
        if (esenred.getIdCittadinoTitolare().equals(esenred.getIdCittadinoDichiarante())) {
        	//dichiarante = IntegrationClientImpl.getInstance().getCittadino(esenred.getIdCittadinoTitolare().toString());
        	this.titolare = d;
        	this.sititolare = true;	
        } else if (esenred.getIdCittadinoTitolare().equals(esenred.getIdCittadinoBeneficiario())) {
        	this.titolare = c;
            this.sititolare = true;
        }
        else
        {
        	 this.titolare = t;
             this.sititolare = false;
        }
    }
        else
        {
        	this.sititolare = true;	
        	 this.titolare = null;
        }
	}
}