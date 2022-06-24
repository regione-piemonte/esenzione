/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChiusuraEsenRedResult" type="{http://ChiusuraEsenRed.central.services.auraws.aura.csi.it}ChiusuraEsenRedRes"/>
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
    "chiusuraEsenRedResult"
})
@XmlRootElement(name = "ChiusuraEsenRedResponse")
public class ChiusuraEsenRedResponse {

    @XmlElement(name = "ChiusuraEsenRedResult", required = true)
    protected ChiusuraEsenRedRes chiusuraEsenRedResult;

    /**
     * Recupera il valore della proprietà chiusuraEsenRedResult.
     * 
     * @return
     *     possible object is
     *     {@link ChiusuraEsenRedRes }
     *     
     */
    public ChiusuraEsenRedRes getChiusuraEsenRedResult() {
        return chiusuraEsenRedResult;
    }

    /**
     * Imposta il valore della proprietà chiusuraEsenRedResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiusuraEsenRedRes }
     *     
     */
    public void setChiusuraEsenRedResult(ChiusuraEsenRedRes value) {
        this.chiusuraEsenRedResult = value;
    }

}
