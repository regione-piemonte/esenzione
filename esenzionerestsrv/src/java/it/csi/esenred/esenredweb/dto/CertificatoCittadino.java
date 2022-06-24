/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.util.DateUtil;

//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.entity.EsenzioneRDiagnosiPrestazione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.util.Constants;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "utilizzabile", "data_inserimento", "data_aggiornamento", "data_scandenza", "esenzione_id", "tipologia_esenzione", "malattia", "inserito_da" })
public class CertificatoCittadino {

  @JsonProperty("id")
  private String id;
  @JsonProperty("utilizzabile")
  private Boolean utilizzabile;
  @JsonProperty("data_inserimento")
  private String dataInserimento;
  @JsonProperty("data_aggiornamento")
  private String dataAggiornamento;
  @JsonProperty("data_scadenza")
  private String dataScadenza;
  @JsonProperty("esenzione_id")
  private String esenzioneId;
  @JsonProperty("tipologia_esenzione")
  private Tipologia tipologiaEsenzione;
  @JsonProperty("malattia")
  private Malattia malattia;
  @JsonProperty("inserito_da")
  private InseritoDa inseritoDa;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public CertificatoCittadino(EsenzioneTDocumento esenzioneTDocumento, EsenzioneTCittadino UserInseritoDa) {
    this.id = esenzioneTDocumento.getSkDocumento().toString();
    this.utilizzabile = (esenzioneTDocumento.getEsenzioneRPraticaEsenzioneDocumentos().isEmpty());
    this.dataInserimento = null;
    if (esenzioneTDocumento.getDatCreazione() != null) {
      this.dataInserimento = DateUtil.formatDate(esenzioneTDocumento.getDatCreazione(), Constants.DATE_FORMAT);
    }
    this.dataAggiornamento = null;
    if (esenzioneTDocumento.getDatModifica() != null) {
      this.dataAggiornamento = DateUtil.formatDate(esenzioneTDocumento.getDatModifica(), Constants.DATE_FORMAT);
    }
    this.dataScadenza = null;
    if (esenzioneTDocumento.getDatFineValidita() != null) {
      this.dataScadenza = DateUtil.formatDate(esenzioneTDocumento.getDatFineValidita(), Constants.DATE_FORMAT);
    }

    this.malattia = new Malattia();
    this.malattia.setPrestazioni(new ArrayList<Prestazioni>());
    //this.malattia.setCodice(esenzioneTDocumento.getSkDiagnosi().toString());
    if (esenzioneTDocumento.getEsenzioneDDiagnosi() != null) {
      this.malattia.setCodice(esenzioneTDocumento.getEsenzioneDDiagnosi().getCodDiagnosi().toString());
      this.malattia.setDescrizione(esenzioneTDocumento.getEsenzioneDDiagnosi().getDescDiagnosi().toString());
      this.malattia.setGiorni_validita(esenzioneTDocumento.getEsenzioneDDiagnosi().getNumDurata());
    } else {
      this.malattia.setCodice(null);
      this.malattia.setDescrizione(null);
      this.malattia.setGiorni_validita(null);
    }

    this.esenzioneId = null;
    this.tipologiaEsenzione = new Tipologia();
    if (!esenzioneTDocumento.getEsenzioneRPraticaEsenzioneDocumentos().isEmpty()) {
      EsenzioneTPraticaEsenzione praticaEsenzione = esenzioneTDocumento.getEsenzioneRPraticaEsenzioneDocumentos().iterator().next().getEsenzioneTPraticaEsenzione();
      this.esenzioneId = praticaEsenzione.getSkPraticaEsenzione().toString();
      if (praticaEsenzione.getEsenzioneDGruppoEsenzioni() != null) {
        this.tipologiaEsenzione.setCodice(praticaEsenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo());
        this.tipologiaEsenzione.setDescrizione(praticaEsenzione.getEsenzioneDGruppoEsenzioni().getDescGruppo());
      } else {
        this.tipologiaEsenzione.setCodice(null);
        this.tipologiaEsenzione.setDescrizione(null);
      }

      if (praticaEsenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_MALATTIA_CRONICA)) {
        ArrayList<Prestazioni> prestazioni = new ArrayList<Prestazioni>();
        Prestazioni prestazione = null;
        if (esenzioneTDocumento.getEsenzioneDDiagnosi() != null && !esenzioneTDocumento.getEsenzioneDDiagnosi().getEsenzioneRDiagnosiPrestaziones().isEmpty()) {
          for (EsenzioneRDiagnosiPrestazione esenzioneRDiagnosiPrestazione : esenzioneTDocumento.getEsenzioneDDiagnosi().getEsenzioneRDiagnosiPrestaziones()) {
            prestazione = new Prestazioni();
            prestazione.setCodice(esenzioneRDiagnosiPrestazione.getEsenzioneDPrestazioneSpecialistica().getCodPrestazione());
            prestazione.setDescrizione(esenzioneRDiagnosiPrestazione.getEsenzioneDPrestazioneSpecialistica().getDescPrestazione());
            prestazioni.add(prestazione);
          }
          this.malattia.setPrestazioni(prestazioni);
        }
      }
    }

