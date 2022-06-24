/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCfOperatoreServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCfOperatoreServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCfOperatoreResponse" type="{http://deleghebe.csi.it/}ricercaCfOperatoreResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCfOperatoreServiceResponse", propOrder = {
    "ricercaCfOperatoreResponse"
})
public class RicercaCfOperatoreServiceResponse {

    protected RicercaCfOperatoreResponse ricercaCfOperatoreResponse;

    /**
     * Gets the value of the ricercaCfOperatoreResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCfOperatoreResponse }
     *     
     */
    public RicercaCfOperatoreResponse getRicercaCfOperatoreResponse() {
        return ricercaCfOperatoreResponse;
    }

    /**
     * Sets the value of the ricercaCfOperatoreResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCfOperatoreResponse }
     *     
     */
    public void setRicercaCfOperatoreResponse(RicercaCfOperatoreResponse value) {
        this.ricercaCfOperatoreResponse = value;
    }

}
