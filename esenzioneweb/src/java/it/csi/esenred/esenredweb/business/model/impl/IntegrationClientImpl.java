/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;

import org.apache.axis.AxisFault;

import it.csi.esenred.esenpatweb.business.gateway.Attachment;
import it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity;
import it.csi.esenred.esenpatweb.business.gateway.FileResult;
import it.csi.esenred.esenpatweb.business.gateway.GateFireSrvcSoapBindingStub;
import it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_Service;
import it.csi.esenred.esenpatweb.business.gateway.Identity;
import it.csi.esenred.esenpatweb.business.gateway.OtpReqInput;
import it.csi.esenred.esenpatweb.business.gateway.OtpResult;
import it.csi.esenred.esenpatweb.business.gateway.PadesInput;
import it.csi.esenred.esenpatweb.business.gateway.SignIdentity;
import it.csi.esenred.esenpatweb.business.iride.base.Application;
import it.csi.esenred.esenpatweb.business.iride.base.Identita;
import it.csi.esenred.esenpatweb.business.iride.base.PolicyEnforcerBaseSoapBindingStub;
import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.CittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniEsenred;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedRes;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedSoap;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRed_Service;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologia;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaRes;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaResponse;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaSoap;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologia_Service;
import it.csi.esenred.esenredweb.business.aura.find.AnagrafeFindStub;
import it.csi.esenred.esenredweb.business.aura.find.DatiAnagrafici;
import it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici;
import it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiRequest;
import it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse;
import it.csi.esenred.esenredweb.business.aura.find.WSSecurityHeaderSOAPHandler;
import it.csi.esenred.esenredweb.business.aura.get.AnagrafeSanitariaNewStub;
import it.csi.esenred.esenredweb.business.aura.get.DatiPrimari;
import it.csi.esenred.esenredweb.business.aura.get.GetProfiloSanitarioNew;
import it.csi.esenred.esenredweb.business.aura.get.GetProfiloSanitarioNewResponse;
import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.aura.get.InfoSanitarie;
import it.csi.esenred.esenredweb.business.aura.get.SoggettoAuraNewMsg;
import it.csi.esenred.esenredweb.business.aura.getEsenred.GetEsenRedBody;
import it.csi.esenred.esenredweb.business.aura.getEsenred.GetEsenRedRes;
import it.csi.esenred.esenredweb.business.aura.getEsenred.GetEsenRedSoap;
import it.csi.esenred.esenredweb.business.aura.getEsenred.GetEsenRed_Service;
import it.csi.esenred.esenredweb.business.aura.getEsenred.InfoEsenzioneGetEsenredVO;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.InsertAutocertEseRedSoap;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.InsertAutocertEseRed_Service;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Request;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.RequestBody;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;
import it.csi.esenred.esenredweb.business.aura.iterrogamefesenredd.InterrogaMefEsenreddRes;
import it.csi.esenred.esenredweb.business.aura.iterrogamefesenredd.InterrogaMefEsenreddResponseBody;
import it.csi.esenred.esenredweb.business.aura.iterrogamefesenredd.InterrogaMefEsenreddSoap;
import it.csi.esenred.esenredweb.business.aura.iterrogamefesenredd.InterrogaMefEsenredd_Service;
import it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RevocaAutocertEseRedSoap;
import it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RevocaAutocertEseRed_Service;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneCertificataBO;
import it.csi.esenred.esenredweb.business.deleghebe.ApplicazioneRichiedente;
import it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService;
import it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService_Service;
import it.csi.esenred.esenredweb.business.deleghebe.GetDelegatiResponse;
import it.csi.esenred.esenredweb.business.deleghebe.RicercaCittadini;
import it.csi.esenred.esenredweb.business.deleghebe.RicercaCittadiniResponse;
import it.csi.esenred.esenredweb.business.deleghebe.Richiedente;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.AuraIntegrationException;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.CittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.Diagnosi;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.LogUtil;

public class IntegrationClientImpl {

	// Logger log = Logger.getLogger("REGPESENRED.business");
	private String usernameGetEsenred;
	private String passwordGetEsenred;
	private String urlFind;
	private String urlGetEsenred;
	private String urlFindWSDL;
	private String urlGet;
	private String urlGetWSDL;
	private String urlInsert;
	private String urlInsertWSDL;
	private String urlRevoca;
	private String urlRevocaWSDL;
	private String urlInterrogaMefEsenredd;
	private String usernameInterrogaMefEsenredd;
	private String passwordInterrogaMefEsenredd;
	private String urlRevocaCertificata;
	private String usernameRevocaCertificata;
	private String passwordRevocaCertificata;
	private String username;
	private String password;
	private String usernamedeleghe;
	private String passworddeleghe;
	private String urlGetDeleghe;
	private Set<Cittadino> listCittadini;
	private Set<Cittadino> listCittadiniFind;
	private String irideBaseEndpoint;
	private String urlGetEsenzionePatologia;
	private String usernameGetEsenzionePatologia;
	private String passwordGetEsenzionePatologia;
	
	private String urlInfoCert;
	private String aliasInfoCert;
	private String pinInfoCert;
	
	private String firmaURL;
	
	private String gatewatFirmaCsiEndpoint;

