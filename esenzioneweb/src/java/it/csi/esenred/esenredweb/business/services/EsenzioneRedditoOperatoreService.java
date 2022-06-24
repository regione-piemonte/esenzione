/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

//import javax.ws.rs.core.Response.Status;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.RequestBody;
import it.csi.esenred.esenredweb.business.aura.insertautocertesered.Response;
import it.csi.esenred.esenredweb.business.bo.EsenzioneRedditoOperatoreBO;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredDTipiEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.entity.comparator.EsenzioniRevocateComparator;
import it.csi.esenred.esenredweb.business.enums.StatoEsenzione;
import it.csi.esenred.esenredweb.business.exception.CheckException;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.AslOperatoreIf;
//import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenpatweb.dto.ResponseAura;
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

			this.checkAuraInputFuoriRegione(eBO.getCodFiscaleDichiaranteFuoriRegione(), DICHIARANTE);
			this.checkAuraInputFuoriRegione(eBO.getCodFiscaleTitolareFuoriRegione(), TITOLARE);
			
			Cittadino beneficiarioAURA = this.checkExistCittadino(eBO.getCodFiscaleBeneficiario(), BENEFICIARIO);
			
			//verifico se si  sta chiedendo una esenzione gia presente
			this.checkDomainEsenzioneInEssereUguali(eBO.getCodEsenzioneBeneficiario(),	new Long(beneficiarioAURA.getIdAura()));
			//controllo se per quel cittadino ci sono esenzioni revocate
			//verifico se beneficiario ha esenzioni revocate prima scadenza Evol2 solo quando entro la prima volta
			if (eBO.getFlagconferma().equalsIgnoreCase("1")) {
				EsenredTEsenzioniReddito esenredTEsenzioniRedditoRevocate = new EsenredTEsenzioniReddito();
				esenredTEsenzioniRedditoRevocate.setIdCittadinoBeneficiario(Long.valueOf(beneficiarioAURA.getIdAura()));
				esenredTEsenzioniRedditoRevocate.setCodEsenzione(eBO.getCodEsenzioneBeneficiario());
				//verifica su esenred
				EsenredTEsenzioniReddito esenzioniRevocate = esenzioneCittadinoIf.getEsenzioniRevocate(esenredTEsenzioniRedditoRevocate);
				//verifica su Certificate
				List<EsenredTEsenzioniReddito> esenzioniRevocatecertList = IntegrationClientImpl.getInstance().getEsenzioniRevocate(beneficiarioAURA.getIdAura(), eBO.getCodEsenzioneBeneficiario());
		
				Collections.sort(esenzioniRevocatecertList, new EsenzioniRevocateComparator()); // FIX ESENRED-179
			
				EsenredTEsenzioniReddito esenzioniRevocatecert = (esenzioniRevocatecertList.size() == 0) ? null : esenzioniRevocatecertList.get(0);
				
				EsenredTEsenzioniReddito esenzioniRevocatepassata = null;
				if (esenzioniRevocate != null && esenzioniRevocatecert == null) {
					esenzioniRevocatepassata = esenzioniRevocate;	
				}
				else if (esenzioniRevocate == null && esenzioniRevocatecert != null) {
					esenzioniRevocatepassata = esenzioniRevocatecert;	
				}
				else if (esenzioniRevocate != null && esenzioniRevocatecert != null) {
				    if (esenzioniRevocate.getDataInizio().compareTo(esenzioniRevocatecert.getDataInizio())>=0) {
				    	//prendi quella di esenred
				    	esenzioniRevocatepassata = esenzioniRevocate;
				    }
				    else {
				    	//prendi quella certificata
				    	esenzioniRevocatepassata = esenzioniRevocatecert;
				    }
				}
				String messaggio = null;
				if (esenzioniRevocatepassata != null) {
					messaggio = this.messaggioIf.getMessaggio(MSG_045).getTesto();//#N_PROTOCOLLO - #DATA_SCADENZA - #DESC_BREVE_ESENZIONE
					messaggio = messaggio.replaceAll("#DATAINIZIO", Converter.getData(esenzioniRevocatepassata.getDataInizio(),"dd/MM/yyyy"));
					messaggio = messaggio.replaceAll("#DATAFINE", Converter.getData(esenzioniRevocatepassata.getDataFine(),"dd/MM/yyyy"));
					messaggio = messaggio.replaceAll("#DATAREVOCA", Converter.getData(esenzioniRevocatepassata.getDataRevoca(),"dd/MM/yyyy"));
					if( esenzioniRevocatepassata.getDescMotivoRevoca() == null) esenzioniRevocatepassata.setDescMotivoRevoca("Vuoto");
					messaggio = messaggio.replaceAll("#MOTIVO", esenzioniRevocatepassata.getDescMotivoRevoca());
			       throw new EsenzioneInvalidaException(MSG_045,messaggio);
				}
			}
			//---------------------
			
			Cittadino dichiaranteAURA;
			if(eBO.getCodFiscaleDichiaranteFuoriRegione()!=null) {
				dichiaranteAURA = this.checkExistCittadino(eBO.getCodFiscaleDichiaranteFuoriRegione(), DICHIARANTE);
			} else {
				dichiaranteAURA= this.checkExistCittadino(eBO.getCodFiscaleDichiarante(), DICHIARANTE);
			}
			//controllo titolare
			Cittadino titolareAURA = null;
			//verifica set si titolare e vuoto se si dai messaggio di errore per campo obbligatorio
			if (eBO.getSititolare().isEmpty()) {
				  EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_015);
		           throw new EsenzioneInvalidaException(MSG_015, Util.composeMessage(messaggioDto, "scelta titolare dell'esenzione"));
			}
			if (eBO.getSititolare().equalsIgnoreCase(TITOLARE) && eBO.getCodFiscaleTitolare()!= null) {
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
			} else if (eBO.getCodFiscaleTitolareFuoriRegione() != null) {
				this.checkExistCittadino(eBO.getCodFiscaleTitolareFuoriRegione(), DICHIARANTE);
			}
			
		
			/* Verifico se esiste una esenzione dello stesso tipo che e stata sbloccata da meno di 2 giorni.
			 * Se si blocco l'inserimento
			 */
			List<EsenredTEsenzioniReddito> elencoEsenzioniSbloccate = esenzioneCittadinoIf.getEsenzioniControlloInserimento(new Long(beneficiarioAURA.getIdAura()), eBO.getCodEsenzioneBeneficiario());
			if (elencoEsenzioniSbloccate != null && !elencoEsenzioniSbloccate.isEmpty()) {
				//va dato messaggio simile a quello del mef per il evitare l'inserimento dell'esenzione
				EsenredCMessaggi esenzionenoninseribile = this.messaggioIf.getMessaggio(MSG_033);
				String messaggio = esenzionenoninseribile.getTesto();

				throw new EsenzioneInvalidaException(MSG_033, messaggio);
			}
			else {
				if (eBO.getCodFiscaleDichiaranteFuoriRegione() == null) {
					// fine controllo blocco
					if (new Long(dichiaranteAURA.getIdAura()) == 0)	
						this.checkDomainEsenzioneConstraints(eBO.getCodEsenzioneBeneficiario(),	new Long(beneficiarioAURA.getIdAura()), null," Il Beneficiario possiede esenzioni incompatibili con quella richiesta",dichiaranteAURA.getCodFiscale());
					else
						this.checkDomainEsenzioneConstraints(eBO.getCodEsenzioneBeneficiario(),new Long(beneficiarioAURA.getIdAura()), new Long(dichiaranteAURA.getIdAura())," Il Beneficiario possiede esenzioni incompatibili con quella richiesta", null);
				}
				this.checkEsenzioneUgualeInLavorazione(eBO.getCodEsenzioneBeneficiario(),
						new Long(beneficiarioAURA.getIdAura()));

				// eBO.setDataScadenzaValidita(getDataScadenza(eBO.getDataInizioValidita()));

				// dati operatore sicuramente presenti altrimenti non sarebbe arrivato al login
				String codFiscaleOperatore = eBO.getCodFiscaleOperatore();
				List<Cittadino> operatoreTMP = IntegrationClientImpl.getInstance()
						.findCittadino(new Cittadino(codFiscaleOperatore));
				Cittadino operatore = IntegrationClientImpl.getInstance().getCittadino(operatoreTMP.get(0).getIdAura());
				// calcolo il codasl per l'operatore che non e quello di residenza
				aslOperatoreIf = (AslOperatoreIf) SpringApplicationContext.getBean("aslOperatore");
				// se vai per CF senza chiamare Aura
				String codAslOperatore = aslOperatoreIf.getAslOperatoreCF(codFiscaleOperatore).getCodAsl();
				operatore.setCodASL(codAslOperatore);


				if (eBO.getSititolare().equalsIgnoreCase(TITOLARE)) {
					if (eBO.getCodFiscaleTitolareFuoriRegione() == null && eBO.getCodFiscaleDichiaranteFuoriRegione() == null) {
					this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(),
							dichiaranteAURA.getIdAura(), beneficiarioAURA.getIdAura(), titolareAURA.getIdAura());
					}
					else if (eBO.getCodFiscaleTitolareFuoriRegione() != null && eBO.getCodFiscaleDichiaranteFuoriRegione() == null) {
						this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(),
								dichiaranteAURA.getIdAura(), beneficiarioAURA.getIdAura(),eBO.getCodFiscaleTitolareFuoriRegione() );
					}
					else if (eBO.getCodFiscaleTitolareFuoriRegione() == null && eBO.getCodFiscaleDichiaranteFuoriRegione() != null) {
						this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(),
								eBO.getCodFiscaleDichiaranteFuoriRegione(), beneficiarioAURA.getIdAura(),titolareAURA.getIdAura());
					}
					else {
						this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(),
								eBO.getCodFiscaleDichiaranteFuoriRegione(), beneficiarioAURA.getIdAura(),eBO.getCodFiscaleTitolareFuoriRegione());
					}
						
				} else {
					if (eBO.getCodFiscaleDichiaranteFuoriRegione() == null) {
						this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(),
								dichiaranteAURA.getIdAura(), beneficiarioAURA.getIdAura(), null);
						}
						
						else {
							this.composeAndcheckDatiObbligatoriResponseAura(operatore.getCodASL(), operatore.getIdAura(),
									eBO.getCodFiscaleDichiaranteFuoriRegione(), beneficiarioAURA.getIdAura(),null);
						}
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
				if (eBO.getCodFiscaleDichiaranteFuoriRegione() == null)
					esenredTEsenzioniReddito.setIdCittadinoDichiarante(Long.valueOf(dichiaranteAURA.getIdAura()));
				esenredTEsenzioniReddito.setCodNazionaleAslRilascio(operatore.getCodASL());
				esenredTEsenzioniReddito.setCodEsenzione(eBO.getCodEsenzioneBeneficiario());
				if (eBO.getCodFiscaleTitolareFuoriRegione() == null)
					esenredTEsenzioniReddito.setIdCittadinoTitolare(Long.valueOf(titolareAURA.getIdAura()));
				esenredTEsenzioniReddito.setNota(eBO.getNota());

				esenredTEsenzioniReddito.setCfTitolarefr(eBO.getCodFiscaleTitolareFuoriRegione());
				esenredTEsenzioniReddito.setCfDichiarantefr(eBO.getCodFiscaleDichiaranteFuoriRegione());

				// non inserisco qui l'esenzione ma solo se esito e non valida vedi sotto
				// modifica voluta il 03/04/2019
				// esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);//inserisco l'esenzione
				String note = esenredTEsenzioniReddito.getCodEsenzione() + " " + this.esenzioneIf
						.getEsenzioni(esenredTEsenzioniReddito.getCodEsenzione()).get(0).getDescEsenzione();
				if (note.length() > 50)
					note = note.substring(0, 49);
				if(eBO.getCodFiscaleDichiaranteFuoriRegione()!= null)
					eBO.setCodFiscaleDichiarante(eBO.getCodFiscaleDichiaranteFuoriRegione());
				if(eBO.getCodFiscaleTitolareFuoriRegione()!= null)
					eBO.setCodFiscaleTitolare(eBO.getCodFiscaleTitolareFuoriRegione());
				
				RequestBody request = new RequestBody(eBO.getCodFiscaleBeneficiario(), // assistito
						operatore.getCodFiscale(), // codFiscaleChiamante
						eBO.getCodFiscaleDichiarante(), // codFiscaleDichiarante
						eBO.getCodFiscaleTitolare(), // codFiscaleTitolare
						eBO.getCodEsenzioneBeneficiario(), note, eBO.getCodTitolo(),
						Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataInizio()),
						Converter.getXMLGregorianCalendar(esenredTEsenzioniReddito.getDataFine()));

				Response responseAura = IntegrationClientImpl.getInstance().insertEsenzione(request);// chiamo AURA

				if (responseAura == null) {
					throw new CheckException(MSG_012, this.messaggioIf.getMessaggio(MSG_012).getTesto());
				}

				String codice = responseAura.getBody().getEsito();
				String messaggio = null;
				if ("OK".equalsIgnoreCase(codice)) {
					esenredTEsenzioniReddito.setCodStato(StatoEsenzione.VALIDA.getCodice());// Valida
					esenredTEsenzioniReddito.setNumProtocolloSogei(new Long(responseAura.getBody().getNumProtMef()));
					messaggio = this.messaggioIf.getMessaggio(MSG_001).getTesto();// #N_PROTOCOLLO - #DATA_SCADENZA -
																					// #DESC_BREVE_ESENZIONE

					messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
					messaggio = messaggio.replaceAll("#DATA_SCADENZA", eBO.getDataScadenzaValidita());
					String descBreve = this.esenzioneIf.getEsenzioni(eBO.getCodEsenzioneBeneficiario()).get(0)
							.getDescEsenzione();
					messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
				} else { // KO
					if ("0003".equals(responseAura.getBody().getCodice())) {// 0003 - KO - MEF NON DISPONIBILE
						messaggio = this.messaggioIf.getMessaggio(MSG_002).getTesto();// ATTENZIONE! La richiesta di
																						// esenzione � stata presa in
																						// carico ed � in fase di
																						// verifica
						messaggio = messaggio.replaceAll("#N_PROTOCOLLO", responseAura.getBody().getNumProtMef());
						messaggio = messaggio.replaceAll("#DATA_SCADENZA", eBO.getDataScadenzaValidita());
						String descBreve = this.esenzioneIf.getEsenzioni(eBO.getCodEsenzioneBeneficiario()).get(0)
								.getDescEsenzione();
						messaggio = messaggio.replaceAll("#DESC_BREVE_ESENZIONE", descBreve);
					} else {
						esenredTEsenzioniReddito.setCodStato(StatoEsenzione.NON_VALIDA.getCodice());// Non valida
						messaggio = this.messaggioIf.getMessaggio(MSG_003).getTesto();// ATTENZIONE! La richiesta di
																						// esenzione non pu� essere
																						// concessa: #R!
						messaggio = messaggio.replaceAll("#R", responseAura.getBody().getDescrizione());
						String motivo_nonvalida = null;
						if (messaggio.length() > 500)
							motivo_nonvalida = messaggio.substring(0, 499);
						else
							motivo_nonvalida = messaggio;
						esenredTEsenzioniReddito.setMotivo_nonvalida(motivo_nonvalida);
					}
				}

				// se lo stato e non valida non inserisco nulla
				if (!esenredTEsenzioniReddito.getCodStato().equalsIgnoreCase(StatoEsenzione.NON_VALIDA.getCodice())) {
					esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);// inserisco l'esenzione
					// ho spostato la data il set della data di modifica nell'insert
				} else {
					// verifica se esiste almeno un altra non valida con le stesse caratteristiche
					// nel caso si non inserisce nulla altrimenti ne crea una sola
					List<EsenredTEsenzioniReddito> esenzioniNonValide = esenzioneCittadinoIf
							.getEsenzioniNonValide(esenredTEsenzioniReddito);
					if (esenzioniNonValide.isEmpty()) {
						esenzioneCittadinoIf.insert(esenredTEsenzioniReddito);// inserisco l'esenzione
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
        
        // valida codiceEsenzione
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
        
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
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
       // esenzioneRedditoOperatoreBO.
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
    
    
    private void checkAuraInputFuoriRegione(String codFiscaleFuoriRegione, String paramErrore)  throws EsenzioneInvalidaException {
    	//valorizzazione o di codFiscale, oppure di cognome,nome e data di nascita
    	if (Checker.isValorizzato(codFiscaleFuoriRegione)) {
    		//controllo codice fiscale mascherato esteso a esenred 1
    		if(isTst==null) {
    			ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
    			List<EsenredCParametri> elencoParametri = parametroIf.getParametri("IS_TST");
    			isTst= elencoParametri.get(0).getValore();		
    		}
    		boolean isCodiceFiscale = Checker.isCodiceFiscale(codFiscaleFuoriRegione,isTst);
    		if (!isCodiceFiscale) {
    			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio(MSG_004);
    			String dettaglio = Util.composeMessage(messaggioDto, " codice fiscale "+paramErrore+" non valido");
    			throw new EsenzioneInvalidaException(MSG_004, dettaglio);
    		}
    	}
    }
}