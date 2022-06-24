/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;

import org.springframework.beans.factory.annotation.Autowired;

//import it.csi.deleghe.deleghebe.ws.DelegheCittadiniService;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedRes;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRedSoap;
import it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata.ChiusuraEsenRed_Service;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologia;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaRes;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaSoap;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologia_Service;
import it.csi.esenred.esenredweb.business.aura.find.AnagrafeFindStub;
import it.csi.esenred.esenredweb.business.aura.find.DatiAnagrafici;
import it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici;
import it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiRequest;
import it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse;
import it.csi.esenred.esenredweb.business.aura.get.AnagrafeSanitariaNewStub;
import it.csi.esenred.esenredweb.business.aura.get.DatiPrimari;
import it.csi.esenred.esenredweb.business.aura.get.GetProfiloSanitarioNew;
import it.csi.esenred.esenredweb.business.aura.get.GetProfiloSanitarioNewResponse;
import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.aura.get.InfoSanitarie;
import it.csi.esenred.esenredweb.business.aura.get.WSSecurityHeaderSOAPHandler;
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
import it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RevocaAutocertEseRedSoap;
import it.csi.esenred.esenredweb.business.aura.revocaautocertesered.RevocaAutocertEseRed_Service;
import it.csi.esenred.esenredweb.business.bo.RevocaEsenzioneCertificataBO;
import it.csi.esenred.esenredweb.business.deleghebe.ApplicazioneRichiedente;
import it.csi.esenred.esenredweb.business.deleghebe.DelegaCittadino;
import it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService;
import it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService_Service;
import it.csi.esenred.esenredweb.business.deleghebe.GetDeleganti;
import it.csi.esenred.esenredweb.business.deleghebe.GetDelegantiResponse;
import it.csi.esenred.esenredweb.business.deleghebe.GetDelegati;
import it.csi.esenred.esenredweb.business.deleghebe.GetDelegatiResponse;
import it.csi.esenred.esenredweb.business.deleghebe.RicercaCittadini;
import it.csi.esenred.esenredweb.business.deleghebe.RicercaCittadiniResponse;
import it.csi.esenred.esenredweb.business.deleghebe.Richiedente;
import it.csi.esenred.esenredweb.business.deleghebe.GetDeleganti.CodiciServizio;
import it.csi.esenred.esenredweb.business.deleghebe.GetDeleganti.StatiDelega;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.Filtri;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniAcceleratore;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.LogUtil;

