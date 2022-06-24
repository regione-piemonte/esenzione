/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.util.DateUtil;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.entity.*;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "numero_pratica", "beneficiario", "data_richiesta", "data_revoca", "data_annullamento",
		"data_scadenza", "data_aggiornamento", "data_inizio_validita", "data_fine_validita",
    "certificato_id", "zip", "archiviata", "annullabile", "rinnovabile", "revocabile", "in_scadenza", "codice", "stato", "tipologia", "malattia", "richiesto_da", "revocato_da", "annullato_da",
    "motivazione_revoca", "motivazione_annullamento" })
public class EsenzioneCittadinoEsenpat {

  @JsonProperty("id")
  private String id;
  @JsonProperty("numero_pratica")
  private String numeroPratica;
	@JsonProperty("beneficiario")
	private Beneficiario beneficiario;
  @JsonProperty("data_richiesta")
  private String dataRichiesta;
  @JsonProperty("data_revoca")
  private String dataRevoca;
  @JsonProperty("data_annullamento")
  private String dataAnnullamento;
  @JsonProperty("data_scadenza")
  private String dataScadenza;
  @JsonProperty("data_aggiornamento")
  private String dataAggiornamento;
  @JsonProperty("data_inizio_validita")
  private String dataInizioValidita;
  @JsonProperty("data_fine_validita")
  private String dataFineValidita;
  @JsonProperty("certificato_id")
  private String certificatoId;
  @JsonProperty("zip")
  private Boolean zip;
  @JsonProperty("archiviata")
  private Boolean archiviata;
  @JsonProperty("annullabile")
  private Boolean annullabile;
  @JsonProperty("rinnovabile")
  private Boolean rinnovabile;
  @JsonProperty("revocabile")
  private Boolean revocabile;
  @JsonProperty("in_scadenza")
  private Boolean inScadenza;
  @JsonProperty("codice")
  private Codice codice;
  @JsonProperty("stato")
  private Stato stato;
  @JsonProperty("tipologia")
  private Tipologia tipologia;
  @JsonProperty("malattia")
  private Malattia malattia;
  @JsonProperty("richiesto_da")
  private RichiestoDa richiestoDa;
  @JsonProperty("revocato_da")
  private RevocatoDa revocatoDa;
  @JsonProperty("annullato_da")
  private AnnullatoDa annullatoDa;
  @JsonProperty("motivazione_revoca")
  private MotivazioneRevocaResponse motivazioneRevocaResponse;
  @JsonProperty("motivazione_annullamento")
  private MotivazioneAnnullamentoResponse motivazioneAnnullamentoResponse;
  @JsonProperty("motivazione")
  private Motivazione motivazione;

