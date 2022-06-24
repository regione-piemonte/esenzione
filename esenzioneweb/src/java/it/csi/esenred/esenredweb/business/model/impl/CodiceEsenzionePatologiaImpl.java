/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.model.interfaces.CodiceEsenzionePatologiaIf;


public class CodiceEsenzionePatologiaImpl implements CodiceEsenzionePatologiaIf {

	private DataDaoPatologiaIf dataDao;

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}
	public List<EsenzioneDEsenzione> getCodiceEsenzionePatologia(String codTipologiaGruppo) {
		return getDataDao().getCodiceEsenzionePatologia(codTipologiaGruppo);
	}
}
