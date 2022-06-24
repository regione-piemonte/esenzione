/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaCfOperatoreService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCfOperatoreService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCfOperatoreServizio" type="{http://deleghebe.csi.it/}ricercaCfOperatore" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCfOperatoreService", propOrder = {
    "ricercaCfOperatoreServizio"
})
public class RicercaCfOperatoreService {

    protected RicercaCfOperatore ricercaCfOperatoreServizio;

    /**
     * Recupera il valore della proprieta ricercaCfOperatoreServizio.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCfOperatore }
     *     
     */
    public RicercaCfOperatore getRicercaCfOperatoreServizio() {
        return ricercaCfOperatoreServizio;
    }

    /**
     * Imposta il valore della proprieta ricercaCfOperatoreServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCfOperatore }
     *     
     */
    public void setRicercaCfOperatoreServizio(RicercaCfOperatore value) {
        this.ricercaCfOperatoreServizio = value;
    }

}
