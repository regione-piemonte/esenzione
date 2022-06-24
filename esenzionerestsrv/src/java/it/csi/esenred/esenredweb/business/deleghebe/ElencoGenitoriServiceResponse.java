/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per elencoGenitoriServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="elencoGenitoriServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="elencoGenitoreResponse" type="{http://deleghebe.csi.it/}elencoGenitoriResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elencoGenitoriServiceResponse", propOrder = {
    "elencoGenitoreResponse"
})
public class ElencoGenitoriServiceResponse {

    protected ElencoGenitoriResponse elencoGenitoreResponse;

    /**
     * Recupera il valore della proprieta elencoGenitoreResponse.
     * 
     * @return
     *     possible object is
     *     {@link ElencoGenitoriResponse }
     *     
     */
    public ElencoGenitoriResponse getElencoGenitoreResponse() {
        return elencoGenitoreResponse;
    }

    /**
     * Imposta il valore della proprieta elencoGenitoreResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoGenitoriResponse }
     *     
     */
    public void setElencoGenitoreResponse(ElencoGenitoriResponse value) {
        this.elencoGenitoreResponse = value;
    }

}
