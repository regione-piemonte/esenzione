/**
 * RevocationInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class RevocationInfo  implements java.io.Serializable {
    private java.util.Calendar expired;

    private java.util.Calendar revocationDate;

    private java.util.Calendar thisUpdate;

    public RevocationInfo() {
    }

    public RevocationInfo(
           java.util.Calendar expired,
           java.util.Calendar revocationDate,
           java.util.Calendar thisUpdate) {
           this.expired = expired;
           this.revocationDate = revocationDate;
           this.thisUpdate = thisUpdate;
    }


    /**
     * Gets the expired value for this RevocationInfo.
     * 
     * @return expired
     */
    public java.util.Calendar getExpired() {
        return expired;
    }


    /**
     * Sets the expired value for this RevocationInfo.
     * 
     * @param expired
     */
    public void setExpired(java.util.Calendar expired) {
        this.expired = expired;
    }


    /**
     * Gets the revocationDate value for this RevocationInfo.
     * 
     * @return revocationDate
     */
    public java.util.Calendar getRevocationDate() {
        return revocationDate;
    }


    /**
     * Sets the revocationDate value for this RevocationInfo.
     * 
     * @param revocationDate
     */
    public void setRevocationDate(java.util.Calendar revocationDate) {
        this.revocationDate = revocationDate;
    }


    /**
     * Gets the thisUpdate value for this RevocationInfo.
     * 
     * @return thisUpdate
     */
    public java.util.Calendar getThisUpdate() {
        return thisUpdate;
    }


    /**
     * Sets the thisUpdate value for this RevocationInfo.
     * 
     * @param thisUpdate
     */
    public void setThisUpdate(java.util.Calendar thisUpdate) {
        this.thisUpdate = thisUpdate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RevocationInfo)) return false;
        RevocationInfo other = (RevocationInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.expired==null && other.getExpired()==null) || 
             (this.expired!=null &&
              this.expired.equals(other.getExpired()))) &&
            ((this.revocationDate==null && other.getRevocationDate()==null) || 
             (this.revocationDate!=null &&
              this.revocationDate.equals(other.getRevocationDate()))) &&
            ((this.thisUpdate==null && other.getThisUpdate()==null) || 
             (this.thisUpdate!=null &&
              this.thisUpdate.equals(other.getThisUpdate())));
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
        if (getExpired() != null) {
            _hashCode += getExpired().hashCode();
        }
        if (getRevocationDate() != null) {
            _hashCode += getRevocationDate().hashCode();
        }
        if (getThisUpdate() != null) {
            _hashCode += getThisUpdate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RevocationInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "revocationInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revocationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "revocationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thisUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "thisUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
