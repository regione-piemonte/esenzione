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
 * <p>Classe Java per getDelegatiFSEResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiFSEResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="delegatiFSE" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="delegatoFSE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
@XmlType(name = "getDelegatiFSEResponse", propOrder = {
    "delegatiFSE"
})
public class GetDelegatiFSEResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected GetDelegatiFSEResponse.DelegatiFSE delegatiFSE;

    /**
     * Recupera il valore della proprieta delegatiFSE.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegatiFSEResponse.DelegatiFSE }
     *     
     */
    public GetDelegatiFSEResponse.DelegatiFSE getDelegatiFSE() {
        return delegatiFSE;
    }

    /**
     * Imposta il valore della proprieta delegatiFSE.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegatiFSEResponse.DelegatiFSE }
     *     
     */
    public void setDelegatiFSE(GetDelegatiFSEResponse.DelegatiFSE value) {
        this.delegatiFSE = value;
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
     *         &lt;element name="delegatoFSE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "delegatoFSE"
    })
    public static class DelegatiFSE {

        @XmlElement(namespace = "")
        protected List<String> delegatoFSE;

        /**
         * Gets the value of the delegatoFSE property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the delegatoFSE property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDelegatoFSE().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getDelegatoFSE() {
            if (delegatoFSE == null) {
                delegatoFSE = new ArrayList<String>();
            }
            return this.delegatoFSE;
        }

    }

}
