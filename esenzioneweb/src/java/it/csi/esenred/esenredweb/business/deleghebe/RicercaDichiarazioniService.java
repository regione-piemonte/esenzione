/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaDichiarazioniService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDichiarazioniService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDichiarazioni" type="{http://deleghebe.csi.it/}ricercaDichiarazioni" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDichiarazioniService", propOrder = {
    "ricercaDichiarazioni"
})
public class RicercaDichiarazioniService {

    protected RicercaDichiarazioni ricercaDichiarazioni;

    /**
     * Gets the value of the ricercaDichiarazioni property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioni }
     *     
     */
    public RicercaDichiarazioni getRicercaDichiarazioni() {
        return ricercaDichiarazioni;
    }

    /**
     * Sets the value of the ricercaDichiarazioni property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioni }
     *     
     */
    public void setRicercaDichiarazioni(RicercaDichiarazioni value) {
        this.ricercaDichiarazioni = value;
    }

}
