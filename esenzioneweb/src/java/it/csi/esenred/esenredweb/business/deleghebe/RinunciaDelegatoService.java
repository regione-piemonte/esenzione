/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rinunciaDelegatoService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rinunciaDelegatoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rinunciaDelegato" type="{http://deleghebe.csi.it/}rinunciaDelegato" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rinunciaDelegatoService", propOrder = {
    "rinunciaDelegato"
})
public class RinunciaDelegatoService {

    protected RinunciaDelegato rinunciaDelegato;

    /**
     * Gets the value of the rinunciaDelegato property.
     * 
     * @return
     *     possible object is
     *     {@link RinunciaDelegato }
     *     
     */
    public RinunciaDelegato getRinunciaDelegato() {
        return rinunciaDelegato;
    }

    /**
     * Sets the value of the rinunciaDelegato property.
     * 
     * @param value
     *     allowed object is
     *     {@link RinunciaDelegato }
     *     
     */
    public void setRinunciaDelegato(RinunciaDelegato value) {
        this.rinunciaDelegato = value;
    }

}
