/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.util;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.dto.DataInizioFine;

public class Util {
	
	public static String composeMessage(EsenredCMessaggi messaggio, String dettaglio) {
		String out = messaggio.getTesto();
		if (Checker.isValorizzato(dettaglio)) {
			if (out.contains("#R"))
				out = out.replaceAll("#R", dettaglio);
			else
				out = out+dettaglio;
		}
		return out;
	}

	public static  <T> T getSingleResult(TypedQuery<T> query) {
		List<T> results = query.getResultList();
		if (!results.isEmpty())
			return (T) results.get(0);
		else
			return null;
	}

	public static DataInizioFine getDataInizioFine(String ggMM) {
		DataInizioFine output = new DataInizioFine();
			String today = Converter.getData(new Date());
			output.setDataRichiesta(today);
			output.setInizioValidita(today);
			output.setFineValidita(getFineValidita(today, ggMM));
			
			return output;
	}
	
	private static String getFineValidita(String data, String ggMM) {

		String annoCorrente = Converter.getAnno(new Date());
		String ggmmaaaa = ggMM + "/" + annoCorrente;
		Date scadCandidate = Converter.getData(ggmmaaaa);
		Date today = Converter.getData(data);

			scadCandidate = Converter.aggiungiAnnoAData(scadCandidate, 1);
			return Converter.getData(scadCandidate);

	}
}