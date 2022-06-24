/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ESENZIONE_D_ERRORI_ESENPAT" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_ERRORI_ESENPAT\"")
@NamedQuery(name="EsenzioneDErroriEsenpat.findAll", query="SELECT e FROM EsenzioneDErroriEsenpat e")
public class EsenzioneDErroriEsenpat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"SK_ERRORI_ESENPAT\"")
	private Integer skErroriEsenpat;

	@Column(name="\"COD_ERRORI_ESENPAT\"")
	private String codErroriEsenpat;

	@Column(name="\"DESCRIPTION\"")
	private String description;

	//bi-directional many-to-one association to EsenzioneRErroriGatewayFirmaErroriEsenpat
	@OneToMany(mappedBy="esenzioneDErroriEsenpat")
	private List<EsenzioneRErroriGatewayFirmaErroriEsenpat> esenzioneRErroriGatewayFirmaErroriEsenpats;

	public EsenzioneDErroriEsenpat() {
	}

	public Integer getSkErroriEsenpat() {
		return this.skErroriEsenpat;
	}

	public void setSkErroriEsenpat(Integer skErroriEsenpat) {
		this.skErroriEsenpat = skErroriEsenpat;
	}

	public String getCodErroriEsenpat() {
		return this.codErroriEsenpat;
	}

	public void setCodErroriEsenpat(String codErroriEsenpat) {
		this.codErroriEsenpat = codErroriEsenpat;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EsenzioneRErroriGatewayFirmaErroriEsenpat> getEsenzioneRErroriGatewayFirmaErroriEsenpats() {
		return this.esenzioneRErroriGatewayFirmaErroriEsenpats;
	}

	public void setEsenzioneRErroriGatewayFirmaErroriEsenpats(List<EsenzioneRErroriGatewayFirmaErroriEsenpat> esenzioneRErroriGatewayFirmaErroriEsenpats) {
		this.esenzioneRErroriGatewayFirmaErroriEsenpats = esenzioneRErroriGatewayFirmaErroriEsenpats;
	}

	public EsenzioneRErroriGatewayFirmaErroriEsenpat addEsenzioneRErroriGatewayFirmaErroriEsenpat(EsenzioneRErroriGatewayFirmaErroriEsenpat esenzioneRErroriGatewayFirmaErroriEsenpat) {
		getEsenzioneRErroriGatewayFirmaErroriEsenpats().add(esenzioneRErroriGatewayFirmaErroriEsenpat);
		esenzioneRErroriGatewayFirmaErroriEsenpat.setEsenzioneDErroriEsenpat(this);

		return esenzioneRErroriGatewayFirmaErroriEsenpat;
	}

	public EsenzioneRErroriGatewayFirmaErroriEsenpat removeEsenzioneRErroriGatewayFirmaErroriEsenpat(EsenzioneRErroriGatewayFirmaErroriEsenpat esenzioneRErroriGatewayFirmaErroriEsenpat) {
		getEsenzioneRErroriGatewayFirmaErroriEsenpats().remove(esenzioneRErroriGatewayFirmaErroriEsenpat);
		esenzioneRErroriGatewayFirmaErroriEsenpat.setEsenzioneDErroriEsenpat(null);

		return esenzioneRErroriGatewayFirmaErroriEsenpat;
	}

}