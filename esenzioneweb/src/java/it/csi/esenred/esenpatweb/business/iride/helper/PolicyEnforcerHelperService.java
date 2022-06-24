/**
 * PolicyEnforcerHelperService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.esenred.esenpatweb.business.iride.helper;

public interface PolicyEnforcerHelperService extends java.rmi.Remote {
    public boolean isApplicationEsistente(it.csi.esenred.esenpatweb.business.iride.helper.Application in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
    public boolean isUseCaseEsistente(it.csi.esenred.esenpatweb.business.iride.helper.UseCase in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
    public it.csi.esenred.esenpatweb.business.iride.helper.UseCase[] findUseCasesForApplication(it.csi.esenred.esenpatweb.business.iride.helper.Application in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
    public it.csi.esenred.esenpatweb.business.iride.helper.Actor[] findActorsForApplication(it.csi.esenred.esenpatweb.business.iride.helper.Application in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
    public it.csi.esenred.esenpatweb.business.iride.helper.Actor[] findActorsForUseCase(it.csi.esenred.esenpatweb.business.iride.helper.UseCase in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchUseCaseException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
    public it.csi.esenred.esenpatweb.business.iride.helper.Application[] findApplications() throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
    public boolean isActorEsistente(it.csi.esenred.esenpatweb.business.iride.helper.Actor in0) throws java.rmi.RemoteException, it.csi.esenred.esenpatweb.business.iride.helper.UnrecoverableException, it.csi.esenred.esenpatweb.business.iride.helper.SystemException, it.csi.esenred.esenpatweb.business.iride.helper.NoSuchApplicationException, it.csi.esenred.esenpatweb.business.iride.helper.InternalException;
}
