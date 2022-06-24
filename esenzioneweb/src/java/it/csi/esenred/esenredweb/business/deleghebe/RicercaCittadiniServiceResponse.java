/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCittadiniServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCittadiniServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCittadiniResponse" type="{http://deleghebe.csi.it/}ricercaCittadiniResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCittadiniServiceResponse", propOrder = {
    "ricercaCittadiniResponse"
})
public class RicercaCittadiniServiceResponse {

    protected RicercaCittadiniResponse ricercaCittadiniResponse;

    /**
     * Gets the value of the ricercaCittadiniResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCittadiniResponse }
     *     
     */
    public RicercaCittadiniResponse getRicercaCittadiniResponse() {
        return ricercaCittadiniResponse;
    }

    /**
     * Sets the value of the ricercaCittadiniResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCittadiniResponse }
     *     
     */
    public void setRicercaCittadiniResponse(RicercaCittadiniResponse value) {
        this.ricercaCittadiniResponse = value;
    }

}
