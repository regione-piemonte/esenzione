/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.endpoints;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneBO;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneCertificataBO;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.AslOperatoreIf;
import it.csi.esenred.esenredweb.business.model.interfaces.AuditIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.NotificaIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;
import it.csi.esenred.esenredweb.business.services.CreateRedditoService;
import it.csi.esenred.esenredweb.business.services.EsenzioneRedditoService;
import it.csi.esenred.esenredweb.business.services.ReportService;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.DataInizioFine;
import it.csi.esenred.esenredweb.dto.EsenzioneAcceleratore;
import it.csi.esenred.esenredweb.dto.EsenzioneCittadino;
import it.csi.esenred.esenredweb.dto.EsenzioneCodiceAcceleratore;
import it.csi.esenred.esenredweb.dto.EsenzioneCreate;
import it.csi.esenred.esenredweb.dto.EsenzioneOperatore;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniAcceleratore;
import it.csi.esenred.esenredweb.dto.Info;
import it.csi.esenred.esenredweb.dto.ListaBeneficiari;
import it.csi.esenred.esenredweb.dto.ListaTitolari;
import it.csi.esenred.esenredweb.dto.MotivazioneRevoca;
import it.csi.esenred.esenredweb.dto.ParametroConfigurazione;
import it.csi.esenred.esenredweb.dto.ParametroDisclaimer;
import it.csi.esenred.esenredweb.dto.ParametroStatoEsenzione;
import it.csi.esenred.esenredweb.dto.TitoloDichiaranteAcceleratore;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.LogUtil;
import it.csi.esenred.esenredweb.util.RestUtils;
import it.csi.esenred.esenredweb.util.Util;

@Path("/rest/services")
// @Api("/services")
public class EsenRedRestEndpoint {
	
