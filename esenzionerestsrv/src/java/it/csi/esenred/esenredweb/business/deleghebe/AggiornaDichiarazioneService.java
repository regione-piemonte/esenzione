/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaDichiarazioneService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaDichiarazioneService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaDichiarazione" type="{http://deleghebe.csi.it/}aggiornaDichiarazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaDichiarazioneService", propOrder = {
    "aggiornaDichiarazione"
})
public class AggiornaDichiarazioneService {

    protected AggiornaDichiarazione aggiornaDichiarazione;

    /**
     * Recupera il valore della proprieta aggiornaDichiarazione.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaDichiarazione }
     *     
     */
    public AggiornaDichiarazione getAggiornaDichiarazione() {
        return aggiornaDichiarazione;
    }

    /**
     * Imposta il valore della proprieta aggiornaDichiarazione.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaDichiarazione }
     *     
     */
    public void setAggiornaDichiarazione(AggiornaDichiarazione value) {
        this.aggiornaDichiarazione = value;
    }

}
