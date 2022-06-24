/**
 * AssociationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class AssociationType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    
    protected AssociationType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _APND = "APND";
    public static final java.lang.String _RPLC = "RPLC";
    public static final java.lang.String _XFRM = "XFRM";
    public static final java.lang.String _XFRM_RPLC = "XFRM_RPLC";
    public static final java.lang.String _HasMember = "HasMember";
    public static final java.lang.String _signs = "signs";
    public static final java.lang.String _IsSnapshotOf = "IsSnapshotOf";
    public static final java.lang.String _UpdateAvailabilityStatus = "UpdateAvailabilityStatus";
    public static final java.lang.String _SubmitAssociation = "SubmitAssociation";
    public static final AssociationType APND = new AssociationType(_APND);
    public static final AssociationType RPLC = new AssociationType(_RPLC);
    public static final AssociationType XFRM = new AssociationType(_XFRM);
    public static final AssociationType XFRM_RPLC = new AssociationType(_XFRM_RPLC);
    public static final AssociationType HasMember = new AssociationType(_HasMember);
    public static final AssociationType signs = new AssociationType(_signs);
    public static final AssociationType IsSnapshotOf = new AssociationType(_IsSnapshotOf);
    public static final AssociationType UpdateAvailabilityStatus = new AssociationType(_UpdateAvailabilityStatus);
    public static final AssociationType SubmitAssociation = new AssociationType(_SubmitAssociation);
    public java.lang.String getValue() { return _value_;}
    public static AssociationType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        AssociationType enumeration = (AssociationType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static AssociationType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(AssociationType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.openehealth.org/ipf/xds", "AssociationType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
