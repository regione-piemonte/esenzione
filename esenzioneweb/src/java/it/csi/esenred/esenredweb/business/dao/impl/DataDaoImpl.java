/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.esenred.esenpatweb.dto.DataInizioFine;
import it.csi.esenred.esenpatweb.dto.Filtri;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniCittadino;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniEsenred;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniOperatore;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.find.AnagrafeFindStub;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMatriceEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredCTitoloDichiarante;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTAslOperatore;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredWNotifiche;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.model.interfaces.TitoloDichiaranteIf;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.ConnectionInfo;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
//import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

public class DataDaoImpl implements DataDaoIf {

	@PersistenceContext
	private EntityManager em;
	private String AppID;
	static EsenzioneIf esenzioneIf;
	ParametroIf parametroIf;
	static TitoloDichiaranteIf titoloDichiaranteIf;
	private final static Logger logger = Logger.getLogger(AnagrafeFindStub.class.getName());
	
	public List<EsenredCComuni> getElencoComuni() {
		TypedQuery<EsenredCComuni> query = em.createNamedQuery("EsenredCComuni.findAll", EsenredCComuni.class);
		return query.getResultList();
	}

	public EsenredCMessaggi getMessaggio(String codice) {
		TypedQuery<EsenredCMessaggi> query = em.createNamedQuery("EsenredCMessaggi.findMessaggio",
				EsenredCMessaggi.class);
		query.setParameter("codice", codice);
		return query.getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertNotifica(EsenredWNotifiche notifica) {
		em.persist(notifica);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateNotifica(EsenredWNotifiche notifica) {
		em.merge(notifica);
	}

	public EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio) {
		TypedQuery<EsenredTEsenzioniReddito> query = em.createNamedQuery("EsenredTEsenzioniReddito.findEsenzione",
				EsenredTEsenzioniReddito.class);
		query.setParameter("idAura", idAura);
		query.setParameter("codEsenzione", codEsenzione);
		query.setParameter("dataInizio", dataInizio);
		return Util.getSingleResult(query);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateEsenzioneCittadino(EsenredTEsenzioniReddito esenzione) {
		esenzione.setDataModify(new Date());
		em.merge(esenzione);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateEsenzioneCittadinoBlocco(EsenredTEsenzioniReddito esenzione) {
		esenzione.setDataModify(new Date());
		esenzione.setCodStato("B");
		em.merge(esenzione);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateEsenzioneCittadinoSblocco(EsenredTEsenzioniReddito esenzione) {
		esenzione.setCodStato("R");
		esenzione.setDataModify(new Date());
		esenzione.setIdUserModify(-8888L);
		em.merge(esenzione);
	}

	public EsenredCParametri getParametro(String codice) {
		TypedQuery<EsenredCParametri> query = em.createNamedQuery("EsenredCParametri.findParametro",
				EsenredCParametri.class);
		query.setParameter("codice", codice);
		return query.getSingleResult();
	}
	
	public EsenredTAslOperatore getAslOperatoreCF(String CodiceFiscaleOperatore) {
		TypedQuery<EsenredTAslOperatore> query = em.createNamedQuery("EsenredTAslOperatore.findPerCF",
				EsenredTAslOperatore.class);
		query.setParameter("codFiscaleOperatore", CodiceFiscaleOperatore);
		return query.getSingleResult();
	}

	public List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioniEsenred filtri, String codASLOperatore) {
		String queryStr ="";
		
		if (filtri.getStorico().equalsIgnoreCase("No")) {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.dataFine >= current_date and ";
		} 
		else
		{
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.dataFine < current_date and ";
		}
		
		final String wildCard = ",";
		if ("false".equalsIgnoreCase(((FiltriRicercaEsenzioniOperatore) filtri).getInCaricoASL())) {
			queryStr = queryStr + " e.idUserInsert != '99999997' and ";
		}
		//if (filtri.getStorico().equalsIgnoreCase("No")) {
		if (Checker.isValorizzato(codASLOperatore)) {
			queryStr = queryStr + " e.codNazionaleAslRilascio = :codASLOperatore and ";
		}
		//}
//			if (!Checker.isValorizzato(filtri.getIdAuraOperatore())) {
//				queryStr = queryStr + " e.idOperatoreRichiesta is null and ";
//			}

		if (Checker.isValorizzato(filtri.getIdAuraDichiarante()))
			queryStr = queryStr + " e.idCittadinoDichiarante = :idCittadinoDichiarante and ";
		else if (Checker.isValorizzato(filtri.getCodFiscaleDichiarante()))
			queryStr = queryStr + " e.cfDichiarantefr = :cfDichiarantefr and ";

		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario()))
			queryStr = queryStr + " e.idCittadinoBeneficiario = :idCittadinoBeneficiario and ";
		
		if (Checker.isValorizzato(filtri.getIdAuraTitolare()))
			queryStr = queryStr + " e.idCittadinoTitolare = :idCittadinoTitolare and ";
		else if (Checker.isValorizzato(filtri.getCodFiscaleTitolare()))
			queryStr = queryStr + " e.cfTitolarefr = :cfTitolarefr and ";
		
		if (Checker.isValorizzato(filtri.getCodEsenzione()))
			queryStr = queryStr + " e.codEsenzione = :codEsenzione and ";
		if (Checker.isValorizzato(filtri.getCodStatoEsenzione())) {
			if (filtri.getCodStatoEsenzione().contains(wildCard)) {
				String[] codStati = filtri.getCodStatoEsenzione().split(wildCard);
				queryStr = queryStr + " ( ";
				for (int i = 0; i < codStati.length; i++) {
					queryStr = queryStr + " e.codStato = :codStatoEsenzione" + i;// +" or ";
					if (i != codStati.length - 1)
						queryStr = queryStr + " or ";
				}
				queryStr = queryStr + " ) and ";
			} else {
				queryStr = queryStr + " e.codStato = :codStatoEsenzione and ";
			}
		}
		
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			queryStr = queryStr + " e.dataInizio >= :dataInizioValidita and ";
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			queryStr = queryStr + " e.dataFine <= :dataFineValidita and ";

		if (filtri instanceof FiltriRicercaEsenzioniOperatore) {
			if (Checker.isValorizzato(filtri.getIdAuraOperatore())
					&& "true".equalsIgnoreCase(((FiltriRicercaEsenzioniOperatore) filtri).getInCaricoASL())) {
				if (!filtri.getIdAuraOperatore().equals("99999999")) {
				Long IdAuraOperatore = new Long(filtri.getIdAuraOperatore());
				queryStr = queryStr + "  (e.idOperatoreRichiesta = :idOperatoreRichiesta or ";
				queryStr = queryStr + "  (e.idOperatoreRichiesta is null and e.idUserInsert = '99999997')) and ";
				}
			}
		}

		queryStr = queryStr + " 1 = 1 ) ";

		if (filtri instanceof FiltriRicercaEsenzioniOperatore) {
			if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy() != null) {
				String order = "desc";
				if (((FiltriRicercaEsenzioniOperatore) filtri).isAsc())
					order = "asc";
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("protocollo"))
					queryStr = queryStr + "order by e.numProtocolloSogei " + order;
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("stato"))
					queryStr = queryStr + "order by e.codStato " + order;
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("codesenzione"))
					queryStr = queryStr + "order by e.codEsenzione " + order;
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("datarichiesta"))
					queryStr = queryStr + "order by e.dataRichiesta " + order;
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("datarevoca"))
					queryStr = queryStr + "order by e.dataRevoca " + order;
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("finevalidita"))
					queryStr = queryStr + "order by e.dataFine " + order;
				if (((FiltriRicercaEsenzioniOperatore) filtri).getOrderBy().toLowerCase().equals("iniziovalidita"))
					queryStr = queryStr + "order by e.dataInizio " + order;
			}
		} else
			queryStr = queryStr + "order by e.dataInizio desc ";

		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);
		//if (filtri.getStorico().equalsIgnoreCase("No")) {
		if (Checker.isValorizzato(codASLOperatore))
			query.setParameter("codASLOperatore", codASLOperatore);
		//}
		if (Checker.isValorizzato(filtri.getIdAuraDichiarante())) {
			Long idDichiarante = new Long(filtri.getIdAuraDichiarante());
			query.setParameter("idCittadinoDichiarante", new Long(idDichiarante));
		}else if (Checker.isValorizzato(filtri.getCodFiscaleDichiarante()))
			query.setParameter("cfDichiarantefr", filtri.getCodFiscaleDichiarante());
		
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario())) {
			Long idBeneficiario = new Long(filtri.getIdAuraBeneficiario());
			query.setParameter("idCittadinoBeneficiario", idBeneficiario);
		}
		
		if (Checker.isValorizzato(filtri.getIdAuraTitolare())) {
			Long idTitolare = new Long(filtri.getIdAuraTitolare());
			query.setParameter("idCittadinoTitolare", idTitolare);
		}else if (Checker.isValorizzato(filtri.getCodFiscaleTitolare()))
			query.setParameter("cfTitolarefr", filtri.getCodFiscaleTitolare());

		if (Checker.isValorizzato(filtri.getCodEsenzione()))
			query.setParameter("codEsenzione", filtri.getCodEsenzione());
		if (Checker.isValorizzato(filtri.getCodStatoEsenzione())) {
			if (filtri.getCodStatoEsenzione().contains(wildCard)) {
				String[] codStati = filtri.getCodStatoEsenzione().split(wildCard);
				queryStr = queryStr + " ( ";
				for (int i = 0; i < codStati.length; i++) {
					query.setParameter("codStatoEsenzione" + i, codStati[i]);
				}
				queryStr = queryStr + " ) and ";
			} else {
				query.setParameter("codStatoEsenzione", filtri.getCodStatoEsenzione());
			}
		}
		if (Checker.isValorizzato(filtri.getDataInizioValidita()))
			query.setParameter("dataInizioValidita", Converter.getData(filtri.getDataInizioValidita()));
		if (Checker.isValorizzato(filtri.getDataFineValidita()))
			query.setParameter("dataFineValidita", Converter.getData(filtri.getDataFineValidita()));
		
		if (filtri instanceof FiltriRicercaEsenzioniOperatore) {
			if (Checker.isValorizzato(((FiltriRicercaEsenzioniOperatore) filtri).getIdOperatoreRichiesta())) {
				Long idOperatoreRichiesta = new Long(
						((FiltriRicercaEsenzioniOperatore) filtri).getIdOperatoreRichiesta());
				query.setParameter("idOperatoreRichiesta", idOperatoreRichiesta);
			}
		}


		if (Checker.isValorizzato(filtri.getIdAuraOperatore())
				&& "true".equalsIgnoreCase(((FiltriRicercaEsenzioniOperatore) filtri).getInCaricoASL())) {
			if (!filtri.getIdAuraOperatore().equals("99999999")) {
			Long IdAuraOperatore = new Long(filtri.getIdAuraOperatore());
			query.setParameter("idOperatoreRichiesta", new Long(IdAuraOperatore));
			}
		}

		return query.getResultList();
	}
	
	private String buildQueryFiltro(String attr, Filtri filtro) {
		String queryStr = "";

		if(filtro.getIn()!=null) {
			queryStr = queryStr + " AND "+ attr + " IN ( '"+filtro.getIn()[0]+"' ";
			for(int i = 1; i<filtro.getIn().length;i++) {
				queryStr = queryStr + ",'"+filtro.getIn()[i]+"' ";
			}
			queryStr = queryStr + ")";
			System.out.println(queryStr);
		}		
		if(filtro.getNin()!=null) {
			queryStr = queryStr + " AND "+ attr + " NOT IN ( '"+filtro.getNin()[0]+"' ";
			for(int i = 1; i<filtro.getNin().length;i++) {
				queryStr = queryStr + ",'"+filtro.getNin()[i]+"' ";
			}
			queryStr = queryStr + ")";
			System.out.println(queryStr);
		}
		if(filtro.getEq()!=null) {
			queryStr = queryStr + " AND "+ attr+" = '"+filtro.getEq()+"'";
			System.out.println(queryStr);
		}
		if(filtro.getNe()!=null) {
			queryStr = queryStr + " AND "+ attr+" != '"+filtro.getNe()+"'";
			System.out.println(queryStr);
		}
		if(filtro.getLt()!=null) {
			queryStr = queryStr + " AND "+ attr+" < '"+filtro.getLt()+"'";
			System.out.println(queryStr);
		}
		if(filtro.getLte()!=null) {
			queryStr = queryStr + " AND "+ attr+" <= '"+filtro.getLte()+"'";
			System.out.println(queryStr);
		}
		if(filtro.getGt()!=null) {
			queryStr = queryStr + " AND "+ attr+" > '"+filtro.getGt()+"'";
			System.out.println(queryStr);
		}
		if(filtro.getGte()!=null) {
			queryStr = queryStr + " AND "+ attr+" >= '"+filtro.getGte()+"'";
			System.out.println(queryStr);
		}
		// tutti gli altri filtri		
		return queryStr;
	}
	
	private String buildQueryFiltroDate(String attr, Filtri filtro) {
		String queryStr = "";

		if(filtro.getIn()!=null) {
			queryStr = queryStr + " AND "+ attr + " IN ( '"+Converter.getDataAcc(filtro.getIn()[0])+"' ";
			for(int i = 1; i<filtro.getIn().length;i++) {
				queryStr = queryStr + ",'"+Converter.getDataAcc(filtro.getIn()[i])+"' ";
			}
			queryStr = queryStr + ")";
			System.out.println(queryStr);
		}		
		if(filtro.getNin()!=null) {
			queryStr = queryStr + " AND "+ attr + " NOT IN ( '"+Converter.getDataAcc(filtro.getNin()[0])+"' ";
			for(int i = 1; i<filtro.getNin().length;i++) {
				queryStr = queryStr + ",'"+Converter.getDataAcc(filtro.getNin()[i])+"' ";
			}
			queryStr = queryStr + ")";
			System.out.println(queryStr);
		}
		if(filtro.getEq()!=null) {
			queryStr = queryStr + " AND "+ attr+" = '"+Converter.getDataAcc(filtro.getEq())+"' ";
			System.out.println(queryStr);
		}
		if(filtro.getNe()!=null) {
			queryStr = queryStr + " AND "+ attr+" != '"+Converter.getDataAcc(filtro.getNe())+"' ";
			System.out.println(queryStr);
		}
		if(filtro.getLt()!=null) {
			queryStr = queryStr + " AND "+ attr+" < '"+Converter.getDataAcc(filtro.getLt())+"' ";
			System.out.println(queryStr);
		}
		if(filtro.getLte()!=null) {
			queryStr = queryStr + " AND "+ attr+" <= '"+Converter.getDataAcc(filtro.getLte())+"' ";
			System.out.println(queryStr);
		}
		if(filtro.getGt()!=null) {
			queryStr = queryStr + " AND "+ attr+" > '"+Converter.getDataAcc(filtro.getGt())+"' ";
			System.out.println(queryStr);
		}
		if(filtro.getGte()!=null) {
			queryStr = queryStr + " AND "+ attr+" >= '"+Converter.getDataAcc(filtro.getGte())+"' ";
			System.out.println(queryStr);
		}
		// tutti gli altri filtri		
		return queryStr;
	}
		
	public List<EsenredTEsenzioniReddito> getEsenzioniAsl(String codASLOperatore) {
		String queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where ( ";

		if (Checker.isValorizzato(codASLOperatore))
			queryStr = queryStr + " e.codNazionaleAslRilascio = :codASLOperatore and ";
		queryStr = queryStr + " 1 = 1 ) order by e.dataInizio desc ";

		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);

		if (Checker.isValorizzato(codASLOperatore))
			query.setParameter("codASLOperatore", codASLOperatore);

		return query.getResultList();
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
			queryStr = queryStr + " p.codice like :codParametro and ";

		queryStr = queryStr + " 1 = 1 ) order by p.valore asc ";

		TypedQuery<EsenredCParametri> query = em.createQuery(queryStr, EsenredCParametri.class);

		if (Checker.isValorizzato(codParametro))
			query.setParameter("codParametro", codParametro + "%");

		return query.getResultList();
	}

	public List<EsenredCTitoloDichiarante> getTitoli(String codTitolo) {
		String queryStr = "SELECT t FROM EsenredCTitoloDichiarante t where ( ";

		if (Checker.isValorizzato(codTitolo))
			queryStr = queryStr + " t.codTitolo like :codTitolo and ";

		queryStr = queryStr + " 1 = 1 ) order by t.descrizione asc ";

		TypedQuery<EsenredCTitoloDichiarante> query = em.createQuery(queryStr, EsenredCTitoloDichiarante.class);

		if (Checker.isValorizzato(codTitolo))
			query.setParameter("codTitolo", codTitolo + "%");

		return query.getResultList();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertEsenzioneCittadino(EsenredTEsenzioniReddito esenzione) {
		esenzione.setDataInsert(new Date());
		esenzione.setDataModify(new Date());
		em.persist(esenzione);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteEsenzioneCittadino(EsenredTEsenzioniReddito esenzione) {
		esenzione.setDataInsert(new Date());
		em.merge(esenzione);
	}

	@Override
	public List<EsenredCMatriceEsenzioni> getMatriceEsenzioni(String codEsistente, String codRichiesta) {
		String queryStr = "EsenredCMatriceEsenzioni.getRiga";
		List<EsenredCMatriceEsenzioni> resultList = this.em.createNamedQuery(queryStr, EsenredCMatriceEsenzioni.class)
				.setParameter("codRichiesta", codRichiesta).setParameter("codEsistente", codEsistente).getResultList();

		return resultList;
	}

	public List<EsenredTEsenzioniReddito> getEsenzioniValideByIdAura(long idAura) {

		List<EsenredTEsenzioniReddito> resultList = this.em
				.createNamedQuery("EsenredTEsenzioniReddito.findEsenzioneValidaLavorazione",
						EsenredTEsenzioniReddito.class)
				.setParameter("idAura", idAura).getResultList();

		return resultList.isEmpty() ? new ArrayList<EsenredTEsenzioniReddito>() : resultList;
	}

	public List<EsenredWNotifiche> getNotificheOperatore(Long idOperatore) {
		List<EsenredWNotifiche> resultList = this.em
				.createNamedQuery("EsenredWNotifiche.findNotificheOperatore", EsenredWNotifiche.class)
				.setParameter("idOperatore", idOperatore).getResultList();

		return resultList.isEmpty() ? new ArrayList<EsenredWNotifiche>() : resultList;
	}

	public List<EsenredWNotifiche> getNotificheCittadino(Long idAura) {
		List<EsenredWNotifiche> resultList = this.em
				.createNamedQuery("EsenredWNotifiche.findNotificheCittadino", EsenredWNotifiche.class)
				.setParameter("idAura", idAura).getResultList();

		return resultList.isEmpty() ? new ArrayList<EsenredWNotifiche>() : resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean deleteNotifica(Long idNotifica) {
		EsenredWNotifiche notifica = em.find(EsenredWNotifiche.class, idNotifica);

		if (notifica != null) {
			em.remove(notifica);
			return true;
		}
		return false;
	}

	@Override
	public List<EsenredTEsenzioniReddito> getEsenzioneById(long idEsenzione) {

		EsenredTEsenzioniReddito esenzione = em.find(EsenredTEsenzioniReddito.class, idEsenzione);

		if (esenzione != null) {
			return Arrays.asList(esenzione);
		} else {
			return new ArrayList<EsenredTEsenzioniReddito>();
		}
	}
	
	@Transactional(readOnly = true)
	public EsenredTEsenzioniReddito getEsenzioneByProtocollo(long protocollo, long idAura, String codEsenzione) {

		TypedQuery<EsenredTEsenzioniReddito> query = em
				.createNamedQuery("EsenredTEsenzioniReddito.findEsenzioneProtocollo",
						EsenredTEsenzioniReddito.class);
		query.setParameter("idAura", idAura);
		query.setParameter("codEsenzione", codEsenzione);
		query.setParameter("protocollo", protocollo);

		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		
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

	@Transactional(readOnly = true)
	public byte[] getReport(Map<String, Object> parameters) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("/report/certificato_esenzione.jrxml");

		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(input);
			org.hibernate.internal.SessionImpl session = (org.hibernate.internal.SessionImpl) em.getDelegate();
			ConnectionInfo connectionInfo = new ConnectionInfo();
			session.doWork(connectionInfo);

			JasperPrint jasperPrint = null;
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connectionInfo.getConn());

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));


			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

			exportConfig.setMetadataAuthor("SISTEMA PIEMONTE");
			// exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");

			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}
	
	@Transactional(readOnly = true)
	public byte[] getReportStoricoNodb(Map<String, Object> parameters) {
	
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		InputStream input = classloader.getResourceAsStream("/report/certificato_esenzione_storico.jrxml");
	
		JasperReport jasperReport = null;
		//JRSwapFileVirtualizer virtualizer = null;
		try {

			jasperReport = JasperCompileManager.compileReport(input);
;
		
			
			JasperPrint jasperPrint = null;
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
	
			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			try {
				outputStream.close();
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

			exportConfig.setMetadataAuthor("SISTEMA PIEMONTE");
			// exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");
	
			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();
	
		} catch (JRException e) {
			e.printStackTrace();
		}


		return outputStream.toByteArray();
	}
	
	@Transactional(readOnly = true)
	public byte[] getReportStorico(Map<String, Object> parameters) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		InputStream input = classloader.getResourceAsStream("/report/certesenstorico.jrxml");
		
		JasperReport jasperReport = null;

		try {
			jasperReport = JasperCompileManager.compileReport(input);
			org.hibernate.internal.SessionImpl session = (org.hibernate.internal.SessionImpl) em.getDelegate();
			ConnectionInfo connectionInfo = new ConnectionInfo();
			session.doWork(connectionInfo);
		

			
			JasperPrint jasperPrint = null;
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connectionInfo.getConn());
			
			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			try {
				outputStream.close();
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

			exportConfig.setMetadataAuthor("SISTEMA PIEMONTE");
			// exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");
	
			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();
		
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		return outputStream.toByteArray();
	}

	public List<EsenredCTitoloDichiarante> getTitoliFamiliare() {
		String queryStr = "SELECT t FROM EsenredCTitoloDichiarante t where (t.codTitolo = '1' or t.codTitolo = '2' or t.codTitolo = '4') order by t.descrizione asc ";
		TypedQuery<EsenredCTitoloDichiarante> query = em.createQuery(queryStr, EsenredCTitoloDichiarante.class);
		return query.getResultList();
	}

	public List<EsenredTEsenzioniReddito> getEsenzioneBozzaByIdAuraCodEsenzione(long idAura, String codEsenzione) {

		List<EsenredTEsenzioniReddito> resultList = this.em
				.createNamedQuery("EsenredTEsenzioniReddito.findEsenzioneLavorazione", EsenredTEsenzioniReddito.class)
				.setParameter("idAura", idAura).setParameter("codEsenzione", codEsenzione).getResultList();

		return resultList.isEmpty() ? new ArrayList<EsenredTEsenzioniReddito>() : resultList;
	}

	@Transactional(readOnly = true)
	public boolean updateSetConsultata(Long idNotifica) {
		EsenredWNotifiche notifica = em.find(EsenredWNotifiche.class, idNotifica);

		if (notifica != null) {
			notifica.setFlagConsultazione(new Integer(1));
			em.merge(notifica);
			return true;
		}
		return false;
	}

	public List<EsenredTEsenzioniReddito> getEsenzioniCittadino(FiltriRicercaEsenzioniCittadino filtri) {
		try {
			final String wildCard = ",";
			String listaprotocolli = "";
			List<EsenredTEsenzioniReddito> elencoEsenzioni = null;
			List<EsenredTEsenzioniReddito> elencoEsenzioniFamiliari = null;
			EsenredTEsenzioniReddito EsenzioneStorico = null;
			List<EsenredTEsenzioniReddito> elencoEsenzioniStoricoEssere = null;
			// if (filtri.getCodStatoEsenzione().contains(wildCard) &&
			// filtri.getIdAuraBeneficiario()==null) { //la prima volta
			if (filtri.getIdAuraBeneficiario() == null) { // la prima volta
				elencoEsenzioni = getEsenzioniUtente(filtri);
				elencoEsenzioniFamiliari = getEsenzioniFamiliari(filtri);
				elencoEsenzioni.addAll(elencoEsenzioniFamiliari);
				//preleva i protocolli per quelle valide e le mette in una striga per il not in e
				//contemporaneamente
				for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
					//if (esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("V") && esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
					if ((esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("V") || esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("S") || esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("B")) && esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
						listaprotocolli = listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString() + ",";
						//chiamo il metodo della ricerca storico
						try {
							
							//EsenzioneStorico = getSingleEsenzioniOracle(esenredTEsenzioniReddito.getNumProtocolloSogei().toString(),filtri.getStorico());
							EsenzioneStorico = IntegrationClientImpl.getInstance().getEsenzioni(esenredTEsenzioniReddito.getNumProtocolloSogei().toString(), 
									null, null, esenredTEsenzioniReddito.getIdCittadinoBeneficiario().toString(), "B",null).get(0);
							
							if (EsenzioneStorico != null) {
							esenredTEsenzioniReddito.setDataRevoca(EsenzioneStorico.getDataRevoca());
							esenredTEsenzioniReddito.setCodStato("R");
							esenredTEsenzioniReddito.setIdUserInsert(new Long ("99999998"));
							if (esenredTEsenzioniReddito.getNota()!=null) {
							esenredTEsenzioniReddito.setNota(esenredTEsenzioniReddito.getNota() + "(nota storico : " + EsenzioneStorico.getNota() + ")");
							}
							else
							{
								esenredTEsenzioniReddito.setNota(" (nota storico : " + EsenzioneStorico.getNota() + ")");
							}
						    }
						} catch (ClassNotFoundException e) {
							
							
							e.printStackTrace();
						} catch (SQLException e) {
							
							
							e.printStackTrace();
						}
					}	
					else if (esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
						listaprotocolli = listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString() + ",";
					}
				}
	
				if (listaprotocolli != "") {
					//prendi tutte le altre dello storico escludendo quelle di esenred
					listaprotocolli = listaprotocolli.substring(0, listaprotocolli.length() - 1);
				}
					try {
						
						elencoEsenzioniStoricoEssere = IntegrationClientImpl.getInstance().getEsenzioniAcceleratore(filtri,listaprotocolli);
						
					} catch (ClassNotFoundException e) {
						
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						
						e.printStackTrace();
					}
	
				if (elencoEsenzioniStoricoEssere != null) {
				elencoEsenzioni.addAll(elencoEsenzioniStoricoEssere);
				}
				
				return elencoEsenzioni;
			} else {
				elencoEsenzioni = getEsenzioni(filtri, null);
				for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : elencoEsenzioni) {
					//if (esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("V") && esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
					if ((esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("V") || esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("S") || esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase("B")) && esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
						listaprotocolli = listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString() + ",";
						//chiamo il metodo della ricerca storico
						try {
							EsenzioneStorico = IntegrationClientImpl.getInstance().getEsenzioni(esenredTEsenzioniReddito.getNumProtocolloSogei().toString(), 
									null, null, esenredTEsenzioniReddito.getIdCittadinoBeneficiario().toString(), "B",null).get(0);
							if (EsenzioneStorico != null) {
							esenredTEsenzioniReddito.setDataRevoca(EsenzioneStorico.getDataRevoca());
							esenredTEsenzioniReddito.setCodStato("R");
							esenredTEsenzioniReddito.setIdUserInsert(new Long ("99999998"));
							if (esenredTEsenzioniReddito.getNota()!=null) {
							esenredTEsenzioniReddito.setNota(esenredTEsenzioniReddito.getNota() + " (nota storico : " + EsenzioneStorico.getNota() + ")");
							}
							else
							{
								esenredTEsenzioniReddito.setNota(" (nota storico : " + EsenzioneStorico.getNota() + ")");
							}
							}
						} catch (ClassNotFoundException e) {
							
							
							e.printStackTrace();
						} catch (SQLException e) {
							
							
							e.printStackTrace();
						}
					}	
					else if (esenredTEsenzioniReddito.getNumProtocolloSogei() != null) {
						listaprotocolli = listaprotocolli + esenredTEsenzioniReddito.getNumProtocolloSogei().toString() + ",";
					}
				}
				if (listaprotocolli != "") {
					//prendi tutte le altre dello storico escludendo quelle di esenred
					listaprotocolli = listaprotocolli.substring(0, listaprotocolli.length() - 1);
				}
					try {
						//elencoEsenzioniStoricoEssere = getEsenzioniUtenteOracleTotale(filtri,listaprotocolli);
						elencoEsenzioniStoricoEssere = IntegrationClientImpl.getInstance().getEsenzioniAcceleratore(filtri,listaprotocolli);
					} catch (ClassNotFoundException e) {
						
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						
						e.printStackTrace();
					}
	
				if (elencoEsenzioniStoricoEssere != null) {
					elencoEsenzioni.addAll(elencoEsenzioniStoricoEssere);
					}
				
				return elencoEsenzioni;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<EsenredTEsenzioniReddito> getOperatoriAsl(String codAslOperatore) {

		if (codAslOperatore != null) {
			return getEsenzioniAsl(codAslOperatore);
		}
		return null;
	}

	private List<EsenredTEsenzioniReddito> getEsenzioniUtente(FiltriRicercaEsenzioniCittadino filtri) {
		String queryStr ="";
		
		if (filtri.getStorico().equalsIgnoreCase("No")) {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.dataFine >= current_date and ";
		} 
		else
		{
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.dataFine < current_date and ";
		}
		//String queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where ( ";
		final String wildCard = ",";
		if (Checker.isValorizzato(filtri.getIdAuraDichiarante())) {
			queryStr = queryStr
					+ " e.idCittadinoDichiarante = :idCittadinoDichiarante and e.idCittadinoBeneficiario = :idCittadinoDichiarante and ";
		}

		if (Checker.isValorizzato(filtri.getCodEsenzione()))
			queryStr = queryStr + " e.codEsenzione = :codEsenzione and ";

		if (Checker.isValorizzato(filtri.getCodStatoEsenzione())) {
			if (filtri.getCodStatoEsenzione().contains(wildCard)) {
				String[] codStati = filtri.getCodStatoEsenzione().split(wildCard);
				queryStr = queryStr + " ( ";
				for (int i = 0; i < codStati.length; i++) {
					queryStr = queryStr + " e.codStato = :codStatoEsenzione" + i;
					if (i != codStati.length - 1)
						queryStr = queryStr + " or ";
				}
				queryStr = queryStr + " ) and ";
			} else {
				queryStr = queryStr + " e.codStato = :codStatoEsenzione and ";
			}
		}
		// ultimi 365 giorni
		// queryStr = queryStr + " e.dataInizio >= :dataInizioValidita and e.dataFine <=
		// :dataFineValidita ) order by e.dataInizio desc ";
		queryStr = queryStr
				+ " e.dataInizio >= :dataInizioValidita and e.dataFine <= :dataFineValidita ) order by e.dataInizio desc ";

		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);

		if (Checker.isValorizzato(filtri.getIdAuraDichiarante())) {
			Long idDichiarante = new Long(filtri.getIdAuraDichiarante());
			query.setParameter("idCittadinoDichiarante", new Long(idDichiarante));
		}
		if (Checker.isValorizzato(filtri.getIdAuraBeneficiario())) {
			Long idBeneficiario = new Long(filtri.getIdAuraBeneficiario());
			query.setParameter("idCittadinoBeneficiario", idBeneficiario);
		}

		if (Checker.isValorizzato(filtri.getCodEsenzione()))
			query.setParameter("codEsenzione", filtri.getCodEsenzione());
		if (Checker.isValorizzato(filtri.getCodStatoEsenzione())) {
			if (filtri.getCodStatoEsenzione().contains(wildCard)) {
				String[] codStati = filtri.getCodStatoEsenzione().split(wildCard);
				queryStr = queryStr + " ( ";
				for (int i = 0; i < codStati.length; i++) {
					query.setParameter("codStatoEsenzione" + i, codStati[i]);
				}
				queryStr = queryStr + " ) and ";
			} else {
				query.setParameter("codStatoEsenzione", filtri.getCodStatoEsenzione());
			}
		}

		// ultimi 365 GG
		//Date today = Converter.getData(Converter.getData(new Date()));
		//errore perche la data era errata
		parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
		String ggmm = parametroIf.getParametri("DATA_SCADENZA_PARAMETRIZZATA").get(0).getValore();
		DataInizioFine output = Util.getDataInizioFine(ggmm);
		Date today = Converter.getData(output.getFineValidita());
		//Date today = Converter.getData(ggmm + "/" + Converter.getAnno(new Date()));
		Date last365 = Converter.getLast365Days();
		Date last365St = Converter.aggiungiAnnoAData(today, -1);
		Date last1095 = Converter.getLast1095Days();
		if (filtri.getStorico().equalsIgnoreCase("No")) {
		if (Checker.isValorizzato(filtri.getDataInizioValidita())) {
			query.setParameter("dataInizioValidita", Converter.getData(filtri.getDataInizioValidita()));
			// queryStr = queryStr + " e.dataInizio >= :dataInizioValidita and ";
		} else
			query.setParameter("dataInizioValidita", last365);

		if (Checker.isValorizzato(filtri.getDataFineValidita())) {
			// queryStr = queryStr + " e.dataFine <= :dataFineValidita and ";
			query.setParameter("dataFineValidita", Converter.getData(filtri.getDataFineValidita()));
		} else
			query.setParameter("dataFineValidita", today);
		}
		else
		{
			if (Checker.isValorizzato(filtri.getDataInizioValidita())) {
				query.setParameter("dataInizioValidita", Converter.getData(filtri.getDataInizioValidita()));
				// queryStr = queryStr + " e.dataInizio >= :dataInizioValidita and ";
			} else
				query.setParameter("dataInizioValidita", last1095);

			if (Checker.isValorizzato(filtri.getDataFineValidita())) {
				// queryStr = queryStr + " e.dataFine <= :dataFineValidita and ";
				query.setParameter("dataFineValidita", Converter.getData(filtri.getDataFineValidita()));
			} else
				query.setParameter("dataFineValidita", last365St);
		}
		
		return query.getResultList();
		
	}
	
	public List<EsenredTEsenzioniReddito> getEsenzioniNonValide(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
		List<EsenredTEsenzioniReddito> resultList = this.em
				.createNamedQuery("EsenredTEsenzioniReddito.findEsenzioneNonValida", EsenredTEsenzioniReddito.class)
				.setParameter("idAuraBen", esenredTEsenzioniReddito.getIdCittadinoBeneficiario())
				.setParameter("idAuraDic", esenredTEsenzioniReddito.getIdCittadinoDichiarante())
				.setParameter("idAuraTit", esenredTEsenzioniReddito.getIdCittadinoTitolare())
				.setParameter("codEsenzione", esenredTEsenzioniReddito.getCodEsenzione())
				.setParameter("titDich", esenredTEsenzioniReddito.getCodTitoloDichiarante())
				.setParameter("dataInizio", esenredTEsenzioniReddito.getDataInizio())
				.setParameter("dataFine", esenredTEsenzioniReddito.getDataFine())
				.setParameter("codAsl", esenredTEsenzioniReddito.getCodNazionaleAslRilascio())
				.getResultList();

		return resultList.isEmpty() ? new ArrayList<EsenredTEsenzioniReddito>() : resultList;
		
	}
	
	public EsenredTEsenzioniReddito getEsenzioniRevocate(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
		List<EsenredTEsenzioniReddito> resultList = this.em
				.createNamedQuery("EsenredTEsenzioniReddito.findEsenzioneRevocata", EsenredTEsenzioniReddito.class)
				.setParameter("idAura", esenredTEsenzioniReddito.getIdCittadinoBeneficiario())
				.setParameter("codEsenzione", esenredTEsenzioniReddito.getCodEsenzione())
				.getResultList();

		return resultList.isEmpty() ? null : resultList.get(0);
		
	}



	public List<Long> getEsenzioniBeneficiari(Long idAura) {
		String queryStr ="";
		

		queryStr = "SELECT distinct e.idCittadinoBeneficiario FROM EsenredTEsenzioniReddito e ";
		if (Checker.isValorizzato(idAura)) {
			queryStr = queryStr
					+ " where (e.idCittadinoDichiarante = :idCittadinoDichiarante or e.idCittadinoBeneficiario = :idCittadinoDichiarante or e.idCittadinoTitolare = :idCittadinoDichiarante) and e.idCittadinoBeneficiario is not null";
		}
		
		queryStr = queryStr
				+ " order by e.idCittadinoBeneficiario"; 
		
		TypedQuery<Long> query = em.createQuery(queryStr, Long.class);

		if (Checker.isValorizzato(idAura)) {
			Long idDichiarante = new Long(idAura);
			query.setParameter("idCittadinoDichiarante", new Long(idDichiarante));
		}

		return query.getResultList();
	}
		
	public List<Long> getEsenzioniTitolari(Long idAura) {
		String queryStr ="";

		
		queryStr = "SELECT distinct e.idCittadinoTitolare FROM EsenredTEsenzioniReddito e ";
		if (Checker.isValorizzato(idAura)) {
			queryStr = queryStr
					+ " where (e.idCittadinoDichiarante = :idCittadinoDichiarante or e.idCittadinoBeneficiario = :idCittadinoDichiarante or e.idCittadinoTitolare = :idCittadinoDichiarante) and e.idCittadinoTitolare is not null";
		}
		
		queryStr = queryStr
				+ " order by e.idCittadinoTitolare"; 

		TypedQuery<Long> query = em.createQuery(queryStr, Long.class);

		if (Checker.isValorizzato(idAura)) {
			Long idDichiarante = new Long(idAura);
			query.setParameter("idCittadinoDichiarante", new Long(idDichiarante));
		}

		return query.getResultList();
	}

	private List<EsenredTEsenzioniReddito> getEsenzioniFamiliari(FiltriRicercaEsenzioniCittadino filtri) {
		final String wildCard = ",";
		
		String queryStr ="";
		
		if (filtri.getStorico().equalsIgnoreCase("No")) {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.dataFine >= current_date and ";
		} 
		else
		{
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.dataFine < current_date and ";
		}
		//String queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where ( ";

		if (Checker.isValorizzato(filtri.getIdAuraDichiarante())) {
			queryStr = queryStr
					+ " e.idCittadinoDichiarante = :idCittadinoDichiarante and e.idCittadinoBeneficiario != :idCittadinoDichiarante and ";
		}

		if (Checker.isValorizzato(filtri.getCodEsenzione()))
			queryStr = queryStr + " e.codEsenzione = :codEsenzione and ";
		if (Checker.isValorizzato(filtri.getCodStatoEsenzione())) {
			if (filtri.getCodStatoEsenzione().contains(wildCard)) {
				String[] codStati = filtri.getCodStatoEsenzione().split(wildCard);
				queryStr = queryStr + " ( ";
				for (int i = 0; i < codStati.length; i++) {
					queryStr = queryStr + " e.codStato = :codStatoEsenzione" + i;
					if (i != codStati.length - 1)
						queryStr = queryStr + " or ";
				}
				queryStr = queryStr + " ) and ";
			} else {
				queryStr = queryStr + " e.codStato = :codStatoEsenzione and ";
			}
		}


		queryStr = queryStr
				+ " e.dataInizio >= :dataInizioValidita and e.dataFine <= :dataFineValidita ) order by e.dataInizio desc ";

		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);

		Long idDichiarante = new Long(filtri.getIdAuraDichiarante());
		query.setParameter("idCittadinoDichiarante", new Long(idDichiarante));

		if (Checker.isValorizzato(filtri.getCodEsenzione()))
			query.setParameter("codEsenzione", filtri.getCodEsenzione());
		if (Checker.isValorizzato(filtri.getCodStatoEsenzione())) {
			if (filtri.getCodStatoEsenzione().contains(wildCard)) {
				String[] codStati = filtri.getCodStatoEsenzione().split(wildCard);
				queryStr = queryStr + " ( ";
				for (int i = 0; i < codStati.length; i++) {
					query.setParameter("codStatoEsenzione" + i, codStati[i]);
				}
				queryStr = queryStr + " ) and ";
			} else {
				query.setParameter("codStatoEsenzione", filtri.getCodStatoEsenzione());
			}
		}

		
		parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
		String ggmm = parametroIf.getParametri("DATA_SCADENZA_PARAMETRIZZATA").get(0).getValore();
		DataInizioFine output = Util.getDataInizioFine(ggmm);
		Date today = Converter.getData(output.getFineValidita());
		//Date today = Converter.getData(ggmm + "/" + Converter.getAnno(new Date()));
		Date last365 = Converter.getLast365Days();
		Date last365St = Converter.aggiungiAnnoAData(today, -1);
		Date last1095 = Converter.getLast1095Days();
		if (filtri.getStorico().equalsIgnoreCase("No")) {
		if (Checker.isValorizzato(filtri.getDataInizioValidita())) {
			query.setParameter("dataInizioValidita", Converter.getData(filtri.getDataInizioValidita()));
			// queryStr = queryStr + " e.dataInizio >= :dataInizioValidita and ";
		} else
			query.setParameter("dataInizioValidita", last365);

		if (Checker.isValorizzato(filtri.getDataFineValidita())) {
			// queryStr = queryStr + " e.dataFine <= :dataFineValidita and ";
			query.setParameter("dataFineValidita", Converter.getData(filtri.getDataFineValidita()));
		} else
			query.setParameter("dataFineValidita", today);
		}
		else
		{
			if (Checker.isValorizzato(filtri.getDataInizioValidita())) {
				query.setParameter("dataInizioValidita", Converter.getData(filtri.getDataInizioValidita()));
				// queryStr = queryStr + " e.dataInizio >= :dataInizioValidita and ";
			} else
				query.setParameter("dataInizioValidita", last1095);

			if (Checker.isValorizzato(filtri.getDataFineValidita())) {
				// queryStr = queryStr + " e.dataFine <= :dataFineValidita and ";
				query.setParameter("dataFineValidita", Converter.getData(filtri.getDataFineValidita()));
			} else
				query.setParameter("dataFineValidita", last365St);
		}
		return query.getResultList();
	}

	public boolean checkBloccoCittadino(Long idAura, Long idAuraDic,String codfiscDich) {
		String queryStr = "";
		if (idAuraDic != null) {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.idCittadinoBeneficiario = :idCittadinoBeneficiario or e.idCittadinoDichiarante = :idCittadinoDichiarante) and e.codStato = 'B'";
		} else if (codfiscDich != null) {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.idCittadinoBeneficiario = :idCittadinoBeneficiario or e.cfDichiarantefr = :cfDichiarantefr) and e.codStato = 'B'";
		}
		else {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idCittadinoBeneficiario and e.codStato = 'B'";
		}
			
		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);
		if (idAuraDic != null) {
			query.setParameter("idCittadinoBeneficiario", idAura);
			query.setParameter("idCittadinoDichiarante", idAuraDic);
		} else if (codfiscDich != null) {
			query.setParameter("idCittadinoBeneficiario", idAura);
			query.setParameter("cfDichiarantefr", codfiscDich);
		} else {
		query.setParameter("idCittadinoBeneficiario", idAura);
	}

		return query.getResultList().size() > 0;
	}
	
	@Transactional(readOnly = true)
	public boolean checkBloccoCittadino(String codfiscDich) {
		String queryStr = "";
		if (codfiscDich != null) {
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where (e.cfDichiarantefr = :cfDichiarantefr) and e.codStato = 'B'";
		}
		
		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);
		if (codfiscDich != null) {
			query.setParameter("cfDichiarantefr", codfiscDich);
		} 
		
		return query.getResultList().size() > 0;
	}

	public Date DataSBloccoCittadino(Long idAura) {
		String queryStr = "";
			queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idCittadinoBeneficiario and e.codStato = 'B'";	
		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);
			query.setParameter("idCittadinoBeneficiario", idAura);			
		return query.getResultList().get(0).getDataRevoca();
	}
	
	public List<EsenredTEsenzioniReddito> getEsenzioniFamiliariDaBloccare(Long idAura, String codEsenzione) {

		String queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where ( ";
		queryStr = queryStr
				+ " e.idCittadinoDichiarante = :idCittadinoDichiarante and e.idCittadinoBeneficiario != :idCittadinoDichiarante and ";
		queryStr = queryStr + " e.codEsenzione = :codEsenzione and ";
		queryStr = queryStr + " e.codStato = :codStatoEsenzione)";

		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);

		query.setParameter("idCittadinoDichiarante", idAura);
		query.setParameter("codEsenzione", codEsenzione);
		query.setParameter("codStatoEsenzione", "V");

		return query.getResultList();
	}

	public List<EsenredTEsenzioniReddito> getEsenzioniControlloInserimento(Long idAura, String codEsenzione) {

		String queryStr = "SELECT e FROM EsenredTEsenzioniReddito e where ( ";
		queryStr = queryStr + " e.idCittadinoBeneficiario = :idCittadinoBeneficiario and ";
		queryStr = queryStr + " e.codEsenzione = :codEsenzione and ";
		queryStr = queryStr + " e.codStato = :codStatoEsenzione and ";
		queryStr = queryStr + " e.idUserModify = :idUserModify and ";
		queryStr = queryStr + " current_date - e.dataModify <= 2)";
		TypedQuery<EsenredTEsenzioniReddito> query = em.createQuery(queryStr, EsenredTEsenzioniReddito.class);

		query.setParameter("idCittadinoBeneficiario", idAura);
		query.setParameter("codEsenzione", codEsenzione);
		query.setParameter("codStatoEsenzione", "R");
		query.setParameter("idUserModify", -8888L);
		return query.getResultList();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertAudit(CsiLogAudit audit) {
		if(AppID==null) {
			AppID = getParametro("ID_APP_AUDIT").getValore();
		}
		audit.setIdApp(AppID);
		audit.setDataOra(new Timestamp(new Date().getTime()));
		em.persist(audit);
	}

	@Override
	public boolean updateAudit(CsiLogAudit audit) {
		try{
			em.merge(audit);
			return true;
		}
		catch (Exception e) {			
		}
		return false;
	}

	@Override
	public CsiLogAudit findAudit(Long idAudit) {
		TypedQuery<CsiLogAudit> query = em.createNamedQuery("CsiLogAudit.findAudit",
				CsiLogAudit.class);
		query.setParameter("auditId", idAudit);
		return query.getSingleResult();
	}

	@Override
	public List<CsiLogAudit> findAllAudit() {
		TypedQuery<CsiLogAudit> query = em.createNamedQuery("CsiLogAudit.findAll", CsiLogAudit.class);
		return query.getResultList();
	}	

}