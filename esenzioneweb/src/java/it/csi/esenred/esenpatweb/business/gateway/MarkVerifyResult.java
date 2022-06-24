/**
 * MarkVerifyResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class MarkVerifyResult  extends it.csi.esenred.esenpatweb.business.gateway.BaseResult  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Certificate certificate;

    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp timeStamp;

    private java.lang.Boolean valid;

    private java.lang.String validation;

    private java.lang.String validationMessage;

    public MarkVerifyResult() {
    }

    public MarkVerifyResult(
           it.csi.esenred.esenpatweb.business.gateway.Result result,
           it.csi.esenred.esenpatweb.business.gateway.Certificate certificate,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp timeStamp,
           java.lang.Boolean valid,
           java.lang.String validation,
           java.lang.String validationMessage) {
        super(
            result);
        this.certificate = certificate;
        this.timeStamp = timeStamp;
        this.valid = valid;
        this.validation = validation;
        this.validationMessage = validationMessage;
    }


    /**
     * Gets the certificate value for this MarkVerifyResult.
     * 
     * @return certificate
     */
    public it.csi.esenred.esenpatweb.business.gateway.Certificate getCertificate() {
        return certificate;
    }


    /**
     * Sets the certificate value for this MarkVerifyResult.
     * 
     * @param certificate
     */
    public void setCertificate(it.csi.esenred.esenpatweb.business.gateway.Certificate certificate) {
        this.certificate = certificate;
    }


    /**
     * Gets the timeStamp value for this MarkVerifyResult.
     * 
     * @return timeStamp
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this MarkVerifyResult.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(it.csi.esenred.esenpatweb.business.gateway.TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the valid value for this MarkVerifyResult.
     * 
     * @return valid
     */
    public java.lang.Boolean getValid() {
        return valid;
    }


    /**
     * Sets the valid value for this MarkVerifyResult.
     * 
     * @param valid
     */
    public void setValid(java.lang.Boolean valid) {
        this.valid = valid;
    }


    /**
     * Gets the validation value for this MarkVerifyResult.
     * 
     * @return validation
     */
    public java.lang.String getValidation() {
        return validation;
    }


    /**
     * Sets the validation value for this MarkVerifyResult.
     * 
     * @param validation
     */
    public void setValidation(java.lang.String validation) {
        this.validation = validation;
    }


    /**
     * Gets the validationMessage value for this MarkVerifyResult.
     * 
     * @return validationMessage
     */
    public java.lang.String getValidationMessage() {
        return validationMessage;
    }


    /**
     * Sets the validationMessage value for this MarkVerifyResult.
     * 
     * @param validationMessage
     */
    public void setValidationMessage(java.lang.String validationMessage) {
        this.validationMessage = validationMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarkVerifyResult)) return false;
        MarkVerifyResult other = (MarkVerifyResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.certificate==null && other.getCertificate()==null) || 
             (this.certificate!=null &&
              this.certificate.equals(other.getCertificate()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.valid==null && other.getValid()==null) || 
             (this.valid!=null &&
              this.valid.equals(other.getValid()))) &&
            ((this.validation==null && other.getValidation()==null) || 
             (this.validation!=null &&
              this.validation.equals(other.getValidation()))) &&
            ((this.validationMessage==null && other.getValidationMessage()==null) || 
             (this.validationMessage!=null &&
              this.validationMessage.equals(other.getValidationMessage())));
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
        if (getCertificate() != null) {
            _hashCode += getCertificate().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getValid() != null) {
            _hashCode += getValid().hashCode();
        }
        if (getValidation() != null) {
            _hashCode += getValidation().hashCode();
        }
        if (getValidationMessage() != null) {
            _hashCode += getValidationMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarkVerifyResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "markVerifyResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certificate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "timeStamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validationMessage"));
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