public class IntegrationClientImpl {

	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENRED,null);

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
	private String usernameGetEsenzionePatologia;
	private String passwordGetEsenzionePatologia;
	private String urlGetEsenzionePatologia;
	

	
	DelegheCittadiniService service;
	
	private boolean debug;

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

			List<EsenredCParametri> isDebug = parametroIf.getParametri("DEBUG");
			if (!isDebug.isEmpty())
				instance.setDebug("true".equalsIgnoreCase(isDebug.get(0).getValore()));

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

	public Set<Cittadino> getListCittadiniFind() {
		return listCittadiniFind;
	}

	public void setListCittadiniFind(Set<Cittadino> listCittadiniFind) {
		this.listCittadiniFind = listCittadiniFind;
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

	public Set<Cittadino> getListCittadini() {
		return listCittadini;
	}

	public void setListCittadini(Set<Cittadino> listCittadini) {
		this.listCittadini = listCittadini;
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

	public String getUrlGetEsenzionePatologia() {
		return urlGetEsenzionePatologia;
	}

	public void setUrlGetEsenzionePatologia(String urlGetEsenzionePatologia) {
		this.urlGetEsenzionePatologia = urlGetEsenzionePatologia;
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
			
			JAXBContext jaxbReq = JAXBContext.newInstance(EsenzionePatologia.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(filtroesenzione, System.out);
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

	private Cittadino soggettoToCittadinoEsenred(GetProfiloSanitarioNewResponse getProfiloSanitarioNewResponse) {
		Cittadino cResponse = null;

		if (getProfiloSanitarioNewResponse != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
						.getDatiPrimari() != null) {
			DatiPrimari da = getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
					.getDatiPrimari();
			cResponse = new Cittadino(da.getCodiceFiscale());
			cResponse.setIdAura(
					getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getIdAura().toString());
			cResponse.setCognome(da.getCognome());
			cResponse.setNome(da.getNome());
			cResponse.setCodSesso(da.getSesso());
			cResponse.setDataNascita(Converter.getDataToString(da.getDataNascita()));
			cResponse.setCodComuneNascita(
					da.getCodComuneNascita() != null ? da.getCodComuneNascita() : da.getCodStatoNascita());
			cResponse.setComuneNascita(
					da.getDescComuneNascita() != null ? da.getDescComuneNascita() : da.getDescStatoNascita());
			cResponse.setProvinciaNascita(da.getSiglaProvNascita());
			cResponse.setStatoProfiloAnagrafico(da.getStatoProfiloAnagrafico());
		   if (getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
						.getResidenza() != null) {
			cResponse.setIndirizzoResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoAnag().getResidenza().getIndirizzo() + ", "
					+ getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
							.getResidenza().getNumCivico());
			cResponse.setCapResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoAnag().getResidenza().getCap());
			cResponse.setCittaResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoAnag().getResidenza().getDescComune());
		   }
			if (getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan() != null) {
			cResponse.setCodASL(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan()
					.getAslDomicilio());
			cResponse.setDatascadenzaSSN(Converter.getDataToString(getProfiloSanitarioNewResponse
					.getGetProfiloSanitarioNewResult().getBody().getInfoSan().getDataFineSSN()));
			 }
			System.out.println("[EsenzioneRedditoAutocertificazioneService::soggettoToCittadinoEsenred (AuRA)] " + cResponse.getIdAura()!=null ? cResponse.getIdAura() : "NULL");
			System.out.println("[EsenzioneRedditoAutocertificazioneService::soggettoToCittadinoEsenred (AuRA)] " + cResponse.getCodASL()!=null ? cResponse.getCodASL() : "NULL");
			
			this.listCittadini.add(cResponse);
		   }
		return cResponse;
	}

	private Cittadino soggettoToCittadinoEsenpat(GetProfiloSanitarioNewResponse getProfiloSanitarioNewResponse) {
		Cittadino cResponse = null;

		if (getProfiloSanitarioNewResponse != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
						.getDatiPrimari() != null
				&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
						.getResidenza() != null) {
			DatiPrimari da = getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
					.getDatiPrimari();
			cResponse = new Cittadino();
			cResponse.setCodFiscale(da.getCodiceFiscale());
			cResponse.setIdAura(
					getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getIdAura().toString());
			cResponse.setCognome(da.getCognome());
			cResponse.setNome(da.getNome());
			cResponse.setCodSesso(da.getSesso());
			cResponse.setDataNascita(Converter.getDataToString(da.getDataNascita()));
			cResponse.setCodComuneNascita(
					da.getCodComuneNascita() != null ? da.getCodComuneNascita() : da.getCodStatoNascita());
			cResponse.setIndirizzoResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoAnag().getResidenza().getIndirizzo() + ", "
					+ getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoAnag()
							.getResidenza().getNumCivico());
			cResponse.setCapResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoAnag().getResidenza().getCap());
			cResponse.setCittaResidenza(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoAnag().getResidenza().getDescComune());
			cResponse.setComuneNascita(
					da.getDescComuneNascita() != null ? da.getDescComuneNascita() : da.getDescStatoNascita());
			cResponse.setProvinciaNascita(da.getSiglaProvNascita());
			cResponse.setStatoProfiloAnagrafico(da.getStatoProfiloAnagrafico());
			cResponse.setCodASL(getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoSan()
					.getAslResidenza());
			cResponse.setDatascadenzaSSN(Converter.getDataToString(getProfiloSanitarioNewResponse
					.getGetProfiloSanitarioNewResult().getBody().getInfoSan().getDataFineSSN()));
			 
			ArrayList<InfoEsenzioneNew> esenzioniValide = new ArrayList<InfoEsenzioneNew>();
			ArrayList<InfoEsenzioneNew> esenzioniScadute = new ArrayList<InfoEsenzioneNew>();
			if (getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult() != null
					&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody() != null
					&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni() != null
					&& getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni().getInfoesenzione() != null) {
				for (InfoEsenzioneNew esenzione : getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni()
						.getInfoesenzione()) {
					if (esenzione.getDataScadenza() != null) {
						if (!esenzione.getDataScadenza().getTime().before(Converter.getDateWithoutTime(new Date()))) {
							esenzioniValide.add(esenzione);
						} else {
							esenzioniScadute.add(esenzione);
						}
					} else {
						esenzioniValide.add(esenzione);
					}
					
				}
			}
			cResponse.setEsenzioniAuraValide(esenzioniValide);
			cResponse.setEsenzioniAuraScadute(esenzioniScadute);
			
			InfoSanitarie infoSan = getProfiloSanitarioNewResponse.getGetProfiloSanitarioNewResult().getBody()
					.getInfoSan();
			if (infoSan != null && infoSan.getCodDistrettoDomicilio() != null
					&& infoSan.getCodDistrettoDomicilio().getCodDistrettoDomicilio_type0() != null)
				cResponse.setCodDistrettoSocioSanitario(
						infoSan.getCodDistrettoDomicilio().getCodDistrettoDomicilio_type0());
			if (infoSan != null && infoSan.getCodiceTesseraRegionale() != null
					&& infoSan.getCodiceTesseraRegionale().getCodiceTesseraRegionale_type0() != null)
				cResponse.setCodiceTesseraRegionale(
						infoSan.getCodiceTesseraRegionale().getCodiceTesseraRegionale_type0());
		}

		
		return cResponse;
	}
	
	public Cittadino getCittadino(String idAura) {
		return soggettoToCittadinoEsenred(getCittadinoAura(idAura));
	}

	public Cittadino getCittadinoEsenpat(String idAura) {
		return soggettoToCittadinoEsenpat(getCittadinoAura(idAura));
	}
	
	public GetProfiloSanitarioNewResponse getCittadinoAura(String idAura) {
		GetProfiloSanitarioNewResponse dati = null;
//		Cittadino cResponse = null;
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
				for (InfoEsenzioneNew esenzione : dati.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni()
						.getInfoesenzione()) {
					if (!esenzione.getCodEsenzione().startsWith("E")) {
//						if (esenzione.getDataScadenza() != null) {
//							if (esenzione.getDataSospensione() != null) {
//								if (esenzione.getDataSospensione().getTime()
//										.after(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
//									nuovaLista.add(esenzione);
//								}
//							} else if (esenzione.getDataScadenza().getTime()
//									.after(Converter.getData(new Timestamp(System.currentTimeMillis())))) {
//								nuovaLista.add(esenzione);
//							}
//						} else {
							nuovaLista.add(esenzione);
//						}
					}
				}
				InfoEsenzioneNew[] finalLista = new InfoEsenzioneNew[nuovaLista.size()];
				for (int i = 0; i < nuovaLista.size(); i++) {
					finalLista[i] = nuovaLista.get(i);
				}

				dati.getGetProfiloSanitarioNewResult().getBody().getInfoEsenzioni().setInfoesenzione(finalLista);

			} else
				return dati;
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return dati;

	}


	/**
	 * Chiamata a getEsenred
	 * 
	 * @param idAura
	 * @param tipoUser
	 * @return
	 */
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

	public List<String> chiamaGetDeleganti(String codicefiscale) throws Exception {
		String methodName = "chiamaGetDeleganti";
		log.info(methodName, "BEGIN");
		GetDelegantiResponse dati = null;
		List<String> elencoDeleganti = new ArrayList<String>();

		try {
			String endpoint = this.getUrlGetDeleghe();
			
			log.debug(methodName, "endpoint:" + endpoint !=null ? endpoint : "endpoint deleghe nullo");
			
			DelegheCittadiniService_Service service = new DelegheCittadiniService_Service();
			it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService port = service.getDelegheCittadiniServiceImplPort();
			BindingProvider prov = (BindingProvider) port;
			
			prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
			
			prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, this.usernamedeleghe);
			prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, this.passworddeleghe);
	
			
	
			GetDeleganti cRequest = new GetDeleganti();
			
	
			// --------------Dati Delega---------------
	
			it.csi.esenred.esenredweb.business.deleghebe.Cittadino cittadinodelegante = new it.csi.esenred.esenredweb.business.deleghebe.Cittadino();
			Richiedente richiedente = new Richiedente();
			ApplicazioneRichiedente applicazionerichiedente = new ApplicazioneRichiedente();
			CodiciServizio codiciservizio = new CodiciServizio();
			StatiDelega statidelega = new StatiDelega();
			applicazionerichiedente.setCodice("ESENRED");
			applicazionerichiedente.setIdRequest("1");
			richiedente.setServizio(applicazionerichiedente);
			richiedente.setCodiceFiscale(codicefiscale);
			cittadinodelegante.setCodiceFiscale(codicefiscale);
			cRequest.setRichiedente(richiedente);
			cRequest.setCittadinoDelegato(cittadinodelegante);
			codiciservizio.getCodiceServizio().add(0, "ESENRED");
			statidelega.getStatoDelega().add(0, "ATTIVA");
			cRequest.setCodiciServizio(codiciservizio);
			cRequest.setStatiDelega(statidelega);

		


			JAXBContext jaxReq = JAXBContext.newInstance(GetDeleganti.class);
			Marshaller jaxM = jaxReq.createMarshaller();
			jaxM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxM.marshal(cRequest, System.out);
			
			log.logXmlTypeObject(cRequest, "Service Request");
			
			dati = port.getDelegantiService(cRequest);
			
			jaxReq = JAXBContext.newInstance(GetDelegantiResponse.class);
			jaxM = jaxReq.createMarshaller();
			jaxM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxM.marshal(dati, System.out);
			
			log.logXmlTypeObject(cRequest, "Service Response");

		} catch (WebServiceException ws) {
			log.error(methodName, " WebServiceException : " + ws.getMessage());
			ws.printStackTrace();
			//RM 07-04-2021: per ora commento perche deleghe va in unmasrshall
			//throw ws;
			return null;
		} catch (Exception e) {
			log.error(methodName, " Exception : " + e.getMessage());
			e.printStackTrace();
			//RM 07-04-2021: per ora commento perche deleghe va in unmasrshall
			//throw e;
			return null;
		}catch (Throwable te) {
			log.error(methodName, " Throwable : " + te.getMessage());
			te.printStackTrace();
			//RM 07-04-2021: per ora commento perche deleghe va in unmasrshall
			//throw te;
			return null;
		}

		// if (response.getEsito().equalsIgnoreCase("SUCCESSO")) {
		if (dati.getDeleganti() != null) {
			for (DelegaCittadino delega : dati.getDeleganti().getDelegante()) {
				if (Converter.CalcolaEta(delega.getDataDiNascita()) <= 18) {
					
					log.debug(methodName, "CF Delegante:  " + delega.getCodiceFiscale());
					elencoDeleganti.add(delega.getCodiceFiscale());
				}
			}
			// }
		} else {
			return null;
		}
		log.info(methodName, "END");
		return elencoDeleganti;
	}

	public List<Cittadino> findCittadino(Cittadino c) throws Exception {
		String methodName = "findCittadino";
		log.info(methodName, "BEGIN " );

		for (Cittadino sogg : this.listCittadiniFind) {
			try {
				if (sogg.equals(c)) {
					List<Cittadino> tmp = new ArrayList<Cittadino>();
					log.info(methodName, "Trovato cittadino in cache, non chiamo aura " );
					tmp.add(sogg);
					return tmp;
				}
			} catch (Exception e) {
			}
		}
		List<Cittadino> elencoCittadini = new ArrayList<Cittadino>();

		AnagrafeFindStub stub = new AnagrafeFindStub(this.getUrlFind());

		log.info(methodName, "Chiamo aura per verifica cittadino:"+ c.getCodFiscale() + ", flagDecesso 0, idEnte ESENRED");
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
			log.logXmlTypeObject(fpa, " Aura Service Request");
			dati = stub.findProfiliAnagrafici(fpa);

			jaxbRes = JAXBContext.newInstance(FindProfiliAnagraficiResponse.class);
			jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(dati, System.out);

			log.logXmlTypeObject(dati, " Aura Service Response");
		} catch (Exception e) {
			log.error(methodName, " Exception: ", e.getMessage());
			e.printStackTrace();
			throw e;
		}

		if (dati != null && dati.getFindProfiliAnagraficiResult() != null
				&& dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili() != null) {
			Cittadino cResponse = null;
			if (dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili().getDatianagrafici().length == 1) {
				DatiAnagrafici da = dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili()
						.getDatianagrafici()[0];
				cResponse = costruisciCittadino(da);
				log.info(methodName, "Cittadino trovato con IDAURA: " + cResponse.getIdAura());
				elencoCittadini.add(cResponse);
			} else {
				for (DatiAnagrafici da : dati.getFindProfiliAnagraficiResult().getBody().getElencoProfili()
						.getDatianagrafici()) {
					Cittadino cittadinoTest = getCittadino(da.getIdProfiloAnagrafico().toString());
					if ("1".equals(cittadinoTest.getStatoProfiloAnagrafico())) {
						log.info(methodName, "Cittadino trovato con IDAURA: " + cittadinoTest.getIdAura());
						elencoCittadini.add(cittadinoTest);
						break;
					}
				}
			}
			
			
		}
		log.info(methodName, "END" );
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

		String methodName = "revocaEsenzione";
		log.info(methodName, "BEGIN");
		
		BindingProvider prov = (BindingProvider) port;
		
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getUrlRevoca());
		log.info(methodName, "ENDPOINT ADDRESS REVCA SERVICE: " + this.getUrlRevoca());
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(this.username, this.password));
		prov.getBinding().setHandlerChain(handlerChain);
		it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Request req = new it.csi.esenred.esenredweb.business.aura.revocaautocertesered.Request();
		req.setBody(body);
		log.logXmlTypeObject(req, methodName + " Aura RevocaAutocertEsered Request");
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
			log.logXmlTypeObject(response, methodName + " Aura RevocaAutocertEsered Response");
			// }
		} catch (WebServiceException ws) {
			log.error(methodName, "WebServiceException: " +ws.getMessage());
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			log.error(methodName, "Exception: " +e.getMessage());
			e.printStackTrace();
		}
		log.info(methodName, "END");
		return response;
	}

	//non usata
