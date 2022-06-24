/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciDichiarazioneService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciDichiarazioneService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciDichiarazione" type="{http://deleghebe.csi.it/}inserisciDichiarazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciDichiarazioneService", propOrder = {
    "inserisciDichiarazione"
})
public class InserisciDichiarazioneService {

    protected InserisciDichiarazione inserisciDichiarazione;

    /**
     * Gets the value of the inserisciDichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link InserisciDichiarazione }
     *     
     */
    public InserisciDichiarazione getInserisciDichiarazione() {
        return inserisciDichiarazione;
    }

    /**
     * Sets the value of the inserisciDichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciDichiarazione }
     *     
     */
    public void setInserisciDichiarazione(InserisciDichiarazione value) {
        this.inserisciDichiarazione = value;
    }

}
