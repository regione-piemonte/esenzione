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
 * <p>Java class for servizio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="servizio">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}codifica">
 *       &lt;sequence>
 *         &lt;element name="descrizioneEstesa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arruolabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="delegabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="notificabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="obbligatorioArruolamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="minore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroGiorniDelegabili" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servizio", propOrder = {
    "descrizioneEstesa",
    "arruolabile",
    "delegabile",
    "notificabile",
    "obbligatorioArruolamento",
    "minore",
    "url",
    "numeroGiorniDelegabili",
    "dataInizioValidita",
    "dataFineValidita"
})
public class Servizio
    extends Codifica
{

    protected String descrizioneEstesa;
    protected Boolean arruolabile;
    protected Boolean delegabile;
    protected Boolean notificabile;
    protected Boolean obbligatorioArruolamento;
    protected Boolean minore;
    protected String url;
    protected Integer numeroGiorniDelegabili;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;

    /**
     * Gets the value of the descrizioneEstesa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneEstesa() {
        return descrizioneEstesa;
    }

    /**
     * Sets the value of the descrizioneEstesa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneEstesa(String value) {
        this.descrizioneEstesa = value;
    }

    /**
     * Gets the value of the arruolabile property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isArruolabile() {
        return arruolabile;
    }

    /**
     * Sets the value of the arruolabile property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setArruolabile(Boolean value) {
        this.arruolabile = value;
    }

    /**
     * Gets the value of the delegabile property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDelegabile() {
        return delegabile;
    }

    /**
     * Sets the value of the delegabile property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDelegabile(Boolean value) {
        this.delegabile = value;
    }

    /**
     * Gets the value of the notificabile property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNotificabile() {
        return notificabile;
    }

    /**
     * Sets the value of the notificabile property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNotificabile(Boolean value) {
        this.notificabile = value;
    }

    /**
     * Gets the value of the obbligatorioArruolamento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isObbligatorioArruolamento() {
        return obbligatorioArruolamento;
    }

    /**
     * Sets the value of the obbligatorioArruolamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setObbligatorioArruolamento(Boolean value) {
        this.obbligatorioArruolamento = value;
    }

    /**
     * Gets the value of the minore property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMinore() {
        return minore;
    }

    /**
     * Sets the value of the minore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMinore(Boolean value) {
        this.minore = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the numeroGiorniDelegabili property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroGiorniDelegabili() {
        return numeroGiorniDelegabili;
    }

    /**
     * Sets the value of the numeroGiorniDelegabili property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroGiorniDelegabili(Integer value) {
        this.numeroGiorniDelegabili = value;
    }

    /**
     * Gets the value of the dataInizioValidita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Sets the value of the dataInizioValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Gets the value of the dataFineValidita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Sets the value of the dataFineValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

}
