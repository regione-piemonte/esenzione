/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaCittadinoServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCittadinoServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaCittadinoResponse" type="{http://deleghebe.csi.it/}aggiornaCittadinoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCittadinoServiceResponse", propOrder = {
    "aggiornaCittadinoResponse"
})
public class AggiornaCittadinoServiceResponse {

    protected AggiornaCittadinoResponse aggiornaCittadinoResponse;

    /**
     * Recupera il valore della proprieta aggiornaCittadinoResponse.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCittadinoResponse }
     *     
     */
    public AggiornaCittadinoResponse getAggiornaCittadinoResponse() {
        return aggiornaCittadinoResponse;
    }

    /**
     * Imposta il valore della proprieta aggiornaCittadinoResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCittadinoResponse }
     *     
     */
    public void setAggiornaCittadinoResponse(AggiornaCittadinoResponse value) {
        this.aggiornaCittadinoResponse = value;
    }

}
