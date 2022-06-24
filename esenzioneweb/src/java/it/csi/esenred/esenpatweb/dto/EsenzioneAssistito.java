/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import java.util.Calendar;

import org.codehaus.jackson.annotate.*;
import org.jboss.resteasy.util.DateUtil;

import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

@JsonPropertyOrder({ "numero_pratica", "asl", "beneficiario", "compilatore", "codice_esenzione", "stato_pratica",
		"data_richiesta", "valida_dal", "data_scadenza","data_sospensione","sk_pratica_esenzione",
		"diagnosi", "cod_diagnosi", "desc_gruppo", "desc_esenzione", "numeroTotaleElementi", "asl_denominazione",
		"distretto", "visualizza" })
public class EsenzioneAssistito implements Comparable<EsenzioneAssistito> {
	@JsonProperty("numero_pratica")
	private String numeroPratica;

	@JsonProperty("sk_pratica_esenzione")
	private String skpraticaesenzione;	
	
	@JsonProperty("diagnosi")
	private String diagnosi;
	
	@JsonProperty("cod_diagnosi")
	private String coddiagnosi;
	
	@JsonProperty("asl")
	private String asl;

	@JsonProperty("beneficiario")
	private Beneficiario beneficiario;

	@JsonProperty("compilatore")
	private String compilatore;

	@JsonProperty("codice_esenzione")
	private String codiceEsenzione;

	@JsonProperty("stato_pratica")
	private String statoPratica;

	@JsonProperty("data_richiesta")
	private String dataRichiesta;

	@JsonProperty("valida_dal")
	private String validaDal;

	@JsonProperty("data_scadenza")
	private String dataScadenza;
	
	@JsonProperty("data_sospensione")
	private String dataSospensione;

    @JsonProperty("desc_gruppo")
	private String descGruppo;
    
	@JsonProperty("desc_esenzione")
	private String descEsenzione;
	 
	@JsonProperty("numeroTotaleElementi")
	private int numeroTotaleElementi;

	@JsonProperty("asl_denominazione")
	private String aslDenominazione;

	@JsonProperty("distretto")
	private String distretto;

	@JsonProperty("visualizza")
	private boolean visualizza;


	public EsenzioneAssistito(){
			
	}
	
	public EsenzioneAssistito(EsenzioneAssistito ese){
	this.numeroPratica = ese.getNumeroPratica();
	this.skpraticaesenzione = ese.getSkpraticaesenzione();
	this.asl = ese.getAsl();
	Beneficiario ben = new Beneficiario();
	ben.setCodiceFiscale(ese.beneficiario.getCodiceFiscale());
	ben.setCognome(ese.beneficiario.getCognome());
	ben.setIdAura(ese.beneficiario.getIdAura());
	ben.setNome(ese.beneficiario.getNome());
	this.beneficiario = ben;
	this.compilatore = ese.getCompilatore();
	this.codiceEsenzione = ese.getCodiceEsenzione();
	this.descEsenzione= ese.getDescEsenzione();
	this.coddiagnosi = ese.getCoddiagnosi();
	this.diagnosi = ese.getDiagnosi();
	this.descGruppo = ese.getDescGruppo();
	this.statoPratica = ese.getStatoPratica();
	this.dataRichiesta = ese.getDataRichiesta();
	this.validaDal = ese.getValidaDal();
	this.dataScadenza = ese.getDataScadenza();
	this.numeroTotaleElementi = ese.getNumeroTotaleElementi(); 
	this.dataSospensione = ese.getDataSospensione();
	}
	
