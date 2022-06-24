/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;

public interface ComuneIf {

    List<EsenredCComuni> getElencoComuni(String descComune);
    EsenredCComuni getElencoComuniPerCodIstat(String codistat);
}
