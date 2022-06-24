/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.util.List;

import it.csi.esenred.esenredweb.business.entity.EsenredWNotifiche;

public interface NotificaIf {
	
	void updateNotifica(EsenredWNotifiche notifica);

	void insert(EsenredWNotifiche notifica);
	
	List<EsenredWNotifiche> getNotificheOperatore(Long idOperatore);
	
	List<EsenredWNotifiche> getNotificheCittadino(Long idCittadino);
	
	boolean deleteNotifica(Long idNotifica);

	boolean updateSetConsultata(Long idNotifica);
}