    this.inseritoDa = new InseritoDa();
    this.inseritoDa.setCodiceFiscale(UserInseritoDa.getCodiceFiscale().toString());
    this.inseritoDa.setNome("");
    this.inseritoDa.setCognome("");
    this.inseritoDa.setDataNascita("");
    this.inseritoDa.setComuneNascita("");
    this.inseritoDa.setSesso("");
    this.inseritoDa.setIdAura(null);
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("utilizzabile")
  public Boolean getUtilizzabile() {
    return utilizzabile;
  }

  @JsonProperty("utilizzabile")
  public void setUtilizzabile(Boolean utilizzabile) {
    this.utilizzabile = utilizzabile;
  }

  @JsonProperty("data_inserimento")
  public String getDataInserimento() {
    return dataInserimento;
  }

  @JsonProperty("data_inserimento")
  public void setDataInserimento(String dataInserimento) {
    this.dataInserimento = dataInserimento;
  }

  @JsonProperty("data_aggiornamento")
  public String getDataAggiornamento() {
    return dataAggiornamento;
  }

  @JsonProperty("data_aggiornamento")
  public void setDataAggiornamento(String dataAggiornamento) {
    this.dataAggiornamento = dataAggiornamento;
  }

  @JsonProperty("data_scadenza")
  public String getDataScadenza() {
    return dataScadenza;
  }

  @JsonProperty("data_scadenza")
  public void setDataScadenza(String dataScadenza) {
    this.dataScadenza = dataScadenza;
  }

  @JsonProperty("esenzione_id")
  public String getEsenzioneId() {
    return esenzioneId;
  }

  @JsonProperty("esenzione_id")
  public void setEsenzioneId(String esenzioneId) {
    this.esenzioneId = esenzioneId;
  }

  @JsonProperty("tipologia_esenzione")
  public Tipologia getTipologiaEsenzione() {
    return tipologiaEsenzione;
  }

  @JsonProperty("tipologia_esenzione")
  public void setTipologiaEsenzione(Tipologia tipologiaEsenzione) {
    this.tipologiaEsenzione = tipologiaEsenzione;
  }

  @JsonProperty("malattia")
  public Malattia getMalattia() {
    return malattia;
  }

  @JsonProperty("malattia")
  public void setMalattia(Malattia malattia) {
    this.malattia = malattia;
  }

  @JsonProperty("inserito_da")
  public InseritoDa getInseritoDa() {
    return inseritoDa;
  }

  @JsonProperty("inserito_da")
  public void setInseritoDa(InseritoDa inseritoDa) {
    this.inseritoDa = inseritoDa;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public CertificatoCittadino() {
  }

}
