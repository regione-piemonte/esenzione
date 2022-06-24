/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.revocaautocertesered;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestBody">
 *   &lt;complexContent>
 *     &lt;extension base="{http://RevocaAutocertEseRed.central.services.auraws.aura.csi.it}Ens_Request">
 *       &lt;sequence>
 *         &lt;element name="cfChiamante" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *               &lt;minLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cfAssistito" minOccurs="0">
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
 *         &lt;element name="numProtMef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cfChiamante",
    "cfAssistito",
    "codeEsenzione",
    "numProtMef"
})
public class RequestBody
    extends EnsRequest
{

    protected String cfChiamante;
    protected String cfAssistito;
    protected String codeEsenzione;
    protected String numProtMef;

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
     * Gets the value of the numProtMef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumProtMef() {
        return numProtMef;
    }

    /**
     * Sets the value of the numProtMef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumProtMef(String value) {
        this.numProtMef = value;
    }

}
