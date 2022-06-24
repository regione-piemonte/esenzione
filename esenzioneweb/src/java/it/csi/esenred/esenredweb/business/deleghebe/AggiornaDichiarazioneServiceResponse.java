/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaDichiarazioneServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaDichiarazioneServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaDichiarazioneResponse" type="{http://deleghebe.csi.it/}aggiornaDichiarazioneResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaDichiarazioneServiceResponse", propOrder = {
    "aggiornaDichiarazioneResponse"
})
public class AggiornaDichiarazioneServiceResponse {

    protected AggiornaDichiarazioneResponse aggiornaDichiarazioneResponse;

    /**
     * Gets the value of the aggiornaDichiarazioneResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaDichiarazioneResponse }
     *     
     */
    public AggiornaDichiarazioneResponse getAggiornaDichiarazioneResponse() {
        return aggiornaDichiarazioneResponse;
    }

    /**
     * Sets the value of the aggiornaDichiarazioneResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaDichiarazioneResponse }
     *     
     */
    public void setAggiornaDichiarazioneResponse(AggiornaDichiarazioneResponse value) {
        this.aggiornaDichiarazioneResponse = value;
    }

}
