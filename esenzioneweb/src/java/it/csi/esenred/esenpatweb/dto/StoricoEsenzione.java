/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenpatweb.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "data_creazione", "nuovo_stato", "utente", "nota" })

public class StoricoEsenzione implements Comparable<StoricoEsenzione> {

	@JsonProperty("id")
	private String id;
	@JsonProperty("data_creazione")
	private String dataCreazione;
	@JsonProperty("nuovo_stato")
	private Stato stato;
	@JsonProperty("utente")
	private Utente utente;

	@JsonProperty("nota")
	private String nota;

	@JsonProperty("notaBeneficiario")
	private String notaBeneficiario;

	@JsonProperty("notaOperatore")
	private String notaOperatore;

	@JsonProperty("notaInterna")
	private String notaInterna;
	
	private Utente utenteBeneficiario = null;

	public Utente getUtenteBeneficiario() {
		return utenteBeneficiario;
	}

	public StoricoEsenzione(EsenzioneTPraticaEsenzione esenzione, String cronologia) {
		// EsenzioneTPraticaEsenzione esenzione = (EsenzioneTPraticaEsenzione) esenzioneGeneric;
		this.id = esenzione.getSkPraticaEsenzione().toString();
		this.dataCreazione = esenzione.getDatModifica() != null
				? Converter.getStringDataFromTimeStamp(esenzione.getDatModifica(),
						Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
				: esenzione.getDatCreazione() != null
						? Converter.getStringDataFromTimeStamp(esenzione.getDatCreazione(),
								Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
						: null;

		this.stato = new Stato();
		this.stato.setCodice(esenzione.getEsenzioneDPraticaStato().getCodStato());
		this.stato.setDescrizione(esenzione.getEsenzioneDPraticaStato().getDescStato());

		this.utente = new Utente();

		if (esenzione.getCodiceFiscaleOperatore() != null && !esenzione.getCodiceFiscaleOperatore().isEmpty()) {
			this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleOperatore());
		} else if (esenzione.getCodiceFiscaleDelegato() != null && !esenzione.getCodiceFiscaleDelegato().isEmpty()) {
			this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleDelegato());
			// this.utente.setCognome(esenzione.getEsenzioneTCittadino2().getCognome());
		} else {
			this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleBeneficiario());
			// this.utente.setCognome(esenzione.getEsenzioneTCittadino1().getCognome());
		}

		// dati beneficiario
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.utenteBeneficiario = new Utente();
		this.utenteBeneficiario.setNome(esenzione.getEsenzioneTCittadino1().getNome());
		this.utenteBeneficiario.setCognome(esenzione.getEsenzioneTCittadino1().getCognome());
		this.utenteBeneficiario.setComuneNascita(esenzione.getEsenzioneTCittadino1().getComuneDiNascita());
		this.utenteBeneficiario.setDataNascita(sdf.format(esenzione.getEsenzioneTCittadino1().getDataDiNascita()));
		if (esenzione.getEsenzioneTCittadino1().getId_aura() != null
				&& !esenzione.getEsenzioneTCittadino1().getId_aura().trim().equals("")) {
			this.utenteBeneficiario.setIdAura(new Integer(esenzione.getEsenzioneTCittadino1().getId_aura()));
		}
		this.utenteBeneficiario.setSesso(esenzione.getEsenzioneTCittadino1().getSesso());

		if (esenzione.getDescNotaInterna() != null && !esenzione.getDescNotaInterna().equals("")) {
			this.utente.setNome("OPERATORE");

		} else if (esenzione.getDescNotaOperatore() != null && !esenzione.getDescNotaOperatore().equals("")) {
			this.utente.setNome("OPERATORE");

		}
		if (esenzione.getCodiceFiscaleOperatore() != null && !esenzione.getCodiceFiscaleOperatore().isEmpty()) {
			this.utente.setNome("OPERATORE");

		} else {
			this.utente = this.utenteBeneficiario;
		}

