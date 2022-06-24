/**
 * Signer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Signer  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Certificate certificate;

    private java.util.Calendar signTime;

    private java.lang.String signTimeStr;

    private java.lang.String signValidation;

    private java.lang.String signValidationMessage;

    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp timestamp;

    private java.lang.Boolean valid;

    public Signer() {
    }

    public Signer(
           it.csi.esenred.esenpatweb.business.gateway.Certificate certificate,
           java.util.Calendar signTime,
           java.lang.String signTimeStr,
           java.lang.String signValidation,
           java.lang.String signValidationMessage,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp timestamp,
           java.lang.Boolean valid) {
           this.certificate = certificate;
           this.signTime = signTime;
           this.signTimeStr = signTimeStr;
           this.signValidation = signValidation;
           this.signValidationMessage = signValidationMessage;
           this.timestamp = timestamp;
           this.valid = valid;
    }


    /**
     * Gets the certificate value for this Signer.
     * 
     * @return certificate
     */
    public it.csi.esenred.esenpatweb.business.gateway.Certificate getCertificate() {
        return certificate;
    }


    /**
     * Sets the certificate value for this Signer.
     * 
     * @param certificate
     */
    public void setCertificate(it.csi.esenred.esenpatweb.business.gateway.Certificate certificate) {
        this.certificate = certificate;
    }


    /**
     * Gets the signTime value for this Signer.
     * 
     * @return signTime
     */
    public java.util.Calendar getSignTime() {
        return signTime;
    }


    /**
     * Sets the signTime value for this Signer.
     * 
     * @param signTime
     */
    public void setSignTime(java.util.Calendar signTime) {
        this.signTime = signTime;
    }


    /**
     * Gets the signTimeStr value for this Signer.
     * 
     * @return signTimeStr
     */
    public java.lang.String getSignTimeStr() {
        return signTimeStr;
    }


    /**
     * Sets the signTimeStr value for this Signer.
     * 
     * @param signTimeStr
     */
    public void setSignTimeStr(java.lang.String signTimeStr) {
        this.signTimeStr = signTimeStr;
    }


    /**
     * Gets the signValidation value for this Signer.
     * 
     * @return signValidation
     */
    public java.lang.String getSignValidation() {
        return signValidation;
    }


    /**
     * Sets the signValidation value for this Signer.
     * 
     * @param signValidation
     */
    public void setSignValidation(java.lang.String signValidation) {
        this.signValidation = signValidation;
    }


    /**
     * Gets the signValidationMessage value for this Signer.
     * 
     * @return signValidationMessage
     */
    public java.lang.String getSignValidationMessage() {
        return signValidationMessage;
    }


    /**
     * Sets the signValidationMessage value for this Signer.
     * 
     * @param signValidationMessage
     */
    public void setSignValidationMessage(java.lang.String signValidationMessage) {
        this.signValidationMessage = signValidationMessage;
    }


    /**
     * Gets the timestamp value for this Signer.
     * 
     * @return timestamp
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this Signer.
     * 
     * @param timestamp
     */
    public void setTimestamp(it.csi.esenred.esenpatweb.business.gateway.TimeStamp timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the valid value for this Signer.
     * 
     * @return valid
     */
    public java.lang.Boolean getValid() {
        return valid;
    }


    /**
     * Sets the valid value for this Signer.
     * 
     * @param valid
     */
    public void setValid(java.lang.Boolean valid) {
        this.valid = valid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Signer)) return false;
        Signer other = (Signer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.certificate==null && other.getCertificate()==null) || 
             (this.certificate!=null &&
              this.certificate.equals(other.getCertificate()))) &&
            ((this.signTime==null && other.getSignTime()==null) || 
             (this.signTime!=null &&
              this.signTime.equals(other.getSignTime()))) &&
            ((this.signTimeStr==null && other.getSignTimeStr()==null) || 
             (this.signTimeStr!=null &&
              this.signTimeStr.equals(other.getSignTimeStr()))) &&
            ((this.signValidation==null && other.getSignValidation()==null) || 
             (this.signValidation!=null &&
              this.signValidation.equals(other.getSignValidation()))) &&
            ((this.signValidationMessage==null && other.getSignValidationMessage()==null) || 
             (this.signValidationMessage!=null &&
              this.signValidationMessage.equals(other.getSignValidationMessage()))) &&
            ((this.timestamp==null && other.getTimestamp()==null) || 
             (this.timestamp!=null &&
              this.timestamp.equals(other.getTimestamp()))) &&
            ((this.valid==null && other.getValid()==null) || 
             (this.valid!=null &&
              this.valid.equals(other.getValid())));
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
        if (getCertificate() != null) {
            _hashCode += getCertificate().hashCode();
        }
        if (getSignTime() != null) {
            _hashCode += getSignTime().hashCode();
        }
        if (getSignTimeStr() != null) {
            _hashCode += getSignTimeStr().hashCode();
        }
        if (getSignValidation() != null) {
            _hashCode += getSignValidation().hashCode();
        }
        if (getSignValidationMessage() != null) {
            _hashCode += getSignValidationMessage().hashCode();
        }
        if (getTimestamp() != null) {
            _hashCode += getTimestamp().hashCode();
        }
        if (getValid() != null) {
            _hashCode += getValid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Signer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certificate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signTimeStr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signTimeStr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signValidation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signValidation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signValidationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signValidationMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timestamp"));
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