  public EsenzioneCittadinoEsenpat(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    this.id = esenzioneTPraticaEsenzione.getSkPraticaEsenzione().toString();
    this.numeroPratica = esenzioneTPraticaEsenzione.getNumPratica().toString();

		this.beneficiario = new Beneficiario(esenzioneTPraticaEsenzione.getEsenzioneTCittadino1() != null
				? esenzioneTPraticaEsenzione.getEsenzioneTCittadino1()
				: null);

		this.dataRichiesta = esenzioneTPraticaEsenzione.getDatCreazione() == null ? null
				: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatCreazione(),
						Constants.DATE_FORMAT_ITALIAN);
    //DATA REVOCA ANNULLATA e SCADUTA?
    //this.dataRichiesta = DateUtil.formatDate(esenzioneTPraticaEsenzione.getDatCreazione(), EsenzioniEndpoint.DATE_FORMAT);
		this.dataAggiornamento = esenzioneTPraticaEsenzione.getDatModifica() == null ? this.dataRichiesta
				: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatModifica(),
						Constants.DATE_FORMAT_ITALIAN);
		this.dataInizioValidita = esenzioneTPraticaEsenzione.getDatInizioValidita() == null ? null
				: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatInizioValidita(),
						Constants.DATE_FORMAT_ITALIAN);

		this.dataFineValidita = esenzioneTPraticaEsenzione.getDatFineValidita() == null ? null
				: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatFineValidita(),
						Constants.DATE_FORMAT_ITALIAN);

    List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones = null;
    EsenzioneSPraticaEsenzione lastEsenzioneSPraticaEsenzione = null;
    if (esenzioneTPraticaEsenzione.getEsenzioneSPraticaEsenziones() != null && !esenzioneTPraticaEsenzione.getEsenzioneSPraticaEsenziones().isEmpty()) {
      esenzioneSPraticaEsenziones = new ArrayList<EsenzioneSPraticaEsenzione>(esenzioneTPraticaEsenzione.getEsenzioneSPraticaEsenziones());
      Collections.sort(esenzioneSPraticaEsenziones);
      lastEsenzioneSPraticaEsenzione = esenzioneSPraticaEsenziones.iterator().next();

			this.dataRichiesta = lastEsenzioneSPraticaEsenzione.getDatCreazione() == null ? null
					: Converter.getStringDataFromTimeStamp(lastEsenzioneSPraticaEsenzione.getDatCreazione(),
							Constants.DATE_FORMAT_ITALIAN);


    } 
      if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_SCADUTA)) {
				this.dataFineValidita = esenzioneTPraticaEsenzione.getDatModifica() == null ? null
					: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatModifica(),
								Constants.DATE_FORMAT_ITALIAN);
        this.dataScadenza = this.dataFineValidita;
      } else if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_ANNULLATA)) {
				this.dataFineValidita = esenzioneTPraticaEsenzione.getDatModifica() == null ? null
					: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatModifica(),
								Constants.DATE_FORMAT_ITALIAN);
        this.dataAnnullamento = this.dataFineValidita;
      } else if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_REVOCATA)) {
				this.dataFineValidita = esenzioneTPraticaEsenzione.getDatModifica() == null ? null
					: Converter.getStringDataFromTimeStamp(esenzioneTPraticaEsenzione.getDatModifica(),
								Constants.DATE_FORMAT_ITALIAN);
        this.dataRevoca = this.dataFineValidita;
      }
