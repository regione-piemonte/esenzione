/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaDichiarazioniService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaDichiarazioniService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaDichiarazioni" type="{http://deleghebe.csi.it/}ricercaDichiarazioni" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDichiarazioniService", propOrder = {
    "ricercaDichiarazioni"
})
public class RicercaDichiarazioniService {

    protected RicercaDichiarazioni ricercaDichiarazioni;

    /**
     * Recupera il valore della proprieta ricercaDichiarazioni.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioni }
     *     
     */
    public RicercaDichiarazioni getRicercaDichiarazioni() {
        return ricercaDichiarazioni;
    }

    /**
     * Imposta il valore della proprieta ricercaDichiarazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioni }
     *     
     */
    public void setRicercaDichiarazioni(RicercaDichiarazioni value) {
        this.ricercaDichiarazioni = value;
    }

}
