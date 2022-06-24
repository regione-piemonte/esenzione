/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenpatweb.business.db;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
//import org.hibernate.type.StringType;

public class EsenpatPostgreSQLDialect extends PostgreSQL9Dialect {
	
	public EsenpatPostgreSQLDialect() {
		super();
		registerFunction("decryptpraticaesenzione", new StandardSQLFunction("esenred_dev.decryptpraticaesenzione", StandardBasicTypes.STRING));
		registerFunction("encryptpraticaesenzione", new StandardSQLFunction("esenred_dev.encryptpraticaesenzione", StandardBasicTypes.STRING));
	}
}
