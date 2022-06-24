/**
 * Precision.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class Precision implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    
    protected Precision(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _YEAR = "YEAR";
    public static final java.lang.String _MONTH = "MONTH";
    public static final java.lang.String _DAY = "DAY";
    public static final java.lang.String _HOUR = "HOUR";
    public static final java.lang.String _MINUTE = "MINUTE";
    public static final java.lang.String _SECOND = "SECOND";
    public static final Precision YEAR = new Precision(_YEAR);
    public static final Precision MONTH = new Precision(_MONTH);
    public static final Precision DAY = new Precision(_DAY);
    public static final Precision HOUR = new Precision(_HOUR);
    public static final Precision MINUTE = new Precision(_MINUTE);
    public static final Precision SECOND = new Precision(_SECOND);
    public java.lang.String getValue() { return _value_;}
    public static Precision fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Precision enumeration = (Precision)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Precision fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Precision.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "Precision"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
