/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniEsenred;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenpatweb.dto.Filtri;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniAcceleratore;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniCittadino;

public interface EsenzioneCittadinoIf {
	
	List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioniEsenred filtri, String codASLOperatore);
	
	List<EsenredTEsenzioniReddito> getEsenzioniAsl(String codASLOperatore);
	
	EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio);
	
//	EsenredTEsenzioniReddito getSingleEsenzioniOracleStampa(String Protocollo,String CodEsenzione,String data_inizio_validita, String TipoEsenzione) throws ClassNotFoundException, SQLException;
	
//	List<EsenredTEsenzioniReddito> getSingleEsenzioniOracleAcceleratore(String Protocollo,String CodEsenzione,String data_inizio_validita, String TipoEsenzione) throws ClassNotFoundException, SQLException;

//	EsenredTEsenzioniReddito getSingleEsenzioniOracle(String Protocollo,String Storico) throws ClassNotFoundException, SQLException;
	
//    public List<EsenredTEsenzioniReddito> getEsenzioniUtenteOracle(FiltriRicercaEsenzioniCittadino filtri, String protocollo) throws ClassNotFoundException, SQLException;
	
	void update(EsenredTEsenzioniReddito esenzioneDB);
	
	void updateBlocco(EsenredTEsenzioniReddito esenzioneDB);
	
	void updateSblocco(EsenredTEsenzioniReddito esenzioneDB);

	void insert(EsenredTEsenzioniReddito esenzioneDB);
	
	void delete(EsenredTEsenzioniReddito esenzioneDB);

	List<EsenredTEsenzioniReddito> getEsenzioniValideByIdAura(long idAura);
	
	List<EsenredTEsenzioniReddito> getEsenzioneById(long idEsenzione);
	
	List<EsenredTEsenzioniReddito> getEsenzioneBozzaByIdAuraCodEsenzione(long idAura, String codEsenzione);
	
    List<EsenredTEsenzioniReddito> getEsenzioniNonValide(EsenredTEsenzioniReddito esenredTEsenzioniReddito);
    
    EsenredTEsenzioniReddito getEsenzioniRevocate(EsenredTEsenzioniReddito esenredTEsenzioniReddito);
    
//    EsenredTEsenzioniReddito getRevocateEsenzioniOracle(String idAuraBen,String codEsenzione) throws ClassNotFoundException, SQLException;
    
	List<EsenredTEsenzioniReddito> getEsenzioniCittadino(FiltriRicercaEsenzioniCittadino filtri);
	
//	List<Long> getEsenzioniOracleBenTit(Long idAura,String Bentit) throws SQLException;

	List<Long> getEsenzioniBeneficiari(Long idAura);
	
	List<Long> getEsenzioniTitolari(Long idAura);
	
	boolean getBloccoCittadino(Long idAura, Long idAuraDic,String codfiscDich);
	
	boolean getBloccoCittadino(String codfiscDich);
	
	public Date getDataSBloccoCittadino(Long idAura);
	
	List<EsenredTEsenzioniReddito> getEsenzioniFamiliariDaBloccare(Long idAura, String codEsenzione);
	
	List<EsenredTEsenzioniReddito> getEsenzioniControlloInserimento(Long idAura, String codEsenzione);
	
	public EsenredTEsenzioniReddito getEsenzioneByProtocollo(long protocollo, long idAura, String codEsenzione);
}
