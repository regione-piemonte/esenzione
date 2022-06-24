/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegantiService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegantiService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDeleganti" type="{http://deleghebe.csi.it/}getDeleganti" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegantiService", propOrder = {
    "getDeleganti"
})
public class GetDelegantiService {

    protected GetDeleganti getDeleganti;

    /**
     * Recupera il valore della proprieta getDeleganti.
     * 
     * @return
     *     possible object is
     *     {@link GetDeleganti }
     *     
     */
    public GetDeleganti getGetDeleganti() {
        return getDeleganti;
    }

    /**
     * Imposta il valore della proprieta getDeleganti.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDeleganti }
     *     
     */
    public void setGetDeleganti(GetDeleganti value) {
        this.getDeleganti = value;
    }

}
