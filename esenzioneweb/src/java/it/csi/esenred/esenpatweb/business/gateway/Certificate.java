/**
 * Certificate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Certificate  implements java.io.Serializable {
    private it.csi.esenred.esenpatweb.business.gateway.CertPolicy[] certPolicyList;

    private it.csi.esenred.esenpatweb.business.gateway.Issuer issuer;

    private java.lang.String name;

    private it.csi.esenred.esenpatweb.business.gateway.RevocationInfo revocationInfo;

    private java.lang.String sn;

    private it.csi.esenred.esenpatweb.business.gateway.Subject subject;

    private java.lang.Boolean timeValid;

    private java.lang.Boolean valid;

    private java.util.Calendar validFrom;

    private java.util.Calendar validTo;

    public Certificate() {
    }

    public Certificate(
           it.csi.esenred.esenpatweb.business.gateway.CertPolicy[] certPolicyList,
           it.csi.esenred.esenpatweb.business.gateway.Issuer issuer,
           java.lang.String name,
           it.csi.esenred.esenpatweb.business.gateway.RevocationInfo revocationInfo,
           java.lang.String sn,
           it.csi.esenred.esenpatweb.business.gateway.Subject subject,
           java.lang.Boolean timeValid,
           java.lang.Boolean valid,
           java.util.Calendar validFrom,
           java.util.Calendar validTo) {
           this.certPolicyList = certPolicyList;
           this.issuer = issuer;
           this.name = name;
           this.revocationInfo = revocationInfo;
           this.sn = sn;
           this.subject = subject;
           this.timeValid = timeValid;
           this.valid = valid;
           this.validFrom = validFrom;
           this.validTo = validTo;
    }


    /**
     * Gets the certPolicyList value for this Certificate.
     * 
     * @return certPolicyList
     */
    public it.csi.esenred.esenpatweb.business.gateway.CertPolicy[] getCertPolicyList() {
        return certPolicyList;
    }


    /**
     * Sets the certPolicyList value for this Certificate.
     * 
     * @param certPolicyList
     */
    public void setCertPolicyList(it.csi.esenred.esenpatweb.business.gateway.CertPolicy[] certPolicyList) {
        this.certPolicyList = certPolicyList;
    }

    public it.csi.esenred.esenpatweb.business.gateway.CertPolicy getCertPolicyList(int i) {
        return this.certPolicyList[i];
    }

    public void setCertPolicyList(int i, it.csi.esenred.esenpatweb.business.gateway.CertPolicy _value) {
        this.certPolicyList[i] = _value;
    }


    /**
     * Gets the issuer value for this Certificate.
     * 
     * @return issuer
     */
    public it.csi.esenred.esenpatweb.business.gateway.Issuer getIssuer() {
        return issuer;
    }


    /**
     * Sets the issuer value for this Certificate.
     * 
     * @param issuer
     */
    public void setIssuer(it.csi.esenred.esenpatweb.business.gateway.Issuer issuer) {
        this.issuer = issuer;
    }


    /**
     * Gets the name value for this Certificate.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Certificate.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the revocationInfo value for this Certificate.
     * 
     * @return revocationInfo
     */
    public it.csi.esenred.esenpatweb.business.gateway.RevocationInfo getRevocationInfo() {
        return revocationInfo;
    }


    /**
     * Sets the revocationInfo value for this Certificate.
     * 
     * @param revocationInfo
     */
    public void setRevocationInfo(it.csi.esenred.esenpatweb.business.gateway.RevocationInfo revocationInfo) {
        this.revocationInfo = revocationInfo;
    }


    /**
     * Gets the sn value for this Certificate.
     * 
     * @return sn
     */
    public java.lang.String getSn() {
        return sn;
    }


    /**
     * Sets the sn value for this Certificate.
     * 
     * @param sn
     */
    public void setSn(java.lang.String sn) {
        this.sn = sn;
    }


    /**
     * Gets the subject value for this Certificate.
     * 
     * @return subject
     */
    public it.csi.esenred.esenpatweb.business.gateway.Subject getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Certificate.
     * 
     * @param subject
     */
    public void setSubject(it.csi.esenred.esenpatweb.business.gateway.Subject subject) {
        this.subject = subject;
    }


    /**
     * Gets the timeValid value for this Certificate.
     * 
     * @return timeValid
     */
    public java.lang.Boolean getTimeValid() {
        return timeValid;
    }


    /**
     * Sets the timeValid value for this Certificate.
     * 
     * @param timeValid
     */
    public void setTimeValid(java.lang.Boolean timeValid) {
        this.timeValid = timeValid;
    }


    /**
     * Gets the valid value for this Certificate.
     * 
     * @return valid
     */
    public java.lang.Boolean getValid() {
        return valid;
    }


    /**
     * Sets the valid value for this Certificate.
     * 
     * @param valid
     */
    public void setValid(java.lang.Boolean valid) {
        this.valid = valid;
    }


    /**
     * Gets the validFrom value for this Certificate.
     * 
     * @return validFrom
     */
    public java.util.Calendar getValidFrom() {
        return validFrom;
    }


    /**
     * Sets the validFrom value for this Certificate.
     * 
     * @param validFrom
     */
    public void setValidFrom(java.util.Calendar validFrom) {
        this.validFrom = validFrom;
    }


    /**
     * Gets the validTo value for this Certificate.
     * 
     * @return validTo
     */
    public java.util.Calendar getValidTo() {
        return validTo;
    }


    /**
     * Sets the validTo value for this Certificate.
     * 
     * @param validTo
     */
    public void setValidTo(java.util.Calendar validTo) {
        this.validTo = validTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Certificate)) return false;
        Certificate other = (Certificate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.certPolicyList==null && other.getCertPolicyList()==null) || 
             (this.certPolicyList!=null &&
              java.util.Arrays.equals(this.certPolicyList, other.getCertPolicyList()))) &&
            ((this.issuer==null && other.getIssuer()==null) || 
             (this.issuer!=null &&
              this.issuer.equals(other.getIssuer()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.revocationInfo==null && other.getRevocationInfo()==null) || 
             (this.revocationInfo!=null &&
              this.revocationInfo.equals(other.getRevocationInfo()))) &&
            ((this.sn==null && other.getSn()==null) || 
             (this.sn!=null &&
              this.sn.equals(other.getSn()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.timeValid==null && other.getTimeValid()==null) || 
             (this.timeValid!=null &&
              this.timeValid.equals(other.getTimeValid()))) &&
            ((this.valid==null && other.getValid()==null) || 
             (this.valid!=null &&
              this.valid.equals(other.getValid()))) &&
            ((this.validFrom==null && other.getValidFrom()==null) || 
             (this.validFrom!=null &&
              this.validFrom.equals(other.getValidFrom()))) &&
            ((this.validTo==null && other.getValidTo()==null) || 
             (this.validTo!=null &&
              this.validTo.equals(other.getValidTo())));
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
        if (getCertPolicyList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCertPolicyList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCertPolicyList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIssuer() != null) {
            _hashCode += getIssuer().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getRevocationInfo() != null) {
            _hashCode += getRevocationInfo().hashCode();
        }
        if (getSn() != null) {
            _hashCode += getSn().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getTimeValid() != null) {
            _hashCode += getTimeValid().hashCode();
        }
        if (getValid() != null) {
            _hashCode += getValid().hashCode();
        }
        if (getValidFrom() != null) {
            _hashCode += getValidFrom().hashCode();
        }
        if (getValidTo() != null) {
            _hashCode += getValidTo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Certificate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certificate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certPolicyList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certPolicyList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "certPolicy"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issuer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "issuer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "issuer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revocationInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "revocationInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "revocationInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "subject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeValid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
