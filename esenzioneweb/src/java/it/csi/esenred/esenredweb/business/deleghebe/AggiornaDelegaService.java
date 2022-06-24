/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaDelegaService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the aggiornaDelega property.
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
     * Sets the value of the aggiornaDelega property.
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
