/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

	public static final String STATO_PRATICA_INVIATA_DAL_MEDICO = "INM";
	public static final String STATO_PRATICA_INVIATA = "INV";
	public static final String STATO_PRATICA_RICHIESTA_APPUNTAMENTO = "RAP";
	public static final String STATO_PRATICA_ANNULLATA = "ANN";
	public static final String STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_OPERATORE = "REO";
	public static final String STATO_PRATICA_RESPINTA = "RES";
	public static final String STATO_PRATICA_DA_VALIDARE = "DAV";
	public static final String STATO_PRATICA_IN_LAVORAZIONE = "LAV";
	public static final String STATO_PRATICA_VALIDATA = "VAL";
	public static final String STATO_PRATICA_IN_SCADENZA = "INS";
	public static final String STATO_PRATICA_SCADUTA = "SCA";
	public static final String STATO_PRATICA_REVOCATA = "REV";
	public static final String STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_MEDICO = "REM";
	public static final String STATO_PRATICA_VALIDATA_IN_SCADENZA = "VIS";
	
	public static final String OP_SCARICA_ATTESTATO = "OP-ScaricaAttestato";
	public static final String OP_SALVA_IN_BOZZA = "OP-SalvaInBozza";
	public static final String OP_RINNOVA = "OP-Rinnova";
	public static final String OP_APPROVA = "OP-Approva";
	public static final String OP_VALIDA = "OP-Valida";
	public static final String OP_RESPINGI = "OP-Respingi";
	public static final String OP_VISUALIZZA_CRONOLOGIA = "OP-Visualizza cronologia";
	public static final String OP_ANNULLAREVOCA = "OP-Annulla/Revoca";
	public static final String OP_RETTIFICA_BENEFICIARIO = "OP-RettificaBeneficiario";
	public static final String OP_RETTIFICA_MEDICO = "OP-RettificaMedico";
	public static final String OP_RETTIFICA = "OP-Rettifica";
	public static final String OP_RETTIFICAESENZIONE = "OP-RettificaEsenzione";
	public static final String OP_CREA_VALIDA_ESENZIONE = "OP-CreaValidaEsenzione";
	public static final String OP_INSERISCI_NUOVA_ESENZIONE = "OP-InserisciNuovaEsenzione";
	
	public static final List<String> STATI_PRATICA_ARCHIVIATA = new ArrayList<String>(Arrays
			.asList(STATO_PRATICA_ANNULLATA, STATO_PRATICA_RESPINTA, STATO_PRATICA_SCADUTA, STATO_PRATICA_REVOCATA));

	public static final List<String> STATI_PRATICA_NON_ARCHIVIATA = new ArrayList<String>(
			Arrays.asList(STATO_PRATICA_INVIATA_DAL_MEDICO, STATO_PRATICA_INVIATA, STATO_PRATICA_RICHIESTA_APPUNTAMENTO,
					STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_OPERATORE, STATO_PRATICA_DA_VALIDARE,
					STATO_PRATICA_IN_LAVORAZIONE, STATO_PRATICA_VALIDATA, STATO_PRATICA_IN_SCADENZA,
					STATO_PRATICA_RICHIESTA_RETTIFICA_DATI_MEDICO, STATO_PRATICA_VALIDATA_IN_SCADENZA));

	public static final String MSG145 = "MSG145"; // Parametri obbligatori non presenti
	public static final String MSG012 = "MSG012";
	public static final String MSG147 = "MSG147";
	public static final String MSG148 = "MSG148";
	public static final String MSG149 = "MSG149";
	public static final String MSG143 = "MSG143";
	public static final String MSG150 = "MSG150";
	public static final String MSG151 = "MSG151";
	public static final String MSG005 = "MSG005";
	public static final String MESSAGE = "message";
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";
	public static final String DATE_FORMAT_ITALIAN = "dd/MM/yyyy";
	public static final String DATE_FORMAT_ITALIAN_WITH_TIME = "dd/MM/yyyy HH:mm";
	public static final String CODE = "code";
	public static final String DATA = "data";
	public static final String TITLE = "title";
	public static final String STATUS = "status";
	public static final String OK = "ok";
	public static final String KO = "ko";

	public static final String GRUPPO_ESENZIONE_MALATTIA_RARA = "MR";
	public static final String GRUPPO_ESENZIONE_INVALIDITA = "IN";
	public static final String GRUPPO_ESENZIONE_MALATTIA_CRONICA = "MC";
	public static final String GRUPPO_ESENZIONE_ALTRE = "AL";

	public static final String CODICE_PROGRESSIVO_NUMERO_PRATICA = "NUMP";
	public static final String CODICE_PROGRESSIVO_ATTESTATO_ESENZIONE = "AE";
	public static final String CODICE_PROGRESSIVO_CERTIFICATO_MEDICO = "CM";

	public static final String STATO_DOCUMENTO_DA_FIRMARE = "1";
	public static final String STATO_DOCUMENTO_VALIDO = "2";
	public static final String STATO_DOCUMENTO_SCADUTO = "3";
	public static final String STATO_DOCUMENTO_ANNULLATO = "4";
	public static final String STATO_DOCUMENTO_VALIDOINATTESA = "5";
	
	public static final String AZIONE_RICERCA_ESENZIONE = "6";

	public static final String TIPO_DOCUMENTO_CERTIFICATO_CONDIZIONE_MALATTIA = "ccm";
	public static final String TIPO_DOCUMENTO_ATTESTATO_ESENZIONE = "aes";
	public static final String TIPO_DOCUMENTO_CARTELLA_CLINICA_ELETTRONICA = "cce";
	public static final String TIPO_DOCUMENTO_VERBALE_INVALIDITA = "vin";
	public static final String TIPO_DOCUMENTO_CERTIFICAZIONE_RILASCIATA_SPECIALISTI = "crs";
	public static final String TIPO_DOCUMENTO_CENTRO_RETE_INTERREGIONALE_MALATTIE_RARE = "cmr";

	public static final String DOCUMENT_TYPE_NUOVA_ESENZIONE = "01";
	public static final String DOCUMENT_TYPE_PRENOTAZIONE_ESENZIONE = "02";
	public static final String DOCUMENT_TYPE_RINNOVO_ESENZIONE = "03";
	public static final String DOCUMENT_TYPE_ANNULLAMENTO_ESENZIONE = "04";
	public static final String DOCUMENT_TYPE_REVOCA_ESENZIONE = "05";
	public static final String DOCUMENT_TYPE_RETTIFICA_ESENZIONE = "06";
	public static final String DOCUMENT_TYPE_SINGOLA_ESENZIONE = "07";

	public static final String PREFISSO_NOME_FILE_CERTIFICATO_MALATTIA = "Certificato_malattia";
	public static final String PREFISSO_NOME_FILE_ATTESTATO_ESENZIONE = "Attestato_esenzione";

	public static final List<String> DOC_TYPES = new ArrayList<String>(Arrays.asList(DOCUMENT_TYPE_NUOVA_ESENZIONE,
			DOCUMENT_TYPE_PRENOTAZIONE_ESENZIONE, DOCUMENT_TYPE_RINNOVO_ESENZIONE, DOCUMENT_TYPE_ANNULLAMENTO_ESENZIONE,
			DOCUMENT_TYPE_REVOCA_ESENZIONE, DOCUMENT_TYPE_RETTIFICA_ESENZIONE, DOCUMENT_TYPE_SINGOLA_ESENZIONE));

	public static final List<String> TEMPLATE_1 = new ArrayList<String>(Arrays.asList(DOCUMENT_TYPE_NUOVA_ESENZIONE,
			DOCUMENT_TYPE_PRENOTAZIONE_ESENZIONE, DOCUMENT_TYPE_RINNOVO_ESENZIONE, DOCUMENT_TYPE_RETTIFICA_ESENZIONE));

	public static final List<String> TEMPLATE_2 = new ArrayList<String>(
			Arrays.asList(DOCUMENT_TYPE_ANNULLAMENTO_ESENZIONE, DOCUMENT_TYPE_REVOCA_ESENZIONE));

	public static final List<String> TEMPLATE_3 = new ArrayList<String>(Arrays.asList(DOCUMENT_TYPE_SINGOLA_ESENZIONE));

	@SuppressWarnings("serial")
	public static final Map<String, String> DOC_TEMPLATES = new HashMap<String, String>() {
		{
			put(DOCUMENT_TYPE_NUOVA_ESENZIONE, "NUOVA ESENZIONE");
			put(DOCUMENT_TYPE_PRENOTAZIONE_ESENZIONE, "PRENOTAZIONE ESENZIONE");
			put(DOCUMENT_TYPE_RINNOVO_ESENZIONE, "RINNOVO ESENZIONE");
			put(DOCUMENT_TYPE_ANNULLAMENTO_ESENZIONE, "ANNULLAMENTO ESENZIONE");
			put(DOCUMENT_TYPE_REVOCA_ESENZIONE, "REVOCA ESENZIONE");
			put(DOCUMENT_TYPE_RETTIFICA_ESENZIONE, "RETTIFICA ESENZIONE");
			put(DOCUMENT_TYPE_SINGOLA_ESENZIONE, "PROMEMORIA ESENZIONE");
		}
	};

	public static final String AURA_ERROR_E0010 = "E0010";
	public static final String MSG214 = "MSG214";
	public static final String MSG200 = "MSG200"; // Si è verificato un ERRORE: sistema non disponibile
	public static final String MSG201 = "MSG201"; // Selezionare una voce nel campo "motivazione"
	public static final String MSG211 = "MSG211";
	public static final String MSG212 = "MSG212"; // ATTENZIONE! Compilare il campo note
	public static final String MSG027 = "MSG027";
	public static final String MSG228 = "MSG228";
	public static final String MSG229 = "MSG229";
	public static final String MSG230 = "MSG230";
	public static final String MSG231 = "MSG231";
	public static final String MSG206 = "MSG206"; // Si è verificato un ERRORE: problemi durante la creazione dell'attestato nel formato PDF-A2
	public static final String MSG146 = "MSG146"; // Ci sono problemi di comunicazione con i servizi del repository	
	public static final String MSG233 = "MSG233";
	public static final String MSG234 = "MSG234";

	public static final String TAB_DA_FARE = "daFare";
	public static final String TAB_TUTTE_LE_PRATICHE = "tutteLePratiche";

	public static final String MEDICO_CAS = "MEDICO_SPECIALISTA_CAS";
	public static final String OPERATORE_ASL = "OPERATORE_ASL_DISTRETTO";
	public static final String MEDICO_SPECIALISTA = "MEDICO_SPECIALISTA_ASL_DISTRETTO";
	public static final String AMMINISTRATORE = "AMMINISTRATORE_CSI";
	
	public static final String[] CHECK_LISTA_STATI = {"RINNOVO-RESPINTA", "RINNOVO-ANNULLATA", "RESPINTA", "SCADUTA", "ANNULLATA", "REVOCATA"};

	public static final String PREFISSO_OID = "2.16.840.1.113883.2.9.2.10.4.4.10";

	public static final String PASS_PROPERTIES = "configuration/prop.properties";
	public static final String CRYPT_PASSWORD = "crypt.password";

	public static final String OID_CCM = "C_C_M";
	public static final String OID_AES = "A_E";
}