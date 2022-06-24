/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DelegheCittadiniService", targetNamespace = "http://deleghebe.csi.it/", wsdlLocation = "file:/C:/Users/2048/AppData/Local/Temp/tempdir4169015323296922775.tmp/DelegheCittadiniService_1.wsdl")
public class DelegheCittadiniService_Service
    extends Service
{

    private final static URL DELEGHECITTADINISERVICE_WSDL_LOCATION;
    private final static WebServiceException DELEGHECITTADINISERVICE_EXCEPTION;
    private final static QName DELEGHECITTADINISERVICE_QNAME = new QName("http://deleghebe.csi.it/", "DelegheCittadiniService");

    private final static Logger logger = Logger.getLogger(it.csi.esenred.esenredweb.business.deleghebe.DelegheCittadiniService_Service.class.getName());

    static {
        URL url = null;
        WebServiceException e = null;
        String urlWSDL = IntegrationClientImpl.getInstance().getUrlGetDeleghe();
        try {
            URL baseUrl;
            baseUrl =DelegheCittadiniService_Service.class.getResource(".");
            url = new URL(baseUrl, urlWSDL);

        } catch (MalformedURLException ex) {
        	 logger.warning("Failed to create URL for the wsdl Location: '"+urlWSDL+"', retrying as a local file");
             logger.warning(ex.getMessage());
            e = new WebServiceException(ex);
        }
        DELEGHECITTADINISERVICE_WSDL_LOCATION = url;
        DELEGHECITTADINISERVICE_EXCEPTION = e;
    }
    
    public DelegheCittadiniService_Service() {
        super(__getWsdlLocation(), DELEGHECITTADINISERVICE_QNAME);
    }

    public DelegheCittadiniService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), DELEGHECITTADINISERVICE_QNAME, features);
    }

    public DelegheCittadiniService_Service(URL wsdlLocation) {
        super(wsdlLocation, DELEGHECITTADINISERVICE_QNAME);
    }

    public DelegheCittadiniService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DELEGHECITTADINISERVICE_QNAME, features);
    }
    public DelegheCittadiniService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }


    public DelegheCittadiniService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }
    /**
     * 
     * @return
     *     returns DelegheCittadiniService
     */
    @WebEndpoint(name = "DelegheCittadiniServiceImplPort")
    public DelegheCittadiniService getDelegheCittadiniServiceImplPort() {
        return super.getPort(new QName("http://deleghebe.csi.it/", "DelegheCittadiniServiceImplPort"), DelegheCittadiniService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DelegheCittadiniService
     */
    @WebEndpoint(name = "DelegheCittadiniServiceImplPort")
    public DelegheCittadiniService getDelegheCittadiniServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://deleghebe.csi.it/", "DelegheCittadiniServiceImplPort"), DelegheCittadiniService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DELEGHECITTADINISERVICE_EXCEPTION!= null) {
            throw DELEGHECITTADINISERVICE_EXCEPTION;
        }
        return DELEGHECITTADINISERVICE_WSDL_LOCATION;
    }
}
