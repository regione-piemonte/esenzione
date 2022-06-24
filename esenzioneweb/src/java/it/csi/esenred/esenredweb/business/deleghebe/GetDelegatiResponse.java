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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDelegatiResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="delegati" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="delegato" type="{http://deleghebe.csi.it/}delegaCittadino" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
@XmlType(name = "getDelegatiResponse", propOrder = {
    "delegati"
})
@XmlRootElement
public class GetDelegatiResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected GetDelegatiResponse.Delegati delegati;

    /**
     * Gets the value of the delegati property.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegatiResponse.Delegati }
     *     
     */
    public GetDelegatiResponse.Delegati getDelegati() {
        return delegati;
    }

    /**
     * Sets the value of the delegati property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegatiResponse.Delegati }
     *     
     */
    public void setDelegati(GetDelegatiResponse.Delegati value) {
        this.delegati = value;
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
     *         &lt;element name="delegato" type="{http://deleghebe.csi.it/}delegaCittadino" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "delegato"
    })
    public static class Delegati {

        @XmlElement(namespace = "")
        protected List<DelegaCittadino> delegato;

        /**
         * Gets the value of the delegato property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the delegato property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDelegato().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DelegaCittadino }
         * 
         * 
         */
        public List<DelegaCittadino> getDelegato() {
            if (delegato == null) {
                delegato = new ArrayList<DelegaCittadino>();
            }
            return this.delegato;
        }

    }

}
