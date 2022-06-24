/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.facade;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
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
import org.jboss.resteasy.util.DateUtil;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.XfaForm;
import com.sun.mail.handlers.text_html;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenpatweb.business.gateway.Attachment;
import it.csi.esenred.esenpatweb.business.gateway.CallInfo;
import it.csi.esenred.esenpatweb.business.gateway.FileResult;
import it.csi.esenred.esenpatweb.business.gateway.Identity;
import it.csi.esenred.esenpatweb.business.gateway.OtpCredentialsType;
import it.csi.esenred.esenpatweb.business.gateway.OtpReqInput;
import it.csi.esenred.esenpatweb.business.gateway.OtpResult;
import it.csi.esenred.esenpatweb.business.gateway.PadesInput;
import it.csi.esenred.esenpatweb.business.gateway.SignIdentity;
import it.csi.esenred.esenpatweb.business.gateway.SignLayout;
import it.csi.esenred.esenpatweb.business.iride.base.Ruolo;
import it.csi.esenred.esenpatweb.dto.AttestatoEsenzione;
import it.csi.esenred.esenpatweb.dto.Certificato;
import it.csi.esenred.esenpatweb.dto.CertificatoAssistito;
import it.csi.esenred.esenpatweb.dto.CertificatoCittadino;
import it.csi.esenred.esenpatweb.dto.CertificatoEsenpat;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.CittadinoEsenpat;
import it.csi.esenred.esenpatweb.dto.DettaglioCertificato;
import it.csi.esenred.esenpatweb.dto.Documenti;
import it.csi.esenred.esenpatweb.dto.FiltriFirmaDigitale;
import it.csi.esenred.esenpatweb.dto.FiltriListaCertificati;
import it.csi.esenred.esenpatweb.dto.FiltriRichiestaOtp;
import it.csi.esenred.esenpatweb.dto.ModelCa;
import it.csi.esenred.esenpatweb.dto.NuovoCertificato;
import it.csi.esenred.esenpatweb.dto.OtpType;
import it.csi.esenred.esenpatweb.dto.StoricoEsenzione;
import it.csi.esenred.esenpatweb.dto.Tipologia;
import it.csi.esenred.esenpatweb.dto.UserInfo;
import it.csi.esenred.esenpatweb.dto.Utente;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenredDAziendasanitaria;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDCa;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDErroriEsenpat;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDErroriGatewayFirma;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDTypeOtp;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRCaTypeOtp;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumentoPK;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTMetadatiDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.LogUtil;

public class CertificatiFacade {
	
