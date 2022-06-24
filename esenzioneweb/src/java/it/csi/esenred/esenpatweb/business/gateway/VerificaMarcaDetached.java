/**
 * VerificaMarcaDetached.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class VerificaMarcaDetached  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Attachment mark;

    private it.csi.esenred.esenpatweb.business.gateway.Attachment originalFile;

    private it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo;

    public VerificaMarcaDetached() {
    }

    public VerificaMarcaDetached(
           it.csi.esenred.esenpatweb.business.gateway.Attachment mark,
           it.csi.esenred.esenpatweb.business.gateway.Attachment originalFile,
           it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
           this.mark = mark;
           this.originalFile = originalFile;
           this.callInfo = callInfo;
    }


    /**
     * Gets the mark value for this VerificaMarcaDetached.
     * 
     * @return mark
     */
    public it.csi.esenred.esenpatweb.business.gateway.Attachment getMark() {
        return mark;
    }


    /**
     * Sets the mark value for this VerificaMarcaDetached.
     * 
     * @param mark
     */
    public void setMark(it.csi.esenred.esenpatweb.business.gateway.Attachment mark) {
        this.mark = mark;
    }


    /**
     * Gets the originalFile value for this VerificaMarcaDetached.
     * 
     * @return originalFile
     */
    public it.csi.esenred.esenpatweb.business.gateway.Attachment getOriginalFile() {
        return originalFile;
    }


    /**
     * Sets the originalFile value for this VerificaMarcaDetached.
     * 
     * @param originalFile
     */
    public void setOriginalFile(it.csi.esenred.esenpatweb.business.gateway.Attachment originalFile) {
        this.originalFile = originalFile;
    }


    /**
     * Gets the callInfo value for this VerificaMarcaDetached.
     * 
     * @return callInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.CallInfo getCallInfo() {
        return callInfo;
    }


    /**
     * Sets the callInfo value for this VerificaMarcaDetached.
     * 
     * @param callInfo
     */
    public void setCallInfo(it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
        this.callInfo = callInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificaMarcaDetached)) return false;
        VerificaMarcaDetached other = (VerificaMarcaDetached) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mark==null && other.getMark()==null) || 
             (this.mark!=null &&
              this.mark.equals(other.getMark()))) &&
            ((this.originalFile==null && other.getOriginalFile()==null) || 
             (this.originalFile!=null &&
              this.originalFile.equals(other.getOriginalFile()))) &&
            ((this.callInfo==null && other.getCallInfo()==null) || 
             (this.callInfo!=null &&
              this.callInfo.equals(other.getCallInfo())));
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
        if (getMark() != null) {
            _hashCode += getMark().hashCode();
        }
        if (getOriginalFile() != null) {
            _hashCode += getOriginalFile().hashCode();
        }
        if (getCallInfo() != null) {
            _hashCode += getCallInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificaMarcaDetached.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "verificaMarcaDetached"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mark");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalFile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "originalFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "attachment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "callInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"));
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
