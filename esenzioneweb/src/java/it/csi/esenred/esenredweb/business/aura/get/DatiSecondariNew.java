/**
 * DatiSecondariNew.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.get;


/**
 *  DatiSecondariNew bean class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class DatiSecondariNew implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
       name = DatiSecondariNew
       Namespace URI = http://AnagrafeSanitaria.central.services.auraws.aura.csi.it
       Namespace Prefix = ns1
     */

    /**
     * field for Cap
     */
    protected java.lang.String localCap;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCapTracker = false;

    /**
     * field for CodComune
     */
    protected java.lang.String localCodComune;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodComuneTracker = false;

    /**
     * field for CodStato
     */
    protected java.lang.String localCodStato;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodStatoTracker = false;

    /**
     * field for DescComune
     */
    protected java.lang.String localDescComune;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescComuneTracker = false;

    /**
     * field for DescStato
     */
    protected java.lang.String localDescStato;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescStatoTracker = false;

    /**
     * field for Indirizzo
     */
    protected java.lang.String localIndirizzo;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIndirizzoTracker = false;

    /**
     * field for NumCivico
     */
    protected java.lang.String localNumCivico;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumCivicoTracker = false;

    /**
     * field for StatoResidenza
     */
    protected java.lang.String localStatoResidenza;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatoResidenzaTracker = false;

    /**
     * field for Telefono
     */
    protected java.lang.String localTelefono;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTelefonoTracker = false;

    /**
     * field for DataInizio
     */
    protected java.util.Calendar localDataInizio;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataInizioTracker = false;

    /**
     * field for DataFine
     */
    protected java.util.Calendar localDataFine;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataFineTracker = false;

    /**
     * field for IdAmbitoMmg
     */
    protected java.math.BigDecimal localIdAmbitoMmg;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdAmbitoMmgTracker = false;

    /**
     * field for CodAmbitoMmg
     */
    protected java.lang.String localCodAmbitoMmg;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodAmbitoMmgTracker = false;

    /**
     * field for DescAmbitoMmg
     */
    protected java.lang.String localDescAmbitoMmg;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescAmbitoMmgTracker = false;

    public boolean isCapSpecified() {
        return localCapTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCap() {
        return localCap;
    }

    /**
     * Auto generated setter method
     * @param param Cap
     */
    public void setCap(java.lang.String param) {
        localCapTracker = param != null;

        this.localCap = param;
    }

    public boolean isCodComuneSpecified() {
        return localCodComuneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodComune() {
        return localCodComune;
    }

    /**
     * Auto generated setter method
     * @param param CodComune
     */
    public void setCodComune(java.lang.String param) {
        localCodComuneTracker = param != null;

        this.localCodComune = param;
    }

    public boolean isCodStatoSpecified() {
        return localCodStatoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodStato() {
        return localCodStato;
    }

    /**
     * Auto generated setter method
     * @param param CodStato
     */
    public void setCodStato(java.lang.String param) {
        localCodStatoTracker = param != null;

        this.localCodStato = param;
    }

    public boolean isDescComuneSpecified() {
        return localDescComuneTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDescComune() {
        return localDescComune;
    }

    /**
     * Auto generated setter method
     * @param param DescComune
     */
    public void setDescComune(java.lang.String param) {
        localDescComuneTracker = param != null;

        this.localDescComune = param;
    }

    public boolean isDescStatoSpecified() {
        return localDescStatoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDescStato() {
        return localDescStato;
    }

    /**
     * Auto generated setter method
     * @param param DescStato
     */
    public void setDescStato(java.lang.String param) {
        localDescStatoTracker = param != null;

        this.localDescStato = param;
    }

    public boolean isIndirizzoSpecified() {
        return localIndirizzoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getIndirizzo() {
        return localIndirizzo;
    }

    /**
     * Auto generated setter method
     * @param param Indirizzo
     */
    public void setIndirizzo(java.lang.String param) {
        localIndirizzoTracker = param != null;

        this.localIndirizzo = param;
    }

    public boolean isNumCivicoSpecified() {
        return localNumCivicoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getNumCivico() {
        return localNumCivico;
    }

    /**
     * Auto generated setter method
     * @param param NumCivico
     */
    public void setNumCivico(java.lang.String param) {
        localNumCivicoTracker = param != null;

        this.localNumCivico = param;
    }

    public boolean isStatoResidenzaSpecified() {
        return localStatoResidenzaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getStatoResidenza() {
        return localStatoResidenza;
    }

    /**
     * Auto generated setter method
     * @param param StatoResidenza
     */
    public void setStatoResidenza(java.lang.String param) {
        localStatoResidenzaTracker = param != null;

        this.localStatoResidenza = param;
    }

    public boolean isTelefonoSpecified() {
        return localTelefonoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getTelefono() {
        return localTelefono;
    }

    /**
     * Auto generated setter method
     * @param param Telefono
     */
    public void setTelefono(java.lang.String param) {
        localTelefonoTracker = param != null;

        this.localTelefono = param;
    }

    public boolean isDataInizioSpecified() {
        return localDataInizioTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataInizio() {
        return localDataInizio;
    }

    /**
     * Auto generated setter method
     * @param param DataInizio
     */
    public void setDataInizio(java.util.Calendar param) {
        localDataInizioTracker = param != null;

        this.localDataInizio = param;
    }

    public boolean isDataFineSpecified() {
        return localDataFineTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataFine() {
        return localDataFine;
    }

    /**
     * Auto generated setter method
     * @param param DataFine
     */
    public void setDataFine(java.util.Calendar param) {
        localDataFineTracker = param != null;

        this.localDataFine = param;
    }

    public boolean isIdAmbitoMmgSpecified() {
        return localIdAmbitoMmgTracker;
    }

    /**
     * Auto generated getter method
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getIdAmbitoMmg() {
        return localIdAmbitoMmg;
    }

    /**
     * Auto generated setter method
     * @param param IdAmbitoMmg
     */
    public void setIdAmbitoMmg(java.math.BigDecimal param) {
        localIdAmbitoMmgTracker = param != null;

        this.localIdAmbitoMmg = param;
    }

    public boolean isCodAmbitoMmgSpecified() {
        return localCodAmbitoMmgTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodAmbitoMmg() {
        return localCodAmbitoMmg;
    }

    /**
     * Auto generated setter method
     * @param param CodAmbitoMmg
     */
    public void setCodAmbitoMmg(java.lang.String param) {
        localCodAmbitoMmgTracker = param != null;

        this.localCodAmbitoMmg = param;
    }

    public boolean isDescAmbitoMmgSpecified() {
        return localDescAmbitoMmgTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDescAmbitoMmg() {
        return localDescAmbitoMmg;
    }

    /**
     * Auto generated setter method
     * @param param DescAmbitoMmg
     */
    public void setDescAmbitoMmg(java.lang.String param) {
        localDescAmbitoMmgTracker = param != null;

        this.localDescAmbitoMmg = param;
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
                    namespacePrefix + ":DatiSecondariNew", xmlWriter);
            } else {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    "DatiSecondariNew", xmlWriter);
            }
        }

        if (localCapTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "cap", xmlWriter);

            if (localCap == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "cap cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCap);
            }

            xmlWriter.writeEndElement();
        }

        if (localCodComuneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codComune", xmlWriter);

            if (localCodComune == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codComune cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodComune);
            }

            xmlWriter.writeEndElement();
        }

        if (localCodStatoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codStato", xmlWriter);

            if (localCodStato == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codStato cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodStato);
            }

            xmlWriter.writeEndElement();
        }

        if (localDescComuneTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "descComune", xmlWriter);

            if (localDescComune == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "descComune cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDescComune);
            }

            xmlWriter.writeEndElement();
        }

        if (localDescStatoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "descStato", xmlWriter);

            if (localDescStato == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "descStato cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDescStato);
            }

            xmlWriter.writeEndElement();
        }

        if (localIndirizzoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "indirizzo", xmlWriter);

            if (localIndirizzo == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "indirizzo cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localIndirizzo);
            }

            xmlWriter.writeEndElement();
        }

        if (localNumCivicoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "numCivico", xmlWriter);

            if (localNumCivico == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "numCivico cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localNumCivico);
            }

            xmlWriter.writeEndElement();
        }

        if (localStatoResidenzaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "statoResidenza", xmlWriter);

            if (localStatoResidenza == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "statoResidenza cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localStatoResidenza);
            }

            xmlWriter.writeEndElement();
        }

        if (localTelefonoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "telefono", xmlWriter);

            if (localTelefono == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "telefono cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localTelefono);
            }

            xmlWriter.writeEndElement();
        }

        if (localDataInizioTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataInizio", xmlWriter);

            if (localDataInizio == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataInizio cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataInizio));
            }

            xmlWriter.writeEndElement();
        }

        if (localDataFineTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataFine", xmlWriter);

            if (localDataFine == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataFine cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataFine));
            }

            xmlWriter.writeEndElement();
        }

        if (localIdAmbitoMmgTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "idAmbitoMmg", xmlWriter);

            if (localIdAmbitoMmg == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "idAmbitoMmg cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localIdAmbitoMmg));
            }

            xmlWriter.writeEndElement();
        }

        if (localCodAmbitoMmgTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codAmbitoMmg", xmlWriter);

            if (localCodAmbitoMmg == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codAmbitoMmg cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodAmbitoMmg);
            }

            xmlWriter.writeEndElement();
        }

        if (localDescAmbitoMmgTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "descAmbitoMmg", xmlWriter);

            if (localDescAmbitoMmg == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "descAmbitoMmg cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDescAmbitoMmg);
            }

            xmlWriter.writeEndElement();
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
        public static DatiSecondariNew parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            DatiSecondariNew object = new DatiSecondariNew();

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

                        if (!"DatiSecondariNew".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (DatiSecondariNew) it.csi.esenred.esenredweb.business.aura.get.ExtensionMapper.getTypeObject(nsUri,
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
                            "cap").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "cap" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCap(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "codComune").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codComune" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodComune(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "codStato").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codStato" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodStato(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "descComune").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "descComune" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDescComune(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "descStato").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "descStato" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDescStato(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "indirizzo").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "indirizzo" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setIndirizzo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "numCivico").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "numCivico" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setNumCivico(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "statoResidenza").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "statoResidenza" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setStatoResidenza(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "telefono").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "telefono" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setTelefono(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "dataInizio").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataInizio" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataInizio(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "dataFine").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataFine" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataFine(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "idAmbitoMmg").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "idAmbitoMmg" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setIdAmbitoMmg(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
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
                            "codAmbitoMmg").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codAmbitoMmg" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodAmbitoMmg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "descAmbitoMmg").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "descAmbitoMmg" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDescAmbitoMmg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

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
