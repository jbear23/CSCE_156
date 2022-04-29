package com.cinco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

	private String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://cse.unl.edu/jbearden?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
	private String user = "jbearden";
	private String password = "xa3:aZ";
	private Connection conn;
	
	public List<Invoice> getAllInvoices() {
		List<Invoice> invoices = new ArrayList<>();
		
		//load the JDBC
		try {
			Class.forName(this.DRIVER_CLASS).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		//create connection to database
		
		//prepare/create the queries
		String q = "SELECT ";
		//prepared query
		PreparedStatement ps = null;
		//set of results from database
		ResultSet rs = null;
		
		try {
			//prepare the statements
			ps = this.conn.prepareStatement(q);
			rs = ps.executeQuery(q);
			while(rs.next()) {
				int invoiceId = rs.getInt("invoiceId");
				//get all the information!!!!!!!
				String s = null;
				Invoice invoice = new Invoice(null, null, null, null);
				invoices.add(invoice);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		return invoices;
	}
	
	public void connectionStart() {
		Connection conn = null;
		try {
			this.conn = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
