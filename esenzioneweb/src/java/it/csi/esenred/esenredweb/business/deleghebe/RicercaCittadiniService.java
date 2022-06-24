/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCittadiniService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCittadiniService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCittadino" type="{http://deleghebe.csi.it/}ricercaCittadini" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCittadiniService", propOrder = {
    "ricercaCittadino"
})
public class RicercaCittadiniService {

    protected RicercaCittadini ricercaCittadino;

    /**
     * Gets the value of the ricercaCittadino property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCittadini }
     *     
     */
    public RicercaCittadini getRicercaCittadino() {
        return ricercaCittadino;
    }

    /**
     * Sets the value of the ricercaCittadino property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCittadini }
     *     
     */
    public void setRicercaCittadino(RicercaCittadini value) {
        this.ricercaCittadino = value;
    }

}
