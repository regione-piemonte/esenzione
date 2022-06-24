/**
 * CertPolicy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class CertPolicy  implements java.io.Serializable {
    private java.lang.String cpText;

    private java.lang.String cpsUri;

    public CertPolicy() {
    }

    public CertPolicy(
           java.lang.String cpText,
           java.lang.String cpsUri) {
           this.cpText = cpText;
           this.cpsUri = cpsUri;
    }


    /**
     * Gets the cpText value for this CertPolicy.
     * 
     * @return cpText
     */
    public java.lang.String getCpText() {
        return cpText;
    }


    /**
     * Sets the cpText value for this CertPolicy.
     * 
     * @param cpText
     */
    public void setCpText(java.lang.String cpText) {
        this.cpText = cpText;
    }


    /**
     * Gets the cpsUri value for this CertPolicy.
     * 
     * @return cpsUri
     */
    public java.lang.String getCpsUri() {
        return cpsUri;
    }


    /**
     * Sets the cpsUri value for this CertPolicy.
     * 
     * @param cpsUri
     */
    public void setCpsUri(java.lang.String cpsUri) {
        this.cpsUri = cpsUri;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CertPolicy)) return false;
        CertPolicy other = (CertPolicy) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cpText==null && other.getCpText()==null) || 
             (this.cpText!=null &&
              this.cpText.equals(other.getCpText()))) &&
            ((this.cpsUri==null && other.getCpsUri()==null) || 
             (this.cpsUri!=null &&
              this.cpsUri.equals(other.getCpsUri())));
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
        if (getCpText() != null) {
            _hashCode += getCpText().hashCode();
        }
        if (getCpsUri() != null) {
            _hashCode += getCpsUri().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CertPolicy.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certPolicy"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpText");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpsUri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpsUri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
