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
 * <p>Classe Java per delega complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="delega">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="dataCreazioneDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="servizi" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="servizio" type="{http://deleghebe.csi.it/}delegaServ" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="bloccaDelega" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ciIdDelegato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="citIdDelegante" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="compilatoreCF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delegante" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="delegato" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="delegatoInput" type="{http://deleghebe.csi.it/}delegato" minOccurs="0"/>
 *         &lt;element name="dichiarazioneDettaglio" type="{http://deleghebe.csi.it/}dichiarazioneDettaglio" minOccurs="0"/>
 *         &lt;element name="motivazioneCasella" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivazioneMenu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presavisione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="presavisioneData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDelega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "delega", propOrder = {
    "dataCreazioneDelega",
    "servizi",
    "bloccaDelega",
    "ciIdDelegato",
    "citIdDelegante",
    "compilatoreCF",
    "delegante",
    "delegato",
    "delegatoInput",
    "dichiarazioneDettaglio",
    "motivazioneCasella",
    "motivazioneMenu",
    "presavisione",
    "presavisioneData",
    "statoDelega",
    "tipoDelega",
    "uuid",
    "nPratica"
})
public class Delega
    extends Entita
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCreazioneDelega;
    protected Delega.Servizi servizi;
    protected Boolean bloccaDelega;
    protected Integer ciIdDelegato;
    protected Integer citIdDelegante;
    protected String compilatoreCF;
    protected Cittadino delegante;
    protected Cittadino delegato;
    protected Delegato delegatoInput;
    protected DichiarazioneDettaglio dichiarazioneDettaglio;
    protected String motivazioneCasella;
    protected String motivazioneMenu;
    protected Boolean presavisione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar presavisioneData;
    protected String statoDelega;
    protected String tipoDelega;
    protected String uuid;
    protected Integer nPratica;

    /**
     * Recupera il valore della proprieta dataCreazioneDelega.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCreazioneDelega() {
        return dataCreazioneDelega;
    }

    /**
     * Imposta il valore della proprieta dataCreazioneDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCreazioneDelega(XMLGregorianCalendar value) {
        this.dataCreazioneDelega = value;
    }

    /**
     * Recupera il valore della proprieta servizi.
     * 
     * @return
     *     possible object is
     *     {@link Delega.Servizi }
     *     
     */
    public Delega.Servizi getServizi() {
        return servizi;
    }

    /**
     * Imposta il valore della proprieta servizi.
     * 
     * @param value
     *     allowed object is
     *     {@link Delega.Servizi }
     *     
     */
    public void setServizi(Delega.Servizi value) {
        this.servizi = value;
    }

    /**
     * Recupera il valore della proprieta bloccaDelega.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBloccaDelega() {
        return bloccaDelega;
    }

    /**
     * Imposta il valore della proprieta bloccaDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBloccaDelega(Boolean value) {
        this.bloccaDelega = value;
    }

    /**
     * Recupera il valore della proprieta ciIdDelegato.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCiIdDelegato() {
        return ciIdDelegato;
    }

    /**
     * Imposta il valore della proprieta ciIdDelegato.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCiIdDelegato(Integer value) {
        this.ciIdDelegato = value;
    }

    /**
     * Recupera il valore della proprieta citIdDelegante.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCitIdDelegante() {
        return citIdDelegante;
    }

    /**
     * Imposta il valore della proprieta citIdDelegante.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCitIdDelegante(Integer value) {
        this.citIdDelegante = value;
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
     * Recupera il valore della proprieta delegante.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getDelegante() {
        return delegante;
    }

    /**
     * Imposta il valore della proprieta delegante.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setDelegante(Cittadino value) {
        this.delegante = value;
    }

    /**
     * Recupera il valore della proprieta delegato.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getDelegato() {
        return delegato;
    }

    /**
     * Imposta il valore della proprieta delegato.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setDelegato(Cittadino value) {
        this.delegato = value;
    }

    /**
     * Recupera il valore della proprieta delegatoInput.
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
     * Imposta il valore della proprieta delegatoInput.
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
     * Recupera il valore della proprieta dichiarazioneDettaglio.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneDettaglio }
     *     
     */
    public DichiarazioneDettaglio getDichiarazioneDettaglio() {
        return dichiarazioneDettaglio;
    }

    /**
     * Imposta il valore della proprieta dichiarazioneDettaglio.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneDettaglio }
     *     
     */
    public void setDichiarazioneDettaglio(DichiarazioneDettaglio value) {
        this.dichiarazioneDettaglio = value;
    }

    /**
     * Recupera il valore della proprieta motivazioneCasella.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivazioneCasella() {
        return motivazioneCasella;
    }

    /**
     * Imposta il valore della proprieta motivazioneCasella.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivazioneCasella(String value) {
        this.motivazioneCasella = value;
    }

    /**
     * Recupera il valore della proprieta motivazioneMenu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivazioneMenu() {
        return motivazioneMenu;
    }

    /**
     * Imposta il valore della proprieta motivazioneMenu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivazioneMenu(String value) {
        this.motivazioneMenu = value;
    }

    /**
     * Recupera il valore della proprieta presavisione.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPresavisione() {
        return presavisione;
    }

    /**
     * Imposta il valore della proprieta presavisione.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPresavisione(Boolean value) {
        this.presavisione = value;
    }

    /**
     * Recupera il valore della proprieta presavisioneData.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPresavisioneData() {
        return presavisioneData;
    }

    /**
     * Imposta il valore della proprieta presavisioneData.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPresavisioneData(XMLGregorianCalendar value) {
        this.presavisioneData = value;
    }

    /**
     * Recupera il valore della proprieta statoDelega.
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
     * Imposta il valore della proprieta statoDelega.
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
     * Recupera il valore della proprieta tipoDelega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDelega() {
        return tipoDelega;
    }

    /**
     * Imposta il valore della proprieta tipoDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDelega(String value) {
        this.tipoDelega = value;
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
     *         &lt;element name="servizio" type="{http://deleghebe.csi.it/}delegaServ" maxOccurs="unbounded" minOccurs="0"/>
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
        "servizio"
    })
    public static class Servizi {

        protected List<DelegaServ> servizio;

        /**
         * Gets the value of the servizio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the servizio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServizio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DelegaServ }
         * 
         * 
         */
        public List<DelegaServ> getServizio() {
            if (servizio == null) {
                servizio = new ArrayList<DelegaServ>();
            }
            return this.servizio;
        }

    }

}
