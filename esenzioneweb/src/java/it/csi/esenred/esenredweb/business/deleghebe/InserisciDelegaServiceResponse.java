/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciDelegaServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciDelegaServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciDelegaResponse" type="{http://deleghebe.csi.it/}inserisciDelegaResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciDelegaServiceResponse", propOrder = {
    "inserisciDelegaResponse"
})
public class InserisciDelegaServiceResponse {

    protected InserisciDelegaResponse inserisciDelegaResponse;

    /**
     * Gets the value of the inserisciDelegaResponse property.
     * 
     * @return
     *     possible object is
     *     {@link InserisciDelegaResponse }
     *     
     */
    public InserisciDelegaResponse getInserisciDelegaResponse() {
        return inserisciDelegaResponse;
    }

    /**
     * Sets the value of the inserisciDelegaResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciDelegaResponse }
     *     
     */
    public void setInserisciDelegaResponse(InserisciDelegaResponse value) {
        this.inserisciDelegaResponse = value;
    }

}
