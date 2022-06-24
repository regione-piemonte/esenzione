/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.model.impl;

import it.csi.esenred.esenredsrv.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredsrv.business.entity.EsenredCParametri;
import it.csi.esenred.esenredsrv.business.model.interfaces.ParametroIf;

public class ParametroImpl implements ParametroIf {

	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public EsenredCParametri getParametro(String codice) {
		EsenredCParametri parametro = getDataDao().getParametro(codice);
		return parametro;
	}
}