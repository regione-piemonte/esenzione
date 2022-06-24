/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.dao.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.csi.esenred.esenredweb.business.entity.EsenzioneDDiagnosi;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDistrettoSocioSanitario;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoStato;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDDocumentoTipo;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzione;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDEsenzioneAuraArchivio;
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
import it.csi.esenred.esenredweb.dto.FiltriListaCertificati;
import it.csi.esenred.esenredweb.dto.FiltriListaEsenzioni;
import it.csi.esenred.esenredweb.business.entity.CsiLogAudit;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenzioneDGruppoEsenzioni;

public interface DataDaoPatologiaIf {

  public EsenredCMessaggi getMessaggio(String codice);

  public List<EsenzioneTPraticaEsenzione> getListaEsenzioni(String cit_id, String esenzione_id, FiltriListaEsenzioni filtriListaEsenzioni) throws Exception;

  public EsenzioneTPraticaEsenzione loadFullEsenzioneTPraticaEsenzione(Integer skPraticaEsenzione) throws Exception;

  public List<EsenzioneTDocumento> getListaCertificati(String cit_id, FiltriListaCertificati filtriListaCertificati);

  public List<EsenredDTipiEsenzioniReddito> getEsenzioni(String codEsenzione);

  public List<EsenredCParametri> getParametri(String codice);

  public EsenzioneTPraticaEsenzione insertPraticaEsenzione(EsenzioneTPraticaEsenzione praticaEsenzione) throws Exception;

  public void insertDocumentoPraticaEsenzione(EsenzioneTDocumento documento);

  public void insertEsenzioneRPraticaDocumento(EsenzioneRPraticaEsenzioneDocumento esenzioneRPraticaDocumento);

  public void insertRepositoryDocumentale(EsenzioneTRepositoryDocumentale repoDocumentale);

  public void insertOrUpdateCittadino(EsenzioneTCittadino cittadino);

  public EsenzioneDEsenzione getEsenzioneDEsenzioneByDiagnosiId(String idDiagnosi);

  public EsenzioneDDistrettoSocioSanitario getCodiceAslByDistrettoId(String idDistretto);

  public EsenzioneTProgressivo getNumeroProgressivo(String idAzienda, String codiceTipo);

  public void setAnnoProgressivo(EsenzioneTProgressivo prog);

  public EsenzioneRPraticaEsenzioneDocumento getEsenzioneRPraticaEsenzioneDocumentoByCertificatoId(String documentoId);

  public EsenzioneTPraticaEsenzione getPraticaEsenzioneByStatus(String cf, Integer codMalattia, String codTipo, String[] status);

  public List<EsenredCComuni> getElencoComuni(String descComune);

  public EsenredCComuni getElencoComuniPerCodIstat(String codistat);

  public List<EsenredCMessaggi> getMessaggioLike(String codMessaggio);

  public void insertAudit(CsiLogAudit audit);

  public boolean updateAudit(CsiLogAudit audit);

  public CsiLogAudit findAudit(Long idAudit);

  public List<CsiLogAudit> findAllAudit();

  public EsenzioneTCittadino getCittadino(String cf);

  public EsenzioneTCittadino getCittadinoByUserId(String userId);

  public EsenzioneTPraticaEsenzione getEsenzioneTPraticaEsenzione(String numPratica);

  public void insertEsenzioneSPraticaEsenzione(EsenzioneSPraticaEsenzione espe);

	public EsenzioneTPraticaEsenzione insertEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione etpe);

  public EsenzioneDPraticaStato getEsenzioneDPraticaStatoPerCodStato(String codStato);

  public EsenzioneDMotivazioneTipo getEsenzioneDMotivazioneTipoPerCodMotivazione(String codMotivazione);

  public EsenzioneTDocumento getDettaglioCertificato(String cit_id, String certificato_id);

  public EsenzioneTPraticaEsenzione getStoricoEsenzione(String cit_id, String certificato_id) throws Exception;

  public EsenzioneDDocumentoStato getEsenzioneDDocumentoStatoPerCodStato(String codStato);

  public boolean setRinnovoEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione);

  /*
   * Elenco pratiche esenzione correlate ad un codice fiscale con data inizio validita' not null
   */
  public List<EsenzioneTPraticaEsenzione> getListPraticaEsenzioneByCodFiscale(String codFiscale);

  public EsenzioneDDiagnosi getEsenzioneDDiagnosi(String codDiagnosi);

  public EsenzioneDInvaliditaTipo getEsenzioneDInvaliditaTipoByCode(String code);

  /*
   * Ricerca documento per PK
   */
  public EsenzioneTRepositoryDocumentale getEsenzioneTRepositoryDocumentale(String skRepository);

  /*
   * Ricerca elenco documenti associati ad un sk pratica esenzione
   */
  public List<EsenzioneRPraticaEsenzioneDocumento> getEsenzioneRPraticaEsenzioneDocumentoBySkPratica(String skPraticaEsenzione);

  public EsenzioneTPraticaEsenzione updateEsenzioneTPraticaEsenzione(EsenzioneTPraticaEsenzione esenzioneTPraticaEsenzione);

  public byte[] getPdfEsenzione(Map<String, Object> parameters, String template);
  
  /*
   * Ricerca elenco pratiche esenzione valide per beneficiario e diagnosi
   */
  public List<EsenzioneTPraticaEsenzione> getElencoPraticheEsenzioneValideByDiagnosi(String cf, Integer [] elencoSkDiagnosi, String codStato) throws Exception;
  
  /*
   * Update esenzione documento
   */
  public EsenzioneTDocumento updateEsenzioneTDocumento(EsenzioneTDocumento documento) throws Exception;
  
  /*
   * Ricerca esenzione per codice esenzione
   */
  public EsenzioneDEsenzione getEsenzioneDEsenzione(String codEsenzione);

  /*
   * Ricerca diagnosi per sk esenzione
   */
  public List<EsenzioneDDiagnosi> getElencoEsenzioneDDiagnosiByEsenzione(EsenzioneDEsenzione esenzione);

  /*
   * get sk_documento_tipo by codice_documento_tipo
   */
  public EsenzioneDDocumentoTipo getEsenzioneDDocumentoTipo(String codTipoDocumento);

  
  public EsenzioneDGruppoEsenzioni getGruppoEsenzioneByCodice(String codGruppo);

	public List<EsenzioneDEsenzione> getListaEsenzioniByGruppoEsenzione(String codGruppo);

	public List<EsenzioneDDocumentoTipo> getListaTipologieDocumentobyCodiceEsenzione(String tipologia_codice);

	public List<EsenzioneDDiagnosi> getListaDiagnosibyCodiceEsenzione(String tipologia_codice, String malattia_codice);

	public List<EsenzioneDInvaliditaTipo> getListaTipologieInvalidita();
	
	public List<EsenzioneDMotivazioneTipo> getMotivazioniByCodStatoPratica(String codStato);

	public List<EsenzioneDPraticaStato> getListaStatiEsenzione();
	
	public List<EsenzioneDGruppoEsenzioni> getListaTipologieEsenzione();

	public EsenzioneDEsenzione getDettaglioEsenzioneRichiedibile(String skGruppo, String codEsenzione);

	public List<EsenzioneDDiagnosi> getDiagnosibyCodiceEsenzione(String codEsenzione);

	public EsenzioneDEsenzioneAuraArchivio getEsenzioneArchivioByCodEsenzioneAndCodDiagnosi(String codEsenzione,
			String codDiagnosi);

	public EsenzioneDGruppoEsenzioni getEsenzioneDGruppoEsenzioneByTipoAura(String codTipologiaEsenzioneAura);

}
