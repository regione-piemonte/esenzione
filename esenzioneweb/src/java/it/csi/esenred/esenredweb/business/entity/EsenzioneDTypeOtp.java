/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ESENZIONE_D_TYPE_OTP" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_TYPE_OTP\"")
@NamedQuery(name="EsenzioneDTypeOtp.findAll", query="SELECT e FROM EsenzioneDTypeOtp e")
public class EsenzioneDTypeOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"SK_TYPE_OTP\"")
	private Integer skTypeOtp;

	@Column(name="\"COD_TYPE_OTP\"")
	private String codTypeOtp;

	@Column(name="\"DESC_TYPE_OTP\"")
	private String descTypeOtp;

	//bi-directional many-to-one association to EsenzioneRCaTypeOtp
	@OneToMany(mappedBy="esenzioneDTypeOtp")
	private List<EsenzioneRCaTypeOtp> esenzioneRCaTypeOtps;

	public EsenzioneDTypeOtp() {
	}

	public Integer getSkTypeOtp() {
		return this.skTypeOtp;
	}

	public void setSkTypeOtp(Integer skTypeOtp) {
		this.skTypeOtp = skTypeOtp;
	}

	public String getCodTypeOtp() {
		return this.codTypeOtp;
	}

	public void setCodTypeOtp(String codTypeOtp) {
		this.codTypeOtp = codTypeOtp;
	}

	public String getDescTypeOtp() {
		return this.descTypeOtp;
	}

	public void setDescTypeOtp(String descTypeOtp) {
		this.descTypeOtp = descTypeOtp;
	}

	public List<EsenzioneRCaTypeOtp> getEsenzioneRCaTypeOtps() {
		return this.esenzioneRCaTypeOtps;
	}

	public void setEsenzioneRCaTypeOtps(List<EsenzioneRCaTypeOtp> esenzioneRCaTypeOtps) {
		this.esenzioneRCaTypeOtps = esenzioneRCaTypeOtps;
	}

	public EsenzioneRCaTypeOtp addEsenzioneRCaTypeOtp(EsenzioneRCaTypeOtp esenzioneRCaTypeOtp) {
		getEsenzioneRCaTypeOtps().add(esenzioneRCaTypeOtp);
		esenzioneRCaTypeOtp.setEsenzioneDTypeOtp(this);

		return esenzioneRCaTypeOtp;
	}

	public EsenzioneRCaTypeOtp removeEsenzioneRCaTypeOtp(EsenzioneRCaTypeOtp esenzioneRCaTypeOtp) {
		getEsenzioneRCaTypeOtps().remove(esenzioneRCaTypeOtp);
		esenzioneRCaTypeOtp.setEsenzioneDTypeOtp(null);

		return esenzioneRCaTypeOtp;
	}

}