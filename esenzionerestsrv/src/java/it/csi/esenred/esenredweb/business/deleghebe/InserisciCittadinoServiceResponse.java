/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciCittadinoServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciCittadinoServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciCittadinoResponse" type="{http://deleghebe.csi.it/}inserisciCittadinoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCittadinoServiceResponse", propOrder = {
    "inserisciCittadinoResponse"
})
public class InserisciCittadinoServiceResponse {

    protected InserisciCittadinoResponse inserisciCittadinoResponse;

    /**
     * Recupera il valore della proprieta inserisciCittadinoResponse.
     * 
     * @return
     *     possible object is
     *     {@link InserisciCittadinoResponse }
     *     
     */
    public InserisciCittadinoResponse getInserisciCittadinoResponse() {
        return inserisciCittadinoResponse;
    }

    /**
     * Imposta il valore della proprieta inserisciCittadinoResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciCittadinoResponse }
     *     
     */
    public void setInserisciCittadinoResponse(InserisciCittadinoResponse value) {
        this.inserisciCittadinoResponse = value;
    }

}
