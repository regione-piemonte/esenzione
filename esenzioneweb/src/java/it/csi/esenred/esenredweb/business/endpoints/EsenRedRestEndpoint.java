/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.endpoints;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.WebServiceException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jboss.resteasy.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenpatweb.business.facade.CertificatiFacade;
import it.csi.esenred.esenpatweb.business.facade.CittadinoFacade;
import it.csi.esenred.esenpatweb.business.facade.EsenzioniFacade;
import it.csi.esenred.esenpatweb.business.gateway.Attachment;
import it.csi.esenred.esenpatweb.business.gateway.PadesInput;
import it.csi.esenred.esenpatweb.business.gateway.SignIdentity;
import it.csi.esenred.esenpatweb.business.iride.base.Application;
import it.csi.esenred.esenpatweb.business.iride.base.Identita;
import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenpatweb.dto.AggiornaAttestato;
import it.csi.esenred.esenpatweb.dto.Assistito;
import it.csi.esenred.esenpatweb.dto.AttestatoEsenzione;
import it.csi.esenred.esenpatweb.dto.Beneficiario;
import it.csi.esenred.esenpatweb.dto.Certificato;
import it.csi.esenred.esenpatweb.dto.CertificatoAssistito;
import it.csi.esenred.esenpatweb.dto.CertificatoEsenpat;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.CittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.Comune;
import it.csi.esenred.esenpatweb.dto.DataInizioFine;
import it.csi.esenred.esenpatweb.dto.DettaglioCertificato;
import it.csi.esenred.esenpatweb.dto.DiagnosiGruppo;
import it.csi.esenred.esenpatweb.dto.Esenzione;
import it.csi.esenred.esenpatweb.dto.EsenzioneAcceleratoreEsenred;
import it.csi.esenred.esenpatweb.dto.EsenzioneAssistito;
import it.csi.esenred.esenpatweb.dto.EsenzioneCittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.EsenzioneCittadinoEsenred;
import it.csi.esenred.esenpatweb.dto.EsenzioneGruppo;
import it.csi.esenred.esenpatweb.dto.EsenzioneOperatoreEsenred;
import it.csi.esenred.esenpatweb.dto.EsenzionePatologiaElenco;
import it.csi.esenred.esenpatweb.dto.EsenzioneTipologia;
import it.csi.esenred.esenpatweb.dto.FiltriFirmaDigitale;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaAssistito;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaCittadino;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniCittadino;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniOperatore;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaPratiche;
import it.csi.esenred.esenpatweb.dto.FiltriRichiestaOtp;
import it.csi.esenred.esenpatweb.dto.FiltriValidaEsenzionePatologia;
import it.csi.esenred.esenpatweb.dto.FiltroDettaglioEsenzione;
import it.csi.esenred.esenpatweb.dto.GruppoEsenzionePatologiaElenco;
import it.csi.esenred.esenpatweb.dto.ListaBeneficiari;
import it.csi.esenred.esenpatweb.dto.ListaDiagnosi;
import it.csi.esenred.esenpatweb.dto.ListaEsenzioni;
import it.csi.esenred.esenpatweb.dto.ListaTitolari;
import it.csi.esenred.esenpatweb.dto.Malattia;
import it.csi.esenred.esenpatweb.dto.ModelCa;
import it.csi.esenred.esenpatweb.dto.Motivazione;
import it.csi.esenred.esenpatweb.dto.MotivazioneAnnullamento;
import it.csi.esenred.esenpatweb.dto.MotivazioneRevocaEsenpat;
import it.csi.esenred.esenpatweb.dto.Notifica;
import it.csi.esenred.esenpatweb.dto.NuovaEsenzione;
import it.csi.esenred.esenpatweb.dto.NuovoCertificato;
import it.csi.esenred.esenpatweb.dto.Parametro;
import it.csi.esenred.esenpatweb.dto.Prestazioni;
import it.csi.esenred.esenpatweb.dto.ResponseAura;
import it.csi.esenred.esenpatweb.dto.StatoDocumento;
import it.csi.esenred.esenpatweb.dto.StoricoEsenzione;
import it.csi.esenred.esenpatweb.dto.Tipologia;
import it.csi.esenred.esenpatweb.dto.TitoloDichiarante;
import it.csi.esenred.esenpatweb.dto.TitoloDichiaranteAcceleratore;
import it.csi.esenred.esenpatweb.dto.UserInfo;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologia;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaRes;
import it.csi.esenred.esenredweb.business.aura.find.AnagrafeFindStub;
import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.aura.get.ArrayOfinfoesenzioneInfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedRes;
import it.csi.esenred.esenredweb.business.bo.ApprovaEsenzioneBO;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoAutocertificazioneBO;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoFamiliareBO;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoOperatoreBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneCertificataBO;
import it.csi.esenred.esenredweb.business.bo.RichiestaRettificaDatiBO;
import it.csi.esenred.esenredweb.business.bo.RinnovaEsenzioneBO;
import it.csi.esenred.esenredweb.business.bo.SalvaInBozzaBO;
import it.csi.esenred.esenredweb.business.check.CheckRicercaCittadini;
import it.csi.esenred.esenredweb.business.check.CheckRicercaEsenzioneOperatore;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredWNotifiche;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDAzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoUtenti;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPrestazioneSpecialistica;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTMetadatiDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.entity.Messaggio;
import it.csi.esenred.esenredweb.business.exception.AuraIntegrationException;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.infocert.proxysign.ProxySignStub;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.AslOperatoreIf;
import it.csi.esenred.esenredweb.business.model.interfaces.AuditIf;
import it.csi.esenred.esenredweb.business.model.interfaces.CertificatiPatologiaIf;
import it.csi.esenred.esenredweb.business.model.interfaces.CodiceEsenzionePatologiaIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneTPraticaEsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.GruppoEsenzionePatologiaIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.NotificaIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.model.interfaces.RuoliIf;
import it.csi.esenred.esenredweb.business.model.interfaces.StatoDocumentoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.StatoPraticaIf;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;
import it.csi.esenred.esenredweb.business.services.EsenzioneRedditoAutocertificazioneService;
import it.csi.esenred.esenredweb.business.services.EsenzioneRedditoCertService;
import it.csi.esenred.esenredweb.business.services.EsenzioneRedditoFamiliareService;
import it.csi.esenred.esenredweb.business.services.EsenzioneRedditoOperatoreService;
import it.csi.esenred.esenredweb.business.services.EsenzioneRedditoService;
import it.csi.esenred.esenredweb.business.services.ReportService;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenredweb.filter.IrideIdAdapterFilter;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.LogUtil;
import it.csi.esenred.esenredweb.util.RestUtils;
import it.csi.esenred.esenredweb.util.Util;

@Path("/rest/services")
public class EsenRedRestEndpoint {

	@Autowired
	private EsenzioniFacade esenzioniFacade;

	@Autowired
	private CittadinoFacade cittadinoFacade;

