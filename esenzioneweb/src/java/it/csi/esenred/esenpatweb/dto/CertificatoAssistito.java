/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;


import org.codehaus.jackson.annotate.*;
import org.jboss.resteasy.util.DateUtil;

import it.csi.esenred.esenredweb.business.entity.EsenzioneTDocumento;
import it.csi.esenred.esenredweb.business.entity.EsenzioneTMetadatiDocumento;
import it.csi.esenred.esenredweb.util.Constants;
import it.csi.esenred.esenredweb.util.Converter;

@JsonPropertyOrder({ "sk_documento", "diagnosi","cod_diagnosi", "codice_esenzione", 
	"data_inserimento", "medico_firmatario","medico_redattore","desc_gruppo","desc_esenzione","beneficiario","numeroTotaleElementi" })
public class CertificatoAssistito {
  @JsonProperty("sk_documento")
  private String skdocumento;	
  @JsonProperty("diagnosi")
  private String diagnosi;
  @JsonProperty("cod_diagnosi")
  private String coddiagnosi;
  @JsonProperty("codice_esenzione")
  private String codiceEsenzione;
  @JsonProperty("data_inserimento")
  private String dataInserimento;
  @JsonProperty("medico_firmatario")
  private String medicoFirmatario;
  @JsonProperty("medico_redattore")
  private String medicoRedattore;
  @JsonProperty("desc_gruppo")
  private String descGruppo;
  @JsonProperty("desc_esenzione")
  private String descEsenzione;
  @JsonProperty("beneficiario")
  private Beneficiario beneficiario;
  @JsonProperty("numeroTotaleElementi")
  private int numeroTotaleElementi;

  public CertificatoAssistito(EsenzioneTDocumento doc) {
	    if (doc.getEsenzioneDDiagnosi() != null) {
	      this.diagnosi = doc.getEsenzioneDDiagnosi().getDescDiagnosi();
	      this.codiceEsenzione = doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getCodEsenzione();
	      this.skdocumento = doc.getSkDocumento().toString();
	      Cittadino cit = new Cittadino();
	      cit.setCodFiscale(doc.getEsenzioneTCittadino().getCodiceFiscale());
	      cit.setCognome(doc.getEsenzioneTCittadino().getCognome());
	      cit.setNome(doc.getEsenzioneTCittadino().getNome());
	      this.beneficiario = new Beneficiario(cit);
	      this.descGruppo = doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getEsenzioneDGruppoEsenzioni().getDescGruppo();
	      this.descEsenzione = doc.getEsenzioneDDiagnosi().getEsenzioneDEsenzione().getDescEsenzione();
	      this.coddiagnosi = doc.getEsenzioneDDiagnosi().getCodDiagnosi();
	    }
	    if (doc.getDatCreazione() != null) {
			this.dataInserimento = Converter.getStringDataFromTimeStamp(doc.getDatCreazione(),
					Constants.DATE_FORMAT_ITALIAN);
	    }

	    if (doc.getEsenzioneTMetadatiDocumento() != null && doc.getEsenzioneTMetadatiDocumento().getMedicoValidatore() != null) {
	      this.medicoFirmatario = doc.getEsenzioneTMetadatiDocumento().getMedicoValidatore();
	      this.medicoRedattore = doc.getEsenzioneTMetadatiDocumento().getMedicoRedattore();
	    }
	  }
  public CertificatoAssistito(int numeropagine) {
	  this.numeroTotaleElementi = numeropagine;
  }
  
  public String getDiagnosi() {
    return diagnosi;
  }

  public void setDiagnosi(String diagnosi) {
    this.diagnosi = diagnosi;
  }

  public String getCodiceEsenzione() {
    return codiceEsenzione;
  }

  public void setCodiceEsenzione(String codiceEsenzione) {
    this.codiceEsenzione = codiceEsenzione;
  }

  public String getDataInserimento() {
    return dataInserimento;
  }

  public void setDataInserimento(String dataInserimento) {
    this.dataInserimento = dataInserimento;
  }

  public String getMedicoFirmatario() {
    return medicoFirmatario;
  }

  public void setMedicoFirmatario(String medicoFirmatario) {
    this.medicoFirmatario = medicoFirmatario;
  }
  public String getSkdocumento() {
		return skdocumento;
	}
	
	public void setSkdocumento(String skdocumento) {
		this.skdocumento = skdocumento;
	}
	
	public String getMedicoRedattore() {
		return medicoRedattore;
	}
	
	public void setMedicoRedattore(String medicoRedattore) {
		this.medicoRedattore = medicoRedattore;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getDescGruppo() {
		return descGruppo;
	}

	public void setDescGruppo(String descGruppo) {
		this.descGruppo = descGruppo;
	}

	public String getCoddiagnosi() {
		return coddiagnosi;
	}

	public void setCoddiagnosi(String coddiagnosi) {
		this.coddiagnosi = coddiagnosi;
	}

	public String getDescEsenzione() {
		return descEsenzione;
	}

	public void setDescEsenzione(String descEsenzione) {
		this.descEsenzione = descEsenzione;
	}

	public int getNumeroTotaleElementi() {
		return numeroTotaleElementi;
	}

	public void setNumeroTotaleElementi(int numeroTotaleElementi) {
		this.numeroTotaleElementi = numeroTotaleElementi;
	}
	
}
