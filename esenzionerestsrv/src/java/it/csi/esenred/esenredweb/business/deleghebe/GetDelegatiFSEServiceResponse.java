/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegatiFSEServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiFSEServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegatiFSEResponse" type="{http://deleghebe.csi.it/}getDelegatiFSEResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegatiFSEServiceResponse", propOrder = {
    "getDelegatiFSEResponse"
})
public class GetDelegatiFSEServiceResponse {

    protected GetDelegatiFSEResponse getDelegatiFSEResponse;

    /**
     * Recupera il valore della proprieta getDelegatiFSEResponse.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegatiFSEResponse }
     *     
     */
    public GetDelegatiFSEResponse getGetDelegatiFSEResponse() {
        return getDelegatiFSEResponse;
    }

    /**
     * Imposta il valore della proprieta getDelegatiFSEResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegatiFSEResponse }
     *     
     */
    public void setGetDelegatiFSEResponse(GetDelegatiFSEResponse value) {
        this.getDelegatiFSEResponse = value;
    }

}
