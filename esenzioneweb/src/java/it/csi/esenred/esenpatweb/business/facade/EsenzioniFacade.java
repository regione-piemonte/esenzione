/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.facade;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response.Status;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.xml.XmpSerializer;
import org.hibernate.annotations.Check;
import org.jboss.resteasy.util.DateUtil;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.XfaForm;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenpatweb.dto.Beneficiario;
import it.csi.esenred.esenpatweb.dto.Certificato;
import it.csi.esenred.esenpatweb.dto.CertificatoCittadino;
import it.csi.esenred.esenpatweb.dto.CertificatoEsenpat;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.CittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.DiagnosiGruppo;
import it.csi.esenred.esenpatweb.dto.Documenti;
import it.csi.esenred.esenpatweb.dto.Esenzione;
import it.csi.esenred.esenpatweb.dto.EsenzioneAssistito;
import it.csi.esenred.esenpatweb.dto.EsenzioneCittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.EsenzioneGruppo;
import it.csi.esenred.esenpatweb.dto.FiltriListaEsenzioni;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaCertificatoPatologia;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaPratiche;
import it.csi.esenred.esenpatweb.dto.FiltriValidaEsenzionePatologia;
import it.csi.esenred.esenpatweb.dto.FiltroDettaglioEsenzione;
import it.csi.esenred.esenpatweb.dto.ListaDiagnosi;
import it.csi.esenred.esenpatweb.dto.ListaEsenzioni;
import it.csi.esenred.esenpatweb.dto.Malattia;
import it.csi.esenred.esenpatweb.dto.MotivazioneAnnullamento;
import it.csi.esenred.esenpatweb.dto.MotivazioneRevocaEsenpat;
import it.csi.esenred.esenpatweb.dto.NuovaEsenzione;
import it.csi.esenred.esenpatweb.dto.NuovoCertificato;
import it.csi.esenred.esenpatweb.dto.Prestazioni;
import it.csi.esenred.esenpatweb.dto.StatoDocumento;
import it.csi.esenred.esenpatweb.dto.UserInfo;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.get.ArrayOfinfoesenzioneInfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.bo.RichiestaRettificaDatiBO;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredDAziendasanitaria;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDurataTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPrestazioneSpecialistica;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumentoPK;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTMetadatiDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTProgressivo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

@Service
public class EsenzioniFacade {

	private final static Logger logger = Logger.getLogger(EsenzioniFacade.class.getName());

	private DataDaoPatologiaIf dataDao;
	ComuneIf comuneIf;

	public List<EsenzioneCittadinoEsenpat> getListaEsenzioni(String cit_id, String esenzione_id, String queryString,
			String codiceFiscale) throws Exception {
		FiltriListaEsenzioni filtriListaEsenzioni = null;
		if (queryString != null && queryString.length() > 0) {
			String queryStringNew = queryString.replace("'", "\"");

			ObjectMapper mapper = new ObjectMapper();
			try {
				filtriListaEsenzioni = mapper.readValue(queryStringNew, FiltriListaEsenzioni.class);
			} catch (Exception e) {
				String errorMessage = "Impossibile leggere il filtro per la ricerca delle pratiche di esezioni per il cittadino "
						+ cit_id;
				logger.error(errorMessage);
				e.printStackTrace();
				throw new Exception(errorMessage);
			}
		}

		List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones = dataDao.getListaEsenzioni(cit_id, esenzione_id,
				filtriListaEsenzioni);

		if (esenzioneTPraticaEsenziones != null && esenzioneTPraticaEsenziones.size() > 0) {
			List<EsenzioneCittadinoEsenpat> esenzioniCittadinos = new ArrayList<EsenzioneCittadinoEsenpat>(
					esenzioneTPraticaEsenziones.size());
			for (EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione : esenzioneTPraticaEsenziones) {
				esenzioniCittadinos.add(new EsenzioneCittadinoEsenpat(esenzioneTPraticaEsenzione));
			}
			return esenzioniCittadinos;
		}
		return null;
	}

