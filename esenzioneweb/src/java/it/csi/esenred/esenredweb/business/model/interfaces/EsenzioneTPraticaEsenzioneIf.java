/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;



public interface EsenzioneTPraticaEsenzioneIf {

	EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzioneperskpratica(String skpraticaesenzione);
	
	List<EsenzioneTPraticaEsenzione> getEsenzionePatologia(FiltriRicercaCertificatoPatologia filtri, String codasl, String cfMed);
	List<EsenzioneTPraticaEsenzione> getRicercaCertificato(FiltriRicercaCertificatoPatologia filtri, String codasl,int pagesize);
	Long ContaRicercaCertificato(FiltriRicercaCertificatoPatologia filtri,String codAsl);
}
