/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaDelegheService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDelegheService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDeleghe" type="{http://deleghebe.csi.it/}ricercaDeleghe" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDelegheService", propOrder = {
    "ricercaDeleghe"
})
public class RicercaDelegheService {

    protected RicercaDeleghe ricercaDeleghe;

    /**
     * Gets the value of the ricercaDeleghe property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDeleghe }
     *     
     */
    public RicercaDeleghe getRicercaDeleghe() {
        return ricercaDeleghe;
    }

    /**
     * Sets the value of the ricercaDeleghe property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDeleghe }
     *     
     */
    public void setRicercaDeleghe(RicercaDeleghe value) {
        this.ricercaDeleghe = value;
    }

}
