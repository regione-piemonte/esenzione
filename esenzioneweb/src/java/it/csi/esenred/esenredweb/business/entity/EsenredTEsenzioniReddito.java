/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "ESENRED_T_ESENZIONI_REDDITO" database table.
 */
@Entity
@Table(name = "\"ESENRED_T_ESENZIONI_REDDITO\"")
@SequenceGenerator(name="\"ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq\"",  sequenceName="\"ESENRED_T_ESENZIONI_REDDITO_ID_ESENZIONE_seq\"", initialValue=1, allocationSize=1)
@NamedQueries({
        @NamedQuery(name = "EsenredTEsenzioniReddito.findAll", query = "SELECT e FROM EsenredTEsenzioniReddito e"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzione", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and e.codEsenzione = :codEsenzione and e.dataInizio = :dataInizio"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzioneValidaLavorazione", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and e.codStato = 'L' and e.dataFine > current_date"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzioneLavorazione", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and e.codEsenzione = :codEsenzione and e.codStato = 'L' "),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzioneNonValida", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAuraBen and e.idCittadinoDichiarante = :idAuraDic and e.idCittadinoTitolare = :idAuraTit and e.codEsenzione = :codEsenzione and e.codTitoloDichiarante = :titDich  and e.dataInizio = :dataInizio and e.dataFine = :dataFine and e.codNazionaleAslRilascio = :codAsl and e.codStato = 'N'"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzioneRevocata", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and e.codStato = 'R' and e.codEsenzione = :codEsenzione and e.dataRevoca < e.dataFine order by e.dataInizio desc"),
        @NamedQuery(name = "EsenredTEsenzioniReddito.findEsenzioneProtocollo", query = "SELECT e FROM EsenredTEsenzioniReddito e where e.idCittadinoBeneficiario = :idAura and e.codStato = 'V' and e.codEsenzione = :codEsenzione and e.numProtocolloSogei= :protocollo")
})
public class EsenredTEsenzioniReddito implements Serializable, Comparable<EsenredTEsenzioniReddito> {
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
    
    @Column(name = "\"ID_CITTADINO_TITOLARE\"")
    private Long idCittadinoTitolare;
    
    @Column(name = "\"CF_DICHIARANTE_FR\"")
    private String cfDichiarantefr;
    
    @Column(name = "\"CF_TITOLARE_FR\"")
    private String cfTitolarefr;

    @Column(name = "\"ID_OPERATORE_RICHIESTA\"")
    private Long idOperatoreRichiesta;

    @Column(name = "\"ID_USER_INSERT\"")
    private Long idUserInsert;

    @Column(name = "\"ID_USER_MODIFY\"")
    private Long idUserModify;

    @Column(name = "\"NUM_PROTOCOLLO_SOGEI\"")
    private Long numProtocolloSogei;
    
    @Column(name = "\"NOTA\"")
    private String nota;
   
    @Column(name = "\"MOTIVO_NONVALIDA\"")
    private String motivo_nonvalida;
    
    @Column(name = "\"ATTESTATO\"")
    private String attestato;
    
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
    
