/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciCittadinoService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciCittadinoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciCittadino" type="{http://deleghebe.csi.it/}inserisciCittadino" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCittadinoService", propOrder = {
    "inserisciCittadino"
})
public class InserisciCittadinoService {

    protected InserisciCittadino inserisciCittadino;

    /**
     * Gets the value of the inserisciCittadino property.
     * 
     * @return
     *     possible object is
     *     {@link InserisciCittadino }
     *     
     */
    public InserisciCittadino getInserisciCittadino() {
        return inserisciCittadino;
    }

    /**
     * Sets the value of the inserisciCittadino property.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciCittadino }
     *     
     */
    public void setInserisciCittadino(InserisciCittadino value) {
        this.inserisciCittadino = value;
    }

}
