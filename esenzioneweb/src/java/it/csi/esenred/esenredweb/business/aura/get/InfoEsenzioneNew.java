/**
 * InfoEsenzioneNew.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.get;


/**
 *  InfoEsenzioneNew bean class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class InfoEsenzioneNew implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
       name = InfoEsenzioneNew
       Namespace URI = http://AnagrafeSanitaria.central.services.auraws.aura.csi.it
       Namespace Prefix = ns1
     */

    /**
     * field for CodEsenzione
     */
    protected java.lang.String localCodEsenzione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodEsenzioneTracker = false;

    /**
     * field for DescEsenzione
     */
    protected it.csi.esenred.esenredweb.business.aura.get.DescEsenzione_type1 localDescEsenzione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescEsenzioneTracker = false;

    /**
     * field for CodDiagnosi
     */
    protected it.csi.esenred.esenredweb.business.aura.get.CodDiagnosi_type1 localCodDiagnosi;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodDiagnosiTracker = false;

    /**
     * field for Diagnosi
     */
    protected java.lang.String localDiagnosi;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDiagnosiTracker = false;

    /**
     * field for FineValEsenzione
     */
    protected java.lang.String localFineValEsenzione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFineValEsenzioneTracker = false;

    /**
     * field for IdEsenzione
     */
    protected java.math.BigDecimal localIdEsenzione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdEsenzioneTracker = false;

    /**
     * field for InizioValEsenzione
     */
    protected java.lang.String localInizioValEsenzione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localInizioValEsenzioneTracker = false;

    /**
     * field for DataEmissione
     */
    protected java.util.Calendar localDataEmissione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataEmissioneTracker = false;

    /**
     * field for DataScadenza
     */
    protected java.util.Calendar localDataScadenza;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataScadenzaTracker = false;

    /**
     * field for DataSospensione
     */
    protected java.util.Calendar localDataSospensione;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataSospensioneTracker = false;

    /**
     * field for CodAttestato
     */
    protected java.lang.String localCodAttestato;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodAttestatoTracker = false;

    /**
     * field for Fonte
     */
    protected it.csi.esenred.esenredweb.business.aura.get.Fonte_type1 localFonte;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFonteTracker = false;

    public boolean isCodEsenzioneSpecified() {
        return localCodEsenzioneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodEsenzione() {
        return localCodEsenzione;
    }

    /**
     * Auto generated setter method
     * @param param CodEsenzione
     */
    public void setCodEsenzione(java.lang.String param) {
        localCodEsenzioneTracker = param != null;

        this.localCodEsenzione = param;
    }

    public boolean isDescEsenzioneSpecified() {
        return localDescEsenzioneTracker;
    }

    /**
     * Auto generated getter method
     * @return it.csi.aura.auraws.services.central.anagrafesanitaria.DescEsenzione_type1
     */
    public it.csi.esenred.esenredweb.business.aura.get.DescEsenzione_type1 getDescEsenzione() {
        return localDescEsenzione;
    }

    /**
     * Auto generated setter method
     * @param param DescEsenzione
     */
    public void setDescEsenzione(
        it.csi.esenred.esenredweb.business.aura.get.DescEsenzione_type1 param) {
        localDescEsenzioneTracker = param != null;

        this.localDescEsenzione = param;
    }

    public boolean isCodDiagnosiSpecified() {
        return localCodDiagnosiTracker;
    }

    /**
     * Auto generated getter method
     * @return it.csi.aura.auraws.services.central.anagrafesanitaria.CodDiagnosi_type1
     */
    public it.csi.esenred.esenredweb.business.aura.get.CodDiagnosi_type1 getCodDiagnosi() {
        return localCodDiagnosi;
    }

    /**
     * Auto generated setter method
     * @param param CodDiagnosi
     */
    public void setCodDiagnosi(
        it.csi.esenred.esenredweb.business.aura.get.CodDiagnosi_type1 param) {
        localCodDiagnosiTracker = param != null;

        this.localCodDiagnosi = param;
    }

    public boolean isDiagnosiSpecified() {
        return localDiagnosiTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDiagnosi() {
        return localDiagnosi;
    }

    /**
     * Auto generated setter method
     * @param param Diagnosi
     */
    public void setDiagnosi(java.lang.String param) {
        localDiagnosiTracker = param != null;

        this.localDiagnosi = param;
    }

    public boolean isFineValEsenzioneSpecified() {
        return localFineValEsenzioneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getFineValEsenzione() {
        return localFineValEsenzione;
    }

    /**
     * Auto generated setter method
     * @param param FineValEsenzione
     */
    public void setFineValEsenzione(java.lang.String param) {
        localFineValEsenzioneTracker = param != null;

        this.localFineValEsenzione = param;
    }

    public boolean isIdEsenzioneSpecified() {
        return localIdEsenzioneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getIdEsenzione() {
        return localIdEsenzione;
    }

    /**
     * Auto generated setter method
     * @param param IdEsenzione
     */
    public void setIdEsenzione(java.math.BigDecimal param) {
        localIdEsenzioneTracker = param != null;

        this.localIdEsenzione = param;
    }

    public boolean isInizioValEsenzioneSpecified() {
        return localInizioValEsenzioneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getInizioValEsenzione() {
        return localInizioValEsenzione;
    }

    /**
     * Auto generated setter method
     * @param param InizioValEsenzione
     */
    public void setInizioValEsenzione(java.lang.String param) {
        localInizioValEsenzioneTracker = param != null;

        this.localInizioValEsenzione = param;
    }

    public boolean isDataEmissioneSpecified() {
        return localDataEmissioneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataEmissione() {
        return localDataEmissione;
    }

    /**
     * Auto generated setter method
     * @param param DataEmissione
     */
    public void setDataEmissione(java.util.Calendar param) {
        localDataEmissioneTracker = param != null;

        this.localDataEmissione = param;
    }

    public boolean isDataScadenzaSpecified() {
        return localDataScadenzaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataScadenza() {
        return localDataScadenza;
    }

    /**
     * Auto generated setter method
     * @param param DataScadenza
     */
    public void setDataScadenza(java.util.Calendar param) {
        localDataScadenzaTracker = param != null;

        this.localDataScadenza = param;
    }

    public boolean isDataSospensioneSpecified() {
        return localDataSospensioneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataSospensione() {
        return localDataSospensione;
    }

    /**
     * Auto generated setter method
     * @param param DataSospensione
     */
    public void setDataSospensione(java.util.Calendar param) {
        localDataSospensioneTracker = param != null;

        this.localDataSospensione = param;
    }

    public boolean isCodAttestatoSpecified() {
        return localCodAttestatoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodAttestato() {
        return localCodAttestato;
    }

    /**
     * Auto generated setter method
     * @param param CodAttestato
     */
    public void setCodAttestato(java.lang.String param) {
        localCodAttestatoTracker = param != null;

        this.localCodAttestato = param;
    }

    public boolean isFonteSpecified() {
        return localFonteTracker;
    }

    /**
     * Auto generated getter method
     * @return it.csi.aura.auraws.services.central.anagrafesanitaria.Fonte_type1
     */
    public it.csi.esenred.esenredweb.business.aura.get.Fonte_type1 getFonte() {
        return localFonte;
    }

    /**
     * Auto generated setter method
     * @param param Fonte
     */
    public void setFonte(
        it.csi.esenred.esenredweb.business.aura.get.Fonte_type1 param) {
        localFonteTracker = param != null;

        this.localFonte = param;
    }

    /**
     *
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName,
        final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {
        return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(
                this, parentQName));
    }

    public void serialize(final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException,
            org.apache.axis2.databinding.ADBException {
        serialize(parentQName, xmlWriter, false);
    }

    public void serialize(final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter, boolean serializeType)
        throws javax.xml.stream.XMLStreamException,
            org.apache.axis2.databinding.ADBException {
        java.lang.String prefix = null;
        java.lang.String namespace = null;

        prefix = parentQName.getPrefix();
        namespace = parentQName.getNamespaceURI();
        writeStartElement(prefix, namespace, parentQName.getLocalPart(),
            xmlWriter);

        if (serializeType) {
            java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                    "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it");

            if ((namespacePrefix != null) &&
                    (namespacePrefix.trim().length() > 0)) {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    namespacePrefix + ":InfoEsenzioneNew", xmlWriter);
            } else {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    "InfoEsenzioneNew", xmlWriter);
            }
        }

        if (localCodEsenzioneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codEsenzione", xmlWriter);

            if (localCodEsenzione == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codEsenzione cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodEsenzione);
            }

            xmlWriter.writeEndElement();
        }

        if (localDescEsenzioneTracker) {
            if (localDescEsenzione == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "descEsenzione cannot be null!!");
            }

            localDescEsenzione.serialize(new javax.xml.namespace.QName(
                    "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                    "descEsenzione"), xmlWriter);
        }

        if (localCodDiagnosiTracker) {
            if (localCodDiagnosi == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "codDiagnosi cannot be null!!");
            }

            localCodDiagnosi.serialize(new javax.xml.namespace.QName(
                    "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                    "codDiagnosi"), xmlWriter);
        }

        if (localDiagnosiTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "diagnosi", xmlWriter);

            if (localDiagnosi == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "diagnosi cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDiagnosi);
            }

            xmlWriter.writeEndElement();
        }

        if (localFineValEsenzioneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "fineValEsenzione", xmlWriter);

            if (localFineValEsenzione == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "fineValEsenzione cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFineValEsenzione);
            }

            xmlWriter.writeEndElement();
        }

        if (localIdEsenzioneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "idEsenzione", xmlWriter);

            if (localIdEsenzione == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "idEsenzione cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localIdEsenzione));
            }

            xmlWriter.writeEndElement();
        }

        if (localInizioValEsenzioneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "inizioValEsenzione", xmlWriter);

            if (localInizioValEsenzione == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "inizioValEsenzione cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localInizioValEsenzione);
            }

            xmlWriter.writeEndElement();
        }

        if (localDataEmissioneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataEmissione", xmlWriter);

            if (localDataEmissione == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataEmissione cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataEmissione));
            }

            xmlWriter.writeEndElement();
        }

        if (localDataScadenzaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataScadenza", xmlWriter);

            if (localDataScadenza == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataScadenza cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataScadenza));
            }

            xmlWriter.writeEndElement();
        }

        if (localDataSospensioneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataSospensione", xmlWriter);

            if (localDataSospensione == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataSospensione cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataSospensione));
            }

            xmlWriter.writeEndElement();
        }

        if (localCodAttestatoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codAttestato", xmlWriter);

            if (localCodAttestato == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codAttestato cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodAttestato);
            }

            xmlWriter.writeEndElement();
        }

        if (localFonteTracker) {
            if (localFonte == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "fonte cannot be null!!");
            }

            localFonte.serialize(new javax.xml.namespace.QName(
                    "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                    "fonte"), xmlWriter);
        }

        xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if (namespace.equals(
                    "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it")) {
            return "ns1";
        }

        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /**
     * Utility method to write an element start tag.
     */
    private void writeStartElement(java.lang.String prefix,
        java.lang.String namespace, java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

        if (writerPrefix != null) {
            xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
        } else {
            if (namespace.length() == 0) {
                prefix = "";
            } else if (prefix == null) {
                prefix = generatePrefix(namespace);
            }

            xmlWriter.writeStartElement(prefix, localPart, namespace);
            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
        }
    }

    /**
     * Util method to write an attribute with the ns prefix
     */
    private void writeAttribute(java.lang.String prefix,
        java.lang.String namespace, java.lang.String attName,
        java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

        if (writerPrefix != null) {
            xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
        } else {
            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
            xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
        }
    }

    /**
     * Util method to write an attribute without the ns prefix
     */
    private void writeAttribute(java.lang.String namespace,
        java.lang.String attName, java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        if (namespace.equals("")) {
            xmlWriter.writeAttribute(attName, attValue);
        } else {
            xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace),
                namespace, attName, attValue);
        }
    }

    /**
     * Util method to write an attribute without the ns prefix
     */
    private void writeQNameAttribute(java.lang.String namespace,
        java.lang.String attName, javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String attributeNamespace = qname.getNamespaceURI();
        java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

        if (attributePrefix == null) {
            attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
        }

        java.lang.String attributeValue;

        if (attributePrefix.trim().length() > 0) {
            attributeValue = attributePrefix + ":" + qname.getLocalPart();
        } else {
            attributeValue = qname.getLocalPart();
        }

        if (namespace.equals("")) {
            xmlWriter.writeAttribute(attName, attributeValue);
        } else {
            registerPrefix(xmlWriter, namespace);
            xmlWriter.writeAttribute(attributePrefix, namespace, attName,
                attributeValue);
        }
    }

    /**
     *  method to handle Qnames
     */
    private void writeQName(javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String namespaceURI = qname.getNamespaceURI();

        if (namespaceURI != null) {
            java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

            if (prefix == null) {
                prefix = generatePrefix(namespaceURI);
                xmlWriter.writeNamespace(prefix, namespaceURI);
                xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
                xmlWriter.writeCharacters(prefix + ":" +
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            } else {
                
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        } else {
            xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                    qname));
        }
    }

    private void writeQNames(javax.xml.namespace.QName[] qnames,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
        if (qnames != null) {
            
            
            java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
            java.lang.String namespaceURI = null;
            java.lang.String prefix = null;

            for (int i = 0; i < qnames.length; i++) {
                if (i > 0) {
                    stringToWrite.append(" ");
                }

                namespaceURI = qnames[i].getNamespaceURI();

                if (namespaceURI != null) {
                    prefix = xmlWriter.getPrefix(namespaceURI);

                    if ((prefix == null) || (prefix.length() == 0)) {
                        prefix = generatePrefix(namespaceURI);
                        xmlWriter.writeNamespace(prefix, namespaceURI);
                        xmlWriter.setPrefix(prefix, namespaceURI);
                    }

                    if (prefix.trim().length() > 0) {
                        stringToWrite.append(prefix).append(":")
                                     .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                } else {
                    stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qnames[i]));
                }
            }

            xmlWriter.writeCharacters(stringToWrite.toString());
        }
    }

    /**
     * Register a namespace prefix
     */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
        java.lang.String prefix = xmlWriter.getPrefix(namespace);

        if (prefix == null) {
            prefix = generatePrefix(namespace);

            javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();

            while (true) {
                java.lang.String uri = nsContext.getNamespaceURI(prefix);

                if ((uri == null) || (uri.length() == 0)) {
                    break;
                }

                prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
            }

            xmlWriter.writeNamespace(prefix, namespace);
            xmlWriter.setPrefix(prefix, namespace);
        }

        return prefix;
    }

    /**
     *  Factory class that keeps the parse method
     */
    public static class Factory {
        private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

        /**
         * static method to create the object
         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
         *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
         * Postcondition: If this object is an element, the reader is positioned at its end element
         *                If this object is a complex type, the reader is positioned at the end element of its outer element
         */
        public static InfoEsenzioneNew parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            InfoEsenzioneNew object = new InfoEsenzioneNew();

            int event;
            javax.xml.namespace.QName currentQName = null;
            java.lang.String nillableValue = null;
            java.lang.String prefix = "";
            java.lang.String namespaceuri = "";

            try {
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                currentQName = reader.getName();

                if (reader.getAttributeValue(
                            "http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                    java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "type");

                    if (fullTypeName != null) {
                        java.lang.String nsPrefix = null;

                        if (fullTypeName.indexOf(":") > -1) {
                            nsPrefix = fullTypeName.substring(0,
                                    fullTypeName.indexOf(":"));
                        }

                        nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                        java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                    ":") + 1);

                        if (!"InfoEsenzioneNew".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (InfoEsenzioneNew) it.csi.esenred.esenredweb.business.aura.get.ExtensionMapper.getTypeObject(nsUri,
                                type, reader);
                        }
                    }
                }

                
                
                java.util.Vector handledAttributes = new java.util.Vector();

                reader.next();

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "codEsenzione").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codEsenzione" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodEsenzione(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "descEsenzione").equals(reader.getName())) {
                    object.setDescEsenzione(it.csi.esenred.esenredweb.business.aura.get.DescEsenzione_type1.Factory.parse(
                            reader));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "codDiagnosi").equals(reader.getName())) {
                    object.setCodDiagnosi(it.csi.esenred.esenredweb.business.aura.get.CodDiagnosi_type1.Factory.parse(
                            reader));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "diagnosi").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "diagnosi" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDiagnosi(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "fineValEsenzione").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "fineValEsenzione" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setFineValEsenzione(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "idEsenzione").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "idEsenzione" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setIdEsenzione(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "inizioValEsenzione").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "inizioValEsenzione" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setInizioValEsenzione(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "dataEmissione").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataEmissione" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataEmissione(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "dataScadenza").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataScadenza" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataScadenza(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "dataSospensione").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataSospensione" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataSospensione(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "codAttestato").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codAttestato" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodAttestato(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it",
                            "fonte").equals(reader.getName())) {
                    object.setFonte(it.csi.esenred.esenredweb.business.aura.get.Fonte_type1.Factory.parse(
                            reader));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement()) {
                    
                    throw new org.apache.axis2.databinding.ADBException(
                        "Unexpected subelement " + reader.getName());
                }
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }
    } 
}
