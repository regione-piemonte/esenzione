/**
 * FirmaPAdESMassivaRemota.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class FirmaPAdESMassivaRemota  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment;

    private it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput;

    private it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity;

    public FirmaPAdESMassivaRemota() {
    }

    public FirmaPAdESMassivaRemota(
           it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment,
           it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput,
           it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) {
           this.attachment = attachment;
           this.padesInput = padesInput;
           this.identity = identity;
    }


    /**
     * Gets the attachment value for this FirmaPAdESMassivaRemota.
     * 
     * @return attachment
     */
    public it.csi.esenred.esenpatweb.business.gateway.Attachment[] getAttachment() {
        return attachment;
    }


    /**
     * Sets the attachment value for this FirmaPAdESMassivaRemota.
     * 
     * @param attachment
     */
    public void setAttachment(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment) {
        this.attachment = attachment;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Attachment getAttachment(int i) {
        return this.attachment[i];
    }

    public void setAttachment(int i, it.csi.esenred.esenpatweb.business.gateway.Attachment _value) {
        this.attachment[i] = _value;
    }


    /**
     * Gets the padesInput value for this FirmaPAdESMassivaRemota.
     * 
     * @return padesInput
     */
    public it.csi.esenred.esenpatweb.business.gateway.PadesInput getPadesInput() {
        return padesInput;
    }


    /**
     * Sets the padesInput value for this FirmaPAdESMassivaRemota.
     * 
     * @param padesInput
     */
    public void setPadesInput(it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput) {
        this.padesInput = padesInput;
    }


    /**
     * Gets the identity value for this FirmaPAdESMassivaRemota.
     * 
     * @return identity
     */
    public it.csi.esenred.esenpatweb.business.gateway.SignIdentity getIdentity() {
        return identity;
    }


    /**
     * Sets the identity value for this FirmaPAdESMassivaRemota.
     * 
     * @param identity
     */
    public void setIdentity(it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) {
        this.identity = identity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FirmaPAdESMassivaRemota)) return false;
        FirmaPAdESMassivaRemota other = (FirmaPAdESMassivaRemota) obj;
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
              java.util.Arrays.equals(this.attachment, other.getAttachment()))) &&
            ((this.padesInput==null && other.getPadesInput()==null) || 
             (this.padesInput!=null &&
              this.padesInput.equals(other.getPadesInput()))) &&
            ((this.identity==null && other.getIdentity()==null) || 
             (this.identity!=null &&
              this.identity.equals(other.getIdentity())));
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
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttachment());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttachment(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPadesInput() != null) {
            _hashCode += getPadesInput().hashCode();
        }
        if (getIdentity() != null) {
            _hashCode += getIdentity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FirmaPAdESMassivaRemota.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "firmaPAdESMassivaRemota"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attachment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("padesInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "padesInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "padesInput"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signIdentity"));
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
