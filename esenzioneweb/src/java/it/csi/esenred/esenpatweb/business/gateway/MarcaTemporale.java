/**
 * MarcaTemporale.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class MarcaTemporale  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Attachment attachment;

    private it.csi.esenred.esenpatweb.business.gateway.MarkInput markInput;

    public MarcaTemporale() {
    }

    public MarcaTemporale(
           it.csi.esenred.esenpatweb.business.gateway.Attachment attachment,
           it.csi.esenred.esenpatweb.business.gateway.MarkInput markInput) {
           this.attachment = attachment;
           this.markInput = markInput;
    }


    /**
     * Gets the attachment value for this MarcaTemporale.
     * 
     * @return attachment
     */
    public it.csi.esenred.esenpatweb.business.gateway.Attachment getAttachment() {
        return attachment;
    }


    /**
     * Sets the attachment value for this MarcaTemporale.
     * 
     * @param attachment
     */
    public void setAttachment(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment) {
        this.attachment = attachment;
    }


    /**
     * Gets the markInput value for this MarcaTemporale.
     * 
     * @return markInput
     */
    public it.csi.esenred.esenpatweb.business.gateway.MarkInput getMarkInput() {
        return markInput;
    }


    /**
     * Sets the markInput value for this MarcaTemporale.
     * 
     * @param markInput
     */
    public void setMarkInput(it.csi.esenred.esenpatweb.business.gateway.MarkInput markInput) {
        this.markInput = markInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarcaTemporale)) return false;
        MarcaTemporale other = (MarcaTemporale) obj;
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
            ((this.markInput==null && other.getMarkInput()==null) || 
             (this.markInput!=null &&
              this.markInput.equals(other.getMarkInput())));
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
        if (getMarkInput() != null) {
            _hashCode += getMarkInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarcaTemporale.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "marcaTemporale"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attachment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("markInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "markInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markInput"));
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
