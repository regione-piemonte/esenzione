/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaDichiarazioniResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaDichiarazioniResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="dichiarazioni" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDichiarazioniResponse", propOrder = {
    "dichiarazioni"
})
public class RicercaDichiarazioniResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected RicercaDichiarazioniResponse.Dichiarazioni dichiarazioni;

    /**
     * Recupera il valore della proprieta dichiarazioni.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioniResponse.Dichiarazioni }
     *     
     */
    public RicercaDichiarazioniResponse.Dichiarazioni getDichiarazioni() {
        return dichiarazioni;
    }

    /**
     * Imposta il valore della proprieta dichiarazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioniResponse.Dichiarazioni }
     *     
     */
    public void setDichiarazioni(RicercaDichiarazioniResponse.Dichiarazioni value) {
        this.dichiarazioni = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "dichiarazione"
    })
    public static class Dichiarazioni {

        @XmlElement(namespace = "")
        protected List<Dichiarazione> dichiarazione;

        /**
         * Gets the value of the dichiarazione property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dichiarazione property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDichiarazione().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Dichiarazione }
         * 
         * 
         */
        public List<Dichiarazione> getDichiarazione() {
            if (dichiarazione == null) {
                dichiarazione = new ArrayList<Dichiarazione>();
            }
            return this.dichiarazione;
        }

    }

}
