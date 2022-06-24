/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.model.impl;

import it.csi.esenred.esenredsrv.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredsrv.business.entity.EsenredWNotifiche;
import it.csi.esenred.esenredsrv.business.model.interfaces.NotificaIf;

public class NotificaImpl implements NotificaIf {
	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public void updateNotifica(EsenredWNotifiche notifica) {
		getDataDao().updateNotifica(notifica);
	}

	public void insert(EsenredWNotifiche notifica) {
		getDataDao().insertNotifica(notifica);
	}
}
