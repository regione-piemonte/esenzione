/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaCfOperatoreServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCfOperatoreServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCfOperatoreResponse" type="{http://deleghebe.csi.it/}ricercaCfOperatoreResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCfOperatoreServiceResponse", propOrder = {
    "ricercaCfOperatoreResponse"
})
public class RicercaCfOperatoreServiceResponse {

    protected RicercaCfOperatoreResponse ricercaCfOperatoreResponse;

    /**
     * Recupera il valore della proprieta ricercaCfOperatoreResponse.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCfOperatoreResponse }
     *     
     */
    public RicercaCfOperatoreResponse getRicercaCfOperatoreResponse() {
        return ricercaCfOperatoreResponse;
    }

    /**
     * Imposta il valore della proprieta ricercaCfOperatoreResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCfOperatoreResponse }
     *     
     */
    public void setRicercaCfOperatoreResponse(RicercaCfOperatoreResponse value) {
        this.ricercaCfOperatoreResponse = value;
    }

}
