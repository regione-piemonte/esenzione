/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.Map;

public interface ReportIf {
	
	byte[] getReport(Map<String, Object> parameters);
	
	byte[] getReportStorico(Map<String, Object> parameters);

}
