/**
 * AutoSignIdentity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class AutoSignIdentity  extends it.csi.esenred.esenpatweb.business.gateway.Identity  implements java.io.Serializable {
    private java.lang.String delegatedDomain;

    private java.lang.String delegatedPassword;

    private java.lang.String delegatedUser;

    public AutoSignIdentity() {
    }

    public AutoSignIdentity(
           java.lang.String otp,
           java.lang.String password,
           java.lang.String user,
           java.lang.String delegatedDomain,
           java.lang.String delegatedPassword,
           java.lang.String delegatedUser) {
        super(
            otp,
            password,
            user);
        this.delegatedDomain = delegatedDomain;
        this.delegatedPassword = delegatedPassword;
        this.delegatedUser = delegatedUser;
    }


    /**
     * Gets the delegatedDomain value for this AutoSignIdentity.
     * 
     * @return delegatedDomain
     */
    public java.lang.String getDelegatedDomain() {
        return delegatedDomain;
    }


    /**
     * Sets the delegatedDomain value for this AutoSignIdentity.
     * 
     * @param delegatedDomain
     */
    public void setDelegatedDomain(java.lang.String delegatedDomain) {
        this.delegatedDomain = delegatedDomain;
    }


    /**
     * Gets the delegatedPassword value for this AutoSignIdentity.
     * 
     * @return delegatedPassword
     */
    public java.lang.String getDelegatedPassword() {
        return delegatedPassword;
    }


    /**
     * Sets the delegatedPassword value for this AutoSignIdentity.
     * 
     * @param delegatedPassword
     */
    public void setDelegatedPassword(java.lang.String delegatedPassword) {
        this.delegatedPassword = delegatedPassword;
    }


    /**
     * Gets the delegatedUser value for this AutoSignIdentity.
     * 
     * @return delegatedUser
     */
    public java.lang.String getDelegatedUser() {
        return delegatedUser;
    }


    /**
     * Sets the delegatedUser value for this AutoSignIdentity.
     * 
     * @param delegatedUser
     */
    public void setDelegatedUser(java.lang.String delegatedUser) {
        this.delegatedUser = delegatedUser;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutoSignIdentity)) return false;
        AutoSignIdentity other = (AutoSignIdentity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.delegatedDomain==null && other.getDelegatedDomain()==null) || 
             (this.delegatedDomain!=null &&
              this.delegatedDomain.equals(other.getDelegatedDomain()))) &&
            ((this.delegatedPassword==null && other.getDelegatedPassword()==null) || 
             (this.delegatedPassword!=null &&
              this.delegatedPassword.equals(other.getDelegatedPassword()))) &&
            ((this.delegatedUser==null && other.getDelegatedUser()==null) || 
             (this.delegatedUser!=null &&
              this.delegatedUser.equals(other.getDelegatedUser())));
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
        if (getDelegatedDomain() != null) {
            _hashCode += getDelegatedDomain().hashCode();
        }
        if (getDelegatedPassword() != null) {
            _hashCode += getDelegatedPassword().hashCode();
        }
        if (getDelegatedUser() != null) {
            _hashCode += getDelegatedUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutoSignIdentity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "autoSignIdentity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegatedDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegatedDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegatedPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegatedPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegatedUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegatedUser"));
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
