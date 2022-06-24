/**
 * IdentifiedObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public abstract class IdentifiedObject  implements java.io.Serializable {
    private java.lang.String homeCommunityId;

    private java.lang.String entryUuid;

    private java.lang.String logicalUuid;

    private it.csi.esenred.esenpatweb.business.gateway.Version version;

    private java.lang.String uniqueId;

    private it.csi.esenred.esenpatweb.business.gateway.Identifiable patientId;

    private it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus;

    private it.csi.esenred.esenpatweb.business.gateway.LocalizedString title;

    private it.csi.esenred.esenpatweb.business.gateway.LocalizedString comments;

    private boolean limitedMetadata;

    private java.lang.Object extraMetadata;

    public IdentifiedObject() {
    }

    public IdentifiedObject(
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
           java.lang.Object extraMetadata) {
           this.homeCommunityId = homeCommunityId;
           this.entryUuid = entryUuid;
           this.logicalUuid = logicalUuid;
           this.version = version;
           this.uniqueId = uniqueId;
           this.patientId = patientId;
           this.availabilityStatus = availabilityStatus;
           this.title = title;
           this.comments = comments;
           this.limitedMetadata = limitedMetadata;
           this.extraMetadata = extraMetadata;
    }


    /**
     * Gets the homeCommunityId value for this IdentifiedObject.
     * 
     * @return homeCommunityId
     */
    public java.lang.String getHomeCommunityId() {
        return homeCommunityId;
    }


    /**
     * Sets the homeCommunityId value for this IdentifiedObject.
     * 
     * @param homeCommunityId
     */
    public void setHomeCommunityId(java.lang.String homeCommunityId) {
        this.homeCommunityId = homeCommunityId;
    }


    /**
     * Gets the entryUuid value for this IdentifiedObject.
     * 
     * @return entryUuid
     */
    public java.lang.String getEntryUuid() {
        return entryUuid;
    }


    /**
     * Sets the entryUuid value for this IdentifiedObject.
     * 
     * @param entryUuid
     */
    public void setEntryUuid(java.lang.String entryUuid) {
        this.entryUuid = entryUuid;
    }


    /**
     * Gets the logicalUuid value for this IdentifiedObject.
     * 
     * @return logicalUuid
     */
    public java.lang.String getLogicalUuid() {
        return logicalUuid;
    }


    /**
     * Sets the logicalUuid value for this IdentifiedObject.
     * 
     * @param logicalUuid
     */
    public void setLogicalUuid(java.lang.String logicalUuid) {
        this.logicalUuid = logicalUuid;
    }


    /**
     * Gets the version value for this IdentifiedObject.
     * 
     * @return version
     */
    public it.csi.esenred.esenpatweb.business.gateway.Version getVersion() {
        return version;
    }


    /**
     * Sets the version value for this IdentifiedObject.
     * 
     * @param version
     */
    public void setVersion(it.csi.esenred.esenpatweb.business.gateway.Version version) {
        this.version = version;
    }


    /**
     * Gets the uniqueId value for this IdentifiedObject.
     * 
     * @return uniqueId
     */
    public java.lang.String getUniqueId() {
        return uniqueId;
    }


    /**
     * Sets the uniqueId value for this IdentifiedObject.
     * 
     * @param uniqueId
     */
    public void setUniqueId(java.lang.String uniqueId) {
        this.uniqueId = uniqueId;
    }


    /**
     * Gets the patientId value for this IdentifiedObject.
     * 
     * @return patientId
     */
    public it.csi.esenred.esenpatweb.business.gateway.Identifiable getPatientId() {
        return patientId;
    }


    /**
     * Sets the patientId value for this IdentifiedObject.
     * 
     * @param patientId
     */
    public void setPatientId(it.csi.esenred.esenpatweb.business.gateway.Identifiable patientId) {
        this.patientId = patientId;
    }


    /**
     * Gets the availabilityStatus value for this IdentifiedObject.
     * 
     * @return availabilityStatus
     */
    public it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }


    /**
     * Sets the availabilityStatus value for this IdentifiedObject.
     * 
     * @param availabilityStatus
     */
    public void setAvailabilityStatus(it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }


    /**
     * Gets the title value for this IdentifiedObject.
     * 
     * @return title
     */
    public it.csi.esenred.esenpatweb.business.gateway.LocalizedString getTitle() {
        return title;
    }


    /**
     * Sets the title value for this IdentifiedObject.
     * 
     * @param title
     */
    public void setTitle(it.csi.esenred.esenpatweb.business.gateway.LocalizedString title) {
        this.title = title;
    }


    /**
     * Gets the comments value for this IdentifiedObject.
     * 
     * @return comments
     */
    public it.csi.esenred.esenpatweb.business.gateway.LocalizedString getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this IdentifiedObject.
     * 
     * @param comments
     */
    public void setComments(it.csi.esenred.esenpatweb.business.gateway.LocalizedString comments) {
        this.comments = comments;
    }


    /**
     * Gets the limitedMetadata value for this IdentifiedObject.
     * 
     * @return limitedMetadata
     */
    public boolean isLimitedMetadata() {
        return limitedMetadata;
    }


    /**
     * Sets the limitedMetadata value for this IdentifiedObject.
     * 
     * @param limitedMetadata
     */
    public void setLimitedMetadata(boolean limitedMetadata) {
        this.limitedMetadata = limitedMetadata;
    }


    /**
     * Gets the extraMetadata value for this IdentifiedObject.
     * 
     * @return extraMetadata
     */
    public java.lang.Object getExtraMetadata() {
        return extraMetadata;
    }


    /**
     * Sets the extraMetadata value for this IdentifiedObject.
     * 
     * @param extraMetadata
     */
    public void setExtraMetadata(java.lang.Object extraMetadata) {
        this.extraMetadata = extraMetadata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdentifiedObject)) return false;
        IdentifiedObject other = (IdentifiedObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.homeCommunityId==null && other.getHomeCommunityId()==null) || 
             (this.homeCommunityId!=null &&
              this.homeCommunityId.equals(other.getHomeCommunityId()))) &&
            ((this.entryUuid==null && other.getEntryUuid()==null) || 
             (this.entryUuid!=null &&
              this.entryUuid.equals(other.getEntryUuid()))) &&
            ((this.logicalUuid==null && other.getLogicalUuid()==null) || 
             (this.logicalUuid!=null &&
              this.logicalUuid.equals(other.getLogicalUuid()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.uniqueId==null && other.getUniqueId()==null) || 
             (this.uniqueId!=null &&
              this.uniqueId.equals(other.getUniqueId()))) &&
            ((this.patientId==null && other.getPatientId()==null) || 
             (this.patientId!=null &&
              this.patientId.equals(other.getPatientId()))) &&
            ((this.availabilityStatus==null && other.getAvailabilityStatus()==null) || 
             (this.availabilityStatus!=null &&
              this.availabilityStatus.equals(other.getAvailabilityStatus()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            this.limitedMetadata == other.isLimitedMetadata() &&
            ((this.extraMetadata==null && other.getExtraMetadata()==null) || 
             (this.extraMetadata!=null &&
              this.extraMetadata.equals(other.getExtraMetadata())));
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
        if (getHomeCommunityId() != null) {
            _hashCode += getHomeCommunityId().hashCode();
        }
        if (getEntryUuid() != null) {
            _hashCode += getEntryUuid().hashCode();
        }
        if (getLogicalUuid() != null) {
            _hashCode += getLogicalUuid().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getUniqueId() != null) {
            _hashCode += getUniqueId().hashCode();
        }
        if (getPatientId() != null) {
            _hashCode += getPatientId().hashCode();
        }
        if (getAvailabilityStatus() != null) {
            _hashCode += getAvailabilityStatus().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        _hashCode += (isLimitedMetadata() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getExtraMetadata() != null) {
            _hashCode += getExtraMetadata().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdentifiedObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "IdentifiedObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeCommunityId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "homeCommunityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entryUuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "entryUuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logicalUuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "logicalUuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Version"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "uniqueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "patientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Identifiable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availabilityStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "availabilityStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AvailabilityStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "LocalizedString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "LocalizedString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limitedMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "limitedMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "extraMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
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
