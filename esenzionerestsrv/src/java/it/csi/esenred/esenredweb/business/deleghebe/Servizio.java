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
 * <p>Classe Java per servizio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="servizio">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}codifica">
 *       &lt;sequence>
 *         &lt;element name="serId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="descrizioneEstesa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arruolabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="delegabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="notificabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="obbligatorioArruolamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="minore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validitaServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroGiorniDelegabili" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="codSerPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fraseDebole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fraseForte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "serId",
    "descrizioneEstesa",
    "arruolabile",
    "delegabile",
    "notificabile",
    "obbligatorioArruolamento",
    "minore",
    "url",
    "validitaServizio",
    "numeroGiorniDelegabili",
    "dataInizioValidita",
    "dataFineValidita",
    "codSerPadre",
    "fraseDebole",
    "fraseForte"
})
public class Servizio
    extends Codifica
{

    protected Integer serId;
    protected String descrizioneEstesa;
    protected Boolean arruolabile;
    protected Boolean delegabile;
    protected Boolean notificabile;
    protected Boolean obbligatorioArruolamento;
    protected Boolean minore;
    protected String url;
    protected String validitaServizio;
    protected Integer numeroGiorniDelegabili;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;
    protected String codSerPadre;
    protected String fraseDebole;
    protected String fraseForte;

    /**
     * Recupera il valore della proprieta serId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSerId() {
        return serId;
    }

    /**
     * Imposta il valore della proprieta serId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSerId(Integer value) {
        this.serId = value;
    }

    /**
     * Recupera il valore della proprieta descrizioneEstesa.
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
     * Imposta il valore della proprieta descrizioneEstesa.
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
     * Recupera il valore della proprieta arruolabile.
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
     * Imposta il valore della proprieta arruolabile.
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
     * Recupera il valore della proprieta delegabile.
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
     * Imposta il valore della proprieta delegabile.
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
     * Recupera il valore della proprieta notificabile.
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
     * Imposta il valore della proprieta notificabile.
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
     * Recupera il valore della proprieta obbligatorioArruolamento.
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
     * Imposta il valore della proprieta obbligatorioArruolamento.
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
     * Recupera il valore della proprieta minore.
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
     * Imposta il valore della proprieta minore.
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
     * Recupera il valore della proprieta url.
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
     * Imposta il valore della proprieta url.
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
     * Recupera il valore della proprieta validitaServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValiditaServizio() {
        return validitaServizio;
    }

    /**
     * Imposta il valore della proprieta validitaServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValiditaServizio(String value) {
        this.validitaServizio = value;
    }

    /**
     * Recupera il valore della proprieta numeroGiorniDelegabili.
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
     * Imposta il valore della proprieta numeroGiorniDelegabili.
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
     * Recupera il valore della proprieta dataInizioValidita.
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
     * Imposta il valore della proprieta dataInizioValidita.
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
     * Recupera il valore della proprieta dataFineValidita.
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
     * Imposta il valore della proprieta dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprieta codSerPadre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSerPadre() {
        return codSerPadre;
    }

    /**
     * Imposta il valore della proprieta codSerPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSerPadre(String value) {
        this.codSerPadre = value;
    }

    /**
     * Recupera il valore della proprieta fraseDebole.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFraseDebole() {
        return fraseDebole;
    }

    /**
     * Imposta il valore della proprieta fraseDebole.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFraseDebole(String value) {
        this.fraseDebole = value;
    }

    /**
     * Recupera il valore della proprieta fraseForte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFraseForte() {
        return fraseForte;
    }

    /**
     * Imposta il valore della proprieta fraseForte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFraseForte(String value) {
        this.fraseForte = value;
    }

}
