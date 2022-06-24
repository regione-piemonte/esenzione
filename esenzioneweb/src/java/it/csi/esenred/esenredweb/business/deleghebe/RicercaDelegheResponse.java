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
 * <p>Java class for ricercaDelegheResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaDelegheResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="deleghe" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="delega" type="{http://deleghebe.csi.it/}delega" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
@XmlType(name = "ricercaDelegheResponse", propOrder = {
    "deleghe"
})
public class RicercaDelegheResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected RicercaDelegheResponse.Deleghe deleghe;

    /**
     * Gets the value of the deleghe property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDelegheResponse.Deleghe }
     *     
     */
    public RicercaDelegheResponse.Deleghe getDeleghe() {
        return deleghe;
    }

    /**
     * Sets the value of the deleghe property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDelegheResponse.Deleghe }
     *     
     */
    public void setDeleghe(RicercaDelegheResponse.Deleghe value) {
        this.deleghe = value;
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
     *         &lt;element name="delega" type="{http://deleghebe.csi.it/}delega" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "delega"
    })
    public static class Deleghe {

        @XmlElement(namespace = "")
        protected List<Delega> delega;

        /**
         * Gets the value of the delega property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the delega property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDelega().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Delega }
         * 
         * 
         */
        public List<Delega> getDelega() {
            if (delega == null) {
                delega = new ArrayList<Delega>();
            }
            return this.delega;
        }

    }

}
