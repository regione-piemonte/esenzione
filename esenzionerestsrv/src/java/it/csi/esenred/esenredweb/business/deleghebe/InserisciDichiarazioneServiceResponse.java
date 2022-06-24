/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciDichiarazioneServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciDichiarazioneServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciDichiarazioneResponse" type="{http://deleghebe.csi.it/}inserisciDichiarazioneResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciDichiarazioneServiceResponse", propOrder = {
    "inserisciDichiarazioneResponse"
})
public class InserisciDichiarazioneServiceResponse {

    protected InserisciDichiarazioneResponse inserisciDichiarazioneResponse;

    /**
     * Recupera il valore della proprieta inserisciDichiarazioneResponse.
     * 
     * @return
     *     possible object is
     *     {@link InserisciDichiarazioneResponse }
     *     
     */
    public InserisciDichiarazioneResponse getInserisciDichiarazioneResponse() {
        return inserisciDichiarazioneResponse;
    }

    /**
     * Imposta il valore della proprieta inserisciDichiarazioneResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciDichiarazioneResponse }
     *     
     */
    public void setInserisciDichiarazioneResponse(InserisciDichiarazioneResponse value) {
        this.inserisciDichiarazioneResponse = value;
    }

}
