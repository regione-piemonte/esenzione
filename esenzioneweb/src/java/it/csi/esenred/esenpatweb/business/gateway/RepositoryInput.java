/**
 * RepositoryInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class RepositoryInput  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Attachment attachment;

    private it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity identity;

    private it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata;

    public RepositoryInput() {
    }

    public RepositoryInput(
           it.csi.esenred.esenpatweb.business.gateway.Attachment attachment,
           it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity identity,
           it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata) {
           this.attachment = attachment;
           this.identity = identity;
           this.metadata = metadata;
    }


    /**
     * Gets the attachment value for this RepositoryInput.
     * 
     * @return attachment
     */
    public it.csi.esenred.esenpatweb.business.gateway.Attachment getAttachment() {
        return attachment;
    }


    /**
     * Sets the attachment value for this RepositoryInput.
     * 
     * @param attachment
     */
    public void setAttachment(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment) {
        this.attachment = attachment;
    }


    /**
     * Gets the identity value for this RepositoryInput.
     * 
     * @return identity
     */
    public it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity getIdentity() {
        return identity;
    }


    /**
     * Sets the identity value for this RepositoryInput.
     * 
     * @param identity
     */
    public void setIdentity(it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity identity) {
        this.identity = identity;
    }


    /**
     * Gets the metadata value for this RepositoryInput.
     * 
     * @return metadata
     */
    public it.csi.esenred.esenpatweb.business.gateway.ItiMetadata getMetadata() {
        return metadata;
    }


    /**
     * Sets the metadata value for this RepositoryInput.
     * 
     * @param metadata
     */
    public void setMetadata(it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata) {
        this.metadata = metadata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepositoryInput)) return false;
        RepositoryInput other = (RepositoryInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attachment==null && other.getAttachment()==null) || 
             (this.attachment!=null &&
              this.attachment.equals(other.getAttachment()))) &&
            ((this.identity==null && other.getIdentity()==null) || 
             (this.identity!=null &&
              this.identity.equals(other.getIdentity()))) &&
            ((this.metadata==null && other.getMetadata()==null) || 
             (this.metadata!=null &&
              this.metadata.equals(other.getMetadata())));
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
        if (getAttachment() != null) {
            _hashCode += getAttachment().hashCode();
        }
        if (getIdentity() != null) {
            _hashCode += getIdentity().hashCode();
        }
        if (getMetadata() != null) {
            _hashCode += getMetadata().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepositoryInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "repositoryInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attachment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "assertionIdentity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "metadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"));
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
