/**
 * Association.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Association  implements java.io.Serializable {
    private java.lang.String entryUuid;

    private java.lang.String sourceUuid;

    private java.lang.String targetUuid;

    private it.csi.esenred.esenpatweb.business.gateway.AssociationType associationType;

    private it.csi.esenred.esenpatweb.business.gateway.AssociationLabel label;

    private it.csi.esenred.esenpatweb.business.gateway.Code docCode;

    private java.lang.String previousVersion;

    private it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus originalStatus;

    private it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus newStatus;

    private java.lang.Boolean associationPropagation;

    private it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus;

    private java.lang.String[][] extraMetadata;

    public Association() {
    }

    public Association(
           java.lang.String entryUuid,
           java.lang.String sourceUuid,
           java.lang.String targetUuid,
           it.csi.esenred.esenpatweb.business.gateway.AssociationType associationType,
           it.csi.esenred.esenpatweb.business.gateway.AssociationLabel label,
           it.csi.esenred.esenpatweb.business.gateway.Code docCode,
           java.lang.String previousVersion,
           it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus originalStatus,
           it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus newStatus,
           java.lang.Boolean associationPropagation,
           it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus,
           java.lang.String[][] extraMetadata) {
           this.entryUuid = entryUuid;
           this.sourceUuid = sourceUuid;
           this.targetUuid = targetUuid;
           this.associationType = associationType;
           this.label = label;
           this.docCode = docCode;
           this.previousVersion = previousVersion;
           this.originalStatus = originalStatus;
           this.newStatus = newStatus;
           this.associationPropagation = associationPropagation;
           this.availabilityStatus = availabilityStatus;
           this.extraMetadata = extraMetadata;
    }


    /**
     * Gets the entryUuid value for this Association.
     * 
     * @return entryUuid
     */
    public java.lang.String getEntryUuid() {
        return entryUuid;
    }


    /**
     * Sets the entryUuid value for this Association.
     * 
     * @param entryUuid
     */
    public void setEntryUuid(java.lang.String entryUuid) {
        this.entryUuid = entryUuid;
    }


    /**
     * Gets the sourceUuid value for this Association.
     * 
     * @return sourceUuid
     */
    public java.lang.String getSourceUuid() {
        return sourceUuid;
    }


    /**
     * Sets the sourceUuid value for this Association.
     * 
     * @param sourceUuid
     */
    public void setSourceUuid(java.lang.String sourceUuid) {
        this.sourceUuid = sourceUuid;
    }


    /**
     * Gets the targetUuid value for this Association.
     * 
     * @return targetUuid
     */
    public java.lang.String getTargetUuid() {
        return targetUuid;
    }


    /**
     * Sets the targetUuid value for this Association.
     * 
     * @param targetUuid
     */
    public void setTargetUuid(java.lang.String targetUuid) {
        this.targetUuid = targetUuid;
    }


    /**
     * Gets the associationType value for this Association.
     * 
     * @return associationType
     */
    public it.csi.esenred.esenpatweb.business.gateway.AssociationType getAssociationType() {
        return associationType;
    }


    /**
     * Sets the associationType value for this Association.
     * 
     * @param associationType
     */
    public void setAssociationType(it.csi.esenred.esenpatweb.business.gateway.AssociationType associationType) {
        this.associationType = associationType;
    }


    /**
     * Gets the label value for this Association.
     * 
     * @return label
     */
    public it.csi.esenred.esenpatweb.business.gateway.AssociationLabel getLabel() {
        return label;
    }


    /**
     * Sets the label value for this Association.
     * 
     * @param label
     */
    public void setLabel(it.csi.esenred.esenpatweb.business.gateway.AssociationLabel label) {
        this.label = label;
    }


    /**
     * Gets the docCode value for this Association.
     * 
     * @return docCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this Association.
     * 
     * @param docCode
     */
    public void setDocCode(it.csi.esenred.esenpatweb.business.gateway.Code docCode) {
        this.docCode = docCode;
    }


    /**
     * Gets the previousVersion value for this Association.
     * 
     * @return previousVersion
     */
    public java.lang.String getPreviousVersion() {
        return previousVersion;
    }


    /**
     * Sets the previousVersion value for this Association.
     * 
     * @param previousVersion
     */
    public void setPreviousVersion(java.lang.String previousVersion) {
        this.previousVersion = previousVersion;
    }


    /**
     * Gets the originalStatus value for this Association.
     * 
     * @return originalStatus
     */
    public it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus getOriginalStatus() {
        return originalStatus;
    }


    /**
     * Sets the originalStatus value for this Association.
     * 
     * @param originalStatus
     */
    public void setOriginalStatus(it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus originalStatus) {
        this.originalStatus = originalStatus;
    }


    /**
     * Gets the newStatus value for this Association.
     * 
     * @return newStatus
     */
    public it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus getNewStatus() {
        return newStatus;
    }


    /**
     * Sets the newStatus value for this Association.
     * 
     * @param newStatus
     */
    public void setNewStatus(it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus newStatus) {
        this.newStatus = newStatus;
    }


    /**
     * Gets the associationPropagation value for this Association.
     * 
     * @return associationPropagation
     */
    public java.lang.Boolean getAssociationPropagation() {
        return associationPropagation;
    }


    /**
     * Sets the associationPropagation value for this Association.
     * 
     * @param associationPropagation
     */
    public void setAssociationPropagation(java.lang.Boolean associationPropagation) {
        this.associationPropagation = associationPropagation;
    }


    /**
     * Gets the availabilityStatus value for this Association.
     * 
     * @return availabilityStatus
     */
    public it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }


    /**
     * Sets the availabilityStatus value for this Association.
     * 
     * @param availabilityStatus
     */
    public void setAvailabilityStatus(it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }


    /**
     * Gets the extraMetadata value for this Association.
     * 
     * @return extraMetadata
     */
    public java.lang.String[][] getExtraMetadata() {
        return extraMetadata;
    }


    /**
     * Sets the extraMetadata value for this Association.
     * 
     * @param extraMetadata
     */
    public void setExtraMetadata(java.lang.String[][] extraMetadata) {
        this.extraMetadata = extraMetadata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Association)) return false;
        Association other = (Association) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entryUuid==null && other.getEntryUuid()==null) || 
             (this.entryUuid!=null &&
              this.entryUuid.equals(other.getEntryUuid()))) &&
            ((this.sourceUuid==null && other.getSourceUuid()==null) || 
             (this.sourceUuid!=null &&
              this.sourceUuid.equals(other.getSourceUuid()))) &&
            ((this.targetUuid==null && other.getTargetUuid()==null) || 
             (this.targetUuid!=null &&
              this.targetUuid.equals(other.getTargetUuid()))) &&
            ((this.associationType==null && other.getAssociationType()==null) || 
             (this.associationType!=null &&
              this.associationType.equals(other.getAssociationType()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.docCode==null && other.getDocCode()==null) || 
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode()))) &&
            ((this.previousVersion==null && other.getPreviousVersion()==null) || 
             (this.previousVersion!=null &&
              this.previousVersion.equals(other.getPreviousVersion()))) &&
            ((this.originalStatus==null && other.getOriginalStatus()==null) || 
             (this.originalStatus!=null &&
              this.originalStatus.equals(other.getOriginalStatus()))) &&
            ((this.newStatus==null && other.getNewStatus()==null) || 
             (this.newStatus!=null &&
              this.newStatus.equals(other.getNewStatus()))) &&
            ((this.associationPropagation==null && other.getAssociationPropagation()==null) || 
             (this.associationPropagation!=null &&
              this.associationPropagation.equals(other.getAssociationPropagation()))) &&
            ((this.availabilityStatus==null && other.getAvailabilityStatus()==null) || 
             (this.availabilityStatus!=null &&
              this.availabilityStatus.equals(other.getAvailabilityStatus()))) &&
            ((this.extraMetadata==null && other.getExtraMetadata()==null) || 
             (this.extraMetadata!=null &&
              java.util.Arrays.equals(this.extraMetadata, other.getExtraMetadata())));
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
        if (getEntryUuid() != null) {
            _hashCode += getEntryUuid().hashCode();
        }
        if (getSourceUuid() != null) {
            _hashCode += getSourceUuid().hashCode();
        }
        if (getTargetUuid() != null) {
            _hashCode += getTargetUuid().hashCode();
        }
        if (getAssociationType() != null) {
            _hashCode += getAssociationType().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        if (getPreviousVersion() != null) {
            _hashCode += getPreviousVersion().hashCode();
        }
        if (getOriginalStatus() != null) {
            _hashCode += getOriginalStatus().hashCode();
        }
        if (getNewStatus() != null) {
            _hashCode += getNewStatus().hashCode();
        }
        if (getAssociationPropagation() != null) {
            _hashCode += getAssociationPropagation().hashCode();
        }
        if (getAvailabilityStatus() != null) {
            _hashCode += getAvailabilityStatus().hashCode();
        }
        if (getExtraMetadata() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtraMetadata());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtraMetadata(), i);
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
        new org.apache.axis.description.TypeDesc(Association.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Association"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entryUuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "entryUuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceUuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "sourceUuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetUuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "targetUuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "associationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssociationType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssociationLabel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "docCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("previousVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "previousVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "originalStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AvailabilityStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "newStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AvailabilityStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associationPropagation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "associationPropagation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("extraMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "extraMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", ">>Association>extraMetadata>entry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "entry"));
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
