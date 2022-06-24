/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.esenred.esenredweb.business.deleghebe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.esenred.esenredweb.business.deleghebe package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDelegatiServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "getDelegatiServiceResponse");
    private final static QName _RicercaDelegheServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "ricercaDelegheServiceResponse");
    private final static QName _GetDelegantiServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "getDelegantiServiceResponse");
    private final static QName _IsAliveResponse_QNAME = new QName("http://deleghebe.csi.it/", "isAliveResponse");
    private final static QName _InserisciDichiarazioneService_QNAME = new QName("http://deleghebe.csi.it/", "inserisciDichiarazioneService");
    private final static QName _RinunciaDelegato_QNAME = new QName("http://deleghebe.csi.it/", "rinunciaDelegato");
    private final static QName _RinunciaDelegatoServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "rinunciaDelegatoServiceResponse");
    private final static QName _ServizioDelega_QNAME = new QName("http://deleghebe.csi.it/", "servizioDelega");
    private final static QName _Cittadino_QNAME = new QName("http://deleghebe.csi.it/", "cittadino");
    private final static QName _RicercaCfOperatoreService_QNAME = new QName("http://deleghebe.csi.it/", "ricercaCfOperatoreService");
    private final static QName _RicercaDelegheService_QNAME = new QName("http://deleghebe.csi.it/", "ricercaDelegheService");
    private final static QName _AggiornaDelegaServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "aggiornaDelegaServiceResponse");
    private final static QName _GetDelegatiResponse_QNAME = new QName("http://deleghebe.csi.it/", "getDelegatiResponse");
    private final static QName _IsAliveService_QNAME = new QName("http://deleghebe.csi.it/", "isAliveService");
    private final static QName _SaveDelegatiService_QNAME = new QName("http://deleghebe.csi.it/", "saveDelegatiService");
    private final static QName _GetDelegati_QNAME = new QName("http://deleghebe.csi.it/", "getDelegati");
    private final static QName _InserisciCittadinoServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "inserisciCittadinoServiceResponse");
    private final static QName _GetDelegantiResponse_QNAME = new QName("http://deleghebe.csi.it/", "getDelegantiResponse");
    private final static QName _AggiornaDelegaService_QNAME = new QName("http://deleghebe.csi.it/", "aggiornaDelegaService");
    private final static QName _InserisciCittadinoService_QNAME = new QName("http://deleghebe.csi.it/", "inserisciCittadinoService");
    private final static QName _InserisciDichiarazioneServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "inserisciDichiarazioneServiceResponse");
    private final static QName _RicercaDichiarazioniService_QNAME = new QName("http://deleghebe.csi.it/", "ricercaDichiarazioniService");
    private final static QName _SaveDelegatiResponse_QNAME = new QName("http://deleghebe.csi.it/", "saveDelegatiResponse");
    private final static QName _InserisciDelegaService_QNAME = new QName("http://deleghebe.csi.it/", "inserisciDelegaService");
    private final static QName _GetDelegatiService_QNAME = new QName("http://deleghebe.csi.it/", "getDelegatiService");
    private final static QName _AggiornaCittadinoServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "aggiornaCittadinoServiceResponse");
    private final static QName _Codifica_QNAME = new QName("http://deleghebe.csi.it/", "codifica");
    private final static QName _RinunciaDelegatoResponse_QNAME = new QName("http://deleghebe.csi.it/", "rinunciaDelegatoResponse");
    private final static QName _Richiedente_QNAME = new QName("http://deleghebe.csi.it/", "richiedente");
    private final static QName _GetDelegantiService_QNAME = new QName("http://deleghebe.csi.it/", "getDelegantiService");
    private final static QName _RicercaCittadiniServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "ricercaCittadiniServiceResponse");
    private final static QName _RicercaCfOperatoreServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "ricercaCfOperatoreServiceResponse");
    private final static QName _InserisciDelegaServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "inserisciDelegaServiceResponse");
    private final static QName _AggiornaDichiarazioneService_QNAME = new QName("http://deleghebe.csi.it/", "aggiornaDichiarazioneService");
    private final static QName _SaveDelegatiServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "saveDelegatiServiceResponse");
    private final static QName _GetDeleganti_QNAME = new QName("http://deleghebe.csi.it/", "getDeleganti");
    private final static QName _ElencoGenitoriService_QNAME = new QName("http://deleghebe.csi.it/", "elencoGenitoriService");
    private final static QName _SaveDelegati_QNAME = new QName("http://deleghebe.csi.it/", "saveDelegati");
    private final static QName _IsAlive_QNAME = new QName("http://deleghebe.csi.it/", "isAlive");
    private final static QName _AggiornaDichiarazioneServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "aggiornaDichiarazioneServiceResponse");
    private final static QName _ElencoGenitoriServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "elencoGenitoriServiceResponse");
    private final static QName _CittadinoDeleghe_QNAME = new QName("http://deleghebe.csi.it/", "cittadinoDeleghe");
    private final static QName _RicercaDichiarazioniServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "ricercaDichiarazioniServiceResponse");
    private final static QName _RicercaCittadiniService_QNAME = new QName("http://deleghebe.csi.it/", "ricercaCittadiniService");
    private final static QName _IsAliveServiceResponse_QNAME = new QName("http://deleghebe.csi.it/", "isAliveServiceResponse");
    private final static QName _RinunciaDelegatoService_QNAME = new QName("http://deleghebe.csi.it/", "rinunciaDelegatoService");
    private final static QName _AggiornaCittadinoService_QNAME = new QName("http://deleghebe.csi.it/", "aggiornaCittadinoService");
    private final static QName _OperatoreDataModifica_QNAME = new QName("http://deleghebe.csi.it/", "dataModifica");
    private final static QName _OperatoreOpId_QNAME = new QName("http://deleghebe.csi.it/", "opId");
    private final static QName _OperatoreLoginOperazione_QNAME = new QName("http://deleghebe.csi.it/", "loginOperazione");
    private final static QName _OperatoreOpCf_QNAME = new QName("http://deleghebe.csi.it/", "opCf");
    private final static QName _OperatoreDataCreazione_QNAME = new QName("http://deleghebe.csi.it/", "dataCreazione");
    private final static QName _OperatoreOpCognome_QNAME = new QName("http://deleghebe.csi.it/", "opCognome");
    private final static QName _OperatoreOpNome_QNAME = new QName("http://deleghebe.csi.it/", "opNome");
    private final static QName _OperatoreDataCancellazione_QNAME = new QName("http://deleghebe.csi.it/", "dataCancellazione");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.esenred.esenredweb.business.deleghebe
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicazioneRichiedente }
     * 
     */
    public ApplicazioneRichiedente createApplicazioneRichiedente() {
        return new ApplicazioneRichiedente();
    }

    /**
     * Create an instance of {@link RicercaDeleghe.CodiciServizio }
     * 
     */
    public RicercaDeleghe.CodiciServizio createRicercaDelegheCodiciServizio() {
        return new RicercaDeleghe.CodiciServizio();
    }

    /**
     * Create an instance of {@link GetDelegati }
     * 
     */
    public GetDelegati createGetDelegati() {
        return new GetDelegati();
    }

    /**
     * Create an instance of {@link Errore }
     * 
     */
    public Errore createErrore() {
        return new Errore();
    }

    /**
     * Create an instance of {@link DocumentoFile }
     * 
     */
    public DocumentoFile createDocumentoFile() {
        return new DocumentoFile();
    }

    /**
     * Create an instance of {@link TipoEpisodio }
     * 
     */
    public TipoEpisodio createTipoEpisodio() {
        return new TipoEpisodio();
    }

    /**
     * Create an instance of {@link RicercaCfOperatoreService }
     * 
     */
    public RicercaCfOperatoreService createRicercaCfOperatoreService() {
        return new RicercaCfOperatoreService();
    }

    /**
     * Create an instance of {@link StrutturaSanitaria }
     * 
     */
    public StrutturaSanitaria createStrutturaSanitaria() {
        return new StrutturaSanitaria();
    }

    /**
     * Create an instance of {@link RicercaDeleghe }
     * 
     */
    public RicercaDeleghe createRicercaDeleghe() {
        return new RicercaDeleghe();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioni.TipiDichiarazione }
     * 
     */
    public RicercaDichiarazioni.TipiDichiarazione createRicercaDichiarazioniTipiDichiarazione() {
        return new RicercaDichiarazioni.TipiDichiarazione();
    }

    /**
     * Create an instance of {@link ModoDichiarazione }
     * 
     */
    public ModoDichiarazione createModoDichiarazione() {
        return new ModoDichiarazione();
    }

    /**
     * Create an instance of {@link Delega }
     * 
     */
    public Delega createDelega() {
        return new Delega();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioni.StatiDichiarazione }
     * 
     */
    public RicercaDichiarazioni.StatiDichiarazione createRicercaDichiarazioniStatiDichiarazione() {
        return new RicercaDichiarazioni.StatiDichiarazione();
    }

    /**
     * Create an instance of {@link IsAliveServiceResponse }
     * 
     */
    public IsAliveServiceResponse createIsAliveServiceResponse() {
        return new IsAliveServiceResponse();
    }

    /**
     * Create an instance of {@link DichiarazioneRuolo }
     * 
     */
    public DichiarazioneRuolo createDichiarazioneRuolo() {
        return new DichiarazioneRuolo();
    }

    /**
     * Create an instance of {@link InserisciDelegaResponse }
     * 
     */
    public InserisciDelegaResponse createInserisciDelegaResponse() {
        return new InserisciDelegaResponse();
    }

    /**
     * Create an instance of {@link AggiornaDelega }
     * 
     */
    public AggiornaDelega createAggiornaDelega() {
        return new AggiornaDelega();
    }

    /**
     * Create an instance of {@link ElencoGenitori }
     * 
     */
    public ElencoGenitori createElencoGenitori() {
        return new ElencoGenitori();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioniServiceResponse }
     * 
     */
    public RicercaDichiarazioniServiceResponse createRicercaDichiarazioniServiceResponse() {
        return new RicercaDichiarazioniServiceResponse();
    }

    /**
     * Create an instance of {@link AggiornaCittadino }
     * 
     */
    public AggiornaCittadino createAggiornaCittadino() {
        return new AggiornaCittadino();
    }

    /**
     * Create an instance of {@link GetDelegantiService }
     * 
     */
    public GetDelegantiService createGetDelegantiService() {
        return new GetDelegantiService();
    }

    /**
     * Create an instance of {@link UnitaOperativaSanitaria }
     * 
     */
    public UnitaOperativaSanitaria createUnitaOperativaSanitaria() {
        return new UnitaOperativaSanitaria();
    }

    /**
     * Create an instance of {@link Applicazione }
     * 
     */
    public Applicazione createApplicazione() {
        return new Applicazione();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioni.ModiDichiarazione }
     * 
     */
    public RicercaDichiarazioni.ModiDichiarazione createRicercaDichiarazioniModiDichiarazione() {
        return new RicercaDichiarazioni.ModiDichiarazione();
    }

    /**
     * Create an instance of {@link GetDeleganti }
     * 
     */
    public GetDeleganti createGetDeleganti() {
        return new GetDeleganti();
    }

    /**
     * Create an instance of {@link AggiornaDelegaService }
     * 
     */
    public AggiornaDelegaService createAggiornaDelegaService() {
        return new AggiornaDelegaService();
    }

    /**
     * Create an instance of {@link IsAliveResponse }
     * 
     */
    public IsAliveResponse createIsAliveResponse() {
        return new IsAliveResponse();
    }

    /**
     * Create an instance of {@link RicercaCittadiniService }
     * 
     */
    public RicercaCittadiniService createRicercaCittadiniService() {
        return new RicercaCittadiniService();
    }

    /**
     * Create an instance of {@link RinunciaDelegato }
     * 
     */
    public RinunciaDelegato createRinunciaDelegato() {
        return new RinunciaDelegato();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioniResponse }
     * 
     */
    public RicercaDichiarazioniResponse createRicercaDichiarazioniResponse() {
        return new RicercaDichiarazioniResponse();
    }

    /**
     * Create an instance of {@link AggiornaDichiarazioneServiceResponse }
     * 
     */
    public AggiornaDichiarazioneServiceResponse createAggiornaDichiarazioneServiceResponse() {
        return new AggiornaDichiarazioneServiceResponse();
    }

    /**
     * Create an instance of {@link RinunciaDelegatoService }
     * 
     */
    public RinunciaDelegatoService createRinunciaDelegatoService() {
        return new RinunciaDelegatoService();
    }

    /**
     * Create an instance of {@link InserisciDelegaService }
     * 
     */
    public InserisciDelegaService createInserisciDelegaService() {
        return new InserisciDelegaService();
    }

    /**
     * Create an instance of {@link Profilo }
     * 
     */
    public Profilo createProfilo() {
        return new Profilo();
    }

    /**
     * Create an instance of {@link AggiornaDelegaServiceResponse }
     * 
     */
    public AggiornaDelegaServiceResponse createAggiornaDelegaServiceResponse() {
        return new AggiornaDelegaServiceResponse();
    }

    /**
     * Create an instance of {@link GetDeleganti.StatiDelega }
     * 
     */
    public GetDeleganti.StatiDelega createGetDelegantiStatiDelega() {
        return new GetDeleganti.StatiDelega();
    }

    /**
     * Create an instance of {@link TipoDichiarazione }
     * 
     */
    public TipoDichiarazione createTipoDichiarazione() {
        return new TipoDichiarazione();
    }

    /**
     * Create an instance of {@link AggiornaDichiarazione }
     * 
     */
    public AggiornaDichiarazione createAggiornaDichiarazione() {
        return new AggiornaDichiarazione();
    }

    /**
     * Create an instance of {@link AziendaSanitaria }
     * 
     */
    public AziendaSanitaria createAziendaSanitaria() {
        return new AziendaSanitaria();
    }

    /**
     * Create an instance of {@link InserisciDichiarazioneServiceResponse }
     * 
     */
    public InserisciDichiarazioneServiceResponse createInserisciDichiarazioneServiceResponse() {
        return new InserisciDichiarazioneServiceResponse();
    }

    /**
     * Create an instance of {@link GetDelegatiServiceResponse }
     * 
     */
    public GetDelegatiServiceResponse createGetDelegatiServiceResponse() {
        return new GetDelegatiServiceResponse();
    }

    /**
     * Create an instance of {@link RicercaDelegheServiceResponse }
     * 
     */
    public RicercaDelegheServiceResponse createRicercaDelegheServiceResponse() {
        return new RicercaDelegheServiceResponse();
    }

    /**
     * Create an instance of {@link InserisciDichiarazione }
     * 
     */
    public InserisciDichiarazione createInserisciDichiarazione() {
        return new InserisciDichiarazione();
    }

    /**
     * Create an instance of {@link RicercaCittadiniServiceResponse }
     * 
     */
    public RicercaCittadiniServiceResponse createRicercaCittadiniServiceResponse() {
        return new RicercaCittadiniServiceResponse();
    }

    /**
     * Create an instance of {@link AggiornaDichiarazioneResponse }
     * 
     */
    public AggiornaDichiarazioneResponse createAggiornaDichiarazioneResponse() {
        return new AggiornaDichiarazioneResponse();
    }

    /**
     * Create an instance of {@link DelegaCittadino.Deleghe }
     * 
     */
    public DelegaCittadino.Deleghe createDelegaCittadinoDeleghe() {
        return new DelegaCittadino.Deleghe();
    }

    /**
     * Create an instance of {@link RinunciaDelegatoResponse }
     * 
     */
    public RinunciaDelegatoResponse createRinunciaDelegatoResponse() {
        return new RinunciaDelegatoResponse();
    }

    /**
     * Create an instance of {@link InserisciDelegaServiceResponse }
     * 
     */
    public InserisciDelegaServiceResponse createInserisciDelegaServiceResponse() {
        return new InserisciDelegaServiceResponse();
    }

    /**
     * Create an instance of {@link StatoDichiarazione }
     * 
     */
    public StatoDichiarazione createStatoDichiarazione() {
        return new StatoDichiarazione();
    }

    /**
     * Create an instance of {@link RicercaCittadini }
     * 
     */
    public RicercaCittadini createRicercaCittadini() {
        return new RicercaCittadini();
    }

    /**
     * Create an instance of {@link AggiornaDelegaResponse }
     * 
     */
    public AggiornaDelegaResponse createAggiornaDelegaResponse() {
        return new AggiornaDelegaResponse();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioni }
     * 
     */
    public RicercaDichiarazioni createRicercaDichiarazioni() {
        return new RicercaDichiarazioni();
    }

    /**
     * Create an instance of {@link IsAliveService }
     * 
     */
    public IsAliveService createIsAliveService() {
        return new IsAliveService();
    }

    /**
     * Create an instance of {@link ElencoGenitoriResponse.Cittadini }
     * 
     */
    public ElencoGenitoriResponse.Cittadini createElencoGenitoriResponseCittadini() {
        return new ElencoGenitoriResponse.Cittadini();
    }

    /**
     * Create an instance of {@link ComuneDiNascita }
     * 
     */
    public ComuneDiNascita createComuneDiNascita() {
        return new ComuneDiNascita();
    }

    /**
     * Create an instance of {@link GetDelegantiResponse }
     * 
     */
    public GetDelegantiResponse createGetDelegantiResponse() {
        return new GetDelegantiResponse();
    }

    /**
     * Create an instance of {@link RuoloOperazione }
     * 
     */
    public RuoloOperazione createRuoloOperazione() {
        return new RuoloOperazione();
    }

    /**
     * Create an instance of {@link Servizio }
     * 
     */
    public Servizio createServizio() {
        return new Servizio();
    }

    /**
     * Create an instance of {@link SaveDelegatiService }
     * 
     */
    public SaveDelegatiService createSaveDelegatiService() {
        return new SaveDelegatiService();
    }

    /**
     * Create an instance of {@link DelegaServizio }
     * 
     */
    public DelegaServizio createDelegaServizio() {
        return new DelegaServizio();
    }

    /**
     * Create an instance of {@link Richiedente }
     * 
     */
    public Richiedente createRichiedente() {
        return new Richiedente();
    }

    /**
     * Create an instance of {@link InserisciCittadinoResponse }
     * 
     */
    public InserisciCittadinoResponse createInserisciCittadinoResponse() {
        return new InserisciCittadinoResponse();
    }

    /**
     * Create an instance of {@link RicercaCittadiniResponse }
     * 
     */
    public RicercaCittadiniResponse createRicercaCittadiniResponse() {
        return new RicercaCittadiniResponse();
    }

    /**
     * Create an instance of {@link RicercaDelegheService }
     * 
     */
    public RicercaDelegheService createRicercaDelegheService() {
        return new RicercaDelegheService();
    }

    /**
     * Create an instance of {@link GetDelegatiService }
     * 
     */
    public GetDelegatiService createGetDelegatiService() {
        return new GetDelegatiService();
    }

    /**
     * Create an instance of {@link GetDeleganti.CodiciServizio }
     * 
     */
    public GetDeleganti.CodiciServizio createGetDelegantiCodiciServizio() {
        return new GetDeleganti.CodiciServizio();
    }

    /**
     * Create an instance of {@link RicercaCfOperatore }
     * 
     */
    public RicercaCfOperatore createRicercaCfOperatore() {
        return new RicercaCfOperatore();
    }

    /**
     * Create an instance of {@link GetDelegantiResponse.Deleganti }
     * 
     */
    public GetDelegantiResponse.Deleganti createGetDelegantiResponseDeleganti() {
        return new GetDelegantiResponse.Deleganti();
    }

    /**
     * Create an instance of {@link RicercaCfOperatoreServiceResponse }
     * 
     */
    public RicercaCfOperatoreServiceResponse createRicercaCfOperatoreServiceResponse() {
        return new RicercaCfOperatoreServiceResponse();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioniService }
     * 
     */
    public RicercaDichiarazioniService createRicercaDichiarazioniService() {
        return new RicercaDichiarazioniService();
    }

    /**
     * Create an instance of {@link SaveDelegati }
     * 
     */
    public SaveDelegati createSaveDelegati() {
        return new SaveDelegati();
    }

    /**
     * Create an instance of {@link SaveDelegatiServiceResponse }
     * 
     */
    public SaveDelegatiServiceResponse createSaveDelegatiServiceResponse() {
        return new SaveDelegatiServiceResponse();
    }

    /**
     * Create an instance of {@link ElencoGenitoriService }
     * 
     */
    public ElencoGenitoriService createElencoGenitoriService() {
        return new ElencoGenitoriService();
    }

    /**
     * Create an instance of {@link SaveDelegatiResponse }
     * 
     */
    public SaveDelegatiResponse createSaveDelegatiResponse() {
        return new SaveDelegatiResponse();
    }

    /**
     * Create an instance of {@link SaveDelegatiResponse.Delegati }
     * 
     */
    public SaveDelegatiResponse.Delegati createSaveDelegatiResponseDelegati() {
        return new SaveDelegatiResponse.Delegati();
    }

    /**
     * Create an instance of {@link AggiornaCittadinoServiceResponse }
     * 
     */
    public AggiornaCittadinoServiceResponse createAggiornaCittadinoServiceResponse() {
        return new AggiornaCittadinoServiceResponse();
    }

    /**
     * Create an instance of {@link ServiceRequest }
     * 
     */
    public ServiceRequest createServiceRequest() {
        return new ServiceRequest();
    }

    /**
     * Create an instance of {@link it.csi.esenred.esenredweb.business.deleghebe.Delegati }
     * 
     */
    public it.csi.esenred.esenredweb.business.deleghebe.Delegati createDelegati() {
        return new it.csi.esenred.esenredweb.business.deleghebe.Delegati();
    }

    /**
     * Create an instance of {@link GetDelegatiResponse }
     * 
     */
    public GetDelegatiResponse createGetDelegatiResponse() {
        return new GetDelegatiResponse();
    }

    /**
     * Create an instance of {@link InserisciDichiarazioneResponse }
     * 
     */
    public InserisciDichiarazioneResponse createInserisciDichiarazioneResponse() {
        return new InserisciDichiarazioneResponse();
    }

    /**
     * Create an instance of {@link RicercaDeleghe.StatiDelega }
     * 
     */
    public RicercaDeleghe.StatiDelega createRicercaDelegheStatiDelega() {
        return new RicercaDeleghe.StatiDelega();
    }

    /**
     * Create an instance of {@link Dichiarazione }
     * 
     */
    public Dichiarazione createDichiarazione() {
        return new Dichiarazione();
    }

    /**
     * Create an instance of {@link Dichiarazione.Dettagli }
     * 
     */
    public Dichiarazione.Dettagli createDichiarazioneDettagli() {
        return new Dichiarazione.Dettagli();
    }

    /**
     * Create an instance of {@link InserisciCittadinoServiceResponse }
     * 
     */
    public InserisciCittadinoServiceResponse createInserisciCittadinoServiceResponse() {
        return new InserisciCittadinoServiceResponse();
    }

    /**
     * Create an instance of {@link Delegato }
     * 
     */
    public Delegato createDelegato() {
        return new Delegato();
    }

    /**
     * Create an instance of {@link RicercaDelegheResponse.Deleghe }
     * 
     */
    public RicercaDelegheResponse.Deleghe createRicercaDelegheResponseDeleghe() {
        return new RicercaDelegheResponse.Deleghe();
    }

    /**
     * Create an instance of {@link DichiarazioneDettaglioStato }
     * 
     */
    public DichiarazioneDettaglioStato createDichiarazioneDettaglioStato() {
        return new DichiarazioneDettaglioStato();
    }

    /**
     * Create an instance of {@link DelegaServStato }
     * 
     */
    public DelegaServStato createDelegaServStato() {
        return new DelegaServStato();
    }

    /**
     * Create an instance of {@link it.csi.esenred.esenredweb.business.deleghebe.Deleganti }
     * 
     */
    public it.csi.esenred.esenredweb.business.deleghebe.Deleganti createDeleganti() {
        return new it.csi.esenred.esenredweb.business.deleghebe.Deleganti();
    }

    /**
     * Create an instance of {@link AggiornaDichiarazioneService }
     * 
     */
    public AggiornaDichiarazioneService createAggiornaDichiarazioneService() {
        return new AggiornaDichiarazioneService();
    }

    /**
     * Create an instance of {@link InserisciCittadino }
     * 
     */
    public InserisciCittadino createInserisciCittadino() {
        return new InserisciCittadino();
    }

    /**
     * Create an instance of {@link RicercaCittadiniResponse.Cittadini }
     * 
     */
    public RicercaCittadiniResponse.Cittadini createRicercaCittadiniResponseCittadini() {
        return new RicercaCittadiniResponse.Cittadini();
    }

    /**
     * Create an instance of {@link DelegaServ }
     * 
     */
    public DelegaServ createDelegaServ() {
        return new DelegaServ();
    }

    /**
     * Create an instance of {@link Operatore }
     * 
     */
    public Operatore createOperatore() {
        return new Operatore();
    }

    /**
     * Create an instance of {@link ElencoGenitoriResponse }
     * 
     */
    public ElencoGenitoriResponse createElencoGenitoriResponse() {
        return new ElencoGenitoriResponse();
    }

    /**
     * Create an instance of {@link IsAlive }
     * 
     */
    public IsAlive createIsAlive() {
        return new IsAlive();
    }

    /**
     * Create an instance of {@link GetDelegati.CodiciServizio }
     * 
     */
    public GetDelegati.CodiciServizio createGetDelegatiCodiciServizio() {
        return new GetDelegati.CodiciServizio();
    }

    /**
     * Create an instance of {@link RuoloDeleghe }
     * 
     */
    public RuoloDeleghe createRuoloDeleghe() {
        return new RuoloDeleghe();
    }

    /**
     * Create an instance of {@link ElencoGenitoriServiceResponse }
     * 
     */
    public ElencoGenitoriServiceResponse createElencoGenitoriServiceResponse() {
        return new ElencoGenitoriServiceResponse();
    }

    /**
     * Create an instance of {@link RinunciaDelegato.UuidDelegaServizii }
     * 
     */
    public RinunciaDelegato.UuidDelegaServizii createRinunciaDelegatoUuidDelegaServizii() {
        return new RinunciaDelegato.UuidDelegaServizii();
    }

    /**
     * Create an instance of {@link DelegaCittadino }
     * 
     */
    public DelegaCittadino createDelegaCittadino() {
        return new DelegaCittadino();
    }

    /**
     * Create an instance of {@link Delega.Servizi }
     * 
     */
    public Delega.Servizi createDelegaServizi() {
        return new Delega.Servizi();
    }

    /**
     * Create an instance of {@link InserisciDichiarazioneService }
     * 
     */
    public InserisciDichiarazioneService createInserisciDichiarazioneService() {
        return new InserisciDichiarazioneService();
    }

    /**
     * Create an instance of {@link Cittadino }
     * 
     */
    public Cittadino createCittadino() {
        return new Cittadino();
    }

    /**
     * Create an instance of {@link GetDelegati.StatiDelega }
     * 
     */
    public GetDelegati.StatiDelega createGetDelegatiStatiDelega() {
        return new GetDelegati.StatiDelega();
    }

    /**
     * Create an instance of {@link InserisciDelega }
     * 
     */
    public InserisciDelega createInserisciDelega() {
        return new InserisciDelega();
    }

    /**
     * Create an instance of {@link RicercaCfOperatoreResponse }
     * 
     */
    public RicercaCfOperatoreResponse createRicercaCfOperatoreResponse() {
        return new RicercaCfOperatoreResponse();
    }

    /**
     * Create an instance of {@link DichiarazioneDettaglio }
     * 
     */
    public DichiarazioneDettaglio createDichiarazioneDettaglio() {
        return new DichiarazioneDettaglio();
    }

    /**
     * Create an instance of {@link StatoDiNascita }
     * 
     */
    public StatoDiNascita createStatoDiNascita() {
        return new StatoDiNascita();
    }

    /**
     * Create an instance of {@link RicercaDichiarazioniResponse.Dichiarazioni }
     * 
     */
    public RicercaDichiarazioniResponse.Dichiarazioni createRicercaDichiarazioniResponseDichiarazioni() {
        return new RicercaDichiarazioniResponse.Dichiarazioni();
    }

    /**
     * Create an instance of {@link Codifica }
     * 
     */
    public Codifica createCodifica() {
        return new Codifica();
    }

    /**
     * Create an instance of {@link SaveDelegati.Delegati }
     * 
     */
    public SaveDelegati.Delegati createSaveDelegatiDelegati() {
        return new SaveDelegati.Delegati();
    }

    /**
     * Create an instance of {@link AggiornaCittadinoResponse }
     * 
     */
    public AggiornaCittadinoResponse createAggiornaCittadinoResponse() {
        return new AggiornaCittadinoResponse();
    }

    /**
     * Create an instance of {@link GetDelegatiResponse.Delegati }
     * 
     */
    public GetDelegatiResponse.Delegati createGetDelegatiResponseDelegati() {
        return new GetDelegatiResponse.Delegati();
    }

    /**
     * Create an instance of {@link Documento }
     * 
     */
    public Documento createDocumento() {
        return new Documento();
    }

    /**
     * Create an instance of {@link AggiornaCittadinoService }
     * 
     */
    public AggiornaCittadinoService createAggiornaCittadinoService() {
        return new AggiornaCittadinoService();
    }

    /**
     * Create an instance of {@link DocumentoTipo }
     * 
     */
    public DocumentoTipo createDocumentoTipo() {
        return new DocumentoTipo();
    }

    /**
     * Create an instance of {@link RinunciaDelegatoServiceResponse }
     * 
     */
    public RinunciaDelegatoServiceResponse createRinunciaDelegatoServiceResponse() {
        return new RinunciaDelegatoServiceResponse();
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link GetDelegantiServiceResponse }
     * 
     */
    public GetDelegantiServiceResponse createGetDelegantiServiceResponse() {
        return new GetDelegantiServiceResponse();
    }

    /**
     * Create an instance of {@link InserisciCittadinoService }
     * 
     */
    public InserisciCittadinoService createInserisciCittadinoService() {
        return new InserisciCittadinoService();
    }

    /**
     * Create an instance of {@link TipoFileDocumento }
     * 
     */
    public TipoFileDocumento createTipoFileDocumento() {
        return new TipoFileDocumento();
    }

    /**
     * Create an instance of {@link RicercaDelegheResponse }
     * 
     */
    public RicercaDelegheResponse createRicercaDelegheResponse() {
        return new RicercaDelegheResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegatiServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegatiServiceResponse")
    public JAXBElement<GetDelegatiServiceResponse> createGetDelegatiServiceResponse(GetDelegatiServiceResponse value) {
        return new JAXBElement<GetDelegatiServiceResponse>(_GetDelegatiServiceResponse_QNAME, GetDelegatiServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDelegheServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaDelegheServiceResponse")
    public JAXBElement<RicercaDelegheServiceResponse> createRicercaDelegheServiceResponse(RicercaDelegheServiceResponse value) {
        return new JAXBElement<RicercaDelegheServiceResponse>(_RicercaDelegheServiceResponse_QNAME, RicercaDelegheServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegantiServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegantiServiceResponse")
    public JAXBElement<GetDelegantiServiceResponse> createGetDelegantiServiceResponse(GetDelegantiServiceResponse value) {
        return new JAXBElement<GetDelegantiServiceResponse>(_GetDelegantiServiceResponse_QNAME, GetDelegantiServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAliveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "isAliveResponse")
    public JAXBElement<IsAliveResponse> createIsAliveResponse(IsAliveResponse value) {
        return new JAXBElement<IsAliveResponse>(_IsAliveResponse_QNAME, IsAliveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciDichiarazioneService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "inserisciDichiarazioneService")
    public JAXBElement<InserisciDichiarazioneService> createInserisciDichiarazioneService(InserisciDichiarazioneService value) {
        return new JAXBElement<InserisciDichiarazioneService>(_InserisciDichiarazioneService_QNAME, InserisciDichiarazioneService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RinunciaDelegato }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "rinunciaDelegato")
    public JAXBElement<RinunciaDelegato> createRinunciaDelegato(RinunciaDelegato value) {
        return new JAXBElement<RinunciaDelegato>(_RinunciaDelegato_QNAME, RinunciaDelegato.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RinunciaDelegatoServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "rinunciaDelegatoServiceResponse")
    public JAXBElement<RinunciaDelegatoServiceResponse> createRinunciaDelegatoServiceResponse(RinunciaDelegatoServiceResponse value) {
        return new JAXBElement<RinunciaDelegatoServiceResponse>(_RinunciaDelegatoServiceResponse_QNAME, RinunciaDelegatoServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelegaServizio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "servizioDelega")
    public JAXBElement<DelegaServizio> createServizioDelega(DelegaServizio value) {
        return new JAXBElement<DelegaServizio>(_ServizioDelega_QNAME, DelegaServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cittadino }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "cittadino")
    public JAXBElement<Cittadino> createCittadino(Cittadino value) {
        return new JAXBElement<Cittadino>(_Cittadino_QNAME, Cittadino.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaCfOperatoreService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaCfOperatoreService")
    public JAXBElement<RicercaCfOperatoreService> createRicercaCfOperatoreService(RicercaCfOperatoreService value) {
        return new JAXBElement<RicercaCfOperatoreService>(_RicercaCfOperatoreService_QNAME, RicercaCfOperatoreService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDelegheService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaDelegheService")
    public JAXBElement<RicercaDelegheService> createRicercaDelegheService(RicercaDelegheService value) {
        return new JAXBElement<RicercaDelegheService>(_RicercaDelegheService_QNAME, RicercaDelegheService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaDelegaServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "aggiornaDelegaServiceResponse")
    public JAXBElement<AggiornaDelegaServiceResponse> createAggiornaDelegaServiceResponse(AggiornaDelegaServiceResponse value) {
        return new JAXBElement<AggiornaDelegaServiceResponse>(_AggiornaDelegaServiceResponse_QNAME, AggiornaDelegaServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegatiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegatiResponse")
    public JAXBElement<GetDelegatiResponse> createGetDelegatiResponse(GetDelegatiResponse value) {
        return new JAXBElement<GetDelegatiResponse>(_GetDelegatiResponse_QNAME, GetDelegatiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAliveService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "isAliveService")
    public JAXBElement<IsAliveService> createIsAliveService(IsAliveService value) {
        return new JAXBElement<IsAliveService>(_IsAliveService_QNAME, IsAliveService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDelegatiService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "saveDelegatiService")
    public JAXBElement<SaveDelegatiService> createSaveDelegatiService(SaveDelegatiService value) {
        return new JAXBElement<SaveDelegatiService>(_SaveDelegatiService_QNAME, SaveDelegatiService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegati }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegati")
    public JAXBElement<GetDelegati> createGetDelegati(GetDelegati value) {
        return new JAXBElement<GetDelegati>(_GetDelegati_QNAME, GetDelegati.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciCittadinoServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "inserisciCittadinoServiceResponse")
    public JAXBElement<InserisciCittadinoServiceResponse> createInserisciCittadinoServiceResponse(InserisciCittadinoServiceResponse value) {
        return new JAXBElement<InserisciCittadinoServiceResponse>(_InserisciCittadinoServiceResponse_QNAME, InserisciCittadinoServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegantiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegantiResponse")
    public JAXBElement<GetDelegantiResponse> createGetDelegantiResponse(GetDelegantiResponse value) {
        return new JAXBElement<GetDelegantiResponse>(_GetDelegantiResponse_QNAME, GetDelegantiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaDelegaService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "aggiornaDelegaService")
    public JAXBElement<AggiornaDelegaService> createAggiornaDelegaService(AggiornaDelegaService value) {
        return new JAXBElement<AggiornaDelegaService>(_AggiornaDelegaService_QNAME, AggiornaDelegaService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciCittadinoService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "inserisciCittadinoService")
    public JAXBElement<InserisciCittadinoService> createInserisciCittadinoService(InserisciCittadinoService value) {
        return new JAXBElement<InserisciCittadinoService>(_InserisciCittadinoService_QNAME, InserisciCittadinoService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciDichiarazioneServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "inserisciDichiarazioneServiceResponse")
    public JAXBElement<InserisciDichiarazioneServiceResponse> createInserisciDichiarazioneServiceResponse(InserisciDichiarazioneServiceResponse value) {
        return new JAXBElement<InserisciDichiarazioneServiceResponse>(_InserisciDichiarazioneServiceResponse_QNAME, InserisciDichiarazioneServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDichiarazioniService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaDichiarazioniService")
    public JAXBElement<RicercaDichiarazioniService> createRicercaDichiarazioniService(RicercaDichiarazioniService value) {
        return new JAXBElement<RicercaDichiarazioniService>(_RicercaDichiarazioniService_QNAME, RicercaDichiarazioniService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDelegatiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "saveDelegatiResponse")
    public JAXBElement<SaveDelegatiResponse> createSaveDelegatiResponse(SaveDelegatiResponse value) {
        return new JAXBElement<SaveDelegatiResponse>(_SaveDelegatiResponse_QNAME, SaveDelegatiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciDelegaService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "inserisciDelegaService")
    public JAXBElement<InserisciDelegaService> createInserisciDelegaService(InserisciDelegaService value) {
        return new JAXBElement<InserisciDelegaService>(_InserisciDelegaService_QNAME, InserisciDelegaService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegatiService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegatiService")
    public JAXBElement<GetDelegatiService> createGetDelegatiService(GetDelegatiService value) {
        return new JAXBElement<GetDelegatiService>(_GetDelegatiService_QNAME, GetDelegatiService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaCittadinoServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "aggiornaCittadinoServiceResponse")
    public JAXBElement<AggiornaCittadinoServiceResponse> createAggiornaCittadinoServiceResponse(AggiornaCittadinoServiceResponse value) {
        return new JAXBElement<AggiornaCittadinoServiceResponse>(_AggiornaCittadinoServiceResponse_QNAME, AggiornaCittadinoServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Codifica }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "codifica")
    public JAXBElement<Codifica> createCodifica(Codifica value) {
        return new JAXBElement<Codifica>(_Codifica_QNAME, Codifica.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RinunciaDelegatoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "rinunciaDelegatoResponse")
    public JAXBElement<RinunciaDelegatoResponse> createRinunciaDelegatoResponse(RinunciaDelegatoResponse value) {
        return new JAXBElement<RinunciaDelegatoResponse>(_RinunciaDelegatoResponse_QNAME, RinunciaDelegatoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Richiedente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "richiedente")
    public JAXBElement<Richiedente> createRichiedente(Richiedente value) {
        return new JAXBElement<Richiedente>(_Richiedente_QNAME, Richiedente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDelegantiService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDelegantiService")
    public JAXBElement<GetDelegantiService> createGetDelegantiService(GetDelegantiService value) {
        return new JAXBElement<GetDelegantiService>(_GetDelegantiService_QNAME, GetDelegantiService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaCittadiniServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaCittadiniServiceResponse")
    public JAXBElement<RicercaCittadiniServiceResponse> createRicercaCittadiniServiceResponse(RicercaCittadiniServiceResponse value) {
        return new JAXBElement<RicercaCittadiniServiceResponse>(_RicercaCittadiniServiceResponse_QNAME, RicercaCittadiniServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaCfOperatoreServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaCfOperatoreServiceResponse")
    public JAXBElement<RicercaCfOperatoreServiceResponse> createRicercaCfOperatoreServiceResponse(RicercaCfOperatoreServiceResponse value) {
        return new JAXBElement<RicercaCfOperatoreServiceResponse>(_RicercaCfOperatoreServiceResponse_QNAME, RicercaCfOperatoreServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciDelegaServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "inserisciDelegaServiceResponse")
    public JAXBElement<InserisciDelegaServiceResponse> createInserisciDelegaServiceResponse(InserisciDelegaServiceResponse value) {
        return new JAXBElement<InserisciDelegaServiceResponse>(_InserisciDelegaServiceResponse_QNAME, InserisciDelegaServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaDichiarazioneService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "aggiornaDichiarazioneService")
    public JAXBElement<AggiornaDichiarazioneService> createAggiornaDichiarazioneService(AggiornaDichiarazioneService value) {
        return new JAXBElement<AggiornaDichiarazioneService>(_AggiornaDichiarazioneService_QNAME, AggiornaDichiarazioneService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDelegatiServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "saveDelegatiServiceResponse")
    public JAXBElement<SaveDelegatiServiceResponse> createSaveDelegatiServiceResponse(SaveDelegatiServiceResponse value) {
        return new JAXBElement<SaveDelegatiServiceResponse>(_SaveDelegatiServiceResponse_QNAME, SaveDelegatiServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeleganti }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "getDeleganti")
    public JAXBElement<GetDeleganti> createGetDeleganti(GetDeleganti value) {
        return new JAXBElement<GetDeleganti>(_GetDeleganti_QNAME, GetDeleganti.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElencoGenitoriService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "elencoGenitoriService")
    public JAXBElement<ElencoGenitoriService> createElencoGenitoriService(ElencoGenitoriService value) {
        return new JAXBElement<ElencoGenitoriService>(_ElencoGenitoriService_QNAME, ElencoGenitoriService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDelegati }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "saveDelegati")
    public JAXBElement<SaveDelegati> createSaveDelegati(SaveDelegati value) {
        return new JAXBElement<SaveDelegati>(_SaveDelegati_QNAME, SaveDelegati.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAlive }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "isAlive")
    public JAXBElement<IsAlive> createIsAlive(IsAlive value) {
        return new JAXBElement<IsAlive>(_IsAlive_QNAME, IsAlive.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaDichiarazioneServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "aggiornaDichiarazioneServiceResponse")
    public JAXBElement<AggiornaDichiarazioneServiceResponse> createAggiornaDichiarazioneServiceResponse(AggiornaDichiarazioneServiceResponse value) {
        return new JAXBElement<AggiornaDichiarazioneServiceResponse>(_AggiornaDichiarazioneServiceResponse_QNAME, AggiornaDichiarazioneServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElencoGenitoriServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "elencoGenitoriServiceResponse")
    public JAXBElement<ElencoGenitoriServiceResponse> createElencoGenitoriServiceResponse(ElencoGenitoriServiceResponse value) {
        return new JAXBElement<ElencoGenitoriServiceResponse>(_ElencoGenitoriServiceResponse_QNAME, ElencoGenitoriServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelegaCittadino }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "cittadinoDeleghe")
    public JAXBElement<DelegaCittadino> createCittadinoDeleghe(DelegaCittadino value) {
        return new JAXBElement<DelegaCittadino>(_CittadinoDeleghe_QNAME, DelegaCittadino.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDichiarazioniServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaDichiarazioniServiceResponse")
    public JAXBElement<RicercaDichiarazioniServiceResponse> createRicercaDichiarazioniServiceResponse(RicercaDichiarazioniServiceResponse value) {
        return new JAXBElement<RicercaDichiarazioniServiceResponse>(_RicercaDichiarazioniServiceResponse_QNAME, RicercaDichiarazioniServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaCittadiniService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "ricercaCittadiniService")
    public JAXBElement<RicercaCittadiniService> createRicercaCittadiniService(RicercaCittadiniService value) {
        return new JAXBElement<RicercaCittadiniService>(_RicercaCittadiniService_QNAME, RicercaCittadiniService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsAliveServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "isAliveServiceResponse")
    public JAXBElement<IsAliveServiceResponse> createIsAliveServiceResponse(IsAliveServiceResponse value) {
        return new JAXBElement<IsAliveServiceResponse>(_IsAliveServiceResponse_QNAME, IsAliveServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RinunciaDelegatoService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "rinunciaDelegatoService")
    public JAXBElement<RinunciaDelegatoService> createRinunciaDelegatoService(RinunciaDelegatoService value) {
        return new JAXBElement<RinunciaDelegatoService>(_RinunciaDelegatoService_QNAME, RinunciaDelegatoService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggiornaCittadinoService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "aggiornaCittadinoService")
    public JAXBElement<AggiornaCittadinoService> createAggiornaCittadinoService(AggiornaCittadinoService value) {
        return new JAXBElement<AggiornaCittadinoService>(_AggiornaCittadinoService_QNAME, AggiornaCittadinoService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "dataModifica", scope = Operatore.class)
    public JAXBElement<XMLGregorianCalendar> createOperatoreDataModifica(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OperatoreDataModifica_QNAME, XMLGregorianCalendar.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "opId", scope = Operatore.class)
    public JAXBElement<Integer> createOperatoreOpId(Integer value) {
        return new JAXBElement<Integer>(_OperatoreOpId_QNAME, Integer.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "loginOperazione", scope = Operatore.class)
    public JAXBElement<String> createOperatoreLoginOperazione(String value) {
        return new JAXBElement<String>(_OperatoreLoginOperazione_QNAME, String.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "opCf", scope = Operatore.class)
    public JAXBElement<String> createOperatoreOpCf(String value) {
        return new JAXBElement<String>(_OperatoreOpCf_QNAME, String.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "dataCreazione", scope = Operatore.class)
    public JAXBElement<XMLGregorianCalendar> createOperatoreDataCreazione(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OperatoreDataCreazione_QNAME, XMLGregorianCalendar.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "opCognome", scope = Operatore.class)
    public JAXBElement<String> createOperatoreOpCognome(String value) {
        return new JAXBElement<String>(_OperatoreOpCognome_QNAME, String.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "opNome", scope = Operatore.class)
    public JAXBElement<String> createOperatoreOpNome(String value) {
        return new JAXBElement<String>(_OperatoreOpNome_QNAME, String.class, Operatore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://deleghebe.csi.it/", name = "dataCancellazione", scope = Operatore.class)
    public JAXBElement<XMLGregorianCalendar> createOperatoreDataCancellazione(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OperatoreDataCancellazione_QNAME, XMLGregorianCalendar.class, Operatore.class, value);
    }

}
