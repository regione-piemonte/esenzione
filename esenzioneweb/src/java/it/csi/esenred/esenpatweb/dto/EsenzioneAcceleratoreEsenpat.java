/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;


import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenpatweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.util.Converter;

public class EsenzioneAcceleratoreEsenpat {

  protected Long id;

  protected String protocollo;

  protected String data_richiesta;

  protected String data_inizio_validita;

  protected String data_scadenza;

  protected String data_revoca;

  protected String motivazione_revoca;

  protected Boolean revocabile;

  protected Boolean stampabile;

  // protected String storico;

  protected boolean familiare;

  //protected String tipoesenzione;

  protected ParametroStatoEsenzione stato;

  protected EsenzioneCodiceAcceleratore codice_esenzione;

  protected CreatoDaIns creato_da;

  protected CreatoPerIns creato_per;

  protected TitolareIns titolare;

  protected TitoloDichiaranteAcceleratore rapporto_familiare;

  protected String codAura;

  protected String messaggio;

  //	public String getTipoesenzione() {
  //		return tipoesenzione;
  //	}
  //
  //	public void setTipoesenzione(String tipoesenzione) {
  //		this.tipoesenzione = tipoesenzione;
  //	}

  public boolean getFamiliare() {
    return familiare;
  }

  public void setFamiliare(boolean familiare) {
    this.familiare = familiare;
  }

  public String getCodAura() {
    return codAura;
  }

  public void setCodAura(String codAura) {
    this.codAura = codAura;
  }

  //	public String getStorico() {
  //		return storico;
  //	}
  //
  //	public void setStorico(String Storico) {
  //		this.storico = Storico;
  //	}

  public String getMessaggio() {
    return messaggio;
  }

  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProtocollo() {
    return protocollo;
  }

  public void setProtocollo(String protocollo) {
    this.protocollo = protocollo;
  }

  public String getData_richiesta() {
    return data_richiesta;
  }

  public void setData_richiesta(String data_richiesta) {
    this.data_richiesta = data_richiesta;
  }

  public String getData_inizio_validita() {
    return data_inizio_validita;
  }

  public void setData_inizio_validita(String data_inizio_validita) {
    this.data_inizio_validita = data_inizio_validita;
  }

  public String getData_scadenza() {
    return data_scadenza;
  }

  public void setData_scadenza(String data_scadenza) {
    this.data_scadenza = data_scadenza;
  }

  public String getData_revoca() {
    return data_revoca;
  }

  public void setData_revoca(String data_revoca) {
    this.data_revoca = data_revoca;
  }

  public String getMotivazione_revoca() {
    return motivazione_revoca;
  }

  public void setMotivazione_revoca(String motivazione_revoca) {
    this.motivazione_revoca = motivazione_revoca;
  }

  public Boolean getRevocabile() {
    return revocabile;
  }

  public void setRevocabile(Boolean revocabile) {
    this.revocabile = revocabile;
  }

  public Boolean getStampabile() {
    return stampabile;
  }

  public void setStampabile(Boolean stampabile) {
    this.stampabile = stampabile;
  }

  public ParametroStatoEsenzione getStato() {
    return stato;
  }

  public void setStato(ParametroStatoEsenzione stato) {
    this.stato = stato;
  }

  public EsenzioneCodiceAcceleratore getCodice_esenzione() {
    return codice_esenzione;
  }

  public void setCodice_esenzione(EsenzioneCodiceAcceleratore codice_esenzione) {
    this.codice_esenzione = codice_esenzione;
  }

  public CreatoDaIns getCreato_da() {
    return creato_da;
  }

  public void setCreato_da(CreatoDaIns creato_da) {
    this.creato_da = creato_da;
  }

  public TitolareIns getTitolare() {
    return titolare;
  }

  public void setTitolare(TitolareIns titolare) {
    this.titolare = titolare;
  }

  public CreatoPerIns getCreato_per() {
    return creato_per;
  }

  public void setCreato_per(CreatoPerIns creato_per) {
    this.creato_per = creato_per;
  }

  public TitoloDichiaranteAcceleratore getRapporto_familiare() {
    return rapporto_familiare;
  }

  public void setRapporto_familiare(TitoloDichiaranteAcceleratore rapporto_familiare) {
    this.rapporto_familiare = rapporto_familiare;
  }

  public EsenzioneAcceleratoreEsenpat() {
  }
  //	public EsenzioneAcceleratore(EsenredTEsenzioniReddito esenred, Cittadino beneficiario, Cittadino dichiarante) {
  //		this(esenred, beneficiario, dichiarante,null);
  //	}

