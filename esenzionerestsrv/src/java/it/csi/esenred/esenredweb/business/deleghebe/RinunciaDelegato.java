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
 * <p>Classe Java per rinunciaDelegato complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="rinunciaDelegato">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="cittadinoDelegato" type="{http://deleghebe.csi.it/}cittadino" form="unqualified"/>
 *         &lt;element name="cittadinoDelegante" type="{http://deleghebe.csi.it/}cittadino" form="unqualified"/>
 *         &lt;element name="uuidDelegaServizii" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="uuidDelegaServizio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" form="unqualified"/>
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
@XmlType(name = "rinunciaDelegato", propOrder = {
    "cittadinoDelegato",
    "cittadinoDelegante",
    "uuidDelegaServizii"
})
public class RinunciaDelegato
    extends ServiceRequest
{

    @XmlElement(namespace = "", required = true)
    protected Cittadino cittadinoDelegato;
    @XmlElement(namespace = "", required = true)
    protected Cittadino cittadinoDelegante;
    @XmlElement(namespace = "")
    protected RinunciaDelegato.UuidDelegaServizii uuidDelegaServizii;

    /**
     * Recupera il valore della proprieta cittadinoDelegato.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadinoDelegato() {
        return cittadinoDelegato;
    }

    /**
     * Imposta il valore della proprieta cittadinoDelegato.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadinoDelegato(Cittadino value) {
        this.cittadinoDelegato = value;
    }

    /**
     * Recupera il valore della proprieta cittadinoDelegante.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadinoDelegante() {
        return cittadinoDelegante;
    }

    /**
     * Imposta il valore della proprieta cittadinoDelegante.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadinoDelegante(Cittadino value) {
        this.cittadinoDelegante = value;
    }

    /**
     * Recupera il valore della proprieta uuidDelegaServizii.
     * 
     * @return
     *     possible object is
     *     {@link RinunciaDelegato.UuidDelegaServizii }
     *     
     */
    public RinunciaDelegato.UuidDelegaServizii getUuidDelegaServizii() {
        return uuidDelegaServizii;
    }

    /**
     * Imposta il valore della proprieta uuidDelegaServizii.
     * 
     * @param value
     *     allowed object is
     *     {@link RinunciaDelegato.UuidDelegaServizii }
     *     
     */
    public void setUuidDelegaServizii(RinunciaDelegato.UuidDelegaServizii value) {
        this.uuidDelegaServizii = value;
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
     *         &lt;element name="uuidDelegaServizio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" form="unqualified"/>
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
        "uuidDelegaServizio"
    })
    public static class UuidDelegaServizii {

        @XmlElement(namespace = "", required = true)
        protected List<String> uuidDelegaServizio;

        /**
         * Gets the value of the uuidDelegaServizio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the uuidDelegaServizio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUuidDelegaServizio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getUuidDelegaServizio() {
            if (uuidDelegaServizio == null) {
                uuidDelegaServizio = new ArrayList<String>();
            }
            return this.uuidDelegaServizio;
        }

    }

}
