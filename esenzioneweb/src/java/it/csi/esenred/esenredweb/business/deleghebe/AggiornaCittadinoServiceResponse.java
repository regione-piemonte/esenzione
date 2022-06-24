/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaCittadinoServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCittadinoServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaCittadinoResponse" type="{http://deleghebe.csi.it/}aggiornaCittadinoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCittadinoServiceResponse", propOrder = {
    "aggiornaCittadinoResponse"
})
public class AggiornaCittadinoServiceResponse {

    protected AggiornaCittadinoResponse aggiornaCittadinoResponse;

    /**
     * Gets the value of the aggiornaCittadinoResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCittadinoResponse }
     *     
     */
    public AggiornaCittadinoResponse getAggiornaCittadinoResponse() {
        return aggiornaCittadinoResponse;
    }

    /**
     * Sets the value of the aggiornaCittadinoResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCittadinoResponse }
     *     
     */
    public void setAggiornaCittadinoResponse(AggiornaCittadinoResponse value) {
        this.aggiornaCittadinoResponse = value;
    }

}
