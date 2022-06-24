/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.Map;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ReportIf;

public class ReportImpl  implements ReportIf {
	
	private DataDaoIf dataDao;

    public DataDaoIf getDataDao() {
        return dataDao;
    }
    
    public void setDataDao(DataDaoIf dataDao) {
        this.dataDao = dataDao;
    }
    
	public byte[] getReport(Map<String, Object> parameters) {
		return getDataDao().getReport(parameters);
	}
	
	public byte[] getReportStorico(Map<String, Object> parameters) {
		return getDataDao().getReportStorico(parameters);
	}
}
