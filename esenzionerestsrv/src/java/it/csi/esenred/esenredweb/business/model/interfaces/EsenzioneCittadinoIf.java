/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.interfaces;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioni;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniAcceleratore;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniCittadino;

public interface EsenzioneCittadinoIf {
	
	List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioni filtri, String codASLOperatore);
	
	List<EsenredTEsenzioniReddito> getEsenzioniAsl(String codASLOperatore);
	
	EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio);
	

	void update(EsenredTEsenzioniReddito esenzioneDB, String idAura);
	
	void updateBlocco(EsenredTEsenzioniReddito esenzioneDB);
	
	void updateSblocco(EsenredTEsenzioniReddito esenzioneDB);

	void insert(EsenredTEsenzioniReddito esenzioneDB);
	
	void delete(EsenredTEsenzioniReddito esenzioneDB);

	List<EsenredTEsenzioniReddito> getEsenzioniValideByIdAura(long idAura);
	
	List<EsenredTEsenzioniReddito> getEsenzioneById(long idEsenzione);
	
	List<EsenredTEsenzioniReddito> getEsenzioneBozzaByIdAuraCodEsenzione(long idAura, String codEsenzione);
	
    List<EsenredTEsenzioniReddito> getEsenzioniNonValide(EsenredTEsenzioniReddito esenredTEsenzioniReddito);
    


	List<Long> getEsenzioniBeneficiari(Long idAura,Set<Long> idAuradelegato);
	
	List<Long> getEsenzioniTitolari(Long idAura,Set<Long> idAuradelegato);

	List<EsenredTEsenzioniReddito> getEsenzioniCittadino(Long idAura, FiltriRicercaEsenzioniAcceleratore filtri,Set<Long> idAuradelegato);
	
	boolean getBloccoCittadino(Long idAura, Long idAuraDic);
	
	public Date getDataSBloccoCittadino(Long idAura);
	
	List<EsenredTEsenzioniReddito> getEsenzioniFamiliariDaBloccare(Long idAura, String codEsenzione);
	
	List<EsenredTEsenzioniReddito> getEsenzioniControlloInserimento(Long idAura, String codEsenzione);
}
