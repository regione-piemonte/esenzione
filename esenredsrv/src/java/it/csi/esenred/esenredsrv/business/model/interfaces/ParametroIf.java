/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.model.interfaces;

import it.csi.esenred.esenredsrv.business.entity.EsenredCParametri;

public interface ParametroIf {
	EsenredCParametri getParametro(String codice);
}