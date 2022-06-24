/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.esenzionePatologia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="esenzionePatologiaResult" type="{http://EsenzionePatologia.central.services.auraws.aura.csi.it}EsenzionePatologiaRes"/>
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
    "esenzionePatologiaResult"
})
@XmlRootElement(name = "esenzionePatologiaResponse")
public class EsenzionePatologiaResponse {

    @XmlElement(required = true)
    protected EsenzionePatologiaRes esenzionePatologiaResult;

    /**
     * Recupera il valore della proprieta esenzionePatologiaResult.
     * 
     * @return
     *     possible object is
     *     {@link EsenzionePatologiaRes }
     *     
     */
    public EsenzionePatologiaRes getEsenzionePatologiaResult() {
        return esenzionePatologiaResult;
    }

    /**
     * Imposta il valore della proprieta esenzionePatologiaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link EsenzionePatologiaRes }
     *     
     */
    public void setEsenzionePatologiaResult(EsenzionePatologiaRes value) {
        this.esenzionePatologiaResult = value;
    }

}
