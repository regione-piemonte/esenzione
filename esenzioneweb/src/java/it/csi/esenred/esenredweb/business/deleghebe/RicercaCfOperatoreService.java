/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCfOperatoreService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCfOperatoreService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCfOperatoreServizio" type="{http://deleghebe.csi.it/}ricercaCfOperatore" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCfOperatoreService", propOrder = {
    "ricercaCfOperatoreServizio"
})
public class RicercaCfOperatoreService {

    protected RicercaCfOperatore ricercaCfOperatoreServizio;

    /**
     * Gets the value of the ricercaCfOperatoreServizio property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCfOperatore }
     *     
     */
    public RicercaCfOperatore getRicercaCfOperatoreServizio() {
        return ricercaCfOperatoreServizio;
    }

    /**
     * Sets the value of the ricercaCfOperatoreServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCfOperatore }
     *     
     */
    public void setRicercaCfOperatoreServizio(RicercaCfOperatore value) {
        this.ricercaCfOperatoreServizio = value;
    }

}
