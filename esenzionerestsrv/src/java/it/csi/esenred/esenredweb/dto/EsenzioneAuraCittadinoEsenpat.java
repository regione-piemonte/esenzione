/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.jboss.resteasy.util.DateUtil;

import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzioneAuraArchivio;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDInvaliditaTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRDiagnosiPrestazione;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

@JsonPropertyOrder({ "data_emissione", "data_scadenza", "revocabile", 
	"codice", "stato", "tipologia", "tipologia_invalidita", "malattia" })
public class EsenzioneAuraCittadinoEsenpat {

	@JsonProperty("data_emissione")
	private String dataEmissione;
	@JsonProperty("data_scadenza")
	private String dataScadenza;
	@JsonProperty("revocabile")
	private Boolean revocabile;
	@JsonProperty("codice")
	private Codice codice;
	@JsonProperty("stato")
	private Stato stato;
	@JsonProperty("tipologia")
	private Tipologia tipologia;
	@JsonProperty("tipologia_invalidita")
	private TipologiaInvalidita tipologiaInvalidita;
	@JsonProperty("malattia")
	private Malattia malattia;
	
	public EsenzioneAuraCittadinoEsenpat() {}
	
	public EsenzioneAuraCittadinoEsenpat( InfoEsenzioneNew esenzioneAura, EsenzioneDEsenzione esen, EsenzioneDDiagnosi diag, EsenzioneDPraticaStato stato, EsenzioneDInvaliditaTipo invalidita) {
		this.dataEmissione = esenzioneAura.getDataEmissione() == null ? null
				: DateUtil.formatDate(esenzioneAura.getDataEmissione().getTime(), Constants.DATE_FORMAT);
		
		this.dataScadenza = esenzioneAura.getDataScadenza() == null ? null 
				: DateUtil.formatDate(esenzioneAura.getDataScadenza().getTime(), Constants.DATE_FORMAT);
		
		if (esenzioneAura.getDataScadenza() != null) {
			if (esenzioneAura.getDataScadenza().getTime()
					.after(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
				this.revocabile = true;
			} else {
				this.revocabile = false;
			}
		} else {
			this.revocabile = true;
		}
		
		this.codice = new Codice();
		this.codice.setCodice(esenzioneAura.getCodEsenzione());
		this.codice.setDescrizione(esen.getDescEsenzione());

		this.stato = new Stato();
		if(stato == null) {			
			if (esenzioneAura.getDataScadenza() != null) {
				if (!esenzioneAura.getDataScadenza().getTime()
						.before(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
					this.stato.setCodice(Constants.STATO_PRATICA_VALIDATA);
					this.stato.setDescrizione("Validata");
				} else {
					this.stato.setCodice(Constants.STATO_PRATICA_SCADUTA);
					this.stato.setDescrizione("Scaduta");
				}
			} else {
				this.stato.setCodice(Constants.STATO_PRATICA_VALIDATA);
				this.stato.setDescrizione("Validata");
			}
		} else {
			this.stato.setCodice(stato.getCodStato());
			this.stato.setDescrizione(stato.getDescStato());
		}

		this.tipologia = new Tipologia();
		this.tipologia.setCodice(esen.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo());
		this.tipologia.setDescrizione(esen.getEsenzioneDGruppoEsenzioni().getDescGruppo());
		
		if (invalidita != null) this.tipologiaInvalidita = new TipologiaInvalidita(invalidita);

		this.malattia = new Malattia();
		this.malattia.setCodice(esenzioneAura.getCodDiagnosi().getCodDiagnosi_type0());
		this.malattia.setDescrizione(this.malattia.getCodice().equalsIgnoreCase("000") || diag == null ? 
				esenzioneAura.getDiagnosi() : diag.getDescDiagnosi());
		this.malattia.setGiorni_validita(diag != null ? diag.getNumDurata() : null);

		ArrayList<Prestazioni> prestazioni = new ArrayList<Prestazioni>();
		if(diag != null) {			
			Prestazioni prestazione = null;
			for (EsenzioneRDiagnosiPrestazione rDP : diag.getEsenzioneRDiagnosiPrestaziones()) {
				prestazione = new Prestazioni();
				prestazione.setCodice(rDP.getEsenzioneDPrestazioneSpecialistica().getCodPrestazione());
				prestazione.setDescrizione(rDP.getEsenzioneDPrestazioneSpecialistica().getDescPrestazione());
				prestazioni.add(prestazione);
			}
		}
		this.malattia.setPrestazioni(prestazioni);
	}

	public EsenzioneAuraCittadinoEsenpat(InfoEsenzioneNew esenzioneAura, EsenzioneDEsenzioneAuraArchivio esen,
			EsenzioneDGruppoEsenzioni gruppoEsen, EsenzioneDPraticaStato stato, EsenzioneDInvaliditaTipo invalidita) {
		this.dataEmissione = esenzioneAura.getDataEmissione() == null ? null
				: DateUtil.formatDate(esenzioneAura.getDataEmissione().getTime(), Constants.DATE_FORMAT);
		
		this.dataScadenza = esenzioneAura.getDataScadenza() == null ? null 
				: DateUtil.formatDate(esenzioneAura.getDataScadenza().getTime(), Constants.DATE_FORMAT);
		
		if (esenzioneAura.getDataScadenza() != null) {
			if (esenzioneAura.getDataScadenza().getTime()
					.after(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
				this.revocabile = true;
			} else {
				this.revocabile = false;
			}
		} else {
			this.revocabile = true;
		}
		
		this.codice = new Codice();
		this.codice.setCodice(esenzioneAura.getCodEsenzione());
		this.codice.setDescrizione(esen.getDescEsenzione());

		this.stato = new Stato();
		if(stato == null) {			
			if (esenzioneAura.getDataScadenza() != null) {
				if (!esenzioneAura.getDataScadenza().getTime()
						.before(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
					this.stato.setCodice(Constants.STATO_PRATICA_VALIDATA);
					this.stato.setDescrizione("Validata");
				} else {
					this.stato.setCodice(Constants.STATO_PRATICA_SCADUTA);
					this.stato.setDescrizione("Scaduta");
				}
			} else {
				this.stato.setCodice(Constants.STATO_PRATICA_VALIDATA);
				this.stato.setDescrizione("Validata");
			}
		} else {
			this.stato.setCodice(stato.getCodStato());
			this.stato.setDescrizione(stato.getDescStato());
		}

		this.tipologia = new Tipologia();
		this.tipologia.setCodice(gruppoEsen.getCodTipologiaGruppo());
		this.tipologia.setDescrizione(gruppoEsen.getDescGruppo());
		
		if (invalidita != null) this.tipologiaInvalidita = new TipologiaInvalidita(invalidita);

		this.malattia = new Malattia();
		this.malattia.setCodice(esen.getCodDiagnosi());
		this.malattia.setDescrizione(esen.getDescDiagnosi());
		this.malattia.setGiorni_validita(null);

		ArrayList<Prestazioni> prestazioni = new ArrayList<Prestazioni>();
		if(!esen.getPrestazioneEsente().trim().isEmpty()) {			
			Prestazioni prestazione = new Prestazioni();
			prestazione.setCodice("0");
			prestazione.setDescrizione(esen.getPrestazioneEsente());
			prestazioni.add(prestazione);
			
		}
		this.malattia.setPrestazioni(prestazioni);
	}

	@JsonProperty("data_emissione")
	public String getDataEmissione() {
		return dataEmissione;
	}

	@JsonProperty("data_emissione")
	public void setDataEmissione(String dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	@JsonProperty("data_scadenza")
	public String getDataScadenza() {
		return dataScadenza;
	}

	@JsonProperty("data_scadenza")
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	@JsonProperty("revocabile")
	public Boolean getRevocabile() {
		return revocabile;
	}

	@JsonProperty("revocabile")
	public void setRevocabile(Boolean revocabile) {
		this.revocabile = revocabile;
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

	public TipologiaInvalidita getTipologiaInvalidita() {
		return tipologiaInvalidita;
	}

	public void setTipologiaInvalidita(TipologiaInvalidita tipologiaInvalidita) {
		this.tipologiaInvalidita = tipologiaInvalidita;
	}

	@JsonProperty("malattia")
	public Malattia getMalattia() {
		return malattia;
	}

	@JsonProperty("malattia")
	public void setMalattia(Malattia malattia) {
		this.malattia = malattia;
	}
}
