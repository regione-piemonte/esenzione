/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "ESENZIONE_R_CA_TYPE_OTP" database table.
 * 
 */
@Embeddable
public class EsenzioneRCaTypeOtpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"SK_CA\"", insertable=false, updatable=false)
	private Integer skCa;

	@Column(name="\"SK_TYPE_OTP\"", insertable=false, updatable=false)
	private Integer skTypeOtp;

	public EsenzioneRCaTypeOtpPK() {
	}
	public Integer getSkCa() {
		return this.skCa;
	}
	public void setSkCa(Integer skCa) {
		this.skCa = skCa;
	}
	public Integer getSkTypeOtp() {
		return this.skTypeOtp;
	}
	public void setSkTypeOtp(Integer skTypeOtp) {
		this.skTypeOtp = skTypeOtp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EsenzioneRCaTypeOtpPK)) {
			return false;
		}
		EsenzioneRCaTypeOtpPK castOther = (EsenzioneRCaTypeOtpPK)other;
		return 
			this.skCa.equals(castOther.skCa)
			&& this.skTypeOtp.equals(castOther.skTypeOtp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.skCa.hashCode();
		hash = hash * prime + this.skTypeOtp.hashCode();
		
		return hash;
	}
}