/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniCittadino;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniEsenred;
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

public interface DataDaoIf {

    public List<EsenredCComuni> getElencoComuni();

    public EsenredCMessaggi getMessaggio(String codice);

    public void updateNotifica(EsenredWNotifiche notifica);

    public EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio);

    public void updateEsenzioneCittadino(EsenredTEsenzioniReddito esenzione);
    
    public void updateEsenzioneCittadinoBlocco(EsenredTEsenzioniReddito esenzione);
    
    public void updateEsenzioneCittadinoSblocco(EsenredTEsenzioniReddito esenzione);

    public void insertNotifica(EsenredWNotifiche notifica);

    public List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioniEsenred filtri, String codASLOperatore);
    
    public List<EsenredTEsenzioniReddito> getEsenzioniAsl(String codASLOperatore);

    public List<EsenredDTipiEsenzioniReddito> getEsenzioni(String codEsenzione);

    public EsenredCParametri getParametro(String codice);

    public List<EsenredCParametri> getParametri(String codice);
    
    public EsenredTAslOperatore getAslOperatoreCF(String  CodiceFiscaleOperatore);

    public List<EsenredCTitoloDichiarante> getTitoli(String codTitolo);

    public void insertEsenzioneCittadino(EsenredTEsenzioniReddito esenzione);

    public void deleteEsenzioneCittadino(EsenredTEsenzioniReddito esenzione);

    public List<EsenredCMatriceEsenzioni> getMatriceEsenzioni(String esenzione1, String esenzione2);

    List<EsenredTEsenzioniReddito> getEsenzioniValideByIdAura(long idAura);
    
	List<EsenredWNotifiche> getNotificheOperatore(Long idOperatore);

	List<EsenredWNotifiche> getNotificheCittadino(Long idCittadino);

	boolean deleteNotifica(Long idNotifica);
	
	List<EsenredTEsenzioniReddito> getEsenzioneById(long idEsenzione);

	public List<EsenredCComuni> getElencoComuni(String descComune);
	
	public EsenredCComuni getElencoComuniPerCodIstat(String codistat);

	public List<EsenredCMessaggi> getMessaggioLike(String codMessaggio);

	public byte[] getReport(Map<String, Object> parameters);
	
	public byte[] getReportStorico(Map<String, Object> parameters);

	public List<EsenredCTitoloDichiarante> getTitoliFamiliare();
	
	public Date DataSBloccoCittadino(Long idAura);
	
	List<EsenredTEsenzioniReddito> getEsenzioneBozzaByIdAuraCodEsenzione(long idAura, String codEsenzione);
	
    List<EsenredTEsenzioniReddito> getEsenzioniNonValide(EsenredTEsenzioniReddito esenredTEsenzioniReddito);
    
    EsenredTEsenzioniReddito getEsenzioniRevocate(EsenredTEsenzioniReddito esenredTEsenzioniReddito);
    
//    public EsenredTEsenzioniReddito getRevocateEsenzioniOracle(String idAuraBen,String codEsenzione) throws ClassNotFoundException, SQLException;
	
	public List<Long> getEsenzioniBeneficiari(Long idAura);
	
//	public List<Long> getEsenzioniOracleBenTit(Long idAura, String Bentit) throws SQLException;
	
	public List<Long> getEsenzioniTitolari(Long idAura);
	
	List<EsenredTEsenzioniReddito> getEsenzioniFamiliariDaBloccare(Long idAura, String codEsenzione);
	
	List<EsenredTEsenzioniReddito> getEsenzioniControlloInserimento(Long idAura, String codEsenzione);

	public boolean updateSetConsultata(Long idNotifica);
	
	public List<EsenredTEsenzioniReddito> getEsenzioniCittadino(FiltriRicercaEsenzioniCittadino filtri);
	
	public boolean checkBloccoCittadino(Long idAura, Long idAuraDic,String codfiscDich);
	
	public boolean checkBloccoCittadino(String codfiscDich);
	
	public void insertAudit(CsiLogAudit audit);
	
	public boolean updateAudit(CsiLogAudit audit);
	
	public CsiLogAudit findAudit(Long idAudit);
	
	public List<CsiLogAudit> findAllAudit();
	
	public EsenredTEsenzioniReddito getEsenzioneByProtocollo(long protocollo, long idAura, String codEsenzione);
}
