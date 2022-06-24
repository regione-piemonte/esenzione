/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for isAliveServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="isAliveServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isAliveResponse" type="{http://deleghebe.csi.it/}isAliveResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isAliveServiceResponse", propOrder = {
    "isAliveResponse"
})
public class IsAliveServiceResponse {

    protected IsAliveResponse isAliveResponse;

    /**
     * Gets the value of the isAliveResponse property.
     * 
     * @return
     *     possible object is
     *     {@link IsAliveResponse }
     *     
     */
    public IsAliveResponse getIsAliveResponse() {
        return isAliveResponse;
    }

    /**
     * Sets the value of the isAliveResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link IsAliveResponse }
     *     
     */
    public void setIsAliveResponse(IsAliveResponse value) {
        this.isAliveResponse = value;
    }

}
