/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.model.impl;

import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoIf;
import it.csi.esenred.esenredweb.business.entity.EsenredCMatriceEsenzioni;
import it.csi.esenred.esenredweb.business.model.interfaces.MatriceEsenzioniCittadinoIf;

import java.util.List;

public class MatriceEsenzioniCittadinoImpl implements MatriceEsenzioniCittadinoIf {

    private DataDaoIf dataDao;

    public DataDaoIf getDataDao() {
        return dataDao;
    }

    public void setDataDao(DataDaoIf dataDao) {
        this.dataDao = dataDao;
    }

    @Override
    public List<EsenredCMatriceEsenzioni> getValue(String codEsenzioneEsistente, String codEsenzioneRichiesta) {

        return getDataDao().getMatriceEsenzioni(codEsenzioneEsistente, codEsenzioneRichiesta);
    }

    public void getMatriceEsenzioniPerRichiesta(String codiceRichiesta) {

    }
}
