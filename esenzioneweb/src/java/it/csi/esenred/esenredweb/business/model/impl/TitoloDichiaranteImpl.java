/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;

public class TitoloDichiaranteImpl implements TitoloDichiaranteIf {

	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public List<EsenredCTitoloDichiarante> getTitoli(String codTitolo) {
		return getDataDao().getTitoli(codTitolo);
	}

	public List<EsenredCTitoloDichiarante> getTitoliFamiliare() {
		return getDataDao().getTitoliFamiliare();
	}
}