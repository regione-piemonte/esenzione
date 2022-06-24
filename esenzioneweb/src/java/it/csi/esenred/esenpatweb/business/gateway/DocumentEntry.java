/**
 * DocumentEntry.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class DocumentEntry  extends it.csi.esenred.esenpatweb.business.gateway.IdentifiedObject  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.Identifiable sourcePatientId;

    private it.csi.esenred.esenpatweb.business.gateway.PatientInfo sourcePatientInfo;

    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp creationTime;

    private it.csi.esenred.esenpatweb.business.gateway.Author[] author;

    private it.csi.esenred.esenpatweb.business.gateway.Person legalAuthenticator;

    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp serviceStartTime;

    private it.csi.esenred.esenpatweb.business.gateway.TimeStamp serviceStopTime;

    private it.csi.esenred.esenpatweb.business.gateway.Code classCode;

    private it.csi.esenred.esenpatweb.business.gateway.Code[] confidentialityCode;

    private it.csi.esenred.esenpatweb.business.gateway.Code[] eventCode;

    private it.csi.esenred.esenpatweb.business.gateway.Code formatCode;

    private it.csi.esenred.esenpatweb.business.gateway.Code healthcareFacilityTypeCode;

    private java.lang.String languageCode;

    private it.csi.esenred.esenpatweb.business.gateway.Code practiceSettingCode;

    private it.csi.esenred.esenpatweb.business.gateway.Code typeCode;

    private java.lang.String repositoryUniqueId;

    private java.lang.String mimeType;

    private java.lang.Long size;

    private java.lang.String hash;

    private java.lang.String uri;

    private it.csi.esenred.esenpatweb.business.gateway.DocumentEntryType type;

    private it.csi.esenred.esenpatweb.business.gateway.ReferenceId[] referenceIdList;

    private it.csi.esenred.esenpatweb.business.gateway.DocumentAvailability documentAvailability;

    public DocumentEntry() {
    }

    public DocumentEntry(
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
           it.csi.esenred.esenpatweb.business.gateway.Identifiable sourcePatientId,
           it.csi.esenred.esenpatweb.business.gateway.PatientInfo sourcePatientInfo,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp creationTime,
           it.csi.esenred.esenpatweb.business.gateway.Author[] author,
           it.csi.esenred.esenpatweb.business.gateway.Person legalAuthenticator,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp serviceStartTime,
           it.csi.esenred.esenpatweb.business.gateway.TimeStamp serviceStopTime,
           it.csi.esenred.esenpatweb.business.gateway.Code classCode,
           it.csi.esenred.esenpatweb.business.gateway.Code[] confidentialityCode,
           it.csi.esenred.esenpatweb.business.gateway.Code[] eventCode,
           it.csi.esenred.esenpatweb.business.gateway.Code formatCode,
           it.csi.esenred.esenpatweb.business.gateway.Code healthcareFacilityTypeCode,
           java.lang.String languageCode,
           it.csi.esenred.esenpatweb.business.gateway.Code practiceSettingCode,
           it.csi.esenred.esenpatweb.business.gateway.Code typeCode,
           java.lang.String repositoryUniqueId,
           java.lang.String mimeType,
           java.lang.Long size,
           java.lang.String hash,
           java.lang.String uri,
           it.csi.esenred.esenpatweb.business.gateway.DocumentEntryType type,
           it.csi.esenred.esenpatweb.business.gateway.ReferenceId[] referenceIdList,
           it.csi.esenred.esenpatweb.business.gateway.DocumentAvailability documentAvailability) {
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
        this.sourcePatientId = sourcePatientId;
        this.sourcePatientInfo = sourcePatientInfo;
        this.creationTime = creationTime;
        this.author = author;
        this.legalAuthenticator = legalAuthenticator;
        this.serviceStartTime = serviceStartTime;
        this.serviceStopTime = serviceStopTime;
        this.classCode = classCode;
        this.confidentialityCode = confidentialityCode;
        this.eventCode = eventCode;
        this.formatCode = formatCode;
        this.healthcareFacilityTypeCode = healthcareFacilityTypeCode;
        this.languageCode = languageCode;
        this.practiceSettingCode = practiceSettingCode;
        this.typeCode = typeCode;
        this.repositoryUniqueId = repositoryUniqueId;
        this.mimeType = mimeType;
        this.size = size;
        this.hash = hash;
        this.uri = uri;
        this.type = type;
        this.referenceIdList = referenceIdList;
        this.documentAvailability = documentAvailability;
    }


    /**
     * Gets the sourcePatientId value for this DocumentEntry.
     * 
     * @return sourcePatientId
     */
    public it.csi.esenred.esenpatweb.business.gateway.Identifiable getSourcePatientId() {
        return sourcePatientId;
    }


    /**
     * Sets the sourcePatientId value for this DocumentEntry.
     * 
     * @param sourcePatientId
     */
    public void setSourcePatientId(it.csi.esenred.esenpatweb.business.gateway.Identifiable sourcePatientId) {
        this.sourcePatientId = sourcePatientId;
    }


    /**
     * Gets the sourcePatientInfo value for this DocumentEntry.
     * 
     * @return sourcePatientInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.PatientInfo getSourcePatientInfo() {
        return sourcePatientInfo;
    }


    /**
     * Sets the sourcePatientInfo value for this DocumentEntry.
     * 
     * @param sourcePatientInfo
     */
    public void setSourcePatientInfo(it.csi.esenred.esenpatweb.business.gateway.PatientInfo sourcePatientInfo) {
        this.sourcePatientInfo = sourcePatientInfo;
    }


    /**
     * Gets the creationTime value for this DocumentEntry.
     * 
     * @return creationTime
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getCreationTime() {
        return creationTime;
    }


    /**
     * Sets the creationTime value for this DocumentEntry.
     * 
     * @param creationTime
     */
    public void setCreationTime(it.csi.esenred.esenpatweb.business.gateway.TimeStamp creationTime) {
        this.creationTime = creationTime;
    }


    /**
     * Gets the author value for this DocumentEntry.
     * 
     * @return author
     */
    public it.csi.esenred.esenpatweb.business.gateway.Author[] getAuthor() {
        return author;
    }


    /**
     * Sets the author value for this DocumentEntry.
     * 
     * @param author
     */
    public void setAuthor(it.csi.esenred.esenpatweb.business.gateway.Author[] author) {
        this.author = author;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Author getAuthor(int i) {
        return this.author[i];
    }

    public void setAuthor(int i, it.csi.esenred.esenpatweb.business.gateway.Author _value) {
        this.author[i] = _value;
    }


    /**
     * Gets the legalAuthenticator value for this DocumentEntry.
     * 
     * @return legalAuthenticator
     */
    public it.csi.esenred.esenpatweb.business.gateway.Person getLegalAuthenticator() {
        return legalAuthenticator;
    }


    /**
     * Sets the legalAuthenticator value for this DocumentEntry.
     * 
     * @param legalAuthenticator
     */
    public void setLegalAuthenticator(it.csi.esenred.esenpatweb.business.gateway.Person legalAuthenticator) {
        this.legalAuthenticator = legalAuthenticator;
    }


    /**
     * Gets the serviceStartTime value for this DocumentEntry.
     * 
     * @return serviceStartTime
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getServiceStartTime() {
        return serviceStartTime;
    }


    /**
     * Sets the serviceStartTime value for this DocumentEntry.
     * 
     * @param serviceStartTime
     */
    public void setServiceStartTime(it.csi.esenred.esenpatweb.business.gateway.TimeStamp serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }


    /**
     * Gets the serviceStopTime value for this DocumentEntry.
     * 
     * @return serviceStopTime
     */
    public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getServiceStopTime() {
        return serviceStopTime;
    }


    /**
     * Sets the serviceStopTime value for this DocumentEntry.
     * 
     * @param serviceStopTime
     */
    public void setServiceStopTime(it.csi.esenred.esenpatweb.business.gateway.TimeStamp serviceStopTime) {
        this.serviceStopTime = serviceStopTime;
    }


    /**
     * Gets the classCode value for this DocumentEntry.
     * 
     * @return classCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code getClassCode() {
        return classCode;
    }


    /**
     * Sets the classCode value for this DocumentEntry.
     * 
     * @param classCode
     */
    public void setClassCode(it.csi.esenred.esenpatweb.business.gateway.Code classCode) {
        this.classCode = classCode;
    }


    /**
     * Gets the confidentialityCode value for this DocumentEntry.
     * 
     * @return confidentialityCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code[] getConfidentialityCode() {
        return confidentialityCode;
    }


    /**
     * Sets the confidentialityCode value for this DocumentEntry.
     * 
     * @param confidentialityCode
     */
    public void setConfidentialityCode(it.csi.esenred.esenpatweb.business.gateway.Code[] confidentialityCode) {
        this.confidentialityCode = confidentialityCode;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Code getConfidentialityCode(int i) {
        return this.confidentialityCode[i];
    }

    public void setConfidentialityCode(int i, it.csi.esenred.esenpatweb.business.gateway.Code _value) {
        this.confidentialityCode[i] = _value;
    }


    /**
     * Gets the eventCode value for this DocumentEntry.
     * 
     * @return eventCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code[] getEventCode() {
        return eventCode;
    }


    /**
     * Sets the eventCode value for this DocumentEntry.
     * 
     * @param eventCode
     */
    public void setEventCode(it.csi.esenred.esenpatweb.business.gateway.Code[] eventCode) {
        this.eventCode = eventCode;
    }

    public it.csi.esenred.esenpatweb.business.gateway.Code getEventCode(int i) {
        return this.eventCode[i];
    }

    public void setEventCode(int i, it.csi.esenred.esenpatweb.business.gateway.Code _value) {
        this.eventCode[i] = _value;
    }


    /**
     * Gets the formatCode value for this DocumentEntry.
     * 
     * @return formatCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code getFormatCode() {
        return formatCode;
    }


    /**
     * Sets the formatCode value for this DocumentEntry.
     * 
     * @param formatCode
     */
    public void setFormatCode(it.csi.esenred.esenpatweb.business.gateway.Code formatCode) {
        this.formatCode = formatCode;
    }


    /**
     * Gets the healthcareFacilityTypeCode value for this DocumentEntry.
     * 
     * @return healthcareFacilityTypeCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code getHealthcareFacilityTypeCode() {
        return healthcareFacilityTypeCode;
    }


    /**
     * Sets the healthcareFacilityTypeCode value for this DocumentEntry.
     * 
     * @param healthcareFacilityTypeCode
     */
    public void setHealthcareFacilityTypeCode(it.csi.esenred.esenpatweb.business.gateway.Code healthcareFacilityTypeCode) {
        this.healthcareFacilityTypeCode = healthcareFacilityTypeCode;
    }


    /**
     * Gets the languageCode value for this DocumentEntry.
     * 
     * @return languageCode
     */
    public java.lang.String getLanguageCode() {
        return languageCode;
    }


    /**
     * Sets the languageCode value for this DocumentEntry.
     * 
     * @param languageCode
     */
    public void setLanguageCode(java.lang.String languageCode) {
        this.languageCode = languageCode;
    }


    /**
     * Gets the practiceSettingCode value for this DocumentEntry.
     * 
     * @return practiceSettingCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code getPracticeSettingCode() {
        return practiceSettingCode;
    }


    /**
     * Sets the practiceSettingCode value for this DocumentEntry.
     * 
     * @param practiceSettingCode
     */
    public void setPracticeSettingCode(it.csi.esenred.esenpatweb.business.gateway.Code practiceSettingCode) {
        this.practiceSettingCode = practiceSettingCode;
    }


    /**
     * Gets the typeCode value for this DocumentEntry.
     * 
     * @return typeCode
     */
    public it.csi.esenred.esenpatweb.business.gateway.Code getTypeCode() {
        return typeCode;
    }


    /**
     * Sets the typeCode value for this DocumentEntry.
     * 
     * @param typeCode
     */
    public void setTypeCode(it.csi.esenred.esenpatweb.business.gateway.Code typeCode) {
        this.typeCode = typeCode;
    }


    /**
     * Gets the repositoryUniqueId value for this DocumentEntry.
     * 
     * @return repositoryUniqueId
     */
    public java.lang.String getRepositoryUniqueId() {
        return repositoryUniqueId;
    }


    /**
     * Sets the repositoryUniqueId value for this DocumentEntry.
     * 
     * @param repositoryUniqueId
     */
    public void setRepositoryUniqueId(java.lang.String repositoryUniqueId) {
        this.repositoryUniqueId = repositoryUniqueId;
    }


    /**
     * Gets the mimeType value for this DocumentEntry.
     * 
     * @return mimeType
     */
    public java.lang.String getMimeType() {
        return mimeType;
    }


    /**
     * Sets the mimeType value for this DocumentEntry.
     * 
     * @param mimeType
     */
    public void setMimeType(java.lang.String mimeType) {
        this.mimeType = mimeType;
    }


    /**
     * Gets the size value for this DocumentEntry.
     * 
     * @return size
     */
    public java.lang.Long getSize() {
        return size;
    }


    /**
     * Sets the size value for this DocumentEntry.
     * 
     * @param size
     */
    public void setSize(java.lang.Long size) {
        this.size = size;
    }


    /**
     * Gets the hash value for this DocumentEntry.
     * 
     * @return hash
     */
    public java.lang.String getHash() {
        return hash;
    }


    /**
     * Sets the hash value for this DocumentEntry.
     * 
     * @param hash
     */
    public void setHash(java.lang.String hash) {
        this.hash = hash;
    }


    /**
     * Gets the uri value for this DocumentEntry.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this DocumentEntry.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }


    /**
     * Gets the type value for this DocumentEntry.
     * 
     * @return type
     */
    public it.csi.esenred.esenpatweb.business.gateway.DocumentEntryType getType() {
        return type;
    }


    /**
     * Sets the type value for this DocumentEntry.
     * 
     * @param type
     */
    public void setType(it.csi.esenred.esenpatweb.business.gateway.DocumentEntryType type) {
        this.type = type;
    }


    /**
     * Gets the referenceIdList value for this DocumentEntry.
     * 
     * @return referenceIdList
     */
    public it.csi.esenred.esenpatweb.business.gateway.ReferenceId[] getReferenceIdList() {
        return referenceIdList;
    }


    /**
     * Sets the referenceIdList value for this DocumentEntry.
     * 
     * @param referenceIdList
     */
    public void setReferenceIdList(it.csi.esenred.esenpatweb.business.gateway.ReferenceId[] referenceIdList) {
        this.referenceIdList = referenceIdList;
    }

    public it.csi.esenred.esenpatweb.business.gateway.ReferenceId getReferenceIdList(int i) {
        return this.referenceIdList[i];
    }

    public void setReferenceIdList(int i, it.csi.esenred.esenpatweb.business.gateway.ReferenceId _value) {
        this.referenceIdList[i] = _value;
    }


    /**
     * Gets the documentAvailability value for this DocumentEntry.
     * 
     * @return documentAvailability
     */
    public it.csi.esenred.esenpatweb.business.gateway.DocumentAvailability getDocumentAvailability() {
        return documentAvailability;
    }


    /**
     * Sets the documentAvailability value for this DocumentEntry.
     * 
     * @param documentAvailability
     */
    public void setDocumentAvailability(it.csi.esenred.esenpatweb.business.gateway.DocumentAvailability documentAvailability) {
        this.documentAvailability = documentAvailability;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentEntry)) return false;
        DocumentEntry other = (DocumentEntry) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sourcePatientId==null && other.getSourcePatientId()==null) || 
             (this.sourcePatientId!=null &&
              this.sourcePatientId.equals(other.getSourcePatientId()))) &&
            ((this.sourcePatientInfo==null && other.getSourcePatientInfo()==null) || 
             (this.sourcePatientInfo!=null &&
              this.sourcePatientInfo.equals(other.getSourcePatientInfo()))) &&
            ((this.creationTime==null && other.getCreationTime()==null) || 
             (this.creationTime!=null &&
              this.creationTime.equals(other.getCreationTime()))) &&
            ((this.author==null && other.getAuthor()==null) || 
             (this.author!=null &&
              java.util.Arrays.equals(this.author, other.getAuthor()))) &&
            ((this.legalAuthenticator==null && other.getLegalAuthenticator()==null) || 
             (this.legalAuthenticator!=null &&
              this.legalAuthenticator.equals(other.getLegalAuthenticator()))) &&
            ((this.serviceStartTime==null && other.getServiceStartTime()==null) || 
             (this.serviceStartTime!=null &&
              this.serviceStartTime.equals(other.getServiceStartTime()))) &&
            ((this.serviceStopTime==null && other.getServiceStopTime()==null) || 
             (this.serviceStopTime!=null &&
              this.serviceStopTime.equals(other.getServiceStopTime()))) &&
            ((this.classCode==null && other.getClassCode()==null) || 
             (this.classCode!=null &&
              this.classCode.equals(other.getClassCode()))) &&
            ((this.confidentialityCode==null && other.getConfidentialityCode()==null) || 
             (this.confidentialityCode!=null &&
              java.util.Arrays.equals(this.confidentialityCode, other.getConfidentialityCode()))) &&
            ((this.eventCode==null && other.getEventCode()==null) || 
             (this.eventCode!=null &&
              java.util.Arrays.equals(this.eventCode, other.getEventCode()))) &&
            ((this.formatCode==null && other.getFormatCode()==null) || 
             (this.formatCode!=null &&
              this.formatCode.equals(other.getFormatCode()))) &&
            ((this.healthcareFacilityTypeCode==null && other.getHealthcareFacilityTypeCode()==null) || 
             (this.healthcareFacilityTypeCode!=null &&
              this.healthcareFacilityTypeCode.equals(other.getHealthcareFacilityTypeCode()))) &&
            ((this.languageCode==null && other.getLanguageCode()==null) || 
             (this.languageCode!=null &&
              this.languageCode.equals(other.getLanguageCode()))) &&
            ((this.practiceSettingCode==null && other.getPracticeSettingCode()==null) || 
             (this.practiceSettingCode!=null &&
              this.practiceSettingCode.equals(other.getPracticeSettingCode()))) &&
            ((this.typeCode==null && other.getTypeCode()==null) || 
             (this.typeCode!=null &&
              this.typeCode.equals(other.getTypeCode()))) &&
            ((this.repositoryUniqueId==null && other.getRepositoryUniqueId()==null) || 
             (this.repositoryUniqueId!=null &&
              this.repositoryUniqueId.equals(other.getRepositoryUniqueId()))) &&
            ((this.mimeType==null && other.getMimeType()==null) || 
             (this.mimeType!=null &&
              this.mimeType.equals(other.getMimeType()))) &&
            ((this.size==null && other.getSize()==null) || 
             (this.size!=null &&
              this.size.equals(other.getSize()))) &&
            ((this.hash==null && other.getHash()==null) || 
             (this.hash!=null &&
              this.hash.equals(other.getHash()))) &&
            ((this.uri==null && other.getUri()==null) || 
             (this.uri!=null &&
              this.uri.equals(other.getUri()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.referenceIdList==null && other.getReferenceIdList()==null) || 
             (this.referenceIdList!=null &&
              java.util.Arrays.equals(this.referenceIdList, other.getReferenceIdList()))) &&
            ((this.documentAvailability==null && other.getDocumentAvailability()==null) || 
             (this.documentAvailability!=null &&
              this.documentAvailability.equals(other.getDocumentAvailability())));
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
        if (getSourcePatientId() != null) {
            _hashCode += getSourcePatientId().hashCode();
        }
        if (getSourcePatientInfo() != null) {
            _hashCode += getSourcePatientInfo().hashCode();
        }
        if (getCreationTime() != null) {
            _hashCode += getCreationTime().hashCode();
        }
        if (getAuthor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthor(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLegalAuthenticator() != null) {
            _hashCode += getLegalAuthenticator().hashCode();
        }
        if (getServiceStartTime() != null) {
            _hashCode += getServiceStartTime().hashCode();
        }
        if (getServiceStopTime() != null) {
            _hashCode += getServiceStopTime().hashCode();
        }
        if (getClassCode() != null) {
            _hashCode += getClassCode().hashCode();
        }
        if (getConfidentialityCode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConfidentialityCode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConfidentialityCode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEventCode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEventCode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEventCode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFormatCode() != null) {
            _hashCode += getFormatCode().hashCode();
        }
        if (getHealthcareFacilityTypeCode() != null) {
            _hashCode += getHealthcareFacilityTypeCode().hashCode();
        }
        if (getLanguageCode() != null) {
            _hashCode += getLanguageCode().hashCode();
        }
        if (getPracticeSettingCode() != null) {
            _hashCode += getPracticeSettingCode().hashCode();
        }
        if (getTypeCode() != null) {
            _hashCode += getTypeCode().hashCode();
        }
        if (getRepositoryUniqueId() != null) {
            _hashCode += getRepositoryUniqueId().hashCode();
        }
        if (getMimeType() != null) {
            _hashCode += getMimeType().hashCode();
        }
        if (getSize() != null) {
            _hashCode += getSize().hashCode();
        }
        if (getHash() != null) {
            _hashCode += getHash().hashCode();
        }
        if (getUri() != null) {
            _hashCode += getUri().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getReferenceIdList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReferenceIdList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReferenceIdList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumentAvailability() != null) {
            _hashCode += getDocumentAvailability().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourcePatientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "sourcePatientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Identifiable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourcePatientInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "sourcePatientInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "PatientInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "creationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Timestamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("author");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Author"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("legalAuthenticator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "legalAuthenticator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Person"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceStartTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "serviceStartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Timestamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceStopTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "serviceStopTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Timestamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "classCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confidentialityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "confidentialityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "eventCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "formatCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("healthcareFacilityTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "healthcareFacilityTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("languageCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "languageCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("practiceSettingCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "practiceSettingCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "typeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repositoryUniqueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "repositoryUniqueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mimeType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "mimeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("size");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "size"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hash");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "hash"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uri");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentEntryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceIdList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "referenceIdList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "ReferenceId"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentAvailability");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "documentAvailability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "DocumentAvailability"));
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
