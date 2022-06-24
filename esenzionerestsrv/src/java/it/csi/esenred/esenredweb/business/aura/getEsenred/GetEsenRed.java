/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.getEsenred;

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
 *         &lt;element name="idAURA" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idAURA",
    "tipoUser"
})
@XmlRootElement(name = "GetEsenRed")
public class GetEsenRed {

    protected Long idAURA;
    protected String tipoUser;

    /**
     * Recupera il valore della propriet idAURA.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAURA() {
        return idAURA;
    }

    /**
     * Imposta il valore della propriet idAURA.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAURA(Long value) {
        this.idAURA = value;
    }

    /**
     * Recupera il valore della propriet tipoUser.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoUser() {
        return tipoUser;
    }

    /**
     * Imposta il valore della propriet tipoUser.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoUser(String value) {
        this.tipoUser = value;
    }

}
