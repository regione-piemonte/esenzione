/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per saveDelegatiService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="saveDelegatiService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saveDelegati" type="{http://deleghebe.csi.it/}saveDelegati" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveDelegatiService", propOrder = {
    "saveDelegati"
})
public class SaveDelegatiService {

    protected SaveDelegati saveDelegati;

    /**
     * Recupera il valore della proprieta saveDelegati.
     * 
     * @return
     *     possible object is
     *     {@link SaveDelegati }
     *     
     */
    public SaveDelegati getSaveDelegati() {
        return saveDelegati;
    }

    /**
     * Imposta il valore della proprieta saveDelegati.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveDelegati }
     *     
     */
    public void setSaveDelegati(SaveDelegati value) {
        this.saveDelegati = value;
    }

}
