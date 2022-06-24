--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 12.0

-- Started on 2019-11-18 15:33:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 148905)
-- Name: esenred; Type: SCHEMA; Schema: -; Owner: esenred
--

CREATE SCHEMA esenred;


ALTER SCHEMA esenred OWNER TO esenred;

SET default_tablespace = '';

--
-- TOC entry 186 (class 1259 OID 148906)
-- Name: CSI_LOG_AUDIT; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."CSI_LOG_AUDIT" (
    "AUDIT_ID" integer NOT NULL,
    "DATA_ORA" timestamp without time zone NOT NULL,
    "ID_APP" character varying NOT NULL,
    "ID_REQUEST" character varying,
    "IP_ADDRESS" character varying NOT NULL,
    "KEY_OPER" character varying,
    "OGG_OPER" character varying NOT NULL,
    "OPERAZIONE" character varying NOT NULL,
    "UTENTE" character varying,
    "UU_ID" character varying,
    "XCOD_SERVIZIO" character varying,
    "UTENTE_BEN" text,
    "UTENTE_DEL" text
);


ALTER TABLE esenred."CSI_LOG_AUDIT" OWNER TO esenred;

--
-- TOC entry 2707 (class 0 OID 0)
-- Dependencies: 186
-- Name: TABLE "CSI_LOG_AUDIT"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."CSI_LOG_AUDIT" IS 'Contiene dati di log inerenti l''utilizzo del Sistema';


--
-- TOC entry 2708 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."DATA_ORA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."DATA_ORA" IS 'Data e ora dell''evento';


--
-- TOC entry 2709 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."ID_APP"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."ID_APP" IS 'Identificativo dell''applicazione che ha effettuato l''operazione loggata.';


--
-- TOC entry 2710 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."ID_REQUEST"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."ID_REQUEST" IS 'Identificativo della richiesta di operazione.';


--
-- TOC entry 2711 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."IP_ADDRESS"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."IP_ADDRESS" IS 'IP della macchina che ha effettuato l''operazione loggata.';


--
-- TOC entry 2712 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."KEY_OPER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."KEY_OPER" IS 'Esito dell''operazione effettuata.
Questo campo dovrà contenere l''identificativo univoco dell''oggetto dell''operazione oppure nel caso di aggiornamenti multipli del valore che caratterizza l''insieme di oggetti (es: modifica di un dato in tutta una categoria merceologica)';


--
-- TOC entry 2713 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."OGG_OPER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."OGG_OPER" IS 'Oggetto dell''operazione effettuata.
Questo campo consentirà di identificare i dati e le informazioni trattati dall''operazione. Se la funzionalità lo permette inserire il nome delle tabelle (o in alternativa degli oggetti/entità) su cui viene eseguita l''operazione; l''indicazione della colonna è opzionale e andrà indicata nel formato tabella.colonna. Se l''applicativo prevede accessi a schemi dati esterni premettere il nome dello schema proprietario al nome della tabella.';


--
-- TOC entry 2714 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."OPERAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."OPERAZIONE" IS 'Descrizione della tipologia di operazione effettuata (Inser, Update, ecc.).
Questo campo dovrà contenere l''informazione circa l''operazione effettuata; utilizzare uno dei seguenti valori: login / logout / read / insert / update / delete / merge Nei casi in cui il nome dell''operazione di business sia significativo e non riconducibile all''elenco di cui sopra, è possibile indicare il nome dell''operazione.';


--
-- TOC entry 2715 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."UTENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."UTENTE" IS 'Utente che ha effettuato l''operazione loggata.';


--
-- TOC entry 2716 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."XCOD_SERVIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."XCOD_SERVIZIO" IS 'Codice del servizio invocato.';


--
-- TOC entry 2717 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."UTENTE_BEN"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."UTENTE_BEN" IS 'Utente beneficiario che ha effettuato l''operazione loggata.';


--
-- TOC entry 2718 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN "CSI_LOG_AUDIT"."UTENTE_DEL"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."CSI_LOG_AUDIT"."UTENTE_DEL" IS 'Utente Delegato che ha effettuato l''operazione loggata';


--
-- TOC entry 187 (class 1259 OID 148912)
-- Name: ESENRED_C_COMUNI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_C_COMUNI" (
    "COD_ISTAT" character varying(6) NOT NULL,
    "DENOMINAZIONE" character varying(100) NOT NULL,
    "CODICE_FISCALE" character varying(4) NOT NULL,
    "COD_PROVINCIA" character varying(2) NOT NULL,
    "COD_SIGLA_PROVINCIA" character varying(3) NOT NULL,
    "DENOMINAZIONE_PROVINCIA" character varying(100) NOT NULL,
    "COD_REGIONE" integer NOT NULL,
    "DENOMINAZIONE_REGIONE" character varying(30) NOT NULL
);


ALTER TABLE esenred."ESENRED_C_COMUNI" OWNER TO esenred;

--
-- TOC entry 2719 (class 0 OID 0)
-- Dependencies: 187
-- Name: TABLE "ESENRED_C_COMUNI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_C_COMUNI" IS 'Archivio dei Comuni italiani e degli Stati Esteri.';


--
-- TOC entry 2720 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."DENOMINAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."DENOMINAZIONE" IS 'Denominazione del Comune';


--
-- TOC entry 2721 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."CODICE_FISCALE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."CODICE_FISCALE" IS 'Codice fiscale del Comune';


--
-- TOC entry 2722 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."COD_PROVINCIA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."COD_PROVINCIA" IS 'Codice Nazionale della Provincia.';


--
-- TOC entry 2723 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."COD_SIGLA_PROVINCIA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."COD_SIGLA_PROVINCIA" IS 'Sigla della Provincia.';


--
-- TOC entry 2724 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."DENOMINAZIONE_PROVINCIA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."DENOMINAZIONE_PROVINCIA" IS 'Denominazione della Provincia.';


--
-- TOC entry 2725 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."COD_REGIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."COD_REGIONE" IS 'Codice Nazionale della Regione.';


--
-- TOC entry 2726 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN "ESENRED_C_COMUNI"."DENOMINAZIONE_REGIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_COMUNI"."DENOMINAZIONE_REGIONE" IS 'Denominazione della Regione.';


--
-- TOC entry 188 (class 1259 OID 148915)
-- Name: ESENRED_C_MATRICE_ESENZIONI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_C_MATRICE_ESENZIONI" (
    "COD_RICHIESTA" character varying(3) NOT NULL,
    "COD_ESISTENTE" character varying(3) NOT NULL,
    "FLAG_INSERIMENTO" character varying(2) NOT NULL
);


ALTER TABLE esenred."ESENRED_C_MATRICE_ESENZIONI" OWNER TO esenred;

--
-- TOC entry 2727 (class 0 OID 0)
-- Dependencies: 188
-- Name: TABLE "ESENRED_C_MATRICE_ESENZIONI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_C_MATRICE_ESENZIONI" IS 'Tabella contenente la matrice dei controlli sull’esistenza delle esenzioni a parità di Cittadino e Nuova esenzione richiesta';


--
-- TOC entry 2728 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN "ESENRED_C_MATRICE_ESENZIONI"."COD_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_MATRICE_ESENZIONI"."COD_RICHIESTA" IS 'Codice di esenzione richiesta.';


--
-- TOC entry 2729 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN "ESENRED_C_MATRICE_ESENZIONI"."COD_ESISTENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_MATRICE_ESENZIONI"."COD_ESISTENTE" IS 'Codice di esenzione esistente.';


--
-- TOC entry 2730 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN "ESENRED_C_MATRICE_ESENZIONI"."FLAG_INSERIMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_MATRICE_ESENZIONI"."FLAG_INSERIMENTO" IS 'Indica se l’esenzione richiesta può essere o meno rilasciata a fronte di una esenzione esistente. I possibili valori sono: SI/NO';


--
-- TOC entry 189 (class 1259 OID 148918)
-- Name: ESENRED_C_MESSAGGI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_C_MESSAGGI" (
    "CODICE" character varying(6) NOT NULL,
    "TESTO" character varying(1024) NOT NULL,
    "COD_TIPO_ERRORE" character varying,
    "TESTO_MSG_RESTITUITO" character varying,
    "IDMESSAGGIO" integer
);


ALTER TABLE esenred."ESENRED_C_MESSAGGI" OWNER TO esenred;

--
-- TOC entry 2731 (class 0 OID 0)
-- Dependencies: 189
-- Name: TABLE "ESENRED_C_MESSAGGI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_C_MESSAGGI" IS 'Tabella per la gestione dei messaggi gestiti all’interno del servizio.';


--
-- TOC entry 2732 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN "ESENRED_C_MESSAGGI"."CODICE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_MESSAGGI"."CODICE" IS 'Codice del messaggio';


--
-- TOC entry 2733 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN "ESENRED_C_MESSAGGI"."TESTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_MESSAGGI"."TESTO" IS 'Descrizione del messaggio';


--
-- TOC entry 190 (class 1259 OID 148921)
-- Name: ESENRED_C_PARAMETRI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_C_PARAMETRI" (
    "NUM_PROGRESSIVO" integer NOT NULL,
    "CODICE" character varying NOT NULL,
    "VALORE" character varying NOT NULL,
    "DESC_PARAMETRO" character varying
);


ALTER TABLE esenred."ESENRED_C_PARAMETRI" OWNER TO esenred;

--
-- TOC entry 2734 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN "ESENRED_C_PARAMETRI"."NUM_PROGRESSIVO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_PARAMETRI"."NUM_PROGRESSIVO" IS 'Identificativo del parametro';


--
-- TOC entry 2735 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN "ESENRED_C_PARAMETRI"."CODICE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_PARAMETRI"."CODICE" IS 'Codice del parametro';


--
-- TOC entry 2736 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN "ESENRED_C_PARAMETRI"."VALORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_PARAMETRI"."VALORE" IS 'Valore del parametro';


--
-- TOC entry 2737 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN "ESENRED_C_PARAMETRI"."DESC_PARAMETRO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_PARAMETRI"."DESC_PARAMETRO" IS 'Descrizione della tipologia di  parametro';


--
-- TOC entry 191 (class 1259 OID 148927)
-- Name: ESENRED_C_TITOLO_DICHIARANTE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_C_TITOLO_DICHIARANTE" (
    "COD_TITOLO" character varying(1) NOT NULL,
    "DESCRIZIONE" character varying(300) NOT NULL
);


ALTER TABLE esenred."ESENRED_C_TITOLO_DICHIARANTE" OWNER TO esenred;

--
-- TOC entry 2738 (class 0 OID 0)
-- Dependencies: 191
-- Name: TABLE "ESENRED_C_TITOLO_DICHIARANTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_C_TITOLO_DICHIARANTE" IS 'Tabella dei valori del titolo del dichiarante (fonte SOGEI).';


--
-- TOC entry 2739 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN "ESENRED_C_TITOLO_DICHIARANTE"."DESCRIZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_C_TITOLO_DICHIARANTE"."DESCRIZIONE" IS 'Descrizione del titolo';


--
-- TOC entry 192 (class 1259 OID 148930)
-- Name: ESENRED_D_AZIENDASANITARIA; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_D_AZIENDASANITARIA" (
    "ID_AZIENDA" character varying(6) NOT NULL,
    "COD_AZIENDA" character varying(3) NOT NULL,
    "COD_REGIONE" character varying(3) NOT NULL,
    "DENOMINAZIONE" character varying(100) NOT NULL,
    "DATA_CHIUSURA" date,
    "DATA_ATTIVAZIONE" date NOT NULL
);


ALTER TABLE esenred."ESENRED_D_AZIENDASANITARIA" OWNER TO esenred;

--
-- TOC entry 2740 (class 0 OID 0)
-- Dependencies: 192
-- Name: TABLE "ESENRED_D_AZIENDASANITARIA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_D_AZIENDASANITARIA" IS 'Anagrafica delle Aziende Sanitarie Nazionali ( ASL, ASO, IRCCS) e degli enti trattati alla pari di aziende a livello regionale.';


--
-- TOC entry 2741 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN "ESENRED_D_AZIENDASANITARIA"."COD_AZIENDA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."COD_AZIENDA" IS 'Codice ISTAT azienda';


--
-- TOC entry 2742 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN "ESENRED_D_AZIENDASANITARIA"."COD_REGIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."COD_REGIONE" IS 'codice ISTAT regione';


--
-- TOC entry 2743 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN "ESENRED_D_AZIENDASANITARIA"."DENOMINAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."DENOMINAZIONE" IS 'Descrizione della ASL';


--
-- TOC entry 2744 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN "ESENRED_D_AZIENDASANITARIA"."DATA_CHIUSURA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."DATA_CHIUSURA" IS 'Data di fine validità di una ASL.';


--
-- TOC entry 2745 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN "ESENRED_D_AZIENDASANITARIA"."DATA_ATTIVAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."DATA_ATTIVAZIONE" IS 'Data di inizio validità di una ASL.';


--
-- TOC entry 193 (class 1259 OID 148933)
-- Name: ESENRED_D_TIPI_ESENZIONI_REDDITO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO" (
    "DESC_MOTIVO" character varying(1000) NOT NULL,
    "DESC_ESENZIONE" character varying(100) NOT NULL,
    "COD_ESENZIONE" character varying(3) NOT NULL,
    "FLAG_INSERIBILE" integer
);


ALTER TABLE esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO" OWNER TO esenred;

--
-- TOC entry 2746 (class 0 OID 0)
-- Dependencies: 193
-- Name: TABLE "ESENRED_D_TIPI_ESENZIONI_REDDITO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO" IS 'Tipologia di esenzione per reddito prevista dalla normativa nazione e regionale.';


--
-- TOC entry 2747 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN "ESENRED_D_TIPI_ESENZIONI_REDDITO"."DESC_MOTIVO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"."DESC_MOTIVO" IS 'Descrizione estesa dell’esenzione.';


--
-- TOC entry 2748 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN "ESENRED_D_TIPI_ESENZIONI_REDDITO"."DESC_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"."DESC_ESENZIONE" IS ' Descrizione breve dell’esenzione.';


--
-- TOC entry 2749 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN "ESENRED_D_TIPI_ESENZIONI_REDDITO"."COD_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"."COD_ESENZIONE" IS 'Codice Nazionale dell’esenzione per reddito.';


--
-- TOC entry 2750 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN "ESENRED_D_TIPI_ESENZIONI_REDDITO"."FLAG_INSERIBILE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"."FLAG_INSERIBILE" IS 'Inserire 1 = SI se l''esenzione è selezionabile durante l''inserimento dell''esenzione e 0 = NO se non deve essere visibile nel menù a tendina';


--
-- TOC entry 194 (class 1259 OID 148939)
-- Name: ESENRED_T_ASL_OPERATORE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_T_ASL_OPERATORE" (
    "COD_FISCALE_OPERATORE" character varying(16) NOT NULL,
    "COD_ASL" character varying(6) NOT NULL,
	"NUM_DISTRETTO" integer
);


ALTER TABLE esenred."ESENRED_T_ASL_OPERATORE" OWNER TO esenred;

--
-- TOC entry 2751 (class 0 OID 0)
-- Dependencies: 194
-- Name: TABLE "ESENRED_T_ASL_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_T_ASL_OPERATORE" IS 'Contiene le informazioni delle ASL dell''operatore. Alimentata da CSI';


--
-- TOC entry 2752 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN "ESENRED_T_ASL_OPERATORE"."COD_FISCALE_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ASL_OPERATORE"."COD_FISCALE_OPERATORE" IS 'Codice fiscale dell''operatore.';


--
-- TOC entry 2753 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN "ESENRED_T_ASL_OPERATORE"."COD_ASL"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ASL_OPERATORE"."COD_ASL" IS 'Codice ASL in cui presta servizio l''operatore';


--
-- TOC entry 195 (class 1259 OID 148942)
-- Name: ESENRED_T_ESENZIONI_REDDITO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_T_ESENZIONI_REDDITO" (
    "COD_STATO" character varying(1) NOT NULL,
    "COD_TITOLO_DICHIARANTE" character varying(1) NOT NULL,
    "DATA_FINE" date NOT NULL,
    "DATA_INIZIO" date NOT NULL,
    "DATA_INSERT" date NOT NULL,
    "DATA_MODIFY" date,
    "DATA_REVOCA" date,
    "DATA_RICHIESTA" date NOT NULL,
    "DESC_MOTIVO_REVOCA" character varying(500),
    "FLAG_CONSENSO" integer NOT NULL,
    "ID_USER_INSERT" bigint NOT NULL,
    "ID_USER_MODIFY" bigint,
    "NUM_PROTOCOLLO_SOGEI" bigint,
    "ID_CITTADINO_DICHIARANTE" bigint NOT NULL,
    "ID_CITTADINO_BENEFICIARIO" bigint NOT NULL,
    "ID_OPERATORE_RICHIESTA" bigint,
    "COD_NAZIONALE_ASL_RILASCIO" character varying(6) NOT NULL,
    "COD_ESENZIONE" character varying(3) NOT NULL,
    "ID_ESENZIONE" bigint NOT NULL,
    "NOTA" character varying(500),
    "ID_CITTADINO_TITOLARE" bigint,
    "CF_DICHIARANTE_FR" character varying(16),
    "CF_TITOLARE_FR" character varying(16)
);


ALTER TABLE esenred."ESENRED_T_ESENZIONI_REDDITO" OWNER TO esenred;

--
-- TOC entry 2754 (class 0 OID 0)
-- Dependencies: 195
-- Name: TABLE "ESENRED_T_ESENZIONI_REDDITO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_T_ESENZIONI_REDDITO" IS 'Esenzione per reddito rilasciata dalla Regione Piemonte.';


--
-- TOC entry 2755 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."COD_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."COD_STATO" IS 'Indica lo stato in cui si trova una esenzione per reddito.';


--
-- TOC entry 2756 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."COD_TITOLO_DICHIARANTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."COD_TITOLO_DICHIARANTE" IS 'Titolo del dichiarante. Valore espresso secondo la tabella 10.2 di 1.Servizio On Line Autocertificazione Per Esenzione Studio Funzionale - MAC-REGP-ESENRED-SFU-001-V02 Studio Funzionale Autocertificazione Esenzione per Reddito.docx';


--
-- TOC entry 2757 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DATA_FINE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DATA_FINE" IS 'Data di fine validità di una esenzione per reddito.';


--
-- TOC entry 2758 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DATA_INIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DATA_INIZIO" IS 'Data di inizio validità di una esenzione per reddito.';


--
-- TOC entry 2759 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DATA_INSERT"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DATA_INSERT" IS 'Data di inserimento della richiesta di esenzione per reddito.';


--
-- TOC entry 2760 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DATA_MODIFY"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DATA_MODIFY" IS 'Data di modifica della richiesta di esenzione per reddito.';


--
-- TOC entry 2761 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DATA_REVOCA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DATA_REVOCA" IS 'Data in cui l’esenzione è stata revocata.';


--
-- TOC entry 2762 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DATA_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DATA_RICHIESTA" IS 'Data della richiesta di esenzione per reddito.';


--
-- TOC entry 2763 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."DESC_MOTIVO_REVOCA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."DESC_MOTIVO_REVOCA" IS 'Descrizione del motivo per il quale l’esenzione è stata revocata';


--
-- TOC entry 2764 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."FLAG_CONSENSO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."FLAG_CONSENSO" IS 'Indica se il chek box del Disclaimer (dichiarazione di responsabilità) è stato o meno selezionato. 1 = SI 0 = NO';


--
-- TOC entry 2765 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."ID_USER_INSERT"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_USER_INSERT" IS 'Identificativo dell’utente che ha inserito la richiesta di esenzione per reddito.';


--
-- TOC entry 2766 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."ID_USER_MODIFY"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_USER_MODIFY" IS 'Identificativo dell’utente che ha modificato la richiesta di esenzione per reddito.';


--
-- TOC entry 2767 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."NUM_PROTOCOLLO_SOGEI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."NUM_PROTOCOLLO_SOGEI" IS 'Numero di protocollo rilasciato da SOGEI in merito alla pratica di inserimento esenzione.';


