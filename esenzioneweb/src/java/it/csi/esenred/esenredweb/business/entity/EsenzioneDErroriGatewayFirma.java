/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ESENZIONE_D_ERRORI_GATEWAY_FIRMA" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_ERRORI_GATEWAY_FIRMA\"")
@NamedQuery(name="EsenzioneDErroriGatewayFirma.findAll", query="SELECT e FROM EsenzioneDErroriGatewayFirma e")
public class EsenzioneDErroriGatewayFirma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"SK_ERRORI_GATEWAY_FIRMA\"")
	private Integer skErroriGatewayFirma;

	@Column(name="\"COD_ERRORI_GATEWAY_FIRMA\"")
	private String codErroriGatewayFirma;

	@Column(name="\"DESCRIPTION\"")
	private String description;

	@Column(name="\"ERROR_CODE\"")
	private String errorCode;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Column(name="\"ORIGINAL_RETURN_CODE\"")
	private String originalReturnCode;

	//bi-directional many-to-one association to EsenzioneRErroriGatewayFirmaErroriEsenpat
	@OneToMany(mappedBy="esenzioneDErroriGatewayFirma")
	private List<EsenzioneRErroriGatewayFirmaErroriEsenpat> esenzioneRErroriGatewayFirmaErroriEsenpats;

	public EsenzioneDErroriGatewayFirma() {
	}

	public Integer getSkErroriGatewayFirma() {
		return this.skErroriGatewayFirma;
	}

	public void setSkErroriGatewayFirma(Integer skErroriGatewayFirma) {
		this.skErroriGatewayFirma = skErroriGatewayFirma;
	}

	public String getCodErroriGatewayFirma() {
		return this.codErroriGatewayFirma;
	}

	public void setCodErroriGatewayFirma(String codErroriGatewayFirma) {
		this.codErroriGatewayFirma = codErroriGatewayFirma;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOriginalReturnCode() {
		return this.originalReturnCode;
	}

	public void setOriginalReturnCode(String originalReturnCode) {
		this.originalReturnCode = originalReturnCode;
	}

	public List<EsenzioneRErroriGatewayFirmaErroriEsenpat> getEsenzioneRErroriGatewayFirmaErroriEsenpats() {
		return this.esenzioneRErroriGatewayFirmaErroriEsenpats;
	}

	public void setEsenzioneRErroriGatewayFirmaErroriEsenpats(List<EsenzioneRErroriGatewayFirmaErroriEsenpat> esenzioneRErroriGatewayFirmaErroriEsenpats) {
		this.esenzioneRErroriGatewayFirmaErroriEsenpats = esenzioneRErroriGatewayFirmaErroriEsenpats;
	}

	public EsenzioneRErroriGatewayFirmaErroriEsenpat addEsenzioneRErroriGatewayFirmaErroriEsenpat(EsenzioneRErroriGatewayFirmaErroriEsenpat esenzioneRErroriGatewayFirmaErroriEsenpat) {
		getEsenzioneRErroriGatewayFirmaErroriEsenpats().add(esenzioneRErroriGatewayFirmaErroriEsenpat);
		esenzioneRErroriGatewayFirmaErroriEsenpat.setEsenzioneDErroriGatewayFirma(this);

		return esenzioneRErroriGatewayFirmaErroriEsenpat;
	}

	public EsenzioneRErroriGatewayFirmaErroriEsenpat removeEsenzioneRErroriGatewayFirmaErroriEsenpat(EsenzioneRErroriGatewayFirmaErroriEsenpat esenzioneRErroriGatewayFirmaErroriEsenpat) {
		getEsenzioneRErroriGatewayFirmaErroriEsenpats().remove(esenzioneRErroriGatewayFirmaErroriEsenpat);
		esenzioneRErroriGatewayFirmaErroriEsenpat.setEsenzioneDErroriGatewayFirma(null);

		return esenzioneRErroriGatewayFirmaErroriEsenpat;
	}

}