    public String getNota() {
        return this.nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
    
    public Long getIdCittadinoTitolare() {
        return this.idCittadinoTitolare;
    }

    public void setIdCittadinoTitolare(Long idCittadinoTitolare) {
        this.idCittadinoTitolare = idCittadinoTitolare;
    }

    public Long getIdCittadinoDichiarante() {
        return this.idCittadinoDichiarante;
    }

    public void setIdCittadinoDichiarante(Long idCittadinoDichiarante) {
        this.idCittadinoDichiarante = idCittadinoDichiarante;
    }

//	public Long getIdCittadinoRevoca() {
//		return this.idCittadinoRevoca;
//	}
//
//	public void setIdCittadinoRevoca(Long idCittadinoRevoca) {
//		this.idCittadinoRevoca = idCittadinoRevoca;
//	}
//
//	public Long getIdOperatoreRevoca() {
//		return this.idOperatoreRevoca;
//	}
//
//	public void setIdOperatoreRevoca(Long idOperatoreRevoca) {
//		this.idOperatoreRevoca = idOperatoreRevoca;
//	}

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

	public String getMotivo_nonvalida() {
		return motivo_nonvalida;
	}

	public void setMotivo_nonvalida(String motivo_nonvalida) {
		this.motivo_nonvalida = motivo_nonvalida;
	}

	
	public String getCfDichiarantefr() {
		return cfDichiarantefr;
	}

	public void setCfDichiarantefr(String cfDichiarantefr) {
		this.cfDichiarantefr = cfDichiarantefr;
	}

	public String getCfTitolarefr() {
		return cfTitolarefr;
	}

	public void setCfTitolarefr(String cfTitolarefr) {
		this.cfTitolarefr = cfTitolarefr;
	}
	
	 public String getAttestato() {
			return attestato;
		}

		public void setAttestato(String attestato) {
			this.attestato = attestato;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			if (numProtocolloSogei==null || numProtocolloSogei==0) {
			result = prime * result + ((codEsenzione == null) ? 0 : codEsenzione.hashCode());
			result = prime * result + ((dataInizio == null) ? 0 : dataInizio.hashCode());
			result = prime * result + ((idCittadinoDichiarante == null) ? 0 : idCittadinoDichiarante.hashCode());
			}
			else {
				result = prime * result + ((codEsenzione == null) ? 0 : codEsenzione.hashCode());
				result = prime * result + ((dataInizio == null) ? 0 : dataInizio.hashCode());
				result = prime * result + ((idCittadinoDichiarante == null) ? 0 : idCittadinoDichiarante.hashCode());
				result = prime * result + ((numProtocolloSogei == null) ? 0 : numProtocolloSogei.hashCode());
			}
			return result;
	}	
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EsenredTEsenzioniReddito other = (EsenredTEsenzioniReddito) obj;
		try {		
			if (other.numProtocolloSogei==null || other.numProtocolloSogei==0) 
				return codEsenzione.equals(other.codEsenzione) && dataInizio.equals(other.dataInizio) && idCittadinoBeneficiario.equals(other.idCittadinoBeneficiario);
			else
				return codEsenzione.equals(other.codEsenzione) && dataInizio.equals(other.dataInizio) && idCittadinoBeneficiario.equals(other.idCittadinoBeneficiario)
						&& numProtocolloSogei.equals(other.numProtocolloSogei);
		}catch(Exception e) {
			return false;
		}		
	}


	@Override
	public int compareTo(EsenredTEsenzioniReddito arg0) {
		try {
			if (arg0.numProtocolloSogei==null || arg0.numProtocolloSogei==0) {
				if(codEsenzione.compareTo(arg0.codEsenzione) != 0 ) return codEsenzione.compareTo(arg0.codEsenzione);
				else if (dataInizio.compareTo(arg0.dataInizio) != 0) return dataInizio.compareTo(arg0.dataInizio);
				else if (idCittadinoBeneficiario.compareTo(arg0.idCittadinoBeneficiario) != 0) return idCittadinoBeneficiario.compareTo(arg0.idCittadinoBeneficiario);
			}
			else {
			if(codEsenzione.compareTo(arg0.codEsenzione) != 0 ) return codEsenzione.compareTo(arg0.codEsenzione);
			else if (dataInizio.compareTo(arg0.dataInizio) != 0) return dataInizio.compareTo(arg0.dataInizio);
			else if (idCittadinoBeneficiario.compareTo(arg0.idCittadinoBeneficiario) != 0) return idCittadinoBeneficiario.compareTo(arg0.idCittadinoBeneficiario);
			else if (numProtocolloSogei.compareTo(arg0.numProtocolloSogei) != 0) return numProtocolloSogei.compareTo(arg0.numProtocolloSogei);
			}
		}catch(Exception e) {
			return 0;
		}
		return 0;
	}

}