/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import it.csi.esenred.esenredsrv.business.check.CheckInserisciNotifica;
import it.csi.esenred.esenredsrv.business.context.SpringApplicationContext;
import it.csi.esenred.esenredsrv.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredsrv.business.entity.EsenredWNotifiche;
import it.csi.esenred.esenredsrv.business.exception.CheckException;
import it.csi.esenred.esenredsrv.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredsrv.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredsrv.business.model.interfaces.NotificaIf;
import it.csi.esenred.esenredsrv.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredsrv.dto.NotificaRequest;
import it.csi.esenred.esenredsrv.dto.NotificaResponse;
import it.csi.esenred.esenredsrv.util.Converter;
import it.csi.esenred.esenredsrv.util.LogUtil;
import it.csi.esenred.esenredsrv.util.Util;

@Path("/rest")
public class InserisciNotificaService {

	MessaggioIf messaggioIf;
	NotificaIf notificaIf;
	EsenzioneCittadinoIf esenzioneCittadinoIf;
	ParametroIf parametroIf;
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE, null);
	
	@POST
	@Path("/inseriscinotifica")
	@Consumes("application/json")
	@Produces("application/json")
	public Response inseriscinotifica(NotificaRequest notificaRequest) throws CheckException {
		NotificaResponse response = new NotificaResponse();
		log.info("inseriscinotifica", " BEGIN");
		
		String esitoPositivo;
		try {
			CheckInserisciNotifica.isValidRequest(notificaRequest);
			
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			EsenredTEsenzioniReddito esenzioneDB = esenzioneCittadinoIf.getEsenzioneCittadino(Converter.getLong(notificaRequest.getIdAura()), notificaRequest.getCodEsenzione(), Converter.getData(notificaRequest.getDataInizio()));
			CheckInserisciNotifica.chkEsenzione(esenzioneDB);
			
			esenzioneDB = aggiornaEsenzione(esenzioneDB, notificaRequest);
			esenzioneCittadinoIf.update(esenzioneDB);
			log.info("inseriscinotifica", " Inizializzo la notifica");
			EsenredWNotifiche notifica = creaNotifica(notificaRequest,esenzioneDB);
			log.info("inseriscinotifica", " desc notifica: " + notifica.getDescNotifica());
			log.info("inseriscinotifica", " cod tipologia: " + notifica.getCodTipologia());
			log.info("inseriscinotifica", " flag consultazione: " + notifica.getFlagConsultazione());
			
			log.logXmlTypeObject(notifica, "inseriscinotifica");
			notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
			
			notificaIf.insert(notifica);
			log.info("inseriscinotifica", " notifica insetita sul db ID:  " + notifica.getIdNotifica());
			esitoPositivo = notifica.getDescNotifica();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String descErrore = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());
			response.setDescEsito(descErrore);
			response.setCodEsito("0");
			return Response.status(200).entity(response).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String descErrore = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());
			response.setDescEsito(descErrore);
			response.setCodEsito("0");
			return Response.status(200).entity(response).build();
		}
		response.setDescEsito(esitoPositivo);
		response.setCodEsito("1");
		
		log.info("inseriscinotifica", " DescEsito: " + response.getDescEsito());
		log.info("inseriscinotifica", " CodEsito: " + response.getCodEsito());
		
		
		log.info("inseriscinotifica", " END");
		return Response.status(200).entity(response).build();
	}

	private EsenredTEsenzioniReddito aggiornaEsenzione(EsenredTEsenzioniReddito esenzioneDB,NotificaRequest notificaRequest) {
		if (notificaRequest.getEsito().equals("1")) { //OK
			if (notificaRequest.getTipoNotifica().equalsIgnoreCase("R")) {
				esenzioneDB.setCodStato("R");//revocata
				esenzioneDB.setDataRevoca(new Date());
			} else {
				esenzioneDB.setCodStato("V");//valida
			}
			esenzioneDB.setNumProtocolloSogei(Converter.getLong(notificaRequest.getNumProtSogei()));
		} else {
			if (notificaRequest.getTipoNotifica().equalsIgnoreCase("I")) {
				esenzioneDB.setCodStato("N");//Non valida
			}
		}
		esenzioneDB.setDataModify(new Date());
		return esenzioneDB;
	}

	/**
	 * 
	 * @param notificaRequest
	 * @param esenzioneDB 
	 * @return
	 */
	private EsenredWNotifiche creaNotifica(NotificaRequest notificaRequest, EsenredTEsenzioniReddito esenzioneDB) {
		EsenredWNotifiche notifica = new EsenredWNotifiche();

		String descNotifica = null;
		parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
		
		if (notificaRequest.getEsito().equals("1")) { //tutto ok
			if (notificaRequest.getTipoNotifica().equalsIgnoreCase("R")) {
				descNotifica = parametroIf.getParametro("DESC_NOTIFICA_R_OK").getValore();
			} else if (notificaRequest.getTipoNotifica().equalsIgnoreCase("I")) {
				descNotifica = parametroIf.getParametro("DESC_NOTIFICA_I_OK").getValore();
			}
			if (descNotifica.contains("#COD_ESENZIONE")) descNotifica = descNotifica.replaceAll("#COD_ESENZIONE", notificaRequest.getCodEsenzione());
			if (descNotifica.contains("#NUM_PROTOCOLLO")) descNotifica = descNotifica.replaceAll("#NUM_PROTOCOLLO", notificaRequest.getNumProtSogei());
		} else { //errore
			if (notificaRequest.getTipoNotifica().equalsIgnoreCase("R")) {
				descNotifica = parametroIf.getParametro("DESC_NOTIFICA_R_KO").getValore();
			} else if (notificaRequest.getTipoNotifica().equalsIgnoreCase("I")) {
				descNotifica = parametroIf.getParametro("DESC_NOTIFICA_I_KO").getValore();
			}
			if (descNotifica.contains("#COD_ESENZIONE")) descNotifica = descNotifica.replaceAll("#COD_ESENZIONE", notificaRequest.getCodEsenzione());
			if (descNotifica.contains("#COD_ESITO")) descNotifica = descNotifica.replaceAll("#COD_ESITO", notificaRequest.getCodEsitoMEF());
			if (descNotifica.contains("#DESC_ESITO")) descNotifica = descNotifica.replaceAll("#DESC_ESITO", notificaRequest.getDescEsitoMEF());
		}

		notifica.setDescNotifica(descNotifica);
		notifica.setCodTipologia(notificaRequest.getTipoNotifica());
		notifica.setData(new Date());
		notifica.setFlagConsultazione(new Integer(0));

		if (esenzioneDB.getIdOperatoreRichiesta() != null) {
			notifica.setIdOperatore(esenzioneDB.getIdOperatoreRichiesta());
		} else if (esenzioneDB.getIdCittadinoDichiarante() != null) {
			notifica.setIdAura(esenzioneDB.getIdCittadinoDichiarante());
		}
		return notifica;
	}
}