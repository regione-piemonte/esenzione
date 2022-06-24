/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;


public interface CertificatiPatologiaIf {

	List<EsenzioneTDocumento> getCertificatiPatologia(FiltriRicercaCertificatoPatologia filtri,String codAsl,int pagesize, String cfMed);
	Long ContaCertificatiPatologia(FiltriRicercaCertificatoPatologia filtri,String codAsl, String cfMed);
}
