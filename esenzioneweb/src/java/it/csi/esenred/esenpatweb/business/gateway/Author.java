/**
 * Author.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Author  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Person authorPerson;

    private it.csi.esenred.esenpatweb.business.gateway.Organization[] authorInstitution;

    private it.csi.esenred.esenpatweb.business.gateway.Identifiable[] authorSpecialty;

    private it.csi.esenred.esenpatweb.business.gateway.Identifiable[] authorRole;

    private it.csi.esenred.esenpatweb.business.gateway.Telecom[] authorTelecom;

    public Author() {
    }

    public Author(
           it.csi.esenred.esenpatweb.business.gateway.Person authorPerson,
           it.csi.esenred.esenpatweb.business.gateway.Organization[] authorInstitution,
           it.csi.esenred.esenpatweb.business.gateway.Identifiable[] authorSpecialty,
           it.csi.esenred.esenpatweb.business.gateway.Identifiable[] authorRole,
           it.csi.esenred.esenpatweb.business.gateway.Telecom[] authorTelecom) {
           this.authorPerson = authorPerson;
           this.authorInstitution = authorInstitution;
           this.authorSpecialty = authorSpecialty;
           this.authorRole = authorRole;
           this.authorTelecom = authorTelecom;
    }


    /**
     * Gets the authorPerson value for this Author.
     * 
     * @return authorPerson
     */
    public it.csi.esenred.esenpatweb.business.gateway.Person getAuthorPerson() {
        return authorPerson;
    }


    /**
     * Sets the authorPerson value for this Author.
     * 
     * @param authorPerson
     */
    public void setAuthorPerson(it.csi.esenred.esenpatweb.business.gateway.Person authorPerson) {
        this.authorPerson = authorPerson;
    }


    /**
     * Gets the authorInstitution value for this Author.
     * 
     * @return authorInstitution
     */
    public it.csi.esenred.esenpatweb.business.gateway.Organization[] getAuthorInstitution() {
        return authorInstitution;
    }


    /**
     * Sets the authorInstitution value for this Author.
     * 
     * @param authorInstitution
     */
    public void setAuthorInstitution(it.csi.esenred.esenpatweb.business.gateway.Organization[] authorInstitution) {
        this.authorInstitution = authorInstitution;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Organization getAuthorInstitution(int i) {
        return this.authorInstitution[i];
    }

    public void setAuthorInstitution(int i, it.csi.esenred.esenpatweb.business.gateway.Organization _value) {
        this.authorInstitution[i] = _value;
    }


    /**
     * Gets the authorSpecialty value for this Author.
     * 
     * @return authorSpecialty
     */
    public it.csi.esenred.esenpatweb.business.gateway.Identifiable[] getAuthorSpecialty() {
        return authorSpecialty;
    }


    /**
     * Sets the authorSpecialty value for this Author.
     * 
     * @param authorSpecialty
     */
    public void setAuthorSpecialty(it.csi.esenred.esenpatweb.business.gateway.Identifiable[] authorSpecialty) {
        this.authorSpecialty = authorSpecialty;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Identifiable getAuthorSpecialty(int i) {
        return this.authorSpecialty[i];
    }

    public void setAuthorSpecialty(int i, it.csi.esenred.esenpatweb.business.gateway.Identifiable _value) {
        this.authorSpecialty[i] = _value;
    }


    /**
     * Gets the authorRole value for this Author.
     * 
     * @return authorRole
     */
    public it.csi.esenred.esenpatweb.business.gateway.Identifiable[] getAuthorRole() {
        return authorRole;
    }


    /**
     * Sets the authorRole value for this Author.
     * 
     * @param authorRole
     */
    public void setAuthorRole(it.csi.esenred.esenpatweb.business.gateway.Identifiable[] authorRole) {
        this.authorRole = authorRole;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Identifiable getAuthorRole(int i) {
        return this.authorRole[i];
    }

    public void setAuthorRole(int i, it.csi.esenred.esenpatweb.business.gateway.Identifiable _value) {
        this.authorRole[i] = _value;
    }


    /**
     * Gets the authorTelecom value for this Author.
     * 
     * @return authorTelecom
     */
    public it.csi.esenred.esenpatweb.business.gateway.Telecom[] getAuthorTelecom() {
        return authorTelecom;
    }


    /**
     * Sets the authorTelecom value for this Author.
     * 
     * @param authorTelecom
     */
    public void setAuthorTelecom(it.csi.esenred.esenpatweb.business.gateway.Telecom[] authorTelecom) {
        this.authorTelecom = authorTelecom;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Telecom getAuthorTelecom(int i) {
        return this.authorTelecom[i];
    }

    public void setAuthorTelecom(int i, it.csi.esenred.esenpatweb.business.gateway.Telecom _value) {
        this.authorTelecom[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Author)) return false;
        Author other = (Author) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authorPerson==null && other.getAuthorPerson()==null) || 
             (this.authorPerson!=null &&
              this.authorPerson.equals(other.getAuthorPerson()))) &&
            ((this.authorInstitution==null && other.getAuthorInstitution()==null) || 
             (this.authorInstitution!=null &&
              java.util.Arrays.equals(this.authorInstitution, other.getAuthorInstitution()))) &&
            ((this.authorSpecialty==null && other.getAuthorSpecialty()==null) || 
             (this.authorSpecialty!=null &&
              java.util.Arrays.equals(this.authorSpecialty, other.getAuthorSpecialty()))) &&
            ((this.authorRole==null && other.getAuthorRole()==null) || 
             (this.authorRole!=null &&
              java.util.Arrays.equals(this.authorRole, other.getAuthorRole()))) &&
            ((this.authorTelecom==null && other.getAuthorTelecom()==null) || 
             (this.authorTelecom!=null &&
              java.util.Arrays.equals(this.authorTelecom, other.getAuthorTelecom())));
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
        if (getAuthorPerson() != null) {
            _hashCode += getAuthorPerson().hashCode();
        }
        if (getAuthorInstitution() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthorInstitution());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthorInstitution(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthorSpecialty() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthorSpecialty());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthorSpecialty(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthorRole() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthorRole());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthorRole(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthorTelecom() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthorTelecom());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthorTelecom(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Author.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Author"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorPerson");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "authorPerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Person"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorInstitution");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "authorInstitution"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Organization"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorSpecialty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "authorSpecialty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Identifiable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorRole");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "authorRole"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Identifiable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorTelecom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "authorTelecom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Telecom"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
