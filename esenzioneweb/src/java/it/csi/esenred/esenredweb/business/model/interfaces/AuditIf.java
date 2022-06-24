/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;

public interface AuditIf {
	
	void insertAudit(CsiLogAudit audit);
	
	boolean updateAudit(CsiLogAudit audit);
	
	CsiLogAudit findAudit(Long idAudit);
	
	List<CsiLogAudit> findAllAudit();

	void insertAuditEsenpat(CsiLogAudit audit);

	boolean updateAuditEsenpat(CsiLogAudit audit);

	CsiLogAudit findAuditEsenpat(Long idAudit);

	List<CsiLogAudit> findAllAuditEsenpat();
}
