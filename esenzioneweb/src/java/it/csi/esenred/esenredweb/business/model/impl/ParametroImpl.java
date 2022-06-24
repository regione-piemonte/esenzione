/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;

public class ParametroImpl implements ParametroIf {

	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public List<EsenredCParametri> getParametri(String codParametro) {
		return getDataDao().getParametri(codParametro);
	}
}