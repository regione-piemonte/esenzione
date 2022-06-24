/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per rinunciaDelegatoServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della proprieta rinunciaDelegatoResponse.
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
     * Imposta il valore della proprieta rinunciaDelegatoResponse.
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