//	public List<DelegaCittadino> chiamaGetDelegati(String codicefiscale) throws Exception {
//
//		String endpoint = this.getUrlGetDeleghe();
//		DelegheCittadiniService_Service service = new DelegheCittadiniService_Service();
//		it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService port = service.getDelegheCittadiniServiceImplPort();
//
//		BindingProvider prov = (BindingProvider) port;
//
//		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
//
//		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, this.usernamedeleghe);
//		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, this.passworddeleghe);
//
//		GetDelegati cRequest = new GetDelegati();
//		List<DelegaCittadino> elencoDelegati = new ArrayList<DelegaCittadino>();
//
//		// --------------Dati Delega---------------
//
//		it.csi.esenred.esenredweb.business.deleghebe.Cittadino cittadinodelegante = new it.csi.esenred.esenredweb.business.deleghebe.Cittadino();
//		Richiedente richiedente = new Richiedente();
//		ApplicazioneRichiedente applicazionerichiedente = new ApplicazioneRichiedente();
//		it.csi.esenred.esenredweb.business.deleghebe.GetDelegati.CodiciServizio codiciservizio = new it.csi.esenred.esenredweb.business.deleghebe.GetDelegati.CodiciServizio();
//		it.csi.esenred.esenredweb.business.deleghebe.GetDelegati.StatiDelega statidelega = new it.csi.esenred.esenredweb.business.deleghebe.GetDelegati.StatiDelega();
//		applicazionerichiedente.setCodice("ESENPAT");
//		applicazionerichiedente.setIdRequest("11111111");
//		richiedente.setServizio(applicazionerichiedente);
//		richiedente.setCodiceFiscale(codicefiscale);
//		cittadinodelegante.setCodiceFiscale(codicefiscale);
//		cRequest.setRichiedente(richiedente);
//		cRequest.setCittadinoDelegante(cittadinodelegante);
//		codiciservizio.getCodiceServizio().add(0, "ESENPAT");
//		statidelega.getStatoDelega().add(0, "ATTIVA");
//		cRequest.setCodiciServizio(codiciservizio);
//		cRequest.setStatiDelega(statidelega);
//
//		GetDelegatiResponse dati = null;
//
//		try {
//			dati = port.getDelegatiService(cRequest);
//			JAXBContext jaxReq = JAXBContext.newInstance(GetDelegatiResponse.class);
//			Marshaller jaxM = jaxReq.createMarshaller();
//			jaxM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			jaxM.marshal(dati, System.out);
//
//		} catch (WebServiceException ws) {
//			ws.printStackTrace();
//			throw ws;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//
//		// if (response.getEsito().equalsIgnoreCase("SUCCESSO")) {
//		if (dati.getDelegati() != null) {
//			for (DelegaCittadino delega : dati.getDelegati().getDelegato()) {
//				if (Converter.CalcolaEta(delega.getDataDiNascita()) <= 18) {
//					// popola la delega
//					elencoDelegati.add(delega);
//				}
//			}
//			// }
//		} else {
//			return null;
//		}
//		return elencoDelegati;
//	}

	
	
	public List<it.csi.esenred.esenredweb.business.deleghebe.Cittadino> chiamaRicercaCittadiniService(
	
			String codicefiscale) throws Exception {

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
		
		//cittadino.setCodFiscale(codicefiscale);
		ricercaCittadino.setRichiedente(richiedente);
		
		ricercaCittadino.setCittadino(cittadino);

		//
		RicercaCittadiniResponse dati = null;

		try {
			
			dati = port.ricercaCittadiniService(ricercaCittadino);
			//dati = delegheCittadiniService.ricercaCittadiniService(ricercaCittadino);
			
		
			
			//JAXBContext jaxReq = JAXBContext.newInstance(GetDelegatiResponse.class);
			JAXBContext jaxReq = JAXBContext.newInstance(RicercaCittadiniResponse.class);
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
			
			for (it.csi.esenred.esenredweb.business.deleghebe.Cittadino cittadinoDel : dati.getCittadini()
					.getCittadino()) {
				elencoCittadini.add(cittadinoDel);
			}
		} else {
			return null;
		}
		
		return elencoCittadini;
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

	/**
	 * Recupera la lista dei titolari delle esenzioni che hanno come beneficiario
	 * l'idAura in input
	 * 
	 * @param idAura
	 * @return
	 */
	public List<Long> getListaTitolari(String idAura) {
		Set<Long> ritorno = new TreeSet<Long>();
		List<Long> lista = new ArrayList<Long>();

		GetEsenRedRes dati = getEsenred(idAura, "B");

		for (InfoEsenzioneGetEsenredVO esenzione : dati.getBody().getInfoEsenzione()) {
			try {
				ritorno.add(esenzione.getIdAuraTitolare().longValue());
			} catch (Exception e) {
			} 
		}

		for (Long l : ritorno) {
			lista.add(l);
		}

		return lista;
	}

	/**
	 * Recupera la lista dei beneficiari associati all'idAura in input
	 * 
	 * @param idAura
	 * @return
	 */
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
	 * Chiamata a getEsenred filtrando per attestato,codesenzione,datainiziovalidit o protocollo
	 * 
	 * @param attestato
	 * @param CodEsenzione
	 * @param data_inizio_validita
	 * @param idAura
	 * @param tipoUser
	 * @param protocollo
	 * @return
	 * @throws Exception
	 */
	public List<EsenredTEsenzioniReddito> getEsenzioni(String attestato, String codEsenzione,
			String data_inizio_validita, String idAura, String tipoUser, String protocollo,String dovesono) throws Exception {
		List<EsenredTEsenzioniReddito> esenzione;
		FiltriRicercaEsenzioniAcceleratore filtri = new FiltriRicercaEsenzioniAcceleratore();

		try {

			if (protocollo != null) {
				Long prot = Converter.getLong(protocollo);
				Filtri proto = new Filtri();
				proto.setEq(prot.toString());
				filtri.setProtocollo(proto);
			}
			
			if (attestato != null) {
				Filtri att = new Filtri();
				att.setEq(attestato);
				filtri.setAttestato(att);
			}
			
			if (codEsenzione != null) {
				Filtri codE = new Filtri();
				codE.setEq(codEsenzione);
				filtri.setCodice_esenzione(codE);
			}
			if (data_inizio_validita != null) {
				Filtri data = new Filtri();
				data.setEq(data_inizio_validita);
				filtri.setData_inizio_validita(data);
			}

			esenzione = getEsenzioni(filtri, idAura, tipoUser,dovesono);

			return esenzione;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioniAcceleratore filtri, String idAura,
			String tipoUser,String dovesono) throws Exception {
		List<EsenredTEsenzioniReddito> esenzione = new ArrayList<EsenredTEsenzioniReddito>();
		List<EsenredTEsenzioniReddito> esenzioneOut = new ArrayList<EsenredTEsenzioniReddito>();

		
		System.out.println("| Richiamo getEsenred");
		GetEsenRedRes dati = getEsenred(idAura, tipoUser);
		System.out.println("| End Richiamo getEsenred Ok");
		
		
		System.out.println("| Start conversione getEsenred aura to esenzione reddito");
		for (InfoEsenzioneGetEsenredVO singleEs : dati.getBody().getInfoEsenzione()) {
			esenzione.add(getEsenredTEsenzioniRedditoByGetEsenred(dati.getBody(), singleEs));
		}
		System.out.println("| End conversione getEsenred aura to esenzione reddito, OK");

		
		esenzioneOut = filtraLista(esenzione, filtri,dovesono);

		return esenzioneOut;
	}
	

	/**
	 * Converte da GetEsenred a Entity JPA
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
			
			System.out.println("| info.codice Esenzione: " + info.getCodEsenzione());
			System.out.println("| info.codice Attestato: " + info.getCodAttestato());
			System.out.println("| info.data Emissione: " + info.getDataEmissione());
			System.out.println("| info.data Scadenza: " + info.getDataScadenza());
			response.setCodEsenzione(info.getCodEsenzione());
			response.setCodNazionaleAslRilascio(getEsenred.getInfoAnagDomicilio().getAslDomicilio());

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

			System.out.println("| codice stato: " + response.getCodStato());
			EsenzioneIf EsenzioneIf;
			EsenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
			
			System.out.println("| start ricerco tipo esenzione sul db");
			List<EsenredDTipiEsenzioniReddito> elencoEsenzioni = EsenzioneIf.getEsenzioni(info.getCodEsenzione());
			System.out.println("| tipi esenzione trovate: " + elencoEsenzioni.size());
			response.setTipoEsenzione(elencoEsenzioni.get(0));

			System.out.println("| start leggo titolo dichiarante: " + info.getTitoloDichiarante());	
			if (Checker.isValorizzato(info.getTitoloDichiarante())) {
			response.setCodTitoloDichiarante(info.getTitoloDichiarante());
			response.setEsenredCTitoloDichiarante(titoloDichiaranteIf.getTitoli(info.getTitoloDichiarante()).get(0));
			}
			else
			{
				response.setCodTitoloDichiarante(null);
				response.setEsenredCTitoloDichiarante(null);
			}
			System.out.println("| end leggo titolo dichiarante ");	
			
			response.setDataFine(Converter.getDataAura(info.getDataScadenza()));
			response.setDataInizio(Converter.getDataAura(info.getDataEmissione()));
			response.setDataRevoca(Converter.getDataAura(info.getDataSospensione()));
			
			System.out.println("| post date ");	

			response.setAttestato(info.getCodAttestato());
			
			response.setFlagConsenso(1);

			if (info.getIdAuraBeneficiario() != null) response.setIdCittadinoBeneficiario(info.getIdAuraBeneficiario().longValue());
			else response.setIdCittadinoBeneficiario(new Long(0));
			
			if (info.getIdAuraDichiarante() != null) response.setIdCittadinoDichiarante(info.getIdAuraDichiarante().longValue());
			else response.setIdCittadinoDichiarante(new Long(0));
			
			if (info.getIdAuraTitolare() != null) response.setIdCittadinoTitolare(info.getIdAuraTitolare().longValue());
			else response.setIdCittadinoTitolare(new Long(0));

			if (info.getProtocollo() == null)
				response.setIdUserInsert(new Long(99999997));
			else
				response.setIdUserInsert(new Long(99999999));

			response.setNota(info.getNote());

			if (info.getProtocollo() != null)
				response.setNumProtocolloSogei(new Long(info.getProtocollo()));
			else 
				response.setNumProtocolloSogei(new Long(0));
			
			System.out.println("| end conversione ");	
			
		} catch (Exception e) {
			throw new Exception("Errore durante la conversione: " + e.getMessage());
		}
		return response;

	}

	/**
	 * 
	 * @param filtro
	 * @param elemento
	 * @return
	 */
	private boolean checkFiltro(Filtri filtro, String elemento) {
		if (filtro == null)
			return true;

		boolean checkIn = false;
		boolean checkNin = false;
		boolean checkEq = false;
		boolean checkNe = false;
		boolean checkLt = false;
		boolean checkLte = false;
		boolean checkGt = false;
		boolean checkGte = false;

		if (filtro.getIn() != null) {
			for (String s : filtro.getIn()) {
				if (elemento.equals(s)) {
					checkIn = true;
					break;
				}
			}
		} else
			checkIn = true;

		if (filtro.getNin() != null) {
			for (String s : filtro.getNin()) {
				if (!elemento.equals(s)) {
					checkNin = true;
					break;
				}
			}
		} else
			checkNin = true;

		if (filtro.getEq() != null) {
			if (elemento.equals(filtro.getEq()))
				checkEq = true;
		} else
			checkEq = true;

		if (filtro.getNe() != null) {
			if (!elemento.equals(filtro.getNe()))
				checkNe = true;
		} else
			checkNe = true;

		if (filtro.getLt() != null) {
			try {
				if (Checker.isData(Converter.getDataAcc(filtro.getLt()), "yyyy-MM-dd", Locale.ITALIAN)) {
					Date dataElemento = Converter.getData(elemento);					
					Date dataFiltro = Converter.getDataISO(Converter.getDataAcc(filtro.getLt()));
					if (dataElemento.before(dataFiltro))
						checkLt = true;
				} else if (Checker.isNumericString(filtro.getLt())) {
					int numElemento = Integer.parseInt(elemento);
					int numFiltro = Integer.parseInt(filtro.getLt());
					if (numElemento < numFiltro)
						checkLt = true;
				}
			} catch (Exception e) {
				System.out.println("Errore nel check dei filtri: " + e.getMessage());
				checkLt = true;
			}
		} else
			checkLt = true;

		if (filtro.getLte() != null) {
			try {
				if (Checker.isData(Converter.getDataAcc(filtro.getLte()), "yyyy-MM-dd", Locale.ITALIAN)) {
					Date dataElemento = Converter.getData(elemento);
					Date dataFiltro = Converter.getDataISO(Converter.getDataAcc(filtro.getLte()));
					if (dataElemento.before(dataFiltro) || dataElemento.equals(dataFiltro))
						checkLte = true;
				} else if (Checker.isNumericString(filtro.getLte())) {
					int numElemento = Integer.parseInt(elemento);
					int numFiltro = Integer.parseInt(filtro.getLte());
					if (numElemento <= numFiltro)
						checkLte = true;
				}
			} catch (Exception e) {
				System.out.println("Errore nel check dei filtri: " + e.getMessage());
				checkLte = true;
			}
		} else
			checkLte = true;

		if (filtro.getGt() != null) {
			try {
				if (Checker.isData(Converter.getDataAcc(filtro.getGt()), "yyyy-MM-dd", Locale.ITALIAN)) {
					Date dataElemento = Converter.getData(elemento);
					Date dataFiltro = Converter.getDataISO(Converter.getDataAcc(filtro.getGt()));
					if (dataElemento.after(dataFiltro))
						checkGt = true;
				} else if (Checker.isNumericString(filtro.getGt())) {
					int numElemento = Integer.parseInt(elemento);
					int numFiltro = Integer.parseInt(filtro.getGt());
					if (numElemento > numFiltro)
						checkGt = true;
				}
			} catch (Exception e) {
				System.out.println("Errore nel check dei filtri: " + e.getMessage());
				checkGt = true;
			}
		} else
			checkGt = true;

		if (filtro.getGte() != null) {
			try {
				if (Checker.isData(Converter.getDataAcc(filtro.getGte()), "yyyy-MM-dd", Locale.ITALIAN))  {
					Date dataElemento = Converter.getData(elemento);
					Date dataFiltro = Converter.getDataISO(Converter.getDataAcc(filtro.getGte()));
					if (dataElemento.after(dataFiltro) || dataElemento.equals(dataFiltro))
						checkGte = true;
				} else if (Checker.isNumericString(filtro.getGte())) {
					int numElemento = Integer.parseInt(elemento);
					int numFiltro = Integer.parseInt(filtro.getGte());
					if (numElemento >= numFiltro)
						checkGte = true;
				}
			} catch (Exception e) {
				System.out.println("Errore nel check dei filtri: " + e.getMessage());
				checkGte = true;
			}
		} else
			checkGte = true;

		return (checkIn && checkNin && checkEq && checkNe && checkLt && checkLte && checkGt && checkGte);
	}

	/**
	 * 
	 * @param lista
	 * @param filtri
	 * @return
	 */
	private List<EsenredTEsenzioniReddito> filtraLista(List<EsenredTEsenzioniReddito> lista,
			FiltriRicercaEsenzioniAcceleratore filtri,String dovesono) {
		List<EsenredTEsenzioniReddito> newList = new ArrayList<EsenredTEsenzioniReddito>();
		try {
			if (filtri != null) {
				for (EsenredTEsenzioniReddito elemento : lista) {
					try {
						if (dovesono==null) {
						if (checkFiltro(filtri.getStato(), elemento.getCodStato())
								&& checkFiltro(filtri.getRapporto_familiare(), elemento.getCodTitoloDichiarante())
								&& checkFiltro(filtri.getData_inizio_validita(),Converter.getData(elemento.getDataInizio()))
								&& checkFiltro(filtri.getData_scadenza(), Converter.getData(elemento.getDataFine()))
								&& checkFiltro(filtri.getCodice_esenzione(), elemento.getCodEsenzione())
								&& checkFiltro(filtri.getCreato_da(), elemento.getIdCittadinoDichiarante().toString())
								&& checkFiltro(filtri.getCreato_per(), elemento.getIdCittadinoBeneficiario().toString())
								&& checkFiltro(filtri.getProtocollo(), elemento.getNumProtocolloSogei().toString())	
								&& checkFiltro(filtri.getTitolare(), elemento.getIdCittadinoTitolare().toString())
								&& checkFiltro(filtri.getAttestato(), elemento.getAttestato())								
								&& checkFiltro(filtri.getData_revoca(), Converter.getData(elemento.getDataRevoca()))) {
							newList.add(elemento);
						}
						}
						else if (dovesono.equalsIgnoreCase("STAMPAESENZIONECERTIFICATA")){
							if ( checkFiltro(filtri.getData_inizio_validita(),Converter.getData(elemento.getDataInizio()))
									 && checkFiltro(filtri.getCodice_esenzione(), elemento.getCodEsenzione())
									 && checkFiltro(filtri.getCreato_per(), elemento.getIdCittadinoBeneficiario().toString())
									)
								newList.add(elemento);
							}
						else if (dovesono.equalsIgnoreCase("STAMPAESENZIONEAUTOCERTIFICATA")){
							if ( checkFiltro(filtri.getProtocollo(), elemento.getNumProtocolloSogei().toString()))
								newList.add(elemento);
							}
					} catch (Exception e) {}
				}
			} else
				return newList;
		} catch (Exception e) {
			return newList;
		}
		return newList;
	}

	/**
	 * 
	 * @param filtri
	 * @param protocollo
	 * @param idAura
	 * @param idAuradelegato
	 * @return
	 * @throws Exception
	 */
	public List<EsenredTEsenzioniReddito> getEsenzioniAcceleratore(FiltriRicercaEsenzioniAcceleratore filtri,
			String protocollo, Long idAura, Set<Long> idAuradelegato) throws Exception {
		List<EsenredTEsenzioniReddito> esenzioni = new ArrayList<EsenredTEsenzioniReddito>();
		Set<EsenredTEsenzioniReddito> tmp = new TreeSet<EsenredTEsenzioniReddito>();

		Filtri protocolli = new Filtri();
		
		if (!protocollo.isEmpty()) {
		protocolli.setNin(protocollo.split(","));
		filtri.setProtocollo(protocolli);
		}
		
//		if (idAuradelegato.size() != 0) {
//			Filtri beneficiario = new Filtri();
//			String[] auradeleg = new String[idAuradelegato.size()];
////			for(int i =0; i< idAuradelegato.size(); i++)  {
////				auradeleg [i]= ;
////			}
//			int i = 0;
//			for(Long l : idAuradelegato) {
//				auradeleg[i] = l.toString();
//				i++;
//			}
//			beneficiario.setIn(auradeleg);
//			filtri.setCreato_per(beneficiario);
//			
//		}
		try {
			if (filtri.getStorico() != null) {
				if (filtri.getStorico().getEq() != null) {
					if (filtri.getStorico().getEq().equalsIgnoreCase("NO")) {
						Filtri scadenza = new Filtri();
						scadenza.setGt(Converter.getData(new Date())+"T00:00");
						filtri.setData_scadenza(scadenza);
					} else {
						Filtri scadenza = new Filtri();
						scadenza.setLt(Converter.getData(new Date())+"T00:00");
						filtri.setData_scadenza(scadenza);
					}
				} else if (filtri.getStorico().getNe() != null) {
					if (filtri.getStorico().getNe().equalsIgnoreCase("SI")) {
						Filtri scadenza = new Filtri();
						scadenza.setGt(Converter.getData(new Date())+"T00:00");
						filtri.setData_scadenza(scadenza);
					} else {
						Filtri scadenza = new Filtri();
						scadenza.setLt(Converter.getData(new Date())+"T00:00");
						filtri.setData_scadenza(scadenza);
					}
					}
			}
			
//			if (!filtri.getStorico().getEq().equalsIgnoreCase("NO")|| !filtri.getStorico().getNe().equalsIgnoreCase("SI")) {
//				Filtri scadenza = new Filtri();
//				scadenza.setLt(Converter.getData(new Date()));
//				filtri.setData_scadenza(scadenza);
//			} else {
//				Filtri scadenza = new Filtri();
//				scadenza.setGt(Converter.getData(new Date()));
//				filtri.setData_scadenza(scadenza);
//			}
		} catch (Exception e) {
//			Filtri scadenza = new Filtri();
//			scadenza.setGt(Converter.getData(new Date()));
//			filtri.setData_scadenza(scadenza);
		}

		tmp.addAll(getEsenzioni(filtri, idAura.toString(), "T",null));
		tmp.addAll(getEsenzioni(filtri, idAura.toString(), "B",null));
		
		System.out.println("START TIPO USER D");
		tmp.addAll(getEsenzioni(filtri, idAura.toString(), "D",null));
		System.out.println("END TIPO USER D");
		
		
		for(Long l : idAuradelegato) {
			tmp.addAll(getEsenzioni(filtri, l.toString(), "B",null));
		}
		
		
		esenzioni.addAll(tmp);

		return esenzioni;
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
	
	public List<EsenredTEsenzioniReddito> getEsenzioniValide(String idAuraBen) {
		List<EsenredTEsenzioniReddito> esenzioni = new ArrayList<EsenredTEsenzioniReddito>();

		try {
			for (EsenredTEsenzioniReddito single : getEsenzioni(null, null, null, idAuraBen, "B",null,null)) {
					try {
						if (single.getCodStato().equalsIgnoreCase(StatoEsenzione.VALIDA.getCodice())) {
						//if (single.getDataFine() != null) {
						//	if (single.getDataFine().compareTo(Converter.getDateWithoutTime(new Date()))>0) {
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
}