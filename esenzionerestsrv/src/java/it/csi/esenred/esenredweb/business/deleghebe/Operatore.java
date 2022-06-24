/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per operatore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="operatore">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="opId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataCancellazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataCreazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="loginOperazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opCf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opCognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opNome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operatore", propOrder = {
    "rest"
})
public class Operatore
    extends Entita
{

    @XmlElementRefs({
        @XmlElementRef(name = "opNome", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "dataCreazione", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "opCognome", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "dataModifica", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "opCf", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "dataCancellazione", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "loginOperazione", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "opId", namespace = "http://deleghebe.csi.it/", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> rest;

    /**
     * Recupera il resto del modello di contenuto. 
     * 
     * <p>
     * Questa proprieta "catch-all" viene recuperata per il seguente motivo: 
     * Il nome di campo "DataCancellazione" e' usato da due diverse parti di uno schema. Vedere: 
     * riga 874 di file:/C:/Users/2048/AppData/Local/Temp/tempdir4169015323296922775.tmp/DelegheCittadiniService_1.wsdl
     * riga 104 di file:/C:/Users/2048/AppData/Local/Temp/tempdir4169015323296922775.tmp/DelegheCittadiniService_1.wsdl
     * <p>
     * Per eliminare questa proprieta, applicare una personalizzazione della proprieta a una 
     * delle seguenti due dichiarazioni per modificarne il nome: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<?>>();
        }
        return this.rest;
    }

}
