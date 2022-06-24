/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ruoloDeleghe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ruoloDeleghe">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}codifica">
 *       &lt;sequence>
 *         &lt;element name="aziendaSanitaria" type="{http://deleghebe.csi.it/}aziendaSanitaria" minOccurs="0"/>
 *         &lt;element name="profilo" type="{http://deleghebe.csi.it/}profilo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ruoloDeleghe", propOrder = {
    "aziendaSanitaria",
    "profilo"
})
public class RuoloDeleghe
    extends Codifica
{

    protected AziendaSanitaria aziendaSanitaria;
    protected Profilo profilo;

    /**
     * Gets the value of the aziendaSanitaria property.
     * 
     * @return
     *     possible object is
     *     {@link AziendaSanitaria }
     *     
     */
    public AziendaSanitaria getAziendaSanitaria() {
        return aziendaSanitaria;
    }

    /**
     * Sets the value of the aziendaSanitaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link AziendaSanitaria }
     *     
     */
    public void setAziendaSanitaria(AziendaSanitaria value) {
        this.aziendaSanitaria = value;
    }

    /**
     * Gets the value of the profilo property.
     * 
     * @return
     *     possible object is
     *     {@link Profilo }
     *     
     */
    public Profilo getProfilo() {
        return profilo;
    }

    /**
     * Sets the value of the profilo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Profilo }
     *     
     */
    public void setProfilo(Profilo value) {
        this.profilo = value;
    }

}
