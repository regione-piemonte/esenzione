/**
 * CXiAssigningAuthority.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class CXiAssigningAuthority  extends it.csi.esenred.esenpatweb.business.gateway.AssigningAuthority  implements java.io.Serializable {
    private java.lang.String namespaceId;  

    public CXiAssigningAuthority() {
    }

    public CXiAssigningAuthority(
           java.lang.String universalId,
           java.lang.String universalIdType,
           java.lang.String namespaceId) {
        super(
            universalId,
            universalIdType);
        this.namespaceId = namespaceId;
    }


    /**
     * Gets the namespaceId value for this CXiAssigningAuthority.
     * 
     * @return namespaceId
     */
    public java.lang.String getNamespaceId() {
        return namespaceId;
    }


    /**
     * Sets the namespaceId value for this CXiAssigningAuthority.
     * 
     * @param namespaceId
     */
    public void setNamespaceId(java.lang.String namespaceId) {
        this.namespaceId = namespaceId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CXiAssigningAuthority)) return false;
        CXiAssigningAuthority other = (CXiAssigningAuthority) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.namespaceId==null && other.getNamespaceId()==null) || 
             (this.namespaceId!=null &&
              this.namespaceId.equals(other.getNamespaceId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getNamespaceId() != null) {
            _hashCode += getNamespaceId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CXiAssigningAuthority.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "CXiAssigningAuthority"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("namespaceId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "namespaceId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
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
