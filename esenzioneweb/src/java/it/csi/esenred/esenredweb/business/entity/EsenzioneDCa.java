/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ESENZIONE_D_CA" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_D_CA\"")
@NamedQuery(name="EsenzioneDCa.findAll", query="SELECT e FROM EsenzioneDCa e")
public class EsenzioneDCa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"SK_CA\"")
	private Integer skCa;

	@Column(name="\"COD_CA\"")
	private String codCa;

	@Column(name="\"DESC_CA\"")
	private String descCa;

	//bi-directional many-to-one association to EsenzioneRCaTypeOtp
	@OneToMany(mappedBy="esenzioneDCa")
	private List<EsenzioneRCaTypeOtp> esenzioneRCaTypeOtps;

	public EsenzioneDCa() {
	}

	public Integer getSkCa() {
		return this.skCa;
	}

	public void setSkCa(Integer skCa) {
		this.skCa = skCa;
	}

	public String getCodCa() {
		return this.codCa;
	}

	public void setCodCa(String codCa) {
		this.codCa = codCa;
	}

	public String getDescCa() {
		return this.descCa;
	}

	public void setDescCa(String descCa) {
		this.descCa = descCa;
	}

	public List<EsenzioneRCaTypeOtp> getEsenzioneRCaTypeOtps() {
		return this.esenzioneRCaTypeOtps;
	}

	public void setEsenzioneRCaTypeOtps(List<EsenzioneRCaTypeOtp> esenzioneRCaTypeOtps) {
		this.esenzioneRCaTypeOtps = esenzioneRCaTypeOtps;
	}

	public EsenzioneRCaTypeOtp addEsenzioneRCaTypeOtp(EsenzioneRCaTypeOtp esenzioneRCaTypeOtp) {
		getEsenzioneRCaTypeOtps().add(esenzioneRCaTypeOtp);
		esenzioneRCaTypeOtp.setEsenzioneDCa(this);

		return esenzioneRCaTypeOtp;
	}

	public EsenzioneRCaTypeOtp removeEsenzioneRCaTypeOtp(EsenzioneRCaTypeOtp esenzioneRCaTypeOtp) {
		getEsenzioneRCaTypeOtps().remove(esenzioneRCaTypeOtp);
		esenzioneRCaTypeOtp.setEsenzioneDCa(null);

		return esenzioneRCaTypeOtp;
	}

}