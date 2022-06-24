/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.check;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.dto.FiltriRicercaCittadino;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;

public class CheckRicercaCittadini {
	private static String isTst;
	//Se data inizio e data fine valorizzata, allora data inizio <= data fine
	public static void chkFiltri(FiltriRicercaCittadino filtri) throws CheckException {
		
		if (Checker.isValorizzato(filtri.getCodFiscale())) {
			 if (
				Checker.isValorizzato(filtri.getCognome())
				|| Checker.isValorizzato(filtri.getNome())
				|| Checker.isValorizzato(filtri.getDataDiNascita())
				)
				 throw new CheckException("MSG006"," valorizzare o codice fiscale, oppure cognome, nome e data di nascita");//o codice fiscale, oppure cognome, nome e data di nascita
			//controllo codice fiscale mascherato esteso a esenred 1
				if(isTst==null) {
	    			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
	    			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
	    			isTst= elencoParametri.get(0).getValore();		
	    		}
			 
			 if (!Checker.isCodiceFiscale(filtri.getCodFiscale(),isTst))
				 throw new CheckException("MSG006"," codice fiscale non valido");//codice fiscale non valido
			
		} else if (
				Checker.isValorizzato(filtri.getCognome())
				&& Checker.isValorizzato(filtri.getNome())
				&& Checker.isValorizzato(filtri.getDataDiNascita())
				) {
			
			if (!Checker.isData(filtri.getDataDiNascita(), "dd/MM/yyyy", Locale.ITALIAN))
				 throw new CheckException("MSG006"," data di nascita non valida");//data di nascita non valida
			
				Date bornDate = Converter.getData(filtri.getDataDiNascita());
				Date now = Converter.getData(Converter.getData(new Date()));
				
				if (bornDate.compareTo(now) > 0)
					throw new CheckException("MSG006"," la data di nascita e' successiva alla data odierna");
		} else {
			//ESENRED-37
			throw new CheckException("MSG006"," valorizzare o codice fiscale, oppure cognome, nome e data di nascita");//o codice fiscale, oppure cognome, nome e data di nascita
//			throw new CheckException("MSG017",""); //
		}
		
	}
}