	private boolean debug;

	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE, null);
	
	private static IntegrationClientImpl instance;

	private IntegrationClientImpl() {
	}

	public static IntegrationClientImpl getInstance() {
		if (null == instance) {
			instance = new IntegrationClientImpl();

			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("AURA_");
			HashMap<String, String> auraParameters = new HashMap<String, String>();
			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri p = (EsenredCParametri) iterator.next();
				auraParameters.put(p.getCodice(), p.getValore());
			}
			instance.setUsername(auraParameters.get("AURA_USERNAME"));
			instance.setPassword(auraParameters.get("AURA_PASSWORD"));
			instance.setUsernameGetEsenred(auraParameters.get("AURA_GETESENRED_USERNAME"));
			instance.setPasswordGetEsenred(auraParameters.get("AURA_GETESENRED_PASSWORD"));
			instance.setUrlFind(auraParameters.get("AURA_FIND"));
			instance.setUrlGetEsenred(auraParameters.get("AURA_GETESENRED"));
			instance.setUrlFindWSDL(auraParameters.get("AURA_FIND") + "?wsdl");
			instance.setUrlGet(auraParameters.get("AURA_GET"));
			instance.setUrlGetWSDL(auraParameters.get("AURA_GET") + "?wsdl");
			instance.setUrlInsert(auraParameters.get("AURA_INSERT"));
			instance.setUrlInsertWSDL(auraParameters.get("AURA_INSERT") + "?wsdl");
			instance.setUrlRevoca(auraParameters.get("AURA_REVOCA"));
			instance.setUrlRevocaWSDL(auraParameters.get("AURA_REVOCA") + "?wsdl");

			instance.setUrlInterrogaMefEsenredd(auraParameters.get("AURA_INTERROGA_MEF_ESENREDD"));
			instance.setUsernameInterrogaMefEsenredd(auraParameters.get("AURA_USERNAME_INTERROGA_MEF_ESENREDD"));
			instance.setPasswordInterrogaMefEsenredd(auraParameters.get("AURA_PASSWORD_INTERROGA_MEF_ESENREDD"));
			
			instance.setUrlRevocaCertificata(auraParameters.get("AURA_REVOCA_CERTIFICATA"));
			instance.setUsernameRevocaCertificata(auraParameters.get("AURA_USERNAME_REVOCA_CERTIFICATA"));
			instance.setPasswordRevocaCertificata(auraParameters.get("AURA_PASSWORD_REVOCA_CERTIFICATA"));
			elencoParametri = parametroIf.getParametri("DELEGHE");
			final HashMap<String, String> delegheParameters = new HashMap<String, String>();
			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri p = (EsenredCParametri) iterator.next();
				delegheParameters.put(p.getCodice(), p.getValore());
			}
			
			instance.setUsernamedeleghe(delegheParameters.get("DELEGHE_USERNAME"));
			instance.setPassworddeleghe(delegheParameters.get("DELEGHE_PASSWORD"));
			instance.setUrlGetDeleghe(delegheParameters.get("DELEGHE") + "?wsdl");
			
		
			java.net.Authenticator.setDefault(new java.net.Authenticator() {

				@Override
				protected java.net.PasswordAuthentication getPasswordAuthentication() {
					return new java.net.PasswordAuthentication(delegheParameters.get("DELEGHE_USERNAME"),
							delegheParameters.get("DELEGHE_PASSWORD").toCharArray());
				}
			});

			elencoParametri = parametroIf.getParametri("ESENZIONEPATOLOGIA");
			final HashMap<String, String> esenzionepatologiaParameters = new HashMap<String, String>();
			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri p = (EsenredCParametri) iterator.next();
				esenzionepatologiaParameters.put(p.getCodice(), p.getValore());
			}
			instance.setUsernameGetEsenzionePatologia(esenzionepatologiaParameters.get("ESENZIONEPATOLOGIA_USERNAME"));
			instance.setPasswordGetEsenzionePatologia(esenzionepatologiaParameters.get("ESENZIONEPATOLOGIA_PASSWORD"));
			instance.setUrlGetEsenzionePatologia(esenzionepatologiaParameters.get("ESENZIONEPATOLOGIA"));
			
			elencoParametri = parametroIf.getParametri("IRIDE_BASE_ENDPOINT");
			instance.setIrideBaseEndpoint(elencoParametri.get(0).getValore());
			
			List<EsenredCParametri> isDebug = parametroIf.getParametri("DEBUG");
			if (!isDebug.isEmpty())
				instance.setDebug("true".equalsIgnoreCase(isDebug.get(0).getValore()));


			//sezione Infocert per firma digitale
			elencoParametri = parametroIf.getParametri("INFOCERT");
			final HashMap<String, String> firmaParameters = new HashMap<String, String>();
			for (Iterator<EsenredCParametri> iterator = elencoParametri.iterator(); iterator.hasNext();) {
				EsenredCParametri p = (EsenredCParametri) iterator.next();
				firmaParameters.put(p.getCodice(), p.getValore());
			}
			instance.setFirmaURL(firmaParameters.get("INFOCERT_URL"));
			
			//sezione gateway firma digitale csi
			elencoParametri = parametroIf.getParametri("GATEWAY_FIRMA_CSI");
			instance.setGatewatFirmaCsiEndpoint(elencoParametri.get(0).getValore());

			instance.setListCittadini(new TreeSet<Cittadino>());
			instance.setListCittadiniFind(new TreeSet<Cittadino>());
		}

		return instance;
	}

	private void setUrlGetEsenred(String string) {
		this.urlGetEsenred = string;

	}

	public String getUrlInsertWSDL() {
		return urlInsertWSDL;
	}

	public String getIrideBaseEndpoint() {
		return irideBaseEndpoint;
	}

	public void setIrideBaseEndpoint(String irideBaseEndpoint) {
		this.irideBaseEndpoint = irideBaseEndpoint;
	}

	public void setUrlInsertWSDL(String urlInsertWSDL) {
		this.urlInsertWSDL = urlInsertWSDL;
	}

	public String getUrlInsert() {
		return urlInsert;
	}

	public void setUrlInsert(String urlInsert) {
		this.urlInsert = urlInsert;
	}

	public String getUrlRevoca() {
		return urlRevoca;
	}

	public void setUrlRevoca(String urlRevoca) {
		this.urlRevoca = urlRevoca;
	}

	public String getUrlRevocaWSDL() {
		return urlRevocaWSDL;
	}

	public void setUrlRevocaWSDL(String urlRevocaWSDL) {
		this.urlRevocaWSDL = urlRevocaWSDL;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Cittadino> getListCittadini() {
		return listCittadini;
	}

	public Set<Cittadino> getListCittadiniFind() {
		return listCittadiniFind;
	}

	public void setListCittadini(Set<Cittadino> listCittadini) {
		this.listCittadini = listCittadini;
	}

	public void setListCittadiniFind(Set<Cittadino> listCittadiniFind) {
		this.listCittadiniFind = listCittadiniFind;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlFind() {
		return this.urlFind;
	}

	public void setUrlFind(String urlFind) {
		this.urlFind = urlFind;
	}

	public String getUrlFindWSDL() {
		return urlFindWSDL;
	}

	public void setUrlFindWSDL(String urlFindWSDL) {
		this.urlFindWSDL = urlFindWSDL;
	}

	public String getUrlGet() {
		return urlGet;
	}

	public void setUrlGet(String urlGet) {
		this.urlGet = urlGet;
	}

	public String getUrlGetWSDL() {
		return urlGetWSDL;
	}

	public void setUrlGetWSDL(String urlGetWSDL) {
		this.urlGetWSDL = urlGetWSDL;
	}

	public String getUsernamedeleghe() {
		return usernamedeleghe;
	}

	public void setUsernamedeleghe(String usernamedeleghe) {
		this.usernamedeleghe = usernamedeleghe;
	}

	public String getPassworddeleghe() {
		return passworddeleghe;
	}

	public void setPassworddeleghe(String passworddeleghe) {
		this.passworddeleghe = passworddeleghe;
	}

	public String getUrlGetDeleghe() {
		return urlGetDeleghe;
	}

	public void setUrlGetDeleghe(String urlGetDeleghe) {
		this.urlGetDeleghe = urlGetDeleghe;
	}

	public String getUsernameInterrogaMefEsenredd() {
		return usernameInterrogaMefEsenredd;
	}

	public void setUsernameInterrogaMefEsenredd(String usernameInterrogaMefEsenredd) {
		this.usernameInterrogaMefEsenredd = usernameInterrogaMefEsenredd;
	}

	public String getPasswordInterrogaMefEsenredd() {
		return passwordInterrogaMefEsenredd;
	}

	public void setPasswordInterrogaMefEsenredd(String passwordInterrogaMefEsenredd) {
		this.passwordInterrogaMefEsenredd = passwordInterrogaMefEsenredd;
	}

	public String getUrlInterrogaMefEsenredd() {
		return urlInterrogaMefEsenredd;
	}

	public void setUrlInterrogaMefEsenredd(String urlInterrogaMefEsenredd) {
		this.urlInterrogaMefEsenredd = urlInterrogaMefEsenredd;
	}

	public String getUrlRevocaCertificata() {
		return urlRevocaCertificata;
	}

	public void setUrlRevocaCertificata(String urlRevocaCertificata) {
		this.urlRevocaCertificata = urlRevocaCertificata;
	}

	public String getUsernameRevocaCertificata() {
		return usernameRevocaCertificata;
	}

	public void setUsernameRevocaCertificata(String usernameRevocaCertificata) {
		this.usernameRevocaCertificata = usernameRevocaCertificata;
	}

	public String getPasswordRevocaCertificata() {
		return passwordRevocaCertificata;
	}

	public void setPasswordRevocaCertificata(String passwordRevocaCertificata) {
		this.passwordRevocaCertificata = passwordRevocaCertificata;
	}
	
	public String getUrlInfoCert() {
		return urlInfoCert;
	}
	
	public void setUrlInfoCert(String urlInfoCert) {
		this.urlInfoCert = urlInfoCert;
	}

	public String getAliasInfoCert() {
		return aliasInfoCert;
	}

	public void setAliasInfoCert(String aliasInfoCert) {
		this.aliasInfoCert = aliasInfoCert;
	}

	public String getPinInfoCert() {
		return pinInfoCert;
	}

	public void setPinInfoCert(String pinInfoCert) {
		this.pinInfoCert = pinInfoCert;
	}
	
	public String getFirmaURL() {
		return firmaURL;
	}

	public void setFirmaURL(String firmaURL) {
		this.firmaURL = firmaURL;
	}	

	private Cittadino soggettoToCittadinoEsenred(GetProfiloSanitarioNewResponse getProfiloSanitarioNewResponse) {
		Cittadino cResponse = null;
		if (getProfiloSanitarioNewResponse != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
						.getDatiPrimari() != null) {
			      //verifico lo stato del profilo
			if (!getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
						.getDatiPrimari().getStatoProfiloAnagrafico().equalsIgnoreCase("1")) {
				return cResponse;
			}
			else {
		if (getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza() != null) {
			DatiPrimari da = getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getDatiPrimari();
			cResponse = new Cittadino(da.getCodiceFiscale());
			cResponse.setIdAura(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getIdAura().toString());
			cResponse.setCognome(da.getCognome());
			cResponse.setNome(da.getNome());
			cResponse.setCodSesso(da.getSesso());
			cResponse.setDataNascita(Converter.getDataToString(da.getDataNascita()));
			cResponse.setCodComuneNascita(
					da.getCodComuneNascita() != null ? da.getCodComuneNascita() : da.getCodStatoNascita());
			cResponse.setIndirizzoResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getIndirizzo() + ", "
					+ getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getNumCivico());
			cResponse.setCapResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getCap());
			cResponse.setCittaResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getDescComune());
			cResponse.setComuneNascita(
					da.getDescComuneNascita() != null ? da.getDescComuneNascita() : da.getDescStatoNascita());
			cResponse.setProvinciaNascita(da.getSiglaProvNascita());
			cResponse.setStatoProfiloAnagrafico(da.getStatoProfiloAnagrafico());
			cResponse.setCodASL(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan().getAslDomicilio());
			cResponse.setDatascadenzaSSN(Converter.getDataToString(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan().getDataFineSSN()));
			this.listCittadini.add(cResponse);
	    	}
			}
		}
		return cResponse;
	}
	
  private CittadinoEsenpat soggettoToCittadinoEsenpat(GetProfiloSanitarioNewResponse getProfiloSanitarioNewResponse) {
    CittadinoEsenpat cResponse = null;

		if (getProfiloSanitarioNewResponse != null && getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody() != null && getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getDatiPrimari() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza() != null) {
			DatiPrimari da = getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getDatiPrimari();
      cResponse = new CittadinoEsenpat();
      cResponse.setCodFiscale(da.getCodiceFiscale());
			cResponse.setIdAura(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getIdAura().toString());
			cResponse.setCognome(da.getCognome());
			cResponse.setNome(da.getNome());
			cResponse.setCodSesso(da.getSesso());
			cResponse.setDataNascita(Converter.getDataToString(da.getDataNascita()));
			cResponse.setCodComuneNascita(
					da.getCodComuneNascita() != null ? da.getCodComuneNascita() : da.getCodStatoNascita());
			cResponse.setIndirizzoResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getIndirizzo() + ", "
					+ getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getNumCivico());
			cResponse.setCapResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getCap());
			cResponse.setCittaResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getDescComune());
			cResponse.setComuneNascita(
					da.getDescComuneNascita() != null ? da.getDescComuneNascita() : da.getDescStatoNascita());
			cResponse.setProvinciaNascita(da.getSiglaProvNascita());
			cResponse.setStatoProfiloAnagrafico(da.getStatoProfiloAnagrafico());
			cResponse.setCodASL(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan().getAslResidenza());
			cResponse.setDatascadenzaSSN(Converter.getDataToString(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan().getDataFineSSN()));
      cResponse.setEsenzioniAura(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni());
			cResponse.setCodStatoNascita(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getDatiPrimari().getCodStatoNascita());
      cResponse.setTelefonoResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getResidenza().getTelefono());
      cResponse.setDescStatoNascita(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag().getDatiPrimari().getDescStatoNascita());
			InfoSanitarie infoSan = getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoSan();
			if (infoSan != null && infoSan.getCodiceTesseraRegionale() != null
					&& infoSan.getCodiceTesseraRegionale().getCodiceTesseraRegionale_type0() != null)
				cResponse.setCodiceTesseraRegionale(
						infoSan.getCodiceTesseraRegionale().getCodiceTesseraRegionale_type0());
		}

		this.listCittadini.add(cResponse);
		return cResponse;
	}
	
	public Cittadino getCittadino(String idAura) {
		return soggettoToCittadinoEsenred(getCittadinoAura(idAura));	
	}

  public CittadinoEsenpat getCittadinoEsenpat(String idAura) {
		return soggettoToCittadinoEsenpat(getCittadinoAura(idAura));	
	}
	
	public GetProfiloSanitarioNewResponse getCittadinoAura(String idAura) {
		GetProfiloSanitarioNewResponse dati = null;
		Cittadino cResponse = null;			
		try {
				
			AnagrafeSanitariaNewStub stub = new AnagrafeSanitariaNewStub(this.getUrlGet());
			GetProfiloSanitarioNew req = new GetProfiloSanitarioNew();
			req.setAURAid(idAura);
			
			dati = stub.getProfiloSanitarioNew(req);
			
//			JAXBContext jaxbRes = JAXBContext.newInstance(GetProfiloSanitarioNewResponse.class);
//			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
//			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			jaxbMarshallerRes.marshal(dati, System.out);
//			
			ArrayList<InfoEsenzioneNew> nuovaLista = new ArrayList<InfoEsenzioneNew>();
			if (dati.getGetProfiloSanitarioNewResult() != null
					&& dati.getGetProfiloSanitarioNewResult().getBody() != null
					&& dati.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni() != null
					&& dati.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni().getInfoesenzione() != null) {
				for (InfoEsenzioneNew esenzione : dati.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni().getInfoesenzione()) {
					if (!esenzione.getCodEsenzione().startsWith("E")) {
						if (esenzione.getDataScadenza() != null) {
							if (esenzione.getDataSospensione() != null) {
								if (esenzione.getDataSospensione().getTime()
										.after(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
									nuovaLista.add(esenzione);
								}
							} else if (esenzione.getDataScadenza().getTime()
									.after(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
								nuovaLista.add(esenzione);
							}
						} else {
							nuovaLista.add(esenzione);
						}
					}
				}
				InfoEsenzioneNew[] finalLista = new InfoEsenzioneNew[nuovaLista.size()];
				for(int i = 0; i<nuovaLista.size();i++) {
					finalLista[i] = nuovaLista.get(i);
				}
								
				dati.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni().setInfoesenzione(finalLista);
				
			} else return dati;
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	return soggettoToCittadino(dati);
		return dati;

	}
	
	

	private GetEsenRedRes getEsenred(String idAura, String tipoUser) {
		GetEsenRed_Service service = null;
		GetEsenRedSoap port = null;

		try {
			service = new GetEsenRed_Service();
			port = service.getGetEsenRedSoap();

			BindingProvider prov = (BindingProvider) port;
			prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.urlGetEsenred);
			List<Handler> handlerChain = new ArrayList<Handler>();
			handlerChain.add(new WSSecurityHeaderSOAPHandler(this.usernameGetEsenred, this.passwordGetEsenred));
			prov.getBinding().setHandlerChain(handlerChain);

			if (tipoUser == null)
				tipoUser = "B"; // default

			GetEsenRedRes dati = port.getEsenRed(new Long(idAura), tipoUser);

			JAXBContext jaxbRes = JAXBContext.newInstance(GetEsenRedRes.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(dati, System.out);

			return dati;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public EsenzionePatologiaRes InserisciEsenzionePatologia(EsenzionePatologia filtroesenzione) {
		
		log.info("InserisciEsenzionePatologia"," BEGIN");
	
		EsenzionePatologia_Service service = null;
		EsenzionePatologiaSoap port = null;

		try {
			service = new EsenzionePatologia_Service();
			port = service.getEsenzionePatologiaSoap();

			BindingProvider prov = (BindingProvider) port;
			prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.urlGetEsenzionePatologia);
			List<Handler> handlerChain = new ArrayList<Handler>();
			handlerChain.add(new WSSecurityHeaderSOAPHandler(this.usernameGetEsenzionePatologia, this.passwordGetEsenzionePatologia));
			prov.getBinding().setHandlerChain(handlerChain);
			
			log.logXmlTypeObject(filtroesenzione, "Req. esenzionePatologia");
			
			EsenzionePatologiaRes dati = port.esenzionePatologia(filtroesenzione.getCodiceFiscaleChiamante(), filtroesenzione.getCodiceFiscaleAssistito(), 
					filtroesenzione.getCodiceEsenzione(), filtroesenzione.getDataInizioValidita(),
					filtroesenzione.getDataFineValidita(), filtroesenzione.getDataAnnullamento(), filtroesenzione.getListaDiagnosi());
	
			JAXBContext jaxbRes = JAXBContext.newInstance(EsenzionePatologiaRes.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(dati, System.out);
			
			log.logXmlTypeObject(dati, "Res. esenzionePatologia");
			
			return dati;
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			log.info("InserisciEsenzionePatologia"," END");
		}
		return null;
	}

	public List<Cittadino> findCittadino(Cittadino c) throws Exception {
		for (Cittadino sogg : this.listCittadiniFind) {
			try {
				if (sogg.equals(c)) {
					List<Cittadino> tmp = new ArrayList<Cittadino>();
					tmp.add(sogg);
					return tmp;
				}
			} catch (Exception e) {
			}
		}
		List<Cittadino> elencoCittadini = new ArrayList<Cittadino>();
	
		AnagrafeFindStub stub = new AnagrafeFindStub(this.getUrlFind());

		FindProfiliAnagrafici fpa = new FindProfiliAnagrafici();
		FindProfiliAnagraficiRequest fpar = new FindProfiliAnagraficiRequest();
		fpar.setCodiceFiscale(c.getCodFiscale());
		fpar.setCognome(c.getCognome());
		fpar.setNome(c.getNome());
		fpar.setDataNascita(c.getDataNascita());
		fpar.setFlagDecesso("0");
		fpar.setIdEnte("ESENRED");
		fpa.setProfiliRequest(fpar);

		FindProfiliAnagraficiResponse dati = null;

		try {
			
			JAXBContext jaxbRes = JAXBContext.newInstance(FindProfiliAnagrafici.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(fpa, System.out);
			
			dati = stub.findProfiliAnagrafici(fpa);
			
			jaxbRes = JAXBContext.newInstance(FindProfiliAnagraficiResponse.class);
			jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(dati, System.out);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		if (dati != null && dati.getFindProfiliAnagraficiResult() != null && dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili() != null) {
			Cittadino cResponse = null;
			if (dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili().getDatianagrafici().length == 1) {
				DatiAnagrafici da = dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili().getDatianagrafici()[0];
				cResponse = costruisciCittadino(da);
				elencoCittadini.add(cResponse);
			} else {
				for (DatiAnagrafici da : dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili().getDatianagrafici()) {
					Cittadino cittadinoTest = getCittadino(da.getIdProfiloAnagrafico().toString());
					if (cittadinoTest!=null) {
					if ("1".equals(cittadinoTest.getStatoProfiloAnagrafico())) {
						elencoCittadini.add(cittadinoTest);
						break;
					}
					}
				}
			}
		}
		return elencoCittadini;
	}

	private Cittadino costruisciCittadino(DatiAnagrafici da) {
		Cittadino cResponse = new Cittadino(da.getCodiceFiscale().getCodiceFiscale_type0());
		cResponse.setCognome(da.getCognome().getCognome_type0());
		cResponse.setNome(da.getNome());
		cResponse.setCodSesso(da.getSesso().getSesso_type0());
		cResponse.setDataNascita(Converter.getDataToString(da.getDataNascita()));
//		cResponse.setCodComuneNascita(da.getCodiceComuneNascita());
//		cResponse.setComuneNascita(da.getComuneNascita());
		cResponse.setCodComuneNascita(
				da.getCodiceComuneNascita() != null ? da.getCodiceComuneNascita() : da.getCodiceStatoNascita());
		cResponse.setComuneNascita(da.getComuneNascita() != null ? da.getComuneNascita() : da.getStatoNascita());
		cResponse.setProvinciaNascita(da.getProvinciaNascita());
		cResponse.setIdAura(da.getIdProfiloAnagrafico().toString());
		return cResponse;
	}

	public Response insertEsenzione(RequestBody body) {
		InsertAutocertEseRed_Service service = new InsertAutocertEseRed_Service();
		InsertAutocertEseRedSoap port = service.getInsertAutocertEseRedSoap();

		BindingProvider prov = (BindingProvider) port;
		
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getUrlInsert());
		
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(this.username, this.password));
		prov.getBinding().setHandlerChain(handlerChain);

		Request req = new Request();
		req.setBody(body);
		Response response = null;
		try {
			// if (this.isDebug()) {
			JAXBContext jaxbReq = JAXBContext.newInstance(RequestBody.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(req, System.out);
			// }

			response = port.insertAutocertEseRed(req);

			// if (this.isDebug()) {
			JAXBContext jaxbRes = JAXBContext.newInstance(Response.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, System.out);
			// }
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Response revocaEsenzione(
			it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody body) {
		RevocaAutocertEseRed_Service service = new RevocaAutocertEseRed_Service();
		RevocaAutocertEseRedSoap port = service.getRevocaAutocertEseRedSoap();

		BindingProvider prov = (BindingProvider) port;
		
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getUrlRevoca());
		
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(this.username, this.password));
		prov.getBinding().setHandlerChain(handlerChain);
		it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Request req = new it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Request();
		req.setBody(body);
		it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Response response = null;
		try {
			// if (this.isDebug()) {
			JAXBContext jaxbReq = JAXBContext
					.newInstance(it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RequestBody.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(req, System.out);
			// }

			response = port.revocaAutocertEseRed(req);

			// if (this.isDebug()) {
			JAXBContext jaxbRes = JAXBContext
					.newInstance(it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Response.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, System.out);
			// }
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String getUrlGetEsenred() {
		return urlGetEsenred;
	}

	public String getUsernameGetEsenred() {
		return usernameGetEsenred;
	}

	public void setUsernameGetEsenred(String usernameGetEsenred) {
		this.usernameGetEsenred = usernameGetEsenred;
	}

	public String getPasswordGetEsenred() {
		return passwordGetEsenred;
	}

	public void setPasswordGetEsenred(String passwordGetEsenred) {
		this.passwordGetEsenred = passwordGetEsenred;
	}

	public String getUrlGetEsenzionePatologia() {
		return urlGetEsenzionePatologia;
	}

	public void setUrlGetEsenzionePatologia(String urlGetEsenzionePatologia) {
		this.urlGetEsenzionePatologia = urlGetEsenzionePatologia;
	}

	public String getUsernameGetEsenzionePatologia() {
		return usernameGetEsenzionePatologia;
	}

	public void setUsernameGetEsenzionePatologia(String usernameGetEsenzionePatologia) {
		this.usernameGetEsenzionePatologia = usernameGetEsenzionePatologia;
	}

	public String getPasswordGetEsenzionePatologia() {
		return passwordGetEsenzionePatologia;
	}

	public void setPasswordGetEsenzionePatologia(String passwordGetEsenzionePatologia) {
		this.passwordGetEsenzionePatologia = passwordGetEsenzionePatologia;
	}

	public List<Long> getListaTitolari(String idAura) {
		Set<Long> ritorno = new TreeSet<Long>();
		List<Long> lista = new ArrayList<Long>();

		GetEsenRedRes dati = getEsenred(idAura, "B");

		for (InfoEsenzioneGetEsenredVO esenzione : dati.getBody().getInfoEsenzione()) {
			ritorno.add(esenzione.getIdAuraTitolare().longValue());
		}

		for (Long l : ritorno) {
			lista.add(l);
		}

		return lista;
	}

	public List<Long> getListaBeneficiari(String idAura) {
		Set<Long> ritorno = new TreeSet<Long>();
		List<Long> lista = new ArrayList<Long>();

		GetEsenRedRes dati = getEsenred(idAura, "T");

		for (InfoEsenzioneGetEsenredVO esenzione : dati.getBody().getInfoEsenzione()) {
			ritorno.add(esenzione.getIdAuraBeneficiario().longValue());
		}

		dati = getEsenred(idAura, "D");

		for (InfoEsenzioneGetEsenredVO esenzione : dati.getBody().getInfoEsenzione()) {
			ritorno.add(esenzione.getIdAuraBeneficiario().longValue());
		}

		for (Long l : ritorno) {
			lista.add(l);
		}

		return lista;
	}

	/**
	 * Effettua la chiamata a GetEsenred
	 * 
	 * @param Protocollo
	 * @param CodEsenzione
	 * @param data_inizio_validita
	 * @param idAura
	 * @param tipoUser
	 * @return
	 * @throws Exception
	 */
	public List<EsenredTEsenzioniReddito> getEsenzioni(String protocollo, String codEsenzione,
			String data_inizio_validita, String idAura, String tipoUser,String dovesono) throws Exception {
		List<EsenredTEsenzioniReddito> esenzione = new ArrayList<EsenredTEsenzioniReddito>();

		FiltriRicercaEsenzioniEsenred filtri = new FiltriRicercaEsenzioniEsenred();

		try {
			if (protocollo != null && !protocollo.equals("null"))
				filtri.setProtocollo(protocollo);
			if (codEsenzione != null)
				filtri.setCodEsenzione(codEsenzione);
			if (data_inizio_validita != null)
				filtri.setDataInizioValidita(data_inizio_validita);
			if (filtri.getIdAuraBeneficiario()==null && tipoUser.equals("B"))
				filtri.setIdAuraBeneficiario(idAura);
			
			esenzione = getEsenzioni(filtri, idAura, tipoUser,dovesono);

			return esenzione;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param filtri
	 * @param idAura
	 * @param tipoUser
	 * @return
	 * @throws Exception
	 */
	public List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioniEsenred filtri, String idAura,
			String tipoUser, String dovesono) throws Exception { 
		List<EsenredTEsenzioniReddito> esenzione = new ArrayList<EsenredTEsenzioniReddito>();

		// Chiamata a GetEsenred AURA
		GetEsenRedRes dati = getEsenred(idAura, tipoUser);

		// Cast to EsenredTEsenzioniReddito
		if (dati != null) { 
			for (InfoEsenzioneGetEsenredVO singleEs : dati.getBody().getInfoEsenzione()) {
				esenzione.add(getEsenredTEsenzioniRedditoByGetEsenred(dati.getBody(), singleEs));
			}
		}

		// Applico i filtri
		esenzione = filtraLista(esenzione, filtri,dovesono);

		return esenzione;
	}
	
	public List<EsenredTEsenzioniReddito> getEsenzioniValide(String idAuraBen) {
		List<EsenredTEsenzioniReddito> esenzioni = new ArrayList<EsenredTEsenzioniReddito>();

		try {
			for (EsenredTEsenzioniReddito single : getEsenzioni(null, null, null, idAuraBen, "B",null)) {
					try {
						
						if (single.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice())) {
//						if (single.getDataFine() != null) {
//							if (!single.getDataFine().before(Converter.getDateWithoutTime(new Date()))) {
								esenzioni.add(single);
							} 
//						} else {
//							esenzioni.add(single);
//						}
						
					//	esenzioni.add(single);
					} catch (Exception e) {		}
			}

		} catch (Exception e) {
			return new ArrayList<EsenredTEsenzioniReddito>();
		}

		return esenzioni;
	}
	
	/**
	 * Filtro la lista in input eliminando i protocolli gi rilevati
	 * 
	 * @param lista
	 * @param protocolli
	 * @return
	 */
	private List<EsenredTEsenzioniReddito> filtraProtocolli(List<EsenredTEsenzioniReddito> lista,
			List<String> protocolli) {
		List<EsenredTEsenzioniReddito> tmp = new ArrayList<EsenredTEsenzioniReddito>();
		for (EsenredTEsenzioniReddito esenzione : lista) {
			try {
				if (!protocolli.contains(esenzione.getNumProtocolloSogei().toString()))
					tmp.add(esenzione);
			}catch (Exception e) {
				tmp.add(esenzione);
			}
		}
		return tmp;
	}

	/**
	 * Effettuo la chiamata a getEsenred per recuperare la lista delle esenzioni
	 * associate a beneficiario/dichiarante/titolare in input
	 * 
	 * @param filtri
	 * @param protocollo
	 * @return
	 * @throws Exception
	 */
	public List<EsenredTEsenzioniReddito> getEsenzioniAcceleratore(FiltriRicercaEsenzioniEsenred filtri, String protocollo)
			throws Exception {
		List<EsenredTEsenzioniReddito> esenzioni = new ArrayList<EsenredTEsenzioniReddito>();
		Set<EsenredTEsenzioniReddito> tmp = new TreeSet<EsenredTEsenzioniReddito>();

		String[] protocolliOld;
		if (protocollo.contains(","))
			protocolliOld = protocollo.split(",");
		else {
			protocolliOld = new String[1];
			protocolliOld[0] = protocollo;
		}  
		if (filtri.getIdAuraBeneficiario() != null) {
			if(!filtri.getIdAuraBeneficiario().equals("")) {
				List<EsenredTEsenzioniReddito> tmpList = getEsenzioni(null, null, null, filtri.getIdAuraBeneficiario(), "B",null);
				List<EsenredTEsenzioniReddito> tmpFilter = filtraProtocolli(tmpList,Arrays.asList(protocolliOld));

				tmp.addAll(tmpFilter);
			}
		}			
		if (filtri.getIdAuraDichiarante() != null)
			if(!filtri.getIdAuraDichiarante().equals("")) tmp.addAll(filtraProtocolli(getEsenzioni(null, null, null, filtri.getIdAuraDichiarante(), "D",null),Arrays.asList(protocolliOld)));
		if (filtri.getIdAuraTitolare() != null)
			if(!filtri.getIdAuraTitolare().equals("")) tmp.addAll(filtraProtocolli(getEsenzioni(null, null, null, filtri.getIdAuraTitolare(), "T",null),Arrays.asList(protocolliOld)));

		esenzioni.addAll(tmp);

		List<EsenredTEsenzioniReddito> esenzioniRitorno = filtraLista(esenzioni, filtri,null);

		return esenzioniRitorno;
	}

	/**
	 * Filtro la lista in input
	 * 
	 * @param esenzioni
	 * @param filtri
	 * @return
	 */ 
	private List<EsenredTEsenzioniReddito> filtraLista(List<EsenredTEsenzioniReddito> esenzioni,
			FiltriRicercaEsenzioniEsenred filtri, String dovesono) {
		List<EsenredTEsenzioniReddito> newList = new ArrayList<EsenredTEsenzioniReddito>();
 
		try {
			for (EsenredTEsenzioniReddito esenzione : esenzioni) {
				if (dovesono==null) {
				if ( checkFiltro(filtri.getCodStatoEsenzione(), esenzione.getCodStato(), null)
						 && checkFiltro(Converter.getData(filtri.getDataInizioValidita()), esenzione.getDataInizio(), "dal")
						 && checkFiltro(Converter.getData(filtri.getDataFineValidita()), esenzione.getDataFine(), "al")
						 && checkFiltro(filtri.getCodEsenzione(), esenzione.getCodEsenzione(), null)
						 && checkFiltro(filtri.getIdAuraDichiarante(), esenzione.getIdCittadinoDichiarante().toString(), null)
						 && checkFiltro(filtri.getIdAuraBeneficiario(), esenzione.getIdCittadinoBeneficiario().toString(), null)
						 && checkFiltro(filtri.getIdAuraTitolare(), esenzione.getIdCittadinoTitolare().toString(), null)
						 && checkFiltro(filtri.getProtocollo(), esenzione.getNumProtocolloSogei().toString(), null)
						 && checkFiltro(filtri.getAslOperatore(), esenzione.getCodNazionaleAslRilascio(), null)
						 && checkFiltro(new Date(), esenzione.getDataFine(), ((filtri.getStorico() == null) ? "n/a" : filtri.getStorico()))
						)
					newList.add(esenzione); 
				}
				else if (dovesono.equalsIgnoreCase("STAMPAESENZIONE")){
					if ( checkFiltro(Converter.getData(filtri.getDataInizioValidita()), esenzione.getDataInizio(), "a")
							 && checkFiltro(filtri.getCodEsenzione(), esenzione.getCodEsenzione(), null)
							 && checkFiltro(filtri.getIdAuraBeneficiario(), esenzione.getIdCittadinoBeneficiario().toString(), null)
							)
						newList.add(esenzione);
					}
				else {
				if ( checkFiltro(filtri.getCodStatoEsenzione(), esenzione.getCodStato(), null)
						 && checkFiltro(Converter.getData(filtri.getDataInizioValidita()), esenzione.getDataInizio(), "a")
						 && checkFiltro(filtri.getCodEsenzione(), esenzione.getCodEsenzione(), null)
						 && checkFiltro(filtri.getIdAuraBeneficiario(), esenzione.getIdCittadinoBeneficiario().toString(), null)
						)
					newList.add(esenzione);
				}
			}
		} catch (Exception e) {
			return esenzioni;
		}
		return newList;
	}
	
	
	private boolean checkFiltro(Object filtro, Object elemento, String dettaglio) {
		try {
			if(filtro!=null && elemento!=null) {
				if(dettaglio != null) {
					Date filtroD = (Date) filtro;
					Date elementoD = (Date) elemento;
					if(dettaglio.equals("dal")) return filtroD.before(elementoD);
					else if(dettaglio.equals("al")) return filtroD.after(elementoD);
					else if(dettaglio.equals("a")) return filtroD.equals(elementoD);
					else if(dettaglio.equalsIgnoreCase("Si")) return filtroD.after(elementoD);
					else if(dettaglio.equalsIgnoreCase("No")) return filtroD.before(elementoD);
					else return true;
				}else { 
					if(filtro.equals("") || elemento.equals("")) return true;
					else return filtro.equals(elemento);
				}
				
			}
		} catch (Exception e) {
			return true;
		}
		return true;		
	}

	/**
	 * Restituisce le esenzioni revocate
	 * 
	 * @param idAuraBen
	 * @param codEsenzione
	 * @return
	 * @throws Exception
	 */
	public List<EsenredTEsenzioniReddito> getEsenzioniRevocate(String idAuraBen, String codEsenzione) {
		List<EsenredTEsenzioniReddito> esenzioni = new ArrayList<EsenredTEsenzioniReddito>();

		try {
			for (EsenredTEsenzioniReddito single : getEsenzioni(null, null, null, idAuraBen, "B",null)) {
				if (single.getCodEsenzione().equals(codEsenzione) && single.getDataRevoca()!=null)
					try {
						esenzioni.add(single);
					} catch (Exception e) {		}
			}

		} catch (Exception e) {
			return new ArrayList<EsenredTEsenzioniReddito>();
		}

		return esenzioni;
	}

	/**
	 * Cast da GetEsenred a Entity JPA
	 * 
	 * @param getEsenred
	 * @param info
	 * @return
	 * @throws Exception
	 */
	private EsenredTEsenzioniReddito getEsenredTEsenzioniRedditoByGetEsenred(GetEsenRedBody getEsenred,
			InfoEsenzioneGetEsenredVO info) throws Exception {
		EsenredTEsenzioniReddito response = null;
		try {
			response = new EsenredTEsenzioniReddito();

			TitoloDichiaranteIf titoloDichiaranteIf = (TitoloDichiaranteIf) SpringApplicationContext
					.getBean("titoloDichiarante");
			
			//response.setIdEsenzione(info.getid);
			response.setCodNazionaleAslRilascio(getEsenred.getInfoAnagDomicilio().getAslDomicilio());
			response.setCodEsenzione(info.getCodEsenzione()); 
			response.setAttestato(info.getCodAttestato());
			if (Converter.getDataAura(info.getDataSospensione()) == null
					&& (Converter.getDataAura(info.getDataScadenza()) != null
							&& Converter.getDataAura(info.getDataScadenza()).after(new Date())))
				response.setCodStato(StatoEsenzione.VALIDA.getCodice());
			else if (Converter.getDataAura(info.getDataSospensione()) != null
					&& Converter.getDataAura(info.getDataScadenza()).before(new Date()))
				response.setCodStato(StatoEsenzione.SCADUTA.getCodice());
			else if (Converter.getDataAura(info.getDataSospensione()) != null)
				response.setCodStato(StatoEsenzione.REVOCATA.getCodice());
			else
				response.setCodStato(StatoEsenzione.SCADUTA.getCodice());

			EsenzioneIf EsenzioneIf;
			EsenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
			List<EsenredDTipiEsenzioniReddito> elencoEsenzioni = EsenzioneIf.getEsenzioni(info.getCodEsenzione());
			response.setTipoEsenzione(elencoEsenzioni.get(0));

			response.setCodTitoloDichiarante(info.getTitoloDichiarante());
			response.setEsenredCTitoloDichiarante(titoloDichiaranteIf.getTitoli(info.getTitoloDichiarante()).get(0));

			response.setDataFine(Converter.getDataAura(info.getDataScadenza()));
			response.setDataRichiesta(Converter.getDataAura(info.getDataEmissione()));
			response.setDataInizio(Converter.getDataAura(info.getDataEmissione()));
			response.setDataRevoca(Converter.getDataAura(info.getDataSospensione()));

			response.setFlagConsenso(1);

			if (info.getIdAuraBeneficiario() != null)
				response.setIdCittadinoBeneficiario(info.getIdAuraBeneficiario().longValue());
			else
				response.setIdCittadinoBeneficiario(new Long(0));
			if (info.getIdAuraDichiarante() != null)
				response.setIdCittadinoDichiarante(info.getIdAuraDichiarante().longValue());
			else
			{
				response.setIdCittadinoDichiarante(new Long(0));
				response.setCfDichiarantefr(info.getCodiceFiscaleDichiarante());
			}
			if (info.getIdAuraTitolare() != null)
				response.setIdCittadinoTitolare(info.getIdAuraTitolare().longValue());
			else
			{
				response.setIdCittadinoTitolare(new Long(0));
				response.setCfTitolarefr(info.getCodiceFiscaleTitolare());
			}

			if (info.getProtocollo() == null)
				response.setIdUserInsert(new Long(99999997));
			else
				response.setIdUserInsert(new Long(99999999));

			response.setNota(info.getNote());

			if (info.getProtocollo() != null)
				response.setNumProtocolloSogei(new Long(info.getProtocollo()));
			else 
				response.setNumProtocolloSogei(new Long(0));
		} catch (Exception e) {
			throw new Exception("Errore durante la conversione: " + e.getMessage());
		}
		return response;

	}
	
	
	public Cittadino findCittadinoFuoriRegione(String codFiscale) throws Exception {
        InterrogaMefEsenredd_Service ss = new InterrogaMefEsenredd_Service();
        InterrogaMefEsenreddSoap port = ss.getInterrogaMefEsenreddSoap();  

		BindingProvider prov = (BindingProvider) port;
		
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getUrlInterrogaMefEsenredd());
		
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(this.usernameInterrogaMefEsenredd, this.passwordInterrogaMefEsenredd));
		prov.getBinding().setHandlerChain(handlerChain);
		
		InterrogaMefEsenreddRes dati = null;
		try {
//			JAXBContextBuilder context = new JAXBContextImpl.JAXBContextBuilder();
//			context.setImprovedXsiTypeHandling(false);
			
			dati = port.interrogaMefEsenredd(codFiscale);

			JAXBContext jaxbRes = JAXBContext.newInstance(InterrogaMefEsenreddRes.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(dati, System.out);
		} catch (WebServiceException ws) {
			//da commentare in rilascio
			ws.printStackTrace();
			throw ws;			
		} catch (Exception e) {
			//da commentare in rilascio
			e.printStackTrace();
			throw e;
		}
		Cittadino c = null;

		if (dati != null && dati.getBody() != null) {
			c = costruisciCittadinoFuoriRegione(dati.getBody());
		}
		return c;
	}
	
	private Cittadino costruisciCittadinoFuoriRegione(InterrogaMefEsenreddResponseBody interrogaMefEsenreddResponseBody) {
		Cittadino cResponse = new Cittadino(interrogaMefEsenreddResponseBody.getCodicefiscale());
		cResponse.setCognome(interrogaMefEsenreddResponseBody.getCognome());
		cResponse.setNome(interrogaMefEsenreddResponseBody.getNome());
		cResponse.setCodSesso(interrogaMefEsenreddResponseBody.getSesso());
		cResponse.setDataNascita(interrogaMefEsenreddResponseBody.getDatanascita());
		cResponse.setCodComuneNascita(interrogaMefEsenreddResponseBody.getIdComunenascita() != null ? interrogaMefEsenreddResponseBody.getIdComunenascita() : interrogaMefEsenreddResponseBody.getIdStatonascita());
		cResponse.setCodComuneNascita(interrogaMefEsenreddResponseBody.getComunenasc() != null ? interrogaMefEsenreddResponseBody.getComunenasc() : interrogaMefEsenreddResponseBody.getNazionenasc());
		cResponse.setProvinciaNascita(interrogaMefEsenreddResponseBody.getProvincianasc());
//		cResponse.setIdAura(interrogaMefEsenreddResponseBody.getIdProfiloAnagrafico().toString()); //N.A.
		return cResponse;
	}
	
	public ChiusuraEsenRedRes revocaEsenzioneCertificata(RevocaEsenzioneCertificataBO r) throws Exception {
        ChiusuraEsenRed_Service ss = new ChiusuraEsenRed_Service();
        ChiusuraEsenRedSoap port = ss.getChiusuraEsenRedSoap();  
        
        BindingProvider prov = (BindingProvider) port;
		
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getUrlRevocaCertificata());
		
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(this.usernameRevocaCertificata, this.passwordRevocaCertificata));
		prov.getBinding().setHandlerChain(handlerChain);
		
		ChiusuraEsenRedRes dati = null;
		try {

			dati = port.chiusuraEsenRed(r.getCodiceFiscaleChiamante(), r.getCodiceEsenzione(), r.getDataInizioValidita(), r.getCodiceFiscaleAssistito());

			JAXBContext jaxbRes = JAXBContext.newInstance(ChiusuraEsenRedRes.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(dati, System.out);
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dati;
	}

	public List<it.csi.esenred.esenredweb.business.deleghebe.Cittadino> chiamaRicercaCittadiniService(String codicefiscale) throws Exception {

	    String endpoint = this.getUrlGetDeleghe();
	    DelegheCittadiniService_Service service = new DelegheCittadiniService_Service();
	    DelegheCittadiniService port = service.getDelegheCittadiniServiceImplPort();

	    BindingProvider prov = (BindingProvider) port;

	    prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

	    prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, this.usernamedeleghe);
	    prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, this.passworddeleghe);


	    RicercaCittadini ricercaCittadino = new RicercaCittadini();
	    List<it.csi.esenred.esenredweb.business.deleghebe.Cittadino> elencoCittadini = new ArrayList<it.csi.esenred.esenredweb.business.deleghebe.Cittadino>();

	    // --------------Dati Ricerca---------------

	    Richiedente richiedente = new Richiedente();
	    ApplicazioneRichiedente applicazionerichiedente = new ApplicazioneRichiedente();
	    applicazionerichiedente.setCodice("ESENPAT");
	    applicazionerichiedente.setIdRequest("11111111");
	    richiedente.setServizio(applicazionerichiedente);
	    richiedente.setCodiceFiscale(codicefiscale);
	    it.csi.esenred.esenredweb.business.deleghebe.Cittadino cittadino = new it.csi.esenred.esenredweb.business.deleghebe.Cittadino();
	    cittadino.setCodiceFiscale(codicefiscale);
	    ricercaCittadino.setRichiedente(richiedente);
	    ricercaCittadino.setCittadino(cittadino);

	    //
	    RicercaCittadiniResponse dati = null;

	    try {
	      dati = port.ricercaCittadiniService(ricercaCittadino);
	      JAXBContext jaxReq = JAXBContext.newInstance(GetDelegatiResponse.class);
	      Marshaller jaxM = jaxReq.createMarshaller();
	      jaxM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	      jaxM.marshal(dati, System.out);

	    } catch (WebServiceException ws) {
	      ws.printStackTrace();
	      throw ws;
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw e;
	    }

	    if (dati.getCittadini() != null) {
	      for (it.csi.esenred.esenredweb.business.deleghebe.Cittadino cittadinoDel : dati.getCittadini().getCittadino()) {
	          elencoCittadini.add(cittadinoDel);
	        }
	    } else {
	      return null;
	    }
	    return elencoCittadini;
	  }

	public List<Ruolo> findRuoli(Identita identita, Application application) throws Exception {
		try {
			PolicyEnforcerBaseSoapBindingStub stub = new PolicyEnforcerBaseSoapBindingStub();
			stub._setProperty(stub.ENDPOINT_ADDRESS_PROPERTY, this.getIrideBaseEndpoint());
			
			JAXBContext jaxbRes = JAXBContext.newInstance(Identita.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(identita, System.out);
			
			Ruolo[] ruoli = stub.findRuoliForPersonaInApplication(identita, application);
			List<Ruolo> ruoliOut = new ArrayList<Ruolo>();
			
			for(Ruolo r : ruoli) {
				ruoliOut.add(r);
			}
		
			return ruoliOut;
			
		} catch (AxisFault e) {
			e.printStackTrace();
//			throw new Exception("Ruoli non trovati");
			ArrayList<Ruolo> ruoli = new ArrayList<Ruolo>();
			Ruolo r = new Ruolo();
			r.setCodiceRuolo(Constants.AMMINISTRATORE);
			ruoli.add(r);
			return ruoli;
		}		
	}
	
	
	public FileResult gatewayFirmaPades(Attachment attachment, PadesInput padesInput, SignIdentity identity) throws Exception {
		try {
			GateFireSrvcSoapBindingStub stub = new GateFireSrvcSoapBindingStub();
			stub._setProperty(stub.ENDPOINT_ADDRESS_PROPERTY, this.getGatewatFirmaCsiEndpoint());
			
			JAXBContext jaxbRes = JAXBContext.newInstance(SignIdentity.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(identity, System.out);
			
			FileResult result = stub.firmaPAdES(attachment, padesInput, identity);

			return result;
			
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public void gatewayFirmaPadesRemota(Attachment attachment, PadesInput padesInput, SignIdentity identity) throws Exception {
		try {
			GateFireSrvcSoapBindingStub stub = new GateFireSrvcSoapBindingStub();
			stub._setProperty(stub.ENDPOINT_ADDRESS_PROPERTY, this.getGatewatFirmaCsiEndpoint());
			
			JAXBContext jaxbRes = JAXBContext.newInstance(SignIdentity.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(identity, System.out);
			
			FileResult result = stub.firmaPAdESRemota(attachment, padesInput, identity);
			List<Ruolo> ruoliOut = new ArrayList<Ruolo>();
			
			
		} catch (AxisFault e) {
			e.printStackTrace();
		}	
	}
	
	public void gatewayFirmaPadesAutomatica(Attachment attachment, PadesInput padesInput, AutoSignIdentity identity) throws Exception {
		try {
			GateFireSrvcSoapBindingStub stub = new GateFireSrvcSoapBindingStub();
			stub._setProperty(stub.ENDPOINT_ADDRESS_PROPERTY, this.getGatewatFirmaCsiEndpoint());
			
			JAXBContext jaxbRes = JAXBContext.newInstance(Identita.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(identity, System.out);
			
			FileResult result = stub.firmaPAdESAutomatica(attachment, padesInput, identity);
			List<Ruolo> ruoliOut = new ArrayList<Ruolo>();
			
			
		} catch (AxisFault e) {
			e.printStackTrace();
		}	
	}
	
	public OtpResult gatewayRichiestaOtp(OtpReqInput otpReqInput) throws Exception {
		try {
			GateFireSrvcSoapBindingStub stub = new GateFireSrvcSoapBindingStub();
			stub._setProperty(stub.ENDPOINT_ADDRESS_PROPERTY, this.getGatewatFirmaCsiEndpoint());
			
			JAXBContext jaxbRes = JAXBContext.newInstance(OtpReqInput.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(otpReqInput, System.out);
			
			OtpResult result = stub.richiestaOtp(otpReqInput);

			
			return result;
			
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return null;	
	}

	public String getGatewatFirmaCsiEndpoint() {
		return gatewatFirmaCsiEndpoint;
	}

	public void setGatewatFirmaCsiEndpoint(String gatewatFirmaCsiEndpoint) {
		this.gatewatFirmaCsiEndpoint = gatewatFirmaCsiEndpoint;
	}
	
	
	
}