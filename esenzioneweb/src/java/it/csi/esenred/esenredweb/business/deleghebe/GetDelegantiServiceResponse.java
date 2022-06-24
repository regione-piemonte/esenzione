/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDelegantiServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDelegantiServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegantiResponse" type="{http://deleghebe.csi.it/}getDelegantiResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegantiServiceResponse", propOrder = {
    "getDelegantiResponse"
})
public class GetDelegantiServiceResponse {

    protected GetDelegantiResponse getDelegantiResponse;

    /**
     * Gets the value of the getDelegantiResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegantiResponse }
     *     
     */
    public GetDelegantiResponse getGetDelegantiResponse() {
        return getDelegantiResponse;
    }

    /**
     * Sets the value of the getDelegantiResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegantiResponse }
     *     
     */
    public void setGetDelegantiResponse(GetDelegantiResponse value) {
        this.getDelegantiResponse = value;
    }

}
