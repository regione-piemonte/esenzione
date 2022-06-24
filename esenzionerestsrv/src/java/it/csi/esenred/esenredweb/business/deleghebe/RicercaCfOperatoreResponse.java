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
 * <p>Classe Java per ricercaCfOperatoreResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCfOperatoreResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="operatore" type="{http://deleghebe.csi.it/}operatore" minOccurs="0" form="unqualified"/>
 *         &lt;element name="exists" type="{http://www.w3.org/2001/XMLSchema}boolean" form="unqualified"/>
 *         &lt;element name="asl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCfOperatoreResponse", propOrder = {
    "operatore",
    "exists",
    "asl"
})
public class RicercaCfOperatoreResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected Operatore operatore;
    @XmlElement(namespace = "")
    protected boolean exists;
    @XmlElement(namespace = "")
    protected String asl;

    /**
     * Recupera il valore della proprieta operatore.
     * 
     * @return
     *     possible object is
     *     {@link Operatore }
     *     
     */
    public Operatore getOperatore() {
        return operatore;
    }

    /**
     * Imposta il valore della proprieta operatore.
     * 
     * @param value
     *     allowed object is
     *     {@link Operatore }
     *     
     */
    public void setOperatore(Operatore value) {
        this.operatore = value;
    }

    /**
     * Recupera il valore della proprieta exists.
     * 
     */
    public boolean isExists() {
        return exists;
    }

    /**
     * Imposta il valore della proprieta exists.
     * 
     */
    public void setExists(boolean value) {
        this.exists = value;
    }

    /**
     * Recupera il valore della proprieta asl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsl() {
        return asl;
    }

    /**
     * Imposta il valore della proprieta asl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsl(String value) {
        this.asl = value;
    }

}
