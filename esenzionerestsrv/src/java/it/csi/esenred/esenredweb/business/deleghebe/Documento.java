/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per documento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="documento">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="autorita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataRilascio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataScadenzaDoc" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentoTipo" type="{http://deleghebe.csi.it/}documentoTipo" minOccurs="0"/>
 *         &lt;element name="file" type="{http://deleghebe.csi.it/}documentoFile" minOccurs="0"/>
 *         &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "autorita",
    "dataRilascio",
    "dataScadenzaDoc",
    "desc",
    "docDesc",
    "documentoTipo",
    "file",
    "fileId",
    "id",
    "numeroDocumento"
})
public class Documento
    extends Entita
{

    protected String autorita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRilascio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataScadenzaDoc;
    protected String desc;
    protected String docDesc;
    protected DocumentoTipo documentoTipo;
    protected DocumentoFile file;
    protected Integer fileId;
    protected Integer id;
    protected String numeroDocumento;

    /**
     * Recupera il valore della proprieta autorita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorita() {
        return autorita;
    }

    /**
     * Imposta il valore della proprieta autorita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorita(String value) {
        this.autorita = value;
    }

    /**
     * Recupera il valore della proprieta dataRilascio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRilascio() {
        return dataRilascio;
    }

    /**
     * Imposta il valore della proprieta dataRilascio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRilascio(XMLGregorianCalendar value) {
        this.dataRilascio = value;
    }

    /**
     * Recupera il valore della proprieta dataScadenzaDoc.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenzaDoc() {
        return dataScadenzaDoc;
    }

    /**
     * Imposta il valore della proprieta dataScadenzaDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenzaDoc(XMLGregorianCalendar value) {
        this.dataScadenzaDoc = value;
    }

    /**
     * Recupera il valore della proprieta desc.
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
     * Imposta il valore della proprieta desc.
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
     * Recupera il valore della proprieta docDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocDesc() {
        return docDesc;
    }

    /**
     * Imposta il valore della proprieta docDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocDesc(String value) {
        this.docDesc = value;
    }

    /**
     * Recupera il valore della proprieta documentoTipo.
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
     * Imposta il valore della proprieta documentoTipo.
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
     * Recupera il valore della proprieta file.
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
     * Imposta il valore della proprieta file.
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
     * Recupera il valore della proprieta fileId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * Imposta il valore della proprieta fileId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFileId(Integer value) {
        this.fileId = value;
    }

    /**
     * Recupera il valore della proprieta id.
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
     * Imposta il valore della proprieta id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprieta numeroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Imposta il valore della proprieta numeroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

}
