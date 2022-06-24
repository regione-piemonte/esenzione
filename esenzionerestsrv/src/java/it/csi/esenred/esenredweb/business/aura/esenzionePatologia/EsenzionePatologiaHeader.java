/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.esenzionePatologia;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per EsenzionePatologiaHeader complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsenzionePatologiaHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceRitorno" type="{http://www.w3.org/2001/XMLSchema}String" minOccurs="0"/>
 *         &lt;element name="listaMessaggi" type="{http://ws.sanitxint.sanitx.csi.it/}messagesGetEsenredVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsenzionePatologiaHeader", propOrder = {
    "codiceRitorno",
    "listaMessaggi"
})
public class EsenzionePatologiaHeader {

    protected String codiceRitorno;
    @XmlElement(nillable = true)
    protected List<MessagesGetEsenredVO> listaMessaggi;

    /**
     * Recupera il valore della proprieta codiceRitorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRitorno() {
        return codiceRitorno;
    }

    /**
     * Imposta il valore della proprieta codiceRitorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRitorno(String value) {
        this.codiceRitorno = value;
    }

    /**
     * Gets the value of the listaMessaggi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaMessaggi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaMessaggi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessagesGetEsenredVO }
     * 
     * 
     */
    public List<MessagesGetEsenredVO> getListaMessaggi() {
        if (listaMessaggi == null) {
            listaMessaggi = new ArrayList<MessagesGetEsenredVO>();
        }
        return this.listaMessaggi;
    }

}