	@Autowired
	private CertificatiFacade certificatiFacade;
	
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE, null);

	private static final String MSG_034 = "MSG034";
	private static final String MSG_045 = "MSG045";

	private static final String CITTADINO_AURA = "CITTADINO_AURA";
	private static final String CITTADINO_NON_PIEMONTESE = "CITTADINO_NON_PIEMONTESE";
	private static final String AURA_ERRORE_CONNESSIONE = "AURA_ERRORE_CONNESSIONE";
	private static final String CITTADINO_DELEGATO = "CITTADINO_DELEGATO";
	private static final String CITTADINO_NON_DELEGATO = "CITTADINO_NON_DELEGATO";
	private static final String DELEGHE_ERRORE_CONNESSIONE = "DELEGHE_ERRORE_CONNESSIONE";

	private static String ambiente;

	MessaggioIf messaggioIf;
	EsenzioneIf esenzioneIf;
	StatoDocumentoIf statoDocumentoIf;
	StatoPraticaIf statoPraticaIf;
	CodiceEsenzionePatologiaIf codiceEsenzionePatologiaIf;
	EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf;
	GruppoEsenzionePatologiaIf gruppoEsenzionePatologiaIf;
	RuoliIf ruoliIf;
	ParametroIf parametroIf;
	AslOperatoreIf aslOperatoreIf;
	AuditIf auditIf;
	NotificaIf notificaIf;
	ComuneIf comuneIf;
	TitoloDichiaranteIf titoloDichiaranteIf;
	EsenzioneCittadinoIf esenzioneCittadinoIf;
	CertificatiPatologiaIf certificatiPatologiaIf;
	private static String isTst;
	public static Connection conn;
	public static PreparedStatement ps;
	private final static Logger logger = Logger.getLogger(AnagrafeFindStub.class.getName());

	@GET
	@Path("/getEsenzione/{codEsenzione}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getEsenzione(
			// @ApiParam(name = "codEsenzione", value = "Codice esenzione", required = true)
			@PathParam("codEsenzione") String codEsenzione) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<Esenzione> listEsenzioniOut = new ArrayList<Esenzione>();
			esenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
			List<EsenredDTipiEsenzioniReddito> elencoEsenzioni = esenzioneIf.getEsenzioni(codEsenzione);
			for (Iterator<EsenredDTipiEsenzioniReddito> iterator = elencoEsenzioni.iterator(); iterator.hasNext();) {
				EsenredDTipiEsenzioniReddito eDB = (EsenredDTipiEsenzioniReddito) iterator.next();
				listEsenzioniOut.add(new Esenzione(eDB));
			}
			if (!listEsenzioniOut.isEmpty()) {
				res.put("status", "ok");
				res.put("data", listEsenzioniOut);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getEsenzioni")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getEsenzioni() throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<Esenzione> listEsenzioniOut = new ArrayList<Esenzione>();
			esenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
			List<EsenredDTipiEsenzioniReddito> elencoEsenzioni = esenzioneIf.getEsenzioni(null);
			for (Iterator<EsenredDTipiEsenzioniReddito> iterator = elencoEsenzioni.iterator(); iterator.hasNext();) {
				EsenredDTipiEsenzioniReddito eDB = (EsenredDTipiEsenzioniReddito) iterator.next();
				listEsenzioniOut.add(new Esenzione(eDB));
			}
			if (!listEsenzioniOut.isEmpty()) {
				res.put("status", "ok");
				res.put("data", listEsenzioniOut);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findCittadino/{codFiscale}")

	public Response getCittadino(
			// @ApiParam(name = "codFiscale", value = "Codice fiscale", required = true)
			@PathParam("codFiscale") String codFiscale) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			String cf = getCodFiscaleDebug(codFiscale); // TODO come da email Carla Gamba 08/03/2018 12:35

			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(cf));

			if (!datiCittadino.isEmpty()) {
				Cittadino esenredTCittadino = datiCittadino.get(0);

				esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
				if (esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(datiCittadino.get(0).getIdAura()), null,
						null)) {
					esenredTCittadino.setStato("B");
				}

				res.put("status", "ok");
				res.put("data", esenredTCittadino);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}

			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/findCittadini")

	public Response getCittadini(
			// @ApiParam(name = "filtri", value = "Filtri di ricerca", required = true)
			FiltriRicercaCittadino filtri) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			CheckRicercaCittadini.chkFiltri(filtri);

			Cittadino c = new Cittadino();
			c.setCodFiscale(filtri.getCodFiscale());
			c.setCognome(filtri.getCognome());
			c.setNome(filtri.getNome());
			c.setDataNascita(filtri.getDataDiNascita());
			List<Cittadino> elencoCittadini = null;
			elencoCittadini = IntegrationClientImpl.getInstance().findCittadino(c);

			if (!elencoCittadini.isEmpty())
				elencoCittadini.get(0).setDatascadenzaSSN(IntegrationClientImpl.getInstance()
						.getCittadino(elencoCittadini.get(0).getIdAura()).getDatascadenzaSSN());
			String message = null;
			// se ruolo = ricerca allora chiama solo la find cittadino altrimenti se sei nel
			// caso di inserimento devi chiamare interroga mef

			if (elencoCittadini.isEmpty() && (filtri.getRuolo().equalsIgnoreCase("DICHIARANTE")
					|| filtri.getRuolo().equalsIgnoreCase("TITOLARE"))) {
				// se non trovo il cittadino del piemonte prima di dare errore verifico il
				// ruolo. Nel caso dichiarante o titolare chiamo servizio interrrogaMEF

				// chiamare il servizio cittadino fuori regione
				// crea oggetto cittadino fuori regione

			}

			if (elencoCittadini.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				message = messaggioIf.getMessaggio("MSG024").getTesto();
			}

			res.put("status", "ok");
			res.put("data", elencoCittadini);
			res.put("code", Status.OK.getStatusCode());
			res.put("message", message);
			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("data", null);
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception e) { e.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG023"), e.getLocalizedMessage());

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}

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
				res.put("status", "ok");
				res.put("data", listparametriOut);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getParametri")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getParametri() throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			List<Parametro> listparametriOut = new ArrayList<Parametro>();
			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri(null);

			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri pDB = (EsenredCParametri) iterator.next();
				listparametriOut.add(new Parametro(pDB));
			}

			if (!listparametriOut.isEmpty()) {
				res.put("status", "ok");
				res.put("data", listparametriOut);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getTitoloDichiarante/{codTitolo}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getTitoloDichiarante(

			@PathParam("codTitolo") String codTitolo) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		List<TitoloDichiarante> listTitoliOut = new ArrayList<TitoloDichiarante>();

		try {
			titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext.getBean("titoloDichiarante");
			List<EsenredCTitoloDichiarante> elencoTitoli = titoloDichiaranteIf.getTitoli(codTitolo);

			for (Iterator<EsenredCTitoloDichiarante> iterator = elencoTitoli.iterator(); iterator.hasNext();) {
				EsenredCTitoloDichiarante tDB = (EsenredCTitoloDichiarante) iterator.next();
				listTitoliOut.add(new TitoloDichiarante(tDB));
			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", listTitoliOut);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", ex.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/rapporti-dichiaranti")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getRapportiDichiarante() throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		List<TitoloDichiaranteAcceleratore> listTitoliOut = new ArrayList<TitoloDichiaranteAcceleratore>();

		try {
			titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext.getBean("titoloDichiarante");
			List<EsenredCTitoloDichiarante> elencoTitoli = titoloDichiaranteIf.getTitoli(null);

			for (Iterator<EsenredCTitoloDichiarante> iterator = elencoTitoli.iterator(); iterator.hasNext();) {
				EsenredCTitoloDichiarante tDB = (EsenredCTitoloDichiarante) iterator.next();
				listTitoliOut.add(new TitoloDichiaranteAcceleratore(tDB));
			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", listTitoliOut);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", ex.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getTitoliDichiarante")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTitoliDichiarante() throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		List<TitoloDichiarante> listTitoliOut = new ArrayList<TitoloDichiarante>();

		try {
			titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext.getBean("titoloDichiarante");
			List<EsenredCTitoloDichiarante> elencoTitoli = titoloDichiaranteIf.getTitoli(null);

			for (Iterator<EsenredCTitoloDichiarante> iterator = elencoTitoli.iterator(); iterator.hasNext();) {
				EsenredCTitoloDichiarante tDB = (EsenredCTitoloDichiarante) iterator.next();
				listTitoliOut.add(new TitoloDichiarante(tDB));
			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", listTitoliOut);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", ex.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getTitoliFamiliare")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTitoliFamiliare() throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		List<TitoloDichiarante> listTitoliOut = new ArrayList<TitoloDichiarante>();

		try {
			titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext.getBean("titoloDichiarante");
			List<EsenredCTitoloDichiarante> elencoTitoli = titoloDichiaranteIf.getTitoliFamiliare();

			for (Iterator<EsenredCTitoloDichiarante> iterator = elencoTitoli.iterator(); iterator.hasNext();) {
				EsenredCTitoloDichiarante tDB = (EsenredCTitoloDichiarante) iterator.next();
				listTitoliOut.add(new TitoloDichiarante(tDB));
			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", listTitoliOut);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", ex.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@POST
	@Path("/getEsenzioniCittadino")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEsenzioniCittadino(
			// @ApiParam(name = "filtri", value = "Filtri di ricerca", required = true)
			FiltriRicercaEsenzioniCittadino filtri) throws Exception {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		List<EsenzioneCittadinoEsenred> elencoEsenzioniOut = new ArrayList<EsenzioneCittadinoEsenred>();
		List<Cittadino> datiCittadino = null;
		try {
			CheckRicercaEsenzioneOperatore.chkFiltri(filtri);
			if (!Checker.isValorizzato(filtri.getIdAuraDichiarante())) { // converto codice fiscale dichiarante in ID
																			// Aura se questo non e' disponibile
				datiCittadino = IntegrationClientImpl.getInstance()
						.findCittadino(new Cittadino(filtri.getCodFiscaleDichiarante()));
				filtri.setIdAuraDichiarante(datiCittadino.get(0).getIdAura());
			}
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			List<EsenredTEsenzioniReddito> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioniCittadino(filtri);
			if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = null;
				if (Checker.isValorizzato(filtri.getCodEsenzione()) && filtri.getCodEsenzione().contains(",")) { // prima
																													// volta
					codMSG = "MSG018";
				} else {
					codMSG = "MSG005";
				}
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioniOut);

				return corsedResponse.status(Status.OK).entity(res).build();
			}

			elencoEsenzioniOut = getAuraInfo(elencoEsenzioni, datiCittadino.get(0));

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", elencoEsenzioniOut);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);
			if (this.ps != null)
				ps.close();
			if (this.conn != null)
				this.conn.close();
			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception ex) {
			ex.printStackTrace();

			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);
			if (this.ps != null)
				ps.close();
			if (this.conn != null)
				this.conn.close();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();

		}

	}

	@POST
	@Path("/getEsenzioniOperatorePaginato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEsenzioniOperatorePaginato(@Context HttpServletRequest req,
			FiltriRicercaEsenzioniOperatore filtri) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		List<EsenzioneOperatoreEsenred> elencoEsenzioniOut = new ArrayList<EsenzioneOperatoreEsenred>();
		boolean compilazioneonline = false;
		try {
			CheckRicercaEsenzioneOperatore.chkFiltri(filtri);
			String codASLOperatore = null;

			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ELEMENTI_RICERCA");

			if (filtri.getInCaricoASL() != null) {
				UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
				List<Cittadino> datiOperatore = IntegrationClientImpl.getInstance()
						.findCittadino(new Cittadino(userInfo.getCodFisc()));
				if ("true".equalsIgnoreCase(filtri.getInCaricoASL())) {
					aslOperatoreIf = (AslOperatoreIf) SpringApplicationContext.getBean("aslOperatore");
					// se vai per CF senza chiamare Aura
					codASLOperatore = aslOperatoreIf.getAslOperatoreCF(userInfo.getCodFisc()).getCodAsl();
					filtri.setAslOperatore(codASLOperatore);
					// se vai per id aura
					// codASLOperatore = aslOperatoreIf.getAslOperatore(new
					// Long(datiOperatore.get(0).getIdAura())).getCodAsl();
					// codASLOperatore =
					// IntegrationClientImpl.getInstance().getCittadino(datiOperatore.get(0).getIdAura()).getCodASL();
				}
				else
				{
					//se asl operatore = null vuol dire che si sta facendo compilazione online quindi non deve chiamare getesenred
					compilazioneonline = true;
				}
			}
			String listaprotocolli = "";
			EsenredTEsenzioniReddito EsenzioneStorico = null;
			List<EsenredTEsenzioniReddito> elencoEsenzioniStoricoEssere = null;
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			List<EsenredTEsenzioniReddito> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioni(filtri, codASLOperatore);
			// preleva i protocolli per quelle valide e le mette in una striga per il not in
			// e
			// contemporaneamente
			for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
				if (esenredTEsenzioniReddito.getNumProtocolloSogei()!=null)
				esenredTEsenzioniReddito.setNumProtocolloSogei(esenredTEsenzioniReddito.getNumProtocolloSogei()==0 ? null : esenredTEsenzioniReddito.getNumProtocolloSogei());
				if ((esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("R")
						|| esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("V")
						|| esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("S")
						|| esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("B"))
						&& esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
					listaprotocolli = listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString()
							+ ",";
					// chiamo il metodo della ricerca storico
					try {
						EsenzioneStorico = IntegrationClientImpl.getInstance()
								.getEsenzioni(esenredTEsenzioniReddito.getNumProtocolloSogei().toString(), null, null,
										esenredTEsenzioniReddito.getIdCittadinoBeneficiario().toString(), "B",null)
								.get(0);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
					listaprotocolli = listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString()
							+ ",";
				}
			}

			if (listaprotocolli != "") {
				// prendi tutte le altre dello storico escludendo quelle di esenred
				listaprotocolli = listaprotocolli.substring(0, listaprotocolli.length() - 1);
			}
			try {
				if (!compilazioneonline)
				elencoEsenzioniStoricoEssere = IntegrationClientImpl.getInstance().getEsenzioniAcceleratore(filtri,
						listaprotocolli);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (elencoEsenzioniStoricoEssere != null) {
				elencoEsenzioni.addAll(elencoEsenzioniStoricoEssere);
			}
			
			Set<EsenredTEsenzioniReddito> valoreunico = new HashSet<EsenredTEsenzioniReddito>(elencoEsenzioni);
			List<EsenredTEsenzioniReddito> esenzionidistinte = new ArrayList<EsenredTEsenzioniReddito>(valoreunico);
			
			if (esenzionidistinte == null || esenzionidistinte.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioniOut);

				return corsedResponse.status(Status.OK).entity(res).build();
			}

			int dimensionePagina = Integer.parseInt(elencoParametri.get(0).getValore());
			if (filtri.getPagina() != 0 && (esenzionidistinte.size() > dimensionePagina)) {
				int lastElement;
				if (filtri.getPagina() == ((int) Math.ceil(esenzionidistinte.size() / dimensionePagina) + 1))
					lastElement = esenzionidistinte.size();
				else
					lastElement = dimensionePagina * filtri.getPagina();
				int FirstElement = dimensionePagina * filtri.getPagina();
				List<EsenredTEsenzioniReddito> elencoEsenzioniTmp = esenzionidistinte
						.subList(FirstElement - dimensionePagina, lastElement);

				elencoEsenzioniOut = getAuraInfoOperatore(elencoEsenzioniTmp);
			} else
				elencoEsenzioniOut = getAuraInfoOperatore(esenzionidistinte);

			if (filtri.getOrderBy().toLowerCase().equals("beneficiario") && filtri.getOrderBy() != null) {
				Collections.sort(elencoEsenzioniOut);
				if (filtri.isAsc())
					Collections.reverse(elencoEsenzioniOut);
			}
			for (EsenzioneOperatoreEsenred ese : elencoEsenzioniOut) {
				ese.setNumeroTotaleElementi(elencoEsenzioni.size());

				// pezza per revoca esenzioni certificate
				if (ese.getNumProtocolloSogei() != null)
					if (ese.getNumProtocolloSogei().equals("0"))
						ese.setNumProtocolloSogei(null);

			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", elencoEsenzioniOut);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@POST
	@Path("/getEsenzioniTitolareRevoca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEsenzioniTitolareRevoca(@Context HttpServletRequest req, String titolare) throws Exception {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		List<EsenzioneOperatoreEsenred> elencoEsenzioniOut = new ArrayList<EsenzioneOperatoreEsenred>();

		try {
			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ELEMENTI_RICERCA");
			String codASLOperatore = null;
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			List<Cittadino> datiOperatore = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(userInfo.getCodFisc()));
			aslOperatoreIf = (AslOperatoreIf) SpringApplicationContext.getBean("aslOperatore");
			// se vai per CF senza chiamare Aura
			codASLOperatore = aslOperatoreIf.getAslOperatoreCF(userInfo.getCodFisc()).getCodAsl();
			FiltriRicercaEsenzioniOperatore filtri = new FiltriRicercaEsenzioniOperatore();
			String listaprotocolli = "";
			EsenredTEsenzioniReddito EsenzioneStorico = null;
			List<EsenredTEsenzioniReddito> elencoEsenzioniStoricoEssere = null;
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			// inizializzo i filtri mettendo solo il titolare ed ignorando gli altri
			if (Checker.isNumericString(titolare))
			filtri.setIdAuraTitolare(titolare);
			else
			filtri.setCodFiscaleTitolare(titolare);
			filtri.setStorico("NO");
			filtri.setIdAuraOperatore("99999999");
			filtri.setCodStatoEsenzione("V");
			List<EsenredTEsenzioniReddito> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioni(filtri, codASLOperatore);
			// preleva i protocolli per quelle valide e le mette in una striga per il not in
			// e
			// contemporaneamente
			// per ora faccio la query solo per le esenzioni di esenred in attesa di
			// integrare le altre
			/*
			 * for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
			 * if ((esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("R") ||
			 * esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("V") ||
			 * esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("S") ||
			 * esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("B")) &&
			 * esenredTEsenzioniReddito.getNumProtocolloSogei() != null) { listaprotocolli =
			 * listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString()
			 * + ","; //chiamo il metodo della ricerca storico try { esenzioneCittadinoIf =
			 * (EsenzioneCittadinoIf)
			 * SpringApplicationContext.getBean("esenzioneCittadino"); EsenzioneStorico =
			 * esenzioneCittadinoIf.getSingleEsenzioniOracle(esenredTEsenzioniReddito.
			 * getNumProtocolloSogei().toString(),filtri.getStorico()); if (EsenzioneStorico
			 * != null) {
			 * esenredTEsenzioniReddito.setDataRevoca(EsenzioneStorico.getDataRevoca());
			 * esenredTEsenzioniReddito.setCodStato("R");
			 * esenredTEsenzioniReddito.setIdUserInsert(new Long ("99999998")); if
			 * (esenredTEsenzioniReddito.getNota()!=null) {
			 * esenredTEsenzioniReddito.setNota(esenredTEsenzioniReddito.getNota() +
			 * " (nota storico : " + EsenzioneStorico.getNota() + ")"); } else {
			 * esenredTEsenzioniReddito.setNota("(nota storico : " +
			 * EsenzioneStorico.getNota() + ")"); } } } catch (ClassNotFoundException e) {
			 *  closeAll(); e.printStackTrace(); } catch
			 * (SQLException e) {  closeAll();
			 * e.printStackTrace(); } } else if
			 * (esenredTEsenzioniReddito.getNumProtocolloSogei() != null) { listaprotocolli
			 * = listaprotocolli +
			 * esenredTEsenzioniReddito.getNumProtocolloSogei().toString() + ","; } }
			 * 
			 * if (listaprotocolli != "") { //prendi tutte le altre dello storico escludendo
			 * quelle di esenred listaprotocolli = listaprotocolli.substring(0,
			 * listaprotocolli.length() - 1); } try { esenzioneCittadinoIf =
			 * (EsenzioneCittadinoIf)
			 * SpringApplicationContext.getBean("esenzioneCittadino");
			 * elencoEsenzioniStoricoEssere =
			 * esenzioneCittadinoIf.getEsenzioniUtenteOracleTotale(filtri,listaprotocolli);
			 * } catch (ClassNotFoundException e) { 
			 * closeAll(); e.printStackTrace(); } catch (SQLException e) { // TODO
			 * Auto-generated catch block closeAll(); e.printStackTrace(); } if
			 * (elencoEsenzioniStoricoEssere != null) {
			 * elencoEsenzioni.addAll(elencoEsenzioniStoricoEssere); } closeAll();
			 */
			if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioniOut);

				return corsedResponse.status(Status.OK).entity(res).build();
			}

			elencoEsenzioniOut = getAuraInfoOperatore(elencoEsenzioni);

			for (EsenzioneOperatoreEsenred ese : elencoEsenzioniOut) {
				ese.setNumeroTotaleElementi(elencoEsenzioni.size());
			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", elencoEsenzioniOut);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getEsenzioniAsl")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEsenzioniAsl(@Context HttpServletRequest req) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		// List<EsenzioneOperatore> elencoEsenzioniOut = new
		// ArrayList<EsenzioneOperatore>();
		String codASLOperatore = null;
		try {
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			List<Cittadino> datiOperatore = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(userInfo.getCodFisc()));
			aslOperatoreIf = (AslOperatoreIf) SpringApplicationContext.getBean("aslOperatore");
			// se vai per CF senza chiamare Aura
			codASLOperatore = aslOperatoreIf.getAslOperatoreCF(userInfo.getCodFisc()).getCodAsl();
			// se vai per id aura
			// codASLOperatore = aslOperatoreIf.getAslOperatore(new
			// Long(datiOperatore.get(0).getIdAura())).getCodAsl();
			// codASLOperatore =
			// IntegrationClientImpl.getInstance().getCittadino(datiOperatore.get(0).getIdAura()).getCodASL();
			Set<Cittadino> elencoOperatoriDistinti = new HashSet<Cittadino>();
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			List<EsenredTEsenzioniReddito> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioniAsl(codASLOperatore);
			if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoOperatoriDistinti);

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			// trovo dati operatore univoco
			Set<Long> elencoIdAuraOperatore = new HashSet<Long>();
			for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
				elencoIdAuraOperatore.add(esenredTEsenzioniReddito.getIdOperatoreRichiesta());
			}

			for (Long idAura : elencoIdAuraOperatore) {
				if (idAura != null) {
					Cittadino c = IntegrationClientImpl.getInstance().getCittadino(idAura.toString());
					if (c == null) {
						c = new Cittadino();
						c.setIdAura(idAura.toString());
					}
					elencoOperatoriDistinti.add(c);
				}
			}
			ArrayList<Cittadino> listaOperatoriDistinti = new ArrayList<Cittadino>(elencoOperatoriDistinti);
			Collections.sort(listaOperatoriDistinti);
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", listaOperatoriDistinti);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			String error = "";
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			if (codASLOperatore != null)
				error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());
			else
				error = messaggioIf.getMessaggio("MSG040").getTesto();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertEsenzioneOperatore")
	public Response insertEsenzioneOperatore(EsenzioneRedditoOperatoreBO esenzioneRedditoOperatoreBO,
			@Context HttpServletRequest req) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			EsenzioneRedditoOperatoreService service = new EsenzioneRedditoOperatoreService();
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			esenzioneRedditoOperatoreBO.setCodFiscaleOperatore(userInfo.getCodFisc());
			ResponseAura r = service.insert(esenzioneRedditoOperatoreBO);
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			res.put("idEsenzione", r.getIdEsenzione());
			res.put("message", r.getMessaggio());

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException e) {
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (EsenzioneInvalidaException e) {
			if (e.getCodice().equalsIgnoreCase(MSG_045)) {
				res.put("status", "ko");
				res.put("code", Status.PRECONDITION_FAILED.getStatusCode());
				res.put("codeAura", null);
				res.put("message", e.getDescrizione());
				if (this.ps != null)
					ps.close();
				if (this.conn != null)
					this.conn.close();
				return corsedResponse.status(Status.PRECONDITION_FAILED).entity(res).build();
			} else {
				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("codeAura", null);
				res.put("message", e.getDescrizione());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertEsenzioneRedditoFamiliare")
	public Response insertEsenzioneRedditoFamiliare(

			EsenzioneRedditoFamiliareBO esenzioneRedditoFamiliareBO) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {

			EsenzioneRedditoFamiliareService esenzioneRedditoFamiliareService = new EsenzioneRedditoFamiliareService();

			ResponseAura r = esenzioneRedditoFamiliareService.insert(esenzioneRedditoFamiliareBO);
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			res.put("idEsenzione", r.getIdEsenzione());
			res.put("message", r.getMessaggio());

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException e) {
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (EsenzioneInvalidaException e) {
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertEsenzioneRedditoAutocertificazione")
	public Response insertEsenzioneRedditoAutocertificazione(

			EsenzioneRedditoAutocertificazioneBO esenzioneRedditoAutocertificazioneBO) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		EsenzioneRedditoAutocertificazioneService service = new EsenzioneRedditoAutocertificazioneService();

		try {
			ResponseAura r = service.insert(esenzioneRedditoAutocertificazioneBO);
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			res.put("idEsenzione", r.getIdEsenzione());
			res.put("message", r.getMessaggio());

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (EsenzioneInvalidaException e) {
			e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());
			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (CheckException e) {
			e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());
			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		}

		catch (Exception e) {
			e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/aggiornaNotifica/{idNotifica}")
	public Response updateNotifica(@PathParam("idNotifica") Long idNotifica) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
			boolean esito = notificaIf.updateSetConsultata(idNotifica);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", esito);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", e.getLocalizedMessage());
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("status", "ko");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getNotificheOperatore/{codFiscaleOperatore}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getNotificheOperatore(

			@PathParam("codFiscaleOperatore") String codFiscale) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<Notifica> listNotificheOut = new ArrayList<Notifica>();

			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(codFiscale));
			if (datiCittadino.isEmpty()) { // cittadino non presente in AURA
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("status", "ok");
				res.put("message", messaggio);
				res.put("data", null);
				return corsedResponse.status(Status.OK).entity(res).build();
			} else { // leggo notifiche
				notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
				List<EsenredWNotifiche> elencoNotifiche = notificaIf
						.getNotificheOperatore(new Long(datiCittadino.get(0).getIdAura()));
				for (EsenredWNotifiche nDB : elencoNotifiche) {
					listNotificheOut.add(new Notifica(nDB));
					// nDB.setFlagConsultazione(new Integer(1));
					// notificaIf.updateNotifica(nDB);
				}
			}
			String messaggio = null;

			if (listNotificheOut.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				messaggio = messaggioIf.getMessaggio("MSG025").getTesto(); // Nessuna notifica ricevuta
			}
			res.put("status", "ok");
			res.put("data", listNotificheOut);
			res.put("message", messaggio);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("data", null);
			res.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getNumeroNuoveNotificheOperatore/{codFiscaleOperatore}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNumeroNuoveNotificheOperatore(@PathParam("codFiscaleOperatore") String codFiscale)
			throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			ArrayList<Integer> numNotifiche = new ArrayList<Integer>();
			int numNuove = 0;
			int numTot = 0;
			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(codFiscale));
			if (datiCittadino.isEmpty()) { // cittadino non presente in AURA
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("status", "ok");
				res.put("message", messaggio);
				res.put("data", null);
				return corsedResponse.status(Status.OK).entity(res).build();
			} else { // leggo notifiche
				notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
				List<EsenredWNotifiche> elencoNotifiche = notificaIf
						.getNotificheOperatore(new Long(datiCittadino.get(0).getIdAura()));
				for (EsenredWNotifiche nDB : elencoNotifiche) {
					numTot++;
					if (nDB.getFlagConsultazione().intValue() == 0)
						numNuove++;
				}
			}
			numNotifiche.add(new Integer(numNuove));
			numNotifiche.add(new Integer(numTot));
			res.put("status", "ok");
			res.put("data", numNotifiche);
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("data", null);
			res.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getNotificheCittadino/{codFiscale}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getNotificheCittadino(
			// @ApiParam(name = "codFiscale", value = "Codice fiscale del cittadino nella
			// relativa anagrafe", required = true)
			@PathParam("codFiscale") String codFiscale) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<Notifica> listNotificheOut = new ArrayList<Notifica>();

			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(codFiscale));
			if (datiCittadino.isEmpty()) { // cittadino non presente in AURA
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("status", "ok");
				res.put("message", messaggio);
				res.put("data", null);
				return corsedResponse.status(Status.OK).entity(res).build();
			} else { // leggo notifiche
				notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
				List<EsenredWNotifiche> elencoNotifiche = notificaIf
						.getNotificheCittadino(new Long(datiCittadino.get(0).getIdAura()));
				for (EsenredWNotifiche nDB : elencoNotifiche) {
					listNotificheOut.add(new Notifica(nDB));
					// nDB.setFlagConsultazione(new Integer(1));
					// notificaIf.updateNotifica(nDB);
				}

			}
			String messaggio = null;

			if (listNotificheOut.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				messaggio = messaggioIf.getMessaggio("MSG025").getTesto(); // Nessuna notifica ricevuta
			}
			res.put("status", "ok");
			res.put("data", listNotificheOut);
			res.put("message", messaggio);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("data", null);
			res.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getNumeroNuoveNotificheCittadino/{codFiscale}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNumeroNuoveNotificheCittadino(@PathParam("codFiscale") String codFiscale) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			int numTot = 0;
			int numNuove = 0;
			ArrayList<Integer> numNotifiche = new ArrayList<Integer>();
			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(codFiscale));
			if (datiCittadino.isEmpty()) { // cittadino non presente in AURA
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("status", "ok");
				res.put("message", messaggio);
				res.put("data", null);
				return corsedResponse.status(Status.OK).entity(res).build();
			} else { // leggo notifiche
				notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
				List<EsenredWNotifiche> elencoNotifiche = notificaIf
						.getNotificheCittadino(new Long(datiCittadino.get(0).getIdAura()));
				for (EsenredWNotifiche nDB : elencoNotifiche) {
					numTot++;
					if (nDB.getFlagConsultazione().intValue() == 0)
						numNuove++;
				}
			}
			numNotifiche.add(new Integer(numNuove));
			numNotifiche.add(new Integer(numTot));
			res.put("status", "ok");
			res.put("data", numNotifiche);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("data", null);
			res.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@PUT
	@Path("/revocaEsenzioneCittadino")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response revocaEsenzioneCittadino(RevocaEsenzioneBO revocaEsenzioneBO) {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {

			EsenzioneRedditoService service = new EsenzioneRedditoService(revocaEsenzioneBO);
			service.checkCampoRevoca();
			service.exists();
			service.isRevocabile();
			ResponseAura r = service.update(revocaEsenzioneBO, null);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			res.put("message", r.getMessaggio());
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException e) {

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDataInizioScadenza/{codEsenzione}")
	public Response getDataInizioScadenza(
			@PathParam("codEsenzione") String codEsenzione) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		esenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
		String ggmm = esenzioneIf.getEsenzioni(codEsenzione).get(0).getDataScadenza().trim();
		DataInizioFine output = null;
		if (ggmm.length()>=5) {
			String ggmmsolo = ggmm.substring(0, 5);
			output = Util.getDataInizioFine(ggmmsolo);
		}
		List<DataInizioFine> data = new ArrayList<DataInizioFine>();
		if (ggmm.length()==10) {
			output.setFineValidita(ggmm);
		}
		data.add(output);
		res.put("status", "ok");
		res.put("data", data);
		res.put("code", Status.OK.getStatusCode());

		return corsedResponse.status(Status.OK).entity(res).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDataInizioScadenzaOld/{codEsenzione}")
	public Response getDataInizioScadenzaOld(
			@PathParam("codEsenzione") String codEsenzione) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
		String ggmm = parametroIf.getParametri("DATA_SCADENZA_PARAMETRIZZATA").get(0).getValore();
		DataInizioFine output = Util.getDataInizioFine(ggmm);
		List<DataInizioFine> data = new ArrayList<DataInizioFine>();
		if (codEsenzione.equalsIgnoreCase("E10")) {
			output.setFineValidita("31/08/2020");
		}
		data.add(output);
		res.put("status", "ok");
		res.put("data", data);
		res.put("code", Status.OK.getStatusCode());

		return corsedResponse.status(Status.OK).entity(res).build();

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancellaNotifica/{idNotifica}")

	public Response deleteNotifica(

			@PathParam("idNotifica") Long idNotifica) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			notificaIf = (NotificaIf) SpringApplicationContext.getBean("notifica");
			boolean esito = notificaIf.deleteNotifica(idNotifica);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", esito);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", e.getLocalizedMessage());
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("status", "ko");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sbloccaEsenzione/{idEsenzione}")
	public Response sbloccaEsenzione(@PathParam("idEsenzione") Long idEsenzione) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			EsenzioneCittadinoIf esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext
					.getBean("esenzioneCittadino");
			esenzioneCittadinoIf.updateSblocco(esenzioneCittadinoIf.getEsenzioneById(idEsenzione).get(0));
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", true);
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			res.put("message", false);
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("status", "ko");
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@PUT
	@Path("/revocaEsenzioneOperatore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response revocaEsenzioneOperatore(

			RevocaEsenzioneBO revocaEsenzioneBO) {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {

			EsenzioneRedditoService service = new EsenzioneRedditoService(revocaEsenzioneBO);
			service.checkCampoRevocaObbligatorio();
			service.checkCampoRevoca();
			service.exists();
			service.isRevocabile();
			ResponseAura r = null;
			String messaggio = null;
			if (revocaEsenzioneBO.isBlocco()) {
				// Revoca esenzioni per familiare
				esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
				List<EsenredTEsenzioniReddito> elencoEsenzioni = esenzioneCittadinoIf
						.getEsenzioneById(revocaEsenzioneBO.getIdEsenzione());
				r = service.update(revocaEsenzioneBO, null);
				List<EsenredTEsenzioniReddito> elencoEsenzioniBlocco = esenzioneCittadinoIf
						.getEsenzioniFamiliariDaBloccare(elencoEsenzioni.get(0).getIdCittadinoBeneficiario(),
								elencoEsenzioni.get(0).getCodEsenzione());
				if (!(elencoEsenzioniBlocco == null || elencoEsenzioniBlocco.isEmpty())) {

					// cicla su tutte quelle che hai trovato revocando su Aura e mettendo R sul db e
					// data revoca
					for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioniBlocco) {
						RevocaEsenzioneBO revocaEsenzioneBOben = new RevocaEsenzioneBO();
						revocaEsenzioneBOben.setIdEsenzione(esenredTEsenzioniReddito.getIdEsenzione());
						revocaEsenzioneBOben.setBlocco(false);
						revocaEsenzioneBOben.setMotivoRevocaEsenzione(esenredTEsenzioniReddito.getDescMotivoRevoca());
						revocaEsenzioneBOben.setNotaInternaRevoca(esenredTEsenzioniReddito.getNota());
						r = service.update(revocaEsenzioneBOben, esenredTEsenzioniReddito);
						if (!"OK".equalsIgnoreCase(r.getEsitoAura())) {
							// una esenzione non viene revocata correttamente indica in un messaggio di
							// revoca parziale
							messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
							messaggio = messaggioIf.getMessaggio("MSG034").getTesto();
						}
					}
				}

			} else {
				// Revoca solo esenzione corrente
				r = service.update(revocaEsenzioneBO, null);
			}

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			if (messaggio == null) {
				res.put("message", r.getMessaggio());
			} else {
				res.put("message", messaggio);
			}
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException e) {

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@PUT
	@Path("/revocaEsenzioneTotaliTitolare/{protocollo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response revocaEsenzioneTotaliTitolare(

			RevocaEsenzioneBO revocaEsenzioneBO, @PathParam("protocollo") String protocollo) {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {

			EsenzioneRedditoService service = new EsenzioneRedditoService(revocaEsenzioneBO);
			service.checkCampoRevocaObbligatorio();
			service.checkCampoRevoca();
			service.exists();
			service.isRevocabile();
			ResponseAura r = null;

			// Revoca solo esenzione corrente
			r = service.update(revocaEsenzioneBO, null);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			res.put("message", " Esenzione con numero protocollo: " + protocollo + " esito: " + r.getMessaggio());
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException e) {

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("codeAura", null);
			res.put("message",
					" Esenzione con numero protocollo: " + protocollo + " esito: " + e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getComuni/{descComune}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComuni(

			@PathParam("descComune") String descComune) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<Comune> listComuniOut = new ArrayList<Comune>();
			comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
			List<EsenredCComuni> elencoComuni = comuneIf.getElencoComuni(descComune);
			for (Iterator<EsenredCComuni> iterator = elencoComuni.iterator(); iterator.hasNext();) {
				EsenredCComuni cDB = (EsenredCComuni) iterator.next();
				listComuniOut.add(new Comune(cDB));
			}
			if (!listComuniOut.isEmpty()) {
				res.put("status", "ok");
				res.put("data", listComuniOut);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/localLogout")

	public void localLogout(@Context HttpServletRequest req) {
		req.getSession(false).setMaxInactiveInterval(1);
		req.getSession().removeAttribute("XSRF_SESSION_TOKEN");
		req.getSession().removeAttribute("JSESSIONID");
		req.getSession().removeAttribute(IrideIdAdapterFilter.IRIDE_ID_SESSIONATTR);
		req.getSession().removeAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		req.getSession().removeAttribute(IrideIdAdapterFilter.AUTH_ID_MARKER);
		req.getSession().invalidate();
	}

	@GET
	@Path("/getUserInfo")
	@Produces(MediaType.APPLICATION_JSON)
	// @ApiOperation(value = "Recupero dei dati dell'utente loggato al sistema")
	public Response getUserInfo(@Context HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		res.put("status", "ok");
		res.put("data", userInfo);
		res.put("code", Status.OK.getStatusCode());
		return corsedResponse.status(Status.OK).entity(res).build();
	}

	private Identita getIdentita(HttpServletRequest req) {
		try {
			it.csi.iride2.policy.entity.Identita identitaTmp = (it.csi.iride2.policy.entity.Identita) req.getSession().getAttribute("iride2_id");
			Identita identitaNew = new Identita();
			
			identitaNew.setCodFiscale(identitaTmp.getCodFiscale());
			identitaNew.setCognome(identitaTmp.getCognome());
//			identitaNew.setLivelloAutenticazione(2); // 17/01/20 Paola mantovani
			identitaNew.setLivelloAutenticazione(identitaTmp.getLivelloAutenticazione());
			identitaNew.setNome(identitaTmp.getNome());
			identitaNew.setMac(identitaTmp.getMac());
			identitaNew.setIdProvider(identitaTmp.getIdProvider()); 
			identitaNew.setTimestamp(identitaTmp.getTimestamp());
	
			return identitaNew;
		} catch (Exception e) { e.printStackTrace();
			return new Identita();
		}
	}

	private Application getApplication() {
		Application app = new Application();
		app.setId("ESENZIONE");
		return app;
	}

	@POST
	@Path("/ruoli")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRuoli(@Context HttpServletRequest req, Cittadino citt) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		log.info("GET Ruoli", " BEGIN");
		try {
			if (citt.getCognome().isEmpty() || citt.getNome().isEmpty())
				citt = IntegrationClientImpl.getInstance().findCittadino(citt).get(0);
			
			Identita identita = getIdentita(req);
			Application application = getApplication();
			
			log.debug("GET Ruoli", " identita: " + identita.getCodFiscale());
			log.debug("GET Ruoli", " application: " + application.getId());

			List<Ruolo> ruoli;
			ruoli = IntegrationClientImpl.getInstance().findRuoli(identita, application);
			if(ruoli!=null && !ruoli.isEmpty()) {
				log.debug("GET Ruoli", " ruoli.size: " + ruoli.size());
				for (Ruolo ruolo : ruoli) {
					log.debug("GET Ruoli", " CodiceRuolo: " + ruolo.getCodiceRuolo());
				}
			}
			log.info("GET Ruoli", " END");
			
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(ruoli).build();
		} catch (Exception e) { e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
	
	private List<Ruolo> getRuoli(HttpServletRequest req, UserInfo user) {		
		try {
			Cittadino citt = new Cittadino();
			citt.setCodFiscale(user.getCodFisc());
			citt.setCognome(user.getCognome());
			citt.setNome(user.getNome());
			return (List<Ruolo>) getRuoli(req,citt).getEntity();
		} catch (Exception e) { e.printStackTrace();
			return null;
		}
	}
	

	@POST
	@Path("/azioni")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAzioni(@Context HttpServletRequest req, Cittadino citt) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		try {

			List<Ruolo> ruoli = (ArrayList<Ruolo>) getRuoli(req,citt).getEntity();

			ruoliIf = (RuoliIf) SpringApplicationContext.getBean("ruoli");
			List<EsenzioneDAzione> azioni = ruoliIf.getAzioni(ruoli);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(azioni).build();
		} catch (Exception e) { e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}
	
	private List<EsenzioneDAzione> getAzioni(@Context HttpServletRequest req,UserInfo user) {		
		try {
			Cittadino citt = new Cittadino();
			citt.setCodFiscale(user.getCodFisc());
			citt.setCognome(user.getCognome());
			citt.setNome(user.getNome());
			return (List<EsenzioneDAzione>) getAzioni(req,citt).getEntity();
		} catch (Exception e) { e.printStackTrace();
			return null;
		}
	}
	
	private List<EsenzioneDAzione> getAzioni(@Context HttpServletRequest req,List<Ruolo> ruoli) {		
		try {
			ruoliIf = (RuoliIf) SpringApplicationContext.getBean("ruoli");
			List<EsenzioneDAzione> azioni = ruoliIf.getAzioni(ruoli);
			return azioni;
		} catch (Exception e) { e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/loginEsenred")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginEsenred(@Context HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		try {
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(userInfo.getCodFisc()));

			if (!datiCittadino.isEmpty()) {
				String idAura = datiCittadino.get(0).getIdAura();

				userInfo.setIdAura(idAura);
				res.put("status", "ok");
				res.put("data", userInfo);
				res.put("code", Status.OK.getStatusCode());
				return corsedResponse.status(Status.OK).entity(res).build();

			}
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
					messaggioIf.getMessaggio("MSG024").getTesto());
			res.put("status", "ok");
			res.put("data", messaggio);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (WebServiceException ws) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = messaggioIf.getMessaggio("MSG041").getTesto();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("data", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		} catch (Exception e) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG023"), e.getLocalizedMessage());
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("data", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/loginEsenpat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginEsenpat(@Context HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		String operazione = "Login";
		String service = "READ";
		try {
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);			
			
			List<Cittadino> datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(userInfo.getCodFisc()));

			registraSuAudit(req.getRemoteAddr(), "1 AnagrafeSanitaria", operazione, "READ", userInfo.getCodFisc(), null,
					null, null, userInfo.getCodFisc(), null);

			Cittadino citt = IntegrationClientImpl.getInstance().getCittadinoEsenpat(datiCittadino.get(0).getIdAura());

			registraSuAudit(req.getRemoteAddr(), "1 AnagrafeSanitaria OK", operazione, "READ", userInfo.getCodFisc(),
					null, null, null, userInfo.getCodFisc(), null);

			registraSuAudit(req.getRemoteAddr(), "1 OK INIT", operazione, service, userInfo.getCodFisc(), null, null,
					null, userInfo.getCodFisc(), null);
			aslOperatoreIf = (AslOperatoreIf) SpringApplicationContext.getBean("aslOperatore");
			String codASLOperatore = aslOperatoreIf.getAslOperatoreCF(userInfo.getCodFisc()).getCodAsl();
			
			userInfo.setCognome(citt.getCognome());
			userInfo.setNome(citt.getNome());
			userInfo.setCodASL(codASLOperatore);
			userInfo.setIdAura(citt.getIdAura());
			
			req.getSession().setAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR,userInfo);

			List<EsenzioneDAzione> resp = (List<EsenzioneDAzione>) getAzioni(req,citt).getEntity();
			userInfo.setAzioni(resp);
			List<Ruolo> ruoliList = (List<Ruolo>) getRuoli(req,citt).getEntity();
			userInfo.setRuoli(ruoliList);
			
			if (resp == null || resp.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = "L'utente � sprovvisto di ruolo/azioni";
				res.put("status", "ko");
				res.put("code", Status.UNAUTHORIZED.getStatusCode());
				res.put("data", messaggio);
				return corsedResponse.status(Status.UNAUTHORIZED).entity(res).build();
			}

			if (!datiCittadino.isEmpty()) {

				registraSuAudit(req.getRemoteAddr(), "1 OK END", operazione, service, userInfo.getCodFisc(), null, null,
						null, userInfo.getCodFisc(), null);

				res.put("status", "ok");
				res.put("data", userInfo);
				res.put("code", Status.OK.getStatusCode());
				return corsedResponse.status(Status.OK).entity(res).build();

			}
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
					messaggioIf.getMessaggio("MSG024").getTesto());
			res.put("status", "ok");
			res.put("data", messaggio);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (WebServiceException ws) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = messaggioIf.getMessaggio("MSG041").getTesto();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("data", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG023"), e.getLocalizedMessage());
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("data", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getMessaggio/{codMessaggio}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessaggio(

			@PathParam("codMessaggio") String codMessaggio) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			List<Messaggio> listMessaggiOut = new ArrayList<Messaggio>();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			List<EsenredCMessaggi> elencoMessaggi = messaggioIf.getMessaggioLike(codMessaggio);

			for (Iterator<EsenredCMessaggi> iterator = elencoMessaggi.iterator(); iterator.hasNext();) {
				EsenredCMessaggi mDB = (EsenredCMessaggi) iterator.next();
				listMessaggiOut.add(new Messaggio(mDB));
			}

			if (!listMessaggiOut.isEmpty()) {
				res.put("status", "ok");
				res.put("data", listMessaggiOut);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Produces("application/pdf")
	@Path("/getReportCertificatoEsenzione/{idEsenzioneCittadino}/{Path}/{cf_beneficiario}")
	public Response getReportCertificatoEsenzione(@PathParam("idEsenzioneCittadino") Integer idEsenzioneCittadino,
			@PathParam("Path") String Path, @PathParam("cf_beneficiario") String cf_beneficiario) throws Exception {

		// leggo l'esenzione
		esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
		EsenredTEsenzioniReddito ese = esenzioneCittadinoIf.getEsenzioneById(idEsenzioneCittadino).get(0);
        Cittadino dichiarante = null;
		Cittadino beneficiario = null;
		Cittadino titolare = null;
		Map<String, Object> parameters = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		// chiamo AURA per individuare i dati del Dichiarante
		if (ese.getIdCittadinoDichiarante()!=null) {
			dichiarante = IntegrationClientImpl.getInstance()
				.getCittadino(ese.getIdCittadinoDichiarante().toString());
			if (ese.getIdCittadinoDichiarante().equals(ese.getIdCittadinoBeneficiario())) {
				beneficiario = dichiarante;
			} else {
				// chiamo AURA per individuare i dati del Beneficiario
				beneficiario = IntegrationClientImpl.getInstance()
						.getCittadino(ese.getIdCittadinoBeneficiario().toString());
			}	
		}
		else if (ese.getCfDichiarantefr()!=null) {
				dichiarante = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(ese.getCfDichiarantefr());
				beneficiario = IntegrationClientImpl.getInstance()
						.getCittadino(ese.getIdCittadinoBeneficiario().toString());
		}
		// chiamo AURA per individuare i dati del Titolare
		// se titolare =beneficiario o dichiarante non vai in aura
		if (ese.getIdCittadinoTitolare() != null) {
			if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoBeneficiario())) {
				titolare = beneficiario;
			} else if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoDichiarante())) {
				titolare = dichiarante;
			} else {
				titolare = IntegrationClientImpl.getInstance().getCittadino(ese.getIdCittadinoTitolare().toString());
			}
		}
		else if (ese.getCfTitolarefr()!=null) {
			if (!ese.getCfDichiarantefr().equalsIgnoreCase(ese.getCfTitolarefr()))
			titolare = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(ese.getCfTitolarefr());
			else
				titolare = dichiarante;
		}
		
		parameters.put("ID_ESENZIONE", idEsenzioneCittadino);

		parameters.put("D_NOME", dichiarante == null ? "" : dichiarante.getNome());
		parameters.put("D_COGNOME", dichiarante == null ? "" : dichiarante.getCognome());
		parameters.put("D_DATANASCITA", dichiarante == null ? "" : dichiarante.getDataNascita());
		parameters.put("D_COMUNENASCITA", dichiarante == null ? "" : dichiarante.getComuneNascita());
		parameters.put("D_PROVINCIANASCITA", dichiarante == null ? "" : dichiarante.getProvinciaNascita());
		parameters.put("D_CODFISCALE", dichiarante == null ? "" : dichiarante.getCodFiscale());

		parameters.put("B_NOME", beneficiario == null ? "" : beneficiario.getNome());
		parameters.put("B_COGNOME", beneficiario == null ? "" : beneficiario.getCognome());
		parameters.put("B_DATANASCITA", beneficiario == null ? "" : beneficiario.getDataNascita());
		parameters.put("B_COMUNENASCITA", beneficiario == null ? "" : beneficiario.getComuneNascita());
		parameters.put("B_PROVINCIANASCITA", beneficiario == null ? "" : beneficiario.getProvinciaNascita());
		parameters.put("B_CODFISCALE", beneficiario == null ? "" : beneficiario.getCodFiscale());

		parameters.put("T_NOME", titolare == null ? "" : titolare.getNome());
		parameters.put("T_COGNOME", titolare == null ? "" : titolare.getCognome());
		parameters.put("T_DATANASCITA", titolare == null ? "" : titolare.getDataNascita());
		parameters.put("T_COMUNENASCITA", titolare == null ? "" : titolare.getComuneNascita());
		parameters.put("T_PROVINCIANASCITA", titolare == null ? "" : titolare.getProvinciaNascita());
		parameters.put("T_CODFISCALE", titolare == null ? "" : titolare.getCodFiscale());

		if (Path.equals("Operatore")) {
			parameters.put("PATH", "Operatore");
		} else {
			parameters.put("PATH", "Cittadino");
		}


		ReportService service = new ReportService();
		byte[] byteArray = service.createReport(parameters);
		if (byteArray.length < 1000) // NO DATA
			return corsedResponse.status(Status.NO_CONTENT).entity(null).build();
		// System.out.println("LUNGHEZZA="+byteArray.length);
		String fileName = null;
		File reportOut = null;
		try {
			fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			reportOut = File.createTempFile(fileName, ".pdf");
			FileUtils.writeByteArrayToFile(reportOut, byteArray);
		} catch (IOException e) {
			reportOut.delete();
			e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		} catch (Exception e) {
			parameters.put("status", "ko");
			parameters.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			parameters.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(parameters).build();
		 }
		reportOut.deleteOnExit();
		return corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName + ".pdf")
				.status(Status.OK).entity(reportOut).build();
	}

	@GET
	@Produces("application/pdf")
	@Path("/getReportCertificatoEsenzioneStorico/{Protocollo}/{CodEsenzione}/{data_inizio_validita}/{Path}/{TipoEsenzione}/{cf_beneficiario}")
	// })
	public Response getReportCertificatoEsenzioneStorico(@PathParam("Protocollo") String Protocollo,
			@PathParam("CodEsenzione") String CodEsenzione,
			@PathParam("data_inizio_validita") String data_inizio_validita, @PathParam("Path") String Path,
			@PathParam("TipoEsenzione") String TipoEsenzione, @PathParam("cf_beneficiario") String cf_beneficiario) throws Exception {

		String datainival = data_inizio_validita;
		EsenredTEsenzioniReddito ese = null;
		if (Checker.isValorizzato(data_inizio_validita)) {
			data_inizio_validita = data_inizio_validita.substring(0, 2) + "/" + data_inizio_validita.substring(2, 4)
					+ "/" + data_inizio_validita.substring(4, 8);
		}
		logger.info("data " + datainival);
		logger.info("dataconslash " + data_inizio_validita);
		logger.info("protocollo " + Protocollo);
		logger.info("esenzione " + CodEsenzione);
		logger.info("Path " + Path);
		logger.info("Tipo Esenzione " + TipoEsenzione);
		try {
			Cittadino c = new Cittadino();
			c.setCodFiscale(cf_beneficiario);
			;
			String idAuraBeneficiario = IntegrationClientImpl.getInstance().findCittadino(c).get(0).getIdAura();

			ese = IntegrationClientImpl.getInstance()
					.getEsenzioni(Protocollo, CodEsenzione, data_inizio_validita, idAuraBeneficiario, "B","StampaEsenzione").get(0);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// chiamo AURA per individuare i dati del Dichiarante
		Cittadino dichiarante = null;
		Cittadino beneficiario = null;
		Cittadino titolare = null;
		if (ese.getIdCittadinoDichiarante()!=null && ese.getIdCittadinoDichiarante()!=0) {
			dichiarante = IntegrationClientImpl.getInstance()
				.getCittadino(ese.getIdCittadinoDichiarante().toString());
		if (ese.getIdCittadinoDichiarante().equals(ese.getIdCittadinoBeneficiario())) {
				beneficiario = dichiarante;
		} else {
			// chiamo AURA per individuare i dati del Beneficiario
				beneficiario = IntegrationClientImpl.getInstance()
						.getCittadino(ese.getIdCittadinoBeneficiario().toString());
			}
		}
		else if (ese.getCfDichiarantefr()!=null) {
				dichiarante = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(ese.getCfDichiarantefr());
				beneficiario = IntegrationClientImpl.getInstance()
						.getCittadino(ese.getIdCittadinoBeneficiario().toString());
		}
		// se dichiarante =beneficiario non vai in aura
		
		// chiamo AURA per individuare i dati del Titolare
		// se titolare =beneficiario o dichiarante non vai in aura
		if (ese.getIdCittadinoTitolare() != null && ese.getIdCittadinoTitolare() != 0) {
			if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoBeneficiario())) {
				titolare = beneficiario;
			} else if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoDichiarante())) {
				titolare = dichiarante;
			} else {
				titolare = IntegrationClientImpl.getInstance().getCittadino(ese.getIdCittadinoTitolare().toString());
			}
		}

		if (ese.getIdCittadinoTitolare() != null && ese.getIdCittadinoTitolare() != 0) {
			if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoBeneficiario())) {
				titolare = beneficiario;
			} else if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoDichiarante())) {
				titolare = dichiarante;
			} else {
				titolare = IntegrationClientImpl.getInstance().getCittadino(ese.getIdCittadinoTitolare().toString());
			}
		}
		else if (ese.getCfTitolarefr()!=null) {
			if (!ese.getCfDichiarantefr().equalsIgnoreCase(ese.getCfTitolarefr()))
			titolare = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(ese.getCfTitolarefr());
			else
				titolare = dichiarante;
		}
		// recupera il titolo dichiarante

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("PROTOCOLLO", ese.getNumProtocolloSogei() == null ? "" : ese.getNumProtocolloSogei());
		parameters.put("COD_ESENZIONE", ese.getCodEsenzione() == null ? "" : ese.getCodEsenzione());
		parameters.put("DATA_INIZIO",
				ese.getDataInizio() == null ? "" : Converter.getData(ese.getDataInizio(), "dd/MM/yyyy"));
		parameters.put("DATA_FINE",
				ese.getDataFine() == null ? "" : Converter.getData(ese.getDataFine(), "dd/MM/yyyy"));
		// parameters.put("DESC_MOTIVO", elencoEsenzioni.get(0).getDescMotivo());
		// parameters.put("DESC_TITOLO_DICHIARANTE",
		// elencoTitoli.get(0).getDescrizione());
		parameters.put("COD_TITOLO_DICHIARANTE",
				ese.getCodTitoloDichiarante() == null ? "0" : ese.getCodTitoloDichiarante());
		parameters.put("NOTA", ese.getNota() == null ? "" : ese.getNota());
		parameters.put("D_NOME", dichiarante == null ? "" : dichiarante.getNome());
		parameters.put("D_COGNOME", dichiarante == null ? "" : dichiarante.getCognome());
		parameters.put("D_DATANASCITA", dichiarante == null ? "" : dichiarante.getDataNascita());
		parameters.put("D_COMUNENASCITA", dichiarante == null ? "" : dichiarante.getComuneNascita());
		parameters.put("D_PROVINCIANASCITA", dichiarante == null ? "" : dichiarante.getProvinciaNascita());
		parameters.put("D_CODFISCALE", dichiarante == null ? "" : dichiarante.getCodFiscale());

		parameters.put("B_NOME", beneficiario == null ? "" : beneficiario.getNome());
		parameters.put("B_COGNOME", beneficiario == null ? "" : beneficiario.getCognome());
		parameters.put("B_DATANASCITA", beneficiario == null ? "" : beneficiario.getDataNascita());
		parameters.put("B_COMUNENASCITA", beneficiario == null ? "" : beneficiario.getComuneNascita());
		parameters.put("B_PROVINCIANASCITA", beneficiario == null ? "" : beneficiario.getProvinciaNascita());
		parameters.put("B_CODFISCALE", beneficiario == null ? "" : beneficiario.getCodFiscale());

		parameters.put("T_NOME", titolare == null ? "" : titolare.getNome());
		parameters.put("T_COGNOME", titolare == null ? "" : titolare.getCognome());
		parameters.put("T_DATANASCITA", titolare == null ? "" : titolare.getDataNascita());
		parameters.put("T_COMUNENASCITA", titolare == null ? "" : titolare.getComuneNascita());
		parameters.put("T_PROVINCIANASCITA", titolare == null ? "" : titolare.getProvinciaNascita());
		parameters.put("T_CODFISCALE", titolare == null ? "" : titolare.getCodFiscale());

		if (Path.equals("Operatore")) {
			parameters.put("PATH", "Operatore");
		} else {
			parameters.put("PATH", "Cittadino");
		}

		if (TipoEsenzione.equals("Autocertificata")) {
			parameters.put("TIPOESENZIONE", "Autocertificata");
		} else {
			parameters.put("TIPOESENZIONE", "Certificata");
		}

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		ReportService service = new ReportService();
		byte[] byteArray = service.createReportStorico(parameters);

		if (byteArray.length < 1000) // NO DATA
			return corsedResponse.status(Status.NO_CONTENT).entity(null).build();
		// System.out.println("LUNGHEZZA="+byteArray.length);
		String fileName = null;
		File reportOut = null;
		try {
			fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			reportOut = File.createTempFile(fileName, ".pdf");

			FileUtils.writeByteArrayToFile(reportOut, byteArray);

		} catch (IOException e) {
			reportOut.delete();
			e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		} catch (Exception e) {
			parameters.put("status", "ko");
			parameters.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			parameters.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(parameters).build();
		 }
		reportOut.deleteOnExit();
		return corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName + ".pdf")
				.status(Status.OK).entity(reportOut).build();
	}


	private Map<String, Object> getMapResponse() {
		Map<String, Object> res = new HashMap<String, Object>();

		return null;
	}

	private String getCodFiscaleDebug(String codFiscale) {
		boolean isDebugValue = false;
		ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
		List<EsenredCParametri> isDebug = parametroIf.getParametri("DEBUG");
		if (!isDebug.isEmpty()) {
			isDebugValue = "true".equalsIgnoreCase(isDebug.get(0).getValore());
			if (isDebugValue) {
				List<EsenredCParametri> cfDebug = parametroIf.getParametri("CF_DEBUG");
				if (!cfDebug.isEmpty())
					return cfDebug.get(0).getValore();
			}
		}
		return codFiscale;
	}

	private List<EsenzioneOperatoreEsenred> getAuraInfoOperatore(List<EsenredTEsenzioniReddito> elencoEsenzioni) {
		List<EsenzioneOperatoreEsenred> elencoEsenzioniOut = new ArrayList<EsenzioneOperatoreEsenred>();

		Set<Long> elencoIdAura = new TreeSet<Long>();
		Set<String> elencoCF = new TreeSet<String>();

		// Riempio l'hashset di idAura/CF di beneficiari e di dichiaranti per fare meno
		// chiamate possibili ad AURA
		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
			if (esenredTEsenzioniReddito.getIdCittadinoDichiarante() == null)
				esenredTEsenzioniReddito.setIdCittadinoDichiarante(new Long(0));
			if (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null)
				esenredTEsenzioniReddito.setIdCittadinoTitolare(new Long(0));

			if (!esenredTEsenzioniReddito.getIdCittadinoBeneficiario().equals(new Long(0)))
				elencoIdAura.add(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());
			if (!esenredTEsenzioniReddito.getIdCittadinoDichiarante().equals(new Long(0)))
				elencoIdAura.add(esenredTEsenzioniReddito.getIdCittadinoDichiarante());
			if (!esenredTEsenzioniReddito.getIdCittadinoTitolare().equals(new Long(0)))
				elencoIdAura.add(esenredTEsenzioniReddito.getIdCittadinoTitolare());
//			else
//				esenredTEsenzioniReddito.setIdCittadinoTitolare(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());

			if (esenredTEsenzioniReddito.getCfDichiarantefr() != null)
				elencoCF.add(esenredTEsenzioniReddito.getCfDichiarantefr());
			if (esenredTEsenzioniReddito.getCfTitolarefr() != null)
				elencoCF.add(esenredTEsenzioniReddito.getCfTitolarefr());
		}

		Set<Cittadino> elencoCittadiniDistinti = new HashSet<Cittadino>();
		for (Long idAura : elencoIdAura) {
			Cittadino c = IntegrationClientImpl.getInstance().getCittadino(idAura.toString());
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAura.toString());
			}
			elencoCittadiniDistinti.add(c);
		}

		for (String codFis : elencoCF) {
			Cittadino c = null;
			try {
				c = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(codFis);
			} catch (Exception e) { e.printStackTrace();
			}
			if (c == null) {
				c = new Cittadino();
				c.setCodFiscale(codFis);
			}
			elencoCittadiniDistinti.add(c);
		}

		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
			Cittadino beneficiario = null;
			Cittadino dichiarante = null;
			Cittadino titolare = null;
			long idAuraBeneficiario = (esenredTEsenzioniReddito.getIdCittadinoBeneficiario() == null ? 0
					: esenredTEsenzioniReddito.getIdCittadinoBeneficiario().longValue());
			long idAuraDichiarante = (esenredTEsenzioniReddito.getIdCittadinoDichiarante() == null ? 0
					: esenredTEsenzioniReddito.getIdCittadinoDichiarante().longValue());
			long idAuraTitolare = (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null ? 0
					: esenredTEsenzioniReddito.getIdCittadinoTitolare().longValue());

			for (Cittadino c : elencoCittadiniDistinti) {
				Long idAuraToTest;
				try {
					idAuraToTest = new Long(c.getIdAura()).longValue();
				} catch (Exception e) { e.printStackTrace();
					idAuraToTest = new Long(0);
				}
				if (idAuraToTest == idAuraBeneficiario)
					beneficiario = c;
				if (idAuraToTest == idAuraDichiarante)
					dichiarante = c;
				if (idAuraToTest == idAuraTitolare)
					titolare = c;
				if (c.getCodFiscale().equals(esenredTEsenzioniReddito.getCfDichiarantefr()))
					dichiarante = c;
				if (c.getCodFiscale().equals(esenredTEsenzioniReddito.getCfTitolarefr()))
					titolare = c;
			}

			if (dichiarante == null) {
				dichiarante = new Cittadino();
				if (esenredTEsenzioniReddito.getIdCittadinoDichiarante() == null)
					dichiarante.setCodFiscale(esenredTEsenzioniReddito.getCfDichiarantefr());
			}
			if (titolare == null) {
				titolare = new Cittadino();
				if (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null)
					titolare.setCodFiscale(esenredTEsenzioniReddito.getCfTitolarefr());
			}

			if (beneficiario != null && dichiarante != null && titolare != null)
				elencoEsenzioniOut.add(
						new EsenzioneOperatoreEsenred(esenredTEsenzioniReddito, beneficiario, titolare, dichiarante));
			// else
			// elencoEsenzioniOut.add(new EsenzioneOperatore(esenredTEsenzioniReddito,
			// beneficiario, dichiarante));
		}
		return elencoEsenzioniOut;
	}

	private List<EsenzioneCittadinoEsenred> getAuraInfo(List<EsenredTEsenzioniReddito> elencoEsenzioni,
			Cittadino dichiarante) {

		List<EsenzioneCittadinoEsenred> elencoEsenzioniOut = new ArrayList<EsenzioneCittadinoEsenred>();

		Set<Long> elencoIdAuraBeneficiari = new HashSet<Long>();
		Set<Long> elencoIdAuraTitolari = new HashSet<Long>();
		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
			Long idAuraBeneficiario = esenredTEsenzioniReddito.getIdCittadinoBeneficiario();
			elencoIdAuraBeneficiari.add(idAuraBeneficiario);
			if (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null) {
				esenredTEsenzioniReddito.setIdCittadinoTitolare(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());
			}

			Long idAuraTitolare = esenredTEsenzioniReddito.getIdCittadinoTitolare();
			elencoIdAuraTitolari.add(idAuraTitolare);

		}

		HashMap<Long, Cittadino> elencoCittadiniBeneficiari = new HashMap<Long, Cittadino>();

		for (Long idAuraBeneficiario : elencoIdAuraBeneficiari) {
			Cittadino c = IntegrationClientImpl.getInstance().getCittadino(idAuraBeneficiario.toString());
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraBeneficiario.toString());
			}
			elencoCittadiniBeneficiari.put(idAuraBeneficiario, c);
		}

		HashMap<Long, Cittadino> elencoCittadiniTitolari = new HashMap<Long, Cittadino>();
		Cittadino c = null;
		for (Long idAuraTitolare : elencoIdAuraTitolari) {
			if (!elencoCittadiniBeneficiari.containsKey(idAuraTitolare)) {
				c = IntegrationClientImpl.getInstance().getCittadino(idAuraTitolare.toString());
			} else {
				c = elencoCittadiniBeneficiari.get(idAuraTitolare);
			}
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraTitolare.toString());
			}
			elencoCittadiniTitolari.put(idAuraTitolare, c);
		}

		for (Iterator<EsenredTEsenzioniReddito> iterator = elencoEsenzioni.iterator(); iterator.hasNext();) { // preserve
																												// order
			EsenredTEsenzioniReddito e = (EsenredTEsenzioniReddito) iterator.next();
			if (e.getIdCittadinoTitolare() == null) {
				e.setIdCittadinoTitolare(e.getIdCittadinoBeneficiario());
			}
			// if (e.getIdCittadinoTitolare()!=null) {
			elencoEsenzioniOut.add(
					new EsenzioneCittadinoEsenred(e, elencoCittadiniBeneficiari.get(e.getIdCittadinoBeneficiario()),
							elencoCittadiniTitolari.get(e.getIdCittadinoTitolare()), dichiarante));
			// } else {
			// elencoEsenzioniOut
			// .add(new EsenzioneCittadino(e,
			// elencoCittadiniBeneficiari.get(e.getIdCittadinoBeneficiario())));
			// }
		}
		return elencoEsenzioniOut;
	}

	private List<EsenzioneAcceleratoreEsenred> getAuraInfoAcc(List<EsenredTEsenzioniReddito> elencoEsenzioni) {

		List<EsenzioneAcceleratoreEsenred> elencoEsenzioniOut = new ArrayList<EsenzioneAcceleratoreEsenred>();

		Set<Long> elencoIdAuraBeneficiari = new HashSet<Long>();
		Set<Long> elencoIdAuraTitolari = new HashSet<Long>();
		Set<Long> elencoIdAuraDichiaranti = new HashSet<Long>();
		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
			Long idAuraBeneficiario = esenredTEsenzioniReddito.getIdCittadinoBeneficiario();
			Long idAuraDichiarante = esenredTEsenzioniReddito.getIdCittadinoDichiarante();
			elencoIdAuraBeneficiari.add(idAuraBeneficiario);
			elencoIdAuraDichiaranti.add(idAuraDichiarante);
			if (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null) {
				esenredTEsenzioniReddito.setIdCittadinoTitolare(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());
			}
			Long idAuraTitolare = esenredTEsenzioniReddito.getIdCittadinoTitolare();
			elencoIdAuraTitolari.add(idAuraTitolare);
		}

		HashMap<Long, Cittadino> elencoCittadiniBeneficiari = new HashMap<Long, Cittadino>();

		for (Long idAuraBeneficiario : elencoIdAuraBeneficiari) {
			Cittadino c = IntegrationClientImpl.getInstance().getCittadino(idAuraBeneficiario.toString());
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraBeneficiario.toString());
			}
			elencoCittadiniBeneficiari.put(idAuraBeneficiario, c);
		}

		HashMap<Long, Cittadino> elencoCittadiniDichiaranti = new HashMap<Long, Cittadino>();
		Cittadino c = null;
		for (Long idAuraDichiarante : elencoIdAuraDichiaranti) {
			if (!elencoCittadiniBeneficiari.containsKey(idAuraDichiarante)) {
				if (idAuraDichiarante != 0)
					c = IntegrationClientImpl.getInstance().getCittadino(idAuraDichiarante.toString());
			} else {
				c = elencoCittadiniBeneficiari.get(idAuraDichiarante);
			}
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraDichiarante.toString());
			}
			elencoCittadiniDichiaranti.put(idAuraDichiarante, c);
		}

		HashMap<Long, Cittadino> elencoCittadiniTitolari = new HashMap<Long, Cittadino>();
		c = null;
		for (Long idAuraTitolare : elencoIdAuraTitolari) {
			if (!elencoCittadiniBeneficiari.containsKey(idAuraTitolare)
					&& !elencoCittadiniDichiaranti.containsKey(idAuraTitolare)) {
				if (idAuraTitolare != 0)
					c = IntegrationClientImpl.getInstance().getCittadino(idAuraTitolare.toString());
			} else if (elencoCittadiniBeneficiari.containsKey(idAuraTitolare)) {
				c = elencoCittadiniBeneficiari.get(idAuraTitolare);
			} else if (elencoCittadiniDichiaranti.containsKey(idAuraTitolare)) {
				c = elencoCittadiniDichiaranti.get(idAuraTitolare);
			}
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraTitolare.toString());
			}
			elencoCittadiniTitolari.put(idAuraTitolare, c);
		}

		for (Iterator<EsenredTEsenzioniReddito> iterator = elencoEsenzioni.iterator(); iterator.hasNext();) { // preserve
																												// order
			EsenredTEsenzioniReddito e = (EsenredTEsenzioniReddito) iterator.next();
			if (e.getIdCittadinoTitolare() == null) {
				e.setIdCittadinoTitolare(e.getIdCittadinoBeneficiario());
			}
			// if (e.getIdCittadinoTitolare()!=null) {
			elencoEsenzioniOut.add(
					new EsenzioneAcceleratoreEsenred(e, elencoCittadiniBeneficiari.get(e.getIdCittadinoBeneficiario()),
							elencoCittadiniDichiaranti.get(e.getIdCittadinoDichiarante()),
							elencoCittadiniTitolari.get(e.getIdCittadinoTitolare())));
			//
			// } else {
			// elencoEsenzioniOut
			// .add(new EsenzioneCittadino(e,
			// elencoCittadiniBeneficiari.get(e.getIdCittadinoBeneficiario())));
			// }
		}
		return elencoEsenzioniOut;
	}

	private List<ListaBeneficiari> getAuraInfoBeneficiari(List<Long> elencoEsenzioni) {

		List<ListaBeneficiari> elencoEsenzioniOut = new ArrayList<ListaBeneficiari>();

		Set<Long> elencoIdAuraBeneficiari = new HashSet<Long>();
		for (Long esenredTEsenzioniReddito : elencoEsenzioni) {
			Long idAuraBeneficiario = new Long(esenredTEsenzioniReddito);
			elencoIdAuraBeneficiari.add(idAuraBeneficiario);
		}

		HashMap<Long, Cittadino> elencoCittadiniBeneficiari = new HashMap<Long, Cittadino>();

		for (Long idAuraBeneficiario : elencoIdAuraBeneficiari) {
			Cittadino c = IntegrationClientImpl.getInstance().getCittadino(idAuraBeneficiario.toString());
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraBeneficiario.toString());
			}
			elencoEsenzioniOut.add(new ListaBeneficiari(c));
			// elencoCittadiniBeneficiari.put(idAuraBeneficiario, c);
		}

		return elencoEsenzioniOut;
	}

	private List<ListaTitolari> getAuraInfoTitolari(List<Long> elencoEsenzioni) {

		List<ListaTitolari> elencoEsenzioniOut = new ArrayList<ListaTitolari>();

		Set<Long> elencoIdAuraTitolari = new HashSet<Long>();
		for (Long esenredTEsenzioniReddito : elencoEsenzioni) {
			Long idAuraTitolare = new Long(esenredTEsenzioniReddito);
			elencoIdAuraTitolari.add(idAuraTitolare);
		}

		HashMap<Long, Cittadino> elencoCittadiniTitolari = new HashMap<Long, Cittadino>();

		for (Long idAuraTitolare : elencoIdAuraTitolari) {
			Cittadino c = IntegrationClientImpl.getInstance().getCittadino(idAuraTitolare.toString());
			if (c == null) {
				c = new Cittadino();
				c.setIdAura(idAuraTitolare.toString());
			}
			elencoEsenzioniOut.add(new ListaTitolari(c));
			// elencoCittadiniBeneficiari.put(idAuraBeneficiario, c);
		}

		return elencoEsenzioniOut;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findCittadinoFuoriRegione/{codFiscale}")
	public Response getCittadinoFuoriRegione(@PathParam("codFiscale") String codFiscale) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			Cittadino esenredTCittadino = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(codFiscale);
			
			if (esenredTCittadino != null) {

				esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
				
				if (esenzioneCittadinoIf.getBloccoCittadino(codFiscale)) {
					esenredTCittadino.setStato("B");
				}
				
				ArrayList<Cittadino> listCittadino = new ArrayList<Cittadino>();
				listCittadino.add(esenredTCittadino);

				res.put("status", "ok");
				res.put("data", listCittadino);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}

			res.put("status", "ok");
			res.put("data", new ArrayList<Cittadino>());
			res.put("code", Status.OK.getStatusCode());
			res.put("message", messaggioIf.getMessaggio("MSG024").getTesto());

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@PUT
	@Path("/revocaEsenzioneCertificataOperatore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response revocaEsenzioneCertificataOperatore(@Context HttpServletRequest req,
			RevocaEsenzioneCertificataBO revocaEsenzioneBO) {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		if(revocaEsenzioneBO.getCodiceFiscaleChiamante()==null) revocaEsenzioneBO.setCodiceFiscaleChiamante(((UserInfo)req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR)).getCodFisc());
		
		try {
			List<Cittadino> datiCittadino = null;
			String idAura = null;
			EsenredTEsenzioniReddito esenzionesel = null;
			datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(revocaEsenzioneBO.getCodiceFiscaleAssistito()));
			if (datiCittadino.isEmpty()) {
		        EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
		        String msg010Testo = Util.composeMessage(msg010, "beneficiario non presente in Aura");
		        throw new CheckException("MSG010", msg010Testo);
		    }
			idAura = datiCittadino.get(0).getIdAura();
			List<EsenredTEsenzioniReddito> esenzioni = IntegrationClientImpl.getInstance().getEsenzioni(null, revocaEsenzioneBO.getCodiceEsenzione(), revocaEsenzioneBO.getDataInizioValidita(),idAura , "B","RevocaCertificate");
			if (esenzioni.isEmpty()) {
		        EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
		        String msg010Testo = Util.composeMessage(msg010, "esenzione non presente in archivio");
		        throw new CheckException("MSG010", msg010Testo);
		    }
			esenzionesel = esenzioni.get(0);
			
			EsenzioneRedditoCertService service = new EsenzioneRedditoCertService(revocaEsenzioneBO);
			service.checkCampoRevocaObbligatorio();
			service.checkCampoRevoca();
			service.isRevocabile(esenzionesel);
			ResponseAura r = null;
			String messaggio = null;

			r = service.update(revocaEsenzioneBO, esenzionesel,datiCittadino.get(0).getCodFiscale(),idAura);
			
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("codeAura", r.getCodAura());
			if (messaggio == null) {
				res.put("message", r.getMessaggio());
			} else {
				res.put("message", messaggio);
			}
			return corsedResponse.status(Status.OK).entity(res).build();
			} catch (CheckException e) {

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("codeAura", null);
			res.put("message", e.getDescrizione());

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();	
		} catch (Exception e) {

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	/*
	 * CDU 20 Ricerca Assistito
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ricercaAssistito")
	public Response ricercaAssistito(@Context HttpServletRequest req, FiltriRicercaAssistito filtri) {

		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		String operazione = "Ricerca Assistito";
		String service = "READ";
		String cfMed = null;
		String cfMedCert = null;
		try {
			UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			UserInfo assistito = filtri.getAssistito();
			
			List<Ruolo> ruoli = getRuoli(req,utente);
			boolean cas = false;
			for (Ruolo ruolo : ruoli) {
				// medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
					cas = true;
				}
				
				parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("FILTRO_MOSTRA_SOLO_ESENZIONI_CREATE");
				boolean mostraSoloEsenzioniCreate = Boolean.parseBoolean(elencoParametri.get(0).getValore());
				if(ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_SPECIALISTA)) {
					cfMedCert = utente.getCodFisc();
					cfMed = mostraSoloEsenzioniCreate ? cfMedCert : null; 
				}
			}
			filtri.setUtente(utente);

			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					assistito.getCodFisc(), null);

			if (!checkAzioneUtente(req,"OP-InserisciNuovoCertificato",ruoli)
					&& !checkAzioneUtente(req,"OP-InserisciNuovaEsenzione", ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, operazione, service, utente.getCodFisc(), null,
						null, null, filtri.getAssistito().getCodFisc(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			CheckRicercaCittadini.chkFiltriAssistito(filtri);

			CittadinoFacade cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");

			List<EsenzioneTCittadino> elencoAssistiti = cittadinoFacade.getAssistito(assistito, utente, cas);

			if (elencoAssistiti == null || elencoAssistiti.size() == 0) {

				if (assistito.getCodFisc() == null || assistito.getCodFisc() == "") {

					throw new CheckException(Constants.MSG211);

				} else {

					res = verificaCittadinoSuAura(req.getRemoteAddr(), operazione, utente, assistito.getCodFisc(),ruoli);

				}
			} else if (elencoAssistiti.size() > 1) {
				// PIU' ASSISTITI TROVATI
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
						assistito.getCodFisc(), null);
				List<Assistito> assistiti = new ArrayList<Assistito>(elencoAssistiti.size());
				for (EsenzioneTCittadino c : elencoAssistiti) {
					assistiti.add(new Assistito(c));
				}
				res.put("elenco_assistiti", assistiti);
				return corsedResponse.status(Status.OK).entity(res).build();

			} else {
				// SINGOLO ASSISTITO
				res = verificaCittadinoSuAura(req.getRemoteAddr(), operazione, utente,
						elencoAssistiti.get(0).getCodiceFiscale(),ruoli);
			}

			if (res.containsKey(CITTADINO_AURA)) {
				// Assistito trovato e valido
				CittadinoEsenpat assis = (CittadinoEsenpat) res.get(CITTADINO_AURA);

				EsenzioniFacade esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
				Set<EsenzioneAssistito> listaEsenzioni = esenzioniFacade
						.getListaEsenzioniByCodiceFiscaleAssistito(assis, utente, cfMed);

				CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext
						.getBean("certificatiFacade");
				List<CertificatoAssistito> listaCertificati = certificatiFacade
						.getListaCertificatiByCodiceFiscaleAssistito(assis.getCodFiscale(), cfMedCert);

				res = new HashMap<String, Object>();

				res.put("assistito", new Assistito(assis));
				res.put("lista_esenzioni", listaEsenzioni);
				res.put("lista_certificati", listaCertificati);

			} else {
				registraSuAudit(req.getRemoteAddr(), "0 KO", operazione, service, utente.getCodFisc(), null, null, null,
						assistito.getCodFisc(), null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (CheckException chEx) {

			String error = getMessaggioEsenpat(chEx.getCodice()).getTesto();
			// TODO ricontrollare codici errori
			registraSuAudit(req.getRemoteAddr(), "0 KO " + error, operazione, service, filtri.getUtente().getCodFisc(),
					null, null, null, filtri.getAssistito().getCodFisc(), null);

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception e) { e.printStackTrace();
			res = new HashMap<String, Object>();
			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

			registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, operazione, service,
					filtri.getUtente().getCodFisc(), null, null, null, filtri.getAssistito().getCodFisc(),
					null);

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}

	/*
	 * CDU 05 Dettaglio Certificato
	 */
	@GET
	@Path("/cittadini/{cit_id}/certificati/{certificato_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDettaglioCertificato(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id,
			@PathParam("certificato_id") String certificato_id) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		DettaglioCertificato dettaglioCertificato = null;
		String operazione = "Dettaglio certificato";
		String service = "READ";

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		try {

			
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					cit_id, null);

			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(utente.getCodFisc())
					|| !Checker.isValorizzato(certificato_id)) {
				res = new HashMap<String, Object>();
				String messaggio145 = getMessaggioEsenpat(Constants.MSG145).getTesto();

				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio145, operazione, service, utente.getCodFisc(),
						null, null, null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", messaggio145);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(utente.getCodFisc(), isTst)
					|| !Checker.isNumericString(certificato_id)) {
				res = new HashMap<String, Object>();
				String messaggio149 = getMessaggioEsenpat(Constants.MSG149).getTesto();

				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio149, operazione, service, utente.getCodFisc(),
						null, null, null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", messaggio149);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(req.getRemoteAddr(), operazione, utente, cit_id, ruoli);

			if (res.containsKey(CITTADINO_AURA)) {

				res = new HashMap<String, Object>();

				// Ricerca dettaglio certificato
				certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
				dettaglioCertificato = certificatiFacade.getDettaglioCertificatoLotto2(cit_id, certificato_id);

				
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
						cit_id, null);
			} else {
				registraSuAudit(req.getRemoteAddr(), "0 KO", operazione, service, utente.getCodFisc(), null, null, null,
						cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			return corsedResponse.status(Status.OK).entity(dettaglioCertificato).build();

		} catch (Exception e) { e.printStackTrace();
			res = new HashMap<String, Object>();
			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

			registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, operazione, service, utente.getCodFisc(), null,
					null, null, cit_id, null);

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	/*
	 * CDU 03 Scarica Certificato
	 */
	@GET
	@Path("/cittadini/{cit_id}/certificati/{certificato_id}/pdf")
	@Produces("application/pdf")
	public Response getCertificatoMalattia(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id,
			@PathParam("certificato_id") String certificato_id) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		String fileName = null;
		File filePdf = null;
		String operazione = "Scarica certificato";
		String service = "READ";

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		try {
			
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					cit_id, null);

			if (!checkAzioneUtente(req,"OP-ScaricaCertificato", ruoli)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, operazione, service, utente.getCodFisc(), null,
						null, null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			if (!Checker.isValorizzato(certificato_id) || !Checker.isValorizzato(cit_id)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				String messaggio145 = getMessaggioEsenpat(Constants.MSG145).getTesto();

				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio145, operazione, service, utente.getCodFisc(),
						null, null, null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", messaggio145);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(utente.getCodFisc(), isTst)
					|| !Checker.isNumericString(certificato_id)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				String messaggio149 = getMessaggioEsenpat(Constants.MSG149).getTesto();

				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio149, operazione, service, utente.getCodFisc(),
						null, null, null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", messaggio149);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			res = new HashMap<String, Object>();

			// Ricerca certificato malattia
			certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
			EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = certificatiFacade
					.getCertificatoMalattia(certificato_id);

			if (esenzioneTRepositoryDocumentale == null) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				// gestione errore 404
				res = new HashMap<String, Object>();
				String messaggio005 = getMessaggioEsenpat(Constants.MSG005).getTesto();
				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio005, operazione, service, utente.getCodFisc(),
						null, null, null, cit_id, null);
				res.put("status", "ko");
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", messaggio005);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();

			} else {

				byte[] byteArray = Base64.getDecoder().decode(esenzioneTRepositoryDocumentale.getFile());
				fileName = Constants.PREFISSO_NOME_FILE_CERTIFICATO_MALATTIA + "_"
						+ esenzioneTRepositoryDocumentale.getSkRepository() + "_"
						+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
				filePdf = File.createTempFile(fileName, ".pdf");
				FileUtils.writeByteArrayToFile(filePdf, byteArray);
			}
			if (filePdf == null) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				throw new Exception();
			}
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					cit_id, null);

			corsedResponse.header("Content-Disposition", "attachment; filename=" + filePdf.getName());
			filePdf.deleteOnExit();
			return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition")
					.status(Status.OK).entity(filePdf).build();

		} catch (Exception e) { e.printStackTrace();
			filePdf.delete();
			res = new HashMap<String, Object>();
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

			registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, operazione, service, utente.getCodFisc(), null,
					null, null, cit_id, null);

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@POST
	@Path("/cittadini/dettaglio-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDettaglioEsenzione(@Context HttpServletRequest req, FiltroDettaglioEsenzione filter)
			throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		String operazione = "Dettaglio esenzione";
		String service = "READ";
		EsenzioneCittadinoEsenpat result = null;
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		try {
			
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					filter.getCfBeneficiario(), null);

			if (!Checker.isValorizzato(filter.getCfBeneficiario())) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				String messaggio145 = getMessaggioEsenpat(Constants.MSG145).getTesto();

				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio145, operazione, service, utente.getCodFisc(),
						null, null, null, filter.getCfBeneficiario(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", messaggio145);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			} 

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(filter.getCfBeneficiario(), isTst)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				String messaggio149 = getMessaggioEsenpat(Constants.MSG149).getTesto();

				registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio149, operazione, service,
						utente.getCodFisc(), null, null, null, filter.getCfBeneficiario(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", messaggio149);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			String cit_id = filter.getCfBeneficiario();

			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(req.getRemoteAddr(), operazione, utente, cit_id, ruoli);
			if (res.containsKey(CITTADINO_AURA)) {
				CittadinoEsenpat assis = (CittadinoEsenpat) res.get(CITTADINO_AURA);
				res = new HashMap<String, Object>();
				esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
				result = esenzioniFacade.getDettaglioEsenzione(assis, filter, utente);
			} else {
				registraSuAudit(req.getRemoteAddr(), "0 KO", operazione, service, utente.getCodFisc(), null, null, null,
						cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					cit_id, null);

			return corsedResponse.status(Status.OK).entity(result).build();

		} catch (CheckException chEx) {

			String error = getMessaggioEsenpat(chEx.getCodice()).getTesto();
			// TODO ricontrollare codici errori
			registraSuAudit(req.getRemoteAddr(), "0 KO " + error, operazione, service, utente.getCodFisc(), null, null,
					null, filter.getCfBeneficiario(), null);

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res = new HashMap<String, Object>();
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

			registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, operazione, service, utente.getCodFisc(), null,
					null, null, filter.getCfBeneficiario(), null);

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getPrestazioniEsenti/{cod_diagnosi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrestazioniEsenti(@Context HttpServletRequest req, @PathParam("cod_diagnosi") String codDiagnosi)
			throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			if (!Checker.isValorizzato(codDiagnosi)) {
				throw new Exception();
			}

			EsenzioniFacade esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			List<Prestazioni> prestazioniEsenti = esenzioniFacade.getPrestazioniEsenti(codDiagnosi);

			return corsedResponse.status(Status.OK).entity(prestazioniEsenti).build();

		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}


	@POST
	@Path("/getPratichePaginato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPratichePaginato(@Context HttpServletRequest req, FiltriRicercaPratiche filtri)
			throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		final String service = "Ricerca Pratiche";
		final String operazione = "READ";
		List<EsenzioneAssistito> elencoEsenzioniOut = new ArrayList<EsenzioneAssistito>();
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		try {

			
			registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, utente.getCodFisc(), null, null, null,
					filtri.getCfBeneficiario(), null);

			if (!checkAzioneUtente(req, "OP-RicercaPraticheDaFare",ruoli)
					&& !checkAzioneUtente(req, "OP-RicercaPraticheTutteLePratiche",ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, service, operazione, utente.getCodFisc(), null,
						null, null, filtri.getCfBeneficiario(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			CheckRicercaEsenzioneOperatore.chkFiltriPratica(filtri);

			String codASLOperatore = null;

			for (Ruolo ruolo : ruoli) {
				// medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
					codASLOperatore = null;
					break;
				} else
					codASLOperatore = utente.getCodASL();
			}

			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ELEMENTI_RICERCA");
			int dimensionePagina = Integer.parseInt(elencoParametri.get(0).getValore());

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");

			List<EsenzioneAssistito> elencoEsenzioni = esenzioniFacade.getPraticheFiltrate(filtri, codASLOperatore,
					utente, dimensionePagina);

			if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();

				registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, utente.getCodFisc(), null, null, null,
						filtri.getCfBeneficiario(), null);

				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioni);

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			if (filtri.getTab().equalsIgnoreCase(Constants.TAB_DA_FARE)) {
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("data", elencoEsenzioni);
			}
			if (filtri.getTab().equalsIgnoreCase(Constants.TAB_TUTTE_LE_PRATICHE)) {
				boolean check = false;
				for (StatoDocumento s : filtri.getStatiPratica()) {
					if (s.getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA)) {
						check = true;
						break;
					}
				}
				if (check && (filtri.getPagina() != 0 && (elencoEsenzioni.size() > dimensionePagina))) {
					int lastElement;
					if (filtri.getPagina() == ((int) Math.ceil(elencoEsenzioni.size() / dimensionePagina) + 1))
						lastElement = elencoEsenzioni.size();
					else
						lastElement = dimensionePagina * filtri.getPagina();
					int FirstElement = dimensionePagina * filtri.getPagina();
					List<EsenzioneAssistito> elencoEsenzioniTmp = elencoEsenzioni
							.subList(FirstElement - dimensionePagina, lastElement);

					elencoEsenzioniOut = new ArrayList<EsenzioneAssistito>(elencoEsenzioniTmp);

				} else {
					elencoEsenzioniOut = new ArrayList<EsenzioneAssistito>(elencoEsenzioni);
				}

				for (EsenzioneAssistito ese : elencoEsenzioniOut) {
					ese.setNumeroTotaleElementi(elencoEsenzioni.size());
				}

				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("data", elencoEsenzioniOut);
			}

			// Tracciatura della risposta
			registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, utente.getCodFisc(), null, null, null,
					filtri.getCfBeneficiario(), null);

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());
			registraSuAudit(req.getRemoteAddr(), "0 KO", service, operazione, utente.getCodFisc(), null, null, null,
					filtri.getCfBeneficiario(), null);
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());
			registraSuAudit(req.getRemoteAddr(), "0 KO", service, operazione, utente.getCodFisc(), null, null, null,
					filtri.getCfBeneficiario(), null);
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/motivazioni/{cod_stato}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMotivazioniCombo(@Context HttpServletRequest req, @PathParam("cod_stato") String codStato) {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			statoPraticaIf = (StatoPraticaIf) SpringApplicationContext.getBean("statopratica");
			List<Motivazione> motivazioni = statoPraticaIf.getMotivazioniByCodStatoPratica(codStato);

			if (!motivazioni.isEmpty()) {
				res.put("status", "ok");
				res.put("data", motivazioni);
				res.put("code", Status.OK.getStatusCode());
				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}

	}

	protected EsenredCMessaggi getMessaggioEsenpat(String msg) {
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
		// checkAmbiente
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
		// checkAmbiente
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

	protected void registraSuAudit(String ipAddress, String keyOper, String oggOper, String operazione, String utente,
			String idRequest, String uuId, String xcodServizio, String utenteBen, String utenteDel) {
		try {
			getAmbiente();
			String appId = "ESENZIONI_RP-01_TEST_ESENPAT";
			if (ambiente != null) {
				appId = "ESENZIONI_RP-01_" + ambiente + "_ESENPAT";
			}

			auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
			// if (utenteDel.equalsIgnoreCase(utenteBen))
			// utenteDel = null;
			CsiLogAudit audit = new CsiLogAudit(appId, ipAddress, keyOper, oggOper, operazione, utente, idRequest, uuId,
					xcodServizio, utenteBen, utenteDel);
			auditIf.insertAuditEsenpat(audit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Map<String, Object> verificaCittadinoSuAura(String ipAddress, String operazione, UserInfo utente,
			String cfAssistito, List<Ruolo> ruoli) {
		Map<String, Object> res = new LinkedHashMap<String, Object>();

		try {
			registraSuAudit(ipAddress, "1 AnagrafeFind", operazione, "READ", utente.getCodFisc(), null, null, null,
					cfAssistito, null);

			List<Cittadino> datiAssistito = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(cfAssistito));

			registraSuAudit(ipAddress, "1 AnagrafeFind OK", operazione, "READ", utente.getCodFisc(), null, null, null,
					cfAssistito, null);

			if (datiAssistito == null || datiAssistito.isEmpty()) {
				throw new AuraIntegrationException("E0010",
						"Non esistono profili anagrafici che rispondono ai parametri indicati");
			}

			registraSuAudit(ipAddress, "1 AnagrafeSanitaria", operazione, "READ", utente.getCodFisc(), null, null, null,
					cfAssistito, null);

			// cittadino trovato su AURA
			CittadinoEsenpat cittadino = IntegrationClientImpl.getInstance()
					.getCittadinoEsenpat(datiAssistito.get(0).getIdAura());

			registraSuAudit(ipAddress, "1 AnagrafeSanitaria OK", operazione, "READ", utente.getCodFisc(), null, null,
					null, cfAssistito, null);

			boolean cas = false;

			if (isCittadinoPiemontese(cittadino)) {
				for (Ruolo r : ruoli) {
					if (r.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
							|| r.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) { // Medico CAS
						cas = true;
					}
				}
				if (!cas && !(cittadino.getCodASL().equalsIgnoreCase(utente.getCodASL()))) {
					throw new AuraIntegrationException("E0010",
							"Non esistono profili anagrafici che rispondono ai parametri indicati");
				} else {
					res.put(CITTADINO_AURA, cittadino);
				}

			} else {
				String messaggio214 = getMessaggioEsenpat(Constants.MSG214).getTesto();
				res.put("status", "ko");
				res.put("code", Constants.MSG214);
				res.put("message", messaggio214);
			}

			return res;

		} catch (AuraIntegrationException auraEx) {
			registraSuAudit(ipAddress, auraEx.getCodice() + " " + auraEx.getDescrizione(), operazione, "READ",
					utente.getCodFisc(), null, null, null, cfAssistito, null);
			res.put("status", "ko");
			res.put("code", auraEx.getCodice());
			res.put("message", auraEx.getDescrizione());
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			String messaggio012 = getMessaggioEsenpat(Constants.MSG012).getTesto();
			res.put("status", "ko");
			res.put("code", Constants.MSG012);
			res.put("message", messaggio012);
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



	
	
	@PUT
	@Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/annullamento")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setAnnullamentoEsenzione(@Context HttpServletRequest req,
			@PathParam("cit_id") String citId,
			@PathParam("esenzione_id") String esenzioneId, MotivazioneAnnullamento motivazioneAnnullamento)
					throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null; 
		String xcodServizio = null;

		final String service = "Annullamento esenzione";
		final String operation = "UPDATE";

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		try {

			 al servizio ANNULLA
			registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, citId, null);

			if (!checkAzioneUtente(req,Constants.OP_ANNULLAREVOCA, ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210.getTesto(), service, operation,
						shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}


			
			if (!Checker.isValorizzato(citId) || !Checker.isValorizzato(esenzioneId)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(),
						service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(citId, isTst) || !Checker.isNumericString(esenzioneId)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(),
						service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(req.getRemoteAddr(), service, utente, citId, ruoli);

			if (res.containsKey(CITTADINO_AURA)) {

				esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade"); 
				EsenzioneTPraticaEsenzione ecPre = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId.toString()));
				
				if (ecPre != null) {
					if (ecPre.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_VALIDATA)) {
						CittadinoEsenpat beneficiario = (CittadinoEsenpat) res.get(CITTADINO_AURA);
						String dataInizioValidita = ecPre.getDatInizioValidita() == null ? null : Converter.getData(ecPre.getDatInizioValidita(), "dd/MM/yyyy");
//						String dataFineValidita = ecPre.getDatFineValidita() == null ? null : Converter.getData(ecPre.getDatFineValidita(), "dd/MM/yyyy");
						EsenzionePatologia epReq = new EsenzionePatologia();
						epReq.setCodiceFiscaleChiamante(shibIdentitaCodiceFiscale);
						epReq.setCodiceFiscaleAssistito(beneficiario.getCodFiscale());
						epReq.setCodiceEsenzione(ecPre.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
						epReq.setDataInizioValidita(dataInizioValidita);
						epReq.setDataFineValidita(Converter.getData(new Date(),"dd/MM/yyyy"));
						epReq.setDataAnnullamento(Converter.getData(new Date(),"dd/MM/yyyy"));
						epReq.setListaDiagnosi(ecPre.getEsenzioneDDiagnosi().getCodDiagnosi().startsWith("F") ? "000"
										: ecPre.getEsenzioneDDiagnosi().getCodDiagnosi());
						EsenzionePatologiaRes epRes = IntegrationClientImpl.getInstance().InserisciEsenzionePatologia(epReq);
						
						if ("OK".equalsIgnoreCase(epRes.getHeader().getCodiceRitorno())) { //solo se Aura mi da OK annullo l'esenzione in ESENPAT

							res = new HashMap<String, Object>();
							res = verificaCittadinoSuAura(req.getRemoteAddr(), service, utente, citId, ruoli);

							if (res.containsKey(CITTADINO_AURA)) {
								beneficiario = (CittadinoEsenpat) res.get(CITTADINO_AURA);

								EsenzioneCittadinoEsenpat ec = esenzioniFacade.annullaEsenzione(esenzioneId,
										motivazioneAnnullamento, citId, utente, beneficiario, ruoli);

								registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale,
										xRequestID, null, xcodServizio, citId, null);
								return corsedResponse.status(Status.OK).entity(ec).build();
							} else {
								registraSuAudit(req.getRemoteAddr(), "0 KO", service, operation,
										shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId,
										null);
								return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
							}
						} else {
							registraSuAudit(XForwardedFor, "0 ko", service, operation, shibIdentitaCodiceFiscale,
									xRequestID, null, xcodServizio, citId, null);
							res = new HashMap<String, Object>();
							res = generateResponseErrore(epRes.getHeader().getListaMessaggi().get(0).getCodice(), epRes.getHeader().getListaMessaggi().get(0).getDescrizione(), Status.BAD_REQUEST.getStatusCode());
							return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
						}
					} else if (ecPre.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)
							|| ecPre.getEsenzioneDPraticaStato().getCodStato()
									.equals(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
						EsenzioneCittadinoEsenpat ec = esenzioniFacade.annullaEsenzione(esenzioneId,
								motivazioneAnnullamento, citId, null, null, null);
						registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale,
								xRequestID, null, xcodServizio, citId, null);
						return corsedResponse.status(Status.OK).entity(ec).build();
					} else
						throw new Exception();
				}
			} else {
				registraSuAudit(req.getRemoteAddr(), "0 KO", service, operation, shibIdentitaCodiceFiscale, xRequestID,
						null, xcodServizio, citId, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (EsenpatException e) {
			e.printStackTrace();
			EsenredCMessaggi messaggio = getMessaggioEsenpat(e.getCodMessaggio());
			res = generateResponseErrore(messaggio.getTesto(), e.getCodice(),e.getStatus());
			registraSuAudit(XForwardedFor, "0 KO " + messaggio.getTesto(), service, operation,
					shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);
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
	


	@GET 
	@Path("/esenzioni/{cit_id}/pdf") 
	@Produces("application/pdf")
	public Response getAttestatoEsenzione(@Context HttpServletRequest req,
			@PathParam("cit_id") String cit_id) throws Exception
	{
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		AttestatoEsenzione attestatoEsenzione = null;

		// String xRequestID = req.getHeader("HTTP_X_REQUEST_ID");
		// String XForwardedFor = req.getHeader("X-FORWARDED-FOR");
		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		
		String oggOper = "Attestato esenzione";
		String operazione = "READ";

		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrente = Constants.OP_SCARICA_ATTESTATO;
			boolean bCheckAzione = checkAzioneUtente(req,azioneCorrente,ruoli);
			if (!bCheckAzione) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(XForwardedFor, "Attestato esenzione", utente, cit_id, ruoli);

			if (res.containsKey(CITTADINO_AURA)) {
				res = new HashMap<String, Object>();

				// Ricerca attestato esenzione
				certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
				attestatoEsenzione = certificatiFacade.getAttestatoEsenzione(cit_id);

				if (attestatoEsenzione == null) {
					// gestione errore 404
					corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
					res = new HashMap<String, Object>();
					EsenredCMessaggi messaggio005 = getMessaggioEsenpat(Constants.MSG005);
					registraSuAudit(XForwardedFor, Status.NOT_FOUND.getStatusCode() + " " + messaggio005.getTesto(), oggOper,
							operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
					res.put("status", Status.NOT_FOUND.getStatusCode());
					res.put("code", "Attestato non trovato");
					res.put("message", messaggio005.getTesto());

					return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
				}
				
				registraSuAudit(XForwardedFor, "1 ok", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID, null,
						xcodServizio, cit_id, null);
			} else {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				registraSuAudit(req.getRemoteAddr(), "0 KO", oggOper, operazione, utente.getCodFisc(), null, null, null,
						cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			File pdf = null;
			String fileName = "Attestato_esenzione_" + cit_id;
			pdf = File.createTempFile(fileName, ".pdf");
			FileUtils.writeByteArrayToFile(pdf, attestatoEsenzione.getDocumentazioneAllegata());

			corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName);
			pdf.deleteOnExit();
			return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition")
					.status(Status.OK).entity(pdf).build();

		} catch (Exception e) {
			e.printStackTrace();
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			
			registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, oggOper, operazione, utente.getCodFisc(), null,
					null, null, cit_id, null);

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	private boolean checkAzioneUtente(@Context HttpServletRequest req, final String azioneCorrente, List<Ruolo> ruoli) {
		boolean bCheckAzione = false;
		Iterator<EsenzioneDAzione> iterAzioni = getAzioni(req,ruoli).iterator();
		while (iterAzioni.hasNext()) {
			EsenzioneDAzione esenzioneDAzione = (EsenzioneDAzione) iterAzioni.next();
			if (esenzioneDAzione.getDescAzione().equals(azioneCorrente)) {
				bCheckAzione = true;
			}
		}
		return bCheckAzione;
	}
	
	/**
	 * ESENRED-ESENPAT-CDU-014-V06-Approva Esenzione
	 */
	@POST
	@Path("/approva-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApprovaEsenzione(@Context HttpServletRequest req, FiltriValidaEsenzionePatologia bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale(); // bo.getCit_id();

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Approva esenzione";
		String operation = "READ";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, "READ", shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrente = Constants.OP_APPROVA;
			boolean bCheckAzione = checkAzioneUtente(req, azioneCorrente, ruoli);
			if (!bCheckAzione) {
				if (!checkAzioneUtente(req, Constants.OP_CREA_VALIDA_ESENZIONE, ruoli)) {
					registraSuAudit(XForwardedFor,
							Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper, operation,
							shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

					res.put("status", Status.BAD_REQUEST.getStatusCode());
					res.put("code", "Verifica utente negativa");
					res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
					return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				}
			}

			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf.getEsenzioneTPraticaEsenzioneperskpratica(bo.getSkPraticaEsenzione());
			if (esenzioneTPraticaEsenzione==null) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_INVIATA)
				&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			
			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(XForwardedFor, oggOper, utente, cit_id, ruoli);

			if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
				EsenredCMessaggi msg012 = getMessaggioEsenpat(Constants.MSG012);
				res = new HashMap<String, Object>();
				res.put("status", Status.SERVICE_UNAVAILABLE.getStatusCode());
				res.put("code", "Mancata connessione ad AURA");
				res.put("message", msg012.getTesto());
				registraSuAudit(XForwardedFor, "503 " + msg012.getTesto(), oggOper, operation, shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

			} else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
				EsenredCMessaggi msg143 = getMessaggioEsenpat(Constants.MSG143);
				res = new HashMap<String, Object>();
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica del CF negativa");
				res.put("message", msg143);
				registraSuAudit(XForwardedFor, "400 " + msg143.getTesto(), oggOper, operation, shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				
			} else if (res.containsKey(CITTADINO_AURA)) {
				res = new HashMap<String, Object>();
                res.put("status", "ok");
                res.put("data", null);
                res.put("code", Status.OK.getStatusCode());
                res.put("message", "OK-Procedi approva");
                return corsedResponse.status(Status.OK).entity(res).build();
 
			} else {
				EsenredCMessaggi msg214 = getMessaggioEsenpat(Constants.MSG214);
				res = new HashMap<String, Object>();
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica AURA negativa");
				res.put("message", msg214);
				registraSuAudit(XForwardedFor, "400 " + msg214.getTesto(), oggOper, operation, shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@POST
	@Path("/procedi-approva-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProcediApprovaEsenzione(@Context HttpServletRequest req, FiltriValidaEsenzionePatologia bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale(); // bo.getCit_id();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		//String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Procedi approva esenzione";
		String operation = "UPDATE";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operation, utente.getCodFisc(),
					xRequestID, null, xcodServizio, cit_id, null);
			
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");		
			esenzioniFacade.approvaEsenzione(req, utente, bo,ruoli);

			
			registraSuAudit(XForwardedFor, "1 ok", oggOper, operation, utente.getCodFisc(), xRequestID, null, xcodServizio,
					cit_id, null);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "Esenzione approvata con successo");
			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (EsenpatException ee) {
			String error = getMessaggioEsenpat(ee.getCodice()).getTesto();

			registraSuAudit(XForwardedFor, "0 KO " + error, oggOper, operation, utente.getCodFisc(), xRequestID, null, xcodServizio,
					cit_id, null);

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);
			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, "READ", utente.getCodFisc(), xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	/**
	 * ESENRED-ESENPAT-CDU-010-V08-Salva in Bozza
	 */
	@POST
	@Path("/salva-in-bozza")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSalvaInBozza(@Context HttpServletRequest req, FiltriValidaEsenzionePatologia bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale(); // bo.getCit_id();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Salva in bozza";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, "READ", shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrente = Constants.OP_SALVA_IN_BOZZA;
			boolean bCheckAzione = checkAzioneUtente(req,azioneCorrente,ruoli);
			if (!bCheckAzione) {
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper,
						"READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
		
			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						"READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						"READ", shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			
			EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf.getEsenzioneTPraticaEsenzioneperskpratica(bo.getSkPraticaEsenzione());
			if (esenzioneTPraticaEsenzione==null) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}			
			if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_INVIATA)
				&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}

			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(XForwardedFor, oggOper, utente, cit_id, ruoli);

			if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
				EsenredCMessaggi msg012 = getMessaggioEsenpat(Constants.MSG012);
				res = new HashMap<String, Object>();
				res.put("status", Status.SERVICE_UNAVAILABLE.getStatusCode());
				res.put("code", "Mancata connessione ad AURA");
				res.put("message", msg012.getTesto());
				registraSuAudit(XForwardedFor, "503 " + msg012.getTesto(), oggOper, "READ", shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

			} else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
				EsenredCMessaggi msg143 = getMessaggioEsenpat(Constants.MSG143);
				res = new HashMap<String, Object>();
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica del CF negativa");
				res.put("message", msg143);
				registraSuAudit(XForwardedFor, "400 " + msg143.getTesto(), oggOper, "READ", shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				
			} else if (res.containsKey(CITTADINO_AURA)) {
				// CittadinoEsenpat cittadino = (CittadinoEsenpat) res.get(CITTADINO_AURA);
				
				res = new HashMap<String, Object>();
                res.put("status", "ok");
                res.put("data", null);
                res.put("code", Status.OK.getStatusCode());
                res.put("message", "OK-Procedi salva in bozza");
                return corsedResponse.status(Status.OK).entity(res).build();
 
			} else {
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			String messaggio200 = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio200);
			
			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, "READ", shibIdentitaCodiceFiscale, xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@POST
	@Path("/procedi-salva-in-bozza")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProcediSalvaInBozza(@Context HttpServletRequest req, FiltriValidaEsenzionePatologia bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		//String xRequestID =  req.getHeader("HTTP_X_REQUEST_ID");
		//String XForwardedFor = req.getHeader("X-FORWARDED-FOR");
		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale(); // bo.getCit_id();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		//String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Procedi salva in bozza";
		String operazione = "UPDATE";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, utente.getCodFisc(),
					xRequestID, null, xcodServizio, cit_id, null);
			
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			esenzioniFacade.salvaInBozza(req, utente, cit_id, bo.getSkPraticaEsenzione(), bo.getNotainterna(),
					bo.getNotabeneficiario(), null, bo.getNota(),ruoli);
			
			
			registraSuAudit(XForwardedFor, "1 ok", oggOper, operazione, utente.getCodFisc(), xRequestID, null, xcodServizio,
					cit_id, null);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "Salva in bozza eseguito con successo");

			// return corsedResponse.status(Status.OK).build();
			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (EsenpatException ee) {
			String error = getMessaggioEsenpat(ee.getCodice()).getTesto();

			registraSuAudit(XForwardedFor, "0 KO " + error, oggOper, operazione, utente.getCodFisc(), xRequestID, null, xcodServizio,
					cit_id, null);

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);
			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio200 = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio200);
			
			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, utente.getCodFisc(), xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	/**
	 * ESENRED-ESENPAT-CDU-013-V06-Rinnova Esenzione
	 */
	@POST
	@Path("/rinnova-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRinnovaEsenzione(@Context HttpServletRequest req,FiltriValidaEsenzionePatologia bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		//String xRequestID =  req.getHeader("HTTP_X_REQUEST_ID");
		//String XForwardedFor = req.getHeader("X-FORWARDED-FOR");
		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		
		String oggOper = "Rinnova esenzione";
		String operazione = "UPDATE";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrente = Constants.OP_RINNOVA;
			boolean bCheckAzione = checkAzioneUtente(req,azioneCorrente,ruoli);
			if (!bCheckAzione) {
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
		
			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			
			EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf.getEsenzioneTPraticaEsenzioneperskpratica(bo.getSkPraticaEsenzione());
			if (esenzioneTPraticaEsenzione==null) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_IN_SCADENZA) 
				&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_SCADUTA)) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}

			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(XForwardedFor, oggOper, utente, cit_id, ruoli);

			if (res.get("code") == AURA_ERRORE_CONNESSIONE) {
				EsenredCMessaggi msg012 = getMessaggioEsenpat(Constants.MSG012);
				res = new HashMap<String, Object>();
				res.put("status", Status.SERVICE_UNAVAILABLE.getStatusCode());
				res.put("code", "Mancata connessione ad AURA");
				res.put("message", msg012.getTesto());
				registraSuAudit(XForwardedFor, "503 " + msg012.getTesto(), oggOper, operazione, shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, shibIdentitaCodiceFiscale);
				return corsedResponse.status(Status.SERVICE_UNAVAILABLE).entity(res).build();

			} else if (res.get("code") == CITTADINO_NON_PIEMONTESE) {
				EsenredCMessaggi msg143 = getMessaggioEsenpat(Constants.MSG143);
				res = new HashMap<String, Object>();
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica del CF negativa");
				res.put("message", msg143);
				registraSuAudit(XForwardedFor, "400 " + msg143.getTesto(), oggOper, operazione, shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				
			} else if (res.containsKey(CITTADINO_AURA)) {
				// CittadinoEsenpat cittadino = (CittadinoEsenpat) res.get(CITTADINO_AURA);
				
				res = new HashMap<String, Object>();
                res.put("status", "ok");
                res.put("data", null);
                res.put("code", Status.OK.getStatusCode());
                res.put("message", "OK-Procedi rinnova esenzione");
                return corsedResponse.status(Status.OK).entity(res).build();
 
			} else {
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			String messaggio200 = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio200);
			
			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@POST
	@Path("/procedi-rinnova-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProcediRinnovaEsenzione(@Context HttpServletRequest req, FiltriValidaEsenzionePatologia bo)
			throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		String XForwardedFor = req.getRemoteAddr(); 
		String xRequestID = null;
		String xcodServizio = null;

		String cit_id = bo.getBeneficiario().getCodiceFiscale();

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req, utente);
		String oggOper = "Procedi rinnova esenzione";
		String operazione = "UPDATE";
		String message = null;

		try {
			CheckRicercaEsenzioneOperatore.chkFiltriRinnova(bo);
			EsenzioneTPraticaEsenzione esenzionerinnovata = new EsenzioneTPraticaEsenzione();
			EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext
					.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf
					.getEsenzioneTPraticaEsenzioneperskpratica(bo.getSkPraticaEsenzione());
			if (esenzioneTPraticaEsenzione == null) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, utente.getCodFisc(),
					xRequestID, null, xcodServizio, cit_id, null);

			Map<String, Object> resAura = verificaCittadinoSuAura(XForwardedFor, oggOper, utente, cit_id, ruoli);
			if (!resAura.containsKey(CITTADINO_AURA)) {
				registraSuAudit(XForwardedFor, "0 KO", oggOper, operazione, utente.getCodFisc(), xRequestID, null,
						xcodServizio, cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(resAura).build();
			}

			CittadinoEsenpat datiCittadino = (CittadinoEsenpat) resAura.get(CITTADINO_AURA);

			// stato nuova pratica
			String statoPratica = null;
			boolean bAzioneValida = checkAzioneUtente(req, Constants.OP_VALIDA, ruoli);
			if (bAzioneValida) {
				statoPratica = Constants.STATO_PRATICA_VALIDATA;
			} else {
				boolean bAzioneApprova = checkAzioneUtente(req, Constants.OP_APPROVA, ruoli);
				if (bAzioneApprova) {
					statoPratica = Constants.STATO_PRATICA_DA_VALIDARE;
				} else {
					boolean bAzioneRinnova = checkAzioneUtente(req, Constants.OP_RINNOVA, ruoli);
					if (bAzioneRinnova) {
						statoPratica = Constants.STATO_PRATICA_INVIATA;
					}
				}
			}

			if (bAzioneValida) {
				// insierimento in aura della patologia
				// chiamo il servizio EsenzionePatologia
				EsenzionePatologia esenzionepatologia = new EsenzionePatologia();
				esenzionepatologia.setCodiceEsenzione(bo.getCodEsenzione());
				esenzionepatologia.setCodiceFiscaleAssistito(bo.getBeneficiario().getCodiceFiscale());
				esenzionepatologia.setCodiceFiscaleChiamante(utente.getCodFisc());
				String datainiziovalidita = Converter.getData(new Date(), "dd/MM/yyyy");
				String datafinevalidita = null;
				if (esenzioneTPraticaEsenzione.getDatInizioValidita() != null)
					datainiziovalidita = Converter.getData(
							Converter.getData(esenzioneTPraticaEsenzione.getDatInizioValidita()), "dd/MM/yyyy");
				if (esenzioneTPraticaEsenzione.getDatFineValidita() != null)
					datafinevalidita = Converter
							.getData(Converter.getData(esenzioneTPraticaEsenzione.getDatFineValidita()), "dd/MM/yyyy");
				esenzionepatologia.setDataInizioValidita(datainiziovalidita);
				esenzionepatologia.setDataFineValidita(datafinevalidita);
				esenzionepatologia.setListaDiagnosi(
						esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi().startsWith("F") ? "000"
								: esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());

				EsenzionePatologiaRes esitoinserimento = IntegrationClientImpl.getInstance()
						.InserisciEsenzionePatologia(esenzionepatologia);
				
				// se esito ok allora update documento con stato valido altimenti errore
				if ("OK".equalsIgnoreCase(esitoinserimento.getHeader().getCodiceRitorno())) {
					registraSuAudit(req.getRemoteAddr(), "1 InserisciEsenzionePatologia OK", oggOper, "INSERT",
							utente.getCodFisc(), null, null, null, bo.getBeneficiario().getCodiceFiscale(),
							utente.getCodFisc());
					datiCittadino = IntegrationClientImpl.getInstance()
							.getCittadinoEsenpat(bo.getBeneficiario().getIdAura());
				} else {
					registraSuAudit(req.getRemoteAddr(), "0 InserisciEsenzionePatologia KO", oggOper, "INSERT",
							utente.getCodFisc(), null, null, null, bo.getBeneficiario().getCodiceFiscale(), null);
					message = esitoinserimento.getHeader().getListaMessaggi().get(0).getDescrizione();
					res.put("status", "ko");
					res.put("data", null);
					res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
					res.put("message", message);
					return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
				}
			}
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			esenzionerinnovata = esenzioniFacade.rinnovaEsenzione(req, datiCittadino, utente, bo, statoPratica, ruoli);

			registraSuAudit(req.getRemoteAddr(), "1 inserisciDocumentoService OK ", oggOper, "INSERT",
					utente.getCodFisc(), null, null, null, bo.getBeneficiario().getCodiceFiscale(), null);

			
			registraSuAudit(XForwardedFor, "1 ok", oggOper, operazione, utente.getCodFisc(), xRequestID, null,
					xcodServizio, cit_id, null);

			// Response
			FiltroDettaglioEsenzione filtrodettaglioesenzione = new FiltroDettaglioEsenzione();
			filtrodettaglioesenzione.setCfBeneficiario(esenzionerinnovata.getCodiceFiscaleBeneficiario());
			filtrodettaglioesenzione.setCodEsenzione(
					esenzionerinnovata.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
			String datainiziovalidita = Converter.getData(new Date(), "dd/MM/yyyy");
			if (esenzionerinnovata.getDatInizioValidita() != null)
				datainiziovalidita = Converter.getData(Converter.getData(esenzionerinnovata.getDatInizioValidita()),
						"dd/MM/yyyy");
			filtrodettaglioesenzione.setDataEmissione(datainiziovalidita);
			filtrodettaglioesenzione.setSkEsenzione(esenzionerinnovata.getSkPraticaEsenzione().toString());
			res.put("status", "ok");
			res.put("data", filtrodettaglioesenzione);
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "Rinnova esenzione eseguito con successo");

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, utente.getCodFisc(), xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@PUT
	@Path("/cittadini/{cit_id}/esenzioni/{esenzione_id}/revoca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setRevocaEsenzione(@Context HttpServletRequest req,
			@PathParam("cit_id") String citId,
			@PathParam("esenzione_id") String esenzioneId, 
			MotivazioneRevocaEsenpat motivazioneRevoca) throws Exception {
		
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null; 
		String xcodServizio = null;

		final String service = "Revoca esenzione";
		final String operation = "UPDATE";

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		try {

			 al servizio REVOCA
			registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, citId, null);

			if (!checkAzioneUtente(req, Constants.OP_ANNULLAREVOCA, ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, service, operation, shibIdentitaCodiceFiscale,
						xRequestID, null, xcodServizio, citId, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			
			if (!Checker.isValorizzato(citId) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(),
						service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(citId, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(),
						service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita' parametri di input", Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(req.getRemoteAddr(), service, utente, citId, ruoli);

			if (res.containsKey(CITTADINO_AURA)) {
				esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
				EsenzioneTPraticaEsenzione ecPre = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId.toString()));
				
				if (ecPre != null) {
					if (ecPre.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_VALIDATA)) {
						CittadinoEsenpat beneficiario = (CittadinoEsenpat) res.get(CITTADINO_AURA);
						String dataInizioValidita = ecPre.getDatInizioValidita() == null ? null : Converter.getData(ecPre.getDatInizioValidita(), "dd/MM/yyyy");
//						String dataFineValidita = ecPre.getDatFineValidita() == null ? null : Converter.getData(ecPre.getDatFineValidita(), "dd/MM/yyyy");
						EsenzionePatologia epReq = new EsenzionePatologia();
						epReq.setCodiceFiscaleChiamante(shibIdentitaCodiceFiscale);
						epReq.setCodiceFiscaleAssistito(beneficiario.getCodFiscale());
						epReq.setCodiceEsenzione(ecPre.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
						epReq.setDataInizioValidita(dataInizioValidita);
						epReq.setDataFineValidita(Converter.getData(new Date(),"dd/MM/yyyy"));
						epReq.setDataAnnullamento(Converter.getData(new Date(),"dd/MM/yyyy"));
						epReq.setListaDiagnosi(ecPre.getEsenzioneDDiagnosi().getCodDiagnosi().startsWith("F") ? "000"
								: ecPre.getEsenzioneDDiagnosi().getCodDiagnosi());
						EsenzionePatologiaRes epRes = IntegrationClientImpl.getInstance().InserisciEsenzionePatologia(epReq);

						if ("OK".equalsIgnoreCase(epRes.getHeader().getCodiceRitorno())) { // solo se Aura mi da OK
																							// revoco l'esenzione in
																							// ESENPAT
							res = new HashMap<String, Object>();
							res = verificaCittadinoSuAura(req.getRemoteAddr(), service, utente, citId, ruoli);

							if (res.containsKey(CITTADINO_AURA)) {
								beneficiario = (CittadinoEsenpat) res.get(CITTADINO_AURA);

								EsenzioneCittadinoEsenpat ec = esenzioniFacade.revocaEsenzione(esenzioneId,
										motivazioneRevoca, citId, utente, beneficiario, ruoli);

								registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale,
										xRequestID, null, xcodServizio, citId, null);

								return corsedResponse.status(Status.OK).entity(ec).build();
							} else {
								registraSuAudit(req.getRemoteAddr(), "0 KO", service, operation,
										shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId,
										null);
								return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
							}
						} else {
							registraSuAudit(XForwardedFor, "0 ko", service, operation, shibIdentitaCodiceFiscale,
									xRequestID, null, xcodServizio, citId, null);
							res = new HashMap<String, Object>();
							res = generateResponseErrore(epRes.getHeader().getListaMessaggi().get(0).getCodice(),
									epRes.getHeader().getListaMessaggi().get(0).getDescrizione(),
									Status.BAD_REQUEST.getStatusCode());
							return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
						}
					}
				}
			} else {
				registraSuAudit(req.getRemoteAddr(), "0 KO", service, operation, shibIdentitaCodiceFiscale, xRequestID,
						null, xcodServizio, citId, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (EsenpatException e) {
			e.printStackTrace();
			EsenredCMessaggi messaggio = getMessaggioEsenpat(e.getCodMessaggio());
			res = generateResponseErrore(messaggio.getTesto(), e.getCodice(), e.getStatus());
			registraSuAudit(XForwardedFor, e.getStatus() + " " + messaggio.getTesto(), service, operation,
					shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, citId, null);

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
	
	/**
	 * ESENRED-ESENPAT-CDU-012-V06-Respingi Esenzione
	 */
	@POST
	@Path("{cit_id}/esenzione/{esenzione_id}/respingi-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRespingiEsenzione(@Context HttpServletRequest req, 
			@PathParam("cit_id") String cit_id,
			@PathParam("esenzione_id") String esenzioneId,
			MotivazioneAnnullamento bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Respingi esenzione";
		String operazione = "UPDATE";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, utente.getCodFisc(),
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrente = Constants.OP_RESPINGI;
			boolean bCheckAzione = checkAzioneUtente(req,azioneCorrente,ruoli);
			if (!bCheckAzione) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, oggOper, operazione, utente.getCodFisc(),
						xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			// controllo specifico codice motivazione
			if (!Checker.isValorizzato(bo.getMotivazione().getCodice())) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio = getMessaggioEsenpat(Constants.MSG201);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio.getTesto(), oggOper,
						operazione, utente.getCodFisc(), xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(utente.getCodFisc()) || !Checker.isValorizzato(esenzioneId)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operazione, utente.getCodFisc(), xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						operazione, utente.getCodFisc(), xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf.getEsenzioneTPraticaEsenzioneperskpratica(esenzioneId);
			if (esenzioneTPraticaEsenzione==null) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_INVIATA)
				&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_IN_LAVORAZIONE)
				&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_DA_VALIDARE)) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			esenzioniFacade.respingiEsenzione(req, utente, bo, esenzioneId, cit_id, ruoli);

			
			registraSuAudit(XForwardedFor, "1 ok", oggOper, operazione, utente.getCodFisc(), xRequestID, null, xcodServizio,
					cit_id, null);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "Esenzione respinta con successo");

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, utente.getCodFisc(), xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	/**
	 * ESENRED-ESENPAT-CDU-024-V06-Richiesta Rettifica Dati
	 */
	@POST
	@Path("/richiesta-rettifica-dati")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRichiestaRettificaDati(@Context HttpServletRequest req, RichiestaRettificaDatiBO bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		
		String oggOper = "Richiesta rettifica dati";
		String operazione = "READ";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrenteBeneficiario = Constants.OP_RETTIFICA_BENEFICIARIO;
			final String azioneCorrenteMedico = Constants.OP_RETTIFICA_MEDICO;
			boolean bCheckAzioneBeneficiario = checkAzioneUtente(req, azioneCorrenteBeneficiario, ruoli);
			boolean bCheckAzioneMedico = checkAzioneUtente(req, azioneCorrenteMedico, ruoli);
			if (!bCheckAzioneBeneficiario && !bCheckAzioneMedico) {
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrenteBeneficiario + "' e '" + azioneCorrenteMedico + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
		
			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			
			EsenzioneTPraticaEsenzioneIf esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf.getEsenzioneTPraticaEsenzioneperskpratica(bo.getSkPraticaEsenzione());
			if (esenzioneTPraticaEsenzione == null) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_INVIATA) 
				&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
				MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
				res.put("status", "ko");
				res.put("data", null);
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}
			
			// Il sistema deve verificare che almeno uno dei campi note sia stato valorizzato
			if ( (bo.getNota() == null || bo.getNota().trim().equals(""))
					&& (bo.getNotainterna() == null || bo.getNotainterna().trim().equals(""))
					&& (bo.getNotabeneficiario() == null || bo.getNotabeneficiario().trim().equals(""))
				) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio = getMessaggioEsenpat(Constants.MSG212);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			} 
				
			res = new HashMap<String, Object>();
			res.put("status", "ok");
			res.put("data", null);
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "OK-Procedi richiesta rettifica dati");
			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();
			
			String messaggio200 = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res = new HashMap<String, Object>();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio200);
			
			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@POST
	@Path("/procedi-richiesta-rettifica-dati")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProcediRichiestaRettificaDati(@Context HttpServletRequest req, RichiestaRettificaDatiBO bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req, utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Procedi richiesta rettifica dati";
		String operazione = "UPDATE";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);

			// controllo specifico codice motivazione
			if (!Checker.isValorizzato(bo.getCodiceMotivazioneTipo())) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio = getMessaggioEsenpat(Constants.MSG201);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			if (!Checker.isValorizzato(bo.getRichiestaRettificaDestinatario())) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			// stato nuova pratica
			String statoPratica = null;
			Ruolo ruolo = ruoli.get(0);
			if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.OPERATORE_ASL)
					|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
				if (bo.getRichiestaRettificaDestinatario().equals("CITTADINO")) {
					statoPratica = Constants.STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_OPERATORE;
				} else if (bo.getRichiestaRettificaDestinatario().equals("MEDICO")) {
					statoPratica = Constants.STATO_PRATICA_INVIATA_DAL_MEDICO;
				}
			} else if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS) || ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_SPECIALISTA)) {
				statoPratica = Constants.STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_MEDICO;
			}
			
			if (statoPratica == null) {
				throw new RuntimeException("Nuovo stato pratica non determinato");
			}

			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			esenzioniFacade.richiestaRettificaDati(req, utente, bo, statoPratica,ruolo);

			
			registraSuAudit(XForwardedFor, "1 ok", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
					cit_id, null);
			
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "Richiesta rettifica dati eseguita con successo");

			return corsedResponse.status(Status.OK).entity(res).build();

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);
			
			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID,
					null, xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	

	/**
	 * ESENRED-ESENPAT-CDU-026-V05-Rettifica Esenzione
	 */
	@POST
	@Path("/rettifica-esenzione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRettificaEsenzione(@Context HttpServletRequest req, RichiestaRettificaDatiBO bo) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;
		
		String cit_id = bo.getBeneficiario().getCodiceFiscale(); // bo.getCit_id();
		
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,utente);
		
		String shibIdentitaCodiceFiscale = utente.getCodFisc();
		String oggOper = "Rettifica esenzione";
		String operation = "UPDATE";
		
		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operation, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);
			
			final String azioneCorrente = Constants.OP_RETTIFICAESENZIONE;
			boolean bCheckAzione = checkAzioneUtente(req, azioneCorrente, ruoli);
			if (!bCheckAzione) {
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper,
						operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper,
						operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			
			if (!Checker.isCodiceFiscale(cit_id, isTst)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper,
						operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Mancata congruita\' parametri di input");
				res.put("message", messaggio149.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");		
			esenzioniFacade.rettificaEsenzione(req, utente, bo,ruoli.get(0));

			
			registraSuAudit(XForwardedFor, "1 ok", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
					cit_id, null);

			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("message", "Rettifica esenzione eseguita con successo");
			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (EsenpatException ee) {
			String error = getMessaggioEsenpat(ee.getCodMessaggio()).getTesto();

			registraSuAudit(req.getRemoteAddr(), "0 KO " + error, oggOper, operation, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);
			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	

	@GET
	@Path("/esenzioni/{cit_id}/{esenzione_id}/storico")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCronologia(@Context HttpServletRequest req,

			@PathParam("cit_id") String cit_id, 
			@PathParam("esenzione_id") String esenzione_id) 
					throws Exception
	{
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();

		final String oggOper = "Storico esenzione";
		final String operation = "READ";

		try {
			
			registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);

			final String azioneCorrente = Constants.OP_VISUALIZZA_CRONOLOGIA;
			List<Ruolo> ruoli = getRuoli(req,utente);
			if (!checkAzioneUtente(req,azioneCorrente, ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");
				registraSuAudit(XForwardedFor, "0 KO " + msg210, operation, oggOper, utente.getCodFisc(), null, null,
						null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			
			if (!Checker.isValorizzato(cit_id) || !Checker.isValorizzato(XForwardedFor) || !Checker.isValorizzato(shibIdentitaCodiceFiscale)
					|| !Checker.isValorizzato(esenzione_id)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper, operation,
						shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(cit_id, isTst)
					// !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					|| !Checker.isXForwardedForValido(XForwardedFor)) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(), oggOper, operation,
						shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
			List<StoricoEsenzione> storico = certificatiFacade.getStoricoEsenzione(cit_id, esenzione_id);

			registraSuAudit(XForwardedFor, "1 ok", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
					null);
			return corsedResponse.status(Status.OK).entity(storico).build();

		} catch (Exception e) {
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@GET
	@Path("/esenzioni/{cit_id}/{esenzione_id}/pdf")
	@Produces("application/pdf")
	public Response createPdfSingolaEsenzione(@Context HttpServletRequest req,
//			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale, 
//			@HeaderParam("X-Request-ID") String xRequestID,
//			@HeaderParam("X-Forwarded-For") String XForwardedFor, 
//			@HeaderParam("X-Codice-Servizio") String xcodServizio,
			@PathParam("cit_id") String cit_id, 
			@PathParam("esenzione_id") String esenzione_id,
			@QueryParam("document_type") String document_type, @QueryParam("cod_esenzione") String cod_esenzione,
			@QueryParam("data_validita") String data_validita)
					throws Exception
	{
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();

		final String oggOper = "Create PDF singola esenzione";
		final String operation = "READ";
		try {
			
			registraSuAudit(XForwardedFor, "1 OK", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);

			final String azioneCorrente = Constants.OP_SCARICA_ATTESTATO;
			List<Ruolo> ruoli = getRuoli(req,utente);
			if (!checkAzioneUtente(req,azioneCorrente, ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");
				registraSuAudit(XForwardedFor, "0 KO " + msg210, operation, oggOper, utente.getCodFisc(), null, null,
						null, cit_id, null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			// Verifica campi obbligatori
			if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id) || !Checker.isValorizzato(esenzione_id)
					|| !Checker.isValorizzato(document_type)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();

				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper, operation,
						shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Parametri obbligatori mancanti");
				res.put("message", messaggio145.getTesto());

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(XForwardedFor, oggOper, utente, cit_id, ruoli);
			if (res.containsKey(CITTADINO_AURA)) {
				Cittadino cittadinoDel = null;
				CittadinoEsenpat cittadinoBen = (CittadinoEsenpat) res.get(CITTADINO_AURA);
				if ("0".equalsIgnoreCase(esenzione_id)) {

					if (!Checker.isValorizzato(cod_esenzione) || !Checker.isValorizzato(data_validita)) {
						corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
						res = new HashMap<String, Object>();

						EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
						registraSuAudit(XForwardedFor,
								Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), oggOper, operation,
								shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
								null);

						res.put("status", Status.BAD_REQUEST.getStatusCode());
						res.put("code", "Parametri obbligatori mancanti");
						res.put("message", messaggio145.getTesto());

						return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
					}
					EsenzioneAssistito esenzione = null;
					if (cittadinoBen.getEsenzioniAura() != null
							&& cittadinoBen.getEsenzioniAura().getInfoesenzione() != null
							&& cittadinoBen.getEsenzioniAura().getInfoesenzione().length > 0) {
						for (InfoEsenzioneNew esen : cittadinoBen.getEsenzioniAura().getInfoesenzione()) {
							EsenzioneAssistito es = new EsenzioneAssistito(esen, cittadinoBen, utente);
							if (es.getCodiceEsenzione().equalsIgnoreCase(cod_esenzione)
									&& es.getBeneficiario().getCodiceFiscale().equalsIgnoreCase(cit_id)
									&& es.getValidaDal().equalsIgnoreCase(data_validita)) {
								esenzione = es;
								break;
							}
						}
						if (esenzione != null) {
							esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
							File pdfEsenzione = esenzioniFacade.createPdfSingolaEsenzione(null, cittadinoBen,
									cittadinoDel, document_type, esenzione);
							if (pdfEsenzione == null) {
								throw new Exception();
							}
							
							registraSuAudit(XForwardedFor, "1 ok", oggOper, operation, shibIdentitaCodiceFiscale,
									xRequestID, null, xcodServizio, cit_id, null);

							corsedResponse.header("Content-Disposition",
									"attachment; filename=" + pdfEsenzione.getName());
							return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition")
									.status(Status.OK).entity(pdfEsenzione).build();
						} else {
							registraSuAudit(req.getRemoteAddr(), "0 KO", oggOper, operation, shibIdentitaCodiceFiscale,
									xRequestID, null, xcodServizio, cit_id, null);
							return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
						}
					} else {
						registraSuAudit(req.getRemoteAddr(), "0 KO", oggOper, operation, shibIdentitaCodiceFiscale,
								xRequestID, null, xcodServizio, cit_id, null);
						return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
					}
				} else {
				esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
				EsenzioneTPraticaEsenzione etpe = esenzioniFacade.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzione_id));
				if (etpe == null) {
					corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
					res = new HashMap<String, Object>();

					MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
					res = new HashMap<String, Object>();
					res.put("status", "ko");
					res.put("data", null);
					res.put("code", Status.NOT_FOUND.getStatusCode());
					res.put("message", message);

					return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
				}
				if (!etpe.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA)
						&& !etpe.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_SCADUTA)
						&& !etpe.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_ANNULLATA)
						&& !etpe.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_REVOCATA)
						&& !etpe.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_MEDICO)
						&& !etpe.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_OPERATORE)) {
					corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
					res = new HashMap<String, Object>();

					MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
					res.put("status", "ko");
					res.put("data", null);
					res.put("code", Status.NOT_FOUND.getStatusCode());
					res.put("message", message);

					return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
				}
				
					File pdfEsenzione = esenzioniFacade.createPdfSingolaEsenzione(etpe, cittadinoBen, cittadinoDel,
							document_type, null);
				if (pdfEsenzione == null) {
					throw new Exception();
				}
				
				registraSuAudit(XForwardedFor, "1 ok", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio,
							cit_id, null);

				
				corsedResponse.header("Content-Disposition", "attachment; filename=" + pdfEsenzione.getName());
				return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition")
						.status(Status.OK).entity(pdfEsenzione).build();			
				}
			} else {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				registraSuAudit(req.getRemoteAddr(), "0 KO", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID,
						null, xcodServizio, cit_id, null);
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

		} catch (Exception e) {
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operation, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	 /*
   * CDU 02 Inserimento Nuovo Certificato
   * creazione di un nuovo certificato di condizione o malattia (ccm)
   */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nuovoCertificato")
	public Response insertNuovoCertificato(@Context HttpServletRequest req, NuovoCertificato certificato) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> resAura = new HashMap<String, Object>();

		final String operazione = "Nuovo Certificato";
		final String service = "INSERT";

		EsenzioneTDocumento documento = null;
		UserInfo utente = null;

		 
		try 
		{
	    utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
	    List<Ruolo> ruoli = getRuoli(req,utente);
	    registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					certificato.getAssistito().getCodFiscale(), null);

	    // controllo se sono valorizzati i dati di UserInfo
	    if (!Checker.isValorizzato(utente.getIdAura())) {
        throw new EsenpatException(Constants.MSG228);
	    }
	    
	    // Verifica CF cittadino e residenza
			resAura = verificaCittadinoSuAura(req.getRemoteAddr(), operazione, utente,
					certificato.getAssistito().getCodFiscale(), ruoli);

			if (resAura.containsKey(CITTADINO_AURA)) {
				// insert or update cittadino
				cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
				CittadinoEsenpat cittadino = (CittadinoEsenpat) resAura.get(CITTADINO_AURA);
				cittadinoFacade.insertOrUpdateCittadino(cittadino);
				if (certificato.getAssistito().getCodAsl() == null) {
					certificato.getAssistito().setCodAsl(cittadino.getCodASL());
				}
  			certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
  			documento = certificatiFacade.insertNuovoCertificato(certificato, utente, cittadino);
  
  			if (documento != null) {
  				res.put("status", "ok");
					res.put("num_certificato", documento.getSkDocumento());
					res.put("code", Status.OK.getStatusCode());
          
					registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null,
							null,
							cittadino.getCodFiscale(), null);
          
  				return corsedResponse.status(Status.OK).entity(res).build();
  			} else {
					registraSuAudit(req.getRemoteAddr(), "0 KO ", operazione, service, utente.getCodFisc(), null, null,
							null,
							cittadino.getCodFiscale(), null);
  			  
  			   res.put("status", "ko");
					res.put("code", Status.BAD_REQUEST.getStatusCode());
					return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
  			}
      } else {
       throw new EsenpatException(Constants.MSG228);
      }
		} catch (EsenpatException ee) {
	      String error = getMessaggioEsenpat(ee.getCodice()).getTesto();
	    
	      registraSuAudit(req.getRemoteAddr(), "0 KO " + error, operazione, service, utente.getCodFisc(), null, null, null,
					certificato.getAssistito().getCodFiscale(), null);

	      res.put("status", "ko");
	      res.put("code", Status.BAD_REQUEST.getStatusCode());
	      res.put("message", error);
	      return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
		  String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

      registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, operazione, service, utente.getCodFisc(), null, null, null,
					certificato.getAssistito().getCodFiscale(), null);
      
      res.put("status", "ko");
      res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
      res.put("message", messaggio);
      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/diagnosi/{gruppo}")
	public Response getDiagnosiGruppo(@Context HttpServletRequest req, @PathParam("gruppo") String gruppoEsenzione)
			throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			Set<DiagnosiGruppo> listaDiagnosi = null;
			// controllo dati inseriti certificato
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			listaDiagnosi = esenzioniFacade.getListaDiagnosiByGruppoEsenzione(gruppoEsenzione);

			if (listaDiagnosi != null) {

				res.put("status", "ok");
				res.put("data", listaDiagnosi);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}

		} catch (Exception e) { e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return null;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/esenzioni/{gruppo}")
	public Response getEsenzioniGruppo(@Context HttpServletRequest req, @PathParam("gruppo") String gruppoEsenzione)
			throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			Set<EsenzioneGruppo> listaEsenzioni = null;
			// controllo dati inseriti certificato
			esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			listaEsenzioni = esenzioniFacade.getListaEsenzioniByGruppoEsenzione(gruppoEsenzione);

			if (listaEsenzioni != null) {

				res.put("status", "ok");
				res.put("data", listaEsenzioni);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}

		} catch (Exception e) { e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return null;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/documentoTipo/{gruppo}")
	public Response getDocumentoTipo(@Context HttpServletRequest req, @PathParam("gruppo") String gruppo)
			throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			List<Tipologia> listaDocumentoTipo = null;
			certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
			listaDocumentoTipo = certificatiFacade.getListaDocumentoTipo(gruppo);

			if (listaDocumentoTipo != null) {

				res.put("status", "ok");
				res.put("data", listaDocumentoTipo);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}

		} catch (Exception e) { e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return null;
	}

	@GET
	@Path("/getStatoDocumento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatoDocumento() throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<StatoDocumento> stato = new ArrayList<StatoDocumento>();
			statoDocumentoIf = (StatoDocumentoIf) SpringApplicationContext.getBean("statodocumento");
			List<EsenzioneDDocumentoStato> statodocumento = statoDocumentoIf.getStatoDocumento(Constants.STATO_DOCUMENTO_VALIDO);
			for (Iterator<EsenzioneDDocumentoStato> iterator = statodocumento.iterator(); iterator.hasNext();) {
				EsenzioneDDocumentoStato eDB = (EsenzioneDDocumentoStato) iterator.next();
				stato.add(new StatoDocumento(eDB));
			}
			if (!stato.isEmpty()) {
				res.put("status", "ok");
				res.put("data", stato);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@GET
	@Path("/getCodiceEsenzionePatologiaFiltro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCodiceEsenzionePatologiaFiltro() throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			
			List<EsenzionePatologiaElenco> elencopatologia = new ArrayList<EsenzionePatologiaElenco>();
			codiceEsenzionePatologiaIf = (CodiceEsenzionePatologiaIf) SpringApplicationContext
					.getBean("codiceesenzionepatologia");
			List<EsenzioneDEsenzione> codiceesenzionepatologia = null;
			EsenzioneDEsenzione eDB = new EsenzioneDEsenzione();
			
			List<GruppoEsenzionePatologiaElenco> gruppoelencopatologia = new ArrayList<GruppoEsenzionePatologiaElenco>();
			gruppoEsenzionePatologiaIf = (GruppoEsenzionePatologiaIf) SpringApplicationContext
					.getBean("gruppoesenzionepatologia");
			//trova elenco dei gruppi e mettili nell'oggetto elenco patologia
			List<EsenzioneDGruppoEsenzioni> gruppoesenzionepatologia = gruppoEsenzionePatologiaIf.getGruppoEsenzionePatologiaInCombo(1);
			for (EsenzioneDGruppoEsenzioni gruppo : gruppoesenzionepatologia) {
				// EsenzioneTipologia esenzionetipologia = new EsenzioneTipologia();
				codiceesenzionepatologia = codiceEsenzionePatologiaIf.getCodiceEsenzionePatologia(gruppo.getCodTipologiaGruppo());
				for (Iterator<EsenzioneDEsenzione> iterator = codiceesenzionepatologia.iterator(); iterator.hasNext();) {
					EsenzionePatologiaElenco patologia = new EsenzionePatologiaElenco();
					patologia.setGruppoEsenzione("ESENZIONI PER " + gruppo.getDescGruppo().toUpperCase() + ":");
					eDB = (EsenzioneDEsenzione) iterator.next();
					EsenzioneTipologia esenzionetipologia = (new EsenzioneTipologia(eDB));
					patologia.setEsenzionetipologia(esenzionetipologia);
					elencopatologia.add(patologia);
				}	
			}
			
			if (!elencopatologia.isEmpty()) {
				res.put("status", "ok");
				res.put("data", elencopatologia);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/findCittadinipato")
	public Response getCittadiniPato(
			@Context HttpServletRequest req,FiltriRicercaCittadino filtri) {
			Map<String, Object> res = new HashMap<String, Object>();
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			String operazione = "Ricerca Assistito";
			String service = "READ";

		try {
			CheckRicercaCittadini.chkFiltriPato(filtri);
			
			//prima cerco ne db di esenpat. Se non trovo nulla e verifico su aura solo se � valorizzato il cf
			UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			String codAsl = null;
			for (Ruolo ruolo : getRuoli(req,utente)) {
				//medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
					codAsl = null;
					break;
				}
				else
					codAsl = utente.getCodASL();
			}
			CittadinoFacade cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
			
			List<EsenzioneTCittadino> elencoAssistiti = cittadinoFacade.getAssistitoPato(filtri, codAsl);
			String message = null;
			message = "";
			List<Cittadino> datiCittadino = new ArrayList<Cittadino>();
			if (elencoAssistiti == null || elencoAssistiti.size() == 0) {
			if (!Checker.isValorizzato(filtri.getCodFiscale())) {
					throw new CheckException(Constants.MSG211);
				} else {
					//fai find e get per asl
					datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(filtri.getCodFiscale()));
					if (datiCittadino.isEmpty()) {
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						message = messaggioIf.getMessaggio("MSG024").getTesto();
					}
					else {
						 CittadinoEsenpat cittadino = IntegrationClientImpl.getInstance().getCittadinoEsenpat(datiCittadino.get(0).getIdAura());
					        if (isCittadinoPiemontese(cittadino)) {
					        	if (Checker.isValorizzato(codAsl)) {
								if (!(cittadino.getCodASL().equalsIgnoreCase(codAsl))) {
									messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
									message = messaggioIf.getMessaggio("MSG024").getTesto();
									datiCittadino.clear();
								} 
								}
							} else {
									message = getMessaggioEsenpat(Constants.MSG214).getTesto();
							datiCittadino.clear();
							}
						 
					}
					
					registraSuAudit(req.getRemoteAddr(), "1 AnagrafeFind OK " + message, operazione, "READ", utente.getCodFisc(), null, null, null,
							filtri.getCodFiscale(), null);
						
					res.put("status", "ok");
					res.put("data", datiCittadino);
					res.put("code", Status.OK.getStatusCode());
					res.put("message", message);
					return corsedResponse.status(Status.OK).entity(res).build();
					
				}
			} else if (elencoAssistiti.size() > 1) {
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
						filtri.getCodFiscale(), null);
				List<Cittadino> assistiti = new ArrayList<Cittadino>(elencoAssistiti.size());
				for (EsenzioneTCittadino c : elencoAssistiti) {
					assistiti.add(new Cittadino(c));
				}
				res.put("status", "ok");
				res.put("data", assistiti);
				res.put("code", Status.OK.getStatusCode());
				return corsedResponse.status(Status.OK).entity(res).build();

			} else {
				datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(elencoAssistiti.get(0).getCodiceFiscale()));
				if (datiCittadino.isEmpty()) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					message = messaggioIf.getMessaggio("MSG024").getTesto();
				}
				else {
					 CittadinoEsenpat cittadino = IntegrationClientImpl.getInstance().getCittadinoEsenpat(datiCittadino.get(0).getIdAura());
				        if (isCittadinoPiemontese(cittadino)) {
				        	if (Checker.isValorizzato(codAsl)) {
							if (!(cittadino.getCodASL().equalsIgnoreCase(utente.getCodASL()))) {
								messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
								message = messaggioIf.getMessaggio("MSG024").getTesto();
								datiCittadino.clear();
							} 
				        	}
						} else {
							message = getMessaggioEsenpat(Constants.MSG214).getTesto();
						datiCittadino.clear();
						}
					 
				}
				
				
					registraSuAudit(req.getRemoteAddr(), "1 AnagrafeFind OK " + message, operazione, "READ", utente.getCodFisc(), null, null, null,
						elencoAssistiti.get(0).getCodiceFiscale(), null);
				
				
				res.put("status", "ok");
				res.put("data", datiCittadino);
				res.put("code", Status.OK.getStatusCode());
				res.put("message", message);
				return corsedResponse.status(Status.OK).entity(res).build();
			}

		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("data", null);
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (AuraIntegrationException auraEx) {
			res.put("status", "ok");
			res.put("data", null);
			res.put("code", Status.OK.getStatusCode());
			res.put("message", auraEx.getDescrizione());
			return corsedResponse.status(Status.OK).entity(res).build();
		}
		catch (Exception e) { e.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG023"), e.getLocalizedMessage());
			res.put("status", "ko");
			res.put("data", null);
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		} 

	}

	@POST
	@Path("/getRicercaCertificatoPaginato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRicercaCertificatoPaginato(@Context HttpServletRequest req,
			FiltriRicercaCertificatoPatologia filtri) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		  final String service = "Richiesta Certificato";
		     final String operazione = "READ";
		List<EsenzioneAssistito> elencoEsenzioniOut = new ArrayList<EsenzioneAssistito>();

		try {
			CheckRicercaEsenzioneOperatore.chkFiltriCertificatoPatologia(filtri);
			String codASLOperatore = null;
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			List<Ruolo> ruoli = getRuoli(req,userInfo);
			registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, userInfo.getCodFisc(), null, null, null,
					filtri.getCodFiscaleBeneficiario(), null);
			
			for (Ruolo ruolo : ruoli) {
				//medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
					codASLOperatore = null;
					break;
				}
				else
					codASLOperatore = userInfo.getCodASL();
			}
			

				if (!checkAzioneUtente(req, "OP-RicercaRichiestaCertificati",ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, service, operazione, userInfo.getCodFisc(), null,
						null, null, filtri.getCodFiscaleBeneficiario(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ELEMENTI_RICERCA");
			int dimensionePagina = Integer.parseInt(elencoParametri.get(0).getValore());
				
			esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			
			Long contarecord = esenzioneTPraticaEsenzioneIf.ContaRicercaCertificato(filtri, codASLOperatore);
			List<EsenzioneTPraticaEsenzione> elencoEsenzioni = esenzioneTPraticaEsenzioneIf.getRicercaCertificato(filtri, codASLOperatore,dimensionePagina);
			

			if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioni);

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			//prendi gli id aura differenti
			List<EsenzioneAssistito> listaEsenzioniAssistito = new ArrayList<EsenzioneAssistito>();

			if (elencoEsenzioni != null && elencoEsenzioni.size() > 0) {
				for (EsenzioneTPraticaEsenzione esenzione : elencoEsenzioni) {
					listaEsenzioniAssistito.add(new EsenzioneAssistito(esenzione));
				}
			}

			for (EsenzioneAssistito esenzione : listaEsenzioniAssistito) {
				esenzione.setNumeroTotaleElementi(contarecord.intValue());
				elencoEsenzioniOut.add(new EsenzioneAssistito(esenzione));
			}
			
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", elencoEsenzioniOut);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			registraSuAudit(req.getRemoteAddr(), "0 KO " + error, service, operazione, filtri.getCodFiscaleBeneficiario(), null,
					null, null, filtri.getCodFiscaleBeneficiario(), null);
			
			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());

			registraSuAudit(req.getRemoteAddr(), "0 KO " + error, service, operazione, filtri.getCodFiscaleBeneficiario(), null,
					null, null, filtri.getCodFiscaleBeneficiario(), null);
			
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/documento/{sk_repository}")
	public Response getDocumentoService(@Context HttpServletRequest req,
			@PathParam("sk_repository") String pkRepository) throws Exception {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		log.info("getDocumentoService", " BEGIN");
		try {
			EsenzioneTRepositoryDocumentale documento = null;
			log.info("getDocumentoService", " Ricerco doc per pkRepository: " + pkRepository);
			certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
			documento = certificatiFacade.getDocumento(pkRepository);

			if (documento != null) {

				res.put("status", "ok");
				res.put("data", documento);
				res.put("code", Status.OK.getStatusCode());
				
				log.info("getDocumentoService", " END");

				return corsedResponse.status(Status.OK).entity(res).build();
			}

		} catch (Exception e) { e.printStackTrace();
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return null;
	}
	
	 @GET
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("/patologie/{gruppo}")
	  public Response getPatologie(@Context HttpServletRequest req, @PathParam("gruppo") String gruppoEsenzione)  
	      throws Exception 
	  {
	    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse(); 
	    Map<String, Object> res = new HashMap<String, Object>();
	   
	    try {     
	      Set<Esenzione> listaPatologie = null;
	        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
	        listaPatologie = esenzioniFacade.getListaPatologie(gruppoEsenzione);
	       
	        if (listaPatologie != null) {

	          res.put("status", "ok");
	          res.put("data", listaPatologie);
	          res.put("code", Status.OK.getStatusCode());

	          return corsedResponse.status(Status.OK).entity(res).build();   
	        }
	            

	    } catch (Exception e) { e.printStackTrace();
	      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
	    }
	    
	    return null;
	  }
	 
	 
	  @GET
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("/esenzioni")
	  public Response getListaEsenzioni(@Context HttpServletRequest req)
	      throws Exception {
	    Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	    Map<String, Object> res = new HashMap<String, Object>();

	    try {
	      List<ListaEsenzioni> listaEsenzioni = null;
	      esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
	      listaEsenzioni = esenzioniFacade.getListaEsenzioni();

	      if (listaEsenzioni != null) {

	        res.put("status", "ok");
	        res.put("data", listaEsenzioni);
	        res.put("code", Status.OK.getStatusCode());

	        return corsedResponse.status(Status.OK).entity(res).build();
	      }

	    } catch (Exception e) { e.printStackTrace();
	      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
	    }

	    return null;
	  }
	  
	   @GET
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/diagnosi")
	    public Response getListaDiagnosi(@Context HttpServletRequest req)
	        throws Exception {
	      Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	      Map<String, Object> res = new HashMap<String, Object>();

	      try {
	        Set<ListaDiagnosi> listaDiagnosi = null;
	        esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
	        listaDiagnosi = esenzioniFacade.getListaDiagnosi();

	        if (listaDiagnosi != null) {

	          res.put("status", "ok");
	          res.put("data", listaDiagnosi);
	          res.put("code", Status.OK.getStatusCode());

	          return corsedResponse.status(Status.OK).entity(res).build();
	        }

	      } catch (Exception e) { e.printStackTrace();
	        return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
	      }

	      return null;
	    }

	/**
	 * ESENRED-ESENPAT-CDU-016-V05-Valida Esenzioni Selezionate
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/valida-esenzioni-selezionate")
	public Response getValidaEsenzioniSelezionate(@Context HttpServletRequest req,  String[] skPraticaEsenzioni) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		 List<Map<String , Object>> listMap  = new ArrayList<Map<String,Object>>();
		 ArrayList<String> praticheok = new ArrayList<String>();
		 ArrayList<String> praticheko = new ArrayList<String>();
		 ArrayList<String> numpraticheok = new ArrayList<String>();
		 ArrayList<String> numpraticheko = new ArrayList<String>();
		 ArrayList<String> messageko = new ArrayList<String>();
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req, utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();

		String oggOper = "Valida esenzioni selezionate";
		String operazione = "READ";
		String cit_id = null;

		try {
			//verifica se vettore vuoto o pieno se vuoto errore
			if (skPraticaEsenzioni.length==0) {
				String message = getMessaggioEsenpat(Constants.MSG231).getTesto();
	            res.put("status", "ko");
	            res.put("data", null);
	            res.put("code", Status.BAD_REQUEST.getStatusCode());
	            res.put("message", message);

	            return corsedResponse.status(Status.BAD_REQUEST).entity(res).build(); 
			}
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);

			final String azioneCorrente = Constants.OP_VALIDA;
			boolean bCheckAzione = checkAzioneUtente(req, azioneCorrente, ruoli);
			if (!bCheckAzione) {
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper, operazione,
						shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			
			for (int i = 0; i < skPraticaEsenzioni.length; i++) {
				FiltriValidaEsenzionePatologia filtriValidaEsenzionePatologia = new FiltriValidaEsenzionePatologia();
				String skPraticaEsenzione = skPraticaEsenzioni[i];
				filtriValidaEsenzionePatologia.setSkPraticaEsenzione(skPraticaEsenzione);
				filtriValidaEsenzionePatologia.setAzione("VALIDA");
				
				Response response = getValidaEsenzione(req, filtriValidaEsenzionePatologia);
				Map<String, Object> mapRes = (Map<String, Object>) response.getEntity();
				mapRes.put("skPraticaEsenzione", skPraticaEsenzione);
				listMap.add(mapRes);
			}
			boolean praticheerrate = false;
			    for (Map<String, Object> map : listMap) {
			    	if (map.get("status").equals("ok")) {
			    			numpraticheok.add(map.get("numPratica").toString());
			    			praticheok.add(map.get("skPraticaEsenzione").toString());
			    		
			    	}
			    	else {
			    		numpraticheko.add(map.get("numPratica").toString());
			    		praticheko.add(map.get("skPraticaEsenzione").toString());
			    		messageko.add(map.get("message").toString());
			    		praticheerrate = true;
			    	}
			    }
			 if(!praticheerrate && praticheok.size()==skPraticaEsenzioni.length) {
				 //tutte ok
			String message = "OK-Procedi valida esenzioni selezionate";
            res.put("status", "ok");
            res.put("data", praticheok);
            res.put("datanum", numpraticheok);
            res.put("code", Status.OK.getStatusCode());
            res.put("message", message);
            return corsedResponse.status(Status.OK).entity(res).build(); 
			 }
			 else if(praticheerrate && praticheko.size()==skPraticaEsenzioni.length) {
				 //tutte ko
					String message = "KO-Procedi valida esenzioni selezionate";
		            res.put("status", "ko");
		            res.put("data", praticheko);
		            res.put("datanum", numpraticheko);
		            res.put("code", Status.BAD_REQUEST.getStatusCode());
		            res.put("messageko", messageko);
		            res.put("message", message);
		            return corsedResponse.status(Status.BAD_REQUEST).entity(res).build(); 
			}
			 else if(praticheerrate && praticheko.size()!=skPraticaEsenzioni.length) {
				//elaborazione parziale alcune pratiche ok altre ko
				 String message = "OK Parziale-Procedi valida esenzioni selezionate";
		            res.put("status", "ok");
		            res.put("dataok", praticheok);
		            res.put("datako", praticheko);
		            res.put("datanumko", numpraticheko);
		            res.put("datanumok", numpraticheok);
		            res.put("code", Status.OK.getStatusCode());
		            res.put("messageko", messageko);
		            res.put("message", message);
		            return corsedResponse.status(Status.OK).entity(res).build(); 
			 }
			 else
				 return null;
			 
		} catch (Exception e) { e.printStackTrace();
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	 
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/validaesenzione")
     public Response getValidaEsenzione(
             @Context HttpServletRequest req,FiltriValidaEsenzionePatologia filtri) {
             Map<String, Object> res = new HashMap<String, Object>();
             Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
             String operazione = filtri.getAzione();
             String service = "READ";
             messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
             String message = null;
             message = "";
         try {
             String codAsl = null;
             
             UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
             List<Ruolo> ruoli = getRuoli(req,utente);
             for (Ruolo ruolo : getRuoli(req,utente)) {
                 //medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
                     codAsl = null;
                     break;
                 }
                 else
                     codAsl = utente.getCodASL();
             }

			// verifica il ruolo
			final String azioneCorrente = Constants.OP_VALIDA;
			boolean bCheckAzione = checkAzioneUtente(req, azioneCorrente, ruoli);
			if (!bCheckAzione) {
				if (!checkAzioneUtente(req, Constants.OP_CREA_VALIDA_ESENZIONE, ruoli)) {
					registraSuAudit(req.getRemoteAddr(),
							Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", operazione, service,
							utente.getCodFisc(), null, null, null, filtri.getBeneficiario().getCodiceFiscale(), null);
					res.put("status", Status.BAD_REQUEST.getStatusCode());
					res.put("code", "Verifica utente negativa");
					res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
					return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				}
			}

             //prendi esenzione e verifica lo stato c oerente
             esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext
                     .getBean("esenzionetpraticaesenzione");
             EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf.getEsenzioneTPraticaEsenzioneperskpratica(filtri.getSkPraticaEsenzione());
             if (esenzioneTPraticaEsenzione==null) {
                 message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
                 res.put("status", "ko");
                 res.put("data", null);
                 res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                 res.put("code", Status.NOT_FOUND.getStatusCode());
                 res.put("message", message);

                 return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
             }
           
             //se procedi e valida allora 
 				 if (operazione.equalsIgnoreCase("VALIDA")) {
 	            	 if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_DA_VALIDARE) 
 	                		 && !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
 	                     message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
 	                     res.put("status", "ko");
 	                     res.put("data", null);
 	                    res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
 	                     res.put("code", Status.NOT_FOUND.getStatusCode());
 	                     res.put("message", message);

 	                     return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
 	                 }
 	             }
 	             else if (operazione.equalsIgnoreCase("APPROVAEVALIDA")) {
 	            	 if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_INVIATA) ) {
 	                     message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
 	                     res.put("status", "ko");
 	                     res.put("data", null);
 	                     res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
 	                     res.put("code", Status.NOT_FOUND.getStatusCode());
 	                     res.put("message", message);

 	                     return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
 	                 }
 	             }
 				 // in caso di chiamata da procedi-valida-esenzioni-selezionate carico beneficiario
 				 if (filtri.getBeneficiario() == null) {
 					if (esenzioneTPraticaEsenzione.getEsenzioneTCittadino1() != null) {
 						Beneficiario beneficiario = new Beneficiario(esenzioneTPraticaEsenzione.getEsenzioneTCittadino1());
 						filtri.setBeneficiario(beneficiario);
 					}
 				 }
             //chiama get aura avendo idaurabeneficiario in input 
              CittadinoEsenpat cittadino = IntegrationClientImpl.getInstance().getCittadinoEsenpat(filtri.getBeneficiario().getIdAura());
              if (cittadino != null && cittadino.getStatoProfiloAnagrafico().equals("0")) {
                  message = getMessaggioEsenpat(Constants.MSG214).getTesto();
                     res.put("status", "ok");
                     res.put("data", null);
                     res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                     res.put("code", Status.OK.getStatusCode());
                     res.put("message", message);
                     return corsedResponse.status(Status.OK).entity(res).build();
              }
              if (cittadino != null) {
                 if (isCittadinoPiemontese(cittadino)) {
                     if (Checker.isValorizzato(codAsl)) {
                     if (!(cittadino.getCodASL().equalsIgnoreCase(codAsl))) {
                         message = messaggioIf.getMessaggio("MSG024").getTesto();
                         res.put("status", "ok");
                         res.put("data", null);
                         res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                         res.put("code", Status.OK.getStatusCode());
                         res.put("message", message);
                         return corsedResponse.status(Status.OK).entity(res).build();
                     } 
                     }
                 } else {
                         message = getMessaggioEsenpat(Constants.MSG214).getTesto();
                         res.put("status", "ok");
                         res.put("data", null);
                         res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                         res.put("code", Status.OK.getStatusCode());
                         res.put("message", message);
                         return corsedResponse.status(Status.OK).entity(res).build();
                 }
              }
              else
              {
                     message = messaggioIf.getMessaggio("MSG024").getTesto();
                     res.put("status", "ok");
                     res.put("data", null);
                     res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                     res.put("code", Status.OK.getStatusCode());
                     res.put("message", message);
                     return corsedResponse.status(Status.OK).entity(res).build(); 
              }
             
             
              
                     registraSuAudit(req.getRemoteAddr(), "1 AnagrafeFind OK " + message, operazione, "READ", utente.getCodFisc(), null, null, null,
					filtri.getBeneficiario().getCodiceFiscale(), null);
			        
    
                    
                     //prendi le esenzioni e confrontale con quella cghe stai validando      
                     boolean trovato = false;
                     Set<EsenzioneAssistito> listaEsenzioniAssistito = new TreeSet<EsenzioneAssistito>();
                     //peer ora prendo solo le esenzioni di aura
                     Date dataCorrente = new Date();
                     if (cittadino.getEsenzioniAura() != null && cittadino.getEsenzioniAura().getInfoesenzione() != null
                             && cittadino.getEsenzioniAura().getInfoesenzione().length > 0) {
                         for (InfoEsenzioneNew e : cittadino.getEsenzioniAura().getInfoesenzione()) {
                             listaEsenzioniAssistito.add(new EsenzioneAssistito(e, cittadino, utente));
                     }
                     }
                     for (EsenzioneAssistito esenzione : listaEsenzioniAssistito) {
                         boolean dataminore = true;
                         if (esenzione.getDataScadenza()!=null) {
                         Date scadenza = Converter.getData(esenzione.getDataScadenza());
                         dataminore = dataCorrente.compareTo(scadenza) < 0 ? true : false;
                         }   
                         if (esenzione.getCodiceEsenzione().equalsIgnoreCase(filtri.getCodEsenzione()) 
                                 && esenzione.getBeneficiario().getCodiceFiscale().equalsIgnoreCase(filtri.getBeneficiario().getCodiceFiscale())
                                 && dataminore && esenzione.getDataSospensione()==null) {
                              trovato = true;
                              break;
                         }
                     }
                     if (trovato) {
                         message = messaggioIf.getMessaggio("MSG224").getTesto();
                         res.put("status", "ok");
                         res.put("data", null);
                         res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                         res.put("code", Status.OK.getStatusCode());
                         res.put("message", message);
                         return corsedResponse.status(Status.OK).entity(res).build(); 
                     }
                     //se non esiste posso procedere con apertura del riepilogo
                     message = "OK-Procedi Valida";
                     res.put("status", "ok");
                     res.put("data", null);
                     res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
                     res.put("code", Status.OK.getStatusCode());
                     res.put("message", message);
                     return corsedResponse.status(Status.OK).entity(res).build(); 
                     
         }
         catch (Exception e) { e.printStackTrace();
             messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
             String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG023"), e.getLocalizedMessage());
             res.put("status", "ko");
             res.put("data", null);
             res.put("numPratica",null);
             res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
             res.put("message", messaggio);

             return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
         } 

     }
     
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/procedi-valida-esenzioni-selezionate")
	public Response getProcediValidaEsenzioniSelezionate(@Context HttpServletRequest req, String[] skPraticaEsenzioni) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		 List<Map<String , Object>> listMap  = new ArrayList<Map<String,Object>>();
		 ArrayList<String> praticheok = new ArrayList<String>();
		 ArrayList<String> praticheko = new ArrayList<String>();
		 ArrayList<String> numpraticheok = new ArrayList<String>();
		 ArrayList<String> numpraticheko = new ArrayList<String>();
		 ArrayList<String> messageko = new ArrayList<String>();
		 ArrayList<String> messageok = new ArrayList<String>();
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		String xcodServizio = null;

		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req, utente);
		String shibIdentitaCodiceFiscale = utente.getCodFisc();

		String oggOper = "Procedi valida esenzioni selezionate";
		String operazione = "UPDATE";
		String cit_id = null;

		try {
			
			registraSuAudit(XForwardedFor, "1 " + Status.OK.getReasonPhrase(), oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID,
					null, xcodServizio, cit_id, null);

			final String azioneCorrente = Constants.OP_VALIDA;
			boolean bCheckAzione = checkAzioneUtente(req, azioneCorrente, ruoli);
			if (!bCheckAzione) {
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", oggOper, operazione,
						shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);

				res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "Verifica utente negativa");
				res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			for (int i = 0; i < skPraticaEsenzioni.length; i++) {
				FiltriValidaEsenzionePatologia filtriValidaEsenzionePatologia = new FiltriValidaEsenzionePatologia();
				String skPraticaEsenzione = skPraticaEsenzioni[i];
				filtriValidaEsenzionePatologia.setSkPraticaEsenzione(skPraticaEsenzione);
				filtriValidaEsenzionePatologia.setAzione("VALIDA");

				Response response = getProcediValidaEsenzione(req, filtriValidaEsenzionePatologia);
				Map<String, Object> mapRes = (Map<String, Object>) response.getEntity();
				mapRes.put("skPraticaEsenzione", skPraticaEsenzione);
				listMap.add(mapRes);
			}
			boolean praticheerrate = false;
		    for (Map<String, Object> map : listMap) {
		    	if (map.get("status").equals("ok")) {
		    		numpraticheok.add(map.get("numPratica").toString());
		    		praticheok.add(map.get("skPraticaEsenzione").toString());
		    		messageok.add(map.get("message").toString());
		    	}
		    	else {
		    		numpraticheko.add(map.get("numPratica").toString());
		    		praticheko.add(map.get("skPraticaEsenzione").toString());
		    		messageko.add(map.get("message").toString());
		    		praticheerrate = true;
		    	}
		    }
		    
		    if(!praticheerrate && praticheok.size()==skPraticaEsenzioni.length) {
				 //tutte ok
			String message = "OK-Procedi valida esenzioni selezionate";
           res.put("status", "ok");
           res.put("data", praticheok);
           res.put("datanum", numpraticheok);
           res.put("code", Status.OK.getStatusCode());
           res.put("message", message);
           res.put("messageok", messageok);
       	    return corsedResponse.status(Status.OK).entity(res).build(); 
			 }
			 else if(praticheerrate && praticheko.size()==skPraticaEsenzioni.length) {
				 //tutte ko
					String message = "KO-Procedi valida esenzioni selezionate";
		            res.put("status", "ko");
		            res.put("data", praticheko);
		            res.put("datanum", numpraticheko);
		            res.put("code", Status.BAD_REQUEST.getStatusCode());
		            res.put("messageko", messageko);
		            res.put("message", message);
		            return corsedResponse.status(Status.BAD_REQUEST).entity(res).build(); 
			}
			 else if(praticheerrate && praticheko.size()!=skPraticaEsenzioni.length) {
				//elaborazione parziale alcune pratiche ok altre ko
				 String message = "OK Parziale-Procedi valida esenzioni selezionate";
		            res.put("status", "ok");
		            res.put("dataok", praticheok);
		            res.put("datako", praticheko);
		            res.put("datanumok", numpraticheok);
		            res.put("datanumko", numpraticheko);
		            res.put("code", Status.OK.getStatusCode());
		            res.put("messageko", messageko);
		            res.put("messageok", messageok);
		            res.put("message", message);
		            return corsedResponse.status(Status.OK).entity(res).build(); 
			 }
			 else
				 return null;


		} catch (Exception e) { e.printStackTrace();
			e.printStackTrace();

			String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();
			res.put(Constants.STATUS, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.CODE, Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put(Constants.MESSAGE, messaggio);

			
			registraSuAudit(XForwardedFor, "500 Errore generico", oggOper, operazione, shibIdentitaCodiceFiscale, xRequestID, null,
					xcodServizio, cit_id, null);
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
     
	   
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/procedivalidaesenzione")
	public Response getProcediValidaEsenzione(@Context HttpServletRequest req, FiltriValidaEsenzionePatologia filtri) {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		log.info("POST procedivalidaesenzione", "BEGIN");
		String operazione = filtri.getAzione();
		String service = "Write";
		messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
		String message = null;
		UserInfo utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req, utente);
		try {
			// se non esiste posso procedere con apertura del riepilogo
			// inserisci i dati in tabella dello storico
			esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext
					.getBean("esenzionetpraticaesenzione");
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = esenzioneTPraticaEsenzioneIf
					.getEsenzioneTPraticaEsenzioneperskpratica(filtri.getSkPraticaEsenzione());
			if (esenzioneTPraticaEsenzione == null) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), "Pratica non trovata");
				res.put("status", "ko");
				res.put("data", null);
				res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", message);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			}

			if (operazione.equalsIgnoreCase("VALIDA")) {
				if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato()
						.equalsIgnoreCase(Constants.STATO_PRATICA_DA_VALIDARE)
						&& !esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato()
								.equalsIgnoreCase(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
					message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
					res.put("status", "ko");
					res.put("data", null);
					res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
					res.put("code", Status.NOT_FOUND.getStatusCode());
					res.put("message", message);

					return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
				}
			} else if (operazione.equalsIgnoreCase("APPROVAEVALIDA")) {
				if (!esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato()
						.equalsIgnoreCase(Constants.STATO_PRATICA_INVIATA)) {
					message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
					res.put("status", "ko");
					res.put("data", null);
					res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
					res.put("code", Status.NOT_FOUND.getStatusCode());
					res.put("message", message);

					return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
				}
			}

			// in caso di chiamata da procedi-valida-esenzioni-selezionate carico
			// beneficiario
			if (filtri.getBeneficiario() == null) {
				if (esenzioneTPraticaEsenzione.getEsenzioneTCittadino1() != null) {
					Beneficiario beneficiario = new Beneficiario(esenzioneTPraticaEsenzione.getEsenzioneTCittadino1());
					filtri.setBeneficiario(beneficiario);
				}
			}

			// in caso di chiamata da procedi-valida-esenzioni-selezionate carico
			// codEsenzione
			if (filtri.getCodEsenzione() == null) {
				if (esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi() != null
						&& esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDEsenzione() != null) {
					filtri.setCodEsenzione(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDEsenzione()
							.getCodEsenzione());
				}
			}

			// insierimento in aura della patologia
			// scrittura file di log
			// chiamo il servizio EsenzionePatologia se esito ok allora update documento con
			// stato valido altimenti errore
			EsenzionePatologia esenzionepatologia = new EsenzionePatologia();
			esenzionepatologia.setCodiceEsenzione(filtri.getCodEsenzione());
			esenzionepatologia.setCodiceFiscaleAssistito(filtri.getBeneficiario().getCodiceFiscale());
			esenzionepatologia.setCodiceFiscaleChiamante(utente.getCodFisc());

			EsenzioniFacade esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");

			Timestamp fineVal = esenzioniFacade.calcolaDataFineValidita(
					esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDDurataTipo(),
					esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi());

			String datainiziovalidita = Converter.getData(new Date(), "dd/MM/yyyy");
			String datafinevalidita = fineVal != null ? Converter.getData(fineVal, "dd/MM/yyyy") : null;

			if (esenzioneTPraticaEsenzione.getDatInizioValidita() != null)
				datainiziovalidita = Converter
						.getData(Converter.getData(esenzioneTPraticaEsenzione.getDatInizioValidita()), "dd/MM/yyyy");
			if (esenzioneTPraticaEsenzione.getDatFineValidita() != null)
				datafinevalidita = Converter.getData(Converter.getData(esenzioneTPraticaEsenzione.getDatFineValidita()),
						"dd/MM/yyyy");
			esenzionepatologia.setDataInizioValidita(datainiziovalidita);
			esenzionepatologia.setDataFineValidita(datafinevalidita);

			esenzionepatologia.setListaDiagnosi(
					esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi().startsWith("F") ? "000"
							: esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());
			log.info("POST procedivalidaesenzione"," Inizio InserisciEsenzionePatologia (aura) ");
			EsenzionePatologiaRes esitoinserimento = IntegrationClientImpl.getInstance()
					.InserisciEsenzionePatologia(esenzionepatologia);
			
			if ("OK".equalsIgnoreCase(esitoinserimento.getHeader().getCodiceRitorno())) {
				// aggiorno lo stato del documento a validata
				registraSuAudit(req.getRemoteAddr(), "InserisciEsenzionePatologia OK", operazione, "INSERT",
						utente.getCodFisc(), null, null, null, filtri.getBeneficiario().getCodiceFiscale(), null);
				// aggiorna i dati nella tabella delle pratiche di esenzione

				EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();
				EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione = new EsenzioneSPraticaEsenzione();
				esenzioneSPraticaEsenzione = esenzioniFacade.copyInEsenzioneSPraticaEsenzione(
						esenzioneTPraticaEsenzione,
						utente.getCodFisc().equalsIgnoreCase(filtri.getBeneficiario().getCodiceFiscale()));
				esenzioneTPraticaEsenzione = esenzioniFacade.updatePraticaEsenzioneValidata(esenzioneTPraticaEsenzione,
						utente, filtri, ruoli);
				// rifaccio chiamata ad aura per riprendere l'esenzione validata
				CittadinoEsenpat datiCittadino = new CittadinoEsenpat();
				datiCittadino = IntegrationClientImpl.getInstance()
						.getCittadinoEsenpat(filtri.getBeneficiario().getIdAura());

				// Generazione del XML CDA
				byte[][] XMLS = esenzioniFacade.generateEsenzioneXmlCda(null, utente, datiCittadino);
				if(XMLS == null) {
					res = new HashMap<String, Object>();		
					res.put("status", "ko");
					res.put("data", null);
					res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
					res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
					res.put("message", "non ci sono esenzioni valide");
					return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
				}
				byte[] filePdf = Base64.getEncoder().encode(esenzioniFacade.generatePdf(XMLS));
				repoDocumentale = esenzioniFacade.insertRepositoryDocumentale(utente, filePdf, ruoli);

				// Copio nello storico
				CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext
						.getBean("certificatiFacade");
				EsenzioneTDocumento attestatoEsenzione = certificatiFacade
						.getEsenzioneTDocumentoAttestato(datiCittadino.getCodFiscale());
				if (attestatoEsenzione != null) {
					certificatiFacade.copyTDocumentoToSDocumento(attestatoEsenzione, esenzioneTPraticaEsenzione);
					certificatiFacade.annullaAttestato(attestatoEsenzione);
				}
				// inserisci una riga in csi_audit
				registraSuAudit(req.getRemoteAddr(), "inserisciDocumentoService OK ", operazione, "INSERT",
						utente.getCodFisc(), null, null, null, filtri.getBeneficiario().getCodiceFiscale(), null);
				EsenzioneTDocumento esenzioneTDocumento = new EsenzioneTDocumento();
				esenzioneTDocumento = esenzioniFacade.insertDocumentoTDocumento(esenzioneTPraticaEsenzione, utente,
						filtri, new Long(repoDocumentale.getSkRepository()), ruoli);

				registraSuAudit(req.getRemoteAddr(), "1 Anagrafe Sanitaria OK ", operazione, "READ",
						utente.getCodFisc(), null, null, null, filtri.getBeneficiario().getCodiceFiscale(), null);
				// insierimento nuovo attestato nel db
				EsenzioneTMetadatiDocumento metadati = new EsenzioneTMetadatiDocumento();
				metadati = esenzioniFacade.insertMetadatiDocumento(esenzioneTDocumento, utente, datiCittadino, ruoli);
				esenzioneTDocumento = esenzioniFacade.UpdateDocumentoTDocumento(esenzioneTDocumento);
				// inserisco il legame tra
				Certificato certificato = new Certificato();
				Malattia malattia = new Malattia();
				malattia.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());
				malattia.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getDescDiagnosi());
				malattia.setGiorni_validita(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getNumDurata());
				certificato.setMalattia(malattia);
				certificato.setData_fine_validita(null);
				if (esenzioneTDocumento.getDatFineValidita() != null) {
					certificato.setData_fine_validita(
							Converter.getData(Converter.getData(esenzioneTDocumento.getDatFineValidita())));
				}

				certificatiFacade.insertEsenzioneRPraticaDocumento(certificato,
						new Long(esenzioneTDocumento.getSkDocumento()), esenzioneTPraticaEsenzione);
				message = esitoinserimento.getHeader().getListaMessaggi().get(0).getDescrizione();
				res.put("status", "ok");
				res.put("data", esenzionepatologia);
				res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
				res.put("code", Status.OK.getStatusCode());
				res.put("message", message);
				return corsedResponse.status(Status.OK).entity(res).build();
			} else {
				registraSuAudit(req.getRemoteAddr(), "InserisciEsenzionePatologia KO", operazione, "INSERT",
						utente.getCodFisc(), null, null, null, filtri.getBeneficiario().getCodiceFiscale(), null);
				message = esitoinserimento.getHeader().getListaMessaggi().get(0).getDescrizione();
				res.put("status", "ko");
				res.put("data", null);
				res.put("numPratica", esenzioneTPraticaEsenzione.getNumPratica().toString());
				res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
				res.put("message", message);
				return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
			}
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());
			res.put("numPratica", null);
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG023"), e.getLocalizedMessage());
			res.put("status", "ko");
			res.put("data", null);
			res.put("numPratica", null);
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", messaggio);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}finally {
			log.info("POST procedivalidaesenzione", "END");
		}

	}
	   
   /*
    * CDU 06 Invia Richiesta Esenzione
    * creazione di una nuova richiesta esenzione 
    */
   @POST
   @Path("/inviaRichiestaEsenzione")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response inviaRichiestaEsenzione(@Context HttpServletRequest req, NuovaEsenzione praticaEsenzione) throws Exception 
   {

     Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
     Map<String, Object> res = new HashMap<String, Object>();
     Map<String, Object> resAura = new HashMap<String, Object>();

		final String operazione = "Invia Richiesta Esenzione";
		final String service = "INSERT";
     
     UserInfo utente = null;
     
     try {
       utente = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
       List<Ruolo> ruoli = getRuoli(req,utente);
       // controllo se sono valorizzati i dati di UserInfo
       if (!Checker.isValorizzato(utente.getIdAura())) {
         throw new EsenpatException(Constants.MSG228);
       }

			final String azioneCorrente = Constants.OP_INSERISCI_NUOVA_ESENZIONE;
			boolean bCheckAzione = checkAzioneUtente(req, azioneCorrente, ruoli);
			if (!bCheckAzione) {
				if (!checkAzioneUtente(req, Constants.OP_CREA_VALIDA_ESENZIONE, ruoli)) {
					registraSuAudit(req.getRemoteAddr(),
							Status.BAD_REQUEST.getStatusCode() + " " + "Verifica utente negativa", operazione, service,
							utente.getCodFisc(), null, null, null, praticaEsenzione.getAssistito().getCodFiscale(),
							null);

					res.put("status", Status.BAD_REQUEST.getStatusCode());
					res.put("code", "Verifica utente negativa");
					res.put("message", "Utente non abilitato all'azione '" + azioneCorrente + "'");
					return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				}
			}

			
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
					praticaEsenzione.getAssistito().getCodFiscale(), null);

       // Verifica CF cittadino e residenza
       resAura = verificaCittadinoSuAura(req.getRemoteAddr(), operazione, utente, praticaEsenzione.getAssistito().getCodFiscale(), ruoli);

       if (resAura.containsKey(CITTADINO_AURA)) {
         
         Cittadino cittadino = (Cittadino) resAura.get(CITTADINO_AURA);
         CittadinoEsenpat citEsenpat = (CittadinoEsenpat) resAura.get(CITTADINO_AURA);

         esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");

           //controllo praticaEsenzioneGiaEsistente
         Set<EsenzioneAssistito> listaEsenzioniAssistito = esenzioniFacade.getListaEsenzioniByCodiceFiscaleAssistito(citEsenpat, utente, null);
         
         for (EsenzioneAssistito esenzione : listaEsenzioniAssistito) {
           if(esenzione.getCodiceEsenzione() == praticaEsenzione.getMalattia().getCodEsenzione())
           {
             //controllo date validit�
        	 int checkStatus = ArrayUtils.indexOf(Constants.CHECK_LISTA_STATI, esenzione.getStatoPratica());
             if(checkStatus != -1)
             {
                 throw new EsenpatException(Constants.MSG230);
             }
           }
         }
         
         //controllo se gia esiste una esenzione
         //getListaEsenzioniByCodiceFiscaleAssistito
         
         // insert or update cittadino
           cittadinoFacade = (CittadinoFacade) SpringApplicationContext.getBean("cittadinoFacade");
           cittadinoFacade.insertOrUpdateCittadino(cittadino);
         
         // inizio creazione nuova pratica
         EsenzioneTPraticaEsenzione ec = esenzioniFacade.insertNuovaPraticaEsenzione(praticaEsenzione, utente, cittadino);
        
         
         if (ec != null) {
             
           registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, utente.getCodFisc(), null, null, null,
							praticaEsenzione.getAssistito().getCodFiscale(), null);
            
           res.put("status", "ok");
           res.put("code", Status.OK.getStatusCode());
           res.put("num_esenzione", ec.getNumPratica().toString());
           res.put("id_esenzione", ec.getSkPraticaEsenzione().toString());
           
           return corsedResponse.status(Status.OK).entity(res).build();
         }
         
       }
       else
       {
         throw new EsenpatException(Constants.MSG228);
       }
   
     } catch (EsenpatException ee) {
       String error = getMessaggioEsenpat(ee.getCodice()).getTesto();
     
       registraSuAudit(req.getRemoteAddr(), "0 KO " + error, operazione, service, utente.getCodFisc(), null, null, null,
					praticaEsenzione.getAssistito().getCodFiscale(), null);

       res.put("status", "ko");
       res.put("code", Status.BAD_REQUEST.getStatusCode());
       res.put("message", error);
       return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
       
     } catch (Exception e) { e.printStackTrace();
       String messaggio = getMessaggioEsenpat(Constants.MSG200).getTesto();

       registraSuAudit(req.getRemoteAddr(), "0 KO " + messaggio, operazione, service, utente.getCodFisc(), null, null, null,
					praticaEsenzione.getAssistito().getCodFiscale(), null);
       
       res.put("status", "ko");
       res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
       res.put("message", messaggio);
       return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();    
       }
  
    return null;
   }
   
   @GET
	@Path("/getStatoPratica/{skAzione}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatoPratica(
			@PathParam("skAzione") String skAzione) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		try {
			List<StatoDocumento> stato = new ArrayList<StatoDocumento>();
			statoPraticaIf = (StatoPraticaIf) SpringApplicationContext.getBean("statopratica");
			List<EsenzioneDPraticaStato> statodocumento = statoPraticaIf.getStatoPratica(skAzione);
			for (Iterator<EsenzioneDPraticaStato> iterator = statodocumento.iterator(); iterator.hasNext();) {
				EsenzioneDPraticaStato eDB = (EsenzioneDPraticaStato) iterator.next();
				stato.add(new StatoDocumento(eDB));
			}
			if (!stato.isEmpty()) {
				res.put("status", "ok");
				res.put("data", stato);
				res.put("code", Status.OK.getStatusCode());

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			res.put("status", "ok");
			res.put("data", null);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (Exception e) { e.printStackTrace();
			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}	  
   
   @POST
	@Path("/getEsenzionePatologiaPaginato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEsenzionePatologiaPaginato(@Context HttpServletRequest req,
			FiltriRicercaCertificatoPatologia filtri) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		  final String service = "Ricerca Esenzione";
		     final String operazione = "READ";
		     List<EsenzioneAssistito> elencoEsenzioniOut = new ArrayList<EsenzioneAssistito>();
		     String cfMed = null;
		     
		try {
			CheckRicercaEsenzioneOperatore.chkFiltriCertificatoPatologia(filtri);
			String codASLOperatore = null;
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			List<Ruolo> ruoli = getRuoli(req,userInfo);
			codASLOperatore = userInfo.getCodASL();

			for (Ruolo ruolo : ruoli) {
				//medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
					codASLOperatore = null;
				}
			
				parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("FILTRO_MOSTRA_SOLO_ESENZIONI_CREATE");
				boolean mostraSoloEsenzioniCreate = Boolean.parseBoolean(elencoParametri.get(0).getValore());
				if(mostraSoloEsenzioniCreate && (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_SPECIALISTA))) {
					cfMed = userInfo.getCodFisc();
					
				}
			}
			
			registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, userInfo.getCodFisc(), null, null, null,
					filtri.getCodFiscaleBeneficiario(), null);

				if (!checkAzioneUtente(req,"OP-RicercaEsenzioni",ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, service, operazione, userInfo.getCodFisc(), null,
						null, null, filtri.getCodFiscaleBeneficiario(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ELEMENTI_RICERCA");
			//cerco le esenzioni di esenpat
			esenzioneTPraticaEsenzioneIf = (EsenzioneTPraticaEsenzioneIf) SpringApplicationContext.getBean("esenzionetpraticaesenzione");
			List<EsenzioneTPraticaEsenzione> elencoEsenzioni = esenzioneTPraticaEsenzioneIf.getEsenzionePatologia(filtri, codASLOperatore, cfMed);

			Set<EsenzioneAssistito> listaEsenzioniAssistito = new TreeSet<EsenzioneAssistito>();
			HashMap<String, String> idAuraBeneficiario = new HashMap<String, String>();
			
			  if (elencoEsenzioni != null && elencoEsenzioni.size() > 0) {
				  for (EsenzioneTPraticaEsenzione esenzione : elencoEsenzioni) {

					 	idAuraBeneficiario.put(esenzione.getEsenzioneTCittadino1().getId_aura(),esenzione.getEsenzioneTCittadino1().getCodiceFiscale());
					    listaEsenzioniAssistito.add(new EsenzioneAssistito(esenzione));
					} 
			  }

			 esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
			 if (Checker.isValorizzato(cfMed) && (filtri.getCodStatoCertificato()==null || filtri.getCodStatoCertificato().equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA))) {
				if (idAuraBeneficiario.isEmpty() && Checker.isValorizzato(filtri.getIdAuraBeneficiario())) {
					idAuraBeneficiario.put(filtri.getIdAuraBeneficiario(), filtri.getCodFiscaleBeneficiario());
				}
			 Set set = idAuraBeneficiario.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         CittadinoEsenpat assis = new CittadinoEsenpat();
		         registraSuAudit(req.getRemoteAddr(), "1 Anagrafe Sanitaria OK ", service, "READ", userInfo.getCodFisc(), null, null, null,
							mentry.getValue().toString(), null);
		         assis = IntegrationClientImpl.getInstance().getCittadinoEsenpat(mentry.getKey().toString());
		         if (assis.getEsenzioniAura() != null && assis.getEsenzioniAura().getInfoesenzione() != null
	                      && assis.getEsenzioniAura().getInfoesenzione().length > 0) {
	                  for (InfoEsenzioneNew e : assis.getEsenzioniAura().getInfoesenzione()) {

							boolean add = true;
							if (add && Checker.isValorizzato(filtri.getCodCertificato())
									&& Checker.isValorizzato(e.getCodEsenzione())) {
								if (!e.getCodEsenzione().equalsIgnoreCase(filtri.getCodCertificato()))
									add = false;
							}

							if (add && Checker.isValorizzato(filtri.getDataInizioValidita())
									&& e.getDataEmissione() != null) {
								if (e.getDataEmissione().getTime()
										.before(Converter.getData(filtri.getDataInizioValidita())))
									add = false;
							}

							if (add)
								listaEsenzioniAssistito.add(new EsenzioneAssistito(e, assis, userInfo));
	              }
	              }
	             
		      }
	         }
		      List<EsenzioneAssistito> listaesenzioni = new ArrayList<EsenzioneAssistito>();
		      for (EsenzioneAssistito listaEsenzioniAssistitoout : listaEsenzioniAssistito) {
		    	  if (listaEsenzioniAssistitoout.getSkpraticaesenzione()==null) {
		    		  //prelevo la descrizione del gruppo		    	  
		          EsenzioneDGruppoEsenzioni gruppo = esenzioniFacade.getGruppoEsenzioneByCodEsenzione(listaEsenzioniAssistitoout.getCodiceEsenzione());
		    	  if (gruppo!=null)
		    	  listaEsenzioniAssistitoout.setDescGruppo(gruppo.getDescGruppo());
		    	  //prelevo la descrizione della esenzione
		    	  EsenzioneDEsenzione descesenzione = esenzioniFacade.getEsenzioneDEsenzione(listaEsenzioniAssistitoout.getCodiceEsenzione());
		    	  if (descesenzione!=null)
		    		  listaEsenzioniAssistitoout.setDescEsenzione(descesenzione.getDescEsenzione());
		    	  }
		    	  listaesenzioni.add(listaEsenzioniAssistitoout);
		      }
			if (listaesenzioni == null || listaesenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioni);

				return corsedResponse.status(Status.OK).entity(res).build();
			}
			
			Collections.sort(listaesenzioni);
			
			
			int dimensionePagina = Integer.parseInt(elencoParametri.get(0).getValore());
			if (filtri.getPagina() != 0 && (listaesenzioni.size() > dimensionePagina)) {
				int lastElement;
				if (filtri.getPagina() == ((int) Math.ceil(listaesenzioni.size() / dimensionePagina) + 1))
					lastElement = listaesenzioni.size();
				else
					lastElement = dimensionePagina * filtri.getPagina();
				int FirstElement = dimensionePagina * filtri.getPagina();
				List<EsenzioneAssistito> elencoEsenzioniTmp = listaesenzioni
						.subList(FirstElement - dimensionePagina, lastElement);
				for (EsenzioneAssistito esenzioneTEsenzione : elencoEsenzioniTmp) {
					 elencoEsenzioniOut.add(new EsenzioneAssistito(esenzioneTEsenzione));
				      }
			} else {
				for (EsenzioneAssistito esenzioneTEsenzione : listaesenzioni) {
					 elencoEsenzioniOut.add(new EsenzioneAssistito(esenzioneTEsenzione));
				      }
				}
				
			for (EsenzioneAssistito ese : elencoEsenzioniOut) {
				ese.setNumeroTotaleElementi(listaesenzioni.size());
			}
	
			registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, userInfo.getCodFisc(), null, null, null,
					filtri.getCodFiscaleBeneficiario(), null);
			
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", elencoEsenzioniOut);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

   /**
	 * Esenpat CDU 023 Firma Digitale
	 * 
	 * @param req
	 * @param pkRepository
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/setFirmaDigitale")
	public Response setFirmaDigitale(@Context HttpServletRequest req, FiltriFirmaDigitale filtri) throws Exception {
		String service = "insert";
		String operazione = "setFirmaDigitale";
		log.info(operazione, "BEGIN");
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

			if (filtri==null || !Checker.isValorizzato(filtri.getAlias()) 
					|| !Checker.isValorizzato(filtri.getPin()) || !Checker.isValorizzato(filtri.getPkDocumento())) {
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(req.getRemoteAddr(),  Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());
				res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
				log.error(operazione, " Errore per Parametri obbligatori mancanti");
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}
			List<Ruolo> ruoli = getRuoli(req, userInfo);
			
			// Passo 2: Invoca getDocumentoService
			Response resp = getDocumentoService(req, filtri.getPkDocumento());
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, "getDocumentoService", userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

			EsenzioneTRepositoryDocumentale documento = (EsenzioneTRepositoryDocumentale)((HashMap<String, Object>) resp.getEntity()).get("data");
			
			log.info(operazione, " dopo ricerca documento -> ok");
			Response responseSign = null;
			try {
				ProxySignStub stub = new ProxySignStub();
				certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
				
				byte[] pdfSigned = certificatiFacade.getDocumento(
						filtri.getPkDocumento(), userInfo, documento.getFile(),
						ruoli);
				
				responseSign = stub.firmaDocumento(pdfSigned, filtri.getPin(), filtri.getAlias());
			} catch (ProcessingException e) {e.printStackTrace();}
						
			registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, "firmaInfocert", userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

			if (responseSign == null) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG200").getTesto();

				res.put("status", "ko");
				res.put("code", Status.NOT_FOUND.getStatusCode());
				res.put("message", error);

				return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
			} else if (responseSign.getStatus() == Status.OK.getStatusCode()) {
				Map<String, Object> respSignMap = (HashMap<String, Object>) responseSign.getEntity();
				
				InputStream fileOut = (InputStream)respSignMap.get("data");
				esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
				EsenzioneTRepositoryDocumentale out = esenzioniFacade.aggiornaCertificatoFirmato(
						filtri.getPkDocumento(), userInfo, Base64.getEncoder().encode(Converter.toByteArray(fileOut)),
						ruoli);
				if (out != null) {
					out.setEsenzioneSDocumentos(null);
					out.setEsenzioneTDocumentos(null);
					// inserisci una riga in csi_audit
					registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, "InserisciDocumentoService",
							userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String message = messaggioIf.getMessaggio("MSG232").getTesto();
					res.put("status", (String) respSignMap.get("status"));
					res.put("code", Status.OK.getStatusCode());
					res.put("data", out);
					res.put("message", message);
					return corsedResponse.status(Status.OK).entity(res).build();
				} else {
					throw new Exception();
				}
			} else {
				Map<String, Object> respSignMap = (HashMap<String, Object>) responseSign.getEntity();
				String infoCertMessage = (String)respSignMap.get("message");
				res.put("status", (String)respSignMap.get("status"));
				res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
				res.put("message", infoCertMessage);
				
				return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", e.getLocalizedMessage());

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	@GET
	@Path("/esenzioni/{cit_id}/allegati/{esenzione_id}/zip")
	@Produces("application/zip")
	public Response createZipAllegati(@Context HttpServletRequest req, @PathParam("cit_id") String cit_id,
			@PathParam("esenzione_id") String esenzione_id) throws Exception {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		Map<String, Object> res = new HashMap<String, Object>();
		final String service = "Create zip";
		final String operation = "READ";

		UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		List<Ruolo> ruoli = getRuoli(req,userInfo);
		String shibIdentitaCodiceFiscale = userInfo.getCodFisc();
		String xcodServizio = null;
		String XForwardedFor = req.getRemoteAddr();
		String xRequestID = null;
		try {
			
			registraSuAudit(XForwardedFor, Status.OK.getReasonPhrase(), service, operation, shibIdentitaCodiceFiscale,
					xRequestID, null, xcodServizio, cit_id, null);

			// Verifica campi obbligatori
			if (!Checker.isValorizzato(shibIdentitaCodiceFiscale) || !Checker.isValorizzato(cit_id)
					|| !Checker.isValorizzato(esenzione_id)) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(),
						service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
						null);
				// res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori
				// mancanti", Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			isTst();
			// Verifica congruita' parametri in input
			if (!Checker.isCodiceFiscale(cit_id, isTst) || !Checker.isCodiceFiscale(shibIdentitaCodiceFiscale, isTst)
					) {
				corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
				res = new HashMap<String, Object>();
				EsenredCMessaggi messaggio149 = getMessaggioEsenpat(Constants.MSG149);
				registraSuAudit(XForwardedFor, Status.BAD_REQUEST.getStatusCode() + " " + messaggio149.getTesto(),
						service, operation, shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id,
						null);
				res = generateResponseErrore(messaggio149.getTesto(), "Mancata congruita\' parametri di input",
						Status.BAD_REQUEST.getStatusCode());
				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			// Verifica CF cittadino e residenza
			res = verificaCittadinoSuAura(req.getRemoteAddr(), service, userInfo, cit_id, ruoli);

			if (res.containsKey(CITTADINO_AURA)) {
				res = new HashMap<String, Object>();
				certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
				File zip = certificatiFacade.createZipAllegati(esenzione_id);

				
				registraSuAudit(XForwardedFor, "1 ok", service, operation, shibIdentitaCodiceFiscale, xRequestID, null,
						xcodServizio, cit_id, null);

				
				corsedResponse.header("Content-Disposition", "attachment; filename=" + zip.getName());
				return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition")
						.status(Status.OK).entity(zip).build();				
				
			} else {
				throw new Exception();
			}
		} catch (EsenpatException e) {
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			res = generateResponseErrore(e.getCodMessaggio(), e.getCodice(), e.getStatus());
			registraSuAudit(XForwardedFor, e.getStatus() + " " + e.getCodMessaggio(), service, operation,
					shibIdentitaCodiceFiscale, xRequestID, null, xcodServizio, cit_id, null);
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
	
	private Map<String, Object> generateResponseErrore(String msg, String code, int status) {
	    Map<String, Object> res = new HashMap<String, Object>();
	    res = new HashMap<String, Object>();
	    res.put(Constants.STATUS, status);
	    res.put(Constants.MESSAGE, code);
	    res.put(Constants.CODE, msg);
	    return res;
	  }
	
	@POST
	@Path("/getCertificatoPatologiaPaginato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCertificatoPatologiaPaginato(@Context HttpServletRequest req,
			FiltriRicercaCertificatoPatologia filtri) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		  final String service = "Ricerca Certificato";
		  final String operazione = "READ";
		List<CertificatoAssistito> elencoEsenzioniOut = new ArrayList<CertificatoAssistito>();
		String cfMed = null;
		
		try {
			CheckRicercaEsenzioneOperatore.chkFiltriCertificatoPatologia(filtri);
			String codASLOperatore = null;
			UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
			List<Ruolo> ruoli = getRuoli(req,userInfo);
			codASLOperatore = userInfo.getCodASL();

			for (Ruolo ruolo : ruoli) {
				//medico cas ignoro asl
				if (ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE)) {
					codASLOperatore = null;
				}
				
				if(ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_CAS)
						|| ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.MEDICO_SPECIALISTA)) {
					cfMed = userInfo.getCodFisc();
				}
			}
			
			registraSuAudit(req.getRemoteAddr(), "1 OK", service, operazione, userInfo.getCodFisc(), null, null, null,
					filtri.getCodFiscaleBeneficiario(), null);

				if (!checkAzioneUtente(req,"OP-RicercaCertificati",ruoli)) {
				EsenredCMessaggi msg210 = getMessaggioEsenpat("MSG210");

				registraSuAudit(req.getRemoteAddr(), "0 KO " + msg210, service, operazione, userInfo.getCodFisc(), null,
						null, null, filtri.getCodFiscaleBeneficiario(), null);

				res.put("status", "ko");
				res.put("code", Status.BAD_REQUEST.getStatusCode());
				res.put("message", msg210.getTesto());
				res.put("data", null);

				return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
			}

			parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("ELEMENTI_RICERCA");
			int dimensionePagina = Integer.parseInt(elencoParametri.get(0).getValore());
				
			certificatiPatologiaIf = (CertificatiPatologiaIf) SpringApplicationContext.getBean("certificatoPatologia");
			
			Long contarecord = certificatiPatologiaIf.ContaCertificatiPatologia(filtri, codASLOperatore, cfMed);
			List<EsenzioneTDocumento> elencoEsenzioni = certificatiPatologiaIf.getCertificatiPatologia(filtri, codASLOperatore,dimensionePagina, cfMed);
			

			if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String error = messaggioIf.getMessaggio("MSG005").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("message", error);
				res.put("data", elencoEsenzioni);

				return corsedResponse.status(Status.OK).entity(res).build();
			}

			for (EsenzioneTDocumento esenzioneTDocumento : elencoEsenzioni) {
				 elencoEsenzioniOut.add(new CertificatoAssistito(esenzioneTDocumento));
			      }

			for (CertificatoAssistito ese : elencoEsenzioniOut) {
				ese.setNumeroTotaleElementi(contarecord.intValue());
			}
			
			res.put("status", "ok");
			res.put("code", Status.OK.getStatusCode());
			res.put("data", elencoEsenzioniOut);

			return corsedResponse.status(Status.OK).entity(res).build();
		} catch (CheckException chEx) {
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio(chEx.getCodice()), chEx.getDescrizione());

			res.put("status", "ko");
			res.put("code", Status.BAD_REQUEST.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String error = Util.composeMessage(messaggioIf.getMessaggio("MSG004"), ex.getLocalizedMessage());

			res.put("status", "ko");
			res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
			res.put("message", error);

			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	

	   /**
		 * Esenpat CDU 023 Firma Digitale + gateway firma csi
		 * 
		 * @param req
		 * @param pkRepository
		 * @return
		 * @throws Exception
		 */
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/firmaDocumentoDigitale")
		public Response firmaDocumentoDigitale(@Context HttpServletRequest req, FiltriFirmaDigitale filtri) throws Exception {
			String service = "insert";
			String operazione = "setFirmaDigitale";
			log.info(operazione, "BEGIN");
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new HashMap<String, Object>();
			
			try {
				UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

				if (filtri==null || !Checker.isValorizzato(filtri.getAlias()) 
						|| !Checker.isValorizzato(filtri.getPin()) || !Checker.isValorizzato(filtri.getPkDocumento())) {
					res = new HashMap<String, Object>();
					EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
					registraSuAudit(req.getRemoteAddr(),  Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());
					res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
					log.error(operazione, " Errore per Parametri obbligatori mancanti");
					return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				}
				List<Ruolo> ruoli = getRuoli(req, userInfo);
				
				// Passo 2: Invoca getDocumentoService
				Response resp = getDocumentoService(req, filtri.getPkDocumento());
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, "getDocumentoService", userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

				EsenzioneTRepositoryDocumentale documento = (EsenzioneTRepositoryDocumentale)((HashMap<String, Object>) resp.getEntity()).get("data");
				byte[] pdfFromGateway = null;
				log.info(operazione, " dopo ricerca documento -> ok");
				try {
					ProxySignStub stub = new ProxySignStub();
					certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
					
					byte[] pdfSigned = certificatiFacade.getDocumento(
							filtri.getPkDocumento(), userInfo, documento.getFile(),
							ruoli);

					pdfFromGateway = certificatiFacade.setFirmaDocumentoDigitale(pdfSigned, filtri, userInfo);


				} catch (EsenpatException e) {
							e.printStackTrace();
							res.put("status", "ko");
							res.put("code", e.getStatus());
							res.put("message", e.getCodMessaggio());
							res.put("msgErrore", e.getCodice());
							
							return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
						
				} catch (ProcessingException e) {e.printStackTrace();}
							
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, "Apposizione firma digitale PADES", userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

				if (pdfFromGateway == null) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String error = messaggioIf.getMessaggio("MSG200").getTesto();

					res.put("status", "ko");
					res.put("code", Status.NOT_FOUND.getStatusCode());
					res.put("message", error);

					return corsedResponse.status(Status.NOT_FOUND).entity(res).build();
				} else {
					
					esenzioniFacade = (EsenzioniFacade) SpringApplicationContext.getBean("esenzioniFacade");
					EsenzioneTRepositoryDocumentale out = esenzioniFacade.aggiornaCertificatoFirmato(
							filtri.getPkDocumento(), userInfo, Base64.getEncoder().encode(pdfFromGateway),
							ruoli);
					
					if (out != null) {
						out.setEsenzioneSDocumentos(null);
						out.setEsenzioneTDocumentos(null);
						// inserisci una riga in csi_audit
						registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, "InserisciDocumentoService",
								userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						String message = messaggioIf.getMessaggio("MSG232").getTesto();
						res.put("status", "OK");
						res.put("code", Status.OK.getStatusCode());
						res.put("data", out);
						res.put("message", message);
						return corsedResponse.status(Status.OK).entity(res).build();
					} else {
						throw new Exception();
					}
				} 
			} catch (Exception e) {
				e.printStackTrace();

				res.put("status", "ko");
				res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
				res.put("message", e.getLocalizedMessage());

				return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
			}
		}
		
		 /**
		 * richiesta otp per firma digitale
		 */
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/richiestaOtpFirma")
		public Response richiestaOtpFirma(@Context HttpServletRequest req, FiltriRichiestaOtp filtri) throws Exception {
			String service = "Richiesta Otp";
			String operazione = "richiestaOtpFirma";
			log.info(operazione, "BEGIN");
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new HashMap<String, Object>();
			
			try {
				UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());

				if (filtri==null || !Checker.isValorizzato(filtri.getAlias()) 
						|| !Checker.isValorizzato(filtri.getPin())) {
					res = new HashMap<String, Object>();
					EsenredCMessaggi messaggio145 = getMessaggioEsenpat(Constants.MSG145);
					registraSuAudit(req.getRemoteAddr(),  Status.BAD_REQUEST.getStatusCode() + " " + messaggio145.getTesto(), operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());
					res = generateResponseErrore(messaggio145.getTesto(), "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
					log.error(operazione, " Errore per Parametri obbligatori mancanti");
					return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				}
				List<Ruolo> ruoli = getRuoli(req, userInfo);
				
				
				log.info(operazione, " dopo ricerca documento -> ok");
				Response response = null;
				try {
					
					certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
					certificatiFacade.richiestaOtp(filtri, userInfo);

		
				} catch (ProcessingException e) {e.printStackTrace();
				} catch (EsenpatException e) {
						e.printStackTrace();
						res.put("status", "ko");
						res.put("code", e.getStatus());
						res.put("message", e.getCodMessaggio());
						res.put("msgErrore", e.getCodice());

						return corsedResponse.status(Status.BAD_REQUEST).entity(res).build();
				}			
				registraSuAudit(req.getRemoteAddr(), "1 OK", operazione, service, userInfo.getCodFisc(), null, null, null, null, userInfo.getCodFisc());
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String message = messaggioIf.getMessaggio("MSG235").getTesto();
				res.put("status", "ok");
				res.put("code", Status.OK.getStatusCode());
				res.put("data", "OK");
				res.put("message", message);
				return corsedResponse.status(Status.OK).entity(res).build();
			} catch (Exception e) {
				e.printStackTrace();

				res.put("status", "ko");
				res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
				res.put("message", e.getLocalizedMessage());

				return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
			}
		}
		

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getOtpTypes")
		public Response getOtpTypes(@Context HttpServletRequest req) {
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse(); 
		    Map<String, Object> res = new HashMap<String, Object>();
		   
		    try {     
		        certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
		        List<ModelCa> caOtpTypes = certificatiFacade.getCaOtpTypes();
		        res.put("status", "ok");
		        res.put("data", caOtpTypes);
		        res.put("code", Status.OK.getStatusCode());

		        return corsedResponse.status(Status.OK).entity(res).build();   
		        
		    } catch (Exception e) { e.printStackTrace();
		      return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
		    }
		}
}