--
-- TOC entry 2768 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."ID_CITTADINO_DICHIARANTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_CITTADINO_DICHIARANTE" IS 'Identificativo del cittadino dichiarante';


--
-- TOC entry 2769 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."ID_CITTADINO_BENEFICIARIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_CITTADINO_BENEFICIARIO" IS 'Identificativo del cittadino beneficiario.';


--
-- TOC entry 2770 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."ID_OPERATORE_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_OPERATORE_RICHIESTA" IS 'Identificativo dell''operatore che registra la richiesta';


--
-- TOC entry 2771 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."COD_NAZIONALE_ASL_RILASCIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."COD_NAZIONALE_ASL_RILASCIO" IS 'Codice nazionale della ASL dell''utente che ha effettuato il rilascio dell''esenzione';


--
-- TOC entry 2772 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN "ESENRED_T_ESENZIONI_REDDITO"."ID_CITTADINO_TITOLARE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_CITTADINO_TITOLARE" IS 'Identificativo del cittadino titolare';


--
-- TOC entry 196 (class 1259 OID 148948)
-- Name: ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq" OWNER TO esenred;

--
-- TOC entry 2773 (class 0 OID 0)
-- Dependencies: 196
-- Name: ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq" OWNED BY esenred."ESENRED_T_ESENZIONI_REDDITO"."ID_ESENZIONE";


--
-- TOC entry 197 (class 1259 OID 148950)
-- Name: ESENRED_W_NOTIFICHE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENRED_W_NOTIFICHE" (
    "ID_NOTIFICA" bigint NOT NULL,
    "DESC_NOTIFICA" character varying(150) NOT NULL,
    "COD_TIPOLOGIA" character varying(1) NOT NULL,
    "DATA" date NOT NULL,
    "FLAG_CONSULTAZIONE" integer NOT NULL,
    "ID_OPERATORE" bigint,
    "ID_AURA" bigint,
	"SK_TIPOLOGIA_NOTIFICA" bigint,
	"CODICE_FISCALE" character varying(16)
);


ALTER TABLE esenred."ESENRED_W_NOTIFICHE" OWNER TO esenred;

--
-- TOC entry 2774 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE "ESENRED_W_NOTIFICHE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENRED_W_NOTIFICHE" IS 'Notifica prodotta dal Sistema ESENRED e destinata ad un Cittadino/Operatore a seguito della registrazione di una nuova esenzione per reddito o per la revoca di una esenzione in corso di validità.';


--
-- TOC entry 2775 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN "ESENRED_W_NOTIFICHE"."DESC_NOTIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_W_NOTIFICHE"."DESC_NOTIFICA" IS 'Descrizione della notifica.';


--
-- TOC entry 2776 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN "ESENRED_W_NOTIFICHE"."COD_TIPOLOGIA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_W_NOTIFICHE"."COD_TIPOLOGIA" IS 'Indica la tipologia di notifica: I = esito inserimento nuova esenzione R= esito esame revoca notifica';


--
-- TOC entry 2777 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN "ESENRED_W_NOTIFICHE"."DATA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_W_NOTIFICHE"."DATA" IS 'Data della notifica.';


--
-- TOC entry 2778 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN "ESENRED_W_NOTIFICHE"."FLAG_CONSULTAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_W_NOTIFICHE"."FLAG_CONSULTAZIONE" IS 'Indica se la notifica è stata già consultata dal destinatario.';


--
-- TOC entry 2779 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN "ESENRED_W_NOTIFICHE"."ID_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_W_NOTIFICHE"."ID_OPERATORE" IS 'Identificativo dell''operatore destinatario della notifica.';


--
-- TOC entry 2780 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN "ESENRED_W_NOTIFICHE"."ID_AURA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENRED_W_NOTIFICHE"."ID_AURA" IS 'Identificativo del cittadino destinatario della notifica.';


--
-- TOC entry 198 (class 1259 OID 148953)
-- Name: ESENZIONE_D_DIAGNOSI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_DIAGNOSI" (
    "COD_DIAGNOSI" text NOT NULL,
    "SK_DIAGNOSI" integer NOT NULL,
    "DAT_FINE_VALIDITA" timestamp(6) without time zone,
    "DAT_INIZIO_VALIDITA" timestamp(6) without time zone NOT NULL,
    "DESC_DIAGNOSI" text NOT NULL,
    "NUM_DURATA" integer,
    "SK_ESENZIONE" bigint NOT NULL,
    "SK_DURATA_TIPO" bigint NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_DIAGNOSI" OWNER TO esenred;

--
-- TOC entry 2781 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE "ESENZIONE_D_DIAGNOSI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_DIAGNOSI" IS 'IIndica le tipologie di diagnosi per le quali è possibile richiedere una esenzione alla partecipazione alla spesa sanitaria.';


--
-- TOC entry 2782 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN "ESENZIONE_D_DIAGNOSI"."COD_DIAGNOSI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DIAGNOSI"."COD_DIAGNOSI" IS 'Codice della diagnosi';


--
-- TOC entry 2783 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN "ESENZIONE_D_DIAGNOSI"."SK_DIAGNOSI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DIAGNOSI"."SK_DIAGNOSI" IS 'Identificativo univoco della diagnosi nell''ambito dell''applicazione Esenzione';


--
-- TOC entry 2784 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN "ESENZIONE_D_DIAGNOSI"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DIAGNOSI"."DAT_FINE_VALIDITA" IS 'Data fine validità della diagnosi';


--
-- TOC entry 2785 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN "ESENZIONE_D_DIAGNOSI"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DIAGNOSI"."DAT_INIZIO_VALIDITA" IS 'Data inizio validità della diagnosi';


--
-- TOC entry 2786 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN "ESENZIONE_D_DIAGNOSI"."DESC_DIAGNOSI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DIAGNOSI"."DESC_DIAGNOSI" IS 'Descrizione della diagnosi';


--
-- TOC entry 2787 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN "ESENZIONE_D_DIAGNOSI"."NUM_DURATA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DIAGNOSI"."NUM_DURATA" IS 'Indica la durata dell''esenzione corrispondente alla specifica invalidità';


--
-- TOC entry 199 (class 1259 OID 148959)
-- Name: ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq" OWNER TO esenred;

--
-- TOC entry 2788 (class 0 OID 0)
-- Dependencies: 199
-- Name: ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq" OWNED BY esenred."ESENZIONE_D_DIAGNOSI"."SK_DIAGNOSI";


--
-- TOC entry 200 (class 1259 OID 148961)
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" (
    "NUM_DISTRETTO" integer NOT NULL,
    "SK_DISTRETTO_SOCIO_SANITARIO" integer NOT NULL,
    "DENOMINAZIONE" text NOT NULL,
    "ID_AZIENDA" character varying
);


ALTER TABLE esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" OWNER TO esenred;

--
-- TOC entry 2789 (class 0 OID 0)
-- Dependencies: 200
-- Name: TABLE "ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" IS 'Elemento dell''articolazione territoriale stabilita dalla Regione per una ASL';


--
-- TOC entry 2790 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN "ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."NUM_DISTRETTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."NUM_DISTRETTO" IS 'Numero di Distretto';


--
-- TOC entry 2791 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN "ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."SK_DISTRETTO_SOCIO_SANITARIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."SK_DISTRETTO_SOCIO_SANITARIO" IS 'Identificativo univoco del Distretto Socio Sanitario';


--
-- TOC entry 2792 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN "ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."DENOMINAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."DENOMINAZIONE" IS 'Denominazione del Distretto ';


--
-- TOC entry 201 (class 1259 OID 148967)
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq" OWNER TO esenred;

--
-- TOC entry 2793 (class 0 OID 0)
-- Dependencies: 201
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq" OWNED BY esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"."SK_DISTRETTO_SOCIO_SANITARIO";


--
-- TOC entry 202 (class 1259 OID 148969)
-- Name: ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" (
    "SK_TIPO_DOCUMENTO" bigint NOT NULL,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "SK_GRUPPO" bigint NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone
);


ALTER TABLE esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" OWNER TO esenred;

--
-- TOC entry 2794 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE "ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" IS 'Contiene l''insieme delle tipologie di documenti producibili per ciascun gruppo esenzioni';


--
-- TOC entry 2795 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità';


--
-- TOC entry 2796 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"."DAT_FINE_VALIDITA" IS 'Data di fine validità';


--
-- TOC entry 203 (class 1259 OID 148972)
-- Name: ESENZIONE_D_DOCUMENTO_STATO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_DOCUMENTO_STATO" (
    "COD_STATO" text NOT NULL,
    "DESC_STATO" text NOT NULL,
    "SK_DOCUMENTO_STATO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_DOCUMENTO_STATO" OWNER TO esenred;

--
-- TOC entry 2797 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE "ESENZIONE_D_DOCUMENTO_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_DOCUMENTO_STATO" IS 'Contiene le tipologie di stato che può assumere un documento prodotto nell''ambito di una pratica di richiesta esenzione per malattia cronica, malattia rara e invalidità';


--
-- TOC entry 2798 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_STATO"."COD_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_STATO"."COD_STATO" IS 'Codice di uno stato che può assumere un documento';


--
-- TOC entry 2799 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_STATO"."DESC_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_STATO"."DESC_STATO" IS 'Descrizione dello stato';


--
-- TOC entry 2800 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_STATO"."SK_DOCUMENTO_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_STATO"."SK_DOCUMENTO_STATO" IS 'Identificativo univoco della tipologia di stato documento';


--
-- TOC entry 204 (class 1259 OID 148978)
-- Name: ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq" OWNER TO esenred;

--
-- TOC entry 2801 (class 0 OID 0)
-- Dependencies: 204
-- Name: ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq" OWNED BY esenred."ESENZIONE_D_DOCUMENTO_STATO"."SK_DOCUMENTO_STATO";


--
-- TOC entry 205 (class 1259 OID 148980)
-- Name: ESENZIONE_D_DOCUMENTO_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_DOCUMENTO_TIPO" (
    "COD_DOCUMENTO_TIPO" text NOT NULL,
    "DESC_DOCUMENTO_TIPO" text NOT NULL,
    "SK_DOCUMENTO_TIPO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_DOCUMENTO_TIPO" OWNER TO esenred;

--
-- TOC entry 2802 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE "ESENZIONE_D_DOCUMENTO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_DOCUMENTO_TIPO" IS 'Indica le tipologie di documento';


--
-- TOC entry 2803 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_TIPO"."COD_DOCUMENTO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_TIPO"."COD_DOCUMENTO_TIPO" IS 'Tipologia di documento allegato.
I possibili valori sono:
CM - Certificato di condizione o malattia 
AE - Attestato di Esenzione 
CC - cartella clinica
VI - verbale di invalidità contenente la diagnosi
DR - documentazione rilasciata dal centro della “Rete Interregionale per le Malattie Rare ';


--
-- TOC entry 2804 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_TIPO"."DESC_DOCUMENTO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_TIPO"."DESC_DOCUMENTO_TIPO" IS 'Descrizione della tipologia di documento';


--
-- TOC entry 2805 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "ESENZIONE_D_DOCUMENTO_TIPO"."SK_DOCUMENTO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DOCUMENTO_TIPO"."SK_DOCUMENTO_TIPO" IS 'Identificativo univoco della tipologia di documento';


--
-- TOC entry 206 (class 1259 OID 148986)
-- Name: ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq" OWNER TO esenred;

--
-- TOC entry 2806 (class 0 OID 0)
-- Dependencies: 206
-- Name: ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq" OWNED BY esenred."ESENZIONE_D_DOCUMENTO_TIPO"."SK_DOCUMENTO_TIPO";


--
-- TOC entry 207 (class 1259 OID 148988)
-- Name: ESENZIONE_D_DURATA_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_DURATA_TIPO" (
    "COD_DURATA_TIPO" text NOT NULL,
    "DESC_DURATA_TIPO" text NOT NULL,
    "SK_DURATA_TIPO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_DURATA_TIPO" OWNER TO esenred;

--
-- TOC entry 2807 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE "ESENZIONE_D_DURATA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_DURATA_TIPO" IS 'Contiene le tipologie di durata di una diagnosi';


--
-- TOC entry 2808 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "ESENZIONE_D_DURATA_TIPO"."COD_DURATA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DURATA_TIPO"."COD_DURATA_TIPO" IS 'Indica il modo in cui può essere espressa la durata di una diagnosi
I possibili valori sono:
G=giorni
M=mesi
A=anni
I=Illimitata';


--
-- TOC entry 2809 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "ESENZIONE_D_DURATA_TIPO"."DESC_DURATA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DURATA_TIPO"."DESC_DURATA_TIPO" IS 'Descrizione del modo in cui può essere espressa la durata di una diagnosi.';


--
-- TOC entry 2810 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "ESENZIONE_D_DURATA_TIPO"."SK_DURATA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_DURATA_TIPO"."SK_DURATA_TIPO" IS 'Identificativo univoco della tipologia di durata di una diagnosi';


--
-- TOC entry 208 (class 1259 OID 148994)
-- Name: ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq" OWNER TO esenred;

--
-- TOC entry 2811 (class 0 OID 0)
-- Dependencies: 208
-- Name: ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq" OWNED BY esenred."ESENZIONE_D_DURATA_TIPO"."SK_DURATA_TIPO";


--
-- TOC entry 209 (class 1259 OID 148996)
-- Name: ESENZIONE_D_ESENZIONE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_ESENZIONE" (
    "COD_ESENZIONE" text NOT NULL,
    "DESC_ESENZIONE" text NOT NULL,
    "FLAG_ESENZIONE_048" boolean NOT NULL,
    "SK_ESENZIONE" integer NOT NULL,
    "SK_GRUPPO" bigint NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_ESENZIONE" OWNER TO esenred;

--
-- TOC entry 2812 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE "ESENZIONE_D_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_ESENZIONE" IS 'Esenzione alla compartecipazione alla spesa sanitaria riconosciuta dalla normativa in vigore.';


--
-- TOC entry 2813 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN "ESENZIONE_D_ESENZIONE"."COD_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_ESENZIONE"."COD_ESENZIONE" IS 'Codice Nazionale dell’esenzione.';


--
-- TOC entry 2814 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN "ESENZIONE_D_ESENZIONE"."DESC_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_ESENZIONE"."DESC_ESENZIONE" IS 'Descrizione dell''Esenzione';


--
-- TOC entry 2815 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN "ESENZIONE_D_ESENZIONE"."FLAG_ESENZIONE_048"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_ESENZIONE"."FLAG_ESENZIONE_048" IS 'Indica se l''esenzione deve essere visibile o meno agli operatori';


--
-- TOC entry 2816 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN "ESENZIONE_D_ESENZIONE"."SK_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_ESENZIONE"."SK_ESENZIONE" IS 'Identificativo univoco della tipologia di ''esenzione nell''applicazione Esenzione';


--
-- TOC entry 210 (class 1259 OID 149002)
-- Name: ESENZIONE_D_ESENZIONE_AURA_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO" (
    "COD_TIPOLOGIA_ESENZIONE_AURA" text NOT NULL,
	"SK_TIPOLOGIA_ESENZIONE_AURA" integer NOT NULL,
    "DESC_TIPOLOGIA_ESENZIONE_AURA" text NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO" OWNER TO esenred;

--
-- TOC entry 2817 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE "ESENZIONE_D_ESENZIONE_AURA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO" IS 'Tipologie di esenzioni riconosciute in AURA';


--
-- TOC entry 2818 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN "ESENZIONE_D_ESENZIONE_AURA_TIPO"."COD_TIPOLOGIA_ESENZIONE_AURA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO"."COD_TIPOLOGIA_ESENZIONE_AURA" IS 'Codice utilizzato in AURA per rappresentare un gruppo esenzioni. I possibili valori sono:
C = Civili
K = Civili ciechi e Sordomuti
F = Detenuti
G = Guerra
L = Lavoro
N = Legge 210
O = Malattie croniche
R = Rare
S = Servizio
T = Donazione
V = Vittime';


--
-- TOC entry 2819 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN "ESENZIONE_D_ESENZIONE_AURA_TIPO"."DESC_TIPOLOGIA_ESENZIONE_AURA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO"."DESC_TIPOLOGIA_ESENZIONE_AURA" IS 'Descrizione della tipologia di esenzione utilizzata in AURA.';


--
-- TOC entry 211 (class 1259 OID 149010)
-- Name: ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq" OWNER TO esenred;

--
-- TOC entry 2820 (class 0 OID 0)
-- Dependencies: 211
-- Name: ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq" OWNED BY esenred."ESENZIONE_D_ESENZIONE"."SK_ESENZIONE";


--
-- TOC entry 212 (class 1259 OID 149012)
-- Name: ESENZIONE_D_GRUPPO_ESENZIONI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_GRUPPO_ESENZIONI" (
    "COD_TIPOLOGIA_GRUPPO" text NOT NULL,
    "DESC_GRUPPO" text NOT NULL,
    "SK_GRUPPO" integer NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_GRUPPO_ESENZIONI" OWNER TO esenred;

--
-- TOC entry 2821 (class 0 OID 0)
-- Dependencies: 212
-- Name: TABLE "ESENZIONE_D_GRUPPO_ESENZIONI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_GRUPPO_ESENZIONI" IS 'Raggruppamento di tipologie di esenzioni alla partecipazione alla spesa sanitaria.';


--
-- TOC entry 2822 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN "ESENZIONE_D_GRUPPO_ESENZIONI"."COD_TIPOLOGIA_GRUPPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_ESENZIONI"."COD_TIPOLOGIA_GRUPPO" IS 'Indica la tipologia di operatore abilitato alla registrazione di esenzioni che rientrano nel gruppo esenzioni I possibili valori sono:
O = Operatore di back-office (Amministrativo, Medico, ecc.)
C = Cittadino
T = Tutti';


--
-- TOC entry 2823 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN "ESENZIONE_D_GRUPPO_ESENZIONI"."DESC_GRUPPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_ESENZIONI"."DESC_GRUPPO" IS 'Descrizione del gruppo di esenzioni. ';


--
-- TOC entry 2824 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN "ESENZIONE_D_GRUPPO_ESENZIONI"."SK_GRUPPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_ESENZIONI"."SK_GRUPPO" IS 'Identificativo univoco del gruppo di esenzioni nell''applicazione Esenzioni. ';


--
-- TOC entry 2825 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN "ESENZIONE_D_GRUPPO_ESENZIONI"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_ESENZIONI"."DAT_FINE_VALIDITA" IS 'Data di fine validità del gruppo esenzioni';


--
-- TOC entry 2826 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN "ESENZIONE_D_GRUPPO_ESENZIONI"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_ESENZIONI"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità del gruppo esenzioni';


--
-- TOC entry 213 (class 1259 OID 149018)
-- Name: ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq" OWNER TO esenred;

--
-- TOC entry 2827 (class 0 OID 0)
-- Dependencies: 213
-- Name: ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq" OWNED BY esenred."ESENZIONE_D_GRUPPO_ESENZIONI"."SK_GRUPPO";


--
-- TOC entry 214 (class 1259 OID 149020)
-- Name: ESENZIONE_D_GRUPPO_UTENTI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_GRUPPO_UTENTI" (
    "COD_GRUPPO_UTENTI" text NOT NULL,
    "DESC_GRUPPO_UTENTI" text NOT NULL,
    "SK_GRUPPO_UTENTI" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_GRUPPO_UTENTI" OWNER TO esenred;

--
-- TOC entry 2828 (class 0 OID 0)
-- Dependencies: 214
-- Name: TABLE "ESENZIONE_D_GRUPPO_UTENTI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_GRUPPO_UTENTI" IS 'Rappresenta un gruppo utenti che svolgono un detrinato ruolo a cui corrispondono determinate abilitazioni';


--
-- TOC entry 2829 (class 0 OID 0)
-- Dependencies: 214
-- Name: COLUMN "ESENZIONE_D_GRUPPO_UTENTI"."COD_GRUPPO_UTENTI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_UTENTI"."COD_GRUPPO_UTENTI" IS 'Codice del gruppo utenti';


