/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFileDocumentoPerIdResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFileDocumentoPerIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaFileDocumentoPerId" type="{http://deleghebe.csi.it/}ricercaFileResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFileDocumentoPerIdResponse", propOrder = {
    "ricercaFileDocumentoPerId"
})
public class RicercaFileDocumentoPerIdResponse {

    protected RicercaFileResponse ricercaFileDocumentoPerId;

    /**
     * Recupera il valore della proprieta ricercaFileDocumentoPerId.
     * 
     * @return
     *     possible object is
     *     {@link RicercaFileResponse }
     *     
     */
    public RicercaFileResponse getRicercaFileDocumentoPerId() {
        return ricercaFileDocumentoPerId;
    }

    /**
     * Imposta il valore della proprieta ricercaFileDocumentoPerId.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaFileResponse }
     *     
     */
    public void setRicercaFileDocumentoPerId(RicercaFileResponse value) {
        this.ricercaFileDocumentoPerId = value;
    }

}
