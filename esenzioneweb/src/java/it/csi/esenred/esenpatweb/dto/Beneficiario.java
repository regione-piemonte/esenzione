/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;

import org.codehaus.jackson.annotate.*;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTCittadino;

@JsonPropertyOrder({ "nome", "cognome", "codice_fiscale", "id_aura", "data_nascita", "sesso", "comune_nascita" })
public class Beneficiario implements Comparable<Beneficiario> {

  @JsonProperty("nome")
  private String nome;

  @JsonProperty("cognome")
  private String cognome;

  @JsonProperty("codice_fiscale")
  private String codiceFiscale;

	@JsonProperty("id_aura")
	private String idAura;

	@JsonProperty("data_nascita")
	private String dataNascita;
	@JsonProperty("sesso")
	private String sesso;

	@JsonProperty("comune_nascita")
	private String comuneNascita;

 public Beneficiario() {}
	
  public Beneficiario(Cittadino cit) {
    this.codiceFiscale = cit.getCodFiscale();
    this.cognome = cit.getCognome();
    this.nome = cit.getNome();
		this.idAura = cit.getIdAura() != null ? cit.getIdAura() : null;
  }

	public Beneficiario(EsenzioneTCittadino cit) {
		this.codiceFiscale = cit.getCodiceFiscale() != null ? cit.getCodiceFiscale() : "";
		this.cognome = cit.getCognome() != null ? cit.getCognome() : "";
		this.nome = cit.getNome() != null ? cit.getNome() : "";
		this.idAura = cit.getId_aura() != null ? cit.getId_aura() : null;
	}

	public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
    result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((idAura == null) ? 0 : idAura.hashCode());
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
    Beneficiario other = (Beneficiario) obj;
    if (codiceFiscale == null) {
      if (other.codiceFiscale != null)
        return false;
    } else if (!codiceFiscale.equals(other.codiceFiscale))
      return false;
    if (cognome == null) {
      if (other.cognome != null)
        return false;
    } else if (!cognome.equals(other.cognome))
      return false;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    if (idAura == null) {
        if (other.idAura != null)
          return false;
      } else if (!idAura.equals(other.idAura))
        return false;
    return true;
    
  }


  @Override
  public int compareTo(Beneficiario o) {
    if (this.equals(o))
      return 0;
    return 1;
  }

}
