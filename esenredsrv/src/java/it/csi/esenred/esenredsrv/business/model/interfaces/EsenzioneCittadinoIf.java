/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.model.interfaces;

import java.util.Date;

import it.csi.esenred.esenredsrv.business.entity.EsenredTEsenzioniReddito;

public interface EsenzioneCittadinoIf {
	EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio);

	void update(EsenredTEsenzioniReddito esenzioneDB);

}