	private static final String MSG_034 = "MSG034";
	MessaggioIf messaggioIf;
	EsenzioneIf esenzioneIf;
	ParametroIf parametroIf;
	AslOperatoreIf aslOperatoreIf;
	AuditIf auditIf;
	NotificaIf notificaIf;
	ComuneIf comuneIf;
	TitoloDichiaranteIf titoloDichiaranteIf;
	EsenzioneCittadinoIf esenzioneCittadinoIf;
	private static String isTst;

	
	

	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENRED, null);
	
	
	
		@GET
		@Produces("application/pdf")
		@Path("/getReportCertificatoEsenzione/{idEsenzioneCittadino}/{Path}")

		public Response getReportCertificatoEsenzione(
		

				@PathParam("idEsenzioneCittadino") Integer idEsenzioneCittadino,
				@PathParam("Path") String Path) {

			String methodName = "GET ReportCertificatoEsenzione";
			log.info(methodName, "BEGIN");			
			log.info(methodName, " PathParam idEsenzioneCittadino: " + idEsenzioneCittadino);
			log.info(methodName, " PathParam path: " + Path);
			
			// leggo l'esenzione
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			
			log.debug(methodName, " Ricerco sul db l'esenzione");
			EsenredTEsenzioniReddito ese = esenzioneCittadinoIf.getEsenzioneById(idEsenzioneCittadino).get(0);

			if(ese!=null && ese.getIdCittadinoDichiarante()!=null) {
				log.debug(methodName, " esenzione trovata num: "+ ese.getIdEsenzione() +", id cittadino dichiarante: " + ese.getIdCittadinoDichiarante());
			}
			// chiamo AURA per individuare i dati del Dichiarante
			log.debug(methodName, " Chiamo AURA per individuare i dati del Dichiarante");
			Cittadino dichiarante = IntegrationClientImpl.getInstance()
					.getCittadino(ese.getIdCittadinoDichiarante().toString());
			Cittadino beneficiario = null;
			Cittadino titolare = null;
			//se dichiarante =beneficiario non vai in aura
			if (ese.getIdCittadinoDichiarante().equals(ese.getIdCittadinoBeneficiario()))
			{
				log.debug(methodName, " id beneficiario = id dichiarante --> NON chiamo AURA per individuare i dati del Beneficiario");
				beneficiario = dichiarante;
			}
			else {
			// chiamo AURA per individuare i dati del Beneficiario
				log.debug(methodName, " id beneficiario <> id dichiarante --> chiamo AURA per individuare i dati del Beneficiario");
			    beneficiario = IntegrationClientImpl.getInstance()
					.getCittadino(ese.getIdCittadinoBeneficiario().toString());
			    log.debug(methodName, " id beneficiario:"+  beneficiario);
			}
			// chiamo AURA per individuare i dati del Titolare
			//se titolare =beneficiario o dichiarante non vai in aura
			if (ese.getIdCittadinoTitolare()!=null){
			if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoBeneficiario()))
			{
				log.debug(methodName, " id titolare = beneficiario --> NON chiamo AURA per individuare i dati del titolare");
				titolare = beneficiario;
			}
			else if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoDichiarante())) {
				log.debug(methodName, " id titolare = dichiarante --> NON chiamo AURA per individuare i dati del titolare");
				titolare = dichiarante;
			}
			else {
				log.debug(methodName, " id titolare <> id dichiarante e id beneficiario --> chiamo AURA per individuare i dati del Titolare");
				titolare = IntegrationClientImpl.getInstance()
					.getCittadino(ese.getIdCittadinoTitolare().toString());
			}
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
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
			} 
			else
			{
				parameters.put("PATH", "Cittadino");
			}


			
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

			ReportService service = new ReportService();
			
			
			byte[] byteArray = service.createReport(parameters);
			if (byteArray.length < 1000) // NO DATA
				return corsedResponse.status(Status.NO_CONTENT).entity(null).build();

			String fileName = null;
			File reportOut = null;
			try {
				fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
				reportOut = File.createTempFile(fileName, ".pdf");
				log.info(methodName, " file: " + fileName);
				reportOut.deleteOnExit();
				FileUtils.writeByteArrayToFile(reportOut, byteArray);
			} catch (IOException e) {
				log.error(methodName, " IOException: ", e);
				reportOut.delete();
				e.printStackTrace();
				return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
			}
			
			log.info(methodName, "END");
			
			return corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName + ".pdf")
					.status(Status.OK).entity(reportOut).build();
		}
		
		private Response getReportCertificatoEsenzioneStorico(
				String Protocollo,String CodEsenzione,String data_inizio_validita,
				String Path, String TipoEsenzione, String cf_beneficiario) {
		
			String methodName = "GET ReportCertificatoEsenzioneStorico";
			log.info(methodName, "BEGIN"); 
			
			String datainival = data_inizio_validita;
			EsenredTEsenzioniReddito ese = null;
			if (Checker.isValorizzato(data_inizio_validita)) {
			data_inizio_validita = data_inizio_validita.substring(0, 2) + "/" + data_inizio_validita.substring(2, 4) + "/"+ data_inizio_validita.substring(4, 8);
			}
			
			log.info(methodName, "data " + datainival);
			log.info(methodName, "dataconslash " + data_inizio_validita);
			log.info(methodName, "protocollo " + Protocollo);
			log.info(methodName, "esenzione " + CodEsenzione);
			log.info(methodName, "Path " + Path);
			log.info(methodName, "Tipo Esenzione " + TipoEsenzione);
		
			try {			
				Cittadino c = new Cittadino();
				c.setCodFiscale(cf_beneficiario);;
				String idAura = IntegrationClientImpl.getInstance().findCittadino(c).get(0).getIdAura();
				if (TipoEsenzione.equalsIgnoreCase("AUTOCERTIFICATA"))
					ese = IntegrationClientImpl.getInstance().getEsenzioni(null, CodEsenzione, data_inizio_validita, idAura, "B",Protocollo,"StampaEsenzioneAutocertificata").get(0);
				else
					ese = IntegrationClientImpl.getInstance().getEsenzioni(null, CodEsenzione, data_inizio_validita, idAura, "B",Protocollo,"StampaEsenzioneCertificata").get(0);
//				closeAll();
			} catch (Exception e1) {
//				closeAll();
				e1.printStackTrace();
			} 
			
			// chiamo AURA per individuare i dati del Dichiarante
			Cittadino dichiarante = IntegrationClientImpl.getInstance()
					.getCittadino(ese.getIdCittadinoDichiarante().toString());
			Cittadino beneficiario = null;
			Cittadino titolare = null;
			//se dichiarante =beneficiario non vai in aura
			if (ese.getIdCittadinoDichiarante().equals(ese.getIdCittadinoBeneficiario()))
			{
				beneficiario = dichiarante;
			}
			else {
			// chiamo AURA per individuare i dati del Beneficiario
			    beneficiario = IntegrationClientImpl.getInstance()
					.getCittadino(ese.getIdCittadinoBeneficiario().toString());
			}
			
			// chiamo AURA per individuare i dati del Titolare
			//se titolare =beneficiario o dichiarante non vai in aura
			if (ese.getIdCittadinoTitolare()!=null && ese.getIdCittadinoTitolare()!=0){
				if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoBeneficiario()))	{
					titolare = beneficiario;
				}
				else if (ese.getIdCittadinoTitolare().equals(ese.getIdCittadinoDichiarante())) {
					titolare = dichiarante;
				}
				else {
					titolare = IntegrationClientImpl.getInstance()
						.getCittadino(ese.getIdCittadinoTitolare().toString());
				}
			}
		
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("PROTOCOLLO", ese.getNumProtocolloSogei()==null ? "" : ese.getNumProtocolloSogei());
			parameters.put("COD_ESENZIONE", ese.getCodEsenzione() == null ? "" : ese.getCodEsenzione());
			parameters.put("DATA_INIZIO",ese.getDataInizio() == null ? "" :Converter.getData(ese.getDataInizio(),"dd/MM/yyyy"));
			parameters.put("DATA_FINE", ese.getDataFine() == null ? "" :Converter.getData(ese.getDataFine(),"dd/MM/yyyy"));

			parameters.put("COD_TITOLO_DICHIARANTE", ese.getCodTitoloDichiarante() == null ? "0" : ese.getCodTitoloDichiarante());
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
			} 	else {
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

			String fileName = null;
			File reportOut = null;
			try {
				fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
				reportOut = File.createTempFile(fileName, ".pdf");
			reportOut.deleteOnExit();

				FileUtils.writeByteArrayToFile(reportOut, byteArray);

			} catch (IOException e) {
			reportOut.delete();
				e.printStackTrace();
//				closeAll();
				return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
			}
//			closeAll();
			return corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName + ".pdf")
					.status(Status.OK).entity(reportOut).build();
		}
		

		//fine esenred 1
		
		//esenred 2 inizio
		
		@GET
		@Path("/disclaimer")
		@Produces(MediaType.APPLICATION_JSON)

		public Response disclaimer(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv) throws Exception {

			String methodName = "GET disclaimer";
			log.info(methodName, "BEGIN"); 
			
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			//controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(shib));
			

			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "DISCLAIMER", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				
			}
			//IntegrationClientImpl.getInstance().getCittadino(datiCittadinoshib.get(0).getIdAura());
			
			String codParametro = "DESCLAIMER";
			String codParametroprivacy = "PRIVACY";
			try {
				ParametroDisclaimer listparametriOut = new ParametroDisclaimer();
				parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				listparametriOut.setTesto_informativa(parametroIf.getParametri(codParametro).get(0).getValore());
				listparametriOut.setVersione_informativa("0");
				listparametriOut.setTesto_privacy(parametroIf.getParametri(codParametroprivacy).get(0).getValore());
				listparametriOut.setVersione_privacy("0");

				
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), null, "DISCLAIMER", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);

				if (listparametriOut!=null) {			
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "DISCLAIMER", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(listparametriOut).build();
				}
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "DISCLAIMER", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(null).build();
			} catch (Exception e) {
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getReasonPhrase(), "DISCLAIMER", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
			
			 
		}
		
		@GET
		@Path("/configurazione")
		@Produces(MediaType.APPLICATION_JSON)

		public Response configurazione(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv) throws Exception {

			String methodName = "GET configurazione";
			log.info(methodName, "BEGIN"); 
			
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);

			
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			log.info(methodName, "1) controllo utente Shib-Identita-CodiceFiscale su AURA ");
			//controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(shib));

			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "CONFIGURAZIONE", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				
			}
			
			String codParametro = "HELPTITOLARE";
			try {
				List<ParametroConfigurazione> listparametriOut = new ArrayList<ParametroConfigurazione>();
				parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri(codParametro);

				log.debug(methodName, "Leggo sul db il valore del parametro = HELPTITOLARE ");

				for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
					EsenredCParametri pDB = (EsenredCParametri) iterator.next();
					listparametriOut.add(new ParametroConfigurazione(pDB.getValore()));
				}
				
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), null, "CONFIGURAZIONE", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);

				if (!listparametriOut.isEmpty()) {		
					for (EsenredCParametri esenredCParametri : elencoParametri) {
						log.debug(methodName, "Valore letto: " + esenredCParametri.getValore());
					}
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "CONFIGURAZIONE", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(listparametriOut.get(0)).build();
				}
				log.debug(methodName, "Nessun parametro trovato! ");
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "CONFIGURAZIONE", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(null).build();
			} catch (Exception e) {
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getReasonPhrase(), "CONFIGURAZIONE", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END"); 
			}
		}
		
		@GET
		@Path("/rapporti-familiari")
		@Produces(MediaType.APPLICATION_JSON)

		public Response getRapportiFamiliare(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv) throws Exception {

			String methodName = "GET RapportiFamiliare";
			log.info(methodName, "BEGIN"); 
			
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new LinkedHashMap<String, Object>();

			//controllo utente shibolet
					List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
							.findCittadino(new Cittadino(shib));

					if (datiCittadinoshib.isEmpty()) {
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
								messaggioIf.getMessaggio("MSG024").getTesto());
						res.put("code", "AURA_NON_TROVATO");
						res.put("title", messaggio);	
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
						res.put("detail", detail);
						auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
						CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Elenco Rapporti Familiari", "READ", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
					}
			
			List<TitoloDichiaranteAcceleratore> listTitoliOut = new ArrayList<TitoloDichiaranteAcceleratore>();

			try {
				
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), null, "Elenco Rapporti Familiari", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				
				titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext.getBean("titoloDichiarante");
				List<EsenredCTitoloDichiarante> elencoTitoli = titoloDichiaranteIf.getTitoliFamiliare();

				for (Iterator<EsenredCTitoloDichiarante> iterator = elencoTitoli.iterator(); iterator.hasNext();) {
					EsenredCTitoloDichiarante tDB = (EsenredCTitoloDichiarante) iterator.next();
					listTitoliOut.add(new TitoloDichiaranteAcceleratore(tDB));
				}


				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "Elenco Rapporti Familiari", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(listTitoliOut).build();

			} catch (Exception e) {
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getReasonPhrase(), "Elenco Rapporti Familiari", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END"); 
			}
		}
		
		@GET
		@Path("/stati-esenzione")
		@Produces(MediaType.APPLICATION_JSON)

		public Response getStatiEsenzione(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv) throws Exception {

			String methodName = "GET StatiEsenzione";
			log.info(methodName, "BEGIN"); 
			
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			
		
					List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
							.findCittadino(new Cittadino(shib));

					if (datiCittadinoshib.isEmpty()) {
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
								messaggioIf.getMessaggio("MSG024").getTesto());
						res.put("code", "AURA_NON_TROVATO");
						res.put("title", messaggio);	
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
						res.put("detail", detail);
						auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
						CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Elenco Stati Esenzione", "READ", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
					}
			
			String codParametro = "STATO_ESENZIONE_";
			try {
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), null, "Elenco Stati Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				List<ParametroStatoEsenzione> listparametriOut = new ArrayList<ParametroStatoEsenzione>();
				parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri(codParametro);

				for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
					EsenredCParametri pDB = (EsenredCParametri) iterator.next();
					listparametriOut.add(new ParametroStatoEsenzione(pDB));
				}

				if (!listparametriOut.isEmpty()) {
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "Elenco Stati Esenzione", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(listparametriOut).build();
				}
				
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "Elenco Stati Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(null).build();
			} catch (Exception e) {
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getReasonPhrase(), "Elenco Stati Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END"); 
			}
		}
		
		@GET
		@Path("/codici-esenzione")
		@Produces(MediaType.APPLICATION_JSON)

		public Response getCodiciEsenzione(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv) throws Exception {
			
			String methodName = "GET CodiciEsenzione";
			log.info(methodName, "BEGIN");
			
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	       //controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(shib));

			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Elenco Codici Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
			}
					
			try {
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), null, "Elenco Codici Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				
				List<EsenzioneCodiceAcceleratore> listEsenzioniOut = new ArrayList<EsenzioneCodiceAcceleratore>();
				esenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
				List<EsenredDTipiEsenzioniReddito> elencoEsenzioni = esenzioneIf.getEsenzioni(null);
				for (Iterator<EsenredDTipiEsenzioniReddito> iterator = elencoEsenzioni.iterator(); iterator.hasNext();) {
					EsenredDTipiEsenzioniReddito eDB = (EsenredDTipiEsenzioniReddito) iterator.next();
					listEsenzioniOut.add(new EsenzioneCodiceAcceleratore(eDB));
				}
				if (!listEsenzioniOut.isEmpty()) {
					
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "Elenco Codici Esenzione", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(listEsenzioniOut).build();
				}
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getReasonPhrase(), "Elenco Codici Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(listEsenzioniOut).build();
			} catch (Exception e) {						
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getReasonPhrase(), "Elenco Codici Esenzione", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/cittadini/{cf}/info")
		//produce la stampa o a video una esenzione
		public Response getInfo(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale) throws Exception {

			String methodName = "GET Info";
			log.info(methodName, "BEGIN");
			
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);
			log.info(methodName, " HeaderParam cf: " + codiceFiscale);

			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			//controllo utente shibolet
			log.info(methodName, "1) controllo utente Shib-Identita-CodiceFiscale su AURA ");
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(shib));

			if (datiCittadinoshib.isEmpty()) {
				log.info(methodName, "AURA - Cittadino (Shib-Identita-CodiceFiscale) non trovato ");
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				
				log.tracciaRes(methodName, res);
				
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
			}
			
			messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String idAura = null;
			auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
			CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), codiceFiscale, "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
			auditIf.insertAudit(audit);
			if(isTst==null) {
				//List<ParametroStatoEsenzione> listparametriOut = new ArrayList<ParametroStatoEsenzione>();
				ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
				isTst= elencoParametri.get(0).getValore();	
				
				log.info(methodName, "isTst vale:" + isTst);
			}
					
			try {
			CreateRedditoService service = new CreateRedditoService();
				service.checkCF(codiceFiscale, isTst);
			}
			catch (EsenzioneInvalidaException e) {
				if(e.getCodice().equals("421")) {
					//res.put("status", "421");
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");		
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(421), e.getDescrizione());
					res.put("detail", detail);
					CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), "421 " +res.toString(), "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit1);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				//res.put("status", "400");
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());			
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit1);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			} 

			//se codice fiscale corretto prendi id aura
			List<Cittadino> datiCittadino = null;
		
			log.info(methodName, "2) controllo HeadParam cf su AURA ");
			datiCittadino = IntegrationClientImpl.getInstance()
				.findCittadino(new Cittadino(codiceFiscale));
			
		
			if (datiCittadino.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				//res.put("status", Status.NOT_FOUND.getStatusCode());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", "Cittadino non trovato dal servizio Aura");		
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);			
				audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getStatusCode() + " " +Status.NOT_FOUND.getReasonPhrase() + " - "+res.toString(), "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();

			}
			idAura= datiCittadino.get(0).getIdAura();
			boolean blocco = false;
			String data_sblocco = null;
			//verifica blocco
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			
			if(esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)){

				log.debug(methodName, "Cittadino Blocco TRUE");
	            blocco = true;
	            //recupero la data del blocco
	            Date d = esenzioneCittadinoIf.getDataSBloccoCittadino(Long.parseLong(idAura));
	            if (d!=null) {
	            d = Converter.aggiungiAnnoAData(d, 1);
	            data_sblocco = Converter.getData(d, "yyyy-MM-dd");
			    }
	  
			}

			try {

				String scadenza = null;	
				parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				String ggmm = parametroIf.getParametri("DATA_SCADENZA_PARAMETRIZZATA").get(0).getValore();
				DataInizioFine output = Util.getDataInizioFine(ggmm);
				scadenza = Converter.getDataISO(Converter.getData(output.getFineValidita()));
				Info r = new Info(scadenza,blocco,data_sblocco);
				log.info(methodName, "Get Info cittadino OK, scadenza:  " + scadenza + ", blocco: " + blocco + ", data_sblocco: " + data_sblocco);
				
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() +" "+  Status.OK.getReasonPhrase(), "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(r).build();	
			}
			

			catch (Exception e) {
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Dettaglio cittadino", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit1);
				log.tracciaRes(methodName,res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
			
		}


		
		@GET
		@Path("/cittadini/{cf}/beneficiari")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getBeneficiari(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale)
				throws Exception {
			
			String methodName = "GET Beneficiari";
			log.info(methodName, "BEGIN");
			
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);
			log.info(methodName, " HeaderParam cf: " + codiceFiscale);

			Map<String, Object> res = new LinkedHashMap<String, Object>();
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		

			log.info(methodName, "1) controllo utente Shib-Identita-CodiceFiscale su AURA ");	
			//controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(shib));

			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
			}
			
			List<ListaBeneficiari> elencoEsenzioniOut = new ArrayList<ListaBeneficiari>();
			String idAura = null;
			// controllo se codice fiscale corretto
			try {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), shib, "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				
				if(isTst==null) {
					//List<ParametroStatoEsenzione> listparametriOut = new ArrayList<ParametroStatoEsenzione>();
					ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
					List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
					isTst= elencoParametri.get(0).getValore();	
					
					log.info(methodName, "isTst vale:" + isTst);
				}
				
			
				CreateRedditoService service = new CreateRedditoService();
					service.checkCF(codiceFiscale, isTst);
					
				


				List<Cittadino> datiCittadino = null;
				log.info(methodName, "2) controllo HeadParam cf su AURA ");
				datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
					

				if (datiCittadino.isEmpty()) {
					
					String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
							messaggioIf.getMessaggio("MSG024").getTesto());
					//res.put("status", Status.NOT_FOUND.getStatusCode());
					res.put("code", "AURA_NON_TROVATO");
					res.put("title", messaggio);
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
					res.put("detail", detail);
					
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				idAura = datiCittadino.get(0).getIdAura();
				
				// verifica blocco				
				esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
				if (esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)) {
					log.info(methodName,"L'utente con cf " +codiceFiscale+ " e idAura " + idAura + " e' BLOCCATO");
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String codMSG = "MSG028";
					String error = messaggioIf.getMessaggio(codMSG).getTesto();
					//res.put("status", Status.UNAUTHORIZED.getStatusCode());
					res.put("code", "NON_AUTORIZZATO");
					res.put("title", error);
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()),  Status.UNAUTHORIZED.getReasonPhrase() );
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.UNAUTHORIZED.getStatusCode() +" "+  Status.UNAUTHORIZED.getReasonPhrase() +" "+res.toString(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				String listaprotocolli = "";
				log.debug(methodName," Controllo se ci sono delegati per il cf: " + codiceFiscale);
				//prendo i delegati nel caso ci siano passo trasformo i CF in idAura
				List<String> elencodelegati = IntegrationClientImpl.getInstance().chiamaGetDeleganti(codiceFiscale);
				List<Cittadino> datiCittadinodelegato = null;
				Set<Long> idAuradelegato = new HashSet<Long>();
				
				if (elencodelegati!=null) {
					for (String cfdelegato : elencodelegati) {
						datiCittadinodelegato = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(cfdelegato));
						if (!datiCittadinodelegato.isEmpty()) {
							log.debug(methodName, " idAura [ "+ datiCittadinodelegato.get(0).getIdAura() +" ] per cf delegante ["+cfdelegato+"]");
							idAuradelegato.add(Converter.getLong(datiCittadinodelegato.get(0).getIdAura()));
						}
					}
				}


				List<Long> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioniBeneficiari(new Long(idAura),idAuradelegato);
				log.debug(methodName, "Ricerco esenzioni per");
				log.debug(methodName, "		  idAura :"+idAura);
				log.debug(methodName, "		  idAuraDelegato :"+idAuradelegato);

					
				elencoEsenzioni.addAll(IntegrationClientImpl.getInstance().getListaBeneficiari(idAura));


				if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
					log.info(methodName, "Nessuna esenzione trovata.");
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String error = messaggioIf.getMessaggio("MSG005").getTesto();
					//res.put("status", Status.NOT_FOUND);
					res.put("code", "ESENZIONE_NON_TROVATA");
					res.put("title", error);	
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.OK.getStatusCode()),  Status.OK.getReasonPhrase() );
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() +" "+  Status.OK.getReasonPhrase() +" "+res.toString(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(elencoEsenzioni).build();
				}
				
				elencoEsenzioniOut = getAuraInfoBeneficiari(elencoEsenzioni);
		
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(elencoEsenzioniOut).build();

			}
			catch (EsenzioneInvalidaException e) {
				if(e.getCodice().equals("421")) {
					//res.put("status", "421");
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");		
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(421),e.getDescrizione() );
					res.put("detail", detail);
//					EsenRedRestEndpoint.closeAll();
					CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), "421 " +res.toString(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit1);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				//res.put("status", "400");
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());			
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit1);	
				log.tracciaRes(methodName, res);
