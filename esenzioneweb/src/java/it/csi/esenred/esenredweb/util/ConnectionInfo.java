/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.jdbc.Work;
public class ConnectionInfo implements Work {

public String dataBaseUrl;
public String dataBaseProductName;
public String driverName;
public Connection conn;

@Override
public void execute(Connection connection) throws SQLException {
    dataBaseUrl = connection.getMetaData().getURL();
    dataBaseProductName = connection.getMetaData().getDatabaseProductName();
    driverName = connection.getMetaData().getDriverName();
    conn = connection.getMetaData().getConnection();
}



public Connection getConn() {
	return conn;
}



public void setConn(Connection conn) {
	this.conn = conn;
}



public String getDataBaseProductName() {
    return dataBaseProductName;
}

public void setDataBaseProductName(String dataBaseProductName) {
    this.dataBaseProductName = dataBaseProductName;
}

public String getDataBaseUrl() {
    return dataBaseUrl;
}

public void setDataBaseUrl(String dataBaseUrl) {
    this.dataBaseUrl = dataBaseUrl;
}

public String getDriverName() {
    return driverName;
}

public void setDriverName(String driverName) {
    this.driverName = driverName;
}
}