/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaCittadinoService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCittadinoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaCittadino" type="{http://deleghebe.csi.it/}aggiornaCittadino" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCittadinoService", propOrder = {
    "aggiornaCittadino"
})
public class AggiornaCittadinoService {

    protected AggiornaCittadino aggiornaCittadino;

    /**
     * Gets the value of the aggiornaCittadino property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCittadino }
     *     
     */
    public AggiornaCittadino getAggiornaCittadino() {
        return aggiornaCittadino;
    }

    /**
     * Sets the value of the aggiornaCittadino property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCittadino }
     *     
     */
    public void setAggiornaCittadino(AggiornaCittadino value) {
        this.aggiornaCittadino = value;
    }

}
