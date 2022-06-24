/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.model.interfaces.CertificatiPatologiaIf;


public class CertificatiPatologiaImpl implements CertificatiPatologiaIf {

	private DataDaoPatologiaIf dataDao;

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}
	public List<EsenzioneTDocumento> getCertificatiPatologia(FiltriRicercaCertificatoPatologia filtri, String codAsl,int pagesize, String cfMed) {
		return getDataDao().getCertificatiPatologia(filtri,codAsl,pagesize, cfMed);
	}
	
	public Long ContaCertificatiPatologia(FiltriRicercaCertificatoPatologia filtri,String codAsl, String cfMed) {
		return getDataDao().ContaCertificatiPatologia(filtri, codAsl, cfMed);
	}
}
