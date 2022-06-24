/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDAzione;
import it.csi.esenred.esenredweb.business.model.interfaces.RuoliIf;

public class RuoliImpl implements RuoliIf{
	private DataDaoPatologiaIf dataDao;

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}

	@Override
	public List<EsenzioneDAzione> getAzioni(List<Ruolo> ruoli) {
		return getDataDao().getAzioni(ruoli);
	}

}
