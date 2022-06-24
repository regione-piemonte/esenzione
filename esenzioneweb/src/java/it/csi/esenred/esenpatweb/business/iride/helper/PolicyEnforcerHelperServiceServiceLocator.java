/**
 * PolicyEnforcerHelperServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.iride.helper;

public class PolicyEnforcerHelperServiceServiceLocator extends org.apache.axis.client.Service implements it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperServiceService {

    public PolicyEnforcerHelperServiceServiceLocator() {
    }


    public PolicyEnforcerHelperServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PolicyEnforcerHelperServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PolicyEnforcerHelper
    private java.lang.String PolicyEnforcerHelper_address = "http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper";

    public java.lang.String getPolicyEnforcerHelperAddress() {
        return PolicyEnforcerHelper_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PolicyEnforcerHelperWSDDServiceName = "PolicyEnforcerHelper";

    public java.lang.String getPolicyEnforcerHelperWSDDServiceName() {
        return PolicyEnforcerHelperWSDDServiceName;
    }

    public void setPolicyEnforcerHelperWSDDServiceName(java.lang.String name) {
        PolicyEnforcerHelperWSDDServiceName = name;
    }

    public it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperService getPolicyEnforcerHelper() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PolicyEnforcerHelper_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPolicyEnforcerHelper(endpoint);
    }

    public it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperService getPolicyEnforcerHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperSoapBindingStub _stub = new it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperSoapBindingStub(portAddress, this);
            _stub.setPortName(getPolicyEnforcerHelperWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPolicyEnforcerHelperEndpointAddress(java.lang.String address) {
        PolicyEnforcerHelper_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperService.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperSoapBindingStub _stub = new it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperSoapBindingStub(new java.net.URL(PolicyEnforcerHelper_address), this);
                _stub.setPortName(getPolicyEnforcerHelperWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PolicyEnforcerHelper".equals(inputPortName)) {
            return getPolicyEnforcerHelper();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "PolicyEnforcerHelperServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "PolicyEnforcerHelper"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PolicyEnforcerHelper".equals(portName)) {
            setPolicyEnforcerHelperEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
