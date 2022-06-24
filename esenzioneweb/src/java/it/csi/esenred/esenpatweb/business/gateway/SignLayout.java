/**
 * SignLayout.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public class SignLayout  implements java.io.Serializable {
    private byte[] image;

    private java.lang.Integer lowLeftX;

    private java.lang.Integer lowLeftY;

    private java.lang.Integer page;

    private java.lang.String reason;

    private boolean showDateTime;

    private java.lang.String text;

    private java.lang.Integer upRightX;

    private java.lang.Integer upRightY;

    public SignLayout() {
    }

    public SignLayout(
           byte[] image,
           java.lang.Integer lowLeftX,
           java.lang.Integer lowLeftY,
           java.lang.Integer page,
           java.lang.String reason,
           boolean showDateTime,
           java.lang.String text,
           java.lang.Integer upRightX,
           java.lang.Integer upRightY) {
           this.image = image;
           this.lowLeftX = lowLeftX;
           this.lowLeftY = lowLeftY;
           this.page = page;
           this.reason = reason;
           this.showDateTime = showDateTime;
           this.text = text;
           this.upRightX = upRightX;
           this.upRightY = upRightY;
    }


    /**
     * Gets the image value for this SignLayout.
     * 
     * @return image
     */
    public byte[] getImage() {
        return image;
    }


    /**
     * Sets the image value for this SignLayout.
     * 
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }


    /**
     * Gets the lowLeftX value for this SignLayout.
     * 
     * @return lowLeftX
     */
    public java.lang.Integer getLowLeftX() {
        return lowLeftX;
    }


    /**
     * Sets the lowLeftX value for this SignLayout.
     * 
     * @param lowLeftX
     */
    public void setLowLeftX(java.lang.Integer lowLeftX) {
        this.lowLeftX = lowLeftX;
    }


    /**
     * Gets the lowLeftY value for this SignLayout.
     * 
     * @return lowLeftY
     */
    public java.lang.Integer getLowLeftY() {
        return lowLeftY;
    }


    /**
     * Sets the lowLeftY value for this SignLayout.
     * 
     * @param lowLeftY
     */
    public void setLowLeftY(java.lang.Integer lowLeftY) {
        this.lowLeftY = lowLeftY;
    }


    /**
     * Gets the page value for this SignLayout.
     * 
     * @return page
     */
    public java.lang.Integer getPage() {
        return page;
    }


    /**
     * Sets the page value for this SignLayout.
     * 
     * @param page
     */
    public void setPage(java.lang.Integer page) {
        this.page = page;
    }


    /**
     * Gets the reason value for this SignLayout.
     * 
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this SignLayout.
     * 
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }


    /**
     * Gets the showDateTime value for this SignLayout.
     * 
     * @return showDateTime
     */
    public boolean isShowDateTime() {
        return showDateTime;
    }


    /**
     * Sets the showDateTime value for this SignLayout.
     * 
     * @param showDateTime
     */
    public void setShowDateTime(boolean showDateTime) {
        this.showDateTime = showDateTime;
    }


    /**
     * Gets the text value for this SignLayout.
     * 
     * @return text
     */
    public java.lang.String getText() {
        return text;
    }


    /**
     * Sets the text value for this SignLayout.
     * 
     * @param text
     */
    public void setText(java.lang.String text) {
        this.text = text;
    }


    /**
     * Gets the upRightX value for this SignLayout.
     * 
     * @return upRightX
     */
    public java.lang.Integer getUpRightX() {
        return upRightX;
    }


    /**
     * Sets the upRightX value for this SignLayout.
     * 
     * @param upRightX
     */
    public void setUpRightX(java.lang.Integer upRightX) {
        this.upRightX = upRightX;
    }


    /**
     * Gets the upRightY value for this SignLayout.
     * 
     * @return upRightY
     */
    public java.lang.Integer getUpRightY() {
        return upRightY;
    }


    /**
     * Sets the upRightY value for this SignLayout.
     * 
     * @param upRightY
     */
    public void setUpRightY(java.lang.Integer upRightY) {
        this.upRightY = upRightY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SignLayout)) return false;
        SignLayout other = (SignLayout) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.image==null && other.getImage()==null) || 
             (this.image!=null &&
              java.util.Arrays.equals(this.image, other.getImage()))) &&
            ((this.lowLeftX==null && other.getLowLeftX()==null) || 
             (this.lowLeftX!=null &&
              this.lowLeftX.equals(other.getLowLeftX()))) &&
            ((this.lowLeftY==null && other.getLowLeftY()==null) || 
             (this.lowLeftY!=null &&
              this.lowLeftY.equals(other.getLowLeftY()))) &&
            ((this.page==null && other.getPage()==null) || 
             (this.page!=null &&
              this.page.equals(other.getPage()))) &&
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            this.showDateTime == other.isShowDateTime() &&
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              this.text.equals(other.getText()))) &&
            ((this.upRightX==null && other.getUpRightX()==null) || 
             (this.upRightX!=null &&
              this.upRightX.equals(other.getUpRightX()))) &&
            ((this.upRightY==null && other.getUpRightY()==null) || 
             (this.upRightY!=null &&
              this.upRightY.equals(other.getUpRightY())));
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
        if (getImage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getImage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getImage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLowLeftX() != null) {
            _hashCode += getLowLeftX().hashCode();
        }
        if (getLowLeftY() != null) {
            _hashCode += getLowLeftY().hashCode();
        }
        if (getPage() != null) {
            _hashCode += getPage().hashCode();
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        _hashCode += (isShowDateTime() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getText() != null) {
            _hashCode += getText().hashCode();
        }
        if (getUpRightX() != null) {
            _hashCode += getUpRightX().hashCode();
        }
        if (getUpRightY() != null) {
            _hashCode += getUpRightY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SignLayout.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.csi.it/gatefire/", "signLayout"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("image");
        elemField.setXmlName(new javax.xml.namespace.QName("", "image"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lowLeftX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lowLeftX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lowLeftY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lowLeftY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("", "page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "showDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("", "text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upRightX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upRightX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upRightY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upRightY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
