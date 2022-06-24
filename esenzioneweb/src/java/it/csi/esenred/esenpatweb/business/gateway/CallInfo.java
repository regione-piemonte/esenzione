/**
 * CallInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class CallInfo  implements java.io.Serializable {
    private java.lang.String applicationCode;

    private java.lang.String caCode;

    private java.lang.String codiceFiscale;

    private java.lang.String collocazione;

    private java.lang.String dominio;

    public CallInfo() {
    }

    public CallInfo(
           java.lang.String applicationCode,
           java.lang.String caCode,
           java.lang.String codiceFiscale,
           java.lang.String collocazione,
           java.lang.String dominio) {
           this.applicationCode = applicationCode;
           this.caCode = caCode;
           this.codiceFiscale = codiceFiscale;
           this.collocazione = collocazione;
           this.dominio = dominio;
    }


    /**
     * Gets the applicationCode value for this CallInfo.
     * 
     * @return applicationCode
     */
    public java.lang.String getApplicationCode() {
        return applicationCode;
    }


    /**
     * Sets the applicationCode value for this CallInfo.
     * 
     * @param applicationCode
     */
    public void setApplicationCode(java.lang.String applicationCode) {
        this.applicationCode = applicationCode;
    }


    /**
     * Gets the caCode value for this CallInfo.
     * 
     * @return caCode
     */
    public java.lang.String getCaCode() {
        return caCode;
    }


    /**
     * Sets the caCode value for this CallInfo.
     * 
     * @param caCode
     */
    public void setCaCode(java.lang.String caCode) {
        this.caCode = caCode;
    }


    /**
     * Gets the codiceFiscale value for this CallInfo.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this CallInfo.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the collocazione value for this CallInfo.
     * 
     * @return collocazione
     */
    public java.lang.String getCollocazione() {
        return collocazione;
    }


    /**
     * Sets the collocazione value for this CallInfo.
     * 
     * @param collocazione
     */
    public void setCollocazione(java.lang.String collocazione) {
        this.collocazione = collocazione;
    }


    /**
     * Gets the dominio value for this CallInfo.
     * 
     * @return dominio
     */
    public java.lang.String getDominio() {
        return dominio;
    }


    /**
     * Sets the dominio value for this CallInfo.
     * 
     * @param dominio
     */
    public void setDominio(java.lang.String dominio) {
        this.dominio = dominio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CallInfo)) return false;
        CallInfo other = (CallInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationCode==null && other.getApplicationCode()==null) || 
             (this.applicationCode!=null &&
              this.applicationCode.equals(other.getApplicationCode()))) &&
            ((this.caCode==null && other.getCaCode()==null) || 
             (this.caCode!=null &&
              this.caCode.equals(other.getCaCode()))) &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.collocazione==null && other.getCollocazione()==null) || 
             (this.collocazione!=null &&
              this.collocazione.equals(other.getCollocazione()))) &&
            ((this.dominio==null && other.getDominio()==null) || 
             (this.dominio!=null &&
              this.dominio.equals(other.getDominio())));
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
        if (getApplicationCode() != null) {
            _hashCode += getApplicationCode().hashCode();
        }
        if (getCaCode() != null) {
            _hashCode += getCaCode().hashCode();
        }
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getCollocazione() != null) {
            _hashCode += getCollocazione().hashCode();
        }
        if (getDominio() != null) {
            _hashCode += getDominio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CallInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "callInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "caCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collocazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "collocazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dominio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dominio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
