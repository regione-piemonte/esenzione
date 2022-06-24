/**
 * SubmissionSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class SubmissionSet extends it.csi.esenred.esenpatweb.business.gateway.IdentifiedObject
		implements java.io.Serializable {
	private java.lang.String sourceId;

	private it.csi.esenred.esenpatweb.business.gateway.TimeStamp submissionTime;

	private it.csi.esenred.esenpatweb.business.gateway.Author[] author;

	private it.csi.esenred.esenpatweb.business.gateway.Recipient[] intendedRecipient;

	private it.csi.esenred.esenpatweb.business.gateway.Code contentTypeCode;

	public SubmissionSet() {
	}

	public SubmissionSet(java.lang.String homeCommunityId, java.lang.String entryUuid, java.lang.String logicalUuid,
			it.csi.esenred.esenpatweb.business.gateway.Version version, java.lang.String uniqueId,
			it.csi.esenred.esenpatweb.business.gateway.Identifiable patientId,
			it.csi.esenred.esenpatweb.business.gateway.AvailabilityStatus availabilityStatus,
			it.csi.esenred.esenpatweb.business.gateway.LocalizedString title,
			it.csi.esenred.esenpatweb.business.gateway.LocalizedString comments, boolean limitedMetadata,
			java.lang.Object extraMetadata, java.lang.String sourceId,
			it.csi.esenred.esenpatweb.business.gateway.TimeStamp submissionTime,
			it.csi.esenred.esenpatweb.business.gateway.Author[] author,
			it.csi.esenred.esenpatweb.business.gateway.Recipient[] intendedRecipient,
			it.csi.esenred.esenpatweb.business.gateway.Code contentTypeCode) {
		super(homeCommunityId, entryUuid, logicalUuid, version, uniqueId, patientId, availabilityStatus, title,
				comments, limitedMetadata, extraMetadata);
		this.sourceId = sourceId;
		this.submissionTime = submissionTime;
		this.author = author;
		this.intendedRecipient = intendedRecipient;
		this.contentTypeCode = contentTypeCode;
	}

	/**
	 * Gets the sourceId value for this SubmissionSet.
	 * 
	 * @return sourceId
	 */
	public java.lang.String getSourceId() {
		return sourceId;
	}

	/**
	 * Sets the sourceId value for this SubmissionSet.
	 * 
	 * @param sourceId
	 */
	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * Gets the submissionTime value for this SubmissionSet.
	 * 
	 * @return submissionTime
	 */
	public it.csi.esenred.esenpatweb.business.gateway.TimeStamp getSubmissionTime() {
		return submissionTime;
	}

	/**
	 * Sets the submissionTime value for this SubmissionSet.
	 * 
	 * @param submissionTime
	 */
	public void setSubmissionTime(it.csi.esenred.esenpatweb.business.gateway.TimeStamp submissionTime) {
		this.submissionTime = submissionTime;
	}

	/**
	 * Gets the author value for this SubmissionSet.
	 * 
	 * @return author
	 */
	public it.csi.esenred.esenpatweb.business.gateway.Author[] getAuthor() {
		return author;
	}

	/**
	 * Sets the author value for this SubmissionSet.
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
	 * Gets the intendedRecipient value for this SubmissionSet.
	 * 
	 * @return intendedRecipient
	 */
	public it.csi.esenred.esenpatweb.business.gateway.Recipient[] getIntendedRecipient() {
		return intendedRecipient;
	}

	/**
	 * Sets the intendedRecipient value for this SubmissionSet.
	 * 
	 * @param intendedRecipient
	 */
	public void setIntendedRecipient(it.csi.esenred.esenpatweb.business.gateway.Recipient[] intendedRecipient) {
		this.intendedRecipient = intendedRecipient;
	}

	public it.csi.esenred.esenpatweb.business.gateway.Recipient getIntendedRecipient(int i) {
		return this.intendedRecipient[i];
	}

	public void setIntendedRecipient(int i, it.csi.esenred.esenpatweb.business.gateway.Recipient _value) {
		this.intendedRecipient[i] = _value;
	}

	/**
	 * Gets the contentTypeCode value for this SubmissionSet.
	 * 
	 * @return contentTypeCode
	 */
	public it.csi.esenred.esenpatweb.business.gateway.Code getContentTypeCode() {
		return contentTypeCode;
	}

	/**
	 * Sets the contentTypeCode value for this SubmissionSet.
	 * 
	 * @param contentTypeCode
	 */
	public void setContentTypeCode(it.csi.esenred.esenpatweb.business.gateway.Code contentTypeCode) {
		this.contentTypeCode = contentTypeCode;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof SubmissionSet))
			return false;
		SubmissionSet other = (SubmissionSet) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj)
				&& ((this.sourceId == null && other.getSourceId() == null)
						|| (this.sourceId != null && this.sourceId.equals(other.getSourceId())))
				&& ((this.submissionTime == null && other.getSubmissionTime() == null)
						|| (this.submissionTime != null && this.submissionTime.equals(other.getSubmissionTime())))
				&& ((this.author == null && other.getAuthor() == null)
						|| (this.author != null && java.util.Arrays.equals(this.author, other.getAuthor())))
				&& ((this.intendedRecipient == null && other.getIntendedRecipient() == null)
						|| (this.intendedRecipient != null
								&& java.util.Arrays.equals(this.intendedRecipient, other.getIntendedRecipient())))
				&& ((this.contentTypeCode == null && other.getContentTypeCode() == null)
						|| (this.contentTypeCode != null && this.contentTypeCode.equals(other.getContentTypeCode())));
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
		if (getSourceId() != null) {
			_hashCode += getSourceId().hashCode();
		}
		if (getSubmissionTime() != null) {
			_hashCode += getSubmissionTime().hashCode();
		}
		if (getAuthor() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getAuthor()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getAuthor(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		if (getIntendedRecipient() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getIntendedRecipient()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getIntendedRecipient(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		if (getContentTypeCode() != null) {
			_hashCode += getContentTypeCode().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			SubmissionSet.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "SubmissionSet"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sourceId");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "sourceId"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("submissionTime");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "submissionTime"));
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
		elemField.setFieldName("intendedRecipient");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "intendedRecipient"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Recipient"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("contentTypeCode");
		elemField.setXmlName(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "contentTypeCode"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Code"));
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
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
