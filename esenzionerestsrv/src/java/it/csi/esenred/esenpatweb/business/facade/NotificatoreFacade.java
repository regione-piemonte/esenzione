/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.facade;

import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.util.LogUtil;
import it.csi.esenred.esenredweb.util.NotificatoreUtil;

@Service
public class NotificatoreFacade {
	
	private static final String BENEFICIARIO = "beneficiario";
	private static final String DELEGATO = "delegato";
	
	private static final String EMAIL_SUBJECT_BENEFICIARIO = "_email_subject_beneficiario";
	private static final String EMAIL_BENEFICIARIO = "_email_beneficiario";
	
	private static final String EMAIL_SUBJECT_DELEGATO = "_email_subject_delegato";
	private static final String EMAIL_DELEGATO = "_email_delegato";
	
	private static final String PUSH_TITLE_BENEFICIARIO = "_push_title_beneficiario";
	private static final String PUSH_BENEFICIARIO = "_push_beneficiario";
	
	private static final String PUSH_TITLE_DELEGATO = "_push_title_delegato";
	private static final String PUSH_DELEGATO = "_push_delegato";
	
	private static final String SMS_BENEFICIARIO = "_sms_beneficiario";
	
	private static final String TEMPLATE = "NOTIFICATORE_TEMPLATE";
	private static final String TAG = "NOTIFICATORE_TAG";
	

	private static final String PARAM1_BATCH = "PARAM1_BATCH";
	private static final String PARAM2_BATCH = "PARAM2_BATCH";
	private static final String PARAM3_BATCH = "PARAM3_BATCH";
	
	private NotificatoreUtil notificatoreUtil;

