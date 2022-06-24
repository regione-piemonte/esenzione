/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedRes;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneCertificataBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class EsenzioneRedditoCertService {
	
	private static final String MSG_008 = "MSG008";
	private static final String MSG_009 = "MSG009";
	private static final String MSG_010 = "MSG010";
	private static final String MSG_012 = "MSG012";

    private final EsenzioneCittadinoIf esenzioneCittadinoIf;
    private final MessaggioIf messaggioIf;
    private final RevocaEsenzioneCertificataBO revocaEsenzioneBO;


    public EsenzioneRedditoCertService(RevocaEsenzioneCertificataBO revocaEsenzioneBO) {

        this.revocaEsenzioneBO = revocaEsenzioneBO;
        this.esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
        this.messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");

    }
    

    public void isRevocabile(EsenredTEsenzioniReddito esenredTEsenzioniReddito) throws CheckException {

        
        boolean isRevocabile = esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice());

        if (!isRevocabile) {
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String errore = Util.composeMessage(msg010, "l'esenzione risulta essere non revocabile");
            throw new CheckException("MSG010", errore);
        }
    }

    public void checkCampoRevoca() throws CheckException {
        String motivoEsenzione = this.revocaEsenzioneBO.getMotivoRevocaEsenzione();

        if (motivoEsenzione.length() > 500) {
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String errore = Util.composeMessage(msg010, "la lunghezza del campo motivo revoca e' superiore al limite di caratteri consentito (500)");
            throw new CheckException("404", errore);
        }

    }
    
    public void checkCampoRevocaObbligatorio() throws CheckException {
        String motivoEsenzione = this.revocaEsenzioneBO.getMotivoRevocaEsenzione();
        
        if (motivoEsenzione.isEmpty()) { //facoltativa per il cittadino
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String errore = Util.composeMessage(msg010, "il campo motivo revoca deve essere valorizzato");
            throw new CheckException("MSG010", errore);
        }
    }

    public ResponseAura update(RevocaEsenzioneCertificataBO revocaBO, EsenredTEsenzioniReddito esenzioneRedditoDB, String codFiscale,String idAura) throws CheckException {
    	
    	ResponseAura r = new ResponseAura();
		try {
			
			if (esenzioneRedditoDB.getNumProtocolloSogei()!=0 && esenzioneRedditoDB.getNumProtocolloSogei()!=null) {
				RevocaEsenzioneBO revocaesenzioneBO = new RevocaEsenzioneBO();
				revocaesenzioneBO.setBlocco(false);
				revocaesenzioneBO.setIdEsenzione(new Long(0));
				revocaesenzioneBO.setMotivoRevocaEsenzione(revocaBO.getMotivoRevocaEsenzione());
				revocaesenzioneBO.setNotaInternaRevoca(revocaBO.getNotaInternaRevoca());
				EsenzioneRedditoService service = new EsenzioneRedditoService(revocaesenzioneBO);
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
				if ("OK".equalsIgnoreCase(codice)) {
					//verifico se esiste esenzione valida in esenred db
					 EsenredTEsenzioniReddito esenredTEsenzioniRedditos = this.esenzioneCittadinoIf.getEsenzioneByProtocollo(esenzioneRedditoDB.getNumProtocolloSogei(),new Long(idAura),esenzioneRedditoDB.getCodEsenzione());
					 if (esenredTEsenzioniRedditos!=null) {
						 esenredTEsenzioniRedditos.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
						 esenredTEsenzioniRedditos.setDescMotivoRevoca(revocaBO.getMotivoRevocaEsenzione());
						 esenredTEsenzioniRedditos.setNota(revocaBO.getNotaInternaRevoca());
						 esenredTEsenzioniRedditos.setDataRevoca(new Date());
						 //ho una copia su esenred faccio update
						 this.esenzioneCittadinoIf.update(esenredTEsenzioniRedditos);
					 }
					 else {
						esenzioneRedditoDB.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
						esenzioneRedditoDB.setDescMotivoRevoca(revocaBO.getMotivoRevocaEsenzione());
						esenzioneRedditoDB.setNota(revocaBO.getNotaInternaRevoca());
					    esenzioneRedditoDB.setDataRevoca(new Date());
				    	esenzioneRedditoDB.setIdUserInsert(new Long(99999999));
				    	if (!Checker.isValorizzato(esenzioneRedditoDB.getCodTitoloDichiarante()))
				    	esenzioneRedditoDB.setCodTitoloDichiarante("0");
				    	esenzioneRedditoDB.setDataRichiesta(esenzioneRedditoDB.getDataInizio());
				    	this.esenzioneCittadinoIf.insert(esenzioneRedditoDB);
					 }
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
				r.setCodAura(resAura.getBody().getCodice());
				r.setEsitoAura(resAura.getBody().getEsito());
				r.setMessaggio(messaggio);
				return r;
			}
			else {
				ChiusuraEsenRedRes resAura;
		    	
		    	resAura = IntegrationClientImpl.getInstance().revocaEsenzioneCertificata(revocaEsenzioneBO);
    	String messaggio = null;
    	
		String codice = resAura.getHeader().getCodiceRitorno();
		esenzioneRedditoDB.setDataModify(new Date());
		if ("OK".equalsIgnoreCase(codice)) {
			esenzioneRedditoDB.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
			esenzioneRedditoDB.setDescMotivoRevoca(revocaBO.getMotivoRevocaEsenzione());
			esenzioneRedditoDB.setNota(revocaBO.getNotaInternaRevoca());
	    	esenzioneRedditoDB.setDataRevoca(new Date());
	    	esenzioneRedditoDB.setIdUserInsert(new Long(99999997));
	    	esenzioneRedditoDB.setCodTitoloDichiarante("0");
	    	esenzioneRedditoDB.setDataRichiesta(esenzioneRedditoDB.getDataInizio());
			messaggio = this.messaggioIf.getMessaggio(MSG_008).getTesto();//'La richiesta di revoca e' stata compilata correttamente! Numero Protocollo #N_PROTOCOLLO.'
			this.esenzioneCittadinoIf.insert(esenzioneRedditoDB);
			//messaggio = messaggio.replaceAll("#N_PROTOCOLLO", resAura.getBody().getCodice());
		} else { //KO
			if ("0003".equals(resAura.getFooter().getMessages().get(0).getCodice())) {//0003 - KO - MEF NON DISPONIBILE
				messaggio = this.messaggioIf.getMessaggio(MSG_009).getTesto();//'ATTENZIONE! La richiesta di revoca e' stata presa in carico ed e' in fase di verifica.
			} else {
//				esenzioneRedditoDB.setCodStato("N");//Non valida //confermato da Annamaria il 15/03/2018 alle 16.38
				messaggio = this.messaggioIf.getMessaggio(MSG_010).getTesto();//ATTENZIONE! La richiesta di revoca non puo' essere concessa: #R!
				messaggio = messaggio.replaceAll("#R", resAura.getFooter().getMessages().get(0).getCodice());
			}
		}
		r.setCodAura(resAura.getFooter().getMessages().get(0).getCodice());
		r.setEsitoAura(codice);
		r.setMessaggio(messaggio);
		return r;
			}
		} catch (Exception e) {
			
			r.setMessaggio(this.messaggioIf.getMessaggio(MSG_012).getTesto());
			return r;
		}
    }
    
}
