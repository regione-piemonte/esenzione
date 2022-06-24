/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.chiusuraesenzionecertificata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ChiusuraEsenRedBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ChiusuraEsenRedBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataOperazioneRevoca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineValiditaOld" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChiusuraEsenRedBody", propOrder = {
    "dataOperazioneRevoca",
    "dataFineValiditaOld"
})
public class ChiusuraEsenRedBody {

    protected String dataOperazioneRevoca;
    protected String dataFineValiditaOld;

    /**
     * Recupera il valore della proprieta dataOperazioneRevoca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOperazioneRevoca() {
        return dataOperazioneRevoca;
    }

    /**
     * Imposta il valore della proprieta dataOperazioneRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOperazioneRevoca(String value) {
        this.dataOperazioneRevoca = value;
    }

    /**
     * Recupera il valore della proprieta dataFineValiditaOld.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFineValiditaOld() {
        return dataFineValiditaOld;
    }

    /**
     * Imposta il valore della proprieta dataFineValiditaOld.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFineValiditaOld(String value) {
        this.dataFineValiditaOld = value;
    }

}
