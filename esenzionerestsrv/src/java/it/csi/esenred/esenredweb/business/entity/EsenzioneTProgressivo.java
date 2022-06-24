/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "ESENZIONE_T_PROGRESSIVO" database table.
 * 
 */
@Entity
@Table(name="\"ESENZIONE_T_PROGRESSIVO\"")
@NamedQueries({ 
  @NamedQuery(name="EsenzioneTProgressivo.findAll", query="SELECT e FROM EsenzioneTProgressivo e"),
  @NamedQuery(name = "EsenzioneTProgressivo.findProgressivoByAziendaAndCodiceTipo", query = "SELECT e FROM EsenzioneTProgressivo e WHERE e.idAzienda = :idAzienda AND e.codTipoProgressivo = :codiceTipo") 
})
public class EsenzioneTProgressivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID_PROGRESSIVO\"", unique=true, nullable=false)
	private Integer idProgressivo;

	@Column(name="\"COD_TIPO_PROGRESSIVO\"", nullable=false, length=2147483647)
	private String codTipoProgressivo;

	@Column(name="\"ID_AZIENDA\"", length=6)
	private String idAzienda;

	@Column(name="\"NUM_VALORE_DISPONIBILE\"", nullable=false)
	private Integer numValoreDisponibile;
	
  @Column(name="\"ANNO\"", nullable=false)
	private Integer anno;

	//bi-directional many-to-one association to EsenredDAziendasanitaria
	@ManyToOne
	@JoinColumn(name = "\"ID_AZIENDA\"", insertable = false, updatable = false)
	private EsenredDAziendasanitaria esenredDAziendasanitaria;

	public EsenzioneTProgressivo() {
	}

	public Integer getIdProgressivo() {
		return this.idProgressivo;
	}

	public void setIdProgressivo(Integer idProgressivo) {
		this.idProgressivo = idProgressivo;
	}

	public String getCodTipoProgressivo() {
		return this.codTipoProgressivo;
	}

	public void setCodTipoProgressivo(String codTipoProgressivo) {
		this.codTipoProgressivo = codTipoProgressivo;
	}

	public String getIdAzienda() {
		return this.idAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.idAzienda = idAzienda;
	}

	public Integer getNumValoreDisponibile() {
		return this.numValoreDisponibile;
	}

	public void setNumValoreDisponibile(Integer numValoreDisponibile) {
		this.numValoreDisponibile = numValoreDisponibile;
	}
	
	public Integer getAnno() {
    return anno;
  }

  public void setAnno(Integer anno) {
    this.anno = anno;
  }

  public EsenredDAziendasanitaria getEsenredDAziendasanitaria() {
		return this.esenredDAziendasanitaria;
	}

	public void setEsenredDAziendasanitaria(EsenredDAziendasanitaria esenredDAziendasanitaria) {
		this.esenredDAziendasanitaria = esenredDAziendasanitaria;
	}

}