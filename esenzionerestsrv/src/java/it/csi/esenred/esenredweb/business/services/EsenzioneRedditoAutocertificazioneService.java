/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.RequestBody;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoAutocertificazioneBO;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoFamiliareBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.enums.TitoloDichiarante;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class EsenzioneRedditoAutocertificazioneService extends AbstractEsenzioneRedditoService {
	private static String isTst;
	public EsenzioneRedditoAutocertificazioneService() {
		super();
	}

	public ResponseAura insert(EsenzioneRedditoAutocertificazioneBO esenzioneRedditoAutocertificazioneBO)
			throws EsenzioneInvalidaException, Exception {
		
		System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] Start ");

		if (esenzioneRedditoAutocertificazioneBO == null) {
			EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_017);
			String messaggio = datiMancanti.getTesto();

			throw new EsenzioneInvalidaException(MSG_017, messaggio);
		}

		try {
			
			this.checkFormalConstraints(esenzioneRedditoAutocertificazioneBO);
			System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] checkFormalConstraints OK ");
			//accedo diretto in AURA perche' sono sicuro che il cittadino esiste (altrimenti non sarebbe arrivato neanche al login)
			Cittadino titolareAURA = null;
			Cittadino cittadinoAURA = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(esenzioneRedditoAutocertificazioneBO.getCodFiscale())).get(0);
			
			System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] Letto cittadino in aura OK ");
			
			//prelevo il titolare se e selezionato sititolare
			if (esenzioneRedditoAutocertificazioneBO.getCodFiscale().equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getCodFiscaleTitolare()))
			{
				System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] CF cittadino = cf Titolare  ");
				titolareAURA=cittadinoAURA;
			} else
			{
				titolareAURA = this.checkExistCittadino(esenzioneRedditoAutocertificazioneBO.getCodFiscaleTitolare(), TITOLARE);
				
				System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] CF cittadino != cf Titolare  ");

			if (!esenzioneRedditoAutocertificazioneBO.getCodFiscale().equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getCodFiscaleTitolare()))
			{
				this.checkDomainConstraints(esenzioneRedditoAutocertificazioneBO, titolareAURA);
				System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] ATTENZIONE! La richiesta di esenzione non puo' essere concessa:E' gia' presente una esenzione con stesso codice revocata da meno di 2 giorni.");
			}	
			
			List<EsenredTEsenzioniReddito> elencoEsenzioniSbloccate = esenzioneCittadinoIf.getEsenzioniControlloInserimento(new Long(cittadinoAURA.getIdAura()), esenzioneRedditoAutocertificazioneBO.getCodEsenzione());
			if (elencoEsenzioniSbloccate != null && !elencoEsenzioniSbloccate.isEmpty()) {
				//va dato messaggio simile a quello del mef per il evitare l'inserimento dell'esenzione
				EsenredCMessaggi esenzionenoninseribile = this.messaggioIf.getMessaggio(MSG_033);
				String messaggio = esenzionenoninseribile.getTesto();
				
				
				throw new EsenzioneInvalidaException(MSG_033, messaggio);
			}
			else {
			//fine controllo blocco
			this.checkDomainEsenzioneConstraints(esenzioneRedditoAutocertificazioneBO.getCodEsenzione(), new Long(cittadinoAURA.getIdAura()),null, null);
			
			this.checkEsenzioneUgualeInLavorazione(esenzioneRedditoAutocertificazioneBO.getCodEsenzione(), new Long(cittadinoAURA.getIdAura()));

			esenzioneRedditoAutocertificazioneBO.setDataScadenza(getDataScadenza(esenzioneRedditoAutocertificazioneBO.getDataInizioValidita(),esenzioneRedditoAutocertificazioneBO.getCodEsenzione()));
			
			String codASL = IntegrationClientImpl.getInstance().getCittadino(cittadinoAURA.getIdAura()).getCodASL();
			
			this.composeAndcheckDatiObbligatoriResponseAura(codASL, cittadinoAURA.getIdAura(),titolareAURA.getIdAura());
			
			System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] Supero i controlli asl, titolare e cittadino.");

			EsenredTEsenzioniReddito esenredTEsenzioniReddito = new EsenredTEsenzioniReddito();

			System.out.println("[EsenzioneRedditoAutocertificazioneService::insert] Preparo esenredTEsenzioniReddito per l'inserimento");
			esenredTEsenzioniReddito.setDataInsert(new Date());
			esenredTEsenzioniReddito.setCodStato(StatoEsenzione.IN_LAVORAZIONE.getCodice());
			esenredTEsenzioniReddito.setCodTitoloDichiarante(TitoloDichiarante.INTERESSATO.getCodice());
			esenredTEsenzioniReddito.setDataInizio(Converter.getData(esenzioneRedditoAutocertificazioneBO.getDataInizioValidita()));
			esenredTEsenzioniReddito.setDataFine(Converter.getData(esenzioneRedditoAutocertificazioneBO.getDataScadenza()));
			esenredTEsenzioniReddito.setDataRichiesta(new Date());
			esenredTEsenzioniReddito.setFlagConsenso(esenzioneRedditoAutocertificazioneBO.getAccettaInformativa() ? 1 : 0);
			esenredTEsenzioniReddito.setIdUserInsert(new Long(cittadinoAURA.getIdAura()));
			esenredTEsenzioniReddito.setIdCittadinoBeneficiario(Long.valueOf(cittadinoAURA.getIdAura()));
			esenredTEsenzioniReddito.setIdCittadinoDichiarante(Long.valueOf(cittadinoAURA.getIdAura()));
			esenredTEsenzioniReddito.setCodEsenzione(esenzioneRedditoAutocertificazioneBO.getCodEsenzione());
			esenredTEsenzioniReddito.setCodNazionaleAslRilascio(codASL);
			esenredTEsenzioniReddito.setIdCittadinoTitolare(Long.valueOf(titolareAURA.getIdAura()));

			String note = esenredTEsenzioniReddito.getCodEsenzione()+" "+this.esenzioneIf.getEsenzioni(esenredTEsenzioniReddito.getCodEsenzione()).get(0).getDescEsenzione();
			if (note.length()>50) note = note.substring(0, 49);
			RequestBody request = null;
			request = new RequestBody(
					esenzioneRedditoAutocertificazioneBO.getCodFiscale(), 
					esenzioneRedditoAutocertificazioneBO.getCodFiscale(), 
					esenzioneRedditoAutocertificazioneBO.getCodFiscale(),
					esenzioneRedditoAutocertificazioneBO.getCodFiscaleTitolare(),
					esenzioneRedditoAutocertificazioneBO.getCodEsenzione(), 
	        		note, 
	        		esenredTEsenzioniReddito.getCodTitoloDichiarante(), 
	        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataInizio()),
	        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataFine()));

			Response responseAura = IntegrationClientImpl.getInstance().insertEsenzione(request);//chiamo AURA

			if (responseAura == null) {
				throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
			}
			
			String codice = responseAura.getBody().getEsito();
			String messaggio = null;
			if ("OK".equalsIgnoreCase(codice)) {
				esenredTEsenzioniReddito.setCodStato(StatoEsenzione.VALIDA.getCodice());//Valida
				esenredTEsenzioniReddito.setNumProtocolloSogei(new Long(responseAura.getBody().getNumProtMef()));
				messaggio = this.messaggioIf.getMessaggio(MSG_001).getTesto();//#N_PROTOCOLLO - #DATA_SCADENZA - #DESC_BREVE_ESENZIONE
				
				messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
				messaggio = messaggio.replaceAll("#DATA_SCADENZA", esenzioneRedditoAutocertificazioneBO.getDataScadenza());
				String descBreve = this.esenzioneIf.getEsenzioni(esenzioneRedditoAutocertificazioneBO.getCodEsenzione()).get(0).getDescEsenzione();
				messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
			} else { //KO
				if ("0003".equals(responseAura.getBody().getCodice())) {//0003 - KO - MEF NON DISPONIBILE
					messaggio = this.messaggioIf.getMessaggio(MSG_002).getTesto();//ATTENZIONE! La richiesta di esenzione e' stata presa in carico ed e' in fase di verifica
					
					messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
					messaggio = messaggio.replaceAll("#DATA_SCADENZA", esenzioneRedditoAutocertificazioneBO.getDataScadenza());
					String descBreve = this.esenzioneIf.getEsenzioni(esenzioneRedditoAutocertificazioneBO.getCodEsenzione()).get(0).getDescEsenzione();
					messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
				} else {
					esenredTEsenzioniReddito.setCodStato(StatoEsenzione.NON_VALIDA.getCodice());//Non valida
					messaggio = this.messaggioIf.getMessaggio(MSG_003).getTesto();//ATTENZIONE! La richiesta di esenzione non puo' essere concessa: #R!
					messaggio = messaggio.replaceAll("#R", responseAura.getBody().getDescrizione());
				}
			}
			//se lo stato e non valida non inserisco nulla
			if (!esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase(StatoEsenzione.NON_VALIDA.getCodice())) {
			esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione
			//ho spostato la data il set della data di modifica nell'insert
			}
			else
			{

				List<EsenredTEsenzioniReddito> esenzioniNonValide = esenzioneCittadinoIf.getEsenzioniNonValide(esenredTEsenzioniReddito);
				if (esenzioniNonValide.isEmpty()) {
					esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione	
				}
			}

			
			ResponseAura r = new ResponseAura();
			r.setIdEsenzione(esenredTEsenzioniReddito.getIdEsenzione());
			r.setCodAura(responseAura.getBody().getCodice());
			r.setMessaggio(messaggio);
			return r;
		}
			} catch (EsenzioneInvalidaException e) {
			throw e;
		}
	}

	private void composeAndcheckDatiObbligatoriResponseAura(String codASL, String idAura, String idAuraTitolare) 
			throws EsenzioneInvalidaException {
		HashMap<String, String> listCampiObbligatori = new HashMap<String, String>();
		listCampiObbligatori.put("codice ASL", codASL);
		listCampiObbligatori.put("identificativo cittadino", idAura);
		listCampiObbligatori.put("identificativo titolare", idAuraTitolare);
		this.checkDatiObbligatoriResponseAura(listCampiObbligatori);
	}

	private void checkDomainConstraints(EsenzioneRedditoAutocertificazioneBO esenzioneRedditoAutocertificazioneBO, Cittadino cittadino) throws Exception {
        EsenredCMessaggi nonCoincideAura = this.messaggioIf.getMessaggio(MSG_020);
        String cognome = cittadino.getCognome();
        String nome = cittadino.getNome();
        String sesso = cittadino.getCodSesso();
        String dataNascita = cittadino.getDataNascita();

        if (!cognome.equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getCognomeTitolare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "cognome titolare"));

        if (!nome.equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getNomeTitolare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "nome titolare"));
        
        if (!sesso.equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getSessoTitolare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "sesso titolare"));

        if (!dataNascita.equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getDataNascitaTitolare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "data di nascita titolare"));
        
        if (Checker.isValorizzato(esenzioneRedditoAutocertificazioneBO.getComuneNascitaTitolare())) {
        	String codComuneNascitaAura = cittadino.getCodComuneNascita();
            String codComuneNascitaEsenred = null;
            boolean erroreComune = false; 
            
        	ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
    		List<EsenredCComuni> elencoComuni = comuneIf.getElencoComuni(esenzioneRedditoAutocertificazioneBO.getComuneNascitaTitolare());
    		if (elencoComuni == null || elencoComuni.isEmpty())
    			erroreComune = true;
    		else {
    			for (EsenredCComuni comune : elencoComuni) {
    				if (comune.getDenominazione().equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getComuneNascitaTitolare())) {
    					codComuneNascitaEsenred = comune.getCodIstat();
    					break;
    				}
    			}
    			
    			
    			erroreComune = !codComuneNascitaAura.equals(codComuneNascitaEsenred);
    		}
    		if (erroreComune)
            	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "comune di nascita titolare"));
        }
    }
	
	
	private void checkFormalConstraints(EsenzioneRedditoAutocertificazioneBO esenzioneRedditoAutocertificazioneBO)
			throws EsenzioneInvalidaException {
		String codiceEsenzione = esenzioneRedditoAutocertificazioneBO.getCodEsenzione();
		
		
		if (codiceEsenzione == null) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "codice esenzione"));
		}

		if (codiceEsenzione.isEmpty()) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "codice esenzione"));
		}

		List<EsenredDTipiEsenzioniReddito> esenzioni = this.esenzioneIf.getEsenzioni(codiceEsenzione);

		if (esenzioni.isEmpty()) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			String messaggioStr = messaggioDto.getTesto();
			throw new EsenzioneInvalidaException(MSG_014, messaggioStr);
		}
		
		String codiceFiscale = esenzioneRedditoAutocertificazioneBO.getCodFiscale();
		
		if (codiceFiscale == null) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "codice fiscale"));
		}

		if (codiceFiscale.isEmpty()) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "codice fiscale"));
		}
		//controllo codice fiscale mascherato esteso a esenred 1
		if(isTst==null) {
			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
			isTst= elencoParametri.get(0).getValore();		
		}
		boolean isCodiceFiscale = Checker.isCodiceFiscale(codiceFiscale,isTst);
		if (!isCodiceFiscale) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
			throw new EsenzioneInvalidaException(MSG_004, Util.composeMessage(messaggioDto, "codice fiscale non valido"));
		}
		
		//controlla il campo sititolare se false allora il cf del titolare deve essere diverso da quello del beneficiario
		//se uguale blocca
		String codiceFiscaleTitolare = esenzioneRedditoAutocertificazioneBO.getCodFiscaleTitolare();
		//verifica set si titolare e vuoto se si dai messaggio di errore per campo obbligatorio
		if (esenzioneRedditoAutocertificazioneBO.getSititolare().isEmpty()) {
			  EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
	           throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "scelta titolare dell'esenzione"));
		}
		if (esenzioneRedditoAutocertificazioneBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
			//controllo se codice fiscale titolare e pieno se si getsititolare
			if (!Checker.isValorizzato(codiceFiscaleTitolare)) {
  	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
  	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "codice fiscale titolare"));
  	        
  	        }
			if (esenzioneRedditoAutocertificazioneBO.getCodFiscale().equalsIgnoreCase(esenzioneRedditoAutocertificazioneBO.getCodFiscaleTitolare())) {
				EsenredCMessaggi cfsititolare = this.messaggioIf.getMessaggio(MSG_038);
				String messaggio = cfsititolare.getTesto();
				throw new EsenzioneInvalidaException(MSG_038, messaggio);
			}
			boolean isCodiceFiscaleTitolare = Checker.isCodiceFiscale(codiceFiscaleTitolare,isTst);
  			if (!isCodiceFiscaleTitolare) {
  				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
  				throw new EsenzioneInvalidaException(MSG_004, Util.composeMessage(messaggioDto, "codice fiscale titolare non valido"));
  			}
  			String cognometitolare = esenzioneRedditoAutocertificazioneBO.getCognomeTitolare();
  	        if (!Checker.isValorizzato(cognometitolare)) {
  	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
  	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "cognome titolare"));
  	        }
  	        
  	        String nometitolare = esenzioneRedditoAutocertificazioneBO.getNomeTitolare();
  	        if (!Checker.isValorizzato(nometitolare)) {
  	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
  	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "nome titolare"));
  	        }
  	        
  	        String sessotitolare = esenzioneRedditoAutocertificazioneBO.getSessoTitolare();
  	        if (!Checker.isValorizzato(sessotitolare)) {
  	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
  	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "sesso titolare"));
  	        }
  	        
  	      String dataNascitatitolare = esenzioneRedditoAutocertificazioneBO.getDataNascitaTitolare();
          if (!Checker.isValorizzato(dataNascitatitolare)) {
          	EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
              throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "data di nascita titolare"));
          } else {
          	if (!Checker.isData(dataNascitatitolare, "dd/MM/yyyy", Locale.ITALIAN)) {
      			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                  String dettaglio = Util.composeMessage(messaggioDto, " data di nascita titolare non valida");
                  throw new EsenzioneInvalidaException(MSG_004, dettaglio);
      		} else {
      			Date dtNascitaDatetitolare = Converter.getData(dataNascitatitolare);
      			if (dtNascitaDatetitolare.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
                      EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                      String dettaglio = Util.composeMessage(messaggioDto, " data di nascita titolare successiva a data odierna");
                      throw new EsenzioneInvalidaException(MSG_004, dettaglio);
                  }
      		}
      	}
			
		}		

		Date dataInizioValidita = Converter.getData(esenzioneRedditoAutocertificazioneBO.getDataInizioValidita());

		if (dataInizioValidita == null) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "data inizio validita'"));
		}

		if (dataInizioValidita.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "data inizio deve essere pari alla data odierna"));
		}

		Date dataScadenza = Converter.getData(esenzioneRedditoAutocertificazioneBO.getDataScadenza());

		if (dataScadenza == null) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, " data fine validita'"));
		}

		if (dataScadenza.before(dataInizioValidita)) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, " data scadenza deve essere successiva alla data odierna"));
		}

		Boolean accettaInformativa = esenzioneRedditoAutocertificazioneBO.getAccettaInformativa();
		if (accettaInformativa == null) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "accetta informativa"));
		}

		if (!accettaInformativa) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "accetta informativa"));
		}
	}
}