//				EsenRedRestEndpoint.closeAll();
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			} 

			catch (Exception e) {
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Elenco Beneficiari ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit1);
				log.tracciaRes(methodName, res);
//				EsenRedRestEndpoint.closeAll();
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
			
		}
		
		@GET
		@Path("/cittadini/{cf}/titolari")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getTitolari(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale)
				throws Exception {

			String methodName = "GET Titolari";
			log.info(methodName, "BEGIN");
			
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);
			log.info(methodName, " HeaderParam cf: " + codiceFiscale);
			
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			
			//controllo utente shibolet
					List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
							.findCittadino(new Cittadino(shib));

					if (datiCittadinoshib.isEmpty()) {
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
								messaggioIf.getMessaggio("MSG024").getTesto());
						res.put("code", "AURA_NON_TROVATO");
						res.put("title", messaggio);	
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
						res.put("detail", detail);
						auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
						CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Elenco Titolari ", "READ", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
					}
			
			List<ListaTitolari> elencoEsenzioniOut = new ArrayList<ListaTitolari>();
			String idAura = null;
			// controllo se codice fiscale corretto
			try {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), shib, "Elenco Titolari ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				
				if(isTst==null) {
					ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
					List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
					isTst= elencoParametri.get(0).getValore();		
				}
				
			
					CreateRedditoService service = new CreateRedditoService();
						service.checkCF(codiceFiscale, isTst);
					
		
					List<Cittadino> datiCittadino = null;
					
						datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
					

					if (datiCittadino.isEmpty()) {
						
						String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
								messaggioIf.getMessaggio("MSG024").getTesto());
						//res.put("status", Status.NOT_FOUND.getStatusCode());
						res.put("code", "AURA_NON_TROVATO");
						res.put("title", messaggio);
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
						res.put("detail", detail);
						
						audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString(), "Elenco Titolari ", "READ", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
					}
					idAura = datiCittadino.get(0).getIdAura();
					
					// verifica blocco				
					esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
					if (esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)) {
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						String codMSG = "MSG028";
						String error = messaggioIf.getMessaggio(codMSG).getTesto();
						//res.put("status", Status.UNAUTHORIZED.getStatusCode());
						res.put("code", "NON_AUTORIZZATO");
						res.put("title", error);
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()),  Status.UNAUTHORIZED.getReasonPhrase() );
						res.put("detail", detail);
						audit = new CsiLogAudit(req.getRemoteAddr(), Status.UNAUTHORIZED.getStatusCode() +" "+  Status.UNAUTHORIZED.getReasonPhrase() +" "+res.toString(), "Elenco Titolari ", "READ", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
					}
					// prendo l'elenco dei Titolari di cui cf e dichiarante compreso se stesso
					String listaprotocolli = "";
					// prendo l'elenco dei beneficiari di cui cf e dichiarante compreso se stesso
					//preleva solo esenzioni di esenred storiche
				  Set<Long> idAuradelegato = new HashSet<Long>();
				  //verifica presenza delegati
				//prendo i delegati nel caso ci siano passo trasformo i CF in idAura
					List<String> elencodelegati = IntegrationClientImpl.getInstance().chiamaGetDeleganti(codiceFiscale);
					List<Cittadino> datiCittadinodelegato = null;
					
					if (elencodelegati!=null) {
					for (String cfdelegato : elencodelegati) {
						datiCittadinodelegato = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(cfdelegato));
						if (!datiCittadinodelegato.isEmpty()) {
							idAuradelegato.add(Converter.getLong(datiCittadinodelegato.get(0).getIdAura()));
					}
						}
					}
					List<Long> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioniTitolari(new Long(idAura),idAuradelegato);
					

					elencoEsenzioni.addAll(IntegrationClientImpl.getInstance().getListaTitolari(idAura));
					
					

					if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
						messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
						String error = messaggioIf.getMessaggio("MSG005").getTesto();
						res.put("code", "ESENZIONE_NON_TROVATA");
						res.put("title", error);	
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.OK.getStatusCode()),  Status.OK.getReasonPhrase() );
						res.put("detail", detail);
						audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() +" "+  Status.OK.getReasonPhrase() +" "+res.toString(), "Elenco Titolari ", "READ", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(elencoEsenzioni).build();
						
					}
					elencoEsenzioniOut = getAuraInfoTitolari(elencoEsenzioni);
			
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(elencoEsenzioniOut).build();

			}
			catch (EsenzioneInvalidaException e) {
				if(e.getCodice().equals("421")) {
			
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");		
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(421), e.getDescrizione());
					res.put("detail", detail);
					CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), "421 " +res.toString(), "Elenco Titolari ", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit1);	
