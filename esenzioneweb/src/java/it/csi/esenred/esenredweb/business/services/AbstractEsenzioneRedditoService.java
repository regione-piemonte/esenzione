/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.EsenredCMatriceEsenzioni;
import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;
import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.EsenzioneIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MatriceEsenzioniCittadinoIf;
import it.csi.esenred.esenredweb.business.model.interfaces.MessaggioIf;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.esenred.esenredweb.business.services.exceptions.EsenzioneInvalidaException;
import it.csi.esenred.esenpatweb.dto.Cittadino;
import it.csi.esenred.esenredweb.util.Checker;
import it.csi.esenred.esenredweb.util.Converter;
import it.csi.esenred.esenredweb.util.Util;

public class AbstractEsenzioneRedditoService {
	
	protected static final String DICHIARANTE = "dichiarante";
	protected static final String BENEFICIARIO = "beneficiario";
	protected static final String TITOLARE = "titolare";

    protected static final String MSG_001 = "MSG001";
    protected static final String MSG_002 = "MSG002";
    protected static final String MSG_003 = "MSG003";
    protected static final String MSG_004 = "MSG004";
    protected static final String MSG_010 = "MSG010";
    protected static final String MSG_012 = "MSG012";
    protected static final String MSG_014 = "MSG014";
    protected static final String MSG_015 = "MSG015";
    protected static final String MSG_017 = "MSG017";
    protected static final String MSG_020 = "MSG020";
    protected static final String MSG_021 = "MSG021"; //La ricerca del #R non puo procedere. E' necessario inserire, alternativamente, codice fiscale, oppure cognome, nome e data di nascita.
    protected static final String MSG_026 = "MSG026";
    protected static final String MSG_027 = "MSG027";
    protected static final String MSG_033 = "MSG033";
    protected static final String MSG_038 = "MSG038";
    protected static final String MSG_039 = "MSG039";
    protected static final String MSG_045 = "MSG045";
    
    protected final MessaggioIf messaggioIf;
    protected final EsenzioneIf esenzioneIf;
    protected final ParametroIf parametroIf;
    protected final EsenzioneCittadinoIf esenzioneCittadinoIf;
    protected final MatriceEsenzioniCittadinoIf matriceEsenzioneIf;
    protected final EsenredCParametri dataScadenzaParaRecord;

    public AbstractEsenzioneRedditoService() {
        this.matriceEsenzioneIf = (MatriceEsenzioniCittadinoIf) SpringApplicationContext.getBean("matriceEsenzioni");
        this.parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
        this.messaggioIf = (MessaggioIf) SpringApplicationContext.getBean("messaggio");
        this.esenzioneIf = (EsenzioneIf) SpringApplicationContext.getBean("esenzione");
        this.esenzioneCittadinoIf = (EsenzioneCittadinoIf) SpringApplicationContext.getBean("esenzioneCittadino");
        this.dataScadenzaParaRecord = parametroIf.getParametri("DATA_SCADENZA_PARAMETRIZZATA").get(0);
    }

