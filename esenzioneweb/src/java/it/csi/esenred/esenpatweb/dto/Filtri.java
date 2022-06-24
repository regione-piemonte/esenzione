/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.dto;
import java.lang.reflect.Field;

public class Filtri {
	private String eq;
	private String ne;	
	private String lt;	
	private String lte;	
	private String gt;	
	private String gte;	
	private String[] in;
	private String[] nin;
	private String c;	
	private String ci;	
	private String s;	
	private String si;	
	private String e;	
	private String ei;
	
	
	
	public Filtri(String eq, String ne, String lt, String lte, String gt, String gte, String[] in, String[] nin, String c,
			String ci, String s, String si, String e, String ei) {
		this.eq = eq;
		this.ne = ne;
		this.lt = lt;
		this.lte = lte;
		this.gt = gt;
		this.gte = gte;
		this.in = in;
		this.nin = nin;
		this.c = c;
		this.ci = ci;
		this.s = s;
		this.si = si;
		this.e = e;
		this.ei = ei;
	}
	
	public Filtri() {
		this.eq = null;
		this.ne = null;
		this.lt = null;
		this.lte = null;
		this.gt = null;
		this.gte = null;
		this.in = null;
		this.nin = null;
		this.c = null;
		this.ci = null;
		this.s = null;
		this.si = null;
		this.e = null;
		this.ei = null;
	}
	
	public void setFiltro(Field field, String value) throws IllegalArgumentException, IllegalAccessException {
			field.set(this, value);		
	}
	
	public void setFiltro(Field field, String[] value) throws IllegalArgumentException, IllegalAccessException {
		field.set(this, value);		
}
	
	public String getEq() {
		return eq;
	}
	public void setEq(String eq) {
		this.eq = eq;
	}
	public String getNe() {
		return ne;
	}
	public void setNe(String ne) {
		this.ne = ne;
	}
	public String getLt() {
		return lt;
	}
	public void setLt(String lt) {
		this.lt = lt;
	}
	public String getLte() {
		return lte;
	}
	public void setLte(String lte) {
		this.lte = lte;
	}
	public String getGt() {
		return gt;
	}
	public void setGt(String gt) {
		this.gt = gt;
	}
	public String getGte() {
		return gte;
	}
	public void setGte(String gte) {
		this.gte = gte;
	}
	public String[] getIn() {
		return in;
	}
	public void setIn(String[] in) {
		this.in = in;
	}
	public String[] getNin() {
		return nin;
	}
	public void setNin(String[] nin) {
		this.nin = nin;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public String getEi() {
		return ei;
	}
	public void setEi(String ei) {
		this.ei = ei;
	}

	@Override
	public String toString() {
		return "Filtri [eq=" + eq + ", ne=" + ne + ", lt=" + lt + ", lte=" + lte + ", gt=" + gt + ", gte=" + gte
				+ ", in=" + in.toString() + ", nin=" + nin.toString() + ", c=" + c + ", ci=" + ci + ", s=" + s + ", si=" + si + ", e=" + e
				+ ", ei=" + ei + "]";
	}	
		
}

