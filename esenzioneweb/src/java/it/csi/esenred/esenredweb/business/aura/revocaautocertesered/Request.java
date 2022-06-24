/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.revocaautocertesered;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Request">
 *   &lt;complexContent>
 *     &lt;extension base="{http://RevocaAutocertEseRed.central.services.auraws.aura.csi.it}Ens_Request">
 *       &lt;sequence>
 *         &lt;element name="body" type="{http://RevocaAutocertEseRed.central.services.auraws.aura.csi.it}RequestBody"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "body"
})
@XmlRootElement
public class Request
    extends EnsRequest
{

    @XmlElement(required = true)
    protected RequestBody body;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link RequestBody }
     *     
     */
    public RequestBody getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestBody }
     *     
     */
    public void setBody(RequestBody value) {
        this.body = value;
    }

}