    protected void checkEsenzioniDistinte(Set<String> esenzioniDistinte, String codiceEsenzioneRichiesta, String details) throws EsenzioneInvalidaException {
        for (String codEsenzioneEsistente : esenzioniDistinte) {
            List<EsenredCMatriceEsenzioni> rigaMatrice = matriceEsenzioneIf.getValue(codEsenzioneEsistente, codiceEsenzioneRichiesta);

            if (!rigaMatrice.isEmpty()) {
                EsenredCMatriceEsenzioni esenredCMatriceEsenzioni = rigaMatrice.get(0);
                String flagInserimento = esenredCMatriceEsenzioni.getFlagInserimento();

                if (flagInserimento.equalsIgnoreCase("no")) {
                	if (details == null)  details = " il codice esenzione richiesto non puo' coesistere con quelli che hai gia'";
                    EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_003);
                    throw new EsenzioneInvalidaException(MSG_003, Util.composeMessage(datiMancanti, details));
                }
            }
        }
    }
    
    protected void checkEsenzioniInEssereUguali(Set<String> esenzioniDistinte, String codiceEsenzioneRichiesta) throws EsenzioneInvalidaException {
        for (String codEsenzioneEsistente : esenzioniDistinte) {
            List<EsenredCMatriceEsenzioni> rigaMatrice = matriceEsenzioneIf.getValue(codEsenzioneEsistente, codiceEsenzioneRichiesta);

            if (!rigaMatrice.isEmpty()) {
                EsenredCMatriceEsenzioni esenredCMatriceEsenzioni = rigaMatrice.get(0);
                if (esenredCMatriceEsenzioni.getCodEsistente().equalsIgnoreCase(esenredCMatriceEsenzioni.getCodRichiesta())) {
                    EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_003);
                    throw new EsenzioneInvalidaException(MSG_003, Util.composeMessage(datiMancanti, "esenzione gia' presente e attiva per il beneficiario indicato"));
                }
            }
        }
    }
    
    protected void checkDatiObbligatoriResponseAura(Map<String,String> listCampiObbligatori) throws EsenzioneInvalidaException {
    	for (Map.Entry<String, String> entry : listCampiObbligatori.entrySet()) {
    	    String campo  = entry.getKey();
    	    String valore = entry.getValue();
    	    if (!Checker.isValorizzato(valore)) {
    	    	EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_026);
                throw new EsenzioneInvalidaException(MSG_026, Util.composeMessage(datiMancanti, campo));
    	    }
    	}
    }
    
    protected Cittadino checkExistCittadino(String codiceFiscale, String detail)
    		throws Exception {
        Cittadino c = new Cittadino(codiceFiscale);
        List<Cittadino> cittadinoAuraResponse = null;
        
        try {
        	cittadinoAuraResponse = IntegrationClientImpl.getInstance().findCittadino(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

        if (cittadinoAuraResponse.isEmpty() && !detail.equalsIgnoreCase("BENEFICIARIO")) {
        	Cittadino citt = IntegrationClientImpl.getInstance().findCittadinoFuoriRegione(codiceFiscale);
        	if(c!=null) cittadinoAuraResponse.add(citt);
        }
        if (cittadinoAuraResponse.isEmpty()) {
        	EsenredCMessaggi cittadinoNonPresente = this.messaggioIf.getMessaggio(MSG_020);
        	throw new EsenzioneInvalidaException(MSG_020, Util.composeMessage(cittadinoNonPresente, detail));
        }
        return cittadinoAuraResponse.get(0);
    }
    
    protected void checkDomainEsenzioneConstraints(String codEsenzioneRichiesta, Long idAura, Long idAuraDic, String details,String codfiscDich)
			throws EsenzioneInvalidaException {

    	if(this.esenzioneCittadinoIf.getBloccoCittadino(idAura,idAuraDic,codfiscDich)) {
			EsenredCMessaggi messaggioDto = this.messaggioIf.getMessaggio("MSG029");
			throw new EsenzioneInvalidaException("MSG029", Util.composeMessage(messaggioDto, ""));
		}
    	
		List<EsenredTEsenzioniReddito> esenzioniValideByIdAura = esenzioneCittadinoIf.getEsenzioniValideByIdAura(idAura);

		Set<String> esenzioniDistinte = new HashSet<String>();
		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : esenzioniValideByIdAura) {
			String codEsenzione = esenredTEsenzioniReddito.getCodEsenzione();
			esenzioniDistinte.add(codEsenzione);
		}

		this.checkEsenzioniDistinte(esenzioniDistinte, codEsenzioneRichiesta, details);
	}
    
    protected void checkDomainEsenzioneInEssereUguali(String codEsenzioneRichiesta, Long idAura)
			throws EsenzioneInvalidaException {

		List<EsenredTEsenzioniReddito> esenzioniValideByIdAura = esenzioneCittadinoIf.getEsenzioniValideByIdAura(idAura);

		Set<String> esenzioniDistinte = new HashSet<String>();
		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : esenzioniValideByIdAura) {
			String codEsenzione = esenredTEsenzioniReddito.getCodEsenzione();
			esenzioniDistinte.add(codEsenzione);
		}
		
		List<EsenredTEsenzioniReddito> esenzioniNonCert = IntegrationClientImpl.getInstance().getEsenzioniValide(idAura.toString());
		for (EsenredTEsenzioniReddito esenredTEsenzioniReddito : esenzioniNonCert) {
			String codEsenzione = esenredTEsenzioniReddito.getCodEsenzione();
			esenzioniDistinte.add(codEsenzione);
		}
		this.checkEsenzioniInEssereUguali(esenzioniDistinte, codEsenzioneRichiesta);
	}
    
    protected String getDataScadenza(String dataInizioValidita,String codEsenzione) throws EsenzioneInvalidaException {
		String dataScadenza = null;

		String dataScadenzaParaRecord = esenzioneIf.getEsenzioni(codEsenzione).get(0).getDataScadenza().trim();
		String dataScadParaStr = null;
		if (dataScadenzaParaRecord.length()==5) {
			//caso di data variabile come ora solo giorno e mese
			dataScadParaStr = dataScadenzaParaRecord + "/" + Converter.getAnno(new Date());
			Date dataScadenzaParametrizzata = Converter.getData(dataScadParaStr, "dd/MM/yyyy", Locale.ITALIAN);

			String dataInizioValiditaStr = dataInizioValidita;
			Date dataInizioValiditaRichiesta = Converter.getData(dataInizioValiditaStr);
			String annoInizioValiditaRichiesta = Converter.getAnno(dataInizioValiditaRichiesta);

			String annoCorrent = Converter.getAnno(new Date());

			Date data0101 = Converter.getData("01/01", "dd/MM", Locale.ITALIAN);
		}
		else if (dataScadenzaParaRecord.length()==10){
			//caso nuovo in cui la data di scadenza fissa titto il periodo ggmmaaaa
			dataScadenza = dataScadenzaParaRecord;
		}
		else
		{
			String  details = " la data scadenza non ha un formato corretto.";
            EsenredCMessaggi datiMancanti = this.messaggioIf.getMessaggio(MSG_003);
            throw new EsenzioneInvalidaException(MSG_003, Util.composeMessage(datiMancanti, details));
		}		
		return dataScadenza;
	}
    

	protected void checkEsenzioneUgualeInLavorazione(String codEsenzione, Long idAura) throws EsenzioneInvalidaException {
		
		List<EsenredTEsenzioniReddito> esenzioniBozzaByIdAura = esenzioneCittadinoIf.getEsenzioneBozzaByIdAuraCodEsenzione(idAura, codEsenzione);
		
		if (!esenzioniBozzaByIdAura.isEmpty()) {
			EsenredCMessaggi esenzioneGiaPresente = this.messaggioIf.getMessaggio(MSG_027); //Non e' possibile procedere con l'operazione, poiche' esiste gia' una richiesta di esenzione in lavorazione.
            throw new EsenzioneInvalidaException(MSG_026, Util.composeMessage(esenzioneGiaPresente, codEsenzione));
	    }
	}
}