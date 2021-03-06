/**
 * AnagrafeFindCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package it.csi.esenred.esenredweb.business.aura.find;


/**
 *  AnagrafeFindCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class AnagrafeFindCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public AnagrafeFindCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public AnagrafeFindCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for findProfiliAnagrafici method
     * override this method for handling normal response from findProfiliAnagrafici operation
     */
    public void receiveResultfindProfiliAnagrafici(
        it.csi.esenred.esenredweb.business.aura.find.FindProfiliAnagraficiResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from findProfiliAnagrafici operation
     */
    public void receiveErrorfindProfiliAnagrafici(java.lang.Exception e) {
    }
}
