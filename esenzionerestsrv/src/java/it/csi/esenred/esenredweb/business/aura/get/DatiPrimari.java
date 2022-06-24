/**
 * DatiPrimari.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.get;


/**
 *  DatiPrimari bean class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class DatiPrimari implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
       name = DatiPrimari
       Namespace URI = http://AnagrafeSanitaria.central.services.auraws.aura.csi.it
       Namespace Prefix = ns1
     */

    /**
     * field for CodCittadinanza
     */
    protected java.lang.String localCodCittadinanza;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodCittadinanzaTracker = false;

    /**
     * field for CodComuneNascita
     */
    protected java.lang.String localCodComuneNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodComuneNascitaTracker = false;

    /**
     * field for CodStatoNascita
     */
    protected java.lang.String localCodStatoNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodStatoNascitaTracker = false;

    /**
     * field for CodiceFiscale
     */
    protected java.lang.String localCodiceFiscale;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodiceFiscaleTracker = false;

    /**
     * field for Cognome
     */
    protected java.lang.String localCognome;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCognomeTracker = false;

    /**
     * field for DataDecesso
     */
    protected java.util.Calendar localDataDecesso;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataDecessoTracker = false;

    /**
     * field for DataNascita
     */
    protected java.util.Calendar localDataNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataNascitaTracker = false;

    /**
     * field for DescCittadinanza
     */
    protected java.lang.String localDescCittadinanza;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescCittadinanzaTracker = false;

    /**
     * field for DescComuneNascita
     */
    protected java.lang.String localDescComuneNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescComuneNascitaTracker = false;

    /**
     * field for DescStatoNascita
     */
    protected java.lang.String localDescStatoNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescStatoNascitaTracker = false;

    /**
     * field for Nome
     */
    protected java.lang.String localNome;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNomeTracker = false;

    /**
     * field for Sesso
     */
    protected java.lang.String localSesso;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSessoTracker = false;

    /**
     * field for SiglaProvNascita
     */
    protected java.lang.String localSiglaProvNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSiglaProvNascitaTracker = false;

    /**
     * field for StatoCodiceFiscale
     */
    protected java.lang.String localStatoCodiceFiscale;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatoCodiceFiscaleTracker = false;

    /**
     * field for StatoProfiloAnagrafico
     */
    protected java.lang.String localStatoProfiloAnagrafico;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatoProfiloAnagraficoTracker = false;

    public boolean isCodCittadinanzaSpecified() {
        return localCodCittadinanzaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodCittadinanza() {
        return localCodCittadinanza;
    }

    /**
     * Auto generated setter method
     * @param param CodCittadinanza
     */
    public void setCodCittadinanza(java.lang.String param) {
        localCodCittadinanzaTracker = param != null;

        this.localCodCittadinanza = param;
    }

    public boolean isCodComuneNascitaSpecified() {
        return localCodComuneNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodComuneNascita() {
        return localCodComuneNascita;
    }

    /**
     * Auto generated setter method
     * @param param CodComuneNascita
     */
    public void setCodComuneNascita(java.lang.String param) {
        localCodComuneNascitaTracker = param != null;

        this.localCodComuneNascita = param;
    }

    public boolean isCodStatoNascitaSpecified() {
        return localCodStatoNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodStatoNascita() {
        return localCodStatoNascita;
    }

    /**
     * Auto generated setter method
     * @param param CodStatoNascita
     */
    public void setCodStatoNascita(java.lang.String param) {
        localCodStatoNascitaTracker = param != null;

        this.localCodStatoNascita = param;
    }

    public boolean isCodiceFiscaleSpecified() {
        return localCodiceFiscaleTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodiceFiscale() {
        return localCodiceFiscale;
    }

    /**
     * Auto generated setter method
     * @param param CodiceFiscale
     */
    public void setCodiceFiscale(java.lang.String param) {
        localCodiceFiscaleTracker = param != null;

        this.localCodiceFiscale = param;
    }

    public boolean isCognomeSpecified() {
        return localCognomeTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCognome() {
        return localCognome;
    }

    /**
     * Auto generated setter method
     * @param param Cognome
     */
    public void setCognome(java.lang.String param) {
        localCognomeTracker = param != null;

        this.localCognome = param;
    }

    public boolean isDataDecessoSpecified() {
        return localDataDecessoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataDecesso() {
        return localDataDecesso;
    }

    /**
     * Auto generated setter method
     * @param param DataDecesso
     */
    public void setDataDecesso(java.util.Calendar param) {
        localDataDecessoTracker = param != null;

        this.localDataDecesso = param;
    }

    public boolean isDataNascitaSpecified() {
        return localDataNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.util.Calendar
     */
    public java.util.Calendar getDataNascita() {
        return localDataNascita;
    }

    /**
     * Auto generated setter method
     * @param param DataNascita
     */
    public void setDataNascita(java.util.Calendar param) {
        localDataNascitaTracker = param != null;

        this.localDataNascita = param;
    }

    public boolean isDescCittadinanzaSpecified() {
        return localDescCittadinanzaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDescCittadinanza() {
        return localDescCittadinanza;
    }

    /**
     * Auto generated setter method
     * @param param DescCittadinanza
     */
    public void setDescCittadinanza(java.lang.String param) {
        localDescCittadinanzaTracker = param != null;

        this.localDescCittadinanza = param;
    }

    public boolean isDescComuneNascitaSpecified() {
        return localDescComuneNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDescComuneNascita() {
        return localDescComuneNascita;
    }

    /**
     * Auto generated setter method
     * @param param DescComuneNascita
     */
    public void setDescComuneNascita(java.lang.String param) {
        localDescComuneNascitaTracker = param != null;

        this.localDescComuneNascita = param;
    }

    public boolean isDescStatoNascitaSpecified() {
        return localDescStatoNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDescStatoNascita() {
        return localDescStatoNascita;
    }

    /**
     * Auto generated setter method
     * @param param DescStatoNascita
     */
    public void setDescStatoNascita(java.lang.String param) {
        localDescStatoNascitaTracker = param != null;

        this.localDescStatoNascita = param;
    }

    public boolean isNomeSpecified() {
        return localNomeTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getNome() {
        return localNome;
    }

    /**
     * Auto generated setter method
     * @param param Nome
     */
    public void setNome(java.lang.String param) {
        localNomeTracker = param != null;

        this.localNome = param;
    }

    public boolean isSessoSpecified() {
        return localSessoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getSesso() {
        return localSesso;
    }

    /**
     * Auto generated setter method
     * @param param Sesso
     */
    public void setSesso(java.lang.String param) {
        localSessoTracker = param != null;

        this.localSesso = param;
    }

    public boolean isSiglaProvNascitaSpecified() {
        return localSiglaProvNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getSiglaProvNascita() {
        return localSiglaProvNascita;
    }

    /**
     * Auto generated setter method
     * @param param SiglaProvNascita
     */
    public void setSiglaProvNascita(java.lang.String param) {
        localSiglaProvNascitaTracker = param != null;

        this.localSiglaProvNascita = param;
    }

    public boolean isStatoCodiceFiscaleSpecified() {
        return localStatoCodiceFiscaleTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getStatoCodiceFiscale() {
        return localStatoCodiceFiscale;
    }

    /**
     * Auto generated setter method
     * @param param StatoCodiceFiscale
     */
    public void setStatoCodiceFiscale(java.lang.String param) {
        localStatoCodiceFiscaleTracker = param != null;

        this.localStatoCodiceFiscale = param;
    }

    public boolean isStatoProfiloAnagraficoSpecified() {
        return localStatoProfiloAnagraficoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getStatoProfiloAnagrafico() {
        return localStatoProfiloAnagrafico;
    }

    /**
     * Auto generated setter method
     * @param param StatoProfiloAnagrafico
     */
    public void setStatoProfiloAnagrafico(java.lang.String param) {
        localStatoProfiloAnagraficoTracker = param != null;

        this.localStatoProfiloAnagrafico = param;
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
                    namespacePrefix + ":DatiPrimari", xmlWriter);
            } else {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    "DatiPrimari", xmlWriter);
            }
        }

        if (localCodCittadinanzaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codCittadinanza", xmlWriter);

            if (localCodCittadinanza == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codCittadinanza cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodCittadinanza);
            }

            xmlWriter.writeEndElement();
        }

        if (localCodComuneNascitaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codComuneNascita", xmlWriter);

            if (localCodComuneNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codComuneNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodComuneNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localCodStatoNascitaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codStatoNascita", xmlWriter);

            if (localCodStatoNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codStatoNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodStatoNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localCodiceFiscaleTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codiceFiscale", xmlWriter);

            if (localCodiceFiscale == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codiceFiscale cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodiceFiscale);
            }

            xmlWriter.writeEndElement();
        }

        if (localCognomeTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "cognome", xmlWriter);

            if (localCognome == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "cognome cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCognome);
            }

            xmlWriter.writeEndElement();
        }

        if (localDataDecessoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataDecesso", xmlWriter);

            if (localDataDecesso == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataDecesso cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataDecesso));
            }

            xmlWriter.writeEndElement();
        }

        if (localDataNascitaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataNascita", xmlWriter);

            if (localDataNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDataNascita));
            }

            xmlWriter.writeEndElement();
        }

        if (localDescCittadinanzaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "descCittadinanza", xmlWriter);

            if (localDescCittadinanza == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "descCittadinanza cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDescCittadinanza);
            }

            xmlWriter.writeEndElement();
        }

        if (localDescComuneNascitaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "descComuneNascita", xmlWriter);

            if (localDescComuneNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "descComuneNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDescComuneNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localDescStatoNascitaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "descStatoNascita", xmlWriter);

            if (localDescStatoNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "descStatoNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDescStatoNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localNomeTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "nome", xmlWriter);

            if (localNome == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "nome cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localNome);
            }

            xmlWriter.writeEndElement();
        }

        if (localSessoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "sesso", xmlWriter);

            if (localSesso == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "sesso cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localSesso);
            }

            xmlWriter.writeEndElement();
        }

        if (localSiglaProvNascitaTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "siglaProvNascita", xmlWriter);

            if (localSiglaProvNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "siglaProvNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localSiglaProvNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localStatoCodiceFiscaleTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "statoCodiceFiscale", xmlWriter);

            if (localStatoCodiceFiscale == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "statoCodiceFiscale cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localStatoCodiceFiscale);
            }

            xmlWriter.writeEndElement();
        }

        if (localStatoProfiloAnagraficoTracker) {
            namespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "statoProfiloAnagrafico",
                xmlWriter);

            if (localStatoProfiloAnagrafico == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "statoProfiloAnagrafico cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localStatoProfiloAnagrafico);
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
        public static DatiPrimari parse(javax.xml.stream.XMLStreamReader reader)
            throws java.lang.Exception {
            DatiPrimari object = new DatiPrimari();

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

                        if (!"DatiPrimari".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (DatiPrimari) it.csi.esenred.esenredweb.business.aura.get.ExtensionMapper.getTypeObject(nsUri,
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
                            "codCittadinanza").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codCittadinanza" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodCittadinanza(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "codComuneNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codComuneNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodComuneNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "codStatoNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codStatoNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodStatoNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "codiceFiscale").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codiceFiscale" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodiceFiscale(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "cognome").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "cognome" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCognome(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "dataDecesso").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataDecesso" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataDecesso(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "dataNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(
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
                            "descCittadinanza").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "descCittadinanza" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDescCittadinanza(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "descComuneNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "descComuneNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDescComuneNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "descStatoNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "descStatoNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDescStatoNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "nome").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "nome" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setNome(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "sesso").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "sesso" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setSesso(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "siglaProvNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "siglaProvNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setSiglaProvNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "statoCodiceFiscale").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "statoCodiceFiscale" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setStatoCodiceFiscale(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "statoProfiloAnagrafico").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "statoProfiloAnagrafico" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setStatoProfiloAnagrafico(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