//					EsenRedRestEndpoint.closeAll();
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				//res.put("status", "400");
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());			
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Elenco Titolari " , "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit1);
//				EsenRedRestEndpoint.closeAll();
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			} 

			catch (Exception e) {
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Elenco Titolari " , "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit1);
//				EsenRedRestEndpoint.closeAll();
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
			
		}
		
		@GET
		@Path("/cittadini/{cf}/esenzioni")
		//@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getEsenzioniCittadini(@Context HttpServletRequest req, @PathParam("cf") String codiceFiscale,
				@QueryParam("filter") String filter, @HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib, @HeaderParam("X-Codice-Servizio") String xcodserv)
				throws Exception {
	
			String methodName = "GET EsenzioniCittadini";
			log.info(methodName, "BEGIN");
			
			Map<String, Object> res = null;
			Response.ResponseBuilder corsedResponse = null;
			
			if(filter==null || filter.equalsIgnoreCase("null")) filter = "{}";
			
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam cf: " + codiceFiscale);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);
			log.info(methodName, " HeaderParam filter: " + filter);
			try {
				res = new LinkedHashMap<String, Object>();
				corsedResponse = RestUtils.getCorsedResponse();
				if (filter.isEmpty() || filter.equalsIgnoreCase("null")) {
					filter = null;
				}
				// controllo utente shibolet
				log.info(methodName, " Controllo utente Shib-Identita-CodiceFiscale su AURA ");
				List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(shib));
	
				if (datiCittadinoshib.isEmpty()) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
							messaggioIf.getMessaggio("MSG024").getTesto());
					res.put("code", "AURA_NON_TROVATO");
					res.put("title", messaggio);
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
					res.put("detail", detail);
					auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getReasonPhrase(),
							"Elenco Esenzioni", "READ", shib, xrequest, null, xcodserv);
					log.tracciaRes(methodName, res);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}							
	
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), shib, "Elenco Esenzioni", "READ", shib, xrequest,
						null, xcodserv);
				auditIf.insertAudit(audit);
	
								
				// Check integrita filtro
				if (filter != null) {
					log.info(methodName, " controllo formale filter");
					if (!(filter.startsWith("{") && filter.endsWith("}"))) {
						// res.put("status", Status.BAD_REQUEST.getStatusCode());
						res.put("code", "ERRORE GENERICO");
						res.put("title", "Generico errore relativo al client (richiesta errata)");
						HashMap<String, String> detail = new HashMap<String, String>();
						detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),
								Status.BAD_REQUEST.getReasonPhrase());
						res.put("detail", detail);
						audit = new CsiLogAudit(
								req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
										+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(),
								"Elenco Esenzioni", "READ", shib, xrequest, null, xcodserv);
						auditIf.insertAudit(audit);
						log.error(methodName, " Il parametro filter non e' formalmente corretto, verificare che i valori siano compresi in { .. }");
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res)
								.build();
					}
				}
	
				List<EsenzioneAcceleratore> elencoEsenzioniOut = new ArrayList<EsenzioneAcceleratore>();
	
				if (isTst == null) {
					ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
					List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
					isTst = elencoParametri.get(0).getValore();
					log.debug(methodName, " valore parametro isTst:  "+ isTst);
				}
	
				try {
					CreateRedditoService service = new CreateRedditoService();
					service.checkCF(codiceFiscale, isTst);
				} catch (EsenzioneInvalidaException e) {
					if (e.getCodice().equals("421")) {
						
						res.put("code", "ERRORE GENERICO");
						res.put("title", "Codice fiscale non corretto");
						HashMap<String, String> detail = new HashMap<String, String>();
						detail.put(Integer.toString(421), e.getDescrizione());
						res.put("detail", detail);
						CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), "421 " + res.toString(),
								"Elenco Esenzioni", "READ", shib, xrequest, null, xcodserv);
						auditIf.insertAudit(audit1);
						log.tracciaRes(methodName, res);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res)
								.build();
					}
					
					res.put("code", "ERRORE GENERICO");
					res.put("title", e.getDescrizione());
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
					res.put("detail", detail);
					CsiLogAudit audit1 = new CsiLogAudit(
							req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
									+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(),
							"Elenco Esenzioni", "READ", shib, xrequest, null, xcodserv);
					log.tracciaRes(methodName, res);
					auditIf.insertAudit(audit1);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
	
				FiltriRicercaEsenzioniAcceleratore filtri = null;
				if (filter != null) {
					String filterNew = filter.replace("'", "\"");
	
					// Parsing in FiltriRicercaEsenzioniAcceleratore
					ObjectMapper mapper2 = new ObjectMapper();
					filtri = mapper2.readValue(filterNew, FiltriRicercaEsenzioniAcceleratore.class);
				}
				
				List<Cittadino> datiCittadino = null;
				log.info(methodName, "2) controllo HeadParam cf su AURA ");
				datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));

				if (datiCittadino.isEmpty()) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
							messaggioIf.getMessaggio("MSG024").getTesto());
					//res.put("status", Status.NOT_FOUND.getStatusCode());
					res.put("code", "AURA GENERICO");
					res.put("title", messaggio);
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " "+ Status.NOT_FOUND.getReasonPhrase() + " " + res.toString(),"Elenco Esenzioni ", "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}			
				
				Long idAura = new Long(datiCittadino.get(0).getIdAura());
				
				//verifica blocco
				esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
				if(esenzioneCittadinoIf.getBloccoCittadino(idAura, null)){
					log.error(methodName," Utente BLOCCATO");
					
					//res.put("status", Status.UNAUTHORIZED.getStatusCode());
					res.put("code", "NON_AUTORIZZATO");
					res.put("title", "Utente non autorizzato a compiere l'operazione");
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()),  Status.UNAUTHORIZED.getReasonPhrase() );
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.UNAUTHORIZED.getStatusCode() +" "+  Status.UNAUTHORIZED.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
	
				esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
				// trasformo il cf in idaura caso eq e neq
				Long datiCittadinocreatoda = null;
				Long datiCittadinocreatoper = null;
				Long datiCittadinotitolare = null;
				String cfCreatoper = null;
				String cfCreatoda = null;
				String cftitolare = null;
				Boolean valore = null;
				if (filtri != null) {
					if (filtri.getCreato_per() != null) {
						log.debug(methodName," Controllo blocco filtro <Creato_per>");
						if (filtri.getCreato_per().getEq() != null || filtri.getCreato_per().getNe() != null) {
							if (filtri.getCreato_per().getEq() != null) {
								cfCreatoper = filtri.getCreato_per().getEq();
								valore = true;
							} else if (filtri.getCreato_per().getNe() != null) {
								cfCreatoper = filtri.getCreato_per().getNe();
								valore = false;
							}
							log.debug(methodName," cfCreatoper: " + cfCreatoper);
							log.debug(methodName," valore: " + valore);
							
							if (codiceFiscale.equalsIgnoreCase(cfCreatoper)) {
								log.debug(methodName," codiceFiscale = cfCreatoper --> setto filtri.creato_per.setEq = idAura (" +idAura + " )");
								filtri.getCreato_per().setEq(idAura.toString());
							} else {
								datiCittadinocreatoper = new Long(IntegrationClientImpl.getInstance()
										.findCittadino(new Cittadino(cfCreatoper)).get(0).getIdAura());
								if (valore)
									filtri.getCreato_per().setEq(datiCittadinocreatoper.toString());
								else
									filtri.getCreato_per().setNe(datiCittadinocreatoper.toString());
							}
						}
						


					}
	
					if (filtri.getCreato_da() != null) {
						log.debug(methodName," Controllo blocco filtro <Creato_da>");
						if (filtri.getCreato_da().getEq() != null || filtri.getCreato_da().getNe() != null) {
							if (filtri.getCreato_da().getEq() != null) {
								cfCreatoda = filtri.getCreato_da().getEq();
								valore = true;
							} else if (filtri.getCreato_da().getNe() != null) {
								cfCreatoda = filtri.getCreato_da().getNe();
								valore = false;
							}
							
							log.debug(methodName," cfCreatoda: " + cfCreatoda);
							log.debug(methodName," valore: " + valore);
							if (codiceFiscale.equalsIgnoreCase(cfCreatoda)) {
								log.debug(methodName," codiceFiscale = cfCreatoda --> setto filtri.creato_da.setEq = idAura (" +idAura + " )");
								filtri.getCreato_da().setEq(idAura.toString());
							} else {
								datiCittadinocreatoda = new Long(IntegrationClientImpl.getInstance()
										.findCittadino(new Cittadino(cfCreatoda)).get(0).getIdAura());
								if (valore)
									filtri.getCreato_da().setEq(datiCittadinocreatoda.toString());
								else
									filtri.getCreato_da().setNe(datiCittadinocreatoda.toString());
							}
						}
					}
	
					if (filtri.getTitolare() != null) {
						log.debug(methodName," Controllo blocco filtro <titolare>");
						if (filtri.getTitolare().getEq() != null || filtri.getTitolare().getNe() != null) {
							if (filtri.getTitolare().getEq() != null) {
								cftitolare = filtri.getTitolare().getEq();
								valore = true;
							} else if (filtri.getTitolare().getNe() != null) {
								cftitolare = filtri.getTitolare().getNe();
								valore = false;
							}
							log.debug(methodName," cftitolare: " + cftitolare);
							log.debug(methodName," valore: " + valore);
							if (codiceFiscale.equalsIgnoreCase(cftitolare)) {
								filtri.getTitolare().setEq(idAura.toString());
							} else {
								datiCittadinotitolare = new Long(IntegrationClientImpl.getInstance()
										.findCittadino(new Cittadino(cftitolare)).get(0).getIdAura());
								if (valore)
									filtri.getTitolare().setEq(datiCittadinotitolare.toString());
								else
									filtri.getTitolare().setNe(datiCittadinotitolare.toString());
							}
						}
					}
				}
				// caso in e non in
	
				List<String> datiCittadinocreatodal = new ArrayList<String>();
				List<String> datiCittadinocreatoperl = new ArrayList<String>();
				List<String> datiCittadinotitolarel = new ArrayList<String>();
				// String cfCreatoper = null;
				// String cfCreatoda = null;
				Boolean valorel = null;
				Set<String> setfiltri = null;
				if (filtri != null) {
					if (filtri.getCreato_per() != null) {
	
						if (filtri.getCreato_per().getIn() != null || filtri.getCreato_per().getNin() != null) {
							if (filtri.getCreato_per().getIn() != null) {
								valorel = true;
								// verifico che non siano uguali i valori inseriti
								setfiltri = new HashSet<String>(Arrays.asList(filtri.getCreato_per().getIn()));
								for (String valfiltro : setfiltri) {
									if (valfiltro.equalsIgnoreCase(codiceFiscale)) {
										datiCittadinocreatoperl.add(idAura.toString());
									} else {
										datiCittadinocreatoper = new Long(IntegrationClientImpl.getInstance()
												.findCittadino(new Cittadino(valfiltro)).get(0).getIdAura());
										datiCittadinocreatoperl.add(datiCittadinocreatoper.toString());
									}
								}
							} else if (filtri.getCreato_per().getNin() != null) {
								valorel = false;
								setfiltri = new HashSet<String>(Arrays.asList(filtri.getCreato_per().getNin()));
								for (String valfiltro : setfiltri) {
									if (valfiltro.equalsIgnoreCase(codiceFiscale)) {
										datiCittadinocreatoperl.add(idAura.toString());
									} else {
										datiCittadinocreatoper = new Long(IntegrationClientImpl.getInstance()
												.findCittadino(new Cittadino(valfiltro)).get(0).getIdAura());
										datiCittadinocreatoperl.add(datiCittadinocreatoper.toString());
									}
								}
							}
							// aggiorno il filtro
							String[] listaidaura = new String[datiCittadinocreatoperl.size()];
							for (int i = 0; i < datiCittadinocreatoperl.size(); i++) {
								listaidaura[i] = datiCittadinocreatoperl.get(i);
							}
	
							log.debug(methodName," set creato per con condizione IN per idAura : " +listaidaura);
							filtri.getCreato_per().setIn(listaidaura);
	
						}
					}
	
					if (filtri.getCreato_da() != null) {
	
						if (filtri.getCreato_da().getIn() != null || filtri.getCreato_da().getNin() != null) {
							if (filtri.getCreato_da().getIn() != null) {
								// verifico che non siano uguali i valori inseriti
								setfiltri = new HashSet<String>(Arrays.asList(filtri.getCreato_da().getIn()));
								for (String valfiltro : setfiltri) {
									if (valfiltro.equalsIgnoreCase(codiceFiscale)) {
										datiCittadinocreatodal.add(idAura.toString());
									} else {
										datiCittadinocreatoda = new Long(IntegrationClientImpl.getInstance()
												.findCittadino(new Cittadino(valfiltro)).get(0).getIdAura());
										datiCittadinocreatodal.add(datiCittadinocreatoda.toString());
									}
								}
							} else if (filtri.getCreato_da().getNin() != null) {
								setfiltri = new HashSet<String>(Arrays.asList(filtri.getCreato_da().getNin()));
								for (String valfiltro : setfiltri) {
									if (valfiltro.equalsIgnoreCase(codiceFiscale)) {
										datiCittadinocreatodal.add(idAura.toString());
									} else {
										datiCittadinocreatoda = new Long(IntegrationClientImpl.getInstance()
												.findCittadino(new Cittadino(valfiltro)).get(0).getIdAura());
										datiCittadinocreatodal.add(datiCittadinocreatoda.toString());
									}
								}
							}
							// aggiorno il filtro
							String[] listaidaura = new String[datiCittadinocreatodal.size()];
							for (int i = 0; i < datiCittadinocreatodal.size(); i++) {
								listaidaura[i] = datiCittadinocreatodal.get(i);
							}
	
							log.debug(methodName," set creato da con condizione IN per idAura : " +listaidaura);
							filtri.getCreato_da().setIn(listaidaura);
						}
					}
	
					if (filtri.getTitolare() != null) {
	
						if (filtri.getTitolare().getIn() != null || filtri.getTitolare().getNin() != null) {
							if (filtri.getTitolare().getIn() != null) {
								// verifico che non siano uguali i valori inseriti
								setfiltri = new HashSet<String>(Arrays.asList(filtri.getTitolare().getIn()));
								for (String valfiltro : setfiltri) {
									if (valfiltro.equalsIgnoreCase(codiceFiscale)) {
										datiCittadinotitolarel.add(idAura.toString());
									} else {
										datiCittadinotitolare = new Long(IntegrationClientImpl.getInstance()
												.findCittadino(new Cittadino(valfiltro)).get(0).getIdAura());
										datiCittadinotitolarel.add(datiCittadinotitolare.toString());
									}
								}
							} else if (filtri.getTitolare().getNin() != null) {
								setfiltri = new HashSet<String>(Arrays.asList(filtri.getTitolare().getNin()));
								for (String valfiltro : setfiltri) {
									if (valfiltro.equalsIgnoreCase(codiceFiscale)) {
										datiCittadinotitolarel.add(idAura.toString());
									} else {
										datiCittadinotitolare = new Long(IntegrationClientImpl.getInstance()
												.findCittadino(new Cittadino(valfiltro)).get(0).getIdAura());
										datiCittadinotitolarel.add(datiCittadinotitolare.toString());
									}
								}
							}
							// aggiorno il filtro
							String[] listaidaura = new String[datiCittadinotitolarel.size()];
							for (int i = 0; i < datiCittadinotitolarel.size(); i++) {
								listaidaura[i] = datiCittadinotitolarel.get(i);
							}
							log.debug(methodName," set Titolare con condizione IN per idAura : " +listaidaura);
							filtri.getTitolare().setIn(listaidaura);
						}
					}
				}


				log.debug(methodName," Controllo se ci sono deleganti per il cf: " + codiceFiscale);
				List<String> elencodelegati = IntegrationClientImpl.getInstance().chiamaGetDeleganti(codiceFiscale);
				List<Cittadino> datiCittadinodelegato = null;
				Set<Long> idAuradelegato = new HashSet<Long>();
			
				if (elencodelegati!=null && !elencodelegati.isEmpty()) {
					for (String cfdelegato : elencodelegati) {
						datiCittadinodelegato = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(cfdelegato));
						if (!datiCittadinodelegato.isEmpty()) {
							log.debug(methodName, " idAura [ "+ datiCittadinodelegato.get(0).getIdAura() +" ] per cf delegante ["+cfdelegato+"]");
							idAuradelegato.add(Converter.getLong(datiCittadinodelegato.get(0).getIdAura()));
						}
					}
				}

				log.info(methodName, "Ricerco esenzioni per");
				log.info(methodName, " idAura :"+idAura);
				log.info(methodName, " idAuraDelegato :"+idAuradelegato);
				log.info(methodName, " filtri :"+filtri);
				
				List<EsenredTEsenzioniReddito> elencoEsenzioni = esenzioneCittadinoIf.getEsenzioniCittadino(idAura, filtri,idAuradelegato);

				if (elencoEsenzioni == null || elencoEsenzioni.isEmpty()) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					
					String messaggio = messaggioIf.getMessaggio("MSG005").getTesto();	
					
			
					res.put("code", "ESENZIONE_NON_TROVATA");
					res.put("title", messaggio);	
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.OK.getStatusCode()),  Status.OK.getReasonPhrase() );
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() +" "+  Status.OK.getReasonPhrase() +" "+res.toString(), "Elenco Esenzioni " , "READ", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(elencoEsenzioni).build();
				}

				elencoEsenzioniOut = getAuraInfoAcc(elencoEsenzioni);
			
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() +" "+  Status.OK.getReasonPhrase() +" -  Numero Esenzioni: "+elencoEsenzioniOut.size(), "Elenco Esenzioni ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);			
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(elencoEsenzioniOut).build();

			} catch (Exception ex) {

				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);			
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() +" "+res.toString(), "Elenco Esenzioni ", "READ", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
//				EsenRedRestEndpoint.closeAll();
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();

			}finally {
				log.info(methodName, "END");
			}
		}

		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/cittadini/{cf}/esenzioni")
		public Response creaEsenzioneReddito(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale,

				EsenzioneCreate EsenzioneCreate) throws Exception {

			String methodName = "POST EsenzioneReddito";
			log.info(methodName, "BEGIN");
			
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);
			log.info(methodName, " HeaderParam cf: " + codiceFiscale);
			
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			log.info(methodName, "1) controllo utente Shib-Identita-CodiceFiscale su AURA ");
			
			//controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(shib));

			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),  Status.NOT_FOUND.getReasonPhrase(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
			}
			
			corsedResponse.header("X-Request-ID", xrequest);
			String idAura = null;
			CreateRedditoService service = new CreateRedditoService();
			
			if(isTst==null) {
				List<ParametroStatoEsenzione> listparametriOut = new ArrayList<ParametroStatoEsenzione>();
				ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
				isTst= elencoParametri.get(0).getValore();		
				
				log.debug(methodName," isTst: " + isTst);
			}
					
			// Controllo su CF: Return 421 error. Vedi Swagger
			try {
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), shib + " " + EsenzioneCreate, "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);
				
				service.checkCF(EsenzioneCreate.getCreato_per().getCodice_fiscale(), isTst);
				service.checkCF(codiceFiscale, isTst);
			}
			catch (EsenzioneInvalidaException e) {
				if(e.getCodice().equals("421")) {
					//res.put("status", "421");
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");	
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(421), e.getDescrizione());
					res.put("detail", detail);
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), "421 " + res.toString(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
					
				}
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Codice fiscale mancante");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			} 		

			//se codice fiscale corretto prendi id aura controllo id aura del CF input
			log.info(methodName, "2) controllo HeadParam cf su AURA ");
			
			List<Cittadino> datiCittadino = null;
			try{
				datiCittadino = IntegrationClientImpl.getInstance()
					.findCittadino(new Cittadino(codiceFiscale));
			}
			catch (Exception e) {
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", "Cittadino non trovato dal servizio Aura " + codiceFiscale);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
		
		
			if (datiCittadino.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				//res.put("status", Status.NOT_FOUND);
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);	
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			idAura= datiCittadino.get(0).getIdAura();
			//verifica blocco
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			if(esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)){
				log.info(methodName, "Utente Bloccato");
				//res.put("status", Status.UNAUTHORIZED.getStatusCode());
				res.put("code", "NON_AUTORIZZATO");
				res.put("title", "Utente non autorizzato a compiere l'operazione");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()),  Status.UNAUTHORIZED.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.UNAUTHORIZED.getStatusCode() +" "+  Status.UNAUTHORIZED.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	        //controllo id aura del beneficiario 
			List<Cittadino> cittadinoAURABeneficiario = null;
			Cittadino cittadinoAURADichiarante = null;
			if (codiceFiscale.equalsIgnoreCase(EsenzioneCreate.getCreato_per().getCodice_fiscale())) {
				cittadinoAURABeneficiario =  datiCittadino;
				cittadinoAURADichiarante = cittadinoAURABeneficiario.get(0);
			}
			else
			{
				try {
				//accedo diretto in AURA perche' sono sicuro che il cittadino esiste (altrimenti non sarebbe arrivato neanche al login)
				cittadinoAURABeneficiario = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(EsenzioneCreate.getCreato_per().getCodice_fiscale()));
				cittadinoAURADichiarante = datiCittadino.get(0);	
				log.debug(methodName, "Trovato cittadinoAURADichiarante: " + cittadinoAURADichiarante);
				}	 catch (Exception e) {
					res.put("code", "AURA_NON_TROVATO");
					res.put("title", "Cittadino non trovato dal servizio Aura " + EsenzioneCreate.getCreato_per().getCodice_fiscale());	
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
					res.put("detail", detail);
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}


				if (cittadinoAURABeneficiario.isEmpty()) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
							messaggioIf.getMessaggio("MSG024").getTesto());
					//res.put("status", Status.NOT_FOUND);
					res.put("code", "AURA_NON_TROVATO");
					res.put("title", messaggio);	
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
					res.put("detail", detail);
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
			}
			Cittadino cittadinoAURATitolare = null;
			List<Cittadino> cittadiniAURATitolare = null;
			//titolare puo essere uguale a beneficiario o a dichiarante o differente
			if (EsenzioneCreate.getTitolare().getCodice_fiscale().equalsIgnoreCase(cittadinoAURABeneficiario.get(0).getCodFiscale())) {
				log.info(methodName,"condizone verificata --> titolare = cittadinoAURABeneficiario");
				cittadinoAURATitolare = cittadinoAURABeneficiario.get(0);
			}
			else if (EsenzioneCreate.getTitolare().getCodice_fiscale().equalsIgnoreCase(cittadinoAURADichiarante.getCodFiscale())) {
				log.info(methodName,"condizone verificata --> titolare = cittadinoAURADichiarante");
				cittadinoAURATitolare = cittadinoAURADichiarante;
			}
			else
			{
				log.info(methodName,"condizone verificata --> titolare <> cittadinoAURADichiarante e titolare <> cittadinoAURABeneficiario");
				try {
					cittadiniAURATitolare = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(EsenzioneCreate.getTitolare().getCodice_fiscale()));	
				}	 catch (Exception e) {
						res.put("code", "AURA_NON_TROVATO");
						res.put("title", "Cittadino non trovato dal servizio Aura " + EsenzioneCreate.getTitolare().getCodice_fiscale());	
						HashMap<String,String> detail = new HashMap<String,String>();
						detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
						res.put("detail", detail);
						CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
						auditIf.insertAudit(audit);	
						log.tracciaRes(methodName, res);
						return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				if (cittadiniAURATitolare.isEmpty()) {
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
							messaggioIf.getMessaggio("MSG024").getTesto());
					res.put("code", "AURA_NON_TROVATO");
					res.put("title", messaggio);	
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()),  Status.NOT_FOUND.getReasonPhrase() );
					res.put("detail", detail);
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() +" "+  Status.NOT_FOUND.getReasonPhrase() +" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				cittadinoAURATitolare = cittadiniAURATitolare.get(0);
			}
				

			try {
				EsenzioneAcceleratore r = service.insert(EsenzioneCreate,codiceFiscale,cittadinoAURABeneficiario.get(0),cittadinoAURADichiarante,cittadinoAURATitolare);

				// In attesa
				if(r.getCodAura().equals("0003")) {
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.ACCEPTED.getStatusCode() +" "+  Status.ACCEPTED.getReasonPhrase() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.ACCEPTED).entity(r).build();	
				}
				else if (!r.getCodAura().equalsIgnoreCase("7199") && !r.getCodAura().equalsIgnoreCase("7099")) {
					res.put("code", "ERRORE_AURA");
					res.put("title", r.getMessaggio());	
					HashMap<String,String> detail = new HashMap<String,String>();

					detail.put(r.getCodAura(),  "Errore generico Aura");
					res.put("detail", detail);

					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), r.getCodAura() +" "+ r.getMessaggio()+" "+res.toString() , "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
				}
				
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.CREATED.getStatusCode() +" "+  Status.CREATED.getReasonPhrase() + " " +r.getProtocollo(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.CREATED).entity(r).build();	
				
			} catch (EsenzioneInvalidaException e) {
				if(e.getCodice().equals("421")) {
					//res.put("status", "421");
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");		
					HashMap<String,String> detail = new HashMap<String,String>();
					detail.put(Integer.toString(421), e.getDescrizione());
					res.put("detail", detail);
					CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), "421 " +res.toString(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
					auditIf.insertAudit(audit);	
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				//res.put("status", "400");
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());			
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();

			} catch (CheckException e) {
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(e.getCodice(), "Errore del servizio Aura");
				res.put("detail", detail);
				
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}

			catch (Exception e) {
				//res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", "Generico errore relativo al client (richiesta errata)");
				HashMap<String,String> detail = new HashMap<String,String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()),  Status.BAD_REQUEST.getReasonPhrase() );
				res.put("detail", detail);
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() +" "+  Status.BAD_REQUEST.getReasonPhrase() + " " +res.toString(), "Creazione esenzione", "INSERT", shib, xrequest, null,xcodserv);
				auditIf.insertAudit(audit);	
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
		}
		
		@GET
		@Produces({ "application/pdf", "application/json" })
		@Path("/cittadini/{cf}/esenzioni/{id}")
		public Response StampaEsenzioneReddito(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest, @HeaderParam("Accept") String accept,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib, @HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale, @PathParam("id") String idesenzione,
				@QueryParam("cf_beneficiario") String cf_beneficiario) throws Exception {
	
			String methodName = "GET StampaEsenzioneReddito";
			log.info(methodName, "BEGIN");
			
			Map<String, Object> res = new LinkedHashMap<String, Object>();
			CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), shib + " ID: " + idesenzione + " / " + accept,
					"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
	
			Boolean pdf = false;
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
	
			Cittadino c = new Cittadino();
			c.setCodFiscale(cf_beneficiario);
			List<Cittadino> beneflist = IntegrationClientImpl.getInstance().findCittadino(c);
	
			if (beneflist.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getReasonPhrase(), "Stampa/mostra esenzione",
						"READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	
			String idAuraBeneficiario = beneflist.get(0).getIdAura();
	
			auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
	
			auditIf.insertAudit(audit);
			
			if (idesenzione.toString().indexOf("-") < 0) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG036";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Stampa/mostra esenzione", "READ",
						shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			if (!idesenzione.substring(0, 3).equalsIgnoreCase("000") && !idesenzione.substring(0, 3).equalsIgnoreCase("001")
					&& !idesenzione.substring(0, 3).equalsIgnoreCase("002")) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG036";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Stampa/mostra esenzione", "READ",
						shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(shib));
	
			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getReasonPhrase(), "Stampa/mostra esenzione",
						"READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	
			// controlla se idesenzione e vuoto
	
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
	
			List<EsenredTEsenzioniReddito> esenzioni = null;
			String TipoEsenzione = null;
			String[] valattestati = null;
			// verifica se esenzione di esenred o certitificata o autocertificata
			int pos = idesenzione.indexOf("-");
			if (pos < 0) {
				// manca il - errore
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG036";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Stampa/mostra esenzione", "READ",
						shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			if (idesenzione.substring(0, 3).equalsIgnoreCase("000")) {
				esenzioni = esenzioneCittadinoIf.getEsenzioneById(new Long(idesenzione.substring(pos + 1)));
				TipoEsenzione = "Esenred";
			} else if (idesenzione.substring(0, 3).equalsIgnoreCase("001")) {
				TipoEsenzione = "Autocertificata";
				esenzioni = IntegrationClientImpl.getInstance().getEsenzioni(null, null, null,
						idAuraBeneficiario, "B",idesenzione.substring(pos + 1),null);
			} else if (idesenzione.substring(0, 3).equalsIgnoreCase("002")) {
				String attestato = idesenzione.substring(pos + 1);
				// vado a slittare i dati dell'attestato
				valattestati = attestato.split("-");
				// verifico se mi ha trovato 3 campi
				if (valattestati.length != 2) {
					// errore
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = messaggioIf.getMessaggio("MSG044").getTesto();
					res.put("code", "ESENZIONE_NON_TROVATA");
					res.put("title", messaggio);
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.OK.getStatusCode()), Status.OK.getReasonPhrase());
					res.put("detail", detail);
					audit = new CsiLogAudit(
							req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase()
									+ " " + res.toString(),
							"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
	
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(res).build();
				}
				// metto gli slash sulla data
				if (valattestati[1].length() != 8) {
					// errore
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = messaggioIf.getMessaggio("MSG044").getTesto();
					res.put("code", "ESENZIONE_NON_TROVATA");
					res.put("title", messaggio);
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.OK.getStatusCode()), Status.OK.getReasonPhrase());
					res.put("detail", detail);
					audit = new CsiLogAudit(
							req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase()
									+ " " + res.toString(),
							"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
	
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(res).build();
				}
				// metto gli slash
				String valattestaticompleta = valattestati[1].substring(0, 2) + "/" + valattestati[1].substring(2, 4) + "/"
						+ valattestati[1].substring(4, 8);
				if (!Checker.isData(valattestaticompleta, "dd/MM/yyyy", Locale.ITALIAN)) {
					// errore
					messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
					String messaggio = messaggioIf.getMessaggio("MSG044").getTesto();
					res.put("code", "ESENZIONE_NON_TROVATA");
					res.put("title", messaggio);
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.OK.getStatusCode()), Status.OK.getReasonPhrase());
					res.put("detail", detail);
					audit = new CsiLogAudit(
							req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase()
									+ " " + res.toString(),
							"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
	
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(res).build();
				}
				TipoEsenzione = "Certificata";
				esenzioni = IntegrationClientImpl.getInstance().getEsenzioni(null, valattestati[0],
						valattestaticompleta, idAuraBeneficiario, "B", null,null);
			}else {
				res.put("code", "ERRORE GENERICO");
				res.put("title", "TIPOLOGIA ESENZIONE NON TROVATA");
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), 
						"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit1);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			
	
			if (esenzioni == null || esenzioni.size() == 0) {
				// gestione errore 404
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = messaggioIf.getMessaggio("MSG005").getTesto();
				
				res.put("code", "ESENZIONE_NON_TROVATA");
				res.put("title", messaggio);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.OK.getStatusCode()), Status.OK.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(),
						Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase() + " " + res.toString(),
						"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(res).build();
			}
	
			Enumeration headerNames = req.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = req.getHeader(key);
				if (key.equalsIgnoreCase("accept") && value.equalsIgnoreCase("application/pdf")) {
					pdf = true;
					break;
				}
				// map.put(key, value);
			}
	
			String idAura = null;
	
			if (isTst == null) {
				ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
				isTst = elencoParametri.get(0).getValore();
			}
	
			try {
				CreateRedditoService service = new CreateRedditoService();
				service.checkCF(codiceFiscale, isTst);
			} catch (EsenzioneInvalidaException e) {
				if (e.getCodice().equals("421")) {
					
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(421), e.getDescrizione());
					res.put("detail", detail);
					CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), "421 " + res.toString(), "Elenco Esenzioni",
							"INSERT", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit1);
	
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Creazione esenzione", "INSERT",
						shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit1);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	
			// se codice fiscale corretto prendi id aura
			List<Cittadino> datiCittadino = null;
	
			datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
	
			if (datiCittadino.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(),
						Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase() + " " + res.toString(),
						"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			idAura = datiCittadino.get(0).getIdAura();
			// verifica blocco
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			if (esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG028";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", "ko");
				res.put("code", "NON_AUTORIZZATO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()), Status.UNAUTHORIZED.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(
						req.getRemoteAddr(), Status.UNAUTHORIZED.getStatusCode() + " "
								+ Status.UNAUTHORIZED.getReasonPhrase() + " " + res.toString(),
						"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			if (pdf) {
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() + " " + Status.OK.getReasonPhrase(),
						"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				if (TipoEsenzione.equalsIgnoreCase("ESENRED")) {
					return getReportCertificatoEsenzione(new Integer(idesenzione.substring(pos + 1)), "Operatore");
				} else if (TipoEsenzione.equalsIgnoreCase("AUTOCERTIFICATA")) {
					return getReportCertificatoEsenzioneStorico(idesenzione.substring(pos + 1), null, null, "Operatore",
							TipoEsenzione, cf_beneficiario);
				} else
					return getReportCertificatoEsenzioneStorico(null, valattestati[0], valattestati[1],
							"Operatore", TipoEsenzione, cf_beneficiario);
			} else {
				try {
					// leggo l'esenzione
					EsenredTEsenzioniReddito esenzione = esenzioni.get(0);
					Cittadino cittadinoAURABeneficiario = null;
					Cittadino cittadinoAURADichiarante = null;
					Cittadino cittadinoAURATitolare = null;
					if (esenzione.getIdCittadinoBeneficiario().equals(esenzione.getIdCittadinoDichiarante())) {
						cittadinoAURABeneficiario = IntegrationClientImpl.getInstance()
								.getCittadino(esenzione.getIdCittadinoBeneficiario().toString());
						cittadinoAURADichiarante = cittadinoAURABeneficiario;
					} else {
						cittadinoAURABeneficiario = IntegrationClientImpl.getInstance()
								.getCittadino(esenzione.getIdCittadinoBeneficiario().toString());
						cittadinoAURADichiarante = IntegrationClientImpl.getInstance()
								.getCittadino(esenzione.getIdCittadinoDichiarante().toString());
					}
					if (esenzione.getIdCittadinoTitolare() != null) {
						if (esenzione.getIdCittadinoBeneficiario().equals(esenzione.getIdCittadinoTitolare())) {
							cittadinoAURATitolare = cittadinoAURABeneficiario;
						} else if (esenzione.getIdCittadinoDichiarante().equals(esenzione.getIdCittadinoTitolare())) {
							cittadinoAURATitolare = cittadinoAURADichiarante;
						} else {
							cittadinoAURATitolare = IntegrationClientImpl.getInstance()
									.getCittadino(esenzione.getIdCittadinoTitolare().toString());
						}
					}
	
					EsenzioneAcceleratore r = new EsenzioneAcceleratore(esenzione, cittadinoAURABeneficiario,
							cittadinoAURADichiarante, cittadinoAURATitolare);
	
					audit = new CsiLogAudit(req.getRemoteAddr(),
							Status.OK.getStatusCode() + " " + Status.OK.getReasonPhrase(), "Stampa/mostra esenzione",
							"READ", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
					//aggiunta per evitare di vedere il protocollo con gli 0 davanti
					if (r.getProtocollo()!=null) {
					Long prot = Converter.getLong(r.getProtocollo());
					r.setProtocollo(prot.toString());
					}
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(r).build();
				}
	
				catch (Exception e) {
	
					// res.put("status", Status.BAD_REQUEST.getStatusCode());
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Generico errore relativo al client (richiesta errata)");
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), e.getLocalizedMessage());
					res.put("detail", detail);
	
					audit = new CsiLogAudit(
							req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
									+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(),
							"Stampa/mostra esenzione", "READ", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
	
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
	
			}
	
		}
		
		@PUT
		@Path("/cittadini/{cf}/esenzioni/{id}/revoca")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response revocaEsenzione(@Context HttpServletRequest req, @HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib, @HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale, @PathParam("id") Integer idesenzione,
				MotivazioneRevoca motivazione_revoca) throws Exception {
	
			String methodName = "PUT revocaEsenzione";
			log.info(methodName, "BEGIN");
			log.info(methodName, " HeaderParam X-Request-ID: " + xrequest);
			log.info(methodName, " HeaderParam Shib-Identita-CodiceFiscale: " + shib);
			log.info(methodName, " HeaderParam X-Codice-Servizio: " + xcodserv);
			log.info(methodName, " HeaderParam cf: " + codiceFiscale);
			log.info(methodName, " HeaderParam id: " + idesenzione);
			
			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new LinkedHashMap<String, Object>();
	
			// controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(shib));
	
			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
				CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getReasonPhrase(),
						"Revoca esenzione", "UPDATE", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	
			String idAura = null;
	
			auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
			CsiLogAudit audit = new CsiLogAudit(req.getRemoteAddr(),
					shib + " ID: " + idesenzione + " " + motivazione_revoca.toString(), "Revoca esenzione", "UPDATE", shib,
					xrequest, null, xcodserv);
			auditIf.insertAudit(audit);
	
			// controlli
			if (idesenzione == 0) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG037";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE GENERICO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			log.info(methodName,"Ricerco l'esenzione sul db.");
			List<EsenredTEsenzioniReddito> esenzioni = esenzioneCittadinoIf.getEsenzioneById(idesenzione);
	
			if (esenzioni.size() == 0) {
				// gestione errore 404
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG037";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				
				res.put("code", "ERRORE GENERICO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(),
						Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase() + " " + res.toString(),
						"Revoca esenzione", "UPDATE", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	
			if (isTst == null) {
				ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
				isTst = elencoParametri.get(0).getValore();
				log.info(methodName, "isTst: " + isTst);
			}
	
			try {
				CreateRedditoService service = new CreateRedditoService();
				service.checkCF(codiceFiscale, isTst);
	
			} catch (EsenzioneInvalidaException e) {
				if (e.getCodice().equals("421")) {
					
					res.put("code", "ERRORE GENERICO");
					res.put("title", "Codice fiscale non corretto");
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(421),e.getDescrizione() );
					res.put("detail", detail);
					CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), "421 " + res.toString(), "Revoca Esenzione",
							"UPDATE", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit1);
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit1);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			// se codice fiscale corretto prendi id aura
			List<Cittadino> datiCittadino = null;
	
			datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
	
			if (datiCittadino.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", "Cittadino non trovato dal servizio Aura");
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " "
						+ Status.NOT_FOUND.getReasonPhrase() + " - " + res.toString(), "Dettaglio cittadino", "READ", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
	
			}
			idAura = datiCittadino.get(0).getIdAura();
			// verifica blocco
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			if (esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)) {
				log.info(methodName, "Utente Bloccato");
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG028";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", Status.UNAUTHORIZED.getStatusCode());
				res.put("code", "NON_AUTORIZZATO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()), Status.UNAUTHORIZED.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
	
			try {
				log.debug(methodName," Preparo la revoca");
				RevocaEsenzioneBO revocaEsenzioneBO = new RevocaEsenzioneBO();
				revocaEsenzioneBO.setIdEsenzione(idesenzione.longValue());
				revocaEsenzioneBO.setBlocco(false);
				revocaEsenzioneBO.setNotaInternaRevoca("Revocata dal cittadino");
				if (motivazione_revoca.getMotivazione_revoca() == null) {
					revocaEsenzioneBO.setMotivoRevocaEsenzione(null);
				} else {
					revocaEsenzioneBO.setMotivoRevocaEsenzione(motivazione_revoca.getMotivazione_revoca());
				}
				EsenzioneRedditoService service = new EsenzioneRedditoService(revocaEsenzioneBO);
				CreateRedditoService service1 = new CreateRedditoService();
				service.checkCampoRevocaObbligatorio();
				service.checkCampoRevoca();
				service.exists();
				service.isRevocabile();
				

				List<String> elencodelegati = IntegrationClientImpl.getInstance().chiamaGetDeleganti(codiceFiscale);
				List<Cittadino> datiCittadinodelegato = null;
				EsenzioneAcceleratore r = new EsenzioneAcceleratore();
				
				if (elencodelegati!=null) {
					for (String cfdelegato : elencodelegati) {
						if (!codiceFiscale.equals(cfdelegato)) {
							datiCittadinodelegato = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(cfdelegato));
							if (!datiCittadinodelegato.isEmpty()) {
								if (esenzioni.get(0).getIdCittadinoBeneficiario().equals(datiCittadinodelegato.get(0).getIdAura()))
									//metti idaura del CF in input nel campo modifica vuol dire che operazione fatta da un delegato 
									 r = service1.update(revocaEsenzioneBO,idAura);
								else
									r = service1.update(revocaEsenzioneBO,null);
							}
						}
						else
							r = service1.update(revocaEsenzioneBO,null);
					}
				}
				else
					r = service1.update(revocaEsenzioneBO,null);

				
				if (!r.getCodAura().equalsIgnoreCase("7199") && !r.getCodAura().equalsIgnoreCase("7099")) {
					res.put("code", "ERRORE_AURA");
					res.put("title",r.getMessaggio());
					HashMap<String, String> detail = new HashMap<String, String>();

					detail.put(r.getCodAura(),"Errore generico Aura");
					res.put("detail", detail);

					CsiLogAudit audit1 = new CsiLogAudit(req.getRemoteAddr(),
							r.getCodAura() + " " + r.getMessaggio() + " " + res.toString(), "Revoca esenzione", "UPDATE",
							shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit1);
					log.tracciaRes(methodName, res);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.INTERNAL_SERVER_ERROR).entity(res)
							.build();
				}
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() + " " + Status.OK.getReasonPhrase(),
						"Revoca esenzione", "UPDATE", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(r).build();
	
			} catch (CheckException e) {
	
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE_GENERICO");
				res.put("title", e.getDescrizione());
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(e.getCodice(),"Errore generico" );
				res.put("detail", detail);
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			} catch (Exception e) {
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE_GENERICO");
				res.put("title", e.getMessage());
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				log.tracciaRes(methodName, res);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
		}

	
		private Map<String, Object> getMapResponse(){
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

		private List<EsenzioneOperatore> getAuraInfoOperatore(List<EsenredTEsenzioniReddito> elencoEsenzioni) {
			List<EsenzioneOperatore> elencoEsenzioniOut = new ArrayList<EsenzioneOperatore>();

			String methodName = "getAuraInfoOperatore";
			log.info(methodName, "BEGIN");
			
			Set<Long> elencoIdAura = new HashSet<Long>();


			for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
				elencoIdAura.add(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());
				elencoIdAura.add(esenredTEsenzioniReddito.getIdCittadinoDichiarante());
				if (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null) {
					esenredTEsenzioniReddito.setIdCittadinoTitolare(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());
				}
					elencoIdAura.add(esenredTEsenzioniReddito.getIdCittadinoTitolare());	
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

			for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
				Cittadino beneficiario = null;
				Cittadino dichiarante = null;
				Cittadino titolare = null;
				long idAuraBeneficiario = esenredTEsenzioniReddito.getIdCittadinoBeneficiario().longValue();
				long idAuraDichiarante = esenredTEsenzioniReddito.getIdCittadinoDichiarante().longValue();
				long idAuraTitolare = esenredTEsenzioniReddito.getIdCittadinoTitolare().longValue();

				for (Cittadino c : elencoCittadiniDistinti) {
					long idAuraToTest = new Long(c.getIdAura()).longValue();
					if (idAuraToTest == idAuraBeneficiario)
						beneficiario = c;
					if (idAuraToTest == idAuraDichiarante)
						dichiarante = c;
					if (idAuraToTest == idAuraTitolare)
						titolare = c;
				}
				if (beneficiario != null && dichiarante != null && titolare != null)
					elencoEsenzioniOut.add(new EsenzioneOperatore(esenredTEsenzioniReddito, beneficiario, titolare,dichiarante));
//					else
//						elencoEsenzioniOut.add(new EsenzioneOperatore(esenredTEsenzioniReddito, beneficiario, dichiarante));
			}
			
			log.info(methodName, "END");
			return elencoEsenzioniOut;
		}

		private List<EsenzioneCittadino> getAuraInfo(List<EsenredTEsenzioniReddito> elencoEsenzioni, Cittadino dichiarante) {

			String methodName = "getAuraInfo";
			log.info(methodName, "BEGIN");
			List<EsenzioneCittadino> elencoEsenzioniOut = new ArrayList<EsenzioneCittadino>();

			Set<Long> elencoIdAuraBeneficiari = new HashSet<Long>();
			Set<Long> elencoIdAuraTitolari = new HashSet<Long>();
			for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
				Long idAuraBeneficiario = esenredTEsenzioniReddito.getIdCittadinoBeneficiario();
				elencoIdAuraBeneficiari.add(idAuraBeneficiario);
				if (esenredTEsenzioniReddito.getIdCittadinoTitolare() == null) {
					esenredTEsenzioniReddito.setIdCittadinoTitolare(esenredTEsenzioniReddito.getIdCittadinoBeneficiario());
				}
			//	if (esenredTEsenzioniReddito.getIdCittadinoTitolare()!=null) {
			//	if (!esenredTEsenzioniReddito.getIdCittadinoTitolare().equals(esenredTEsenzioniReddito.getIdCittadinoBeneficiario())) {
				Long idAuraTitolare = esenredTEsenzioniReddito.getIdCittadinoTitolare();
				elencoIdAuraTitolari.add(idAuraTitolare);
			//	}
			//	}
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
			Cittadino c =null;
			for (Long idAuraTitolare : elencoIdAuraTitolari) {
				if (!elencoCittadiniBeneficiari.containsKey(idAuraTitolare)) {
				c = IntegrationClientImpl.getInstance().getCittadino(idAuraTitolare.toString());
			}else
			{
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
			//	if (e.getIdCittadinoTitolare()!=null) {
					elencoEsenzioniOut
					.add(new EsenzioneCittadino(e, elencoCittadiniBeneficiari.get(e.getIdCittadinoBeneficiario()), elencoCittadiniTitolari.get(e.getIdCittadinoTitolare()),dichiarante));

			}
			
			log.info(methodName, "END");
			return elencoEsenzioniOut;
		}
		
		private List<EsenzioneAcceleratore> getAuraInfoAcc(List<EsenredTEsenzioniReddito> elencoEsenzioni) {

			String methodName = "getAuraInfo";
			log.info(methodName, "BEGIN");
			
			List<EsenzioneAcceleratore> elencoEsenzioniOut = new ArrayList<EsenzioneAcceleratore>();

			Set<Long> elencoIdAuraBeneficiari = new HashSet<Long>();
			Set<Long> elencoIdAuraTitolari = new HashSet<Long>();
			Set<Long> elencoIdAuraDichiaranti = new HashSet<Long>();
			for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
				Long idAuraBeneficiario = esenredTEsenzioniReddito.getIdCittadinoBeneficiario();
				Long idAuraDichiarante = esenredTEsenzioniReddito.getIdCittadinoDichiarante();
				if (idAuraBeneficiario!=null)
				elencoIdAuraBeneficiari.add(idAuraBeneficiario);
				if (idAuraDichiarante!=null)
				elencoIdAuraDichiaranti.add(idAuraDichiarante);
				

				
			Long idAuraTitolare = esenredTEsenzioniReddito.getIdCittadinoTitolare();
			if (idAuraTitolare!=null)
				elencoIdAuraTitolari.add(idAuraTitolare);
			else
				esenredTEsenzioniReddito.setIdCittadinoTitolare(new Long(0));
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
			Cittadino c =null;
			for (Long idAuraDichiarante : elencoIdAuraDichiaranti) {
				if (!elencoCittadiniBeneficiari.containsKey(idAuraDichiarante)) {
					if (idAuraDichiarante!=0)
				  c = IntegrationClientImpl.getInstance().getCittadino(idAuraDichiarante.toString());
				}
				else {
					c = elencoCittadiniBeneficiari.get(idAuraDichiarante);	
				}
				if (c == null) {
					c = new Cittadino();
					c.setIdAura(idAuraDichiarante.toString());
				}
				elencoCittadiniDichiaranti.put(idAuraDichiarante, c);
			}
			
			HashMap<Long, Cittadino> elencoCittadiniTitolari = new HashMap<Long, Cittadino>();
			c =null;
			for (Long idAuraTitolare : elencoIdAuraTitolari) {
				if (!elencoCittadiniBeneficiari.containsKey(idAuraTitolare) && !elencoCittadiniDichiaranti.containsKey(idAuraTitolare)) {
					if (idAuraTitolare!=0)
				c = IntegrationClientImpl.getInstance().getCittadino(idAuraTitolare.toString());
			} else if (elencoCittadiniBeneficiari.containsKey(idAuraTitolare))
			{
				c = elencoCittadiniBeneficiari.get(idAuraTitolare);
			}
			else if (elencoCittadiniDichiaranti.containsKey(idAuraTitolare))
			{
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
			//	if (e.getIdCittadinoTitolare()!=null) {
					elencoEsenzioniOut
					.add(new EsenzioneAcceleratore(e, elencoCittadiniBeneficiari.get(e.getIdCittadinoBeneficiario()), elencoCittadiniDichiaranti.get(e.getIdCittadinoDichiarante()),elencoCittadiniTitolari.get(e.getIdCittadinoTitolare())));
					

			}
			log.info(methodName, "END");
			return elencoEsenzioniOut;
		}

		private List<ListaBeneficiari> getAuraInfoBeneficiari(List<Long> elencoEsenzioni) {

			String methodName = "getAuraInfoBeneficiari";
			log.info(methodName, "BEGIN");
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
				elencoEsenzioniOut
				.add(new ListaBeneficiari(c));
				//elencoCittadiniBeneficiari.put(idAuraBeneficiario, c);
			}
			log.info(methodName, "END");
			return elencoEsenzioniOut;
		}
		
		private List<ListaTitolari> getAuraInfoTitolari(List<Long> elencoEsenzioni) {

			String methodName = "getAuraInfoTitolari";
			log.info(methodName, "BEGIN");
			
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
				elencoEsenzioniOut
				.add(new ListaTitolari(c));
				//elencoCittadiniBeneficiari.put(idAuraBeneficiario, c);
			}
			log.info(methodName,"END");
			return elencoEsenzioniOut;
		}
	
		
		@PUT
		@Path("/cittadini/{cf}/esenzioni/{codEsenzione}/{dataInizio}/revocacertificata")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response revocaEsenzioneCertificata(@Context HttpServletRequest req,
				@HeaderParam("X-Request-ID") String xrequest,
				@HeaderParam("Shib-Identita-CodiceFiscale") String shib,
				@HeaderParam("X-Codice-Servizio") String xcodserv,
				@PathParam("cf") String codiceFiscale,
				@PathParam("codEsenzione") String codiceEsenzione,
				@PathParam("dataInizio") String dataInizio,
				MotivazioneRevoca motivazione_revoca) throws Exception {
		
			String methodName = "PUT revocaEsenzioneCertificata";
			log.info(methodName, "BEGIN");

			Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
			Map<String, Object> res = new HashMap<String, Object>();
			CsiLogAudit audit = new CsiLogAudit();
			// controllo utente shibolet
			List<Cittadino> datiCittadinoshib = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(shib));
			
			auditIf = (AuditIf) SpringApplicationContext.getBean("audit");
			if (datiCittadinoshib.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", messaggio);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);				
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getReasonPhrase(),
						"Revoca esenzione Certificata", "UPDATE", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
		
			if (isTst == null) {
				ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
				List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
				isTst = elencoParametri.get(0).getValore();
			}
			String idAura = null;
			try {
				CreateRedditoService service = new CreateRedditoService();
				service.checkCF(codiceFiscale, isTst);
				
			} catch (EsenzioneInvalidaException e) {
				if (e.getCodice().equals("421")) {
					
					res.put("code", "ERRORE GENERICO");
					res.put("title", e.getDescrizione());
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(421), "Codice fiscale non corretto");
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), "421 " + res.toString(), "Revoca Esenzione Certificata ",
							"UPDATE", shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
				}
				
				res.put("code", "ERRORE GENERICO");
				res.put("title", e.getDescrizione());
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione Certificata", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			List<Cittadino> datiCittadino = null;
			
			datiCittadino = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codiceFiscale));
	
			if (datiCittadino.isEmpty()) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String messaggio = Util.composeMessage(messaggioIf.getMessaggio("MSG011"),
						messaggioIf.getMessaggio("MSG024").getTesto());
				
				res.put("code", "AURA_NON_TROVATO");
				res.put("title", "Cittadino non trovato dal servizio Aura");
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.NOT_FOUND.getStatusCode() + " "
						+ Status.NOT_FOUND.getReasonPhrase() + " - " + res.toString(), "Revoca Esenzione Certificata", "READ", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
	
			}
			idAura = datiCittadino.get(0).getIdAura();
			//controllo se l'esenzione esiste
			String datainival = null;
			if (Checker.isValorizzato(dataInizio)) {
				datainival = dataInizio.substring(0, 2) + "/" + dataInizio.substring(2, 4) + "/"+ dataInizio.substring(4, 8);
			}
			
				List<EsenredTEsenzioniReddito> esenzioni = IntegrationClientImpl.getInstance().getEsenzioni(null, codiceEsenzione, datainival, idAura, "B",null,null);

			if (esenzioni.size() == 0) {
				// gestione errore 404
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG037";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				
				res.put("code", "ERRORE GENERICO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.NOT_FOUND.getStatusCode()), Status.NOT_FOUND.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(),
						Status.NOT_FOUND.getStatusCode() + " " + Status.NOT_FOUND.getReasonPhrase() + " " + res.toString(),
						"Revoca esenzione Certificata", "UPDATE", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
	
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			// verifica blocco
			esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
			if (esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(idAura), null)) {
				messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
				String codMSG = "MSG028";
				String error = messaggioIf.getMessaggio(codMSG).getTesto();
				// res.put("status", Status.UNAUTHORIZED.getStatusCode());
				res.put("code", "NON_AUTORIZZATO");
				res.put("title", error);
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()), Status.UNAUTHORIZED.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione Certificata", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}
			//verifica se revocata
			 boolean isRevocata = esenzioni.get(0).getCodStato().equalsIgnoreCase(StatoEsenzione.REVOCATA.getCodice());
		        if (isRevocata) {
		        	messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
		        	EsenredCMessaggi msg010 = this.messaggioIf.getMessaggio("MSG010");
					String error = Util.composeMessage(msg010, "l'esenzione risulta essere non revocabile");
					// res.put("status", Status.UNAUTHORIZED.getStatusCode());
					res.put("code", "NON_AUTORIZZATO");
					res.put("title", error);
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(Integer.toString(Status.UNAUTHORIZED.getStatusCode()), Status.UNAUTHORIZED.getReasonPhrase());
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
							+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione Certificata", "UPDATE", shib,
							xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
		        }
			
			try {
			RevocaEsenzioneCertificataBO revocaEsenzioneBO = new RevocaEsenzioneCertificataBO();
			revocaEsenzioneBO.setCodiceEsenzione(codiceEsenzione);
			revocaEsenzioneBO.setCodiceFiscaleAssistito(codiceFiscale);
			revocaEsenzioneBO.setCodiceFiscaleChiamante(shib);
			revocaEsenzioneBO.setDataInizioValidita(Converter.getDataAuraRevoca(dataInizio));
			revocaEsenzioneBO.setNotaInternaRevoca("Revocata dal cittadino");
			if (motivazione_revoca.getMotivazione_revoca() == null) {
				revocaEsenzioneBO.setMotivoRevocaEsenzione(null);
			} else {
				revocaEsenzioneBO.setMotivoRevocaEsenzione(motivazione_revoca.getMotivazione_revoca());
			}
			//creare un nuovo servizio che effettua la revoca
			CreateRedditoService service = new CreateRedditoService();
			EsenzioneAcceleratore r = new EsenzioneAcceleratore();
			r = service.updateCertificate(revocaEsenzioneBO,idAura);
			
				// controlla errore Aura
				// se codice 7099 and 7199 allora esito ok
				if (!r.getCodAura().equalsIgnoreCase("7199") && !r.getCodAura().equalsIgnoreCase("7099") && !r.getCodAura().equalsIgnoreCase("7499")) {
					res.put("code", "ERRORE_AURA");
					res.put("title", "Errore generico Aura");
					HashMap<String, String> detail = new HashMap<String, String>();
					detail.put(r.getCodAura(), r.getMessaggio());
					res.put("detail", detail);
					audit = new CsiLogAudit(req.getRemoteAddr(),
							r.getCodAura() + " " + r.getMessaggio() + " " + res.toString(), "Revoca esenzione Certificata", "UPDATE",
							shib, xrequest, null, xcodserv);
					auditIf.insertAudit(audit);
					return corsedResponse.header("X-Request-ID", xrequest).status(Status.INTERNAL_SERVER_ERROR).entity(res)
							.build();
				}
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.OK.getStatusCode() + " " + Status.OK.getReasonPhrase(),
						"Revoca esenzione Certificata", "UPDATE", shib, xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.OK).entity(r).build();			
			} catch (CheckException e) {
				
				// res.put("status", Status.BAD_REQUEST.getStatusCode());
				res.put("code", "ERRORE_GENERICO");
				res.put("title", "Errore generico");
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(e.getCodice(), e.getDescrizione());
				res.put("detail", detail);
	
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione Certificata", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();	
			} catch (Exception e) {

				res.put("code", "ERRORE_GENERICO");
				res.put("title", e.getMessage());
				HashMap<String, String> detail = new HashMap<String, String>();
				detail.put(Integer.toString(Status.BAD_REQUEST.getStatusCode()), Status.BAD_REQUEST.getReasonPhrase());
				res.put("detail", detail);
				audit = new CsiLogAudit(req.getRemoteAddr(), Status.BAD_REQUEST.getStatusCode() + " "
						+ Status.BAD_REQUEST.getReasonPhrase() + " " + res.toString(), "Revoca esenzione Certificata", "UPDATE", shib,
						xrequest, null, xcodserv);
				auditIf.insertAudit(audit);
				return corsedResponse.header("X-Request-ID", xrequest).status(Status.BAD_REQUEST).entity(res).build();
			}finally {
				log.info(methodName, "END");
			}
		}
}