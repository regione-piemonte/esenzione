/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredWNotifiche;
import it.csi.esenred.esenredweb.business.model.interfaces.NotificaIf;

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
	
	public List<EsenredWNotifiche> getNotificheOperatore(Long idOperatore) {
		return this.dataDao.getNotificheOperatore(idOperatore);
	}

	public List<EsenredWNotifiche> getNotificheCittadino(Long idCittadino) {
		return this.dataDao.getNotificheCittadino(idCittadino);
	}

	public boolean deleteNotifica(Long idNotifica) {
		return this.dataDao.deleteNotifica(idNotifica);
	}

	public boolean updateSetConsultata(Long idNotifica) {
		return this.dataDao.updateSetConsultata(idNotifica);
	}
}
