/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;

public interface StatoDocumentoIf {

	List<EsenzioneDDocumentoStato> getStatoDocumento(String codStato);

}
