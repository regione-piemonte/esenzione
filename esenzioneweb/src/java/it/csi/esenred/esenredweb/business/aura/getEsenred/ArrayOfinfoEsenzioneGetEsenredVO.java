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
 * <p>Classe Java per ArrayOfinfoEsenzioneGetEsenredVO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfinfoEsenzioneGetEsenredVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infoEsenzioneGetEsenredVO" type="{http://ws.sanitxint.sanitx.csi.it/}infoEsenzioneGetEsenredVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfinfoEsenzioneGetEsenredVO", propOrder = {
    "infoEsenzioneGetEsenredVO"
})
public class ArrayOfinfoEsenzioneGetEsenredVO {

    @XmlElement(nillable = true)
    protected List<InfoEsenzioneGetEsenredVO> infoEsenzioneGetEsenredVO;

    /**
     * Gets the value of the infoEsenzioneGetEsenredVO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoEsenzioneGetEsenredVO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoEsenzioneGetEsenredVO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoEsenzioneGetEsenredVO }
     * 
     * 
     */
    public List<InfoEsenzioneGetEsenredVO> getInfoEsenzioneGetEsenredVO() {
        if (infoEsenzioneGetEsenredVO == null) {
            infoEsenzioneGetEsenredVO = new ArrayList<InfoEsenzioneGetEsenredVO>();
        }
        return this.infoEsenzioneGetEsenredVO;
    }

}
