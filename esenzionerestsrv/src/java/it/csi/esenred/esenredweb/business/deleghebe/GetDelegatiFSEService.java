/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegatiFSEService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiFSEService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegatiFSE" type="{http://deleghebe.csi.it/}getDelegatiFSE" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegatiFSEService", propOrder = {
    "getDelegatiFSE"
})
public class GetDelegatiFSEService {

    protected GetDelegatiFSE getDelegatiFSE;

    /**
     * Recupera il valore della proprieta getDelegatiFSE.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegatiFSE }
     *     
     */
    public GetDelegatiFSE getGetDelegatiFSE() {
        return getDelegatiFSE;
    }

    /**
     * Imposta il valore della proprieta getDelegatiFSE.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegatiFSE }
     *     
     */
    public void setGetDelegatiFSE(GetDelegatiFSE value) {
        this.getDelegatiFSE = value;
    }

}
