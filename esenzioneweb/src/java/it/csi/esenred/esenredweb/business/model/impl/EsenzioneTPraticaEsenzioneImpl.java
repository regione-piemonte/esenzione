/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.util.List;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneTPraticaEsenzioneIf;


public class EsenzioneTPraticaEsenzioneImpl implements EsenzioneTPraticaEsenzioneIf {

	private DataDaoPatologiaIf dataDao;

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}
	public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzioneperskpratica(String skpraticaesenzione) {
		return getDataDao().getEsenzioneTPraticaEsenzioneperskpratica(skpraticaesenzione);
	}
	
	public List<EsenzioneTPraticaEsenzione> getEsenzionePatologia(FiltriRicercaCertificatoPatologia filtri, String codasl, String cfMed){
		return getDataDao().getEsenzionePatologia(filtri, codasl, cfMed);
	}
	
	public List<EsenzioneTPraticaEsenzione> getRicercaCertificato(FiltriRicercaCertificatoPatologia filtri, String codasl,int pagesize){
		return getDataDao().getRicercaCertificato(filtri, codasl,pagesize);
	}
	
	public Long ContaRicercaCertificato(FiltriRicercaCertificatoPatologia filtri,String codAsl) {
		return getDataDao().ContaRicercaCertificato(filtri, codAsl);
	}
}
