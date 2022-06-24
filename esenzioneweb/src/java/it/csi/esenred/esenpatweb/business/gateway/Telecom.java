/**
 * Telecom.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Telecom  implements java.io.Serializable {
    private java.lang.String use;

    private java.lang.String type;

    private java.lang.String email;

    private java.lang.Long countryCode;

    private java.lang.Long areaCityCode;

    private java.lang.Long localNumber;

    private java.lang.Long extension;

    private java.lang.String unformattedPhoneNumber;

    public Telecom() {
    }

    public Telecom(
           java.lang.String use,
           java.lang.String type,
           java.lang.String email,
           java.lang.Long countryCode,
           java.lang.Long areaCityCode,
           java.lang.Long localNumber,
           java.lang.Long extension,
           java.lang.String unformattedPhoneNumber) {
           this.use = use;
           this.type = type;
           this.email = email;
           this.countryCode = countryCode;
           this.areaCityCode = areaCityCode;
           this.localNumber = localNumber;
           this.extension = extension;
           this.unformattedPhoneNumber = unformattedPhoneNumber;
    }


    /**
     * Gets the use value for this Telecom.
     * 
     * @return use
     */
    public java.lang.String getUse() {
        return use;
    }


    /**
     * Sets the use value for this Telecom.
     * 
     * @param use
     */
    public void setUse(java.lang.String use) {
        this.use = use;
    }


    /**
     * Gets the type value for this Telecom.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this Telecom.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the email value for this Telecom.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Telecom.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the countryCode value for this Telecom.
     * 
     * @return countryCode
     */
    public java.lang.Long getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this Telecom.
     * 
     * @param countryCode
     */
    public void setCountryCode(java.lang.Long countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the areaCityCode value for this Telecom.
     * 
     * @return areaCityCode
     */
    public java.lang.Long getAreaCityCode() {
        return areaCityCode;
    }


    /**
     * Sets the areaCityCode value for this Telecom.
     * 
     * @param areaCityCode
     */
    public void setAreaCityCode(java.lang.Long areaCityCode) {
        this.areaCityCode = areaCityCode;
    }


    /**
     * Gets the localNumber value for this Telecom.
     * 
     * @return localNumber
     */
    public java.lang.Long getLocalNumber() {
        return localNumber;
    }


    /**
     * Sets the localNumber value for this Telecom.
     * 
     * @param localNumber
     */
    public void setLocalNumber(java.lang.Long localNumber) {
        this.localNumber = localNumber;
    }


    /**
     * Gets the extension value for this Telecom.
     * 
     * @return extension
     */
    public java.lang.Long getExtension() {
        return extension;
    }


    /**
     * Sets the extension value for this Telecom.
     * 
     * @param extension
     */
    public void setExtension(java.lang.Long extension) {
        this.extension = extension;
    }


    /**
     * Gets the unformattedPhoneNumber value for this Telecom.
     * 
     * @return unformattedPhoneNumber
     */
    public java.lang.String getUnformattedPhoneNumber() {
        return unformattedPhoneNumber;
    }


    /**
     * Sets the unformattedPhoneNumber value for this Telecom.
     * 
     * @param unformattedPhoneNumber
     */
    public void setUnformattedPhoneNumber(java.lang.String unformattedPhoneNumber) {
        this.unformattedPhoneNumber = unformattedPhoneNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Telecom)) return false;
        Telecom other = (Telecom) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.use==null && other.getUse()==null) || 
             (this.use!=null &&
              this.use.equals(other.getUse()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode()))) &&
            ((this.areaCityCode==null && other.getAreaCityCode()==null) || 
             (this.areaCityCode!=null &&
              this.areaCityCode.equals(other.getAreaCityCode()))) &&
            ((this.localNumber==null && other.getLocalNumber()==null) || 
             (this.localNumber!=null &&
              this.localNumber.equals(other.getLocalNumber()))) &&
            ((this.extension==null && other.getExtension()==null) || 
             (this.extension!=null &&
              this.extension.equals(other.getExtension()))) &&
            ((this.unformattedPhoneNumber==null && other.getUnformattedPhoneNumber()==null) || 
             (this.unformattedPhoneNumber!=null &&
              this.unformattedPhoneNumber.equals(other.getUnformattedPhoneNumber())));
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
        if (getUse() != null) {
            _hashCode += getUse().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        if (getAreaCityCode() != null) {
            _hashCode += getAreaCityCode().hashCode();
        }
        if (getLocalNumber() != null) {
            _hashCode += getLocalNumber().hashCode();
        }
        if (getExtension() != null) {
            _hashCode += getExtension().hashCode();
        }
        if (getUnformattedPhoneNumber() != null) {
            _hashCode += getUnformattedPhoneNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Telecom.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Telecom"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "use"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "countryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "areaCityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "localNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "extension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unformattedPhoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "unformattedPhoneNumber"));
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
