/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.csi.esenred.esenpatweb.dto.Motivazione;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.model.interfaces.StatoPraticaIf;


public class StatoPraticaImpl implements StatoPraticaIf {

	private DataDaoPatologiaIf dataDao;

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}
	public List<EsenzioneDPraticaStato> getStatoPratica(String skAzione) {
		return getDataDao().getStatoPratica(skAzione);
	}

	@Override
	public List<Motivazione> getMotivazioniByCodStatoPratica(String codStato) {
		List<EsenzioneDMotivazioneTipo> motivazioniStato = getDataDao().getMotivazioniByCodStatoPratica(codStato);
		List<Motivazione> motivazioni = new ArrayList<Motivazione>();
		for (EsenzioneDMotivazioneTipo m : motivazioniStato) {
			motivazioni.add(new Motivazione(m));
		}
		return motivazioni;
	}
}
