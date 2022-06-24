/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.aura.get;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ens_Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ens_Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://AnagrafeSanitaria.central.services.auraws.aura.csi.it}Ens_Messagebody">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ens_Response")
@XmlSeeAlso({
    SoggettoAuraMsg.class
})
public class EnsResponse
    extends EnsMessagebody
{


}
