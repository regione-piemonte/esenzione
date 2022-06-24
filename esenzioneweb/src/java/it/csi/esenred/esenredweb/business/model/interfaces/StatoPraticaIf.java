/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenpatweb.dto.Motivazione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;


public interface StatoPraticaIf {

	List<EsenzioneDPraticaStato> getStatoPratica(String skAzione);

	List<Motivazione> getMotivazioniByCodStatoPratica(String codStato);

}
