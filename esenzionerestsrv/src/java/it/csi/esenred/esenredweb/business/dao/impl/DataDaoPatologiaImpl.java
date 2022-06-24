/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDistrettoSocioSanitario;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzioneAuraArchivio;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDInvaliditaTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTProgressivo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.dto.FiltriListaCertificati;
import it.csi.esenred.esenredweb.dto.FiltriListaEsenzioni;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.LogUtil;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

public class DataDaoPatologiaImpl implements DataDaoPatologiaIf {

  @PersistenceContext
  private EntityManager em;
  //private String AppID;
  ParametroIf parametroIf;

  LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENPAT, null);
  
  public EsenredCMessaggi getMessaggio(String codice) {
    TypedQuery<EsenredCMessaggi> query = em.createNamedQuery("EsenredCMessaggi.findMessaggio", EsenredCMessaggi.class);
    query.setParameter("codice", codice);
    return query.getSingleResult();
  }

  public EsenredCParametri getParametro(String codice) {
    TypedQuery<EsenredCParametri> query = em.createNamedQuery("EsenredCParametri.findParametro", EsenredCParametri.class);
    query.setParameter("codice", codice);
    return query.getSingleResult();
  }

  public List<EsenredDTipiEsenzioniReddito> getEsenzioni(String codEsenzione) {
    String queryStr = "SELECT e FROM EsenredDTipiEsenzioniReddito e where ( ";

    if (Checker.isValorizzato(codEsenzione))
      queryStr = queryStr + " e.codEsenzione = :codEsenzione and ";

    queryStr = queryStr + " 1 = 1 ) order by e.codEsenzione asc ";

    TypedQuery<EsenredDTipiEsenzioniReddito> query = em.createQuery(queryStr, EsenredDTipiEsenzioniReddito.class);

    if (Checker.isValorizzato(codEsenzione))
      query.setParameter("codEsenzione", codEsenzione);

    return query.getResultList();
  }

  public List<EsenredCParametri> getParametri(String codParametro) {
    String queryStr = "SELECT p FROM EsenredCParametri p where ( ";

    if (Checker.isValorizzato(codParametro))
      queryStr = queryStr + " p.codice like :codice and ";

    queryStr = queryStr + " 1 = 1 ) order by p.valore asc ";

    TypedQuery<EsenredCParametri> query = em.createQuery(queryStr, EsenredCParametri.class);

    if (Checker.isValorizzato(codParametro))
      query.setParameter("codice", codParametro + "%");

    return query.getResultList();
  }

  @Transactional(readOnly = true)
  public List<EsenredCComuni> getElencoComuni(String descComune) {
    TypedQuery<EsenredCComuni> query = em.createNamedQuery("EsenredCComuni.findPerDesc", EsenredCComuni.class);

    query.setParameter("descComune", descComune.toUpperCase() + "%");

    return query.getResultList().isEmpty() ? new ArrayList<EsenredCComuni>() : query.getResultList();
  }

  @Transactional(readOnly = true)
  public EsenredCComuni getElencoComuniPerCodIstat(String desccodIstat) {
    TypedQuery<EsenredCComuni> query = em.createNamedQuery("EsenredCComuni.findPerCodIstat", EsenredCComuni.class);

    query.setParameter("desccodIstat", desccodIstat);

    return query.getResultList().isEmpty() ? new EsenredCComuni() : query.getResultList().get(0);
  }

  @Transactional(readOnly = true)
  public List<EsenredCMessaggi> getMessaggioLike(String codMessaggio) {
    TypedQuery<EsenredCMessaggi> query = em.createNamedQuery("EsenredCMessaggi.findPerCodiceLike", EsenredCMessaggi.class);

    query.setParameter("codice", codMessaggio.toUpperCase() + "%");

    return query.getResultList().isEmpty() ? new ArrayList<EsenredCMessaggi>() : query.getResultList();
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public void insertAudit(CsiLogAudit audit) {

    audit.setDataOra(new Timestamp(new Date().getTime()));
    em.persist(audit);
  }

  @Override
  public boolean updateAudit(CsiLogAudit audit) {
    try {
      em.merge(audit);
      return true;
    } catch (Exception e) {
    }
    return false;
  }

  @Override
  public CsiLogAudit findAudit(Long idAudit) {
    TypedQuery<CsiLogAudit> query = em.createNamedQuery("CsiLogAudit.findAudit", CsiLogAudit.class);
    query.setParameter("auditId", idAudit);
    return query.getSingleResult();
  }

  @Override
  public List<CsiLogAudit> findAllAudit() {
    TypedQuery<CsiLogAudit> query = em.createNamedQuery("CsiLogAudit.findAll", CsiLogAudit.class);
    return query.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<EsenzioneTPraticaEsenzione> getListaEsenzioni(String cit_id, String esenzione_id, FiltriListaEsenzioni filtriListaEsenzioni) throws Exception {
    
	String methodName = "getListaEsenzioni";
	log.info(methodName, "BEGIN");
	
	String queryString = "select distinct esenzioneTPraticaEsenzione from EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDPraticaStato esenzioneDPraticaStato ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneSPraticaEsenziones esenzioneSPraticaEsenziones ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDDiagnosi esenzioneDDiagnosi ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneRPraticaEsenzioneDocumentos esenzioneRPraticaEsenzioneDocumentos ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDMotivazioneTipo esenzioneDMotivazioneTipo ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDInvaliditaTipo esenzioneDInvaliditaTipo ";
    queryString += "left join fetch esenzioneRPraticaEsenzioneDocumentos.esenzioneTDocumento esenzioneTDocumento ";
    queryString += "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones ";
    queryString += "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica ";

    queryString += "where esenzioneTPraticaEsenzione.codiceFiscaleBeneficiario=:codiceFiscaleBeneficiario ";

    Boolean archiaviata = null;
    if (filtriListaEsenzioni != null) {
      if (filtriListaEsenzioni.getArchiviata() != null && filtriListaEsenzioni.getArchiviata().getEq().equals("true")) {
        //solo pratiche archiviate
        queryString += " and esenzioneDPraticaStato.codStato in (:statiPratica) ";
        archiaviata = true;
      } else {
        //solo pratiche attive
        queryString += " and esenzioneDPraticaStato.codStato in (:statiPratica) ";
        archiaviata = false;
      }
      if (filtriListaEsenzioni.getStato() != null && filtriListaEsenzioni.getStato().getEq() != null) {
        queryString += " and esenzioneDPraticaStato.codStato =  '" + filtriListaEsenzioni.getStato().getEq() + "' ";
      }
      if (filtriListaEsenzioni.getTipologia_esenzione() != null && filtriListaEsenzioni.getTipologia_esenzione().getEq() != null) {
        queryString += " and esenzioneDGruppoEsenzioni.codTipologiaGruppo = '" + filtriListaEsenzioni.getTipologia_esenzione().getEq() + "' ";
      }
    } else {
      if (esenzione_id == null || esenzione_id.isEmpty())
        throw new Exception("Nessun filtro impostato");
    }
    if (esenzione_id != null && !esenzione_id.isEmpty()) {
      queryString += " and esenzioneTPraticaEsenzione.skPraticaEsenzione = '" + esenzione_id + "'";
    }

    Query q = em.createQuery(queryString);
    log.debug(methodName, "Sql: " + queryString);
	q.setParameter("codiceFiscaleBeneficiario", cifraString(cit_id));
	
    if (archiaviata != null) {
      if (archiaviata.booleanValue()) {
    	log.debug(methodName, "statiPratica archiviata [" +Constants.STATI_PRATICA_ARCHIVIATA+"]");  
        q.setParameter("statiPratica", Constants.STATI_PRATICA_ARCHIVIATA);
      } else {
    	log.debug(methodName, "statiPratica non archiviata [" +Constants.STATI_PRATICA_NON_ARCHIVIATA+"]");   
        q.setParameter("statiPratica", Constants.STATI_PRATICA_NON_ARCHIVIATA);
      }
    }
    
	List<EsenzioneTPraticaEsenzione> esenzioni = q.getResultList();
	List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenzione = new ArrayList<EsenzioneTPraticaEsenzione>();
	for (EsenzioneTPraticaEsenzione ese : esenzioni) {
		EsenzioneTPraticaEsenzione esen = decifraEsenzione(ese);
		esenzioneTPraticaEsenzione.add(esen);
	}
	log.info(methodName, "esenzioni trovate: " + (!esenzioneTPraticaEsenzione.isEmpty() ? esenzioneTPraticaEsenzione.size() : "0"));		
	log.info(methodName, "END");	
    return esenzioneTPraticaEsenzione;
  }

  @Override
  public EsenzioneTPraticaEsenzione loadFullEsenzioneTPraticaEsenzione(Integer skPraticaEsenzione) throws Exception {
    
	String methodName = "loadFullEsenzioneTPraticaEsenzione";
	log.info(methodName, "BEGIN");
	  
	  
	String queryString = "from EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDPraticaStato esenzioneDPraticaStato ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneSPraticaEsenziones esenzioneSPraticaEsenziones ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDDiagnosi esenzioneDDiagnosi ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneRPraticaEsenzioneDocumentos esenzioneRPraticaEsenzioneDocumentos ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDMotivazioneTipo esenzioneDMotivazioneTipo ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni ";
	queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDInvaliditaTipo esenzioneDInvaliditaTipo ";
    queryString += "left join fetch esenzioneRPraticaEsenzioneDocumentos.esenzioneTDocumento esenzioneTDocumento ";
    queryString += "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones ";
    queryString += "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenredDAziendasanitaria esenredDAziendasanitaria ";

    queryString += "where esenzioneTPraticaEsenzione.skPraticaEsenzione=:skPraticaEsenzione ";

    Query query = em.createQuery(queryString);
    query.setParameter("skPraticaEsenzione", skPraticaEsenzione);
    
    log.debug(methodName, "skPraticaEsenzione: "+ skPraticaEsenzione);

	EsenzioneTPraticaEsenzione esenzione = (EsenzioneTPraticaEsenzione) query.getSingleResult();
	EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = decifraEsenzione(esenzione);
	log.info(methodName, "esenzione.numeroPratica: " + esenzioneTPraticaEsenzione.getNumPratica());
	log.info(methodName, "END");	
    return esenzioneTPraticaEsenzione;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<EsenzioneTDocumento> getListaCertificati(String cit_id, FiltriListaCertificati filtriListaCertificati) {
		String queryString = "select distinct esenzioneTDocumento from EsenzioneTDocumento esenzioneTDocumento ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneRPraticaEsenzioneDocumentos esenzioneRPraticaEsenzioneDocumentos ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneSDocumentos esenzioneSDocumentos ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneDDiagnosi esenzioneDDiagnosi ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneDDocumentoStato esenzioneDDocumentoStato ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneDDocumentoTipo esenzioneDDocumentoTipo ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneTCittadino esenzioneTCittadino ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale ";
		queryString += "left join fetch esenzioneTDocumento.esenzioneTMetadatiDocumento esenzioneTMetadatiDocumento ";
    queryString += "left join fetch esenzioneRPraticaEsenzioneDocumentos.esenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
    queryString += "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones ";
    queryString += "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni ";

    queryString += "where esenzioneTDocumento.codiceFiscaleCittadino = :codiceFiscaleCittadino ";
    queryString += "and esenzioneDDocumentoTipo.codDocumentoTipo = :codDocumentoTipo ";

    if (filtriListaCertificati != null) {
      if (filtriListaCertificati.getUtilizzabile() != null && filtriListaCertificati.getUtilizzabile().getEq().equalsIgnoreCase("true")) {
        queryString += "and (esenzioneRPraticaEsenzioneDocumentos is null OR esenzioneRPraticaEsenzioneDocumentos.esenzioneTPraticaEsenzione is null) ";
      } else if (filtriListaCertificati.getUtilizzabile() != null && filtriListaCertificati.getUtilizzabile().getEq().equalsIgnoreCase("false")) {
        queryString += "and (esenzioneRPraticaEsenzioneDocumentos is not null OR esenzioneRPraticaEsenzioneDocumentos.esenzioneTPraticaEsenzione is not null) ";
      }
      if (filtriListaCertificati.getTipologia_esenzione() != null) {
        queryString += "and UPPER(esenzioneDGruppoEsenzioni.codTipologiaGruppo) LIKE \'" + filtriListaCertificati.getTipologia_esenzione().getEq().toUpperCase() + "\' ";
      }
    }

    Query query = em.createQuery(queryString);
		query.setParameter("codiceFiscaleCittadino", cifraString(cit_id));
    query.setParameter("codDocumentoTipo", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		List<EsenzioneTDocumento> certificati = query.getResultList();
		List<EsenzioneTDocumento> listaCertificati = new ArrayList<EsenzioneTDocumento>();
		for (EsenzioneTDocumento doc : certificati) {
			EsenzioneTDocumento documento = decifraDocumento(doc);
			listaCertificati.add(documento);
		}
    return listaCertificati;
  }

  @Override
  public EsenzioneTCittadino getCittadino(String cf) {
    try {
      TypedQuery<EsenzioneTCittadino> query = em.createNamedQuery("EsenzioneTCittadino.findCittadino", EsenzioneTCittadino.class);
			query.setParameter("cf", cifraString(cf));
      List<EsenzioneTCittadino> resultList = query.getResultList();
			if (resultList.size() == 1) {
				EsenzioneTCittadino cit = resultList.get(0);
				EsenzioneTCittadino cittadino = decifraCittadino(cit);
				return cittadino;
			}
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public EsenzioneTPraticaEsenzione insertPraticaEsenzione(EsenzioneTPraticaEsenzione praticaEsenzione) throws Exception {
		praticaEsenzione = cifraEsenzione(praticaEsenzione);
	  EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = em.merge(praticaEsenzione);
	  esenzioneTPraticaEsenzione = loadFullEsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzione.getSkPraticaEsenzione());
	  return esenzioneTPraticaEsenzione;
  }

  public void insertDocumentoPraticaEsenzione(EsenzioneTDocumento documento) {
		documento.setCodiceFiscaleCittadino(cifraString(documento.getCodiceFiscaleCittadino()));
    em.persist(documento);
  }

  public void insertEsenzioneRPraticaDocumento(EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento) {
    em.persist(esenzioneRPraticaDocumento);
  }

  public void insertRepositoryDocumentale(EsenzioneTRepositoryDocumentale repoDocumentale) {
		repoDocumentale.setFile(cifraFileDocumento(repoDocumentale.getFile()));
    em.persist(repoDocumentale);
  }

  @SuppressWarnings("unchecked")
  @Override
  public EsenzioneTDocumento getDettaglioCertificato(String cit_id, String certificato_id) {
    String queryString = "from EsenzioneTDocumento esenzioneTDocumento ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneRPraticaEsenzioneDocumentos esenzioneRPraticaEsenzioneDocumentos ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneSDocumentos esenzioneSDocumentos ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneDDiagnosi esenzioneDDiagnosi ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneDDocumentoStato esenzioneDDocumentoStato ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneDDocumentoTipo esenzioneDDocumentoTipo ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneTCittadino esenzioneTCittadino ";
    queryString += "left join fetch esenzioneTDocumento.esenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale ";
		queryString += "left join fetch esenzioneTDocumento.esenzioneTMetadatiDocumento esenzioneTMetadatiDocumento ";
    queryString += "left join fetch esenzioneRPraticaEsenzioneDocumentos.esenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
    queryString += "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones ";
    queryString += "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica ";

    queryString += "where esenzioneTDocumento.codiceFiscaleCittadino = :codiceFiscaleCittadino ";
    queryString += "and esenzioneTDocumento.skDocumento = :skDocumento ";

    Query query = em.createQuery(queryString);
		query.setParameter("codiceFiscaleCittadino", cifraString(cit_id));
    query.setParameter("skDocumento", Integer.valueOf(certificato_id));
    List<EsenzioneTDocumento> listaCertificati = query.getResultList();
		if (listaCertificati.size() > 0) {
			EsenzioneTDocumento documento = listaCertificati.get(0);
			EsenzioneTDocumento esenzioneTDocumento = decifraDocumento(documento);
			return esenzioneTDocumento;
		}
    return null;
  }

  public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzione(String numPratica) {
    TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery("EsenzioneTPraticaEsenzione.findPerNumPratica", EsenzioneTPraticaEsenzione.class);
    query.setParameter("numPratica", new Long(numPratica));
    List<EsenzioneTPraticaEsenzione> resultList = query.getResultList();
		if (resultList.size() == 1) {
			EsenzioneTPraticaEsenzione esenzione = resultList.get(0);
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = new EsenzioneTPraticaEsenzione(esenzione);
			esenzioneTPraticaEsenzione
					.setCodiceFiscaleBeneficiario(esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario());
			esenzioneTPraticaEsenzione.setCodiceFiscaleDelegato(esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato());
			return esenzioneTPraticaEsenzione;
		}
    return null;
  }

  public void insertEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione espe) {
		espe.setCodFiscaleCittadinoBeneficiario(cifraString(espe.getCodFiscaleCittadinoBeneficiario()));
		espe.setCodFiscaleCittadinoDelegato(cifraString(espe.getCodFiscaleCittadinoDelegato()));
		em.persist(espe);
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public void insertOrUpdateCittadino(EsenzioneTCittadino cittadino) {
    cittadino.setDatModifica(new Timestamp(System.currentTimeMillis()));
    EsenzioneTCittadino checkCittadino = getCittadino(cittadino.getCodiceFiscale());
    try {
      if (checkCittadino == null) {
				cittadino.setCodiceFiscale(cifraString(cittadino.getCodiceFiscale()));
				cittadino.setNome(cifraString(cittadino.getNome()));
				cittadino.setCognome(cifraString(cittadino.getCognome()));
				cittadino.setComuneDiNascita(cifraString(cittadino.getComuneDiNascita()));
				em.persist(cittadino);
      } else {
				cittadino.setCodiceFiscale(cifraString(cittadino.getCodiceFiscale()));
				cittadino.setNome(cifraString(cittadino.getNome()));
				cittadino.setCognome(cifraString(cittadino.getCognome()));
				cittadino.setComuneDiNascita(cifraString(cittadino.getComuneDiNascita()));
				em.merge(cittadino);
      }
    } catch (Exception e) {
      e.printStackTrace();
      em.getTransaction().rollback();
      return;
    }
  }

  public EsenzioneDPraticaStato getEsenzioneDPraticaStatoPerCodStato(String codStato) {
    TypedQuery<EsenzioneDPraticaStato> query = em.createNamedQuery("EsenzioneDPraticaStato.findPerCodStato", EsenzioneDPraticaStato.class);
    query.setParameter("codStato", codStato);
    List<EsenzioneDPraticaStato> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

  public EsenzioneDMotivazioneTipo getEsenzioneDMotivazioneTipoPerCodMotivazione(String codMotivazione) {
    TypedQuery<EsenzioneDMotivazioneTipo> query = em.createNamedQuery("EsenzioneDMotivazioneTipo.findPerCodMotivazione", EsenzioneDMotivazioneTipo.class);
    query.setParameter("codMotivazione", codMotivazione);
    List<EsenzioneDMotivazioneTipo> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

	public EsenzioneTPraticaEsenzione insertEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe) {
		etpe = cifraEsenzione(etpe);
    etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		return em.merge(etpe);
  }

  @Override
  public EsenzioneTCittadino getCittadinoByUserId(String userId) {
    try {
      TypedQuery<EsenzioneTCittadino> query = em.createNamedQuery("EsenzioneTCittadino.findCittadinoByUserId", EsenzioneTCittadino.class);
      query.setParameter("idUser", new Long(userId));
      List<EsenzioneTCittadino> resultList = query.getResultList();
			if (resultList.size() == 1) {
				EsenzioneTCittadino cittadino = resultList.get(0);
				EsenzioneTCittadino cit = decifraCittadino(cittadino);
				return cit;
			}
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  public EsenzioneDEsenzione getEsenzioneDEsenzioneByDiagnosiId(String codeDiagnosi) {
    TypedQuery<EsenzioneDEsenzione> query = em.createNamedQuery("EsenzioneDEsenzione.findByDiagnosiId", EsenzioneDEsenzione.class);
    query.setParameter("idDiagnosi", new Integer(codeDiagnosi));
    List<EsenzioneDEsenzione> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

  public EsenzioneDDistrettoSocioSanitario getCodiceAslByDistrettoId(String idDistretto) {
    TypedQuery<EsenzioneDDistrettoSocioSanitario> query = em.createNamedQuery("EsenzioneDDistrettoSocioSanitario.findByDistrettoId", EsenzioneDDistrettoSocioSanitario.class);
    query.setParameter("idDistretto", idDistretto);
    List<EsenzioneDDistrettoSocioSanitario> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

  public EsenzioneTProgressivo getNumeroProgressivo(String idAzienda, String codiceTipo) {
    TypedQuery<EsenzioneTProgressivo> query = em.createNamedQuery("EsenzioneTProgressivo.findProgressivoByAziendaAndCodiceTipo", EsenzioneTProgressivo.class);
    query.setParameter("idAzienda", idAzienda);
    query.setParameter("codiceTipo", codiceTipo);
    List<EsenzioneTProgressivo> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

  public void setAnnoProgressivo(EsenzioneTProgressivo prog) {
    em.merge(prog);
  }

  public EsenzioneRPraticaEsenzioneDocumento getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(String documentoId) {
    TypedQuery<EsenzioneRPraticaEsenzioneDocumento> query = em.createNamedQuery("EsenzioneRPraticaEsenzioneDocumento.findByDocumentoId", EsenzioneRPraticaEsenzioneDocumento.class);
    query.setParameter("documentoId", new Long(documentoId));
    List<EsenzioneRPraticaEsenzioneDocumento> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

  public EsenzioneTPraticaEsenzione getPraticaEsenzioneByStatus(String cf, Integer codMalattia, String codTipo, String[] status) {
    TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery("EsenzioneTPraticaEsenzione.findByStatus", EsenzioneTPraticaEsenzione.class);
		query.setParameter("codFiscale", cifraString(cf));
    query.setParameter("codMalattia", codMalattia);
    //query.setParameter("codTipo", new Integer(codTipo));
    query.setParameter("status", Arrays.asList(status));
    EsenzioneTPraticaEsenzione result;
	try {
		result = query.getSingleResult();
			EsenzioneTPraticaEsenzione esenzione = new EsenzioneTPraticaEsenzione(result);
			esenzione.setCodiceFiscaleBeneficiario(decifraString(esenzione.getCodiceFiscaleBeneficiario()));
			esenzione.setCodiceFiscaleDelegato(decifraString(esenzione.getCodiceFiscaleDelegato()));
			return esenzione;
		} catch (NoResultException e) {
			return null;
		}
  }

  public EsenzioneDDocumentoStato getEsenzioneDDocumentoStatoPerCodStato(String codStato) {
    TypedQuery<EsenzioneDDocumentoStato> query = em.createNamedQuery("EsenzioneDDocumentoStato.findPerCodStato", EsenzioneDDocumentoStato.class);
    query.setParameter("codStato", codStato);
    List<EsenzioneDDocumentoStato> resultList = query.getResultList();
    if (resultList.size() == 1)
      return resultList.get(0);
    return null;
  }

  @Override
  public boolean setRinnovoEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
    insertEsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzione);
    return false;
  }

  /*
   * Elenco pratiche esenzione correlate ad un codice fiscale con data inizio validita' not null
   */
  public List<EsenzioneTPraticaEsenzione> getListPraticaEsenzioneByCodFiscale(String cf) {
    TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery("EsenzioneTPraticaEsenzione.findByCf", EsenzioneTPraticaEsenzione.class);
		query.setParameter("codFiscale", cifraString(cf));
		List<EsenzioneTPraticaEsenzione> listaEsenzioni = query.getResultList();
		List<EsenzioneTPraticaEsenzione> listaPratiche = new ArrayList<EsenzioneTPraticaEsenzione>();
		for (EsenzioneTPraticaEsenzione ese : listaEsenzioni) {
			EsenzioneTPraticaEsenzione esenzione = new EsenzioneTPraticaEsenzione(ese);
			esenzione.setCodiceFiscaleBeneficiario(decifraString(esenzione.getCodiceFiscaleBeneficiario()));
			esenzione.setCodiceFiscaleDelegato(decifraString(esenzione.getCodiceFiscaleDelegato()));
			// EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);
			listaPratiche.add(esenzione);
		}
    return listaPratiche;
  }

  @Override
  public EsenzioneDDiagnosi getEsenzioneDDiagnosi(String codDiagnosi) {
    Query query = em.createQuery("from EsenzioneDDiagnosi where codDiagnosi=:codDiagnosi");
    query.setParameter("codDiagnosi", codDiagnosi);
    EsenzioneDDiagnosi esenzioneDDiagnosi = (EsenzioneDDiagnosi) query.getSingleResult();
    return esenzioneDDiagnosi;
  }

  @Override
  public EsenzioneDInvaliditaTipo getEsenzioneDInvaliditaTipoByCode(String codInvaliditaTipo) {
    Query query = em.createQuery("from EsenzioneDInvaliditaTipo where codInvaliditaTipo=:codInvaliditaTipo");
    query.setParameter("codInvaliditaTipo", codInvaliditaTipo);
    EsenzioneDInvaliditaTipo esenzioneDInvaliditaTipo = (EsenzioneDInvaliditaTipo) query.getSingleResult();
    return esenzioneDInvaliditaTipo;
  }

  /*
   * Ricerca per PK di un documento nel repository documentale 
   */
  public EsenzioneTRepositoryDocumentale getEsenzioneTRepositoryDocumentale(String skRepository) {
		Query query = em.createQuery(
				"from EsenzioneTDocumento a left join fetch a.esenzioneTRepositoryDocumentale b where a.skDocumento=:skDocumento");
		query.setParameter("skDocumento", (new Integer(skRepository)));
    EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale;
    try {
			EsenzioneTDocumento singleResult = (EsenzioneTDocumento) query.getSingleResult();
			esenzioneTRepositoryDocumentale = singleResult != null
					&& singleResult.getEsenzioneTRepositoryDocumentale() != null
							? singleResult.getEsenzioneTRepositoryDocumentale()
							: null;
			esenzioneTRepositoryDocumentale.setFile(decifraFileDocumento(esenzioneTRepositoryDocumentale.getFile()));
    } catch (NoResultException NoRes) {
      esenzioneTRepositoryDocumentale = null;
    }
    return esenzioneTRepositoryDocumentale;
  }

  /*
   * Ricerca per specifica pratica esenzione di tutti i documenti ad essa associati
   */
  public List<EsenzioneRPraticaEsenzioneDocumento> getEsenzioneRPraticaEsenzioneDocumentoBySkPratica(String praticaEsenzioneId) {
    TypedQuery<EsenzioneRPraticaEsenzioneDocumento> query = em.createNamedQuery("EsenzioneRPraticaEsenzioneDocumento.findByPraticaId", EsenzioneRPraticaEsenzioneDocumento.class);
    query.setParameter("praticaEsenzioneId", new Long(praticaEsenzioneId));
    List<EsenzioneRPraticaEsenzioneDocumento> resultList = query.getResultList();
    return resultList;
  }

  @Override
  public EsenzioneTPraticaEsenzione updateEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		esenzioneTPraticaEsenzione = cifraEsenzione(esenzioneTPraticaEsenzione);
    return em.merge(esenzioneTPraticaEsenzione);
  }
  
  /*
   * Ricerca per specifica pratica esenzione lo storico ad essa associati
   */
  @SuppressWarnings("unchecked")
  @Override
  public EsenzioneTPraticaEsenzione getStoricoEsenzione(String citId, String esenzioneId) throws Exception {
    String queryString = "from EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDPraticaStato esenzioneDPraticaStato ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneSPraticaEsenziones esenzioneSPraticaEsenziones ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDDiagnosi esenzioneDDiagnosi ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneRPraticaEsenzioneDocumentos esenzioneRPraticaEsenzioneDocumentos ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDMotivazioneTipo esenzioneDMotivazioneTipo ";
    queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni ";
    queryString += "left join fetch esenzioneRPraticaEsenzioneDocumentos.esenzioneTDocumento esenzioneTDocumento ";
    queryString += "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones ";
    queryString += "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica ";

    queryString += "where esenzioneTPraticaEsenzione.codiceFiscaleBeneficiario = :codiceFiscaleBeneficiario ";
    queryString += "and esenzioneTPraticaEsenzione.skPraticaEsenzione=:skPraticaEsenzione ";

    Query query = em.createQuery(queryString);
		query.setParameter("codiceFiscaleBeneficiario", cifraString(citId));
    query.setParameter("skPraticaEsenzione", Integer.valueOf(esenzioneId));
    List<EsenzioneTPraticaEsenzione> resultList = query.getResultList();
		if (resultList.size() > 0) {
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = resultList.get(0);
			EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(esenzioneTPraticaEsenzione);
			if (esenzione.getEsenzioneSPraticaEsenziones() != null
					&& esenzione.getEsenzioneSPraticaEsenziones().size() > 0) {
				for (EsenzioneSPraticaEsenzione storico : esenzione.getEsenzioneSPraticaEsenziones()) {
					storico = decifraStorico(storico);
				}
			}
			return esenzione;
		}
    return null;
  }

  @Transactional(readOnly = true)
  public byte[] getPdfEsenzione(Map<String, Object> parameters, String template) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream input = classloader.getResourceAsStream("/report/Esenpat_" + template + ".jrxml");
    JasperReport jasperReport = null;
    try {
      jasperReport = JasperCompileManager.compileReport(input);
      JasperPrint jasperPrint = null;
      jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
      JRPdfExporter exporter = new JRPdfExporter();

      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      try {
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }

      SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
      reportConfig.setSizePageToContent(true);
      reportConfig.setForceLineBreakPolicy(false);
      SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

      exportConfig.setMetadataAuthor("SISTEMA PIEMONTE");
      exportConfig.setAllowedPermissionsHint("PRINTING");

      exporter.setConfiguration(reportConfig);
      exporter.setConfiguration(exportConfig);
      exporter.exportReport();

      return outputStream.toByteArray();
    } catch (JRException e) {
      return null;
    }

  }
  
  /*
   * Ricerca elenco pratiche esenzione valide per beneficiario e diagnosi
   */
  public List<EsenzioneTPraticaEsenzione> getElencoPraticheEsenzioneValideByDiagnosi(String cf, Integer [] listSkDiagnosi, String codStato) throws Exception {
	  TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery("EsenzioneTPraticaEsenzione.findBySkEsenzione", EsenzioneTPraticaEsenzione.class);
		query.setParameter("codFiscale", cifraString(cf));
	  query.setParameter("elencoSkDiagnosi", Arrays.asList(listSkDiagnosi));
	  query.setParameter("codStato", codStato);
		List<EsenzioneTPraticaEsenzione> listaEsenzioni = query.getResultList();
		List<EsenzioneTPraticaEsenzione> listaPratiche = new ArrayList<EsenzioneTPraticaEsenzione>();
		for (EsenzioneTPraticaEsenzione ese : listaEsenzioni) {
			EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);
			listaPratiche.add(esenzione);
		}
	  return listaPratiche;
  }

  /*
   * Update documento di una pratica
   */
  public EsenzioneTDocumento updateEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		esenzioneTDocumento.setCodiceFiscaleCittadino(cifraString(esenzioneTDocumento.getCodiceFiscaleCittadino()));
    return em.merge(esenzioneTDocumento);
  }

  /*
   * Ricerca esenzione per codice
   */
  public EsenzioneDEsenzione getEsenzioneDEsenzione(String codEsenzione) {
	    Query query = em.createQuery("from EsenzioneDEsenzione where codEsenzione=:codEsenzione");
	    query.setParameter("codEsenzione", codEsenzione);
	    EsenzioneDEsenzione esenzioneDEsenzione = (EsenzioneDEsenzione) query.getSingleResult();
	    return esenzioneDEsenzione;
	  }
  
  /*
   * elenco diagnosi per sk esenzione
   */
  @SuppressWarnings("unchecked")
public List<EsenzioneDDiagnosi> getElencoEsenzioneDDiagnosiByEsenzione(EsenzioneDEsenzione esenzione) {
    Query query = em.createQuery("from EsenzioneDDiagnosi where skEsenzione=:skEsenzione");
    query.setParameter("skEsenzione", esenzione.getSkEsenzione().longValue());
    List<EsenzioneDDiagnosi> elencoEsenzioneDDiagnosi = query.getResultList();
    return elencoEsenzioneDDiagnosi;
  }

	private String cifraString(String stringToEncrypt) {
	  String encrypted = null;
		if (stringToEncrypt != null) {
		EntityManagerFactory emf = em.getEntityManagerFactory();
		Map<String, Object> properties = emf.getProperties();
		String schema = (String) properties.get("hibernate.default_schema");
		String queryStr = "SELECT " + schema + ".encryptpraticaesenzione(:stringToEncrypt, :password)";

		try {
			Query query = em.createNativeQuery(queryStr);
			query.setParameter("password", getCryptPassword());
			query.setParameter("stringToEncrypt", stringToEncrypt);
			encrypted = (String) query.getSingleResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	  return encrypted;
  }
  
	private String decifraString(String stringToDecrypt) {
	  String decrypted = null;
		if (stringToDecrypt != null) {
		EntityManagerFactory emf = em.getEntityManagerFactory();
		Map<String, Object> properties = emf.getProperties();
		String schema = (String) properties.get("hibernate.default_schema");
		String queryStr = "SELECT " + schema + ".decryptpraticaesenzione(:stringToDecrypt, :password)";

		try {
			Query query = em.createNativeQuery(queryStr);
			query.setParameter("password", getCryptPassword());
			query.setParameter("stringToDecrypt", stringToDecrypt);
			decrypted = (String) query.getSingleResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	  return decrypted;
  }
  
	private String getCryptPassword() throws IOException {
		InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(Constants.PASS_PROPERTIES);
		if (resourceAsStream != null) {
			Properties prop = new Properties();
			prop.load(resourceAsStream);
			return prop.getProperty(Constants.CRYPT_PASSWORD);
		} else {
			throw new FileNotFoundException("Property file " + Constants.PASS_PROPERTIES + " not found");
		}
	}

	private EsenzioneTCittadino decifraCittadino(EsenzioneTCittadino cittadino) {
		EsenzioneTCittadino cit = null;
		if (cittadino != null) {
			cit = new EsenzioneTCittadino(cittadino);
			cit.setCodiceFiscale(decifraString(cit.getCodiceFiscale()));
			cit.setCognome(decifraString(cit.getCognome()));
			cit.setNome(decifraString(cit.getNome()));
			cit.setComuneDiNascita(decifraString(cit.getComuneDiNascita()));
		}
		return cit;
	}

	private EsenzioneTCittadino cifraCittadino(EsenzioneTCittadino cittadino) {
		if (cittadino != null) {
			cittadino = new EsenzioneTCittadino(cittadino);
			cittadino.setCodiceFiscale(cifraString(cittadino.getCodiceFiscale()));
			cittadino.setCognome(cifraString(cittadino.getCognome()));
			cittadino.setNome(cifraString(cittadino.getNome()));
			cittadino.setComuneDiNascita(cifraString(cittadino.getComuneDiNascita()));
		}
		return cittadino;
	}
  
	private EsenzioneTPraticaEsenzione decifraEsenzione(EsenzioneTPraticaEsenzione esenzione) {
		EsenzioneTPraticaEsenzione esen = null;
		if (esenzione != null) {
			esen = new EsenzioneTPraticaEsenzione(esenzione);
			esen.setCodiceFiscaleBeneficiario(decifraString(esen.getCodiceFiscaleBeneficiario()));
			esen.setCodiceFiscaleDelegato(decifraString(esen.getCodiceFiscaleDelegato()));
			esen.setEsenzioneTCittadino1(decifraCittadino(esen.getEsenzioneTCittadino1()));
			esen.setEsenzioneTCittadino2(decifraCittadino(esen.getEsenzioneTCittadino2()));
			if (esen.getEsenzioneRPraticaEsenzioneDocumentos() != null
					&& esen.getEsenzioneRPraticaEsenzioneDocumentos().size() > 0) {
				for (EsenzioneRPraticaEsenzioneDocumento erped : esen.getEsenzioneRPraticaEsenzioneDocumentos()) {
					erped.setEsenzioneTDocumento(decifraDocumento(erped.getEsenzioneTDocumento()));
				}
			}
		}
		return esen;
	}

	private EsenzioneTPraticaEsenzione cifraEsenzione(EsenzioneTPraticaEsenzione esenzione) {
		if (esenzione != null) {
			esenzione = new EsenzioneTPraticaEsenzione(esenzione);
			esenzione.setCodiceFiscaleBeneficiario(cifraString(esenzione.getCodiceFiscaleBeneficiario()));
			esenzione.setCodiceFiscaleDelegato(cifraString(esenzione.getCodiceFiscaleDelegato()));
			esenzione.setEsenzioneTCittadino1(cifraCittadino(esenzione.getEsenzioneTCittadino1()));
			esenzione.setEsenzioneTCittadino2(cifraCittadino(esenzione.getEsenzioneTCittadino2()));
			if (esenzione.getEsenzioneRPraticaEsenzioneDocumentos() != null
					&& esenzione.getEsenzioneRPraticaEsenzioneDocumentos().size() > 0) {
				for (EsenzioneRPraticaEsenzioneDocumento erped : esenzione.getEsenzioneRPraticaEsenzioneDocumentos()) {
					erped.setEsenzioneTDocumento(cifraDocumento(erped.getEsenzioneTDocumento()));
				}
			}
		}
		return esenzione;
	}

	private EsenzioneTDocumento decifraDocumento(EsenzioneTDocumento documento) {
		EsenzioneTDocumento doc = null;
		if (documento != null) {
			doc = new EsenzioneTDocumento(documento);
			doc.setCodiceFiscaleCittadino(decifraString(doc.getCodiceFiscaleCittadino()));

			doc.setEsenzioneTCittadino(decifraCittadino(doc.getEsenzioneTCittadino()));
		}
		return doc;
	}

	private EsenzioneTDocumento cifraDocumento(EsenzioneTDocumento documento) {
		if (documento != null) {
			documento = new EsenzioneTDocumento(documento);
			documento.setCodiceFiscaleCittadino(cifraString(documento.getCodiceFiscaleCittadino()));
			documento.setEsenzioneTCittadino(cifraCittadino(documento.getEsenzioneTCittadino()));
		}
		return documento;
	}

	private EsenzioneSPraticaEsenzione decifraStorico(EsenzioneSPraticaEsenzione storico) {
		if (storico != null) {
			storico.setCodFiscaleCittadinoBeneficiario(decifraString(storico.getCodFiscaleCittadinoBeneficiario()));
			storico.setCodFiscaleCittadinoDelegato(decifraString(storico.getCodFiscaleCittadinoDelegato()));
		}
		return storico;
	}

	private byte[] cifraFileDocumento(byte[] file) {
		byte[] encrypted = null;
		if (file != null) {
			EntityManagerFactory emf = em.getEntityManagerFactory();
			Map<String, Object> properties = emf.getProperties();
			String schema = (String) properties.get("hibernate.default_schema");
			String queryStr = "SELECT " + schema + ".encryptDocumento(:file, :password)";

			try {
				Query query = em.createNativeQuery(queryStr);
				query.setParameter("password", getCryptPassword());
				query.setParameter("file", file);
				encrypted = (byte[]) query.getSingleResult();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return encrypted;
	}

	private byte[] decifraFileDocumento(byte[] file) {
		byte[] decrypted = null;
		if (file != null) {
			EntityManagerFactory emf = em.getEntityManagerFactory();
			Map<String, Object> properties = emf.getProperties();
			String schema = (String) properties.get("hibernate.default_schema");
			String queryStr = "SELECT " + schema + ".decryptdocumento(:file, :password)";

			try {
				Query query = em.createNativeQuery(queryStr);
				query.setParameter("password", getCryptPassword());
				query.setParameter("file", file);
				decrypted = (byte[]) query.getSingleResult();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return decrypted;
	}

  /*
   * Ricerca documentoTipo per codice
   */
  public EsenzioneDDocumentoTipo getEsenzioneDDocumentoTipo(String codTipoDocumento) {
    Query query = em.createQuery("from EsenzioneDDocumentoTipo where UPPER(codDocumentoTipo)=:codTipoDocumento");
    query.setParameter("codTipoDocumento", codTipoDocumento.toUpperCase());
    EsenzioneDDocumentoTipo esenzioneDDocumentoTipo = (EsenzioneDDocumentoTipo) query.getSingleResult();
    return esenzioneDDocumentoTipo;
  }
  
  
  /*
   * Ricerca  per codice
   */
	@Override
	public EsenzioneDGruppoEsenzioni getGruppoEsenzioneByCodice(String codGruppo) {
		Query query = em.createQuery("from EsenzioneDGruppoEsenzioni where codTipologiaGruppo=:codGruppo");
		query.setParameter("codGruppo", codGruppo);
		EsenzioneDGruppoEsenzioni esenzioneGruppoEsenzione = (EsenzioneDGruppoEsenzioni) query.getSingleResult();
		return esenzioneGruppoEsenzione;
	}
  
  
	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDEsenzione> getListaEsenzioniByGruppoEsenzione(String codGruppo) {
		String queryString = "from EsenzioneDEsenzione esenzioneDEsenzione "
				+ "left join fetch esenzioneDEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni "
				+ "left join fetch esenzioneDEsenzione.esenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo "
				+ "where esenzioneDGruppoEsenzioni.codTipologiaGruppo = :codGruppo";
		Query query = em.createQuery(queryString);
		query.setParameter("codGruppo", codGruppo);

		List<EsenzioneDEsenzione> elencoEsenzioniGruppo = query.getResultList();
		return elencoEsenzioniGruppo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDocumentoTipo> getListaTipologieDocumentobyCodiceEsenzione(String tipologia_codice) {
		String queryString = "from EsenzioneDDocumentoTipo a ";
		queryString += "left join fetch a.esenzioneDDocumentoGruppoEsenzionis b ";
		queryString += "left join fetch b.esenzioneDGruppoEsenzioni c ";
		queryString += "where c.codTipologiaGruppo = :gruppo ";
		Query query = em.createQuery(queryString);
		query.setParameter("gruppo", tipologia_codice);
		List<EsenzioneDDocumentoTipo> result = (List<EsenzioneDDocumentoTipo>) query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDiagnosi> getListaDiagnosibyCodiceEsenzione(String tipologia_codice, String malattia_codice) {
		String queryString = "from EsenzioneDDiagnosi a ";
		queryString += "left join fetch a.esenzioneDEsenzione b ";
		queryString += "left join fetch b.esenzioneDGruppoEsenzioni c ";
		queryString += "left join fetch a.esenzioneRDiagnosiPrestaziones d ";
		queryString += "left join fetch d.esenzioneDPrestazioneSpecialistica e ";
		queryString += "left join fetch a.esenzioneDDurataTipo f ";
		queryString += "where c.codTipologiaGruppo = :gruppo ";
		if (malattia_codice != null) {
			queryString += "and a.codDiagnosi = :malattia ";
		}
		Query query = em.createQuery(queryString);
		query.setParameter("gruppo", tipologia_codice);
		if (malattia_codice != null) {
			query.setParameter("malattia", malattia_codice);
		}
		List<EsenzioneDDiagnosi> result = (List<EsenzioneDDiagnosi>) query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDiagnosi> getDiagnosibyCodiceEsenzione(String codEsenzione) {
		String queryString = "from EsenzioneDDiagnosi a ";
		queryString += "left join fetch a.esenzioneDEsenzione b ";
		queryString += "left join fetch b.esenzioneDGruppoEsenzioni c ";
		queryString += "left join fetch a.esenzioneRDiagnosiPrestaziones d ";
		queryString += "left join fetch d.esenzioneDPrestazioneSpecialistica e ";
		queryString += "left join fetch a.esenzioneDDurataTipo f ";
		queryString += "where b.codEsenzione = :codEsenzione ";

		Query query = em.createQuery(queryString);
		query.setParameter("codEsenzione", codEsenzione);
		List<EsenzioneDDiagnosi> result = (List<EsenzioneDDiagnosi>) query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDInvaliditaTipo> getListaTipologieInvalidita() {
		Query query = em.createQuery("from EsenzioneDInvaliditaTipo");
		List<EsenzioneDInvaliditaTipo> result = (List<EsenzioneDInvaliditaTipo>) query.getResultList();
		return result;
	}
  

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDMotivazioneTipo> getMotivazioniByCodStatoPratica(String codStato) {
		String queryString = "from EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo "
				+ "left join fetch esenzioneDMotivazioneTipo.esenzioneRMotivazionePraticaStatos esenzioneRMotivazionePraticaStatos "
				+ "left join fetch esenzioneRMotivazionePraticaStatos.esenzioneDPraticaStato esenzioneDPraticaStato "
				+ "where esenzioneDPraticaStato.codStato = :codStato";
		Query query = em.createQuery(queryString);
		query.setParameter("codStato", codStato);
		List<EsenzioneDMotivazioneTipo> result = (List<EsenzioneDMotivazioneTipo>) query.getResultList();
		if (result != null && !result.isEmpty())
			return result;
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDPraticaStato> getListaStatiEsenzione() {
		String queryString = "from EsenzioneDPraticaStato ";
		Query query = em.createQuery(queryString);
		List<EsenzioneDPraticaStato> result = (List<EsenzioneDPraticaStato>) query.getResultList();
		if (result != null && !result.isEmpty())
			return result;
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDGruppoEsenzioni> getListaTipologieEsenzione() {
		List<EsenzioneDGruppoEsenzioni> result;
		String queryString = "from EsenzioneDGruppoEsenzioni ";
		Query query = em.createQuery(queryString);

		try {
			result = (List<EsenzioneDGruppoEsenzioni>) query.getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (NoResultException NoRes) {
			result = null;
		}
		return result;

	}
	
	
	  /*
	   * Ricerca  per codice
	   */
	@Override
	public EsenzioneDEsenzione getDettaglioEsenzioneRichiedibile(String codGruppo, String codEsenzione) {
		EsenzioneDEsenzione esenzione;
		String queryString = "from EsenzioneDEsenzione esenzioneDEsenzione "
				+ "left join fetch esenzioneDEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni "
				+ "left join fetch esenzioneDEsenzione.esenzioneDEsenzioneAuraTipo esenzioneDEsenzioneAuraTipo "
				+ "where esenzioneDEsenzione.codEsenzione = :codEsenzione "
				+ "and esenzioneDGruppoEsenzioni.codTipologiaGruppo = :codGruppo";
		Query query = em.createQuery(queryString);
		query.setParameter("codGruppo", codGruppo);
		query.setParameter("codEsenzione", codEsenzione);
		try {
			esenzione = (EsenzioneDEsenzione) query.getSingleResult();
		} catch (NoResultException NoRes) {
			esenzione = null;
		}
		return esenzione;
	}

	@Override
	public EsenzioneDEsenzioneAuraArchivio getEsenzioneArchivioByCodEsenzioneAndCodDiagnosi(String codEsenzione,
			String codDiagnosi) {
		TypedQuery<EsenzioneDEsenzioneAuraArchivio> query = em.createNamedQuery("EsenzioneDEsenzioneAuraArchivio.findByCodEsenzioneAndCodDiagnosi", EsenzioneDEsenzioneAuraArchivio.class);
	    query.setParameter("codEsenzione", codEsenzione);
	    query.setParameter("codDiagnosi", codDiagnosi);
	    EsenzioneDEsenzioneAuraArchivio esenzioneArchivio = query.getSingleResult();
	    return esenzioneArchivio;
	}

	@Override
	public EsenzioneDGruppoEsenzioni getEsenzioneDGruppoEsenzioneByTipoAura(String codTipologiaEsenzioneAura) {
		String queryString = "from EsenzioneDGruppoEsenzioni e "
				+ "left join fetch e.esenzioneRGruppoEsenzioniEsenzioneAuraTipos r "
				+ "left join fetch r.esenzioneDEsenzioneAuraTipo d "
				+ "where d.codTipologiaEsenzioneAura = :codTipologiaEsenzioneAura ";
		TypedQuery<EsenzioneDGruppoEsenzioni> query = em.createQuery(queryString, EsenzioneDGruppoEsenzioni.class);
		query.setParameter("codTipologiaEsenzioneAura", codTipologiaEsenzioneAura);
		try {
			return query.getSingleResult();
		} catch (NoResultException NoRes) {
			return null;
		}
	}

}