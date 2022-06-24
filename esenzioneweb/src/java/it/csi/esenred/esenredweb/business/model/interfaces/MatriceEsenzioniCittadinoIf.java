/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import it.csi.esenred.esenredweb.business.entity.EsenredCMatriceEsenzioni;

import java.util.List;

public interface MatriceEsenzioniCittadinoIf {

    List<EsenredCMatriceEsenzioni> getValue(String codEsenzioneEsistente, String codEsenzioneRichiesta);
}
