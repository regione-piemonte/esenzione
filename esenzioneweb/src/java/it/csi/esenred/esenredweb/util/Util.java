/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import it.csi.esenred.esenredweb.business.entity.EsenredCMessaggi;
import it.csi.esenred.esenpatweb.dto.DataInizioFine;

public class Util {
	
	public static String composeMessage(EsenredCMessaggi messaggio, String dettaglio) {
		String out = messaggio.getTesto();
		if (Checker.isValorizzato(dettaglio)) {
			if (out.contains("#R"))
				out = out.replaceAll("#R", dettaglio);
			else
				out = out + " " + dettaglio;
		}
		return out;
	}

	public static  <T> T getSingleResult(TypedQuery<T> query) {
		List<T> results = query.getResultList();
		if (!results.isEmpty())
			return (T) results.get(0);
		else
			return null;
	}

	public static DataInizioFine getDataInizioFine(String ggMM) {
		DataInizioFine output = new DataInizioFine();
			String today = Converter.getData(new Date());
			output.setDataRichiesta(today);
			output.setInizioValidita(today);
			output.setFineValidita(getFineValidita(today, ggMM));
			
			return output;
	}
	
	private static String getFineValidita(String data, String ggMM) {

		String annoCorrente = Converter.getAnno(new Date());
		String ggmmaaaa = ggMM + "/" + annoCorrente;
		Date scadCandidate = Converter.getData(ggmmaaaa);
		Date today = Converter.getData(data);
		//if (scadCandidate.compareTo(today) <= 0) {
			// torna scadCandidate+1anno
			scadCandidate = Converter.aggiungiAnnoAData(scadCandidate, 1);
			return Converter.getData(scadCandidate);
	//	} else {
	//		return ggmmaaaa;
	//	}
	}
	
	public static boolean getFineValiditaAnno(String data) {

		Date today = new Date();
		Date scadCandidate = Converter.getData(data);
		scadCandidate = Converter.aggiungiAnnoAData(scadCandidate, 1);
		if (scadCandidate.compareTo(today) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Restituisce gli anni di differenza tra le due date in input
	 */
	public static long getAnniDifferenza(Date dataInizioDate, Date dataFineDate) {
		long periodo = dataFineDate.getTime() - dataInizioDate.getTime();
		return (periodo / (24 * 60 * 60 * 1000)) / 365;
	}
	
	public static File createTempFile(byte[] bytes) throws IOException {
		File tempFile = File.createTempFile("filename"+System.currentTimeMillis(), null);
		tempFile.deleteOnExit();

		FileOutputStream fos = new FileOutputStream(tempFile);
		fos.write(bytes);
		fos.close();
		return tempFile;
	}
	
	public static String getTagFromXml(String xml, String tag)  {
		DocumentBuilderFactory dbfaFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = dbfaFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(new InputSource(new StringReader(xml)));
			return doc.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}