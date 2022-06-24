/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dichiarazioneDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dichiarazioneDettaglio">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://deleghebe.csi.it/}dichiarazioneDettaglioStato" minOccurs="0"/>
 *         &lt;element name="cittadino1" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="ruoloCittadino1" type="{http://deleghebe.csi.it/}dichiarazioneRuolo" minOccurs="0"/>
 *         &lt;element name="cittadino2" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="ruoloCittadino2" type="{http://deleghebe.csi.it/}dichiarazioneRuolo" minOccurs="0"/>
 *         &lt;element name="notaRevocaBlocco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDelega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="documento" type="{http://deleghebe.csi.it/}documento" minOccurs="0"/>
 *         &lt;element name="motivazioneMenu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivazioneCasella" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idDettaglio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dichiarazioneDettaglio", propOrder = {
    "uuid",
    "dichiarazione",
    "stato",
    "cittadino1",
    "ruoloCittadino1",
    "cittadino2",
    "ruoloCittadino2",
    "notaRevocaBlocco",
    "tipoDelega",
    "deleghe",
    "documento",
    "motivazioneMenu",
    "motivazioneCasella",
    "idDettaglio"
})
public class DichiarazioneDettaglio
    extends Entita
{

    protected String uuid;
    protected Dichiarazione dichiarazione;
    protected DichiarazioneDettaglioStato stato;
    protected Cittadino cittadino1;
    protected DichiarazioneRuolo ruoloCittadino1;
    protected Cittadino cittadino2;
    protected DichiarazioneRuolo ruoloCittadino2;
    protected String notaRevocaBlocco;
    protected String tipoDelega;
    protected DichiarazioneDettaglio.Deleghe deleghe;
    protected Documento documento;
    protected String motivazioneMenu;
    protected String motivazioneCasella;
    protected Integer idDettaglio;

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
     * Recupera il valore della proprieta dichiarazione.
     * 
     * @return
     *     possible object is
     *     {@link Dichiarazione }
     *     
     */
    public Dichiarazione getDichiarazione() {
        return dichiarazione;
    }

    /**
     * Imposta il valore della proprieta dichiarazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Dichiarazione }
     *     
     */
    public void setDichiarazione(Dichiarazione value) {
        this.dichiarazione = value;
    }

    /**
     * Recupera il valore della proprieta stato.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneDettaglioStato }
     *     
     */
    public DichiarazioneDettaglioStato getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprieta stato.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneDettaglioStato }
     *     
     */
    public void setStato(DichiarazioneDettaglioStato value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprieta cittadino1.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino1() {
        return cittadino1;
    }

    /**
     * Imposta il valore della proprieta cittadino1.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino1(Cittadino value) {
        this.cittadino1 = value;
    }

    /**
     * Recupera il valore della proprieta ruoloCittadino1.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public DichiarazioneRuolo getRuoloCittadino1() {
        return ruoloCittadino1;
    }

    /**
     * Imposta il valore della proprieta ruoloCittadino1.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public void setRuoloCittadino1(DichiarazioneRuolo value) {
        this.ruoloCittadino1 = value;
    }

    /**
     * Recupera il valore della proprieta cittadino2.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino2() {
        return cittadino2;
    }

    /**
     * Imposta il valore della proprieta cittadino2.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino2(Cittadino value) {
        this.cittadino2 = value;
    }

    /**
     * Recupera il valore della proprieta ruoloCittadino2.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public DichiarazioneRuolo getRuoloCittadino2() {
        return ruoloCittadino2;
    }

    /**
     * Imposta il valore della proprieta ruoloCittadino2.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public void setRuoloCittadino2(DichiarazioneRuolo value) {
        this.ruoloCittadino2 = value;
    }

    /**
     * Recupera il valore della proprieta notaRevocaBlocco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotaRevocaBlocco() {
        return notaRevocaBlocco;
    }

    /**
     * Imposta il valore della proprieta notaRevocaBlocco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotaRevocaBlocco(String value) {
        this.notaRevocaBlocco = value;
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
     * Recupera il valore della proprieta deleghe.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneDettaglio.Deleghe }
     *     
     */
    public DichiarazioneDettaglio.Deleghe getDeleghe() {
        return deleghe;
    }

    /**
     * Imposta il valore della proprieta deleghe.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneDettaglio.Deleghe }
     *     
     */
    public void setDeleghe(DichiarazioneDettaglio.Deleghe value) {
        this.deleghe = value;
    }

    /**
     * Recupera il valore della proprieta documento.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprieta documento.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setDocumento(Documento value) {
        this.documento = value;
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
     * Recupera il valore della proprieta idDettaglio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdDettaglio() {
        return idDettaglio;
    }

    /**
     * Imposta il valore della proprieta idDettaglio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdDettaglio(Integer value) {
        this.idDettaglio = value;
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
