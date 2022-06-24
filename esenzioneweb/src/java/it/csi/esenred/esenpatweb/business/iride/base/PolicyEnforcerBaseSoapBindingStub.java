/**
 * PolicyEnforcerBaseSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.iride.base;

public class PolicyEnforcerBaseSoapBindingStub extends org.apache.axis.client.Stub implements it.csi.esenred.esenpatweb.business.iride.base.PolicyEnforcerBaseService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("identificaUserPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Identita.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "identificaUserPasswordReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "MalformedUsernameException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.AuthException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "AuthException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdProviderNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("identificaCertificato");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Identita.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "identificaCertificatoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.CertRevokedException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "CertRevokedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.CertOutsideValidityException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "CertOutsideValidityException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdProviderNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("identificaUserPasswordPIN");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Identita.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "identificaUserPasswordPINReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "MalformedUsernameException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.AuthException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "AuthException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdProviderNotFoundException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isPersonaAutorizzataInUseCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UseCase"), it.csi.esenred.esenpatweb.business.iride.base.UseCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isPersonaAutorizzataInUseCaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchUseCaseException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findUseCasesForPersonaInApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Application"), it.csi.esenred.esenpatweb.business.iride.base.Application.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_UseCase"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.UseCase[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findUseCasesForPersonaInApplicationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isIdentitaAutentica");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isIdentitaAutenticaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoPersonaInUseCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UseCase"), it.csi.esenred.esenpatweb.business.iride.base.UseCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInfoPersonaInUseCaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchUseCaseException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findRuoliForPersonaInUseCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UseCase"), it.csi.esenred.esenpatweb.business.iride.base.UseCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_Ruolo"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Ruolo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findRuoliForPersonaInUseCaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchUseCaseException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findRuoliForPersonaInApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Application"), it.csi.esenred.esenpatweb.business.iride.base.Application.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_Ruolo"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Ruolo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findRuoliForPersonaInApplicationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoPersonaSchema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Ruolo"), it.csi.esenred.esenpatweb.business.iride.base.Ruolo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInfoPersonaSchemaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "BadRuoloException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findActorsForPersonaInApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Application"), it.csi.esenred.esenpatweb.business.iride.base.Application.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_Actor"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Actor[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findActorsForPersonaInApplicationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findActorsForPersonaInUseCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UseCase"), it.csi.esenred.esenpatweb.business.iride.base.UseCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_Actor"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.base.Actor[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findActorsForPersonaInUseCaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchUseCaseException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isPersonaInRuolo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita"), it.csi.esenred.esenpatweb.business.iride.base.Identita.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Ruolo"), it.csi.esenred.esenpatweb.business.iride.base.Ruolo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isPersonaInRuoloReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "BadRuoloException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.base.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException"), 
                      true
                     ));
        _operations[12] = oper;

    }

    public PolicyEnforcerBaseSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PolicyEnforcerBaseSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PolicyEnforcerBaseSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_Actor");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.Actor[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Actor");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_Ruolo");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.Ruolo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Ruolo");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "ArrayOf_tns1_UseCase");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.UseCase[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UseCase");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Actor");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.Actor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Application");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.Application.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "AuthException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.AuthException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "BadIdentitaException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.BadIdentitaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "BadPasswordException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.BadPasswordException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "BadRuoloException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "CertException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.CertException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "CertOutsideValidityException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.CertOutsideValidityException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "CertRevokedException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.CertRevokedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "CSIException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.CSIException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Identita");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.Identita.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdentitaNonAutenticaException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "IdProviderNotFoundException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InactiveAccountException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.InactiveAccountException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "InternalException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.InternalException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "MalformedIdTokenException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.MalformedIdTokenException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "MalformedUsernameException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchActorException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.NoSuchActorException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchApplicationException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "NoSuchUseCaseException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "PasswordExpiredException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.PasswordExpiredException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "Ruolo");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.Ruolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "SystemException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UnrecoverableException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UseCase");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.UseCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerBase", "UserException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.base.UserException.class;
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
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
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

    public it.csi.esenred.esenpatweb.business.iride.base.Identita identificaUserPassword(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException, it.csi.esenred.esenpatweb.business.iride.base.AuthException, it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "identificaUserPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Identita) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Identita) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Identita.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.AuthException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.AuthException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.Identita identificaCertificato(byte[] in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.CertRevokedException, it.csi.esenred.esenpatweb.business.iride.base.CertOutsideValidityException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "identificaCertificato"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Identita) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Identita) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Identita.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.CertRevokedException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.CertRevokedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.CertOutsideValidityException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.CertOutsideValidityException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.Identita identificaUserPasswordPIN(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException, it.csi.esenred.esenpatweb.business.iride.base.AuthException, it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "identificaUserPasswordPIN"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Identita) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Identita) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Identita.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.MalformedUsernameException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.AuthException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.AuthException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdProviderNotFoundException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isPersonaAutorizzataInUseCase(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.UseCase in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "isPersonaAutorizzataInUseCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.UseCase[] findUseCasesForPersonaInApplication(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.Application in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findUseCasesForPersonaInApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.UseCase[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.UseCase[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.UseCase[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isIdentitaAutentica(it.csi.esenred.esenpatweb.business.iride.base.Identita in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "isIdentitaAutentica"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getInfoPersonaInUseCase(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.UseCase in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "getInfoPersonaInUseCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.Ruolo[] findRuoliForPersonaInUseCase(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.UseCase in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findRuoliForPersonaInUseCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Ruolo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Ruolo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Ruolo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.Ruolo[] findRuoliForPersonaInApplication(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.Application in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findRuoliForPersonaInApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Ruolo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Ruolo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Ruolo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getInfoPersonaSchema(it.csi.esenred.esenpatweb.business.iride.base.Ruolo in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "getInfoPersonaSchema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.Actor[] findActorsForPersonaInApplication(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.Application in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findActorsForPersonaInApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Actor[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Actor[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Actor[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.base.Actor[] findActorsForPersonaInUseCase(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.UseCase in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException, it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findActorsForPersonaInUseCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.base.Actor[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.base.Actor[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.base.Actor[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchUseCaseException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isPersonaInRuolo(it.csi.esenred.esenpatweb.business.iride.base.Identita in0, it.csi.esenred.esenpatweb.business.iride.base.Ruolo in1) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException, it.csi.esenred.esenpatweb.business.iride.base.SystemException, it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException, it.csi.esenred.esenpatweb.business.iride.base.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "isPersonaInRuolo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.IdentitaNonAutenticaException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.BadRuoloException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.base.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.base.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
