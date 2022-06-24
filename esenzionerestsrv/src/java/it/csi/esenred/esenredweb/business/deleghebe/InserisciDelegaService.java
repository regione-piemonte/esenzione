/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciDelegaService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciDelegaService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inserisciDelega" type="{http://deleghebe.csi.it/}inserisciDelega" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciDelegaService", propOrder = {
    "inserisciDelega"
})
public class InserisciDelegaService {

    protected InserisciDelega inserisciDelega;

    /**
     * Recupera il valore della proprieta inserisciDelega.
     * 
     * @return
     *     possible object is
     *     {@link InserisciDelega }
     *     
     */
    public InserisciDelega getInserisciDelega() {
        return inserisciDelega;
    }

    /**
     * Imposta il valore della proprieta inserisciDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciDelega }
     *     
     */
    public void setInserisciDelega(InserisciDelega value) {
        this.inserisciDelega = value;
    }

}
