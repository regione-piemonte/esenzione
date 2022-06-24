/**
 * GateFireSrvcSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class GateFireSrvcSoapBindingStub extends org.apache.axis.client.Stub implements it.csi.esenred.esenpatweb.business.gateway.GateFireSrvc_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[18];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESConArchiviaz");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"), it.csi.esenred.esenpatweb.business.gateway.Identity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"), it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "assertionIdentity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"), it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ping");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "pingResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.PingResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("archivia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "repositoryInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "repositoryInput"), it.csi.esenred.esenpatweb.business.gateway.RepositoryInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "result"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.Result.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESAutomaticaConArchiviaz");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"), it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"), it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "assertionIdentity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"), it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESAutomatica");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"), it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESMassivaRemota");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"), it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdES");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"), it.csi.esenred.esenpatweb.business.gateway.Identity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("marcaTemporale");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "markInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markInput"), it.csi.esenred.esenpatweb.business.gateway.MarkInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificaFirma");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signVerifyResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificaMarcaDetached");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mark"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "originalFile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markVerifyResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESMassiva");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"), it.csi.esenred.esenpatweb.business.gateway.Identity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESRemota");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"), it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("openSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionInput"), it.csi.esenred.esenpatweb.business.gateway.SessionInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionIdResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.SessionIdResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("richiestaOtp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "otpReqInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpReqInput"), it.csi.esenred.esenpatweb.business.gateway.OtpReqInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.OtpResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("closeSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "sessionInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionInput"), it.csi.esenred.esenpatweb.business.gateway.SessionInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "result"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.Result.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESRemotaConArchiviaz");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"), it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"), it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "assertionIdentity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"), it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("firmaPAdESMassivaAutomatica");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "padesInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"), it.csi.esenred.esenpatweb.business.gateway.PadesInput.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "identity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"), it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificaMarca");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "attachment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"), it.csi.esenred.esenpatweb.business.gateway.Attachment.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "callInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"), it.csi.esenred.esenpatweb.business.gateway.CallInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markVerifyResult"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

    }

    public GateFireSrvcSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public GateFireSrvcSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public GateFireSrvcSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "archivia");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Archivia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "archiviaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.ArchiviaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Attachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "baseResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.BaseResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.CallInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certificate");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Certificate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certPolicy");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.CertPolicy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "closeSession");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.CloseSession.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "closeSessionResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.CloseSessionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "fileResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FileResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdES");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomatica");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESAutomatica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomaticaConArchiviaz");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESAutomaticaConArchiviaz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomaticaConArchiviazResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESAutomaticaConArchiviazResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomaticaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESAutomaticaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESConArchiviaz");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESConArchiviaz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESConArchiviazResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESConArchiviazResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassiva");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESMassiva.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaAutomatica");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESMassivaAutomatica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaAutomaticaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESMassivaAutomaticaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaRemota");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESMassivaRemota.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaRemotaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESMassivaRemotaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESMassivaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemota");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESRemota.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemotaConArchiviaz");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESRemotaConArchiviaz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemotaConArchiviazResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESRemotaConArchiviazResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemotaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESRemotaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.FirmaPAdESResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Identity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "issuer");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Issuer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.ItiMetadata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "marcaTemporale");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.MarcaTemporale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "marcaTemporaleResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.MarcaTemporaleResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markIdentity");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.MarkIdentity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markInput");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.MarkInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markType");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.MarkType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markVerifyResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "openSession");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.OpenSession.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "openSessionResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.OpenSessionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpCredentialsType");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.OtpCredentialsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpReqInput");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.OtpReqInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.OtpResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.PadesInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "ping");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Ping.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "pingResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.PingResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "pingResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.PingResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "repositoryInput");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.RepositoryInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "result");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "revocationInfo");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.RevocationInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "richiestaOtp");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.RichiestaOtp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "richiestaOtpResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.RichiestaOtpResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionIdResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SessionIdResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionInput");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SessionInput.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signer");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Signer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SignIdentity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signLayout");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SignLayout.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signType");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SignType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signVerifyResult");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "subject");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Subject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "timeStamp");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.TimeStamp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaFirma");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.VerificaFirma.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaFirmaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.VerificaFirmaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarca");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.VerificaMarca.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarcaDetached");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.VerificaMarcaDetached.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarcaDetachedResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.VerificaMarcaDetachedResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarcaResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.VerificaMarcaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", ">>Association>extraMetadata>entry");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "value");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", ">Association>extraMetadata");
            cachedSerQNames.add(qName);
            cls = java.lang.String[][].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", ">>Association>extraMetadata>entry");
            qName2 = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "entry");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Address");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Address.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssigningAuthority");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.AssigningAuthority.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Association");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Association.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssociationLabel");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.AssociationLabel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssociationType");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.AssociationType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Author");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Author.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AvailabilityStatus");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Code.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "CXiAssigningAuthority");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.CXiAssigningAuthority.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentAvailability");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.DocumentAvailability.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentEntry");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.DocumentEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentEntryType");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.DocumentEntryType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Folder");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Folder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Identifiable");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Identifiable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "IdentifiedObject");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.IdentifiedObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "LocalizedString");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.LocalizedString.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Name");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Name.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Organization");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Organization.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "PatientInfo");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.PatientInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Person");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Person.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Precision");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Precision.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Recipient");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Recipient.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "ReferenceId");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.ReferenceId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "SubmissionSet");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.SubmissionSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Telecom");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Telecom.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Timestamp");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.TimeStamp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Version");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.Version.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "xcnName");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.gateway.XcnName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }

            synchronized (this) {
                if (firstCall()) {
                    
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESConArchiviaz"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity, metadata, assertionIdentity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.PingResult ping(java.lang.String user, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "ping"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, callInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.PingResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.PingResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.PingResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.Result archivia(it.csi.esenred.esenpatweb.business.gateway.RepositoryInput repositoryInput, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "archivia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {repositoryInput, callInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.Result) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.Result) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.Result.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESAutomaticaConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomaticaConArchiviaz"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity, metadata, assertionIdentity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESAutomatica(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESAutomatica"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassivaRemota(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaRemota"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdES(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdES"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult marcaTemporale(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.MarkInput markInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "marcaTemporale"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, markInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult verificaFirma(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaFirma"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, callInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult verificaMarcaDetached(it.csi.esenred.esenpatweb.business.gateway.Attachment mark, it.csi.esenred.esenpatweb.business.gateway.Attachment originalFile, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarcaDetached"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {mark, originalFile, callInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassiva(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassiva"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESRemota(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemota"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.SessionIdResult openSession(it.csi.esenred.esenpatweb.business.gateway.SessionInput sessionInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "openSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.SessionIdResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.SessionIdResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.SessionIdResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.OtpResult richiestaOtp(it.csi.esenred.esenpatweb.business.gateway.OtpReqInput otpReqInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "richiestaOtp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {otpReqInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.OtpResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.OtpResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.OtpResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.Result closeSession(java.lang.String sessionId, it.csi.esenred.esenpatweb.business.gateway.SessionInput sessionInput) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "closeSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sessionId, sessionInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.Result) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.Result) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.Result.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESRemotaConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESRemotaConArchiviaz"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity, metadata, assertionIdentity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassivaAutomatica(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaAutomatica"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, padesInput, identity});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.FileResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.FileResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult verificaMarca(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarca"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {attachment, callInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
