/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaDelegaServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaDelegaServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaDelegaResponse" type="{http://deleghebe.csi.it/}aggiornaDelegaResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaDelegaServiceResponse", propOrder = {
    "aggiornaDelegaResponse"
})
public class AggiornaDelegaServiceResponse {

    protected AggiornaDelegaResponse aggiornaDelegaResponse;

    /**
     * Recupera il valore della proprieta aggiornaDelegaResponse.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaDelegaResponse }
     *     
     */
    public AggiornaDelegaResponse getAggiornaDelegaResponse() {
        return aggiornaDelegaResponse;
    }

    /**
     * Imposta il valore della proprieta aggiornaDelegaResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaDelegaResponse }
     *     
     */
    public void setAggiornaDelegaResponse(AggiornaDelegaResponse value) {
        this.aggiornaDelegaResponse = value;
    }

}
