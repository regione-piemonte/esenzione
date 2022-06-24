/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.check;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniEsenred;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniCittadino;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniOperatore;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaPratiche;
import it.csi.esenred.esenpatweb.dto.FiltriValidaEsenzionePatologia;
import it.csi.esenred.esenpatweb.dto.FiltroPaginazione;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class CheckRicercaEsenzioneOperatore {

	
public static void chkFiltri(FiltriRicercaEsenzioniEsenred filtri) throws CheckException {
		
		if (filtri instanceof FiltriRicercaEsenzioniCittadino) {
			if (!Checker.isValorizzato(filtri.getCodFiscaleDichiarante()))
				throw new CheckException("MSG015","codice fiscale del dichiarante");
		} 
		else if (filtri instanceof FiltriRicercaEsenzioniOperatore) {
			if (!Checker.isValorizzato(((FiltriRicercaEsenzioniOperatore)filtri).getInCaricoASL()))
				throw new CheckException("MSG015","in carico");	
		} 
		
		String dataInizio = filtri.getDataInizioValidita();
		String dataFine = filtri.getDataFineValidita();
		
		if (Checker.isValorizzato(dataInizio) && Checker.isValorizzato(dataFine)) {

			if (!Checker.isData(dataInizio, "dd/MM/yyyy", Locale.ITALIAN)) {
				throw new CheckException("MSG006"," Data di inizio validita' non valida");
			}
			
			if (!Checker.isData(dataFine, "dd/MM/yyyy", Locale.ITALIAN)) {
				throw new CheckException("MSG006"," Data di fine validita' non valida");
			}

			Date dataInizioDate = Converter.getData(dataInizio);
			Date dataFineDate = Converter.getData(dataFine);

			if (dataInizioDate.compareTo(dataFineDate) > 0)
				throw new CheckException("MSG006"," la data fine validita' deve essere successiva a data inizio validita'");
		} 
		
		if (Checker.isValorizzato(dataFine) && !Checker.isValorizzato(dataInizio)) {
			throw new CheckException("MSG015","data inizio validita'");
		}
		

		if (Checker.isValorizzato(dataInizio) && !Checker.isValorizzato(dataFine)) {
			filtri.setDataFineValidita(Converter.getData(new Date()));
		}
	}

	public static void chkFiltriCertificatoPatologia(FiltriRicercaCertificatoPatologia filtri) throws CheckException {
	
	
	String dataInizio = filtri.getDataInizioValidita();
	String dataFine = filtri.getDataFineValidita();
	String idAuraBeneficiario = filtri.getIdAuraBeneficiario();
	String codCertificato = filtri.getCodCertificato();
	String statoCodCertificato = filtri.getCodStatoCertificato();
	String cognome = filtri.getCognomeBeneficiario();
	String cf = filtri.getCodFiscaleBeneficiario();
	
	
	
	

	if (Checker.isValorizzato(dataInizio)) {

		if (!Checker.isData(dataInizio, "dd/MM/yyyy", Locale.ITALIAN)) {
			throw new CheckException("MSG006"," Data di inizio validita' non valida");
		}

	} 
	if (Checker.isValorizzato(dataFine)) {
		if (!Checker.isData(dataFine, "dd/MM/yyyy", Locale.ITALIAN)) {
			throw new CheckException("MSG006"," Data di scadenza non valida");
		}	

	} 
	if (Checker.isValorizzato(dataInizio) && Checker.isValorizzato(dataFine)) {
		
		Date dataInizioDate = Converter.getData(dataInizio);
		Date dataFineDate = Converter.getData(dataFine);

		if (dataInizioDate.compareTo(dataFineDate) > 0)
			throw new CheckException("MSG006"," la data scadenza deve essere successiva a data inizio validita'");

			if (Util.getAnniDifferenza(dataInizioDate, dataFineDate) >= 1)
				throw new CheckException("MSG222");
	}
	
	}
	
	public static void chkFiltriRinnova(FiltriValidaEsenzionePatologia filtri) throws CheckException {
		
		if (filtri.getDocumentiallegati().length==0) {
			//se non ci sono allegatio dai errore 
			throw new CheckException("MSG207");
		}
		
		}

	public static void chkFiltriPratica(FiltriRicercaPratiche filtri) throws CheckException, Exception {

		if (!Checker.isValorizzato(filtri.getTab()))
			throw new Exception();
		if (filtri.getTab().equalsIgnoreCase(Constants.TAB_DA_FARE)) {

		} else if (filtri.getTab().equalsIgnoreCase(Constants.TAB_TUTTE_LE_PRATICHE)) {

			if (!Checker.isValorizzato(filtri.getIdAura()) || !Checker.isValorizzato(filtri.getCfBeneficiario()))
				throw new CheckException("MSG145");

		} else
			throw new CheckException("MSG149");

		String dataInizio = filtri.getValidaDal();
		String dataFine = filtri.getValidaAl();
		String cognome = filtri.getCognomeBeneficiario();
		String nome = filtri.getNomeBeneficiario();
		String Cf = filtri.getCfBeneficiario();
		if (Checker.isValorizzato(dataInizio)) {

			if (!Checker.isData(dataInizio, "dd/MM/yyyy", Locale.ITALIAN)) {
				throw new CheckException("MSG006", " Data di inizio validita' non valida");
			}

		}
		if (Checker.isValorizzato(dataFine)) {
			if (!Checker.isData(dataFine, "dd/MM/yyyy", Locale.ITALIAN)) {
				throw new CheckException("MSG006", " Data di scadenza non valida");
			}

		}
		if (Checker.isValorizzato(dataInizio) && Checker.isValorizzato(dataFine)) {

			Date dataInizioDate = Converter.getData(dataInizio);
			Date dataFineDate = Converter.getData(dataFine);

			if (dataInizioDate.compareTo(dataFineDate) > 0)
				throw new CheckException("MSG006", " la data scadenza deve essere successiva a data inizio validita'");

			if (Util.getAnniDifferenza(dataInizioDate, dataFineDate) >= 1)
				throw new CheckException("MSG222");
		}
		
	}
}