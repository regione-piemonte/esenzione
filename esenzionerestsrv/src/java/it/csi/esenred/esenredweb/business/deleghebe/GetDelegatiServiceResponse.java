/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegatiServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegatiResponse" type="{http://deleghebe.csi.it/}getDelegatiResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegatiServiceResponse", propOrder = {
    "getDelegatiResponse"
})
public class GetDelegatiServiceResponse {

    protected GetDelegatiResponse getDelegatiResponse;

    /**
     * Recupera il valore della proprieta getDelegatiResponse.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegatiResponse }
     *     
     */
    public GetDelegatiResponse getGetDelegatiResponse() {
        return getDelegatiResponse;
    }

    /**
     * Imposta il valore della proprieta getDelegatiResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegatiResponse }
     *     
     */
    public void setGetDelegatiResponse(GetDelegatiResponse value) {
        this.getDelegatiResponse = value;
    }

}
