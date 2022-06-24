/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaDichiarazioniServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDichiarazioniServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDichiarazioniResponse" type="{http://deleghebe.csi.it/}ricercaDichiarazioniResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDichiarazioniServiceResponse", propOrder = {
    "ricercaDichiarazioniResponse"
})
public class RicercaDichiarazioniServiceResponse {

    protected RicercaDichiarazioniResponse ricercaDichiarazioniResponse;

    /**
     * Gets the value of the ricercaDichiarazioniResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioniResponse }
     *     
     */
    public RicercaDichiarazioniResponse getRicercaDichiarazioniResponse() {
        return ricercaDichiarazioniResponse;
    }

    /**
     * Sets the value of the ricercaDichiarazioniResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioniResponse }
     *     
     */
    public void setRicercaDichiarazioniResponse(RicercaDichiarazioniResponse value) {
        this.ricercaDichiarazioniResponse = value;
    }

}
