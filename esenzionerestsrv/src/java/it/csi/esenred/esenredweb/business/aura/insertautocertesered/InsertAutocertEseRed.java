/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.insertautocertesered;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newEseRed" type="{http://InsertAutocertEseRed.central.services.auraws.aura.csi.it}Request" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "newEseRed"
})
@XmlRootElement(name = "InsertAutocertEseRed")
public class InsertAutocertEseRed {

    protected Request newEseRed;

    /**
     * Gets the value of the newEseRed property.
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getNewEseRed() {
        return newEseRed;
    }

    /**
     * Sets the value of the newEseRed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setNewEseRed(Request value) {
        this.newEseRed = value;
    }

}
