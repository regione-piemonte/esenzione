/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaCittadinoService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCittadinoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaCittadino" type="{http://deleghebe.csi.it/}aggiornaCittadino" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCittadinoService", propOrder = {
    "aggiornaCittadino"
})
public class AggiornaCittadinoService {

    protected AggiornaCittadino aggiornaCittadino;

    /**
     * Recupera il valore della proprieta aggiornaCittadino.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCittadino }
     *     
     */
    public AggiornaCittadino getAggiornaCittadino() {
        return aggiornaCittadino;
    }

    /**
     * Imposta il valore della proprieta aggiornaCittadino.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCittadino }
     *     
     */
    public void setAggiornaCittadino(AggiornaCittadino value) {
        this.aggiornaCittadino = value;
    }

}
