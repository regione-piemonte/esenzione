/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaDelegaService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaDelegaService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaDelega" type="{http://deleghebe.csi.it/}aggiornaDelega" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaDelegaService", propOrder = {
    "aggiornaDelega"
})
public class AggiornaDelegaService {

    protected AggiornaDelega aggiornaDelega;

    /**
     * Recupera il valore della proprieta aggiornaDelega.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaDelega }
     *     
     */
    public AggiornaDelega getAggiornaDelega() {
        return aggiornaDelega;
    }

    /**
     * Imposta il valore della proprieta aggiornaDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaDelega }
     *     
     */
    public void setAggiornaDelega(AggiornaDelega value) {
        this.aggiornaDelega = value;
    }

}
