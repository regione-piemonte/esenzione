/**
 * AssigningAuthority.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class AssigningAuthority  implements java.io.Serializable {
    private java.lang.String universalId;  

    private java.lang.String universalIdType;  

    public AssigningAuthority() {
    }

    public AssigningAuthority(
           java.lang.String universalId,
           java.lang.String universalIdType) {
           this.universalId = universalId;
           this.universalIdType = universalIdType;
    }


    /**
     * Gets the universalId value for this AssigningAuthority.
     * 
     * @return universalId
     */
    public java.lang.String getUniversalId() {
        return universalId;
    }


    /**
     * Sets the universalId value for this AssigningAuthority.
     * 
     * @param universalId
     */
    public void setUniversalId(java.lang.String universalId) {
        this.universalId = universalId;
    }


    /**
     * Gets the universalIdType value for this AssigningAuthority.
     * 
     * @return universalIdType
     */
    public java.lang.String getUniversalIdType() {
        return universalIdType;
    }


    /**
     * Sets the universalIdType value for this AssigningAuthority.
     * 
     * @param universalIdType
     */
    public void setUniversalIdType(java.lang.String universalIdType) {
        this.universalIdType = universalIdType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssigningAuthority)) return false;
        AssigningAuthority other = (AssigningAuthority) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.universalId==null && other.getUniversalId()==null) || 
             (this.universalId!=null &&
              this.universalId.equals(other.getUniversalId()))) &&
            ((this.universalIdType==null && other.getUniversalIdType()==null) || 
             (this.universalIdType!=null &&
              this.universalIdType.equals(other.getUniversalIdType())));
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
        if (getUniversalId() != null) {
            _hashCode += getUniversalId().hashCode();
        }
        if (getUniversalIdType() != null) {
            _hashCode += getUniversalIdType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssigningAuthority.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssigningAuthority"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("universalId");
        attrField.setXmlName(new javax.xml.namespace.QName("", "universalId"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("universalIdType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "universalIdType"));
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
