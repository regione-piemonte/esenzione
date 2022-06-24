/**
 * GateFireSrvc_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class GateFireSrvc_ServiceLocator extends org.apache.axis.client.Service implements it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_Service {

    public GateFireSrvc_ServiceLocator() {
    }


    public GateFireSrvc_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GateFireSrvc_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for gateFireSrvcPort
    private java.lang.String gateFireSrvcPort_address = "https://tst-srv-gatefire.sanita.csi.it/gateway/WS/gatefire";

    public java.lang.String getgateFireSrvcPortAddress() {
        return gateFireSrvcPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String gateFireSrvcPortWSDDServiceName = "gateFireSrvcPort";

    public java.lang.String getgateFireSrvcPortWSDDServiceName() {
        return gateFireSrvcPortWSDDServiceName;
    }

    public void setgateFireSrvcPortWSDDServiceName(java.lang.String name) {
        gateFireSrvcPortWSDDServiceName = name;
    }

    public it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType getgateFireSrvcPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(gateFireSrvcPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getgateFireSrvcPort(endpoint);
    }

    public it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType getgateFireSrvcPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.esenred.esenpatweb.business.gateway.GateFireSrvcSoapBindingStub _stub = new it.csi.esenred.esenpatweb.business.gateway.GateFireSrvcSoapBindingStub(portAddress, this);
            _stub.setPortName(getgateFireSrvcPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setgateFireSrvcPortEndpointAddress(java.lang.String address) {
        gateFireSrvcPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.esenred.esenpatweb.business.gateway.GateFireSrvcSoapBindingStub _stub = new it.csi.esenred.esenpatweb.business.gateway.GateFireSrvcSoapBindingStub(new java.net.URL(gateFireSrvcPort_address), this);
                _stub.setPortName(getgateFireSrvcPortWSDDServiceName());
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
        if ("gateFireSrvcPort".equals(inputPortName)) {
            return getgateFireSrvcPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "gateFireSrvc");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "gateFireSrvcPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("gateFireSrvcPort".equals(portName)) {
            setgateFireSrvcPortEndpointAddress(address);
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
