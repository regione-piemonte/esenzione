/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.facade;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.business.dao.interfaces.DataDaoPatologiaIf;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTPraticaEsenzione;
import it.csi.esenred.esenredweb.dto.EsenzioneCittadinoEsenpat;

public class CittadinoFacade {

  private DataDaoPatologiaIf dataDao;

	public EsenzioneTCittadino getCittadinoEsenpat(String cf) {
    return dataDao.getCittadino(cf);
  }

  public EsenzioneTCittadino insertOrUpdateCittadino(String shibIdentitaCodiceFiscale, String citId, Cittadino cit) {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);


    EsenzioneTCittadino cittadino = new EsenzioneTCittadino();
    cittadino.setCodiceFiscale(cit.getCodFiscale());
    cittadino.setNome(cit.getNome());
    cittadino.setCognome(cit.getCognome());
    cittadino.setComuneDiNascita(cit.getComuneNascita());
    cittadino.setId_aura(cit.getIdAura());
    cittadino.setIdUser(null);
    cittadino.setDatCancellazione(null);
    cittadino.setSesso(cit.getCodSesso());
    cittadino.setDatCreazione(new Timestamp(System.currentTimeMillis()));

    if(cit.getCodASL() != null || !cit.getCodASL().isEmpty())
    {
      cittadino.setIdAzienda(cit.getCodASL());
    }
    else
    {
      //se codASL nn avvalorato allora trovo l'idazienda partendo dal cod distretto sanitario
      cittadino.setIdAzienda(dataDao.getCodiceAslByDistrettoId(cit.getCodDistrettoSocioSanitario()).getIdAzienda());
    }

    try {
      Timestamp parsedTimestamp = new Timestamp(((java.util.Date)df.parse(cit.getDataNascita())).getTime());
      cittadino.setDataDiNascita(parsedTimestamp);          
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    
    dataDao.insertOrUpdateCittadino(cittadino);

    try {
      EsenzioneTCittadino cittadinoInserito = dataDao.getCittadino(cittadino.getCodiceFiscale());
      return cittadinoInserito;
    } catch (Exception e) {
      
      e.printStackTrace();
    }
    return null;
    
  }

  public DataDaoPatologiaIf getDataDao() {
    return dataDao;
  }

  public void setDataDao(DataDaoPatologiaIf dataDao) {
    this.dataDao = dataDao;
  }

}
