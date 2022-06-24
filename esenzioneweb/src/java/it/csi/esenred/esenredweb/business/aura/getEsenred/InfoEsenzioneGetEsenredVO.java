/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.aura.getEsenred;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infoEsenzioneGetEsenredVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infoEsenzioneGetEsenredVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codEsenzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEmissione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataScadenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataSospensione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codAttestato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="protocollo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fonte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleDichiarante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAuraDichiarante" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleTitolare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAuraTitolare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="titoloDichiarante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descTitoloDichiarante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoEsenzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceContestazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrCodiceContestazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAuraBeneficiario" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoEsenzioneGetEsenredVO", propOrder = {
    "codEsenzione",
    "dataEmissione",
    "dataScadenza",
    "dataSospensione",
    "codAttestato",
    "protocollo",
    "fonte",
    "codiceFiscaleDichiarante",
    "idAuraDichiarante",
    "codiceFiscaleTitolare",
    "idAuraTitolare",
    "titoloDichiarante",
    "descTitoloDichiarante",
    "note",
    "statoEsenzione",
    "codiceContestazione",
    "descrCodiceContestazione",
    "codiceFiscaleBeneficiario",
    "idAuraBeneficiario"
})
public class InfoEsenzioneGetEsenredVO {

    protected String codEsenzione;
    protected String dataEmissione;
    protected String dataScadenza;
    protected String dataSospensione;
    protected String codAttestato;
    protected String protocollo;
    protected String fonte;
    protected String codiceFiscaleDichiarante;
    protected BigDecimal idAuraDichiarante;
    protected String codiceFiscaleTitolare;
    protected BigDecimal idAuraTitolare;
    protected String titoloDichiarante;
    protected String descTitoloDichiarante;
    protected String note;
    protected String statoEsenzione;
    protected String codiceContestazione;
    protected String descrCodiceContestazione;
    protected String codiceFiscaleBeneficiario;
    protected BigDecimal idAuraBeneficiario;

    /**
     * Gets the value of the codEsenzione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEsenzione() {
        return codEsenzione;
    }

    /**
     * Sets the value of the codEsenzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEsenzione(String value) {
        this.codEsenzione = value;
    }

    /**
     * Gets the value of the dataEmissione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataEmissione() {
        return dataEmissione;
    }

    /**
     * Sets the value of the dataEmissione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEmissione(String value) {
        this.dataEmissione = value;
    }

    /**
     * Gets the value of the dataScadenza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Sets the value of the dataScadenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataScadenza(String value) {
        this.dataScadenza = value;
    }

    /**
     * Gets the value of the dataSospensione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSospensione() {
        return dataSospensione;
    }

    /**
     * Sets the value of the dataSospensione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSospensione(String value) {
        this.dataSospensione = value;
    }

    /**
     * Gets the value of the codAttestato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAttestato() {
        return codAttestato;
    }

    /**
     * Sets the value of the codAttestato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAttestato(String value) {
        this.codAttestato = value;
    }

    /**
     * Gets the value of the protocollo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocollo() {
        return protocollo;
    }

    /**
     * Sets the value of the protocollo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocollo(String value) {
        this.protocollo = value;
    }

    /**
     * Gets the value of the fonte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFonte() {
        return fonte;
    }

    /**
     * Sets the value of the fonte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFonte(String value) {
        this.fonte = value;
    }

    /**
     * Gets the value of the codiceFiscaleDichiarante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleDichiarante() {
        return codiceFiscaleDichiarante;
    }

    /**
     * Sets the value of the codiceFiscaleDichiarante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleDichiarante(String value) {
        this.codiceFiscaleDichiarante = value;
    }

    /**
     * Gets the value of the idAuraDichiarante property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAuraDichiarante() {
        return idAuraDichiarante;
    }

    /**
     * Sets the value of the idAuraDichiarante property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAuraDichiarante(BigDecimal value) {
        this.idAuraDichiarante = value;
    }

    /**
     * Gets the value of the codiceFiscaleTitolare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleTitolare() {
        return codiceFiscaleTitolare;
    }

    /**
     * Sets the value of the codiceFiscaleTitolare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleTitolare(String value) {
        this.codiceFiscaleTitolare = value;
    }

    /**
     * Gets the value of the idAuraTitolare property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAuraTitolare() {
        return idAuraTitolare;
    }

    /**
     * Sets the value of the idAuraTitolare property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAuraTitolare(BigDecimal value) {
        this.idAuraTitolare = value;
    }

    /**
     * Gets the value of the titoloDichiarante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitoloDichiarante() {
        return titoloDichiarante;
    }

    /**
     * Sets the value of the titoloDichiarante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitoloDichiarante(String value) {
        this.titoloDichiarante = value;
    }

    /**
     * Gets the value of the descTitoloDichiarante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTitoloDichiarante() {
        return descTitoloDichiarante;
    }

    /**
     * Sets the value of the descTitoloDichiarante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTitoloDichiarante(String value) {
        this.descTitoloDichiarante = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the statoEsenzione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoEsenzione() {
        return statoEsenzione;
    }

    /**
     * Sets the value of the statoEsenzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoEsenzione(String value) {
        this.statoEsenzione = value;
    }

    /**
     * Gets the value of the codiceContestazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceContestazione() {
        return codiceContestazione;
    }

    /**
     * Sets the value of the codiceContestazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceContestazione(String value) {
        this.codiceContestazione = value;
    }

    /**
     * Gets the value of the descrCodiceContestazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrCodiceContestazione() {
        return descrCodiceContestazione;
    }

    /**
     * Sets the value of the descrCodiceContestazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrCodiceContestazione(String value) {
        this.descrCodiceContestazione = value;
    }

    /**
     * Gets the value of the codiceFiscaleBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleBeneficiario() {
        return codiceFiscaleBeneficiario;
    }

    /**
     * Sets the value of the codiceFiscaleBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleBeneficiario(String value) {
        this.codiceFiscaleBeneficiario = value;
    }

    /**
     * Gets the value of the idAuraBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAuraBeneficiario() {
        return idAuraBeneficiario;
    }

    /**
     * Sets the value of the idAuraBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAuraBeneficiario(BigDecimal value) {
        this.idAuraBeneficiario = value;
    }

}
