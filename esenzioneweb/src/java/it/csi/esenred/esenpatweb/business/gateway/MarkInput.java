/**
 * MarkInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class MarkInput  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo;

    private it.csi.esenred.esenpatweb.business.gateway.MarkIdentity markIdentity;

    private it.csi.esenred.esenpatweb.business.gateway.MarkType markType;

    public MarkInput() {
    }

    public MarkInput(
           it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo,
           it.csi.esenred.esenpatweb.business.gateway.MarkIdentity markIdentity,
           it.csi.esenred.esenpatweb.business.gateway.MarkType markType) {
           this.callInfo = callInfo;
           this.markIdentity = markIdentity;
           this.markType = markType;
    }


    /**
     * Gets the callInfo value for this MarkInput.
     * 
     * @return callInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.CallInfo getCallInfo() {
        return callInfo;
    }


    /**
     * Sets the callInfo value for this MarkInput.
     * 
     * @param callInfo
     */
    public void setCallInfo(it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
        this.callInfo = callInfo;
    }


    /**
     * Gets the markIdentity value for this MarkInput.
     * 
     * @return markIdentity
     */
    public it.csi.esenred.esenpatweb.business.gateway.MarkIdentity getMarkIdentity() {
        return markIdentity;
    }


    /**
     * Sets the markIdentity value for this MarkInput.
     * 
     * @param markIdentity
     */
    public void setMarkIdentity(it.csi.esenred.esenpatweb.business.gateway.MarkIdentity markIdentity) {
        this.markIdentity = markIdentity;
    }


    /**
     * Gets the markType value for this MarkInput.
     * 
     * @return markType
     */
    public it.csi.esenred.esenpatweb.business.gateway.MarkType getMarkType() {
        return markType;
    }


    /**
     * Sets the markType value for this MarkInput.
     * 
     * @param markType
     */
    public void setMarkType(it.csi.esenred.esenpatweb.business.gateway.MarkType markType) {
        this.markType = markType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarkInput)) return false;
        MarkInput other = (MarkInput) obj;
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
            ((this.markIdentity==null && other.getMarkIdentity()==null) || 
             (this.markIdentity!=null &&
              this.markIdentity.equals(other.getMarkIdentity()))) &&
            ((this.markType==null && other.getMarkType()==null) || 
             (this.markType!=null &&
              this.markType.equals(other.getMarkType())));
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
        if (getMarkIdentity() != null) {
            _hashCode += getMarkIdentity().hashCode();
        }
        if (getMarkType() != null) {
            _hashCode += getMarkType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarkInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "callInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("markIdentity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "markIdentity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markIdentity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("markType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "markType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markType"));
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
