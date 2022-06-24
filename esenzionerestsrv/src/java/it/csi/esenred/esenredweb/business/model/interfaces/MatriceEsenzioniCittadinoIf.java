/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenredweb.business.entity.EsenredCMatriceEsenzioni;

public interface MatriceEsenzioniCittadinoIf {

    List<EsenredCMatriceEsenzioni> getValue(String codEsenzioneEsistente, String codEsenzioneRichiesta);
}
