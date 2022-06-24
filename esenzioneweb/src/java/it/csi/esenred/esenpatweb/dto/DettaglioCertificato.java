/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.jboss.resteasy.util.DateUtil;

import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.entity.*;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

@JsonPropertyOrder({ "beneficiario", "id_certificato", "tipo_certificato", "esenzione", "diagnosi",
		"descrizione", "data_emissione", "utilizzabile", "sk_pratica" })
public class DettaglioCertificato {

	@JsonProperty("beneficiario")
	private Beneficiario beneficiario;
	@JsonProperty("id_certificato")
	private String idCertificato;
	@JsonProperty("tipo_certificato")
	private String tipoCertificato;
	@JsonProperty("esenzione")
	private Tipologia esenzione;
	@JsonProperty("diagnosi")
	private Diagnosi diagnosi;
	@JsonProperty("descrizione")
	private String descrizione;
	@JsonProperty("data_emissione")
	private String dataEmissione;
	@JsonProperty("utilizzabile")
	private boolean utilizzabile;
	@JsonProperty("sk_pratica")
	private Integer skPratica;

	public DettaglioCertificato() {
	}

	public DettaglioCertificato(EsenzioneTDocumento doc, EsenzioneTCittadino cit) {

		this.beneficiario = new Beneficiario(cit);
		// this.nomeBeneficiario = cit.getNome() != null ? cit.getNome() : "";
		// this.cognomeBeneficiario = cit.getCognome() != null ? cit.getCognome() : "";
		this.idCertificato = doc.getSkDocumento() != null ? doc.getSkDocumento().toString() : "";
		this.tipoCertificato = doc.getEsenzioneDDiagnosi() != null
				? doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getEsenzioneDGruppoEsenzioni().getDescGruppo()
				: "";
		this.esenzione = new Tipologia();
		this.esenzione.setCodice(doc.getEsenzioneDDiagnosi() != null
				? doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione()
				: "");
		this.esenzione.setDescrizione(doc.getEsenzioneDDiagnosi() != null
				? doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getDescEsenzione()
				: "");
		this.diagnosi = new Diagnosi(
				doc.getEsenzioneDDiagnosi() != null ? doc.getEsenzioneDDiagnosi() : new EsenzioneDDiagnosi());
		this.descrizione = doc.getDescEstesaPatologiaCertificato() != null ? doc.getDescEstesaPatologiaCertificato()
				: "";
		this.dataEmissione = doc.getDatCreazione() != null
				? Converter.getStringDataFromTimeStamp(doc.getDatCreazione(), Constants.DATE_FORMAT_ITALIAN)
				: "";
		this.utilizzabile = (doc.getEsenzioneRPraticaEsenzioneDocumentos() == null
				|| doc.getEsenzioneRPraticaEsenzioneDocumentos().isEmpty());

		this.skPratica = (doc.getEsenzioneRPraticaEsenzioneDocumentos() == null
				|| doc.getEsenzioneRPraticaEsenzioneDocumentos().isEmpty()) ? null
						: doc.getEsenzioneRPraticaEsenzioneDocumentos().iterator().next()
								.getEsenzioneTPraticaEsenzione().getSkPraticaEsenzione();
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getTipoCertificato() {
		return tipoCertificato;
	}

	public void setTipoCertificato(String tipoCertificato) {
		this.tipoCertificato = tipoCertificato;
	}

	public Tipologia getEsenzione() {
		return esenzione;
	}

	public void setEsenzione(Tipologia esenzione) {
		this.esenzione = esenzione;
	}

	public Diagnosi getDiagnosi() {
		return diagnosi;
	}

	public void setDiagnosi(Diagnosi diagnosi) {
		this.diagnosi = diagnosi;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(String dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public boolean isUtilizzabile() {
		return utilizzabile;
	}

	public void setUtilizzabile(boolean utilizzabile) {
		this.utilizzabile = utilizzabile;
	}

	public String getIdCertificato() {
		return idCertificato;
	}

	public void setIdCertificato(String idCertificato) {
		this.idCertificato = idCertificato;
	}

	public Integer getSkPratica() {
		return skPratica;
	}

	public void setSkPratica(Integer skPratica) {
		this.skPratica = skPratica;
	}

}
