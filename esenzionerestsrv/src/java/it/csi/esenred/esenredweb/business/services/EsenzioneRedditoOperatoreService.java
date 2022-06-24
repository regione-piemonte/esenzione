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
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoOperatoreBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.AslOperatoreIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenredweb.dto.Cittadino;
import it.csi.esenred.esenredweb.dto.ResponseAura;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class EsenzioneRedditoOperatoreService extends AbstractEsenzioneRedditoService {
	AslOperatoreIf aslOperatoreIf;
	private static String isTst;
	
    public EsenzioneRedditoOperatoreService() {
    	super();
    }
    
    public ResponseAura insert(EsenzioneRedditoOperatoreBO eBO) 
			throws EsenzioneInvalidaException, Exception {
    	
    	if (eBO == null) {
			EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_017);
			String messaggio = datiMancanti.getTesto();

			throw new EsenzioneInvalidaException(MSG_017, messaggio);
		}

		try {
			this.checkFormalConstraints(eBO);
			this.checkAuraInput(eBO.getCodFiscaleDichiarante(), eBO.getCognomeDichiarante(), eBO.getNomeDichiarante(), eBO.getDataNascitaDichiarante(), DICHIARANTE);
			this.checkAuraInput(eBO.getCodFiscaleBeneficiario(), eBO.getCognomeBeneficiario(), eBO.getNomeBeneficiario(), eBO.getDataNascitaBeneficiario(), BENEFICIARIO);
			
			Cittadino dichiaranteAURA = this.checkExistCittadino(eBO.getCodFiscaleDichiarante(), DICHIARANTE);
			Cittadino beneficiarioAURA = this.checkExistCittadino(eBO.getCodFiscaleBeneficiario(), BENEFICIARIO);
			//controllo titolare
			Cittadino titolareAURA = null;
			//verifica set si titolare e vuoto se si dai messaggio di errore per campo obbligatorio
			if (eBO.getSititolare().isEmpty()) {
				  EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
		           throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "scelta titolare dell'esenzione"));
			}
			if (eBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
				this.checkAuraInput(eBO.getCodFiscaleTitolare(), eBO.getCognomeTitolare(), eBO.getNomeTitolare(), eBO.getDataNascitaTitolare(), TITOLARE);

		  		if (eBO.getCodFiscaleBeneficiario().equalsIgnoreCase(eBO.getCodFiscaleTitolare())) {
					EsenredCMessaggi cfsititolare = this.messaggioIf.getMessaggio(MSG_038);
					String messaggio = cfsititolare.getTesto();
					throw new EsenzioneInvalidaException(MSG_038, messaggio);
				}
				if (eBO.getCodFiscaleDichiarante().equalsIgnoreCase(eBO.getCodFiscaleTitolare())) {
					EsenredCMessaggi cfsititolare = this.messaggioIf.getMessaggio(MSG_038);
					String messaggio = cfsititolare.getTesto();
					throw new EsenzioneInvalidaException(MSG_038, messaggio);
				}

				titolareAURA = this.checkExistCittadino(eBO.getCodFiscaleTitolare(), TITOLARE);
				//verifica che la nota deve essere non vuota se il titolare e diverso dal dichiarante
				if (eBO.getNota().isEmpty()) {
					  EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
			           String messaggioStr = messaggioDto.getTesto();
			           throw new EsenzioneInvalidaException(MSG_015, messaggioStr + " nota (obbligatoria se titolare diverso da dichiarante)");
				}
				
			}
			else if (eBO.getSititolare().equalsIgnoreCase(BENEFICIARIO)) {
				titolareAURA = beneficiarioAURA;
			} else if (eBO.getSititolare().equalsIgnoreCase(DICHIARANTE)) {
				titolareAURA = dichiaranteAURA;
			}
			
		

			List<EsenredTEsenzioniReddito> elencoEsenzioniSbloccate = esenzioneCittadinoIf.getEsenzioniControlloInserimento(new Long(beneficiarioAURA.getIdAura()), eBO.getCodEsenzioneBeneficiario());
			if (elencoEsenzioniSbloccate != null && !elencoEsenzioniSbloccate.isEmpty()) {
				//va dato messaggio simile a quello del mef per il evitare l'inserimento dell'esenzione
				EsenredCMessaggi esenzionenoninseribile = this.messaggioIf.getMessaggio(MSG_033);
				String messaggio = esenzionenoninseribile.getTesto();

				throw new EsenzioneInvalidaException(MSG_033, messaggio);
			}
			else {
			//fine controllo blocco
			
			this.checkDomainEsenzioneConstraints(eBO.getCodEsenzioneBeneficiario(), new Long(beneficiarioAURA.getIdAura()), new Long(dichiaranteAURA.getIdAura()), " Il Beneficiario possiede esenzioni incompatibili con quella richiesta");
			
			this.checkEsenzioneUgualeInLavorazione(eBO.getCodEsenzioneBeneficiario(), new Long(beneficiarioAURA.getIdAura()));
			

			String codFiscaleOperatore = eBO.getCodFiscaleOperatore();
			List<Cittadino> operatoreTMP = IntegrationClientImpl.getInstance().findCittadino(new Cittadino(codFiscaleOperatore));
			Cittadino operatore = IntegrationClientImpl.getInstance().getCittadino(operatoreTMP.get(0).getIdAura());
		
			aslOperatoreIf = (AslOperatoreIf) SpringApplicationContext.getBean("aslOperatore");

			String codAslOperatore = aslOperatoreIf.getAslOperatoreCF(codFiscaleOperatore).getCodAsl();
			operatore.setCodASL(codAslOperatore);

			 if (eBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
				this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(), dichiaranteAURA.getIdAura(), beneficiarioAURA.getIdAura(),titolareAURA.getIdAura());	
			}
			else 
			{
				this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(), dichiaranteAURA.getIdAura(), beneficiarioAURA.getIdAura(),null);
			}
			EsenredTEsenzioniReddito esenredTEsenzioniReddito = new EsenredTEsenzioniReddito();

			esenredTEsenzioniReddito.setDataInsert(new Date());
			esenredTEsenzioniReddito.setCodStato(StatoEsenzione.IN_LAVORAZIONE.getCodice());
			esenredTEsenzioniReddito.setCodTitoloDichiarante(eBO.getCodTitolo());
			esenredTEsenzioniReddito.setDataInizio(Converter.getData(eBO.getDataInizioValidita()));
			esenredTEsenzioniReddito.setDataFine(Converter.getData(eBO.getDataScadenzaValidita()));
			esenredTEsenzioniReddito.setDataRichiesta(new Date());
			esenredTEsenzioniReddito.setFlagConsenso(1);
			esenredTEsenzioniReddito.setIdUserInsert(new Long(operatore.getIdAura()));
			esenredTEsenzioniReddito.setIdOperatoreRichiesta(new Long(operatore.getIdAura()));
			esenredTEsenzioniReddito.setIdCittadinoBeneficiario(Long.valueOf(beneficiarioAURA.getIdAura()));
			esenredTEsenzioniReddito.setIdCittadinoDichiarante(Long.valueOf(dichiaranteAURA.getIdAura()));
			esenredTEsenzioniReddito.setCodNazionaleAslRilascio(operatore.getCodASL());
			esenredTEsenzioniReddito.setCodEsenzione(eBO.getCodEsenzioneBeneficiario());
			esenredTEsenzioniReddito.setIdCittadinoTitolare(Long.valueOf(titolareAURA.getIdAura()));
			esenredTEsenzioniReddito.setNota(eBO.getNota());

			String note = esenredTEsenzioniReddito.getCodEsenzione()+" "+this.esenzioneIf.getEsenzioni(esenredTEsenzioniReddito.getCodEsenzione()).get(0).getDescEsenzione();
			if (note.length()>50) note = note.substring(0, 49);
			 RequestBody request = new RequestBody(
					eBO.getCodFiscaleBeneficiario(), //assistito
					operatore.getCodFiscale(), //codFiscaleChiamante
					eBO.getCodFiscaleDichiarante(), //codFiscaleDichiarante 
					eBO.getCodFiscaleTitolare(), //codFiscaleTitolare
					eBO.getCodEsenzioneBeneficiario(), 
	        		note, 
	        		eBO.getCodTitolo(), 
	        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataInizio()),
	        		Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataFine()));
			
			Response responseAura = IntegrationClientImpl.getInstance().insertEsenzione(request);//chiamo AURA

			if (responseAura == null) {
				throw new CheckException(MSG_012,this.messaggioIf.getMessaggio(MSG_012).getTesto());
			}
			
			String codice = responseAura.getBody().getEsito();
			String messaggio = null;
			if ("OK".equalsIgnoreCase(codice)) {
				esenredTEsenzioniReddito.setCodStato(StatoEsenzione.VALIDA.getCodice());//Valida
				esenredTEsenzioniReddito.setNumProtocolloSogei(new Long(responseAura.getBody().getNumProtMef()));
				messaggio = this.messaggioIf.getMessaggio(MSG_001).getTesto();//#N_PROTOCOLLO - #DATA_SCADENZA - #DESC_BREVE_ESENZIONE
				
				messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
				messaggio = messaggio.replaceAll("#DATA_SCADENZA", eBO.getDataScadenzaValidita());
				String descBreve = this.esenzioneIf.getEsenzioni(eBO.getCodEsenzioneBeneficiario()).get(0).getDescEsenzione();
				messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
			} else { //KO
				if ("0003".equals(responseAura.getBody().getCodice())) {//0003 - KO - MEF NON DISPONIBILE
					messaggio = this.messaggioIf.getMessaggio(MSG_002).getTesto();//ATTENZIONE! La richiesta di esenzione � stata presa in carico ed � in fase di verifica
					messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
					messaggio = messaggio.replaceAll("#DATA_SCADENZA", eBO.getDataScadenzaValidita());
					String descBreve = this.esenzioneIf.getEsenzioni(eBO.getCodEsenzioneBeneficiario()).get(0).getDescEsenzione();
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
			ResponseAura r = new ResponseAura();
			r.setIdEsenzione(esenredTEsenzioniReddito.getIdEsenzione());
			r.setCodAura(responseAura.getBody().getCodice());
			r.setMessaggio(messaggio);
			return r;
		} 
    } catch (EsenzioneInvalidaException e) {
			throw e;
		}
	}

	private void composeAndcheckDatiObbligatoriResponseAura(String codASLOperatore, String idAuraOperatore, String idAuraDichiarante, String idAuraBeneficiario, String idAuraTitolare) 
			throws EsenzioneInvalidaException {
		HashMap<String, String> listCampiObbligatori = new HashMap<String, String>();
		listCampiObbligatori.put("codice ASL operatore", codASLOperatore);
		listCampiObbligatori.put("identificativo operatore", idAuraOperatore);
		listCampiObbligatori.put("identificativo dichiarante", idAuraDichiarante);
		listCampiObbligatori.put("identificativo beneficiario", idAuraBeneficiario);
		if (idAuraTitolare!=null) {
			listCampiObbligatori.put("identificativo titolare", idAuraTitolare);
		}
		this.checkDatiObbligatoriResponseAura(listCampiObbligatori);
		
	}

	private void checkAuraInput(String codFiscale, String cognome, String nome, String dataNascita, String paramErrore) 
    		throws EsenzioneInvalidaException {
    	//valorizzazione o di codFiscale, oppure di cognome,nome e data di nascita
    	if (Checker.isValorizzato(codFiscale)) {
    		//controllo codice fiscale mascherato esteso a esenred 1
    		if(isTst==null) {
    			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
    			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
    			isTst= elencoParametri.get(0).getValore();		
    		}
    		boolean isCodiceFiscale = Checker.isCodiceFiscale(codFiscale,isTst);
            if (!isCodiceFiscale) {
                EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                String dettaglio = Util.composeMessage(messaggioDto, " codice fiscale "+paramErrore+" non valido");
                throw new EsenzioneInvalidaException(MSG_004, dettaglio);
            }
    	} else if (Checker.isValorizzato(cognome) && Checker.isValorizzato(nome) && Checker.isValorizzato(dataNascita)) {
    		if (!Checker.isAlfabeticString(cognome)) {
    			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                String dettaglio = Util.composeMessage(messaggioDto, " cognome "+paramErrore+" non valido");
                throw new EsenzioneInvalidaException(MSG_004, dettaglio);
    		}
    		if (!Checker.isAlfabeticString(nome)) {
    			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                String dettaglio = Util.composeMessage(messaggioDto, " nome "+paramErrore+" non valido");
                throw new EsenzioneInvalidaException(MSG_004, dettaglio);
    		}
    		if (!Checker.isData(dataNascita, "dd/MM/yyyy", Locale.ITALIAN)) {
    			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                String dettaglio = Util.composeMessage(messaggioDto, " data di nascita "+paramErrore+" non valida");
                throw new EsenzioneInvalidaException(MSG_004, dettaglio);
    		} else {
    			Date dtNascitaDate = Converter.getData(dataNascita);
    			if (dtNascitaDate.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
                    EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
                    String dettaglio = Util.composeMessage(messaggioDto, " data di nascita "+paramErrore+" successiva a data odierna");
                    throw new EsenzioneInvalidaException(MSG_004, dettaglio);
                }
    		}
    	} else {
    		EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_021); //La ricerca del #R non puo procedere. E' necessario inserire, alternativamente, codice fiscale, oppure cognome, nome e data di nascita.
    		String dettaglio = Util.composeMessage(messaggioDto, paramErrore);
            throw new EsenzioneInvalidaException(MSG_021, dettaglio);
    	}
	}
    
    private void checkFormalConstraints(EsenzioneRedditoOperatoreBO esenzioneRedditoOperatoreBO) throws EsenzioneInvalidaException {
        String codiceEsenzione = esenzioneRedditoOperatoreBO.getCodEsenzioneBeneficiario();

        String titolo = esenzioneRedditoOperatoreBO.getCodTitolo(); 
       	if (!Checker.isValorizzato(titolo)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "titolo dichiarante"));
        }
        
       
        if (codiceEsenzione == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015, messaggioStr + " codice esenzione");
        }

        if (codiceEsenzione.isEmpty()) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015, messaggioStr + " codice esenzione");
        }

        List<EsenredDTipiEsenzioniReddito> esenzioni = this.esenzioneIf.getEsenzioni(codiceEsenzione);

        if (esenzioni.isEmpty()) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_017);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_017, messaggioStr);
        }
        
        
        Date dataInizioValidita = Converter.getData(esenzioneRedditoOperatoreBO.getDataInizioValidita());

        if (dataInizioValidita == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015, messaggioStr + " data inizio validita'");
        }

        if (dataInizioValidita.compareTo(Converter.getData(Converter.getData(new Date()))) > 1) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015,messaggioStr + " data inizio deve essere pari alla data odierna");
        }

        Date dataScadenza = Converter.getData(esenzioneRedditoOperatoreBO.getDataScadenzaValidita());
        Date dataScadenzaSSN = Converter.getData(esenzioneRedditoOperatoreBO.getDatascadenzaSSN());

        if (dataScadenza == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015, messaggioStr + " data fine validita'");
        }

        if (dataScadenza.before(dataInizioValidita)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015,messaggioStr + " data scadenza deve essere successiva alla data odierna");
        }
        if (dataScadenzaSSN!=null) {
	        if (dataScadenzaSSN.before(dataScadenza)) {
	            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_039);
	            String messaggioStr = messaggioDto.getTesto();
	            throw new EsenzioneInvalidaException(MSG_039,messaggioStr);
	        }
        }
      
        Date dataRichiesta = Converter.getData(esenzioneRedditoOperatoreBO.getDataRichiesta());

        if (dataRichiesta == null) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015, messaggioStr + " data richiesta");
        }

        if (dataRichiesta.after(dataScadenza)) {
            EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
            String messaggioStr = messaggioDto.getTesto();
            throw new EsenzioneInvalidaException(MSG_015,messaggioStr + " data richiesta successiva a data scadenza");
        }
        
    }
}