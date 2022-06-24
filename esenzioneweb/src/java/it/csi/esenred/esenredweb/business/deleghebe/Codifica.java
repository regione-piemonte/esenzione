/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for codifica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="codifica">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "codifica", propOrder = {
    "codice",
    "descrizione"
})
@XmlSeeAlso({
    DocumentoTipo.class,
    UnitaOperativaSanitaria.class,
    Errore.class,
    ModoDichiarazione.class,
    DelegaServStato.class,
    StatoDiNascita.class,
    TipoDichiarazione.class,
    AziendaSanitaria.class,
    RuoloDeleghe.class,
    StrutturaSanitaria.class,
    TipoFileDocumento.class,
    DichiarazioneDettaglioStato.class,
    RuoloOperazione.class,
    DichiarazioneRuolo.class,
    Profilo.class,
    StatoDichiarazione.class,
    TipoEpisodio.class,
    Servizio.class,
    ComuneDiNascita.class
})
public class Codifica
    extends Entita
{

    protected String codice;
    protected String descrizione;

    /**
     * Gets the value of the codice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the value of the codice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

}
