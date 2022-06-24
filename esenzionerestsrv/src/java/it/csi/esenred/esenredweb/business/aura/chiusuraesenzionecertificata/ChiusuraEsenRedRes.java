/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ChiusuraEsenRedRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ChiusuraEsenRedRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://ChiusuraEsenRed.central.services.auraws.aura.csi.it}ChiusuraEsenRedHeader" minOccurs="0"/>
 *         &lt;element name="body" type="{http://ChiusuraEsenRed.central.services.auraws.aura.csi.it}ChiusuraEsenRedBody" minOccurs="0"/>
 *         &lt;element name="footer" type="{http://ChiusuraEsenRed.central.services.auraws.aura.csi.it}ChiusuraEsenRedFooter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChiusuraEsenRedRes", propOrder = {
    "header",
    "body",
    "footer"
})
@XmlRootElement
public class ChiusuraEsenRedRes {

    protected ChiusuraEsenRedHeader header;
    protected ChiusuraEsenRedBody body;
    protected ChiusuraEsenRedFooter footer;

    /**
     * Recupera il valore della proprieta header.
     * 
     * @return
     *     possible object is
     *     {@link ChiusuraEsenRedHeader }
     *     
     */
    public ChiusuraEsenRedHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprieta header.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiusuraEsenRedHeader }
     *     
     */
    public void setHeader(ChiusuraEsenRedHeader value) {
        this.header = value;
    }

    /**
     * Recupera il valore della proprieta body.
     * 
     * @return
     *     possible object is
     *     {@link ChiusuraEsenRedBody }
     *     
     */
    public ChiusuraEsenRedBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprieta body.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiusuraEsenRedBody }
     *     
     */
    public void setBody(ChiusuraEsenRedBody value) {
        this.body = value;
    }

    /**
     * Recupera il valore della proprieta footer.
     * 
     * @return
     *     possible object is
     *     {@link ChiusuraEsenRedFooter }
     *     
     */
    public ChiusuraEsenRedFooter getFooter() {
        return footer;
    }

    /**
     * Imposta il valore della proprieta footer.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiusuraEsenRedFooter }
     *     
     */
    public void setFooter(ChiusuraEsenRedFooter value) {
        this.footer = value;
    }

}
