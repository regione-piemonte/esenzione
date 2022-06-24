/**
 * ItiMetadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class ItiMetadata  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Association[] associationList;

    private it.csi.esenred.esenpatweb.business.gateway.DocumentEntry documentEntry;

    private it.csi.esenred.esenpatweb.business.gateway.Folder[] folderList;

    private it.csi.esenred.esenpatweb.business.gateway.SubmissionSet submissionSet;

    public ItiMetadata() {
    }

    public ItiMetadata(
           it.csi.esenred.esenpatweb.business.gateway.Association[] associationList,
           it.csi.esenred.esenpatweb.business.gateway.DocumentEntry documentEntry,
           it.csi.esenred.esenpatweb.business.gateway.Folder[] folderList,
           it.csi.esenred.esenpatweb.business.gateway.SubmissionSet submissionSet) {
           this.associationList = associationList;
           this.documentEntry = documentEntry;
           this.folderList = folderList;
           this.submissionSet = submissionSet;
    }


    /**
     * Gets the associationList value for this ItiMetadata.
     * 
     * @return associationList
     */
    public it.csi.esenred.esenpatweb.business.gateway.Association[] getAssociationList() {
        return associationList;
    }


    /**
     * Sets the associationList value for this ItiMetadata.
     * 
     * @param associationList
     */
    public void setAssociationList(it.csi.esenred.esenpatweb.business.gateway.Association[] associationList) {
        this.associationList = associationList;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Association getAssociationList(int i) {
        return this.associationList[i];
    }

    public void setAssociationList(int i, it.csi.esenred.esenpatweb.business.gateway.Association _value) {
        this.associationList[i] = _value;
    }


    /**
     * Gets the documentEntry value for this ItiMetadata.
     * 
     * @return documentEntry
     */
    public it.csi.esenred.esenpatweb.business.gateway.DocumentEntry getDocumentEntry() {
        return documentEntry;
    }


    /**
     * Sets the documentEntry value for this ItiMetadata.
     * 
     * @param documentEntry
     */
    public void setDocumentEntry(it.csi.esenred.esenpatweb.business.gateway.DocumentEntry documentEntry) {
        this.documentEntry = documentEntry;
    }


    /**
     * Gets the folderList value for this ItiMetadata.
     * 
     * @return folderList
     */
    public it.csi.esenred.esenpatweb.business.gateway.Folder[] getFolderList() {
        return folderList;
    }


    /**
     * Sets the folderList value for this ItiMetadata.
     * 
     * @param folderList
     */
    public void setFolderList(it.csi.esenred.esenpatweb.business.gateway.Folder[] folderList) {
        this.folderList = folderList;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Folder getFolderList(int i) {
        return this.folderList[i];
    }

    public void setFolderList(int i, it.csi.esenred.esenpatweb.business.gateway.Folder _value) {
        this.folderList[i] = _value;
    }


    /**
     * Gets the submissionSet value for this ItiMetadata.
     * 
     * @return submissionSet
     */
    public it.csi.esenred.esenpatweb.business.gateway.SubmissionSet getSubmissionSet() {
        return submissionSet;
    }


    /**
     * Sets the submissionSet value for this ItiMetadata.
     * 
     * @param submissionSet
     */
    public void setSubmissionSet(it.csi.esenred.esenpatweb.business.gateway.SubmissionSet submissionSet) {
        this.submissionSet = submissionSet;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ItiMetadata)) return false;
        ItiMetadata other = (ItiMetadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.associationList==null && other.getAssociationList()==null) || 
             (this.associationList!=null &&
              java.util.Arrays.equals(this.associationList, other.getAssociationList()))) &&
            ((this.documentEntry==null && other.getDocumentEntry()==null) || 
             (this.documentEntry!=null &&
              this.documentEntry.equals(other.getDocumentEntry()))) &&
            ((this.folderList==null && other.getFolderList()==null) || 
             (this.folderList!=null &&
              java.util.Arrays.equals(this.folderList, other.getFolderList()))) &&
            ((this.submissionSet==null && other.getSubmissionSet()==null) || 
             (this.submissionSet!=null &&
              this.submissionSet.equals(other.getSubmissionSet())));
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
        if (getAssociationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssociationList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssociationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumentEntry() != null) {
            _hashCode += getDocumentEntry().hashCode();
        }
        if (getFolderList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFolderList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFolderList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubmissionSet() != null) {
            _hashCode += getSubmissionSet().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ItiMetadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "itiMetadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associationList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "associationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Association"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentEntry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Folder"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submissionSet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "submissionSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "SubmissionSet"));
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
