/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.filter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class XSRFProtectionFilter implements Filter {

	//	final static Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	/**
	 * nome dell'header XSRF che la componente client deve inserire ad ogni richiesta rest
	 */
	private static final String XSRF_HEADER_NAME = "X-XSRF-TOKEN";
	/*
	 * nome del cookie XSRF
	 */
	private static final String XSRF_COOKIE_NAME = "XSRF-TOKEN";
	/**
	 * nome dell'attributo di sessione che mantiene il token XSRF
	 */
	private static final String XSRF_INTERNAL_TOKEN_NAME = "XSRF_SESSION_TOKEN";
	@Override
	public void destroy() {
		// nothing to do
	}


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		// get current session or create a new one
		HttpSession session = hreq.getSession();
		if (!isDisabled) {
			if (!isRestRequest(hreq)) {
				try {
					String newToken = createNewXSRFToken(session); addXSRFCookie(hresp, newToken);
				} catch (NoSuchAlgorithmException e) {
					throw new ServletException(e);
				}
			} else { // rest request: token must be valid
				if (isXSRFSessionAlive(session)) {
					if (validXSRFCookieAndHeader(hreq, session)) {
						//OK
					} else {
						// ERRORE!!!
						throw new ServletException("Invalid XSRF HEADER");
					}
				} else {
					throw new ServletException("XSRF TOKEN not already initialized");
				}
			}
		}
		setNoCache((HttpServletResponse)resp);
		chain.doFilter(req, resp);
	}


	
	private void setNoCache(HttpServletResponse response){
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

	}
	
	private boolean isRestRequest(HttpServletRequest hreq) {
		return hreq.getRequestURI() != null && hreq.getRequestURI().contains("restfacade");
	}


	
	private void addXSRFCookie(HttpServletResponse hresp, String token) {
		Cookie c = new Cookie(XSRF_COOKIE_NAME, token);
		c.setPath("/");
		hresp.addCookie(c);
	}


	/**
	 * crea un nuovo token XSRF
	 * @param session
	 * @return il token creato
	 * @throws NoSuchAlgorithmException
	 */
	private String createNewXSRFToken(HttpSession session) throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		String newToken = "" + random.nextLong() + "" + random.nextLong();
		session.setAttribute(XSRF_INTERNAL_TOKEN_NAME, newToken);
		return newToken;
	}
	/**
	 *
	 * @param session
	 * @return il token XSRF presente in sessione
	 */
	private String getActualActiveXSRFToken(HttpSession session) {
		return (String) session.getAttribute(XSRF_INTERNAL_TOKEN_NAME);
	}
	/**
	 * verifica se la richiesta possiede una coppia cookie/header XSRF validi
	 * e coincidenti con quanto memorizzato in sessione (se presente)
	 * @param hreq la request
	 * @param session la sessione servlet
	 * @return il risultato della verifica
	 */
	private boolean validXSRFCookieAndHeader(HttpServletRequest hreq, HttpSession session) {
		String actualActiveToken = getActualActiveXSRFToken(session);
		String actualRequestHeader = getActualXSRFHeader(hreq);
		String actualRequestCookie = getActualXSRFCookie(hreq);
		return actualRequestHeader != null && actualActiveToken != null
				&& actualActiveToken.equals(actualRequestHeader)
				&& actualActiveToken.contentEquals(actualRequestCookie);
	}
	/**
	 *
	 * @param hreq
	 * @return l'header XSRF se presente nella request
	 */
	private String getActualXSRFHeader(HttpServletRequest hreq) {
		return (String) hreq.getHeader(XSRF_HEADER_NAME);
	}

	/**
	 *
	 * @param hreq la request
	 * @return il valore del cookie XSRF, se presente nella request;
	 * la stringa vuota se invece non presente.
	 */
	private String getActualXSRFCookie(HttpServletRequest hreq) {
		Cookie[] cookies = hreq.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().contentEquals(XSRF_COOKIE_NAME)) {
				return cookies[i].getValue();
			}
		}
		// if not found...
		return "";
	}
	/**
	 *
	 * @param session
	 * @return true se in sessione e' presente un token valido
	 */
	private boolean isXSRFSessionAlive(HttpSession session) {
		return getActualActiveXSRFToken(session) != null;
	}
	/**
	 * di default il filter abilita le verifiche
	 */
	private boolean isDisabled = false;
	/**
	 * nome del parametro di inizializzazione che serve per disabilitare
	 * il meccanismo. Serve negli scenari di sviluppo in cui la parte client
	 * non e' deployata nell'ear dei servizi
	 */
	private static String DISABLED_PARAM_NAME = "disabled";

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		String sDisabled = cfg.getInitParameter(DISABLED_PARAM_NAME);
		if ("true".equals(sDisabled)) {
			isDisabled = true;
		}
	}
}