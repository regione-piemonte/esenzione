/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveDelegatiServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="saveDelegatiServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saveDelegatiResponse" type="{http://deleghebe.csi.it/}saveDelegatiResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveDelegatiServiceResponse", propOrder = {
    "saveDelegatiResponse"
})
public class SaveDelegatiServiceResponse {

    protected SaveDelegatiResponse saveDelegatiResponse;

    /**
     * Gets the value of the saveDelegatiResponse property.
     * 
     * @return
     *     possible object is
     *     {@link SaveDelegatiResponse }
     *     
     */
    public SaveDelegatiResponse getSaveDelegatiResponse() {
        return saveDelegatiResponse;
    }

    /**
     * Sets the value of the saveDelegatiResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveDelegatiResponse }
     *     
     */
    public void setSaveDelegatiResponse(SaveDelegatiResponse value) {
        this.saveDelegatiResponse = value;
    }

}
