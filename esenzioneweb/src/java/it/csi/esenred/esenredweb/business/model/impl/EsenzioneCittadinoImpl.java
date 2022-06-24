/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenpatweb.dto.Filtri;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniEsenred;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniAcceleratore;
import it.csi.esenred.esenpatweb.dto.FiltriRicercaEsenzioniCittadino;

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


    
    public void update(EsenredTEsenzioniReddito esenzione) {
        getDataDao().updateEsenzioneCittadino(esenzione);
    }

    public void updateBlocco(EsenredTEsenzioniReddito esenzione) {
        getDataDao().updateEsenzioneCittadinoBlocco(esenzione);
    }
    
    public void updateSblocco(EsenredTEsenzioniReddito esenzione) {
        getDataDao().updateEsenzioneCittadinoSblocco(esenzione);
    }

    public List<EsenredTEsenzioniReddito> getEsenzioni(FiltriRicercaEsenzioniEsenred filtri, String codASLOperatore) {
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
    
    public EsenredTEsenzioniReddito getEsenzioneByProtocollo(long protocollo, long idAura, String codEsenzione) {
        return getDataDao().getEsenzioneByProtocollo(protocollo,idAura,codEsenzione);
    }



    public List<EsenredTEsenzioniReddito> getEsenzioneBozzaByIdAuraCodEsenzione(long idAura, String codEsenzione){
        return getDataDao().getEsenzioneBozzaByIdAuraCodEsenzione(idAura, codEsenzione);
    }
    
    public List<EsenredTEsenzioniReddito> getEsenzioniNonValide(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
        return getDataDao().getEsenzioniNonValide(esenredTEsenzioniReddito);
    }
    
    public EsenredTEsenzioniReddito getEsenzioniRevocate(EsenredTEsenzioniReddito esenredTEsenzioniReddito) {
        return getDataDao().getEsenzioniRevocate(esenredTEsenzioniReddito);
    }
    

    
	public List<EsenredTEsenzioniReddito> getEsenzioniCittadino(FiltriRicercaEsenzioniCittadino filtri) {
		return getDataDao().getEsenzioniCittadino(filtri);
	}
	
	public List<Long> getEsenzioniBeneficiari(Long idAura) {
		return getDataDao().getEsenzioniBeneficiari(idAura);
	}

	
	public List<Long> getEsenzioniTitolari(Long idAura) {
		return getDataDao().getEsenzioniTitolari(idAura);
	}
	
	@Override
	public List<EsenredTEsenzioniReddito> getEsenzioniAsl(String codASLOperatore) {
		return getDataDao().getEsenzioniAsl(codASLOperatore);
	}

	@Override
	public boolean getBloccoCittadino(Long idAura, Long idAuraDic,String codfiscDich) {
		return getDataDao().checkBloccoCittadino(idAura,idAuraDic,codfiscDich);
	}
	
	public boolean getBloccoCittadino(String codfiscDich) {
		return getDataDao().checkBloccoCittadino(codfiscDich);
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
		
}
