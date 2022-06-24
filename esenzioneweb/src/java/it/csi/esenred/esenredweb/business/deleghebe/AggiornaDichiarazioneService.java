/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaDichiarazioneService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaDichiarazioneService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaDichiarazione" type="{http://deleghebe.csi.it/}aggiornaDichiarazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaDichiarazioneService", propOrder = {
    "aggiornaDichiarazione"
})
public class AggiornaDichiarazioneService {

    protected AggiornaDichiarazione aggiornaDichiarazione;

    /**
     * Gets the value of the aggiornaDichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaDichiarazione }
     *     
     */
    public AggiornaDichiarazione getAggiornaDichiarazione() {
        return aggiornaDichiarazione;
    }

    /**
     * Sets the value of the aggiornaDichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaDichiarazione }
     *     
     */
    public void setAggiornaDichiarazione(AggiornaDichiarazione value) {
        this.aggiornaDichiarazione = value;
    }

}
