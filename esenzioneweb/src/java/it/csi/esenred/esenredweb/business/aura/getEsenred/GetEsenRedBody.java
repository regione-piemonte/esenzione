/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.getEsenred;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetEsenRedBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetEsenRedBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAura" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="infoAnag" type="{http://ws.sanitxint.sanitx.csi.it/}infoAnagraficheGetEsenredVO" minOccurs="0"/>
 *         &lt;element name="infoAnagResidenza" type="{http://ws.sanitxint.sanitx.csi.it/}infoAnagraficheResidenzaGetEsenredVO" minOccurs="0"/>
 *         &lt;element name="infoAnagDomicilio" type="{http://ws.sanitxint.sanitx.csi.it/}infoAnagraficheDomicilioGetEsenredVO" minOccurs="0"/>
 *         &lt;element name="infoEsenzione" type="{http://ws.sanitxint.sanitx.csi.it/}infoEsenzioneGetEsenredVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEsenRedBody", propOrder = {
    "idAura",
    "infoAnag",
    "infoAnagResidenza",
    "infoAnagDomicilio",
    "infoEsenzione"
})
public class GetEsenRedBody {

    protected Long idAura;
    protected InfoAnagraficheGetEsenredVO infoAnag;
    protected InfoAnagraficheResidenzaGetEsenredVO infoAnagResidenza;
    protected InfoAnagraficheDomicilioGetEsenredVO infoAnagDomicilio;
    @XmlElement(nillable = true)
    protected List<InfoEsenzioneGetEsenredVO> infoEsenzione;

    /**
     * Recupera il valore della propriet idAura.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAura() {
        return idAura;
    }

    /**
     * Imposta il valore della propriet idAura.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAura(Long value) {
        this.idAura = value;
    }

    /**
     * Recupera il valore della propriet infoAnag.
     * 
     * @return
     *     possible object is
     *     {@link InfoAnagraficheGetEsenredVO }
     *     
     */
    public InfoAnagraficheGetEsenredVO getInfoAnag() {
        return infoAnag;
    }

    /**
     * Imposta il valore della propriet infoAnag.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoAnagraficheGetEsenredVO }
     *     
     */
    public void setInfoAnag(InfoAnagraficheGetEsenredVO value) {
        this.infoAnag = value;
    }

    /**
     * Recupera il valore della propriet infoAnagResidenza.
     * 
     * @return
     *     possible object is
     *     {@link InfoAnagraficheResidenzaGetEsenredVO }
     *     
     */
    public InfoAnagraficheResidenzaGetEsenredVO getInfoAnagResidenza() {
        return infoAnagResidenza;
    }

    /**
     * Imposta il valore della propriet infoAnagResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoAnagraficheResidenzaGetEsenredVO }
     *     
     */
    public void setInfoAnagResidenza(InfoAnagraficheResidenzaGetEsenredVO value) {
        this.infoAnagResidenza = value;
    }

    /**
     * Recupera il valore della propriet infoAnagDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link InfoAnagraficheDomicilioGetEsenredVO }
     *     
     */
    public InfoAnagraficheDomicilioGetEsenredVO getInfoAnagDomicilio() {
        return infoAnagDomicilio;
    }

    /**
     * Imposta il valore della propriet infoAnagDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoAnagraficheDomicilioGetEsenredVO }
     *     
     */
    public void setInfoAnagDomicilio(InfoAnagraficheDomicilioGetEsenredVO value) {
        this.infoAnagDomicilio = value;
    }

    /**
     * Gets the value of the infoEsenzione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoEsenzione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoEsenzione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoEsenzioneGetEsenredVO }
     * 
     * 
     */
    public List<InfoEsenzioneGetEsenredVO> getInfoEsenzione() {
        if (infoEsenzione == null) {
            infoEsenzione = new ArrayList<InfoEsenzioneGetEsenredVO>();
        }
        return this.infoEsenzione;
    }

}
