/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per isAliveService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della proprieta isAlive.
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
     * Imposta il valore della proprieta isAlive.
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
