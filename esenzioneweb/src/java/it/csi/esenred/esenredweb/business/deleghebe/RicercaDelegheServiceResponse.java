/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaDelegheServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDelegheServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDelegheResponse" type="{http://deleghebe.csi.it/}ricercaDelegheResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDelegheServiceResponse", propOrder = {
    "ricercaDelegheResponse"
})
public class RicercaDelegheServiceResponse {

    protected RicercaDelegheResponse ricercaDelegheResponse;

    /**
     * Gets the value of the ricercaDelegheResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDelegheResponse }
     *     
     */
    public RicercaDelegheResponse getRicercaDelegheResponse() {
        return ricercaDelegheResponse;
    }

    /**
     * Sets the value of the ricercaDelegheResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDelegheResponse }
     *     
     */
    public void setRicercaDelegheResponse(RicercaDelegheResponse value) {
        this.ricercaDelegheResponse = value;
    }

}
