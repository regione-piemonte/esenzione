/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.find;

import it.csi.esenred.esenredweb.business.aura.find.Actor.Factory;

/**
 *  ExtensionMapper class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class ExtensionMapper {
    public static java.lang.Object getTypeObject(
        java.lang.String namespaceURI, java.lang.String typeName,
        javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "sesso_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Sesso_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Ens_Messagebody".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Ens_Messagebody.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Ens_Response".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Ens_Response.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "datiAnagraficiMsg".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.DatiAnagraficiMsg.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "findProfiliAnagraficiRequest".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiRequest.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Message".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Message.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "ArrayOfmessageMessage".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.ArrayOfmessageMessage.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Footer".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Footer.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "ArrayOfdatianagraficiDatiAnagrafici".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.ArrayOfdatianagraficiDatiAnagrafici.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "DatiAnagraficiBody".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.DatiAnagraficiBody.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Ens_Request".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Ens_Request.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "cognome_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Cognome_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "sesso_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Sesso_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Header".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Header.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "ArrayOfactorActor".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.ArrayOfactorActor.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "DatiAnagrafici".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.DatiAnagrafici.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Actor".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.Actor.Factory.parse(reader);
        }

        if ("http://AnagrafeFind.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "codiceFiscale_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.find.CodiceFiscale_type1.Factory.parse(reader);
        }

        throw new org.apache.axis2.databinding.ADBException("Unsupported type " +
            namespaceURI + " " + typeName);
    }
}
