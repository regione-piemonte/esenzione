/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioni;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniAcceleratore;
import it.csi.esenred.esenredweb.dto.FiltriRicercaEsenzioniCittadino;

public class EsenzioneCittadinoImpl implements EsenzioneCittadinoIf {
    private DataDaoIf dataDao;

    public DataDaoIf getDataDao() {
        return dataDao;
    }

    public void setDataDao(DataDaoIf dataDao) {
        this.dataDao = dataDao;
    }

    public EsenredTEsenzioniReddito getEsenzioneCittadino(long idAura, String codEsenzione, Date dataInizio) {
        return getDataDao().getEsenzioneCittadino(idAura, codEsenzione, dataInizio);
    }

    
    public void update(EsenredTEsenzioniReddito esenzione,String idAura) {
        getDataDao().updateEsenzioneCittadino(esenzione,idAura);
    }

    public void updateBlocco(EsenredTEsenzioniReddito esenzione) {
        getDataDao().updateEsenzioneCittadinoBlocco(esenzione);
    }
    
    public void updateSblocco(EsenredTEsenzioniReddito esenzione) {
        getDataDao().updateEsenzioneCittadinoSblocco(esenzione);
    }

    public List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioni filtri, String codASLOperatore) {
        return getDataDao().getEsenzioni(filtri, codASLOperatore);
    }
    
    public void insert(EsenredTEsenzioniReddito esenzione) {
        getDataDao().insertEsenzioneCittadino(esenzione);
    }

    public void delete(EsenredTEsenzioniReddito esenzione) {
        getDataDao().deleteEsenzioneCittadino(esenzione);
    }

    public List<EsenredTEsenzioniReddito> getEsenzioniValideByIdAura(long idAura){
        return getDataDao().getEsenzioniValideByIdAura(idAura);
    }
    
//    public List<EsenredTEsenzioniReddito> getEsenzioniFamiliariDaBloccare(Long idAura, String codEsenzione){
//        return getDataDao().;
//    }

    public List<EsenredTEsenzioniReddito> getEsenzioneById(long idEsenzione) {
        return getDataDao().getEsenzioneById(idEsenzione);
    }


    public List<EsenredTEsenzioniReddito> getEsenzioneBozzaByIdAuraCodEsenzione(long idAura, String codEsenzione){
        return getDataDao().getEsenzioneBozzaByIdAuraCodEsenzione(idAura, codEsenzione);
    }
    
    public List<EsenredTEsenzioniReddito> getEsenzioniNonValide(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
        return getDataDao().getEsenzioniNonValide(esenredTEsenzioniReddito);
    }
    
//	public List<EsenredTEsenzioniReddito> getEsenzioniCittadino(FiltriRicercaEsenzioniCittadino filtri) {
//		return getDataDao().getEsenzioniCittadino(filtri);
//	}
	
	public List<Long> getEsenzioniBeneficiari(Long idAura ,Set<Long> idAuradelegato) {
		return getDataDao().getEsenzioniBeneficiari(idAura,idAuradelegato);
	}

//	public List<Long> getEsenzioniOracleBenTit(Long idAura,String Bentit,Set<Long> idAuradelegato) throws SQLException {
//		return getDataDao().getEsenzioniOracleBenTit(idAura, Bentit,idAuradelegato);
//	}
	
	public List<Long> getEsenzioniTitolari(Long idAura,Set<Long> idAuradelegato) {
		return getDataDao().getEsenzioniTitolari(idAura,idAuradelegato);
	}
	
	@Override
	public List<EsenredTEsenzioniReddito> getEsenzioniAsl(String codASLOperatore) {
		return getDataDao().getEsenzioniAsl(codASLOperatore);
	}

	@Override
	public boolean getBloccoCittadino(Long idAura, Long idAuraDic) {
		return getDataDao().checkBloccoCittadino(idAura,idAuraDic);
	}
	
	@Override
	public Date getDataSBloccoCittadino(Long idAura) {
		return getDataDao().DataSBloccoCittadino(idAura);
	}


	@Override
	public List<EsenredTEsenzioniReddito> getEsenzioniFamiliariDaBloccare(Long idAura, String codEsenzione) {
		return getDataDao().getEsenzioniFamiliariDaBloccare(idAura, codEsenzione);
	}
	
	public List<EsenredTEsenzioniReddito> getEsenzioniControlloInserimento(Long idAura, String codEsenzione) {
		return getDataDao().getEsenzioniControlloInserimento(idAura, codEsenzione);
	}

	@Override
	public List<EsenredTEsenzioniReddito> getEsenzioniCittadino(Long idAura, FiltriRicercaEsenzioniAcceleratore filtri,Set<Long> idAuradelegato) {
		return getDataDao().getEsenzioniCittadino(idAura, filtri,idAuradelegato);
	}
	
	
}
