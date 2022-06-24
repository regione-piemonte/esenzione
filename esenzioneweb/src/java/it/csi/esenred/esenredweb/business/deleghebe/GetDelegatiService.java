/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDelegatiService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegati" type="{http://deleghebe.csi.it/}getDelegati" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegatiService", propOrder = {
    "getDelegati"
})
public class GetDelegatiService {

    protected GetDelegati getDelegati;

    /**
     * Gets the value of the getDelegati property.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegati }
     *     
     */
    public GetDelegati getGetDelegati() {
        return getDelegati;
    }

    /**
     * Sets the value of the getDelegati property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegati }
     *     
     */
    public void setGetDelegati(GetDelegati value) {
        this.getDelegati = value;
    }

}
