package com.cinco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Invoice {

	private String InvoiceCode;
	private String CustomerCode;
	private String SalespersonCode;
	private Map<String, String> ProductMap;
	
	public Invoice(String invoiceCode,
				   String customerCode,
				   String salespersonCode,
				   String productList) {
		InvoiceCode = invoiceCode;
		CustomerCode = customerCode;
		SalespersonCode = salespersonCode;
		ProductMap = new HashMap<>();
		String[] ProductTokens = productList.split(",");
    	for(int j=0; j < ProductTokens.length; j++) {
    		String[] Tokens = ProductTokens[j].split(":");
    		if(Tokens.length==3) {
    			ProductMap.put(Tokens[0],(Tokens[1]+","+Tokens[2]));
    		}else {
    			ProductMap.put(Tokens[0],Tokens[1]);
    		}
    		//System.out.print(Tokens[0] +":"+ ProductMap.get(Tokens[0])+"\n");
    	}
	}
	
	public String getInvoiceCode() {
		return InvoiceCode;
	}
	
	public String getCustomerCode() {
		return CustomerCode;
	}
	
	public String getSalespersonCode() {
		return SalespersonCode;
	}
	
	public Map<String, String> getProductMap() {
		return ProductMap;
	}
	
	public String getProductAmountFromMap(String str) {
		return ProductMap.get(str);
	}
	
	public Cost getCostOfItem(String itemCode,
							  Map<String, Product> Catalog,
							  Map<String, Customer> Customers) {
		Cost cost;
		double price;
		double taxes;
		double fees;
		//Confusing looking code, all this is doing is checking what Type the product is, 
		//pulling info from the Product Catalog, and local Product invoice map and putting them into a Cost class
		//Most of the mess is caused by the need to explicitly cast a lot of the data.
		if(Catalog.get(itemCode).getType().equals("E")) {
			
			price = (Double.valueOf(((Equipment) Catalog.get(itemCode)).getPricePerUnit()) * Double.valueOf(this.ProductMap.get(itemCode)));
			taxes = price * .07;
			fees = 0;
			
		} else if (Catalog.get(itemCode).getType().equals("C")) {
			price = (Double.valueOf(((Consultation) Catalog.get(itemCode)).getHourlyFee()) * Double.valueOf(this.ProductMap.get(itemCode)));
			taxes = price * .0425;
			fees = 150;
		} else if (Catalog.get(itemCode).getType().equals("L")) {
			//This was the most frustrating part of the whole assignment, dates where created specifically to torture programmers and you cannot change my mind
			DateTimeFormatter formatter;
			String[] Dates = this.ProductMap.get(itemCode).split(",");
			if (Dates[0].charAt(5)!='-' && Dates[0].charAt(7)!='-') {
				formatter = DateTimeFormatter.ofPattern("yyyy-M-d");	
			} else if (Dates[0].charAt(5)!='-') {
				formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
			} else if (Dates[0].charAt(7)!='-') {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			} else {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
			}
			
			formatter = formatter.withLocale(Locale.US);
			LocalDate date1 = LocalDate.parse(Dates[0], formatter);
			if (Dates[1].charAt(5)!='-' && Dates[0].charAt(7)!='-') {
				formatter = DateTimeFormatter.ofPattern("yyyy-M-d");	
			} else if (Dates[1].charAt(5)!='-') {
				formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
			} else if (Dates[1].charAt(7)!='-') {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			} else {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
			}
			
			formatter = formatter.withLocale(Locale.US);
			LocalDate date2 = LocalDate.parse(Dates[1], formatter);
			Double Length = (((double) (ChronoUnit.DAYS.between(date1, date2))) / 365);
			//System.out.print(Length);
			
			price = (Double.valueOf(((License) Catalog.get(itemCode)).getAnnualLicenseFee()) * Length);
			taxes = price * .0425;
			fees = Double.valueOf(((License) Catalog.get(itemCode)).getServiceFee());
		} else {
			//This should never happen with properly formatted data, but better safe then sorry
			price = .5;
			taxes = .5;
			fees = .5;
		}
		
		//If is government
		if(Customers.get(this.CustomerCode).getType().equals("G")){
			taxes = 0;
		}
		
		cost = new Cost(price, taxes, fees, itemCode);
		return cost;
	}
	
	public CostTotal getTotalInvoiceCost(Map<String, Product>Catalog, Map<String, Customer>Customers){
		Double Fee = (double) 0;
		Cost[] CostTotalArray = new Cost[ProductMap.size()];
		int i = 0;
		for (String key: ProductMap.keySet()) {
			CostTotalArray[i]=this.getCostOfItem(key, Catalog, Customers);
			i++;
		}
		
		if(Customers.get(this.CustomerCode).getType().equals("G")){
			Fee = (double) 125;
		}
		
		CostTotal TotalCost = new CostTotal(CostTotalArray, Fee);
		return TotalCost;
	}
	
	public static List<Invoice> getAllInvoices() {
		List<Invoice> invoices = new ArrayList<>();
		
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://cse.unl.edu/jbearden?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
		String user = "jbearden";
		String password = "xa3:aZ";
		
		//load the JDBC
		try {
			Class.forName(DRIVER_CLASS).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		//create connection to database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		//prepare/create the queries
		String q = "SELECT ";
		//prepared query
		PreparedStatement ps = null;
		//set of results from database
		ResultSet rs = null;
		
		try {
			//prepare the statements
			ps = conn.prepareStatement(q);
			rs = ps.executeQuery(q);
			while(rs.next()) {
				int invoiceId = rs.getInt("invoiceId");
				//get all the information!!!!!!!
				//at some point, create all the objects!!!!
				String s = null;
				Invoice invoice = new Invoice(null, null, null, null);
				invoices.add(invoice);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		return invoices;
	}
	
}