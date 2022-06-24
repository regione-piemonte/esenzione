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
import java.util.Iterator;
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

import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenpatweb.dto.FiltriListaCertificati;
import it.csi.esenred.esenpatweb.dto.FiltriListaEsenzioni;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaCittadino;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaPratiche;
import it.csi.esenred.esenpatweb.dto.StatoDocumento;
import it.csi.esenred.esenpatweb.dto.UserInfo;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDAziendasanitaria;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDAzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDCa;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDistrettoSocioSanitario;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDurataTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDErroriEsenpat;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDErroriGatewayFirma;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDInvaliditaTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPrestazioneSpecialistica;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRCaTypeOtp;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRDiagnosiPrestazione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTMetadatiDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTProgressivo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.model.interfaces.StatoPraticaIf;
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
	// private String AppID;
	ParametroIf parametroIf;
	StatoPraticaIf statoPraticaIf;
	
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE, null);

	public EsenredCMessaggi getMessaggio(String codice) {
		TypedQuery<EsenredCMessaggi> query = em.createNamedQuery("EsenredCMessaggi.findMessaggio",
				EsenredCMessaggi.class);
		query.setParameter("codice", codice);
		return query.getSingleResult();
	}

	public EsenredCParametri getParametro(String codice) {
		TypedQuery<EsenredCParametri> query = em.createNamedQuery("EsenredCParametri.findParametro",
				EsenredCParametri.class);
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
		TypedQuery<EsenredCMessaggi> query = em.createNamedQuery("EsenredCMessaggi.findPerCodiceLike",
				EsenredCMessaggi.class);

		query.setParameter("codice", codMessaggio.toUpperCase() + "%");

		return query.getResultList().isEmpty() ? new ArrayList<EsenredCMessaggi>() : query.getResultList();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertAudit(CsiLogAudit audit) {

		audit.setDataOra(new Timestamp(new Date().getTime()));
		em.persist(audit);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
	public List<EsenzioneTPraticaEsenzione> getListaEsenzioni(String cit_id, String esenzione_id,
			FiltriListaEsenzioni filtriListaEsenzioni) throws Exception {
		String queryString = "select distinct esenzioneTPraticaEsenzione from EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDPraticaStato esenzioneDPraticaStato ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneSPraticaEsenziones esenzioneSPraticaEsenziones ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDDiagnosi esenzioneDDiagnosi ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneRPraticaEsenzioneDocumentos esenzioneRPraticaEsenzioneDocumentos ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDMotivazioneTipo esenzioneDMotivazioneTipo ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni ";
		queryString += "left join fetch esenzioneRPraticaEsenzioneDocumentos.esenzioneTDocumento esenzioneTDocumento ";
		queryString += "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones ";
		queryString += "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica ";

		queryString += "where esenzioneTPraticaEsenzione.codiceFiscaleBeneficiario=:codiceFiscaleBeneficiario ";

		Boolean archiaviata = null;
		if (filtriListaEsenzioni != null) {
			if (filtriListaEsenzioni.getArchiviata() != null
					&& filtriListaEsenzioni.getArchiviata().getEq().equals("true")) {
				// solo pratiche archiviate
				queryString += " and esenzioneDPraticaStato.codStato in (:statiPratica) ";
				archiaviata = true;
			} else {
				// solo pratiche attive
				queryString += " and esenzioneDPraticaStato.codStato in (:statiPratica) ";
				archiaviata = false;
			}
			if (filtriListaEsenzioni.getStato() != null && filtriListaEsenzioni.getStato().getEq() != null) {
				queryString += " and esenzioneDPraticaStato.codStato =  '" + filtriListaEsenzioni.getStato().getEq()
						+ "' ";
			}
			if (filtriListaEsenzioni.getTipologia_esenzione() != null
					&& filtriListaEsenzioni.getTipologia_esenzione().getEq() != null) {
				queryString += " and esenzioneDGruppoEsenzioni.codTipologiaGruppo = '"
						+ filtriListaEsenzioni.getTipologia_esenzione().getEq() + "' ";
			}
		} else {
			if (esenzione_id == null || esenzione_id.isEmpty())
				throw new Exception("Nessun filtro impostato");
		}
		if (esenzione_id != null && !esenzione_id.isEmpty()) {
			queryString += " and esenzioneTPraticaEsenzione.skPraticaEsenzione = '" + esenzione_id + "'";
		}

		Query q = em.createQuery(queryString);
		q.setParameter("codiceFiscaleBeneficiario", cifraString(cit_id));
		if (archiaviata != null) {
			if (archiaviata.booleanValue()) {
				q.setParameter("statiPratica", Constants.STATI_PRATICA_ARCHIVIATA);
			} else {
				q.setParameter("statiPratica", Constants.STATI_PRATICA_NON_ARCHIVIATA);
			}
		}

		List<EsenzioneTPraticaEsenzione> esenzioni = q.getResultList();
		List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenzione = new ArrayList<EsenzioneTPraticaEsenzione>();
		for (EsenzioneTPraticaEsenzione ese : esenzioni) {
			EsenzioneTPraticaEsenzione esen = decifraEsenzione(ese);
			esenzioneTPraticaEsenzione.add(esen);
		}
		return esenzioneTPraticaEsenzione;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneTPraticaEsenzione loadFullEsenzioneTPraticaEsenzione(Integer skPraticaEsenzione) throws Exception {
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
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenredDAziendasanitaria esenredDAziendasanitaria ";

		queryString += "where esenzioneTPraticaEsenzione.skPraticaEsenzione=:skPraticaEsenzione ";

		Query query = em.createQuery(queryString);
		query.setParameter("skPraticaEsenzione", skPraticaEsenzione);

		EsenzioneTPraticaEsenzione esenzione = (EsenzioneTPraticaEsenzione) query.getSingleResult();
		EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = decifraEsenzione(esenzione);
		return esenzioneTPraticaEsenzione;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTDocumento> getListaCertificati(String cit_id, FiltriListaCertificati filtriListaCertificati) {
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
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni ";

		queryString += "where esenzioneTDocumento.codiceFiscaleCittadino = :codiceFiscaleCittadino ";
		queryString += "and esenzioneDDocumentoTipo.codDocumentoTipo = :codDocumentoTipo ";
		queryString += "and esenzioneDDocumentoStato.codStato = :stato ";
		
		if (filtriListaCertificati != null) {
			if (filtriListaCertificati.getUtilizzabile() != null
					&& filtriListaCertificati.getUtilizzabile().getEq().equalsIgnoreCase("true")) {
				queryString += "and (esenzioneRPraticaEsenzioneDocumentos is null OR esenzioneRPraticaEsenzioneDocumentos.esenzioneTPraticaEsenzione is null) ";
			} else if (filtriListaCertificati.getUtilizzabile() != null
					&& filtriListaCertificati.getUtilizzabile().getEq().equalsIgnoreCase("false")) {
				queryString += "and (esenzioneRPraticaEsenzioneDocumentos is not null OR esenzioneRPraticaEsenzioneDocumentos.esenzioneTPraticaEsenzione is not null) ";
			}
			if (filtriListaCertificati.getTipologia_esenzione() != null) {
				queryString += "and UPPER(esenzioneDGruppoEsenzioni.codTipologiaGruppo) LIKE \'"
						+ filtriListaCertificati.getTipologia_esenzione().getEq().toUpperCase() + "\' ";
			}
		}

		Query query = em.createQuery(queryString);
		query.setParameter("codiceFiscaleCittadino", cifraString(cit_id));
		query.setParameter("codDocumentoTipo", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		query.setParameter("stato", "2");
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
			TypedQuery<EsenzioneTCittadino> query = em.createNamedQuery("EsenzioneTCittadino.findCittadino",
					EsenzioneTCittadino.class);
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
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EsenzioneTPraticaEsenzione insertPraticaEsenzione(EsenzioneTPraticaEsenzione praticaEsenzione)
			throws Exception {
		praticaEsenzione = cifraEsenzione(praticaEsenzione);
		EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = em.merge(praticaEsenzione);
		esenzioneTPraticaEsenzione = loadFullEsenzioneTPraticaEsenzione(
				esenzioneTPraticaEsenzione.getSkPraticaEsenzione());
		return esenzioneTPraticaEsenzione;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertDocumentoPraticaEsenzione(EsenzioneTDocumento documento) {
		documento.setCodiceFiscaleCittadino(cifraString(documento.getCodiceFiscaleCittadino()));
		em.persist(documento);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertDocumentoStoricoPraticaEsenzione(EsenzioneSDocumento documento) {
		documento.setCodiceFiscaleCittadino(cifraString(documento.getCodiceFiscaleCittadino()));
		em.persist(documento);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertEsenzioneRPraticaDocumento(EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento) {
		em.persist(esenzioneRPraticaDocumento);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
		TypedQuery<EsenzioneTPraticaEsenzione> query = em
				.createNamedQuery("EsenzioneTPraticaEsenzione.findPerNumPratica", EsenzioneTPraticaEsenzione.class);
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

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
		TypedQuery<EsenzioneDPraticaStato> query = em.createNamedQuery("EsenzioneDPraticaStato.findPerCodStato",
				EsenzioneDPraticaStato.class);
		query.setParameter("codStato", codStato);
		List<EsenzioneDPraticaStato> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}

	public EsenzioneDMotivazioneTipo getEsenzioneDMotivazioneTipoPerCodMotivazione(String codMotivazione) {
		TypedQuery<EsenzioneDMotivazioneTipo> query = em
				.createNamedQuery("EsenzioneDMotivazioneTipo.findPerCodMotivazione", EsenzioneDMotivazioneTipo.class);
		query.setParameter("codMotivazione", codMotivazione);
		List<EsenzioneDMotivazioneTipo> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EsenzioneTPraticaEsenzione insertEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe) {
		etpe = cifraEsenzione(etpe);
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		etpe = em.merge(etpe);
		try {
			return loadFullEsenzioneTPraticaEsenzione(etpe.getSkPraticaEsenzione());
		} catch (Exception e) {
			return etpe;

		}
	}

	@Override
	public EsenzioneTCittadino getCittadinoByUserId(String userId) {
		try {
			TypedQuery<EsenzioneTCittadino> query = em.createNamedQuery("EsenzioneTCittadino.findCittadinoByUserId",
					EsenzioneTCittadino.class);
			query.setParameter("idUser", new Long(userId));
			List<EsenzioneTCittadino> resultList = query.getResultList();
			if (resultList.size() == 1) {
				EsenzioneTCittadino cittadino = resultList.get(0);
				EsenzioneTCittadino cit = decifraCittadino(cittadino);
				return cit;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public EsenzioneDEsenzione getEsenzioneDEsenzioneByDiagnosiId(String codeDiagnosi) {
		TypedQuery<EsenzioneDEsenzione> query = em.createNamedQuery("EsenzioneDEsenzione.findByDiagnosiId",
				EsenzioneDEsenzione.class);
		query.setParameter("idDiagnosi", new Integer(codeDiagnosi));
		List<EsenzioneDEsenzione> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}

	public EsenzioneDDistrettoSocioSanitario getCodiceAslByDistrettoId(String idDistretto) {
		TypedQuery<EsenzioneDDistrettoSocioSanitario> query = em.createNamedQuery(
				"EsenzioneDDistrettoSocioSanitario.findByDistrettoId", EsenzioneDDistrettoSocioSanitario.class);
		query.setParameter("idDistretto", idDistretto);
		List<EsenzioneDDistrettoSocioSanitario> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}

	public EsenzioneTProgressivo getNumeroProgressivo(String idAzienda, String codiceTipo) {
		TypedQuery<EsenzioneTProgressivo> query = em.createNamedQuery(
				"EsenzioneTProgressivo.findProgressivoByAziendaAndCodiceTipo", EsenzioneTProgressivo.class);
		query.setParameter("idAzienda", idAzienda);
		query.setParameter("codiceTipo", codiceTipo);
		List<EsenzioneTProgressivo> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void setAnnoProgressivo(EsenzioneTProgressivo prog) {
		em.merge(prog);
	}

	public EsenzioneRPraticaEsenzioneDocumento getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(
			String documentoId) {
		TypedQuery<EsenzioneRPraticaEsenzioneDocumento> query = em.createNamedQuery(
				"EsenzioneRPraticaEsenzioneDocumento.findByDocumentoId", EsenzioneRPraticaEsenzioneDocumento.class);
		query.setParameter("documentoId", new Long(documentoId));
		List<EsenzioneRPraticaEsenzioneDocumento> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}

	public EsenzioneTPraticaEsenzione getPraticaEsenzioneByStatus(String cf, Integer codMalattia, String codTipo,
			String[] status) {
		TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery("EsenzioneTPraticaEsenzione.findByStatus",
				EsenzioneTPraticaEsenzione.class);
		query.setParameter("codFiscale", cifraString(cf));
		query.setParameter("codMalattia", codMalattia);
		query.setParameter("codTipo", new Integer(codTipo));
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

	@Override
	public EsenzioneDDocumentoStato getEsenzioneDDocumentoStatoPerCodStato(String codStato) {
		TypedQuery<EsenzioneDDocumentoStato> query = em.createNamedQuery("EsenzioneDDocumentoStato.findPerCodStato",
				EsenzioneDDocumentoStato.class);
		query.setParameter("codStato", codStato);
		List<EsenzioneDDocumentoStato> resultList = query.getResultList();
		if (resultList.size() == 1)
			return resultList.get(0);
		return null;
	}

	@Override
	public List<EsenzioneDDocumentoStato> getStatoDocumento(String codStato) {
		String queryStr = "SELECT e FROM EsenzioneDDocumentoStato e where ( ";

		if (Checker.isValorizzato(codStato))
			queryStr = queryStr + " e.codStato = :codStato and ";

		queryStr = queryStr + " 1 = 1 ) order by e.codStato asc ";

		TypedQuery<EsenzioneDDocumentoStato> query = em.createQuery(queryStr, EsenzioneDDocumentoStato.class);

		if (Checker.isValorizzato(codStato))
			query.setParameter("codStato", codStato);

		return query.getResultList();
	}

	@Override
	public List<EsenzioneDEsenzione> getCodiceEsenzionePatologia(String codTipologiaGruppo) {

		String queryString = "select distinct e from EsenzioneDEsenzione e,EsenzioneDGruppoEsenzioni a ";
		queryString += "where ( a.skGruppo=e.skGruppo and ";
		if (Checker.isValorizzato(codTipologiaGruppo))
			queryString = queryString + " a.codTipologiaGruppo=:codTipologiaGruppo and ";
		queryString += " 1 = 1 ) order by e.descEsenzione asc ";

		TypedQuery<EsenzioneDEsenzione> query = em.createQuery(queryString, EsenzioneDEsenzione.class);

		if (Checker.isValorizzato(codTipologiaGruppo))
			query.setParameter("codTipologiaGruppo", codTipologiaGruppo);

		return query.getResultList();
	}

	@Override
	public List<EsenzioneDGruppoEsenzioni> getGruppoEsenzionePatologia(String codTipologiaGruppo) {

		String queryString = "select distinct e from EsenzioneDGruppoEsenzioni e where( ";
		if (Checker.isValorizzato(codTipologiaGruppo))
			queryString = queryString + " e.codTipologiaGruppo=:codTipologiaGruppo and ";
		queryString += " 1 = 1 ) order by e.descGruppo desc ";

		TypedQuery<EsenzioneDGruppoEsenzioni> query = em.createQuery(queryString, EsenzioneDGruppoEsenzioni.class);

		if (Checker.isValorizzato(codTipologiaGruppo))
			query.setParameter("codTipologiaGruppo", codTipologiaGruppo);

		return query.getResultList();
	}

	@Override
	public List<EsenzioneDGruppoEsenzioni> getGruppoEsenzionePatologiaInCombo(int incombo) {

		String queryString = "select distinct e from EsenzioneDGruppoEsenzioni e where( ";
		if (Checker.isValorizzato(incombo))
			queryString = queryString + " e.incombo=:incombo and ";
		queryString += " 1 = 1 ) order by e.descGruppo desc ";

		TypedQuery<EsenzioneDGruppoEsenzioni> query = em.createQuery(queryString, EsenzioneDGruppoEsenzioni.class);

		if (Checker.isValorizzato(incombo))
			query.setParameter("incombo", incombo);

		return query.getResultList();
	}

	@Override
	public boolean setRinnovoEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		insertEsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzione);
		return false;
	}

	/*
	 * Elenco pratiche esenzione correlate ad un codice fiscale con data inizio
	 * validita' not null
	 */
	public List<EsenzioneTPraticaEsenzione> getListPraticaEsenzioneByCodFiscale(String cf) {
		TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery("EsenzioneTPraticaEsenzione.findByCf",
				EsenzioneTPraticaEsenzione.class);
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
	@Override
	public EsenzioneTRepositoryDocumentale getEsenzioneTRepositoryDocumentale(String certificato_id) {
		EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = null;
		log.info("getEsenzioneTRepositoryDocumentale", " BEGIN");
		Query queryDoc = em.createQuery(
				"from EsenzioneTDocumento a left join fetch a.esenzioneDDocumentoTipo b where a.skDocumento=:certificatoId and b.codDocumentoTipo = :tipo");
		queryDoc.setParameter("certificatoId", (new Integer(certificato_id)));
		queryDoc.setParameter("tipo", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		log.info("getEsenzioneTRepositoryDocumentale", " 1 - Ricerco su EsenzioneTDocumento ");
		log.info("getEsenzioneTRepositoryDocumentale", " param query certificatoId: " + certificato_id);
		log.info("getEsenzioneTRepositoryDocumentale", " param query tipo: " +  Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		
		try {
			EsenzioneTDocumento doc = (EsenzioneTDocumento) queryDoc.getSingleResult();
			log.info("getEsenzioneTRepositoryDocumentale", " documento trovato: " + doc.getSkRepository());
				
			log.info("getEsenzioneTRepositoryDocumentale", " 2 - Ricerco su EsenzioneTRepositoryDocumentale ");
			Query query = em.createQuery("from EsenzioneTRepositoryDocumentale where skRepository=:skRepository");
			query.setParameter("skRepository", (new Integer((int) (long) doc.getSkRepository())));
			esenzioneTRepositoryDocumentale = (EsenzioneTRepositoryDocumentale) query.getSingleResult();
			
			log.info("getEsenzioneTRepositoryDocumentale", " documento su repository trovato: " + doc.getSkRepository());
			
			esenzioneTRepositoryDocumentale.setFile(decifraFileDocumento(esenzioneTRepositoryDocumentale.getFile()));
		} catch (NoResultException NoRes) {
			esenzioneTRepositoryDocumentale = null;
		}
		log.info("getEsenzioneTRepositoryDocumentale", " END");
		return esenzioneTRepositoryDocumentale;
	}

	@Override
	public EsenzioneTRepositoryDocumentale getRepositoryDocumentale(Integer skRepository) {
		EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = null;
		try {
			Query query = em.createQuery("from EsenzioneTRepositoryDocumentale where skRepository=:skRepository");
			query.setParameter("skRepository", skRepository);
			esenzioneTRepositoryDocumentale = (EsenzioneTRepositoryDocumentale) query.getSingleResult();
			esenzioneTRepositoryDocumentale.setFile(decifraFileDocumento(esenzioneTRepositoryDocumentale.getFile()));
		} catch (NoResultException NoRes) {
			esenzioneTRepositoryDocumentale = null;
		}
		return esenzioneTRepositoryDocumentale;
	}

	/*
	 * Ricerca per specifica pratica esenzione di tutti i documenti ad essa
	 * associati
	 */
	public List<EsenzioneRPraticaEsenzioneDocumento> getEsenzioneRPraticaEsenzioneDocumentoBySkPratica(
			String praticaEsenzioneId) {
		TypedQuery<EsenzioneRPraticaEsenzioneDocumento> query = em.createNamedQuery(
				"EsenzioneRPraticaEsenzioneDocumento.findByPraticaId", EsenzioneRPraticaEsenzioneDocumento.class);
		query.setParameter("praticaEsenzioneId", new Long(praticaEsenzioneId));
		List<EsenzioneRPraticaEsenzioneDocumento> resultList = query.getResultList();
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EsenzioneTPraticaEsenzione updateEsenzioneTPraticaEsenzione(
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione) {
		// esenzioneTPraticaEsenzione = cifraEsenzione(esenzioneTPraticaEsenzione);
		esenzioneTPraticaEsenzione
				.setCodiceFiscaleBeneficiario(cifraString(esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario()));
		esenzioneTPraticaEsenzione
				.setCodiceFiscaleDelegato(cifraString(esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato()));
		esenzioneTPraticaEsenzione
				.setEsenzioneTCittadino1(cifraCittadino(esenzioneTPraticaEsenzione.getEsenzioneTCittadino1()));
		return decifraEsenzione(em.merge(esenzioneTPraticaEsenzione));
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
	public List<EsenzioneTPraticaEsenzione> getElencoPraticheEsenzioneValideByDiagnosi(String cf,
			Integer[] listSkDiagnosi, String codStato) throws Exception {
		TypedQuery<EsenzioneTPraticaEsenzione> query = em
				.createNamedQuery("EsenzioneTPraticaEsenzione.findBySkEsenzione", EsenzioneTPraticaEsenzione.class);
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EsenzioneTDocumento updateEsenzioneTDocumento(EsenzioneTDocumento esenzioneTDocumento) {
		esenzioneTDocumento.setCodiceFiscaleCittadino(cifraString(esenzioneTDocumento.getCodiceFiscaleCittadino()));
		return decifraDocumento(em.merge(esenzioneTDocumento));
	}

	/*
	 * Ricerca esenzione per codice
	 */
	public EsenzioneDEsenzione getEsenzioneDEsenzione(String codEsenzione) {
		Query query = em.createQuery("from EsenzioneDEsenzione where codEsenzione=:codEsenzione");
		query.setParameter("codEsenzione", codEsenzione);
		try {
			EsenzioneDEsenzione esenzioneDEsenzione = (EsenzioneDEsenzione) query.getSingleResult();
			return esenzioneDEsenzione;
		} catch (NoResultException nre) {
			return null;
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTCittadino> getAssistito(UserInfo assistito, UserInfo utente, boolean cas) {
		
		String queryString = "from EsenzioneTCittadino esenzioneTCittadino ";
		queryString += "where (1=1) ";

		if (!cas) {
			queryString += "and esenzioneTCittadino.idAzienda = :codAsl ";
		}
		if (assistito.getCodFisc() != null && assistito.getCodFisc() != "") {
			queryString += "and esenzioneTCittadino.codiceFiscale = :codiceFiscale ";
		} else {
			queryString += "and esenzioneTCittadino.cognome = :cognome ";
			if (assistito.getNome() != null && assistito.getNome() != "") {
				queryString += "and esenzioneTCittadino.nome = :nome ";
			}
		}

		Query query = em.createQuery(queryString);

		if (!cas) {
			query.setParameter("codAsl", utente.getCodASL());
		}
		if (assistito.getCodFisc() != null && assistito.getCodFisc() != "") {
			query.setParameter("codiceFiscale", cifraString(assistito.getCodFisc()));
		} else {
			query.setParameter("cognome", cifraString(assistito.getCognome()));
			if (assistito.getNome() != null && assistito.getNome() != "") {
				query.setParameter("nome", cifraString(assistito.getNome()));
			}
		}
		List<EsenzioneTCittadino> resultList = query.getResultList();
		if (resultList.size() > 0) {
			List<EsenzioneTCittadino> listaCittadini = new ArrayList<EsenzioneTCittadino>();
			for (EsenzioneTCittadino cit : resultList) {
				EsenzioneTCittadino c = decifraCittadino(cit);
				listaCittadini.add(c);

			}
			return listaCittadini;

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTCittadino> getAssistitoPato(FiltriRicercaCittadino filtri, String codasl) {

		String queryString = "from EsenzioneTCittadino esenzioneTCittadino where( ";
		if (Checker.isValorizzato(codasl))
			queryString += "esenzioneTCittadino.idAzienda = :codAsl and ";

		if (filtri.getCodFiscale() != null && filtri.getCodFiscale() != "") {
			queryString += "esenzioneTCittadino.codiceFiscale = :codiceFiscale and ";
		} else {
			queryString += "esenzioneTCittadino.cognome = :cognome and ";
			if (filtri.getNome() != null && filtri.getNome() != "") {
				queryString += "esenzioneTCittadino.nome = :nome and ";
			}
		}
		queryString += "1 = 1)";
		Query query = em.createQuery(queryString);
		if (Checker.isValorizzato(codasl))
			query.setParameter("codAsl", codasl);

		if (filtri.getCodFiscale() != null && filtri.getCodFiscale() != "") {
			query.setParameter("codiceFiscale", cifraString(filtri.getCodFiscale().toUpperCase()));
		} else {
			query.setParameter("cognome", cifraString(filtri.getCognome().toUpperCase()));
			if (filtri.getNome() != null && filtri.getNome() != "") {
				query.setParameter("nome", cifraString(filtri.getNome().toUpperCase()));
			}
		}
		List<EsenzioneTCittadino> resultList = query.getResultList();
		if (resultList.size() > 0) {
			List<EsenzioneTCittadino> listaCittadini = new ArrayList<EsenzioneTCittadino>();
			for (EsenzioneTCittadino cit : resultList) {
				EsenzioneTCittadino c = decifraCittadino(cit);
				listaCittadini.add(c);
			}
			return listaCittadini;

		}
		return null;
	}

	/**
	 * Ritorna l'elenco di azioni ammesse per uno o pi� ruoli
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDAzione> getAzioni(List<Ruolo> ruoli) {
		try {
			List<EsenzioneDAzione> lista = new ArrayList<EsenzioneDAzione>();
			for (Ruolo ruolo : ruoli) {
				String queryStr = "from EsenzioneDAzione a inner join fetch a.esenzioneRAzioniGruppoUtentis b inner join fetch b.esenzioneDGruppoUtenti c "
						// + " on a.skGruppoUtenti = b.skGruppoUtenti " + " "
						+ "where c.codGruppoUtenti = :codGruppoUtenti ";

				Query query = em.createQuery(queryStr);

				query.setParameter("codGruppoUtenti", ruolo.getCodiceRuolo());

				lista.addAll(query.getResultList());
			}

			// Fix laxy initialize
			List<EsenzioneDAzione> listaReturn = new ArrayList<EsenzioneDAzione>();
			for (EsenzioneDAzione single : lista) {
				EsenzioneDAzione tmp = new EsenzioneDAzione();
				tmp.setCodAzione(single.getCodAzione());
				tmp.setDescAzione(single.getDescAzione());
				tmp.setSkAzione(single.getSkAzione());
				listaReturn.add(tmp);
			}

			return listaReturn;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTPraticaEsenzione> getListaEsenzioniByCodiceFiscale(String cf) {
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
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenredDAziendasanitaria esenredDAziendasanitaria ";

		queryString += "where esenzioneTPraticaEsenzione.codiceFiscaleBeneficiario = :codiceFiscaleBeneficiario ";
		queryString += "order by esenzioneTPraticaEsenzione.datCreazione desc, esenzioneTPraticaEsenzione.datModifica desc, esenzioneTPraticaEsenzione.numPratica desc ";

		Query query = em.createQuery(queryString);
		query.setParameter("codiceFiscaleBeneficiario", cifraString(cf));
		List<EsenzioneTPraticaEsenzione> result = query.getResultList();
		List<EsenzioneTPraticaEsenzione> listaPratiche = new ArrayList<EsenzioneTPraticaEsenzione>();
		for (EsenzioneTPraticaEsenzione ese : result) {
			EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);
			listaPratiche.add(esenzione);
		}
		return listaPratiche;
	}

	/*
	 * Ricerca documentoTipo per codice
	 */
	public EsenzioneDDocumentoTipo getEsenzioneDDocumentoTipo(String codTipoDocumento) {
		Query query = em.createQuery("from EsenzioneDDocumentoTipo where codDocumentoTipo=:codTipoDocumento");
		query.setParameter("codTipoDocumento", codTipoDocumento.toLowerCase());
		EsenzioneDDocumentoTipo esenzioneDDocumentoTipo = (EsenzioneDDocumentoTipo) query.getSingleResult();
		return esenzioneDDocumentoTipo;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertMetadatiDocumento(EsenzioneTMetadatiDocumento metadati) {
		metadati.setCodFiscalePaziente(cifraString(metadati.getCodFiscalePaziente()));
		metadati.setCognomePaziente(cifraString(metadati.getCognomePaziente()));
		metadati.setNomePaziente(cifraString(metadati.getNomePaziente()));
		em.persist(metadati);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateMetadatiDocumento(EsenzioneTMetadatiDocumento metadati) {
		em.merge(metadati);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDiagnosi> getListaDiagnosiByCodiceGruppoEsenzione(EsenzioneDGruppoEsenzioni gruppoEsenzione) {
		Query query = em.createQuery(
				"select d.codDiagnosi, d.descDiagnosi from EsenzioneDDiagnosi d where d.skEsenzione IN ( select e.skEsenzione from EsenzioneDEsenzione e where e.skGruppo=:skGruppo )");
		query.setParameter("skGruppo", new Long(gruppoEsenzione.getSkGruppo()));
		List<EsenzioneDDiagnosi> elencoDiagnosiGruppo = query.getResultList();
		return elencoDiagnosiGruppo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneDGruppoEsenzioni getGruppoEsenzioneByCodice(String codGruppo) {
		Query query = em.createQuery("from EsenzioneDGruppoEsenzioni where codTipologiaGruppo=:codGruppo");
		query.setParameter("codGruppo", codGruppo);
		EsenzioneDGruppoEsenzioni esenzioneGruppoEsenzione = (EsenzioneDGruppoEsenzioni) query.getSingleResult();
		return esenzioneGruppoEsenzione;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDEsenzione> getListaEsenzioniByGruppoEsenzione(EsenzioneDGruppoEsenzioni gruppoEsenzione) {
		Query query = em.createQuery("from EsenzioneDEsenzione where skGruppo=:skGruppo");
		query.setParameter("skGruppo", new Long(gruppoEsenzione.getSkGruppo()));
		List<EsenzioneDEsenzione> elencoEsenzioniGruppo = query.getResultList();
		return elencoEsenzioniGruppo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDocumentoTipo> getListaDocumentoTipo() {
		Query query = em.createQuery("from EsenzioneDDocumentoTipo");
		List<EsenzioneDDocumentoTipo> elencoDocumentoTipo = query.getResultList();
		return elencoDocumentoTipo;
	}

	public EsenzioneTDocumento getDocumentoByCodFiscaleAndCodDiagnosi(String codFiscale, String codDiagnosi) {
		EsenzioneTDocumento documento;
		try {
			EsenzioneDDiagnosi esenzioneDDiagnosi = this.getEsenzioneDDiagnosi(codDiagnosi);

			Query query = em.createQuery(
					"FROM EsenzioneTDocumento WHERE codiceFiscaleCittadino=:codFiscale AND skDiagnosi=:skDiagnosi");
			query.setParameter("codFiscale", cifraString(codFiscale));
			query.setParameter("skDiagnosi", new Long(esenzioneDDiagnosi.getSkDiagnosi()));
			EsenzioneTDocumento doc = (EsenzioneTDocumento) query.getSingleResult();
			documento = decifraDocumento(doc);
		} catch (NoResultException NoRes) {
			documento = null;
		}
		return documento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDEsenzione> getListaPatologieByGruppoEsenzione(EsenzioneDGruppoEsenzioni gruppoEsenzione) {
		String queryString = "from EsenzioneDEsenzione e ";
		queryString += "left join fetch e.esenzioneDDiagnosis esenzioneDDiagnosi ";
		queryString += "where e.skGruppo = :skGruppo ";
		Query query = em.createQuery(queryString);
		query.setParameter("skGruppo", new Long(gruppoEsenzione.getSkGruppo()));

		List<EsenzioneDEsenzione> resultList = query.getResultList();
		if (resultList.size() > 0)
			return resultList;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTDocumento> getCertificatiPatologia(FiltriRicercaCertificatoPatologia filtri, String codasl,
			int pagesize, String cfMed) {

		String queryString = "select a ";
		queryString += "from EsenzioneTDocumento a,EsenzioneDDiagnosi b, ";
		queryString += "EsenzioneDEsenzione c, EsenzioneDDocumentoTipo d, EsenzioneTMetadatiDocumento e, ";
		queryString += "EsenzioneDDocumentoStato f, EsenzioneTCittadino g, EsenzioneDGruppoEsenzioni h ";
		queryString += "where (a.skDiagnosi=b.skDiagnosi and ";
		queryString += "h.skGruppo=c.skGruppo and ";
		queryString += "c.skEsenzione=b.skEsenzione and ";
		queryString += "a.skTipoDocumento=d.skDocumentoTipo and ";
		queryString += "a.skDocumento=e.skDocumento and ";
		queryString += "a.skTipologiaStatoDocumento=f.skDocumentoStato and ";
		queryString += "g.codiceFiscale=a.codiceFiscaleCittadino and ";
		queryString += "d.codDocumentoTipo = :tipoDoc and ";
		if (Checker.isValorizzato(cfMed))
			queryString += "e.medicoRedattore = :cfMed and ";
		if (Checker.isValorizzato(codasl))
			queryString += "g.idAzienda = :codAsl and ";
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			queryString += "f.codStato = :codStato and ";
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			queryString += "g.id_aura = :idAuraBeneficiario and ";
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			queryString += "to_date(to_char(a.datInizioValidita,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			queryString += "(to_date(to_char(a.datFineValidita,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') or a.datFineValidita is null) and ";
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			queryString += "c.codEsenzione = :codCertificato and ";
		queryString += "1 = 1) order by g.cognome || ' ' || g.nome || ' ' || g.codiceFiscale asc,a.datCreazione desc ";
		Query query = em.createQuery(queryString);
		query.setParameter("tipoDoc", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		if (filtri.getPagina() == 1)
			query.setFirstResult(filtri.getPagina() - 1);
		else
			query.setFirstResult(filtri.getPagina() * pagesize - pagesize);
		query.setMaxResults(pagesize);
		if (Checker.isValorizzato(cfMed))
			query.setParameter("cfMed", cfMed);
		if (Checker.isValorizzato(codasl))
			query.setParameter("codAsl", codasl);
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			query.setParameter("codStato", filtri.getCodStatoCertificato());
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			query.setParameter("codCertificato", filtri.getCodCertificato());
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			query.setParameter("idAuraBeneficiario", filtri.getIdAuraBeneficiario());
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			query.setParameter("dataInizioValidita", filtri.getDataInizioValidita());
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			query.setParameter("dataFineValidita", filtri.getDataFineValidita());
		List<EsenzioneTDocumento> resultList = query.getResultList();

		if (resultList.size() > 0) {
			List<EsenzioneTDocumento> listaDocumenti = new ArrayList<EsenzioneTDocumento>();
			for (EsenzioneTDocumento doc : resultList) {
				EsenzioneTDocumento documento = decifraDocumento(doc);
				listaDocumenti.add(documento);
			}
			return listaDocumenti;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long ContaCertificatiPatologia(FiltriRicercaCertificatoPatologia filtri, String codasl, String cfMed) {

		String queryString = "select count(a) ";
		queryString += "from EsenzioneTDocumento a,EsenzioneDDiagnosi b, ";
		queryString += "EsenzioneDEsenzione c, EsenzioneDDocumentoTipo d, EsenzioneTMetadatiDocumento e, ";
		queryString += "EsenzioneDDocumentoStato f, EsenzioneTCittadino g, EsenzioneDGruppoEsenzioni h ";
		queryString += "where (a.skDiagnosi=b.skDiagnosi and ";
		queryString += "h.skGruppo=c.skGruppo and ";
		queryString += "c.skEsenzione=b.skEsenzione and ";
		queryString += "a.skTipoDocumento=d.skDocumentoTipo and ";
		queryString += "a.skDocumento=e.skDocumento and ";
		queryString += "a.skTipologiaStatoDocumento=f.skDocumentoStato and ";
		queryString += "g.codiceFiscale=a.codiceFiscaleCittadino and ";
		queryString += "d.codDocumentoTipo = :tipoDoc and ";
		if (Checker.isValorizzato(cfMed))
			queryString += "e.medicoRedattore = :cfMed and ";
		if (Checker.isValorizzato(codasl))
			queryString += "g.idAzienda = :codAsl and ";
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			queryString += "f.codStato = :codStato and ";
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			queryString += "g.id_aura = :idAuraBeneficiario and ";
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			queryString += "to_date(to_char(a.datInizioValidita,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			queryString += "(to_date(to_char(a.datFineValidita,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') or a.datFineValidita is null) and ";
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			queryString += "c.codEsenzione = :codCertificato and ";
		queryString += "1 = 1)";
		Query query = em.createQuery(queryString);
		query.setParameter("tipoDoc", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		if (Checker.isValorizzato(cfMed))
			query.setParameter("cfMed", cfMed);
		if (Checker.isValorizzato(codasl))
			query.setParameter("codAsl", codasl);
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			query.setParameter("codStato", filtri.getCodStatoCertificato());
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			query.setParameter("codCertificato", filtri.getCodCertificato());
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			query.setParameter("idAuraBeneficiario", filtri.getIdAuraBeneficiario());
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			query.setParameter("dataInizioValidita", filtri.getDataInizioValidita());
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			query.setParameter("dataFineValidita", filtri.getDataFineValidita());
		Long resultList = (Long) query.getSingleResult();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTPraticaEsenzione> getEsenzionePatologia(FiltriRicercaCertificatoPatologia filtri,
			String codasl, String cfMed) {

		String stato = "";
		if (filtri.getCodStatoCertificato() == null) {
			// vuol dire tutti gli stati individuati
			statoPraticaIf = (StatoPraticaIf) SpringApplicationContext.getBean("statopratica");
			List<EsenzioneDPraticaStato> statodocumento = statoPraticaIf
					.getStatoPratica(Constants.AZIONE_RICERCA_ESENZIONE);
			for (Iterator<EsenzioneDPraticaStato> iterator = statodocumento.iterator(); iterator.hasNext();) {
				EsenzioneDPraticaStato eDB = (EsenzioneDPraticaStato) iterator.next();
				stato = stato + "'" + eDB.getCodStato() + "',";
			}
			stato = stato.substring(0, stato.length() - 1);
		}

		String queryString = "select a ";
		queryString += "from EsenzioneTPraticaEsenzione a,EsenzioneDDiagnosi b, ";
		queryString += "EsenzioneDEsenzione c, ";
		queryString += "EsenzioneDPraticaStato f, EsenzioneTCittadino g, EsenzioneDGruppoEsenzioni h ";
		queryString += "where (a.skDiagnosi=b.skDiagnosi and ";
		queryString += "h.skGruppo=c.skGruppo and ";
		queryString += "c.skEsenzione=b.skEsenzione and ";
		queryString += "a.skTipologiaStatoPratica=f.skPraticaStato and ";
		queryString += "g.codiceFiscale=a.codiceFiscaleBeneficiario and ";
		if (Checker.isValorizzato(cfMed))
			queryString += "a.codiceFiscaleOperatore = :cfMed and ";
		if (Checker.isValorizzato(codasl))
			queryString += "g.idAzienda = :codAsl and ";
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			queryString += "f.codStato in (:codStato) and ";
		else
			queryString += "f.codStato in (" + stato + ") and ";
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			queryString += "g.id_aura = :idAuraBeneficiario and ";
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			queryString += "to_date(to_char(a.datInizioValidita,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			queryString += "(to_date(to_char(a.datFineValidita,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') or a.datFineValidita is null) and ";
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			queryString += "c.codEsenzione = :codCertificato and ";
		queryString += "1 = 1)";
		Query query = em.createQuery(queryString);
		if (Checker.isValorizzato(cfMed))
			query.setParameter("cfMed", cfMed);
		if (Checker.isValorizzato(codasl))
			query.setParameter("codAsl", codasl);
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			query.setParameter("codStato", filtri.getCodStatoCertificato());
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			query.setParameter("codCertificato", filtri.getCodCertificato());
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			query.setParameter("idAuraBeneficiario", filtri.getIdAuraBeneficiario());
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			query.setParameter("dataInizioValidita", filtri.getDataInizioValidita());
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			query.setParameter("dataFineValidita", filtri.getDataFineValidita());
		List<EsenzioneTPraticaEsenzione> resultList = query.getResultList();
		if (resultList.size() > 0) {
			List<EsenzioneTPraticaEsenzione> listaPratiche = new ArrayList<EsenzioneTPraticaEsenzione>();
			for (EsenzioneTPraticaEsenzione ese : resultList) {
				EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);
				listaPratiche.add(esenzione);
			}
			return listaPratiche;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long ContaRicercaCertificato(FiltriRicercaCertificatoPatologia filtri, String codasl) {

		String queryString = "select count(a) ";
		queryString += "from EsenzioneTPraticaEsenzione a,EsenzioneDDiagnosi b, ";
		queryString += "EsenzioneDEsenzione c, ";
		queryString += "EsenzioneDPraticaStato f, EsenzioneTCittadino g, EsenzioneDGruppoEsenzioni h ";
		queryString += "where (a.skDiagnosi=b.skDiagnosi and ";
		queryString += "h.skGruppo=c.skGruppo and ";
		queryString += "c.skEsenzione=b.skEsenzione and ";
		queryString += "a.skTipologiaStatoPratica=f.skPraticaStato and ";
		queryString += "g.codiceFiscale=a.codiceFiscaleBeneficiario and ";
		if (Checker.isValorizzato(codasl))
			queryString += "g.idAzienda = :codAsl and ";
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			queryString += "f.codStato in (:codStato) and ";
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			queryString += "g.id_aura = :idAuraBeneficiario and ";
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			queryString += "to_date(to_char(a.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			queryString += "to_date(to_char(a.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			queryString += "c.codEsenzione = :codCertificato and ";
		queryString += "1 = 1)";
		Query query = em.createQuery(queryString);
		if (Checker.isValorizzato(codasl))
			query.setParameter("codAsl", codasl);
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			query.setParameter("codStato", filtri.getCodStatoCertificato());
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			query.setParameter("codCertificato", filtri.getCodCertificato());
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			query.setParameter("idAuraBeneficiario", filtri.getIdAuraBeneficiario());
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			query.setParameter("dataInizioValidita", filtri.getDataInizioValidita());
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			query.setParameter("dataFineValidita", filtri.getDataFineValidita());
		Long resultList = (Long) query.getSingleResult();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTPraticaEsenzione> getRicercaCertificato(FiltriRicercaCertificatoPatologia filtri,
			String codasl, int pagesize) {

		String queryString = "select a ";
		queryString += "from EsenzioneTPraticaEsenzione a,EsenzioneDDiagnosi b, ";
		queryString += "EsenzioneDEsenzione c, ";
		queryString += "EsenzioneDPraticaStato f, EsenzioneTCittadino g, EsenzioneDGruppoEsenzioni h ";
		queryString += "where (a.skDiagnosi=b.skDiagnosi and ";
		queryString += "h.skGruppo=c.skGruppo and ";
		queryString += "c.skEsenzione=b.skEsenzione and ";
		queryString += "a.skTipologiaStatoPratica=f.skPraticaStato and ";
		queryString += "g.codiceFiscale=a.codiceFiscaleBeneficiario and ";
		if (Checker.isValorizzato(codasl))
			queryString += "g.idAzienda = :codAsl and ";
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			queryString += "f.codStato in (:codStato) and ";
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			queryString += "g.id_aura = :idAuraBeneficiario and ";
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			queryString += "to_date(to_char(a.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			queryString += "to_date(to_char(a.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') and ";
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			queryString += "c.codEsenzione = :codCertificato and ";
		queryString += "1 = 1) ";
		if (filtri.getOrderBy() != null) {
			String order = "desc";
			if (filtri.isAsc())
				order = "asc";
			if (filtri.getOrderBy().toLowerCase().equals("numpratica"))
				queryString = queryString + "order by a.numPratica " + order;
			if (filtri.getOrderBy().toLowerCase().equals("idazienda"))
				queryString = queryString + "order by g.idAzienda " + order;
			if (filtri.getOrderBy().toLowerCase().equals("beneficiario"))
				queryString = queryString + "order by g.cognome || ' ' || g.nome " + order;
//				if (filtri.getOrderBy().toLowerCase().equals("compilatore"))
//					queryString = queryString + "order by a.codiceFiscaleOperatore " + order;
			if (filtri.getOrderBy().toLowerCase().equals("datarichiesta"))
				queryString = queryString + "order by a.datCreazione " + order;
			if (filtri.getOrderBy().toLowerCase().equals("finevalidita"))
				queryString = queryString + "order by a.datFineValidita " + order;
			if (filtri.getOrderBy().toLowerCase().equals("iniziovalidita"))
				queryString = queryString + "order by a.datInizioValidita " + order;
			if (filtri.getOrderBy().toLowerCase().equals("beneficiarionumpratica"))
				queryString = queryString + "order by g.cognome || ' ' || g.nome asc, a.numPratica desc";

		} else
			queryString = queryString + "order by g.cognome || ' ' || g.nome asc, a.numPratica desc ";
		Query query = em.createQuery(queryString);
		if (filtri.getPagina() == 1)
			query.setFirstResult(filtri.getPagina() - 1);
		else
			query.setFirstResult(filtri.getPagina() * pagesize - pagesize);
		query.setMaxResults(pagesize);
		if (Checker.isValorizzato(codasl))
			query.setParameter("codAsl", codasl);
		if (Checker.isValorizzato(filtri.getCodStatoCertificato()))
			query.setParameter("codStato", filtri.getCodStatoCertificato());
		if (Checker.isValorizzato(filtri.getCodCertificato()) && !filtri.getCodCertificato().equalsIgnoreCase("TUTTE"))
			query.setParameter("codCertificato", filtri.getCodCertificato());
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			query.setParameter("idAuraBeneficiario", filtri.getIdAuraBeneficiario());
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			query.setParameter("dataInizioValidita", filtri.getDataInizioValidita());
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			query.setParameter("dataFineValidita", filtri.getDataFineValidita());
		List<EsenzioneTPraticaEsenzione> resultList = query.getResultList();
		if (resultList.size() > 0) {
			List<EsenzioneTPraticaEsenzione> listaPratiche = new ArrayList<EsenzioneTPraticaEsenzione>();
			for (EsenzioneTPraticaEsenzione ese : resultList) {
				EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);
				listaPratiche.add(esenzione);
			}
			return listaPratiche;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDiagnosi> getListaDiagnosi() {
		Query query = em.createQuery("select esenzioneDDiagnosi " + "from EsenzioneDDiagnosi esenzioneDDiagnosi "
				+ "left join fetch esenzioneDDiagnosi.esenzioneDEsenzione esenzioneDEsenzione "
				+ "left join fetch esenzioneDEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni");
		List<EsenzioneDDiagnosi> elencoDiagnosi = query.getResultList();
		return elencoDiagnosi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDEsenzione> getListaEsenzioni() {
		Query query = em.createQuery("select esenzioneDEsenzione " + "from EsenzioneDEsenzione esenzioneDEsenzione "
				+ "left join fetch esenzioneDEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni");
		List<EsenzioneDEsenzione> elencoEsenzioni = query.getResultList();
		return elencoEsenzioni;

	}

	public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzioneperskpratica(String skpraticaesenzione) {

		TypedQuery<EsenzioneTPraticaEsenzione> query = em.createNamedQuery(
				"EsenzioneTPraticaEsenzione.findBySkPraticaEsenzione", EsenzioneTPraticaEsenzione.class);
		query.setParameter("skPraticaEsenzione", new Integer(skpraticaesenzione));
		List<EsenzioneTPraticaEsenzione> resultList = query.getResultList();
		if (resultList.size() == 1) {
			EsenzioneTPraticaEsenzione ese = resultList.get(0);
			EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);

			return esenzione;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public EsenzioneDDurataTipo getEsenzioneDDurataTipoperSKDurataTipo(Long skduratatipo) {
		TypedQuery<EsenzioneDDurataTipo> query = em.createNamedQuery("EsenzioneDDurataTipo.findperskduratatipo",
				EsenzioneDDurataTipo.class);

		query.setParameter("skduratatipo", new Integer(skduratatipo.toString()));

		return query.getResultList().isEmpty() ? new EsenzioneDDurataTipo() : query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneDGruppoEsenzioni getGruppoEsenzioneByCodiceEsenzione(String codEsenzione) {
		String queryString = "select esenzioneDEsenzione from EsenzioneDEsenzione esenzioneDEsenzione "
				+ "left join fetch esenzioneDEsenzione.esenzioneDGruppoEsenzioni esenzioneDGruppoEsenzioni "
				+ "where esenzioneDEsenzione.codEsenzione = :codEsenzione";
		Query query = em.createQuery(queryString);
		query.setParameter("codEsenzione", codEsenzione);
		List<EsenzioneDEsenzione> result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result.get(0).getEsenzioneDGruppoEsenzioni();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDPrestazioneSpecialistica> getPrestazioniSpecialisticheByCodDiagnosi(String codDiagnosi) {
		String queryString = "select esenzioneDDiagnosi from EsenzioneDDiagnosi esenzioneDDiagnosi "
				+ "left join fetch esenzioneDDiagnosi.esenzioneRDiagnosiPrestaziones esenzioneRDiagnosiPrestaziones "
				+ "left join fetch esenzioneRDiagnosiPrestaziones.esenzioneDPrestazioneSpecialistica esenzioneDPrestazioneSpecialistica "
				+ "where esenzioneDDiagnosi.codDiagnosi = :codDiagnosi";
		Query query = em.createQuery(queryString);
		query.setParameter("codDiagnosi", codDiagnosi);
		List<EsenzioneDDiagnosi> d = query.getResultList();
		if (d != null && !d.isEmpty()) {
			List<EsenzioneDPrestazioneSpecialistica> result = new ArrayList<EsenzioneDPrestazioneSpecialistica>();
			for (EsenzioneRDiagnosiPrestazione r : d.get(0).getEsenzioneRDiagnosiPrestaziones()) {
				result.add(r.getEsenzioneDPrestazioneSpecialistica());
			}
			return result;
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EsenzioneTRepositoryDocumentale insertAllegatoRepositoryDocumentale(
			EsenzioneTRepositoryDocumentale allegato) {
		allegato.setFile(cifraFileDocumento(allegato.getFile()));
		EsenzioneTRepositoryDocumentale allegatoInserito = em.merge(allegato);
		// allegatoInserito.setFile(decifraFileDocumento(allegatoInserito.getFile()));
		return allegatoInserito;
	}

	/*
	 * Ricerca per PK di un documento nel repository documentale
	 */
	public EsenzioneTDocumento getEsenzioneTDocumentoBySkDocumento(String idDocumento) {
		Query queryDoc = em.createQuery("from EsenzioneTDocumento where skDocumento=:idDocumento");
		queryDoc.setParameter("idDocumento", (new Integer(idDocumento)));
		EsenzioneTDocumento doc = (EsenzioneTDocumento) queryDoc.getSingleResult();
		EsenzioneTDocumento documento = new EsenzioneTDocumento(doc);
		documento.setCodiceFiscaleCittadino(decifraString(documento.getCodiceFiscaleCittadino()));
		return documento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneTDocumento getDocumentoCertificato(String skDocumento) {
		String queryString = "from EsenzioneTDocumento esenzioneTDocumento ";
		queryString += "left join fetch esenzioneTDocumento.esenzioneDDocumentoStato esenzioneDDocumentoStato ";
		queryString += "left join fetch esenzioneTDocumento.esenzioneDDocumentoTipo esenzioneDDocumentoTipo ";
		queryString += "left join fetch esenzioneTDocumento.esenzioneTMetadatiDocumento esenzioneTMetadatiDocumento ";
		queryString += "where esenzioneTDocumento.skDocumento = :skDocumento ";
		queryString += "and esenzioneDDocumentoTipo.codDocumentoTipo = :codDocumentoTipo ";

		Query query = em.createQuery(queryString);
		query.setParameter("skDocumento", Integer.valueOf(skDocumento));
		query.setParameter("codDocumentoTipo", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);
		List<EsenzioneTDocumento> listaCertificati = query.getResultList();
		if (listaCertificati.size() > 0) {
			EsenzioneTDocumento documento = listaCertificati.get(0);
			EsenzioneTDocumento esenzioneTDocumento = new EsenzioneTDocumento(documento);
			esenzioneTDocumento
					.setCodiceFiscaleCittadino(decifraString(esenzioneTDocumento.getCodiceFiscaleCittadino()));
			return esenzioneTDocumento;
		}
		return null;
	}

	/*
	 * Ricerca praticaEsenzione by CodFiscale e CodEsenzione
	 */
	public EsenzioneTPraticaEsenzione getPraticaEsenzioneByCodFiscaleAndCodEsenzione(String codFiscale,
			String codEsenzione) {

		String queryString = "from EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
		queryString += " left join fetch esenzioneTPraticaEsenzione.esenzioneDDiagnosi esenzioneDDiagnosi ";

		queryString += " where esenzioneTPraticaEsenzione.codiceFiscaleBeneficiario=:codFiscale "
				+ "and esenzioneDDiagnosi.codDiagnosi =:codDiagnosi ";
		Query query = em.createQuery(queryString);
		query.setParameter("codFiscale", cifraString(codFiscale));
		query.setParameter("codDiagnosi", codEsenzione);
		EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = (EsenzioneTPraticaEsenzione) query.getSingleResult();
		EsenzioneTPraticaEsenzione esenzione = new EsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzione);
		esenzione.setCodiceFiscaleBeneficiario(decifraString(esenzione.getCodiceFiscaleBeneficiario()));
		esenzione.setCodiceFiscaleDelegato(decifraString(esenzione.getCodiceFiscaleDelegato()));
		return esenzione;
	}

	@Override
	public List<EsenzioneDPraticaStato> getStatoPratica(String skAzione) {
		String queryStr = "SELECT a FROM EsenzioneDPraticaStato a, EsenzioneDAzione b, EsenzioneRAzioniPraticaStato c where ( ";
		queryStr = queryStr + "a.skPraticaStato = c.skPraticaStato and ";
		queryStr = queryStr + "c.skAzione = b.skAzione and ";
		if (Checker.isValorizzato(skAzione))
			queryStr = queryStr + " b.skAzione = :skAzione and ";

		queryStr = queryStr + " 1 = 1 ) order by a.codStato asc ";

		TypedQuery<EsenzioneDPraticaStato> query = em.createQuery(queryStr, EsenzioneDPraticaStato.class);

		if (Checker.isValorizzato(skAzione))
			query.setParameter("skAzione", (new Integer(skAzione)));

		return query.getResultList();
	}

	/*
	 * Ricerca AziendaSanitaria by codAsl
	 */
	public EsenredDAziendasanitaria getAziendaSanitariaByCodAsl(String codAsl) {
		Query query = em.createQuery("from EsenredDAziendasanitaria where idAzienda=:codAsl");
		query.setParameter("codAsl", codAsl);
		EsenredDAziendasanitaria azienda = (EsenredDAziendasanitaria) query.getSingleResult();
		return azienda;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneTPraticaEsenzione> getListaPraticheFiltrate(FiltriRicercaPratiche filtri, String codAsl,
			int pagesize) {

		String queryString = "from EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDPraticaStato esenzioneDPraticaStato ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDDiagnosi esenzioneDDiagnosi ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenredDAziendasanitaria esenredDAziendasanitaria ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneTCittadino1 esenzioneTCittadino1 ";
		queryString += "left join fetch esenzioneTPraticaEsenzione.esenzioneDDistrettoSocioSanitario esenzioneDDistrettoSocioSanitario ";
		queryString += "left join fetch esenzioneDDiagnosi.esenzioneDEsenzione esenzioneDEsenzione ";

		queryString += "where (1=1 ";

		if (Checker.isValorizzato(codAsl)) {
			queryString += "and esenzioneTCittadino1.idAzienda = :idAzienda ";
		}
		if (filtri.getStatiPratica() != null && !filtri.getStatiPratica().isEmpty()) {
			queryString += "and esenzioneDPraticaStato.codStato IN ( ";

			Iterator<StatoDocumento> it = filtri.getStatiPratica().iterator();
			queryString += "\'" + it.next().getCodStato() + "\'";

			while (it.hasNext()) {
				queryString += ", \'" + it.next().getCodStato() + "\'";
			}
			queryString += " ) ";
		}
		if (Checker.isValorizzato(filtri.getCodEsenzione())) {
			queryString += "and esenzioneDEsenzione.codEsenzione = :codEsenzione ";
		}
		if (Checker.isValorizzato(filtri.getValidaDal())) {
			queryString += "and to_date(to_char(esenzioneTPraticaEsenzione.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY') ";
		}
		if (Checker.isValorizzato(filtri.getValidaAl())) {
			queryString += "and to_date(to_char(esenzioneTPraticaEsenzione.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') ";
		}
		if (Checker.isValorizzato(filtri.getIdAura())) {
			queryString += "and esenzioneTCittadino1.id_aura = :id_aura ";
		}
		queryString += ") order by esenzioneTPraticaEsenzione.datCreazione desc";

		Query query = em.createQuery(queryString);

		boolean check = false;
		for (StatoDocumento s : filtri.getStatiPratica()) {
			if (s.getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA)) {
				check = true;
				break;
			}
		}
		if (!check) {
			if (filtri.getPagina() == 1)
				query.setFirstResult(filtri.getPagina() - 1);
			else
				query.setFirstResult(filtri.getPagina() * pagesize - pagesize);

			query.setMaxResults(pagesize);
		}

		if (Checker.isValorizzato(codAsl)) {
			query.setParameter("idAzienda", codAsl);
		}
		if (Checker.isValorizzato(filtri.getCodEsenzione())) {
			query.setParameter("codEsenzione", filtri.getCodEsenzione());
		}
		if (Checker.isValorizzato(filtri.getValidaDal())) {
			query.setParameter("dataInizioValidita", filtri.getValidaDal());
		}
		if (Checker.isValorizzato(filtri.getValidaAl())) {
			query.setParameter("dataFineValidita", filtri.getValidaAl());
		}
		if (Checker.isValorizzato(filtri.getIdAura())) {
			query.setParameter("id_aura", filtri.getIdAura());
		}
		List<EsenzioneTPraticaEsenzione> result = query.getResultList();
		List<EsenzioneTPraticaEsenzione> listaPratiche = new ArrayList<EsenzioneTPraticaEsenzione>();
		for (EsenzioneTPraticaEsenzione ese : result) {
			EsenzioneTPraticaEsenzione esenzione = decifraEsenzione(ese);
			listaPratiche.add(esenzione);
		}
		return listaPratiche;
	}

	@Override
	public Long contaPraticheFiltrate(FiltriRicercaPratiche filtri, String codAsl) {
		String queryString = "select count(a) from EsenzioneTPraticaEsenzione a, ";
		queryString += "EsenzioneDPraticaStato b, ";
		queryString += "EsenzioneDDiagnosi c, ";
		queryString += "EsenzioneTCittadino e, ";
		queryString += "EsenzioneDEsenzione f ";
		queryString += "where a.skTipologiaStatoPratica = b.skPraticaStato and " + "a.skDiagnosi = c.skDiagnosi and "
				 + "a.codiceFiscaleBeneficiario = e.codiceFiscale and "
				+ "c.skEsenzione = f.skEsenzione and (1=1 ";
		if (Checker.isValorizzato(codAsl)) {
			queryString += "and e.idAzienda = :idAzienda ";
		}
		if (filtri.getStatiPratica() != null && !filtri.getStatiPratica().isEmpty()) {
			queryString += "and b.codStato IN ( ";

			Iterator<StatoDocumento> it = filtri.getStatiPratica().iterator();
			queryString += "\'" + it.next().getCodStato() + "\'";

			while (it.hasNext()) {
				queryString += ", \'" + it.next().getCodStato() + "\'";
			}
			queryString += " ) ";
		}
		if (Checker.isValorizzato(filtri.getCodEsenzione())) {
			queryString += "and f.codEsenzione = :codEsenzione ";
		}
		if (Checker.isValorizzato(filtri.getValidaDal())) {
			queryString += "and to_date(to_char(a.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(:dataInizioValidita,'DD/MM/YYYY')";
		}
		if (Checker.isValorizzato(filtri.getValidaAl())) {
			queryString += "and to_date(to_char(a.datCreazione,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(:dataFineValidita,'DD/MM/YYYY') ";
		}
		if (Checker.isValorizzato(filtri.getIdAura())) {
			queryString += "and e.id_aura = :id_aura ";
		}
		queryString += ")";

		Query query = em.createQuery(queryString);

		if (Checker.isValorizzato(codAsl)) {
			query.setParameter("idAzienda", codAsl);
		}
		if (Checker.isValorizzato(filtri.getCodEsenzione())) {
			query.setParameter("codEsenzione", filtri.getCodEsenzione());
		}
		if (Checker.isValorizzato(filtri.getValidaDal())) {
			query.setParameter("dataInizioValidita", filtri.getValidaDal());
		}
		if (Checker.isValorizzato(filtri.getValidaAl())) {
			query.setParameter("dataFineValidita", filtri.getValidaAl());
		}
		if (Checker.isValorizzato(filtri.getIdAura())) {
			query.setParameter("id_aura", filtri.getIdAura());
		}

		Long resultList = (Long) query.getSingleResult();
		return resultList;
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
	public EsenzioneTDocumento getMaxDocumentoPerPratica(String codStato,String SkPratica) {
		String queryString = "SELECT a FROM EsenzioneTDocumento a,";
		queryString += " EsenzioneRPraticaEsenzioneDocumento b";
		queryString += " where b.esenzioneTPraticaEsenzione.skPraticaEsenzione = :SkPratica"; 
		queryString += " and a.skDocumento = b.esenzioneTDocumento.skDocumento";
		queryString += " and a.skTipoDocumento=:codStato";
		queryString += " order by a.skDocumento desc";
		Query query = em.createQuery(queryString);
		query.setMaxResults(1);
		query.setParameter("SkPratica", new Integer(SkPratica));
		query.setParameter("codStato", new Long(codStato));
		List<EsenzioneTDocumento> result = (List<EsenzioneTDocumento>) query.getResultList();
		if (result != null && !result.isEmpty()) {
			EsenzioneTDocumento doc = result.get(0);
			EsenzioneTDocumento documento = new EsenzioneTDocumento(doc);
			documento.setCodiceFiscaleCittadino(decifraString(documento.getCodiceFiscaleCittadino()));
			return documento;
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EsenzioneTDocumento insertEsenzioneTDocumento(EsenzioneTDocumento documento) throws Exception {
		documento = cifraDocumento(documento);
		return decifraDocumento(em.merge(documento));
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneTDocumento getAttestatoEsenzioneByCodiceFiscale(String cit_id) {
		String queryString = "from EsenzioneTDocumento a " + "left join fetch a.esenzioneDDocumentoStato b "
				+ "left join fetch a.esenzioneDDocumentoTipo c "
				+ "left join fetch a.esenzioneTRepositoryDocumentale d "
				+ "left join fetch a.esenzioneTMetadatiDocumento e " + "where a.codiceFiscaleCittadino = :cit_id and "
				+ "b.codStato = :stato and " + "c.codDocumentoTipo = :tipo " + "order by a.skDocumento desc";

		Query query = em.createQuery(queryString);
		query.setMaxResults(1);
		query.setParameter("cit_id", cifraString(cit_id));
		query.setParameter("stato", Constants.STATO_DOCUMENTO_VALIDO);
		query.setParameter("tipo", Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE);
		List<EsenzioneTDocumento> result = (List<EsenzioneTDocumento>) query.getResultList();
		if (result != null && !result.isEmpty()) {
			EsenzioneTDocumento doc = result.get(0);
			EsenzioneTDocumento documento = new EsenzioneTDocumento(doc);
			documento.setCodiceFiscaleCittadino(decifraString(documento.getCodiceFiscaleCittadino()));
			if (documento.getEsenzioneTRepositoryDocumentale() != null)
				documento.getEsenzioneTRepositoryDocumentale()
						.setFile(decifraFileDocumento(documento.getEsenzioneTRepositoryDocumentale().getFile()));

			return documento;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneTDocumento getDocumentoAttestatoEsenzioneByCodiceFiscale(String cit_id) {
		String queryString = "from EsenzioneTDocumento a " + "left join fetch a.esenzioneDDocumentoStato b "
				+ "left join fetch a.esenzioneDDocumentoTipo c " + "where a.codiceFiscaleCittadino = :cit_id and "
				+ "b.codStato = :stato and " + "c.codDocumentoTipo = :tipo " + "order by a.skDocumento desc";

		Query query = em.createQuery(queryString);
		query.setMaxResults(1);
		query.setParameter("cit_id", cifraString(cit_id));
		query.setParameter("stato", Constants.STATO_DOCUMENTO_VALIDO);
		query.setParameter("tipo", Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE);
		List<EsenzioneTDocumento> result = (List<EsenzioneTDocumento>) query.getResultList();
		if (result != null && !result.isEmpty()) {
			EsenzioneTDocumento doc = result.get(0);
			EsenzioneTDocumento documento = new EsenzioneTDocumento(doc);
			documento.setCodiceFiscaleCittadino(decifraString(documento.getCodiceFiscaleCittadino()));

			return documento;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneDDocumentoTipo> getListaDocumentoTipoPerCaricaAllegati(String gruppo) {
		String queryString = "from EsenzioneDDocumentoTipo a"
				+ " left join fetch a.esenzioneDDocumentoGruppoEsenzionis b"
				+ " left join fetch b.esenzioneDGruppoEsenzioni c"
				+ " where c.codTipologiaGruppo = :gruppo and a.codDocumentoTipo not in (:attestato , :certificato)";

		Query query = em.createQuery(queryString);
		query.setParameter("gruppo", gruppo);
		query.setParameter("attestato", Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE);
		query.setParameter("certificato", Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA);

		List<EsenzioneDDocumentoTipo> elencoDocumentoTipo = query.getResultList();
		return elencoDocumentoTipo;
	}

	private String cifraString(String stringToEncrypt) {
		String encrypted = null;
		if (stringToEncrypt != null) {
			if (stringToEncrypt.startsWith("\\"))
				return stringToEncrypt;
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
			if (!stringToDecrypt.startsWith("\\"))
				return stringToDecrypt;
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
			try {
				esen.setEsenzioneTCittadino1(decifraCittadino(esen.getEsenzioneTCittadino1()));
				esen.setEsenzioneTCittadino2(decifraCittadino(esen.getEsenzioneTCittadino2()));

				if (esen.getEsenzioneRPraticaEsenzioneDocumentos() != null
						&& esen.getEsenzioneRPraticaEsenzioneDocumentos().size() > 0) {
					for (EsenzioneRPraticaEsenzioneDocumento erped : esen.getEsenzioneRPraticaEsenzioneDocumentos()) {
						erped.setEsenzioneTDocumento(decifraDocumento(erped.getEsenzioneTDocumento()));
					}
				}
			} catch (org.hibernate.LazyInitializationException e) {

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

			try {
				if (esenzione.getEsenzioneRPraticaEsenzioneDocumentos() != null
						&& esenzione.getEsenzioneRPraticaEsenzioneDocumentos().size() > 0) {
					for (EsenzioneRPraticaEsenzioneDocumento erped : esenzione
							.getEsenzioneRPraticaEsenzioneDocumentos()) {
						erped.setEsenzioneTDocumento(cifraDocumento(erped.getEsenzioneTDocumento()));
					}
				}
			} catch (org.hibernate.LazyInitializationException e) {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<EsenzioneSPraticaEsenzione> getEsenzioneSPraticaEsenzionebyskPratica(Integer skPraticaEsenzione) {
		String queryString = "from EsenzioneSPraticaEsenzione where skPraticaEsenzione = :skPratica";
		Query query = em.createQuery(queryString);
		query.setParameter("skPratica", new Long(skPraticaEsenzione));
		List<EsenzioneSPraticaEsenzione> resultList = new ArrayList<EsenzioneSPraticaEsenzione>();
		try {
			resultList = query.getResultList();
			if (resultList != null && resultList.size() > 0) {
				for (EsenzioneSPraticaEsenzione espe : resultList) {
					espe = decifraStorico(espe);
				}
			}
		} catch (NoResultException e) {
			return null;
		}
		return resultList;
	}

	@Override
	public List<EsenzioneDCa> getAllEsenzioneDCa() {
		String queryString = "Select distinct d from EsenzioneDCa d "
				+ "left join fetch d.esenzioneRCaTypeOtps r "
				+ "left join fetch r.esenzioneDTypeOtp";
		
		TypedQuery<EsenzioneDCa> query = em.createQuery(queryString, EsenzioneDCa.class);
		return query.getResultList();
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public EsenzioneDErroriEsenpat getErroreGatewayFirma(String description, String errorCode, String message) {
		String queryString = "from EsenzioneDErroriEsenpat edee ";
		queryString += "left join fetch edee.esenzioneRErroriGatewayFirmaErroriEsenpats ere ";
		queryString += "left join fetch ere.esenzioneDErroriGatewayFirma egf ";
		queryString += "where egf.description = :description ";
		queryString += "and egf.errorCode = :errorCode ";
		queryString += "and egf.message = :message ";

		Query query = em.createQuery(queryString);
		query.setParameter("description", description);
		query.setParameter("errorCode", errorCode);
		query.setParameter("message", message);
		
		List<EsenzioneDErroriEsenpat> listaErroriGateway = query.getResultList();
		if (listaErroriGateway.size() > 0) {
			EsenzioneDErroriEsenpat errore = listaErroriGateway.get(0);
			return errore;
		}
		return null;
	}
	
}