  public EsenzioneAcceleratoreEsenpat(EsenredTEsenzioniReddito esenred, Cittadino beneficiario, Cittadino dichiarante, Cittadino titolare) {
    this.rapporto_familiare = new TitoloDichiaranteAcceleratore(esenred.getEsenredCTitoloDichiarante());
    this.id = esenred.getIdEsenzione();
    this.data_richiesta = Converter.getDataISO(esenred.getDataRichiesta());
    this.data_inizio_validita = Converter.getDataISO(esenred.getDataInizio());
    this.data_scadenza = Converter.getDataISO(esenred.getDataFine());
    this.revocabile = esenred.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice());
    this.stampabile = esenred.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice());
    //		if (Storico) 
    //			this.storico = "Esenzione Storica";
    //		else
    //			this.storico = "Esenzione in corso";
    this.protocollo = null;
    if (esenred.getNumProtocolloSogei() != null) {
      if (this.id == 0 && esenred.getNumProtocolloSogei().toString().startsWith("1")) {
        this.protocollo = esenred.getNumProtocolloSogei() != null ? "0" + esenred.getNumProtocolloSogei().toString() : null;
      } else {
        this.protocollo = esenred.getNumProtocolloSogei() != null ? esenred.getNumProtocolloSogei().toString() : null;
      }
    }

    this.motivazione_revoca = esenred.getDescMotivoRevoca();
    this.data_revoca = Converter.getDataISO(esenred.getDataRevoca());
    this.stato = new ParametroStatoEsenzione(esenred.getCodStato(), StatoEsenzione.getDescrizione(esenred.getCodStato()));
    this.codice_esenzione = new EsenzioneCodiceAcceleratore(esenred.getTipoEsenzione());
    this.creato_da = new CreatoDaIns();
    this.creato_per = new CreatoPerIns();
    this.titolare = new TitolareIns();
    if (dichiarante != null) {
      if (dichiarante.getIdAura() != null && !dichiarante.getIdAura().equalsIgnoreCase("0")) {
        this.creato_da.codice_fiscale = dichiarante.getCodFiscale();
        this.creato_da.cognome = dichiarante.getCognome();
        this.creato_da.nome = dichiarante.getNome();
        this.creato_da.sesso = dichiarante.getCodSesso();
        this.creato_da.data_nascita = Converter.getDataISO(Converter.getData(dichiarante.getDataNascita()));
        this.creato_da.luogo_nascita = new LuogoNascita(dichiarante.getCodComuneNascita(), dichiarante.getComuneNascita());
      }
    }
    if (beneficiario.getIdAura() != null && !beneficiario.getIdAura().equalsIgnoreCase("0")) {
      this.creato_per.codice_fiscale = beneficiario.getCodFiscale();
      this.creato_per.cognome = beneficiario.getCognome();
      this.creato_per.nome = beneficiario.getNome();
      this.creato_per.sesso = beneficiario.getCodSesso();
      this.creato_per.data_nascita = Converter.getDataISO(Converter.getData(beneficiario.getDataNascita()));
      this.creato_per.luogo_nascita = new LuogoNascita(beneficiario.getCodComuneNascita(), beneficiario.getComuneNascita());
    }
    if (titolare != null) {
      if (titolare.getIdAura() != null && !titolare.getIdAura().equalsIgnoreCase("0")) {
        this.titolare.codice_fiscale = titolare.getCodFiscale();
        this.titolare.cognome = titolare.getCognome();
        this.titolare.nome = titolare.getNome();
        this.titolare.sesso = titolare.getCodSesso();
        this.titolare.data_nascita = Converter.getDataISO(Converter.getData(titolare.getDataNascita()));
        this.titolare.luogo_nascita = new LuogoNascita(titolare.getCodComuneNascita(), titolare.getComuneNascita());
      } else {
        this.creato_per.codice_fiscale = beneficiario.getCodFiscale();
        this.creato_per.cognome = beneficiario.getCognome();
        this.creato_per.nome = beneficiario.getNome();
        this.creato_per.sesso = beneficiario.getCodSesso();
        this.creato_per.data_nascita = Converter.getDataISO(Converter.getData(beneficiario.getDataNascita()));
        this.creato_per.luogo_nascita = new LuogoNascita(beneficiario.getCodComuneNascita(), beneficiario.getComuneNascita());
      }
    }
    if (dichiarante != null) {
      if (dichiarante.getIdAura() != null && !dichiarante.getIdAura().equalsIgnoreCase("0")) {
        if (dichiarante.getIdAura().equalsIgnoreCase(beneficiario.getIdAura()))
          this.familiare = false;
        else
          this.familiare = true;
      } else
        this.familiare = false;
    }
    //			if (esenred.getIdUserInsert() == 99999997)
    //				this.tipoesenzione = "Ceritificata";
    //			else if (esenred.getIdUserInsert() == 99999999)
    //				this.tipoesenzione = "AutoCeritificata";
    //			else
    //				this.tipoesenzione = "Autocertificata Esenred";
  }

  @Override
  public String toString() {
    return "EsenzioneAcceleratore [id=" + id + ", protocollo=" + protocollo + ", data_richiesta=" + data_richiesta + ", data_inizio_validita=" + data_inizio_validita + ", data_scadenza="
        + data_scadenza + ", data_revoca=" + data_revoca + ", motivazione_revoca=" + motivazione_revoca + ", revocabile=" + revocabile + ", stampabile=" + stampabile + ",  familiare=" + familiare
        + ", stato=" + stato.toString() + ", codice_esenzione=" + codice_esenzione.toString() + ", creato_da=" + creato_da.toString() + ", creato_per=" + creato_per.toString()
        + ", rapporto_familiare=" + rapporto_familiare.toString() + ", codAura=" + codAura + ", messaggio=" + messaggio + ",  titolare=" + titolare.toString() + "]";
  }
}