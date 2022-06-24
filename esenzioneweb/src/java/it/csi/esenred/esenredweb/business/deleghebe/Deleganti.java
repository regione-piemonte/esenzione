/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleganti complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleganti">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="delegante" type="{http://deleghebe.csi.it/}delegaCittadino" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleganti", propOrder = {
    "delegante"
})
public class Deleganti {

    protected DelegaCittadino delegante;

    /**
     * Gets the value of the delegante property.
     * 
     * @return
     *     possible object is
     *     {@link DelegaCittadino }
     *     
     */
    public DelegaCittadino getDelegante() {
        return delegante;
    }

    /**
     * Sets the value of the delegante property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegaCittadino }
     *     
     */
    public void setDelegante(DelegaCittadino value) {
        this.delegante = value;
    }

}
