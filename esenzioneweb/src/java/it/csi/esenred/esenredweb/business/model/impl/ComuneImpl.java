/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;

public class ComuneImpl implements ComuneIf {

    private DataDaoIf dataDao;

    public DataDaoIf getDataDao() {
        return dataDao;
    }

    public void setDataDao(DataDaoIf dataDao) {
        this.dataDao = dataDao;
    }

	public List<EsenredCComuni> getElencoComuni(String descComune) {
		return getDataDao().getElencoComuni(descComune);
	}
	public EsenredCComuni getElencoComuniPerCodIstat(String codistat) {
		return getDataDao().getElencoComuniPerCodIstat(codistat);
	}
}
