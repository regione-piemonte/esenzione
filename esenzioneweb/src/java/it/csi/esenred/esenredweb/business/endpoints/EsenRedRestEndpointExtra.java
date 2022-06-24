/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.endpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenpatweb.business.facade.CertificatiFacade;
import it.csi.esenred.esenpatweb.business.facade.EsenzioniFacade;
import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenpatweb.dto.AggiornaAttestato;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.CittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.FiltriValidaEsenzionePatologia;
import it.csi.esenred.esenpatweb.dto.Parametro;
import it.csi.esenred.esenpatweb.dto.UserInfo;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.AuditIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.RestUtils;

@Path("/rest/servicesextra")
public class EsenRedRestEndpointExtra {
	
	private static String isTst;
	private static String ambiente;
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		return corsedResponse.status(Status.OK).entity("TEST OK").build();
	}

	@POST
	@Path("/aggiornaAttestatoBatch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setAggiornaAttestatoBatch(@Context HttpServletRequest req, AggiornaAttestato datiBatch) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		final String service = "Aggiorna attestato esenzione Batch";
		final String operation = "UPDATE";
		
		String citId = datiBatch.getCitId();
		String esenzioneId = datiBatch.getEsenzioneId();

		CittadinoEsenpat datiCittadino = new CittadinoEsenpat();

		UserInfo utente = new UserInfo();
		utente.setCodASL("010301"); // asl città di torino
		utente.setCodFisc(datiBatch.getCodBatch());
		utente.setCognome("BATCH");
		utente.setNome("BATCH");
		utente.setIdAura(datiBatch.getBatchId());
	
		List<Ruolo> ruoli = new ArrayList<Ruolo>();
		ruoli.add(new Ruolo("BATCH","BATCH","BATCH"));
		FiltriValidaEsenzionePatologia bo = new FiltriValidaEsenzionePatologia();
		
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		try {

			isTst();

			EsenzioniFacade esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			EsenzioneTPraticaEsenzione esen = esenzioniFacade
					.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId));

			if (esen != null) {
				
				List<Cittadino> cittadini = IntegrationClientImpl.getInstance()
	                    .findCittadino(new Cittadino(esen.getCodiceFiscaleBeneficiario()));
				
				datiCittadino = IntegrationClientImpl.getInstance().getCittadinoEsenpat(cittadini.get(0).getIdAura());

				CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext
						.getBean("certificatiFacade");
				EsenzioneTDocumento attestatoEsenzione = certificatiFacade
						.getEsenzioneTDocumentoAttestato(datiCittadino.getCodFiscale());
				
				esenzioniFacade.aggiornaAttestato(esen, attestatoEsenzione, utente, datiCittadino, ruoli);

				res.put("status", "ok");
				res.put("data", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", "Aggiornamento Attestato eseguito con successo");

				// return corsedResponse.status(Status.OK).build();
				return corsedResponse.status(Status.OK).entity(res).build();
			}

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (EsenpatException e) {
			e.printStackTrace();
			EsenredCMessaggi messaggio = getMessaggioEsenpat(e.getCodMessaggio());
			res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());
			registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio.getTesto(), service, operation,
					shibIdentitaCodiceFiscale, null, null, null, citId, null);
			return corsedResponse.status(Status.CONFLICT).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	protected String getAmbiente() {
		// checkAmbiente
		if (ambiente == null) {
			List<Parametro> listparametriOut = new ArrayList<Parametro>();
			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ENVIROMENT");

			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri pDB = (EsenredCParametri) iterator.next();
				listparametriOut.add(new Parametro(pDB));
			}

			if (!listparametriOut.isEmpty()) {
				ambiente = listparametriOut.get(0).getValoreParametro();
			}

		}
		return ambiente;
	}
	
	protected void registraSuAudit(String ipAddress, String keyOper, String oggOper, String operazione, String utente,
			String idRequest, String uuId, String xcodServizio, String utenteBen, String utenteDel) {
		try {
			getAmbiente();
			String appId = "ESENZIONI_RP-01_TEST_ESENPAT";
			if (ambiente != null) {
				appId = "ESENZIONI_RP-01_" + ambiente + "_ESENPAT";
			}

			AuditIf auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
			// if (utenteDel.equalsIgnoreCase(utenteBen))
			// utenteDel = null;
			CsiLogAudit audit = new CsiLogAudit(appId, ipAddress, keyOper, oggOper, operazione, utente, idRequest, uuId,
					xcodServizio, utenteBen, utenteDel);
			auditIf.insertAudit(audit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> generateResponseErrore(String msg, String code, int status) {
	    Map<String, Object> res = new HashMap<String, Object>();
	    res = new HashMap<String, Object>();
	    res.put(Constants.STATUS, status);
	    res.put(Constants.MESSAGE, code);
	    res.put(Constants.CODE, msg);
	    return res;
	  }
	
	protected EsenredCMessaggi getMessaggioEsenpat(String msg) {
		try {
			MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			return messaggioIf.getMessaggio(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EsenredCMessaggi esenredCMessaggi = new EsenredCMessaggi();
		esenredCMessaggi.setCodice(msg);
		esenredCMessaggi.setTesto("Errore non anagrafato sul DB");
		return new EsenredCMessaggi();
	}
	
	protected String isTst() {
		// checkAmbiente
		if (isTst == null) {
			List<Parametro> listparametriOut = new ArrayList<Parametro>();
			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");

			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri pDB = (EsenredCParametri) iterator.next();
				listparametriOut.add(new Parametro(pDB));
			}

			if (!listparametriOut.isEmpty()) {
				isTst = listparametriOut.get(0).getValoreParametro();
			}

		}
		return isTst;
	}

	
}