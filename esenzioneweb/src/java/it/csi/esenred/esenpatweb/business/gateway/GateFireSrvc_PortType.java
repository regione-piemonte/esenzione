/**
 * GateFireSrvc_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.gateway;

public interface GateFireSrvc_PortType extends java.rmi.Remote {

    /**
     * firma PAdES con archiviazione del documento firmato su repository
     * - se otp specificato: firma remota. Se otp nullo: firma automatica.
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException;

    /**
     * Verifica disponibilita' servizio di firma
     */
    public it.csi.esenred.esenpatweb.business.gateway.PingResult ping(java.lang.String user, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException;

    /**
     * Archiviazione di un documento su repository.
     */
    public it.csi.esenred.esenpatweb.business.gateway.Result archivia(it.csi.esenred.esenpatweb.business.gateway.RepositoryInput repositoryInput, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException;

    /**
     * firma PAdES Automatica con archiviazione del documento firmato
     * su repository.
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESAutomaticaConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException;

    /**
     * firma PAdES Automatica
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESAutomatica(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity) throws java.rmi.RemoteException;

    /**
     * firma PAdES Massiva Remota
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassivaRemota(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) throws java.rmi.RemoteException;

    /**
     * firma PAdES - se otp specificato: firma remota. Se otp nullo:
     * firma automatica.
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdES(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity) throws java.rmi.RemoteException;

    /**
     * Marca temporale
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult marcaTemporale(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.MarkInput markInput) throws java.rmi.RemoteException;

    /**
     * verifica firma
     */
    public it.csi.esenred.esenpatweb.business.gateway.SignVerifyResult verificaFirma(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException;

    /**
     * verifica marca detached
     */
    public it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult verificaMarcaDetached(it.csi.esenred.esenpatweb.business.gateway.Attachment mark, it.csi.esenred.esenpatweb.business.gateway.Attachment originalFile, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException;

    /**
     * firma PAdES Massiva  - se otp specificato: firma remota. Se
     * otp nullo: firma automatica.
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassiva(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.Identity identity) throws java.rmi.RemoteException;

    /**
     * firma PAdES remota
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESRemota(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity) throws java.rmi.RemoteException;

    /**
     * Apertura sessione di firma multipla. Si ottiene il session
     * id da utlizzare nelle chiamate successive
     */
    public it.csi.esenred.esenpatweb.business.gateway.SessionIdResult openSession(it.csi.esenred.esenpatweb.business.gateway.SessionInput sessionInput) throws java.rmi.RemoteException;

    /**
     * richiesta otp
     */
    public it.csi.esenred.esenpatweb.business.gateway.OtpResult richiestaOtp(it.csi.esenred.esenpatweb.business.gateway.OtpReqInput otpReqInput) throws java.rmi.RemoteException;

    /**
     * Chiusura sessione di firma multipla.
     */
    public it.csi.esenred.esenpatweb.business.gateway.Result closeSession(java.lang.String sessionId, it.csi.esenred.esenpatweb.business.gateway.SessionInput sessionInput) throws java.rmi.RemoteException;

    /**
     * firma PAdES remota con archiviazione del documento firmato
     * su repository.
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESRemotaConArchiviaz(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.SignIdentity identity, it.csi.esenred.esenpatweb.business.gateway.ItiMetadata metadata, it.csi.esenred.esenpatweb.business.gateway.AssertionIdentity assertionIdentity) throws java.rmi.RemoteException;

    /**
     * firma PAdES Massiva Automatica
     */
    public it.csi.esenred.esenpatweb.business.gateway.FileResult firmaPAdESMassivaAutomatica(it.csi.esenred.esenpatweb.business.gateway.Attachment[] attachment, it.csi.esenred.esenpatweb.business.gateway.PadesInput padesInput, it.csi.esenred.esenpatweb.business.gateway.AutoSignIdentity identity) throws java.rmi.RemoteException;

    /**
     * verifica marca
     */
    public it.csi.esenred.esenpatweb.business.gateway.MarkVerifyResult verificaMarca(it.csi.esenred.esenpatweb.business.gateway.Attachment attachment, it.csi.esenred.esenpatweb.business.gateway.CallInfo callInfo) throws java.rmi.RemoteException;
}
