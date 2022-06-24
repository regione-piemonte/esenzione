/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredTAslOperatore;
import it.csi.esenred.esenredweb.business.model.interfaces.AslOperatoreIf;


public class AslOperatoreImpl implements AslOperatoreIf {

	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public EsenredTAslOperatore getAslOperatoreCF(String CodiceFiscaleOperatore) {
		return getDataDao().getAslOperatoreCF(CodiceFiscaleOperatore);
	}	
}
