/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.getEsenred;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per GetEsenRedHeader complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetEsenRedHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceRitorno" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="requestDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEsenRedHeader", propOrder = {
    "codiceRitorno",
    "requestDateTime"
})
public class GetEsenRedHeader {

    protected Long codiceRitorno;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDateTime;

    /**
     * Recupera il valore della propriet codiceRitorno.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodiceRitorno() {
        return codiceRitorno;
    }

    /**
     * Imposta il valore della propriet codiceRitorno.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodiceRitorno(Long value) {
        this.codiceRitorno = value;
    }

    /**
     * Recupera il valore della propriet requestDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Imposta il valore della propriet requestDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDateTime(XMLGregorianCalendar value) {
        this.requestDateTime = value;
    }

}