--
-- TOC entry 2830 (class 0 OID 0)
-- Dependencies: 214
-- Name: COLUMN "ESENZIONE_D_GRUPPO_UTENTI"."DESC_GRUPPO_UTENTI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_UTENTI"."DESC_GRUPPO_UTENTI" IS 'Descrizione del gruppo utenti';


--
-- TOC entry 2831 (class 0 OID 0)
-- Dependencies: 214
-- Name: COLUMN "ESENZIONE_D_GRUPPO_UTENTI"."SK_GRUPPO_UTENTI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_GRUPPO_UTENTI"."SK_GRUPPO_UTENTI" IS 'Codice identificativo del gruppo utenti';


--
-- TOC entry 215 (class 1259 OID 149026)
-- Name: ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq" OWNER TO esenred;

--
-- TOC entry 2832 (class 0 OID 0)
-- Dependencies: 215
-- Name: ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq" OWNED BY esenred."ESENZIONE_D_GRUPPO_UTENTI"."SK_GRUPPO_UTENTI";


--
-- TOC entry 216 (class 1259 OID 149028)
-- Name: ESENZIONE_D_INVALIDITA_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_INVALIDITA_TIPO" (
    "COD_INVALIDITA_TIPO" text NOT NULL,
    "DESC_INVALIDITA_TIPO" text NOT NULL,
    "SK_INVALIDITA_TIPO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_INVALIDITA_TIPO" OWNER TO esenred;

--
-- TOC entry 2833 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE "ESENZIONE_D_INVALIDITA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_INVALIDITA_TIPO" IS 'Contiene le tipologie di invalidità riconosciute dalla normativa in vigore';


--
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN "ESENZIONE_D_INVALIDITA_TIPO"."COD_INVALIDITA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_INVALIDITA_TIPO"."COD_INVALIDITA_TIPO" IS 'Tipologia di invalidità riscontrata sul paziente per la quale è stata richiesta l''esenzione per patologia.
I possibili valori sono:
G - invalidità di guerra
L - invalidità per lavoro
S - invalidità per servizio civile
T - invalidità vittime terrorismo- vittime del dovere';


--
-- TOC entry 2835 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN "ESENZIONE_D_INVALIDITA_TIPO"."DESC_INVALIDITA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_INVALIDITA_TIPO"."DESC_INVALIDITA_TIPO" IS 'Descrizione della tipologia di invalidità.';


--
-- TOC entry 2836 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN "ESENZIONE_D_INVALIDITA_TIPO"."SK_INVALIDITA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_INVALIDITA_TIPO"."SK_INVALIDITA_TIPO" IS 'Identificativo univoco della tipologia di invalidità.';


--
-- TOC entry 217 (class 1259 OID 149034)
-- Name: ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq" OWNER TO esenred;

--
-- TOC entry 2837 (class 0 OID 0)
-- Dependencies: 217
-- Name: ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq" OWNED BY esenred."ESENZIONE_D_INVALIDITA_TIPO"."SK_INVALIDITA_TIPO";


--
-- TOC entry 218 (class 1259 OID 149036)
-- Name: ESENZIONE_D_MOTIVAZIONE_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_MOTIVAZIONE_TIPO" (
    "COD_MOTIVAZIONE" text NOT NULL,
    "DESC_MOTIVAZIONE" text NOT NULL,
    "SK_MOTIVAZIONE_TIPO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_MOTIVAZIONE_TIPO" OWNER TO esenred;

--
-- TOC entry 2838 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE "ESENZIONE_D_MOTIVAZIONE_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_MOTIVAZIONE_TIPO" IS 'Tipologia di motivazione di una modifica effettuata su una pratica di esenzione';


--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 218
-- Name: COLUMN "ESENZIONE_D_MOTIVAZIONE_TIPO"."COD_MOTIVAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"."COD_MOTIVAZIONE" IS 'Codice della motivazione.';


--
-- TOC entry 2840 (class 0 OID 0)
-- Dependencies: 218
-- Name: COLUMN "ESENZIONE_D_MOTIVAZIONE_TIPO"."DESC_MOTIVAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"."DESC_MOTIVAZIONE" IS 'Descrizione della motivazione';


--
-- TOC entry 2841 (class 0 OID 0)
-- Dependencies: 218
-- Name: COLUMN "ESENZIONE_D_MOTIVAZIONE_TIPO"."SK_MOTIVAZIONE_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"."SK_MOTIVAZIONE_TIPO" IS 'Identificativo univoco della tipologia di motivazione';


--
-- TOC entry 219 (class 1259 OID 149042)
-- Name: ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq" OWNER TO esenred;

--
-- TOC entry 2842 (class 0 OID 0)
-- Dependencies: 219
-- Name: ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq" OWNED BY esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"."SK_MOTIVAZIONE_TIPO";


--
-- TOC entry 220 (class 1259 OID 149044)
-- Name: ESENZIONE_D_NOTIFICA_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_NOTIFICA_TIPO" (
    "SK_NOTIFICA_TIPO" integer NOT NULL,
    "DESC_NOTIFICA" text NOT NULL,
    "COD_NOTIFICA" text NOT NULL,
    "SK_DESTINATARIO_TIPO" bigint NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_NOTIFICA_TIPO" OWNER TO esenred;

--
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE "ESENZIONE_D_NOTIFICA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_NOTIFICA_TIPO" IS 'Tipologia di notifica generata ed inviata al destinatario tramite il Notificatore';


--
-- TOC entry 2844 (class 0 OID 0)
-- Dependencies: 220
-- Name: COLUMN "ESENZIONE_D_NOTIFICA_TIPO"."SK_NOTIFICA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_NOTIFICA_TIPO"."SK_NOTIFICA_TIPO" IS 'Identificativo univoco della tipologia notifica';


--
-- TOC entry 2845 (class 0 OID 0)
-- Dependencies: 220
-- Name: COLUMN "ESENZIONE_D_NOTIFICA_TIPO"."DESC_NOTIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_NOTIFICA_TIPO"."DESC_NOTIFICA" IS 'Descrizione della notifica';


--
-- TOC entry 2846 (class 0 OID 0)
-- Dependencies: 220
-- Name: COLUMN "ESENZIONE_D_NOTIFICA_TIPO"."COD_NOTIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_NOTIFICA_TIPO"."COD_NOTIFICA" IS 'Codice della notifica.
I possibili valori sono:
B1: sono state rilevate variazioni di stato della richiesta di esenzione per patologia 
B2: una esenzione per patologia è stata approvata 
B3:  una esenzione per patologia è in prossimità della scadenza 
B4: una esenzione per patologia è scaduta 
B5: una esenzione per patologia è stata rinnovata 
B6: una esenzione per patologia è stata annullata 
B7: una esenzione per patologia è stata Respinta 
B8: una esenzione per patologia è stata inviata 
B9: un operatore ha consultato la tua esenzione 
B11: il delegato ha inviato una richiesta di esenzione patologia
B12 :una esenzione per patologia è stata revocata
B13: una esenzione per patologia è stata rettificata
B14 :il delegato ha revocato una esenzione per patologia
B15: il delegato ha rettificato una esenzione per patologia 
D1: sono state rilevate variazioni di stato della richiesta di esenzione per patologia 
D2: una esenzione per patologia è stata approvata 
D3: una esenzione per patologia è in prossimità della scadenza 
D4: una esenzione per patologia è scaduta 
D5: una esenzione per patologia è stata rinnovata 
D6: una esenzione per patologia è stata annullata 
D7: una esenzione per patologia è stata respinta 
D8: una esenzione per patologia è stata inviata 
D9: un operatore ha consultato l’esenzione di un tuo delegante 
D12 :una esenzione per patologia è stata revocata
D13: una esenzione per patologia è stata rettificata
L1: Il delegato ha effettuato un accesso al servizio “Esenzioni per Patologia" 
L2: sono state rilevate variazioni di stato della richiesta di esenzione per patologia (con specifica dell’utente che ha effettuato la modifica) 
L3: una esenzione per patologia è stata approvata 
L4: una esenzione per patologia è in prossimità della scadenza 
L5: una esenzione per patologia è scaduta 
L6: una esenzione per patologia è stata rinnovata 
L7: una esenzione per patologia è stata annullata 
L8: una esenzione per patologia è stata inviata 
L9: una esenzione per patologia è stata respinta
L10: un operatore ha consultato la tua esenzione';


--
-- TOC entry 221 (class 1259 OID 149050)
-- Name: ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq" OWNER TO esenred;

--
-- TOC entry 2847 (class 0 OID 0)
-- Dependencies: 221
-- Name: ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq" OWNED BY esenred."ESENZIONE_D_NOTIFICA_TIPO"."SK_NOTIFICA_TIPO";


--
-- TOC entry 222 (class 1259 OID 149052)
-- Name: ESENZIONE_D_PRATICA_STATO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_PRATICA_STATO" (
    "COD_STATO" text NOT NULL,
    "DESC_STATO" text NOT NULL,
    "SK_PRATICA_STATO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_PRATICA_STATO" OWNER TO esenred;

--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE "ESENZIONE_D_PRATICA_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_PRATICA_STATO" IS 'Contiene le tipologie di stato in cui si può trovare una pratica di esenzione';


--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN "ESENZIONE_D_PRATICA_STATO"."COD_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRATICA_STATO"."COD_STATO" IS 'Codice dello stato.
I possibili valori sono:
I - INVIATA
R - RINNOVATA
S - SCADUTA
C - IN SCADENZA
A - ANNULLATA
V - REVOCATA
P - RESPINTA
T - RICHIESTA RETTIFICA DATI';


--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN "ESENZIONE_D_PRATICA_STATO"."DESC_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRATICA_STATO"."DESC_STATO" IS 'Descrizione dello stato';


--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN "ESENZIONE_D_PRATICA_STATO"."SK_PRATICA_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRATICA_STATO"."SK_PRATICA_STATO" IS 'Identificativo univoco della tipologia di stato che può assumere una pratica';


--
-- TOC entry 223 (class 1259 OID 149058)
-- Name: ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq" OWNER TO esenred;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 223
-- Name: ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq" OWNED BY esenred."ESENZIONE_D_PRATICA_STATO"."SK_PRATICA_STATO";


--
-- TOC entry 224 (class 1259 OID 149060)
-- Name: ESENZIONE_D_PRESTAZIONE_SPECIALISTICA; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA" (
    "COD_NOTA" text,
    "SK_PRESTAZIONE" integer NOT NULL,
    "COD_PREST_PUNTO" text,
    "DESC_PRESTAZIONE" text NOT NULL,
    "FLAG_CICLI_TERAPEU" boolean NOT NULL,
    "COD_PRESTAZIONE" text NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA" OWNER TO esenred;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA" IS 'Prestazione specialistica o di diagnostica strumentale e di
laboratorio, compresa la diagnostica radioimmunologica, la medicina nucleare e di fisiokinesiterapia.';


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."COD_NOTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."COD_NOTA" IS 'Specifica la classe di appartenenza di una prestazione specialistica';


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."SK_PRESTAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."SK_PRESTAZIONE" IS 'Identificativo univoco della prestazione nell''ambito dell''applicazione Esenzione ';


--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."COD_PREST_PUNTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."COD_PREST_PUNTO" IS 'Codice della prestazione strutturato come riportato nel D.M. 22/07/96';


--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."DESC_PRESTAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."DESC_PRESTAZIONE" IS 'Denominazione della prestazione specialistica come da nomenclatore tariffario regionale';


--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."FLAG_CICLI_TERAPEU"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."FLAG_CICLI_TERAPEU" IS 'Indica se la prestazione è ciclica. I valori possibili sono:
0 - non ciclica (False);
1 - ciclica (True).';


--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 224
-- Name: COLUMN "ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."COD_PRESTAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."COD_PRESTAZIONE" IS 'Codice della prestazione specialistica come da nomenclatore tariffario regionale.';


--
-- TOC entry 225 (class 1259 OID 149066)
-- Name: ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq" OWNER TO esenred;

--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 225
-- Name: ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq" OWNED BY esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"."SK_PRESTAZIONE";


--
-- TOC entry 226 (class 1259 OID 149068)
-- Name: ESENZIONE_D_SERVIZIO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_SERVIZIO" (
    "COD_SERVIZIO" text NOT NULL,
    "DESC_SERVIZIO" text NOT NULL,
    "SK_SERVIZIO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_SERVIZIO" OWNER TO esenred;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 226
-- Name: TABLE "ESENZIONE_D_SERVIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_SERVIZIO" IS 'Elenco dei servizi previsti dall''applicazione';


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 226
-- Name: COLUMN "ESENZIONE_D_SERVIZIO"."COD_SERVIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_SERVIZIO"."COD_SERVIZIO" IS 'Codice del servizio';


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 226
-- Name: COLUMN "ESENZIONE_D_SERVIZIO"."DESC_SERVIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_SERVIZIO"."DESC_SERVIZIO" IS 'Descrizione del servizio';


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 226
-- Name: COLUMN "ESENZIONE_D_SERVIZIO"."SK_SERVIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_D_SERVIZIO"."SK_SERVIZIO" IS 'Codice identificativo del servizio';


--
-- TOC entry 227 (class 1259 OID 149074)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" (
    "SK_GRUPPO_UTENTI" integer NOT NULL,
    "SK_SERVIZIO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" OWNER TO esenred;

--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 227
-- Name: TABLE "ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" IS 'Rappresenta l''elenco dei servizi abilitati per un gruppo utenti';


--
-- TOC entry 228 (class 1259 OID 149077)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq" OWNER TO esenred;

--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 228
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq" OWNED BY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI"."SK_GRUPPO_UTENTI";


--
-- TOC entry 229 (class 1259 OID 149079)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq" OWNER TO esenred;

--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 229
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq" OWNED BY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI"."SK_SERVIZIO";


--
-- TOC entry 230 (class 1259 OID 149081)
-- Name: ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq" OWNER TO esenred;

--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 230
-- Name: ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq" OWNED BY esenred."ESENZIONE_D_SERVIZIO"."SK_SERVIZIO";


--
-- TOC entry 231 (class 1259 OID 149083)
-- Name: ESENZIONE_R_DESTINATARIO_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_R_DESTINATARIO_TIPO" (
    "COD_DESTINATARIO_TIPO" text NOT NULL,
    "DESC_DESTINATRIO_TIPO" text NOT NULL,
    "SK_DESTINATARIO_TIPO" integer NOT NULL
);


ALTER TABLE esenred."ESENZIONE_R_DESTINATARIO_TIPO" OWNER TO esenred;

--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 231
-- Name: TABLE "ESENZIONE_R_DESTINATARIO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_R_DESTINATARIO_TIPO" IS 'Indica le tipologie di destinatario delle notifiche';


--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 231
-- Name: COLUMN "ESENZIONE_R_DESTINATARIO_TIPO"."COD_DESTINATARIO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_DESTINATARIO_TIPO"."COD_DESTINATARIO_TIPO" IS 'Tipologia di destinatario della notifica.
I possibili valori sono:
B: Beneficiario
D: Delegato
L: deLegante';


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 231
-- Name: COLUMN "ESENZIONE_R_DESTINATARIO_TIPO"."DESC_DESTINATRIO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_DESTINATARIO_TIPO"."DESC_DESTINATRIO_TIPO" IS 'Descrizione della tipologia di destinatario della notifica.';


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 231
-- Name: COLUMN "ESENZIONE_R_DESTINATARIO_TIPO"."SK_DESTINATARIO_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_DESTINATARIO_TIPO"."SK_DESTINATARIO_TIPO" IS 'Identificativo univoco della tipologia di destinatario della notifica.';


--
-- TOC entry 232 (class 1259 OID 149089)
-- Name: ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq" OWNER TO esenred;

--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 232
-- Name: ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq" OWNED BY esenred."ESENZIONE_R_DESTINATARIO_TIPO"."SK_DESTINATARIO_TIPO";


--
-- TOC entry 233 (class 1259 OID 149091)
-- Name: ESENZIONE_R_DIAGNOSI_PRESTAZIONE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE" (
    "DAT_FINE" timestamp(6) without time zone,
    "DAT_INIZIO" timestamp(6) without time zone NOT NULL,
    "SK_DIAGNOSI" bigint NOT NULL,
    "SK_PRESTAZIONE" bigint NOT NULL
);


ALTER TABLE esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE" OWNER TO esenred;

--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 233
-- Name: TABLE "ESENZIONE_R_DIAGNOSI_PRESTAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE" IS 'Elenco delle prestazioni specialistiche per le quali è prevista l''esenzione alla partecipazione alla spesa sanitaria per una specifica diagnosi.';


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 233
-- Name: COLUMN "ESENZIONE_R_DIAGNOSI_PRESTAZIONE"."DAT_FINE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE"."DAT_FINE" IS 'Data fine validità appartenenza prestazione all''esenzione per tipologia di invalidità';


--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 233
-- Name: COLUMN "ESENZIONE_R_DIAGNOSI_PRESTAZIONE"."DAT_INIZIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE"."DAT_INIZIO" IS 'Data inizio validità appartenenza prestazione all''esenzione per tipologia di invalidità';


--
-- TOC entry 234 (class 1259 OID 149094)
-- Name: ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" (
    "SK_TIPOLOGIA_ESENZIONE_AURA" bigint NOT NULL,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "SK_GRUPPO" bigint NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone
);


ALTER TABLE esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" OWNER TO esenred;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 234
-- Name: TABLE "ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" IS 'Corrispondenza tra i codici gruppo esenzione dell’applicazione Esenzione ed i codici utilizzati per i gruppi esenzioni in AURA';


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 234
-- Name: COLUMN "ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità';


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 234
-- Name: COLUMN "ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"."DAT_FINE_VALIDITA" IS 'Data di fine validità';


--
-- TOC entry 235 (class 1259 OID 149097)
-- Name: ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" (
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone,
    "SK_MOTIVAZIONE_TIPO" bigint NOT NULL,
    "SK_PRATICA_STATO" bigint NOT NULL
);


ALTER TABLE esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" OWNER TO esenred;

--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 235
-- Name: TABLE "ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" IS 'Indica le associazioni delle tipologie di motivazioni con gli stati della pratica';


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 235
-- Name: COLUMN "ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"."DAT_INIZIO_VALIDITA" IS 'Data inizio validità dell''associazione tipologia motivazione-stato pratica';


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 235
-- Name: COLUMN "ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"."DAT_FINE_VALIDITA" IS 'Data fine validità dell''associazione tipologia motivazione-stato pratica';


--
-- TOC entry 236 (class 1259 OID 149100)
-- Name: ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" (
    "SK_DOCUMENTO" bigint NOT NULL,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "SK_PRATICA_ESENZIONE" bigint NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "DAT_CREAZIONE" timestamp without time zone,
    "DAT_MODIFICA" timestamp without time zone,
    "ID_USER" bigint,
    "COD_RUOLO_OPERATORE" text
);


ALTER TABLE esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" OWNER TO esenred;

--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 236
-- Name: TABLE "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" IS 'Rappresenta i documenti presentati nell''ambito di una pratica di esenzione per malattia cronica, malattia rara, invalidità.';


--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità dell''associazione del documento alla pratica';


--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_FINE_VALIDITA" IS 'Data di fine validità dell''associazione del documento alla pratica';


--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_CANCELLAZIONE" IS 'Data di cancellazione dell''associazione del documento alla pratica';


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_CREAZIONE" IS 'Data di creazione dell''associazione del documento alla pratica';


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."DAT_MODIFICA" IS 'Data di modifica dell''associazione del documento alla pratica';


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."ID_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."ID_USER" IS 'Identificativo dell''utente che ha movimentato l''associazione documento-pratica';


--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 236
-- Name: COLUMN "ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha registrato l''associazione del documento alla pratica';


