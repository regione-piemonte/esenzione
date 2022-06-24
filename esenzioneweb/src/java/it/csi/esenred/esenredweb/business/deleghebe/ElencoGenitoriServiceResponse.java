/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elencoGenitoriServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the elencoGenitoreResponse property.
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
     * Sets the value of the elencoGenitoreResponse property.
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
