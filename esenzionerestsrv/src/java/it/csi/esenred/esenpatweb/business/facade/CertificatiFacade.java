/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumentoPK;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.dto.Certificato;
import it.csi.esenred.esenredweb.dto.CertificatoCittadino;
import it.csi.esenred.esenredweb.dto.FiltriListaCertificati;
import it.csi.esenred.esenredweb.dto.StoricoEsenzione;
import it.csi.esenred.esenredweb.util.Constants;

public class CertificatiFacade {
  private DataDaoPatologiaIf dataDao;
//  Date date = new Date();
  DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);

  public List<CertificatoCittadino> getListaCertificati(String cit_id, String queryString) {
    List<CertificatoCittadino> certificatiCittadino = null;
    FiltriListaCertificati filtriListaCertificati = null;

    if (queryString != null && queryString.length() > 0) {
      String queryStringNew = queryString.replace("'", "\"");

      ObjectMapper mapper = new ObjectMapper();
      try {
        filtriListaCertificati = mapper.readValue(queryStringNew, FiltriListaCertificati.class);
      } catch (Exception e){
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

  public EsenzioneTDocumento insertDocumentoPraticaEsenzione(String cit_id, Certificato certificato, EsenzioneTRepositoryDocumentale repoDocumentale, String codTipoUser) {

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
    
//    Inserimento skTipoDocumento by Codice tipo documento (ccm,aes,vin,cmr..)
    docEsenzione.setSkTipoDocumento(Long.decode(dataDao.getEsenzioneDDocumentoTipo(certificato.getTipologia_documento().getCodice()).getSkDocumentoTipo().toString()));
    
    docEsenzione.setSkRepository(Long.valueOf(repoDocumentale.getSkRepository()));
    docEsenzione.setSkTipologiaStatoDocumento(new Long(dataDao.getEsenzioneDDocumentoStatoPerCodStato(Constants.STATO_DOCUMENTO_VALIDO).getSkDocumentoStato()));
    if (certificato.getMalattia() == null || certificato.getMalattia().getCodice() == null) {
      docEsenzione.setSkDiagnosi(null);
    } else {
      docEsenzione.setSkDiagnosi(Long.decode(dataDao.getEsenzioneDDiagnosi(certificato.getMalattia().getCodice()).getSkDiagnosi().toString()));
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



  public DataDaoPatologiaIf getDataDao() {
    return dataDao;
  }

  public void setDataDao(DataDaoPatologiaIf dataDaoPatologia) {
    this.dataDao = dataDaoPatologia;
  }

  public EsenzioneRPraticaEsenzioneDocumento insertEsenzioneRPraticaDocumento(Certificato certificato, Long idDocumneto, EsenzioneTPraticaEsenzione esenzione) {

    EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento = new EsenzioneRPraticaEsenzioneDocumento();
    EsenzioneRPraticaEsenzioneDocumentoPK esRPraticaDocPK = new EsenzioneRPraticaEsenzioneDocumentoPK();

    esenzioneRPraticaDocumento.setDatCreazione(new Timestamp(System.currentTimeMillis()));
    esenzioneRPraticaDocumento.setDatModifica(null);
    esenzioneRPraticaDocumento.setDatCancellazione(null);
    esenzioneRPraticaDocumento.setIdUser(null);
    esenzioneRPraticaDocumento.setCodRuoloOperatore(null);
    if (certificato.getData_fine_validita() != null) {
    try {
      Timestamp parsedData = new Timestamp(((java.util.Date) df.parse(certificato.getData_fine_validita())).getTime());
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

  public EsenzioneTRepositoryDocumentale getAttestatoEsenzione(String cit_id) {

	  List<EsenzioneTPraticaEsenzione> listaPraticheEsenzioneCodFiscale = dataDao.getListPraticaEsenzioneByCodFiscale(cit_id); 
	  if(listaPraticheEsenzioneCodFiscale == null)
		  return null;
	  else if(listaPraticheEsenzioneCodFiscale.size()<1)
		  return null;
	  else if(listaPraticheEsenzioneCodFiscale.size()>1) {
		  Collections.sort(listaPraticheEsenzioneCodFiscale, new Comparator<EsenzioneTPraticaEsenzione>() {
			  public int compare(EsenzioneTPraticaEsenzione pratica1, EsenzioneTPraticaEsenzione pratica2) {
				  if (pratica1.getDatInizioValidita().before(pratica2.getDatInizioValidita())) {
					  return 1;
				  } else if (pratica1.getDatInizioValidita().after(pratica2.getDatInizioValidita())) {
					  return -1;
				  } else {
					  return 0;
				  }
			  }
		  });
	  }
	  EsenzioneTPraticaEsenzione praticaPiuRecenteCodFiscale = listaPraticheEsenzioneCodFiscale.get(0);
	  List <EsenzioneRPraticaEsenzioneDocumento> elencoDocumentiPratica = dataDao.getEsenzioneRPraticaEsenzioneDocumentoBySkPratica(praticaPiuRecenteCodFiscale.getSkPraticaEsenzione().toString());
	  boolean attestatoTrovato = false;
	  if (elencoDocumentiPratica!=null) {
		  Iterator<EsenzioneRPraticaEsenzioneDocumento> iterator = elencoDocumentiPratica.iterator();
		  EsenzioneTDocumento documento = null;
		  while (iterator.hasNext()) {
			  documento = iterator.next().getEsenzioneTDocumento();
			  if (documento.getEsenzioneDDocumentoTipo().getCodDocumentoTipo().equals(Constants.TIPO_DOCUMENTO_ATTESTATO_ESENZIONE)) {
				  attestatoTrovato = true;
				  break;
			  }
		  }
		  if (attestatoTrovato) {
				EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = dataDao
						.getEsenzioneTRepositoryDocumentale(documento.getSkDocumento().toString());

			  if (esenzioneTRepositoryDocumentale != null) {
				  return esenzioneTRepositoryDocumentale;
			  }   
		  }
	  }
	  return null;
  }

  
  public EsenzioneTRepositoryDocumentale getCertificatoMalattia (String certificato_id) throws IOException {
	  EsenzioneTRepositoryDocumentale esenzioneTRepositoryDocumentale = dataDao.getEsenzioneTRepositoryDocumentale(certificato_id);
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
      EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = dataDao.loadFullEsenzioneTPraticaEsenzione(Integer.valueOf(esenzione_id));
      if (esenzioneTPraticaEsenzione != null && esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos() != null
          && !esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos().isEmpty()) {
        fileName = esenzioneTPraticaEsenzione.getNumPratica() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        f = File.createTempFile(fileName, ".zip");
        z = new ZipOutputStream(new FileOutputStream(f));
        for (EsenzioneRPraticaEsenzioneDocumento erp : esenzioneTPraticaEsenzione.getEsenzioneRPraticaEsenzioneDocumentos()) {
					EsenzioneTRepositoryDocumentale repoDoc = dataDao
							.getEsenzioneTRepositoryDocumentale(
									erp.getEsenzioneTDocumento().getSkDocumento().toString());
          if (repoDoc.getFileName() == null || repoDoc.getFileName().isEmpty()) {
            allegatoName = fileName + "_allegato_" + counter;
            counter++;
          } else {
            allegatoName = repoDoc.getFileName();
          }
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
        z.close();
      } else {
        throw new EsenpatException("La pratica non ha alcun allegato", "Nessun allegato trovato", Status.NOT_FOUND.getStatusCode());
      }
    } catch (Exception e) {
			e.printStackTrace();
      z.closeEntry();
      z.close();
      e.printStackTrace();
    }
    return f;
  }
  
  public List<StoricoEsenzione> getStoricoEsenzione(String citId, String esenzioneId) throws Exception {

    EsenzioneTPraticaEsenzione listaStoricoEsenzione = dataDao.getStoricoEsenzione(citId, esenzioneId);
    List<StoricoEsenzione> storico = new ArrayList<StoricoEsenzione>(); 
    if(listaStoricoEsenzione != null) 
    {
      storico.add(new StoricoEsenzione(listaStoricoEsenzione));
      
      if (listaStoricoEsenzione.getEsenzioneSPraticaEsenziones().size() > 0) {
          for (EsenzioneSPraticaEsenzione esenzioneSpratica : listaStoricoEsenzione.getEsenzioneSPraticaEsenziones()) {
            storico.add(new StoricoEsenzione(esenzioneSpratica));
          }
      }
      
      return storico;
    }
   return null;
  }
  
}