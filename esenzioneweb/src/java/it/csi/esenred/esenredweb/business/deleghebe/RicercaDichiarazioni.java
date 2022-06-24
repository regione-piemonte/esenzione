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
 * <p>Java class for ricercaDichiarazioni complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDichiarazioni">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="modiDichiarazione" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="modoDichiarazione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="tipiDichiarazione" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tipoDichiarazione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="statiDichiarazione" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="statoDichiarazione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDichiarazioni", propOrder = {
    "modiDichiarazione",
    "tipiDichiarazione",
    "statiDichiarazione",
    "dichiarazione"
})
public class RicercaDichiarazioni
    extends ServiceRequest
{

    @XmlElement(namespace = "")
    protected RicercaDichiarazioni.ModiDichiarazione modiDichiarazione;
    @XmlElement(namespace = "")
    protected RicercaDichiarazioni.TipiDichiarazione tipiDichiarazione;
    @XmlElement(namespace = "")
    protected RicercaDichiarazioni.StatiDichiarazione statiDichiarazione;
    @XmlElement(namespace = "")
    protected Dichiarazione dichiarazione;

    /**
     * Gets the value of the modiDichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioni.ModiDichiarazione }
     *     
     */
    public RicercaDichiarazioni.ModiDichiarazione getModiDichiarazione() {
        return modiDichiarazione;
    }

    /**
     * Sets the value of the modiDichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioni.ModiDichiarazione }
     *     
     */
    public void setModiDichiarazione(RicercaDichiarazioni.ModiDichiarazione value) {
        this.modiDichiarazione = value;
    }

    /**
     * Gets the value of the tipiDichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioni.TipiDichiarazione }
     *     
     */
    public RicercaDichiarazioni.TipiDichiarazione getTipiDichiarazione() {
        return tipiDichiarazione;
    }

    /**
     * Sets the value of the tipiDichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioni.TipiDichiarazione }
     *     
     */
    public void setTipiDichiarazione(RicercaDichiarazioni.TipiDichiarazione value) {
        this.tipiDichiarazione = value;
    }

    /**
     * Gets the value of the statiDichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDichiarazioni.StatiDichiarazione }
     *     
     */
    public RicercaDichiarazioni.StatiDichiarazione getStatiDichiarazione() {
        return statiDichiarazione;
    }

    /**
     * Sets the value of the statiDichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDichiarazioni.StatiDichiarazione }
     *     
     */
    public void setStatiDichiarazione(RicercaDichiarazioni.StatiDichiarazione value) {
        this.statiDichiarazione = value;
    }

    /**
     * Gets the value of the dichiarazione property.
     * 
     * @return
     *     possible object is
     *     {@link Dichiarazione }
     *     
     */
    public Dichiarazione getDichiarazione() {
        return dichiarazione;
    }

    /**
     * Sets the value of the dichiarazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dichiarazione }
     *     
     */
    public void setDichiarazione(Dichiarazione value) {
        this.dichiarazione = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="modoDichiarazione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "modoDichiarazione"
    })
    public static class ModiDichiarazione {

        @XmlElement(namespace = "")
        protected List<String> modoDichiarazione;

        /**
         * Gets the value of the modoDichiarazione property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the modoDichiarazione property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getModoDichiarazione().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getModoDichiarazione() {
            if (modoDichiarazione == null) {
                modoDichiarazione = new ArrayList<String>();
            }
            return this.modoDichiarazione;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="statoDichiarazione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "statoDichiarazione"
    })
    public static class StatiDichiarazione {

        @XmlElement(namespace = "")
        protected List<String> statoDichiarazione;

        /**
         * Gets the value of the statoDichiarazione property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the statoDichiarazione property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatoDichiarazione().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStatoDichiarazione() {
            if (statoDichiarazione == null) {
                statoDichiarazione = new ArrayList<String>();
            }
            return this.statoDichiarazione;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="tipoDichiarazione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "tipoDichiarazione"
    })
    public static class TipiDichiarazione {

        @XmlElement(namespace = "")
        protected List<String> tipoDichiarazione;

        /**
         * Gets the value of the tipoDichiarazione property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tipoDichiarazione property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTipoDichiarazione().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getTipoDichiarazione() {
            if (tipoDichiarazione == null) {
                tipoDichiarazione = new ArrayList<String>();
            }
            return this.tipoDichiarazione;
        }

    }

}
