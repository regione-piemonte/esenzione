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
 * <p>Java class for ricercaDeleghe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDeleghe">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="codiciServizio" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="codiceServizio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="statiDelega" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlType(name = "ricercaDeleghe", propOrder = {
    "codiciServizio",
    "statiDelega",
    "delega"
})
public class RicercaDeleghe
    extends ServiceRequest
{

    @XmlElement(namespace = "")
    protected RicercaDeleghe.CodiciServizio codiciServizio;
    @XmlElement(namespace = "")
    protected RicercaDeleghe.StatiDelega statiDelega;
    @XmlElement(namespace = "")
    protected Delega delega;

    /**
     * Gets the value of the codiciServizio property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDeleghe.CodiciServizio }
     *     
     */
    public RicercaDeleghe.CodiciServizio getCodiciServizio() {
        return codiciServizio;
    }

    /**
     * Sets the value of the codiciServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDeleghe.CodiciServizio }
     *     
     */
    public void setCodiciServizio(RicercaDeleghe.CodiciServizio value) {
        this.codiciServizio = value;
    }

    /**
     * Gets the value of the statiDelega property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDeleghe.StatiDelega }
     *     
     */
    public RicercaDeleghe.StatiDelega getStatiDelega() {
        return statiDelega;
    }

    /**
     * Sets the value of the statiDelega property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDeleghe.StatiDelega }
     *     
     */
    public void setStatiDelega(RicercaDeleghe.StatiDelega value) {
        this.statiDelega = value;
    }

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
     *         &lt;element name="codiceServizio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "codiceServizio"
    })
    public static class CodiciServizio {

        @XmlElement(namespace = "")
        protected List<String> codiceServizio;

        /**
         * Gets the value of the codiceServizio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the codiceServizio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCodiceServizio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCodiceServizio() {
            if (codiceServizio == null) {
                codiceServizio = new ArrayList<String>();
            }
            return this.codiceServizio;
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
     *         &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "statoDelega"
    })
    public static class StatiDelega {

        @XmlElement(namespace = "")
        protected List<String> statoDelega;

        /**
         * Gets the value of the statoDelega property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the statoDelega property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatoDelega().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStatoDelega() {
            if (statoDelega == null) {
                statoDelega = new ArrayList<String>();
            }
            return this.statoDelega;
        }

    }

}
