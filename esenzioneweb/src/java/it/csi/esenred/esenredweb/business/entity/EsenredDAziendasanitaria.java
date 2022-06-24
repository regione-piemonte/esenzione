/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "ESENRED_D_AZIENDASANITARIA" database table.
 * 
 */
@Entity
@Table(name="\"ESENRED_D_AZIENDASANITARIA\"")
@NamedQuery(name="EsenredDAziendasanitaria.findAll", query="SELECT e FROM EsenredDAziendasanitaria e")
public class EsenredDAziendasanitaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID_AZIENDA\"", unique=true, nullable=false, length=6)
	private String idAzienda;

	@Column(name="\"COD_AZIENDA\"", nullable=false, length=3)
	private String codAzienda;

	@Column(name="\"COD_REGIONE\"", nullable=false, length=3)
	private String codRegione;

	@Temporal(TemporalType.DATE)
	@Column(name="\"DATA_ATTIVAZIONE\"", nullable=false)
	private Date dataAttivazione;

	@Temporal(TemporalType.DATE)
	@Column(name="\"DATA_CHIUSURA\"")
	private Date dataChiusura;

	@Column(name="\"DENOMINAZIONE\"", nullable=false, length=100)
	private String denominazione;
	
	
	@Column(name="\"DESC_AZIENDA\"")
	private String descAzienda;
	
	@Column(name="\"EMAIL\"")
	private String email;
	
	@Column(name="\"INDIRIZZO\"")
	private String indirizzo;
	
	@Column(name="\"PARTITA_IVA\"")
	private String partitaIva;
	
	@Column(name="\"RECAPITI_TELEFONICI\"")
	private String recapitiTelefonici;

