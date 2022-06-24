/**
 * FindProfiliAnagraficiRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.find;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  FindProfiliAnagraficiRequest bean class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class FindProfiliAnagraficiRequest extends it.csi.esenred.esenredweb.business.aura.find.Ens_Request
    implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
       name = findProfiliAnagraficiRequest
       Namespace URI = http://AnagrafeFind.central.services.auraws.aura.csi.it
       Namespace Prefix = ns1
     */

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
     * field for Nome
     */
    protected java.lang.String localNome;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNomeTracker = false;

    /**
     * field for DataNascita
     */
    protected java.lang.String localDataNascita;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataNascitaTracker = false;

    /**
     * field for DataDa
     */
    protected java.lang.String localDataDa;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataDaTracker = false;

    /**
     * field for DataA
     */
    protected java.lang.String localDataA;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDataATracker = false;

    /**
     * field for FlagDecesso
     */
    protected java.lang.String localFlagDecesso;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFlagDecessoTracker = false;

    /**
     * field for IdEnte
     */
    protected java.lang.String localIdEnte;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdEnteTracker = false;

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

    public boolean isDataNascitaSpecified() {
        return localDataNascitaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDataNascita() {
        return localDataNascita;
    }

    /**
     * Auto generated setter method
     * @param param DataNascita
     */
    public void setDataNascita(java.lang.String param) {
        localDataNascitaTracker = param != null;

        this.localDataNascita = param;
    }

    public boolean isDataDaSpecified() {
        return localDataDaTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDataDa() {
        return localDataDa;
    }

    /**
     * Auto generated setter method
     * @param param DataDa
     */
    public void setDataDa(java.lang.String param) {
        localDataDaTracker = param != null;

        this.localDataDa = param;
    }

    public boolean isDataASpecified() {
        return localDataATracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getDataA() {
        return localDataA;
    }

    /**
     * Auto generated setter method
     * @param param DataA
     */
    public void setDataA(java.lang.String param) {
        localDataATracker = param != null;

        this.localDataA = param;
    }

    public boolean isFlagDecessoSpecified() {
        return localFlagDecessoTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getFlagDecesso() {
        return localFlagDecesso;
    }

    /**
     * Auto generated setter method
     * @param param FlagDecesso
     */
    public void setFlagDecesso(java.lang.String param) {
        localFlagDecessoTracker = param != null;

        this.localFlagDecesso = param;
    }

    public boolean isIdEnteSpecified() {
        return localIdEnteTracker;
    }

    /**
     * Auto generated getter method
     * @return java.lang.String
     */
    public java.lang.String getIdEnte() {
        return localIdEnte;
    }

    /**
     * Auto generated setter method
     * @param param IdEnte
     */
    public void setIdEnte(java.lang.String param) {
        localIdEnteTracker = param != null;

        this.localIdEnte = param;
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

        java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                "http://AnagrafeFind.central.services.auraws.aura.csi.it");

        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
            writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
                "type", namespacePrefix + ":findProfiliAnagraficiRequest",
                xmlWriter);
        } else {
            writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
                "type", "findProfiliAnagraficiRequest", xmlWriter);
        }

        if (localCodiceFiscaleTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
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
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "cognome", xmlWriter);

            if (localCognome == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "cognome cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCognome);
            }

            xmlWriter.writeEndElement();
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

        if (localDataNascitaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataNascita", xmlWriter);

            if (localDataNascita == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataNascita cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDataNascita);
            }

            xmlWriter.writeEndElement();
        }

        if (localDataDaTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataDa", xmlWriter);

            if (localDataDa == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataDa cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDataDa);
            }

            xmlWriter.writeEndElement();
        }

        if (localDataATracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "dataA", xmlWriter);

            if (localDataA == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "dataA cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDataA);
            }

            xmlWriter.writeEndElement();
        }

        if (localFlagDecessoTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "flagDecesso", xmlWriter);

            if (localFlagDecesso == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "flagDecesso cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFlagDecesso);
            }

            xmlWriter.writeEndElement();
        }

        if (localIdEnteTracker) {
            namespace = "http://AnagrafeFind.central.services.auraws.aura.csi.it";
            writeStartElement(null, namespace, "idEnte", xmlWriter);

            if (localIdEnte == null) {
                
                throw new org.apache.axis2.databinding.ADBException(
                    "idEnte cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localIdEnte);
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
        public static FindProfiliAnagraficiRequest parse(
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            FindProfiliAnagraficiRequest object = new FindProfiliAnagraficiRequest();

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

                        if (!"findProfiliAnagraficiRequest".equals(type)) {
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext()
                                                           .getNamespaceURI(nsPrefix);

                            return (FindProfiliAnagraficiRequest) it.csi.esenred.esenredweb.business.aura.find.ExtensionMapper.getTypeObject(nsUri,
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
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
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

                    object.setDataNascita(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "dataDa").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataDa" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataDa(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "dataA").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "dataA" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setDataA(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "flagDecesso").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "flagDecesso" +
                            "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setFlagDecesso(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
                            "idEnte").equals(reader.getName())) {
                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        throw new org.apache.axis2.databinding.ADBException(
                            "The element: " + "idEnte" + "  cannot be null");
                    }

                    java.lang.String content = reader.getElementText();

                    object.setIdEnte(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
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
