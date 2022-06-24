/**
 * PatientInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class PatientInfo  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Identifiable[] id;

    private it.csi.esenred.esenpatweb.business.gateway.XcnName[] name;

    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp birthTime;

    private java.lang.String gender;

    private it.csi.esenred.esenpatweb.business.gateway.Address[] address;

    private java.lang.Object other;

    public PatientInfo() {
    }

    public PatientInfo(
           it.csi.esenred.esenpatweb.business.gateway.Identifiable[] id,
           it.csi.esenred.esenpatweb.business.gateway.XcnName[] name,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp birthTime,
           java.lang.String gender,
           it.csi.esenred.esenpatweb.business.gateway.Address[] address,
           java.lang.Object other) {
           this.id = id;
           this.name = name;
           this.birthTime = birthTime;
           this.gender = gender;
           this.address = address;
           this.other = other;
    }


    /**
     * Gets the id value for this PatientInfo.
     * 
     * @return id
     */
    public it.csi.esenred.esenpatweb.business.gateway.Identifiable[] getId() {
        return id;
    }


    /**
     * Sets the id value for this PatientInfo.
     * 
     * @param id
     */
    public void setId(it.csi.esenred.esenpatweb.business.gateway.Identifiable[] id) {
        this.id = id;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Identifiable getId(int i) {
        return this.id[i];
    }

    public void setId(int i, it.csi.esenred.esenpatweb.business.gateway.Identifiable _value) {
        this.id[i] = _value;
    }


    /**
     * Gets the name value for this PatientInfo.
     * 
     * @return name
     */
    public it.csi.esenred.esenpatweb.business.gateway.XcnName[] getName() {
        return name;
    }


    /**
     * Sets the name value for this PatientInfo.
     * 
     * @param name
     */
    public void setName(it.csi.esenred.esenpatweb.business.gateway.XcnName[] name) {
        this.name = name;
    }

    public it.csi.esenred.esenpatweb.business.gateway.XcnName getName(int i) {
        return this.name[i];
    }

    public void setName(int i, it.csi.esenred.esenpatweb.business.gateway.XcnName _value) {
        this.name[i] = _value;
    }


    /**
     * Gets the birthTime value for this PatientInfo.
     * 
     * @return birthTime
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getBirthTime() {
        return birthTime;
    }


    /**
     * Sets the birthTime value for this PatientInfo.
     * 
     * @param birthTime
     */
    public void setBirthTime(it.csi.esenred.esenpatweb.business.gateway.TimeStamp birthTime) {
        this.birthTime = birthTime;
    }


    /**
     * Gets the gender value for this PatientInfo.
     * 
     * @return gender
     */
    public java.lang.String getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this PatientInfo.
     * 
     * @param gender
     */
    public void setGender(java.lang.String gender) {
        this.gender = gender;
    }


    /**
     * Gets the address value for this PatientInfo.
     * 
     * @return address
     */
    public it.csi.esenred.esenpatweb.business.gateway.Address[] getAddress() {
        return address;
    }


    /**
     * Sets the address value for this PatientInfo.
     * 
     * @param address
     */
    public void setAddress(it.csi.esenred.esenpatweb.business.gateway.Address[] address) {
        this.address = address;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Address getAddress(int i) {
        return this.address[i];
    }

    public void setAddress(int i, it.csi.esenred.esenpatweb.business.gateway.Address _value) {
        this.address[i] = _value;
    }


    /**
     * Gets the other value for this PatientInfo.
     * 
     * @return other
     */
    public java.lang.Object getOther() {
        return other;
    }


    /**
     * Sets the other value for this PatientInfo.
     * 
     * @param other
     */
    public void setOther(java.lang.Object other) {
        this.other = other;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PatientInfo)) return false;
        PatientInfo other = (PatientInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              java.util.Arrays.equals(this.id, other.getId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              java.util.Arrays.equals(this.name, other.getName()))) &&
            ((this.birthTime==null && other.getBirthTime()==null) || 
             (this.birthTime!=null &&
              this.birthTime.equals(other.getBirthTime()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              java.util.Arrays.equals(this.address, other.getAddress()))) &&
            ((this.other==null && other.getOther()==null) || 
             (this.other!=null &&
              this.other.equals(other.getOther())));
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
        if (getId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBirthTime() != null) {
            _hashCode += getBirthTime().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getAddress() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddress());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddress(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOther() != null) {
            _hashCode += getOther().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PatientInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "PatientInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Identifiable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "xcnName"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Timestamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Address"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other");
        elemField.setXmlName(new javax.xml.namespace.QName("", "other"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
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
