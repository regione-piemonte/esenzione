/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.insertautocertesered;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for RequestBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestBody">
 *   &lt;complexContent>
 *     &lt;extension base="{http://InsertAutocertEseRed.central.services.auraws.aura.csi.it}Ens_Request">
 *       &lt;sequence>
 *         &lt;element name="cfAssistito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *               &lt;minLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cfChiamante" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *               &lt;minLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cfDichiarante" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *               &lt;minLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cfTitolare" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *               &lt;minLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codeEsenzione" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="titoloDichiarante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestBody", propOrder = {
    "cfAssistito",
    "cfChiamante",
    "cfDichiarante",
    "cfTitolare",
    "codeEsenzione",
    "dataInizio",
    "dataFine",
    "titoloDichiarante",
    "note"
})
public class RequestBody
    extends EnsRequest
{

    protected String cfAssistito;
    protected String cfChiamante;
    protected String cfDichiarante;
    protected String cfTitolare;
    protected String codeEsenzione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;
    protected String titoloDichiarante;
    protected String note;

    /**
     * Gets the value of the cfAssistito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfAssistito() {
        return cfAssistito;
    }

    /**
     * Sets the value of the cfAssistito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfAssistito(String value) {
        this.cfAssistito = value;
    }

    /**
     * Gets the value of the cfChiamante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfChiamante() {
        return cfChiamante;
    }

    /**
     * Sets the value of the cfChiamante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfChiamante(String value) {
        this.cfChiamante = value;
    }

    /**
     * Gets the value of the cfDichiarante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfDichiarante() {
        return cfDichiarante;
    }

    /**
     * Sets the value of the cfDichiarante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfDichiarante(String value) {
        this.cfDichiarante = value;
    }

    /**
     * Gets the value of the cfTitolare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfTitolare() {
        return cfTitolare;
    }

    /**
     * Sets the value of the cfTitolare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfTitolare(String value) {
        this.cfTitolare = value;
    }

    /**
     * Gets the value of the codeEsenzione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeEsenzione() {
        return codeEsenzione;
    }

    /**
     * Sets the value of the codeEsenzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeEsenzione(String value) {
        this.codeEsenzione = value;
    }

    /**
     * Gets the value of the dataInizio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Sets the value of the dataInizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Gets the value of the dataFine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Sets the value of the dataFine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
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
    
    public RequestBody(
    		String codFiscaleAssistito,
    		String codFiscaleChiamante,
    		String codFiscaleDichiarante,
    		String codFiscaleTitolare,
    		String codEsenzione,
    		String note,
    		String titoloDichiarante,
    		XMLGregorianCalendar dataInizio,
    		XMLGregorianCalendar dataFine
    		) {
    	this.cfAssistito = codFiscaleAssistito;
    	this.cfChiamante = codFiscaleChiamante;
    	this.cfDichiarante = codFiscaleDichiarante;
    	this.cfTitolare = codFiscaleTitolare;
    	this.codeEsenzione = codEsenzione;
    	this.titoloDichiarante = titoloDichiarante;
    	this.dataInizio = dataInizio;
    	this.dataFine = dataFine;
    	this.note = note.length()>50 ? note.substring(0, 49) : note;
    }

    public RequestBody() {}
}
