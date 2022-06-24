/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.endpoints;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenpatweb.business.facade.CertificatiFacade;
import it.csi.esenred.esenpatweb.business.facade.CittadinoFacade;
import it.csi.esenred.esenpatweb.business.facade.EsenzioniFacade;
import it.csi.esenred.esenpatweb.business.facade.NotificatoreFacade;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.EsenzioneAuraCittadinoEsenpat;
import it.csi.esenred.esenredweb.dto.Parametro;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.model.interfaces.AuditIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.dto.BatchNotificatore;
import it.csi.esenred.esenredweb.dto.Certificato;
import it.csi.esenred.esenredweb.dto.CertificatoCittadino;
import it.csi.esenred.esenredweb.dto.EsenzioneCittadinoEsenpat;
import it.csi.esenred.esenredweb.dto.EsenzioniResponse;
import it.csi.esenred.esenredweb.dto.MalattiaV2;
import it.csi.esenred.esenredweb.dto.MotivazioneAnnullamento;
import it.csi.esenred.esenredweb.dto.MotivazioneRevocaEsenpat;
import it.csi.esenred.esenredweb.dto.MotivazioniResponse;
import it.csi.esenred.esenredweb.dto.NotificaPatoRequest;
import it.csi.esenred.esenredweb.dto.NotificaPatoResponse;
import it.csi.esenred.esenredweb.dto.StatiTipologieResponse;
import it.csi.esenred.esenredweb.dto.StoricoEsenzione;
import it.csi.esenred.esenredweb.dto.TipologiaDocumento;
import it.csi.esenred.esenredweb.dto.TipologiaInvalidita;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.LogUtil;
import it.csi.esenred.esenredweb.util.RestUtils;

@Path("/rest/services/patologia")
public class EsenzioniEndpoint {

  //private final static Logger logger = Logger.getLogger(EsenzioniEndpoint.class.getName());

  private static final String CITTADINO_AURA = "CITTADINO_AURA";
  private static final String CITTADINO_NON_PIEMONTESE = "CITTADINO_NON_PIEMONTESE";
  private static final String AURA_ERRORE_CONNESSIONE = "AURA_ERRORE_CONNESSIONE";
  private static final String CITTADINO_DELEGATO = "CITTADINO_DELEGATO";
  private static final String CITTADINO_NON_DELEGATO = "CITTADINO_NON_DELEGATO";
  private static final String DELEGHE_ERRORE_CONNESSIONE = "DELEGHE_ERRORE_CONNESSIONE";
  private static String isTst;
  private static String ambiente;

  private ParametroIf parametroIf;

  @Autowired
  private AuditIf auditIf;

  @Autowired
  private MessaggioIf messaggioIf;

  @Autowired
  private EsenzioniFacade esenzioniFacade;

  @Autowired
  private ComuneIf comuneIf;

  @Autowired
  private CittadinoFacade cittadinoFacade;

  @Autowired
  private CertificatiFacade certificatiFacade;
  
  @Autowired
  private NotificatoreFacade notificatoreFacade;
  
  LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENPAT, null);

  @GET
  @Path("/getParametro/{codParametro}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getParametro(@PathParam("codParametro") String codParametro) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();

    try {
      List<Parametro> listparametriOut = new ArrayList<Parametro>();
      parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
      List<EsenredCParametri> elencoParametri = parametroIf.getParametri(codParametro);

      for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
        EsenredCParametri pDB = (EsenredCParametri) iterator.next();
        listparametriOut.add(new Parametro(pDB));
      }

      if (!listparametriOut.isEmpty()) {
        res.put(Constants.STATUS, Constants.OK);
        res.put(Constants.DATA, listparametriOut);
        res.put(Constants.CODE, Status.OK.getStatusCode());

        return corsedResponse.status(Status.OK).entity(res).build();
      }
      res.put(Constants.STATUS, Constants.OK);
      res.put(Constants.DATA, null);

      return corsedResponse.status(Status.OK).entity(res).build();
    } catch (Exception e) {

      res.put(Constants.STATUS, Constants.KO);
      res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.MESSAGE, e.getLocalizedMessage());

      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }


  
	@GET
    @Path("/cittadini/{cit_id}/esenzioni-aura")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getListaEsenzioniAura(
    		@Context HttpServletRequest httpRequest,
    		@PathParam("cit_id") String cit_id, 
    		@QueryParam("filter") String filters,
    		@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
    		@HeaderParam("X-Request-Id") String xRequestID,
    		@HeaderParam("X-Forwarded-For") String XForwardedFor,
    		@HeaderParam("X-Codice-Servizio") String xcodServizio)  {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	    Map<String, Object> res = new HashMap<String, Object>();
	    final String service = "Lista esenzioni AURA";
	    final String operation = "READ";
	    String methodName = "GET ListaEsenzioniAura";
	    log.info(methodName, "BEGIN");
	    List<EsenzioneAuraCittadinoEsenpat> result = null;
	    try {
	    	
	    	log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
	    	log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
	    	log.info(methodName, " HeaderParam cit_id: " + cit_id!=null? cit_id : "is null");
	    	log.info(methodName, " HeaderParam filter: " + filters!=null? filters : "is null");

	      //Tracciatura della chiamata
	      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

	      //Verifica presenza parametri obbligatori
	      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
	          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
	        res = new HashMap<String, Object>();
	        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
	        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
	            shibIdentitaCodiceFiscale);
	        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
	        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
	      }

	      isTst();
	  	  log.debug(methodName, " isTst: " + isTst);
	  	  //Verifica congruita' parametri in input
	      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
	        res = new HashMap<String, Object>();
	        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
	        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
	            shibIdentitaCodiceFiscale);
	        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());

	        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
	      }

	      //Recupero cittadino su AURA 
	      log.info(methodName, "Recupero cittadino su aura");
	      res = getCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

	      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
	        String msg12 = res.get("title").toString();
	        res = new HashMap<String, Object>();
	        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
	        log.tracciaRes(methodName, res);
	        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
	        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

	      } else if (res.containsKey(CITTADINO_AURA)) {
	    	Cittadino cittadino = (Cittadino) res.get(CITTADINO_AURA);
	        res = new HashMap<String, Object>();
	        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
	        result = esenzioniFacade.getListaEsenzioniAura(cittadino, filters);
	      }
	      //Tracciatura risposta
	      registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

	      return corsedResponse.status(Status.OK).entity(result).build();

	    } catch (Exception e) {
	      e.printStackTrace();
	      res = new HashMap<String, Object>();
	      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
	      res.put(Constants.CODE, "Errore generico");
	      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
	    }
    }
  
  @GET
  @Path("/cittadini/{cit_id}/esenzioni")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getListaEsenzioni(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id, @QueryParam("filter") String filters, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Codice-Servizio") String xcodServizio)
      throws Exception {
    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Lista esenzioni";
    final String operation = "READ";
    String methodName = "GET ListaEsenzioni";
    log.info(methodName, "BEGIN");
    List<EsenzioneCittadinoEsenpat> result = null;
    try {
    	
    	log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
		log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
    	log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
    	log.info(methodName, " HeaderParam cit_id: " + cit_id!=null? cit_id : "is null");
    	log.info(methodName, " HeaderParam filter: " + filters!=null? filters : "is null");

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
  	  log.debug(methodName, " isTst: " + isTst);
  	  //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      log.info(methodName, "verifica su aura del cf cittadino");
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        log.tracciaRes(methodName, res);
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {

        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {
        res = new HashMap<String, Object>();
        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        result = esenzioniFacade.getListaEsenzioni(cit_id, null, filters, shibIdentitaCodiceFiscale);
      }
      //Tracciatura risposta
      registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      return corsedResponse.status(Status.OK).entity(result).build();

    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

	@GET
    @Path("/cittadini/{cit_id}/esenzioni-aura/{cod_esenzione}/{cod_malattia}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getDettaglioEsenzioneAura(
    		@Context HttpServletRequest httpRequest,
    		@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
    		@HeaderParam("X-Request-Id") String xRequestID,
    		@HeaderParam("X-Forwarded-For") String XForwardedFor,
    		@HeaderParam("X-Codice-Servizio") String xcodServizio, 
    		@PathParam("cit_id") String cit_id, 
    		@PathParam("cod_esenzione") String cod_esenzione, 
    		@PathParam("cod_malattia") String cod_malattia,
    		@QueryParam("filter") String filters) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	    Map<String, Object> res = new HashMap<String, Object>();
	    final String service = "Dettaglio esenzione Aura";
	    final String operation = "READ";
	    EsenzioneAuraCittadinoEsenpat result = null;
	    
	    String methodName = "GET DettaglioEsenzioneAura";
	    log.info(methodName, "BEGIN");
	    
	    try {
	    	
	    	log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
	        log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
	        log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
	        log.info(methodName, " HeaderParam cit_id: " + cit_id !=null? cit_id : " is null");
	        log.info(methodName, " HeaderParam cod_esenzione: " + cod_esenzione !=null? cod_esenzione : " is null");
	        log.info(methodName, " HeaderParam cod_malattia: " + cod_malattia !=null? cod_malattia : " is null");
	        log.info(methodName, " HeaderParam filter: " + filters!=null? filters : "is null");

	      //Tracciatura della chiamata
	      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

	      //Verifica presenza parametri obbligatori
	      if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(cod_esenzione) || !Checker.isValorizzato(cod_malattia) || !Checker.isValorizzato(xRequestID)
	          || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(filters)) {
	        res = new HashMap<String, Object>();
	        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
	        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
	            shibIdentitaCodiceFiscale);
	        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
	        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
	      }

	      isTst();
	      log.debug(methodName, " isTst: " + isTst);
	      //Verifica congruita' parametri in input
	      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
	        res = new HashMap<String, Object>();
	        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
	        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
	            shibIdentitaCodiceFiscale);
	        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());

	        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
	      }

	      //Recupero cittadino su AURA 
	      log.info(methodName, "Recupero cittadino su aura");
	      res = getCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

	      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
	        String msg12 = res.get("title").toString();
	        res = new HashMap<String, Object>();
	        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
	        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
	        log.tracciaRes(methodName, res);
	        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

	      } else if (res.containsKey(CITTADINO_AURA)) {
	    	Cittadino cittadino = (Cittadino) res.get(CITTADINO_AURA);
	        res = new HashMap<String, Object>();
	        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
	        result = esenzioniFacade.getDettaglioEsenzioneAura(cittadino, cod_esenzione, cod_malattia, filters);
	      }
	      //Tracciatura risposta
	      registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

	      return corsedResponse.status(Status.OK).entity(result).build();

	    } catch (Exception e) {
	      e.printStackTrace();
	      res = new HashMap<String, Object>();
	      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
	      res.put(Constants.CODE, "Errore generico");
	      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
	    }
	}
    

  
  @GET
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDettaglioEsenzione(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id, @PathParam("esenzione_id") String esenzione_id,
      @HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
      @HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {
    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Dettaglio esenzione";
    final String operation = "READ";
    List<EsenzioneCittadinoEsenpat> result = null;
    
    String methodName = "GET DettaglioEsenzione";
    log.info(methodName, "BEGIN");
    
    try {
    	
    	log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
        log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
        log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
        log.info(methodName, " HeaderParam cit_id: " + cit_id !=null? cit_id : " is null");
        log.info(methodName, " HeaderParam esenzione_id: " + esenzione_id !=null? esenzione_id : " is null");	

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(esenzione_id) || !Checker.isValorizzato(xRequestID)
          || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      log.debug(methodName, " isTst: " + isTst);
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      //Verifica CF cittadino e residenza 
      log.info(methodName, "verifica su aura del cf cittadino");
      res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {

        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {
        res = new HashMap<String, Object>();
        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        result = esenzioniFacade.getListaEsenzioni(cit_id, esenzione_id, null, shibIdentitaCodiceFiscale);
      }
      //Tracciatura risposta
      registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      log.info(methodName, "END");
      return corsedResponse.status(Status.OK).entity(result.size() == 0 ? null : result.get(0)).build();

    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      log.tracciaRes(methodName, res);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  @GET
  @Path("/cittadini/{cit_id}/certificati")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getListaCertificati(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id, @QueryParam("filter") String filter, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Codice-Servizio") String xcodServizio)
      throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    List<CertificatoCittadino> listaCertificati = null;
    
    String methodName = "GET ListaCertificati";
    log.info(methodName, "BEGIN");

    log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
    log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
    log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
    log.info(methodName, " HeaderParam cit_id: " + cit_id !=null? cit_id : " is null");
    log.info(methodName, " HeaderParam esenzione_id: " + filter !=null? filter : " is null");	

    try {

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), "Lista certificati", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), "Lista certificati", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), "Lista certificati", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura("Lista certificati", XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), "Lista certificati", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), "Lista certificati", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {

        res = new HashMap<String, Object>();

        //Ricerca lista esenzioni-certificati
        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
        listaCertificati = certificatiFacade.getListaCertificati(cit_id, filter);

        //Tracciatura risposta
        registraSuAudit(XForwardedFor, "1 ok", "Lista certificati", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      }
      
      log.info(methodName, "END");
      return corsedResponse.status(Status.OK).entity(listaCertificati).build();

    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }

  }

  @GET
  @Path("/cittadini/{cit_id}/certificati/{certificato_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDettaglioCertificato(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id, @PathParam("certificato_id") String certificato_id,
      @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
      @HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    CertificatoCittadino dettaglioCertificato = null;

    String methodName = "GET Info";
    log.info(methodName, "BEGIN");

    try {

      log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
      log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
      log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
      log.info(methodName, " HeaderParam cit_id: " + cit_id !=null? cit_id : " is null");
      log.info(methodName, " HeaderParam certificato_id: " + certificato_id !=null? certificato_id : " is null");	
      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), "Dettaglio certificato", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(certificato_id)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(),
						"Dettaglio certificato", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)
          || !Checker.isNumericString(certificato_id)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(),
						"Dettaglio certificato", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura("Dettaglio certificato", XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), "Dettaglio certificato", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), "Dettaglio certificato", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {

        res = new HashMap<String, Object>();

        //Ricerca dettaglio certificato
        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
        dettaglioCertificato = certificatiFacade.getDettaglioCertificato(cit_id, certificato_id);

        //Tracciatura risposta
        registraSuAudit(XForwardedFor, "1 ok", "Dettaglio certificato", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      }
      log.info(methodName, "END");
      return corsedResponse.status(Status.OK).entity(dettaglioCertificato).build();

    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      log.tracciaRes(methodName, res);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  protected EsenredCMessaggi getMessaggio(String msg) {
    try {
      messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
      return messaggioIf.getMessaggio(msg);
    } catch (Exception e) {
      e.printStackTrace();
    }
    EsenredCMessaggi esenredCMessaggi = new EsenredCMessaggi();
    esenredCMessaggi.setCodice(msg);
    esenredCMessaggi.setTesto("Errore non anagrafato sul DB");
    return new EsenredCMessaggi();
  }

  protected String getAmbiente() {
    //checkAmbiente
    if (ambiente == null) {
      List<Parametro> listparametriOut = new ArrayList<Parametro>();
      parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
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

  protected String isTst() {
    //checkAmbiente
    if (isTst == null) {
      List<Parametro> listparametriOut = new ArrayList<Parametro>();
      parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
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

  protected void registraSuAudit(String ipAddress, String keyOper, String oggOper, String operazione, String utente, String idRequest, String uuId, String xcodServizio, String utenteBen,
      String utenteDel) {
    try {
      getAmbiente();
      String appId = "ESENZIONI_RP-01_TEST_ESENPAT";
      if (ambiente != null) {
        appId = "ESENZIONI_RP-01_" + ambiente + "_ESENPAT";
      }

      auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
      if (utenteDel.equalsIgnoreCase(utenteBen))
        utenteDel = null;
      CsiLogAudit audit = new CsiLogAudit(appId, ipAddress, keyOper, oggOper, operazione, utente, idRequest, uuId, xcodServizio, utenteBen, utenteDel);
			auditIf.insertAuditEsenpat(audit);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected Map<String, Object> verificaCittadinoSuAura(String operazione, String req, String codiceFiscale, String shib, String idRequest, String uuId, String xcodServizio) {
    Map<String, Object> res = new LinkedHashMap<String, Object>();
    
    String methodName = "verificaCittadinoSuAura";
    log.info(methodName, "BEGIN");

    try {
      registraSuAudit(req, "AURA FIND", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

      List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
      if (datiCittadinoshib.isEmpty()) {

        String messaggio143 = getMessaggio(Constants.MSG143).getTesto();

        res.put("code", CITTADINO_NON_PIEMONTESE);
        res.put("title", messaggio143);
        
        log.tracciaRes(methodName, res);

        registraSuAudit(req, "400 " + messaggio143, operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

      } else {

    	registraSuAudit(req, "AURA GET", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

        //cittadino trovato su AURA
				Cittadino cittadino = IntegrationClientImpl.getInstance()
						.getCittadinoEsenpat(datiCittadinoshib.get(0).getIdAura());

        if (isCittadinoPiemontese(cittadino)) {

          log.info(methodName,"Cittadino trovato piemontese");
          res.put(CITTADINO_AURA, cittadino);
          registraSuAudit(req, "1 AURA GET OK", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

        } else {

          log.info(methodName,"Cittadino trovato NON piemontese");	
          String messaggio143 = getMessaggio(Constants.MSG143).getTesto();
          res.put("code", CITTADINO_NON_PIEMONTESE);
          res.put("title", messaggio143);
          
          log.tracciaRes(methodName, res);
          
          registraSuAudit(req, "400 " + messaggio143, operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

        }

      }

      
      log.info(methodName, "END");
      return res;

    } catch (Exception e) {
      e.printStackTrace();
      String messaggio012 = getMessaggio(Constants.MSG012).getTesto();
      res.put("code", AURA_ERRORE_CONNESSIONE);
      res.put("title", messaggio012);
      return res;
    }
  }
  
  protected Map<String, Object> getCittadinoSuAura(String operazione, String req, String codiceFiscale, String shib, String idRequest, String uuId, String xcodServizio) {
	    Map<String, Object> res = new LinkedHashMap<String, Object>();
	    
	    String methodName = "getCittadinoSuAura";
	    log.info(methodName, "BEGIN");

	    try {
	      registraSuAudit(req, "AURA FIND", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

	      List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
	      if (datiCittadinoshib.isEmpty()) {
	        log.info(methodName, "Cittadino non trovato su AURA");
	        throw new Exception();
	      } else {

	    	registraSuAudit(req, "AURA GET", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);

	        //cittadino trovato su AURA
			Cittadino cittadino = IntegrationClientImpl.getInstance().getCittadinoEsenpat(datiCittadinoshib.get(0).getIdAura());

			log.info(methodName,"Cittadino trovato su AURA");
			res.put(CITTADINO_AURA, cittadino);
			registraSuAudit(req, "1 AURA GET OK", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);
	      }

	      log.info(methodName, "END");
	      return res;

	    } catch (Exception e) {
	      e.printStackTrace();
	      String messaggio012 = getMessaggio(Constants.MSG012).getTesto();
	      res.put("code", AURA_ERRORE_CONNESSIONE);
	      res.put("title", messaggio012);
	      return res;
	    }
	  }

	protected Map<String, Object> verificaDelegatoSuAura(String shib) {
		Map<String, Object> res = new LinkedHashMap<String, Object>();

		try {
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(shib));
			if (datiCittadinoshib.isEmpty()) {
				throw new Exception();
			} else {

				
				Cittadino cittadino = IntegrationClientImpl.getInstance()
						.getCittadinoEsenpat(datiCittadinoshib.get(0).getIdAura());
				if (cittadino != null)
					res.put(CITTADINO_AURA, cittadino);
				else
					throw new Exception();
			}

			return res;

		} catch (Exception e) {
			e.printStackTrace();
			String messaggio012 = getMessaggio(Constants.MSG012).getTesto();
			res.put("code", AURA_ERRORE_CONNESSIONE);
			res.put("title", messaggio012);
			return res;
		}
	}

  protected boolean isCittadinoPiemontese(Cittadino cittadino) {
    comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
    List<EsenredCComuni> elencoComuni = comuneIf.getElencoComuni(cittadino.getCittaResidenza());
    if (elencoComuni.isEmpty()) {
      return false;
    } else {
      return elencoComuni.get(0).getDenominazioneRegione().equals("PIEMONTE");
    }
  }

  @POST
  @Path("/cittadini/{cit_id}/esenzioni")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response setRichiestaEsenzione(@Context HttpServletRequest req, @HeaderParam("X-Request-Id") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
      @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id, Certificato certificato)
      throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    res = new HashMap<String, Object>();

    try {
      // tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), "Inserimento Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), "Inserisci Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);

        res.put(Constants.STATUS, Status.BAD_REQUEST.getStatusCode());
        res.put(Constants.CODE, "Parametri obbligatori mancanti");
        res.put(Constants.TITLE, messaggio145.getTesto());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();

      //Verifica congruita parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), "Inserisci Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);

        res.put(Constants.STATUS, Status.BAD_REQUEST.getStatusCode());
        res.put(Constants.CODE, "Mancata congruita parametri di input");
        res.put(Constants.TITLE, messaggio149.getTesto());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura("Inserimento Richiesta", req.getRemoteAddr(), cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
        res.put(Constants.CODE, "Mancata connessione ad aura");
        res.put(Constants.TITLE, msg12);
        registraSuAudit(XForwardedFor, res.get("title").toString(), "Inserisci Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();
      } else if (res.get(Constants.CODE) == CITTADINO_NON_PIEMONTESE) {
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res.put(Constants.STATUS, Status.BAD_REQUEST.getStatusCode());
        res.put(Constants.CODE, "Verifica del CF negativa");
        res.put(Constants.TITLE, msg143);
        registraSuAudit(XForwardedFor, res.get("title").toString(), "Inserisci Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      } else if (res.containsKey(CITTADINO_AURA)) {

        Cittadino cittadino = null;
        
        cittadino = (Cittadino) res.get(CITTADINO_AURA);
        cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");

				
				if (!cit_id.equalsIgnoreCase(shibIdentitaCodiceFiscale)) {
				Cittadino delegato = null;
					EsenzioneTCittadino del = cittadinoFacade.getCittadinoEsenpat(shibIdentitaCodiceFiscale);
				if (del == null) {
						
						Map<String, Object> delRes = new HashMap<String, Object>();
						delRes = verificaDelegatoSuAura(shibIdentitaCodiceFiscale);

						if (delRes.get("code") == AURA_ERRORE_CONNESSIONE) {
							String msg12 = delRes.get("title").toString();
							delRes = new HashMap<String, Object>();
							delRes.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
							delRes.put(Constants.CODE, "Mancata connessione ad aura");
							delRes.put(Constants.TITLE, msg12);
							return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(delRes).build();
						} else if (delRes.containsKey(CITTADINO_AURA)) {
							delegato = (Cittadino) delRes.get(CITTADINO_AURA);
							cittadinoFacade.insertOrUpdateCittadino(shibIdentitaCodiceFiscale,
									shibIdentitaCodiceFiscale, delegato);
					}
				}
				}
				

		cittadinoFacade.insertOrUpdateCittadino(shibIdentitaCodiceFiscale, cit_id, cittadino);

        //inizio creazione nuova pratica
        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");

        EsenzioneCittadinoEsenpat ec = esenzioniFacade.insertNuovaEsenzioneCittadino(shibIdentitaCodiceFiscale, cit_id, cittadino, certificato);

        if (ec != null) {
          EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneReloaded = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.decode(ec.getId().toString()));
          ec = new EsenzioneCittadinoEsenpat(esenzioneTPraticaEsenzioneReloaded);
          
          try {
        	  callNotificatore("setRichiestaEsenzione", (Cittadino)res.get(CITTADINO_AURA), ec, cit_id, shibIdentitaCodiceFiscale);
          } catch (Exception e) {
        	 e.printStackTrace();
						registraSuAudit(XForwardedFor, "ko", "Inserisci Richiesta", "NOTIFICATORE KO",
								shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
								shibIdentitaCodiceFiscale);
          }

          registraSuAudit(XForwardedFor, "ok", "Inserisci Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

          return corsedResponse.status(Status.CREATED).entity(ec).build();
        }

      }
      //sistemare ELSEIF CITTADINO_AURA
      return null;
    } catch (EsenpatException e) {
      e.printStackTrace();
      EsenredCMessaggi messaggio = getMessaggio(e.getCodMessaggio());
      res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());

      registraSuAudit(XForwardedFor, e.getStatus() + " " + messaggio.getTesto(), "Inserisci Richiesta", "INSERT", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
          shibIdentitaCodiceFiscale);

      return corsedResponse.status(Status.CONFLICT).entity(res).build();
    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.MESSAGE, e.getLocalizedMessage());
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  @PUT
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/annullamento")
  @Produces(MediaType.APPLICATION_JSON)
  public Response setAnnullamentoEsenzione(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
      @HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String citId,
      @PathParam("esenzione_id") String esenzioneId, MotivazioneAnnullamento motivazioneAnnullamento) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Annullamento esenzione";
    final String operation = "UPDATE";
    try {

      //Tracciatura della chiamata al servizio REVOCA
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(citId) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(citId, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura(service, XForwardedFor, citId, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();
      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      } else if (res.containsKey(CITTADINO_AURA)) {

				
				if (!citId.equalsIgnoreCase(shibIdentitaCodiceFiscale)) {
					Cittadino delegato = null;
					cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
					EsenzioneTCittadino del = cittadinoFacade.getCittadinoEsenpat(shibIdentitaCodiceFiscale);
					if (del == null) {
						
						Map<String, Object> delRes = new HashMap<String, Object>();
						delRes = verificaDelegatoSuAura(shibIdentitaCodiceFiscale);

						if (delRes.get("code") == AURA_ERRORE_CONNESSIONE) {
							String msg12 = delRes.get("title").toString();
							delRes = new HashMap<String, Object>();
							delRes.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
							delRes.put(Constants.CODE, "Mancata connessione ad aura");
							delRes.put(Constants.TITLE, msg12);
							return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(delRes).build();
						} else if (delRes.containsKey(CITTADINO_AURA)) {
							delegato = (Cittadino) delRes.get(CITTADINO_AURA);
							cittadinoFacade.insertOrUpdateCittadino(shibIdentitaCodiceFiscale,
									shibIdentitaCodiceFiscale, delegato);
						}
					}
				}
				

        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        EsenzioneCittadinoEsenpat ec = esenzioniFacade.annullaEsenzione(esenzioneId, motivazioneAnnullamento, shibIdentitaCodiceFiscale, citId);

        try {
        	callNotificatore("setAnnullamentoEsenzione", (Cittadino)res.get(CITTADINO_AURA), ec, citId, shibIdentitaCodiceFiscale);
        } catch (Exception e) {
        	e.printStackTrace();
        	registraSuAudit(XForwardedFor, "ko", service, "NTOIFICATORE KO", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
        }

        if (ec != null) {
        	//Tracciatura risposta
        	registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
          return corsedResponse.status(Status.OK).entity(ec).build();
        }
      }
      return corsedResponse.status(Status.OK).entity(res).build();
    } catch (EsenpatException e) {
      e.printStackTrace();
      EsenredCMessaggi messaggio = getMessaggio(e.getCodMessaggio());
      res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());
      registraSuAudit(XForwardedFor, e.getStatus() + " " + messaggio.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
      return corsedResponse.status(Status.CONFLICT).entity(res).build();
    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  @PUT
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/rinnovo")
  @Produces(MediaType.APPLICATION_JSON)
  public Response setRinnovoEsenzione(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id, @PathParam("esenzione_id") String esenzione_id,
      Certificato certificato) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Rinnovo esenzione";
    final String operation = "UPDATE";
    EsenzioneCittadinoEsenpat esenzione = null;
    String methodName = "PUT RinnovoEsenzione";
    log.info(methodName, "BEGIN");
    	
    try {
    	
    	log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
        log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
        log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
        log.info(methodName, " HeaderParam cit_id: " + cit_id !=null? cit_id : " is null");
        log.info(methodName, " HeaderParam esenzione_id: " + esenzione_id !=null? esenzione_id : " is null");	

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica campi obbligatori
      if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(esenzione_id) || !Checker.isValorizzato(xRequestID)
          || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio) || certificato == null) {

        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);

        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);

        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {

        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {
        Cittadino citBen = (Cittadino) res.get(CITTADINO_AURA);

			
				if (!cit_id.equalsIgnoreCase(shibIdentitaCodiceFiscale)) {
					Cittadino delegato = null;
					cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
					EsenzioneTCittadino del = cittadinoFacade.getCittadinoEsenpat(shibIdentitaCodiceFiscale);
					if (del == null) {
						
						Map<String, Object> delRes = new HashMap<String, Object>();
						delRes = verificaDelegatoSuAura(shibIdentitaCodiceFiscale);

						if (delRes.get("code") == AURA_ERRORE_CONNESSIONE) {
							String msg12 = delRes.get("title").toString();
							delRes = new HashMap<String, Object>();
							delRes.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
							delRes.put(Constants.CODE, "Mancata connessione ad aura");
							delRes.put(Constants.TITLE, msg12);
							log.tracciaRes(methodName, res);
							return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(delRes).build();
						} else if (delRes.containsKey(CITTADINO_AURA)) {
							delegato = (Cittadino) delRes.get(CITTADINO_AURA);
							cittadinoFacade.insertOrUpdateCittadino(shibIdentitaCodiceFiscale,
									shibIdentitaCodiceFiscale, delegato);
						}
					}
				}
				

        res = new HashMap<String, Object>();
        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.valueOf(esenzione_id));
        if (esenzioneTPraticaEsenzione != null
            && (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_SCADUTA)
                || esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA_IN_SCADENZA))
            && esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario().equals(cit_id)) {
          esenzione = esenzioniFacade.setRinnovoEsenzione(esenzioneTPraticaEsenzione, shibIdentitaCodiceFiscale, cit_id, esenzione_id, citBen, certificato);
         
          EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneReloaded = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzione.getId().toString()));
          esenzione = new EsenzioneCittadinoEsenpat(esenzioneTPraticaEsenzioneReloaded);
         
          
          try {
        	  callNotificatore("setRinnovoEsenzione", citBen, esenzione, cit_id, shibIdentitaCodiceFiscale);
          } catch (Exception e) {
        	 log.error(methodName, "Call notificatore KO");
        	 e.printStackTrace();
        	 registraSuAudit(XForwardedFor, "ko", service, "NTOIFICATORE KO", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
          }
          
        } else {
          log.error(methodName, "Errore generico");	
          throw new Exception();
        }
      }
      //Tracciatura risposta
      registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      log.info(methodName, "END");
      return corsedResponse.status(Status.OK).entity(esenzione).build();

    } catch (EsenpatException e) {
      EsenredCMessaggi messaggio = getMessaggio(e.getCodMessaggio());
      res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());
      registraSuAudit(XForwardedFor, e.getStatus() + " " + messaggio.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      log.tracciaRes(methodName, res);
      return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      log.tracciaRes(methodName, res);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  @PUT
  @Path("/inserisciNotificaPato")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserisciNotificaPato(@Context HttpServletRequest req, NotificaPatoRequest notificaPatoRequest) throws Exception {

	  NotificaPatoResponse response = new NotificaPatoResponse();
	  Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	  final String service = "Inserisci notifica pato";
	  final String operation = "UPDATE";
	  
	  String methodName = "PUT inserisciNotificaPato";
	  log.info(methodName, "BEGIN");
	  try {
		  //Tracciatura della chiamata
		  registraSuAuditAuraRp(req.getRemoteAddr(), "1 " + Status.OK.getReasonPhrase(), service, operation, notificaPatoRequest.getTipoNotifica(), null, null, null, notificaPatoRequest.getTipoNotifica(), null);

		  //verifica campi obbligatori
		  if ((notificaPatoRequest == null) || !Checker.isValorizzato(notificaPatoRequest.getIdAura()) || !Checker.isValorizzato(notificaPatoRequest.getCodiceEsenzione()) || 
				  !Checker.isValorizzato(notificaPatoRequest.getDataInizio()) || !Checker.isValorizzato(notificaPatoRequest.getTipoNotifica()) || 
				  !Checker.isValorizzato(notificaPatoRequest.getCodiceAttestatoEsenzione())) {

			  EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
			  registraSuAuditAuraRp(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, notificaPatoRequest.getTipoNotifica(), null, null, null, 
					  notificaPatoRequest.getTipoNotifica(), null); 

			  response.setCodiceRitorno(Constants.KO);
			  response.setDescrizione("Parametri obbligatori non presenti");
			  log.error(methodName, messaggio145.getCodice() + " Parametri obbligatori non presenti");
			  return corsedResponse.status(Status.BAD_REQUEST).entity(response).build();
		  }
		  isTst();
		  //Verifica congruita parametri in input
		  if (!Checker.isCodiceFiscale(notificaPatoRequest.getTipoNotifica(), isTst) || 
				  !Checker.isData(notificaPatoRequest.getDataInizio(), "dd/MM/yyyy", Locale.ITALIAN)) 
		  {
			  EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
			  registraSuAuditAuraRp(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, notificaPatoRequest.getTipoNotifica(), null, 
					  null, null, notificaPatoRequest.getTipoNotifica(), null);

			  response.setCodiceRitorno(Constants.KO);
			  response.setDescrizione("Mancata congruita\' dei dati di input");
			  log.error(methodName, messaggio149.getCodice() + "Mancata congruita\' dei dati di input [verificare codiceFiscale e/o data inizio]");
			  return corsedResponse.status(Status.BAD_REQUEST).entity(response).build();
		  }
		  if (notificaPatoRequest.getDataFine()!=null){
			  if (!Checker.isData(notificaPatoRequest.getDataFine(), "dd/MM/yyyy", Locale.ITALIAN)) {
				  EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				  registraSuAuditAuraRp(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, notificaPatoRequest.getTipoNotifica(), null, 
						  null, null, notificaPatoRequest.getTipoNotifica(), null);

				  response.setCodiceRitorno(Constants.KO);
				  response.setDescrizione("Mancata congruita\' dei dati di input");
				  log.error(methodName, messaggio149.getCodice() + " Mancata congruita\' dei dati di input [datafine]");
				  return corsedResponse.status(Status.BAD_REQUEST).entity(response).build();
			  }
		  }
						  
		  esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");

		  EsenzioneDEsenzione esenzioneDEsenzione = null;
		  try {
			  //verifica codice esenzione esistente nella base dati 
			  esenzioneDEsenzione = esenzioniFacade.getEsenzioneDEsenzione(notificaPatoRequest.getCodiceEsenzione());
		  }
		  catch(NoResultException noRes) {
			  EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
			  registraSuAuditAuraRp(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, notificaPatoRequest.getTipoNotifica(), null, 
					  null, null, notificaPatoRequest.getTipoNotifica(), null);

			  response.setCodiceRitorno(Constants.KO);
			  response.setDescrizione("Mancata congruita\' dei dati di input");
			  log.error(methodName, messaggio149.getCodice() + " Mancata congruita\' dei dati di input [codice esenzione non trovato]");
			  return corsedResponse.status(Status.BAD_REQUEST).entity(response).build();
		  }

		  //ricerca ultima pratica esenzione valida per beneficiario e codice esenzione 
		  EsenzioneTPraticaEsenzione praticaEsenzioneValidaPiuRecente = esenzioniFacade.getPraticaEsenzioneValidaByEsenzione(
				  notificaPatoRequest.getTipoNotifica(), esenzioneDEsenzione, Constants.STATO_PRATICA_VALIDATA);
		  if (praticaEsenzioneValidaPiuRecente==null) {
			  //gestione errore 404
			  EsenredCMessaggi messaggio005 = getMessaggio(Constants.MSG005);
			  registraSuAuditAuraRp(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " " + messaggio005.getTesto(), service, operation, notificaPatoRequest.getTipoNotifica(), null, 
					  null, null, notificaPatoRequest.getTipoNotifica(), null);

			  response.setCodiceRitorno(Constants.KO);
			  response.setDescrizione("Nessuna pratica esenzione trovata");
			  log.error(methodName, messaggio005.getCodice() + " Nessuna pratica esenzione trovata");
			  return corsedResponse.status(Status.NOT_FOUND).entity(response).build();
		  }

		 
		  EsenzioneTPraticaEsenzione praticaEsenzioneValidaPiuRecenteFull = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(praticaEsenzioneValidaPiuRecente.getSkPraticaEsenzione());
		  Set <EsenzioneRPraticaEsenzioneDocumento> elencoDocumentiPratica = praticaEsenzioneValidaPiuRecenteFull.getEsenzioneRPraticaEsenzioneDocumentos();
		  if (elencoDocumentiPratica==null) {
			  //gestione errore 404
			  EsenredCMessaggi messaggio005 = getMessaggio(Constants.MSG005);
			  registraSuAuditAuraRp(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " " + messaggio005.getTesto(), service, operation, 
					  notificaPatoRequest.getTipoNotifica(), null, null, null, notificaPatoRequest.getTipoNotifica(), null);

			  response.setCodiceRitorno(Constants.KO);
			  response.setDescrizione("Nessun documento pratica trovato");
			  log.error(methodName, messaggio005.getCodice() + " Nessun documento pratica trovato");
			  return corsedResponse.status(Status.NOT_FOUND).entity(response).build();
		  }else {
			  Iterator<EsenzioneRPraticaEsenzioneDocumento> iterator = elencoDocumentiPratica.iterator();
			  EsenzioneTDocumento documento;
			  EsenzioneTDocumento esenzioneTDocumentoDaValidare=null;
			  while (iterator.hasNext()) {
				  documento = iterator.next().getEsenzioneTDocumento();
				  if (documento.getEsenzioneDDocumentoTipo().getCodDocumentoTipo().equals(Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE)) {//"aes" = ATTESTATO DI ESENZIONE
					  esenzioneTDocumentoDaValidare = documento;
					  break;
				  }
			  }
			  if (esenzioneTDocumentoDaValidare!=null) {
				  //validazione attestato esenzione
				  esenzioniFacade.setValidazioneAttestatoEsenzione(esenzioneTDocumentoDaValidare, notificaPatoRequest.getCodiceAttestatoEsenzione());
				  
				  registraSuAuditAuraRp(req.getRemoteAddr(), "1 ok", service, operation, 
						  notificaPatoRequest.getTipoNotifica(), null, null, null, notificaPatoRequest.getTipoNotifica(), null);
				  response.setCodiceRitorno(Constants.OK);
				  response.setDescrizione(Constants.OK);
			  }   
		  }

		  log.info(methodName, "END");
		  return corsedResponse.status(Status.OK).entity(response).build();

	  } catch (Exception e) {
		  e.printStackTrace();
		  registraSuAuditAuraRp(req.getRemoteAddr(), Constants.KO + " " + Status.INTERNAL_SERVER_ERROR.getStatusCode(), service, operation, notificaPatoRequest.getTipoNotifica(), null, null, null, notificaPatoRequest.getTipoNotifica(), null);			  

		  response.setCodiceRitorno(Constants.KO);
		  response.setDescrizione("Errore generico");
		  log.error(methodName, "Errore generico");
		  return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
	  }
  }

  /*
   * Download dell'attestato di esenzione correlato alla pratica di esenzione piu' recente del cittadino 
   */
  @GET
  @Path("/cittadini/{cit_id}/esenzioni/pdf")
  @Produces("application/pdf")
  public Response getAttestatoEsenzione(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    
    String fileName = null;
    File filePdf = null;

    try {
      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
    	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);

        res.put("status", Status.BAD_REQUEST.getStatusCode());
        res.put("code", "Parametri obbligatori mancanti");
        res.put("title", messaggio145.getTesto());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
    	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res.put("status", Status.BAD_REQUEST.getStatusCode());
        res.put("code", "Mancata congruita\' parametri di input");
        res.put("title", messaggio149.getTesto());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura("Attestato esenzione", XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
    	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        EsenredCMessaggi msg012 = getMessaggio(Constants.MSG012);
        res = new HashMap<String, Object>();
        res.put("status", Status.SERVICE_UNAVAILABLE.getStatusCode());
        res.put("code", "Mancata connessione ad AURA");
        res.put("title", msg012.getTesto());
        registraSuAudit(XForwardedFor, "503 " + msg012.getTesto(), "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
    	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        EsenredCMessaggi msg143 = getMessaggio(Constants.MSG143);
        res = new HashMap<String, Object>();
        res.put("status", Status.BAD_REQUEST.getStatusCode());
        res.put("code", "Verifica del CF negativa");
        res.put("title", msg143);
        registraSuAudit(XForwardedFor, "400 " + msg143.getTesto(), "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build(); 
      } else if (res.containsKey(CITTADINO_AURA)) {
        res = new HashMap<String, Object>();

        //Ricerca attestato esenzione
        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
        EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = certificatiFacade.getAttestatoEsenzione(cit_id);

        if (esenzioneTRepositoryDocumentale == null) {
          corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
          //gestione errore 404
          res = new HashMap<String, Object>();
          EsenredCMessaggi messaggio005 = getMessaggio(Constants.MSG005);
          registraSuAudit(XForwardedFor, Status.NOT_FOUND.getStatusCode() + " " + messaggio005.getTesto(), "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
              cit_id, shibIdentitaCodiceFiscale);
          res.put("status", Status.NOT_FOUND.getStatusCode());
          res.put("code", "Attestato non trovato");
          res.put("title", messaggio005.getTesto());

          return corsedResponse.status(Status.NOT_FOUND).entity(res).build();

        }
        
        byte[] byteArray = Base64.getDecoder().decode(esenzioneTRepositoryDocumentale.getFile());
        fileName = Constants.PREFISSO_NOME_FILE_ATTESTATO_ESENZIONE + "_" + esenzioneTRepositoryDocumentale.getSkRepository() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        filePdf = File.createTempFile(fileName, ".pdf");
				filePdf.deleteOnExit();
        FileUtils.writeByteArrayToFile(filePdf, byteArray);
      }
      //return corsedResponse.status(Status.OK).entity(attestatoEsenzione).build();
      if (filePdf == null) {
    	  corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
    	  throw new Exception();
      }
      //Tracciatura risposta
      registraSuAudit(XForwardedFor, "1 ok", "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      return corsedResponse.header("Content-Disposition", "attachment; filename=" + filePdf.getName()).status(Status.OK).entity(filePdf).build();
      
    } catch (Exception e) {
      corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      //Tracciatura risposta
      registraSuAudit(XForwardedFor, "500 Errore generico", "Attestato esenzione", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  @PUT
  @Path("/cittadini/{cit_id}/esenzioni-aura/{cod_esenzione}/{cod_malattia}/revoca")
  @Produces(MediaType.APPLICATION_JSON)
  public Response setRevocaEsenzioneAura(
		@Context HttpServletRequest httpRequest,
  		@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
  		@HeaderParam("X-Request-Id") String xRequestID,
  		@HeaderParam("X-Forwarded-For") String XForwardedFor,
  		@HeaderParam("X-Codice-Servizio") String xcodServizio, 
  		@PathParam("cit_id") String cit_id, 
  		@PathParam("cod_esenzione") String cod_esenzione, 
  		@PathParam("cod_malattia") String cod_malattia,
  		@QueryParam("filter") String filters) throws Exception {
	    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	    Map<String, Object> res = new HashMap<String, Object>();
	    final String service = "Revoca esenzione Aura";
	    final String operation = "UPDATE";
	    
	    String methodName = "PUT RevocaEsenzioneAura";
	    log.info(methodName, "BEGIN");
	    try {

	      log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
		  log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
	      log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
	      log.info(methodName, " HeaderParam citId: " + cit_id!=null? cit_id : "is null");
	      log.info(methodName, " HeaderParam cod_esenzione: " + cod_esenzione!=null? cod_esenzione : "is null");
	      log.info(methodName, " HeaderParam cod_malattia: " + cod_malattia!=null? cod_malattia : "is null");
	      log.info(methodName, " HeaderParam filter: " + filters!=null? filters : "is null");
	      
	      //Tracciatura della chiamata al servizio REVOCA
	      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

	      //Verifica presenza parametri obbligatori
	      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
	          || !Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cod_esenzione) || !Checker.isValorizzato(cod_malattia) || !Checker.isValorizzato(filters)) {
	        res = new HashMap<String, Object>();
	        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
	        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
	            shibIdentitaCodiceFiscale);
	        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
	        log.tracciaRes(methodName, res);
	        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
	      }

	      isTst();
	      log.debug(methodName, " isTst: " + isTst);
	      //Verifica congruita' parametri in input
	      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
	        res = new HashMap<String, Object>();
	        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
	        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
	            shibIdentitaCodiceFiscale);
	        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruità parametri di input", Status.BAD_REQUEST.getStatusCode());
	        log.tracciaRes(methodName, res);
	        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
	      }
	      //Recupero cittadino su AURA 
	      log.info(methodName, "Recupero cittadino su aura");
	      res = getCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

	      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
	        String msg12 = res.get("title").toString();
	        res = new HashMap<String, Object>();
	        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
	        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
	        log.tracciaRes(methodName, res);
	        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();
	      } else if (res.containsKey(CITTADINO_AURA)) {
	    	  	Cittadino cittadino = (Cittadino) res.get(CITTADINO_AURA);

				
				registraSuAudit(XForwardedFor, "AURA EsenzionePatologia", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
		        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
		        EsenzioneAuraCittadinoEsenpat ec = esenzioniFacade.revocaEsenzioneAura(cod_esenzione, cod_malattia, shibIdentitaCodiceFiscale, cittadino, filters);
				registraSuAudit(XForwardedFor, "1 AURA EsenzionePatologia OK", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
		        log.info(methodName, "Revoca esenzione Aura OK");

		        if (ec != null) {
		          //Tracciatura risposta
		          registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
		          log.info(methodName, "END");
		          return corsedResponse.status(Status.CREATED).entity(ec).build();
		        }
	      }
	      
	      log.info(methodName, "END");
	      return corsedResponse.status(Status.OK).entity(res).build();
	    } catch (EsenpatException e) {
	      e.printStackTrace();
	      EsenredCMessaggi messaggio = getMessaggio(e.getCodMessaggio());
	      res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());
	      registraSuAudit(XForwardedFor, e.getStatus() + " " + messaggio.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
	      log.tracciaRes(methodName, res);
	      return corsedResponse.status(Status.CONFLICT).entity(res).build();
	    } catch (Exception e) {
	      e.printStackTrace();
	      res = new HashMap<String, Object>();
	      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
	      res.put(Constants.CODE, "Errore generico");
	      log.tracciaRes(methodName, res);
	      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
	    }
  }
  
  @PUT
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/revoca")
  @Produces(MediaType.APPLICATION_JSON)
  public Response setRevocaEsenzione(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String citId, @PathParam("esenzione_id") String esenzioneId,
      MotivazioneRevocaEsenpat motivazioneRevoca) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Revoca esenzione";
    final String operation = "UPDATE";
    
    String methodName = "PUT Revoca esenzione";
    log.info(methodName, "BEGIN");
    try {

      log.info(methodName, " HeaderParam X-Request-ID: " + xRequestID);
	  log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodServizio);
      log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shibIdentitaCodiceFiscale);
      log.info(methodName, " HeaderParam citId: " + citId!=null? citId : "is null");
      log.info(methodName, " HeaderParam esenzioneId: " + esenzioneId!=null? esenzioneId : "is null");	
      //Tracciatura della chiamata al servizio REVOCA
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(citId) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      log.debug(methodName, " isTst: " + isTst);
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(citId, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruità parametri di input", Status.BAD_REQUEST.getStatusCode());
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      log.info(methodName, " verifico cittadino su aura");
      res = verificaCittadinoSuAura(service, XForwardedFor, citId, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();
      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
        log.tracciaRes(methodName, res);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      } else if (res.containsKey(CITTADINO_AURA)) {

    	  		log.info(methodName, "Verifico se il cittadino e' un delegato");
				
				if (!citId.equalsIgnoreCase(shibIdentitaCodiceFiscale)) {
					Cittadino delegato = null;
					cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
					EsenzioneTCittadino del = cittadinoFacade.getCittadinoEsenpat(shibIdentitaCodiceFiscale);
					if (del == null) {
						
						Map<String, Object> delRes = new HashMap<String, Object>();
						delRes = verificaDelegatoSuAura(shibIdentitaCodiceFiscale);

						if (delRes.get("code") == AURA_ERRORE_CONNESSIONE) {
							String msg12 = delRes.get("title").toString();
							delRes = new HashMap<String, Object>();
							delRes.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
							delRes.put(Constants.CODE, "Mancata connessione ad aura");
							delRes.put(Constants.TITLE, msg12);
							log.tracciaRes(methodName, delRes);
							return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(delRes).build();
						} else if (delRes.containsKey(CITTADINO_AURA)) {
							log.info(methodName, "Verifica cittadino su Aura OK");
							delegato = (Cittadino) delRes.get(CITTADINO_AURA);
							log.info(methodName, "Aggiorno cittadino / delegato sul db");
							cittadinoFacade.insertOrUpdateCittadino(shibIdentitaCodiceFiscale,
									shibIdentitaCodiceFiscale, delegato);
						}
					}
				}
				

        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        EsenzioneCittadinoEsenpat ec = esenzioniFacade.revocaEsenzione(esenzioneId, motivazioneRevoca, shibIdentitaCodiceFiscale, citId);
        log.info(methodName, "Revoca esenzione OK");
        try {
        	callNotificatore("setRevocaEsenzione", (Cittadino)res.get(CITTADINO_AURA), ec, citId, shibIdentitaCodiceFiscale);
        } catch (Exception e) {
        	log.error(methodName, "Errore nella chiamata al notifcatore");
        	e.printStackTrace();
        	registraSuAudit(XForwardedFor, "ko", service, "NTOIFICATORE KO", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
        }
        if (ec != null) {
          //Tracciatura risposta
          registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
          log.info(methodName, "END");
          return corsedResponse.status(Status.CREATED).entity(ec).build();
        }
      }
      
      log.info(methodName, "END");
      return corsedResponse.status(Status.OK).entity(res).build();
    } catch (EsenpatException e) {
      e.printStackTrace();
      EsenredCMessaggi messaggio = getMessaggio(e.getCodMessaggio());
      res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());
      registraSuAudit(XForwardedFor, e.getStatus() + " " + messaggio.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, shibIdentitaCodiceFiscale);
      log.tracciaRes(methodName, res);
      return corsedResponse.status(Status.CONFLICT).entity(res).build();
    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      log.tracciaRes(methodName, res);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

	@PUT
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/rettifica")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response setRettificaEsenzione(@Context HttpServletRequest req, @HeaderParam("X-Request-Id") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
      @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id,
      @PathParam("esenzione_id") String esenzione_id, Certificato certificato) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Rettifica esenzione";
    final String operation = "UPDATE";
    messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");

    try {
      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      if (Checker.isValorizzato(xRequestID) && Checker.isValorizzato(shibIdentitaCodiceFiscale) && Checker.isValorizzato(esenzione_id) && Checker.isValorizzato(XForwardedFor)
          && Checker.isValorizzato(cit_id) && certificato != null) {
        isTst();
        //Verifica congruita' parametri in input
        if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor)
            || !Checker.isUuidValido(xRequestID)) {
          res = new HashMap<String, Object>();
          EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
          registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
              shibIdentitaCodiceFiscale);
          res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruità parametri di input", Status.BAD_REQUEST.getStatusCode());
          return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
        }

        //Verifica CF cittadino e residenza 
        res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

        if (res.get(Constants.CODE) == AURA_ERRORE_CONNESSIONE) {
          String msg12 = res.get(Constants.TITLE).toString();
          res = new HashMap<String, Object>();
          res.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
          res.put(Constants.CODE, "Mancata connessione ad aura");
          res.put(Constants.TITLE, msg12);
          registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
          return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

        } else if (res.get(Constants.CODE) == CITTADINO_NON_PIEMONTESE) {
          String msg143 = res.get(Constants.TITLE).toString();
          res = new HashMap<String, Object>();
          res.put(Constants.STATUS, Status.BAD_REQUEST.getStatusCode());
          res.put(Constants.CODE, "Verifica del CF negativa");
          res.put(Constants.TITLE, msg143);
          registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
          return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

        }
        Cittadino cittadino = (Cittadino) res.get(CITTADINO_AURA);

				
				if (!cit_id.equalsIgnoreCase(shibIdentitaCodiceFiscale)) {
					Cittadino delegato = null;
					cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
					EsenzioneTCittadino del = cittadinoFacade.getCittadinoEsenpat(shibIdentitaCodiceFiscale);
					if (del == null) {
						
						Map<String, Object> delRes = new HashMap<String, Object>();
						delRes = verificaDelegatoSuAura(shibIdentitaCodiceFiscale);

						if (delRes.get("code") == AURA_ERRORE_CONNESSIONE) {
							String msg12 = delRes.get("title").toString();
							delRes = new HashMap<String, Object>();
							delRes.put(Constants.STATUS, Status.SERVICE_UNAVAILABLE.getStatusCode());
							delRes.put(Constants.CODE, "Mancata connessione ad aura");
							delRes.put(Constants.TITLE, msg12);
							return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(delRes).build();
						} else if (delRes.containsKey(CITTADINO_AURA)) {
							delegato = (Cittadino) delRes.get(CITTADINO_AURA);
							cittadinoFacade.insertOrUpdateCittadino(shibIdentitaCodiceFiscale,
									shibIdentitaCodiceFiscale, delegato);
						}
					}
				}
				

        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        EsenzioneCittadinoEsenpat esenzioneCittadino = esenzioniFacade.setRettificaEsenzione(req, shibIdentitaCodiceFiscale, xRequestID, XForwardedFor, xcodServizio, cit_id, esenzione_id, cittadino,
            certificato);

        try {
        	callNotificatore("setRettificaEsenzione", cittadino, esenzioneCittadino, cit_id, shibIdentitaCodiceFiscale);
        } catch (Exception e) {
        	e.printStackTrace();
        	registraSuAudit(XForwardedFor, "ko", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        }


        //Tracciatura risposta
        registraSuAudit(XForwardedFor, "ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);


				return corsedResponse.status(Status.OK).entity(esenzioneCittadino).build();
        //}
      } else {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
    } catch (EsenpatException esenpatException) {
      EsenredCMessaggi messaggio = getMessaggio(esenpatException.getCodMessaggio());
      res = generateResponseErrore(messaggio.getTesto(), esenpatException.getCodice(), esenpatException.getStatus());

      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Constants.KO);
      res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.MESSAGE, e.getLocalizedMessage());

      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }

  }

  private Map<String, Object> generateResponseErrore(String msg, String code, int status) {
    Map<String, Object> res = new HashMap<String, Object>();
    res = new HashMap<String, Object>();
    res.put(Constants.STATUS, status);
    res.put(Constants.CODE, code);
    res.put(Constants.TITLE, msg);
    return res;
  }

  /*
   * Download di uno specifico certificato usato in una richiesta di esenzione per patologia 
   */
  @GET
  @Path("/cittadini/{cit_id}/certificati/{certificato_id}/pdf")
  @Produces("application/pdf")
  public Response getCertificatoMalattia(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id,
      @PathParam("certificato_id") String certificato_id) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();

    String fileName = null;
    File filePdf = null;

    try {
      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(certificato_id) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
          || !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
      	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);

        res.put("status", Status.BAD_REQUEST.getStatusCode());
        res.put("code", "Parametri obbligatori mancanti");
        res.put("title", messaggio145.getTesto());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)
          || !Checker.isNumericString(certificato_id)) {
      	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res.put("status", Status.BAD_REQUEST.getStatusCode());
        res.put("code", "Mancata congruita\' parametri di input");
        res.put("title", messaggio149.getTesto());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura("Certificato malattia", XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
      	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        EsenredCMessaggi msg012 = getMessaggio(Constants.MSG012);
        res = new HashMap<String, Object>();
        res.put("status", Status.SERVICE_UNAVAILABLE.getStatusCode());
        res.put("code", "Mancata connessione ad AURA");
        res.put("title", msg012.getTesto());
        registraSuAudit(XForwardedFor, "503 " + msg012.getTesto(), "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
      	corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        EsenredCMessaggi msg143 = getMessaggio(Constants.MSG143);
        res = new HashMap<String, Object>();
        res.put("status", Status.BAD_REQUEST.getStatusCode());
        res.put("code", "Verifica del CF negativa");
        res.put("title", msg143);
        registraSuAudit(XForwardedFor, "400 " + msg143.getTesto(), "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      } else if (res.containsKey(CITTADINO_AURA)) {
        res = new HashMap<String, Object>();

        //Ricerca certificato malattia
        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
        EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = certificatiFacade.getCertificatoMalattia(certificato_id);

        if (esenzioneTRepositoryDocumentale == null) {
          corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
          //gestione errore 404
          res = new HashMap<String, Object>();
          EsenredCMessaggi messaggio005 = getMessaggio(Constants.MSG005);
          registraSuAudit(XForwardedFor, Status.NOT_FOUND.getStatusCode() + " " + messaggio005.getTesto(), "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
              cit_id, shibIdentitaCodiceFiscale);
          res.put("status", Status.NOT_FOUND.getStatusCode());
          res.put("code", "Certificato malattia non trovato");
          res.put("title", messaggio005.getTesto());

          return corsedResponse.status(Status.NOT_FOUND).entity(res).build();

        }else {
          //Tracciatura risposta

          byte[] byteArray = Base64.getDecoder().decode(esenzioneTRepositoryDocumentale.getFile());
  
          fileName = Constants.PREFISSO_NOME_FILE_CERTIFICATO_MALATTIA + "_" + esenzioneTRepositoryDocumentale.getSkRepository() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
          filePdf = File.createTempFile(fileName, ".pdf");
					filePdf.deleteOnExit();
          FileUtils.writeByteArrayToFile(filePdf, byteArray);
        }
      }

      if (filePdf == null) {
       	  corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
    	  throw new Exception();
      }
      registraSuAudit(XForwardedFor, "1 ok", "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      return corsedResponse.header("Content-Disposition", "attachment; filename=" + filePdf.getName()).status(Status.OK).entity(filePdf).build();

    } catch (Exception e) {
      corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      //Tracciatura risposta
      registraSuAudit(XForwardedFor, "500 Errore generico", "Certificato malattia", "READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  @GET
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/zip")
  @Produces("application/zip")
  public Response createZipAllegati(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, @HeaderParam("X-Request-ID") String xRequestID,
      @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id, @PathParam("esenzione_id") String esenzione_id)
      throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Create zip";
    final String operation = "READ";
    try {

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica campi obbligatori
      if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(esenzione_id) || !Checker.isValorizzato(xRequestID)
          || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {
        res = new HashMap<String, Object>();
        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
        File zip = certificatiFacade.createZipAllegati(esenzione_id);
				zip.deleteOnExit();
        //Tracciatura risposta
        registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
				corsedResponse.header("Content-Disposition", "attachment; filename=" + zip.getName());
				return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition").status(Status.OK)
						.entity(zip).build();
			} else {
        throw new Exception();
      }
    } catch (EsenpatException e) {
      corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
      res = generateResponseErrore(e.getCodMessaggio(), e.getCodice(), e.getStatus());
      registraSuAudit(XForwardedFor, e.getStatus() + " " + e.getCodMessaggio(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
      return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
    } catch (Exception e) {
      corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }
  
  @GET
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/storico")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCronologia(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, 
      @HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor, 
      @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id,
      @PathParam("esenzione_id") String esenzione_id) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Storico esenzione";
    final String operation = "READ";
    
    try {

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica presenza parametri obbligatori
      if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio)
          || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
            cit_id, shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());

        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }
      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {

        res = new HashMap<String, Object>();

        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
        List<StoricoEsenzione> storico = certificatiFacade.getStoricoEsenzione(cit_id, esenzione_id);

        registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.OK).entity(storico).build();
    
      }
      return corsedResponse.status(Status.OK).entity(null).build();

    } catch (Exception e) {
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }

  }

  protected Map<String, Object> verificaCittadinoSuDeleghe(String operazione, String req, String codiceFiscale, String shib, String idRequest, String uuId, String xcodServizio) {
    List<it.csi.esenred.esenredweb.business.deleghebe.Cittadino> elencoDelegati = null;
    Map<String, Object> res = new HashMap<String, Object>();
    registraSuAudit(req, "Deleghe", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);
    try {
      elencoDelegati =IntegrationClientImpl.getInstance().chiamaRicercaCittadiniService(shib);
      if (elencoDelegati != null && !elencoDelegati.isEmpty()) {
        res.put(CITTADINO_DELEGATO, elencoDelegati.get(0));
        registraSuAudit(req, "Deleghe OK", operazione, "READ", shib, idRequest, uuId, xcodServizio, codiceFiscale, shib);
      } else {
        res.put(Constants.CODE, CITTADINO_NON_DELEGATO);
      }
      return res;
    } catch (Exception e) {
      e.printStackTrace();
      res.put(Constants.CODE, DELEGHE_ERRORE_CONNESSIONE);
      return res;
    }
  }

  @GET
  @Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/pdf")
  @Produces("application/pdf")
  public Response createPdfSingolaEsenzione(@Context HttpServletRequest req, @HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
      @HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor, @HeaderParam("X-Codice-Servizio") String xcodServizio, @PathParam("cit_id") String cit_id,
      @PathParam("esenzione_id") String esenzione_id, @QueryParam("document_type") String document_type) throws Exception {

    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
    Map<String, Object> res = new HashMap<String, Object>();
    final String service = "Create PDF singola esenzione";
    final String operation = "READ";
    try {

      //Tracciatura della chiamata
      registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

      //Verifica campi obbligatori
      if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(esenzione_id) || !Checker.isValorizzato(xRequestID)
          || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(document_type)) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      isTst();
      //Verifica congruita' parametri in input
      if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst) || !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)
          || !Checker.isDocTypeValido(document_type)) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        res = new HashMap<String, Object>();
        EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
        registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
            shibIdentitaCodiceFiscale);
        res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input", Status.BAD_REQUEST.getStatusCode());
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
      }

      //Verifica CF cittadino e residenza 
      res = verificaCittadinoSuAura(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);

      if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        String msg12 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg12, "Mancata connessione ad aura", Status.SERVICE_UNAVAILABLE.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

      } else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
        corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
        String msg143 = res.get("title").toString();
        res = new HashMap<String, Object>();
        res = generateResponseErrore(msg143, "Verifica del CF negativa", Status.BAD_REQUEST.getStatusCode());
        registraSuAudit(XForwardedFor, res.get("title").toString(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
        return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

      } else if (res.containsKey(CITTADINO_AURA)) {
        Cittadino cittadinoBen = (Cittadino) res.get(CITTADINO_AURA);
        if (cittadinoBen == null)
          throw new Exception();

        res = new HashMap<String, Object>();
        Cittadino cittadinoDel = null;
        if (!cit_id.equalsIgnoreCase(shibIdentitaCodiceFiscale)) {

          //Verifica CF delegato su Deleghe 
          res = verificaCittadinoSuDeleghe(service, XForwardedFor, cit_id, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio);
          if (res.get("code") == DELEGHE_ERRORE_CONNESSIONE) {
            corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
            String msg147 = getMessaggio(Constants.MSG147).getTesto();
            res = new HashMap<String, Object>();
            res = generateResponseErrore(msg147, "Mancata connessione a deleghe", Status.SERVICE_UNAVAILABLE.getStatusCode());
            registraSuAudit(XForwardedFor, msg147, service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
            return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

          } else if (res.get("code") == CITTADINO_NON_DELEGATO) {
            corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
            String msg148 = getMessaggio(Constants.MSG148).getTesto();
            res = new HashMap<String, Object>();
            res = generateResponseErrore(msg148, "Verifica del CF delegato negativa", Status.BAD_REQUEST.getStatusCode());
            registraSuAudit(XForwardedFor, msg148, service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
            return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

          } else if (res.containsKey(CITTADINO_DELEGATO)) {
            cittadinoDel = new Cittadino((it.csi.esenred.esenredweb.business.deleghebe.Cittadino) res.get(CITTADINO_DELEGATO));
          } else {
            throw new Exception();
          }
        }
        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
        EsenzioneTPraticaEsenzione praticaEsenzione = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzione_id));
        if (praticaEsenzione == null) {
          corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
          res = new HashMap<String, Object>();
          res = generateResponseErrore("Pratica non trovata", Status.NOT_FOUND.getReasonPhrase(), Status.NOT_FOUND.getStatusCode());
          registraSuAudit(XForwardedFor, Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
              cit_id, shibIdentitaCodiceFiscale);
          return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
        }

        File pdfEsenzione = esenzioniFacade.createPdfSingolaEsenzione(praticaEsenzione, cittadinoBen, cittadinoDel, document_type);
        if (pdfEsenzione == null) {
          throw new Exception();
        }
        //Tracciatura risposta
        registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);

        return corsedResponse.header("Content-Disposition", "attachment; filename=" + pdfEsenzione.getName()).status(Status.OK).entity(pdfEsenzione).build();
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
      e.printStackTrace();
      res = new HashMap<String, Object>();
      res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put(Constants.CODE, "Errore generico");
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
  }

  protected void registraSuAuditAuraRp(String ipAddress, String keyOper, String oggOper, String operazione, String utente, String idRequest, String uuId, String xcodServizio, String utenteBen,
	      String utenteDel) {
	    try {
	      getAmbiente();
	      String appId = "AURA_RP-01_TEST";
	      if (ambiente != null) {
	        appId = "AURA_RP-01_" + ambiente;
	      }

	      auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
	      if (utenteDel!=null && utenteDel.equalsIgnoreCase(utenteBen))
	        utenteDel = null;
	      CsiLogAudit audit = new CsiLogAudit(true, appId, ipAddress, keyOper, oggOper, operazione, utente, idRequest, uuId, xcodServizio, utenteBen, utenteDel);
			auditIf.insertAuditEsenpat(audit);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
  
  private void callNotificatore(String webService, Cittadino cit, EsenzioneCittadinoEsenpat ec, String citId,String shibIdentitaCodiceFiscale) throws Exception {
	  
	  String methodName = "callNotificatore";
	  log.info(methodName, "BEGIN");
	  
	  Map<String, String> replacements = new HashMap<String, String>();
	  replacements.put("@BENEFICIARIO@", cit.getCognome()+" "+cit.getNome());
	  replacements.put("@NUMPRATICA@", ec.getNumeroPratica());
	  replacements.put("@CODESENZIONE@", ec.getCodice().getCodice());
	  replacements.put("@DESCESENZIONE@", ec.getCodice().getDescrizione());
	  
	  log.debug(methodName, "BENEFICIARIO: " + cit.getCognome()+" "+cit.getNome());
	  log.debug(methodName, "NUMPRATICA: " + ec.getNumeroPratica());
	  log.debug(methodName, "CODESENZIONE: " + ec.getCodice().getCodice());
	  log.debug(methodName, "DESCESENZIONE: " + ec.getCodice().getDescrizione());
	  
	  if (!citId.equals(shibIdentitaCodiceFiscale)) {//richiesta fatta da delegato
		  //recuperare i dati anagrafici del delegato
		  log.debug(methodName, "richiesta fatta da delegato, verifico la delega.");
		  		  

		  
		  it.csi.esenred.esenredweb.business.deleghebe.Cittadino delegato =IntegrationClientImpl.getInstance().chiamaRicercaCittadiniService(shibIdentitaCodiceFiscale).get(0);
		  		  
		  replacements.put("@DELEGATO@", delegato.getCognome()+" "+delegato.getNome());
		  log.debug(methodName, "DELEGATO: " + delegato.getCognome()+" "+delegato.getNome());
	  }
	  

	  
	  notificatoreFacade = (NotificatoreFacade) SpringApplicationContext.getBean("notificatoreFacade");
	  notificatoreFacade.sendMessage(webService, citId, replacements);
	  
	  log.debug(methodName, "Messaggio inviato");
	  
	  log.info(methodName, "END");
  }

  
	@GET
	@Path("/tipologie-esenzione/{tipologia_codice}/esenzioni")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEsenzioniByTipo(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio,
			@PathParam("tipologia_codice") String tipologia_codice) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)
					|| !Checker.isValorizzato(tipologia_codice)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			Set<EsenzioniResponse> listaEsenzioni = null;
			// controllo dati inseriti certificato
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			listaEsenzioni = esenzioniFacade.getListaEsenzioniByGruppoEsenzione(tipologia_codice);
			return corsedResponse.status(Status.OK).entity(listaEsenzioni).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}
	
	@GET
	@Path("/disclaimer-trattamento-dati")
	@Produces(MediaType.TEXT_HTML)
	public Response getDisclaimerTrattamentoDati(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> parametri = parametroIf.getParametri(Constants.DISCLAIMER_TRATTAMENTO_DATI);
			EsenredCParametri parametro = parametri.get(0);
			return corsedResponse.status(Status.OK).entity(parametro.getValore()).build();
		} catch (Exception e) {
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/disclaimer-iscrizione-malattie-rare")
	@Produces(MediaType.TEXT_HTML)
	public Response getDisclaimerIscrizioneMalattieRare(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> parametri = parametroIf.getParametri(Constants.DISCLAIMER_ISCRIZIONE);
			EsenredCParametri parametro = parametri.get(0);
			return corsedResponse.status(Status.OK).entity(parametro.getValore()).build();
		} catch (Exception e) {
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/tipologie-esenzione/{tipologia_codice}/tipologie-documento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaTipologieDocumento(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio,
			@PathParam("tipologia_codice") String tipologia_codice) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)
					|| !Checker.isValorizzato(tipologia_codice)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<TipologiaDocumento> listaTipologieDocumento = esenzioniFacade
					.getListaTipologieDocumento(tipologia_codice);
			return corsedResponse.status(Status.OK).entity(listaTipologieDocumento).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@GET
	@Path("/motivazioni/annullamento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaMotivazioniAnnullamento(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) 
					|| !Checker.isValorizzato(shibIdentitaCodiceFiscale)
				) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			String motivazioniAnnullamento = Constants.STATO_PRATICA_ANNULLATA;
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<MotivazioniResponse> listaMotivazioniAnnullamento = esenzioniFacade
					.getListaMotivazioniByCodStato(motivazioniAnnullamento);
			return corsedResponse.status(Status.OK).entity(listaMotivazioniAnnullamento).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@GET
	@Path("/motivazioni/revoca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaMotivazioniRevoca(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) 
					|| !Checker.isValorizzato(shibIdentitaCodiceFiscale)
				) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			String motivazioniAnnullamento = Constants.STATO_PRATICA_REVOCATA;
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<MotivazioniResponse> listaMotivazioniAnnullamento = esenzioniFacade
					.getListaMotivazioniByCodStato(motivazioniAnnullamento);
			return corsedResponse.status(Status.OK).entity(listaMotivazioniAnnullamento).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@GET
	@Path("/stati-esenzione")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatiEsenzione(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) 
					|| !Checker.isValorizzato(shibIdentitaCodiceFiscale)) 
			{
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<StatiTipologieResponse> listaStatiEsenzione = esenzioniFacade
					.getListaStatiEsenzione();
			return corsedResponse.status(Status.OK).entity(listaStatiEsenzione).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@GET
	@Path("/tipologie-esenzione")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTipologieEsenzione(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) 
					|| !Checker.isValorizzato(shibIdentitaCodiceFiscale)) 
			{
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<StatiTipologieResponse> listaTipologieEsenzione = esenzioniFacade
					.getListaTipologieEsenzione();
			return corsedResponse.status(Status.OK).entity(listaTipologieEsenzione).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	@GET
	@Path("/tipologie-esenzione/{tipologia_codice}/esenzioni/{esenzione_codice}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDettaglioEsenzioneRichiedibile(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio,
			@PathParam("tipologia_codice") String tipologia_codice,
			@PathParam("esenzione_codice") String esenzione_codice) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)
					|| !Checker.isValorizzato(tipologia_codice)
					|| !Checker.isValorizzato(esenzione_codice) ) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");

			// controllo dati inseriti certificato
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			EsenzioniResponse dettaglioEsenzione = esenzioniFacade.getDettaglioEsenzioneRichiedibile(tipologia_codice,esenzione_codice);
			return corsedResponse.status(Status.OK).entity(dettaglioEsenzione).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}

	@GET
	@Path("/tipologie-esenzione/{tipologia_codice}/malattie")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaMalattie(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio,
			@PathParam("tipologia_codice") String tipologia_codice) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)
					|| !Checker.isValorizzato(tipologia_codice)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<MalattiaV2> listaMalattie = esenzioniFacade.getListaMalattie(tipologia_codice);
			return corsedResponse.status(Status.OK).entity(listaMalattie).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/tipologie-esenzione/{tipologia_codice}/malattie/{malattia_codice}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDettaglioMalattia(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio,
			@PathParam("tipologia_codice") String tipologia_codice,
			@PathParam("malattia_codice") String malattia_codice) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)
					|| !Checker.isValorizzato(tipologia_codice) || !Checker.isValorizzato(malattia_codice)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			MalattiaV2 listaMalattie = esenzioniFacade.getDettaglioMalattia(tipologia_codice, malattia_codice);
			return corsedResponse.status(Status.OK).entity(listaMalattie).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/tipologie-invalidita")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaTipologieInvalidita(@Context HttpServletRequest req,
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestID, @HeaderParam("X-Forwarded-For") String XForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xcodServizio) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			
			if (!Checker.isValorizzato(xRequestID) || !Checker.isValorizzato(XForwardedFor)
					|| !Checker.isValorizzato(xcodServizio) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggio(Constants.MSG145);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor) || !Checker.isUuidValido(xRequestID)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggio(Constants.MSG149);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<TipologiaInvalidita> listaInvalidita = esenzioniFacade.getListaTipologieInvalidita();
			return corsedResponse.status(Status.OK).entity(listaInvalidita).build();

		} catch (Exception e) {
			e.printStackTrace();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, "Errore generico");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	@POST
	@Path("/notificatoreBatch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response callNotificatoreBatch(@Context HttpServletRequest req, BatchNotificatore datiBatch)
			throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		String citId = datiBatch.getCitId();
		String esenzioneId = datiBatch.getEsenzioneId();
		try {
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(citId));
			if (datiCittadinoshib.isEmpty()) {
				return null;
			} else {
				
				Cittadino cit = IntegrationClientImpl.getInstance()
						.getCittadinoEsenpat(datiCittadinoshib.get(0).getIdAura());
				if (isCittadinoPiemontese(cit)) {
					esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
					EsenzioneTPraticaEsenzione esen = esenzioniFacade
							.loadFullEsenzioneTPraticaEsenzione(new Integer(esenzioneId));
					Map<String, String> replacements = new HashMap<String, String>();
					replacements.put("@BENEFICIARIO@", cit.getCognome() + " " + cit.getNome());
					replacements.put("@NUMPRATICA@", esen.getNumPratica().toString());
					replacements.put("@CODESENZIONE@",
							esen.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
					replacements.put("@DESCESENZIONE@",
							esen.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getDescEsenzione());

					notificatoreFacade = (NotificatoreFacade) SpringApplicationContext.getBean("notificatoreFacade");
					notificatoreFacade.sendMessage(datiBatch.getParametroBatch(), citId, replacements);

					res.put(Constants.STATUS, Constants.OK);
					res.put(Constants.DATA, "notifica inviata");
					res.put(Constants.CODE, Status.OK.getStatusCode());

					return corsedResponse.status(Status.OK).entity(res).build();
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}