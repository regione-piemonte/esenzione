/**
 * SessionInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class SessionInput  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo;

    private it.csi.esenred.esenpatweb.business.gateway.SignIdentity signIdentity;

    private it.csi.esenred.esenpatweb.business.gateway.SignType signType;

    public SessionInput() {
    }

    public SessionInput(
           it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo,
           it.csi.esenred.esenpatweb.business.gateway.SignIdentity signIdentity,
           it.csi.esenred.esenpatweb.business.gateway.SignType signType) {
           this.callInfo = callInfo;
           this.signIdentity = signIdentity;
           this.signType = signType;
    }


    /**
     * Gets the callInfo value for this SessionInput.
     * 
     * @return callInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.CallInfo getCallInfo() {
        return callInfo;
    }


    /**
     * Sets the callInfo value for this SessionInput.
     * 
     * @param callInfo
     */
    public void setCallInfo(it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
        this.callInfo = callInfo;
    }


    /**
     * Gets the signIdentity value for this SessionInput.
     * 
     * @return signIdentity
     */
    public it.csi.esenred.esenpatweb.business.gateway.SignIdentity getSignIdentity() {
        return signIdentity;
    }


    /**
     * Sets the signIdentity value for this SessionInput.
     * 
     * @param signIdentity
     */
    public void setSignIdentity(it.csi.esenred.esenpatweb.business.gateway.SignIdentity signIdentity) {
        this.signIdentity = signIdentity;
    }


    /**
     * Gets the signType value for this SessionInput.
     * 
     * @return signType
     */
    public it.csi.esenred.esenpatweb.business.gateway.SignType getSignType() {
        return signType;
    }


    /**
     * Sets the signType value for this SessionInput.
     * 
     * @param signType
     */
    public void setSignType(it.csi.esenred.esenpatweb.business.gateway.SignType signType) {
        this.signType = signType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SessionInput)) return false;
        SessionInput other = (SessionInput) obj;
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
            ((this.signIdentity==null && other.getSignIdentity()==null) || 
             (this.signIdentity!=null &&
              this.signIdentity.equals(other.getSignIdentity()))) &&
            ((this.signType==null && other.getSignType()==null) || 
             (this.signType!=null &&
              this.signType.equals(other.getSignType())));
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
        if (getSignIdentity() != null) {
            _hashCode += getSignIdentity().hashCode();
        }
        if (getSignType() != null) {
            _hashCode += getSignType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SessionInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "sessionInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "callInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signIdentity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signIdentity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signType"));
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