--
-- TOC entry 237 (class 1259 OID 149106)
-- Name: ESENZIONE_S_DOCUMENTO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_S_DOCUMENTO" (
    "COD_RUOLO_OPERATORE" text,
    "COD_TIPO_USER" text NOT NULL,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "DAT_CREAZIONE" timestamp without time zone NOT NULL,
    "DAT_DOCUMENTO" timestamp without time zone NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "DAT_MODIFICA" timestamp without time zone,
    "DESC_NOTE" text,
    "FLAG_CONFORMITA_DOCUMENTO" boolean NOT NULL,
    "ID_USERID" bigint NOT NULL,
    "SK_STORICO_DOUMENTO" integer NOT NULL,
    "SK_DOCUMENTO" bigint NOT NULL,
    "DESC_ESTESA_PATOLOGIA_CERTIFICATO" text,
    "SK_TIPOLOGIA_STATO_DOCUMENTO" bigint NOT NULL,
    "SK_DIAGNOSI" bigint NOT NULL,
    "SK_TIPO_DOCUMENTO" bigint NOT NULL,
    "SK_REPOSITORY" bigint NOT NULL,
	"ID_AURA_ATTESTATO" character varying,
    "SK_METADATI_DOCUMENTO" bigint,
	"OID_DOCUMENTO" character varying
);


ALTER TABLE esenred."ESENZIONE_S_DOCUMENTO" OWNER TO esenred;

--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 237
-- Name: TABLE "ESENZIONE_S_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_S_DOCUMENTO" IS 'Rappresenta lo storico di evoluzione di un documento';


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha prodotto il documento';


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."COD_TIPO_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."COD_TIPO_USER" IS 'Tipologia di utente che ha prodotto il documento 
I possibili valori sono:
B = Beneficiario
O = Operatore
D = Delegato';


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DAT_CANCELLAZIONE" IS 'Data di cancellazione del documento';


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DAT_CREAZIONE" IS 'Data di creazione del documento';


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DAT_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DAT_DOCUMENTO" IS 'Data di produzione del documento';


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DAT_FINE_VALIDITA" IS 'Data di scadenza del documento';


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità del documento';


--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DAT_MODIFICA" IS 'Data di modifica del documento.';


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DESC_NOTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DESC_NOTE" IS 'Note riportate dal medico per la ASL.';


--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."FLAG_CONFORMITA_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."FLAG_CONFORMITA_DOCUMENTO" IS 'Indica se il documento è conforme all''originale o meno';


--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."ID_USERID"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."ID_USERID" IS 'Identificativo dell''utente che ha prodotto il documento';


--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."SK_STORICO_DOUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."SK_STORICO_DOUMENTO" IS 'Codice identificativo dell''evento storico di un documento';


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 237
-- Name: COLUMN "ESENZIONE_S_DOCUMENTO"."DESC_ESTESA_PATOLOGIA_CERTIFICATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_DOCUMENTO"."DESC_ESTESA_PATOLOGIA_CERTIFICATO" IS 'Descrizione estesa della patologia riportata dal medico sul certificato';


--
-- TOC entry 238 (class 1259 OID 149112)
-- Name: ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq" OWNER TO esenred;

--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 238
-- Name: ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq" OWNED BY esenred."ESENZIONE_S_DOCUMENTO"."SK_STORICO_DOUMENTO";


--
-- TOC entry 239 (class 1259 OID 149114)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_S_PRATICA_ESENZIONE" (
    "COD_RUOLO_OPERATORE" text,
    "COD_TIPO_USER" text NOT NULL,
    "DAT_CREAZIONE" timestamp(6) without time zone NOT NULL,
    "DESC_NOTA_BENEFICIARIO" text,
    "DESC_NOTA" text,
    "DESC_NOTA_OPERATORE" text,
    "ID_USER" integer NOT NULL,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "SK_ID_ESENZIONE" bigint NOT NULL,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "DAT_FINE_VALIDITA" timestamp without time zone,
    "SK_CRONOLOGIA" integer NOT NULL,
    "SK_TIPOLOGIA_STATO_PRATICA" bigint NOT NULL,
    "DAT_MODIFICA" timestamp without time zone,
    "SK_TIPO_MOTIVAZIONE" bigint NOT NULL,
    "DESC_NOTA_INTERNA" text,
    "NUM_PRATICA" bigint NOT NULL,
    "SK_DIAGNOSI" bigint NOT NULL,
    "SK_DISTRETTO_SOCIO_SANITARIO" bigint,
    "SK_INVALIDITA_TIPO" bigint,
    "ID_AZIENDA" character varying,
    "COD_FISCALE_CITTADINO_BENEFICIARIO" character varying NOT NULL,
    "SK_GRUPPO" bigint NOT NULL,
    "COD_FISCALE_CITTADINO_DELEGATO" character varying,
    "COD_FISCALE_OPERATORE" character varying NOT NULL
);


ALTER TABLE esenred."ESENZIONE_S_PRATICA_ESENZIONE" OWNER TO esenred;

--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 239
-- Name: TABLE "ESENZIONE_S_PRATICA_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_S_PRATICA_ESENZIONE" IS 'Contiene gli aggiornamenti intervenuti su una pratica di richiesta esenzione per patologia';


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha registrato l''operazione';


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."COD_TIPO_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."COD_TIPO_USER" IS 'Tipologia di utente che ha determinato l''aggiornamento.
I possibili valori sono:
B = Beneficiario
O = Operatore
D = Delegato';


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DAT_CREAZIONE" IS 'Data di creazione dell''operazione di cronologia';


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA_BENEFICIARIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA_BENEFICIARIO" IS 'Nota riportata dal beneficiario/delegato';


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA" IS 'Note relative all''operazione effettuata';


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA_OPERATORE" IS 'Nota riportata dall''operatore';


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."ID_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."ID_USER" IS 'Identificativo dell''utente che ha determinato l''aggiornamento';


--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DAT_CANCELLAZIONE" IS 'Data di cancellazione dell''operazione di cronologia';


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità dell’evento di cronologia';


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DAT_FINE_VALIDITA" IS 'Data di fine validità dell’evento di cronologia';


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."SK_CRONOLOGIA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."SK_CRONOLOGIA" IS 'Identificativo univoco dell''evento di cronologia di una pratica di esenzione';


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DAT_MODIFICA" IS 'Data di modifica dell''operazione di cronologia';


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA_INTERNA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."DESC_NOTA_INTERNA" IS 'Nota interna';


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN "ESENZIONE_S_PRATICA_ESENZIONE"."NUM_PRATICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_S_PRATICA_ESENZIONE"."NUM_PRATICA" IS 'Numero identificativo della pratica di richiesta esenzione per patologia';


--
-- TOC entry 240 (class 1259 OID 149120)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq" OWNER TO esenred;

--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 240
-- Name: ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq" OWNED BY esenred."ESENZIONE_S_PRATICA_ESENZIONE"."SK_CRONOLOGIA";






CREATE TABLE esenred."ESENZIONE_T_CITTADINO"
(
	"CODICE_FISCALE" character varying(16) NOT NULL,
	"COGNOME" text NOT NULL,
	"ID AURA" character varying,
	"NOME" text NOT NULL,
	"ID_USER" bigint NOT NULL,
	"DAT_MODIFICA" timestamp without time zone,
	"DAT_CANCELLAZIONE" timestamp without time zone,
	"DAT_CREAZIONE" timestamp without time zone NOT NULL,
	"DATA_DI_NASCITA" timestamp without time zone,
	"ID_AZIENDA" character varying(6) NOT NULL,
	"SESSO" text,
	"COMUNE_DI_NASCITA" text
)
;




--
-- TOC entry 241 (class 1259 OID 149122)
-- Name: ESENZIONE_T_DOCUMENTO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_T_DOCUMENTO" (
    "DAT_CREAZIONE" timestamp(6) without time zone NOT NULL,
    "DAT_FINE_VALIDITA" timestamp(6) without time zone,
    "DAT_MODIFICA" timestamp(6) without time zone,
    "DESC_NOTE" text,
    "FLAG_CONFORMITA_DOCUMENTO" boolean NOT NULL,
    "SK_DOCUMENTO" integer NOT NULL,
    "COD_RUOLO_OPERATORE" character varying(50),
    "COD_TIPO_USER" text NOT NULL,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "DAT_DOCUMENTO" timestamp without time zone NOT NULL,
    "DAT_INIZIO_VALIDITA" timestamp without time zone NOT NULL,
    "ID_USERID" bigint NOT NULL,
    "SK_REPOSITORY" bigint NOT NULL,
    "SK_TIPO_DOCUMENTO" bigint NOT NULL,
    "DESC_ESTESA_PATOLOGIA_CERTIFICATO" text,
    "SK_TIPOLOGIA_STATO_DOCUMENTO" bigint NOT NULL,
    "SK_DIAGNOSI" bigint,
	"CODICE_FISCALE_CITTADINO" character varying(16) NOT NULL,
	"OID_DOCUMENTO" character varying,
	"ID_AURA_ATTESTATO" character varying
);


ALTER TABLE esenred."ESENZIONE_T_DOCUMENTO" OWNER TO esenred;

--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 241
-- Name: TABLE "ESENZIONE_T_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_T_DOCUMENTO" IS 'Documento prodotto durante la gestione di una pratica di esenzione per malattia cronica, malattia rara, invalidità.';


--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DAT_CREAZIONE" IS 'Data di creazione.';


--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DAT_FINE_VALIDITA" IS 'Data di produzione del documento';


--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DAT_MODIFICA" IS 'Data di modifica.';


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DESC_NOTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DESC_NOTE" IS 'Note riportate dal medico per la ASL.';


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."FLAG_CONFORMITA_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."FLAG_CONFORMITA_DOCUMENTO" IS 'Indica se il documento è conforme all''originale o meno';


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."SK_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."SK_DOCUMENTO" IS 'Codice identificativo del documento';


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha prodotto il documento';


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."COD_TIPO_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."COD_TIPO_USER" IS 'Tipologia di utente che ha prodotto il documento 
I possibili valori sono:
B = Beneficiario
O = Operatore
D = Delegato';


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DAT_CANCELLAZIONE" IS 'Data di cancellazione.';


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DAT_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DAT_DOCUMENTO" IS 'Data di produzione del documento';


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità del documento';


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."ID_USERID"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."ID_USERID" IS 'Identificativo dell''utente che ha prodotto il documento';


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 241
-- Name: COLUMN "ESENZIONE_T_DOCUMENTO"."DESC_ESTESA_PATOLOGIA_CERTIFICATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_DOCUMENTO"."DESC_ESTESA_PATOLOGIA_CERTIFICATO" IS 'Descrizione estesa della patologia riportata dal medico sul certificato';


--
-- TOC entry 242 (class 1259 OID 149128)
-- Name: ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq" OWNER TO esenred;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 242
-- Name: ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq" OWNED BY esenred."ESENZIONE_T_DOCUMENTO"."SK_DOCUMENTO";


--
-- TOC entry 243 (class 1259 OID 149130)
-- Name: ESENZIONE_T_METADATI_DOCUMENTO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_T_METADATI_DOCUMENTO" (
    "ASSETTO_ORGANIZZATIVO" text,
    "COD_APPLICATIVO_RICHIESTA" text,
    "COD_AZIENDA_RICHIESTA" text,
    "COD_COMUNE_PAZIENTE" text,
    "COD_FISCALE_PAZIENTE" text,
    "CODICE_PIN" text,
    "COD_MATRICOLA" text,
    "COD_MIME_TYPE" text,
    "COD_OSCURA_SCARICO_CITTADINO" text,
    "COD_PRIVACY_CITTADINO" text,
    "COD_SESSO_PAZIENTE" text,
    "COD_STATO_NASCITA_PAZIENTE" text,
    "COD_STRUTTURA" integer,
    "COD_TIPO_ATTIVITA_CLINICA" text,
    "COD_TIPO_AZIONE" text,
    "COD_TIPO_AZIONE_EPISODIO" text,
    "COD_TIPO_DOCUMENTO_ALTO" text,
    "COD_TIPO_DOCUMENTO_MEDIO" text,
    "COD_TIPO_EPISODIO" text,
    "COD_TIPO_FIRMA" text,
    "COD_TIPO_STRUTTURA_PROD_DOC" text,
    "COGNOME_PAZIENTE" text,
    "DAT_FINE_EPISODIO" timestamp without time zone,
    "DAT_FIRMA" timestamp without time zone,
    "DAT_INIZIO_EPISODIO" timestamp without time zone,
    "DAT_NASCITA_PAZIENTE" timestamp without time zone,
    "DAT_RICHIESTA" timestamp without time zone,
    "FLAG_FIRMATO" boolean,
    "FLAG_SCARICA_CITTADINO" boolean,
    "FLAG_SOGGETTO_LEGGI_SPECIALI" boolean,
    "HASH_DOC" text,
    "ID_AULA" bigint,
    "ID_AURA_PAZIENTE" bigint,
    "ID_EPISODIO" text,
    "ID_GENITORE_TUTORE_PAZIENTE" text,
    "ID_RICHIESTA" text,
    "ID_RICHIESTA_UTENTE" text,
    "MEDICO_VALIDATORE" text,
    "MEDICO_REDATTOIRE" text,
    "NOME_PAZIENTE" text,
    "NRE" text,
    "RUOLO_UTENTE" text,
    "SIZE_DOC" text,
    "SK_METADATI_DOCUMENTO" integer NOT NULL,
    "COD_RUOLO_OPERATORE" text,
    "SK_DOCUMENTO" bigint NOT NULL,
    "DAT_CREAZIONE" timestamp without time zone,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "DAT_MODIFICA" timestamp without time zone,
    "ID_USER" bigint
);


ALTER TABLE esenred."ESENZIONE_T_METADATI_DOCUMENTO" OWNER TO esenred;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 243
-- Name: TABLE "ESENZIONE_T_METADATI_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_T_METADATI_DOCUMENTO" IS 'Metadati di un documento rappresentante un Certificato rilasciato da un medico specialista attestante la presenza di una malattia per la quale un cittadino o un suo delegato ha presentato una richiesta di esenzione per patologia o un Attestato di Esenzione.';


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_APPLICATIVO_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_APPLICATIVO_RICHIESTA" IS 'Codice dell''applicativo che ha fatto richiesta';


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_AZIENDA_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_AZIENDA_RICHIESTA" IS 'Codice Identificativo dell''Azienda che ha effettuato la richiesta';


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_COMUNE_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_COMUNE_PAZIENTE" IS 'Codice ISTAT(6 cifre)del luogo di nascita del paziente.
Se il paziente è nato all’estero “comuneDiNascita” dovrà essere valorizzato con “999” +“statoDiNascita”.';


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_FISCALE_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_FISCALE_PAZIENTE" IS 'Codice Fiscale del paziente';


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."CODICE_PIN"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."CODICE_PIN" IS 'E’ il PIN che il paziente dovrà utilizzare per scaricare il proprio documento

E'' obbligatorio se “scaricabileDalCittadino” vale “true”, entrambi i tag “tipoAzione” (relativi a episodio e documento) sono diversi da “REGISTRA_INFO_SCARICO_REFERTO”';


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_MATRICOLA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_MATRICOLA" IS 'Codice  che  identifica la matricola  dell’Unità Produttiva o dell’unitàMulti-Specialistica (secondo la codifica assegnata dal progetto ARPE) che identifica la struttura dell’utente che effettua la richiesta 
E'' obbligatorio se non valorizzato il tag codiceStrutturaUtente';


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_MIME_TYPE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_MIME_TYPE" IS 'Indica il formato del documento secondo una codifica della Regione Piemonte.
La Regione Piemonte ha stabilito che i documenti prodotti dalle aziende sanitarie saranno solo del tipo PDF con CDA iniettato, per cui deve essere possibile identificare se il documento è nel formato PDF con CDA iniettato oppure solo PDF (per i documenti che non sono interoperabili). Pertanto sonoammesseleseguenti ulteriori codifiche:
application_pdf_cda: se il documento è un pdf con CDA iniettato
application_pdf: se il documento è un pdf
Deve essere valorizzato in fase di invio o variazione di un documento.Non valorizzare in caso di annullamento';


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_OSCURA_SCARICO_CITTADINO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_OSCURA_SCARICO_CITTADINO" IS 'Indica se il documento è oscurato su richiesta del medico, in quanto le informazioni devono essere comunicate direttamente dal medico al paziente. Quando pari a “S” il documento non può essere scaricato on-line dal paziente oppure visualizzato dal paziente sulla web application del Fascicolo della Regione Piemonte
Valori ammessi
“S” ? documento oscurato al paziente;
“N” ? documento non oscurato al paziente.
“M” ? documento visibile al paziente a seguito di mediazione, ossia dopo la spiegazione da parte del medicoPer default questa informazione viene valorizzata a “S” quando nulla.';


--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_PRIVACY_CITTADINO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_PRIVACY_CITTADINO" IS 'Indica se il documento debba o meno ereditare le impostazioni relative al consenso definite nel FSE. In caso negativo il documento risulterà oscurato agli operatori sanitari.In particolare il tag privacyDocumentoindica se il documento deve essere oscurato nel Fascicolo Sanitario su richiesta dell’assistito.
Valori ammessi:
“0” = documento non oscurato su indicazione dell’assistito,eredita le impostazioni di consenso del FSE;
“1” = documento oscurato su indicazione dell’assistito, non eredita le impostazioni di consenso del FSE.
Per default questa informazione viene valorizzata a “0” quando nulla';


--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_SESSO_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_SESSO_PAZIENTE" IS 'Sesso del paziente. I possibili valori sono:
F = Femmina
M = Maschio';


--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_STATO_NASCITA_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_STATO_NASCITA_PAZIENTE" IS 'Codice ISTAT dello Stato di nascita del paziente';


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_STRUTTURA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_STRUTTURA" IS 'Codice della struttura di appartenenza dell’utente che effettua la richiesta
Obbligatorio se non valorizzato il tag codiceMatricola';


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_ATTIVITA_CLINICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_ATTIVITA_CLINICA" IS 'Codice della tipologia di attività clinica/organizzativa che ha portato alla condivisione del documento.
Sono ammessi i codici riportati nella tabella 3.2-1 dell’Affinity Domain Italia.

Deve essere valorizzato in fase di invio o variazione di un documento.Non valorizzare in caso di annullamento';


--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_AZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_AZIONE" IS 'E’ il tipo di azione che l’applicativo inviante richiede al Fascicolosui dati dell’episodio trattato.
Valori ammessi:
“INSERIMENTO” = va utilizzato quando si vuole inserire un episodio sull’FSE;
“AGGIORNAMENTO” = va utilizzato quando si vuole aggiornare i dati di un episodio già presente sull’ FSE;
“ANNULLAMENTO” = va utilizzato quando si vuole annullare un episodio già presente sull’FSE;
“REGISTRA_INFO_SCARICO_REFERTO” = va utilizzato quando si vuole aggiornare solo alcune informazioni relative al documento di un episodio già presente sull’ FSE(vedi tag “documento.tipoAzione”);
“RINVIO” = va utilizzato quando si vuole aggiornare i dati di un episodio già presente sull’FSE (ha lo stesso comportamento di “AGGIORNAMENTO”) e allo stesso tempo tenere traccia del fatto che si tratta di un rinvio.';


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_AZIONE_EPISODIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_AZIONE_EPISODIO" IS 'Codice della tipologia di azione che ha generato episodio cui si riferisce il documento';


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_DOCUMENTO_ALTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_DOCUMENTO_ALTO" IS ': nsieme alla TipologiaDocumentoMedio individua la tipologia del documento trasmesso, e deve rispettare le codifiche previste nel documento dell’Affinity DomainItalia.
In fase di alimentazione del fascicolo ai fini dell''interoperabilità INI, sono consentiti solo i valori-REF: Referto-LDO: Lettera di Dimissione OspedalieraQuesto tag dovrà essere valorizzato in coerenza con i valori riportati nel tag TipologiaDocumentoMedio.
Deve essere valorizzato in fase di invio o variazione di un documento.In fase di annullamento può essere non valorizzato';


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_DOCUMENTO_MEDIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_DOCUMENTO_MEDIO" IS 'Insieme alla TipologiaDocumentoAlto individua la tipologia del documento trasmesso, e deve rispettare le codifiche previste nel documento dell’Affinity DomainItalia.Tale tag sostituisce ed estende il tag tipoDocumentoFSEpresente nel metodo registraEpisodio2.
Sono ammessi alcuni valori riportati nella tabella 2.18-1 del documento di Affinity Domain Italia estesa per la codifica di alcune tipologie di documenti ad uso regionale e non rientranti nell’inter-operabilità nazionale.
Deve essere valorizzato in fase di invio o variazione di un documento.In fase di annullamento può essere non valorizzato';


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_EPISODIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_EPISODIO" IS 'Indica il regime dell’episodio: ambulatoriale, di ricovero o di pronto soccorso.
Valori ammessi:
“O” = regime ambulatoriale; può essere assegnato anche per richieste di esami diagnostici effettuati durante un ricovero o un passaggio in pronto soccorso;
“I” = regime di ricovero; comprende i ricoveri ordinari (RO), i day hospital (DH) e i day surgery (DS);
“E” = regime di pronto soccorso';


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_FIRMA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_FIRMA" IS 'Indica come è stato firmato il documento
Valori ammessi:
<non valorizzato> ?documento non firmato digitalmente
C ?firma CADES
PB? firma PADES-BESIl tipo di firma concorre a determinare se il documento è inter-operabileovveropuò essere reso disponibile sull’infrastruttura nazionale.E’ quindi necessario indicare il tipodi firma corretto.
Deve essere valorizzato in fase di invio o variazione di un documento, se il documento è firmato digitalmente.Non valorizzare in caso di annullamento';


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_STRUTTURA_PROD_DOC"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_TIPO_STRUTTURA_PROD_DOC" IS 'Codice tipologia struttura che processa il documento';


