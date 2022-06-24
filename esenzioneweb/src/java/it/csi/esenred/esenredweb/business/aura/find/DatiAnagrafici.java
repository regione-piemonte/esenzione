/**
 * DatiAnagrafici.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.find;


/**
 *  DatiAnagrafici bean class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class DatiAnagrafici implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
       name = DatiAnagrafici
       Namespace URI = http://AnagrafeFind.central.services.auraws.aura.csi.it
       Namespace Prefix = ns1
     */

    /**
     * field for IdProfiloAnagrafico
     */
    protected java.math.BigDecimal localIdProfiloAnagrafico;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdProfiloAnagraficoTracker = false;

    /**
     * field for CodiceFiscale
     */
    protected it.csi.esenred.esenredweb.business.aura.find.CodiceFiscale_type1 localCodiceFiscale;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodiceFiscaleTracker = false;

    /**
     * field for Cognome
     */
    protected it.csi.esenred.esenredweb.business.aura.find.Cognome_type1 localCognome;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCognomeTracker = false;

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
    protected it.csi.esenred.esenredweb.business.aura.find.Sesso_type1 localSesso;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSessoTracker = false;

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
     * field for CodiceStatoNascita
     */
    protected java.lang.String localCodiceStatoNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodiceStatoNascitaTracker = false;

    /**
     * field for StatoNascita
     */
    protected java.lang.String localStatoNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatoNascitaTracker = false;

    /**
     * field for ComuneNascita
     */
    protected java.lang.String localComuneNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localComuneNascitaTracker = false;

    /**
     * field for CodiceComuneNascita
     */
    protected java.lang.String localCodiceComuneNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCodiceComuneNascitaTracker = false;

    /**
     * field for ProvinciaNascita
     */
    protected java.lang.String localProvinciaNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localProvinciaNascitaTracker = false;

    /**
     * field for DataDecesso
     */
    protected java.util.Calendar localDataDecesso;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataDecessoTracker = false;

    public boolean isIdProfiloAnagraficoSpecified() {
        return localIdProfiloAnagraficoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getIdProfiloAnagrafico() {
        return localIdProfiloAnagrafico;
    }

    /**
     * Auto generated setter method
     * @param param IdProfiloAnagrafico
     */
    public void setIdProfiloAnagrafico(java.math.BigDecimal param) {
        localIdProfiloAnagraficoTracker = param != null;

        this.localIdProfiloAnagrafico = param;
    }

    public boolean isCodiceFiscaleSpecified() {
        return localCodiceFiscaleTracker;
    }

    /**
     * Auto generated getter method
     * @return it.csi.aura.auraws.services.central.anagrafefind.CodiceFiscale_type1
     */
    public it.csi.esenred.esenredweb.business.aura.find.CodiceFiscale_type1 getCodiceFiscale() {
        return localCodiceFiscale;
    }

    /**
     * Auto generated setter method
     * @param param CodiceFiscale
     */
    public void setCodiceFiscale(
        it.csi.esenred.esenredweb.business.aura.find.CodiceFiscale_type1 param) {
        localCodiceFiscaleTracker = param != null;

        this.localCodiceFiscale = param;
    }

    public boolean isCognomeSpecified() {
        return localCognomeTracker;
    }

    /**
     * Auto generated getter method
     * @return it.csi.aura.auraws.services.central.anagrafefind.Cognome_type1
     */
    public it.csi.esenred.esenredweb.business.aura.find.Cognome_type1 getCognome() {
        return localCognome;
    }

    /**
     * Auto generated setter method
     * @param param Cognome
     */
    public void setCognome(
        it.csi.esenred.esenredweb.business.aura.find.Cognome_type1 param) {
        localCognomeTracker = param != null;

        this.localCognome = param;
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
     * @return it.csi.aura.auraws.services.central.anagrafefind.Sesso_type1
     */
    public it.csi.esenred.esenredweb.business.aura.find.Sesso_type1 getSesso() {
        return localSesso;
    }

    /**
     * Auto generated setter method
     * @param param Sesso
     */
    public void setSesso(
        it.csi.esenred.esenredweb.business.aura.find.Sesso_type1 param) {
        localSessoTracker = param != null;

        this.localSesso = param;
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

    public boolean isCodiceStatoNascitaSpecified() {
        return localCodiceStatoNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodiceStatoNascita() {
        return localCodiceStatoNascita;
    }

    /**
     * Auto generated setter method
     * @param param CodiceStatoNascita
     */
    public void setCodiceStatoNascita(java.lang.String param) {
        localCodiceStatoNascitaTracker = param != null;

        this.localCodiceStatoNascita = param;
    }

    public boolean isStatoNascitaSpecified() {
        return localStatoNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getStatoNascita() {
        return localStatoNascita;
    }

    /**
     * Auto generated setter method
     * @param param StatoNascita
     */
    public void setStatoNascita(java.lang.String param) {
        localStatoNascitaTracker = param != null;

        this.localStatoNascita = param;
    }

    public boolean isComuneNascitaSpecified() {
        return localComuneNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getComuneNascita() {
        return localComuneNascita;
    }

    /**
     * Auto generated setter method
     * @param param ComuneNascita
     */
    public void setComuneNascita(java.lang.String param) {
        localComuneNascitaTracker = param != null;

        this.localComuneNascita = param;
    }

    public boolean isCodiceComuneNascitaSpecified() {
        return localCodiceComuneNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getCodiceComuneNascita() {
        return localCodiceComuneNascita;
    }

    /**
     * Auto generated setter method
     * @param param CodiceComuneNascita
     */
    public void setCodiceComuneNascita(java.lang.String param) {
        localCodiceComuneNascitaTracker = param != null;

        this.localCodiceComuneNascita = param;
    }

    public boolean isProvinciaNascitaSpecified() {
        return localProvinciaNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getProvinciaNascita() {
        return localProvinciaNascita;
    }

    /**
     * Auto generated setter method
     * @param param ProvinciaNascita
     */
    public void setProvinciaNascita(java.lang.String param) {
        localProvinciaNascitaTracker = param != null;

        this.localProvinciaNascita = param;
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
                    "http://AnagrafeFind.central.services.auraws.aura.csi.it");

            if ((namespacePrefix != null) &&
                    (namespacePrefix.trim().length() > 0)) {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    namespacePrefix + ":DatiAnagrafici", xmlWriter);
            } else {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    "DatiAnagrafici", xmlWriter);
            }
        }

        if (localIdProfiloAnagraficoTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "idProfiloAnagrafico", xmlWriter);

            if (localIdProfiloAnagrafico == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "idProfiloAnagrafico cannot be null!!");
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localIdProfiloAnagrafico));
            }

            xmlWriter.writeEndElement();
        }

        if (localCodiceFiscaleTracker) {
            if (localCodiceFiscale == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "codiceFiscale cannot be null!!");
            }

            localCodiceFiscale.serialize(new javax.xml.namespace.QName(
                    "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                    "codiceFiscale"), xmlWriter);
        }

        if (localCognomeTracker) {
            if (localCognome == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "cognome cannot be null!!");
            }

            localCognome.serialize(new javax.xml.namespace.QName(
                    "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                    "cognome"), xmlWriter);
        }

        if (localNomeTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
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
            if (localSesso == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "sesso cannot be null!!");
            }

            localSesso.serialize(new javax.xml.namespace.QName(
                    "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                    "sesso"), xmlWriter);
        }

        if (localDataNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
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

        if (localCodiceStatoNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codiceStatoNascita", xmlWriter);

            if (localCodiceStatoNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codiceStatoNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodiceStatoNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localStatoNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "statoNascita", xmlWriter);

            if (localStatoNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "statoNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localStatoNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localComuneNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "comuneNascita", xmlWriter);

            if (localComuneNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "comuneNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localComuneNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localCodiceComuneNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "codiceComuneNascita", xmlWriter);

            if (localCodiceComuneNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "codiceComuneNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCodiceComuneNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localProvinciaNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "provinciaNascita", xmlWriter);

            if (localProvinciaNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "provinciaNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localProvinciaNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localDataDecessoTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
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

        xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if (namespace.equals(
                    "http://AnagrafeFind.central.services.auraws.aura.csi.it")) {
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
        public static DatiAnagrafici parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            DatiAnagrafici object = new DatiAnagrafici();

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

                        if (!"DatiAnagrafici".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (DatiAnagrafici) it.csi.esenred.esenredweb.business.aura.find.ExtensionMapper.getTypeObject(nsUri,
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
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "idProfiloAnagrafico").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "idProfiloAnagrafico" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setIdProfiloAnagrafico(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "codiceFiscale").equals(reader.getName())) {
                    object.setCodiceFiscale(it.csi.esenred.esenredweb.business.aura.find.CodiceFiscale_type1.Factory.parse(
                            reader));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "cognome").equals(reader.getName())) {
                    object.setCognome(it.csi.esenred.esenredweb.business.aura.find.Cognome_type1.Factory.parse(
                            reader));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
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
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "sesso").equals(reader.getName())) {
                    object.setSesso(it.csi.esenred.esenredweb.business.aura.find.Sesso_type1.Factory.parse(
                            reader));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
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
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "codiceStatoNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codiceStatoNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodiceStatoNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "statoNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "statoNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setStatoNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "comuneNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "comuneNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setComuneNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "codiceComuneNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "codiceComuneNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setCodiceComuneNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "provinciaNascita").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "provinciaNascita" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setProvinciaNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            content));

                    reader.next();
                } 

                else {
                }

                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                if (reader.isStartElement() &&
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
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