//    }

    this.zip = false;
		this.certificatoId = null;
    if (esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos() != null && esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().size() > 0) {

			for (EsenzioneRPraticaEsenzioneDocumento doc : esenzioneTPraticaEsenzione
					.getEsenzioneRPraticaEsenzioneDocumentos()) {
				if (doc.getEsenzioneTDocumento().getEsenzioneDDocumentoTipo() != null
						&& doc.getEsenzioneTDocumento().getEsenzioneDDocumentoTipo().getCodDocumentoTipo()
								.equalsIgnoreCase(Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA)
						&& doc.getEsenzioneTDocumento().getEsenzioneDDocumentoStato() != null
						&& doc.getEsenzioneTDocumento().getEsenzioneDDocumentoStato().getCodStato()
								.equalsIgnoreCase(Constants.STATO_DOCUMENTO_VALIDO)) {
					this.certificatoId = doc.getEsenzioneTDocumento().getSkDocumento().toString();
					break;
				}
				if (doc.getEsenzioneTDocumento().getEsenzioneDDocumentoTipo() != null
						&& !doc.getEsenzioneTDocumento().getEsenzioneDDocumentoTipo().getCodDocumentoTipo()
								.equalsIgnoreCase(Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA)
						&& !doc.getEsenzioneTDocumento().getEsenzioneDDocumentoTipo().getCodDocumentoTipo()
								.equalsIgnoreCase(Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE)) {
					this.zip = true;
					break;
				}
			}
    }

    this.archiviata = false;
    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_SCADUTA)
        || esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_REVOCATA)
        || esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_ANNULLATA)) {
      this.archiviata = true;
    }
    this.annullabile = false;
    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)) {
      this.annullabile = true;
    }

    this.rinnovabile = false;
    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_SCADUTA)
        || esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_SCADENZA)) {
      this.rinnovabile = true;
    }

    this.revocabile = false;
    this.inScadenza = false;
    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_VALIDATA)) {
      this.revocabile = true;
      this.inScadenza = true;
    }

    this.codice = new Codice();
    //this.codice.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());
    this.codice.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
    //this.codice.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());
    this.codice.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getDescEsenzione());

    this.stato = new Stato();
    this.stato.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato());
    this.stato.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getDescStato());

    if (esenzioneTPraticaEsenzione.getEsenzioneDGruppoEsenzioni() != null) {
      this.tipologia = new Tipologia();
      this.tipologia.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo());
      this.tipologia.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDGruppoEsenzioni().getDescGruppo());
    }

    this.malattia = new Malattia();
    this.malattia.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());
    this.malattia.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getDescDiagnosi());
    this.malattia.setGiorni_validita(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getNumDurata());

		ArrayList<Prestazioni> prestazioni = new ArrayList<Prestazioni>();
      Prestazioni prestazione = null;
      for (EsenzioneRDiagnosiPrestazione esenzioneRDiagnosiPrestazione : esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneRDiagnosiPrestaziones()) {
        prestazione = new Prestazioni();
        prestazione.setCodice(esenzioneRDiagnosiPrestazione.getEsenzioneDPrestazioneSpecialistica().getCodPrestazione());
        prestazione.setDescrizione(esenzioneRDiagnosiPrestazione.getEsenzioneDPrestazioneSpecialistica().getDescPrestazione());
        prestazioni.add(prestazione);
      }
      this.malattia.setPrestazioni(prestazioni);


    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)
        || esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA_DAL_MEDICO)) {
      this.richiestoDa = new RichiestoDa();
      if (esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore() != null && !esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore().isEmpty()) {
        this.richiestoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore());
      } else if (esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato() != null && !esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato().isEmpty()) {
        this.richiestoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato());
      } else {
        this.richiestoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario());
      }
    }

    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_REVOCATA)) {
      this.motivazioneRevocaResponse = new MotivazioneRevocaResponse();
      this.motivazioneRevocaResponse.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDMotivazioneTipo().getCodMotivazione());
      this.motivazioneRevocaResponse.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDMotivazioneTipo().getDescMotivazione());
      this.revocatoDa = new RevocatoDa();
      if (esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore() != null && !esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore().isEmpty()) {
        this.revocatoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore());
      } else if (esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato() != null && !esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato().isEmpty()) {
        this.revocatoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato());
      } else {
        this.revocatoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario());
      }
    }

    if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_ANNULLATA)) {
      this.motivazioneAnnullamentoResponse = new MotivazioneAnnullamentoResponse();
      this.motivazioneAnnullamentoResponse.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDMotivazioneTipo().getCodMotivazione());
      this.motivazioneAnnullamentoResponse.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDMotivazioneTipo().getDescMotivazione());
      this.annullatoDa = new AnnullatoDa();
      if (esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore() != null && !esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore().isEmpty()) {
        this.annullatoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleOperatore());
      } else if (esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato() != null && !esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato().isEmpty()) {
        this.annullatoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato());
      } else {
        this.annullatoDa.setCodiceFiscale(esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario());
      }
    }

		if (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato()
				.equals(Constants.STATO_PRATICA_RESPINTA)) {
			this.motivazione = new Motivazione();
			this.motivazione.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDMotivazioneTipo().getCodMotivazione());
			this.motivazione
					.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDMotivazioneTipo().getDescMotivazione());
		}
  }


	public EsenzioneCittadinoEsenpat(InfoEsenzioneNew esenzioneAura, Beneficiario ben, EsenzioneDGruppoEsenzioni gruppo,
			List<EsenzioneDPrestazioneSpecialistica> prestazioniSpec) {
		this.id = null;
		this.numeroPratica = null;
		this.beneficiario = ben;

		this.dataRichiesta = esenzioneAura.getDataEmissione() == null ? null
				: DateUtil.formatDate(esenzioneAura.getDataEmissione().getTime(),
						Constants.DATE_FORMAT_ITALIAN);
		this.dataAggiornamento = this.dataRichiesta;
		this.dataInizioValidita = this.dataRichiesta;
		if (esenzioneAura.getDataScadenza() != null) {
			if (esenzioneAura.getDataSospensione() != null) {
				this.dataFineValidita = esenzioneAura.getDataScadenza().getTime()
						.before(esenzioneAura.getDataSospensione().getTime())
								? DateUtil.formatDate(esenzioneAura.getDataScadenza().getTime(),
										Constants.DATE_FORMAT_ITALIAN)
								: DateUtil.formatDate(esenzioneAura.getDataSospensione().getTime(),
										Constants.DATE_FORMAT_ITALIAN);
			} else {
				this.dataFineValidita = DateUtil.formatDate(esenzioneAura.getDataScadenza().getTime(),
						Constants.DATE_FORMAT_ITALIAN);
			}
		}
		
		this.dataScadenza = this.dataFineValidita;

		this.zip = false;
		this.certificatoId = null;

		this.codice = new Codice();
		this.codice.setCodice(esenzioneAura.getCodEsenzione());
		this.codice.setDescrizione(
				esenzioneAura.getDescEsenzione() != null ? esenzioneAura.getDescEsenzione().getDescEsenzione_type0()
						: "");

		this.stato = new Stato();
		if (this.dataScadenza != null) {
			if (Converter.getData(this.dataScadenza)
				.before(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
			this.stato.setCodice("SCA");
			this.stato.setDescrizione("Scaduta");
		} else {
			this.stato.setCodice("VAL");
			this.stato.setDescrizione("Validata");
		}
		}

		if (gruppo != null) {
			this.tipologia = new Tipologia();
			this.tipologia.setCodice(gruppo.getCodTipologiaGruppo());
			this.tipologia.setDescrizione(gruppo.getDescGruppo());
		}

		this.malattia = new Malattia();
		this.malattia.setCodice(
				esenzioneAura.getCodDiagnosi() != null ? esenzioneAura.getCodDiagnosi().getCodDiagnosi_type0() : null);
		this.malattia.setDescrizione(esenzioneAura.getDiagnosi() != null ? esenzioneAura.getDiagnosi() : "");

		if (prestazioniSpec != null) {
			ArrayList<Prestazioni> prestazioni = new ArrayList<Prestazioni>();
			Prestazioni prestazione = null;
			for (EsenzioneDPrestazioneSpecialistica pres : prestazioniSpec) {
				prestazione = new Prestazioni();
				prestazione.setCodice(pres.getCodPrestazione());
				prestazione.setDescrizione(pres.getDescPrestazione());
				prestazioni.add(prestazione);
			}
			this.malattia.setPrestazioni(prestazioni);
		}
	}

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("numero_pratica")
  public String getNumeroPratica() {
    return numeroPratica;
  }

  @JsonProperty("numero_pratica")
  public void setNumeroPratica(String numeroPratica) {
    this.numeroPratica = numeroPratica;
  }


	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	@JsonProperty("data_richiesta")
  public String getDataRichiesta() {
    return dataRichiesta;
  }

  @JsonProperty("data_richiesta")
  public void setDataRichiesta(String dataRichiesta) {
    this.dataRichiesta = dataRichiesta;
  }

  @JsonProperty("data_revoca")
  public String getDataRevoca() {
    return dataRevoca;
  }

  @JsonProperty("data_revoca")
  public void setDataRevoca(String dataRevoca) {
    this.dataRevoca = dataRevoca;
  }

  @JsonProperty("data_annullamento")
  public String getDataAnnullamento() {
    return dataAnnullamento;
  }

  @JsonProperty("data_annullamento")
  public void setDataAnnullamento(String dataAnnullamento) {
    this.dataAnnullamento = dataAnnullamento;
  }

  @JsonProperty("data_scadenza")
  public String getDataScadenza() {
    return dataScadenza;
  }

  @JsonProperty("data_scadenza")
  public void setDataScadenza(String dataScadenza) {
    this.dataScadenza = dataScadenza;
  }

  @JsonProperty("data_aggiornamento")
  public String getDataAggiornamento() {
    return dataAggiornamento;
  }

  @JsonProperty("data_aggiornamento")
  public void setDataAggiornamento(String dataAggiornamento) {
    this.dataAggiornamento = dataAggiornamento;
  }

  @JsonProperty("data_inizio_validita")
  public String getDataInizioValidita() {
    return dataInizioValidita;
  }

  @JsonProperty("data_inizio_validita")
  public void setDataInizioValidita(String dataInizioValidita) {
    this.dataInizioValidita = dataInizioValidita;
  }

  @JsonProperty("data_fine_validita")
  public String getDataFineValidita() {
    return dataFineValidita;
  }

  @JsonProperty("data_fine_validita")
  public void setDataFineValidita(String dataFineValidita) {
    this.dataFineValidita = dataFineValidita;
  }

  @JsonProperty("certificato_id")
  public String getCertificatoId() {
    return certificatoId;
  }

  @JsonProperty("certificato_id")
  public void setCertificatoId(String certificatoId) {
    this.certificatoId = certificatoId;
  }

  @JsonProperty("zip")
  public Boolean getZip() {
    return zip;
  }

  @JsonProperty("zip")
  public void setZip(Boolean zip) {
    this.zip = zip;
  }

  @JsonProperty("archiviata")
  public Boolean getArchiviata() {
    return archiviata;
  }

  @JsonProperty("archiviata")
  public void setArchiviata(Boolean archiviata) {
    this.archiviata = archiviata;
  }

  @JsonProperty("annullabile")
  public Boolean getAnnullabile() {
    return annullabile;
  }

  @JsonProperty("annullabile")
  public void setAnnullabile(Boolean annullabile) {
    this.annullabile = annullabile;
  }

  @JsonProperty("rinnovabile")
  public Boolean getRinnovabile() {
    return rinnovabile;
  }

  @JsonProperty("rinnovabile")
  public void setRinnovabile(Boolean rinnovabile) {
    this.rinnovabile = rinnovabile;
  }

  @JsonProperty("revocabile")
  public Boolean getRevocabile() {
    return revocabile;
  }

  @JsonProperty("revocabile")
  public void setRevocabile(Boolean revocabile) {
    this.revocabile = revocabile;
  }

  @JsonProperty("in_scadenza")
  public Boolean getInScadenza() {
    return inScadenza;
  }

  @JsonProperty("in_scadenza")
  public void setInScadenza(Boolean inScadenza) {
    this.inScadenza = inScadenza;
  }

  @JsonProperty("codice")
  public Codice getCodice() {
    return codice;
  }

  @JsonProperty("codice")
  public void setCodice(Codice codice) {
    this.codice = codice;
  }

  @JsonProperty("stato")
  public Stato getStato() {
    return stato;
  }

  @JsonProperty("stato")
  public void setStato(Stato stato) {
    this.stato = stato;
  }

  @JsonProperty("tipologia")
  public Tipologia getTipologia() {
    return tipologia;
  }

  @JsonProperty("tipologia")
  public void setTipologia(Tipologia tipologia) {
    this.tipologia = tipologia;
  }

  @JsonProperty("malattia")
  public Malattia getMalattia() {
    return malattia;
  }

  @JsonProperty("malattia")
  public void setMalattia(Malattia malattia) {
    this.malattia = malattia;
  }

  @JsonProperty("richiesto_da")
  public RichiestoDa getRichiestoDa() {
    return richiestoDa;
  }

  @JsonProperty("richiesto_da")
  public void setRichiestoDa(RichiestoDa richiestoDa) {
    this.richiestoDa = richiestoDa;
  }

  @JsonProperty("revocato_da")
  public RevocatoDa getRevocatoDa() {
    return revocatoDa;
  }

  @JsonProperty("revocato_da")
  public void setRevocatoDa(RevocatoDa revocatoDa) {
    this.revocatoDa = revocatoDa;
  }

  @JsonProperty("annullato_da")
  public AnnullatoDa getAnnullatoDa() {
    return annullatoDa;
  }

  @JsonProperty("annullato_da")
  public void setAnnullatoDa(AnnullatoDa annullatoDa) {
    this.annullatoDa = annullatoDa;
  }

  @JsonProperty("motivazione_revoca")
  public MotivazioneRevocaResponse getMotivazioneRevoca() {
    return motivazioneRevocaResponse;
  }

  @JsonProperty("motivazione_revoca")
  public void setMotivazioneRevoca(MotivazioneRevocaResponse motivazioneRevocaResponse) {
    this.motivazioneRevocaResponse = motivazioneRevocaResponse;
  }

  @JsonProperty("motivazione_annullamento")
  public MotivazioneAnnullamentoResponse getMotivazioneAnnullamento() {
    return motivazioneAnnullamentoResponse;
  }

  @JsonProperty("motivazione_annullamento")
  public void setMotivazioneAnnullamento(MotivazioneAnnullamentoResponse motivazioneAnnullamentoResponse) {
    this.motivazioneAnnullamentoResponse = motivazioneAnnullamentoResponse;
  }
}