	private DataDaoPatologiaIf dataDao;
//	Date date = new Date();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
	SimpleDateFormat sdfc = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfXml = new SimpleDateFormat("yyyyMMdd");
	
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE, null);

	public static String CCM = "CCM";
	public static String AES = "AES";

	public List<CertificatoCittadino> getListaCertificati(String cit_id, String queryString) {
		List<CertificatoCittadino> certificatiCittadino = null;
		FiltriListaCertificati filtriListaCertificati = null;

		if (queryString != null && queryString.length() > 0) {
			String queryStringNew = queryString.replace("'", "\"");

			ObjectMapper mapper = new ObjectMapper();
			try {
				filtriListaCertificati = mapper.readValue(queryStringNew, FiltriListaCertificati.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<EsenzioneTDocumento> esenzioneTDocumentos = dataDao.getListaCertificati(cit_id, filtriListaCertificati);
		if (esenzioneTDocumentos != null && esenzioneTDocumentos.size() > 0) {
			certificatiCittadino = new ArrayList<CertificatoCittadino>(esenzioneTDocumentos.size());
			for (EsenzioneTDocumento esenzioneTDocumento : esenzioneTDocumentos) {
				EsenzioneTCittadino inseritoDa = null;
				if (esenzioneTDocumento.getIdUserid() != null)
					inseritoDa = dataDao.getCittadinoByUserId(esenzioneTDocumento.getIdUserid().toString());
				else
					inseritoDa = dataDao.getCittadino(esenzioneTDocumento.getCodiceFiscaleCittadino());
				certificatiCittadino.add(new CertificatoCittadino(esenzioneTDocumento, inseritoDa));
			}
		}
		return certificatiCittadino;
	}

	public CertificatoCittadino getDettaglioCertificato(String cit_id, String certificato_id) {
		EsenzioneTDocumento dettaglioCertificato = dataDao.getDettaglioCertificato(cit_id, certificato_id);
		if (dettaglioCertificato != null) {
			EsenzioneTCittadino inseritoDa = null;
			if (dettaglioCertificato.getIdUserid() != null)
				inseritoDa = dataDao.getCittadinoByUserId(dettaglioCertificato.getIdUserid().toString());
			else
				inseritoDa = dataDao.getCittadino(dettaglioCertificato.getCodiceFiscaleCittadino());
			return new CertificatoCittadino(dettaglioCertificato, inseritoDa);
		}
		return null;
	}

	public DettaglioCertificato getDettaglioCertificatoLotto2(String cit_id, String certificato_id) {
		EsenzioneTDocumento dettaglioCertificato = dataDao.getDettaglioCertificato(cit_id, certificato_id);
		if (dettaglioCertificato != null) {
			EsenzioneTCittadino beneficiario = dataDao.getCittadino(cit_id);

			return new DettaglioCertificato(dettaglioCertificato, beneficiario);
		}
		return null;
	}

	public EsenzioneTDocumento insertDocumentoPraticaEsenzione(String cit_id, Certificato certificato,
			EsenzioneTRepositoryDocumentale repoDocumentale, String codTipoUser) {

		EsenzioneTDocumento docEsenzione = new EsenzioneTDocumento();
		docEsenzione.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		docEsenzione.setDatModifica(null);
		docEsenzione.setDatCancellazione(null);

		Timestamp parsedData = null;
		try {
			if (certificato.getData_fine_validita() != null) {
				parsedData = new Timestamp(((java.util.Date) df.parse(certificato.getData_fine_validita())).getTime());
				docEsenzione.setDatFineValidita(parsedData);
			} else {
				docEsenzione.setDatFineValidita(null);
			}
			parsedData = new Timestamp(((java.util.Date) df.parse(certificato.getData_rilascio())).getTime());
			docEsenzione.setDatDocumento(parsedData);
			docEsenzione.setDatInizioValidita(parsedData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		docEsenzione.setDescNote(null);
		docEsenzione.setFlagConformitaDocumento(null);
		docEsenzione.setDescEstesaPatologiaCertificato(null);


		docEsenzione.setSkTipoDocumento(new Long(1)); 

		docEsenzione.setSkRepository(Long.valueOf(repoDocumentale.getSkRepository()));
		docEsenzione.setSkTipologiaStatoDocumento(new Long(dataDao
				.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_VALIDO).getSkDocumentoStato()));
		if (certificato.getMalattia() == null || certificato.getMalattia().getCodice() == null) {
			docEsenzione.setSkDiagnosi(null);
		} else {
			docEsenzione.setSkDiagnosi(Long.decode(
					dataDao.getEsenzioneDDiagnosi(certificato.getMalattia().getCodice()).getSkDiagnosi().toString()));
		}
		docEsenzione.setCodRuoloOperatore(null);
		docEsenzione.setIdUserid(null);
		docEsenzione.setCodTipoUser(codTipoUser);
		docEsenzione.setFlagConformitaDocumento(null);

	
		docEsenzione.setOidDocumento(null);

	
		docEsenzione.setCodiceFiscaleCittadino(cit_id); 

		dataDao.insertDocumentoPraticaEsenzione(docEsenzione);
		return docEsenzione;
	}

	public EsenzioneTRepositoryDocumentale insertRepositoryDocumentale(Certificato certificato) {

		EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();
		repoDocumentale.setDescFile(certificato.getTipologia_documento().getDescrizione());
		repoDocumentale.setDatArchiviazione(new Timestamp(System.currentTimeMillis()));
		repoDocumentale.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		repoDocumentale.setDatModifica(null);
		repoDocumentale.setFile(certificato.getFile_base64().getBytes());
		repoDocumentale.setFileName(null);
		repoDocumentale.setDatCancellazione(null);
		
		repoDocumentale.setCodRuoloOperatore(null);
		repoDocumentale.setIdUser(null);

		dataDao.insertRepositoryDocumentale(repoDocumentale);
		return repoDocumentale;

	}

	public EsenzioneTRepositoryDocumentale insertRepositoryDocumentale(NuovoCertificato certificato, UserInfo utente,
			byte[] pdfFile) {
		EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();
		repoDocumentale.setDescFile(null); // null?
		repoDocumentale.setDatArchiviazione(new Timestamp(System.currentTimeMillis()));
		repoDocumentale.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		repoDocumentale.setDatModifica(null);
		repoDocumentale.setFile(pdfFile);
		repoDocumentale.setFileName(null);
		repoDocumentale.setDatCancellazione(null);
		
		repoDocumentale.setCodRuoloOperatore(utente.getCodFisc());
		repoDocumentale.setIdUser(null);

		dataDao.insertRepositoryDocumentale(repoDocumentale);
		return repoDocumentale;
	}

	
	public DataDaoPatologiaIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoPatologiaIf dataDaoPatologia) {
		this.dataDao = dataDaoPatologia;
	}

	public EsenzioneRPraticaEsenzioneDocumento insertEsenzioneRPraticaDocumento(Certificato certificato,
			Long idDocumneto, EsenzioneTPraticaEsenzione esenzione) {

		EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento = new EsenzioneRPraticaEsenzioneDocumento();
		EsenzioneRPraticaEsenzioneDocumentoPK esRPraticaDocPK = new EsenzioneRPraticaEsenzioneDocumentoPK();

		esenzioneRPraticaDocumento.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		esenzioneRPraticaDocumento.setDatModifica(null);
		esenzioneRPraticaDocumento.setDatCancellazione(null);
		esenzioneRPraticaDocumento.setIdUser(null);
		esenzioneRPraticaDocumento.setCodRuoloOperatore(null);
		if (certificato.getData_fine_validita() != null) {
			try {
				Timestamp parsedData = new Timestamp(
						((java.util.Date) df.parse(certificato.getData_fine_validita())).getTime());
				esenzioneRPraticaDocumento.setDatFineValidita(parsedData);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			esenzioneRPraticaDocumento.setDatFineValidita(null);
		}
		esRPraticaDocPK.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
		esRPraticaDocPK.setSkDocumento(idDocumneto);
		esRPraticaDocPK.setSkPraticaEsenzione(new Long(esenzione.getSkPraticaEsenzione()));
		esenzioneRPraticaDocumento.setId(esRPraticaDocPK);

		dataDao.insertEsenzioneRPraticaDocumento(esenzioneRPraticaDocumento);
		return esenzioneRPraticaDocumento;
	}

	public AttestatoEsenzione getAttestatoEsenzione(String cit_id) {
		EsenzioneTDocumento attestato = dataDao.getAttestatoEsenzioneByCodiceFiscale(cit_id);
		if (attestato != null && attestato.getEsenzioneTRepositoryDocumentale() != null)
			return new AttestatoEsenzione(attestato.getEsenzioneTRepositoryDocumentale());
		return null;



	}

	public EsenzioneTRepositoryDocumentale getCertificatoMalattia(String certificato_id) throws IOException {
		EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = dataDao
				.getEsenzioneTRepositoryDocumentale(certificato_id);
		if (esenzioneTRepositoryDocumentale != null) {
			return esenzioneTRepositoryDocumentale;
		}
		return null;
	}

	public File createZipAllegati(String esenzione_id) throws EsenpatException, Exception {
		String fileName;
		File f = null;
		ZipOutputStream z = null;
		ZipEntry entry = null;
		int counter = 1;
		String allegatoName;
		try {
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = dataDao
					.loadFullEsenzioneTPraticaEsenzione(Integer.valueOf(esenzione_id));
			if (esenzioneTPraticaEsenzione != null
					&& esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos() != null
					&& !esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().isEmpty()) {
				fileName = esenzioneTPraticaEsenzione.getNumPratica() + "_"
						+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
				f = File.createTempFile(fileName, ".zip");
				z = new ZipOutputStream(new FileOutputStream(f));
				Integer tipoCcm = dataDao
						.getEsenzioneDDocumentoTipo(Constants.TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA)
						.getSkDocumentoTipo();
				Integer tipoAes = dataDao.getEsenzioneDDocumentoTipo(Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE)
						.getSkDocumentoTipo();
				for (EsenzioneRPraticaEsenzioneDocumento erp : esenzioneTPraticaEsenzione
						.getEsenzioneRPraticaEsenzioneDocumentos()) {

					if (!erp.getEsenzioneTDocumento().getSkTipoDocumento().equals(new Long(tipoAes))
							&& !erp.getEsenzioneTDocumento().getSkTipoDocumento().equals(new Long(tipoCcm))) {

						Set<String> nomiAllegati = new HashSet<String>();
						EsenzioneTRepositoryDocumentale repoDoc = dataDao.getRepositoryDocumentale(
							new Integer(erp.getEsenzioneTDocumento().getSkRepository().intValue()));

							allegatoName = fileName + "_allegato_" + counter;
							counter++;

						nomiAllegati.add(allegatoName);
						if (repoDoc.getFile() == null) {
							z.closeEntry();
							z.close();
							throw new Exception();
						}
						byte[] decoder = Base64.getDecoder().decode(repoDoc.getFile());
						entry = new ZipEntry(allegatoName + ".pdf");
						z.putNextEntry(entry);
						z.write(decoder);
						z.closeEntry();
					}
				}
				z.close();
				f.deleteOnExit();
			} else {
				throw new EsenpatException("La pratica non ha alcun allegato", "Nessun allegato trovato",
						Status.NOT_FOUND.getStatusCode());
			}
		} catch (Exception e) {
			f.delete();
			z.closeEntry();
			z.close();
			e.printStackTrace();
		}
		return f;
	}

	public List<StoricoEsenzione> getStoricoEsenzione(String citId, String esenzioneId) throws Exception {

		EsenzioneTPraticaEsenzione listaStoricoEsenzione = dataDao.getStoricoEsenzione(citId, esenzioneId);
		List<StoricoEsenzione> storico = new ArrayList<StoricoEsenzione>();
		if (listaStoricoEsenzione != null) {
			StoricoEsenzione storicoEsenzione = new StoricoEsenzione(listaStoricoEsenzione, "");
			Utente utenteBeneficiario = storicoEsenzione.getUtenteBeneficiario();
			storico.add(storicoEsenzione);

			if (listaStoricoEsenzione.getEsenzioneSPraticaEsenziones().size() > 0) {
				for (EsenzioneSPraticaEsenzione esenzioneSpratica : listaStoricoEsenzione
						.getEsenzioneSPraticaEsenziones()) {
					storico.add(new StoricoEsenzione(esenzioneSpratica, utenteBeneficiario));
				}
			}
			Collections.sort(storico);
			return storico;
		}
		return null;
	}

	@Transactional
	public EsenzioneTDocumento insertNuovoCertificato(NuovoCertificato certificato, UserInfo utente, CittadinoEsenpat cittadino)
			throws EsenpatException, DocumentException, IOException {

		// controllo i dati obbligatori del body
		if (!Checker.isValorizzato(certificato.getAssistito().getIdAura())
				|| !Checker.isValorizzato(certificato.getCertificatoEsenpat().getCodDiagnosi())
				|| !Checker.isValorizzato(certificato.getCertificatoEsenpat().getCodEsenzione())) {
			throw new EsenpatException(Constants.MSG145);
		} else {

			EsenzioneTDocumento documento = new EsenzioneTDocumento();
			EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();
			EsenzioneTMetadatiDocumento metadati = new EsenzioneTMetadatiDocumento();



			byte[][] XMLS = this.generateXmlPdf(certificato, utente, cittadino);
			byte[] pdf =  Base64.getEncoder().encode(this.generatePdf(XMLS));

			repoDocumentale = this.insertRepositoryDocumentale(certificato, utente, pdf);
			documento = this.insertDocumentoCertificato(certificato, utente, repoDocumentale);
			metadati = this.insertMetadatiDocumento(certificato, utente, documento, pdf);

			return documento;
		}
	}

	/**
	 * Genera il CDA-R2 del certificato in input
	 * @param certificato
	 * @param utente
	 * @return
	 */
	private byte[][] generateXmlPdf(NuovoCertificato certificato, UserInfo utente, CittadinoEsenpat cittadino) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream xmlFile = classloader.getResourceAsStream("/template/docAmbu-esenpat.xml");
		InputStream resXml = null;
		
		String oidDocumento = getOidDocumento(null, Constants.OID_CCM, cittadino.getCodASL());
		final String ROOT="2.16.840.1.113883.2.9.2.10.4.4.10";

    try 
    {
      SAXBuilder builder = new SAXBuilder();

      Namespace rootNs = Namespace.getNamespace("urn:hl7-org:v3");
      Namespace xfaNs = Namespace.getNamespace("xmlns:xfa='http://www.xfa.org/schema/xfa-data/1.0/'");
      
      
      Document doc = (Document) builder.build(xmlFile);
      Element xfaRoot = doc.getRootElement();
      Element xfaData = (Element) xfaRoot.getChildren().get(0);

      Element rootNode =  xfaData.getChild("ClinicalDocument", null);
      


      Element effectiveTime = rootNode.getChild("effectiveTime", null);
      String effectiveDateTime = Converter.getEffectiveTimeHL7();
      effectiveTime.setAttribute(new Attribute("value", effectiveDateTime));
      
      EsenredDAziendasanitaria asl = dataDao.getAziendaSanitariaByCodAsl(cittadino.getCodASL());

      Element idClinical = rootNode.getChild("id", null);
      idClinical.setAttribute(new Attribute("extension", oidDocumento));
      idClinical.setAttribute(new Attribute("root", ROOT +"." + asl.getCodAzienda()));

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
	
				}
    	  		break;
		default:
			break;
		}
      }
      
      Element addr = patientRole.getChild("addr",rootNs);
      addr.addContent(new Element("country",rootNs).setText(cittadino.getCodStatoNascita()));
      addr.addContent(new Element("streetName",rootNs).setText(cittadino.getIndirizzoResidenza()));
      addr.addContent(new Element("postalCode",rootNs).setText(cittadino.getCapResidenza()));
      addr.addContent(new Element("city",rootNs).setText(cittadino.getCittaResidenza()));
      addr.addContent(new Element("censusTract",rootNs).setText(cittadino.getCodComuneNascita()));

      
      
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
      addrBirthPlace.addContent(new Element("country",rootNs).setText(cittadino.getDescStatoNascita()));
      addrBirthPlace.addContent(new Element("city",rootNs).setText(cittadino.getCittaResidenza()));
      addrBirthPlace.addContent(new Element("censusTract",rootNs).setText(cittadino.getCodComuneNascita()));

      Element author = rootNode.getChild("author", null);
      
      Element authorTime = author.getChild("time", null);
      authorTime.setAttribute("value", effectiveDateTime);
      
      Element assignedAuthor = author.getChild("assignedAuthor", null);
      
      Element idAuthor = assignedAuthor.getChild("id", null);
      idAuthor.setAttribute(new Attribute("extension", utente.getCodFisc()));
      
      Element assignedPerson = assignedAuthor.getChild("assignedPerson", null);
      Element authorName = assignedPerson.getChild("name", null);
      authorName.addContent(new Element("family",rootNs).setText(utente.getCognome()));
      authorName.addContent(new Element("given",rootNs).setText(utente.getNome()));
      
      

	  String nomeAzienda = dataDao.getAziendaSanitariaByCodAsl(utente.getCodASL()).getDenominazione();

      Element custodian = rootNode.getChild("custodian", null);
      Element assignedCustodian = custodian.getChild("assignedCustodian", null);
      Element representedCustodianOrganization = assignedCustodian.getChild("representedCustodianOrganization", null);
      Element idCustodian = representedCustodianOrganization.getChild("id", null);
      idCustodian.setAttribute("extension", utente.getCodASL());

      Element nameCustodian = new Element("name", rootNs);
      nameCustodian.setText(nomeAzienda);
      representedCustodianOrganization.addContent(nameCustodian);

      Element componentRoot = rootNode.getChild("component", null);
      Element structuredBody = componentRoot.getChild("structuredBody", null);
      List components = structuredBody.getChildren();
      
      EsenzioneDDiagnosi diagnosi = dataDao.getEsenzioneDDiagnosi(certificato.getCertificatoEsenpat().getCodDiagnosi());
      EsenzioneDEsenzione esenzione = dataDao.getEsenzioneDEsenzioneByDiagnosiId(diagnosi.getSkDiagnosi().toString());
     
      
      for(int i=0; i< components.size(); i++)
      {
    	  Element element = (Element)components.get(i);  
          Element section = element.getChild("section", null);
;
          
          Element title = section.getChild("title", null);
          Element text = section.getChild("text", null);
			Element entry = null;
			Element act = null;
			Element codeEntry = null;

          switch (title.getText()) {
			case "Prestazioni":
			      text.setText(certificato.getCertificatoEsenpat().getGruppoEsenzione());
				break;
			case "Referto":
			      text.setText(certificato.getCertificatoEsenpat().getNotePatologia());
				break;
			case "Diagnosi":
			      text.setText("cod. " + diagnosi.getCodDiagnosi());
		
					entry = new Element("entry", rootNs);
					entry.setAttribute(new Attribute("typeCode", "DRIV"));

					Element observation = new Element("observation", rootNs);
					observation.setAttribute(new Attribute("classCode", "OBS"));
					observation.setAttribute(new Attribute("moodCode", "EVN"));

					codeEntry = new Element("code", rootNs);
					codeEntry.setAttribute(new Attribute("code", diagnosi.getCodDiagnosi()));
					codeEntry.setAttribute(new Attribute("codeSystem", "2.16.840.1.113883.6.1"));
					codeEntry.setAttribute(new Attribute("codeSystemName", "LOINC"));
					codeEntry.setAttribute(new Attribute("displayName", diagnosi.getDescDiagnosi()));



					observation.addContent(codeEntry);

	
			          entry.addContent(observation);
			          section.addContent(entry);
			          
				break;
			case "Esenzione":
			      text.setText("cod. " + esenzione.getCodEsenzione());
			      
			  	// insert entry
					entry = new Element("entry", rootNs);
					entry.setAttribute(new Attribute("typeCode", "DRIV"));

					act = new Element("act", rootNs);
					act.setAttribute(new Attribute("classCode", "ACT"));
					act.setAttribute(new Attribute("moodCode", "EVN"));

					Element templateIdEntry = new Element("templateId", rootNs);
					templateIdEntry.setAttribute(new Attribute("root", "2.16.840.1.113883.2.9.10.1.10.3"));

					codeEntry = new Element("code", rootNs);
					codeEntry.setAttribute(new Attribute("code", esenzione.getCodEsenzione()));
					codeEntry.setAttribute(new Attribute("codeSystem", "2.16.840.1.113883.2.9.6.1.22"));
					codeEntry.setAttribute(new Attribute("codeSystemName", "Catalogo nazionale delle esenzioni"));
					codeEntry.setAttribute(new Attribute("displayName", esenzione.getDescEsenzione()));
		          
			          act.addContent(templateIdEntry);
			          act.addContent(codeEntry);
	
			          entry.addContent(act);
			          section.addContent(entry);
				break;
			default:
				break;
			}
          
      }
      

      
      XMLOutputter xmlOutput = new XMLOutputter();
      
      ByteArrayOutputStream byteArrayout = new ByteArrayOutputStream();
      xmlOutput.output(doc, byteArrayout);

      resXml = new ByteArrayInputStream(byteArrayout.toByteArray());

	   
      //HL7 XML FOP
  	InputStream xmlFop = classloader.getResourceAsStream("/template/docAmbu-fop.xml");
	InputStream resXmlFop = null;
	
      Document docFop = (Document) builder.build(xmlFop);
      Element rootNodeFop = docFop.getRootElement();
      rootNodeFop.addContent(new Element("oid").setText(oidDocumento));
      rootNodeFop.addContent(new Element("effectiveTime").setText(DateUtil.formatDate(new Date(),"dd/MM/yyyy")));
      
      Element patientFop = rootNodeFop.getChild("patient", null);
      patientFop.addContent(new Element("family").setText(cittadino.getCognome()));
      patientFop.addContent(new Element("given").setText(cittadino.getNome()));
      patientFop.addContent(new Element("birthTime").setText(cittadino.getDataNascita()));
      patientFop.addContent(new Element("id").setText(cittadino.getCodFiscale()));
      
      Element report = new Element("report");
      report.addContent(new Element("title").setText("Referto"));
      report.addContent(new Element("description").setText(certificato.getCertificatoEsenpat().getNotePatologia()));
      rootNodeFop.addContent(report);

      Element componentFop = new Element("component");
      componentFop.addContent(new Element("title").setText("Codice e descrizione esenzione"));
      componentFop.addContent(new Element("code").setText("cod. " + esenzione.getCodEsenzione()));
      componentFop.addContent(new Element("name").setText(esenzione.getDescEsenzione()));
      rootNodeFop.addContent(componentFop);
      Element componentFop2 = new Element("component");
      componentFop2.addContent(new Element("title").setText("Codice e descrizione diagnosi"));
      if(diagnosi.getCodDiagnosi().startsWith("F"))
      {
    	  componentFop2.addContent(new Element("code").setText("-"));
          componentFop2.addContent(new Element("name").setText("-"));
      }
      else
      {
    	  componentFop2.addContent(new Element("code").setText("cod. " + diagnosi.getCodDiagnosi()));
          componentFop2.addContent(new Element("name").setText(diagnosi.getDescDiagnosi()));
      }
      rootNodeFop.addContent(componentFop2);
      
      
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

	private EsenzioneTMetadatiDocumento insertMetadatiDocumento(NuovoCertificato certificato, UserInfo utente,
			EsenzioneTDocumento documento, byte[] pdfFile) {
		String tipoAzione = "INSERIMENTO";
		String codApplicativoRichiesta = "ESENPAT";
		String mimeType = "application/pdf";
		String codCertificatoMalattia = "2.16.840.1.113883.2.9.3.3.6.1.5";

		EsenzioneTMetadatiDocumento metadati = new EsenzioneTMetadatiDocumento();

		metadati.setAssettoOrganizzativo(null);
		metadati.setCodApplicativoRichiesta(codApplicativoRichiesta);
		metadati.setCodicePin(null);
		metadati.setCodMimeType(mimeType); 
		metadati.setHashDoc(null); // al momento settato a null
		metadati.setSizeDoc(null); // dimensione del documento espressa in byte
		metadati.setCodOscuraScaricoCittadino("N");
		metadati.setCodPrivacyCittadino("1");
		metadati.setCodTipoAttivitaClinica(null);
		metadati.setCodTipoAzione(tipoAzione);
		metadati.setIdAula(null);
		metadati.setIdRichiesta(null);
		metadati.setIdRichiestaUtente(null);
		metadati.setNre(null);
		metadati.setFlagSoggettoLeggiSpeciali(false);
		metadati.setFlagScaricaCittadino(true);
		metadati.setCodTipoStrutturaProdDoc(null);
		metadati.setCodMatricola(null);
		metadati.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		metadati.setDatCancellazione(null);
		metadati.setDatModifica(null);

		// DOCUMENTO
		metadati.setSkDocumento(new Long(documento.getSkDocumento()));

		// OPERATORE
		EsenredDAziendasanitaria azienda = dataDao.getAziendaSanitariaByCodAsl(utente.getCodASL());
		metadati.setCodAziendaRichiesta(azienda.getCodRegione()+azienda.getCodAzienda()); 
		metadati.setCodStruttura(new Integer(azienda.getCodAzienda())); 
		metadati.setRuoloUtente(null);
		metadati.setCodRuoloOperatore(null);
		metadati.setIdUser(null);

		// DATI PAZIENTE
		metadati.setIdAuraPaziente(new Long(certificato.getAssistito().getIdAura()));
		metadati.setCodComunePaziente(certificato.getAssistito().getComuneNascita());
		metadati.setCodFiscalePaziente(certificato.getAssistito().getCodFiscale());
		metadati.setCodSessoPaziente(certificato.getAssistito().getCodSesso());
		metadati.setCodStatoNascitaPaziente(certificato.getAssistito().getCodStatoNascita());
		metadati.setCognomePaziente(certificato.getAssistito().getCognome());
		metadati.setNomePaziente(certificato.getAssistito().getNome());
		metadati.setIdGenitoreTutorePaziente(null); // null
		Timestamp parsedData = null;
		try {
			if (certificato.getAssistito().getDataNascita() != null) {
				Date date = sdfc.parse(certificato.getAssistito().getDataNascita());
				parsedData = new Timestamp(date.getTime());
				metadati.setDatNascitaPaziente(parsedData);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// MEDICO
		metadati.setMedicoValidatore(null);
		metadati.setMedicoRedattore(utente.getCodFisc()); // inserire firmatario documento (dopo cdu Firma digitale)

		// EPISODIO
		metadati.setIdEpisodio(null);
		metadati.setCodTipoAzioneEpisodio(null);

		metadati.setDatInizioEpisodio(new Timestamp(System.currentTimeMillis()));

		metadati.setDatFineEpisodio(metadati.getDatInizioEpisodio());
		metadati.setCodTipoEpisodio(null);

		metadati.setCodTipoDocumentoMedio(null); // inserire null
		// vedi tabella
		// se tipoDocumento ccm certificati di malattia (vedi tabella 2.3.1 affinity)
		metadati.setCodTipoDocumentoAlto(codCertificatoMalattia);

		// FIRMA -> CDU FIRMA
		metadati.setCodTipoFirma(null);
		metadati.setDatFirma(null);
		metadati.setFlagFirmato(null);

		dataDao.insertMetadatiDocumento(metadati);
		return metadati;
	}

	public List<CertificatoAssistito> getListaCertificatiByCodiceFiscaleAssistito(String cit_id, String cfMed) {
		List<CertificatoAssistito> certificatiAssistito = null;

		List<EsenzioneTDocumento> esenzioneTDocumentos = dataDao.getListaCertificati(cit_id, null);
		if (esenzioneTDocumentos != null && esenzioneTDocumentos.size() > 0) {
			certificatiAssistito = new ArrayList<CertificatoAssistito>(esenzioneTDocumentos.size());
			for (EsenzioneTDocumento esenzioneTDocumento : esenzioneTDocumentos) {
				if(!Checker.isValorizzato(cfMed) || (esenzioneTDocumento.getEsenzioneTMetadatiDocumento() != null && esenzioneTDocumento.getEsenzioneTMetadatiDocumento().getMedicoRedattore().equalsIgnoreCase(cfMed))) {
					certificatiAssistito.add(new CertificatoAssistito(esenzioneTDocumento));
				}
			}
		}
		return certificatiAssistito;
	}

	public List<Tipologia> getListaDocumentoTipo(String gruppo) {
		List<Tipologia> listaTipologie = null;

		// List<EsenzioneDDocumentoTipo> esenzioneDDocumentoTipo =
		// dataDao.getListaDocumentoTipo();
		List<EsenzioneDDocumentoTipo> esenzioneDDocumentoTipo = dataDao.getListaDocumentoTipoPerCaricaAllegati(gruppo);
		if (esenzioneDDocumentoTipo != null && esenzioneDDocumentoTipo.size() > 0) {
			listaTipologie = new ArrayList<Tipologia>(esenzioneDDocumentoTipo.size());
			for (EsenzioneDDocumentoTipo eddt : esenzioneDDocumentoTipo) {
				listaTipologie.add(new Tipologia(eddt));
			}
		}
		return listaTipologie;
	}

	public EsenzioneTRepositoryDocumentale getDocumento(String pkRepository) {
		return dataDao.getEsenzioneTRepositoryDocumentale(pkRepository);
	}

	public String getOidDocumento(EsenzioneTDocumento documento, String type, String codAsl) {

		String oid = type + ".";

		String progressivo = "1";
		

		oid += progressivo + ".";
			
			// versione
		String versione = "1";
		if (type.equalsIgnoreCase(Constants.OID_AES)) {
			if (documento != null && documento.getOidDocumento() != null) {
				String oidTemp = documento.getOidDocumento();
				oidTemp = oidTemp.substring(oidTemp.lastIndexOf(".") + 1);
				Integer vers = Integer.valueOf(oidTemp);
				versione = (++vers).toString();
			}
		}
		oid += versione;
		
		// query get oid documento di quella tipologia
		return oid;
	}

	// CCM
	public EsenzioneTDocumento insertDocumentoCertificato(NuovoCertificato certificato, UserInfo utente,
			EsenzioneTRepositoryDocumentale repoDocumentale){

		EsenzioneTDocumento docCertificato = new EsenzioneTDocumento();
		docCertificato.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		docCertificato.setDatModifica(null);
		docCertificato.setDatCancellazione(null);
		docCertificato.setDatFineValidita(null);

		Timestamp parsedData = null;
		try {
			if (certificato.getCertificatoEsenpat().getData_invio() != null) {
				Date date = sdfc.parse(certificato.getCertificatoEsenpat().getData_invio());
				parsedData = new Timestamp(date.getTime());
				docCertificato.setDatDocumento(parsedData);
				docCertificato.setDatInizioValidita(parsedData);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		docCertificato.setDescNote(null);
		docCertificato.setFlagConformitaDocumento(null);
		docCertificato.setDescEstesaPatologiaCertificato(certificato.getCertificatoEsenpat().getNotePatologia());

//    Inserimento skTipoDocumento by Codice tipo documento (ccm,aes,vin,cmr..)
		docCertificato.setSkTipoDocumento(new Long(1)); // di defaul � ccm -> 1

		docCertificato.setSkRepository(Long.valueOf(repoDocumentale.getSkRepository()));
		docCertificato.setSkTipologiaStatoDocumento(new Long(dataDao
				.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_DA_FIRMARE).getSkDocumentoStato()));

		docCertificato.setSkDiagnosi(
				Long.decode(dataDao.getEsenzioneDDiagnosi(certificato.getCertificatoEsenpat().getCodDiagnosi())
						.getSkDiagnosi().toString()));

		docCertificato.setCodRuoloOperatore(null);
		docCertificato.setIdUserid(null);
		docCertificato.setCodTipoUser("B");
		docCertificato.setFlagConformitaDocumento(null);


		// TODO non presente sui documenti
		docCertificato.setCodiceFiscaleCittadino(certificato.getAssistito().getCodFiscale());
		
		
		docCertificato.setOidDocumento(getOidDocumento(null, Constants.OID_CCM, certificato.getAssistito().getCodAsl()));
		
		dataDao.insertDocumentoPraticaEsenzione(docCertificato);
		return docCertificato;
	}


	public List<EsenzioneTDocumento> caricaAllegati(List<Documenti> documenti, Cittadino cittadino, String codDiagnosi)
			throws EsenpatException {

		List<EsenzioneTDocumento> listaDocumentiInseriti = new ArrayList<EsenzioneTDocumento>();

		for (Documenti documento : documenti) {
			EsenzioneTRepositoryDocumentale repoDoc = new EsenzioneTRepositoryDocumentale();
			repoDoc.setDatArchiviazione(new Timestamp(System.currentTimeMillis()));
			repoDoc.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			repoDoc.setDatModifica(null);
			repoDoc.setFile(documento.getFileBase64().getBytes());
			repoDoc.setFileName(documento.getFilename());
			repoDoc.setDatCancellazione(null);
			// utente che ha effettuato l'accesso - codRuoloOp e IdUser
			repoDoc.setCodRuoloOperatore(null);
			repoDoc.setIdUser(null);
			repoDoc.setDescFile("nuovaEsenzione");

			repoDoc = dataDao.insertAllegatoRepositoryDocumentale(repoDoc);

			// inserimento nella T_DOCUMENTO

			EsenzioneTDocumento docEsenzione = new EsenzioneTDocumento();
			docEsenzione.setSkTipoDocumento(Long.decode(
					dataDao.getEsenzioneDDocumentoTipo(documento.getTipologia()).getSkDocumentoTipo().toString()));
			docEsenzione.setSkRepository(Long.valueOf(repoDoc.getSkRepository()));
			docEsenzione.setDatCreazione(new Timestamp(System.currentTimeMillis()));
			docEsenzione.setDatModifica(null);
			docEsenzione.setDatCancellazione(null);
			docEsenzione.setDescNote(null);
			docEsenzione.setFlagConformitaDocumento(null);
			docEsenzione.setDescEstesaPatologiaCertificato(null);
			docEsenzione.setCodRuoloOperatore(null);
			docEsenzione.setCodTipoUser("B");
			docEsenzione.setFlagConformitaDocumento(null);
			docEsenzione.setCodiceFiscaleCittadino(cittadino.getCodFiscale());

			docEsenzione.setSkTipologiaStatoDocumento(new Long(dataDao
					.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_VALIDO).getSkDocumentoStato()));
			if (codDiagnosi == null) {
				docEsenzione.setSkDiagnosi(null);
			} else {
				docEsenzione.setSkDiagnosi(
						Long.decode(dataDao.getEsenzioneDDiagnosi(codDiagnosi).getSkDiagnosi().toString()));
			}

			Timestamp parsedData = null;
			try {
				if (Checker.isValorizzato(documento.getDataRilascio())) {
					Date date;
					if (Checker.isValorizzato(documento.getDataFineValidita())) {
						date = sdfc.parse(documento.getDataFineValidita());
						parsedData = new Timestamp(date.getTime());
						docEsenzione.setDatFineValidita(parsedData);
					} else {
						docEsenzione.setDatFineValidita(null);
					}
					date = sdfc.parse(documento.getDataRilascio());
					parsedData = new Timestamp(date.getTime());
					docEsenzione.setDatInizioValidita(parsedData);
					docEsenzione.setDatDocumento(parsedData);
				} else {
					throw new EsenpatException(Constants.MSG145);
				}
			} catch (ParseException e) {
				throw new EsenpatException(Constants.MSG145);
			}

			// TODO attesa CSI - valorizzato secondo le norme CSI Piemonte
			docEsenzione.setOidDocumento(null);
			docEsenzione.setIdUserid(null);

			dataDao.insertDocumentoPraticaEsenzione(docEsenzione);

			listaDocumentiInseriti.add(docEsenzione);

		}

		return listaDocumentiInseriti;
	}

	public EsenzioneRPraticaEsenzioneDocumento insertRPraticaDocumento(EsenzioneTDocumento documento,
			EsenzioneTPraticaEsenzione esenzione) {

		EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento = new EsenzioneRPraticaEsenzioneDocumento();
		EsenzioneRPraticaEsenzioneDocumentoPK esRPraticaDocPK = new EsenzioneRPraticaEsenzioneDocumentoPK();

		esenzioneRPraticaDocumento.setDatCreazione(new Timestamp(System.currentTimeMillis()));
		esenzioneRPraticaDocumento.setDatModifica(null);
		esenzioneRPraticaDocumento.setDatCancellazione(null);
		esenzioneRPraticaDocumento.setIdUser(null);
		esenzioneRPraticaDocumento.setCodRuoloOperatore(null);
		esenzioneRPraticaDocumento.setDatFineValidita(documento.getDatFineValidita());

		esRPraticaDocPK.setDatInizioValidita(new Timestamp(System.currentTimeMillis()));
		esRPraticaDocPK.setSkDocumento(new Long(documento.getSkDocumento()));
		esRPraticaDocPK.setSkPraticaEsenzione(new Long(esenzione.getSkPraticaEsenzione()));
		esenzioneRPraticaDocumento.setId(esRPraticaDocPK);

		dataDao.insertEsenzioneRPraticaDocumento(esenzioneRPraticaDocumento);
		return esenzioneRPraticaDocumento;
	}

	public EsenzioneTDocumento getDocumentoBySkDocumento(String idDocumento) {
		return dataDao.getEsenzioneTDocumentoBySkDocumento(idDocumento);
	}

	/**
	 * Trasforma l'XML in input in un PDF inettando i dati in una struttura XFA
	 * @param XML
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private byte[] generatePdf(byte[][] XMLS) throws DocumentException, IOException {
		ByteArrayOutputStream fopPdfOut = new ByteArrayOutputStream();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

	   	byte[] xmlCda = XMLS[0];
	   	InputStream xmlFop = new ByteArrayInputStream(XMLS[1]);

	   	
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
            	
            	InputStream xslDoc = classloader.getResourceAsStream("/template/docAmbu.xsl");
            	
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, fopPdfOut);
    		    TransformerFactory factory = TransformerFactory.newInstance();


                Transformer transformer = factory.newTransformer(new StreamSource(xslDoc));

                Source src = new StreamSource(xmlFop);
    		    Result res = new SAXResult(fop.getDefaultHandler());
    		    transformer.transform(src, res);
    		  
    		} catch (TransformerException ex) {
                ex.printStackTrace();
    		} finally {
    			fopPdfOut.close();
    		}
            
//          return fopPdfOut.toByteArray();
          
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
		    
            info.setTitle("Certificato Condizione Malattia");
            info.setSubject("document desc");
            info.setAuthor("Csi Piemonte");
            info.setCreator("Csi Piemonte");
            info.setProducer("Csi Piemonte");
            info.setKeywords("Apache, PdfBox, XMP, PDF");
            info.setCreationDate(Calendar.getInstance());
            info.setModificationDate(Calendar.getInstance());
            info.setTrapped("Unknown");

            
            
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


			   document.setDocumentInformation(info);
	            document.setVersion(1.7f);

			PDMetadata metadataStream = new PDMetadata(document);
			catalog.setMetadata( metadataStream );
			
			XmpSerializer serializer = new XmpSerializer();
			serializer.serialize(metadata, baos, false);
			metadataStream.importXMPMetadata( baos.toByteArray() );
            
		 
			    COSStream cosos = new COSStream();
		
			    OutputStream os = cosos.createOutputStream(COSName.FLATE_DECODE);
			   	 InputStream xmlCda2 = new ByteArrayInputStream(xmlCda);
	             Source src = new StreamSource(xmlCda2);
			      Transformer transformer = TransformerFactory.newInstance().newTransformer();
			      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			      transformer.transform(src, new StreamResult(os));

				 os.close();


					
				

			        // Add the entry to the embedded file tree and set in the document.
			        Map efMap = new HashMap();
			        efMap.put("datasets", cosos);
			        final PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();
			        efTree.setNames(efMap);

			        
			        // Attachments are stored as part of the "/Names" dictionary in the document /Catalog
			        final PDDocumentNameDictionary names = new PDDocumentNameDictionary(document.getDocumentCatalog());
//			        names.getCOSObject().setItem("XFAResources", new PDXFAResource(cosos));
			        names.getCOSObject().setItem("XFAResources", efTree);
//			        names.setEmbeddedFiles(efTree);
			        
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

	public InputStream generateXmlMario() {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream xmlFile = classloader.getResourceAsStream("/template/docAmbu-esenpat.xml");
		return xmlFile;
	}

	public EsenzioneTDocumento getEsenzioneTDocumentoAttestato(String codFiscale) {
		return dataDao.getAttestatoEsenzioneByCodiceFiscale(codFiscale);
	}

	public void copyTDocumentoToSDocumento(EsenzioneTDocumento attestato, EsenzioneTPraticaEsenzione pratica) {
		// Copio nello storico
		EsenzioneSDocumento doc = new EsenzioneSDocumento(attestato);
		doc.setDatInizioValidita(pratica.getDatModifica() != null ? pratica.getDatModifica()
				: pratica.getDatCreazione() != null ? pratica.getDatCreazione() : null);
		doc.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
		dataDao.insertDocumentoStoricoPraticaEsenzione(doc);
	}

	public void annullaAttestato(EsenzioneTDocumento attestato) throws Exception {
		// Aggiorno il vecchio attestato
		attestato.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
		attestato.setDatModifica(new Timestamp(System.currentTimeMillis()));
		attestato.setDescNote(null);

		EsenzioneDDocumentoStato st = dataDao
				.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_ANNULLATO);
		attestato.setSkTipologiaStatoDocumento(st.getSkDocumentoStato().longValue());
		dataDao.updateEsenzioneTDocumento(attestato);

	}
	
	/**
	 * Genera il CDA-R2 del certificato in input
	 * @param certificato
	 * @param utente
	 * @return
	 */
	public byte[][] generateXmlCertificatoFirmato(NuovoCertificato certificato, UserInfo utente, CittadinoEsenpat cittadino) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream xmlFile = classloader.getResourceAsStream("/template/docAmbu-esenpat.xml");
		InputStream resXml = null;
		
		String oidDocumento = getOidDocumento(null, Constants.OID_CCM, cittadino.getCodASL());
		final String ROOT="2.16.840.1.113883.2.9.2.10.4.4.10";
    try 
    {
      SAXBuilder builder = new SAXBuilder();

      Namespace rootNs = Namespace.getNamespace("urn:hl7-org:v3");
      Namespace xfaNs = Namespace.getNamespace("xmlns:xfa='http://www.xfa.org/schema/xfa-data/1.0/'");
      
      
      Document doc = (Document) builder.build(xmlFile);
      Element xfaRoot = doc.getRootElement();
      Element xfaData = (Element) xfaRoot.getChildren().get(0);

      Element rootNode =  xfaData.getChild("ClinicalDocument", null);
      


      Element effectiveTime = rootNode.getChild("effectiveTime", null);
      String effectiveDateTime = Converter.getEffectiveTimeHL7();
      effectiveTime.setAttribute(new Attribute("value", effectiveDateTime));
      
      EsenredDAziendasanitaria asl = dataDao.getAziendaSanitariaByCodAsl(cittadino.getCodASL());

      Element idClinical = rootNode.getChild("id", null);
      idClinical.setAttribute(new Attribute("extension", oidDocumento));
      idClinical.setAttribute(new Attribute("root", ROOT +"." + asl.getCodAzienda()));

      Element setIdClinical = rootNode.getChild("setId", null);
      setIdClinical.setAttribute(new Attribute("assigningAuthorityName", asl.getDenominazione()));
      setIdClinical.setAttribute(new Attribute("extension", oidDocumento));
      setIdClinical.setAttribute(new Attribute("root", ROOT +"." + asl.getCodAzienda()));

      
      Element recordTarget = rootNode.getChild("recordTarget", null);
      Element patientRole = recordTarget.getChild("patientRole", null);

      
      ////////// START PAZIENTE ////////////// 

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
      
      Element addr = patientRole.getChild("addr",rootNs);
      addr.addContent(new Element("country",rootNs).setText(cittadino.getCodStatoNascita()));
      addr.addContent(new Element("streetName",rootNs).setText(cittadino.getIndirizzoResidenza()));
      addr.addContent(new Element("postalCode",rootNs).setText(cittadino.getCapResidenza()));
      addr.addContent(new Element("city",rootNs).setText(cittadino.getCittaResidenza()));
      addr.addContent(new Element("censusTract",rootNs).setText(cittadino.getCodComuneNascita()));

      
      
      Element patient = patientRole.getChild("patient", null);
      
      
      Element name = patient.getChild("name", null);
			name.addContent(new Element("family", rootNs).setText(cittadino.getCognome()));
			name.addContent(new Element("given", rootNs).setText(cittadino.getNome()));
//      patient.addContent(name);      
      
      Element administrativeGenderCode = patient.getChild("administrativeGenderCode", null);
      administrativeGenderCode.setAttribute("code", cittadino.getCodSesso());
//      patient.addContent(administrativeGenderCode);
      
      Element birthTime = patient.getChild("birthTime", null);
      String birthTimeDate = Converter.getDataXml(cittadino.getDataNascita().toString());
      birthTime.setAttribute("value", birthTimeDate);

      
      Element birthplace = patient.getChild("birthplace", null);
      Element place = birthplace.getChild("place", null);
      Element addrBirthPlace = place.getChild("addr", null);
      addrBirthPlace.setAttribute(new Attribute("use", "H"));
      addrBirthPlace.addContent(new Element("country",rootNs).setText(cittadino.getDescStatoNascita()));
      addrBirthPlace.addContent(new Element("city",rootNs).setText(cittadino.getCittaResidenza()));
      addrBirthPlace.addContent(new Element("censusTract",rootNs).setText(cittadino.getCodComuneNascita()));

      Element author = rootNode.getChild("author", null);
      
      Element authorTime = author.getChild("time", null);
      authorTime.setAttribute("value", effectiveDateTime);
      
      Element assignedAuthor = author.getChild("assignedAuthor", null);
      
      Element idAuthor = assignedAuthor.getChild("id", null);
      idAuthor.setAttribute(new Attribute("extension", utente.getCodFisc()));
      
      Element assignedPerson = assignedAuthor.getChild("assignedPerson", null);
      Element authorName = assignedPerson.getChild("name", null);
      authorName.addContent(new Element("family",rootNs).setText(utente.getCognome()));
      authorName.addContent(new Element("given",rootNs).setText(utente.getNome()));
      
      
  
	  String nomeAzienda = dataDao.getAziendaSanitariaByCodAsl(utente.getCodASL()).getDenominazione();

      Element custodian = rootNode.getChild("custodian", null);
      Element assignedCustodian = custodian.getChild("assignedCustodian", null);
      Element representedCustodianOrganization = assignedCustodian.getChild("representedCustodianOrganization", null);
      Element idCustodian = representedCustodianOrganization.getChild("id", null);
      idCustodian.setAttribute("extension", utente.getCodASL());

      Element nameCustodian = new Element("name", rootNs);
      nameCustodian.setText(nomeAzienda);
      representedCustodianOrganization.addContent(nameCustodian);

	    Element legalAuth = rootNode.getChild("legalAuthenticator", null);

		Element timeLegal = new Element("time", rootNs);
		timeLegal.setAttribute(new Attribute("value", effectiveDateTime));
		legalAuth.addContent(timeLegal);
		
		Element signatureCode = new Element("signatureCode", rootNs);
		signatureCode.setAttribute(new Attribute("code", "S"));
		legalAuth.addContent(signatureCode);
		
		Element assignedEntity = new Element("assignedEntity", rootNs);
		Element idLegal = new Element("id", rootNs);
		idLegal.setAttribute(new Attribute("extension", utente.getCodFisc()));
		idLegal.setAttribute(new Attribute("root", "2.16.840.1.113883.6.1"));
		idLegal.setAttribute(new Attribute("assigningAuthorityName", "Ministero Economia e Finanze"));
		
		Element assignedPersonLegal = new Element("assignedPerson", rootNs);
		Element nameLegal = new Element("name", rootNs);
		nameLegal.addContent(new Element("given", rootNs).setText(utente.getNome()));
		nameLegal.addContent(new Element("family", rootNs).setText(utente.getCognome()));
		assignedPersonLegal.addContent(nameLegal);
		
		assignedEntity.addContent(idLegal);
		assignedEntity.addContent(assignedPersonLegal);
		legalAuth.addContent(assignedEntity);
				

      Element componentRoot = rootNode.getChild("component", null);
      Element structuredBody = componentRoot.getChild("structuredBody", null);
      List components = structuredBody.getChildren();
      
      EsenzioneDDiagnosi diagnosi = dataDao.getEsenzioneDDiagnosi(certificato.getCertificatoEsenpat().getCodDiagnosi());
      EsenzioneDEsenzione esenzione = dataDao.getEsenzioneDEsenzioneByDiagnosiId(diagnosi.getSkDiagnosi().toString());
     
      
      for(int i=0; i< components.size(); i++)
      {
    	  Element element = (Element)components.get(i);  
          Element section = element.getChild("section", null);

          
          Element title = section.getChild("title", null);
          Element text = section.getChild("text", null);
			Element entry = null;
			Element act = null;
			Element codeEntry = null;

          switch (title.getText()) {
			case "Prestazioni":
			      text.setText(certificato.getCertificatoEsenpat().getGruppoEsenzione());
				break;
			case "Referto":
			      text.setText(certificato.getCertificatoEsenpat().getNotePatologia());
				break;
			case "Diagnosi":
			      text.setText("cod. " + diagnosi.getCodDiagnosi());
			      
			   // insert entry
					entry = new Element("entry", rootNs);
					entry.setAttribute(new Attribute("typeCode", "DRIV"));

					Element observation = new Element("observation", rootNs);
					observation.setAttribute(new Attribute("classCode", "OBS"));
					observation.setAttribute(new Attribute("moodCode", "EVN"));

					codeEntry = new Element("code", rootNs);
					codeEntry.setAttribute(new Attribute("code", diagnosi.getCodDiagnosi()));
					codeEntry.setAttribute(new Attribute("codeSystem", "2.16.840.1.113883.6.1"));
					codeEntry.setAttribute(new Attribute("codeSystemName", "LOINC"));
					codeEntry.setAttribute(new Attribute("displayName", diagnosi.getDescDiagnosi()));



					observation.addContent(codeEntry);

	
			          entry.addContent(observation);
			          section.addContent(entry);
			          
				break;
			case "Esenzione":
			      text.setText("cod. " + esenzione.getCodEsenzione());
			      
			  	// insert entry
					entry = new Element("entry", rootNs);
					entry.setAttribute(new Attribute("typeCode", "DRIV"));

					act = new Element("act", rootNs);
					act.setAttribute(new Attribute("classCode", "ACT"));
					act.setAttribute(new Attribute("moodCode", "EVN"));

					Element templateIdEntry = new Element("templateId", rootNs);
					templateIdEntry.setAttribute(new Attribute("root", "2.16.840.1.113883.2.9.10.1.10.3"));

					codeEntry = new Element("code", rootNs);
					codeEntry.setAttribute(new Attribute("code", esenzione.getCodEsenzione()));
					codeEntry.setAttribute(new Attribute("codeSystem", "2.16.840.1.113883.2.9.6.1.22"));
					codeEntry.setAttribute(new Attribute("codeSystemName", "Catalogo nazionale delle esenzioni"));
					codeEntry.setAttribute(new Attribute("displayName", esenzione.getDescEsenzione()));
		          
			          act.addContent(templateIdEntry);
			          act.addContent(codeEntry);
	
			          entry.addContent(act);
			          section.addContent(entry);
				break;
			default:
				break;
			}
          
      }
      
      
	   XMLOutputter xmlOutput = new XMLOutputter();
	      
      ByteArrayOutputStream byteArrayout = new ByteArrayOutputStream();
      xmlOutput.output(doc, byteArrayout);

      resXml = new ByteArrayInputStream(byteArrayout.toByteArray());
      
      //HL7 XML FOP SIGNED
    	InputStream xmlFop = classloader.getResourceAsStream("/template/docAmbu-fop.xml");
    	InputStream resXmlFop = null;
  	
        Document docFop = (Document) builder.build(xmlFop);
        Element rootNodeFop = docFop.getRootElement();
        rootNodeFop.addContent(new Element("oid").setText(oidDocumento));
        rootNodeFop.addContent(new Element("effectiveTime").setText(DateUtil.formatDate(new Date(),"dd/MM/yyyy")));
        
        
        //header ASL info header
        String headerAsl = "";
        String headerAddress = "";
        String headerContact = "";
        String headerLegal = "";
        
        
        if(!StringUtils.isEmpty(asl.getDescAzienda())) {
        	headerAsl = asl.getDescAzienda();
        }
        
        if(!StringUtils.isEmpty(asl.getIndirizzo())) {
        	headerAddress = "Sede legale: " + asl.getIndirizzo();
        }
        
        if(!StringUtils.isEmpty(asl.getRecapitiTelefonici())) 
        {
        	headerContact = asl.getRecapitiTelefonici();
        }
             
        if(!StringUtils.isEmpty(asl.getEmail())) {
        	headerLegal = "PEC: " + asl.getEmail();
        }
        
        if(!StringUtils.isEmpty(asl.getPartitaIva())) {
        	headerLegal += " � Codice Fiscale/Partita Iva: " + asl.getPartitaIva();
        }
        
        rootNodeFop.addContent(new Element("headerAsl").setText(headerAsl));
        rootNodeFop.addContent(new Element("headerAddress").setText(headerAddress));
        rootNodeFop.addContent(new Element("headerContact").setText(headerContact));
        rootNodeFop.addContent(new Element("headerLegal").setText(headerLegal));

        
        Element authorFop = rootNodeFop.getChild("author", null);
        authorFop.addContent(new Element("family").setText(utente.getCognome()));
        authorFop.addContent(new Element("given").setText(utente.getNome()));
        authorFop.addContent(new Element("id").setText(utente.getCodFisc()));
        
        Element patientFop = rootNodeFop.getChild("patient", null);
        patientFop.addContent(new Element("family").setText(cittadino.getCognome()));
        patientFop.addContent(new Element("given").setText(cittadino.getNome()));
        patientFop.addContent(new Element("birthTime").setText(cittadino.getDataNascita()));
        patientFop.addContent(new Element("id").setText(cittadino.getCodFiscale()));
        
        Element report = new Element("report");
        report.addContent(new Element("title").setText("Referto"));
        report.addContent(new Element("description").setText(certificato.getCertificatoEsenpat().getNotePatologia()));
        rootNodeFop.addContent(report);

        Element componentFop = new Element("component");
        componentFop.addContent(new Element("title").setText("Codice e descrizione esenzione"));
        componentFop.addContent(new Element("code").setText("cod. " + esenzione.getCodEsenzione()));
        componentFop.addContent(new Element("name").setText(esenzione.getDescEsenzione()));
        rootNodeFop.addContent(componentFop);
        Element componentFop2 = new Element("component");
        componentFop2.addContent(new Element("title").setText("Codice e descrizione diagnosi"));
        if(diagnosi.getCodDiagnosi().startsWith("F"))
        {
      	    componentFop2.addContent(new Element("code").setText("-"));
            componentFop2.addContent(new Element("name").setText("-"));
        }
        else
        {
            componentFop2.addContent(new Element("code").setText("cod. " + diagnosi.getCodDiagnosi()));
            componentFop2.addContent(new Element("name").setText(diagnosi.getDescDiagnosi()));
        }

        rootNodeFop.addContent(componentFop2);
        
        
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
	
	
	public byte[] generateSignedPdf(byte[][] XMLS) throws DocumentException, IOException 
	{
		
		ByteArrayOutputStream fopPdfOut = new ByteArrayOutputStream();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

	   	byte[] xmlCda = XMLS[0];
	   	InputStream xmlFop = new ByteArrayInputStream(XMLS[1]);

	   	
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
            	
            	InputStream xslDoc = classloader.getResourceAsStream("/template/docAmbuSigned.xsl");
            	
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, fopPdfOut);
    		    TransformerFactory factory = TransformerFactory.newInstance();

//                Transformer transformer = factory.newTransformer(new StreamSource(new File("C://template/docAmbu.xsl")));
                Transformer transformer = factory.newTransformer(new StreamSource(xslDoc));

                Source src = new StreamSource(xmlFop);
    		    Result res = new SAXResult(fop.getDefaultHandler());
    		    transformer.transform(src, res);
    		  
    		} catch (TransformerException ex) {
                ex.printStackTrace();
    		} finally {
    			fopPdfOut.close();
    		}
;
          
        }catch(Exception e){
            e.printStackTrace();
        }
		
	
	
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
			baos.write(prefix.getBytes("UTF-8")); 
			serializer.serialize(metadata, baos, false);
			baos.write(suffix.getBytes("UTF-8"));

			metadataStream.importXMPMetadata( baos.toByteArray() );
		  
				 
			    COSStream cosos = new COSStream();
			    
			    OutputStream os = cosos.createOutputStream(COSName.FLATE_DECODE);
			   	 InputStream xmlCda2 = new ByteArrayInputStream(xmlCda);
	             Source src = new StreamSource(xmlCda2);

			      Transformer transformer = TransformerFactory.newInstance().newTransformer();
			      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			      transformer.transform(src, new StreamResult(os));
	
				 os.close();
						

			        // Add the entry to the embedded file tree and set in the document.
			        Map efMap = new HashMap();
			        efMap.put("datasets", cosos);
			        final PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();
			        efTree.setNames(efMap);
			        
			        // Attachments are stored as part of the "/Names" dictionary in the document /Catalog
			        final PDDocumentNameDictionary names = new PDDocumentNameDictionary(document.getDocumentCatalog());
			        names.getCOSObject().removeItem(COSName.LIMITS);
			        
			        names.getCOSObject().setItem("XFAResources", efTree);
			        document.getDocumentCatalog().setNames(names);
			     		        
			        
            document.save(out);		    
		    document.close();
		    
		    
		    return out.toByteArray();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		} 
		
			return null;
	}
	
	public byte[] getDocumento(String skDocumento, UserInfo userInfo,
			byte[] pdf, List<Ruolo> ruoli)
		{
		
		log.info("getDocumento", " BEGIN");
		
		try {		//Aggiorno firma sul PDF ->cda2
			
			log.info("getDocumento", " skDocumento: " + skDocumento);
			log.info("getDocumento", " cf: " + userInfo.getCodFisc());
			log.info("getDocumento", " pdf.length" + pdf.length);
			log.info("getDocumento", " ruoli.size: " + ruoli.size());
			byte[] tempPdf = Base64.getDecoder().decode(pdf);
			EsenzioneTDocumento doc = dataDao.getDocumentoCertificato(skDocumento);
			
			CertificatoEsenpat certificatoEsenpat = new CertificatoEsenpat();
			certificatoEsenpat.setCodEsenzione(doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
			certificatoEsenpat.setCodDiagnosi(doc.getEsenzioneDDiagnosi().getCodDiagnosi());
			certificatoEsenpat.setGruppoEsenzione(doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo());
			certificatoEsenpat.setNotePatologia(doc.getDescEstesaPatologiaCertificato());
			NuovoCertificato certificato = new NuovoCertificato();
			certificato.setCertificatoEsenpat(certificatoEsenpat);
			//preparare l'oggetto cittadino
			CittadinoEsenpat cittadino = new CittadinoEsenpat();
	
			List<Cittadino> cittadini = IntegrationClientImpl.getInstance()
	                .findCittadino(new Cittadino(doc.getCodiceFiscaleCittadino()));
			cittadino = IntegrationClientImpl.getInstance().getCittadinoEsenpat(cittadini.get(0).getIdAura());
	
			CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
			byte[][] XMLS = certificatiFacade.generateXmlCertificatoFirmato(certificato, userInfo, cittadino);
			byte[] pdfSigned = certificatiFacade.generateSignedPdf(XMLS);
			
			log.info("getDocumento", " END");
			return pdfSigned;
			
		} catch (CheckException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] setFirmaDocumentoDigitale(byte[] pdfSigned, FiltriFirmaDigitale filtri, UserInfo userInfo) throws Exception {
		Attachment attachment = new Attachment();
		PadesInput padesInput = new PadesInput();
		SignIdentity identity = new SignIdentity();
		SignLayout signLayout = new SignLayout();
		
		attachment.setFile(pdfSigned);
		String nomeFile = "Certificato malattia";
		attachment.setFileName(nomeFile);
		
		identity.setPassword(filtri.getPin());
		identity.setOtp(filtri.getOtp());
		identity.setUser(filtri.getAlias());
		
		CallInfo callInfo = new CallInfo();
		callInfo.setCaCode(filtri.getCa());
		callInfo.setCodiceFiscale(userInfo.getCodFisc());
		callInfo.setCollocazione(userInfo.getCodASL());
		callInfo.setApplicationCode("ESENPAT");
		padesInput.setCallInfo(callInfo);
		
		padesInput.setSignLayout(signLayout);
		padesInput.setMarkIdentity(null);
		padesInput.setRequiredMark(false);
		
		FileResult result = IntegrationClientImpl.getInstance().gatewayFirmaPades(attachment, padesInput, identity);
		
		if(result.getAttachment() != null) {
			return result.getAttachment().getFile();
		}
		else { 
			if(result.getResult().getDescription() != null && result.getResult().getErrorCode() != null  && result.getResult().getMessage() != null ) {
				EsenzioneDErroriEsenpat errore = dataDao.getErroreGatewayFirma(result.getResult().getDescription(), result.getResult().getErrorCode(), result.getResult().getMessage());
				if(errore != null)
				{
					throw new EsenpatException(errore.getDescription(), errore.getDescription(),
							Status.BAD_REQUEST.getStatusCode());
				}else {
					String erroreConsole = result.getResult().getErrorCode() +" - "+  result.getResult().getDescription()  +" - "+  result.getResult().getMessage() +" - "+ result.getResult().getOriginalReturnCode();
					throw new EsenpatException("ERRORE GENERICO", erroreConsole, 
							Status.BAD_REQUEST.getStatusCode());
				}
			}
			else {
				String erroreConsole = result.getResult().getErrorCode() +" - "+  result.getResult().getDescription()  +" - "+  result.getResult().getMessage() +" - "+ result.getResult().getOriginalReturnCode();
				throw new EsenpatException("ERRORE GENERICO", erroreConsole, 
						Status.BAD_REQUEST.getStatusCode());

			}
		}
	}
	
	public void richiestaOtp(FiltriRichiestaOtp filtri, UserInfo userInfo) throws Exception {
		OtpReqInput otpReqInput = new OtpReqInput();
		Identity identity = new Identity();
		CallInfo callInfo = new CallInfo();
		callInfo.setCaCode(filtri.getCa());
		callInfo.setCodiceFiscale(userInfo.getCodFisc());
		callInfo.setCollocazione(userInfo.getCodASL());
		callInfo.setApplicationCode("ESENPAT");
		otpReqInput.setCallInfo(callInfo);

		identity.setPassword(filtri.getPin());
		identity.setUser(filtri.getAlias());
		otpReqInput.setIdentity(identity);
		
		OtpCredentialsType otpCredentialsType = OtpCredentialsType.fromString(filtri.getTypeOtp().toUpperCase());
		otpReqInput.setOtpCredentialsType(otpCredentialsType);
				
		OtpResult result = IntegrationClientImpl.getInstance().gatewayRichiestaOtp(otpReqInput);
		if(!result.getSuccess()) {
			if (result.getResult().getDescription() != null && result.getResult().getErrorCode() != null
					&& result.getResult().getMessage() != null) {
				EsenzioneDErroriEsenpat errore = dataDao.getErroreGatewayFirma(result.getResult().getDescription(),
						result.getResult().getErrorCode(), result.getResult().getMessage());
				if (errore != null) {
					throw new EsenpatException(errore.getDescription(), errore.getDescription(),
							Status.BAD_REQUEST.getStatusCode());
				} else {
					String erroreConsole = result.getResult().getErrorCode() +" - "+  result.getResult().getDescription()  +" - "+  result.getResult().getMessage() +" - "+ result.getResult().getOriginalReturnCode();
					throw new EsenpatException("ERRORE GENERICO", erroreConsole, 
							Status.BAD_REQUEST.getStatusCode());
				}
			} else {
				String erroreConsole = result.getResult().getErrorCode() +" - "+  result.getResult().getDescription()  +" - "+  result.getResult().getMessage() +" - "+ result.getResult().getOriginalReturnCode();
				throw new EsenpatException("ERRORE GENERICO", erroreConsole, 
						Status.BAD_REQUEST.getStatusCode());
			}
			
		}
	}
	
	public List<ModelCa> getCaOtpTypes() {
		List<ModelCa> ca = new ArrayList<ModelCa>();
		List<EsenzioneDCa> allEsenzioneDCa = dataDao.getAllEsenzioneDCa();
		for(EsenzioneDCa d : allEsenzioneDCa) {
			List<OtpType> o = new ArrayList<OtpType>();
			for(EsenzioneRCaTypeOtp r : d.getEsenzioneRCaTypeOtps()) {
				o.add(new OtpType(r.getEsenzioneDTypeOtp()));
			}
			ca.add(new ModelCa(d, o));
		}
		return ca;
	}
	
}