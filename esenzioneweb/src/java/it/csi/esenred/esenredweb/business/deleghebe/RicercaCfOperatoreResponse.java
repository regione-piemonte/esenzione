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
 * <p>Java class for ricercaCfOperatoreResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCfOperatoreResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="operatore" type="{http://deleghebe.csi.it/}operatore" minOccurs="0" form="unqualified"/>
 *         &lt;element name="exists" type="{http://www.w3.org/2001/XMLSchema}boolean" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCfOperatoreResponse", propOrder = {
    "operatore",
    "exists"
})
public class RicercaCfOperatoreResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected Operatore operatore;
    @XmlElement(namespace = "")
    protected boolean exists;

    /**
     * Gets the value of the operatore property.
     * 
     * @return
     *     possible object is
     *     {@link Operatore }
     *     
     */
    public Operatore getOperatore() {
        return operatore;
    }

    /**
     * Sets the value of the operatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Operatore }
     *     
     */
    public void setOperatore(Operatore value) {
        this.operatore = value;
    }

    /**
     * Gets the value of the exists property.
     * 
     */
    public boolean isExists() {
        return exists;
    }

    /**
     * Sets the value of the exists property.
     * 
     */
    public void setExists(boolean value) {
        this.exists = value;
    }

}
