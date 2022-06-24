/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per saveDelegatiServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="saveDelegatiServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saveDelegatiResponse" type="{http://deleghebe.csi.it/}saveDelegatiResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveDelegatiServiceResponse", propOrder = {
    "saveDelegatiResponse"
})
public class SaveDelegatiServiceResponse {

    protected SaveDelegatiResponse saveDelegatiResponse;

    /**
     * Recupera il valore della proprieta saveDelegatiResponse.
     * 
     * @return
     *     possible object is
     *     {@link SaveDelegatiResponse }
     *     
     */
    public SaveDelegatiResponse getSaveDelegatiResponse() {
        return saveDelegatiResponse;
    }

    /**
     * Imposta il valore della proprieta saveDelegatiResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveDelegatiResponse }
     *     
     */
    public void setSaveDelegatiResponse(SaveDelegatiResponse value) {
        this.saveDelegatiResponse = value;
    }

}
