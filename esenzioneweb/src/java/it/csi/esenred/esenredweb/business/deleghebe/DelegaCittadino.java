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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for delegaCittadino complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="delegaCittadino">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataDiNascita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAura" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="luogoNascita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="delegatoInput" type="{http://deleghebe.csi.it/}delegato" minOccurs="0"/>
 *         &lt;element name="deleghe" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="delega" type="{http://deleghebe.csi.it/}delegaServizio" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delegaCittadino", propOrder = {
    "uuid",
    "codiceFiscale",
    "cognome",
    "nome",
    "dataDiNascita",
    "sesso",
    "idAura",
    "luogoNascita",
    "stato",
    "delegatoInput",
    "deleghe"
})
public class DelegaCittadino {

    @XmlElement(name = "UUID")
    protected String uuid;
    @XmlElement(required = true)
    protected String codiceFiscale;
    protected String cognome;
    protected String nome;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDiNascita;
    protected String sesso;
    protected Long idAura;
    protected String luogoNascita;
    @XmlElement(required = true)
    protected String stato;
    protected Delegato delegatoInput;
    protected DelegaCittadino.Deleghe deleghe;

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
     * Gets the value of the codiceFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Gets the value of the cognome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the value of the cognome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the dataDiNascita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Sets the value of the dataDiNascita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDiNascita(XMLGregorianCalendar value) {
        this.dataDiNascita = value;
    }

    /**
     * Gets the value of the sesso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Sets the value of the sesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSesso(String value) {
        this.sesso = value;
    }

    /**
     * Gets the value of the idAura property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAura() {
        return idAura;
    }

    /**
     * Sets the value of the idAura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAura(Long value) {
        this.idAura = value;
    }

    /**
     * Gets the value of the luogoNascita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLuogoNascita() {
        return luogoNascita;
    }

    /**
     * Sets the value of the luogoNascita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLuogoNascita(String value) {
        this.luogoNascita = value;
    }

    /**
     * Gets the value of the stato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStato() {
        return stato;
    }

    /**
     * Sets the value of the stato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStato(String value) {
        this.stato = value;
    }

    /**
     * Gets the value of the delegatoInput property.
     * 
     * @return
     *     possible object is
     *     {@link Delegato }
     *     
     */
    public Delegato getDelegatoInput() {
        return delegatoInput;
    }

    /**
     * Sets the value of the delegatoInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link Delegato }
     *     
     */
    public void setDelegatoInput(Delegato value) {
        this.delegatoInput = value;
    }

    /**
     * Gets the value of the deleghe property.
     * 
     * @return
     *     possible object is
     *     {@link DelegaCittadino.Deleghe }
     *     
     */
    public DelegaCittadino.Deleghe getDeleghe() {
        return deleghe;
    }

    /**
     * Sets the value of the deleghe property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegaCittadino.Deleghe }
     *     
     */
    public void setDeleghe(DelegaCittadino.Deleghe value) {
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
     *         &lt;element name="delega" type="{http://deleghebe.csi.it/}delegaServizio" maxOccurs="unbounded" minOccurs="0"/>
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

        protected List<DelegaServizio> delega;

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
         * {@link DelegaServizio }
         * 
         * 
         */
        public List<DelegaServizio> getDelega() {
            if (delega == null) {
                delega = new ArrayList<DelegaServizio>();
            }
            return this.delega;
        }

    }

}
