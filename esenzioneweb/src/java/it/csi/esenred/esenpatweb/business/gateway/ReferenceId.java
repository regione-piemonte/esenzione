/**
 * ReferenceId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class ReferenceId  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.CXiAssigningAuthority assigningAuthority;

    private java.lang.String id;  

    private java.lang.String idTypeCode;  

    public ReferenceId() {
    }

    public ReferenceId(
           it.csi.esenred.esenpatweb.business.gateway.CXiAssigningAuthority assigningAuthority,
           java.lang.String id,
           java.lang.String idTypeCode) {
           this.assigningAuthority = assigningAuthority;
           this.id = id;
           this.idTypeCode = idTypeCode;
    }


    /**
     * Gets the assigningAuthority value for this ReferenceId.
     * 
     * @return assigningAuthority
     */
    public it.csi.esenred.esenpatweb.business.gateway.CXiAssigningAuthority getAssigningAuthority() {
        return assigningAuthority;
    }


    /**
     * Sets the assigningAuthority value for this ReferenceId.
     * 
     * @param assigningAuthority
     */
    public void setAssigningAuthority(it.csi.esenred.esenpatweb.business.gateway.CXiAssigningAuthority assigningAuthority) {
        this.assigningAuthority = assigningAuthority;
    }


    /**
     * Gets the id value for this ReferenceId.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ReferenceId.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the idTypeCode value for this ReferenceId.
     * 
     * @return idTypeCode
     */
    public java.lang.String getIdTypeCode() {
        return idTypeCode;
    }


    /**
     * Sets the idTypeCode value for this ReferenceId.
     * 
     * @param idTypeCode
     */
    public void setIdTypeCode(java.lang.String idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReferenceId)) return false;
        ReferenceId other = (ReferenceId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assigningAuthority==null && other.getAssigningAuthority()==null) || 
             (this.assigningAuthority!=null &&
              this.assigningAuthority.equals(other.getAssigningAuthority()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idTypeCode==null && other.getIdTypeCode()==null) || 
             (this.idTypeCode!=null &&
              this.idTypeCode.equals(other.getIdTypeCode())));
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
        if (getAssigningAuthority() != null) {
            _hashCode += getAssigningAuthority().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdTypeCode() != null) {
            _hashCode += getIdTypeCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReferenceId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "ReferenceId"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("idTypeCode");
        attrField.setXmlName(new javax.xml.namespace.QName("", "idTypeCode"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assigningAuthority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "assigningAuthority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "CXiAssigningAuthority"));
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
