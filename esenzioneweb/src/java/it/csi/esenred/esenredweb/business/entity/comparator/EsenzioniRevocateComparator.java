/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.entity.comparator;

import java.util.Comparator;

import it.csi.esenred.esenredweb.business.entity.EsenredTEsenzioniReddito;

public class EsenzioniRevocateComparator implements Comparator<EsenredTEsenzioniReddito>{

	@Override
	public int compare(EsenredTEsenzioniReddito o1, EsenredTEsenzioniReddito o2) {
		if(o1.getDataRevoca()==null && o2.getDataRevoca()==null) return 0;
		else if(o1.getDataRevoca()==null) return 1;
		else if(o2.getDataRevoca()==null) return -1;
		else return o2.getDataRevoca().compareTo(o1.getDataRevoca());
	}

}
