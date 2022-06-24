/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_R_DESTINATARIO_TIPO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_R_DESTINATARIO_TIPO\"")
@NamedQuery(name="EsenzioneRDestinatarioTipo.findAll", query="SELECT e FROM EsenzioneRDestinatarioTipo e")
public class EsenzioneRDestinatarioTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"SK_DESTINATARIO_TIPO\"", unique=true, nullable=false)
	private Integer skDestinatarioTipo;

	@Column(name="\"COD_DESTINATARIO_TIPO\"", nullable=false, length=2147483647)
	private String codDestinatarioTipo;

	@Column(name="\"DESC_DESTINATRIO_TIPO\"", nullable=false, length=2147483647)
	private String descDestinatrioTipo;

	//bi-directional many-to-one association to EsenzioneDNotificaTipo
	@OneToMany(mappedBy="esenzioneRDestinatarioTipo")
	private List<EsenzioneDNotificaTipo> esenzioneDNotificaTipos;

	public EsenzioneRDestinatarioTipo() {
	}

	public Integer getSkDestinatarioTipo() {
		return this.skDestinatarioTipo;
	}

	public void setSkDestinatarioTipo(Integer skDestinatarioTipo) {
		this.skDestinatarioTipo = skDestinatarioTipo;
	}

	public String getCodDestinatarioTipo() {
		return this.codDestinatarioTipo;
	}

	public void setCodDestinatarioTipo(String codDestinatarioTipo) {
		this.codDestinatarioTipo = codDestinatarioTipo;
	}

	public String getDescDestinatrioTipo() {
		return this.descDestinatrioTipo;
	}

	public void setDescDestinatrioTipo(String descDestinatrioTipo) {
		this.descDestinatrioTipo = descDestinatrioTipo;
	}

	public List<EsenzioneDNotificaTipo> getEsenzioneDNotificaTipos() {
		return this.esenzioneDNotificaTipos;
	}

	public void setEsenzioneDNotificaTipos(List<EsenzioneDNotificaTipo> esenzioneDNotificaTipos) {
		this.esenzioneDNotificaTipos = esenzioneDNotificaTipos;
	}

	public EsenzioneDNotificaTipo addEsenzioneDNotificaTipo(EsenzioneDNotificaTipo esenzioneDNotificaTipo) {
		getEsenzioneDNotificaTipos().add(esenzioneDNotificaTipo);
		esenzioneDNotificaTipo.setEsenzioneRDestinatarioTipo(this);

		return esenzioneDNotificaTipo;
	}

	public EsenzioneDNotificaTipo removeEsenzioneDNotificaTipo(EsenzioneDNotificaTipo esenzioneDNotificaTipo) {
		getEsenzioneDNotificaTipos().remove(esenzioneDNotificaTipo);
		esenzioneDNotificaTipo.setEsenzioneRDestinatarioTipo(null);

		return esenzioneDNotificaTipo;
	}

}