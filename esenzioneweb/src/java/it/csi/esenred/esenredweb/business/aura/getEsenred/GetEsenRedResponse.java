/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.getEsenred;

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
 *         &lt;element name="GetEsenRedResult" type="{http://GetEsenRed.central.services.auraws.aura.csi.it}GetEsenRedRes"/>
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
    "getEsenRedResult"
})
@XmlRootElement(name = "GetEsenRedResponse")
public class GetEsenRedResponse {

    @XmlElement(name = "GetEsenRedResult", required = true)
    protected GetEsenRedRes getEsenRedResult;

    /**
     * Recupera il valore della  getEsenRedResult.
     * 
     * @return
     *     possible object is
     *     {@link GetEsenRedRes }
     *     
     */
    public GetEsenRedRes getGetEsenRedResult() {
        return getEsenRedResult;
    }

    /**
     * Imposta il valore della  getEsenRedResult.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEsenRedRes }
     *     
     */
    public void setGetEsenRedResult(GetEsenRedRes value) {
        this.getEsenRedResult = value;
    }

}
