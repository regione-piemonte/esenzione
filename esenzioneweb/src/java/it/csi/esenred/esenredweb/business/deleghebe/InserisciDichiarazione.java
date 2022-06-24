/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciDichiarazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciDichiarazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciDichiarazione", propOrder = {
    "dichiarazione"
})
public class InserisciDichiarazione
    extends ServiceRequest
{

    @XmlElement(namespace = "")
    protected Dichiarazione dichiarazione;

    /**
     * Gets the value of the dichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link Dichiarazione }
     *     
     */
    public Dichiarazione getDichiarazione() {
        return dichiarazione;
    }

    /**
     * Sets the value of the dichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dichiarazione }
     *     
     */
    public void setDichiarazione(Dichiarazione value) {
        this.dichiarazione = value;
    }

}
