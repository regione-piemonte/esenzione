/**
 * AnagrafeFindMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.find;


/**
 *  AnagrafeFindMessageReceiverInOut message receiver
 */
public class AnagrafeFindMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            AnagrafeFindSkeleton skel = (AnagrafeFindSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("findProfiliAnagrafici".equals(methodName)) {
                    it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse findProfiliAnagraficiResponse5 =
                        null;
                    it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici wrappedParam =
                        (it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici) fromOM(msgContext.getEnvelope()
                                                                                                                  .getBody()
                                                                                                                  .getFirstElement(),
                            it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagrafici.class);

                    findProfiliAnagraficiResponse5 = skel.findProfiliAnagrafici(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            findProfiliAnagraficiResponse5, false,
                            new javax.xml.namespace.QName(
                                "http://AnagrafeFind.central.services.auraws.aura.csi.it",
                                "FindProfiliAnagraficiResponse"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
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
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse wrapFindProfiliAnagrafici() {
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse wrappedElement =
            new it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse();

        return wrappedElement;
    }

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

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