	// Esenzioni ESENPAT
	public EsenzioneAssistito(EsenzioneTPraticaEsenzione ese, Cittadino cit, UserInfo utente, String cfMed) {
		this.numeroPratica = ese.getNumPratica().toString();
		this.skpraticaesenzione = ese.getSkPraticaEsenzione().toString();
		this.asl = ese.getIdAzienda();
		this.beneficiario = new Beneficiario(cit);
		this.compilatore = "";

		if (ese.getEsenzioneDDiagnosi() != null) {
			this.codiceEsenzione = ese.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione();
			this.descEsenzione= ese.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getDescEsenzione();
			this.coddiagnosi = ese.getEsenzioneDDiagnosi().getCodDiagnosi();
			this.diagnosi = ese.getEsenzioneDDiagnosi().getDescDiagnosi();
			this.descGruppo = ese.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getEsenzioneDGruppoEsenzioni().getDescGruppo();
		}

		if (ese.getEsenzioneDPraticaStato() != null) {
			this.statoPratica = ese.getEsenzioneDPraticaStato().getDescStato();
		}

		if (ese.getDatCreazione() != null) {
			this.dataRichiesta = Converter.getStringDataFromTimeStamp(ese.getDatCreazione(),
					Constants.DATE_FORMAT_ITALIAN);
		}

		if (ese.getDatInizioValidita() != null) {
			this.validaDal = Converter.getStringDataFromTimeStamp(ese.getDatInizioValidita(),
					Constants.DATE_FORMAT_ITALIAN);
		}

		if (ese.getDatFineValidita() != null) {
			this.dataScadenza = Converter.getStringDataFromTimeStamp(ese.getDatFineValidita(),
					Constants.DATE_FORMAT_ITALIAN);
		}

		if (ese.getEsenredDAziendasanitaria() != null) {
			this.aslDenominazione = ese.getEsenredDAziendasanitaria().getDenominazione();
		}

		if (ese.getEsenzioneDDistrettoSocioSanitario() != null) {
			this.distretto = ese.getEsenzioneDDistrettoSocioSanitario().getNumDistretto().toString();
		}
		
		this.visualizza = cfMed == null || cfMed.equalsIgnoreCase(ese.getCodiceFiscaleOperatore());
	}
	// Esenzioni ESENPAT Ricerca Esenzione
		public EsenzioneAssistito(EsenzioneTPraticaEsenzione ese) {
			this.numeroPratica = ese.getNumPratica().toString();
			this.skpraticaesenzione = ese.getSkPraticaEsenzione().toString();
			this.asl = ese.getIdAzienda();
			Beneficiario bencittadino = new Beneficiario();
			bencittadino.setCodiceFiscale(ese.getEsenzioneTCittadino1().getCodiceFiscale());
			bencittadino.setCognome(ese.getEsenzioneTCittadino1().getCognome());
			bencittadino.setNome(ese.getEsenzioneTCittadino1().getNome());
			bencittadino.setIdAura(ese.getEsenzioneTCittadino1().getId_aura());
			this.beneficiario = bencittadino;
			this.compilatore = "";
			if (ese.getEsenzioneDDiagnosi() != null) {
				this.codiceEsenzione = ese.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione();
				this.descEsenzione= ese.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getDescEsenzione();
				this.coddiagnosi = ese.getEsenzioneDDiagnosi().getCodDiagnosi();
				this.diagnosi = ese.getEsenzioneDDiagnosi().getDescDiagnosi();
				this.descGruppo = ese.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getEsenzioneDGruppoEsenzioni().getDescGruppo();
			}

			if (ese.getEsenzioneDPraticaStato() != null) {
				this.statoPratica = ese.getEsenzioneDPraticaStato().getDescStato();
			}

			if (ese.getDatCreazione() != null) {
			this.dataRichiesta = Converter.getStringDataFromTimeStamp(ese.getDatCreazione(),
					Constants.DATE_FORMAT_ITALIAN);
			}

			if (ese.getDatInizioValidita() != null) {
			this.validaDal = Converter.getStringDataFromTimeStamp(ese.getDatInizioValidita(),
					Constants.DATE_FORMAT_ITALIAN);
			}

			if (ese.getDatFineValidita() != null) {
			this.dataScadenza = Converter.getStringDataFromTimeStamp(ese.getDatFineValidita(),
					Constants.DATE_FORMAT_ITALIAN);
			}
			
			if (ese.getEsenredDAziendasanitaria() != null) {
				this.aslDenominazione = ese.getEsenredDAziendasanitaria().getDenominazione();
			}

			if (ese.getEsenzioneDDistrettoSocioSanitario() != null) {
				this.distretto = ese.getEsenzioneDDistrettoSocioSanitario().getNumDistretto().toString();
			}
		}
	// Esenzioni AURA
	public EsenzioneAssistito(InfoEsenzioneNew ese, Cittadino cit, UserInfo utente) {
		this.numeroPratica = null;
		this.asl = cit.getCodASL();
		this.beneficiario = new Beneficiario(cit);
		this.compilatore = "";
		this.codiceEsenzione = ese.getCodEsenzione();
		if (ese.getDescEsenzione()!=null)
		this.descEsenzione = ese.getDescEsenzione().getDescEsenzione_type0();
		else
		this.descEsenzione = null; 
		this.statoPratica = "Validata";
		this.dataRichiesta = null;
		this.coddiagnosi = ese.getCodDiagnosi().getCodDiagnosi_type0();
		this.diagnosi = ese.getDiagnosi();
		this.descGruppo = null;

		if (ese.getDataEmissione() != null) {
			this.validaDal = DateUtil.formatDate(ese.getDataEmissione().getTime(),
					Constants.DATE_FORMAT_ITALIAN);
		}

		if (ese.getDataScadenza() != null) {
			if (ese.getDataScadenza().before(Calendar.getInstance())) {
				this.statoPratica = "Scaduta";
			}
			this.dataScadenza = DateUtil.formatDate(ese.getDataScadenza().getTime(),
					Constants.DATE_FORMAT_ITALIAN);
		}
		
		if (ese.getDataSospensione() != null) {
			if (ese.getDataSospensione().before(Calendar.getInstance())) {
				this.statoPratica = "Scaduta";
			}
			this.dataSospensione = DateUtil.formatDate(ese.getDataSospensione().getTime(),
					Constants.DATE_FORMAT_ITALIAN);
		}
		
	}

