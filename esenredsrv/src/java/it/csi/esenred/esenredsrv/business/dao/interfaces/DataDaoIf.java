/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.dao.interfaces;

import java.util.Date;
import java.util.List;

import it.csi.esenred.esenredsrv.business.entity.EsenredCComuni;
import it.csi.esenred.esenredsrv.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredsrv.business.entity.EsenredCParametri;
import it.csi.esenred.esenredsrv.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredsrv.business.entity.EsenredWNotifiche;

public interface DataDaoIf {
	
	public List<EsenredCComuni> getElencoComuni();
	
	public EsenredCMessaggi getMessaggio(String codice);

	public void updateNotifica(EsenredWNotifiche notifica);
	
	public EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio);

	public void updateEsenzioneCittadino(EsenredTEsenzioniReddito esenzione);
	
	public void insertNotifica(EsenredWNotifiche notifica);

	public EsenredCParametri getParametro(String codice);

}
