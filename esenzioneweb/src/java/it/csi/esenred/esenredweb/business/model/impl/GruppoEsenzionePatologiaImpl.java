/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.model.interfaces.GruppoEsenzionePatologiaIf;


public class GruppoEsenzionePatologiaImpl implements GruppoEsenzionePatologiaIf {

	private DataDaoPatologiaIf dataDao;

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}
	public List<EsenzioneDGruppoEsenzioni> getGruppoEsenzionePatologia(String codTipologiaGruppo) {
		return getDataDao().getGruppoEsenzionePatologia(codTipologiaGruppo);
	}
	public List<EsenzioneDGruppoEsenzioni> getGruppoEsenzionePatologiaInCombo(int incombo) {
		return getDataDao().getGruppoEsenzionePatologiaInCombo(incombo);
	}
}
