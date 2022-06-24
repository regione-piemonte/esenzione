/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.bo;

public class RevocaEsenzioneBO {
	
	private Long idEsenzione;
	
	private String motivoRevocaEsenzione;
	
	private String notaInternaRevoca;

	private boolean blocco;
	
	
	public Long getIdEsenzione() {
		return idEsenzione;
	}

	public void setIdEsenzione(Long idEsenzione) {
		this.idEsenzione = idEsenzione;
	}

	public String getNotaInternaRevoca() {
		return notaInternaRevoca;
	}

	public void setNotaInternaRevoca(String notaInternaRevoca) {
		this.notaInternaRevoca = notaInternaRevoca;
	}
	
	public String getMotivoRevocaEsenzione() {
		return motivoRevocaEsenzione;
	}

	public void setMotivoRevocaEsenzione(String motivoRevocaEsenzione) {
		this.motivoRevocaEsenzione = motivoRevocaEsenzione;
	}
	public RevocaEsenzioneBO(Long idEsenzione, String motivoRevocaEsenzione, String notaInternaRevoca) {
		this.idEsenzione = idEsenzione;
		this.motivoRevocaEsenzione = motivoRevocaEsenzione;
		this.notaInternaRevoca = notaInternaRevoca;
	}

	public RevocaEsenzioneBO() {
	}

	public boolean isBlocco() {
		return blocco;
	}

	public void setBlocco(boolean blocco) {
		this.blocco = blocco;
	}


}
