/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.util;

import java.util.List;

import javax.persistence.TypedQuery;

import it.csi.esenred.esenredsrv.business.entity.EsenredCMessaggi;

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
}