/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFileResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFileResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="documentoFile" type="{http://deleghebe.csi.it/}documentoFile" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFileResponse", propOrder = {
    "documentoFile"
})
public class RicercaFileResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected DocumentoFile documentoFile;

    /**
     * Recupera il valore della proprieta documentoFile.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoFile }
     *     
     */
    public DocumentoFile getDocumentoFile() {
        return documentoFile;
    }

    /**
     * Imposta il valore della proprieta documentoFile.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoFile }
     *     
     */
    public void setDocumentoFile(DocumentoFile value) {
        this.documentoFile = value;
    }

}
