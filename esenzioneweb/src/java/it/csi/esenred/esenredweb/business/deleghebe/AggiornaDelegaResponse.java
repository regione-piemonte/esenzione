/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaDelegaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaDelegaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="delega" type="{http://deleghebe.csi.it/}delega" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaDelegaResponse", propOrder = {
    "delega"
})
public class AggiornaDelegaResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected Delega delega;

    /**
     * Gets the value of the delega property.
     * 
     * @return
     *     possible object is
     *     {@link Delega }
     *     
     */
    public Delega getDelega() {
        return delega;
    }

    /**
     * Sets the value of the delega property.
     * 
     * @param value
     *     allowed object is
     *     {@link Delega }
     *     
     */
    public void setDelega(Delega value) {
        this.delega = value;
    }

}