--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COGNOME_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COGNOME_PAZIENTE" IS 'Cognome del paziente';


--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_FINE_EPISODIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_FINE_EPISODIO" IS 'Timestamp contenente la data e ora della dimissione.';


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_FIRMA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_FIRMA" IS 'Timestamp contenente la data e ora della firma nel caso di documento firmato o la data e ora della validazione nel caso di documento non firmato.';


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_INIZIO_EPISODIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_INIZIO_EPISODIO" IS 'Timestamp contenente la data e ora dell’accettazione';


--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_NASCITA_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_NASCITA_PAZIENTE" IS 'Data di nascita del paziente';


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_RICHIESTA" IS 'Timestamp della data e ora di invio del documento.';


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."FLAG_FIRMATO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."FLAG_FIRMATO" IS 'Indica se il documento è firmato digitalmente
I possibili valori sono:
1 = SI
0 = NO';


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."FLAG_SCARICA_CITTADINO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."FLAG_SCARICA_CITTADINO" IS 'Indica se il documento può essere scaricato on-line dal paziente
1 = SI
0= NO

Deve essere valorizzato in fase di invio o variazione di un documento.
Non valorizzare in caso di annullamento';


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."FLAG_SOGGETTO_LEGGI_SPECIALI"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."FLAG_SOGGETTO_LEGGI_SPECIALI" IS 'Indica se il documento contiene dei dati soggetti a Leggi Speciali, ovvero relativi a:
atti di violenza sessuale o di pedofilia;
sieropositività;
uso di sostanze stupefacenti, di sostanze psicotrope e di alcool;
aborti , interruzioni volontarie di gravidanza, parti richiesti in forma di anonimato;
servizi offerti dai consultori familiari.
Valori ammessi:
1 = SI
0= NO';


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."HASH_DOC"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."HASH_DOC" IS 'Deve essere valorizzato con l’hash del contenuto del documento.
Le regole di produzione dell’hash sono descritte nelle specifiche ministeriali del Framework [FRAMWORK].Il tag HashDoc può essere valorizzato con una stringa di lunghezza massima di 256 caratteri.

Deve essere valorizzato in fase di invio o variazione di un documento.Non valorizzare in caso di annullamento';


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_AULA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_AULA" IS 'Identificativo univoco del paziente all’interno dell’anagrafica unica dell’azienda sanitaria trattata, chiamata anche “Archivio Unico LocaleAssistiti”(AULA).';


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_AURA_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_AURA_PAZIENTE" IS 'Identificativo univoco del paziente all’interno dell’Archivio Unico Regionale Assistiti(AURA) della Regione Piemonte.
Valorizzare con “-1” quando non disponibileo per pazienti con assistenza fuori regione';


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_EPISODIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_EPISODIO" IS 'Identificativo univoco dell''episodio che ha generato il documento.
E’ fornito dal sistema inviante e deve essere univoco per lo stesso sistema e il paziente. In altre parole lo stesso identificativo potrebbe essere utilizzato per due pazienti differenti all’interno del dipartimentale in questione.';


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_GENITORE_TUTORE_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_GENITORE_TUTORE_PAZIENTE" IS 'Contiene il codice fiscale del genitore/tutore che ha richiesto l’operazione per un paziente minorenneo tutelato';


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_RICHIESTA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_RICHIESTA" IS 'Identificativo della richiesta';


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_RICHIESTA_UTENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_RICHIESTA_UTENTE" IS 'Codice fiscale dell’utente che fa richiesta del servizio di interoperabilità';


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."MEDICO_VALIDATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."MEDICO_VALIDATORE" IS 'nominativo di uno degli N medici che hanno:
- validato il documento nel caso di documenti non firmati 
- o firmato il documento nel caso di documenti firmati';


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."MEDICO_REDATTOIRE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."MEDICO_REDATTOIRE" IS 'Nome del medico redattore del documento
Deve essere valorizzato almeno un medico redattore in fase di invio o variazione di un documento';


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."NOME_PAZIENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."NOME_PAZIENTE" IS 'Nome del paziente';


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."NRE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."NRE" IS 'Contiene l’elenco dei numeri di ricetta elettronica (NRE) delle ricette dematerializzate prescritte che hanno portato alla generazione del documento/referto';


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."RUOLO_UTENTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."RUOLO_UTENTE" IS 'Rappresenta il ruolo dell’utente che effettua la richiesta. 
Deve contenere un valore tra quelli previsti nella tabella 5.4-1 dell’Affinity Domain Italia abilitati ad effettuare la comunicazione e la cancellazione dei metadati.';


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."SIZE_DOC"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."SIZE_DOC" IS 'Deve essere valorizzato con la dimensione del documentoe spressa in byte. 
l tag SizeDocpuò essere valorizzato con una stringa di lunghezza massima di 256 caratteri
Tipo: varchar(256)

Deve essere valorizzato in fase di invio o variazione di un documento.Non valorizzare in caso di annullamento';


--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."SK_METADATI_DOCUMENTO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."SK_METADATI_DOCUMENTO" IS 'Codice univoco del documento a cui si riferiscono i metadati.';


--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha registrato i metadati';


--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_CREAZIONE" IS 'Data di creazione dei metadati.';


--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_CANCELLAZIONE" IS 'Data di cancellazione dei metadati.';


--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."DAT_MODIFICA" IS 'Data di modifica dei metadati.';


--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN "ESENZIONE_T_METADATI_DOCUMENTO"."ID_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_METADATI_DOCUMENTO"."ID_USER" IS 'Identificativo dell''utente che ha movimentato l''associazione documento-pratica';


--
-- TOC entry 244 (class 1259 OID 149136)
-- Name: ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq" OWNER TO esenred;

--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 244
-- Name: ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq" OWNED BY esenred."ESENZIONE_T_METADATI_DOCUMENTO"."SK_METADATI_DOCUMENTO";


--
-- TOC entry 245 (class 1259 OID 149138)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_T_PRATICA_ESENZIONE" (
    "COD_RUOLO_OPERATORE" text,
    "DAT_MODIFICA" timestamp(6) without time zone,
    "DAT_INIZIO_VALIDITA" timestamp(6) without time zone,
    "DAT_CREAZIONE" timestamp(6) without time zone NOT NULL,
    "DAT_FINE_VALIDITA" timestamp(6) without time zone,
    "DESC_NOTA_INTERNA" text,
    "SK_PRATICA_ESENZIONE" integer NOT NULL,
    "NUM_PRATICA" bigint NOT NULL,
    "FLAG_DICHIARAZIONE" boolean,
    "ID_DICHIARAZIONE" text,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "DESC_NOTA_BENEFICIARIO" text,
    "DESC_NOTA_OPERATORE" text,
    "DESC_NOTE" text NOT NULL,
    "SK_TIPO_MOTIVAZIONE" bigint NOT NULL,
    "SK_TIPOLOGIA_STATO_PRATICA" bigint NOT NULL,
    "SK_GRUPPO" bigint NOT NULL,
    "ID_AZIENDA" character varying,
    "SK_DISTRETTO_SOCIO_SANITARIO" bigint,
    "SK_DIAGNOSI" bigint NOT NULL,
    "SK_INVALIDITA_TIPO" bigint,
    "ID_USER" bigint NOT NULL,
    "COD_FISCALE_OPERATORE" character varying NOT NULL,
	"CODICE_FISCALE_BENEFICIARIO" character varying(16) NOT NULL,
	"CODICE_FISCALE_DELEGATO" character varying(16)
);


ALTER TABLE esenred."ESENZIONE_T_PRATICA_ESENZIONE" OWNER TO esenred;

--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 245
-- Name: TABLE "ESENZIONE_T_PRATICA_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_T_PRATICA_ESENZIONE" IS 'Esenzione per patologia rilasciata dalla Regione Piemonte';


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha registrato la pratica';


--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DAT_MODIFICA" IS 'Data di modifica della richiesta di Esenzione.';


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DAT_INIZIO_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DAT_INIZIO_VALIDITA" IS 'Data di inizio validità dell''Esenzione.';


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DAT_CREAZIONE" IS 'Data di presentazione della richiesta di Esenzione';


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DAT_FINE_VALIDITA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DAT_FINE_VALIDITA" IS 'Data di fine validità dell''esenzione';


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTA_INTERNA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTA_INTERNA" IS 'Nota interna';


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."SK_PRATICA_ESENZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."SK_PRATICA_ESENZIONE" IS 'Codice identificativo attribuito da ESENPAT alla richiesta di esenzione. ';


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."NUM_PRATICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."NUM_PRATICA" IS 'Numero identificativo della pratica di richiesta esenzione per patologia';


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."FLAG_DICHIARAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."FLAG_DICHIARAZIONE" IS 'Indica la presenza della dichiarazione di iscrizione al registro interregionale delle malattie rare';


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."ID_DICHIARAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."ID_DICHIARAZIONE" IS 'Identificativo della dichiarazione di iscrizione al registro interregionale delle malattie rare';


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DAT_CANCELLAZIONE" IS 'Data di cancellazione della richiesta di esenzione';


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTA_BENEFICIARIO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTA_BENEFICIARIO" IS 'Nota riportata dal beneficiario/delegato';


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTA_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTA_OPERATORE" IS 'Nota riportata dall''operatore';


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."DESC_NOTE" IS 'Comunicazione dell''operatore.';


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 245
-- Name: COLUMN "ESENZIONE_T_PRATICA_ESENZIONE"."ID_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PRATICA_ESENZIONE"."ID_USER" IS 'Identificativo dell''utente che ha registrato la pratica';


--
-- TOC entry 246 (class 1259 OID 149144)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq" OWNER TO esenred;

--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 246
-- Name: ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq" OWNED BY esenred."ESENZIONE_T_PRATICA_ESENZIONE"."SK_PRATICA_ESENZIONE";


--
-- TOC entry 247 (class 1259 OID 149146)
-- Name: ESENZIONE_T_PROGRESSIVO; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_T_PROGRESSIVO" (
    "ID_PROGRESSIVO" integer NOT NULL,
    "COD_TIPO_PROGRESSIVO" text NOT NULL,
    "NUM_VALORE_DISPONIBILE" integer NOT NULL,
    "ID_AZIENDA" character varying
);


ALTER TABLE esenred."ESENZIONE_T_PROGRESSIVO" OWNER TO esenred;

--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 247
-- Name: TABLE "ESENZIONE_T_PROGRESSIVO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_T_PROGRESSIVO" IS 'Contiene i progressivi utilizzati nell''ambito dell''applicazione Esenzione per Patologia';


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN "ESENZIONE_T_PROGRESSIVO"."ID_PROGRESSIVO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PROGRESSIVO"."ID_PROGRESSIVO" IS 'Codice identificativo del progressivo.';


--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN "ESENZIONE_T_PROGRESSIVO"."COD_TIPO_PROGRESSIVO"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PROGRESSIVO"."COD_TIPO_PROGRESSIVO" IS 'Codice della tipologia di progressivo.';


--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN "ESENZIONE_T_PROGRESSIVO"."NUM_VALORE_DISPONIBILE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_PROGRESSIVO"."NUM_VALORE_DISPONIBILE" IS 'Valore del progressivo disponibile';


--
-- TOC entry 248 (class 1259 OID 149152)
-- Name: ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq" OWNER TO esenred;

--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 248
-- Name: ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq" OWNED BY esenred."ESENZIONE_T_PROGRESSIVO"."ID_PROGRESSIVO";


--
-- TOC entry 249 (class 1259 OID 149154)
-- Name: ESENZIONE_T_REPOSITORY_DOCUMENTALE; Type: TABLE; Schema: esenred; Owner: esenred
--

CREATE TABLE esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE" (
    "DESC_FILE" text NOT NULL,
    "DAT_ARCHIVIIAZIONE" timestamp(6) without time zone NOT NULL,
    "FILE" bytea,
    "SK_REPOSITORY" integer NOT NULL,
    "FILE_NAME" text NOT NULL,
    "DAT_CANCELLAZIONE" timestamp without time zone,
    "DAT_CREAZIONE" timestamp without time zone,
    "DAT_MODIFICA" timestamp without time zone,
    "COD_RUOLO_OPERATORE" text,
    "ID_USER" bigint
);


ALTER TABLE esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE" OWNER TO esenred;

--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 249
-- Name: TABLE "ESENZIONE_T_REPOSITORY_DOCUMENTALE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON TABLE esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE" IS 'Contiene i documenti fisici prodotti nell’ambito della gestione delle pratiche di esenzione per malattia cronica, malattia rara, invalidità.';


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DESC_FILE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DESC_FILE" IS 'Descrizione del contenuto del documento ';


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_ARCHIVIIAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_ARCHIVIIAZIONE" IS 'Data di archiviazione del documento ';


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."FILE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."FILE" IS 'File contenente il documento';


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."SK_REPOSITORY"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."SK_REPOSITORY" IS 'Codice identificativo del documento nell''ambito del repository documentale';


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."FILE_NAME"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."FILE_NAME" IS 'Nome del file contenente il documento';


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_CANCELLAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_CANCELLAZIONE" IS 'Data di cancellazione del documento dal repository';


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_CREAZIONE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_CREAZIONE" IS 'Data di creazione del documento nel repository';


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_MODIFICA"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."DAT_MODIFICA" IS 'Data di modifica del documento nel repository';


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."COD_RUOLO_OPERATORE"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."COD_RUOLO_OPERATORE" IS 'Codice del ruolo svolto dall''operatore che ha registrato il documento nel repository';


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN "ESENZIONE_T_REPOSITORY_DOCUMENTALE"."ID_USER"; Type: COMMENT; Schema: esenred; Owner: esenred
--

COMMENT ON COLUMN esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."ID_USER" IS 'Identificativo dell''utente che ha movimentato il documento nel repository documentale';


--
-- TOC entry 250 (class 1259 OID 149160)
-- Name: ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq" OWNER TO esenred;

--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 250
-- Name: ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq; Type: SEQUENCE OWNED BY; Schema: esenred; Owner: esenred
--

ALTER SEQUENCE esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq" OWNED BY esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"."SK_REPOSITORY";


--
-- TOC entry 251 (class 1259 OID 149162)
-- Name: SEQ_ESENRED_W_NOTIFICHE; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred."SEQ_ESENRED_W_NOTIFICHE"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred."SEQ_ESENRED_W_NOTIFICHE" OWNER TO esenred;

--
-- TOC entry 252 (class 1259 OID 149839)
-- Name: csi_log_audit_audit_id_seq; Type: SEQUENCE; Schema: esenred; Owner: esenred
--

CREATE SEQUENCE esenred.csi_log_audit_audit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esenred.csi_log_audit_audit_id_seq OWNER TO esenred;

--
-- TOC entry 2306 (class 2604 OID 149164)
-- Name: ESENRED_T_ESENZIONI_REDDITO ID_ESENZIONE; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_T_ESENZIONI_REDDITO" ALTER COLUMN "ID_ESENZIONE" SET DEFAULT nextval('esenred."ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq"'::regclass);


--
-- TOC entry 2307 (class 2604 OID 149165)
-- Name: ESENZIONE_D_DIAGNOSI SK_DIAGNOSI; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DIAGNOSI" ALTER COLUMN "SK_DIAGNOSI" SET DEFAULT nextval('esenred."ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq"'::regclass);


--
-- TOC entry 2308 (class 2604 OID 149166)
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO SK_DISTRETTO_SOCIO_SANITARIO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" ALTER COLUMN "SK_DISTRETTO_SOCIO_SANITARIO" SET DEFAULT nextval('esenred."ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq"'::regclass);


--
-- TOC entry 2309 (class 2604 OID 149167)
-- Name: ESENZIONE_D_DOCUMENTO_STATO SK_DOCUMENTO_STATO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_STATO" ALTER COLUMN "SK_DOCUMENTO_STATO" SET DEFAULT nextval('esenred."ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq"'::regclass);


--
-- TOC entry 2310 (class 2604 OID 149168)
-- Name: ESENZIONE_D_DOCUMENTO_TIPO SK_DOCUMENTO_TIPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_TIPO" ALTER COLUMN "SK_DOCUMENTO_TIPO" SET DEFAULT nextval('esenred."ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq"'::regclass);


--
-- TOC entry 2311 (class 2604 OID 149169)
-- Name: ESENZIONE_D_DURATA_TIPO SK_DURATA_TIPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DURATA_TIPO" ALTER COLUMN "SK_DURATA_TIPO" SET DEFAULT nextval('esenred."ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq"'::regclass);


--
-- TOC entry 2312 (class 2604 OID 149170)
-- Name: ESENZIONE_D_ESENZIONE SK_ESENZIONE; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_ESENZIONE" ALTER COLUMN "SK_ESENZIONE" SET DEFAULT nextval('esenred."ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq"'::regclass);


--
-- TOC entry 2313 (class 2604 OID 149172)
-- Name: ESENZIONE_D_GRUPPO_ESENZIONI SK_GRUPPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_GRUPPO_ESENZIONI" ALTER COLUMN "SK_GRUPPO" SET DEFAULT nextval('esenred."ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq"'::regclass);


--
-- TOC entry 2314 (class 2604 OID 149173)
-- Name: ESENZIONE_D_GRUPPO_UTENTI SK_GRUPPO_UTENTI; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_GRUPPO_UTENTI" ALTER COLUMN "SK_GRUPPO_UTENTI" SET DEFAULT nextval('esenred."ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq"'::regclass);


--
-- TOC entry 2315 (class 2604 OID 149174)
-- Name: ESENZIONE_D_INVALIDITA_TIPO SK_INVALIDITA_TIPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_INVALIDITA_TIPO" ALTER COLUMN "SK_INVALIDITA_TIPO" SET DEFAULT nextval('esenred."ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq"'::regclass);


--
-- TOC entry 2316 (class 2604 OID 149175)
-- Name: ESENZIONE_D_MOTIVAZIONE_TIPO SK_MOTIVAZIONE_TIPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_MOTIVAZIONE_TIPO" ALTER COLUMN "SK_MOTIVAZIONE_TIPO" SET DEFAULT nextval('esenred."ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq"'::regclass);


--
-- TOC entry 2317 (class 2604 OID 149176)
-- Name: ESENZIONE_D_NOTIFICA_TIPO SK_NOTIFICA_TIPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_NOTIFICA_TIPO" ALTER COLUMN "SK_NOTIFICA_TIPO" SET DEFAULT nextval('esenred."ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq"'::regclass);


