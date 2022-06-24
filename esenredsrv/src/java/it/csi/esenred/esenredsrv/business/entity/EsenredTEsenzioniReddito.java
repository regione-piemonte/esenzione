/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredsrv.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the "ESENRED_T_ESENZIONI_REDDITO" database table.
 */
@Entity
@Table(name = "\"ESENRED_T_ESENZIONI_REDDITO\"", schema = "esenred")
@SequenceGenerator(name="\"ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq\"",  sequenceName="\"ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq\"", initialValue=1, allocationSize=1)
@NamedQueries({
        @NamedQuery(name = "EsenredTEsenzioniReddito.findAll", query = "SELECT e FROM EsenredTEsenzioniReddito e"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzione", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and e.codEsenzione = :codEsenzione and e.dataInizio = :dataInizio"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzioneValidaLavorazione", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and (e.codStato = 'V' or e.codStato = 'B')")
})
public class EsenredTEsenzioniReddito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "\"ID_ESENZIONE\"")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq\"")
    private Long idEsenzione;

    @Column(name = "\"COD_ESENZIONE\"")
    private String codEsenzione;

    @Column(name = "\"COD_NAZIONALE_ASL_RILASCIO\"")
    private String codNazionaleAslRilascio;

    @Column(name = "\"COD_STATO\"")
    private String codStato;

    @Column(name = "\"COD_TITOLO_DICHIARANTE\"")
    private String codTitoloDichiarante;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"DATA_FINE\"")
    private Date dataFine;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"DATA_INIZIO\"")
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"DATA_INSERT\"")
    private Date dataInsert;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"DATA_MODIFY\"")
    private Date dataModify;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"DATA_REVOCA\"")
    private Date dataRevoca;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"DATA_RICHIESTA\"")
    private Date dataRichiesta;

    @Column(name = "\"DESC_MOTIVO_REVOCA\"")
    private String descMotivoRevoca;

    @Column(name = "\"FLAG_CONSENSO\"")
    private Integer flagConsenso;

    @Column(name = "\"ID_CITTADINO_BENEFICIARIO\"")
    private Long idCittadinoBeneficiario;

    @Column(name = "\"ID_CITTADINO_DICHIARANTE\"")
    private Long idCittadinoDichiarante;

    @Column(name = "\"ID_OPERATORE_RICHIESTA\"")
    private Long idOperatoreRichiesta;

    @Column(name = "\"ID_USER_INSERT\"")
    private Long idUserInsert;

    @Column(name = "\"ID_USER_MODIFY\"")
    private Long idUserModify;

    @Column(name = "\"NUM_PROTOCOLLO_SOGEI\"")
    private Long numProtocolloSogei;

    //bi-directional many-to-one association to EsenredCTitoloDichiarante
    @ManyToOne
    @JoinColumn(name = "\"COD_TITOLO_DICHIARANTE\"", insertable = false, updatable = false)
    private EsenredCTitoloDichiarante esenredCTitoloDichiarante;

    //bi-directional many-to-one association to EsenredDTipiEsenzioniReddito
    @ManyToOne
    @JoinColumn(name = "\"COD_ESENZIONE\"", insertable = false, updatable = false)
    private EsenredDTipiEsenzioniReddito tipoEsenzione;

    public EsenredTEsenzioniReddito() {
    }

    public Long getIdEsenzione() {
        return this.idEsenzione;
    }

    public void setIdEsenzione(Long idEsenzione) {
        this.idEsenzione = idEsenzione;
    }

    public String getCodEsenzione() {
        return this.codEsenzione;
    }

    public void setCodEsenzione(String codEsenzione) {
        this.codEsenzione = codEsenzione;
    }

    public String getCodNazionaleAslRilascio() {
        return this.codNazionaleAslRilascio;
    }

    public void setCodNazionaleAslRilascio(String codNazionaleAslRilascio) {
        this.codNazionaleAslRilascio = codNazionaleAslRilascio;
    }

    public String getCodStato() {
        return this.codStato;
    }

    public void setCodStato(String codStato) {
        this.codStato = codStato;
    }

    public String getCodTitoloDichiarante() {
        return this.codTitoloDichiarante;
    }

    public void setCodTitoloDichiarante(String codTitoloDichiarante) {
        this.codTitoloDichiarante = codTitoloDichiarante;
    }

    public Date getDataFine() {
        return this.dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Date getDataInizio() {
        return this.dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataInsert() {
        return this.dataInsert;
    }

    public void setDataInsert(Date dataInsert) {
        this.dataInsert = dataInsert;
    }

    public Date getDataModify() {
        return this.dataModify;
    }

    public void setDataModify(Date dataModify) {
        this.dataModify = dataModify;
    }

    public Date getDataRevoca() {
        return this.dataRevoca;
    }

    public void setDataRevoca(Date dataRevoca) {
        this.dataRevoca = dataRevoca;
    }

    public Date getDataRichiesta() {
        return this.dataRichiesta;
    }

    public void setDataRichiesta(Date dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getDescMotivoRevoca() {
        return this.descMotivoRevoca;
    }

    public void setDescMotivoRevoca(String descMotivoRevoca) {
        this.descMotivoRevoca = descMotivoRevoca;
    }

    public Integer getFlagConsenso() {
        return this.flagConsenso;
    }

    public void setFlagConsenso(Integer flagConsenso) {
        this.flagConsenso = flagConsenso;
    }

    public Long getIdCittadinoBeneficiario() {
        return this.idCittadinoBeneficiario;
    }

    public void setIdCittadinoBeneficiario(Long idCittadinoBeneficiario) {
        this.idCittadinoBeneficiario = idCittadinoBeneficiario;
    }

    public Long getIdCittadinoDichiarante() {
        return this.idCittadinoDichiarante;
    }

    public void setIdCittadinoDichiarante(Long idCittadinoDichiarante) {
        this.idCittadinoDichiarante = idCittadinoDichiarante;
    }

    public Long getIdOperatoreRichiesta() {
        return this.idOperatoreRichiesta;
    }

    public void setIdOperatoreRichiesta(Long idOperatoreRichiesta) {
        this.idOperatoreRichiesta = idOperatoreRichiesta;
    }

    public Long getIdUserInsert() {
        return this.idUserInsert;
    }

    public void setIdUserInsert(Long idUserInsert) {
        this.idUserInsert = idUserInsert;
    }

    public Long getIdUserModify() {
        return this.idUserModify;
    }

    public void setIdUserModify(Long idUserModify) {
        this.idUserModify = idUserModify;
    }

    public Long getNumProtocolloSogei() {
        return this.numProtocolloSogei;
    }

    public void setNumProtocolloSogei(Long numProtocolloSogei) {
        this.numProtocolloSogei = numProtocolloSogei;
    }

    public EsenredCTitoloDichiarante getEsenredCTitoloDichiarante() {
        return this.esenredCTitoloDichiarante;
    }

    public void setEsenredCTitoloDichiarante(EsenredCTitoloDichiarante esenredCTitoloDichiarante) {
        this.esenredCTitoloDichiarante = esenredCTitoloDichiarante;
    }

    public EsenredDTipiEsenzioniReddito getTipoEsenzione() {
        return tipoEsenzione;
    }

    public void setTipoEsenzione(EsenredDTipiEsenzioniReddito tipoEsenzione) {
        this.tipoEsenzione = tipoEsenzione;
    }
}