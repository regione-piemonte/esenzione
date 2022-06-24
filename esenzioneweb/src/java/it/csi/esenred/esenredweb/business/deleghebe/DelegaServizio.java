/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for delegaServizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delegaServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceServizio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataInizioDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataFineDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataRevoca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRinuncia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tipoDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gradoDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delegaServizio", propOrder = {
    "uuid",
    "codiceServizio",
    "dataInizioDelega",
    "dataFineDelega",
    "statoDelega",
    "dataRevoca",
    "dataRinuncia",
    "tipoDelega",
    "gradoDelega"
})
public class DelegaServizio {

    @XmlElement(name = "UUID")
    protected String uuid;
    @XmlElement(required = true)
    protected String codiceServizio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioDelega;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineDelega;
    @XmlElement(required = true)
    protected String statoDelega;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRevoca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRinuncia;
    protected String tipoDelega;
    protected String gradoDelega;

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
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
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the codiceServizio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceServizio() {
        return codiceServizio;
    }

    /**
     * Sets the value of the codiceServizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceServizio(String value) {
        this.codiceServizio = value;
    }

    /**
     * Gets the value of the dataInizioDelega property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioDelega() {
        return dataInizioDelega;
    }

    /**
     * Sets the value of the dataInizioDelega property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioDelega(XMLGregorianCalendar value) {
        this.dataInizioDelega = value;
    }

    /**
     * Gets the value of the dataFineDelega property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineDelega() {
        return dataFineDelega;
    }

    /**
     * Sets the value of the dataFineDelega property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineDelega(XMLGregorianCalendar value) {
        this.dataFineDelega = value;
    }

    /**
     * Gets the value of the statoDelega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoDelega() {
        return statoDelega;
    }

    /**
     * Sets the value of the statoDelega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoDelega(String value) {
        this.statoDelega = value;
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
    
    public String getTipoDelega() {
        return tipoDelega;
    }
    
    public void setTipoDelega(String value) {
        this.tipoDelega = value;
    }
    public String getGradoDelega() {
        return gradoDelega;
    }
    
    public void setGradoDelega(String value) {
        this.gradoDelega = value;
    }
}
