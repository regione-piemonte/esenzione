/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegantiServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegantiServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegantiResponse" type="{http://deleghebe.csi.it/}getDelegantiResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegantiServiceResponse", propOrder = {
    "getDelegantiResponse"
})
public class GetDelegantiServiceResponse {

    protected GetDelegantiResponse getDelegantiResponse;

    /**
     * Recupera il valore della proprieta getDelegantiResponse.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegantiResponse }
     *     
     */
    public GetDelegantiResponse getGetDelegantiResponse() {
        return getDelegantiResponse;
    }

    /**
     * Imposta il valore della proprieta getDelegantiResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegantiResponse }
     *     
     */
    public void setGetDelegantiResponse(GetDelegantiResponse value) {
        this.getDelegantiResponse = value;
    }

}
