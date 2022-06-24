/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.iterrogamefesenredd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InterrogaMefEsenreddRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InterrogaMefEsenreddRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="esitomef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="body" type="{http://InterrogaMefEsenredd.central.services.auraws.aura.csi.it}InterrogaMefEsenreddResponseBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterrogaMefEsenreddRes", propOrder = {
    "errorCode",
    "esitomef",
    "body"
})
@XmlRootElement
public class InterrogaMefEsenreddRes {

    protected Long errorCode;
    protected String esitomef;
    protected InterrogaMefEsenreddResponseBody body;

    /**
     * Recupera il valore della proprieta errorCode.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * Imposta il valore della proprieta errorCode.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setErrorCode(Long value) {
        this.errorCode = value;
    }

    /**
     * Recupera il valore della proprieta esitomef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsitomef() {
        return esitomef;
    }

    /**
     * Imposta il valore della proprieta esitomef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsitomef(String value) {
        this.esitomef = value;
    }

    /**
     * Recupera il valore della proprieta body.
     * 
     * @return
     *     possible object is
     *     {@link InterrogaMefEsenreddResponseBody }
     *     
     */
    public InterrogaMefEsenreddResponseBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprieta body.
     * 
     * @param value
     *     allowed object is
     *     {@link InterrogaMefEsenreddResponseBody }
     *     
     */
    public void setBody(InterrogaMefEsenreddResponseBody value) {
        this.body = value;
    }

}
