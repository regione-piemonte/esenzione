/**
 * Organization.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Organization  implements java.io.Serializable {
    private java.lang.String idNumber;

    private it.csi.esenred.esenpatweb.business.gateway.AssigningAuthority assigningAuthority;

    private java.lang.String name;

    public Organization() {
    }

    public Organization(
           java.lang.String idNumber,
           it.csi.esenred.esenpatweb.business.gateway.AssigningAuthority assigningAuthority,
           java.lang.String name) {
           this.idNumber = idNumber;
           this.assigningAuthority = assigningAuthority;
           this.name = name;
    }


    /**
     * Gets the idNumber value for this Organization.
     * 
     * @return idNumber
     */
    public java.lang.String getIdNumber() {
        return idNumber;
    }


    /**
     * Sets the idNumber value for this Organization.
     * 
     * @param idNumber
     */
    public void setIdNumber(java.lang.String idNumber) {
        this.idNumber = idNumber;
    }


    /**
     * Gets the assigningAuthority value for this Organization.
     * 
     * @return assigningAuthority
     */
    public it.csi.esenred.esenpatweb.business.gateway.AssigningAuthority getAssigningAuthority() {
        return assigningAuthority;
    }


    /**
     * Sets the assigningAuthority value for this Organization.
     * 
     * @param assigningAuthority
     */
    public void setAssigningAuthority(it.csi.esenred.esenpatweb.business.gateway.AssigningAuthority assigningAuthority) {
        this.assigningAuthority = assigningAuthority;
    }


    /**
     * Gets the name value for this Organization.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Organization.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Organization)) return false;
        Organization other = (Organization) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idNumber==null && other.getIdNumber()==null) || 
             (this.idNumber!=null &&
              this.idNumber.equals(other.getIdNumber()))) &&
            ((this.assigningAuthority==null && other.getAssigningAuthority()==null) || 
             (this.assigningAuthority!=null &&
              this.assigningAuthority.equals(other.getAssigningAuthority()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName())));
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
        if (getIdNumber() != null) {
            _hashCode += getIdNumber().hashCode();
        }
        if (getAssigningAuthority() != null) {
            _hashCode += getAssigningAuthority().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Organization.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Organization"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "idNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assigningAuthority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "assigningAuthority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssigningAuthority"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "name"));
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
