/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata;

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
 *         &lt;element name="codiceEsenzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleAssistito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "codiceEsenzione",
    "dataInizioValidita",
    "codiceFiscaleAssistito"
})
@XmlRootElement(name = "ChiusuraEsenRed")
public class ChiusuraEsenRed {

    protected String codiceFiscaleChiamante;
    protected String codiceEsenzione;
    protected String dataInizioValidita;
    protected String codiceFiscaleAssistito;

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

}
