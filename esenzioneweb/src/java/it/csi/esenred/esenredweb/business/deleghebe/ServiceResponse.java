/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errori" type="{http://deleghebe.csi.it/}errore" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceResponse", propOrder = {
    "errori",
    "esito"
})
@XmlSeeAlso({
    SaveDelegatiResponse.class,
    RinunciaDelegatoResponse.class,
    IsAliveResponse.class,
    GetDelegatiResponse.class,
    GetDelegantiResponse.class,
    RicercaDelegheResponse.class,
    InserisciCittadinoResponse.class,
    AggiornaDichiarazioneResponse.class,
    RicercaDichiarazioniResponse.class,
    ElencoGenitoriResponse.class,
    AggiornaDelegaResponse.class,
    RicercaCittadiniResponse.class,
    InserisciDichiarazioneResponse.class,
    InserisciDelegaResponse.class,
    AggiornaCittadinoResponse.class,
    RicercaCfOperatoreResponse.class
})
public class ServiceResponse {

    @XmlElement(namespace = "", nillable = true)
    protected List<Errore> errori;
    @XmlElement(namespace = "")
    protected String esito;

    /**
     * Gets the value of the errori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Errore }
     * 
     * 
     */
    public List<Errore> getErrori() {
        if (errori == null) {
            errori = new ArrayList<Errore>();
        }
        return this.errori;
    }

    /**
     * Gets the value of the esito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsito() {
        return esito;
    }

    /**
     * Sets the value of the esito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsito(String value) {
        this.esito = value;
    }

}
