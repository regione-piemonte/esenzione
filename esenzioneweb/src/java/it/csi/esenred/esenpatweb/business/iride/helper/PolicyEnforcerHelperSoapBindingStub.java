/**
 * PolicyEnforcerHelperSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.iride.helper;

public class PolicyEnforcerHelperSoapBindingStub extends org.apache.axis.client.Stub implements it.csi.esenred.esenpatweb.business.iride.helper.PolicyEnforcerHelperService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isApplicationEsistente");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Application"), it.csi.esenred.esenpatweb.business.iride.helper.Application.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isApplicationEsistenteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isUseCaseEsistente");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UseCase"), it.csi.esenred.esenpatweb.business.iride.helper.UseCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isUseCaseEsistenteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findUseCasesForApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Application"), it.csi.esenred.esenpatweb.business.iride.helper.Application.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_UseCase"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.helper.UseCase[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findUseCasesForApplicationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findActorsForApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Application"), it.csi.esenred.esenpatweb.business.iride.helper.Application.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_Actor"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.helper.Actor[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findActorsForApplicationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findActorsForUseCase");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UseCase"), it.csi.esenred.esenpatweb.business.iride.helper.UseCase.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_Actor"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.helper.Actor[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findActorsForUseCaseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.NoSuchUseCaseException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchUseCaseException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findApplications");
        oper.setReturnType(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_Application"));
        oper.setReturnClass(it.csi.esenred.esenpatweb.business.iride.helper.Application[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "findApplicationsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isActorEsistente");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Actor"), it.csi.esenred.esenpatweb.business.iride.helper.Actor.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "isActorEsistenteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.SystemException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchApplicationException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "fault"),
                      "it.csi.esenred.esenpatweb.business.iride.helper.InternalException",
                      new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException"), 
                      true
                     ));
        _operations[6] = oper;

    }

    public PolicyEnforcerHelperSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PolicyEnforcerHelperSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PolicyEnforcerHelperSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_Actor");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.Actor[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Actor");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_Application");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.Application[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Application");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerHelper", "ArrayOf_tns1_UseCase");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.UseCase[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UseCase");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Actor");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.Actor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "Application");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.Application.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "AuthException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.AuthException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "BadIdentitaException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.BadIdentitaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "BadPasswordException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.BadPasswordException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "BadRuoloException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.BadRuoloException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "CertException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.CertException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "CertOutsideValidityException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.CertOutsideValidityException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "CertRevokedException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.CertRevokedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "CSIException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.CSIException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "IdentitaNonAutenticaException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.IdentitaNonAutenticaException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "IdProviderNotFoundException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.IdProviderNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InactiveAccountException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.InactiveAccountException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "InternalException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.InternalException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "MalformedIdTokenException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.MalformedIdTokenException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "MalformedUsernameException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.MalformedUsernameException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchActorException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.NoSuchActorException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchApplicationException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "NoSuchUseCaseException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.NoSuchUseCaseException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "PasswordExpiredException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.PasswordExpiredException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "SystemException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UnrecoverableException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UseCase");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.UseCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:PolicyEnforcerHelper", "UserException");
            cachedSerQNames.add(qName);
            cls = it.csi.esenred.esenpatweb.business.iride.helper.UserException.class;
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
.
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

    public boolean isApplicationEsistente(it.csi.esenred.esenpatweb.business.iride.helper.Application in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "isApplicationEsistente"));

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
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isUseCaseEsistente(it.csi.esenred.esenpatweb.business.iride.helper.UseCase in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "isUseCaseEsistente"));

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
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.helper.UseCase[] findUseCasesForApplication(it.csi.esenred.esenpatweb.business.iride.helper.Application in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findUseCasesForApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.helper.UseCase[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.helper.UseCase[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.helper.UseCase[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.helper.Actor[] findActorsForApplication(it.csi.esenred.esenpatweb.business.iride.helper.Application in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findActorsForApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.helper.Actor[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.helper.Actor[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.helper.Actor[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.helper.Actor[] findActorsForUseCase(it.csi.esenred.esenpatweb.business.iride.helper.UseCase in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchUseCaseException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findActorsForUseCase"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.helper.Actor[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.helper.Actor[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.helper.Actor[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.NoSuchUseCaseException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.NoSuchUseCaseException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.esenred.esenpatweb.business.iride.helper.Application[] findApplications() throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "findApplications"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.esenred.esenpatweb.business.iride.helper.Application[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.esenred.esenpatweb.business.iride.helper.Application[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.esenred.esenpatweb.business.iride.helper.Application[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isActorEsistente(it.csi.esenred.esenpatweb.business.iride.helper.Actor in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://interfaces.policy.iride2.csi.it", "isActorEsistente"));

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
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.SystemException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.esenred.esenpatweb.business.iride.helper.InternalException) {
              throw (it.csi.esenred.esenpatweb.business.iride.helper.InternalException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
