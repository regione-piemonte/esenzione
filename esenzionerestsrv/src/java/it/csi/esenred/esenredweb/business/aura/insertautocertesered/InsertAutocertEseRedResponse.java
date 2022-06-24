/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.insertautocertesered;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InsertAutocertEseRedResult" type="{http://InsertAutocertEseRed.central.services.auraws.aura.csi.it}Response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "insertAutocertEseRedResult"
})
@XmlRootElement(name = "InsertAutocertEseRedResponse")
public class InsertAutocertEseRedResponse {

    @XmlElement(name = "InsertAutocertEseRedResult", required = true)
    protected Response insertAutocertEseRedResult;

    /**
     * Gets the value of the insertAutocertEseRedResult property.
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getInsertAutocertEseRedResult() {
        return insertAutocertEseRedResult;
    }

    /**
     * Sets the value of the insertAutocertEseRedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setInsertAutocertEseRedResult(Response value) {
        this.insertAutocertEseRedResult = value;
    }

}
