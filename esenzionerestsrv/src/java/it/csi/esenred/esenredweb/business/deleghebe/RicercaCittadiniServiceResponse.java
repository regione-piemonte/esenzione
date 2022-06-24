/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaCittadiniServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCittadiniServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCittadiniResponse" type="{http://deleghebe.csi.it/}ricercaCittadiniResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCittadiniServiceResponse", propOrder = {
    "ricercaCittadiniResponse"
})
public class RicercaCittadiniServiceResponse {

    protected RicercaCittadiniResponse ricercaCittadiniResponse;

    /**
     * Recupera il valore della proprieta ricercaCittadiniResponse.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCittadiniResponse }
     *     
     */
    public RicercaCittadiniResponse getRicercaCittadiniResponse() {
        return ricercaCittadiniResponse;
    }

    /**
     * Imposta il valore della proprieta ricercaCittadiniResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCittadiniResponse }
     *     
     */
    public void setRicercaCittadiniResponse(RicercaCittadiniResponse value) {
        this.ricercaCittadiniResponse = value;
    }

}
