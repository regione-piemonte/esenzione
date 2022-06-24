/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import it.csi.esenred.esenpatweb.dto.UserInfo;
import it.csi.esenred.esenredweb.business.SpringApplicationContext;
import it.csi.esenred.esenredweb.business.entity.EsenredCParametri;
import it.csi.esenred.esenredweb.business.model.interfaces.ParametroIf;
import it.csi.iride2.policy.entity.Identita;
import it.csi.iride2.policy.exceptions.MalformedIdTokenException;

public class IrideIdAdapterFilter implements Filter {
	public static final String IRIDE_ID_SESSIONATTR = "iride2_id";
	public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";
	public static final String AUTH_ID_MARKER2 = "shib-iride-identitadigitale";
	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";
	public static final String USERINFO_COD_FISCALE = "codFiscale";
	/** */
	//	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");

	private static final String DEVMODE_INIT_PARAM = "devmode";

	private boolean devmode = false;

	public void init(FilterConfig fc) throws ServletException {
		String sDevmode = fc.getInitParameter(DEVMODE_INIT_PARAM);
		if ("true".equals(sDevmode)) {
			devmode = true;
		} else {
			devmode = false;
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fchn)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;

		if (hreq.getSession().getAttribute(IRIDE_ID_SESSIONATTR) == null) {
			String marker = getToken(hreq);
			if (marker != null) {
				try {
					Identita identita = new Identita(normalizeToken(marker));
					
					hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
					UserInfo userInfo = new UserInfo();
					userInfo.setNome(identita.getNome());
					userInfo.setCognome(identita.getCognome());
					userInfo.setCodFisc(getCodFiscaleDebug(identita.getCodFiscale())); 
					hreq.getSession().setAttribute(USERINFO_SESSIONATTR, userInfo);
				} catch (MalformedIdTokenException e) {
					e.printStackTrace();
					
				}
			} else {

				if (mustCheckPage(hreq.getRequestURI())) {

					throw new ServletException("Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
				}
			}
		}
		fchn.doFilter(req, resp);
	}

	private String getCodFiscaleDebug(String codFiscaleAuth) {
		boolean isDebugValue = false;
		ParametroIf parametroIf = (ParametroIf) SpringApplicationContext.getBean("parametro");
		List<EsenredCParametri> isDebug = parametroIf.getParametri("DEBUG");
		if (!isDebug.isEmpty()) {
			isDebugValue = "true".equalsIgnoreCase(isDebug.get(0).getValore());
			if (isDebugValue) {
				List<EsenredCParametri> cfDebug = parametroIf.getParametri("CF_DEBUG");
				if (!cfDebug.isEmpty())
					return cfDebug.get(0).getValore();
				else
					return codFiscaleAuth;
			} else
				return codFiscaleAuth;
		} else
			return codFiscaleAuth;
	}

	private boolean mustCheckPage(String requestURI) {
		return true;
	}
	public void destroy() {}

	public String getToken(HttpServletRequest httpreq) {

		
		String marker = (String) httpreq.getHeader(AUTH_ID_MARKER);
		if (marker == null) marker =  (String) httpreq.getHeader(AUTH_ID_MARKER2);
		if (marker == null && devmode) {
			return getTokenDevMode(httpreq);
		} else {
			return marker;
		}
	}

	private String getTokenDevMode(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getParameter(AUTH_ID_MARKER);
		if (marker == null) marker =  (String) httpreq.getParameter(AUTH_ID_MARKER2);
		return marker;
	}
	private String normalizeToken(String token) {
		return token;
	}
}