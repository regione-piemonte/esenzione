/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.infocert.proxysign;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.esenred.esenredweb.business.model.impl.IntegrationClientImpl;
import it.csi.esenred.esenredweb.util.LogUtil;
import it.csi.esenred.esenredweb.util.RestUtils;
import it.csi.esenred.esenredweb.util.Util;

public class ProxySignStub {

	private static final String CHARSET = "UTF-8";

	private static final String CRLF = "\r\n";
	
	LogUtil log = new LogUtil(this.getClass(), LogUtil.APPLICATION_CODE, null);

	public Response firmaDocumento( byte[] byteStream, String pin, String alias) throws MalformedURLException, IOException {

		log.info("firmaDocumento", " BEGIN");
		
		String url = IntegrationClientImpl.getInstance().getFirmaURL();
		url = url+"/auto/sign/pades/"+ alias;

		log.info("firmaDocumento", " url: " + url);
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		final String boundary = generateBoundary();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Cache-Control", "no-cache");
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		log.info("firmaDocumento", " Connection set ok! ");
		OutputStream directOutput = connection.getOutputStream();
		
		log.debug("firmaDocumento", ".. dopo connection.getOutputStream() ");
		PrintWriter body = new PrintWriter(new OutputStreamWriter(directOutput, CHARSET), true);

		body.append(CRLF);
		addSimpleFormData("alias", alias, body, boundary);
		addSimpleFormData("pin", pin, body, boundary);
		addFileData("contentToSign-0", "fileName", byteStream, body, directOutput, boundary);
		addCloseDelimiter(body, boundary);

		log.debug("firmaDocumento", ".. prima di connection.getResponseCode() ");
		int responseCode = connection.getResponseCode();
		
		log.debug("firmaDocumento", ".. dopo connection.getResponseCode() ");
		InputStream in;
		String responseMessage;
		String status;
		if (responseCode == Status.OK.getStatusCode()) {
			log.debug("firmaDocumento", " Response code: " + Status.OK.getStatusCode());
			in = connection.getInputStream();
			responseMessage = connection.getResponseMessage();
			status = "ok";
		} else {
			status = "ko";
			in = connection.getErrorStream();
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			String xml = result.toString();
			responseMessage = Util.getTagFromXml(xml, "proxysign-error-description");
		}

		log.info("firmaDocumento", " status:" + status);
		log.info("firmaDocumento", " code:" + responseCode);
		log.info("firmaDocumento", " message:" + responseMessage);

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("status", status);
		res.put("code", responseCode);
		res.put("data", in);
		res.put("message", responseMessage);
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		log.info("firmaDocumento", " END");
		return corsedResponse.status(responseCode).entity(res).build();
	}

	private static void addSimpleFormData(String paramName, String wert, PrintWriter body, final String boundary) {

		//body.append(boundary).append(CRLF);
		body.append("--").append(boundary).append(CRLF);
		body.append("Content-Disposition: form-data; name=\"" + paramName + "\"").append(CRLF);
		body.append("Content-Type: text/plain; charset=" + CHARSET).append(CRLF);
		body.append(CRLF);
		body.append(wert).append(CRLF);
		body.flush();
	}

	private static void addFileData(String paramName, String filename, byte[] byteStream, PrintWriter body, OutputStream directOutput, final String boundary) throws IOException {

		//body.append(boundary).append(CRLF);
		body.append("--").append(boundary).append(CRLF);
		body.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + filename + "\"").append(CRLF);
		body.append("Content-Type: application/pdf").append(CRLF);
		body.append("Content-Transfer-Encoding: binary").append(CRLF);
		body.append(CRLF);
		body.flush();

		directOutput.write(byteStream);
		directOutput.flush();

		body.append(CRLF);
		body.flush();
	}

	private static void addCloseDelimiter(PrintWriter body, final String boundary) {

		body.append(boundary).append("--").append(CRLF);
		body.flush();
	}
	
	private String generateBoundary() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < 15; i++) {
			buffer.append("-");
		}
		buffer.append(Long.toHexString(System.currentTimeMillis()));
		return buffer.toString();
	}
}