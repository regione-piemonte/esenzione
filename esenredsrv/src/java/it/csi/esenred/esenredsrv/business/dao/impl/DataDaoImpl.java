/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.esenred.esenredsrv.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredsrv.business.entity.EsenredCComuni;
import it.csi.esenred.esenredsrv.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredsrv.business.entity.EsenredCParametri;
import it.csi.esenred.esenredsrv.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredsrv.business.entity.EsenredWNotifiche;
import it.csi.esenred.esenredsrv.util.Util;

public class DataDaoImpl implements DataDaoIf{

	@PersistenceContext
	private EntityManager em;
	

	public List<EsenredCComuni> getElencoComuni() {
		TypedQuery<EsenredCComuni> query = em.createNamedQuery("EsenredCComuni.findAll", EsenredCComuni.class);
		return query.getResultList();
	}
	
	public EsenredCMessaggi getMessaggio(String codice) {
		TypedQuery<EsenredCMessaggi> query = em.createNamedQuery("EsenredCMessaggi.findMessaggio", EsenredCMessaggi.class);
		query.setParameter("codice", codice);
		return query.getSingleResult();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertNotifica(EsenredWNotifiche notifica) {
		em.persist(notifica);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateNotifica(EsenredWNotifiche notifica) {
		em.merge(notifica);
	}

	public EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio) {
		TypedQuery<EsenredTEsenzioniReddito> query = em.createNamedQuery("EsenredTEsenzioniReddito.findEsenzione", EsenredTEsenzioniReddito.class);
		query.setParameter("idAura", idAura);
		query.setParameter("codEsenzione", codEsenzione);
		query.setParameter("dataInizio", dataInizio);
		return Util.getSingleResult(query);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateEsenzioneCittadino(EsenredTEsenzioniReddito esenzione) {
		em.merge(esenzione);
	}

	public EsenredCParametri getParametro(String codice) {
		TypedQuery<EsenredCParametri> query = em.createNamedQuery("EsenredCParametri.findParametro", EsenredCParametri.class);
		query.setParameter("codice", codice);
		return query.getSingleResult();
	}

}