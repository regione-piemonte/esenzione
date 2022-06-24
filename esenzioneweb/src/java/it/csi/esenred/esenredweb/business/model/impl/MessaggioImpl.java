/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;

public class MessaggioImpl implements MessaggioIf {

	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public EsenredCMessaggi getMessaggio(String codice) {
		EsenredCMessaggi messaggio = getDataDao().getMessaggio(codice);
		return messaggio;
	}

	public List<EsenredCMessaggi> getMessaggioLike(String codMessaggio) {
		return getDataDao().getMessaggioLike(codMessaggio);
	}
}