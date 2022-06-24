/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for delegaServ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delegaServ">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="dataDecorrenza" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataScadenza" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRevoca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRinuncia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="delega" type="{http://deleghebe.csi.it/}delega" minOccurs="0"/>
 *         &lt;element name="servizio" type="{http://deleghebe.csi.it/}servizio" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://deleghebe.csi.it/}delegaServStato" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delegaServ", propOrder = {
    "dataDecorrenza",
    "dataScadenza",
    "dataRevoca",
    "dataRinuncia",
    "delega",
    "servizio",
    "stato",
    "uuid"
})
public class DelegaServ
    extends Entita
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDecorrenza;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataScadenza;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRevoca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRinuncia;
    protected Delega delega;
    protected Servizio servizio;
    protected DelegaServStato stato;
    protected String uuid;

    /**
     * Gets the value of the dataDecorrenza property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDecorrenza() {
        return dataDecorrenza;
    }

    /**
     * Sets the value of the dataDecorrenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDecorrenza(XMLGregorianCalendar value) {
        this.dataDecorrenza = value;
    }

    /**
     * Gets the value of the dataScadenza property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Sets the value of the dataScadenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
    }

    /**
     * Gets the value of the dataRevoca property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRevoca() {
        return dataRevoca;
    }

    /**
     * Sets the value of the dataRevoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRevoca(XMLGregorianCalendar value) {
        this.dataRevoca = value;
    }

    /**
     * Gets the value of the dataRinuncia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRinuncia() {
        return dataRinuncia;
    }

    /**
     * Sets the value of the dataRinuncia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRinuncia(XMLGregorianCalendar value) {
        this.dataRinuncia = value;
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
     * Gets the value of the servizio property.
     * 
     * @return
     *     possible object is
     *     {@link Servizio }
     *     
     */
    public Servizio getServizio() {
        return servizio;
    }

    /**
     * Sets the value of the servizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Servizio }
     *     
     */
    public void setServizio(Servizio value) {
        this.servizio = value;
    }

    /**
     * Gets the value of the stato property.
     * 
     * @return
     *     possible object is
     *     {@link DelegaServStato }
     *     
     */
    public DelegaServStato getStato() {
        return stato;
    }

    /**
     * Sets the value of the stato property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegaServStato }
     *     
     */
    public void setStato(DelegaServStato value) {
        this.stato = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
