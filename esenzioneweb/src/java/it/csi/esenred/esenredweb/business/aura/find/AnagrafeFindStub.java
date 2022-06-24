/**
 * AnagrafeFindStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.find;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;

/*
 *  AnagrafeFindStub java implementation
 */
public class AnagrafeFindStub extends org.apache.axis2.client.Stub {
    private static int counter = 0;
    protected org.apache.axis2.description.AxisOperation[] _operations;

    //hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();
    private javax.xml.namespace.QName[] opNameArray = null;

    /**
     *Constructor that takes in a configContext
     */
    public AnagrafeFindStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public AnagrafeFindStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,
                _service);

        _serviceClient.getOptions()
                      .setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
    }

    /**
     * Default Constructor
     */
    public AnagrafeFindStub(
        org.apache.axis2.context.ConfigurationContext configurationContext)
        throws org.apache.axis2.AxisFault {
        this(configurationContext,
            "https://xyz/sanitxens/AURA.WS.AnagrafeFind.cls");
    }

    /**
     * Default Constructor
     */
    public AnagrafeFindStub() throws org.apache.axis2.AxisFault {
        this(
            "https://xyz/sanitxens/AURA.WS.AnagrafeFind.cls");
    }

    /**
     * Constructor taking the target endpoint
     */
    public AnagrafeFindStub(java.lang.String targetEndpoint)
        throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    private static synchronized java.lang.String getUniqueSuffix() {
        // reset the counter if it is greater than 99999
        if (counter > 99999) {
            counter = 0;
        }

        counter = counter + 1;

        return java.lang.Long.toString(java.lang.System.currentTimeMillis()) +
        "_" + counter;
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("AnagrafeFind" +
                getUniqueSuffix());
        addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                "findProfiliAnagrafici"));
        _service.addOperation(__operation);

        _operations[0] = __operation;
    }

    //populates the faults
    private void populateFaults() {
    }

    public static void addSecurityToHeader(org.apache.axiom.soap.SOAPHeader header) {

		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace namespaceWSSE = factory.createOMNamespace(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse");
		OMElement element = factory.createOMElement("Security", namespaceWSSE);
		OMAttribute attribute = factory.createOMAttribute("mustUnderstand", null, "1");
		element.addAttribute(attribute);
		header.addChild(element);
		OMElement element2 = factory.createOMElement("UsernameToken", namespaceWSSE);
		OMNamespace namespaceWSU = factory.createOMNamespace(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu");
		attribute = factory.createOMAttribute("Id", namespaceWSU, "UsernameToken-1");
		element2.addAttribute(attribute);
		element.addChild(element2);
		OMElement element3 = factory.createOMElement("Username", namespaceWSSE);
		element3.setText(IntegrationClientImpl.getInstance().getUsername());
		OMElement element4 = factory.createOMElement("Password", namespaceWSSE);
		attribute = factory.createOMAttribute("Type", null,
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		element4.setText(IntegrationClientImpl.getInstance().getPassword());
		element2.addChild(element3);
		element2.addChild(element4);
	}
    
    /**
     * Auto generated method signature
     *
     * @see it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFind#findProfiliAnagrafici
     * @param findProfiliAnagrafici12
     */
    public it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse findProfiliAnagrafici(
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici findProfiliAnagrafici12)
        throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
        
       

        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
            _operationClient.getOptions()
                            .setAction("http://AnagrafeFind.central.services.auraws.aura.csi.it/AURA.WS.AnagrafeFind.FindProfiliAnagrafici");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                                                        .getSoapVersionURI()),
                    findProfiliAnagrafici12,
                    optimizeContent(
                        new javax.xml.namespace.QName(
                            "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                            "findProfiliAnagrafici")),
                    new javax.xml.namespace.QName(
                        "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                        "FindProfiliAnagrafici"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);
            
            addSecurityToHeader(_messageContext.getEnvelope().getHeader());

            
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(_returnEnv.getBody()
                                                       .getFirstElement(),
                    it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse.class);

            return (it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse) object;
        } catch (org.apache.axis2.AxisFault f) {
            org.apache.axiom.om.OMElement faultElt = f.getDetail();

            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(
                            new org.apache.axis2.client.FaultMapKey(
                                faultElt.getQName(), "FindProfiliAnagrafici"))) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(
                                    faultElt.getQName(), "FindProfiliAnagrafici"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(java.lang.String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());

                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(
                                    faultElt.getQName(), "FindProfiliAnagrafici"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender()
                               .cleanup(_messageContext);
            }
        }
    }

    /**
     * Auto generated method signature for Asynchronous Invocations
     *
     * @see it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFind#startfindProfiliAnagrafici
     * @param findProfiliAnagrafici12
     */
    public void startfindProfiliAnagrafici(
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici findProfiliAnagrafici12,
        final it.csi.esenred.esenredweb.business.aura.find.AnagrafeFindCallbackHandler callback)
        throws java.rmi.RemoteException {
        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
        _operationClient.getOptions()
                        .setAction("http://AnagrafeFind.central.services.auraws.aura.csi.it/AURA.WS.AnagrafeFind.FindProfiliAnagrafici");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
            "&");

        
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.
        env = toEnvelope(getFactory(_operationClient.getOptions()
                                                    .getSoapVersionURI()),
                findProfiliAnagrafici12,
                optimizeContent(
                    new javax.xml.namespace.QName(
                        "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                        "findProfiliAnagrafici")),
                new javax.xml.namespace.QName(
                    "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                    "FindProfiliAnagrafici"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                public void onMessage(
                    org.apache.axis2.context.MessageContext resultContext) {
                    try {
                        org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                        java.lang.Object object = fromOM(resultEnv.getBody()
                                                                  .getFirstElement(),
                                it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse.class);
                        callback.receiveResultfindProfiliAnagrafici((it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse) object);
                    } catch (org.apache.axis2.AxisFault e) {
                        callback.receiveErrorfindProfiliAnagrafici(e);
                    }
                }

                public void onError(java.lang.Exception error) {
                    if (error instanceof org.apache.axis2.AxisFault) {
                        org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                        org.apache.axiom.om.OMElement faultElt = f.getDetail();

                        if (faultElt != null) {
                            if (faultExceptionNameMap.containsKey(
                                        new org.apache.axis2.client.FaultMapKey(
                                            faultElt.getQName(),
                                            "FindProfiliAnagrafici"))) {
                                //make the fault by reflection
                                try {
                                    java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(
                                                faultElt.getQName(),
                                                "FindProfiliAnagrafici"));
                                    java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                                    java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(java.lang.String.class);
                                    java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());

                                    //message class
                                    java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(
                                                faultElt.getQName(),
                                                "FindProfiliAnagrafici"));
                                    java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                                    java.lang.Object messageObject = fromOM(faultElt,
                                            messageClass);
                                    java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                            new java.lang.Class[] { messageClass });
                                    m.invoke(ex,
                                        new java.lang.Object[] { messageObject });

                                    callback.receiveErrorfindProfiliAnagrafici(new java.rmi.RemoteException(
                                            ex.getMessage(), ex));
                                } catch (java.lang.ClassCastException e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                } catch (java.lang.ClassNotFoundException e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                } catch (java.lang.NoSuchMethodException e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                } catch (java.lang.reflect.InvocationTargetException e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                } catch (java.lang.IllegalAccessException e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                } catch (java.lang.InstantiationException e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                } catch (org.apache.axis2.AxisFault e) {
                                    
                                    callback.receiveErrorfindProfiliAnagrafici(f);
                                }
                            } else {
                                callback.receiveErrorfindProfiliAnagrafici(f);
                            }
                        } else {
                            callback.receiveErrorfindProfiliAnagrafici(f);
                        }
                    } else {
                        callback.receiveErrorfindProfiliAnagrafici(error);
                    }
                }

                public void onFault(
                    org.apache.axis2.context.MessageContext faultContext) {
                    org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                    onError(fault);
                }

                public void onComplete() {
                    try {
                        _messageContext.getTransportOut().getSender()
                                       .cleanup(_messageContext);
                    } catch (org.apache.axis2.AxisFault axisFault) {
                        callback.receiveErrorfindProfiliAnagrafici(axisFault);
                    }
                }
            });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;

        if ((_operations[0].getMessageReceiver() == null) &&
                _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[0].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);
    }

    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        if (opNameArray == null) {
            return false;
        }

        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }

        return false;
    }

    //https://xyz/sanitxens/AURA.WS.AnagrafeFind.cls
    private org.apache.axiom.om.OMElement toOM(
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /* methods to provide back word compatibility */

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici.class.equals(
                        type)) {
                return it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse.class.equals(
                        type)) {
                return it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }
}
