/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import java.util.Date;

import it.csi.esenred.esenredweb.business.entity.EsenredWNotifiche;

public class Notifica {
	
	private Long idNotifica;
	
	private String descNotifica;
	
	private String codTipologia;
	
	private Date dataNotifica;
	
	private boolean isConsultata;
	
	private Long idOperatore;
	
	private Long idCittadino;

	public Notifica(EsenredWNotifiche nDB) {
		this.idNotifica = nDB.getIdNotifica();
		this.descNotifica = nDB.getDescNotifica();
		this.codTipologia = nDB.getCodTipologia();
		this.dataNotifica = nDB.getData();
		this.isConsultata = nDB.getFlagConsultazione().intValue() == 0 ? false : true;
		this.idOperatore = nDB.getIdOperatore();
		this.idCittadino = nDB.getIdAura();
	}

	public Long getIdNotifica() {
		return idNotifica;
	}

	public void setIdNotifica(Long idNotifica) {
		this.idNotifica = idNotifica;
	}

	public String getDescNotifica() {
		return descNotifica;
	}

	public void setDescNotifica(String descNotifica) {
		this.descNotifica = descNotifica;
	}

	public String getCodTipologia() {
		return codTipologia;
	}

	public void setCodTipologia(String codTipologia) {
		this.codTipologia = codTipologia;
	}

	public Date getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}

	public boolean isConsultata() {
		return isConsultata;
	}

	public void setConsultata(boolean isConsultata) {
		this.isConsultata = isConsultata;
	}

	public Long getIdOperatore() {
		return idOperatore;
	}

	public void setIdOperatore(Long idOperatore) {
		this.idOperatore = idOperatore;
	}

	public Long getIdCittadino() {
		return idCittadino;
	}

	public void setIdCittadino(Long idCittadino) {
		this.idCittadino = idCittadino;
	}
}