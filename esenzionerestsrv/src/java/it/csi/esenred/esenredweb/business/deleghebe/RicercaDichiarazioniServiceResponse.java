/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaDichiarazioniServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaDichiarazioniServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDichiarazioniResponse" type="{http://deleghebe.csi.it/}ricercaDichiarazioniResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDichiarazioniServiceResponse", propOrder = {
    "ricercaDichiarazioniResponse"
})
public class RicercaDichiarazioniServiceResponse {

    protected RicercaDichiarazioniResponse ricercaDichiarazioniResponse;

    /**
     * Recupera il valore della proprieta ricercaDichiarazioniResponse.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioniResponse }
     *     
     */
    public RicercaDichiarazioniResponse getRicercaDichiarazioniResponse() {
        return ricercaDichiarazioniResponse;
    }

    /**
     * Imposta il valore della proprieta ricercaDichiarazioniResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioniResponse }
     *     
     */
    public void setRicercaDichiarazioniResponse(RicercaDichiarazioniResponse value) {
        this.ricercaDichiarazioniResponse = value;
    }

}
