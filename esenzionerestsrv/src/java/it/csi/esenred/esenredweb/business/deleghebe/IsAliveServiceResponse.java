/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per isAliveServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="isAliveServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isAliveResponse" type="{http://deleghebe.csi.it/}isAliveResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isAliveServiceResponse", propOrder = {
    "isAliveResponse"
})
public class IsAliveServiceResponse {

    protected IsAliveResponse isAliveResponse;

    /**
     * Recupera il valore della proprieta isAliveResponse.
     * 
     * @return
     *     possible object is
     *     {@link IsAliveResponse }
     *     
     */
    public IsAliveResponse getIsAliveResponse() {
        return isAliveResponse;
    }

    /**
     * Imposta il valore della proprieta isAliveResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link IsAliveResponse }
     *     
     */
    public void setIsAliveResponse(IsAliveResponse value) {
        this.isAliveResponse = value;
    }

}
