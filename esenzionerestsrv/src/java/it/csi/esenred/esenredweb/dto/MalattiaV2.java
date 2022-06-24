/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPrestazioneSpecialistica;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRDiagnosiPrestazione;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codice", "descrizione", "giorni_validita", "prestazioni", "codice_esenzione" })
public class MalattiaV2 {

	@JsonProperty("codice")
	private String codice;

	@JsonProperty("descrizione")
	private String descrizione;

	@JsonProperty("giorni_validita")
	private Integer giorni_validita;

	@JsonProperty("prestazioni")
	private List<Prestazioni> prestazioni = null;

	@JsonProperty("codice_esenzione")
	private String codiceEsenzione;

	public MalattiaV2() {

	}

	public MalattiaV2(EsenzioneDDiagnosi diag) {
		this.codice = diag.getCodDiagnosi();
		this.descrizione = diag.getDescDiagnosi();
		this.giorni_validita = null; 
		this.prestazioni = new ArrayList<Prestazioni>();
		if (diag.getEsenzioneRDiagnosiPrestaziones() != null && !diag.getEsenzioneRDiagnosiPrestaziones().isEmpty()) {
			Set<EsenzioneRDiagnosiPrestazione> rDiagnosiPrestaziones = diag.getEsenzioneRDiagnosiPrestaziones();
			for (EsenzioneRDiagnosiPrestazione r : rDiagnosiPrestaziones) {
				EsenzioneDPrestazioneSpecialistica prest = r.getEsenzioneDPrestazioneSpecialistica();
				Prestazioni p = new Prestazioni();
				p.setCodice(prest.getCodPrestazione());
				p.setDescrizione(prest.getDescPrestazione());
				this.prestazioni.add(p);
			}
		}
		if (diag.getEsenzioneDEsenzione() != null) {
			this.codiceEsenzione = diag.getEsenzioneDEsenzione().getCodEsenzione();
		}
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getGiorni_validita() {
		return giorni_validita;
	}

	public void setGiorni_validita(Integer giorni_validita) {
		this.giorni_validita = giorni_validita;
	}

	public List<Prestazioni> getPrestazioni() {
		return prestazioni;
	}

	public void setPrestazioni(List<Prestazioni> prestazioni) {
		this.prestazioni = prestazioni;
	}
}
