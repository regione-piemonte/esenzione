/**
 * GateFireSrvcSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class GateFireSrvcSoapBindingSkeleton implements it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType, org.apache.axis.wsdl.Skeleton {
    private it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"), it.csi.esenred.esenpatweb.business.gateway.Identity.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"), it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "assertionIdentity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"), it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESConArchiviaz", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESConArchiviaz"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESConArchiviaz") == null) {
            _myOperations.put("firmaPAdESConArchiviaz", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESConArchiviaz")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("ping", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "pingResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "ping"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("ping") == null) {
            _myOperations.put("ping", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("ping")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "repositoryInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "repositoryInput"), it.csi.esenred.esenpatweb.business.gateway.RepositoryInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("archivia", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "result"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "archivia"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("archivia") == null) {
            _myOperations.put("archivia", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("archivia")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"), it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"), it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "assertionIdentity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"), it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESAutomaticaConArchiviaz", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomaticaConArchiviaz"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESAutomaticaConArchiviaz") == null) {
            _myOperations.put("firmaPAdESAutomaticaConArchiviaz", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESAutomaticaConArchiviaz")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"), it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESAutomatica", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomatica"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESAutomatica") == null) {
            _myOperations.put("firmaPAdESAutomatica", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESAutomatica")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"), it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESMassivaRemota", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaRemota"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESMassivaRemota") == null) {
            _myOperations.put("firmaPAdESMassivaRemota", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESMassivaRemota")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"), it.csi.esenred.esenpatweb.business.gateway.Identity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdES", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdES"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdES") == null) {
            _myOperations.put("firmaPAdES", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdES")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "markInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markInput"), it.csi.esenred.esenpatweb.business.gateway.MarkInput.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("marcaTemporale", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "marcaTemporale"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("marcaTemporale") == null) {
            _myOperations.put("marcaTemporale", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("marcaTemporale")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("verificaFirma", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signVerifyResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaFirma"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("verificaFirma") == null) {
            _myOperations.put("verificaFirma", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("verificaFirma")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mark"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "originalFile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("verificaMarcaDetached", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markVerifyResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarcaDetached"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("verificaMarcaDetached") == null) {
            _myOperations.put("verificaMarcaDetached", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("verificaMarcaDetached")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"), it.csi.esenred.esenpatweb.business.gateway.Identity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESMassiva", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassiva"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESMassiva") == null) {
            _myOperations.put("firmaPAdESMassiva", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESMassiva")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"), it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESRemota", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemota"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESRemota") == null) {
            _myOperations.put("firmaPAdESRemota", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESRemota")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionInput"), it.csi.esenred.esenpatweb.business.gateway.SessionInput.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("openSession", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionIdResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "openSession"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("openSession") == null) {
            _myOperations.put("openSession", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("openSession")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "otpReqInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpReqInput"), it.csi.esenred.esenpatweb.business.gateway.OtpReqInput.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("richiestaOtp", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "richiestaOtp"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("richiestaOtp") == null) {
            _myOperations.put("richiestaOtp", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("richiestaOtp")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionInput"), it.csi.esenred.esenpatweb.business.gateway.SessionInput.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("closeSession", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "result"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "closeSession"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("closeSession") == null) {
            _myOperations.put("closeSession", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("closeSession")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"), it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"), it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "assertionIdentity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"), it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESRemotaConArchiviaz", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemotaConArchiviaz"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESRemotaConArchiviaz") == null) {
            _myOperations.put("firmaPAdESRemotaConArchiviaz", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESRemotaConArchiviaz")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"), it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("firmaPAdESMassivaAutomatica", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaAutomatica"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("firmaPAdESMassivaAutomatica") == null) {
            _myOperations.put("firmaPAdESMassivaAutomatica", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("firmaPAdESMassivaAutomatica")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("verificaMarca", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markVerifyResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarca"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("verificaMarca") == null) {
            _myOperations.put("verificaMarca", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("verificaMarca")).add(_oper);
    }

    public GateFireSrvcSoapBindingSkeleton() {
        this.impl = new it.csi.esenred.esenpatweb.business.gateway.GateFireSrvcSoapBindingImpl();
    }

    public GateFireSrvcSoapBindingSkeleton(it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType impl) {
        this.impl = impl;
    }
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESConArchiviaz(attachment, padesInput, identity, metadata, assertionIdentity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.PingResult ping(java.lang.String user, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.PingResult ret = impl.ping(user, callInfo);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Result archivia(it.csi.esenred.esenpatweb.business.gateway.RepositoryInput repositoryInput, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.Result ret = impl.archivia(repositoryInput, callInfo);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESAutomaticaConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESAutomaticaConArchiviaz(attachment, padesInput, identity, metadata, assertionIdentity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESAutomatica(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESAutomatica(attachment, padesInput, identity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassivaRemota(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESMassivaRemota(attachment, padesInput, identity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdES(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdES(attachment, padesInput, identity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult marcaTemporale(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.MarkInput markInput) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.marcaTemporale(attachment, markInput);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult verificaFirma(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult ret = impl.verificaFirma(attachment, callInfo);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult verificaMarcaDetached(it.csi.esenred.esenpatweb.business.gateway.Attachment mark, it.csi.esenred.esenpatweb.business.gateway.Attachment originalFile, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult ret = impl.verificaMarcaDetached(mark, originalFile, callInfo);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassiva(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESMassiva(attachment, padesInput, identity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESRemota(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESRemota(attachment, padesInput, identity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.SessionIdResult openSession(it.csi.esenred.esenpatweb.business.gateway.SessionInput sessionInput) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.SessionIdResult ret = impl.openSession(sessionInput);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.OtpResult richiestaOtp(it.csi.esenred.esenpatweb.business.gateway.OtpReqInput otpReqInput) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.OtpResult ret = impl.richiestaOtp(otpReqInput);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Result closeSession(java.lang.String sessionId, it.csi.esenred.esenpatweb.business.gateway.SessionInput sessionInput) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.Result ret = impl.closeSession(sessionId, sessionInput);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESRemotaConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESRemotaConArchiviaz(attachment, padesInput, identity, metadata, assertionIdentity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassivaAutomatica(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.FileResult ret = impl.firmaPAdESMassivaAutomatica(attachment, padesInput, identity);
        return ret;
    }

    public it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult verificaMarca(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException
    {
        it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult ret = impl.verificaMarca(attachment, callInfo);
        return ret;
    }

}