--
-- TOC entry 2318 (class 2604 OID 149177)
-- Name: ESENZIONE_D_PRATICA_STATO SK_PRATICA_STATO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_PRATICA_STATO" ALTER COLUMN "SK_PRATICA_STATO" SET DEFAULT nextval('esenred."ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq"'::regclass);


--
-- TOC entry 2319 (class 2604 OID 149178)
-- Name: ESENZIONE_D_PRESTAZIONE_SPECIALISTICA SK_PRESTAZIONE; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA" ALTER COLUMN "SK_PRESTAZIONE" SET DEFAULT nextval('esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq"'::regclass);


--
-- TOC entry 2320 (class 2604 OID 149179)
-- Name: ESENZIONE_D_SERVIZIO SK_SERVIZIO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO" ALTER COLUMN "SK_SERVIZIO" SET DEFAULT nextval('esenred."ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq"'::regclass);


--
-- TOC entry 2321 (class 2604 OID 149180)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI SK_GRUPPO_UTENTI; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" ALTER COLUMN "SK_GRUPPO_UTENTI" SET DEFAULT nextval('esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq"'::regclass);


--
-- TOC entry 2322 (class 2604 OID 149181)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI SK_SERVIZIO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" ALTER COLUMN "SK_SERVIZIO" SET DEFAULT nextval('esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq"'::regclass);


--
-- TOC entry 2323 (class 2604 OID 149182)
-- Name: ESENZIONE_R_DESTINATARIO_TIPO SK_DESTINATARIO_TIPO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_DESTINATARIO_TIPO" ALTER COLUMN "SK_DESTINATARIO_TIPO" SET DEFAULT nextval('esenred."ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq"'::regclass);


--
-- TOC entry 2324 (class 2604 OID 149183)
-- Name: ESENZIONE_S_DOCUMENTO SK_STORICO_DOUMENTO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO" ALTER COLUMN "SK_STORICO_DOUMENTO" SET DEFAULT nextval('esenred."ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq"'::regclass);


--
-- TOC entry 2325 (class 2604 OID 149184)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE SK_CRONOLOGIA; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE" ALTER COLUMN "SK_CRONOLOGIA" SET DEFAULT nextval('esenred."ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq"'::regclass);


--
-- TOC entry 2326 (class 2604 OID 149185)
-- Name: ESENZIONE_T_DOCUMENTO SK_DOCUMENTO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO" ALTER COLUMN "SK_DOCUMENTO" SET DEFAULT nextval('esenred."ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq"'::regclass);


--
-- TOC entry 2327 (class 2604 OID 149186)
-- Name: ESENZIONE_T_METADATI_DOCUMENTO SK_METADATI_DOCUMENTO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_METADATI_DOCUMENTO" ALTER COLUMN "SK_METADATI_DOCUMENTO" SET DEFAULT nextval('esenred."ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq"'::regclass);


--
-- TOC entry 2328 (class 2604 OID 149187)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE SK_PRATICA_ESENZIONE; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE" ALTER COLUMN "SK_PRATICA_ESENZIONE" SET DEFAULT nextval('esenred."ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq"'::regclass);


--
-- TOC entry 2329 (class 2604 OID 149188)
-- Name: ESENZIONE_T_PROGRESSIVO ID_PROGRESSIVO; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PROGRESSIVO" ALTER COLUMN "ID_PROGRESSIVO" SET DEFAULT nextval('esenred."ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq"'::regclass);


--
-- TOC entry 2330 (class 2604 OID 149189)
-- Name: ESENZIONE_T_REPOSITORY_DOCUMENTALE SK_REPOSITORY; Type: DEFAULT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE" ALTER COLUMN "SK_REPOSITORY" SET DEFAULT nextval('esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq"'::regclass);



--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 196
-- Name: ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq"', 1, true);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 199
-- Name: ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_DIAGNOSI_SK_DIAGNOSI_seq"', 1, false);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 201
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_DISTRETTO_SOCIO_SA_SK_DISTRETTO_SOCIO_SANITARIO_seq"', 1, false);


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 204
-- Name: ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_DOCUMENTO_STATO_SK_DOCUMENTO_STATO_seq"', 1, false);


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 206
-- Name: ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_DOCUMENTO_TIPO_SK_DOCUMENTO_TIPO_seq"', 1, false);


--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 208
-- Name: ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_DURATA_TIPO_SK_DURATA_TIPO_seq"', 1, false);


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 211
-- Name: ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_ESENZIONE_SK_ESENZIONE_seq"', 1, false);


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 213
-- Name: ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_GRUPPO_ESENZIONI_SK_GRUPPO_seq"', 1, false);


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 215
-- Name: ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq"', 1, false);


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 217
-- Name: ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_INVALIDITA_TIPO_SK_INVALIDITA_TIPO_seq"', 1, false);


--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 219
-- Name: ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_MOTIVAZIONE_TIPO_SK_MOTIVAZIONE_TIPO_seq"', 1, false);


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 221
-- Name: ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_NOTIFICA_TIPO_SK_NOTIFICA_TIPO_seq"', 1, false);


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 223
-- Name: ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_PRATICA_STATO_SK_PRATICA_STATO_seq"', 1, false);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 225
-- Name: ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA_SK_PRESTAZIONE_seq"', 1, false);


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 228
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_GRUPPO_UTENTI_seq"', 1, false);


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 229
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_SK_SERVIZIO_seq"', 1, false);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 230
-- Name: ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_D_SERVIZIO_SK_SERVIZIO_seq"', 1, false);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 232
-- Name: ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_R_DESTINATARIO_TIPO_SK_DESTINATARIO_TIPO_seq"', 1, false);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 238
-- Name: ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_S_DOCUMENTO_SK_STORICO_DOUMENTO_seq"', 1, false);


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 240
-- Name: ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_S_PRATICA_ESENZIONE_SK_CRONOLOGIA_seq"', 1, false);


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 242
-- Name: ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_T_DOCUMENTO_SK_DOCUMENTO_seq"', 1, false);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 244
-- Name: ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_T_METADATI_DOCUMENTO_SK_METADATI_DOCUMENTO_seq"', 1, false);


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 246
-- Name: ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_T_PRATICA_ESENZIONE_SK_PRATICA_ESENZIONE_seq"', 1, false);


--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 248
-- Name: ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_T_PROGRESSIVO_ID_PROGRESSIVO_seq"', 1, false);


--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 250
-- Name: ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE_SK_REPOSITORY_seq"', 1, false);


--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 251
-- Name: SEQ_ESENRED_W_NOTIFICHE; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."SEQ_ESENRED_W_NOTIFICHE"', 1, true);


--
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 252
-- Name: csi_log_audit_audit_id_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred.csi_log_audit_audit_id_seq', 1, false);


--
-- TOC entry 2385 (class 2606 OID 149738)
-- Name: ESENZIONE_D_ESENZIONE_AURA_TIPO ESENZIONE_D_ESENZIONE_AURA_TIPO_pkey; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_D_TIPOLOGIA_ESENIONE_AURA" PRIMARY KEY ("SK_TIPOLOGIA_ESENZIONE_AURA");


--
-- TOC entry 2339 (class 2606 OID 149191)
-- Name: ESENRED_C_MATRICE_ESENZIONI PK; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_MATRICE_ESENZIONI"
    ADD CONSTRAINT "PK" PRIMARY KEY ("COD_RICHIESTA", "COD_ESISTENTE");


--
-- TOC entry 2347 (class 2606 OID 149193)
-- Name: ESENRED_D_AZIENDASANITARIA PK_Azienda; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_D_AZIENDASANITARIA"
    ADD CONSTRAINT "PK_Azienda" PRIMARY KEY ("ID_AZIENDA");


--
-- TOC entry 2351 (class 2606 OID 149195)
-- Name: ESENRED_T_ASL_OPERATORE PK_CF_OPERATORE; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_T_ASL_OPERATORE"
    ADD CONSTRAINT "PK_CF_OPERATORE" PRIMARY KEY ("COD_FISCALE_OPERATORE");


--
-- TOC entry 2446 (class 2606 OID 149197)
-- Name: ESENZIONE_T_DOCUMENTO PK_CONFIGURATOR_01; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO"
    ADD CONSTRAINT "PK_CONFIGURATOR_01" PRIMARY KEY ("SK_DOCUMENTO");


--
-- TOC entry 2332 (class 2606 OID 149199)
-- Name: CSI_LOG_AUDIT PK_CSI_LOG_AUDIT; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."CSI_LOG_AUDIT"
    ADD CONSTRAINT "PK_CSI_LOG_AUDIT" PRIMARY KEY ("AUDIT_ID");


--
-- TOC entry 2335 (class 2606 OID 149201)
-- Name: ESENRED_C_COMUNI PK_Comune; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_COMUNI"
    ADD CONSTRAINT "PK_Comune" PRIMARY KEY ("COD_ISTAT");


--
-- TOC entry 2383 (class 2606 OID 149203)
-- Name: ESENZIONE_D_ESENZIONE PK_ESENZIONE_D_ESENZIONE; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_ESENZIONE"
    ADD CONSTRAINT "PK_ESENZIONE_D_ESENZIONE" PRIMARY KEY ("SK_ESENZIONE");


--
-- TOC entry 2370 (class 2606 OID 149205)
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO PK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"
    ADD CONSTRAINT "PK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" PRIMARY KEY ("SK_DISTRETTO_SOCIO_SANITARIO");


--
-- TOC entry 2374 (class 2606 OID 149207)
-- Name: ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI PK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"
    ADD CONSTRAINT "PK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" PRIMARY KEY ("SK_GRUPPO", "SK_TIPO_DOCUMENTO", "DAT_INIZIO_VALIDITA");


--
-- TOC entry 2380 (class 2606 OID 149209)
-- Name: ESENZIONE_D_DURATA_TIPO PK_ESENZIONE_D_DURATA_TIPO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DURATA_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_D_DURATA_TIPO" PRIMARY KEY ("SK_DURATA_TIPO");


--
-- TOC entry 2387 (class 2606 OID 149211)
-- Name: ESENZIONE_D_GRUPPO_ESENZIONI PK_ESENZIONE_D_GRUPPO_ESENZIONI; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_GRUPPO_ESENZIONI"
    ADD CONSTRAINT "PK_ESENZIONE_D_GRUPPO_ESENZIONI" PRIMARY KEY ("SK_GRUPPO");


--
-- TOC entry 2389 (class 2606 OID 149213)
-- Name: ESENZIONE_D_GRUPPO_UTENTI PK_ESENZIONE_D_GRUPPO_UTENTI; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_GRUPPO_UTENTI"
    ADD CONSTRAINT "PK_ESENZIONE_D_GRUPPO_UTENTI" PRIMARY KEY ("SK_GRUPPO_UTENTI");


ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI"
	ADD CONSTRAINT "PK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" PRIMARY KEY ("SK_GRUPPO_UTENTI");

--
-- TOC entry 2391 (class 2606 OID 149215)
-- Name: ESENZIONE_D_INVALIDITA_TIPO PK_ESENZIONE_D_INVALIDITA_TIPO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_INVALIDITA_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_D_INVALIDITA_TIPO" PRIMARY KEY ("SK_INVALIDITA_TIPO");


--
-- TOC entry 2400 (class 2606 OID 149217)
-- Name: ESENZIONE_D_SERVIZIO PK_ESENZIONE_D_SERVIZIO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO"
    ADD CONSTRAINT "PK_ESENZIONE_D_SERVIZIO" PRIMARY KEY ("SK_SERVIZIO");


--
-- TOC entry 2412 (class 2606 OID 149219)
-- Name: ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO PK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO" PRIMARY KEY ("SK_TIPOLOGIA_ESENZIONE_AURA", "SK_GRUPPO", "DAT_INIZIO_VALIDITA");


--
-- TOC entry 2396 (class 2606 OID 149223)
-- Name: ESENZIONE_D_PRATICA_STATO PK_ESENZIONE_D_TIPOLOGIA_STATO_PRATICA; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_PRATICA_STATO"
    ADD CONSTRAINT "PK_ESENZIONE_D_TIPOLOGIA_STATO_PRATICA" PRIMARY KEY ("SK_PRATICA_STATO");


--
-- TOC entry 2378 (class 2606 OID 149225)
-- Name: ESENZIONE_D_DOCUMENTO_TIPO PK_ESENZIONE_D_TIPO_DOCUMENTO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_D_TIPO_DOCUMENTO" PRIMARY KEY ("SK_DOCUMENTO_TIPO");


--
-- TOC entry 2393 (class 2606 OID 149227)
-- Name: ESENZIONE_D_MOTIVAZIONE_TIPO PK_ESENZIONE_D_TIPO_MOTIVAZIONE; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_D_TIPO_MOTIVAZIONE" PRIMARY KEY ("SK_MOTIVAZIONE_TIPO");


--
-- TOC entry 2398 (class 2606 OID 149229)
-- Name: ESENZIONE_D_PRESTAZIONE_SPECIALISTICA PK_ESENZIONE_D__01; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"
    ADD CONSTRAINT "PK_ESENZIONE_D__01" PRIMARY KEY ("SK_PRESTAZIONE");


--
-- TOC entry 2367 (class 2606 OID 149231)
-- Name: ESENZIONE_D_DIAGNOSI PK_ESENZIONE_D__04; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DIAGNOSI"
    ADD CONSTRAINT "PK_ESENZIONE_D__04" PRIMARY KEY ("SK_DIAGNOSI");


--
-- TOC entry 2408 (class 2606 OID 149233)
-- Name: ESENZIONE_R_DIAGNOSI_PRESTAZIONE PK_ESENZIONE_INVA_01; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE"
    ADD CONSTRAINT "PK_ESENZIONE_INVA_01" PRIMARY KEY ("DAT_INIZIO", "SK_DIAGNOSI", "SK_PRESTAZIONE");


--
-- TOC entry 2404 (class 2606 OID 149235)
-- Name: ESENZIONE_R_DESTINATARIO_TIPO PK_ESENZIONE_R_DESTINATARIO_TIPO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_DESTINATARIO_TIPO"
    ADD CONSTRAINT "PK_ESENZIONE_R_DESTINATARIO_TIPO" PRIMARY KEY ("SK_DESTINATARIO_TIPO");


--
-- TOC entry 2416 (class 2606 OID 149237)
-- Name: ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO PK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"
    ADD CONSTRAINT "PK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" PRIMARY KEY ("SK_PRATICA_STATO", "SK_MOTIVAZIONE_TIPO", "DAT_INIZIO_VALIDITA");


--
-- TOC entry 2428 (class 2606 OID 149239)
-- Name: ESENZIONE_S_DOCUMENTO PK_ESENZIONE_STORICO_DOCUMENTO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "PK_ESENZIONE_STORICO_DOCUMENTO" PRIMARY KEY ("SK_STORICO_DOUMENTO");


--
-- TOC entry 2376 (class 2606 OID 149241)
-- Name: ESENZIONE_D_DOCUMENTO_STATO PK_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_STATO"
    ADD CONSTRAINT "PK_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO" PRIMARY KEY ("SK_DOCUMENTO_STATO");


--
-- TOC entry 2465 (class 2606 OID 149243)
-- Name: ESENZIONE_T_REPOSITORY_DOCUMENTALE PK_ESENZIONE_T_AT_01; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"
    ADD CONSTRAINT "PK_ESENZIONE_T_AT_01" PRIMARY KEY ("SK_REPOSITORY");


--
-- TOC entry 2450 (class 2606 OID 149245)
-- Name: ESENZIONE_T_METADATI_DOCUMENTO PK_ESENZIONE_T_METADATI_DOCUMENTO; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_METADATI_DOCUMENTO"
    ADD CONSTRAINT "PK_ESENZIONE_T_METADATI_DOCUMENTO" PRIMARY KEY ("SK_METADATI_DOCUMENTO");


--
-- TOC entry 2460 (class 2606 OID 149247)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE PK_ESENZIONE_T__01; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "PK_ESENZIONE_T__01" PRIMARY KEY ("SK_PRATICA_ESENZIONE");


--
-- TOC entry 2420 (class 2606 OID 149249)
-- Name: ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO PK_ESENZIONE_T__02; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"
    ADD CONSTRAINT "PK_ESENZIONE_T__02" PRIMARY KEY ("SK_PRATICA_ESENZIONE", "SK_DOCUMENTO", "DAT_INIZIO_VALIDITA");


--
-- TOC entry 2463 (class 2606 OID 149251)
-- Name: ESENZIONE_T_PROGRESSIVO PK_ESENZIONE_T__03; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PROGRESSIVO"
    ADD CONSTRAINT "PK_ESENZIONE_T__03" PRIMARY KEY ("ID_PROGRESSIVO");


--
-- TOC entry 2439 (class 2606 OID 149253)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE PK_ESENZIONE_T__04; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "PK_ESENZIONE_T__04" PRIMARY KEY ("SK_CRONOLOGIA");


--
-- TOC entry 2359 (class 2606 OID 149255)
-- Name: ESENRED_T_ESENZIONI_REDDITO PK_Esenzione; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_T_ESENZIONI_REDDITO"
    ADD CONSTRAINT "PK_Esenzione" PRIMARY KEY ("ID_ESENZIONE");


--
-- TOC entry 2341 (class 2606 OID 149257)
-- Name: ESENRED_C_MESSAGGI PK_Messaggi; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_MESSAGGI"
    ADD CONSTRAINT "PK_Messaggi" PRIMARY KEY ("CODICE");


--
-- TOC entry 2363 (class 2606 OID 149259)
-- Name: ESENRED_W_NOTIFICHE PK_Notifica; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_W_NOTIFICHE"
    ADD CONSTRAINT "PK_Notifica" PRIMARY KEY ("ID_NOTIFICA");


--
-- TOC entry 2343 (class 2606 OID 149261)
-- Name: ESENRED_C_PARAMETRI PK_Parametri; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_PARAMETRI"
    ADD CONSTRAINT "PK_Parametri" PRIMARY KEY ("NUM_PROGRESSIVO");


--
-- TOC entry 2349 (class 2606 OID 149263)
-- Name: ESENRED_D_TIPI_ESENZIONI_REDDITO PK_TipologiaEse_01; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"
    ADD CONSTRAINT "PK_TipologiaEse_01" PRIMARY KEY ("COD_ESENZIONE");


--
-- TOC entry 2345 (class 2606 OID 149265)
-- Name: ESENRED_C_TITOLO_DICHIARANTE PK_TitoloDichiarante; Type: CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_TITOLO_DICHIARANTE"
    ADD CONSTRAINT "PK_TitoloDichiarante" PRIMARY KEY ("COD_TITOLO");



CREATE INDEX "IXFK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_GRUPPO_UTENTI" ON esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" ("SK_GRUPPO_UTENTI" ASC)
;

ALTER TABLE esenred."ESENZIONE_D_NOTIFICA_TIPO" ADD CONSTRAINT "PK_TIPOLOGIA NO_01"
	PRIMARY KEY ("SK_NOTIFICA_TIPO")
;

