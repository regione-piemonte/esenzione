/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.RequestBody;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoAutocertificazioneBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.enums.TitoloDichiarante;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.EsenzioneAcceleratoreEsenred;
import it.csi.esenred.esenpatweb.dto.EsenzioneCreate;
import it.csi.esenred.esenpatweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class CreateRedditoService extends AbstractEsenzioneRedditoService {

	private static final String MSG_008 = "MSG008";
	private static final String MSG_009 = "MSG009";
	private static final String MSG_010 = "MSG010";
	private static final String MSG_012 = "MSG012";
	TitoloDichiaranteIf titoloDichiaranteIf;
	
    public EsenzioneAcceleratoreEsenred update(RevocaEsenzioneBO revocaBO) throws CheckException {
 
    	List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos = esenzioneCittadinoIf.getEsenzioneById(revocaBO.getIdEsenzione());
    	 EsenredTEsenzioniReddito esenzioneRedditoDB = esenredTEsenzioniRedditos.get(0);
    	 Cittadino cittadinoAURABeneficiario = null;
    	 Cittadino cittadinoAURADichiarante = null;
    	 Cittadino cittadinoAURATitolare = null;
        if (esenzioneRedditoDB.getIdCittadinoBeneficiario().equals(esenzioneRedditoDB.getIdCittadinoDichiarante())) {
        	cittadinoAURABeneficiario=IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoBeneficiario().toString());
        	cittadinoAURADichiarante=cittadinoAURABeneficiario;
        }
        else {
        	cittadinoAURABeneficiario = IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoBeneficiario().toString());
        	cittadinoAURADichiarante = IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoDichiarante().toString());      		
        }
        if (esenzioneRedditoDB.getIdCittadinoTitolare()!=null) {
        	if (esenzioneRedditoDB.getIdCittadinoBeneficiario().equals(esenzioneRedditoDB.getIdCittadinoTitolare())) {
        		cittadinoAURATitolare=cittadinoAURABeneficiario;
        		}
        	else if (esenzioneRedditoDB.getIdCittadinoDichiarante().equals(esenzioneRedditoDB.getIdCittadinoTitolare())) {
        		cittadinoAURATitolare=cittadinoAURADichiarante;
        	}
        	else {
        			cittadinoAURATitolare=IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoTitolare().toString());	
        	}
        }
    	String codFiscale = cittadinoAURABeneficiario.getCodFiscale(); 
    	EsenzioneRedditoService service = new EsenzioneRedditoService(revocaBO);
    	it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody reqAura = service.createRequestAuraRevocaEsenzione(
    			codFiscale, 
    			codFiscale, 
    			esenzioneRedditoDB.getCodEsenzione(), 
    			esenzioneRedditoDB.getNumProtocolloSogei().toString());
    	it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Response resAura = IntegrationClientImpl.getInstance().revocaEsenzione(reqAura); //chiamo revocaAutocertEseRed di AURA
    	String messaggio = null;
    	if (resAura == null) {
    		throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
    	}
		String codice = resAura.getBody().getEsito();
		esenzioneRedditoDB.setDataModify(new Date());
		if ("OK".equalsIgnoreCase(codice)) {
			esenzioneRedditoDB.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
			esenzioneRedditoDB.setDescMotivoRevoca(revocaBO.getMotivoRevocaEsenzione());
			esenzioneRedditoDB.setNota(revocaBO.getNotaInternaRevoca());
	    	esenzioneRedditoDB.setDataRevoca(new Date());
			messaggio = this.messaggioIf.getMessaggio(MSG_008).getTesto();//'La richiesta di revoca e' stata compilata correttamente! Numero Protocollo #N_PROTOCOLLO.'
			messaggio = messaggio.replaceAll("#N_PROTOCOLLO", resAura.getBody().getCodice());
		} else { //KO
			if ("0003".equals(resAura.getBody().getCodice())) {//0003 - KO - MEF NON DISPONIBILE
				messaggio = this.messaggioIf.getMessaggio(MSG_009).getTesto();//'ATTENZIONE! La richiesta di revoca e' stata presa in carico ed e' in fase di verifica.
			} else {
				messaggio = this.messaggioIf.getMessaggio(MSG_010).getTesto();//ATTENZIONE! La richiesta di revoca non puo' essere concessa: #R!
				messaggio = messaggio.replaceAll("#R", resAura.getBody().getDescrizione());
			}
		}

		// Controllo sul blocco
			
		if(revocaBO.isBlocco()) {
			this.esenzioneCittadinoIf.updateBlocco(esenzioneRedditoDB);
		} else {
			this.esenzioneCittadinoIf.update(esenzioneRedditoDB);
		}
		//boolean Storico = false;
		EsenzioneAcceleratoreEsenred r = new EsenzioneAcceleratoreEsenred(esenzioneRedditoDB,cittadinoAURABeneficiario,cittadinoAURADichiarante,cittadinoAURATitolare);
