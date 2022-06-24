/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.iterrogamefesenredd;

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
 *         &lt;element name="InterrogaMefEsenreddResult" type="{http://InterrogaMefEsenredd.central.services.auraws.aura.csi.it}InterrogaMefEsenreddRes"/>
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
    "interrogaMefEsenreddResult"
})
@XmlRootElement(name = "InterrogaMefEsenreddResponse")
public class InterrogaMefEsenreddResponse {

    @XmlElement(name = "InterrogaMefEsenreddResult", required = true)
    protected InterrogaMefEsenreddRes interrogaMefEsenreddResult;

    /**
     * Recupera il valore della proprieta interrogaMefEsenreddResult.
     * 
     * @return
     *     possible object is
     *     {@link InterrogaMefEsenreddRes }
     *     
     */
    public InterrogaMefEsenreddRes getInterrogaMefEsenreddResult() {
        return interrogaMefEsenreddResult;
    }

    /**
     * Imposta il valore della proprieta interrogaMefEsenreddResult.
     * 
     * @param value
     *     allowed object is
     *     {@link InterrogaMefEsenreddRes }
     *     
     */
    public void setInterrogaMefEsenreddResult(InterrogaMefEsenreddRes value) {
        this.interrogaMefEsenreddResult = value;
    }

}