CREATE INDEX "IXFK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_PRESTAZIONE_SPECIALISTICA" ON esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE" ("SK_PRESTAZIONE" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO_ESENZIONE_D_GRUPPO_ESENZIONI" ON esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" ("SK_GRUPPO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO_ESENZIONE_D_TIPOLOGIA_ESENIONE_AURA" ON esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" ("SK_TIPOLOGIA_ESENZIONE_AURA" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_MOTIVAZIONE_TIPO" ON esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" ("SK_MOTIVAZIONE_TIPO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_PRATICA_STATO " ON esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" ("SK_PRATICA_STATO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_DOCUMENTO" ON esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" ("SK_DOCUMENTO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_PRATICA_ESENZIONE" ON esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" ("SK_PRATICA_ESENZIONE" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTALE" ON esenred."ESENZIONE_S_DOCUMENTO" ("SK_REPOSITORY" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO" ON esenred."ESENZIONE_S_DOCUMENTO" ("SK_TIPOLOGIA_STATO_DOCUMENTO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPOLOGIA_STATO_PRATICA" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" ("SK_TIPOLOGIA_STATO_PRATICA" ASC)
;


ALTER TABLE esenred."ESENZIONE_T_CITTADINO" ADD CONSTRAINT "PK_ESENZIONE_T_CITTADINO"
	PRIMARY KEY ("CODICE_FISCALE")
;


CREATE INDEX "IXFK_ESENZIONE_T_CITTADINO_ESENRED_D_AZIENDASANITARIA" ON esenred."ESENZIONE_T_CITTADINO" ("ID_AZIENDA" ASC)
;


CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_CITTADINO" ON esenred."ESENZIONE_T_DOCUMENTO" ("CODICE_FISCALE_CITTADINO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO_03" ON esenred."ESENZIONE_T_METADATI_DOCUMENTO" ("SK_METADATI_DOCUMENTO" ASC)
;


CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" ("SK_DISTRETTO_SOCIO_SANITARIO" ASC)
;


CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPOLOGIA_STATO_PRATICA" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" ("SK_TIPOLOGIA_STATO_PRATICA" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_T_CITTADINO" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" ("CODICE_FISCALE_BENEFICIARIO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_T_CITTADINO_02" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" ("CODICE_FISCALE_DELEGATO" ASC)
;

ALTER TABLE esenred."ESENRED_W_NOTIFICHE" ADD CONSTRAINT "FK_ESENRED_W_NO_TIPOLOGIA_N_01"
	FOREIGN KEY ("SK_TIPOLOGIA_NOTIFICA") REFERENCES esenred."ESENZIONE_D_NOTIFICA_TIPO" ("SK_NOTIFICA_TIPO") ON DELETE No Action ON UPDATE No Action
;



--
-- TOC entry 2333 (class 1259 OID 149266)
-- Name: IXFK_Comune_Provincia; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Comune_Provincia" ON esenred."ESENRED_C_COMUNI" USING btree ("COD_PROVINCIA");


--
-- TOC entry 2336 (class 1259 OID 149267)
-- Name: IXFK_ESENRED_C_MA_ESENRED01; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENRED_C_MA_ESENRED01" ON esenred."ESENRED_C_MATRICE_ESENZIONI" USING btree ("COD_ESISTENTE");


--
-- TOC entry 2381 (class 1259 OID 149268)
-- Name: IXFK_ESENZIONE_D_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI" ON esenred."ESENZIONE_D_ESENZIONE" USING btree ("SK_GRUPPO");


--
-- TOC entry 2364 (class 1259 OID 149269)
-- Name: IXFK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_ESENZIONE; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_ESENZIONE" ON esenred."ESENZIONE_D_DIAGNOSI" USING btree ("SK_ESENZIONE");


--
-- TOC entry 2365 (class 1259 OID 149270)
-- Name: IXFK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_DURATA_TIPO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_DURATA_TIPO" ON esenred."ESENZIONE_D_DIAGNOSI" USING btree ("SK_DURATA_TIPO");


--
-- TOC entry 2368 (class 1259 OID 149720)
-- Name: IXFK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" ON esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" USING btree ("ID_AZIENDA");


--
-- TOC entry 2371 (class 1259 OID 149272)
-- Name: IXFK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" ON esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" USING btree ("SK_GRUPPO");


--
-- TOC entry 2372 (class 1259 OID 149273)
-- Name: IXFK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI2; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI2" ON esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" USING btree ("SK_TIPO_DOCUMENTO");


--
-- TOC entry 2394 (class 1259 OID 149274)
-- Name: IXFK_ESENZIONE_D_NOTIFICA_TIPO_ESENZIONE_R_DESTINATARIO_TIPO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_NOTIFICA_TIPO_ESENZIONE_R_DESTINATARIO_TIPO" ON esenred."ESENZIONE_D_NOTIFICA_TIPO" USING btree ("SK_DESTINATARIO_TIPO");


--
-- TOC entry 2401 (class 1259 OID 149275)
-- Name: IXFK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" ON esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" USING btree ("SK_GRUPPO_UTENTI");


--
-- TOC entry 2402 (class 1259 OID 149276)
-- Name: IXFK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_SERVIZIO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_SERVIZIO" ON esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI" USING btree ("SK_SERVIZIO");


CREATE INDEX "IXFK_ESENRED_W_NO_TIPOLOG01" ON esenred."ESENRED_W_NOTIFICHE" ("SK_TIPOLOGIA_NOTIFICA" ASC)
;

--
-- TOC entry 2409 (class 1259 OID 149277)
-- Name: IXFK_ESENZIONE_D_TIPOLOGIA_AURA; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_TIPOLOGIA_AURA" ON esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" USING btree ("SK_TIPOLOGIA_ESENZIONE_AURA");


--
-- TOC entry 2410 (class 1259 OID 149278)
-- Name: IXFK_ESENZIONE_D_TIPOLOGIA_AURA_GRUPPO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_D_TIPOLOGIA_AURA_GRUPPO" ON esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" USING btree ("SK_GRUPPO");


--
-- TOC entry 2405 (class 1259 OID 149279)
-- Name: IXFK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE" ON esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE" USING btree ("SK_PRESTAZIONE");


--
-- TOC entry 2406 (class 1259 OID 149280)
-- Name: IXFK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_DIAGNOSI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_DIAGNOSI" ON esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE" USING btree ("SK_DIAGNOSI");


--
-- TOC entry 2413 (class 1259 OID 149281)
-- Name: IXFK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_MOTIVAZI; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_MOTIVAZI" ON esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" USING btree ("SK_MOTIVAZIONE_TIPO");


--
-- TOC entry 2414 (class 1259 OID 149282)
-- Name: IXFK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_PRATICA_; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_PRATICA_" ON esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO" USING btree ("SK_PRATICA_STATO");


--
-- TOC entry 2421 (class 1259 OID 149283)
-- Name: IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_DIAGNOSI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_DIAGNOSI" ON esenred."ESENZIONE_S_DOCUMENTO" USING btree ("SK_DIAGNOSI");


--
-- TOC entry 2422 (class 1259 OID 149284)
-- Name: IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO" ON esenred."ESENZIONE_S_DOCUMENTO" USING btree ("SK_TIPO_DOCUMENTO");


--
-- TOC entry 2423 (class 1259 OID 149285)
-- Name: IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCU; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCU" ON esenred."ESENZIONE_S_DOCUMENTO" USING btree ("SK_TIPOLOGIA_STATO_DOCUMENTO");


--
-- TOC entry 2424 (class 1259 OID 149286)
-- Name: IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_DOCUMENTO" ON esenred."ESENZIONE_S_DOCUMENTO" USING btree ("SK_DOCUMENTO");


--
-- TOC entry 2425 (class 1259 OID 149287)
-- Name: IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO" ON esenred."ESENZIONE_S_DOCUMENTO" USING btree ("SK_METADATI_DOCUMENTO");


--
-- TOC entry 2426 (class 1259 OID 149288)
-- Name: IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMEN; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMEN" ON esenred."ESENZIONE_S_DOCUMENTO" USING btree ("SK_REPOSITORY");


--
-- TOC entry 2429 (class 1259 OID 149761)
-- Name: IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_D_AZIENDASANITARIA; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_D_AZIENDASANITARIA" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("ID_AZIENDA");


--
-- TOC entry 2430 (class 1259 OID 149739)
-- Name: IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_T_ASL_OPERATORE; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_T_ASL_OPERATORE" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("COD_FISCALE_OPERATORE");


--
-- TOC entry 2431 (class 1259 OID 149291)
-- Name: IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DIAGNOSI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DIAGNOSI" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_DIAGNOSI");


--
-- TOC entry 2432 (class 1259 OID 149292)
-- Name: IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DISTRETTO_SOCIO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DISTRETTO_SOCIO" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_DISTRETTO_SOCIO_SANITARIO");


--
-- TOC entry 2433 (class 1259 OID 149293)
-- Name: IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_GRUPPO_ESENZION; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_GRUPPO_ESENZION" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_GRUPPO");


--
-- TOC entry 2434 (class 1259 OID 149294)
-- Name: IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_INVALIDITA_TIPO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_INVALIDITA_TIPO" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_INVALIDITA_TIPO");


--
-- TOC entry 2435 (class 1259 OID 149295)
-- Name: IXFK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPOLOGIA_STATO; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPOLOGIA_STATO" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_TIPOLOGIA_STATO_PRATICA");


--
-- TOC entry 2436 (class 1259 OID 149296)
-- Name: IXFK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPO_MOTIVAZION; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPO_MOTIVAZION" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_TIPO_MOTIVAZIONE");


--
-- TOC entry 2440 (class 1259 OID 149297)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_DIAGNOSI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_DIAGNOSI" ON esenred."ESENZIONE_T_DOCUMENTO" USING btree ("SK_DIAGNOSI");


--
-- TOC entry 2441 (class 1259 OID 149298)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO" ON esenred."ESENZIONE_T_DOCUMENTO" USING btree ("SK_TIPO_DOCUMENTO");


--
-- TOC entry 2442 (class 1259 OID 149299)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO" ON esenred."ESENZIONE_T_DOCUMENTO" USING btree ("SK_TIPOLOGIA_STATO_DOCUMENTO");


--
-- TOC entry 2443 (class 1259 OID 149300)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO" ON esenred."ESENZIONE_T_DOCUMENTO" USING btree ("SK_DOCUMENTO");


--
-- TOC entry 2444 (class 1259 OID 149301)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTALE; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTALE" ON esenred."ESENZIONE_T_DOCUMENTO" USING btree ("SK_REPOSITORY");


--
-- TOC entry 2417 (class 1259 OID 149302)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_DOCUME; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_DOCUME" ON esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" USING btree ("SK_DOCUMENTO");


--
-- TOC entry 2418 (class 1259 OID 149303)
-- Name: IXFK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_PRATIC; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_PRATIC" ON esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO" USING btree ("SK_PRATICA_ESENZIONE");


--
-- TOC entry 2447 (class 1259 OID 149304)
-- Name: IXFK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T" ON esenred."ESENZIONE_T_METADATI_DOCUMENTO" USING btree ("SK_METADATI_DOCUMENTO");


--
-- TOC entry 2448 (class 1259 OID 149305)
-- Name: IXFK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_DOCUMENTO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_DOCUMENTO" ON esenred."ESENZIONE_T_METADATI_DOCUMENTO" USING btree ("SK_DOCUMENTO");


--
-- TOC entry 2451 (class 1259 OID 149804)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_D_AZIENDASANITARIA; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_D_AZIENDASANITARIA" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("ID_AZIENDA");


--
-- TOC entry 2452 (class 1259 OID 149783)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_T_ASL_OPERATORE; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_T_ASL_OPERATORE" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("COD_FISCALE_OPERATORE");


--
-- TOC entry 2453 (class 1259 OID 149308)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DIAGNOSI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DIAGNOSI" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("SK_DIAGNOSI");


--
-- TOC entry 2454 (class 1259 OID 149309)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DISTRETTO_SOCIO_; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DISTRETTO_SOCIO_" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("SK_DISTRETTO_SOCIO_SANITARIO");


--
-- TOC entry 2455 (class 1259 OID 149310)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("SK_GRUPPO");


--
-- TOC entry 2456 (class 1259 OID 149311)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_INVALIDITA_TIPO; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_INVALIDITA_TIPO" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("SK_INVALIDITA_TIPO");


--
-- TOC entry 2457 (class 1259 OID 149312)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPOLOGIA_STATO_; Type: INDEX; Schema: esenred; Owner: esenred
--

--CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPOLOGIA_STATO_" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("SK_TIPOLOGIA_STATO_PRATICA");


--
-- TOC entry 2458 (class 1259 OID 149313)
-- Name: IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPO_MOTIVAZIONE; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPO_MOTIVAZIONE" ON esenred."ESENZIONE_T_PRATICA_ESENZIONE" USING btree ("SK_TIPO_MOTIVAZIONE");


--
-- TOC entry 2461 (class 1259 OID 149825)
-- Name: IXFK_ESENZIONE_T__ESENRED01; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T__ESENRED01" ON esenred."ESENZIONE_T_PROGRESSIVO" USING btree ("ID_AZIENDA");


--
-- TOC entry 2437 (class 1259 OID 149315)
-- Name: IXFK_ESENZIONE_T__ESENZIO01; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_ESENZIONE_T__ESENZIO01" ON esenred."ESENZIONE_S_PRATICA_ESENZIONE" USING btree ("SK_ID_ESENZIONE");


--
-- TOC entry 2352 (class 1259 OID 149316)
-- Name: IXFK_Esenzione_ASL; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Esenzione_ASL" ON esenred."ESENRED_T_ESENZIONI_REDDITO" USING btree ("COD_NAZIONALE_ASL_RILASCIO");


--
-- TOC entry 2353 (class 1259 OID 149317)
-- Name: IXFK_Esenzione_Cittadino02; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Esenzione_Cittadino02" ON esenred."ESENRED_T_ESENZIONI_REDDITO" USING btree ("ID_CITTADINO_DICHIARANTE");


--
-- TOC entry 2354 (class 1259 OID 149319)
-- Name: IXFK_Esenzione_Cittadino03; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Esenzione_Cittadino03" ON esenred."ESENRED_T_ESENZIONI_REDDITO" USING btree ("ID_CITTADINO_BENEFICIARIO");


--
-- TOC entry 2355 (class 1259 OID 149320)
-- Name: IXFK_Esenzione_Operatore; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Esenzione_Operatore" ON esenred."ESENRED_T_ESENZIONI_REDDITO" USING btree ("ID_OPERATORE_RICHIESTA");


--
-- TOC entry 2356 (class 1259 OID 149321)
-- Name: IXFK_Esenzione_TipologiaE01; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Esenzione_TipologiaE01" ON esenred."ESENRED_T_ESENZIONI_REDDITO" USING btree ("COD_ESENZIONE");


--
-- TOC entry 2357 (class 1259 OID 149323)
-- Name: IXFK_Esenzione_TitoloDich01; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Esenzione_TitoloDich01" ON esenred."ESENRED_T_ESENZIONI_REDDITO" USING btree ("COD_TITOLO_DICHIARANTE");


--
-- TOC entry 2337 (class 1259 OID 149324)
-- Name: IXFK_MatriceEsenz_Tipolog01; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_MatriceEsenz_Tipolog01" ON esenred."ESENRED_C_MATRICE_ESENZIONI" USING btree ("COD_RICHIESTA");


--
-- TOC entry 2360 (class 1259 OID 149325)
-- Name: IXFK_Notifica_Cittadino; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Notifica_Cittadino" ON esenred."ESENRED_W_NOTIFICHE" USING btree ("ID_AURA");


CREATE INDEX "IXFK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO_ESENRED_D_AZIENDASANITARIA" ON esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO" ("ID_AZIENDA" ASC)
;


CREATE INDEX "IXFK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI_ESENZIONE_D_GRUPPO_ESENZIONI" ON esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" ("SK_GRUPPO" ASC)
;

CREATE INDEX "IXFK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI_ESENZIONE_D_TIPO_DOCUMENTO" ON esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI" ("SK_TIPO_DOCUMENTO" ASC)
;

--
-- TOC entry 2361 (class 1259 OID 149326)
-- Name: IXFK_Notifica_Operatore; Type: INDEX; Schema: esenred; Owner: esenred
--

CREATE INDEX "IXFK_Notifica_Operatore" ON esenred."ESENRED_W_NOTIFICHE" USING btree ("ID_OPERATORE");


CREATE INDEX "IXFK_ESENRED_W_NOTIFICHE_ESENZIONE_T_CITTADINO" ON esenred."ESENRED_W_NOTIFICHE" ("CODICE_FISCALE" ASC)
;


--
-- TOC entry 2466 (class 2606 OID 149327)
-- Name: ESENRED_C_MATRICE_ESENZIONI FK_ESENRED_C_MA_ESENRED_D_T_01; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_MATRICE_ESENZIONI"
    ADD CONSTRAINT "FK_ESENRED_C_MA_ESENRED_D_T_01" FOREIGN KEY ("COD_ESISTENTE") REFERENCES esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"("COD_ESENZIONE");


--
-- TOC entry 2476 (class 2606 OID 149332)
-- Name: ESENZIONE_D_ESENZIONE FK_ESENZIONE_D_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_D_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI" FOREIGN KEY ("SK_GRUPPO") REFERENCES esenred."ESENZIONE_D_GRUPPO_ESENZIONI"("SK_GRUPPO");


--
-- TOC entry 2471 (class 2606 OID 149337)
-- Name: ESENZIONE_D_DIAGNOSI FK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_ESENZIONE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DIAGNOSI"
    ADD CONSTRAINT "FK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_ESENZIONE" FOREIGN KEY ("SK_ESENZIONE") REFERENCES esenred."ESENZIONE_D_ESENZIONE"("SK_ESENZIONE");


--
-- TOC entry 2472 (class 2606 OID 149342)
-- Name: ESENZIONE_D_DIAGNOSI FK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_DURATA_TIPO ; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DIAGNOSI"
    ADD CONSTRAINT "FK_ESENZIONE_D_DIAGNOSI_ESENZIONE_D_DURATA_TIPO " FOREIGN KEY ("SK_DURATA_TIPO") REFERENCES esenred."ESENZIONE_D_DURATA_TIPO"("SK_DURATA_TIPO");


--
-- TOC entry 2473 (class 2606 OID 149721)
-- Name: ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO FK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO_ESENRED_D_AZIENDASANIT; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"
    ADD CONSTRAINT "FK_ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO_ESENRED_D_AZIENDASANIT" FOREIGN KEY ("ID_AZIENDA") REFERENCES esenred."ESENRED_D_AZIENDASANITARIA"("ID_AZIENDA");


--
-- TOC entry 2474 (class 2606 OID 149352)
-- Name: ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI FK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI_ESENZIONE_D_GRUPPO_ES; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"
    ADD CONSTRAINT "FK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI_ESENZIONE_D_GRUPPO_ES" FOREIGN KEY ("SK_GRUPPO") REFERENCES esenred."ESENZIONE_D_GRUPPO_ESENZIONI"("SK_GRUPPO");


--
-- TOC entry 2475 (class 2606 OID 149357)
-- Name: ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI FK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI_ESENZIONE_D_TIPO_DOCU; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI"
    ADD CONSTRAINT "FK_ESENZIONE_D_DOCUMENTO_GRUPPO_ESENZIONI_ESENZIONE_D_TIPO_DOCU" FOREIGN KEY ("SK_TIPO_DOCUMENTO") REFERENCES esenred."ESENZIONE_D_DOCUMENTO_TIPO"("SK_DOCUMENTO_TIPO");


--
-- TOC entry 2477 (class 2606 OID 149362)
-- Name: ESENZIONE_D_NOTIFICA_TIPO FK_ESENZIONE_D_NOTIFICA_TIPO_ESENZIONE_R_DESTINATARIO_TIPO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_NOTIFICA_TIPO"
    ADD CONSTRAINT "FK_ESENZIONE_D_NOTIFICA_TIPO_ESENZIONE_R_DESTINATARIO_TIPO" FOREIGN KEY ("SK_DESTINATARIO_TIPO") REFERENCES esenred."ESENZIONE_R_DESTINATARIO_TIPO"("SK_DESTINATARIO_TIPO");


--
-- TOC entry 2478 (class 2606 OID 149367)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI FK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_GRUPPO_UTENTI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI"
    ADD CONSTRAINT "FK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_GRUPPO_UTENTI" FOREIGN KEY ("SK_GRUPPO_UTENTI") REFERENCES esenred."ESENZIONE_D_GRUPPO_UTENTI"("SK_GRUPPO_UTENTI");


--
-- TOC entry 2479 (class 2606 OID 149372)
-- Name: ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI FK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_SERVIZIO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI"
    ADD CONSTRAINT "FK_ESENZIONE_D_SERVIZIO_GRUPPO_UTENTI_ESENZIONE_D_SERVIZIO" FOREIGN KEY ("SK_SERVIZIO") REFERENCES esenred."ESENZIONE_D_SERVIZIO"("SK_SERVIZIO");