	private DataDaoPatologiaIf dataDao;
	
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENPAT, null);


	public void sendMessage(String webService, String cfDestinatario, Map<String, String> replacements) throws CheckException {
		
		String methodName = "sendMessage";
		log.info(methodName, " BEGIN");
		
		String emailSubjectBeneficiario = dataDao.getParametri(webService +EMAIL_SUBJECT_BENEFICIARIO) != null ? !dataDao.getParametri(webService +EMAIL_SUBJECT_BENEFICIARIO).isEmpty() ?  dataDao.getParametri(webService +EMAIL_SUBJECT_BENEFICIARIO).iterator().next().getValore() : null : null;
		String emailBeneficiario = dataDao.getParametri(webService + EMAIL_BENEFICIARIO) != null ? !dataDao.getParametri(webService + EMAIL_BENEFICIARIO).isEmpty() ?  dataDao.getParametri(webService + EMAIL_BENEFICIARIO).iterator().next().getValore() : null : null;

		log.debug(methodName, " webService: " + webService + " EMAIL_SUBJECT_BENEFICIARIO: " + emailSubjectBeneficiario);
		log.debug(methodName, " webService: " + webService + " EMAIL_BENEFICIARIO: " + emailBeneficiario);
	
		Properties prop = getPropCondivise();
		if (emailBeneficiario != null) {
			prop.put(webService+EMAIL_SUBJECT_BENEFICIARIO, emailSubjectBeneficiario);
			prop.put(webService+EMAIL_BENEFICIARIO, emailBeneficiario);
		}
		
		log.debug(methodName, " Lette props per la chiamata al notificatore (endpoint e token)");
		
		String pushTitleBeneficiario = dataDao.getParametri(webService + PUSH_TITLE_BENEFICIARIO) != null ? !dataDao.getParametri(webService + PUSH_TITLE_BENEFICIARIO).isEmpty() ?  dataDao.getParametri(webService + PUSH_TITLE_BENEFICIARIO).iterator().next().getValore() : null : null;
		String pushBeneficiario = dataDao.getParametri(webService + PUSH_BENEFICIARIO) != null ? !dataDao.getParametri(webService + PUSH_BENEFICIARIO).isEmpty() ?  dataDao.getParametri(webService + PUSH_BENEFICIARIO).iterator().next().getValore() : null : null;
		if (pushBeneficiario != null) {
			prop.put(webService+PUSH_TITLE_BENEFICIARIO, pushTitleBeneficiario);
			prop.put(webService+PUSH_BENEFICIARIO, pushBeneficiario);
			
			log.debug(methodName, " PUSH_TITLE_BENEFICIARIO: " + pushTitleBeneficiario);
			log.debug(methodName, " PUSH_BENEFICIARIO: " + pushBeneficiario);
			
		}
		
		enza
		if(webService.equalsIgnoreCase(PARAM1_BATCH) || webService.equalsIgnoreCase(PARAM2_BATCH)
				|| webService.equalsIgnoreCase(PARAM3_BATCH))
		{
			String endpointDays = webService.toUpperCase() + "_ESENPAT";
			String pushDays = dataDao.getParametri(endpointDays) != null ? !dataDao.getParametri(endpointDays).isEmpty() ?  dataDao.getParametri(endpointDays).iterator().next().getValore() : null : null;
			prop.put(endpointDays, pushDays);
		}
		
		
		notificatoreUtil.setProp(prop);
		notificatoreUtil.callNotificatore(webService, cfDestinatario, BENEFICIARIO, replacements);

		if (replacements.containsKey("@DELEGATO@")) {
			String emailSubjectDelegato = dataDao.getParametri(webService +EMAIL_SUBJECT_DELEGATO) != null ? !dataDao.getParametri(webService +EMAIL_SUBJECT_DELEGATO).isEmpty() ?  dataDao.getParametri(webService +EMAIL_SUBJECT_DELEGATO).iterator().next().getValore() : null : null;
			String emailDelegato = dataDao.getParametri(webService + EMAIL_DELEGATO) != null ? !dataDao.getParametri(webService +EMAIL_DELEGATO).isEmpty() ?  dataDao.getParametri(webService + EMAIL_DELEGATO).iterator().next().getValore() : null : null;

			prop = getPropCondivise();
			if (emailDelegato != null) {
				prop.put(webService+EMAIL_SUBJECT_DELEGATO, emailSubjectDelegato);
				prop.put(webService+EMAIL_DELEGATO, emailDelegato);
			}
			
			String pushTitleDelegato = dataDao.getParametri(webService + PUSH_TITLE_DELEGATO) != null ? !dataDao.getParametri(webService + PUSH_TITLE_DELEGATO).isEmpty() ?  dataDao.getParametri(webService + PUSH_TITLE_DELEGATO).iterator().next().getValore() : null : null;
			String pushDelegato = dataDao.getParametri(webService +PUSH_DELEGATO) != null ? !dataDao.getParametri(webService + PUSH_DELEGATO).isEmpty() ?  dataDao.getParametri(webService + PUSH_DELEGATO).iterator().next().getValore() : null : null;
			if (pushDelegato != null) {
				prop.put(webService+PUSH_TITLE_DELEGATO, pushTitleDelegato);
				prop.put(webService+PUSH_DELEGATO, pushDelegato);
			}
			if (emailDelegato != null || pushDelegato  != null) {
				notificatoreUtil.setProp(prop);
				notificatoreUtil.callNotificatore(webService, cfDestinatario, DELEGATO, replacements);
			}
		}
s
		String smsBeneficiario = dataDao.getParametri(webService + SMS_BENEFICIARIO) != null ? !dataDao.getParametri(webService + SMS_BENEFICIARIO).isEmpty() ?  dataDao.getParametri(webService + SMS_BENEFICIARIO).iterator().next().getValore() : null : null;
		if (smsBeneficiario != null) {
			prop = getPropCondivise();
			prop.put(webService+SMS_BENEFICIARIO, smsBeneficiario);

			notificatoreUtil.setProp(prop);
			notificatoreUtil.callNotificatore(webService, cfDestinatario, BENEFICIARIO, replacements);
		}
		
		log.info(methodName, " END");
	}

	private Properties getPropCondivise() {
		Properties prop = new Properties();
		String xAuthenticationString = dataDao.getParametri("NOTIFICATORE_TOKEN").iterator().next().getValore();
		String endpoint = dataDao.getParametri("NOTIFICATORE_ENDPOINT").iterator().next().getValore();
		String template = dataDao.getParametri(TEMPLATE).iterator().next().getValore();
		String tag = dataDao.getParametri(TAG).iterator().next().getValore();
		prop.put("xAuthenticationString", xAuthenticationString);
		prop.put("endpoint", endpoint);
		prop.put("template", template);
		prop.put("tag", tag);
		return prop;
	}

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}

	public NotificatoreUtil getNotificatoreUtil() {
		return notificatoreUtil;
	}

	public void setNotificatoreUtil(NotificatoreUtil notificatoreUtil) {
		this.notificatoreUtil = notificatoreUtil;
	}
}