/**
 * PadesInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class PadesInput  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo;

    private it.csi.esenred.esenpatweb.business.gateway.MarkIdentity markIdentity;

    private boolean requiredMark;

    private it.csi.esenred.esenpatweb.business.gateway.SignLayout signLayout;

    public PadesInput() {
    }

    public PadesInput(
           it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo,
           it.csi.esenred.esenpatweb.business.gateway.MarkIdentity markIdentity,
           boolean requiredMark,
           it.csi.esenred.esenpatweb.business.gateway.SignLayout signLayout) {
           this.callInfo = callInfo;
           this.markIdentity = markIdentity;
           this.requiredMark = requiredMark;
           this.signLayout = signLayout;
    }


    /**
     * Gets the callInfo value for this PadesInput.
     * 
     * @return callInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.CallInfo getCallInfo() {
        return callInfo;
    }


    /**
     * Sets the callInfo value for this PadesInput.
     * 
     * @param callInfo
     */
    public void setCallInfo(it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
        this.callInfo = callInfo;
    }


    /**
     * Gets the markIdentity value for this PadesInput.
     * 
     * @return markIdentity
     */
    public it.csi.esenred.esenpatweb.business.gateway.MarkIdentity getMarkIdentity() {
        return markIdentity;
    }


    /**
     * Sets the markIdentity value for this PadesInput.
     * 
     * @param markIdentity
     */
    public void setMarkIdentity(it.csi.esenred.esenpatweb.business.gateway.MarkIdentity markIdentity) {
        this.markIdentity = markIdentity;
    }


    /**
     * Gets the requiredMark value for this PadesInput.
     * 
     * @return requiredMark
     */
    public boolean isRequiredMark() {
        return requiredMark;
    }


    /**
     * Sets the requiredMark value for this PadesInput.
     * 
     * @param requiredMark
     */
    public void setRequiredMark(boolean requiredMark) {
        this.requiredMark = requiredMark;
    }


    /**
     * Gets the signLayout value for this PadesInput.
     * 
     * @return signLayout
     */
    public it.csi.esenred.esenpatweb.business.gateway.SignLayout getSignLayout() {
        return signLayout;
    }


    /**
     * Sets the signLayout value for this PadesInput.
     * 
     * @param signLayout
     */
    public void setSignLayout(it.csi.esenred.esenpatweb.business.gateway.SignLayout signLayout) {
        this.signLayout = signLayout;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PadesInput)) return false;
        PadesInput other = (PadesInput) obj;
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
            this.requiredMark == other.isRequiredMark() &&
            ((this.signLayout==null && other.getSignLayout()==null) || 
             (this.signLayout!=null &&
              this.signLayout.equals(other.getSignLayout())));
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
        _hashCode += (isRequiredMark() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSignLayout() != null) {
            _hashCode += getSignLayout().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PadesInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"));
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
        elemField.setFieldName("requiredMark");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requiredMark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signLayout");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signLayout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signLayout"));
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
