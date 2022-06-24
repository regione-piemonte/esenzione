/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.model.impl;

import java.util.Date;

import it.csi.esenred.esenredsrv.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredsrv.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredsrv.business.model.interfaces.EsenzioneCittadinoIf;

public class EsenzioneCittadinoImpl implements EsenzioneCittadinoIf {
	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio) {
		return getDataDao().getEsenzioneCittadino(idAura, codEsenzione, dataInizio);
	}

	public void update(EsenredTEsenzioniReddito esenzione) {
		getDataDao().updateEsenzioneCittadino(esenzione);
	}
}
