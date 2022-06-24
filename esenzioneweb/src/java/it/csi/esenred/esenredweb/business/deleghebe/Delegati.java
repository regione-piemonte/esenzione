/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for delegati complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delegati">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="delegato" type="{http://deleghebe.csi.it/}delegaCittadino" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delegati", propOrder = {
    "delegato"
})
public class Delegati {

    protected DelegaCittadino delegato;

    /**
     * Gets the value of the delegato property.
     * 
     * @return
     *     possible object is
     *     {@link DelegaCittadino }
     *     
     */
    public DelegaCittadino getDelegato() {
        return delegato;
    }

    /**
     * Sets the value of the delegato property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegaCittadino }
     *     
     */
    public void setDelegato(DelegaCittadino value) {
        this.delegato = value;
    }

}
