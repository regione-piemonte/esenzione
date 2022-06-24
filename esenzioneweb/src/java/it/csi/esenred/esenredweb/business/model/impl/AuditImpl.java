/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.model.interfaces.AuditIf;

public class AuditImpl implements AuditIf {

	private DataDaoIf dataDao;
	private DataDaoPatologiaIf dataDaoPatologia;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public DataDaoPatologiaIf getDataDaoPatologia() {
		return dataDaoPatologia;
	}

	public void setDataDaoPatologia(DataDaoPatologiaIf dataDaoPatologia) {
		this.dataDaoPatologia = dataDaoPatologia;
	}

	@Override
	public void insertAudit(CsiLogAudit audit) {
		dataDao.insertAudit(audit);
	}

	@Override
	public boolean updateAudit(CsiLogAudit audit) {
		return dataDao.updateAudit(audit);
	}

	@Override
	public CsiLogAudit findAudit(Long idAudit) {
		return dataDao.findAudit(idAudit);
	}

	@Override
	public List<CsiLogAudit> findAllAudit() {
		return dataDao.findAllAudit();
	}

	@Override
	public void insertAuditEsenpat(CsiLogAudit audit) {
		dataDaoPatologia.insertAudit(audit);
	}

	@Override
	public boolean updateAuditEsenpat(CsiLogAudit audit) {
		return dataDaoPatologia.updateAudit(audit);
	}

	@Override
	public CsiLogAudit findAuditEsenpat(Long idAudit) {
		return dataDaoPatologia.findAudit(idAudit);
	}

	@Override
	public List<CsiLogAudit> findAllAuditEsenpat() {
		return dataDaoPatologia.findAllAudit();
	}

}
