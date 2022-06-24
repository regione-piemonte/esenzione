/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedRes;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.RequestBody;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoAutocertificazioneBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneCertificataBO;
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
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.EsenzioneAcceleratore;
import it.csi.esenred.esenredweb.dto.EsenzioneCreate;
import it.csi.esenred.esenredweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class CreateRedditoService extends AbstractEsenzioneRedditoService {

	private static final String MSG_008 = "MSG008";
	private static final String MSG_009 = "MSG009";
	private static final String MSG_010 = "MSG010";
	private static final String MSG_012 = "MSG012";
	TitoloDichiaranteIf titoloDichiaranteIf;
	
    public EsenzioneAcceleratore update(RevocaEsenzioneBO revocaBO, String idAura) throws CheckException {
 
    	String methodName = "update";
		log.info(methodName, "BEGIN");
		
    	List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos = esenzioneCittadinoIf.getEsenzioneById(revocaBO.getIdEsenzione());
		 EsenredTEsenzioniReddito esenzioneRedditoDB = esenredTEsenzioniRedditos.get(0);
		 Cittadino cittadinoAURABeneficiario = null;
		 Cittadino cittadinoAURADichiarante = null;
		 Cittadino cittadinoAURATitolare = null;
        if (esenzioneRedditoDB.getIdCittadinoBeneficiario().equals(esenzioneRedditoDB.getIdCittadinoDichiarante())) {
        	log.info(methodName, " condizione verificata --> db.IdCittadinoBeneficiario = db.IdCittadinoDichiarante");
        	cittadinoAURABeneficiario=IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoBeneficiario().toString());
        	cittadinoAURADichiarante=cittadinoAURABeneficiario;
        }
        else {
        	log.info(methodName, " condizione verificata --> db.IdCittadinoBeneficiario <> db.IdCittadinoDichiarante, interrogo AURA");
        	cittadinoAURABeneficiario = IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoBeneficiario().toString());
        	cittadinoAURADichiarante = IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoDichiarante().toString());
        	log.info(methodName, " Aura.cittadinoAURABeneficiario: "  +cittadinoAURABeneficiario);
        	log.info(methodName, " Aura.cittadinoAURADichiarante: "+cittadinoAURADichiarante);
        }
        if (esenzioneRedditoDB.getIdCittadinoTitolare()!=null) {
        	if (esenzioneRedditoDB.getIdCittadinoBeneficiario().equals(esenzioneRedditoDB.getIdCittadinoTitolare())) {
        		cittadinoAURATitolare=cittadinoAURABeneficiario;
        		log.info(methodName, " condizione verificata --> db.IdCittadinoBeneficiario = db.IdCittadinoTitolare");
        	}
        	else if (esenzioneRedditoDB.getIdCittadinoDichiarante().equals(esenzioneRedditoDB.getIdCittadinoTitolare())) {
        		cittadinoAURATitolare=cittadinoAURADichiarante;
        		log.info(methodName, " condizione verificata --> db.IdCittadinoDichiarante = db.IdCittadinoTitolare");
        	}
        	else {
        		log.info(methodName, " condizione verificata --> db.IdCittadinoDichiarante <> db.IdCittadinoTitolare e db.IdCittadinoBeneficiario <> db.IdCittadinoTitolare");
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
    	it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Response resAura = IntegrationClientImpl.getInstance().revocaEsenzione(reqAura); 
    	String messaggio = null;
    	if (resAura == null) {
    		log.error(methodName, "Errore --> risposta service Revoca Aura is null");
    		throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
    	}
		String codice = resAura.getBody().getEsito();
		log.info(methodName, "Codice Esito REVOCA [" +codice+"]");
		esenzioneRedditoDB.setDataModify(new Date());
		if ("OK".equalsIgnoreCase(codice)) {
			esenzioneRedditoDB.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
			esenzioneRedditoDB.setDescMotivoRevoca(revocaBO.getMotivoRevocaEsenzione());
			esenzioneRedditoDB.setNota(revocaBO.getNotaInternaRevoca());
	    	esenzioneRedditoDB.setDataRevoca(new Date());
			messaggio = this.messaggioIf.getMessaggio(MSG_008).getTesto();//'La richiesta di revoca e' stata compilata correttamente! Numero Protocollo #N_PROTOCOLLO.'
			messaggio = messaggio.replaceAll("#N_PROTOCOLLO", resAura.getBody().getCodice());
			log.info(methodName, "Messaggio Esito REVOCA [" +messaggio+"]");
		} else { //KO
			if ("0003".equals(resAura.getBody().getCodice())) {//0003 - KO - MEF NON DISPONIBILE
				messaggio = this.messaggioIf.getMessaggio(MSG_009).getTesto();//'ATTENZIONE! La richiesta di revoca e' stata presa in carico ed e' in fase di verifica.
			
			} else {
				messaggio = this.messaggioIf.getMessaggio(MSG_010).getTesto();//ATTENZIONE! La richiesta di revoca non puo' essere concessa: #R!
				messaggio = messaggio.replaceAll("#R", resAura.getBody().getDescrizione());
			}
		}
		
		log.info(methodName, "Messaggio Esito REVOCA [" +messaggio+"]");


			
		if(revocaBO.isBlocco()) {
			this.esenzioneCittadinoIf.updateBlocco(esenzioneRedditoDB);
		} else {
			this.esenzioneCittadinoIf.update(esenzioneRedditoDB,idAura);
		}
;
		EsenzioneAcceleratore r = new EsenzioneAcceleratore(esenzioneRedditoDB,cittadinoAURABeneficiario,cittadinoAURADichiarante,cittadinoAURATitolare);
;
		r.setCodAura(resAura.getBody().getCodice());

		r.setMessaggio(messaggio);
		return r;
    }
    
    public EsenzioneAcceleratore updateCertificate(RevocaEsenzioneCertificataBO revocaBO,String idAura) throws Exception {
    	 
   
		
		List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos = IntegrationClientImpl.getInstance().getEsenzioni(null, revocaBO.getCodiceEsenzione(), revocaBO.getDataInizioValidita(), idAura, "B",null,null);

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
        	if (Checker.isValorizzato(esenzioneRedditoDB.getIdCittadinoDichiarante()))
        	cittadinoAURADichiarante = IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoDichiarante().toString());
        	else
        	cittadinoAURADichiarante = cittadinoAURABeneficiario;
        }
        if (Checker.isValorizzato(esenzioneRedditoDB.getIdCittadinoTitolare())) {
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
		if (esenzioneRedditoDB.getNumProtocolloSogei()!=0 && esenzioneRedditoDB.getNumProtocolloSogei()!=null) {
			//chiama esenzione Autocertificata revoca con protocollo
			String codFiscale = cittadinoAURABeneficiario.getCodFiscale(); 
			RevocaEsenzioneBO revocaEsenzioneBO = new RevocaEsenzioneBO();
			revocaEsenzioneBO.setBlocco(false);
			revocaEsenzioneBO.setIdEsenzione(new Long(0));
			revocaEsenzioneBO.setMotivoRevocaEsenzione(revocaBO.getMotivoRevocaEsenzione());
            revocaEsenzioneBO.setNotaInternaRevoca(revocaBO.getNotaInternaRevoca());
	    	EsenzioneRedditoService service = new EsenzioneRedditoService(revocaEsenzioneBO);
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
		    	esenzioneRedditoDB.setIdUserInsert(new Long(99999999));
		    	if (Checker.isValorizzato(esenzioneRedditoDB.getCodTitoloDichiarante()))
		    		esenzioneRedditoDB.setCodTitoloDichiarante(esenzioneRedditoDB.getCodTitoloDichiarante());
		    	else
		    		esenzioneRedditoDB.setCodTitoloDichiarante("0");
		    	esenzioneRedditoDB.setDataRichiesta(esenzioneRedditoDB.getDataInizio());
				esenzioneCittadinoIf.insert(esenzioneRedditoDB);
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
			
			EsenzioneAcceleratore r = new EsenzioneAcceleratore(esenzioneRedditoDB,cittadinoAURABeneficiario,cittadinoAURADichiarante,cittadinoAURATitolare);
			r.setCodAura(resAura.getBody().getCodice());
			r.setMessaggio(messaggio);
			return r;
		}
		else {
			//chiama esenzione certificata senza protocollo
    	ChiusuraEsenRedRes resAura = IntegrationClientImpl.getInstance().revocaEsenzioneCertificata(revocaBO);
    	String messaggio = null;
    	if (resAura == null) {
    		throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
    	}
		String codice = resAura.getHeader().getCodiceRitorno();
		
		if ("OK".equalsIgnoreCase(codice)) {
			messaggio = this.messaggioIf.getMessaggio(MSG_008).getTesto();
			esenzioneRedditoDB.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
			esenzioneRedditoDB.setDescMotivoRevoca(revocaBO.getMotivoRevocaEsenzione());
			esenzioneRedditoDB.setNota(revocaBO.getNotaInternaRevoca());
	    	esenzioneRedditoDB.setDataRevoca(new Date());
	    	esenzioneRedditoDB.setIdUserInsert(new Long(99999997));
	    	esenzioneRedditoDB.setCodTitoloDichiarante("0");
	    	esenzioneRedditoDB.setDataRichiesta(esenzioneRedditoDB.getDataInizio());
			esenzioneCittadinoIf.insert(esenzioneRedditoDB);

		} else { //KO
			if ("0003".equals( resAura.getFooter().getMessages().get(0).getCodice())) {//0003 - KO - MEF NON DISPONIBILE
				messaggio = this.messaggioIf.getMessaggio(MSG_009).getTesto();//'ATTENZIONE! La richiesta di revoca e' stata presa in carico ed e' in fase di verifica.
			} else {
				messaggio = this.messaggioIf.getMessaggio(MSG_010).getTesto();//ATTENZIONE! La richiesta di revoca non puo' essere concessa: #R!
				messaggio = messaggio.replaceAll("#R",  resAura.getFooter().getMessages().get(0).getDescrizione());
			}
		}
		EsenzioneAcceleratore r = new EsenzioneAcceleratore(esenzioneRedditoDB,cittadinoAURABeneficiario,cittadinoAURADichiarante,cittadinoAURATitolare);
		r.setCodAura( resAura.getFooter().getMessages().get(0).getCodice());
		r.setMessaggio(messaggio);
		return r;
		}
    	
		
    }
    
	public EsenzioneAcceleratore insert(EsenzioneCreate EsenzioneCreate,String codiceFiscale,Cittadino cittadinoAURABeneficiario,Cittadino cittadinoAURADichiarante,Cittadino cittadinoAURATitolare)
			throws EsenzioneInvalidaException, Exception {

		if (EsenzioneCreate == null) {
			EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_017);
			String messaggio = datiMancanti.getTesto();

			throw new EsenzioneInvalidaException(MSG_017, messaggio);
		}

		try {
			this.checkFormalConstraints(EsenzioneCreate);

			

			List<EsenredTEsenzioniReddito> elencoEsenzioniSbloccate = esenzioneCittadinoIf.getEsenzioniControlloInserimento(new Long(cittadinoAURABeneficiario.getIdAura()), EsenzioneCreate.getCodice_esenzione());
			if (elencoEsenzioniSbloccate != null && !elencoEsenzioniSbloccate.isEmpty()) {
				
				EsenredCMessaggi esenzionenoninseribile = this.messaggioIf.getMessaggio(MSG_033);
				String messaggio = esenzionenoninseribile.getTesto();

				throw new EsenzioneInvalidaException(MSG_033, messaggio);
			}
			else {
			//fine controllo blocco
			this.checkDomainEsenzioneConstraints(EsenzioneCreate.getCodice_esenzione(), new Long(cittadinoAURABeneficiario.getIdAura()),null, null);
			
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
					messaggio = this.messaggioIf.getMessaggio(MSG_002).getTesto();
					
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

				List<EsenredTEsenzioniReddito> esenzioniNonValide = esenzioneCittadinoIf.getEsenzioniNonValide(esenredTEsenzioniReddito);
				if (esenzioniNonValide.isEmpty()) {
					esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione	
				}
			}
			

			
			EsenzioneAcceleratore r = new EsenzioneAcceleratore(esenredTEsenzioniReddito,cittadinoAURABeneficiario,cittadinoAURADichiarante,cittadinoAURATitolare);
			
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
			log.error("checkCF", "codice fiscale null");
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "codice fiscale"));
		}

		if (codiceFiscale.isEmpty()) {
			log.error("checkCF", "codice fiscale vuoto");
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "codice fiscale"));
		}

		if (!test.equalsIgnoreCase("TRUE")) {
			boolean isCodiceFiscale = Checker.isCodiceFiscale(codiceFiscale,test);
			if (!isCodiceFiscale) {
				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
				log.error("checkCF", "codice fiscale formalmente errato");
				throw new EsenzioneInvalidaException("421",
						Util.composeMessage(messaggioDto, "codice fiscale " +codiceFiscale+ " non valido"));
			}
		} else {
			if (codiceFiscale.length() != 16) {
				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
				log.error("checkCF", "codice fiscale formalmente errato");
				throw new EsenzioneInvalidaException("421",
						Util.composeMessage(messaggioDto, "codice fiscale " +codiceFiscale+ " non valido"));
			}
		}
	}
}
