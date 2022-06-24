/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.get;

import it.csi.esenred.esenredweb.business.aura.get.AltreInfoNew.Factory;

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
        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "fonte_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Fonte_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "DatiPrimari".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.DatiPrimari.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "motivoFineAssistenza_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.MotivoFineAssistenza_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "codDistrettoResidenza_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.CodDistrettoResidenza_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "statoInvioMEF_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.StatoInvioMEF_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Ens_Response".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Ens_Response.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "AltreInfoNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.AltreInfoNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "codTipoProfiloSanitario_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.CodTipoProfiloSanitario_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "descDistrettoResidenza_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.DescDistrettoResidenza_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "descEsenzione_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.DescEsenzione_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "soggettoAuraNewMsg".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.SoggettoAuraNewMsg.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Header".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Header.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "codiceTesseraRegionale_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.CodiceTesseraRegionale_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "InfoAnagraficheNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.InfoAnagraficheNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "codDiagnosi_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.CodDiagnosi_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "ArrayOfmessageMessage".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.ArrayOfmessageMessage.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Footer".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Footer.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "InfoSanitarie".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.InfoSanitarie.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "DatiSecondariNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.DatiSecondariNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "codDistrettoDomicilio_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.CodDistrettoDomicilio_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "InfoEsenzioneNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "SoggettoAuraBodyNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.SoggettoAuraBodyNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "ArrayOfinformazioniAltreInfoNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.ArrayOfinformazioniAltreInfoNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Message".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Message.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "descTipoProfiloSanitario_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.DescTipoProfiloSanitario_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Ens_Messagebody".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Ens_Messagebody.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "ArrayOfinfoesenzioneInfoEsenzioneNew".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.ArrayOfinfoesenzioneInfoEsenzioneNew.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "ArrayOfeventoEvento".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.ArrayOfeventoEvento.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "fonte_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Fonte_type1.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) && "Evento".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.Evento.Factory.parse(reader);
        }

        if ("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it".equals(
                    namespaceURI) &&
                "descDistrettoDomicilio_type1".equals(typeName)) {
            return it.csi.esenred.esenredweb.business.aura.get.DescDistrettoDomicilio_type1.Factory.parse(reader);
        }

        throw new org.apache.axis2.databinding.ADBException("Unsupported type " +
            namespaceURI + " " + typeName);
    }
}
