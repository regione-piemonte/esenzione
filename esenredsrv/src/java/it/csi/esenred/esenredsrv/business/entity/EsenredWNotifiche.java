/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "ESENRED_W_NOTIFICHE" database table.
 * 
 */
@Entity
@Table(name="\"ESENRED_W_NOTIFICHE\"")
@SequenceGenerator(name="\"SEQ_ESENRED_W_NOTIFICHE\"",  sequenceName="\"SEQ_ESENRED_W_NOTIFICHE\"", initialValue=1, allocationSize=1)
@NamedQueries({ 
	@NamedQuery(name="EsenredWNotifiche.findAll", query="SELECT e FROM EsenredWNotifiche e"),
	@NamedQuery(name="EsenredWNotifiche.findNotifica", query="SELECT r FROM EsenredWNotifiche r where r.idNotifica = :idNotifica")
})
public class EsenredWNotifiche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="\"SEQ_ESENRED_W_NOTIFICHE\"")
	@Column(name="\"ID_NOTIFICA\"")
	private Long idNotifica;

	@Column(name="\"COD_TIPOLOGIA\"")
	private String codTipologia;

	@Temporal(TemporalType.DATE)
	@Column(name="\"DATA\"")
	private Date data;

	@Column(name="\"DESC_NOTIFICA\"")
	private String descNotifica;

	@Column(name="\"FLAG_CONSULTAZIONE\"")
	private Integer flagConsultazione;

	@Column(name="\"ID_AURA\"")
	private Long idAura;

	@Column(name="\"ID_OPERATORE\"")
	private Long idOperatore;

	public EsenredWNotifiche() {
	}

	public Long getIdNotifica() {
		return this.idNotifica;
	}

	public void setIdNotifica(Long idNotifica) {
		this.idNotifica = idNotifica;
	}

	public String getCodTipologia() {
		return this.codTipologia;
	}

	public void setCodTipologia(String codTipologia) {
		this.codTipologia = codTipologia;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescNotifica() {
		return this.descNotifica;
	}

	public void setDescNotifica(String descNotifica) {
		this.descNotifica = descNotifica;
	}

	public Integer getFlagConsultazione() {
		return this.flagConsultazione;
	}

	public void setFlagConsultazione(Integer flagConsultazione) {
		this.flagConsultazione = flagConsultazione;
	}

	public Long getIdAura() {
		return this.idAura;
	}

	public void setIdAura(Long idAura) {
		this.idAura = idAura;
	}

	public Long getIdOperatore() {
		return this.idOperatore;
	}

	public void setIdOperatore(Long idOperatore) {
		this.idOperatore = idOperatore;
	}


}