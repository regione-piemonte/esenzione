/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per dichiarazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dichiarazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="dataCreazioneDichiarazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dettagli" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dettaglio" type="{http://deleghebe.csi.it/}dichiarazioneDettaglio" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="autorita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cittadino" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="compilatoreCF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineTutela" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioTutela" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="modo" type="{http://deleghebe.csi.it/}modoDichiarazione" minOccurs="0"/>
 *         &lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://deleghebe.csi.it/}statoDichiarazione" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://deleghebe.csi.it/}tipoDichiarazione" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nPratica" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dichiarazione", propOrder = {
    "dataCreazioneDichiarazione",
    "dettagli",
    "autorita",
    "cittadino",
    "compilatoreCF",
    "dataFineTutela",
    "dataInizioTutela",
    "modo",
    "numeroDocumento",
    "stato",
    "tipo",
    "uuid",
    "nPratica"
})
public class Dichiarazione
    extends Entita
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCreazioneDichiarazione;
    protected Dichiarazione.Dettagli dettagli;
    protected String autorita;
    protected Cittadino cittadino;
    protected String compilatoreCF;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineTutela;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioTutela;
    protected ModoDichiarazione modo;
    protected String numeroDocumento;
    protected StatoDichiarazione stato;
    protected TipoDichiarazione tipo;
    protected String uuid;
    protected Integer nPratica;

    /**
     * Recupera il valore della proprieta dataCreazioneDichiarazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCreazioneDichiarazione() {
        return dataCreazioneDichiarazione;
    }

    /**
     * Imposta il valore della proprieta dataCreazioneDichiarazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCreazioneDichiarazione(XMLGregorianCalendar value) {
        this.dataCreazioneDichiarazione = value;
    }

    /**
     * Recupera il valore della proprieta dettagli.
     * 
     * @return
     *     possible object is
     *     {@link Dichiarazione.Dettagli }
     *     
     */
    public Dichiarazione.Dettagli getDettagli() {
        return dettagli;
    }

    /**
     * Imposta il valore della proprieta dettagli.
     * 
     * @param value
     *     allowed object is
     *     {@link Dichiarazione.Dettagli }
     *     
     */
    public void setDettagli(Dichiarazione.Dettagli value) {
        this.dettagli = value;
    }

    /**
     * Recupera il valore della proprieta autorita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorita() {
        return autorita;
    }

    /**
     * Imposta il valore della proprieta autorita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorita(String value) {
        this.autorita = value;
    }

    /**
     * Recupera il valore della proprieta cittadino.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino() {
        return cittadino;
    }

    /**
     * Imposta il valore della proprieta cittadino.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino(Cittadino value) {
        this.cittadino = value;
    }

    /**
     * Recupera il valore della proprieta compilatoreCF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompilatoreCF() {
        return compilatoreCF;
    }

    /**
     * Imposta il valore della proprieta compilatoreCF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompilatoreCF(String value) {
        this.compilatoreCF = value;
    }

    /**
     * Recupera il valore della proprieta dataFineTutela.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineTutela() {
        return dataFineTutela;
    }

    /**
     * Imposta il valore della proprieta dataFineTutela.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineTutela(XMLGregorianCalendar value) {
        this.dataFineTutela = value;
    }

    /**
     * Recupera il valore della proprieta dataInizioTutela.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioTutela() {
        return dataInizioTutela;
    }

    /**
     * Imposta il valore della proprieta dataInizioTutela.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioTutela(XMLGregorianCalendar value) {
        this.dataInizioTutela = value;
    }

    /**
     * Recupera il valore della proprieta modo.
     * 
     * @return
     *     possible object is
     *     {@link ModoDichiarazione }
     *     
     */
    public ModoDichiarazione getModo() {
        return modo;
    }

    /**
     * Imposta il valore della proprieta modo.
     * 
     * @param value
     *     allowed object is
     *     {@link ModoDichiarazione }
     *     
     */
    public void setModo(ModoDichiarazione value) {
        this.modo = value;
    }

    /**
     * Recupera il valore della proprieta numeroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Imposta il valore della proprieta numeroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Recupera il valore della proprieta stato.
     * 
     * @return
     *     possible object is
     *     {@link StatoDichiarazione }
     *     
     */
    public StatoDichiarazione getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprieta stato.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoDichiarazione }
     *     
     */
    public void setStato(StatoDichiarazione value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprieta tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoDichiarazione }
     *     
     */
    public TipoDichiarazione getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprieta tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDichiarazione }
     *     
     */
    public void setTipo(TipoDichiarazione value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprieta uuid.
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
     * Imposta il valore della proprieta uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    /**
     * Recupera il valore della proprieta nPratica.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNPratica() {
        return nPratica;
    }

    /**
     * Imposta il valore della proprieta nPratica.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNPratica(Integer value) {
        this.nPratica = value;
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
     *         &lt;element name="dettaglio" type="{http://deleghebe.csi.it/}dichiarazioneDettaglio" maxOccurs="unbounded" minOccurs="0"/>
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
        "dettaglio"
    })
    public static class Dettagli {

        protected List<DichiarazioneDettaglio> dettaglio;

        /**
         * Gets the value of the dettaglio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dettaglio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDettaglio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DichiarazioneDettaglio }
         * 
         * 
         */
        public List<DichiarazioneDettaglio> getDettaglio() {
            if (dettaglio == null) {
                dettaglio = new ArrayList<DichiarazioneDettaglio>();
            }
            return this.dettaglio;
        }

    }

}