		this.nota = esenzione.getDescNota();
		this.notaOperatore = esenzione.getDescNotaOperatore();
		this.notaBeneficiario = esenzione.getDescNotaBeneficiario();
		this.notaInterna = esenzione.getDescNotaInterna();
	}
	
	public StoricoEsenzione(EsenzioneSPraticaEsenzione esenzione, Utente utenteBeneficiario) {
		// EsenzioneSPraticaEsenzione esenzione = (EsenzioneSPraticaEsenzione) esenzioneGeneric;
		this.id = esenzione.getSkIdEsenzione().toString();
		this.dataCreazione = esenzione.getDatModifica() != null
				? Converter.getStringDataFromTimeStamp(esenzione.getDatModifica(),
						Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
				: esenzione.getDatCreazione() != null
						? Converter.getStringDataFromTimeStamp(esenzione.getDatCreazione(),
								Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
						: null;

		this.stato = new Stato();
		this.stato.setCodice(esenzione.getEsenzioneDPraticaStato().getCodStato());
		this.stato.setDescrizione(esenzione.getEsenzioneDPraticaStato().getDescStato());

		this.utente = new Utente();
		if (esenzione.getCodFiscaleOperatore() != null && !esenzione.getCodFiscaleOperatore().isEmpty()) {
			this.utente.setCodiceFiscale(esenzione.getCodFiscaleOperatore());
		} else if (esenzione.getCodFiscaleCittadinoDelegato() != null && !esenzione.getCodFiscaleCittadinoDelegato().isEmpty()) {
			this.utente.setCodiceFiscale(esenzione.getCodFiscaleCittadinoDelegato());
		} else {
			this.utente.setCodiceFiscale(esenzione.getCodFiscaleCittadinoBeneficiario());
		}

		if (esenzione.getDescNotaInterna() != null && !esenzione.getDescNotaInterna().equals("")) {
			this.utente.setNome("OPERATORE");
		} else if (esenzione.getDescNotaOperatore() != null && !esenzione.getDescNotaOperatore().equals("")) {
			this.utente.setNome("OPERATORE");
		}
		if (esenzione.getCodFiscaleOperatore() != null && !esenzione.getCodFiscaleOperatore().isEmpty()) {
			this.utente.setNome("OPERATORE");

		} else {
			this.utente = utenteBeneficiario;
		}

		this.nota = esenzione.getDescNota();
		this.notaOperatore = esenzione.getDescNotaOperatore();
		this.notaBeneficiario = esenzione.getDescNotaBeneficiario();
		this.notaInterna = esenzione.getDescNotaInterna();
	}

	public <T> StoricoEsenzione(T esenzioneGeneric) {

	    if(esenzioneGeneric instanceof EsenzioneTPraticaEsenzione) {
	      EsenzioneTPraticaEsenzione esenzione = (EsenzioneTPraticaEsenzione) esenzioneGeneric; 
	      this.id = esenzione.getSkPraticaEsenzione().toString();
			this.dataCreazione = esenzione.getDatModifica() != null
					? Converter.getStringDataFromTimeStamp(esenzione.getDatModifica(),
							Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
					: esenzione.getDatCreazione() != null
							? Converter.getStringDataFromTimeStamp(esenzione.getDatCreazione(),
									Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
							: null;
	      
	      this.stato = new Stato();
	      this.stato.setCodice(esenzione.getEsenzioneDPraticaStato().getCodStato());
	      this.stato.setDescrizione(esenzione.getEsenzioneDPraticaStato().getDescStato());

	      this.utente = new Utente();
	      if (esenzione.getCodiceFiscaleOperatore() != null && !esenzione.getCodiceFiscaleOperatore().isEmpty()) {
	        this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleOperatore());
	      } else if (esenzione.getCodiceFiscaleDelegato() != null && !esenzione.getCodiceFiscaleDelegato().isEmpty()) {
	        this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleDelegato());
	      } else {
	        this.utente.setCodiceFiscale(esenzione.getCodiceFiscaleBeneficiario());
	      }

	      if (esenzione.getDescNotaBeneficiario() != null && !esenzione.getDescNotaBeneficiario().isEmpty()) {
	        this.nota = esenzione.getDescNotaBeneficiario();
	      } else if (esenzione.getDescNotaOperatore() != null && !esenzione.getDescNotaOperatore().isEmpty()) {
	        this.nota = esenzione.getDescNotaOperatore();
	      } else {
	        this.nota = null;
	      }
	    } 
	    else
	    {
	      EsenzioneSPraticaEsenzione esenzione = (EsenzioneSPraticaEsenzione) esenzioneGeneric;  
	      this.id = esenzione.getSkIdEsenzione().toString();
			this.dataCreazione = esenzione.getDatModifica() != null
					? Converter.getStringDataFromTimeStamp(esenzione.getDatModifica(),
							Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
					: esenzione.getDatCreazione() != null
							? Converter.getStringDataFromTimeStamp(esenzione.getDatCreazione(),
									Constants.DATE_FORMAT_ITALIAN_WITH_TIME)
							: null;
	      this.stato = new Stato();
	      this.stato.setCodice(esenzione.getEsenzioneDPraticaStato().getCodStato());
	      this.stato.setDescrizione(esenzione.getEsenzioneDPraticaStato().getDescStato());

	      this.utente = new Utente();
	      if (esenzione.getCodFiscaleOperatore() != null && !esenzione.getCodFiscaleOperatore().isEmpty()) {
	        this.utente.setCodiceFiscale(esenzione.getCodFiscaleOperatore());
	      } else if (esenzione.getCodFiscaleCittadinoDelegato() != null && !esenzione.getCodFiscaleCittadinoDelegato().isEmpty()) {
	        this.utente.setCodiceFiscale(esenzione.getCodFiscaleCittadinoDelegato());
	      } else {
	        this.utente.setCodiceFiscale(esenzione.getCodFiscaleCittadinoBeneficiario());
	      }

	      this.nota = esenzione.getDescNota();
	      if (esenzione.getDescNotaOperatore() != null && !esenzione.getDescNotaOperatore().isEmpty()) {
	        this.nota = esenzione.getDescNotaOperatore();
	      } else if (esenzione.getDescNotaBeneficiario() != null && !esenzione.getDescNotaBeneficiario().isEmpty()) {
	        this.nota = esenzione.getDescNotaBeneficiario();
	      } else {
	        this.nota = null;
	      }
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

	@JsonProperty("data_creazione")
	public String getDataCreazione() {
		return dataCreazione;
	}

	@JsonProperty("data_creazione")
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	@JsonProperty("stato")
	public Stato getStato() {
		return stato;
	}

	@JsonProperty("stato")
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	@JsonProperty("utente")
	public Utente getUtente() {
		return utente;
	}

	@JsonProperty("utente")
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@JsonProperty("nota")
	public String getNota() {
		return nota;
	}

	@JsonProperty("nota")
	public void setNota(String nota) {
		this.nota = nota;
	}

	@Override
	public int compareTo(StoricoEsenzione o) {
		Date data1 = null;
		Date data2 = null;
		int res = 0;
		try {
			data1 = new SimpleDateFormat("DD/MM/YY HH:mm").parse(this.dataCreazione);
			data2 = new SimpleDateFormat("DD/MM/YY HH:mm").parse(o.dataCreazione);
			res = -(data1.compareTo(data2));
		} catch (ParseException e) {
			return 0;
		}
		return res;
	}

}