	public String getNumeroPratica() {
		return numeroPratica;
	}

	public void setNumeroPratica(String numeroPratica) {
		this.numeroPratica = numeroPratica;
	}

	public String getAsl() {
		return asl;
	}

	public void setAsl(String asl) {
		this.asl = asl;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getCompilatore() {
		return compilatore;
	}

	public void setCompilatore(String compilatore) {
		this.compilatore = compilatore;
	}

	public String getCodiceEsenzione() {
		return codiceEsenzione;
	}

	public void setCodiceEsenzione(String codiceEsenzione) {
		this.codiceEsenzione = codiceEsenzione;
	}

	public String getStatoPratica() {
		return statoPratica;
	}

	public void setStatoPratica(String statoPratica) {
		this.statoPratica = statoPratica;
	}

	public String getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(String dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public String getValidaDal() {
		return validaDal;
	}

	public void setValidaDal(String validaDal) {
		this.validaDal = validaDal;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDataSospensione() {
		return dataSospensione;
	}

	public void setDataSospensione(String dataSospensione) {
		this.dataSospensione = dataSospensione;
	}

	public String getSkpraticaesenzione() {
		return skpraticaesenzione;
	}

	public void setSkpraticaesenzione(String skpraticaesenzione) {
		this.skpraticaesenzione = skpraticaesenzione;
	}

	public String getDiagnosi() {
		return diagnosi;
	}

	public void setDiagnosi(String diagnosi) {
		this.diagnosi = diagnosi;
	}

	public String getCoddiagnosi() {
		return coddiagnosi;
	}

	public void setCoddiagnosi(String coddiagnosi) {
		this.coddiagnosi = coddiagnosi;
	}

	public String getDescGruppo() {
		return descGruppo;
	}

	public void setDescGruppo(String descGruppo) {
		this.descGruppo = descGruppo;
	}

	public String getDescEsenzione() {
		return descEsenzione;
	}

	public void setDescEsenzione(String descEsenzione) {
		this.descEsenzione = descEsenzione;
	}

	public int getNumeroTotaleElementi() {
		return numeroTotaleElementi;
	}

	public void setNumeroTotaleElementi(int numeroTotaleElementi) {
		this.numeroTotaleElementi = numeroTotaleElementi;
	}

	public String getAslDenominazione() {
		return aslDenominazione;
	}

	public void setAslDenominazione(String aslDenominazione) {
		this.aslDenominazione = aslDenominazione;
	}

	public String getDistretto() {
		return distretto;
	}

	public void setDistretto(String distretto) {
		this.distretto = distretto;
	}
	
	public boolean isVisualizza() {
		return visualizza;
	}

	public void setVisualizza(boolean visualizza) {
		this.visualizza = visualizza;
	}

	@Override
	public int compareTo(EsenzioneAssistito o) {
		
		int res = ((this.beneficiario.getCognome().concat(" "+this.beneficiario.getNome().concat(" " + this.beneficiario.getCodiceFiscale()))).compareTo((o.getBeneficiario().getCognome().concat(" "+o.getBeneficiario().getNome().concat(" " + o.getBeneficiario().getCodiceFiscale())))));	
		try {
			if (res == 0) {
				if (this.validaDal == null) {
					if (o.getValidaDal() == null) {
						res = 0;
					} else {
						res = -1;
					}
				} else if (o.getValidaDal() == null) {
					res = -1;
				} else {
					res = -Converter.getData(this.validaDal).compareTo(Converter.getData(o.getValidaDal()));
				}
			}
			if (res == 0)
				res = -this.getCodiceEsenzione().compareTo(o.getCodiceEsenzione());
			if (res == 0)
				if (this.skpraticaesenzione != null) {
					if (o.getSkpraticaesenzione() != null) {
						res = -this.getSkpraticaesenzione().compareTo(o.getSkpraticaesenzione());
					} else {
						res = 0;
					}
				} else if (o.getSkpraticaesenzione() != null) {
					res = 0;
				}
		} catch (Exception e){
			res = -1;
		}
		
		return res;
//		  if (this.equals(o))
//		      return 0;
//		    return 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asl == null) ? 0 : asl.hashCode());
		result = prime * result + ((beneficiario == null) ? 0 : beneficiario.hashCode());
		result = prime * result + ((coddiagnosi == null) ? 0 : coddiagnosi.hashCode());
		result = prime * result + ((codiceEsenzione == null) ? 0 : codiceEsenzione.hashCode());
		result = prime * result + ((compilatore == null) ? 0 : compilatore.hashCode());
		result = prime * result + ((dataRichiesta == null) ? 0 : dataRichiesta.hashCode());
		result = prime * result + ((dataScadenza == null) ? 0 : dataScadenza.hashCode());
		result = prime * result + ((dataSospensione == null) ? 0 : dataSospensione.hashCode());
		result = prime * result + ((descEsenzione == null) ? 0 : descEsenzione.hashCode());
		result = prime * result + ((descGruppo == null) ? 0 : descGruppo.hashCode());
		result = prime * result + ((diagnosi == null) ? 0 : diagnosi.hashCode());
		result = prime * result + ((numeroPratica == null) ? 0 : numeroPratica.hashCode());
		result = prime * result + numeroTotaleElementi;
		result = prime * result + ((skpraticaesenzione == null) ? 0 : skpraticaesenzione.hashCode());
		result = prime * result + ((statoPratica == null) ? 0 : statoPratica.hashCode());
		result = prime * result + ((validaDal == null) ? 0 : validaDal.hashCode());
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
		EsenzioneAssistito other = (EsenzioneAssistito) obj;
	if (beneficiario == null) {
			if (other.beneficiario != null)
				return false;
		} else if (!beneficiario.equals(other.beneficiario))
			return false;
		if (codiceEsenzione == null) {
			if (other.codiceEsenzione != null)
				return false;
		} else if (!codiceEsenzione.equals(other.codiceEsenzione))
			return false;
	if (validaDal == null) {
			if (other.validaDal != null)
				return false;
		} else if (!validaDal.equals(other.validaDal))
			return false;
		return true;
	}
}
