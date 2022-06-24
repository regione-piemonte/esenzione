/**
 * SignVerifyResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class SignVerifyResult  extends it.csi.esenred.esenpatweb.business.gateway.BaseResult  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Signer[] signer;

    public SignVerifyResult() {
    }

    public SignVerifyResult(
           it.csi.esenred.esenpatweb.business.gateway.Result result,
           it.csi.esenred.esenpatweb.business.gateway.Signer[] signer) {
        super(
            result);
        this.signer = signer;
    }


    /**
     * Gets the signer value for this SignVerifyResult.
     * 
     * @return signer
     */
    public it.csi.esenred.esenpatweb.business.gateway.Signer[] getSigner() {
        return signer;
    }


    /**
     * Sets the signer value for this SignVerifyResult.
     * 
     * @param signer
     */
    public void setSigner(it.csi.esenred.esenpatweb.business.gateway.Signer[] signer) {
        this.signer = signer;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Signer getSigner(int i) {
        return this.signer[i];
    }

    public void setSigner(int i, it.csi.esenred.esenpatweb.business.gateway.Signer _value) {
        this.signer[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SignVerifyResult)) return false;
        SignVerifyResult other = (SignVerifyResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.signer==null && other.getSigner()==null) || 
             (this.signer!=null &&
              java.util.Arrays.equals(this.signer, other.getSigner())));
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
        if (getSigner() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSigner());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSigner(), i);
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
        new org.apache.axis.description.TypeDesc(SignVerifyResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signVerifyResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signer"));
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
