/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.revocaautocertesered;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RevocaAutocertEseRedResult" type="{http://RevocaAutocertEseRed.central.services.auraws.aura.csi.it}Response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "revocaAutocertEseRedResult"
})
@XmlRootElement(name = "RevocaAutocertEseRedResponse")
public class RevocaAutocertEseRedResponse {

    @XmlElement(name = "RevocaAutocertEseRedResult", required = true)
    protected Response revocaAutocertEseRedResult;

    /**
     * Gets the value of the revocaAutocertEseRedResult property.
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getRevocaAutocertEseRedResult() {
        return revocaAutocertEseRedResult;
    }

    /**
     * Sets the value of the revocaAutocertEseRedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setRevocaAutocertEseRedResult(Response value) {
        this.revocaAutocertEseRedResult = value;
    }

}
