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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ricercaDichiarazioni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
 *         &lt;element name="cittadinoCF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/>
 *         &lt;element name="dataInserimentoA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="unqualified"/>
 *         &lt;element name="dataInserimentoDa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="unqualified"/>
 *         &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" minOccurs="0" form="unqualified"/>
 *         &lt;element name="ruoloCittadino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/>
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
    "cittadinoCF",
    "dataInserimentoA",
    "dataInserimentoDa",
    "dichiarazione",
    "ruoloCittadino"
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
    protected String cittadinoCF;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimentoA;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimentoDa;
    @XmlElement(namespace = "")
    protected Dichiarazione dichiarazione;
    @XmlElement(namespace = "")
    protected String ruoloCittadino;

    /**
     * Recupera il valore della proprieta modiDichiarazione.
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
     * Imposta il valore della proprieta modiDichiarazione.
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
     * Recupera il valore della proprieta tipiDichiarazione.
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
     * Imposta il valore della proprieta tipiDichiarazione.
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
     * Recupera il valore della proprieta statiDichiarazione.
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
     * Imposta il valore della proprieta statiDichiarazione.
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
     * Recupera il valore della proprieta cittadinoCF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCittadinoCF() {
        return cittadinoCF;
    }

    /**
     * Imposta il valore della proprieta cittadinoCF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCittadinoCF(String value) {
        this.cittadinoCF = value;
    }

    /**
     * Recupera il valore della proprieta dataInserimentoA.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimentoA() {
        return dataInserimentoA;
    }

    /**
     * Imposta il valore della proprieta dataInserimentoA.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimentoA(XMLGregorianCalendar value) {
        this.dataInserimentoA = value;
    }

    /**
     * Recupera il valore della proprieta dataInserimentoDa.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimentoDa() {
        return dataInserimentoDa;
    }

    /**
     * Imposta il valore della proprieta dataInserimentoDa.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimentoDa(XMLGregorianCalendar value) {
        this.dataInserimentoDa = value;
    }

    /**
     * Recupera il valore della proprieta dichiarazione.
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
     * Imposta il valore della proprieta dichiarazione.
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
     * Recupera il valore della proprieta ruoloCittadino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuoloCittadino() {
        return ruoloCittadino;
    }

    /**
     * Imposta il valore della proprieta ruoloCittadino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuoloCittadino(String value) {
        this.ruoloCittadino = value;
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
