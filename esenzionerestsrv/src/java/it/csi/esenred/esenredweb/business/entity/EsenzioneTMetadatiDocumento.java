/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;


/**
 * The persistent class for the "ESENZIONE_T_METADATI_DOCUMENTO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_T_METADATI_DOCUMENTO\"")
@SequenceGenerator(name = "\"ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq\"", sequenceName = "\"ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq\"", initialValue = 1, allocationSize = 1)
@NamedQuery(name="EsenzioneTMetadatiDocumento.findAll", query="SELECT e FROM EsenzioneTMetadatiDocumento e")
public class EsenzioneTMetadatiDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq\"")
	@Column(name="\"SK_METADATI_DOCUMENTO\"", unique=true, nullable=false)
	private Integer skMetadatiDocumento;

	@Column(name = "\"ASSETTO_ORGANIZZATIVO\"")
	private String assettoOrganizzativo;

	@Column(name = "\"COD_APPLICATIVO_RICHIESTA\"")
	private String codApplicativoRichiesta;

	@Column(name = "\"COD_AZIENDA_RICHIESTA\"")
	private String codAziendaRichiesta;

	@Column(name = "\"COD_COMUNE_PAZIENTE\"")
	private String codComunePaziente;

	@Column(name = "\"COD_FISCALE_PAZIENTE\"")
	private String codFiscalePaziente;

	@Column(name = "\"COD_MATRICOLA\"")
	private String codMatricola;

	@Column(name = "\"COD_MIME_TYPE\"")
	private String codMimeType;

	@Column(name = "\"COD_OSCURA_SCARICO_CITTADINO\"")
	private String codOscuraScaricoCittadino;

	@Column(name = "\"COD_PRIVACY_CITTADINO\"")
	private String codPrivacyCittadino;

	@Column(name = "\"COD_RUOLO_OPERATORE\"")
	private String codRuoloOperatore;

	@Column(name = "\"COD_SESSO_PAZIENTE\"")
	private String codSessoPaziente;

	@Column(name = "\"COD_STATO_NASCITA_PAZIENTE\"")
	private String codStatoNascitaPaziente;

	@Column(name="\"COD_STRUTTURA\"")
	private Integer codStruttura;

	@Column(name = "\"COD_TIPO_ATTIVITA_CLINICA\"")
	private String codTipoAttivitaClinica;

	@Column(name = "\"COD_TIPO_AZIONE\"")
	private String codTipoAzione;

	@Column(name = "\"COD_TIPO_AZIONE_EPISODIO\"")
	private String codTipoAzioneEpisodio;

	@Column(name = "\"COD_TIPO_DOCUMENTO_ALTO\"")
	private String codTipoDocumentoAlto;

	@Column(name = "\"COD_TIPO_DOCUMENTO_MEDIO\"")
	private String codTipoDocumentoMedio;

	@Column(name = "\"COD_TIPO_EPISODIO\"")
	private String codTipoEpisodio;

	@Column(name = "\"COD_TIPO_FIRMA\"")
	private String codTipoFirma;

	@Column(name = "\"COD_TIPO_STRUTTURA_PROD_DOC\"")
	private String codTipoStrutturaProdDoc;

	@Column(name = "\"CODICE_PIN\"")
	private String codicePin;

	@Column(name = "\"COGNOME_PAZIENTE\"")
	private String cognomePaziente;

	@Column(name="\"DAT_CANCELLAZIONE\"")
	private Timestamp datCancellazione;

	@Column(name="\"DAT_CREAZIONE\"")
	private Timestamp datCreazione;

	@Column(name="\"DAT_FINE_EPISODIO\"")
	private Timestamp datFineEpisodio;

	@Column(name="\"DAT_FIRMA\"")
	private Timestamp datFirma;

	@Column(name="\"DAT_INIZIO_EPISODIO\"")
	private Timestamp datInizioEpisodio;

	@Column(name="\"DAT_MODIFICA\"")
	private Timestamp datModifica;

	@Column(name="\"DAT_NASCITA_PAZIENTE\"")
	private Timestamp datNascitaPaziente;

	@Column(name="\"DAT_RICHIESTA\"")
	private Timestamp datRichiesta;

	@Column(name="\"FLAG_FIRMATO\"")
	private Boolean flagFirmato;

	@Column(name="\"FLAG_SCARICA_CITTADINO\"")
	private Boolean flagScaricaCittadino;

	@Column(name="\"FLAG_SOGGETTO_LEGGI_SPECIALI\"")
	private Boolean flagSoggettoLeggiSpeciali;

	@Column(name = "\"HASH_DOC\"")
	private String hashDoc;

	@Column(name="\"ID_AULA\"")
	private Long idAula;

	@Column(name="\"ID_AURA_PAZIENTE\"")
	private Long idAuraPaziente;

	@Column(name = "\"ID_EPISODIO\"")
	private String idEpisodio;

	@Column(name = "\"ID_GENITORE_TUTORE_PAZIENTE\"")
	private String idGenitoreTutorePaziente;

	@Column(name = "\"ID_RICHIESTA\"")
	private String idRichiesta;

	@Column(name = "\"ID_RICHIESTA_UTENTE\"")
	private String idRichiestaUtente;

	@Column(name="\"ID_USER\"")
	private Long idUser;

	@Column(name = "\"MEDICO_REDATTORE\"")
	private String medicoRedattore;

	@Column(name = "\"MEDICO_VALIDATORE\"")
	private String medicoValidatore;

	@Column(name = "\"NOME_PAZIENTE\"")
	private String nomePaziente;

	@Column(name = "\"NRE\"")
	private String nre;

	@Column(name = "\"RUOLO_UTENTE\"")
	private String ruoloUtente;

	@Column(name = "\"SIZE_DOC\"")
	private String sizeDoc;

	@Column(name = "\"SK_DOCUMENTO\"", unique = true)
	private Long skDocumento;

	//bi-directional many-to-one association to EsenzioneTDocumento
	@OneToOne
	@JoinColumn(name = "\"SK_DOCUMENTO\"", insertable = false, updatable = false, unique = true)
	private EsenzioneTDocumento esenzioneTDocumento;

	public EsenzioneTMetadatiDocumento() {
	}

	public Integer getSkMetadatiDocumento() {
		return this.skMetadatiDocumento;
	}

	public void setSkMetadatiDocumento(Integer skMetadatiDocumento) {
		this.skMetadatiDocumento = skMetadatiDocumento;
	}

	public String getAssettoOrganizzativo() {
		return this.assettoOrganizzativo;
	}

	public void setAssettoOrganizzativo(String assettoOrganizzativo) {
		this.assettoOrganizzativo = assettoOrganizzativo;
	}

	public String getCodApplicativoRichiesta() {
		return this.codApplicativoRichiesta;
	}

	public void setCodApplicativoRichiesta(String codApplicativoRichiesta) {
		this.codApplicativoRichiesta = codApplicativoRichiesta;
	}

	public String getCodAziendaRichiesta() {
		return this.codAziendaRichiesta;
	}

	public void setCodAziendaRichiesta(String codAziendaRichiesta) {
		this.codAziendaRichiesta = codAziendaRichiesta;
	}

	public String getCodComunePaziente() {
		return this.codComunePaziente;
	}

	public void setCodComunePaziente(String codComunePaziente) {
		this.codComunePaziente = codComunePaziente;
	}

	public String getCodFiscalePaziente() {
		return this.codFiscalePaziente;
	}

	public void setCodFiscalePaziente(String codFiscalePaziente) {
		this.codFiscalePaziente = codFiscalePaziente;
	}

	public String getCodMatricola() {
		return this.codMatricola;
	}

	public void setCodMatricola(String codMatricola) {
		this.codMatricola = codMatricola;
	}

	public String getCodMimeType() {
		return this.codMimeType;
	}

	public void setCodMimeType(String codMimeType) {
		this.codMimeType = codMimeType;
	}

	public String getCodOscuraScaricoCittadino() {
		return this.codOscuraScaricoCittadino;
	}

	public void setCodOscuraScaricoCittadino(String codOscuraScaricoCittadino) {
		this.codOscuraScaricoCittadino = codOscuraScaricoCittadino;
	}

	public String getCodPrivacyCittadino() {
		return this.codPrivacyCittadino;
	}

	public void setCodPrivacyCittadino(String codPrivacyCittadino) {
		this.codPrivacyCittadino = codPrivacyCittadino;
	}

	public String getCodRuoloOperatore() {
		return this.codRuoloOperatore;
	}

	public void setCodRuoloOperatore(String codRuoloOperatore) {
		this.codRuoloOperatore = codRuoloOperatore;
	}

	public String getCodSessoPaziente() {
		return this.codSessoPaziente;
	}

	public void setCodSessoPaziente(String codSessoPaziente) {
		this.codSessoPaziente = codSessoPaziente;
	}

	public String getCodStatoNascitaPaziente() {
		return this.codStatoNascitaPaziente;
	}

	public void setCodStatoNascitaPaziente(String codStatoNascitaPaziente) {
		this.codStatoNascitaPaziente = codStatoNascitaPaziente;
	}

	public Integer getCodStruttura() {
		return this.codStruttura;
	}

	public void setCodStruttura(Integer codStruttura) {
		this.codStruttura = codStruttura;
	}

	public String getCodTipoAttivitaClinica() {
		return this.codTipoAttivitaClinica;
	}

	public void setCodTipoAttivitaClinica(String codTipoAttivitaClinica) {
		this.codTipoAttivitaClinica = codTipoAttivitaClinica;
	}

	public String getCodTipoAzione() {
		return this.codTipoAzione;
	}

	public void setCodTipoAzione(String codTipoAzione) {
		this.codTipoAzione = codTipoAzione;
	}

	public String getCodTipoAzioneEpisodio() {
		return this.codTipoAzioneEpisodio;
	}

	public void setCodTipoAzioneEpisodio(String codTipoAzioneEpisodio) {
		this.codTipoAzioneEpisodio = codTipoAzioneEpisodio;
	}

	public String getCodTipoDocumentoAlto() {
		return this.codTipoDocumentoAlto;
	}

	public void setCodTipoDocumentoAlto(String codTipoDocumentoAlto) {
		this.codTipoDocumentoAlto = codTipoDocumentoAlto;
	}

	public String getCodTipoDocumentoMedio() {
		return this.codTipoDocumentoMedio;
	}

	public void setCodTipoDocumentoMedio(String codTipoDocumentoMedio) {
		this.codTipoDocumentoMedio = codTipoDocumentoMedio;
	}

	public String getCodTipoEpisodio() {
		return this.codTipoEpisodio;
	}

	public void setCodTipoEpisodio(String codTipoEpisodio) {
		this.codTipoEpisodio = codTipoEpisodio;
	}

	public String getCodTipoFirma() {
		return this.codTipoFirma;
	}

	public void setCodTipoFirma(String codTipoFirma) {
		this.codTipoFirma = codTipoFirma;
	}

	public String getCodTipoStrutturaProdDoc() {
		return this.codTipoStrutturaProdDoc;
	}

	public void setCodTipoStrutturaProdDoc(String codTipoStrutturaProdDoc) {
		this.codTipoStrutturaProdDoc = codTipoStrutturaProdDoc;
	}

	public String getCodicePin() {
		return this.codicePin;
	}

	public void setCodicePin(String codicePin) {
		this.codicePin = codicePin;
	}

	public String getCognomePaziente() {
		return this.cognomePaziente;
	}

	public void setCognomePaziente(String cognomePaziente) {
		this.cognomePaziente = cognomePaziente;
	}

	public Timestamp getDatCancellazione() {
		return this.datCancellazione;
	}

	public void setDatCancellazione(Timestamp datCancellazione) {
		this.datCancellazione = datCancellazione;
	}

	public Timestamp getDatCreazione() {
		return this.datCreazione;
	}

	public void setDatCreazione(Timestamp datCreazione) {
		this.datCreazione = datCreazione;
	}

	public Timestamp getDatFineEpisodio() {
		return this.datFineEpisodio;
	}

	public void setDatFineEpisodio(Timestamp datFineEpisodio) {
		this.datFineEpisodio = datFineEpisodio;
	}

	public Timestamp getDatFirma() {
		return this.datFirma;
	}

	public void setDatFirma(Timestamp datFirma) {
		this.datFirma = datFirma;
	}

	public Timestamp getDatInizioEpisodio() {
		return this.datInizioEpisodio;
	}

	public void setDatInizioEpisodio(Timestamp datInizioEpisodio) {
		this.datInizioEpisodio = datInizioEpisodio;
	}

	public Timestamp getDatModifica() {
		return this.datModifica;
	}

	public void setDatModifica(Timestamp datModifica) {
		this.datModifica = datModifica;
	}

	public Timestamp getDatNascitaPaziente() {
		return this.datNascitaPaziente;
	}

	public void setDatNascitaPaziente(Timestamp datNascitaPaziente) {
		this.datNascitaPaziente = datNascitaPaziente;
	}

	public Timestamp getDatRichiesta() {
		return this.datRichiesta;
	}

	public void setDatRichiesta(Timestamp datRichiesta) {
		this.datRichiesta = datRichiesta;
	}

	public Boolean getFlagFirmato() {
		return this.flagFirmato;
	}

	public void setFlagFirmato(Boolean flagFirmato) {
		this.flagFirmato = flagFirmato;
	}

	public Boolean getFlagScaricaCittadino() {
		return this.flagScaricaCittadino;
	}

	public void setFlagScaricaCittadino(Boolean flagScaricaCittadino) {
		this.flagScaricaCittadino = flagScaricaCittadino;
	}

	public Boolean getFlagSoggettoLeggiSpeciali() {
		return this.flagSoggettoLeggiSpeciali;
	}

	public void setFlagSoggettoLeggiSpeciali(Boolean flagSoggettoLeggiSpeciali) {
		this.flagSoggettoLeggiSpeciali = flagSoggettoLeggiSpeciali;
	}

	public String getHashDoc() {
		return this.hashDoc;
	}

	public void setHashDoc(String hashDoc) {
		this.hashDoc = hashDoc;
	}

	public Long getIdAula() {
		return this.idAula;
	}

	public void setIdAula(Long idAula) {
		this.idAula = idAula;
	}

	public Long getIdAuraPaziente() {
		return this.idAuraPaziente;
	}

	public void setIdAuraPaziente(Long idAuraPaziente) {
		this.idAuraPaziente = idAuraPaziente;
	}

	public String getIdEpisodio() {
		return this.idEpisodio;
	}

	public void setIdEpisodio(String idEpisodio) {
		this.idEpisodio = idEpisodio;
	}

	public String getIdGenitoreTutorePaziente() {
		return this.idGenitoreTutorePaziente;
	}

	public void setIdGenitoreTutorePaziente(String idGenitoreTutorePaziente) {
		this.idGenitoreTutorePaziente = idGenitoreTutorePaziente;
	}

	public String getIdRichiesta() {
		return this.idRichiesta;
	}

	public void setIdRichiesta(String idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getIdRichiestaUtente() {
		return this.idRichiestaUtente;
	}

	public void setIdRichiestaUtente(String idRichiestaUtente) {
		this.idRichiestaUtente = idRichiestaUtente;
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getMedicoRedattore() {
		return this.medicoRedattore;
	}

	public void setMedicoRedattore(String medicoRedattore) {
		this.medicoRedattore = medicoRedattore;
	}

	public String getMedicoValidatore() {
		return this.medicoValidatore;
	}

	public void setMedicoValidatore(String medicoValidatore) {
		this.medicoValidatore = medicoValidatore;
	}

	public String getNomePaziente() {
		return this.nomePaziente;
	}

	public void setNomePaziente(String nomePaziente) {
		this.nomePaziente = nomePaziente;
	}

	public String getNre() {
		return this.nre;
	}

	public void setNre(String nre) {
		this.nre = nre;
	}

	public String getRuoloUtente() {
		return this.ruoloUtente;
	}

	public void setRuoloUtente(String ruoloUtente) {
		this.ruoloUtente = ruoloUtente;
	}

	public String getSizeDoc() {
		return this.sizeDoc;
	}

	public void setSizeDoc(String sizeDoc) {
		this.sizeDoc = sizeDoc;
	}

	public Long getSkDocumento() {
		return this.skDocumento;
	}

	public void setSkDocumento(Long skDocumento) {
		this.skDocumento = skDocumento;
	}

	public EsenzioneTDocumento getEsenzioneTDocumento() {
		return this.esenzioneTDocumento;
	}

	public void setEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		this.esenzioneTDocumento = esenzioneTDocumento;
	}
}