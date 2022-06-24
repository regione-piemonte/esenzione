/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.RequestBody;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoFamiliareBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCComuni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.ComuneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class EsenzioneRedditoFamiliareService extends AbstractEsenzioneRedditoService {
	
	private static String isTst;

    public EsenzioneRedditoFamiliareService() {
    	super();

    }
    
    public ResponseAura insert(EsenzioneRedditoFamiliareBO esenzioneRedditoFamiliareBO) 
    		throws Exception {
    	if (esenzioneRedditoFamiliareBO == null) {
			EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_017);
			String messaggio = datiMancanti.getTesto();

			throw new EsenzioneInvalidaException(MSG_017, messaggio);
		}
    	
        this.checkFormalConstraints(esenzioneRedditoFamiliareBO);
        Cittadino familiareAURA = this.checkExistCittadino(esenzioneRedditoFamiliareBO.getCodFiscaleFamiliare(), BENEFICIARIO);

		List<EsenredTEsenzioniReddito> elencoEsenzioniSbloccate = esenzioneCittadinoIf.getEsenzioniControlloInserimento(new Long(familiareAURA.getIdAura()), esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare());
		if (elencoEsenzioniSbloccate != null && !elencoEsenzioniSbloccate.isEmpty()) {
			//va dato messaggio simile a quello del mef per il evitare l'inserimento dell'esenzione
			EsenredCMessaggi esenzionenoninseribile = this.messaggioIf.getMessaggio(MSG_033);
			String messaggio = esenzionenoninseribile.getTesto();

			throw new EsenzioneInvalidaException(MSG_033, messaggio);
		}
		else {

			this.checkDomainConstraints(esenzioneRedditoFamiliareBO, familiareAURA,"");
			Cittadino titolareAURA = null;
			List<Cittadino> elencoCittadinoCittadinoDichiarante = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(esenzioneRedditoFamiliareBO.getCodFiscaleDichiarante()));
			//verifica set si titolare e vuoto se si dai messaggio di errore per campo obbligatorio
			if (esenzioneRedditoFamiliareBO.getSititolare().isEmpty()) {
				  EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
		           throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "scelta titolare dell'esenzione"));
			}
			if (esenzioneRedditoFamiliareBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
				titolareAURA = this.checkExistCittadino(esenzioneRedditoFamiliareBO.getCodFiscaleTitolare(), TITOLARE);
				this.checkDomainConstraints(esenzioneRedditoFamiliareBO,titolareAURA,"Titolare");
			}
			else if (esenzioneRedditoFamiliareBO.getSititolare().equalsIgnoreCase(BENEFICIARIO)) {
				titolareAURA = familiareAURA;
			} else if (esenzioneRedditoFamiliareBO.getSititolare().equalsIgnoreCase(DICHIARANTE)) {
				titolareAURA = elencoCittadinoCittadinoDichiarante.get(0);
			}
	
        this.checkDomainEsenzioneConstraints(esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare(), new Long(familiareAURA.getIdAura()),null, " Il Beneficiario possiede esenzioni incompatibili con quella richiesta");
        
        this.checkEsenzioneUgualeInLavorazione(esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare(), new Long(familiareAURA.getIdAura()));
        
        esenzioneRedditoFamiliareBO.setDataScadenzaValiditaFamiliare(getDataScadenza(esenzioneRedditoFamiliareBO.getDataInizioValiditaFamiliare(),esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare()));
        String codASLFamiliare = IntegrationClientImpl.getInstance().getCittadino(familiareAURA.getIdAura()).getCodASL();
        

        
        String idAuraDichiarante = elencoCittadinoCittadinoDichiarante.get(0).getIdAura();
        if (esenzioneRedditoFamiliareBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
        	this.composeAndcheckDatiObbligatoriResponseAura(codASLFamiliare, idAuraDichiarante, familiareAURA.getIdAura(),titolareAURA.getIdAura());
        }
        else {
        	this.composeAndcheckDatiObbligatoriResponseAura(codASLFamiliare, idAuraDichiarante, familiareAURA.getIdAura(),null);	
        }
        EsenredTEsenzioniReddito esenredTEsenzioniReddito = new EsenredTEsenzioniReddito();

        esenredTEsenzioniReddito.setDataInsert(new Date());
        esenredTEsenzioniReddito.setCodStato(StatoEsenzione.IN_LAVORAZIONE.getCodice());

        esenredTEsenzioniReddito.setCodTitoloDichiarante(esenzioneRedditoFamiliareBO.getTitolo());
        esenredTEsenzioniReddito.setDataInizio(Converter.getData(esenzioneRedditoFamiliareBO.getDataInizioValiditaFamiliare()));
        esenredTEsenzioniReddito.setDataFine(Converter.getData(esenzioneRedditoFamiliareBO.getDataScadenzaValiditaFamiliare()));
        esenredTEsenzioniReddito.setDataRichiesta(new Date());
        esenredTEsenzioniReddito.setFlagConsenso(esenzioneRedditoFamiliareBO.getAccettaInformativa() ? 1 : 0);
        esenredTEsenzioniReddito.setIdUserInsert(new Long(idAuraDichiarante));
        esenredTEsenzioniReddito.setIdCittadinoBeneficiario(Long.valueOf(familiareAURA.getIdAura()));
        esenredTEsenzioniReddito.setIdCittadinoDichiarante(Long.valueOf(idAuraDichiarante));
        esenredTEsenzioniReddito.setCodEsenzione(esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare());
        esenredTEsenzioniReddito.setCodNazionaleAslRilascio(codASLFamiliare);
        esenredTEsenzioniReddito.setIdCittadinoTitolare(Long.valueOf(titolareAURA.getIdAura()));

        
        
        String note = esenredTEsenzioniReddito.getCodEsenzione()+" "+this.esenzioneIf.getEsenzioni(esenredTEsenzioniReddito.getCodEsenzione()).get(0).getDescEsenzione();
		if (note.length()>50) note = note.substring(0, 49);
		
		RequestBody request = new RequestBody(
        		esenzioneRedditoFamiliareBO.getCodFiscaleFamiliare(), 
        		esenzioneRedditoFamiliareBO.getCodFiscaleDichiarante(), 
        		esenzioneRedditoFamiliareBO.getCodFiscaleDichiarante(),
        		esenzioneRedditoFamiliareBO.getCodFiscaleTitolare(),
        		esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare(), 
        		note, 
        		esenzioneRedditoFamiliareBO.getTitolo(), 
        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataInizio()),
        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataFine()));
		
		Response responseAura = IntegrationClientImpl.getInstance().insertEsenzione(request);//chiamo AURA

		if (responseAura == null) {
			throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
		}
		String messaggio = null;
		String codice = responseAura.getBody().getEsito();
		
		if ("OK".equalsIgnoreCase(codice)) {
			esenredTEsenzioniReddito.setCodStato(StatoEsenzione.VALIDA.getCodice());//Valida
			esenredTEsenzioniReddito.setNumProtocolloSogei(new Long(responseAura.getBody().getNumProtMef()));
			messaggio = this.messaggioIf.getMessaggio(MSG_001).getTesto();//#N_PROTOCOLLO - #DATA_SCADENZA - #DESC_BREVE_ESENZIONE
			
			messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
			messaggio = messaggio.replaceAll("#DATA_SCADENZA", esenzioneRedditoFamiliareBO.getDataScadenzaValiditaFamiliare());
			String descBreve = this.esenzioneIf.getEsenzioni(esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare()).get(0).getDescEsenzione();
			messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
		} else { //KO
			if ("0003".equals(responseAura.getBody().getCodice())) {//0003 - KO - MEF NON DISPONIBILE
				messaggio = this.messaggioIf.getMessaggio(MSG_002).getTesto();//ATTENZIONE! La richiesta di esenzione � stata presa in carico ed � in fase di verifica
				
				messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
				messaggio = messaggio.replaceAll("#DATA_SCADENZA", esenzioneRedditoFamiliareBO.getDataScadenzaValiditaFamiliare());
				String descBreve = this.esenzioneIf.getEsenzioni(esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare()).get(0).getDescEsenzione();
				messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
			} else {
				esenredTEsenzioniReddito.setCodStato(StatoEsenzione.NON_VALIDA.getCodice());//Non valida
				messaggio = this.messaggioIf.getMessaggio(MSG_003).getTesto();//ATTENZIONE! La richiesta di esenzione non pu� essere concessa: #R!
				messaggio = messaggio.replaceAll("#R", responseAura.getBody().getDescrizione());
			}
		}

		//se lo stato e non valida non inserisco nulla
		if (!esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase(StatoEsenzione.NON_VALIDA.getCodice())) {
		esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione
		//ho spostato la data il set della data di modifica nell'insert
		}
		else
		{
	
			List<EsenredTEsenzioniReddito> esenzioniNonValide = esenzioneCittadinoIf.getEsenzioniNonValide(esenredTEsenzioniReddito);
			if (esenzioniNonValide.isEmpty()) {
				esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione	
			}
		}
		//esenzioneCittadinoIf.update(esenredTEsenzioniReddito);//aggiorno l'esenzione in base a quanto ricevuto da AURA

        ResponseAura r = new ResponseAura();
        r.setIdEsenzione(esenredTEsenzioniReddito.getIdEsenzione());
		r.setCodAura(responseAura.getBody().getCodice());
		r.setMessaggio(messaggio);
		
        return r;
    }
    }
    
	

    private void composeAndcheckDatiObbligatoriResponseAura(String codASL, String idAuraDichiarante, String idAuraFamiliare, String idAuraTitolare)
    		throws EsenzioneInvalidaException {
    	HashMap<String, String> listCampiObbligatori = new HashMap<String, String>();
    	listCampiObbligatori.put("codice ASL", codASL);
    	listCampiObbligatori.put("identificativo dichiarante", idAuraDichiarante);
    	listCampiObbligatori.put("identificativo familiare", idAuraFamiliare);
    	if (idAuraTitolare!=null) {
    	listCampiObbligatori.put("identificativo titolare", idAuraTitolare);
    	}
    	this.checkDatiObbligatoriResponseAura(listCampiObbligatori);
    }

	private void checkDomainConstraints(EsenzioneRedditoFamiliareBO esenzioneRedditoFamiliareBO, Cittadino cittadino, String Chisono) throws Exception {
        EsenredCMessaggi nonCoincideAura = this.messaggioIf.getMessaggio(MSG_020);
        String cognome = cittadino.getCognome();
        String nome = cittadino.getNome();
        String sesso = cittadino.getCodSesso();
        String dataNascita = cittadino.getDataNascita();
        
        if (Chisono == "Titolare") {
        	 if (!cognome.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getCognomeTitolare()))
             	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "cognome titolare"));

             if (!nome.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getNomeTitolare()))
             	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "nome titolare"));
             
             if (!sesso.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getSessoTitolare()))
             	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "sesso titolare"));

             if (!dataNascita.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getDataNascitaTitolare()))
             	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "data di nascita titolare"));
             
             if (Checker.isValorizzato(esenzioneRedditoFamiliareBO.getComuneNascitaTitolare())) {
             	String codComuneNascitaAura = cittadino.getCodComuneNascita();
                 String codComuneNascitaEsenred = null;
                 boolean erroreComune = false; 
                 
             	ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
         		List<EsenredCComuni> elencoComuni = comuneIf.getElencoComuni(esenzioneRedditoFamiliareBO.getComuneNascitaTitolare());
         		if (elencoComuni == null || elencoComuni.isEmpty())
         			erroreComune = true;
         		else {
         			for (EsenredCComuni comune : elencoComuni) {
        				if (comune.getDenominazione().equalsIgnoreCase(esenzioneRedditoFamiliareBO.getComuneNascitaTitolare())) {
        					codComuneNascitaEsenred = comune.getCodIstat();
        					break;
        				}
        			}
         			//codComuneNascitaEsenred = elencoComuni.get(0).getCodIstat();
         			
         			erroreComune = !codComuneNascitaAura.equals(codComuneNascitaEsenred);
         		}
         		if (erroreComune)
                 	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "comune di nascita titolare"));
             }
        }
        else {
	        if(this.esenzioneCittadinoIf.getBloccoCittadino(Long.parseLong(cittadino.getIdAura()),null)) {
				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio("MSG029");
				throw new EsenzioneInvalidaException("MSG029", Util.composeMessage(messaggioDto, ""));
			}
    
        if (!cognome.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getCognomeFamiliare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "cognome"));

        if (!nome.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getNomeFamiliare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "nome"));
        
        if (!sesso.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getSessoFamiliare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "sesso"));

        if (!dataNascita.equalsIgnoreCase(esenzioneRedditoFamiliareBO.getDataNascitaFamiliare()))
        	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "data di nascita"));
        
        if (Checker.isValorizzato(esenzioneRedditoFamiliareBO.getComuneNascitaFamiliare())) {
        	String codComuneNascitaAura = cittadino.getCodComuneNascita();
            String codComuneNascitaEsenred = null;
            boolean erroreComune = false; 
            
        	ComuneIf comuneIf = (ComuneIf) SpringApplicationContext.getBean("comune");
    		List<EsenredCComuni> elencoComuni = comuneIf.getElencoComuni(esenzioneRedditoFamiliareBO.getComuneNascitaFamiliare());
    		if (elencoComuni == null || elencoComuni.isEmpty())
    			erroreComune = true;
    		else {
    			for (EsenredCComuni comune : elencoComuni) {
    				if (comune.getDenominazione().equalsIgnoreCase(esenzioneRedditoFamiliareBO.getComuneNascitaFamiliare())) {
    					codComuneNascitaEsenred = comune.getCodIstat();
    					break;
    				}
    			}
    			
    			
    			erroreComune = !codComuneNascitaAura.equals(codComuneNascitaEsenred);
    		}
    		if (erroreComune)
            	throw new CheckException(MSG_020, Util.composeMessage(nonCoincideAura, "comune di nascita"));
        }
        }
    }

    private void checkFormalConstraints(EsenzioneRedditoFamiliareBO esenzioneRedditoFamiliareBO) throws EsenzioneInvalidaException {
        String codiceEsenzione = esenzioneRedditoFamiliareBO.getCodEsenzioneFamiliare();

        if (!Checker.isValorizzato(codiceEsenzione)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "codice esenzione"));
        }

        List<EsenredDTipiEsenzioniReddito> esenzioni = this.esenzioneIf.getEsenzioni(codiceEsenzione);

        if (esenzioni.isEmpty()) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_017);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_017, messaggioStr);
        }
        
        String titolo = esenzioneRedditoFamiliareBO.getTitolo(); 
       	if (!Checker.isValorizzato(titolo)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "titolo dichiarante"));
        }
        
        String codiceFiscale = esenzioneRedditoFamiliareBO.getCodFiscaleFamiliare();
        if (!Checker.isValorizzato(codiceFiscale)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "codice fiscale"));
        
        }
		//controllo codice fiscale mascherato esteso a esenred 1
        if(isTst==null) {
			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
			isTst= elencoParametri.get(0).getValore();		
		}
        boolean isCodiceFiscale = Checker.isCodiceFiscale(codiceFiscale,isTst);
        if (!isCodiceFiscale) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
            throw new EsenzioneInvalidaException(MSG_004, Util.composeMessage(messaggioDto, "codice fiscale non valido"));
        }
        
        String cognome = esenzioneRedditoFamiliareBO.getCognomeFamiliare();
        if (!Checker.isValorizzato(cognome)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "cognome"));
        }
        
        String nome = esenzioneRedditoFamiliareBO.getNomeFamiliare();
        if (!Checker.isValorizzato(nome)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "nome"));
        }
        
        String sesso = esenzioneRedditoFamiliareBO.getSessoFamiliare();
        if (!Checker.isValorizzato(sesso)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "sesso"));
        }
        
        String dataNascita = esenzioneRedditoFamiliareBO.getDataNascitaFamiliare();
        if (!Checker.isValorizzato(dataNascita)) {
        	EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "data di nascita"));
        } else {
        	if (!Checker.isData(dataNascita, "dd/MM/yyyy", Locale.ITALIAN)) {
    			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                String dettaglio = Util.composeMessage(messaggioDto, " data di nascita non valida");
                throw new EsenzioneInvalidaException(MSG_004, dettaglio);
    		} else {
    			Date dtNascitaDate = Converter.getData(dataNascita);
    			if (dtNascitaDate.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
                    EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                    String dettaglio = Util.composeMessage(messaggioDto, " data di nascita successiva a data odierna");
                    throw new EsenzioneInvalidaException(MSG_004, dettaglio);
                }
    		}
    	}

      //aggiunta titolare obbligatorio se sititolare=si ma se c'e deve rispettare i criteri
      		String codiceFiscaleTitolare = esenzioneRedditoFamiliareBO.getCodFiscaleTitolare();
      		if (esenzioneRedditoFamiliareBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
      			if (!Checker.isValorizzato(codiceFiscaleTitolare)) {
      	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
      	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "codice fiscale titolare"));
      	        
      	        }
      			//ripeti controllo sia per beneficiario che per dichiarante
    			if (esenzioneRedditoFamiliareBO.getCodFiscaleFamiliare().equalsIgnoreCase(esenzioneRedditoFamiliareBO.getCodFiscaleTitolare())) {
    				EsenredCMessaggi cfsititolare = this.messaggioIf.getMessaggio(MSG_038);
    				String messaggio = cfsititolare.getTesto();
    				throw new EsenzioneInvalidaException(MSG_038, messaggio);
    			}
    			if (esenzioneRedditoFamiliareBO.getCodFiscaleDichiarante().equalsIgnoreCase(esenzioneRedditoFamiliareBO.getCodFiscaleTitolare())) {
    				EsenredCMessaggi cfsititolare = this.messaggioIf.getMessaggio(MSG_038);
    				String messaggio = cfsititolare.getTesto();
    				throw new EsenzioneInvalidaException(MSG_038, messaggio);
    			}
      			//controllo codice fiscale mascherato esteso a esenred 1
      			
      			boolean isCodiceFiscaleTitolare = Checker.isCodiceFiscale(codiceFiscaleTitolare,isTst);
      			if (!isCodiceFiscaleTitolare) {
      				EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
      				throw new EsenzioneInvalidaException(MSG_004, Util.composeMessage(messaggioDto, "codice fiscale titolare non valido"));
      			}
      			String cognometitolare = esenzioneRedditoFamiliareBO.getCognomeTitolare();
      	        if (!Checker.isValorizzato(cognometitolare)) {
      	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
      	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "cognome titolare"));
      	        }
      	        
      	        String nometitolare = esenzioneRedditoFamiliareBO.getNomeTitolare();
      	        if (!Checker.isValorizzato(nometitolare)) {
      	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
      	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "nome titolare"));
      	        }
      	        
      	        String sessotitolare = esenzioneRedditoFamiliareBO.getSessoTitolare();
      	        if (!Checker.isValorizzato(sessotitolare)) {
      	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
      	            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "sesso titolare"));
      	        }
      	        
      	      String dataNascitatitolare = esenzioneRedditoFamiliareBO.getDataNascitaTitolare();
              if (!Checker.isValorizzato(dataNascitatitolare)) {
              	EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
                  throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "data di nascita titolare"));
              } else {
              	if (!Checker.isData(dataNascitatitolare, "dd/MM/yyyy", Locale.ITALIAN)) {
          			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                      String dettaglio = Util.composeMessage(messaggioDto, " data di nascita titolare non valida");
                      throw new EsenzioneInvalidaException(MSG_004, dettaglio);
          		} else {
          			Date dtNascitaDatetitolare = Converter.getData(dataNascitatitolare);
          			if (dtNascitaDatetitolare.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
                          EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                          String dettaglio = Util.composeMessage(messaggioDto, " data di nascita titolare successiva a data odierna");
                          throw new EsenzioneInvalidaException(MSG_004, dettaglio);
                      }
          		}
          	}
      		}

        Date dataInizioValidita = Converter.getData(esenzioneRedditoFamiliareBO.getDataInizioValiditaFamiliare());

        if (dataInizioValidita == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "data inizio validita'"));
        }

        if (dataInizioValidita.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "data inizio deve essere pari alla data odierna"));
        }

        Date dataScadenza = Converter.getData(esenzioneRedditoFamiliareBO.getDataScadenzaValiditaFamiliare());

        if (dataScadenza == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "data fine validita'"));
        }

        if (dataScadenza.before(dataInizioValidita)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, "data scadenza deve essere successiva alla data odierna"));
        }  

        Boolean accettaInformativa = esenzioneRedditoFamiliareBO.getAccettaInformativa();
        if (accettaInformativa == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015,Util.composeMessage(messaggioDto, " accetta informativa"));
        }

        if (!accettaInformativa) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "accetta informativa"));
        }
    }
}
