/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Map;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.model.interfaces.ReportIf;

public class ReportService {
	
	 private final ReportIf reportIf;

	    public ReportService() {
	        this.reportIf = (ReportIf) SpringApplicationContext.getBean("report");
	    }
	    
	public  byte[] createReport(Map<String, Object> parameters) {
		return reportIf.getReport(parameters);
	}
	
	public  byte[] createReportStorico(Map<String, Object> parameters) {
		return reportIf.getReportStorico(parameters);
	}
}
