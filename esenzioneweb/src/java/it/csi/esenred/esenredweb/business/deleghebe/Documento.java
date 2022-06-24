/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for documento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documento">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentoTipo" type="{http://deleghebe.csi.it/}documentoTipo" minOccurs="0"/>
 *         &lt;element name="file" type="{http://deleghebe.csi.it/}documentoFile" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documento", propOrder = {
    "desc",
    "documentoTipo",
    "file",
    "id"
})
public class Documento
    extends Entita
{

    protected String desc;
    protected DocumentoTipo documentoTipo;
    protected DocumentoFile file;
    protected Integer id;

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the documentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoTipo }
     *     
     */
    public DocumentoTipo getDocumentoTipo() {
        return documentoTipo;
    }

    /**
     * Sets the value of the documentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoTipo }
     *     
     */
    public void setDocumentoTipo(DocumentoTipo value) {
        this.documentoTipo = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoFile }
     *     
     */
    public DocumentoFile getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoFile }
     *     
     */
    public void setFile(DocumentoFile value) {
        this.file = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

}
