/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.check;

import java.util.Date;
import java.util.Locale;

import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioni;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniCittadino;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniOperatore;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;

public class CheckRicercaEsenzioneOperatore {

	//Se data inizio e data fine valorizzata, allora data inizio <= data fine
	public static void chkFiltri(FiltriRicercaEsenzioni filtri) throws CheckException {
		
		if (filtri instanceof FiltriRicercaEsenzioniCittadino) {
			if (!Checker.isValorizzato(filtri.getCodFiscaleDichiarante()))
				throw new CheckException("MSG015","codice fiscale del dichiarante");
		} else if (filtri instanceof FiltriRicercaEsenzioniOperatore) {
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
		
		//Se valorizzata data di inizio periodo validita', ma non valorizzata data di fine
		//periodo validita', si imposta data di fine periodo validita' pari alla data di sistema;
		if (Checker.isValorizzato(dataInizio) && !Checker.isValorizzato(dataFine)) {
			filtri.setDataFineValidita(Converter.getData(new Date()));
		}
	}
}