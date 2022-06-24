/**
 * Archivia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Archivia  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.RepositoryInput repositoryInput;

    private it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo;

    public Archivia() {
    }

    public Archivia(
           it.csi.esenred.esenpatweb.business.gateway.RepositoryInput repositoryInput,
           it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
           this.repositoryInput = repositoryInput;
           this.callInfo = callInfo;
    }


    /**
     * Gets the repositoryInput value for this Archivia.
     * 
     * @return repositoryInput
     */
    public it.csi.esenred.esenpatweb.business.gateway.RepositoryInput getRepositoryInput() {
        return repositoryInput;
    }


    /**
     * Sets the repositoryInput value for this Archivia.
     * 
     * @param repositoryInput
     */
    public void setRepositoryInput(it.csi.esenred.esenpatweb.business.gateway.RepositoryInput repositoryInput) {
        this.repositoryInput = repositoryInput;
    }


    /**
     * Gets the callInfo value for this Archivia.
     * 
     * @return callInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.CallInfo getCallInfo() {
        return callInfo;
    }


    /**
     * Sets the callInfo value for this Archivia.
     * 
     * @param callInfo
     */
    public void setCallInfo(it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) {
        this.callInfo = callInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Archivia)) return false;
        Archivia other = (Archivia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.repositoryInput==null && other.getRepositoryInput()==null) || 
             (this.repositoryInput!=null &&
              this.repositoryInput.equals(other.getRepositoryInput()))) &&
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
        if (getRepositoryInput() != null) {
            _hashCode += getRepositoryInput().hashCode();
        }
        if (getCallInfo() != null) {
            _hashCode += getCallInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Archivia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "archivia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repositoryInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repositoryInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "repositoryInput"));
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
