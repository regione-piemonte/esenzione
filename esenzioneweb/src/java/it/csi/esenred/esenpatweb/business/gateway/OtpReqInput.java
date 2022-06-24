/**
 * OtpReqInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class OtpReqInput  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo;

    private it.csi.esenred.esenpatweb.business.gateway.Identity identity;

    private it.csi.esenred.esenpatweb.business.gateway.OtpCredentialsType otpCredentialsType;

    public OtpReqInput() {
    }

    public OtpReqInput(
           it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo,
           it.csi.esenred.esenpatweb.business.gateway.Identity identity,
           it.csi.esenred.esenpatweb.business.gateway.OtpCredentialsType otpCredentialsType) {
           this.callInfo = callInfo;
           this.identity = identity;
           this.otpCredentialsType = otpCredentialsType;
    }


    /**
     * Gets the callInfo value for this OtpReqInput.
     * 
     * @return callInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.CallInfo getCallInfo() {
        return callInfo;
    }


    /**
     * Sets the callInfo value for this OtpReqInput.
     * 
     * @param callInfo
     */
    public void setCallInfo(it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
        this.callInfo = callInfo;
    }


    /**
     * Gets the identity value for this OtpReqInput.
     * 
     * @return identity
     */
    public it.csi.esenred.esenpatweb.business.gateway.Identity getIdentity() {
        return identity;
    }


    /**
     * Sets the identity value for this OtpReqInput.
     * 
     * @param identity
     */
    public void setIdentity(it.csi.esenred.esenpatweb.business.gateway.Identity identity) {
        this.identity = identity;
    }


    /**
     * Gets the otpCredentialsType value for this OtpReqInput.
     * 
     * @return otpCredentialsType
     */
    public it.csi.esenred.esenpatweb.business.gateway.OtpCredentialsType getOtpCredentialsType() {
        return otpCredentialsType;
    }


    /**
     * Sets the otpCredentialsType value for this OtpReqInput.
     * 
     * @param otpCredentialsType
     */
    public void setOtpCredentialsType(it.csi.esenred.esenpatweb.business.gateway.OtpCredentialsType otpCredentialsType) {
        this.otpCredentialsType = otpCredentialsType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OtpReqInput)) return false;
        OtpReqInput other = (OtpReqInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.callInfo==null && other.getCallInfo()==null) || 
             (this.callInfo!=null &&
              this.callInfo.equals(other.getCallInfo()))) &&
            ((this.identity==null && other.getIdentity()==null) || 
             (this.identity!=null &&
              this.identity.equals(other.getIdentity()))) &&
            ((this.otpCredentialsType==null && other.getOtpCredentialsType()==null) || 
             (this.otpCredentialsType!=null &&
              this.otpCredentialsType.equals(other.getOtpCredentialsType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCallInfo() != null) {
            _hashCode += getCallInfo().hashCode();
        }
        if (getIdentity() != null) {
            _hashCode += getIdentity().hashCode();
        }
        if (getOtpCredentialsType() != null) {
            _hashCode += getOtpCredentialsType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OtpReqInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpReqInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "callInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "identity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otpCredentialsType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "otpCredentialsType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpCredentialsType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
