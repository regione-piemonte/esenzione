/**
 * RichiestaOtp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class RichiestaOtp  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.OtpReqInput otpReqInput;

    public RichiestaOtp() {
    }

    public RichiestaOtp(
           it.csi.esenred.esenpatweb.business.gateway.OtpReqInput otpReqInput) {
           this.otpReqInput = otpReqInput;
    }


    /**
     * Gets the otpReqInput value for this RichiestaOtp.
     * 
     * @return otpReqInput
     */
    public it.csi.esenred.esenpatweb.business.gateway.OtpReqInput getOtpReqInput() {
        return otpReqInput;
    }


    /**
     * Sets the otpReqInput value for this RichiestaOtp.
     * 
     * @param otpReqInput
     */
    public void setOtpReqInput(it.csi.esenred.esenpatweb.business.gateway.OtpReqInput otpReqInput) {
        this.otpReqInput = otpReqInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RichiestaOtp)) return false;
        RichiestaOtp other = (RichiestaOtp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.otpReqInput==null && other.getOtpReqInput()==null) || 
             (this.otpReqInput!=null &&
              this.otpReqInput.equals(other.getOtpReqInput())));
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
        if (getOtpReqInput() != null) {
            _hashCode += getOtpReqInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RichiestaOtp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "richiestaOtp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otpReqInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "otpReqInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "otpReqInput"));
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