	public EsenzioneTPraticaEsenzione insertPraticaEsenzione(NuovaEsenzione praticaEsenzione, UserInfo utente,
			Cittadino cittadino) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);

		EsenzioneTPraticaEsenzione pratica = new EsenzioneTPraticaEsenzione();
		EsenzioneTDocumento esenzioneTDocumento = new EsenzioneTDocumento();
		
		String codFiscaleBen = praticaEsenzione.getAssistito().getCodFiscale();
		pratica.setCodiceFiscaleBeneficiario(codFiscaleBen);
		pratica.setCodiceFiscaleDelegato(null);
		// praticaEsenzione.setDatCreazione(new Timestamp(date.getTime()));
		pratica.setDatCreazione(new Timestamp(System.currentTimeMillis()));
	
		pratica.setDescNotaBeneficiario(null);
		pratica.setEsenzioneDDiagnosi(dataDao.getEsenzioneDDiagnosi(praticaEsenzione.getMalattia().getCodDiagnosi()));
		pratica.setEsenzioneDGruppoEsenzioni(
				dataDao.getEsenzioneDEsenzioneByDiagnosiId(pratica.getEsenzioneDDiagnosi().getSkDiagnosi().toString())
						.getEsenzioneDGruppoEsenzioni());
			
		pratica.setSkDiagnosi(new Long(pratica.getEsenzioneDDiagnosi().getSkDiagnosi()));
		pratica.setSkGruppo(new Long(pratica.getEsenzioneDGruppoEsenzioni().getSkGruppo()));
		pratica.setEsenzioneDPraticaStato(new EsenzioneDPraticaStato(
				dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA).getSkPraticaStato()));
		pratica.setSkTipologiaStatoPratica(new Long(pratica.getEsenzioneDPraticaStato().getSkPraticaStato()));

		pratica.setIdAzienda(cittadino.getCodASL());
		pratica.setCodTipoUser("B");
		String numPratica = getNumeroProgressivoPraticaEsenzione(cittadino.getCodASL());
		pratica.setNumPratica(Long.valueOf(numPratica));

		pratica.setEsenzioneDPraticaStato(
				dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA));

		// lotto 2 - na - null
		pratica.setCodRuoloOperatore(null);
		pratica.setIdUser(null);
		pratica.setCodiceFiscaleOperatore(utente.getCodFisc());
		pratica.setDescNotaOperatore(null);
		pratica.setDescNota(null);
		// praticaEsenzione.setSkTipoMotivazione(null);
		pratica.setEsenzioneDMotivazioneTipo(null);
		pratica.setFlagDichiarazione(null);
		pratica.setIdDichiarazione(null);
		pratica.setDatCancellazione(null);
		pratica.setDescNotaInterna(null);
		pratica.setDatModifica(null);
		pratica.setDatInizioValidita(null);

		EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneAlreadySaved = new EsenzioneTPraticaEsenzione();
		try {
			esenzioneTPraticaEsenzioneAlreadySaved = dataDao.getPraticaEsenzioneByCodFiscaleAndCodEsenzione(
					cittadino.getCodFiscale(), praticaEsenzione.getMalattia().getCodEsenzione());

		} catch (Exception e) {
			esenzioneTPraticaEsenzioneAlreadySaved = null;
		}

		if (esenzioneTPraticaEsenzioneAlreadySaved == null || esenzioneTPraticaEsenzioneAlreadySaved
				.getEsenzioneDDiagnosi().getSkEsenzione() == pratica.getEsenzioneDDiagnosi().getSkEsenzione()) {
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = dataDao.insertPraticaEsenzione(pratica);
			return esenzioneTPraticaEsenzione;
		} else {
			throw new EsenpatException(Constants.MSG230);
		}

	}

	public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzione(String numPratica) {
		return dataDao.getEsenzioneTPraticaEsenzione(numPratica);
	}

	boolean hasStorico(EsenzioneTPraticaEsenzione etpe) {
		List<EsenzioneSPraticaEsenzione> espe = dataDao
				.getEsenzioneSPraticaEsenzionebyskPratica(etpe.getSkPraticaEsenzione());
		return (espe != null && espe.size() > 0);
	}

	public EsenzioneSPraticaEsenzione copyInEsenzioneSPraticaEsenzione(EsenzioneTPraticaEsenzione etpe,
			boolean isDelegato) throws CheckException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();

			String codTipoUser = isDelegato ? "DELEGATO" : "BENEFICIARIO";
			EsenzioneSPraticaEsenzione espe = new EsenzioneSPraticaEsenzione();
			espe.setCodRuoloOperatore(etpe.getCodRuoloOperatore());
			espe.setIdUser(etpe.getIdUser() != null ? new Integer(etpe.getIdUser().toString()) : null);
			espe.setCodFiscaleOperatore(etpe.getCodiceFiscaleOperatore());
			espe.setCodTipoUser(codTipoUser);
			espe.setDatCreazione(etpe.getDatCreazione());
			espe.setDescNotaBeneficiario(etpe.getDescNotaBeneficiario());
			espe.setDescNota(etpe.getDescNota());
			espe.setDescNotaOperatore(etpe.getDescNotaOperatore());
			if (etpe.getEsenzioneDPraticaStato() == null)
				espe.setSkTipologiaStatoPratica(null);
			else
				espe.setSkTipologiaStatoPratica(
						Long.decode(etpe.getEsenzioneDPraticaStato().getSkPraticaStato().toString()));
			// espe.setDatModifica(etpe.getDatModifica());
			if (hasStorico(etpe)) {
				espe.setDatModifica(etpe.getDatModifica());
			} else {
				espe.setDatModifica(null);
			}
			espe.setEsenzioneDMotivazioneTipo(etpe.getEsenzioneDMotivazioneTipo());
			espe.setDescNotaInterna(etpe.getDescNotaInterna());
			espe.setNumPratica(etpe.getNumPratica());
			if (etpe.getEsenzioneDDiagnosi() == null)
				espe.setSkDiagnosi(null);
			else
				espe.setSkDiagnosi(Long.decode(etpe.getEsenzioneDDiagnosi().getSkDiagnosi().toString()));
			espe.setSkDistrettoSocioSanitario(etpe.getSkDistrettoSocioSanitario());
			if (etpe.getEsenzioneDInvaliditaTipo() == null)
				espe.setSkInvaliditaTipo(null);
			else
				espe.setSkInvaliditaTipo(
						Long.decode(etpe.getEsenzioneDInvaliditaTipo().getSkInvaliditaTipo().toString()));
			espe.setIdAzienda(etpe.getIdAzienda());
			espe.setCodFiscaleCittadinoBeneficiario(etpe.getCodiceFiscaleBeneficiario());
			if (etpe.getEsenzioneDGruppoEsenzioni() == null)
				espe.setSkGruppo(null);
			else
				espe.setSkGruppo(Long.decode(etpe.getEsenzioneDGruppoEsenzioni().getSkGruppo().toString()));
			espe.setCodFiscaleCittadinoDelegato(etpe.getCodiceFiscaleDelegato());
			espe.setCodFiscaleOperatore(etpe.getCodiceFiscaleOperatore());
			espe.setSkIdEsenzione(new Long(etpe.getSkPraticaEsenzione()));
			espe.setDatInizioValidita(etpe.getDatModifica() != null ? etpe.getDatModifica() : etpe.getDatCreazione());
			espe.setDatFineValidita(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));

			if (hasStorico(etpe)) {
				espe.setDatModifica(etpe.getDatModifica());
			}

			dataDao.insertEsenzioneSPraticaEsenzione(espe);

			return espe;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}

	public EsenzioneSDocumento copyInEsenzioneSDocumento(EsenzioneTPraticaEsenzione etpe) throws CheckException {
		try {
			//prendo ultimo elemento da t_documento relativo alla pratica in oggetto
			EsenzioneTDocumento maxDocumentoPratica = dataDao
					.getMaxDocumentoPerPratica("2", etpe.getSkPraticaEsenzione().toString());
			//copio il record nello storico
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();

			EsenzioneSDocumento espe = new EsenzioneSDocumento();
			espe.setCodRuoloOperatore(maxDocumentoPratica.getCodRuoloOperatore());
			espe.setCodiceFiscaleCittadino(maxDocumentoPratica.getCodiceFiscaleCittadino());
			espe.setCodTipoUser(maxDocumentoPratica.getCodTipoUser());
			espe.setDatCreazione(maxDocumentoPratica.getDatCreazione());
			espe.setDatCancellazione(maxDocumentoPratica.getDatCancellazione());
			espe.setDatCreazione(maxDocumentoPratica.getDatCreazione());
			espe.setDatDocumento(maxDocumentoPratica.getDatDocumento());
			espe.setDatModifica(maxDocumentoPratica.getDatModifica());
			espe.setDescEstesaPatologiaCertificato(maxDocumentoPratica.getDescEstesaPatologiaCertificato());
			espe.setDescNote(maxDocumentoPratica.getDescNote());
			espe.setFlagConformitaDocumento(maxDocumentoPratica.getFlagConformitaDocumento());
			espe.setIdAuraAttestato(maxDocumentoPratica.getIdAuraAttestato());
			espe.setIdUserid(maxDocumentoPratica.getIdUserid());
			espe.setOidDocumento(maxDocumentoPratica.getOidDocumento());
		    espe.setSkDiagnosi(maxDocumentoPratica.getSkDiagnosi());
		    espe.setSkDocumento(new Long(maxDocumentoPratica.getSkDocumento()));
			espe.setSkRepository(maxDocumentoPratica.getSkRepository());
			espe.setSkTipoDocumento(maxDocumentoPratica.getSkTipoDocumento());
			espe.setSkTipologiaStatoDocumento(maxDocumentoPratica.getSkTipologiaStatoDocumento());
			espe.setDatInizioValidita(etpe.getDatModifica() != null ? etpe.getDatModifica() : etpe.getDatCreazione());
			if (etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_SCADUTA))
				espe.setDatFineValidita(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
			else
				espe.setDatFineValidita(null);
			
			dataDao.insertDocumentoStoricoPraticaEsenzione(espe);
			return espe;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}
	
	public EsenzioneSDocumento copyInEsenzioneSDocumento(EsenzioneTDocumento etd) throws CheckException {
		try {

			EsenzioneSDocumento espe = new EsenzioneSDocumento();
			espe.setCodRuoloOperatore(etd.getCodRuoloOperatore());
			espe.setCodiceFiscaleCittadino(etd.getCodiceFiscaleCittadino());
			espe.setCodTipoUser(etd.getCodTipoUser());
			espe.setDatCreazione(etd.getDatCreazione());
			espe.setDatCancellazione(etd.getDatCancellazione());
			espe.setDatCreazione(etd.getDatCreazione());
			espe.setDatDocumento(etd.getDatDocumento());
			espe.setDatModifica(etd.getDatModifica());
			espe.setDescEstesaPatologiaCertificato(etd.getDescEstesaPatologiaCertificato());
			espe.setDescNote(etd.getDescNote());
			espe.setFlagConformitaDocumento(etd.getFlagConformitaDocumento());
			espe.setIdAuraAttestato(etd.getIdAuraAttestato());
			espe.setIdUserid(etd.getIdUserid());
			espe.setOidDocumento(etd.getOidDocumento());
			espe.setSkDiagnosi(etd.getSkDiagnosi());
			espe.setSkDocumento(new Long(etd.getSkDocumento()));
			espe.setSkRepository(etd.getSkRepository());
			espe.setSkTipoDocumento(etd.getSkTipoDocumento());
			espe.setSkTipologiaStatoDocumento(etd.getSkTipologiaStatoDocumento());
			espe.setDatInizioValidita(etd.getDatModifica() != null ? etd.getDatModifica() : etd.getDatCreazione());
			espe.setDatFineValidita(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));

			dataDao.insertDocumentoStoricoPraticaEsenzione(espe);
			return espe;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}

	public EsenzioneTRepositoryDocumentale insertRepositoryDocumentale(UserInfo utente, byte[] pdfFile,List<Ruolo> ruoli)
			throws CheckException {
		try {
			EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();
			repoDocumentale.setDescFile(null); // null?
			repoDocumentale.setDatArchiviazione(new Timestamp(System.currentTimeMillis()));
			repoDocumentale.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			repoDocumentale.setDatModifica(null);
			repoDocumentale.setFile(pdfFile);
			repoDocumentale.setFileName(null);
			repoDocumentale.setDatCancellazione(null);
			// utente che ha effettuato l'accesso - codRuoloOp e IdUser
			repoDocumentale.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			repoDocumentale.setIdUser(new Long(utente.getIdAura()));

			dataDao.insertRepositoryDocumentale(repoDocumentale);
			return repoDocumentale;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}

	public EsenzioneTPraticaEsenzione updatePraticaEsenzioneValidata(EsenzioneTPraticaEsenzione etpe, UserInfo utente,
			FiltriValidaEsenzionePatologia filtri,List<Ruolo> ruoli) throws CheckException {
		try {
			etpe.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
			etpe.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
			EsenzioneDDurataTipo esenzioneduratatipo = dataDao
					.getEsenzioneDDurataTipoperSKDurataTipo(etpe.getEsenzioneDDiagnosi().getSkDurataTipo());
			impostaDataFineValidita(etpe, esenzioneduratatipo, etpe.getEsenzioneDDiagnosi());

			etpe.setDescNota(filtri.getNota());
			etpe.setDescNotaBeneficiario(filtri.getNotabeneficiario());
			etpe.setDescNotaInterna(filtri.getNotainterna());
			EsenzioneDPraticaStato statoesenzione = dataDao
					.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_VALIDATA);
			etpe.setSkTipologiaStatoPratica(new Long(statoesenzione.getSkPraticaStato()));
			etpe.setIdUser(new Long(utente.getIdAura()));
			etpe.setCodiceFiscaleOperatore(utente.getCodFisc());
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneUpdated = dataDao
					.updateEsenzioneTPraticaEsenzione(etpe);
			return etpe;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
			// return null;
		}
	}
	
	public EsenzioneTPraticaEsenzione updatePraticaEsenzioneStato(EsenzioneTPraticaEsenzione etpe,String Stato) throws CheckException {
		try {
			
			EsenzioneDPraticaStato statoesenzione = dataDao
					.getEsenzioneDPraticaStatoPerCodStato(Stato);
			etpe.setSkTipologiaStatoPratica(new Long(statoesenzione.getSkPraticaStato()));
			return dataDao
					.updateEsenzioneTPraticaEsenzione(etpe);
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
//			return null;
		}
	}

	public Timestamp calcolaDataFineValidita(EsenzioneDDurataTipo esenzioneduratatipo, EsenzioneDDiagnosi diagnosi) {
		if (diagnosi.getNumDurata() != null) {
			// imposta data fine in base lla validita illimitato null
			Calendar cal = Calendar.getInstance();
			switch (esenzioneduratatipo.getCodDurataTipo().toUpperCase()) {
			case "G": {
				cal.add(Calendar.DATE, diagnosi.getNumDurata());
				return (new Timestamp(cal.getTimeInMillis()));
			}
			case "A": {
				cal.add(Calendar.YEAR, diagnosi.getNumDurata());
				return (new Timestamp(cal.getTimeInMillis()));
			}
			case "M": {
				cal.add(Calendar.MONTH, diagnosi.getNumDurata());
				return (new Timestamp(cal.getTimeInMillis()));
			}
			default:
				return null;
			}
		} else {
			return null;
		}
	}

	private void impostaDataFineValidita(EsenzioneTPraticaEsenzione etpe, EsenzioneDDurataTipo esenzioneduratatipo,
			EsenzioneDDiagnosi diagnosi) {
		if (diagnosi.getNumDurata() != null) {
		// imposta data fine in base lla validita illimitato null
			Calendar cal = Calendar.getInstance();
			switch (esenzioneduratatipo.getCodDurataTipo().toUpperCase()) {
			case "G": {
				cal.add(Calendar.DATE, diagnosi.getNumDurata());
				etpe.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
				break;
			}
			case "A": {
				cal.add(Calendar.YEAR, diagnosi.getNumDurata());
				etpe.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
				break;
			}
			case "M": {
				cal.add(Calendar.MONTH, diagnosi.getNumDurata());
				etpe.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
				break;
			}
			default:
				etpe.setDatFineValidita(null);
			}
		} else {
			etpe.setDatFineValidita(null);
		}
	}

	public EsenzioneTDocumento insertDocumentoTDocumento(EsenzioneTPraticaEsenzione etpe, UserInfo utente,
			FiltriValidaEsenzionePatologia filtri, Long skrepository,List<Ruolo> ruoli) throws CheckException {
		
		CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
		
		try {
			EsenzioneTDocumento docCertificato = new EsenzioneTDocumento();
			docCertificato.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			docCertificato.setDatModifica(null);
			docCertificato.setDatCancellazione(null);
			EsenzioneDDurataTipo esenzioneduratatipo = dataDao
					.getEsenzioneDDurataTipoperSKDurataTipo(etpe.getEsenzioneDDiagnosi().getSkDurataTipo());
			Calendar cal = Calendar.getInstance();
			if (etpe.getEsenzioneDDiagnosi().getNumDurata() != null) {
				switch (esenzioneduratatipo.getCodDurataTipo().toUpperCase()) {
				case "G": {
					cal.add(Calendar.DATE, etpe.getEsenzioneDDiagnosi().getNumDurata());
					docCertificato.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
					break;
				}
				case "A": {
					cal.add(Calendar.YEAR, etpe.getEsenzioneDDiagnosi().getNumDurata());
					docCertificato.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
					break;
				}
				case "M": {
					cal.add(Calendar.MONTH, etpe.getEsenzioneDDiagnosi().getNumDurata());
					docCertificato.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
					break;
				}
				default:
					docCertificato.setDatFineValidita(null);
				}
			} else {
				docCertificato.setDatFineValidita(null);
			}

			docCertificato.setDescNote(filtri.getNotabeneficiario());
			docCertificato.setFlagConformitaDocumento(null);
			docCertificato.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			docCertificato.setIdUserid(new Long(utente.getIdAura()));
			docCertificato.setCodTipoUser("D");
			docCertificato.setDatDocumento(new Timestamp(System.currentTimeMillis()));
			docCertificato.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
			docCertificato.setSkRepository(skrepository);
			docCertificato.setSkTipoDocumento(new Long(2));
			docCertificato.setDescEstesaPatologiaCertificato(null);
			docCertificato.setSkTipologiaStatoDocumento(
					new Long(dataDao.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_VALIDOINATTESA)
							.getSkDocumentoStato()));
			docCertificato.setSkDiagnosi(new Long(etpe.getEsenzioneDDiagnosi().getSkDiagnosi()));
			docCertificato.setCodiceFiscaleCittadino(etpe.getCodiceFiscaleBeneficiario());
			docCertificato.setOidDocumento(certificatiFacade.getOidDocumento(null, Constants.OID_AES, etpe.getIdAzienda()));
			docCertificato.setIdAuraAttestato(null);
			dataDao.insertDocumentoPraticaEsenzione(docCertificato);
			return docCertificato;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}

	public EsenzioneTDocumento UpdateDocumentoTDocumento(EsenzioneTDocumento docCertificato) throws Exception {

		// caso di aggiornamento da stato da valido in attesa a valido
		docCertificato.setSkTipologiaStatoDocumento(new Long(dataDao
				.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_VALIDO).getSkDocumentoStato()));
		try {
			dataDao.updateEsenzioneTDocumento(docCertificato);
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
		return docCertificato;

	}

	public EsenzioneTMetadatiDocumento insertMetadatiDocumento(EsenzioneTDocumento documento, UserInfo utente,
			CittadinoEsenpat cittadino,List<Ruolo> ruoli) throws CheckException {
		try {
			String tipoAzione = "INSERIMENTO";
			String codApplicativoRichiesta = "ESENPAT";
			String mimeType = "application/pdf";

			EsenzioneTMetadatiDocumento metadati = new EsenzioneTMetadatiDocumento();

			metadati.setAssettoOrganizzativo(null);
			metadati.setCodApplicativoRichiesta(codApplicativoRichiesta);

			if (utente.getCodASL() != null) {
				if(utente.getCodASL().length() == 3) {
					EsenredDAziendasanitaria asl = dataDao.getAziendaSanitariaByCodAsl(utente.getCodASL());
					metadati.setCodAziendaRichiesta(asl.getCodRegione()+asl.getCodAzienda());
				} else {
					metadati.setCodAziendaRichiesta(utente.getCodASL());
				}
			} else metadati.setCodAziendaRichiesta(null);
			
			// DATI PAZIENTE
			metadati.setIdAuraPaziente(new Long(cittadino.getIdAura()));
			metadati.setCodComunePaziente(cittadino.getCodComuneNascita());
			metadati.setCodFiscalePaziente(cittadino.getCodFiscale());
			metadati.setCodSessoPaziente(cittadino.getCodSesso());
			metadati.setCodStatoNascitaPaziente(cittadino.getStato());
			metadati.setCognomePaziente(cittadino.getCognome());
			metadati.setNomePaziente(cittadino.getNome());
			metadati.setIdGenitoreTutorePaziente(null); // null
			
	
			if (utente.getCodASL() != null) {
				if ( utente.getCodASL().length() == 6) {
					metadati.setCodStruttura(new Integer(utente.getCodASL().substring(3, 6)));
				} else metadati.setCodStruttura(new Integer(utente.getCodASL()));
			} else metadati.setCodStruttura(null);
			
			metadati.setCodicePin(null);
			metadati.setCodMatricola(null);
			metadati.setCodMimeType(mimeType); // mimetype del documento
			metadati.setCodOscuraScaricoCittadino("S");
			metadati.setCodPrivacyCittadino("1");
			metadati.setCodTipoAttivitaClinica(null);
			metadati.setCodTipoAzione(tipoAzione);
			metadati.setIdEpisodio(null);
			metadati.setCodTipoAzioneEpisodio(null);
			metadati.setCodTipoEpisodio(null);
			metadati.setCodTipoDocumentoMedio(null);
			metadati.setCodTipoDocumentoAlto(null);
			metadati.setCodTipoStrutturaProdDoc(null);
			// FIRMA -> CDU FIRMA
			metadati.setCodTipoFirma(null);
			metadati.setDatFirma(null);
			metadati.setFlagFirmato(null);
			metadati.setDatInizioEpisodio(new Timestamp(System.currentTimeMillis()));
			metadati.setDatFineEpisodio(null);
			metadati.setFlagScaricaCittadino(true);
			metadati.setFlagSoggettoLeggiSpeciali(false);
			metadati.setHashDoc(null); // al momento settato a null
			metadati.setSizeDoc(null); // dimensione del documento espressa in byte
			metadati.setIdAula(null);
			metadati.setIdRichiesta(null);
			metadati.setIdRichiestaUtente(utente.getCodFisc());
			metadati.setNre(null);
			metadati.setMedicoValidatore(null);
			metadati.setMedicoRedattore(null);
			metadati.setRuoloUtente(ruoli.get(0).getCodiceRuolo());
			metadati.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			metadati.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			metadati.setDatCancellazione(null);
			metadati.setDatModifica(null);

			// DOCUMENTO
			metadati.setSkDocumento(new Long(documento.getSkDocumento()));

			// OPERATORE
			metadati.setIdUser(new Long(utente.getIdAura()));

			dataDao.insertMetadatiDocumento(metadati);
			return metadati;
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}

	public EsenzioneTPraticaEsenzione annullaEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe,
			String shibIdentitaCodiceFiscale, String citId, MotivazioneAnnullamento motivazione) {

		String cfDelegato = null;
	
		etpe.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
		etpe.setCodiceFiscaleBeneficiario(citId);
		etpe.setCodiceFiscaleDelegato(cfDelegato);
		etpe.setEsenzioneDMotivazioneTipo(
				dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(motivazione.getMotivazione().getCodice()));
		EsenzioneDPraticaStato eseStato = dataDao
				.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_ANNULLATA);
		etpe.setEsenzioneDPraticaStato(eseStato);
		etpe.setSkTipologiaStatoPratica(eseStato.getSkPraticaStato().longValue());
		etpe.setCodRuoloOperatore(null);
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		// etpe.setDatInizioValidita(null);
		etpe.setDescNotaInterna(motivazione.getNotaInterna());
		etpe.setFlagDichiarazione(null);
		etpe.setIdDichiarazione(null);
		etpe.setDatCancellazione(null);
		etpe.setDescNotaOperatore(null);
		etpe.setDescNota(motivazione.getNotaServizio());
		etpe.setIdUser(null);
		etpe.setCodiceFiscaleOperatore(null);
		etpe.setDescNotaBeneficiario(motivazione.getNotaBeneficiario());

		return dataDao.insertEsenzioneTPraticaEsenzione(etpe);
	}

	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDao) {
		this.dataDao = dataDao;
	}

	public String getNumeroProgressivoPraticaEsenzione(String idAzienda) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		cal.setTime(new Date());
		int numeroValore;
		String numPratica = null;
		EsenzioneTProgressivo numProg = dataDao.getNumeroProgressivo(idAzienda,
				Constants.CODICE_PROGRESSIVO_NUMERO_PRATICA);
		if (numProg != null) {
			if (numProg.getAnno() == cal.get(Calendar.YEAR)) {
				numeroValore = numProg.getNumValoreDisponibile();
			} else {
				numeroValore = 1;
				numProg.setAnno(cal.get(Calendar.YEAR));
			}
			numProg.setNumValoreDisponibile(numeroValore + 1);
			dataDao.setAnnoProgressivo(numProg);
			numPratica = cal.get(Calendar.YEAR) + numProg.getIdAzienda() + String.format("%08d", numeroValore);
			return numPratica;
		}
		return null;
	}



	public EsenzioneTPraticaEsenzione revocaEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe,
			String shibIdentitaCodiceFiscale, String citId, MotivazioneRevocaEsenpat motivazione) {

		String cfDelegato = null;
		etpe.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
		etpe.setCodiceFiscaleBeneficiario(citId);
		etpe.setCodiceFiscaleDelegato(cfDelegato);
		etpe.setEsenzioneDMotivazioneTipo(
				dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(motivazione.getMotivazione().getCodice()));
		EsenzioneDPraticaStato eseStato = dataDao
				.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_REVOCATA);
		etpe.setEsenzioneDPraticaStato(eseStato);
		etpe.setSkTipologiaStatoPratica(eseStato.getSkPraticaStato().longValue());
		etpe.setCodRuoloOperatore(null);
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		// etpe.setDatInizioValidita(null);
		etpe.setDescNotaInterna(motivazione.getNotaInterna());
		etpe.setFlagDichiarazione(null);
		etpe.setIdDichiarazione(null);
		etpe.setDatCancellazione(null);
		etpe.setDescNotaOperatore(null);
		etpe.setDescNota(motivazione.getNotaServizio());
		etpe.setIdUser(null);
		etpe.setCodiceFiscaleOperatore(null);
		etpe.setDescNotaBeneficiario(motivazione.getNotaBeneficiario());

		return dataDao.insertEsenzioneTPraticaEsenzione(etpe);
	}

	// prende una esenzione di un cittadino in base ad uno stato
	public EsenzioneTPraticaEsenzione getPraticaEsenzioneByStatus(String cit_id, Integer codMalattia, String codTipo,
			String[] status) {
		try {
			return dataDao.getPraticaEsenzioneByStatus(cit_id, codMalattia, codTipo, status);
		} catch (NoResultException e) {
			return null;
		}
	}

	public EsenzioneRPraticaEsenzioneDocumento getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(
			String certificato_id) {
		return dataDao.getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(certificato_id);
	}

	@Transactional()
	public EsenzioneCittadinoEsenpat setRinnovoEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione,
			String shibIdentitaCodiceFiscale, String citId, String numPratica, Cittadino citBen,
			Certificato certificato) throws EsenpatException, Exception {

		CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
		EsenzioneTPraticaEsenzione praticaEsenzione = new EsenzioneTPraticaEsenzione();

		// controllo se e' valorizzato il certificato_id
		if (!Checker.isValorizzato(certificato.getCertificato_id())) {
			// controllo se e' valorizzato il codiceMalattia e il codiceTipologia
			if (!Checker.isValorizzato(certificato.getFile_base64())
					|| !Checker.isValorizzato(certificato.getData_rilascio())
					|| certificato.getTipologia_documento() == null
					|| !Checker.isValorizzato(certificato.getTipologia_documento().getCodice())) {
				throw new EsenpatException(Constants.MSG145, "Parametri obbligatori mancanti",
						Status.BAD_REQUEST.getStatusCode());
			} else {

				EsenzioneTDocumento documento = new EsenzioneTDocumento();
				EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();
				String codTipoUser = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? "B" : "D";
				if (esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi() != null) {
					Malattia malattia = new Malattia();
					malattia.setCodice(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi());
					malattia.setDescrizione(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getDescDiagnosi());
					malattia.setGiorni_validita(esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getNumDurata());
					certificato.setMalattia(malattia);
				}
				praticaEsenzione = rinnovoPraticaEsenzione(shibIdentitaCodiceFiscale, citId, citBen, certificato,
						esenzioneTPraticaEsenzione);
				repoDocumentale = certificatiFacade.insertRepositoryDocumentale(certificato);
				documento = certificatiFacade.insertDocumentoPraticaEsenzione(citId, certificato, repoDocumentale,
						codTipoUser);
				certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(documento.getSkDocumento()),
						praticaEsenzione);
				return new EsenzioneCittadinoEsenpat(praticaEsenzione);
			}
		} else {
			// INSERIMENTO PRATICAESENZIONE CON CERTIFICATO_ID
			if (Checker.isNumericString(certificato.getCertificato_id())) {
				praticaEsenzione = rinnovoPraticaEsenzione(shibIdentitaCodiceFiscale, citId, citBen, certificato,
						esenzioneTPraticaEsenzione);
				CertificatoCittadino documento = certificatiFacade.getDettaglioCertificato(citId,
						certificato.getCertificato_id());
				String idDocumento = documento.getId();
				certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(idDocumento),
						praticaEsenzione);
				return new EsenzioneCittadinoEsenpat(praticaEsenzione);
			} else
				throw new EsenpatException(Constants.MSG149, "Mancata congruità parametri di input",
						Status.BAD_REQUEST.getStatusCode());
		}
	}

	@Transactional
	public EsenzioneCittadinoEsenpat setRettificaEsenzione(HttpServletRequest req, String shibIdentitaCodiceFiscale,
			String xRequestID, String XForwardedFor, String xcodServizio, String cit_id, String esenzione_id,
			Cittadino cittadino, Certificato certificato) throws Exception {

		EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = dataDao
				.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzione_id));
		if (esenzioneTPraticaEsenzione == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}
		if (esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos() == null
				|| esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().size() == 0
				|| certificato.getFile_base64() == null || certificato.getFile_base64().length() == 0) {
			throw new EsenpatException(Constants.MSG151, "Certificato non presente, pratica non modificabile",
					Status.CONFLICT.getStatusCode());
		}
		if (esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato() != null
				&& !shibIdentitaCodiceFiscale.equals(esenzioneTPraticaEsenzione.getCodiceFiscaleDelegato())) {
			throw new EsenpatException(Constants.MSG151, "Impossibile cambiare il delegato",
					Status.CONFLICT.getStatusCode());
		}
		if (esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario() != null
				&& !cit_id.equals(esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario())) {
			throw new EsenpatException(Constants.MSG151, "Impossibile cambiare il beneficiario",
					Status.CONFLICT.getStatusCode());
		}
		if (!esenzioneTPraticaEsenzione.getEsenzioneDInvaliditaTipo().getCodInvaliditaTipo()
				.equals(certificato.getTipologia().getCodice())) {
			throw new EsenpatException(Constants.MSG151, "Impossibile cambiare il tipo di invalidità",
					Status.CONFLICT.getStatusCode());
		}
		if (!esenzioneTPraticaEsenzione.getEsenzioneDDiagnosi().getCodDiagnosi()
				.equals(certificato.getMalattia().getCodice())) {
			throw new EsenpatException(Constants.MSG151, "Impossibile cambiare la patologia",
					Status.CONFLICT.getStatusCode());
		}
		if (esenzioneTPraticaEsenzione.getCodiceFiscaleBeneficiario().equals(cit_id)
				&& (esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato()
						.equals(Constants.STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_MEDICO)
						|| esenzioneTPraticaEsenzione.getEsenzioneDPraticaStato().getCodStato()
								.equals(Constants.STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_OPERATORE))) {
			// stato pratica compatibile, procediamo
			copyInEsenzioneSPraticaEsenzione(esenzioneTPraticaEsenzione,
					shibIdentitaCodiceFiscale.equalsIgnoreCase(cit_id));

			esenzioneTPraticaEsenzione.setDatModifica(new Timestamp(System.currentTimeMillis()));
			esenzioneTPraticaEsenzione.setDescNota(certificato.getNote());
			esenzioneTPraticaEsenzione.setEsenzioneDPraticaStato(
					dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA));

			esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().iterator().next()
					.getEsenzioneTDocumento().getEsenzioneTRepositoryDocumentale()
					.setFile(certificato.getFile_base64().getBytes());
			esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().iterator().next()
					.getEsenzioneTDocumento().getEsenzioneTRepositoryDocumentale()
					.setDatModifica(esenzioneTPraticaEsenzione.getDatModifica());
			esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().iterator().next()
					.getEsenzioneTDocumento().getEsenzioneTRepositoryDocumentale()
					.setDescFile(certificato.getTipologia_documento().getDescrizione());
			esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().iterator().next()
					.setDatModifica(esenzioneTPraticaEsenzione.getDatModifica());

			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneUpdated = dataDao
					.updateEsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzione);
			EsenzioneCittadinoEsenpat esenzioneTPraticaEsenzioneRettificata = new EsenzioneCittadinoEsenpat(
					esenzioneTPraticaEsenzioneUpdated);
			return esenzioneTPraticaEsenzioneRettificata;
		} else {
			throw new EsenpatException(Constants.MSG151, "Impossibile rettificare una pratica in questo stato",
					Status.CONFLICT.getStatusCode());
		}
	}

	@Transactional
	public EsenzioneTPraticaEsenzione insertNuovaPraticaEsenzione(NuovaEsenzione praticaEsenzione, UserInfo utente,
			Cittadino cittadino) throws EsenpatException, Exception {
		CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
		EsenzioneTPraticaEsenzione pratica = new EsenzioneTPraticaEsenzione();
		String codFiscale = cittadino.getCodFiscale();
		Documenti singleDoc = new Documenti();
		try
		{
			 singleDoc = praticaEsenzione.getDocumenti().get(0);
				if (!Checker.isValorizzato(singleDoc.getDocumentoId())) {

					if (Checker.isValorizzato(singleDoc.getFileBase64())
							&& Checker.isValorizzato(singleDoc.getDataRilascio())) {

						// controllo se e' valorizzato il codiceMalattia e il codiceTipologia
						if (!Checker.isValorizzato(praticaEsenzione.getMalattia().getCodEsenzione())
								|| !Checker.isValorizzato(praticaEsenzione.getMalattia().getCodDiagnosi())) {
							throw new EsenpatException(Constants.MSG145);
						} else {

							// inserimento certificati allegati nella T_Repo e T_Doc
							List<EsenzioneTDocumento> listaDocumenti;
							String codDiagnosi = praticaEsenzione.getMalattia().getCodDiagnosi();
							List<Documenti> certificati = praticaEsenzione.getDocumenti();
							listaDocumenti = certificatiFacade.caricaAllegati(certificati, cittadino, codDiagnosi);

							// inserimento nuova pratica esenzione
							pratica = insertPraticaEsenzione(praticaEsenzione, utente, cittadino);

							// collegamento tra le tabelle documento e la pratica
							for (EsenzioneTDocumento documento : listaDocumenti) {
								certificatiFacade.insertRPraticaDocumento(documento, pratica);
							}
							pratica = dataDao.loadFullEsenzioneTPraticaEsenzione(pratica.getSkPraticaEsenzione());

							return pratica;
						}
					}	
					else {
						throw new EsenpatException(Constants.MSG151);
					}
					
				} else {

					EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento = getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(singleDoc.getDocumentoId());
					if (esenzioneRPraticaDocumento != null) {
						throw new EsenpatException(Constants.MSG151);
					}

					// INSERIMENTO PRATICAESENZIONE CON CERTIFICATO_ID
					pratica = insertPraticaEsenzione(praticaEsenzione, utente, cittadino);
					EsenzioneTDocumento documento = certificatiFacade.getDocumentoBySkDocumento(singleDoc.getDocumentoId());
					certificatiFacade.insertRPraticaDocumento(documento, pratica);

					return pratica;
				}
		}
		catch(IndexOutOfBoundsException e){
			// inserimento nuova pratica esenzione senza documenti
			pratica = insertPraticaEsenzione(praticaEsenzione, utente, cittadino);
			pratica = dataDao.loadFullEsenzioneTPraticaEsenzione(pratica.getSkPraticaEsenzione());
			return pratica;			
		}


	}

	@Transactional
	public EsenzioneCittadinoEsenpat revocaEsenzione(String esenzioneId, MotivazioneRevocaEsenpat motivazioneRevoca,
			String citId, UserInfo utente, CittadinoEsenpat cittadino,
			List<Ruolo> ruoli) throws Exception, EsenpatException, NumberFormatException {
		EsenzioneTPraticaEsenzione etpe = loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId.toString()));
		if (etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_VALIDATA)) {
			copyInEsenzioneSPraticaEsenzione(etpe, true);
			etpe = revocaEsenzioneTPraticaEsenzione(etpe, null, citId, motivazioneRevoca);
			if (etpe == null) {
				throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
						Status.BAD_REQUEST.getStatusCode());
			} else {
				EsenzioneTDocumento attestato = dataDao.getDocumentoAttestatoEsenzioneByCodiceFiscale(citId);
				if (attestato != null) {
					aggiornaAttestato(etpe, attestato, utente, cittadino, ruoli);
				} else {
					throw new EsenpatException(Constants.MSG145, "attestato non trovato",
							Status.BAD_REQUEST.getStatusCode());
				}
				return new EsenzioneCittadinoEsenpat(etpe);
			}
		} else {
			throw new EsenpatException(Constants.MSG151, "Non e' possibile Revocare questa esenzione",
					Status.CONFLICT.getStatusCode());
		}
	}

	public EsenzioneTPraticaEsenzione rinnovoPraticaEsenzione(String shibIdentitaCodiceFiscale, String citId,
			Cittadino cittadino, Certificato certificato, EsenzioneTPraticaEsenzione praticaEsenzione)
			throws Exception {
		String cfDelegato = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? null : shibIdentitaCodiceFiscale;

		// Nuova Esenzione
		praticaEsenzione.setSkPraticaEsenzione(null);

		praticaEsenzione.setCodiceFiscaleBeneficiario(citId);
		praticaEsenzione.setCodiceFiscaleDelegato(cfDelegato);
		praticaEsenzione.setDatCreazione(new Timestamp(System.currentTimeMillis()));

		String numPratica = getNumeroProgressivoPraticaEsenzione(cittadino.getCodASL());
		praticaEsenzione.setNumPratica(Long.valueOf(numPratica));
		praticaEsenzione.setDescNotaBeneficiario(
				(certificato.getNote() == null || certificato.getNote().isEmpty()) ? null : certificato.getNote());
		praticaEsenzione.setEsenzioneDPraticaStato(new EsenzioneDPraticaStato(
				dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA).getSkPraticaStato()));


		praticaEsenzione.setCodRuoloOperatore(null);
		praticaEsenzione.setDatModifica(null);
		praticaEsenzione.setDatInizioValidita(null);
		praticaEsenzione.setDescNotaInterna(null);
		praticaEsenzione.setFlagDichiarazione(null);
		praticaEsenzione.setIdDichiarazione(null);
		praticaEsenzione.setDatCancellazione(null);
		praticaEsenzione.setDescNotaOperatore(null);
		praticaEsenzione.setDescNota(null);

		praticaEsenzione.setEsenzioneDMotivazioneTipo(null);
		praticaEsenzione.setIdUser(null);
		praticaEsenzione.setCodiceFiscaleOperatore(null);

		EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = dataDao.insertPraticaEsenzione(praticaEsenzione);

		return esenzioneTPraticaEsenzione;
	}

	public EsenzioneTPraticaEsenzione loadFullEsenzioneTPraticaEsenzione(Integer skPraticaEsenzione) throws Exception {
		return dataDao.loadFullEsenzioneTPraticaEsenzione(skPraticaEsenzione);
	}

	@Transactional()
	public EsenzioneCittadinoEsenpat annullaEsenzione(String esenzioneId,
			MotivazioneAnnullamento motivazioneAnnullamento, String citId, UserInfo utente, CittadinoEsenpat cittadino,
			List<Ruolo> ruoli) throws Exception, EsenpatException, NumberFormatException {
		EsenzioneTPraticaEsenzione etpe = loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId.toString()));
		if (etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)
				|| etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_LAVORAZIONE)
		) {
			copyInEsenzioneSPraticaEsenzione(etpe, true);
			etpe = annullaEsenzioneTPraticaEsenzione(etpe, null, citId, motivazioneAnnullamento);
			if (etpe == null) {
				throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
						Status.BAD_REQUEST.getStatusCode());
			} else {
				return new EsenzioneCittadinoEsenpat(etpe);
			}

		} else if (etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_VALIDATA)) {
			copyInEsenzioneSPraticaEsenzione(etpe, true);
			etpe = annullaEsenzioneTPraticaEsenzione(etpe, null, citId, motivazioneAnnullamento);
			if (etpe == null) {
				throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
						Status.BAD_REQUEST.getStatusCode());
			} else {
				EsenzioneTDocumento attestato = dataDao.getDocumentoAttestatoEsenzioneByCodiceFiscale(citId);
				if (attestato != null) {
					aggiornaAttestato(etpe, attestato, utente, cittadino, ruoli);
				} else {
					throw new EsenpatException(Constants.MSG145, "attestato non trovato",
							Status.BAD_REQUEST.getStatusCode());
				}
				return new EsenzioneCittadinoEsenpat(etpe);
			}
		} else {
			throw new EsenpatException(Constants.MSG151, "Non e' possibile Annullare questa esenzione",
					Status.CONFLICT.getStatusCode());
		}
	}

	public void aggiornaAttestato(EsenzioneTPraticaEsenzione pratica, EsenzioneTDocumento attestato, UserInfo utente,
			CittadinoEsenpat cittadino, List<Ruolo> ruoli) throws Exception {

		if (attestato != null) {
			// Copio nello storico
			EsenzioneSDocumento doc = new EsenzioneSDocumento(attestato);
			doc.setDatInizioValidita(pratica.getDatModifica() != null ? pratica.getDatModifica()
					: pratica.getDatCreazione() != null ? pratica.getDatCreazione() : null);
			doc.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
			dataDao.insertDocumentoStoricoPraticaEsenzione(doc);

			// Aggiorno il vecchio attestato
			attestato.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
			attestato.setDatModifica(new Timestamp(System.currentTimeMillis()));
			attestato.setDescNote(null);

			EsenzioneDDocumentoStato st = dataDao
					.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_ANNULLATO);
			attestato.setSkTipologiaStatoDocumento(st.getSkDocumentoStato().longValue());
			attestato = dataDao.updateEsenzioneTDocumento(attestato);
		}

		if (Checker.cittadinoAuraHasEsenzioni(cittadino)) {
			// Inserisco il nuovo attestato
			byte[][] XMLS = generateEsenzioneXmlCda(attestato, utente, cittadino);
			if(XMLS == null)
			{
				throw new Exception();
			}
			byte[] filePdf = Base64.getEncoder().encode(generatePdf(XMLS));
			
			EsenzioneTRepositoryDocumentale repoDocumentale = insertRepositoryDocumentale(utente, filePdf, ruoli);

			// Inserisco il nuovo documento di attestato
			EsenzioneTDocumento docCertificato = new EsenzioneTDocumento();
			docCertificato.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			docCertificato.setDatModifica(null);
			docCertificato.setDatCancellazione(null);
			EsenzioneDDurataTipo esenzioneduratatipo = dataDao
					.getEsenzioneDDurataTipoperSKDurataTipo(attestato.getEsenzioneDDiagnosi().getSkDurataTipo());
			Calendar cal = Calendar.getInstance();
			if (attestato.getEsenzioneDDiagnosi().getNumDurata() != null) {
				switch (esenzioneduratatipo.getCodDurataTipo().toUpperCase()) {
				case "G": {
					cal.add(Calendar.DATE, attestato.getEsenzioneDDiagnosi().getNumDurata());
					docCertificato.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
					break;
				}
				case "A": {
					cal.add(Calendar.YEAR, attestato.getEsenzioneDDiagnosi().getNumDurata());
					docCertificato.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
					break;
				}
				case "M": {
					cal.add(Calendar.MONTH, attestato.getEsenzioneDDiagnosi().getNumDurata());
					docCertificato.setDatFineValidita(new Timestamp(cal.getTimeInMillis()));
					break;
				}
				default:
					docCertificato.setDatFineValidita(null);
				}
			} else {
				docCertificato.setDatFineValidita(null);
			}
			docCertificato.setDescNote(null);
			docCertificato.setFlagConformitaDocumento(null);
			docCertificato.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			docCertificato.setIdUserid(new Long(utente.getIdAura()));
			docCertificato.setCodTipoUser("D");
			docCertificato.setDatDocumento(new Timestamp(System.currentTimeMillis()));
			docCertificato.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
			docCertificato.setSkRepository(repoDocumentale.getSkRepository().longValue());
			docCertificato.setSkTipoDocumento(attestato.getSkTipoDocumento());
			docCertificato.setDescEstesaPatologiaCertificato(null);
			docCertificato.setSkTipologiaStatoDocumento(new Long(dataDao
					.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_VALIDO).getSkDocumentoStato()));
			docCertificato.setSkDiagnosi(new Long(attestato.getEsenzioneDDiagnosi().getSkDiagnosi()));
			docCertificato.setCodiceFiscaleCittadino(attestato.getCodiceFiscaleCittadino());
			docCertificato.setOidDocumento(null);
			docCertificato.setIdAuraAttestato(null);
			EsenzioneTDocumento docNew = dataDao.insertEsenzioneTDocumento(docCertificato);

			// Inserisco i metadati
			insertMetadatiDocumento(docNew, utente, cittadino, ruoli);

			// Inserisco la relazione pratica-documento
			EsenzioneRPraticaEsenzioneDocumento rel = new EsenzioneRPraticaEsenzioneDocumento();
			EsenzioneRPraticaEsenzioneDocumentoPK relPk = new EsenzioneRPraticaEsenzioneDocumentoPK();

			rel.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			rel.setDatModifica(null);
			rel.setDatCancellazione(null);
			rel.setIdUser(null);
			rel.setCodRuoloOperatore(null);
			rel.setDatFineValidita(docNew.getDatFineValidita());
			relPk.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
			relPk.setSkDocumento(docNew.getSkDocumento().longValue());
			relPk.setSkPraticaEsenzione(pratica.getSkPraticaEsenzione().longValue());
			rel.setId(relPk);

			dataDao.insertEsenzioneRPraticaDocumento(rel);
		}
	}

	public File createPdfSingolaEsenzione(EsenzioneTPraticaEsenzione praticaEsenzione, Cittadino cittadinoBen,
			Cittadino cittadinoDel, String document_type, EsenzioneAssistito esenzione) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String template = null;
		if (praticaEsenzione == null) {
			if (Constants.TEMPLATE_3.contains(document_type)) {
				template = "Template3";
				parameters.put("NUM_PRATICA", "");

				parameters.put("BEN_NOME", cittadinoBen.getNome());
				parameters.put("BEN_COGNOME", cittadinoBen.getCognome());
				parameters.put("BEN_CF", cittadinoBen.getCodFiscale());
				parameters.put("DATA_NASCITA", cittadinoBen.getDataNascita());
				parameters.put("COMUNE_RES", cittadinoBen.getCittaResidenza());
				parameters.put("INDIRIZZO_RES", cittadinoBen.getIndirizzoResidenza());
				parameters.put("TESSERA_REGIONALE",
						cittadinoBen.getCodiceTesseraRegionale() != null ? cittadinoBen.getCodiceTesseraRegionale()
								: "");

				parameters.put("DATA_INIZIO_VALIDITA",
						esenzione.getValidaDal() != null ? esenzione.getValidaDal() : "");
				parameters.put("DATA_FINE_VALIDITA",
						esenzione.getDataScadenza() != null ? esenzione.getDataScadenza() : "Senza scadenza");
				parameters.put("ID_AZIENDA",
						esenzione.getAsl() != null
								? dataDao.getAziendaSanitariaByCodAsl(esenzione.getAsl()).getDenominazione()
								: "");

				parameters.put("COD_ESENZIONE",
						esenzione.getCodiceEsenzione() != null ? esenzione.getCodiceEsenzione() : "");
				String prest = "";
				if (esenzione.getCoddiagnosi() != null) {
					List<EsenzioneDPrestazioneSpecialistica> prestazione = dataDao
							.getPrestazioniSpecialisticheByCodDiagnosi(esenzione.getCoddiagnosi());
					if (prestazione != null && prestazione.size() > 0) {
						prest = prestazione.get(0).getDescPrestazione().trim();
					}
				}
				parameters.put("PRESTAZIONE", prest);

			} else {
				throw new Exception();
			}
		} else {
		if (Constants.TEMPLATE_1.contains(document_type)) {
			template = "Template1";
			parameters.put("DOC_TYPE", Constants.DOC_TEMPLATES.get(document_type));
			parameters.put("NUM_PRATICA", praticaEsenzione.getNumPratica().toString());

			parameters.put("BEN_NOME", cittadinoBen.getNome());
			parameters.put("BEN_COGNOME", cittadinoBen.getCognome());
			parameters.put("BEN_CF", cittadinoBen.getCodFiscale());

			parameters.put("DEL_NOME", cittadinoDel == null ? "" : cittadinoDel.getNome());
			parameters.put("DEL_COGNOME", cittadinoDel == null ? "" : cittadinoDel.getCognome());
			parameters.put("DEL_CF", cittadinoDel == null ? "" : cittadinoDel.getCodFiscale());

			parameters.put("DATA_COMP",
					praticaEsenzione.getDatModifica() == null
							? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatCreazione())
							: new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatModifica()));
			parameters.put("COD_RUOLO_OPERATORE",
					praticaEsenzione.getCodRuoloOperatore() == null ? "" : praticaEsenzione.getCodRuoloOperatore());

		} else if (Constants.TEMPLATE_2.contains(document_type)) {
			template = "Template2";
			parameters.put("NUM_PRATICA", praticaEsenzione.getNumPratica().toString());

			parameters.put("BEN_NOME", cittadinoBen.getNome());
			parameters.put("BEN_COGNOME", cittadinoBen.getCognome());
			parameters.put("BEN_CF", cittadinoBen.getCodFiscale());

			parameters.put("DEL_NOME", cittadinoDel == null ? "" : cittadinoDel.getNome());
			parameters.put("DEL_COGNOME", cittadinoDel == null ? "" : cittadinoDel.getCognome());
			parameters.put("DEL_CF", cittadinoDel == null ? "" : cittadinoDel.getCodFiscale());

			parameters.put("DATA_COMP",
					praticaEsenzione.getDatModifica() == null
							? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatCreazione())
							: new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatModifica()));
			parameters.put("COD_RUOLO_OPERATORE",
					praticaEsenzione.getCodRuoloOperatore() == null ? "" : praticaEsenzione.getCodRuoloOperatore());

			parameters.put("MOTIVAZIONE",
					(praticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase("ANN")
							|| praticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase("REV"))
									? praticaEsenzione.getEsenzioneDMotivazioneTipo().getDescMotivazione()
									: null);

		} else if (Constants.TEMPLATE_3.contains(document_type)) {
			template = "Template3";
			parameters.put("NUM_PRATICA", praticaEsenzione.getNumPratica().toString());

			parameters.put("BEN_NOME", cittadinoBen.getNome());
			parameters.put("BEN_COGNOME", cittadinoBen.getCognome());
			parameters.put("BEN_CF", cittadinoBen.getCodFiscale());
			parameters.put("DATA_NASCITA", cittadinoBen.getDataNascita());
			parameters.put("COMUNE_RES", cittadinoBen.getCittaResidenza());
			parameters.put("INDIRIZZO_RES", cittadinoBen.getIndirizzoResidenza());
				parameters.put("TESSERA_REGIONALE",
						cittadinoBen.getCodiceTesseraRegionale() != null ? cittadinoBen.getCodiceTesseraRegionale()
								: "");

			parameters.put("DATA_INIZIO_VALIDITA",
					praticaEsenzione.getDatInizioValidita() != null
							? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatInizioValidita())
							: "");
			parameters.put("DATA_FINE_VALIDITA",
					praticaEsenzione.getDatFineValidita() != null
							? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatFineValidita())
							: "Senza scadenza");
			parameters.put("ID_AZIENDA",
					praticaEsenzione.getEsenredDAziendasanitaria() != null
							? praticaEsenzione.getEsenredDAziendasanitaria().getDenominazione()
							: "");

			if (praticaEsenzione.getEsenzioneDDiagnosi() != null) {
				parameters.put("COD_ESENZIONE",
						praticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
				parameters.put("PRESTAZIONE",
						praticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneRDiagnosiPrestaziones().iterator().next()
									.getEsenzioneDPrestazioneSpecialistica().getDescPrestazione().trim());
			}
		}
		}
		byte[] byteArray = dataDao.getPdfEsenzione(parameters, template);
		if (byteArray.length < 1000) // NO DATA
			throw new Exception();

		File pdf = null;
		String fileName = "";
		if (praticaEsenzione != null) {
			fileName = StringUtils.capitalize(Constants.DOC_TEMPLATES.get(document_type).replace(" ", "_").toLowerCase()
					+ "_" + praticaEsenzione.getNumPratica().toString());
		} else {
			fileName = StringUtils.capitalize(Constants.DOC_TEMPLATES.get(document_type).replace(" ", "_").toLowerCase()
					+ "_" + esenzione.getCodiceEsenzione());
		}
		pdf = File.createTempFile(fileName, ".pdf");
		FileUtils.writeByteArrayToFile(pdf, byteArray);
		pdf.deleteOnExit();
		return pdf;
	}

	/*
	 * Ricerca esenzione per codice
	 */
	public EsenzioneDEsenzione getEsenzioneDEsenzione(String codEsenzione) throws Exception {
		return dataDao.getEsenzioneDEsenzione(codEsenzione);
	}

	/*
	 * Ricerca ultima pratica esenzione valida per beneficiario e codice esenzione
	 */
	public EsenzioneTPraticaEsenzione getPraticaEsenzioneValidaByEsenzione(String cf, EsenzioneDEsenzione esenzione,
			String codStato) throws Exception {
		List<EsenzioneDDiagnosi> elencoDiagnosi = dataDao.getElencoEsenzioneDDiagnosiByEsenzione(esenzione);

		Integer[] elencoSkDiagnosi = new Integer[elencoDiagnosi.size()];
		int i = 0;
		for (Iterator<EsenzioneDDiagnosi> iterator = elencoDiagnosi.iterator(); iterator.hasNext();) {
			EsenzioneDDiagnosi diagnosi = (EsenzioneDDiagnosi) iterator.next();
			elencoSkDiagnosi[i] = diagnosi.getSkDiagnosi();
			i++;
		}

		List<EsenzioneTPraticaEsenzione> listaPraticheValide = dataDao.getElencoPraticheEsenzioneValideByDiagnosi(cf,
				elencoSkDiagnosi, codStato);
		if (listaPraticheValide.size() > 1) {
			Collections.sort(listaPraticheValide, new Comparator<EsenzioneTPraticaEsenzione>() {
				public int compare(EsenzioneTPraticaEsenzione pratica1, EsenzioneTPraticaEsenzione pratica2) {
					if (pratica1.getDatCreazione().before(pratica2.getDatCreazione())) {
						return 1;
					} else if (pratica1.getDatCreazione().after(pratica2.getDatCreazione())) {
						return -1;
					} else {
						return 0;
					}
				}
			});
		} else
			return null;
		return listaPraticheValide.get(0);
	}

	@Transactional()
	public EsenzioneTDocumento setValidazioneAttestatoEsenzione(EsenzioneTDocumento attestatoDaValidare,
			String codAttestatoEsenzione) throws Exception {
		attestatoDaValidare.setIdAuraAttestato(codAttestatoEsenzione);
		attestatoDaValidare.setSkTipologiaStatoDocumento(Long.valueOf(Constants.STATO_DOCUMENTO_VALIDO));
		EsenzioneTDocumento attestatoValidato = dataDao.updateEsenzioneTDocumento(attestatoDaValidare);
		return attestatoValidato;
	}

	public Set<EsenzioneAssistito> getListaEsenzioniByCodiceFiscaleAssistito(CittadinoEsenpat cit, UserInfo utente, String cfMed) {
		Set<EsenzioneAssistito> listaEsenzioniAssistito = new TreeSet<EsenzioneAssistito>();

		List<EsenzioneTPraticaEsenzione> listaEsenzioni = dataDao.getListaEsenzioniByCodiceFiscale(cit.getCodFiscale());
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			for (EsenzioneTPraticaEsenzione ese : listaEsenzioni) {
				listaEsenzioniAssistito.add(new EsenzioneAssistito(ese, cit, utente, cfMed));
			}
		}
		if (cit.getEsenzioniAura() != null && cit.getEsenzioniAura().getInfoesenzione() != null
				&& cit.getEsenzioniAura().getInfoesenzione().length > 0) {
			for (InfoEsenzioneNew e : cit.getEsenzioniAura().getInfoesenzione()) {
				EsenzioneAssistito esenzioneAssistito = new EsenzioneAssistito(e, cit, utente);
				esenzioneAssistito.setVisualizza(!Checker.isValorizzato(cfMed));
				listaEsenzioniAssistito.add(esenzioneAssistito);
				
			}
		}
		return listaEsenzioniAssistito;
	}

	public Set<EsenzioneAssistito> getListaEsenzioniPatologia(CittadinoEsenpat cit, UserInfo utente,
			FiltriRicercaCertificatoPatologia filtri, String codAsl) {
		Set<EsenzioneAssistito> listaEsenzioniAssistito = new HashSet<EsenzioneAssistito>();

		List<EsenzioneTPraticaEsenzione> listaEsenzioni = dataDao.getEsenzionePatologia(filtri, codAsl, null);
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			for (EsenzioneTPraticaEsenzione ese : listaEsenzioni) {
				listaEsenzioniAssistito.add(new EsenzioneAssistito(ese, cit, utente, null));
			}
		}
		if (cit.getEsenzioniAura() != null && cit.getEsenzioniAura().getInfoesenzione() != null
				&& cit.getEsenzioniAura().getInfoesenzione().length > 0) {
			for (InfoEsenzioneNew e : cit.getEsenzioniAura().getInfoesenzione()) {
				listaEsenzioniAssistito.add(new EsenzioneAssistito(e, cit, utente));
			}
		}
		return listaEsenzioniAssistito;
	}

	public Set<DiagnosiGruppo> getListaDiagnosiByGruppoEsenzione(String codGruppo) {
		Set<DiagnosiGruppo> listaDiagnosiGruppo = null;

		EsenzioneDGruppoEsenzioni gruppoEsenzione = dataDao.getGruppoEsenzioneByCodice(codGruppo);

		List<EsenzioneDDiagnosi> listaDiagnosi = dataDao.getListaDiagnosiByCodiceGruppoEsenzione(gruppoEsenzione);
		if (listaDiagnosi != null && listaDiagnosi.size() > 0) {
			listaDiagnosiGruppo = new HashSet<DiagnosiGruppo>();
			for (EsenzioneDDiagnosi edd : listaDiagnosi) {
				listaDiagnosiGruppo.add(new DiagnosiGruppo(edd, codGruppo));
			}
		}
		return listaDiagnosiGruppo;
	}

	public Set<EsenzioneGruppo> getListaEsenzioniByGruppoEsenzione(String codGruppo) {
		Set<EsenzioneGruppo> listaEsenzioniGruppo = null;

		EsenzioneDGruppoEsenzioni gruppoEsenzione = dataDao.getGruppoEsenzioneByCodice(codGruppo);

		List<EsenzioneDEsenzione> listaEsenzioni = dataDao.getListaEsenzioniByGruppoEsenzione(gruppoEsenzione);
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			listaEsenzioniGruppo = new HashSet<EsenzioneGruppo>();
			for (EsenzioneDEsenzione ede : listaEsenzioni) {
				listaEsenzioniGruppo.add(new EsenzioneGruppo(ede, codGruppo));
			}
		}
		return listaEsenzioniGruppo;
	}

	public Set<Esenzione> getEsenzioniDiagnosi(String gruppo) {
		Set<Esenzione> listaEsenzioniGruppo = null;

		EsenzioneDGruppoEsenzioni gruppoEsenzione = dataDao.getGruppoEsenzioneByCodice(gruppo);

		List<EsenzioneDEsenzione> listaEsenzioni = dataDao.getListaEsenzioniByGruppoEsenzione(gruppoEsenzione);
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			listaEsenzioniGruppo = new HashSet<Esenzione>();
			for (EsenzioneDEsenzione ede : listaEsenzioni) {
				listaEsenzioniGruppo.add(new Esenzione(ede));
			}
		}
		return listaEsenzioniGruppo;
	}

	public Set<Esenzione> getListaPatologie(String codGruppo) {
		Set<Esenzione> listaPatoGruppo = null;

		EsenzioneDGruppoEsenzioni gruppoEsenzione = dataDao.getGruppoEsenzioneByCodice(codGruppo);

		List<EsenzioneDEsenzione> listaPato = dataDao.getListaPatologieByGruppoEsenzione(gruppoEsenzione);
		if (listaPato != null && listaPato.size() > 0) {
			listaPatoGruppo = new HashSet<Esenzione>();
			for (EsenzioneDEsenzione edd : listaPato) {
				listaPatoGruppo.add(new Esenzione(edd));
			}
		}
		return listaPatoGruppo;
	}

	public List<ListaEsenzioni> getListaEsenzioni() {
		List<ListaEsenzioni> listaEsenzioniGruppo = null;

		List<EsenzioneDEsenzione> listaEsenzioni = dataDao.getListaEsenzioni();
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			listaEsenzioniGruppo = new ArrayList<ListaEsenzioni>(listaEsenzioni.size());
			for (EsenzioneDEsenzione ede : listaEsenzioni) {
				listaEsenzioniGruppo.add(new ListaEsenzioni(ede));
			}
		}
		return listaEsenzioniGruppo;
	}

	public Set<ListaDiagnosi> getListaDiagnosi() {
		Set<ListaDiagnosi> listaDiagnosiGruppo = null;

		List<EsenzioneDDiagnosi> listaDiagnosi = dataDao.getListaDiagnosi();
		if (listaDiagnosi != null && listaDiagnosi.size() > 0) {
			listaDiagnosiGruppo = new HashSet<ListaDiagnosi>();
			for (EsenzioneDDiagnosi edd : listaDiagnosi) {
				listaDiagnosiGruppo.add(new ListaDiagnosi(edd));
			}
		}
		return listaDiagnosiGruppo;
	}

	public EsenzioneCittadinoEsenpat getDettaglioEsenzione(CittadinoEsenpat assis, FiltroDettaglioEsenzione filter,
			UserInfo utente) throws Exception {
		List<EsenzioneCittadinoEsenpat> esenzioni = new ArrayList<EsenzioneCittadinoEsenpat>();
		EsenzioneCittadinoEsenpat dettaglioEsenzione = null;

		if (Checker.isValorizzato(filter.getSkEsenzione())) {
			// Pratica Esenpat
			esenzioni = getListaEsenzioni(filter.getCfBeneficiario(), filter.getSkEsenzione(), null,
					utente.getCodFisc());
			dettaglioEsenzione = esenzioni.get(0);

		} else if (Checker.isValorizzato(filter.getCfBeneficiario()) && Checker.isValorizzato(filter.getCodEsenzione())
				&& Checker.isValorizzato(filter.getDataEmissione())) {
			// Pratica AURA
			ArrayOfinfoesenzioneInfoEsenzioneNew esenzioniAura = assis.getEsenzioniAura();
			Beneficiario ben = new Beneficiario(assis);
			Date dataFiltro = Converter.getData(filter.getDataEmissione());
			for (InfoEsenzioneNew esen : esenzioniAura.getInfoesenzione()) {
				String dataConvertita = DateUtil.formatDate(esen.getDataEmissione().getTime(),
						Constants.DATE_FORMAT_ITALIAN);
				Date dataEsenzioneAura = Converter.getData(dataConvertita);
				if (esen.getCodEsenzione().equalsIgnoreCase(filter.getCodEsenzione())
						&& assis.getCodFiscale().equalsIgnoreCase(filter.getCfBeneficiario())
						&& dataFiltro.compareTo(dataEsenzioneAura) == 0) {
					EsenzioneDGruppoEsenzioni gruppo = getGruppoEsenzioneByCodEsenzione(esen.getCodEsenzione());
					List<EsenzioneDPrestazioneSpecialistica> prestazioni = getPrestazioniSpecialisticheByCodDiagnosi(
							esen.getCodDiagnosi().getCodDiagnosi_type0());
					dettaglioEsenzione = new EsenzioneCittadinoEsenpat(esen, ben, gruppo, prestazioni);
					return dettaglioEsenzione;
				}
			}

		} else
			throw new CheckException(Constants.MSG145);
		dettaglioEsenzione.getBeneficiario().setDataNascita(assis.getDataNascita());
		dettaglioEsenzione.getBeneficiario().setComuneNascita(assis.getComuneNascita());
		dettaglioEsenzione.getBeneficiario().setSesso(
				assis.getCodSesso() != null && assis.getCodSesso().equalsIgnoreCase("M") ? "Maschio" : "Femmina");
		return dettaglioEsenzione;
	}

	public EsenzioneDGruppoEsenzioni getGruppoEsenzioneByCodEsenzione(String codEsenzione) {
		return dataDao.getGruppoEsenzioneByCodiceEsenzione(codEsenzione);
	}

	public List<EsenzioneDPrestazioneSpecialistica> getPrestazioniSpecialisticheByCodDiagnosi(String codDiagnosi) {
		return dataDao.getPrestazioniSpecialisticheByCodDiagnosi(codDiagnosi);
	}

	public boolean isCittadinoPiemontese(Cittadino cittadino) {
		comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
		List<EsenredCComuni> elencoComuni = comuneIf.getElencoComuni(cittadino.getCittaResidenza());
		if (elencoComuni.isEmpty()) {
			return false;
		} else {
			return elencoComuni.get(0).getDenominazioneRegione().equals("PIEMONTE");
		}
	}

	public List<Prestazioni> getPrestazioniEsenti(String codDiagnosi) {
		List<Prestazioni> prestazioni = new ArrayList<Prestazioni>();
		List<EsenzioneDPrestazioneSpecialistica> prest = getPrestazioniSpecialisticheByCodDiagnosi(codDiagnosi);
		for (EsenzioneDPrestazioneSpecialistica p : prest) {
			Prestazioni pr = new Prestazioni();
			pr.setCodice(p.getCodPrestazione());
			pr.setDescrizione(p.getDescPrestazione());
			prestazioni.add(pr);
		}
		return prestazioni;
	}

	@SuppressWarnings("unused")
	@Transactional

	public void approvaEsenzione(HttpServletRequest req, UserInfo utente,
			FiltriValidaEsenzionePatologia bo,List<Ruolo> ruoli) throws Exception {
		EsenzioneTPraticaEsenzione etpe = dataDao
				.loadFullEsenzioneTPraticaEsenzione(Integer.decode(bo.getSkPraticaEsenzione()));
		if (etpe == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}

		if (!etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_LAVORAZIONE)
				&& !etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)) {
			MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
			throw new EsenpatException(Constants.MSG151, message, Status.CONFLICT.getStatusCode());
		}

		copyInEsenzioneSPraticaEsenzione(etpe,
				utente.getCodFisc().equalsIgnoreCase(bo.getBeneficiario().getCodiceFiscale()));

		

		etpe.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		etpe.setDescNotaInterna(bo.getNotainterna());
		etpe.setDescNotaBeneficiario(bo.getNotabeneficiario());
		// etpe.setDescNotaOperatore(notaOperatore);
		etpe.setDescNota(bo.getNota());
		EsenzioneDPraticaStato esestato = dataDao
				.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_DA_VALIDARE);
		etpe.setSkTipologiaStatoPratica(new Long(esestato.getSkPraticaStato()));
		etpe.setIdUser(new Long(utente.getIdAura()));
		etpe.setCodiceFiscaleOperatore(utente.getCodFisc());

		dataDao.insertEsenzioneTPraticaEsenzione(etpe);

		if (etpe == null) {
			throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
					Status.BAD_REQUEST.getStatusCode());
		} else {
			return;
		}
	}

	@SuppressWarnings("unused")
	@Transactional
	public void salvaInBozza(HttpServletRequest req, UserInfo utente, String cit_id,
			String SkPraticaEsenzione, String notaInterna, String notaBeneficiario, String notaOperatore, String note,List<Ruolo> ruoli)
			throws Exception {
		EsenzioneTPraticaEsenzione etpe = dataDao
				.loadFullEsenzioneTPraticaEsenzione(Integer.decode(SkPraticaEsenzione));
		if (etpe == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}

		if (!etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)
				&& !etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
			MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
			throw new EsenpatException(Constants.MSG151, message, Status.CONFLICT.getStatusCode());
		}

		copyInEsenzioneSPraticaEsenzione(etpe, utente.getCodFisc().equalsIgnoreCase(cit_id));

		etpe.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		etpe.setDescNotaInterna(notaInterna);
		etpe.setDescNotaBeneficiario(notaBeneficiario);
		etpe.setDescNotaOperatore(notaOperatore);
		etpe.setDescNota(note);
		etpe.setEsenzioneDPraticaStato(
				dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_IN_LAVORAZIONE));
		// etpe.setEsenzioneDMotivazioneTipo(dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(codMotivazione));
		etpe.setIdUser(new Long(utente.getIdAura()));
		etpe.setCodiceFiscaleOperatore(utente.getCodFisc());
		etpe.setSkTipologiaStatoPratica(new Long(etpe.getEsenzioneDPraticaStato().getSkPraticaStato()));

		dataDao.insertEsenzioneTPraticaEsenzione(etpe);

		if (etpe == null) {
			throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
					Status.BAD_REQUEST.getStatusCode());
		} else {
			return;
		}
	}

	@Transactional
	public EsenzioneTPraticaEsenzione rinnovaEsenzione(HttpServletRequest req, CittadinoEsenpat ben, UserInfo utente,
			FiltriValidaEsenzionePatologia bo, String statoPratica,List<Ruolo> ruoli) throws Exception {
		EsenzioneTPraticaEsenzione etpeCorrente = dataDao
				.loadFullEsenzioneTPraticaEsenzione(Integer.decode(bo.getSkPraticaEsenzione()));
		if (etpeCorrente == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}

		if (!etpeCorrente.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_SCADENZA)
				&& !etpeCorrente.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_SCADUTA)) {
			MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
			throw new EsenpatException(Constants.MSG151, message, Status.CONFLICT.getStatusCode());
		}

		copyInEsenzioneSPraticaEsenzione(etpeCorrente, utente.getCodFisc().equalsIgnoreCase(etpeCorrente.getEsenzioneTCittadino1().getCodiceFiscale()));


		if (statoPratica.equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA)
				&& etpeCorrente.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_SCADENZA)) {
			etpeCorrente.setEsenzioneDPraticaStato(
					dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_SCADUTA));
			etpeCorrente = dataDao.insertEsenzioneTPraticaEsenzione(etpeCorrente);

			if (etpeCorrente == null) {
				throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
						Status.BAD_REQUEST.getStatusCode());
			}
		}

	
		EsenzioneTPraticaEsenzione esenzioneRinnovo = new EsenzioneTPraticaEsenzione();

		esenzioneRinnovo.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());

		esenzioneRinnovo.setDatModifica(null);
		esenzioneRinnovo.setDatCreazione(new Timestamp(System.currentTimeMillis()));

		if (statoPratica.equals(Constants.STATO_PRATICA_VALIDATA)) {
			esenzioneRinnovo.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));

			EsenzioneDDurataTipo esenzioneduratatipo = dataDao
					.getEsenzioneDDurataTipoperSKDurataTipo(etpeCorrente.getEsenzioneDDiagnosi().getSkDurataTipo());
			impostaDataFineValidita(esenzioneRinnovo, esenzioneduratatipo, etpeCorrente.getEsenzioneDDiagnosi());
		}

		esenzioneRinnovo.setDescNotaInterna(bo.getNotainterna());
		
		String numPratica = getNumeroProgressivoPraticaEsenzione(etpeCorrente.getEsenzioneTCittadino1().getIdAzienda());
		esenzioneRinnovo.setNumPratica(Long.valueOf(numPratica));

		esenzioneRinnovo.setFlagDichiarazione(null);
		esenzioneRinnovo.setIdDichiarazione(null);
		esenzioneRinnovo.setCodiceFiscaleBeneficiario(etpeCorrente.getEsenzioneTCittadino1().getCodiceFiscale());
		esenzioneRinnovo.setCodiceFiscaleDelegato(null);
		esenzioneRinnovo.setDatCancellazione(null);

		esenzioneRinnovo.setDescNotaBeneficiario(bo.getNotabeneficiario());
		esenzioneRinnovo.setDescNotaOperatore(null);
		esenzioneRinnovo.setDescNota(bo.getNota());

		esenzioneRinnovo.setEsenzioneDMotivazioneTipo(null);

		EsenzioneDPraticaStato esenzioneDPraticaStato = new EsenzioneDPraticaStato(
				dataDao.getEsenzioneDPraticaStatoPerCodStato(statoPratica).getSkPraticaStato());
		esenzioneRinnovo.setEsenzioneDPraticaStato(esenzioneDPraticaStato);
		esenzioneRinnovo.setSkTipologiaStatoPratica(new Long(esenzioneDPraticaStato.getSkPraticaStato()));

		esenzioneRinnovo.setIdAzienda(etpeCorrente.getEsenzioneTCittadino1().getIdAzienda());
		esenzioneRinnovo.setEsenzioneDDistrettoSocioSanitario(null);


		esenzioneRinnovo.setSkGruppo(etpeCorrente.getSkGruppo());


		String codDiagnosi = etpeCorrente.getEsenzioneDDiagnosi().getCodDiagnosi();
		EsenzioneDDiagnosi esenzioneDDiagnosi = dataDao.getEsenzioneDDiagnosi(codDiagnosi);
		esenzioneRinnovo.setEsenzioneDDiagnosi(esenzioneDDiagnosi);


		esenzioneRinnovo.setSkDiagnosi(etpeCorrente.getSkDiagnosi());


		esenzioneRinnovo.setEsenzioneDInvaliditaTipo(etpeCorrente.getEsenzioneDInvaliditaTipo());


		esenzioneRinnovo.setIdUser(new Long(utente.getIdAura()));
		esenzioneRinnovo.setCodiceFiscaleOperatore(utente.getCodFisc());

		esenzioneRinnovo = dataDao.insertEsenzioneTPraticaEsenzione(esenzioneRinnovo);

		if (esenzioneRinnovo == null) {
			throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
					Status.BAD_REQUEST.getStatusCode());
		}

		//carico i documenti in tutti i casi
		CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
		List<EsenzioneTDocumento> listaDocumenti;
		List<Documenti> certificati = new ArrayList<Documenti>(); 
		for ( Documenti documento : bo.getDocumentiallegati()) {
			certificati.add(documento);
		}
		
		Cittadino cittadino = new Cittadino();
		cittadino.setIdAura(etpeCorrente.getEsenzioneTCittadino1().getId_aura());
		cittadino.setCognome(etpeCorrente.getEsenzioneTCittadino1().getCognome());
		cittadino.setNome(etpeCorrente.getEsenzioneTCittadino1().getNome());
		cittadino.setCodFiscale(etpeCorrente.getEsenzioneTCittadino1().getCodiceFiscale());
		listaDocumenti = certificatiFacade.caricaAllegati(certificati, cittadino, codDiagnosi);

		// aggiungo relazioni pratica-allegati
		if (listaDocumenti != null) {
			for (EsenzioneTDocumento doc : listaDocumenti) {
				EsenzioneRPraticaEsenzioneDocumento rel = new EsenzioneRPraticaEsenzioneDocumento();
				EsenzioneRPraticaEsenzioneDocumentoPK relPk = new EsenzioneRPraticaEsenzioneDocumentoPK();

				rel.setDatCreazione(new Timestamp(System.currentTimeMillis()));
				rel.setDatModifica(null);
				rel.setDatCancellazione(null);
				rel.setIdUser(null);
				rel.setCodRuoloOperatore(null);
				rel.setDatFineValidita(doc.getDatFineValidita());
				relPk.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
				relPk.setSkDocumento(doc.getSkDocumento().longValue());
				relPk.setSkPraticaEsenzione(esenzioneRinnovo.getSkPraticaEsenzione().longValue());
				rel.setId(relPk);

				dataDao.insertEsenzioneRPraticaDocumento(rel);
			}
		}

		if (statoPratica.equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA)) {
			// Aggiorno Attestato
			EsenzioneTDocumento attestato = dataDao
					.getDocumentoAttestatoEsenzioneByCodiceFiscale(bo.getBeneficiario().getCodiceFiscale());
			aggiornaAttestato(esenzioneRinnovo, attestato, utente, ben, ruoli);

		}
		return esenzioneRinnovo;
	}
	
	@SuppressWarnings("unused")
	@Transactional
	public void respingiEsenzione(HttpServletRequest req, UserInfo utente, MotivazioneAnnullamento bo, String esenzioneId, String cit_id, List<Ruolo> ruoli)
			throws Exception
	{
		EsenzioneTPraticaEsenzione etpe = dataDao.loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId));
		if (etpe == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}

		copyInEsenzioneSPraticaEsenzione(etpe, utente.getCodFisc().equalsIgnoreCase(cit_id));

		etpe.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		etpe.setDescNotaInterna(bo.getNotaInterna());
		etpe.setDescNotaBeneficiario(bo.getNotaBeneficiario());
		// etpe.setDescNotaOperatore(notaOperatore);
		etpe.setDescNota(bo.getNotaServizio());

		EsenzioneDPraticaStato esestato = dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_RESPINTA);
		etpe.setSkTipologiaStatoPratica(new Long(esestato.getSkPraticaStato()));
		
		EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo = dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(bo.getMotivazione().getCodice());
		etpe.setEsenzioneDMotivazioneTipo(esenzioneDMotivazioneTipo);
		
		etpe.setIdUser(new Long(utente.getIdAura()));
		etpe.setCodiceFiscaleOperatore(utente.getCodFisc());

		dataDao.insertEsenzioneTPraticaEsenzione(etpe);

		if (etpe == null) {
			throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB", Status.BAD_REQUEST.getStatusCode());
		} else {
			return;
		}
	}
	@SuppressWarnings("unused")
	@Transactional
	public void richiestaRettificaDati(HttpServletRequest req, UserInfo utente, RichiestaRettificaDatiBO bo, String statoPratica,Ruolo ruolo)
			throws Exception
	{
		EsenzioneTPraticaEsenzione etpe = dataDao.loadFullEsenzioneTPraticaEsenzione(Integer.decode(bo.getSkPraticaEsenzione()));
		if (etpe == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}
		
		if (!etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)
				&& !etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_IN_LAVORAZIONE)) {
			MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
			throw new EsenpatException(Constants.MSG151, message, Status.CONFLICT.getStatusCode());
		}

		copyInEsenzioneSPraticaEsenzione(etpe, utente.getCodFisc().equalsIgnoreCase(bo.getBeneficiario().getCodiceFiscale()));

		etpe.setCodRuoloOperatore(ruolo.getCodiceRuolo());
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		etpe.setDescNotaInterna(bo.getNotainterna());
		etpe.setDescNotaBeneficiario(bo.getNotabeneficiario());
		// etpe.setDescNotaOperatore(notaOperatore);
		etpe.setDescNota(bo.getNota());

		EsenzioneDPraticaStato esestato = dataDao.getEsenzioneDPraticaStatoPerCodStato(statoPratica);
		etpe.setSkTipologiaStatoPratica(new Long(esestato.getSkPraticaStato()));
		
		EsenzioneDMotivazioneTipo esenzioneDMotivazioneTipo = dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(bo.getCodiceMotivazioneTipo());
		etpe.setEsenzioneDMotivazioneTipo(esenzioneDMotivazioneTipo);
		
		etpe.setIdUser(new Long(utente.getIdAura()));
		etpe.setCodiceFiscaleOperatore(utente.getCodFisc());

		dataDao.insertEsenzioneTPraticaEsenzione(etpe);

		if (etpe == null) {
			throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB", Status.BAD_REQUEST.getStatusCode());
		} else {
			return;
		}
	}
	
	
	@Transactional
	public void rettificaEsenzione(HttpServletRequest req, UserInfo utente, RichiestaRettificaDatiBO bo,Ruolo ruolo)
			throws Exception
	{
		EsenzioneTPraticaEsenzione etpe = dataDao.loadFullEsenzioneTPraticaEsenzione(Integer.decode(bo.getSkPraticaEsenzione()));
		if (etpe == null) {
			throw new EsenpatException(Constants.MSG151, "Pratica non trovata", Status.NOT_FOUND.getStatusCode());
		}

		if (!etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA_DAL_MEDICO)) {
			MessaggioIf messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
			String message = Util.composeMessage(messaggioIf.getMessaggio("MSG151"), ". Stato pratica non coerente.");
			throw new EsenpatException(Constants.MSG151, message, Status.CONFLICT.getStatusCode());
		}

		EsenzioneTDocumento etd = dataDao.getDettaglioCertificato(bo.getBeneficiario().getCodiceFiscale(),
				bo.getSkDocumento());
		if (etd == null) {
			throw new EsenpatException(Constants.MSG233, "Certificato non trovato", Status.NOT_FOUND.getStatusCode());
		}
		if (!etd.getEsenzioneDDocumentoStato().getCodStato().equalsIgnoreCase(Constants.STATO_DOCUMENTO_VALIDO)) {
			throw new EsenpatException(Constants.MSG234, "Stato del certificato non valido",
					Status.NOT_FOUND.getStatusCode());
		}

		copyInEsenzioneSPraticaEsenzione(etpe, utente.getCodFisc().equalsIgnoreCase(bo.getBeneficiario().getCodiceFiscale()));

		etpe.setCodRuoloOperatore(ruolo.getCodiceRuolo());
		etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
		etpe.setDescNotaInterna(bo.getNotainterna());
		etpe.setDescNotaBeneficiario(bo.getNotabeneficiario());
		// etpe.setDescNotaOperatore(notaOperatore);
		etpe.setDescNota(bo.getNota());
		
		etpe.setEsenzioneDMotivazioneTipo(null);
		if (bo.getCodiceMotivazioneTipo() != null) {
			etpe.setEsenzioneDMotivazioneTipo(
					dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(bo.getCodiceMotivazioneTipo()));
		}

		EsenzioneDPraticaStato esestato = dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA);
		etpe.setSkTipologiaStatoPratica(new Long(esestato.getSkPraticaStato()));

		etpe.setIdUser(new Long(utente.getIdAura()));
		etpe.setCodiceFiscaleOperatore(utente.getCodFisc());

		etpe = dataDao.insertEsenzioneTPraticaEsenzione(etpe);
		if (etpe == null) {
			throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB",
					Status.BAD_REQUEST.getStatusCode());
		}

		// Inserisco la relazione pratica-documento
		EsenzioneRPraticaEsenzioneDocumento rel = new EsenzioneRPraticaEsenzioneDocumento();
		EsenzioneRPraticaEsenzioneDocumentoPK relPk = new EsenzioneRPraticaEsenzioneDocumentoPK();

		rel.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		rel.setDatModifica(null);
		rel.setDatCancellazione(null);
		rel.setIdUser(null);
		rel.setCodRuoloOperatore(null);
		rel.setDatFineValidita(etpe.getDatFineValidita());
		relPk.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
		relPk.setSkDocumento(etd.getSkDocumento().longValue());
		relPk.setSkPraticaEsenzione(etpe.getSkPraticaEsenzione().longValue());
		rel.setId(relPk);

		dataDao.insertEsenzioneRPraticaDocumento(rel);

	}
	

	public List<EsenzioneAssistito> getPraticheFiltrate(FiltriRicercaPratiche filtri, String codAsl, UserInfo utente,
			int pagesize) {
		Set<EsenzioneAssistito> listaEsenzioniAssistito = new TreeSet<EsenzioneAssistito>();
		List<EsenzioneTPraticaEsenzione> listaEsenzioni = dataDao.getListaPraticheFiltrate(filtri, codAsl, pagesize);
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			for (EsenzioneTPraticaEsenzione ese : listaEsenzioni) {
				listaEsenzioniAssistito.add(new EsenzioneAssistito(ese));
			}
		}

		if (filtri.getTab().equalsIgnoreCase(Constants.TAB_DA_FARE)) {
			Long contarecord = contaPraticheFiltrate(filtri, codAsl);
			for (EsenzioneAssistito ese : listaEsenzioniAssistito) {
				ese.setNumeroTotaleElementi(contarecord.intValue());
			}
		}
		if (filtri.getTab().equalsIgnoreCase(Constants.TAB_TUTTE_LE_PRATICHE)) {
			boolean check = false;
			for (StatoDocumento s : filtri.getStatiPratica()) {
				if (s.getCodStato().equalsIgnoreCase(Constants.STATO_PRATICA_VALIDATA)) {
					check = true;
					break;
				}
			}
			if (check) {
				CittadinoEsenpat cittadinoEsenpat = IntegrationClientImpl.getInstance()
						.getCittadinoEsenpat(filtri.getIdAura());
				if (cittadinoEsenpat != null && cittadinoEsenpat.getEsenzioniAura() != null
						&& cittadinoEsenpat.getEsenzioniAura().getInfoesenzione() != null
						&& cittadinoEsenpat.getEsenzioniAura().getInfoesenzione().length > 0) {
					InfoEsenzioneNew[] infoesenzione = cittadinoEsenpat.getEsenzioniAura().getInfoesenzione();
					EsenredDAziendasanitaria asl = null;
					if (codAsl != null)
						asl = dataDao.getAziendaSanitariaByCodAsl(codAsl);

					for (int i = 0; i < infoesenzione.length; i++) {
						EsenzioneAssistito esenzioneAssistito = new EsenzioneAssistito(infoesenzione[i],
								cittadinoEsenpat, utente);

						boolean add = true;
						if (add && Checker.isValorizzato(filtri.getCodEsenzione())
								&& Checker.isValorizzato(esenzioneAssistito.getCodiceEsenzione())) {
							if (!esenzioneAssistito.getCodiceEsenzione().equalsIgnoreCase(filtri.getCodEsenzione()))
								add = false;
						}

						if (add && Checker.isValorizzato(filtri.getValidaDal())
								&& Checker.isValorizzato(esenzioneAssistito.getValidaDal())) {
							if (Converter.getData(esenzioneAssistito.getValidaDal())
									.before(Converter.getData(filtri.getValidaDal())))
								add = false;
						}

						if (add) {
							esenzioneAssistito.setAslDenominazione(asl == null ? null : asl.getDenominazione());
							listaEsenzioniAssistito.add(esenzioneAssistito);
						}
					}
				}
			}
		}

		List<EsenzioneAssistito> listaPratiche = new ArrayList<EsenzioneAssistito>(listaEsenzioniAssistito);
		Collections.sort(listaPratiche);

		return listaPratiche;
	}

	public Long contaPraticheFiltrate(FiltriRicercaPratiche filtri, String codAsl) {
		return dataDao.contaPraticheFiltrate(filtri, codAsl);
	}

	/**
	 * Genera il CDA-R2 dell'attestato di esenzione
	 * 
	 * @param certificato
	 * @param utente
	 * @return
	 */
	public byte[][] generateEsenzioneXmlCda(EsenzioneTDocumento documento, UserInfo utente, CittadinoEsenpat cittadino) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream xmlFile = classloader.getResourceAsStream("/template/docEsenzione-esenpat.xml");
		InputStream resXml = null;
		final String ROOT="2.16.840.1.113883.2.9.2.10.4.4.10";

		CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
		String oidDocumento = certificatiFacade.getOidDocumento(documento, Constants.OID_AES, cittadino.getCodASL());

		try {
			SAXBuilder builder = new SAXBuilder();

			ArrayOfinfoesenzioneInfoEsenzioneNew esenzioniAura = cittadino.getEsenzioniAura();

			
			Namespace rootNs = Namespace.getNamespace("urn:hl7-org:v3");
			Namespace xfaNs = Namespace.getNamespace("xmlns:xfa='http://www.xfa.org/schema/xfa-data/1.0/'");
		      
		      
		     Document doc = (Document) builder.build(xmlFile);
		     Element xfaRoot = doc.getRootElement();
		     Element xfaData = (Element) xfaRoot.getChildren().get(0);
	
		     Element rootNode =  xfaData.getChild("ClinicalDocument", null);
		      
//			Document doc = (Document) builder.build(xmlFile);
//			Element rootNode = doc.getRootElement();

			Element effectiveTime = rootNode.getChild("effectiveTime", null);
			String effectiveDateTime = Converter.getEffectiveTimeHL7();
			effectiveTime.setAttribute(new Attribute("value", effectiveDateTime));
			
			 EsenredDAziendasanitaria asl = dataDao.getAziendaSanitariaByCodAsl(cittadino.getCodASL());
			 
			 Element idDocEsenzione = rootNode.getChild("id", null);
				idDocEsenzione.setAttribute(new Attribute("extension", oidDocumento));
				idDocEsenzione.setAttribute(new Attribute("root", ROOT +"." + asl.getCodAzienda()));

				
		      Element setIdClinical = rootNode.getChild("setId", null);
		      setIdClinical.setAttribute(new Attribute("assigningAuthorityName", asl.getDenominazione()));
		      setIdClinical.setAttribute(new Attribute("extension", oidDocumento));
		      setIdClinical.setAttribute(new Attribute("root", ROOT +"." + asl.getCodAzienda()));

			Element recordTarget = rootNode.getChild("recordTarget", null);
			Element patientRole = recordTarget.getChild("patientRole", null);

			
			 List idsPatient = (List) patientRole.getChildren("id",rootNs);
		      for(int i=0; i< idsPatient.size(); i++)
		      {
		    	  Element idPat = null;
		    	  switch (i) {
		    	  	case 0:
		    	  		idPat = (Element) idsPatient.get(i);  
		    	  		idPat.setAttribute("extension", cittadino.getCodFiscale());
		    	  		break;
		    	  	case 1:
		    	  		try {
			    	  		if(cittadino.getIdAura() != null || cittadino.getIdAura() != "")
			    	  			idPat = (Element) idsPatient.get(i);  
			    	  			idPat.setAttribute("assigningAuthorityName", "ID_PRS_Regione Piemonte");
			    	  			idPat.setAttribute("extension", cittadino.getIdAura());
		    	  		}catch (Exception e) {
							// non inserisco il 2� TAG
						}
		    	  		break;
				default:
					break;
				}
		      }

			Element addr = patientRole.getChild("addr", rootNs);
			addr.addContent(new Element("country", rootNs).setText(cittadino.getCodStatoNascita()));
			addr.addContent(new Element("streetName", rootNs).setText(cittadino.getIndirizzoResidenza()));
			addr.addContent(new Element("postalCode", rootNs).setText(cittadino.getCapResidenza()));
			addr.addContent(new Element("city", rootNs).setText(cittadino.getCittaResidenza()));
			addr.addContent(new Element("censusTract", rootNs).setText(cittadino.getCodComuneNascita()));


			Element patient = patientRole.getChild("patient", null);

			Element name = patient.getChild("name", null);
			name.addContent(new Element("family", rootNs).setText(cittadino.getCognome()));
			name.addContent(new Element("given", rootNs).setText(cittadino.getNome()));
  

			Element administrativeGenderCode = patient.getChild("administrativeGenderCode", null);
			administrativeGenderCode.setAttribute("code", cittadino.getCodSesso());


			Element birthTime = patient.getChild("birthTime", null);
			String birthTimeDate = Converter.getDataXml(cittadino.getDataNascita().toString());
			birthTime.setAttribute("value", birthTimeDate);

			Element birthplace = patient.getChild("birthplace", null);
			Element place = birthplace.getChild("place", null);
			Element addrBirthPlace = place.getChild("addr", null);
			addrBirthPlace.setAttribute(new Attribute("use", "H"));
			addrBirthPlace.addContent(new Element("country", rootNs).setText(cittadino.getDescStatoNascita()));
			addrBirthPlace.addContent(new Element("city", rootNs).setText(cittadino.getCittaResidenza()));
			addrBirthPlace.addContent(new Element("censusTract", rootNs).setText(cittadino.getCodComuneNascita()));

			Element author = rootNode.getChild("author", null);

			Element authorTime = author.getChild("time", null);
			authorTime.setAttribute("value", effectiveDateTime);

			Element assignedAuthor = author.getChild("assignedAuthor", null);

			Element idAuthor = assignedAuthor.getChild("id", null);
			idAuthor.setAttribute(new Attribute("extension", utente.getCodFisc()));

			Element assignedPerson = assignedAuthor.getChild("assignedPerson", null);
			Element authorName = assignedPerson.getChild("name", null);
			authorName.addContent(new Element("family", rootNs).setText(utente.getCognome()));
			authorName.addContent(new Element("given", rootNs).setText(utente.getNome()));


			String nomeAzienda = dataDao.getAziendaSanitariaByCodAsl(utente.getCodASL()).getDenominazione();

			Element custodian = rootNode.getChild("custodian", null);
			Element assignedCustodian = custodian.getChild("assignedCustodian", null);
			Element representedCustodianOrganization = assignedCustodian.getChild("representedCustodianOrganization",
					null);
			Element idCustodian = representedCustodianOrganization.getChild("id", null);
			idCustodian.setAttribute("extension", utente.getCodASL());

			Element nameCustodian = new Element("name", rootNs);
			nameCustodian.setText(nomeAzienda);
			representedCustodianOrganization.addContent(nameCustodian);


			String idAttEsenzione= null;		
			int i = 0;
		      Element componentRoot = rootNode.getChild("component", null);
		      Element structuredBody = componentRoot.getChild("structuredBody", null);
		      
				for (InfoEsenzioneNew esen : esenzioniAura.getInfoesenzione()) 
				{
				
					EsenzioneDEsenzione esenzione = dataDao.getEsenzioneDEsenzione(esen.getCodEsenzione());

				     
				i+=1;
		    	  Element singleComponent =  new Element("component", rootNs);
		          Element section = new Element("section", rootNs);
		          
		          Element templateId = new Element("templateId", rootNs);
		          templateId.setAttribute(new Attribute("root", "2.16.840.1.113883.6.1"));

		          
		          Element code = new Element("code", rootNs);
		          code.setAttribute(new Attribute("code", "57827-8"));
		          code.setAttribute(new Attribute("codeSystem", "2.16.840.1.113883.6.1"));
		          code.setAttribute(new Attribute("codeSystemName", "LOINC"));
		          code.setAttribute(new Attribute("displayName", "Motivo di esenzione dal co-pagamento"));
		          
		          Element title = new Element("title", rootNs);
		          title.setText("Esenzione");
		          

				
				Element text = new Element("text", rootNs);
				Element table = new Element("table", rootNs);
				Element thead = new Element("thead", rootNs);
				Element tbody = new Element("tbody", rootNs);
				Element trhead = new Element("tr", rootNs);
				Element td = new Element("td", rootNs);
				td.setText("Esenzione Per patologia");
				trhead.addContent(td);
				thead.addContent(trhead);
				Element trEsenzione = new Element("tr", rootNs);
				Element tdCodEsen = new Element("td", rootNs);
				Element tdDescEsen = new Element("td", rootNs);
				trEsenzione.setAttribute("ID", "esenzione_"+i);
				tdCodEsen.setText("Cod. " + esen.getCodEsenzione());
				try {
					if (esenzione.getDescEsenzione()!= null) {
						tdDescEsen.setText(esenzione.getDescEsenzione());
					}
				} catch (Exception e) {
					tdDescEsen.setText("Descrizione non presente");
				}
				
				trEsenzione.addContent(tdCodEsen);
				trEsenzione.addContent(tdDescEsen);

				Element trNote = new Element("tr", rootNs);
				Element tdNoteText = new Element("td", rootNs);
				Element tdNoteDesc = new Element("td", rootNs);
				trNote.setAttribute("ID", "note_commenti_"+i);
				tdNoteText.setText("Cod. " + esen.getCodEsenzione());
				try {
					String prestazioni = dataDao.getPrestazioniSpecialisticheByCodDiagnosi(esen.getCodDiagnosi().toString())
							.get(0).getDescPrestazione();
					tdNoteDesc.setText(prestazioni);
				} catch (Exception e) {
					tdNoteDesc.setText("");
				}
				
				trNote.addContent(tdNoteText);
				trNote.addContent(tdNoteDesc);

				tbody.addContent(trEsenzione);
				tbody.addContent(trNote);

				table.addContent(thead);
				table.addContent(tbody);

				text.addContent(table);

				// insert text
				section.addContent(templateId);
//				section.addContent(componentId);
				section.addContent(code);
				section.addContent(title);
				section.addContent(text);

				// insert entry
				Element entry = new Element("entry", rootNs);
				entry.setAttribute(new Attribute("typeCode", "DRIV"));

				Element act = new Element("act", rootNs);
				act.setAttribute(new Attribute("classCode", "ACT"));
				act.setAttribute(new Attribute("moodCode", "EVN"));

				Element templateIdEntry = new Element("templateId", rootNs);
				templateIdEntry.setAttribute(new Attribute("root", "2.16.840.1.113883.2.9.10.1.10.3"));

				Element statusCode = new Element("statusCode", rootNs);
				statusCode.setAttribute(new Attribute("code", "active"));

				Element codeEntry = new Element("code", rootNs);
				codeEntry.setAttribute(new Attribute("code", esen.getCodEsenzione()));
				codeEntry.setAttribute(new Attribute("codeSystem", "2.16.840.1.113883.6.1"));
				codeEntry.setAttribute(new Attribute("codeSystemName", "Catalogo nazionale delle esenzioni"));
				try {
					if (esenzione.getDescEsenzione()!= null) {
						codeEntry.setAttribute(new Attribute("displayName", esenzione.getDescEsenzione()));
					}
				} catch (Exception e) {
					codeEntry.setAttribute(new Attribute("displayName", "Descrizione non presente"));
				}

				Element effectiveTimeEsenzione = new Element("effectiveTime", rootNs);
				String dataInizioValidita = Converter.getDataXmlFromCalendarDate(esen.getDataEmissione());
				String dataFineValidita = Converter.getDataXmlFromCalendarDate(esen.getDataScadenza());
				effectiveTimeEsenzione.addContent(new Element("low", rootNs).setAttribute("value", dataInizioValidita));
				if (Checker.isValorizzato(dataFineValidita)) {
					effectiveTimeEsenzione
							.addContent(new Element("high", rootNs).setAttribute("value", dataFineValidita));
				}
				
		          
		          
		          act.addContent(templateIdEntry);
		          act.addContent(codeEntry);
		          act.addContent(statusCode);
		          act.addContent(effectiveTimeEsenzione);

		          entry.addContent(act);
		          section.addContent(entry);
		          
		          
		          singleComponent.addContent(section);
		          
		          structuredBody.addContent(singleComponent);
		          
					 idAttEsenzione = esen.getCodAttestato();

		      }
			
		
				Format format = Format.getPrettyFormat();
			    format.setEncoding("iso-8859-1");

			XMLOutputter xmlOutput = new XMLOutputter(format);
			
			ByteArrayOutputStream byteArrayout = new ByteArrayOutputStream();
			xmlOutput.output(doc, byteArrayout);
			
			resXml = new ByteArrayInputStream(byteArrayout.toByteArray());

			 //////////HL7 XML FOP
			  InputStream xmlFopEsen = classloader.getResourceAsStream("/template/docEsen-fop.xml");
			  InputStream resXmlFop = null;

		      Document docFop = (Document) builder.build(xmlFopEsen);
		      Element rootNodeFop = docFop.getRootElement();
		      rootNodeFop.addContent(new Element("oid").setText(oidDocumento));
		      rootNodeFop.addContent(new Element("idAttestato").setText(idAttEsenzione));
		      rootNodeFop.addContent(new Element("effectiveTime").setText(DateUtil.formatDate(new Date(),"dd/MM/yyyy")));
		      

		      Element aslFop = rootNodeFop.getChild("asl", null);
		      aslFop.addContent(nomeAzienda);
		      
		      Element patientFop = rootNodeFop.getChild("patient", null);
		      patientFop.addContent(new Element("family").setText(cittadino.getCognome()));
		      patientFop.addContent(new Element("given").setText(cittadino.getNome()));
		      patientFop.addContent(new Element("birthTime").setText(cittadino.getDataNascita()));
		      patientFop.addContent(new Element("id").setText(cittadino.getCodFiscale()));
		      String indirizzo = cittadino.getCittaResidenza() + " - " + cittadino.getIndirizzoResidenza();
		      patientFop.addContent(new Element("address").setText(indirizzo));
		      patientFop.addContent(new Element("card").setText(cittadino.getCodiceTesseraRegionale()));
		      
		      

		      Element tableEsenzioni = rootNodeFop.getChild("tableEsenzioni", null);
		      Element components = rootNodeFop.getChild("components", null);
		      String prestazioniEsenzione = null;
		      
		  	for (InfoEsenzioneNew esen : esenzioniAura.getInfoesenzione()) 
			{
		  		Element detailEsenzione = new Element("detailEsenzione");

				String dataInizioValidita = Converter.getPdfDateFromCalendar(esen.getDataEmissione());
				String dataFineValidita = Converter.getPdfDateFromCalendar(esen.getDataScadenza());
				
				EsenzioneDEsenzione esenzione = dataDao.getEsenzioneDEsenzione(esen.getCodEsenzione());
				
		  		detailEsenzione.addContent(new Element("startDate").setText(dataInizioValidita));
		  		if(esen.getCodDiagnosi() != null && 
		  				esen.getCodDiagnosi().getCodDiagnosi_type0() != null && 
		  				esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase("000"))
		  			detailEsenzione.addContent(new Element("code").setText(esen.getCodEsenzione()));
		  		else
		  			detailEsenzione.addContent(new Element("code").setText(esen.getCodEsenzione() + " / " + esen.getCodDiagnosi()));
		  		
		  		String descAsl = dataDao.getAziendaSanitariaByCodAsl(esen.getFonte().toString()).getDenominazione();
		  		
		  		if(descAsl.startsWith("ASL")) {
			  		String nomeAziendaSplit[] =  descAsl.split("ASL");
			  		detailEsenzione.addContent(new Element("codAsl").setText(nomeAziendaSplit[1].trim()));
		  		}
		  		else
		  		{
			  		detailEsenzione.addContent(new Element("codAsl").setText(descAsl));
		  		}
		  		
			     try
			     {
			  		detailEsenzione.addContent(new Element("reference").setText(esenzione.getRiferimentoLegislativo()));

			     } catch (NullPointerException e) {
					detailEsenzione.addContent(new Element("reference").setText("/"));
				 }
			     
			     try {
					if (Checker.isValorizzato(dataFineValidita)) {
						detailEsenzione.addContent(new Element("endDate").setText(dataFineValidita));
					}
				} catch (NullPointerException e) {
					detailEsenzione.addContent(new Element("endDate").setText("/"));
				}
		  		
				tableEsenzioni.addContent(detailEsenzione);

		  		
		      Element componentFop = new Element("component");
		      if(esen.getCodDiagnosi() != null && 
		  			esen.getCodDiagnosi().getCodDiagnosi_type0() != null &&
		  			esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase("000"))
		    	componentFop.addContent(new Element("code").setText(esen.getCodEsenzione()));
		      else
		  		componentFop.addContent(new Element("code").setText(esen.getCodEsenzione() + " / " + esen.getCodDiagnosi()));
		      try {
		    	  prestazioniEsenzione = null;
		    	  prestazioniEsenzione = dataDao.getPrestazioniSpecialisticheByCodDiagnosi(esen.getCodDiagnosi().toString())
							.get(0).getDescPrestazione();
					if (prestazioniEsenzione != null) {
					   componentFop.addContent(new Element("description").setText(prestazioniEsenzione));
					}
				} catch (Exception e) {
				}
		      components.addContent(componentFop);
			} 
		      
		      ByteArrayOutputStream byteaFop = new ByteArrayOutputStream();
				xmlOutput.output(docFop, byteaFop);
				
				resXmlFop = new ByteArrayInputStream(byteaFop.toByteArray());
		      //
				
				byte[][] xmls = new byte[2][];
				xmls[0]= byteArrayout.toByteArray();
				xmls[1] =  byteaFop.toByteArray();
			
			return xmls;

		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public byte[] generatePdf(byte[][] XMLS) throws DocumentException, IOException {
		ByteArrayOutputStream fopPdfOut = new ByteArrayOutputStream();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

	   	byte[] xmlCdaGenerated = XMLS[0];
	   	InputStream xmlFopEsen = new ByteArrayInputStream(XMLS[1]);

	   	
	   	//set file xconf per FOP
	   	InputStream arialStream = classloader.getResourceAsStream("/template/arial.ttf");
		File arialFile = new File("arial.ttf");
        FileUtils.copyInputStreamToFile(arialStream, arialFile);

        InputStream arialBDStream = classloader.getResourceAsStream("/template/arialbd.ttf");
		File arialBDFile = new File("arialbd.ttf");
        FileUtils.copyInputStreamToFile(arialBDStream, arialBDFile);
        
		InputStream cfgFop = classloader.getResourceAsStream("/template/fop.xconf");
		File fileFopConf = new File("fop.xconf");
        FileUtils.copyInputStreamToFile(cfgFop, fileFopConf);

	    
		try{
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
			FopFactory fopFactory = FopFactory.newInstance(fileFopConf);


         FOUserAgent userAgent = fopFactory.newFOUserAgent();
         userAgent.getRendererOptions().put("pdf-a-mode", "PDF/A-2b");

            
            try {
            	
            	InputStream xslDocEsen = classloader.getResourceAsStream("/template/docEsen.xsl");
            	
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, fopPdfOut);
    		    TransformerFactory factory = TransformerFactory.newInstance();

//                Transformer transformer = factory.newTransformer(new StreamSource(new File("C://template/xslEsenzioneFop.xsl")));
                Transformer transformer = factory.newTransformer(new StreamSource(xslDocEsen));
//                Source src = new StreamSource(new File("C://template/xmlEsenzioneFop.xml"));
                Source src = new StreamSource(xmlFopEsen);
    		    Result res = new SAXResult(fop.getDefaultHandler());
    		    transformer.transform(src, res);
    		  
    		} catch (TransformerException ex) {
                ex.printStackTrace();
    		} finally {
    			fopPdfOut.close();
    		}
            
          
        }catch(Exception e){
            e.printStackTrace();
        }
		
		//// PDFBOX PART 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();		  
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
		try {

			PDDocument document = PDDocument.load(fopPdfOut.toByteArray());

		   	PDDocumentCatalog catalog = document.getDocumentCatalog();		   
		    PDDocumentInformation info = new PDDocumentInformation();
		    
            info.setTitle("Attestato Esenzione");
            info.setSubject("document desc");
            info.setAuthor("Csi Piemonte");
            info.setCreator("Csi Piemonte");
            info.setProducer("Csi Piemonte");
            info.setKeywords("Apache, PdfBox, XMP, PDF");
            info.setCreationDate(Calendar.getInstance());
            info.setModificationDate(Calendar.getInstance());

            
            
            XMPMetadata metadata = XMPMetadata.createXMPMetadata();
            
            PDFAIdentificationSchema pdfaid = new PDFAIdentificationSchema(metadata);
    		metadata.addSchema(pdfaid);
    		pdfaid.setPart(2);
    		pdfaid.setConformance("B");
    		
    		
            AdobePDFSchema pdfSchema = metadata.createAndAddAdobePDFSchema();
			pdfSchema.setProducer(info.getProducer());
			pdfSchema.setPDFVersion("1.7");

			XMPBasicSchema basicSchema = metadata.createAndAddXMPBasicSchema();
			basicSchema.setCreateDate(info.getCreationDate());
			basicSchema.setCreatorTool(info.getCreator());
			basicSchema.setMetadataDate(info.getCreationDate());
			
			DublinCoreSchema dcSchema = metadata.createAndAddDublinCoreSchema();
//			dcSchema.setTitle("x-default", info.getTitle());
			dcSchema.addCreator(info.getCreator());
//			dcSchema.setDescription(info.getSubject());
			dcSchema.addDate(info.getCreationDate());
			dcSchema.setFormat("application/pdf");
			dcSchema.addLanguage("it-IT");

			   document.setDocumentInformation(info);
	            document.setVersion(1.7f);

			PDMetadata metadataStream = new PDMetadata(document);
			catalog.setMetadata( metadataStream );
			
			 String prefix = "<?xpacket begin=\"\uFEFF\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";
			  String suffix = "<?xpacket end=\"w\"?>";
			  
			XmpSerializer serializer = new XmpSerializer();
			baos.write(prefix.getBytes("UTF-8")); // see https://github.com/ZUGFeRD/mustangproject/issues/44
			serializer.serialize(metadata, baos, false);
			baos.write(suffix.getBytes("UTF-8"));

			metadataStream.importXMPMetadata( baos.toByteArray() );
		  
				 
			    COSStream cosos = new COSStream();
			    
			    OutputStream os = cosos.createOutputStream(COSName.FLATE_DECODE);
			   	 InputStream xmlCda2 = new ByteArrayInputStream(xmlCdaGenerated);
	             Source src = new StreamSource(xmlCda2);
//	                Source src = new StreamSource(new File("C://template/xmlEsenzioneFop.xml"));
			      Transformer transformer = TransformerFactory.newInstance().newTransformer();
			      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			      transformer.transform(src, new StreamResult(os));
/		
				 os.close();
						

			        // Add the entry to the embedded file tree and set in the document.
			        Map efMap = new HashMap();
			        efMap.put("datasets", cosos);
			        final PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();
			        efTree.setNames(efMap);
			        
			        // Attachments are stored as part of the "/Names" dictionary in the document /Catalog
			        final PDDocumentNameDictionary names = new PDDocumentNameDictionary(document.getDocumentCatalog());
			        names.getCOSObject().setItem("XFAResources", efTree);
			        document.getDocumentCatalog().setNames(names);
			     		        
			        
            document.save(out);		    
		    document.close();
		    
		    
		    arialBDFile.delete();
		    arialFile.delete();
		    fileFopConf.delete();
		    
		    return out.toByteArray();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		} 
		
			return null;
	}
	

	public EsenzioneTPraticaEsenzione updatePraticaEsenzioneFirmata(EsenzioneTPraticaEsenzione etpe, UserInfo utente, 
			List<Ruolo> ruoli, EsenzioneDDocumentoTipo esenzioneDDocumentoTipo) throws CheckException {
		try {
			etpe.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			etpe.setDatModifica(new Timestamp(System.currentTimeMillis()));
			etpe.setIdUser(new Long(utente.getIdAura()));
			etpe.setCodiceFiscaleOperatore(utente.getCodFisc());
			String tipo = esenzioneDDocumentoTipo.getCodDocumentoTipo().equals(Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE) ? Constants.STATO_DOCUMENTO_VALIDO : Constants.STATO_DOCUMENTO_VALIDOINATTESA;
			EsenzioneDPraticaStato statoesenzione = dataDao.getEsenzioneDPraticaStatoPerCodStato(tipo);
			etpe.setSkTipologiaStatoPratica(new Long(statoesenzione.getSkPraticaStato()));
			return dataDao.updateEsenzioneTPraticaEsenzione(etpe);
		} catch (NoResultException e) {
			throw new CheckException("MSG200");
		}
	}


	public EsenzioneTRepositoryDocumentale aggiornaCertificatoFirmato(String skDocumento, UserInfo userInfo,
			byte[] encode, List<Ruolo> ruoli) {
		try {
			// crea un nuovo record in S_DOCUMENTO
			EsenzioneTDocumento doc = dataDao.getDocumentoCertificato(skDocumento);
			copyInEsenzioneSDocumento(doc);
		
			// Aggiorno T_Documento
			doc.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			doc.setDatModifica(new Timestamp(System.currentTimeMillis()));
			doc = UpdateDocumentoTDocumento(doc);

			// Aggiorno T_MetaDati
			EsenzioneTMetadatiDocumento meta = doc.getEsenzioneTMetadatiDocumento();
			if (meta != null) {
				String firmatario = userInfo.getCognome() + " " + userInfo.getNome();
				meta.setMedicoValidatore(firmatario.trim());
				//24/11/2020
				meta.setFlagFirmato(true);
				meta.setDatFirma(new Timestamp(System.currentTimeMillis()));
				//
				dataDao.updateMetadatiDocumento(meta);
			}

			// Aggiorno T_Repository_Documentale
			EsenzioneTRepositoryDocumentale repo = dataDao.getRepositoryDocumentale(doc.getSkRepository().intValue());
			repo = new EsenzioneTRepositoryDocumentale(repo);
			repo.setFile(encode);
			repo.setDatModifica(new Timestamp(System.currentTimeMillis()));
			repo.setCodRuoloOperatore(ruoli.get(0).getCodiceRuolo());
			dataDao.insertAllegatoRepositoryDocumentale(repo);
		
			return dataDao.getRepositoryDocumentale(repo.getSkRepository());

		} catch (CheckException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}