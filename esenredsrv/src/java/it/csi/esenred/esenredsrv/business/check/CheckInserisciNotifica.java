/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.check;

import java.util.Date;
import java.util.Locale;

import it.csi.esenred.esenredsrv.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredsrv.business.exception.CheckException;
import it.csi.esenred.esenredsrv.dto.NotificaRequest;
import it.csi.esenred.esenredsrv.util.Checker;
import it.csi.esenred.esenredsrv.util.Converter;

public class CheckInserisciNotifica {

	public static boolean isValidRequest(NotificaRequest notifica) 
	throws CheckException {
		
		if (!Checker.isValorizzato(notifica.getIdAura())) {
			throw new CheckException("MSG015","idAura");
		}
		
		if (!Checker.isValorizzato(notifica.getCodEsenzione())) {
			throw new CheckException("MSG015","codice esenzione");
		}
		
		if (!Checker.isValorizzato(notifica.getDataInizio())) {
			throw new CheckException("MSG015","data inizio esenzione");
		}
		
		if (!Checker.isValorizzato(notifica.getTipoNotifica())) {
			throw new CheckException("MSG015","tipo notifica");
		}
		
		if (notifica.getEsito().equals("1") && !Checker.isValorizzato(notifica.getNumProtSogei())) {
			throw new CheckException("MSG015", "numero protocollo SOGEI");
		}
		
		if (!Checker.isValorizzato(notifica.getEsito())) {
			throw new CheckException("MSG015","esito");
		}
		
		if (!Checker.isValorizzato(notifica.getCodEsitoMEF())) {
			throw new CheckException("MSG015","codice esito MEF");
		}
		
		if (!Checker.isValorizzato(notifica.getDescEsitoMEF())) {
			throw new CheckException("MSG015","descrizione esito MEF");
		}
		
		if (!notifica.getEsito().equals("0") && !notifica.getEsito().equals("1")) {
			throw new CheckException("MSG004","esito");
		}
		
		if (notifica.getCodEsenzione().length() > 3) {
			throw new CheckException("MSG004"," lunghezza campo codice esenzione al limite consentito (3)");
		}
		
		if (notifica.getTipoNotifica().length() > 1) {
			throw new CheckException("MSG004"," lunghezza campo tipo notifica al limite consentito (1)");
		}
		
		if (!notifica.getTipoNotifica().equalsIgnoreCase("I") && !notifica.getTipoNotifica().equalsIgnoreCase("R")) {
			throw new CheckException("MSG004","tipo notifica");
		}
		
		if (!Checker.isNumericString(notifica.getIdAura())) {
			throw new CheckException("MSG004"," idAura non valido");
		}
		
		if (notifica.getIdAura().length() > 9) {
			throw new CheckException("MSG004"," lunghezza campo idAura superiore al limite consentito (9)");
		}
		
		if (notifica.getEsito().equals("1") && !Checker.isNumericString(notifica.getNumProtSogei())) {
			throw new CheckException("MSG004"," numero protocollo SOGEI non valido");
		}
		
		if (notifica.getEsito().equals("1") && notifica.getNumProtSogei().length() > 9) {
			throw new CheckException("MSG004"," lunghezza campo numero protocollo SOGEI superiore al limite consentito (9)");
		}

		if (!Checker.isData(notifica.getDataInizio(), "dd/MM/yyyy", Locale.ITALY))
			throw new CheckException("MSG004","la data inizio esenzione non e' valida");
		
		if (Converter.getData(notifica.getDataInizio()).after(new Date()))
			throw new CheckException("MSG004","la data inizio esenzione e' successiva alla data odierna");
		
		return true;
	}

	public static void chkEsenzione(EsenredTEsenzioniReddito esenzioneDB) 
	throws CheckException {
		if (esenzioneDB == null) 
			throw new CheckException("MSG014","");
	}
}