/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenredweb.business.entity.*;

public interface RuoliIf {
	List<EsenzioneDAzione> getAzioni(List<Ruolo> ruoli);

	//List<EsenzioneDAzione> getAzioni(String ruolo);
}