--
-- TOC entry 2482 (class 2606 OID 149377)
-- Name: ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO FK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO_ESENZIONE_D_GRUPPO_E; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO"
    ADD CONSTRAINT "FK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO_ESENZIONE_D_GRUPPO_E" FOREIGN KEY ("SK_GRUPPO") REFERENCES esenred."ESENZIONE_D_GRUPPO_ESENZIONI"("SK_GRUPPO");


--
-- TOC entry 2480 (class 2606 OID 149387)
-- Name: ESENZIONE_R_DIAGNOSI_PRESTAZIONE FK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_DIAGNOSI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_DIAGNOSI" FOREIGN KEY ("SK_DIAGNOSI") REFERENCES esenred."ESENZIONE_D_DIAGNOSI"("SK_DIAGNOSI");


--
-- TOC entry 2481 (class 2606 OID 149392)
-- Name: ESENZIONE_R_DIAGNOSI_PRESTAZIONE FK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_PRESTAZIONE_SPE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_DIAGNOSI_PRESTAZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_R_DIAGNOSI_PRESTAZIONE_ESENZIONE_D_PRESTAZIONE_SPE" FOREIGN KEY ("SK_PRESTAZIONE") REFERENCES esenred."ESENZIONE_D_PRESTAZIONE_SPECIALISTICA"("SK_PRESTAZIONE");


--
-- TOC entry 2483 (class 2606 OID 149397)
-- Name: ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO FK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_MOTIVAZION; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"
    ADD CONSTRAINT "FK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_MOTIVAZION" FOREIGN KEY ("SK_MOTIVAZIONE_TIPO") REFERENCES esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"("SK_MOTIVAZIONE_TIPO");


--
-- TOC entry 2484 (class 2606 OID 149402)
-- Name: ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO FK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_PRATICA_ST; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO"
    ADD CONSTRAINT "FK_ESENZIONE_R_MOTIVAZIONE_PRATICA_STATO_ESENZIONE_D_PRATICA_ST" FOREIGN KEY ("SK_PRATICA_STATO") REFERENCES esenred."ESENZIONE_D_PRATICA_STATO"("SK_PRATICA_STATO");


--
-- TOC entry 2487 (class 2606 OID 149407)
-- Name: ESENZIONE_S_DOCUMENTO FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_DIAGNOSI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_DIAGNOSI" FOREIGN KEY ("SK_DIAGNOSI") REFERENCES esenred."ESENZIONE_D_DIAGNOSI"("SK_DIAGNOSI");


--
-- TOC entry 2488 (class 2606 OID 149412)
-- Name: ESENZIONE_S_DOCUMENTO FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO" FOREIGN KEY ("SK_TIPO_DOCUMENTO") REFERENCES esenred."ESENZIONE_D_DOCUMENTO_TIPO"("SK_DOCUMENTO_TIPO");


--
-- TOC entry 2489 (class 2606 OID 149417)
-- Name: ESENZIONE_S_DOCUMENTO FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUME; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUME" FOREIGN KEY ("SK_TIPOLOGIA_STATO_DOCUMENTO") REFERENCES esenred."ESENZIONE_D_DOCUMENTO_STATO"("SK_DOCUMENTO_STATO");


--
-- TOC entry 2490 (class 2606 OID 149422)
-- Name: ESENZIONE_S_DOCUMENTO FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_DOCUMENTO" FOREIGN KEY ("SK_DOCUMENTO") REFERENCES esenred."ESENZIONE_T_DOCUMENTO"("SK_DOCUMENTO");


--
-- TOC entry 2491 (class 2606 OID 149427)
-- Name: ESENZIONE_S_DOCUMENTO FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO" FOREIGN KEY ("SK_METADATI_DOCUMENTO") REFERENCES esenred."ESENZIONE_T_METADATI_DOCUMENTO"("SK_METADATI_DOCUMENTO");


--
-- TOC entry 2492 (class 2606 OID 149432)
-- Name: ESENZIONE_S_DOCUMENTO FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTA; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_STORICO_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTA" FOREIGN KEY ("SK_REPOSITORY") REFERENCES esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"("SK_REPOSITORY");


--
-- TOC entry 2501 (class 2606 OID 149762)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_D_AZIENDASANITARIA; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_D_AZIENDASANITARIA" FOREIGN KEY ("ID_AZIENDA") REFERENCES esenred."ESENRED_D_AZIENDASANITARIA"("ID_AZIENDA");


--
-- TOC entry 2500 (class 2606 OID 149740)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_T_ASL_OPERATORE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENRED_T_ASL_OPERATORE" FOREIGN KEY ("COD_FISCALE_OPERATORE") REFERENCES esenred."ESENRED_T_ASL_OPERATORE"("COD_FISCALE_OPERATORE");


--
-- TOC entry 2493 (class 2606 OID 149447)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DIAGNOSI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DIAGNOSI" FOREIGN KEY ("SK_DIAGNOSI") REFERENCES esenred."ESENZIONE_D_DIAGNOSI"("SK_DIAGNOSI");


--
-- TOC entry 2494 (class 2606 OID 149452)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DISTRETTO_SOCIO_S; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_DISTRETTO_SOCIO_S" FOREIGN KEY ("SK_DISTRETTO_SOCIO_SANITARIO") REFERENCES esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"("SK_DISTRETTO_SOCIO_SANITARIO");


--
-- TOC entry 2495 (class 2606 OID 149457)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_GRUPPO_ESENZIONI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_GRUPPO_ESENZIONI" FOREIGN KEY ("SK_GRUPPO") REFERENCES esenred."ESENZIONE_D_GRUPPO_ESENZIONI"("SK_GRUPPO");


--
-- TOC entry 2496 (class 2606 OID 149462)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_INVALIDITA_TIPO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_S_PRATICA_ESENZIONE _ESENZIONE_D_INVALIDITA_TIPO" FOREIGN KEY ("SK_INVALIDITA_TIPO") REFERENCES esenred."ESENZIONE_D_INVALIDITA_TIPO"("SK_INVALIDITA_TIPO");


--
-- TOC entry 2497 (class 2606 OID 149467)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPOLOGIA_STATO_P; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPOLOGIA_STATO_P" FOREIGN KEY ("SK_TIPOLOGIA_STATO_PRATICA") REFERENCES esenred."ESENZIONE_D_PRATICA_STATO"("SK_PRATICA_STATO");


--
-- TOC entry 2498 (class 2606 OID 149472)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPO_MOTIVAZIONE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_CRONOLOGIA_PRATICA_ESENZIONE_D_TIPO_MOTIVAZIONE" FOREIGN KEY ("SK_TIPO_MOTIVAZIONE") REFERENCES esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"("SK_MOTIVAZIONE_TIPO");


--
-- TOC entry 2502 (class 2606 OID 149477)
-- Name: ESENZIONE_T_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_DIAGNOSI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_DIAGNOSI" FOREIGN KEY ("SK_DIAGNOSI") REFERENCES esenred."ESENZIONE_D_DIAGNOSI"("SK_DIAGNOSI");


--
-- TOC entry 2503 (class 2606 OID 149482)
-- Name: ESENZIONE_T_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_D_TIPO_DOCUMENTO" FOREIGN KEY ("SK_TIPO_DOCUMENTO") REFERENCES esenred."ESENZIONE_D_DOCUMENTO_TIPO"("SK_DOCUMENTO_TIPO");


--
-- TOC entry 2504 (class 2606 OID 149487)
-- Name: ESENZIONE_T_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_TIPOLOGIA_STATO_DOCUMENTO" FOREIGN KEY ("SK_TIPOLOGIA_STATO_DOCUMENTO") REFERENCES esenred."ESENZIONE_D_DOCUMENTO_STATO"("SK_DOCUMENTO_STATO");


--
-- TOC entry 2505 (class 2606 OID 149492)
-- Name: ESENZIONE_T_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENTO" FOREIGN KEY ("SK_DOCUMENTO") REFERENCES esenred."ESENZIONE_T_METADATI_DOCUMENTO"("SK_METADATI_DOCUMENTO");


--
-- TOC entry 2506 (class 2606 OID 149497)
-- Name: ESENZIONE_T_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTALE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_REPOSITORY_DOCUMENTALE" FOREIGN KEY ("SK_REPOSITORY") REFERENCES esenred."ESENZIONE_T_REPOSITORY_DOCUMENTALE"("SK_REPOSITORY");


--
-- TOC entry 2485 (class 2606 OID 149502)
-- Name: ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_DOCUMENT; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_DOCUMENT" FOREIGN KEY ("SK_DOCUMENTO") REFERENCES esenred."ESENZIONE_T_DOCUMENTO"("SK_DOCUMENTO");


--
-- TOC entry 2486 (class 2606 OID 149507)
-- Name: ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO FK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_PRATICA_; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_R_PRATICA_ESENZIONE_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_PRATICA_ESENZIONE_ESENZIONE_T_PRATICA_" FOREIGN KEY ("SK_PRATICA_ESENZIONE") REFERENCES esenred."ESENZIONE_T_PRATICA_ESENZIONE"("SK_PRATICA_ESENZIONE");


--
-- TOC entry 2507 (class 2606 OID 149512)
-- Name: ESENZIONE_T_METADATI_DOCUMENTO FK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_DOCUMENTO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_METADATI_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_DOCUMENTO" FOREIGN KEY ("SK_DOCUMENTO") REFERENCES esenred."ESENZIONE_T_DOCUMENTO"("SK_DOCUMENTO");


--
-- TOC entry 2508 (class 2606 OID 149517)
-- Name: ESENZIONE_T_METADATI_DOCUMENTO FK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENT; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_METADATI_DOCUMENTO"
    ADD CONSTRAINT "FK_ESENZIONE_T_METADATI_DOCUMENTO_ESENZIONE_T_METADATI_DOCUMENT" FOREIGN KEY ("SK_METADATI_DOCUMENTO") REFERENCES esenred."ESENZIONE_T_METADATI_DOCUMENTO"("SK_METADATI_DOCUMENTO");


--
-- TOC entry 2516 (class 2606 OID 149805)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_D_AZIENDASANITARIA; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_D_AZIENDASANITARIA" FOREIGN KEY ("ID_AZIENDA") REFERENCES esenred."ESENRED_D_AZIENDASANITARIA"("ID_AZIENDA");


--
-- TOC entry 2515 (class 2606 OID 149784)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_T_ASL_OPERATORE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENRED_T_ASL_OPERATORE" FOREIGN KEY ("COD_FISCALE_OPERATORE") REFERENCES esenred."ESENRED_T_ASL_OPERATORE"("COD_FISCALE_OPERATORE");


--
-- TOC entry 2509 (class 2606 OID 149532)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DIAGNOSI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DIAGNOSI" FOREIGN KEY ("SK_DIAGNOSI") REFERENCES esenred."ESENZIONE_D_DIAGNOSI"("SK_DIAGNOSI");


--
-- TOC entry 2510 (class 2606 OID 149537)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DISTRETTO_SOCIO_SA; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_DISTRETTO_SOCIO_SA" FOREIGN KEY ("SK_DISTRETTO_SOCIO_SANITARIO") REFERENCES esenred."ESENZIONE_D_DISTRETTO_SOCIO_SANITARIO"("SK_DISTRETTO_SOCIO_SANITARIO");


--
-- TOC entry 2511 (class 2606 OID 149542)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_GRUPPO_ESENZIONI" FOREIGN KEY ("SK_GRUPPO") REFERENCES esenred."ESENZIONE_D_GRUPPO_ESENZIONI"("SK_GRUPPO");


--
-- TOC entry 2512 (class 2606 OID 149547)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_INVALIDITA_TIPO; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_INVALIDITA_TIPO" FOREIGN KEY ("SK_INVALIDITA_TIPO") REFERENCES esenred."ESENZIONE_D_INVALIDITA_TIPO"("SK_INVALIDITA_TIPO");


--
-- TOC entry 2513 (class 2606 OID 149552)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPOLOGIA_STATO_PR; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPOLOGIA_STATO_PR" FOREIGN KEY ("SK_TIPOLOGIA_STATO_PRATICA") REFERENCES esenred."ESENZIONE_D_PRATICA_STATO"("SK_PRATICA_STATO");


--
-- TOC entry 2514 (class 2606 OID 149557)
-- Name: ESENZIONE_T_PRATICA_ESENZIONE FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPO_MOTIVAZIONE; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_D_TIPO_MOTIVAZIONE" FOREIGN KEY ("SK_TIPO_MOTIVAZIONE") REFERENCES esenred."ESENZIONE_D_MOTIVAZIONE_TIPO"("SK_MOTIVAZIONE_TIPO");


--
-- TOC entry 2517 (class 2606 OID 149826)
-- Name: ESENZIONE_T_PROGRESSIVO FK_ESENZIONE_T__ESENRED_D_A_01; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_T_PROGRESSIVO"
    ADD CONSTRAINT "FK_ESENZIONE_T__ESENRED_D_A_01" FOREIGN KEY ("ID_AZIENDA") REFERENCES esenred."ESENRED_D_AZIENDASANITARIA"("ID_AZIENDA");


--
-- TOC entry 2499 (class 2606 OID 149567)
-- Name: ESENZIONE_S_PRATICA_ESENZIONE FK_ESENZIONE_T__ESENZIONE_T_02; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENZIONE_S_PRATICA_ESENZIONE"
    ADD CONSTRAINT "FK_ESENZIONE_T__ESENZIONE_T_02" FOREIGN KEY ("SK_ID_ESENZIONE") REFERENCES esenred."ESENZIONE_T_PRATICA_ESENZIONE"("SK_PRATICA_ESENZIONE");


--
-- TOC entry 2468 (class 2606 OID 149572)
-- Name: ESENRED_T_ESENZIONI_REDDITO FK_Esenzione_ASL; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_T_ESENZIONI_REDDITO"
    ADD CONSTRAINT "FK_Esenzione_ASL" FOREIGN KEY ("COD_NAZIONALE_ASL_RILASCIO") REFERENCES esenred."ESENRED_D_AZIENDASANITARIA"("ID_AZIENDA");


--
-- TOC entry 2469 (class 2606 OID 149577)
-- Name: ESENRED_T_ESENZIONI_REDDITO FK_Esenzione_TipologiaEsenz_01; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_T_ESENZIONI_REDDITO"
    ADD CONSTRAINT "FK_Esenzione_TipologiaEsenz_01" FOREIGN KEY ("COD_ESENZIONE") REFERENCES esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"("COD_ESENZIONE");


--
-- TOC entry 2470 (class 2606 OID 149582)
-- Name: ESENRED_T_ESENZIONI_REDDITO FK_Esenzione_TitoloDichiarante; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_T_ESENZIONI_REDDITO"
    ADD CONSTRAINT "FK_Esenzione_TitoloDichiarante" FOREIGN KEY ("COD_TITOLO_DICHIARANTE") REFERENCES esenred."ESENRED_C_TITOLO_DICHIARANTE"("COD_TITOLO");


--
-- TOC entry 2467 (class 2606 OID 149587)
-- Name: ESENRED_C_MATRICE_ESENZIONI FK_MatriceEsenz_TipologiaEs_01; Type: FK CONSTRAINT; Schema: esenred; Owner: esenred
--

ALTER TABLE ONLY esenred."ESENRED_C_MATRICE_ESENZIONI"
    ADD CONSTRAINT "FK_MatriceEsenz_TipologiaEs_01" FOREIGN KEY ("COD_RICHIESTA") REFERENCES esenred."ESENRED_D_TIPI_ESENZIONI_REDDITO"("COD_ESENZIONE");



ALTER TABLE esenred."ESENZIONE_R_GRUPPO_ESENZIONI_ESENZIONE_AURA_TIPO" ADD CONSTRAINT "FK_ESENZIONE_D_TIPOLOGIA_AURA_COPRE_GRUPPO_ESENZIONE_D_TIPOLOGIA_ESENIONE_AURA"
	FOREIGN KEY ("SK_TIPOLOGIA_ESENZIONE_AURA") REFERENCES esenred."ESENZIONE_D_ESENZIONE_AURA_TIPO" ("SK_TIPOLOGIA_ESENZIONE_AURA") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE esenred."ESENZIONE_T_CITTADINO" ADD CONSTRAINT "FK_ESENZIONE_T_CITTADINO_ESENRED_D_AZIENDASANITARIA"
	FOREIGN KEY ("ID_AZIENDA") REFERENCES esenred."ESENRED_D_AZIENDASANITARIA" ("ID_AZIENDA") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE esenred."ESENZIONE_T_DOCUMENTO" ADD CONSTRAINT "FK_ESENZIONE_T_DOCUMENTO_ESENZIONE_T_CITTADINO"
	FOREIGN KEY ("CODICE_FISCALE_CITTADINO") REFERENCES esenred."ESENZIONE_T_CITTADINO" ("CODICE_FISCALE") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE esenred."ESENZIONE_T_PRATICA_ESENZIONE" ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_T_CITTADINO"
	FOREIGN KEY ("CODICE_FISCALE_BENEFICIARIO") REFERENCES esenred."ESENZIONE_T_CITTADINO" ("CODICE_FISCALE") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE esenred."ESENZIONE_T_PRATICA_ESENZIONE" ADD CONSTRAINT "FK_ESENZIONE_T_PRATICA_ESENZIONE_ESENZIONE_T_CITTADINO_02"
	FOREIGN KEY ("CODICE_FISCALE_DELEGATO") REFERENCES esenred."ESENZIONE_T_CITTADINO" ("CODICE_FISCALE") ON DELETE No Action ON UPDATE No Action
;


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 207
-- Name: ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq"', 1, true);


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 206
-- Name: SEQ_ESENRED_W_NOTIFICHE; Type: SEQUENCE SET; Schema: esenred; Owner: esenred
--

SELECT pg_catalog.setval('esenred."SEQ_ESENRED_W_NOTIFICHE"', 1, true);


-- Completed on 2018-03-21 12:24:15

--
-- PostgreSQL database dump complete
--




ALTER TABLE esenred."ESENRED_T_ESENZIONI_REDDITO" DROP CONSTRAINT "FK_Esenzione_ASL";

DROP INDEX esenred."IXFK_Esenzione_ASL";

-- Table: esenred."ESENRED_T_ESENZIONI_ASL"

DROP TABLE esenred."ESENRED_D_AZIENDASANITARIA" CASCADE;

CREATE TABLE esenred."ESENRED_D_AZIENDASANITARIA"
(
    "ID_AZIENDA" character varying(6) COLLATE pg_catalog."default" NOT NULL,
    "COD_AZIENDA" character varying(3) COLLATE pg_catalog."default" NOT NULL,
    "COD_REGIONE" character varying(3) COLLATE pg_catalog."default" NOT NULL,
    "DENOMINAZIONE" character varying(100) COLLATE pg_catalog."default" NOT NULL,
    "DATA_CHIUSURA" date,
    "DATA_ATTIVAZIONE" date NOT NULL,
    CONSTRAINT "PK_Azienda" PRIMARY KEY ("ID_AZIENDA")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE esenred."ESENRED_D_AZIENDASANITARIA"
    OWNER to esenred;
COMMENT ON TABLE esenred."ESENRED_D_AZIENDASANITARIA"
    IS 'Anagrafica delle Aziende Sanitarie Nazionali ( ASL, ASO, IRCCS) e degli enti trattati alla pari di aziende a livello regionale.';
	
COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."COD_AZIENDA"
    IS 'Codice ISTAT azienda';

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."COD_REGIONE"
    IS 'codice ISTAT regione';

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."DATA_CHIUSURA"
    IS 'Data di fine validità di una ASL.';

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."DATA_ATTIVAZIONE"
    IS 'Data di inizio validità di una ASL.';

COMMENT ON COLUMN esenred."ESENRED_D_AZIENDASANITARIA"."DENOMINAZIONE"
    IS 'Descrizione della ASL';

truncate table esenred."ESENRED_C_MESSAGGI";

ALTER TABLE esenred_m."ESENZIONE_D_PRATICA_STATO" ADD CONSTRAINT E_PRATICA_STATO_UQ UNIQUE ("COD_STATO");


