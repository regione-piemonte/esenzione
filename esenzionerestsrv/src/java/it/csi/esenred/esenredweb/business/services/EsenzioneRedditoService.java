/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.List;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.util.LogUtil;
import it.csi.esenred.esenredweb.util.Util;

public class EsenzioneRedditoService {
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENRED, null);
	
	private static final String MSG_008 = "MSG008";
	private static final String MSG_009 = "MSG009";
	private static final String MSG_010 = "MSG010";
	private static final String MSG_012 = "MSG012";

    private final EsenzioneCittadinoIf esenzioneCittadinoIf;
    private final MessaggioIf messaggioIf;
    private final RevocaEsenzioneBO revocaEsenzioneBO;

    public EsenzioneRedditoService(RevocaEsenzioneBO revocaEsenzioneBO) {

        this.revocaEsenzioneBO = revocaEsenzioneBO;
        this.esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
        this.messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");

    }

    public List<EsenredTEsenzioniReddito> get() {
        List<EsenredTEsenzioniReddito> esenzioneById = this.esenzioneCittadinoIf.getEsenzioneById(revocaEsenzioneBO.getIdEsenzione());

        return esenzioneById;
    }


    public void exists() throws CheckException {
        List<EsenredTEsenzioniReddito> esenzioneById = this.esenzioneCittadinoIf.getEsenzioneById(revocaEsenzioneBO.getIdEsenzione());

        if (esenzioneById.isEmpty()) {
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String msg010Testo = Util.composeMessage(msg010, "esenzione non presente in archivio");
            throw new CheckException("MSG010", msg010Testo);
        }
        
        log.info("exists", " check esenzione exist (rilettura sul db by id "+esenzioneById +" ) --> OK");
    }

    public void isRevocabile() throws CheckException {

        List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos = this.get();
        EsenredTEsenzioniReddito esenredTEsenzioniReddito = esenredTEsenzioniRedditos.get(0);

        boolean isRevocabile = esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice());

        if (!isRevocabile) {
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String errore = Util.composeMessage(msg010, "l'esenzione risulta essere non revocabile");
            throw new CheckException("MSG010", errore);
        }
        
        log.info("isRevocabile", " check isRevocabile TRUE --> OK");
    }

    public void checkCampoRevoca() throws CheckException {
        String motivoEsenzione = this.revocaEsenzioneBO.getMotivoRevocaEsenzione();

        if (motivoEsenzione.length() > 500) {
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String errore = Util.composeMessage(msg010, "la lunghezza del campo motivo revoca e' superiore al limite di caratteri consentito (500)");
            throw new CheckException("404", errore);
        }
        
        log.info("checkCampoRevoca", " check lunghezza MotivoRevocaEsenzione --> OK");

    }
    
    public void checkCampoRevocaObbligatorio() throws CheckException {
        String motivoEsenzione = this.revocaEsenzioneBO.getMotivoRevocaEsenzione();
        
        if (motivoEsenzione.isEmpty()) { //facoltativa per il cittadino
            EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
            String errore = Util.composeMessage(msg010, "il campo motivo revoca deve essere valorizzato");
            throw new CheckException("MSG010", errore);
        }
        log.info("checkCampoRevocaObbligatorio", " check is not empty MotivoRevocaEsenzione --> OK");
    }

    public ResponseAura update(RevocaEsenzioneBO revocaBO, EsenredTEsenzioniReddito esenzioneRedditoDB) throws CheckException {
    	//EsenredTEsenzioniReddito esenzioneRedditoDB = new EsenredTEsenzioniReddito() ;
    	if (esenzioneRedditoDB==null) {
    		List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos = this.get();
        	 esenzioneRedditoDB = esenredTEsenzioniRedditos.get(0);
    	}

    	Cittadino c = IntegrationClientImpl.getInstance().getCittadino(esenzioneRedditoDB.getIdCittadinoBeneficiario().toString());
    	String codFiscale = c.getCodFiscale(); 
    	
    	it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody reqAura = createRequestAuraRevocaEsenzione(
    			codFiscale, 
    			codFiscale, 
    			esenzioneRedditoDB.getCodEsenzione(), 
    			esenzioneRedditoDB.getNumProtocolloSogei().toString());
    	it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Response resAura = IntegrationClientImpl.getInstance().revocaEsenzione(reqAura);
    	String messaggio = null;
    	if (resAura == null) {
    		throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
    	}
		String codice = resAura.getBody().getEsito();
		esenzioneRedditoDB.setDataModify(new Date());
		if ("OK".equalsIgnoreCase(codice)) {
			esenzioneRedditoDB.setCodStato(StatoEsenzione.REVOCATA.getCodice());//Revocata
			esenzioneRedditoDB.setDescMotivoRevoca(revocaEsenzioneBO.getMotivoRevocaEsenzione());
			esenzioneRedditoDB.setNota(revocaEsenzioneBO.getNotaInternaRevoca());
	    	esenzioneRedditoDB.setDataRevoca(new Date());
			messaggio = this.messaggioIf.getMessaggio(MSG_008).getTesto();
			messaggio = messaggio.replaceAll("#N_PROTOCOLLO", resAura.getBody().getCodice());
		} else { //KO
			if ("0003".equals(resAura.getBody().getCodice())) {
				messaggio = this.messaggioIf.getMessaggio(MSG_009).getTesto();//'ATTENZIONE! La richiesta di revoca e' stata presa in carico ed e' in fase di verifica.
			} else {

				messaggio = this.messaggioIf.getMessaggio(MSG_010).getTesto();//ATTENZIONE! La richiesta di revoca non puo' essere concessa: #R!
				messaggio = messaggio.replaceAll("#R", resAura.getBody().getDescrizione());
			}
		}

		
			
		if(revocaBO.isBlocco()) {
			this.esenzioneCittadinoIf.updateBlocco(esenzioneRedditoDB);
		} else {
			this.esenzioneCittadinoIf.update(esenzioneRedditoDB,null);
		}
    	
    	ResponseAura r = new ResponseAura();
		r.setCodAura(resAura.getBody().getCodice());
		r.setEsitoAura(resAura.getBody().getEsito());
		r.setMessaggio(messaggio);
		return r;
    }

    it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody createRequestAuraRevocaEsenzione(
    		String codFiscaleChiamante,
    		String codFiscaleBeneficiario,
    		String codEsenzione, 
    		String numProtocolloSogei) {
    	it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody req = new it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody();
    	req.setCfAssistito(codFiscaleBeneficiario);
    	req.setCfChiamante(codFiscaleChiamante);
    	req.setCodeEsenzione(codEsenzione);
    	req.setNumProtMef(numProtocolloSogei);
    	return req;
    }
}
