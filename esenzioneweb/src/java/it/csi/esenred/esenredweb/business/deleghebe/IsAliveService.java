/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for isAliveService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="isAliveService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isAlive" type="{http://deleghebe.csi.it/}isAlive" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isAliveService", propOrder = {
    "isAlive"
})
public class IsAliveService {

    protected IsAlive isAlive;

    /**
     * Gets the value of the isAlive property.
     * 
     * @return
     *     possible object is
     *     {@link IsAlive }
     *     
     */
    public IsAlive getIsAlive() {
        return isAlive;
    }

    /**
     * Sets the value of the isAlive property.
     * 
     * @param value
     *     allowed object is
     *     {@link IsAlive }
     *     
     */
    public void setIsAlive(IsAlive value) {
        this.isAlive = value;
    }

}
