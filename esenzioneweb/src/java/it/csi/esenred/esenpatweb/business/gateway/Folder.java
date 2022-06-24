/**
 * Folder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Folder  extends it.csi.esenred.esenpatweb.business.gateway.IdentifiedObject  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp lastUpdateTime;

    private it.csi.esenred.esenpatweb.business.gateway.Code[] code;

    public Folder() {
    }

    public Folder(
           java.lang.String homeCommunityId,
           java.lang.String entryUuid,
           java.lang.String logicalUuid,
           it.csi.esenred.esenpatweb.business.gateway.Version version,
           java.lang.String uniqueId,
           it.csi.esenred.esenpatweb.business.gateway.Identifiable patientId,
           it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus,
           it.csi.esenred.esenpatweb.business.gateway.LocalizedString title,
           it.csi.esenred.esenpatweb.business.gateway.LocalizedString comments,
           boolean limitedMetadata,
           java.lang.Object extraMetadata,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp lastUpdateTime,
           it.csi.esenred.esenpatweb.business.gateway.Code[] code) {
        super(
            homeCommunityId,
            entryUuid,
            logicalUuid,
            version,
            uniqueId,
            patientId,
            availabilityStatus,
            title,
            comments,
            limitedMetadata,
            extraMetadata);
        this.lastUpdateTime = lastUpdateTime;
        this.code = code;
    }


    /**
     * Gets the lastUpdateTime value for this Folder.
     * 
     * @return lastUpdateTime
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getLastUpdateTime() {
        return lastUpdateTime;
    }


    /**
     * Sets the lastUpdateTime value for this Folder.
     * 
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(it.csi.esenred.esenpatweb.business.gateway.TimeStamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


    /**
     * Gets the code value for this Folder.
     * 
     * @return code
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code[] getCode() {
        return code;
    }


    /**
     * Sets the code value for this Folder.
     * 
     * @param code
     */
    public void setCode(it.csi.esenred.esenpatweb.business.gateway.Code[] code) {
        this.code = code;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Code getCode(int i) {
        return this.code[i];
    }

    public void setCode(int i, it.csi.esenred.esenpatweb.business.gateway.Code _value) {
        this.code[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Folder)) return false;
        Folder other = (Folder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.lastUpdateTime==null && other.getLastUpdateTime()==null) || 
             (this.lastUpdateTime!=null &&
              this.lastUpdateTime.equals(other.getLastUpdateTime()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              java.util.Arrays.equals(this.code, other.getCode())));
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
        if (getLastUpdateTime() != null) {
            _hashCode += getLastUpdateTime().hashCode();
        }
        if (getCode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCode(), i);
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
        new org.apache.axis.description.TypeDesc(Folder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Folder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "lastUpdateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Timestamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
