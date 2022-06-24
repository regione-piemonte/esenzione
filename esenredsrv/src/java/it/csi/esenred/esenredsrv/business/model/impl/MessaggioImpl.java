/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.model.impl;

import it.csi.esenred.esenredsrv.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredsrv.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredsrv.business.model.interfaces.MessaggioIf;

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
}