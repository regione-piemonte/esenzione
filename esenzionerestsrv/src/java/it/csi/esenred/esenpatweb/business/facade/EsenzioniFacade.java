/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.facade;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.esenred.esenpatweb.business.exception.EsenpatException;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologia;
import it.csi.esenred.esenredweb.business.aura.esenzionePatologia.EsenzionePatologiaRes;
import it.csi.esenred.esenredweb.business.aura.get.InfoEsenzioneNew;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzioneAuraArchivio;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDInvaliditaTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDMotivazioneTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDPraticaStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneRPraticaEsenzioneDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneSPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTProgressivo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTRepositoryDocumentale;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.dto.Certificato;
import it.csi.esenred.esenredweb.dto.CertificatoCittadino;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.EsenzioneAuraCittadinoEsenpat;
import it.csi.esenred.esenredweb.dto.EsenzioneCittadinoEsenpat;
import it.csi.esenred.esenredweb.dto.EsenzioniResponse;
import it.csi.esenred.esenredweb.dto.FiltriEsenzioneAuraDettaglio;
import it.csi.esenred.esenredweb.dto.FiltriListaEsenzioni;
import it.csi.esenred.esenredweb.dto.Malattia;
import it.csi.esenred.esenredweb.dto.MalattiaV2;
import it.csi.esenred.esenredweb.dto.MotivazioneAnnullamento;
import it.csi.esenred.esenredweb.dto.MotivazioneRevocaEsenpat;
import it.csi.esenred.esenredweb.dto.MotivazioniResponse;
import it.csi.esenred.esenredweb.dto.StatiTipologieResponse;
import it.csi.esenred.esenredweb.dto.Stato;
import it.csi.esenred.esenredweb.dto.TipologiaDocumento;
import it.csi.esenred.esenredweb.dto.TipologiaInvalidita;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.LogUtil;

@Service
public class EsenzioniFacade {

  //private final static Logger logger = Logger.getLogger(EsenzioniFacade.class.getName());

  LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE_ESENPAT, null);
  private DataDaoPatologiaIf dataDao;

  public List<EsenzioneCittadinoEsenpat> getListaEsenzioni(String cit_id, String esenzione_id, String queryString, String codiceFiscale) throws Exception {
    FiltriListaEsenzioni filtriListaEsenzioni = null;
    String methodName = "getListaEsenzioni";
    log.info(methodName, "BEGIN");
    if (queryString != null && queryString.length() > 0) {
      String queryStringNew = queryString.replace("'", "\"");

      ObjectMapper mapper = new ObjectMapper();
      try {
        filtriListaEsenzioni = mapper.readValue(queryStringNew, FiltriListaEsenzioni.class);
      } catch (Exception e) {
        String errorMessage = "Impossibile leggere il filtro per la ricerca delle pratiche di esezioni per il cittadino " + cit_id;
        log.error(methodName,errorMessage);
        e.printStackTrace();
        throw new Exception(errorMessage);
      }
    }

    List<EsenzioneTPraticaEsenzione> esenzioneTPraticaEsenziones = dataDao.getListaEsenzioni(cit_id, esenzione_id, filtriListaEsenzioni);

    if (esenzioneTPraticaEsenziones != null && esenzioneTPraticaEsenziones.size() > 0) {
      List<EsenzioneCittadinoEsenpat> esenzioniCittadinos = new ArrayList<EsenzioneCittadinoEsenpat>(esenzioneTPraticaEsenziones.size());
      for (EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione : esenzioneTPraticaEsenziones) {
        esenzioniCittadinos.add(new EsenzioneCittadinoEsenpat(esenzioneTPraticaEsenzione));
      }
      
      log.info(methodName, "END");
      return esenzioniCittadinos;
    }
    return null;
  }
  
	public List<EsenzioneAuraCittadinoEsenpat> getListaEsenzioniAura(Cittadino cittadino, String filters) throws Exception {
		List<EsenzioneAuraCittadinoEsenpat> listaEsen = new ArrayList<EsenzioneAuraCittadinoEsenpat>();
		FiltriListaEsenzioni filtriListaEsenzioni = null;
		String methodName = "getListaEsenzioniAura";
		log.info(methodName, "BEGIN");
		
		// Mappatura filtri
		EsenzioneDGruppoEsenzioni gruppoEsenzioneFiltro = null;
		if (filters != null && filters.length() > 0) {
			String queryStringNew = filters.replace("'", "\"");

			ObjectMapper mapper = new ObjectMapper();
			try {
				filtriListaEsenzioni = mapper.readValue(queryStringNew, FiltriListaEsenzioni.class);
			} catch (Exception e) {
				String errorMessage = "Impossibile leggere il filtro per la ricerca delle esezioni restituite da AURA";
				log.error(methodName, errorMessage);
				e.printStackTrace();
				throw new Exception(errorMessage);
			}
			// Gruppo esenzione del filtro
			if(filtriListaEsenzioni.getTipologia_esenzione() != null) {
				gruppoEsenzioneFiltro = dataDao.getGruppoEsenzioneByCodice(
						filtriListaEsenzioni.getTipologia_esenzione().getEq());
				if(gruppoEsenzioneFiltro == null) {
					String errorMessage = "Filtro Tipologia_esezione non valido";
					log.error(methodName, errorMessage);
					throw new Exception(errorMessage);
				}
			}
		}
		
		
		List<InfoEsenzioneNew> esenzioni = new ArrayList<InfoEsenzioneNew>();
		EsenzioneDPraticaStato esenzioneStato = null;
		if(filtriListaEsenzioni != null && filtriListaEsenzioni.getArchiviata() != null 
				&& filtriListaEsenzioni.getArchiviata().getEq() != null) {

			if (filtriListaEsenzioni.getArchiviata().getEq().equalsIgnoreCase("false")) {
				// Esenzioni valide
				esenzioneStato = dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_VALIDATA);
				esenzioni = cittadino.getEsenzioniAuraValide();
			} else {
				// Esenzioni scadute
				esenzioneStato = dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_SCADUTA);
				esenzioni = cittadino.getEsenzioniAuraScadute();
			}
		} else {
			// Tutte le esenzioni
			esenzioni.addAll(cittadino.getEsenzioniAuraValide());
			esenzioni.addAll(cittadino.getEsenzioniAuraScadute());
		}
		
		for(InfoEsenzioneNew esen : esenzioni) {
			// Esenzione da DB
			EsenzioneDEsenzione esenzione = null;
			EsenzioneDEsenzioneAuraArchivio esenzioneArchivio = null;
			try {
				esenzione = dataDao.getEsenzioneDEsenzione(esen.getCodEsenzione());
			} catch (NoResultException e) {
				esenzione = null;
				try {
					esenzioneArchivio = dataDao.getEsenzioneArchivioByCodEsenzioneAndCodDiagnosi(esen.getCodEsenzione(), esen.getCodDiagnosi().getCodDiagnosi_type0());
				} catch (NoResultException ex) {
					String errorMessage = "esezione non trovata. COD_ESENZIONE: " + esen.getCodEsenzione();
					log.error(methodName, errorMessage);
					throw new Exception(errorMessage);
				}
			}
			
			if(esenzione != null) {	
				if(!esenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_NON_VISIBILE)) {

					EsenzioneDDiagnosi diagnosi = null;
					List<EsenzioneDDiagnosi> listaDiagnosi = dataDao.getDiagnosibyCodiceEsenzione(esenzione.getCodEsenzione());
					if(listaDiagnosi != null && listaDiagnosi.size() > 0) {
						if(esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase("000")) {	

							diagnosi = listaDiagnosi.get(0);
						} else {

							for (EsenzioneDDiagnosi tmpdiagnosi : listaDiagnosi) {
								if(esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase(tmpdiagnosi.getCodDiagnosi())) {
									diagnosi = tmpdiagnosi;
									break;
								}
									
							}
						}
					}
					

					EsenzioneDInvaliditaTipo invalidita = null;
					if(esenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)) {
						invalidita = dataDao.getEsenzioneDInvaliditaTipoByCode(""+esen.getCodEsenzione().charAt(0));
					}
					
					if(gruppoEsenzioneFiltro != null) {
						if(esenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo().equalsIgnoreCase(gruppoEsenzioneFiltro.getCodTipologiaGruppo())) {
							listaEsen.add(new EsenzioneAuraCittadinoEsenpat(esen, esenzione, diagnosi, esenzioneStato, invalidita));
						}
					} else {
						listaEsen.add(new EsenzioneAuraCittadinoEsenpat(esen, esenzione, diagnosi, esenzioneStato, invalidita));
					}
				}
			} else {		
				EsenzioneDGruppoEsenzioni gruppoEsen = dataDao.getEsenzioneDGruppoEsenzioneByTipoAura(
						esenzioneArchivio.getEsenzioneDEsenzioneAuraTipo().getCodTipologiaEsenzioneAura());
	
				if(gruppoEsen != null && !gruppoEsen.getCodTipologiaGruppo().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_NON_VISIBILE)) {
					
					// Tipo invalidita da DB
					EsenzioneDInvaliditaTipo invalidita = null;
					if(gruppoEsen.getCodTipologiaGruppo().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)) {
						invalidita = dataDao.getEsenzioneDInvaliditaTipoByCode(""+esen.getCodEsenzione().charAt(0));
					}
					
					if(gruppoEsenzioneFiltro != null) {
						if(gruppoEsen.getCodTipologiaGruppo().equalsIgnoreCase(gruppoEsenzioneFiltro.getCodTipologiaGruppo())) {
							listaEsen.add(new EsenzioneAuraCittadinoEsenpat(esen, esenzioneArchivio, gruppoEsen, esenzioneStato, invalidita));
						}
					} else {
						listaEsen.add(new EsenzioneAuraCittadinoEsenpat(esen, esenzioneArchivio, gruppoEsen, esenzioneStato, invalidita));
					}
				}
			}
		}		
		log.info(methodName, "END");
		return listaEsen;
	}
	
	public EsenzioneAuraCittadinoEsenpat getDettaglioEsenzioneAura(Cittadino cittadino, String codEsenzione, String codMalattia, String filters) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
		FiltriEsenzioneAuraDettaglio filtriDettaglioEsenzione = null;
		String methodName = "getDettaglioEsenzioneAura";
		log.info(methodName, "BEGIN");
		
		// Mappatura filtri
		String queryStringNew = filters.replace("'", "\"");
		ObjectMapper mapper = new ObjectMapper();
		try {
			filtriDettaglioEsenzione = mapper.readValue(queryStringNew, FiltriEsenzioneAuraDettaglio.class);
		} catch (Exception e) {
			String errorMessage = "Impossibile leggere il filtro per la ricerca della esezione tra quelle restituite da AURA";
			log.error(methodName, errorMessage);
			e.printStackTrace();
			throw new Exception(errorMessage);
		}
		Date dataEmissione = df.parse(filtriDettaglioEsenzione.getData_emissione().getEq());

		
		List<InfoEsenzioneNew> esenzioni = new ArrayList<InfoEsenzioneNew>();
		esenzioni.addAll(cittadino.getEsenzioniAuraValide());
		esenzioni.addAll(cittadino.getEsenzioniAuraScadute());
		for(InfoEsenzioneNew esen : esenzioni) {
			if (esen.getCodEsenzione().equalsIgnoreCase(codEsenzione)
					&& esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase(codMalattia)
					&& Converter.getDateWithoutTime(esen.getDataEmissione().getTime()).equals(dataEmissione)
)
					) {

				// Esenzione da DB
				EsenzioneDEsenzione esenzione = null;
				EsenzioneDEsenzioneAuraArchivio esenzioneArchivio = null;
				try {
					esenzione = dataDao.getEsenzioneDEsenzione(esen.getCodEsenzione());
				} catch (NoResultException e) {
					esenzione = null;
					try {
						esenzioneArchivio = dataDao.getEsenzioneArchivioByCodEsenzioneAndCodDiagnosi(esen.getCodEsenzione(), esen.getCodDiagnosi().getCodDiagnosi_type0());
					} catch (NoResultException ex) {
						String errorMessage = "esezione non trovata. COD_ESENZIONE: " + esen.getCodEsenzione();
						log.error(methodName, errorMessage);
						throw new Exception(errorMessage);
					}
				}
				if(esenzione != null) {	
					EsenzioneDDiagnosi diagnosi = null;
					List<EsenzioneDDiagnosi> listaDiagnosi = dataDao
							.getDiagnosibyCodiceEsenzione(esenzione.getCodEsenzione());
					if (listaDiagnosi != null && listaDiagnosi.size() > 0) {
						if(esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase("000")) {	
.
							diagnosi = listaDiagnosi.get(0);
						} else {

							for (EsenzioneDDiagnosi tmpdiagnosi : listaDiagnosi) {
								if(esen.getCodDiagnosi().getCodDiagnosi_type0().equalsIgnoreCase(tmpdiagnosi.getCodDiagnosi())) {
									diagnosi = tmpdiagnosi;
									break;
								}
									
							}
						}
					}
	
					// Tipo invalidita da DB
					EsenzioneDInvaliditaTipo invalidita = null;
					if (esenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo()
							.equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)) {
						invalidita = dataDao.getEsenzioneDInvaliditaTipoByCode("" + esen.getCodEsenzione().charAt(0));
					}
					log.info(methodName, "END");
					return new EsenzioneAuraCittadinoEsenpat(esen, esenzione, diagnosi, null, invalidita);
					
				} else { // ESENZIONE AURA NON PIU' VALIDA			
					EsenzioneDGruppoEsenzioni gruppoEsen = dataDao.getEsenzioneDGruppoEsenzioneByTipoAura(
							esenzioneArchivio.getEsenzioneDEsenzioneAuraTipo().getCodTipologiaEsenzioneAura());
					
					// Tipo invalidita da DB
					EsenzioneDInvaliditaTipo invalidita = null;
					if (gruppoEsen.getCodTipologiaGruppo()
							.equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)) {
						invalidita = dataDao.getEsenzioneDInvaliditaTipoByCode("" + esen.getCodEsenzione().charAt(0));
					}
					log.info(methodName, "END");
					return new EsenzioneAuraCittadinoEsenpat(esen, esenzioneArchivio, gruppoEsen, null, invalidita);
				}
			}
		}
		
		log.info(methodName, "END");
		return null;
	}

  public EsenzioneTPraticaEsenzione insertPraticaEsenzione(String shibIdentitaCodiceFiscale, String citId, Cittadino cittadino, Certificato certificato) throws Exception {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);

    EsenzioneTPraticaEsenzione praticaEsenzione = new EsenzioneTPraticaEsenzione();
    EsenzioneTDocumento esenzioneTDocumento = null;

    String cfDelegato = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? null : shibIdentitaCodiceFiscale;
    praticaEsenzione.setCodiceFiscaleBeneficiario(citId);
    praticaEsenzione.setCodiceFiscaleDelegato(cfDelegato);

    praticaEsenzione.setDatCreazione(new Timestamp(System.currentTimeMillis()));

    if (Checker.isValorizzato(certificato.getCertificato_id())) {
      //caso 1
      esenzioneTDocumento = dataDao.getDettaglioCertificato(citId, certificato.getCertificato_id());

			if (!esenzioneTDocumento.getCodiceFiscaleCittadino().equals(citId)) {
        throw new EsenpatException(Constants.MSG151, "CF Certificato differente", Status.CONFLICT.getStatusCode());
      }

      praticaEsenzione.setDescNotaBeneficiario(certificato.getNote() != null ? certificato.getNote() : null);

      praticaEsenzione.setEsenzioneDGruppoEsenzioni(dataDao.getEsenzioneDEsenzioneByDiagnosiId(esenzioneTDocumento.getEsenzioneDDiagnosi().getSkDiagnosi().toString()).getEsenzioneDGruppoEsenzioni());
      praticaEsenzione.setEsenzioneDDiagnosi(esenzioneTDocumento.getEsenzioneDDiagnosi());



    } else {

			if (certificato.getTipologia().getCodice().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)) {
				EsenzioneDInvaliditaTipo inv = dataDao
						.getEsenzioneDInvaliditaTipoByCode(certificato.getTipologia_invalidita().getCodice());
				if (inv == null)
					throw new Exception();
				praticaEsenzione.setEsenzioneDInvaliditaTipo(inv);
			}

      praticaEsenzione.setDescNotaBeneficiario(certificato.getNote() != null ? certificato.getNote() : null);
      praticaEsenzione.setEsenzioneDDiagnosi(dataDao.getEsenzioneDDiagnosi(certificato.getMalattia().getCodice()));

      praticaEsenzione.setEsenzioneDGruppoEsenzioni(dataDao.getEsenzioneDEsenzioneByDiagnosiId(praticaEsenzione.getEsenzioneDDiagnosi().getSkDiagnosi().toString()).getEsenzioneDGruppoEsenzioni());
      praticaEsenzione.setEsenzioneDInvaliditaTipo(null);

    }

    if (cittadino.getCodASL() != null || !cittadino.getCodASL().isEmpty()) {
      praticaEsenzione.setIdAzienda(cittadino.getCodASL());
      praticaEsenzione.setSkDistrettoSocioSanitario(null);

    } else {

      praticaEsenzione.setIdAzienda(dataDao.getCodiceAslByDistrettoId(cittadino.getCodDistrettoSocioSanitario()).getIdAzienda());
      praticaEsenzione.setSkDistrettoSocioSanitario(new Long(dataDao.getCodiceAslByDistrettoId(cittadino.getCodDistrettoSocioSanitario()).getSkDistrettoSocioSanitario()));
    }

    String numPratica = getNumeroProgressivoPraticaEsenzione(cittadino.getCodASL());
    praticaEsenzione.setNumPratica(Long.valueOf(numPratica));

    praticaEsenzione.setEsenzioneDPraticaStato(dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA));

    //lotto 2 - na - null
    praticaEsenzione.setCodRuoloOperatore(null);
    praticaEsenzione.setIdUser(null);
    praticaEsenzione.setCodiceFiscaleOperatore(null);
    praticaEsenzione.setDescNotaOperatore(null);
    praticaEsenzione.setDescNota(null);
    //    praticaEsenzione.setSkTipoMotivazione(null);
    praticaEsenzione.setEsenzioneDMotivazioneTipo(null);
    praticaEsenzione.setFlagDichiarazione(null);
    praticaEsenzione.setIdDichiarazione(null);
    praticaEsenzione.setCodiceFiscaleOperatore(null);
    praticaEsenzione.setDatCancellazione(null);
    praticaEsenzione.setDescNotaInterna(null);
    praticaEsenzione.setDatModifica(null);
    praticaEsenzione.setDatInizioValidita(null);

    EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneAlreadySaved = null;
    try {
      esenzioneTPraticaEsenzioneAlreadySaved = dataDao.getPraticaEsenzioneByStatus(citId, praticaEsenzione.getEsenzioneDDiagnosi().getSkDiagnosi(),
          null,

          new String[] { praticaEsenzione.getEsenzioneDPraticaStato().getCodStato() });
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (esenzioneTPraticaEsenzioneAlreadySaved == null) {
      EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione = dataDao.insertPraticaEsenzione(praticaEsenzione);
      return esenzioneTPraticaEsenzione;
    } else {
      throw new EsenpatException(Constants.MSG151, "Pratica già presente", Status.CONFLICT.getStatusCode());
    }

  }

  public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzione(String numPratica) {
    return dataDao.getEsenzioneTPraticaEsenzione(numPratica);
  }

  public EsenzioneSPraticaEsenzione copyInEsenzioneSPraticaEsenzione(EsenzioneTPraticaEsenzione etpe, boolean isDelegato) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    Calendar cal = Calendar.getInstance();

    String codTipoUser = isDelegato ? "DELEGATO" : "BENEFICIARIO";
    EsenzioneSPraticaEsenzione espe = new EsenzioneSPraticaEsenzione();
    espe.setCodRuoloOperatore(etpe.getCodRuoloOperatore());
    espe.setIdUser(etpe.getIdUser() != null ? new Integer(etpe.getIdUser().toString()) : null);
    espe.setCodFiscaleOperatore(etpe.getCodiceFiscaleOperatore());
    espe.setCodTipoUser(codTipoUser);
    espe.setDatCreazione(new Timestamp(System.currentTimeMillis()));
    espe.setDescNotaBeneficiario(etpe.getDescNotaBeneficiario());
    espe.setDescNota(etpe.getDescNota());
    espe.setDescNotaOperatore(etpe.getDescNotaOperatore());
    espe.setSkTipologiaStatoPratica(Long.decode(etpe.getEsenzioneDPraticaStato().getSkPraticaStato().toString()));
    espe.setDatModifica(etpe.getDatModifica());
    espe.setEsenzioneDMotivazioneTipo(etpe.getEsenzioneDMotivazioneTipo());
    espe.setDescNotaInterna(etpe.getDescNotaInterna());
    espe.setNumPratica(etpe.getNumPratica());
    espe.setSkDiagnosi(Long.decode(etpe.getEsenzioneDDiagnosi().getSkDiagnosi().toString()));
    espe.setSkDistrettoSocioSanitario(etpe.getSkDistrettoSocioSanitario());
    espe.setSkInvaliditaTipo(etpe.getEsenzioneDInvaliditaTipo() == null ? null : Long.decode(etpe.getEsenzioneDInvaliditaTipo().getSkInvaliditaTipo().toString()));
    espe.setIdAzienda(etpe.getIdAzienda());
    espe.setCodFiscaleCittadinoBeneficiario(etpe.getCodiceFiscaleBeneficiario());
    espe.setSkGruppo(Long.decode(etpe.getEsenzioneDGruppoEsenzioni().getSkGruppo().toString()));
    espe.setCodFiscaleCittadinoDelegato(etpe.getCodiceFiscaleDelegato());
    espe.setCodFiscaleOperatore(etpe.getCodiceFiscaleOperatore());
    espe.setSkIdEsenzione(new Long(etpe.getSkPraticaEsenzione()));
    espe.setDatInizioValidita(etpe.getDatModifica() != null ? etpe.getDatModifica() : etpe.getDatCreazione());
    espe.setDatFineValidita(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));

    dataDao.insertEsenzioneSPraticaEsenzione(espe);

    return espe;
  }

  public EsenzioneTPraticaEsenzione annullaEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe, String shibIdentitaCodiceFiscale, String citId, MotivazioneAnnullamento motivazione) {
    String cfDelegato = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? null : shibIdentitaCodiceFiscale;
    etpe.setDatCreazione(new Timestamp(System.currentTimeMillis()));
    etpe.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
    etpe.setCodiceFiscaleBeneficiario(citId);
    etpe.setCodiceFiscaleDelegato(cfDelegato);
    etpe.setEsenzioneDMotivazioneTipo(dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(motivazione.getMotivazione().getCodice()));
    etpe.setEsenzioneDPraticaStato(dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_ANNULLATA));
    etpe.setCodRuoloOperatore(null);
    etpe.setDatModifica(null);
    etpe.setDatInizioValidita(null);
    etpe.setDescNotaInterna(null);
    etpe.setFlagDichiarazione(null);
    etpe.setIdDichiarazione(null);
    etpe.setDatCancellazione(null);
    etpe.setDescNotaOperatore(null);
    etpe.setDescNota(null);
    etpe.setIdUser(null);
    etpe.setCodiceFiscaleOperatore(null);

		return dataDao.insertEsenzioneTPraticaEsenzione(etpe);

		// return etpe;
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
    EsenzioneTProgressivo numProg = dataDao.getNumeroProgressivo(idAzienda, Constants.CODICE_PROGRESSIVO_NUMERO_PRATICA);
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



  public EsenzioneTPraticaEsenzione revocaEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe, String shibIdentitaCodiceFiscale, String citId, MotivazioneRevocaEsenpat motivazione) {
    String cfDelegato = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? null : shibIdentitaCodiceFiscale;
    etpe.setDatFineValidita(new Timestamp(System.currentTimeMillis()));
    etpe.setCodiceFiscaleBeneficiario(citId);
    etpe.setCodiceFiscaleDelegato(cfDelegato);
    etpe.setEsenzioneDMotivazioneTipo(dataDao.getEsenzioneDMotivazioneTipoPerCodMotivazione(motivazione.getMotivazione().getCodice()));
    etpe.setEsenzioneDPraticaStato(dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_REVOCATA));
    etpe.setCodRuoloOperatore(null);
    etpe.setDatModifica(null);
    etpe.setDatInizioValidita(null);
    etpe.setDescNotaInterna(null);
    etpe.setFlagDichiarazione(null);
    etpe.setIdDichiarazione(null);
    etpe.setDatCancellazione(null);
    etpe.setDescNotaOperatore(null);
    etpe.setDescNota(null);
    etpe.setIdUser(null);
    etpe.setCodiceFiscaleOperatore(null);

		return dataDao.insertEsenzioneTPraticaEsenzione(etpe);


  }

  public EsenzioneTPraticaEsenzione getPraticaEsenzioneByStatus(String cit_id, Integer codMalattia, String codTipo, String[] status) {
    try {
      return dataDao.getPraticaEsenzioneByStatus(cit_id, codMalattia, codTipo, status);
    } catch (NoResultException e) {
      return null;
    }
  }

  public EsenzioneRPraticaEsenzioneDocumento getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(String certificato_id) {
    return dataDao.getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(certificato_id);
  }

  @Transactional()
  public EsenzioneCittadinoEsenpat setRinnovoEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione, String shibIdentitaCodiceFiscale, String citId, String numPratica, Cittadino citBen,
      Certificato certificato) throws EsenpatException, Exception {

    CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
    EsenzioneTPraticaEsenzione praticaEsenzione = new EsenzioneTPraticaEsenzione();

    if (!Checker.isValorizzato(certificato.getCertificato_id())) {

      if (!Checker.isValorizzato(certificato.getFile_base64()) || !Checker.isValorizzato(certificato.getData_rilascio()) || certificato.getTipologia_documento() == null
          || !Checker.isValorizzato(certificato.getTipologia_documento().getCodice())) {
        throw new EsenpatException(Constants.MSG145, "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
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
        praticaEsenzione = rinnovoPraticaEsenzione(shibIdentitaCodiceFiscale, citId, citBen, certificato, esenzioneTPraticaEsenzione);
        repoDocumentale = certificatiFacade.insertRepositoryDocumentale(certificato);
        documento = certificatiFacade.insertDocumentoPraticaEsenzione(citId, certificato, repoDocumentale, codTipoUser);
        certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(documento.getSkDocumento()), praticaEsenzione);
        return new EsenzioneCittadinoEsenpat(praticaEsenzione);
      }
    } else {

      if (Checker.isNumericString(certificato.getCertificato_id())) {
        praticaEsenzione = rinnovoPraticaEsenzione(shibIdentitaCodiceFiscale, citId, citBen, certificato, esenzioneTPraticaEsenzione);
        CertificatoCittadino documento = certificatiFacade.getDettaglioCertificato(citId, certificato.getCertificato_id());
        String idDocumento = documento.getId();
        certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(idDocumento), praticaEsenzione);
        return new EsenzioneCittadinoEsenpat(praticaEsenzione);
      } else
        throw new EsenpatException(Constants.MSG149, "Mancata congruità parametri di input", Status.BAD_REQUEST.getStatusCode());
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
		if (!esenzioneTPraticaEsenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo()
				.equals(certificato.getTipologia().getCodice())) {
			throw new EsenpatException(Constants.MSG151, "Impossibile cambiare la tipologia esenzione",
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

			if (esenzioneTPraticaEsenzione.getEsenzioneDGruppoEsenzioni().getCodTipologiaGruppo()
					.equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)) {
				if (certificato.getTipologia_invalidita() != null
						&& Checker.isValorizzato(certificato.getTipologia_invalidita().getCodice())) {
					EsenzioneDInvaliditaTipo inv = dataDao
							.getEsenzioneDInvaliditaTipoByCode(certificato.getTipologia_invalidita().getCodice());
					esenzioneTPraticaEsenzione.setEsenzioneDInvaliditaTipo(inv);
				} else {
					throw new EsenpatException(Constants.MSG145, "Parametri obbligatori mancanti",
							Status.BAD_REQUEST.getStatusCode());
				}
			}

			esenzioneTPraticaEsenzione.setDatModifica(new Timestamp(System.currentTimeMillis()));
			esenzioneTPraticaEsenzione.setDescNota(certificato.getNote());
			esenzioneTPraticaEsenzione.setEsenzioneDPraticaStato(
					dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA));
			EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzioneUpdated = dataDao
					.updateEsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzione);

			CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext
					.getBean("certificatiFacade");

			EsenzioneTRepositoryDocumentale repo = certificatiFacade.insertRepositoryDocumentale(certificato);
			EsenzioneTDocumento documento = certificatiFacade.insertDocumentoPraticaEsenzione(cit_id, certificato, repo,
					cit_id.equalsIgnoreCase(shibIdentitaCodiceFiscale) ? "B" : "D");
			certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(documento.getSkDocumento()),
					esenzioneTPraticaEsenzione);

			EsenzioneCittadinoEsenpat esenzioneTPraticaEsenzioneRettificata = new EsenzioneCittadinoEsenpat(
					loadFullEsenzioneTPraticaEsenzione(esenzioneTPraticaEsenzioneUpdated.getSkPraticaEsenzione()));

			return esenzioneTPraticaEsenzioneRettificata;
		} else {
			throw new EsenpatException(Constants.MSG151, "Impossibile rettificare una pratica in questo stato",
					Status.CONFLICT.getStatusCode());
		}
	}

  @Transactional
  public EsenzioneCittadinoEsenpat insertNuovaEsenzioneCittadino(String shibIdentitaCodiceFiscale, String citId, Cittadino cittadino, Certificato certificato) throws EsenpatException, Exception {
    CertificatiFacade certificatiFacade = (CertificatiFacade) SpringApplicationContext.getBean("certificatiFacade");
    EsenzioneTPraticaEsenzione praticaEsenzione = new EsenzioneTPraticaEsenzione();

    //controllo se e' valorizzato il certificato_id
    if (!Checker.isValorizzato(certificato.getCertificato_id())) {

      if (!Checker.isValorizzato(certificato.getMalattia().getCodice()) || !Checker.isValorizzato(certificato.getTipologia().getCodice())) {
        throw new EsenpatException(Constants.MSG145, "Parametri obbligatori mancanti", Status.BAD_REQUEST.getStatusCode());
      } else {
				// Se e' una invalidita' controllo che tipologia invalidita' sia avvalorato
				if (certificato.getTipologia().getCodice().equalsIgnoreCase(Constants.GRUPPO_ESENZIONE_INVALIDITA)
						&& !Checker.isValorizzato(certificato.getTipologia_invalidita().getCodice())) {
					throw new EsenpatException(Constants.MSG145, "Parametri obbligatori mancanti",
							Status.BAD_REQUEST.getStatusCode());
				}

        EsenzioneDDiagnosi esenzioneDDiagnosi = dataDao.getEsenzioneDDiagnosi(certificato.getMalattia().getCodice());
        EsenzioneTPraticaEsenzione esenzioneValida = getPraticaEsenzioneByStatus(citId, esenzioneDDiagnosi.getSkDiagnosi(), null,

            new String[] { Constants.STATO_PRATICA_VALIDATA, Constants.STATO_PRATICA_VALIDATA_IN_SCADENZA });
        if (esenzioneValida != null) {
          throw new EsenpatException(Constants.MSG151, "Pratica già validata", Status.CONFLICT.getStatusCode());
        }


        if (Checker.isValorizzato(certificato.getData_rilascio()) && certificato.getFile_base64() != null 
            && Checker.isValorizzato(certificato.getTipologia_documento().getCodice())) {

          EsenzioneTDocumento documento = new EsenzioneTDocumento();
          EsenzioneTRepositoryDocumentale repoDocumentale = new EsenzioneTRepositoryDocumentale();

          String codTipoUser = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? "B" : "D";

          praticaEsenzione = insertPraticaEsenzione(shibIdentitaCodiceFiscale, citId, cittadino, certificato);
          repoDocumentale = certificatiFacade.insertRepositoryDocumentale(certificato);
          documento = certificatiFacade.insertDocumentoPraticaEsenzione(citId, certificato, repoDocumentale, codTipoUser);
          certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(documento.getSkDocumento()), praticaEsenzione);

          praticaEsenzione = dataDao.loadFullEsenzioneTPraticaEsenzione(praticaEsenzione.getSkPraticaEsenzione());
        } else {

          praticaEsenzione = insertPraticaEsenzione(shibIdentitaCodiceFiscale, citId, cittadino, certificato);
        }
        return new EsenzioneCittadinoEsenpat(praticaEsenzione);
      }
    } else {

      EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento = getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(certificato.getCertificato_id());
      if (esenzioneRPraticaDocumento != null) {
        throw new EsenpatException(Constants.MSG151, "Certificato agganciato ad un'altra pratica", Status.CONFLICT.getStatusCode());
      }


      praticaEsenzione = insertPraticaEsenzione(shibIdentitaCodiceFiscale, citId, cittadino, certificato);
      CertificatoCittadino documento = certificatiFacade.getDettaglioCertificato(citId, certificato.getCertificato_id());
      String idDocumento = documento.getId();
      certificatiFacade.insertEsenzioneRPraticaDocumento(certificato, new Long(idDocumento), praticaEsenzione);

      return new EsenzioneCittadinoEsenpat(praticaEsenzione);
    }
  }

  @Transactional
  public EsenzioneCittadinoEsenpat revocaEsenzione(String esenzioneId, MotivazioneRevocaEsenpat motivazioneRevoca, String shibIdentitaCodiceFiscale, String citId)
      throws Exception, EsenpatException, NumberFormatException {
	  
	String methodName = "revocaEsenzione";
	log.info(methodName, "BEGIN");
	  
    EsenzioneTPraticaEsenzione etpe = loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId.toString()));
    if (etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_VALIDATA)) {
      copyInEsenzioneSPraticaEsenzione(etpe, shibIdentitaCodiceFiscale.equalsIgnoreCase(citId));
      etpe = revocaEsenzioneTPraticaEsenzione(etpe, shibIdentitaCodiceFiscale, citId, motivazioneRevoca);
      if (etpe == null) {
    	log.error(methodName, Constants.MSG145+" errore salvataggio su DB");
        throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB", Status.BAD_REQUEST.getStatusCode());
      } else {
    	log.info(methodName, " Richiamo loadFullEsenzioneTPraticaEsenzione");
    	return new EsenzioneCittadinoEsenpat(loadFullEsenzioneTPraticaEsenzione(etpe.getSkPraticaEsenzione()));
      }
    } else {
    	log.error(methodName, Constants.MSG151 +" Non e' possibile Revocare questa esenzione");	
    	throw new EsenpatException(Constants.MSG151, "Non e' possibile Revocare questa esenzione", Status.CONFLICT.getStatusCode());
    }
     
  }

  public EsenzioneTPraticaEsenzione rinnovoPraticaEsenzione(String shibIdentitaCodiceFiscale, String citId, Cittadino cittadino, Certificato certificato, EsenzioneTPraticaEsenzione praticaEsenzione)
      throws Exception {
    String cfDelegato = shibIdentitaCodiceFiscale.equalsIgnoreCase(citId) ? null : shibIdentitaCodiceFiscale;

    //Nuova Esenzione
    praticaEsenzione.setSkPraticaEsenzione(null);

    praticaEsenzione.setCodiceFiscaleBeneficiario(citId);
    praticaEsenzione.setCodiceFiscaleDelegato(cfDelegato);
    praticaEsenzione.setDatCreazione(new Timestamp(System.currentTimeMillis()));

		praticaEsenzione.setDatFineValidita(null);

    String numPratica = getNumeroProgressivoPraticaEsenzione(cittadino.getCodASL());
    praticaEsenzione.setNumPratica(Long.valueOf(numPratica));
    praticaEsenzione.setDescNotaBeneficiario((certificato.getNote() == null || certificato.getNote().isEmpty()) ? null : certificato.getNote());
    praticaEsenzione.setEsenzioneDPraticaStato(new EsenzioneDPraticaStato(dataDao.getEsenzioneDPraticaStatoPerCodStato(Constants.STATO_PRATICA_INVIATA).getSkPraticaStato()));

    //lotto 2 - na - null
    praticaEsenzione.setCodRuoloOperatore(null);
    praticaEsenzione.setDatModifica(null);
    praticaEsenzione.setDatInizioValidita(null);
    praticaEsenzione.setDescNotaInterna(null);
    praticaEsenzione.setFlagDichiarazione(null);
    praticaEsenzione.setIdDichiarazione(null);
    praticaEsenzione.setDatCancellazione(null);
    praticaEsenzione.setDescNotaOperatore(null);
    praticaEsenzione.setDescNota(null);
    //    praticaEsenzione.setSkTipoMotivazione(null);
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
  public EsenzioneCittadinoEsenpat annullaEsenzione(String esenzioneId, MotivazioneAnnullamento motivazioneAnnullamento, String shibIdentitaCodiceFiscale, String citId)
      throws Exception, EsenpatException, NumberFormatException {
    EsenzioneTPraticaEsenzione etpe = loadFullEsenzioneTPraticaEsenzione(Integer.decode(esenzioneId.toString()));
    if (etpe.getEsenzioneDPraticaStato().getCodStato().equals(Constants.STATO_PRATICA_INVIATA)) {
      copyInEsenzioneSPraticaEsenzione(etpe, shibIdentitaCodiceFiscale.equalsIgnoreCase(citId));
      etpe = annullaEsenzioneTPraticaEsenzione(etpe, shibIdentitaCodiceFiscale, citId, motivazioneAnnullamento);
      if (etpe == null) {
        throw new EsenpatException(Constants.MSG145, "errore salvataggio su DB", Status.BAD_REQUEST.getStatusCode());
      } else {
				return new EsenzioneCittadinoEsenpat(loadFullEsenzioneTPraticaEsenzione(etpe.getSkPraticaEsenzione()));
      }
    } else {
      throw new EsenpatException(Constants.MSG151, "Non e' possibile Annullare questa esenzione", Status.CONFLICT.getStatusCode());
    }
  }

  public File createPdfSingolaEsenzione(EsenzioneTPraticaEsenzione praticaEsenzione, Cittadino cittadinoBen, Cittadino cittadinoDel, String document_type) throws Exception {
  
    Map<String, Object> parameters = new HashMap<String, Object>();
    String template = null;
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
     
      
      parameters.put("DATA_COMP", praticaEsenzione.getDatModifica() == null ? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatCreazione())
          : new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatModifica()));
      parameters.put("COD_RUOLO_OPERATORE", praticaEsenzione.getCodRuoloOperatore() == null ? "" : praticaEsenzione.getCodRuoloOperatore());

    } else if (Constants.TEMPLATE_2.contains(document_type)) {
      template = "Template2";
      parameters.put("NUM_PRATICA", praticaEsenzione.getNumPratica().toString());

      parameters.put("BEN_NOME", cittadinoBen.getNome());
      parameters.put("BEN_COGNOME", cittadinoBen.getCognome());
      parameters.put("BEN_CF", cittadinoBen.getCodFiscale());
;

      parameters.put("DEL_NOME", cittadinoDel == null ? "" : cittadinoDel.getNome());
      parameters.put("DEL_COGNOME", cittadinoDel == null ? "" : cittadinoDel.getCognome());
      parameters.put("DEL_CF", cittadinoDel == null ? "" : cittadinoDel.getCodFiscale());
e());
      
      parameters.put("DATA_COMP", praticaEsenzione.getDatModifica() == null ? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatCreazione())
          : new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatModifica()));
      parameters.put("COD_RUOLO_OPERATORE", praticaEsenzione.getCodRuoloOperatore() == null ? "" : praticaEsenzione.getCodRuoloOperatore());

      parameters.put("MOTIVAZIONE",
          (praticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase("ANN") || praticaEsenzione.getEsenzioneDPraticaStato().getCodStato().equalsIgnoreCase("REV"))
              ? praticaEsenzione.getEsenzioneDMotivazioneTipo().getDescMotivazione()
              : null);

    } else if (Constants.TEMPLATE_3.contains(document_type)) {
      template = "Template3";
			parameters.put("NUM_PRATICA",
					praticaEsenzione.getNumPratica() != null ? praticaEsenzione.getNumPratica().toString() : "");

      parameters.put("BEN_NOME", cittadinoBen.getNome());
      parameters.put("BEN_COGNOME", cittadinoBen.getCognome());
      parameters.put("BEN_CF", cittadinoBen.getCodFiscale());
      parameters.put("DATA_NASCITA", cittadinoBen.getDataNascita());
      
      
      parameters.put("COMUNE_RES", cittadinoBen.getCittaResidenza());
      parameters.put("INDIRIZZO_RES", cittadinoBen.getIndirizzoResidenza());
	  parameters.put("TESSERA_REGIONALE",	cittadinoBen.getCodiceTesseraRegionale() != null ? cittadinoBen.getCodiceTesseraRegionale() : "");

      			
					
      parameters.put("DATA_INIZIO_VALIDITA", praticaEsenzione.getDatInizioValidita() != null ? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatInizioValidita()) : "");
      parameters.put("DATA_FINE_VALIDITA", praticaEsenzione.getDatFineValidita() != null ? new SimpleDateFormat("dd-MM-yyyy").format(praticaEsenzione.getDatFineValidita()) : "Senza scadenza");
      parameters.put("ID_AZIENDA", praticaEsenzione.getEsenredDAziendasanitaria() != null ? praticaEsenzione.getEsenredDAziendasanitaria().getDenominazione() : "");

      if (praticaEsenzione.getEsenzioneDDiagnosi() != null) {
        parameters.put("COD_ESENZIONE", praticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione());
				parameters.put("PRESTAZIONE",
						praticaEsenzione.getEsenzioneDDiagnosi().getEsenzioneRDiagnosiPrestaziones().iterator().next()
								.getEsenzioneDPrestazioneSpecialistica().getDescPrestazione().trim());
      }
    }

    byte[] byteArray = dataDao.getPdfEsenzione(parameters, template);
    if (byteArray.length < 1000) // NO DATA
      throw new Exception();

    File pdf = null;

    String fileName = StringUtils.capitalize(Constants.DOC_TEMPLATES.get(document_type).replace(" ", "_").toLowerCase() + "_" + praticaEsenzione.getNumPratica().toString());
    pdf = File.createTempFile(fileName, ".pdf");
		pdf.deleteOnExit();
    FileUtils.writeByteArrayToFile(pdf, byteArray);

    return pdf;
  }
  

    public EsenzioneDEsenzione getEsenzioneDEsenzione(String codEsenzione) throws Exception {
    	return dataDao.getEsenzioneDEsenzione(codEsenzione);
    }


    public EsenzioneTPraticaEsenzione getPraticaEsenzioneValidaByEsenzione (String cf, EsenzioneDEsenzione esenzione, String codStato) throws Exception {
    	List<EsenzioneDDiagnosi> elencoDiagnosi = dataDao.getElencoEsenzioneDDiagnosiByEsenzione(esenzione);
    	
    	Integer[] elencoSkDiagnosi = new Integer[elencoDiagnosi.size()];
    	int i=0;
    	for (Iterator<EsenzioneDDiagnosi> iterator = elencoDiagnosi.iterator(); iterator.hasNext();) {
    		EsenzioneDDiagnosi diagnosi = (EsenzioneDDiagnosi) iterator.next();
    		elencoSkDiagnosi[i] = diagnosi.getSkDiagnosi();
    		i++;
    	}
    	
    	List<EsenzioneTPraticaEsenzione> listaPraticheValide = dataDao.getElencoPraticheEsenzioneValideByDiagnosi(cf, elencoSkDiagnosi, codStato);
    	if(listaPraticheValide.size()>1) {
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
    	}else return null;
    	return listaPraticheValide.get(0);
    }
    
    @Transactional()
    public EsenzioneTDocumento setValidazioneAttestatoEsenzione(EsenzioneTDocumento attestatoDaValidare, String codAttestatoEsenzione) throws Exception {
    	attestatoDaValidare.setIdAuraAttestato(codAttestatoEsenzione);
    	attestatoDaValidare.setSkTipologiaStatoDocumento(Long.valueOf(Constants.STATO_DOCUMENTO_VALIDO));
    	EsenzioneTDocumento attestatoValidato = dataDao.updateEsenzioneTDocumento(attestatoDaValidare);
    	return attestatoValidato;
    }
    
    
    public Set<EsenzioniResponse> getListaEsenzioniByGruppoEsenzione(String codGruppo) {
		Set<EsenzioniResponse> listaEsenzioniGruppo = null;

	

		List<EsenzioneDEsenzione> listaEsenzioni = dataDao.getListaEsenzioniByGruppoEsenzione(codGruppo);
		if (listaEsenzioni != null && listaEsenzioni.size() > 0) {
			listaEsenzioniGruppo = new HashSet<EsenzioniResponse>();
 query
			for (EsenzioneDEsenzione ede : listaEsenzioni) {
				listaEsenzioniGruppo.add(new EsenzioniResponse(ede));
			}
		}
		return listaEsenzioniGruppo;
	}

	public List<TipologiaDocumento> getListaTipologieDocumento(String tipologia_codice) {
		List<TipologiaDocumento> tipologie = new ArrayList<TipologiaDocumento>();
		List<EsenzioneDDocumentoTipo> listaTipoDocumento = dataDao
				.getListaTipologieDocumentobyCodiceEsenzione(tipologia_codice);
		for (EsenzioneDDocumentoTipo tipo : listaTipoDocumento) {
			tipologie.add(new TipologiaDocumento(tipo));
		}
		return tipologie;
	}
	
	public List<MalattiaV2> getListaMalattie(String tipologia_codice) {
		List<MalattiaV2> malattie = new ArrayList<MalattiaV2>();
		List<EsenzioneDDiagnosi> diagnosi = dataDao.getListaDiagnosibyCodiceEsenzione(tipologia_codice, null);
		for (EsenzioneDDiagnosi diag : diagnosi) {
			malattie.add(new MalattiaV2(diag));
		}
		return malattie;
	}

	public MalattiaV2 getDettaglioMalattia(String tipologia_codice, String malattia_codice) {
		MalattiaV2 malattia = null;
		List<EsenzioneDDiagnosi> diagnosi = dataDao.getListaDiagnosibyCodiceEsenzione(tipologia_codice,
				malattia_codice);
		if (diagnosi != null && !diagnosi.isEmpty()) {
			malattia = new MalattiaV2(diagnosi.get(0));
		}
		return malattia;
	}

	public List<TipologiaInvalidita> getListaTipologieInvalidita() {
		List<TipologiaInvalidita> listaTipologiaInvalidita = new ArrayList<TipologiaInvalidita>();
		List<EsenzioneDInvaliditaTipo> listaInvalidita = dataDao.getListaTipologieInvalidita();
		for (EsenzioneDInvaliditaTipo inv : listaInvalidita) {
			listaTipologiaInvalidita.add(new TipologiaInvalidita(inv));
		}
		return listaTipologiaInvalidita;
	}
	
	
	public List<MotivazioniResponse> getListaMotivazioniByCodStato(String codStato) {
		List<EsenzioneDMotivazioneTipo> motivazioniStato = dataDao.getMotivazioniByCodStatoPratica(codStato);
		List<MotivazioniResponse> motivazioni = new ArrayList<MotivazioniResponse>();
		for (EsenzioneDMotivazioneTipo m : motivazioniStato) {
			motivazioni.add(new MotivazioniResponse(m));
		}
		return motivazioni;
	}
	
	public List<StatiTipologieResponse> getListaStatiEsenzione() {
		List<StatiTipologieResponse> stati = new ArrayList<StatiTipologieResponse>();
		List<EsenzioneDPraticaStato> listaStati = dataDao.getListaStatiEsenzione();
		for (EsenzioneDPraticaStato s : listaStati) {
			stati.add(new StatiTipologieResponse(s));
		}
		return stati;
	}
	
	public List<StatiTipologieResponse> getListaTipologieEsenzione() {
		List<StatiTipologieResponse> tipologie = new ArrayList<StatiTipologieResponse>();
		List<EsenzioneDGruppoEsenzioni> listaTipoDocumento = dataDao.getListaTipologieEsenzione();
		for (EsenzioneDGruppoEsenzioni tipo : listaTipoDocumento) {
			tipologie.add(new StatiTipologieResponse(tipo));
		}
		return tipologie;
	}
	
	public EsenzioniResponse getDettaglioEsenzioneRichiedibile(String codGruppo, String codEsenzione) {
		EsenzioneDEsenzione dettaglioEsenzione = null;
		EsenzioniResponse dettaglio;
		
		dettaglioEsenzione = dataDao.getDettaglioEsenzioneRichiedibile(codGruppo, codEsenzione);
		if (dettaglioEsenzione != null) {
			dettaglio = new EsenzioniResponse(dettaglioEsenzione);
			return dettaglio;
		}
		return null;
	}

	public EsenzioneAuraCittadinoEsenpat revocaEsenzioneAura(String cod_esenzione, String cod_malattia,
			String shibIdentitaCodiceFiscale, Cittadino cittadino, String filters) throws EsenpatException, Exception {
		String methodName = "revocaEsenzioneAura";
		log.info(methodName, "BEGIN");
		
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		EsenzioneAuraCittadinoEsenpat dettaglioEsenzioneAura = getDettaglioEsenzioneAura(cittadino, cod_esenzione, cod_malattia, filters);
		String dataInizioValidita = dettaglioEsenzioneAura.getDataEmissione() == null ? null : Converter.getData(df.parse(dettaglioEsenzioneAura.getDataEmissione()), "dd/MM/yyyy");

		EsenzionePatologia epReq = new EsenzionePatologia();
		epReq.setCodiceFiscaleChiamante(shibIdentitaCodiceFiscale);
		epReq.setCodiceFiscaleAssistito(cittadino.getCodFiscale());
		epReq.setCodiceEsenzione(cod_esenzione);
		epReq.setDataInizioValidita(dataInizioValidita);
		epReq.setDataFineValidita(Converter.getData(new Date(),"dd/MM/yyyy"));
		epReq.setDataAnnullamento(Converter.getData(new Date(),"dd/MM/yyyy"));
		epReq.setListaDiagnosi(cod_malattia);
		log.info(methodName, "Chiamata al servizio AURA EsenzionePatologia");
		EsenzionePatologiaRes epRes = IntegrationClientImpl.getInstance().InserisciEsenzionePatologia(epReq);
		
		if ("OK".equalsIgnoreCase(epRes.getHeader().getCodiceRitorno())) {
			dettaglioEsenzioneAura.setDataScadenza(df.format(Converter.getData(epReq.getDataFineValidita())));
			dettaglioEsenzioneAura.setRevocabile(false);
			dettaglioEsenzioneAura.getStato().setCodice(Constants.STATO_PRATICA_SCADUTA);
			dettaglioEsenzioneAura.getStato().setDescrizione("Scaduta");
			log.info(methodName, "END");
			return dettaglioEsenzioneAura;
		} else {
			log.error(methodName, epRes.getHeader().getListaMessaggi().get(0).getCodice()+" "+epRes.getHeader().getListaMessaggi().get(0).getDescrizione());
	        throw new EsenpatException(epRes.getHeader().getListaMessaggi().get(0).getCodice(), epRes.getHeader().getListaMessaggi().get(0).getDescrizione(), Status.BAD_REQUEST.getStatusCode());
		}
	}

	
	
}