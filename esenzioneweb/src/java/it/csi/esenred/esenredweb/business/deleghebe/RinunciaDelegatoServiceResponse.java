/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rinunciaDelegatoServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rinunciaDelegatoServiceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rinunciaDelegatoResponse" type="{http://deleghebe.csi.it/}rinunciaDelegatoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rinunciaDelegatoServiceResponse", propOrder = {
    "rinunciaDelegatoResponse"
})
public class RinunciaDelegatoServiceResponse {

    protected RinunciaDelegatoResponse rinunciaDelegatoResponse;

    /**
     * Gets the value of the rinunciaDelegatoResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RinunciaDelegatoResponse }
     *     
     */
    public RinunciaDelegatoResponse getRinunciaDelegatoResponse() {
        return rinunciaDelegatoResponse;
    }

    /**
     * Sets the value of the rinunciaDelegatoResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RinunciaDelegatoResponse }
     *     
     */
    public void setRinunciaDelegatoResponse(RinunciaDelegatoResponse value) {
        this.rinunciaDelegatoResponse = value;
    }

}