//    	ResponseAura r = new ResponseAura();
		r.setCodAura(resAura.getBody().getCodice());
//		r.setEsitoAura(resAura.getBody().getEsito());
		r.setMessaggio(messaggio);
		return r;
    }
    
	public EsenzioneAcceleratoreEsenred insert(EsenzioneCreate EsenzioneCreate,String codiceFiscale,Cittadino cittadinoAURABeneficiario,Cittadino cittadinoAURADichiarante,Cittadino cittadinoAURATitolare)
			throws EsenzioneInvalidaException, Exception {

		if (EsenzioneCreate == null) {
			EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_017);
			String messaggio = datiMancanti.getTesto();

			throw new EsenzioneInvalidaException(MSG_017, messaggio);
		}

		try {
			this.checkFormalConstraints(EsenzioneCreate);

			
			/* Verifico se esiste una esenzione dello stesso tipo che e' stata sbloccata da meno di 2 giorni.
			 * Se si blocco l'inserimento
			 */
			List<EsenredTEsenzioniReddito> elencoEsenzioniSbloccate = esenzioneCittadinoIf.getEsenzioniControlloInserimento(new Long(cittadinoAURABeneficiario.getIdAura()), EsenzioneCreate.getCodice_esenzione());
			if (elencoEsenzioniSbloccate != null && !elencoEsenzioniSbloccate.isEmpty()) {
				//va dato messaggio simile a quello del mef per il evitare l'inserimento dell'esenzione
				EsenredCMessaggi esenzionenoninseribile = this.messaggioIf.getMessaggio(MSG_033);
				String messaggio = esenzionenoninseribile.getTesto();

				throw new EsenzioneInvalidaException(MSG_033, messaggio);
			}
			else {
			//fine controllo blocco
			this.checkDomainEsenzioneConstraints(EsenzioneCreate.getCodice_esenzione(), new Long(cittadinoAURABeneficiario.getIdAura()),null, null,null);
			
			this.checkEsenzioneUgualeInLavorazione(EsenzioneCreate.getCodice_esenzione(), new Long(cittadinoAURABeneficiario.getIdAura()));
			String codASL = IntegrationClientImpl.getInstance().getCittadino(cittadinoAURABeneficiario.getIdAura()).getCodASL();
			if (EsenzioneCreate.getTitolare().getCodice_fiscale()!=null) {
			this.composeAndcheckDatiObbligatoriResponseAura(codASL, cittadinoAURABeneficiario.getIdAura(),cittadinoAURATitolare.getIdAura());	
			}
			else {
				this.composeAndcheckDatiObbligatoriResponseAura(codASL, cittadinoAURABeneficiario.getIdAura(),null);	
			}
			EsenredTEsenzioniReddito esenredTEsenzioniReddito = new EsenredTEsenzioniReddito();

			esenredTEsenzioniReddito.setDataInsert(new Date());
			esenredTEsenzioniReddito.setCodStato(StatoEsenzione.IN_LAVORAZIONE.getCodice());
			//esenredTEsenzioniReddito.setCodTitoloDichiarante(EsenzioneCreate.getRapporto_familiare());
			//recupero la descrizione del titolo dichiarante
			if (EsenzioneCreate.getRapporto_familiare()==null) {
				esenredTEsenzioniReddito.setCodTitoloDichiarante(TitoloDichiarante.INTERESSATO.getCodice());
			}
			else {
				esenredTEsenzioniReddito.setCodTitoloDichiarante(EsenzioneCreate.getRapporto_familiare());
			}
		
				titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext.getBean("titoloDichiarante");
				List<EsenredCTitoloDichiarante> elencoTitoli = titoloDichiaranteIf.getTitoli(esenredTEsenzioniReddito.getCodTitoloDichiarante());
				//for (Iterator<EsenredCTitoloDichiarante> iterator = elencoTitoli.iterator(); iterator.hasNext();) {
					esenredTEsenzioniReddito.setEsenredCTitoloDichiarante(elencoTitoli.get(0));
				//}
			
			esenredTEsenzioniReddito.setDataInizio(new Date());
			esenredTEsenzioniReddito.setDataFine(Converter.getData(getDataScadenza(Converter.getData(new Date(), "dd/MM/yyyy"),EsenzioneCreate.getCodice_esenzione())));
			esenredTEsenzioniReddito.setDataRichiesta(new Date());
			//attendere risposta
			esenredTEsenzioniReddito.setFlagConsenso(EsenzioneCreate.isDisclaimer() ? 1 : 0); 
			esenredTEsenzioniReddito.setIdUserInsert(new Long(cittadinoAURADichiarante.getIdAura()));
			esenredTEsenzioniReddito.setIdCittadinoBeneficiario(Long.valueOf(cittadinoAURABeneficiario.getIdAura()));
			esenredTEsenzioniReddito.setIdCittadinoDichiarante(Long.valueOf(cittadinoAURADichiarante.getIdAura()));
			esenredTEsenzioniReddito.setCodEsenzione(EsenzioneCreate.getCodice_esenzione());
			//recupero la descr esenzione ed il motivo
			EsenzioneIf EsenzioneIf;
			EsenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
			List<EsenredDTipiEsenzioniReddito> elencoEsenzioni = esenzioneIf.getEsenzioni(EsenzioneCreate.getCodice_esenzione());
			esenredTEsenzioniReddito.setTipoEsenzione(elencoEsenzioni.get(0));
			esenredTEsenzioniReddito.setCodNazionaleAslRilascio(codASL);
			if (EsenzioneCreate.getTitolare().getCodice_fiscale()!=null) {
				esenredTEsenzioniReddito.setIdCittadinoTitolare(Long.valueOf(cittadinoAURATitolare.getIdAura()));
			}
			//non inserisco esenzione perche non valide inserite solo una volta 
			//esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione
			
			String note = esenredTEsenzioniReddito.getCodEsenzione()+" "+this.esenzioneIf.getEsenzioni(esenredTEsenzioniReddito.getCodEsenzione()).get(0).getDescEsenzione();
			if (note.length()>50) note = note.substring(0, 49);
			RequestBody request = null;
			if (EsenzioneCreate.getTitolare().getCodice_fiscale()!=null) {
			        request = new RequestBody(
					EsenzioneCreate.getCreato_per().getCodice_fiscale(), 
					codiceFiscale, 
					codiceFiscale,
					EsenzioneCreate.getTitolare().getCodice_fiscale(), 
					EsenzioneCreate.getCodice_esenzione(), 
	        		note, 
	        		esenredTEsenzioniReddito.getCodTitoloDichiarante(), 
	        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataInizio()),
	        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataFine()));
			}
			else
			{
				 request = new RequestBody(
							EsenzioneCreate.getCreato_per().getCodice_fiscale(), 
							codiceFiscale, 
							codiceFiscale,
							EsenzioneCreate.getCreato_per().getCodice_fiscale(), 
							EsenzioneCreate.getCodice_esenzione(), 
			        		note, 
			        		esenredTEsenzioniReddito.getCodTitoloDichiarante(), 
			        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataInizio()),
			        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataFine()));
			}
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
				messaggio = messaggio.replaceAll("#DATA_SCADENZA", Converter.getData(esenredTEsenzioniReddito.getDataFine(),"dd/MM/yyyy"));
				String descBreve = this.esenzioneIf.getEsenzioni(EsenzioneCreate.getCodice_esenzione()).get(0).getDescEsenzione();
				messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
			} else { //KO
				if ("0003".equals(responseAura.getBody().getCodice())) {//0003 - KO - MEF NON DISPONIBILE
					messaggio = this.messaggioIf.getMessaggio(MSG_002).getTesto();//ATTENZIONE! La richiesta di esenzione e' stata presa in carico ed e' in fase di verifica
					
					messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
					messaggio = messaggio.replaceAll("#DATA_SCADENZA",  Converter.getData(esenredTEsenzioniReddito.getDataFine(),"dd/MM/yyyy"));
					String descBreve = this.esenzioneIf.getEsenzioni(EsenzioneCreate.getCodice_esenzione()).get(0).getDescEsenzione();
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
				//verifica se esiste almeno un altra non valida con le stesse caratteristiche
				//nel caso si non inserisce nulla altrimenti ne crea una sola
				List<EsenredTEsenzioniReddito> esenzioniNonValide = esenzioneCittadinoIf.getEsenzioniNonValide(esenredTEsenzioniReddito);
				if (esenzioniNonValide.isEmpty()) {
					esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione	
				}
			}
			
			//aggiorno l'esenzione
			//this.esenzioneCittadinoIf.update(esenredTEsenzioniReddito);//aggiorno l'esenzione in base a quanto ricevuto da AURA
			//boolean Storico = false;
			
			EsenzioneAcceleratoreEsenred r = new EsenzioneAcceleratoreEsenred(esenredTEsenzioniReddito,cittadinoAURABeneficiario,cittadinoAURADichiarante,cittadinoAURATitolare);
			
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
		if (idAuraTitolare!=null) {
			listCampiObbligatori.put("identificativo titolare", idAuraTitolare);	
		}
		this.checkDatiObbligatoriResponseAura(listCampiObbligatori);
	}

	private void checkFormalConstraints(EsenzioneCreate EsenzioneCreate)
			throws EsenzioneInvalidaException {
		String codiceEsenzione = EsenzioneCreate.getCodice_esenzione();
		
		// valida codiceEsenzione
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
		


			}
	
	/**
	 * Nasce con lo scopo di fornire un check per il codice fiscale prima che possa essere inviato a AURA.
	 * @param codiceFiscale
	 * @param test
	 * @throws EsenzioneInvalidaException
	 */
	public void checkCF(String codiceFiscale, String test) throws EsenzioneInvalidaException {
		
		if (codiceFiscale == null) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "codice fiscale"));
		}

		if (codiceFiscale.isEmpty()) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "codice fiscale"));
		}

		if (!test.equalsIgnoreCase("TRUE")) {
			boolean isCodiceFiscale = Checker.isCodiceFiscale(codiceFiscale,test);
			if (!isCodiceFiscale) {
				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
				throw new EsenzioneInvalidaException("421",
						Util.composeMessage(messaggioDto, "codice fiscale " +codiceFiscale+ " non valido"));
			}
		} else {
			if (codiceFiscale.length() != 16) {
				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
				throw new EsenzioneInvalidaException("421",
						Util.composeMessage(messaggioDto, "codice fiscale " +codiceFiscale+ " non valido"));
			}
		}
	}
}
