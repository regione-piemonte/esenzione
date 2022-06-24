/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFileDocumentoPerId complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFileDocumentoPerId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaFile" type="{http://deleghebe.csi.it/}ricercaFile" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFileDocumentoPerId", propOrder = {
    "ricercaFile"
})
public class RicercaFileDocumentoPerId {

    protected RicercaFile ricercaFile;

    /**
     * Recupera il valore della proprieta ricercaFile.
     * 
     * @return
     *     possible object is
     *     {@link RicercaFile }
     *     
     */
    public RicercaFile getRicercaFile() {
        return ricercaFile;
    }

    /**
     * Imposta il valore della proprieta ricercaFile.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaFile }
     *     
     */
    public void setRicercaFile(RicercaFile value) {
        this.ricercaFile = value;
    }

}
