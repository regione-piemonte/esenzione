/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaDelegheService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaDelegheService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDeleghe" type="{http://deleghebe.csi.it/}ricercaDeleghe" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDelegheService", propOrder = {
    "ricercaDeleghe"
})
public class RicercaDelegheService {

    protected RicercaDeleghe ricercaDeleghe;

    /**
     * Recupera il valore della proprieta ricercaDeleghe.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDeleghe }
     *     
     */
    public RicercaDeleghe getRicercaDeleghe() {
        return ricercaDeleghe;
    }

    /**
     * Imposta il valore della proprieta ricercaDeleghe.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDeleghe }
     *     
     */
    public void setRicercaDeleghe(RicercaDeleghe value) {
        this.ricercaDeleghe = value;
    }

}
