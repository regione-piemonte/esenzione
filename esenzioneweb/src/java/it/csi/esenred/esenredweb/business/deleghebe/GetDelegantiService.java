/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDelegantiService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDelegantiService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDeleganti" type="{http://deleghebe.csi.it/}getDeleganti" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegantiService", propOrder = {
    "getDeleganti"
})
public class GetDelegantiService {

    protected GetDeleganti getDeleganti;

    /**
     * Gets the value of the getDeleganti property.
     * 
     * @return
     *     possible object is
     *     {@link GetDeleganti }
     *     
     */
    public GetDeleganti getGetDeleganti() {
        return getDeleganti;
    }

    /**
     * Sets the value of the getDeleganti property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDeleganti }
     *     
     */
    public void setGetDeleganti(GetDeleganti value) {
        this.getDeleganti = value;
    }

}
