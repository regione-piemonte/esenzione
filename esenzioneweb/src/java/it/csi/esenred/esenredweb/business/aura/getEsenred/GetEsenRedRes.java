/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.getEsenred;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetEsenRedRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetEsenRedRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://GetEsenRed.central.services.auraws.aura.csi.it}GetEsenRedHeader" minOccurs="0"/>
 *         &lt;element name="body" type="{http://GetEsenRed.central.services.auraws.aura.csi.it}GetEsenRedBody" minOccurs="0"/>
 *         &lt;element name="footer" type="{http://GetEsenRed.central.services.auraws.aura.csi.it}GetEsenRedFooter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEsenRedRes", propOrder = {
    "header",
    "body",
    "footer"
})
public class GetEsenRedRes {

    protected GetEsenRedHeader header;
    protected GetEsenRedBody body;
    protected GetEsenRedFooter footer;

    /**
     * Recupera il valore della propriet header.
     * 
     * @return
     *     possible object is
     *     {@link GetEsenRedHeader }
     *     
     */
    public GetEsenRedHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della propriet header.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEsenRedHeader }
     *     
     */
    public void setHeader(GetEsenRedHeader value) {
        this.header = value;
    }

    /**
     * Recupera il valore della propriet body.
     * 
     * @return
     *     possible object is
     *     {@link GetEsenRedBody }
     *     
     */
    public GetEsenRedBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della propriet body.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEsenRedBody }
     *     
     */
    public void setBody(GetEsenRedBody value) {
        this.body = value;
    }

    /**
     * Recupera il valore della propriet footer.
     * 
     * @return
     *     possible object is
     *     {@link GetEsenRedFooter }
     *     
     */
    public GetEsenRedFooter getFooter() {
        return footer;
    }

    /**
     * Imposta il valore della propriet footer.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEsenRedFooter }
     *     
     */
    public void setFooter(GetEsenRedFooter value) {
        this.footer = value;
    }

}
