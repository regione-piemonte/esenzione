/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.esenzionePatologia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="codiceFiscaleChiamante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleAssistito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceEsenzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAnnullamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaDiagnosi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "codiceFiscaleChiamante",
    "codiceFiscaleAssistito",
    "codiceEsenzione",
    "dataInizioValidita",
    "dataFineValidita",
    "dataAnnullamento",
    "listaDiagnosi"
})
@XmlRootElement(name = "esenzionePatologia")
public class EsenzionePatologia {

    protected String codiceFiscaleChiamante;
    protected String codiceFiscaleAssistito;
    protected String codiceEsenzione;
    protected String dataInizioValidita;
    protected String dataFineValidita;
    protected String dataAnnullamento;
    protected String listaDiagnosi;

    /**
     * Recupera il valore della proprietà codiceFiscaleChiamante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleChiamante() {
        return codiceFiscaleChiamante;
    }

    /**
     * Imposta il valore della proprietà codiceFiscaleChiamante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleChiamante(String value) {
        this.codiceFiscaleChiamante = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscaleAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleAssistito() {
        return codiceFiscaleAssistito;
    }

    /**
     * Imposta il valore della proprietà codiceFiscaleAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleAssistito(String value) {
        this.codiceFiscaleAssistito = value;
    }

    /**
     * Recupera il valore della proprietà codiceEsenzione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsenzione() {
        return codiceEsenzione;
    }

    /**
     * Imposta il valore della proprietà codiceEsenzione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsenzione(String value) {
        this.codiceEsenzione = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInizioValidita(String value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFineValidita(String value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataAnnullamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAnnullamento() {
        return dataAnnullamento;
    }

    /**
     * Imposta il valore della proprietà dataAnnullamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAnnullamento(String value) {
        this.dataAnnullamento = value;
    }

    /**
     * Recupera il valore della proprietà listaDiagnosi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListaDiagnosi() {
        return listaDiagnosi;
    }

    /**
     * Imposta il valore della proprietà listaDiagnosi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListaDiagnosi(String value) {
        this.listaDiagnosi = value;
    }

}
