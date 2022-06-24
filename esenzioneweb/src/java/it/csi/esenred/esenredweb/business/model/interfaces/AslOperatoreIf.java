/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import it.csi.esenred.esenredweb.business.entity.EsenredTAslOperatore;;

public interface AslOperatoreIf {
	EsenredTAslOperatore getAslOperatoreCF(String CodiceFiscaleOperatore);
}