//	//bi-directional many-to-one association to EsenredTEsenzioniReddito
//	@OneToMany(mappedBy="esenredDAziendasanitaria")
//	private List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos;

	//bi-directional many-to-one association to EsenzioneDDistrettoSocioSanitario
	@OneToMany(mappedBy="esenredDAziendasanitaria")
	private List<EsenzioneDDistrettoSocioSanitario> esenzioneDDistrettoSocioSanitarios;

	//bi-directional many-to-one association to EsenzioneSPraticaEsenzione
	@OneToMany(mappedBy="esenredDAziendasanitaria")
	private List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones;

	//bi-directional many-to-one association to EsenzioneTPraticaEsenzione
	@OneToMany(mappedBy="esenredDAziendasanitaria")
	private List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones;

	//bi-directional many-to-one association to EsenzioneTProgressivo
	@OneToMany(mappedBy="esenredDAziendasanitaria")
	private List<EsenzioneTProgressivo> esenzioneTProgressivos;

	public EsenredDAziendasanitaria() {
	}

	public String getIdAzienda() {
		return this.idAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.idAzienda = idAzienda;
	}

	public String getCodAzienda() {
		return this.codAzienda;
	}

	public void setCodAzienda(String codAzienda) {
		this.codAzienda = codAzienda;
	}

	public String getCodRegione() {
		return this.codRegione;
	}

	public void setCodRegione(String codRegione) {
		this.codRegione = codRegione;
	}

	public Date getDataAttivazione() {
		return this.dataAttivazione;
	}

	public void setDataAttivazione(Date dataAttivazione) {
		this.dataAttivazione = dataAttivazione;
	}

	public Date getDataChiusura() {
		return this.dataChiusura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	

	public String getDescAzienda() {
		return descAzienda;
	}

	public void setDescAzienda(String descAzienda) {
		this.descAzienda = descAzienda;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getRecapitiTelefonici() {
		return recapitiTelefonici;
	}

	public void setRecapitiTelefonici(String recapitiTelefonici) {
		this.recapitiTelefonici = recapitiTelefonici;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

//	public List<EsenredTEsenzioniReddito> getEsenredTEsenzioniRedditos() {
//		return this.esenredTEsenzioniRedditos;
//	}
//
//	public void setEsenredTEsenzioniRedditos(List<EsenredTEsenzioniReddito> esenredTEsenzioniRedditos) {
//		this.esenredTEsenzioniRedditos = esenredTEsenzioniRedditos;
//	}

//	public EsenredTEsenzioniReddito addEsenredTEsenzioniReddito(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
//		getEsenredTEsenzioniRedditos().add(esenredTEsenzioniReddito);
//		esenredTEsenzioniReddito.setEsenredDAziendasanitaria(this);
//
//		return esenredTEsenzioniReddito;
//	}
////
////	public EsenredTEsenzioniReddito removeEsenredTEsenzioniReddito(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
//		getEsenredTEsenzioniRedditos().remove(esenredTEsenzioniReddito);
//		esenredTEsenzioniReddito.setEsenredDAziendasanitaria(null);
//
//		return esenredTEsenzioniReddito;
//	}


	public List<EsenzioneDDistrettoSocioSanitario> getEsenzioneDDistrettoSocioSanitarios() {
		return this.esenzioneDDistrettoSocioSanitarios;
	}

	public void setEsenzioneDDistrettoSocioSanitarios(List<EsenzioneDDistrettoSocioSanitario> esenzioneDDistrettoSocioSanitarios) {
		this.esenzioneDDistrettoSocioSanitarios = esenzioneDDistrettoSocioSanitarios;
	}

	public EsenzioneDDistrettoSocioSanitario addEsenzioneDDistrettoSocioSanitario(EsenzioneDDistrettoSocioSanitario esenzioneDDistrettoSocioSanitario) {
		getEsenzioneDDistrettoSocioSanitarios().add(esenzioneDDistrettoSocioSanitario);
		esenzioneDDistrettoSocioSanitario.setEsenredDAziendasanitaria(this);

		return esenzioneDDistrettoSocioSanitario;
	}

	public EsenzioneDDistrettoSocioSanitario removeEsenzioneDDistrettoSocioSanitario(EsenzioneDDistrettoSocioSanitario esenzioneDDistrettoSocioSanitario) {
		getEsenzioneDDistrettoSocioSanitarios().remove(esenzioneDDistrettoSocioSanitario);
		esenzioneDDistrettoSocioSanitario.setEsenredDAziendasanitaria(null);

		return esenzioneDDistrettoSocioSanitario;
	}

	public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenziones() {
		return this.esenzioneSPraticaEsenziones;
	}

	public void setEsenzioneSPraticaEsenziones(List<EsenzioneSPraticaEsenzione> esenzioneSPraticaEsenziones) {
		this.esenzioneSPraticaEsenziones = esenzioneSPraticaEsenziones;
	}

	public EsenzioneSPraticaEsenzione addEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
		getEsenzioneSPraticaEsenziones().add(esenzioneSPraticaEsenzione);
		esenzioneSPraticaEsenzione.setEsenredDAziendasanitaria(this);

		return esenzioneSPraticaEsenzione;
	}

	public EsenzioneSPraticaEsenzione removeEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione esenzioneSPraticaEsenzione) {
		getEsenzioneSPraticaEsenziones().remove(esenzioneSPraticaEsenzione);
		esenzioneSPraticaEsenzione.setEsenredDAziendasanitaria(null);

		return esenzioneSPraticaEsenzione;
	}

	public List<EsenzioneTPraticaEsenzione> getEsenzioneTPraticaEsenziones() {
		return this.esenzioneTPraticaEsenziones;
	}

	public void setEsenzioneTPraticaEsenziones(List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones) {
		this.esenzioneTPraticaEsenziones = esenzioneTPraticaEsenziones;
	}

	public EsenzioneTPraticaEsenzione addEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		getEsenzioneTPraticaEsenziones().add(esenzioneTPraticaEsenzione);
		esenzioneTPraticaEsenzione.setEsenredDAziendasanitaria(this);

		return esenzioneTPraticaEsenzione;
	}

	public EsenzioneTPraticaEsenzione removeEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		getEsenzioneTPraticaEsenziones().remove(esenzioneTPraticaEsenzione);
		esenzioneTPraticaEsenzione.setEsenredDAziendasanitaria(null);

		return esenzioneTPraticaEsenzione;
	}

	public List<EsenzioneTProgressivo> getEsenzioneTProgressivos() {
		return this.esenzioneTProgressivos;
	}

	public void setEsenzioneTProgressivos(List<EsenzioneTProgressivo> esenzioneTProgressivos) {
		this.esenzioneTProgressivos = esenzioneTProgressivos;
	}

	public EsenzioneTProgressivo addEsenzioneTProgressivo(EsenzioneTProgressivo esenzioneTProgressivo) {
		getEsenzioneTProgressivos().add(esenzioneTProgressivo);
		esenzioneTProgressivo.setEsenredDAziendasanitaria(this);

		return esenzioneTProgressivo;
	}

	public EsenzioneTProgressivo removeEsenzioneTProgressivo(EsenzioneTProgressivo esenzioneTProgressivo) {
		getEsenzioneTProgressivos().remove(esenzioneTProgressivo);
		esenzioneTProgressivo.setEsenredDAziendasanitaria(null);

		return esenzioneTProgressivo;
	}

}