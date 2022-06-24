/**
 * Recipient.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Recipient  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Person person;

    private it.csi.esenred.esenpatweb.business.gateway.Organization organization;

    private it.csi.esenred.esenpatweb.business.gateway.Telecom telecom;

    public Recipient() {
    }

    public Recipient(
           it.csi.esenred.esenpatweb.business.gateway.Person person,
           it.csi.esenred.esenpatweb.business.gateway.Organization organization,
           it.csi.esenred.esenpatweb.business.gateway.Telecom telecom) {
           this.person = person;
           this.organization = organization;
           this.telecom = telecom;
    }


    /**
     * Gets the person value for this Recipient.
     * 
     * @return person
     */
    public it.csi.esenred.esenpatweb.business.gateway.Person getPerson() {
        return person;
    }


    /**
     * Sets the person value for this Recipient.
     * 
     * @param person
     */
    public void setPerson(it.csi.esenred.esenpatweb.business.gateway.Person person) {
        this.person = person;
    }


    /**
     * Gets the organization value for this Recipient.
     * 
     * @return organization
     */
    public it.csi.esenred.esenpatweb.business.gateway.Organization getOrganization() {
        return organization;
    }


    /**
     * Sets the organization value for this Recipient.
     * 
     * @param organization
     */
    public void setOrganization(it.csi.esenred.esenpatweb.business.gateway.Organization organization) {
        this.organization = organization;
    }


    /**
     * Gets the telecom value for this Recipient.
     * 
     * @return telecom
     */
    public it.csi.esenred.esenpatweb.business.gateway.Telecom getTelecom() {
        return telecom;
    }


    /**
     * Sets the telecom value for this Recipient.
     * 
     * @param telecom
     */
    public void setTelecom(it.csi.esenred.esenpatweb.business.gateway.Telecom telecom) {
        this.telecom = telecom;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Recipient)) return false;
        Recipient other = (Recipient) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.person==null && other.getPerson()==null) || 
             (this.person!=null &&
              this.person.equals(other.getPerson()))) &&
            ((this.organization==null && other.getOrganization()==null) || 
             (this.organization!=null &&
              this.organization.equals(other.getOrganization()))) &&
            ((this.telecom==null && other.getTelecom()==null) || 
             (this.telecom!=null &&
              this.telecom.equals(other.getTelecom())));
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
        if (getPerson() != null) {
            _hashCode += getPerson().hashCode();
        }
        if (getOrganization() != null) {
            _hashCode += getOrganization().hashCode();
        }
        if (getTelecom() != null) {
            _hashCode += getTelecom().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Recipient.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Recipient"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("person");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "person"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Person"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organization");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "organization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Organization"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telecom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "telecom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Telecom"));
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
