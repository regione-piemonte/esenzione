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
 * <p>Classe Java per ricercaCittadiniResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCittadiniResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="cittadini" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cittadino" type="{http://deleghebe.csi.it/}cittadino" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
@XmlType(name = "ricercaCittadiniResponse", propOrder = {
    "cittadini"
})
@XmlRootElement
public class RicercaCittadiniResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected RicercaCittadiniResponse.Cittadini cittadini;

    /**
     * Recupera il valore della proprieta cittadini.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCittadiniResponse.Cittadini }
     *     
     */
    public RicercaCittadiniResponse.Cittadini getCittadini() {
        return cittadini;
    }

    /**
     * Imposta il valore della proprieta cittadini.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCittadiniResponse.Cittadini }
     *     
     */
    public void setCittadini(RicercaCittadiniResponse.Cittadini value) {
        this.cittadini = value;
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
     *         &lt;element name="cittadino" type="{http://deleghebe.csi.it/}cittadino" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "cittadino"
    })
    public static class Cittadini {

        @XmlElement(namespace = "")
        protected List<Cittadino> cittadino;

        /**
         * Gets the value of the cittadino property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cittadino property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCittadino().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Cittadino }
         * 
         * 
         */
        public List<Cittadino> getCittadino() {
            if (cittadino == null) {
                cittadino = new ArrayList<Cittadino>();
            }
            return this.cittadino;
        }

    }

}
