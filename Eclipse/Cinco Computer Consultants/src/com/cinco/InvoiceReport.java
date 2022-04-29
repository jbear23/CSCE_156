package com.cinco;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

public class InvoiceReport {
	
	public static void main(String[] args) {
		//We need to pull
		MapHolder mapHolder = DataConverter.main(args);
		Invoice InvoiceArray[] = null;
		//Begin parsing the invoice data
		try {
            Scanner s = new Scanner(new File("data/Invoices.dat"));
            String line = s.nextLine();
            InvoiceArray = new Invoice[Integer.valueOf(line)];
            for(int i=0; i < InvoiceArray.length; i++) {
            	line = s.nextLine();
            	String[] InvoiceTokens = line.split(";");
            	InvoiceArray[i] = new Invoice(InvoiceTokens[0],InvoiceTokens[1],InvoiceTokens[2],InvoiceTokens[3]);
            }
            
            s.close();
		} catch (FileNotFoundException e){
            e.printStackTrace();
        }
		
		//Begin printing info to the SO
		double TS = 0.0;
		double TF = 0.0;
		double TA = 0.0;
		double TT = 0.0;
		System.out.printf("%70s\n====================================================================================================================\n","Executive Summary Report");
		System.out.printf("%-10s%-35s%-20s%-18s%-10s%-13s%s\n","Invoice","Customer","Salesperson","Subtotal","Fees","Taxes","Total");
		for(Invoice i: InvoiceArray) {
			//Temporary Storage of CostTotal, otherwise we would be creating another CostTotal everytime we called on info
			CostTotal j = i.getTotalInvoiceCost(mapHolder.getProductMap(), mapHolder.getCustomerMap());
			TS += j.getPrice(); TF += j.getFees() + j.getComplianceFee();
			TA += j.getTax(); TT += j.getTotalCost();
			System.out.printf("%-10s%-35s%-20s$%-15.2f$%-10.2f$%-11.2f$%.2f\n",
					i.getInvoiceCode(),
					mapHolder.getCustomerMap().get(i.getCustomerCode()).getName(),
					mapHolder.getPersonMap().get(i.getSalespersonCode()).getFullName(),
					j.getPrice(),
					j.getFees() + j.getComplianceFee(),
					j.getTax(),
					j.getTotalCost());
		}
		System.out.printf("====================================================================================================================\n","Executive Summary Report");
		System.out.printf("%-10s%-35s%-20s$%-15.2f$%-10.2f$%-11.2f$%.2f\n","Totals","","",TS,TF,TA,TT);
		
		
		//Begin printing individual reports
		System.out.printf("\n\n%70s\n====================================================================================================================\n","Detailed Invoice Reports");
		for(Invoice i: InvoiceArray) {
			//More temporary storage of CostTotal
			CostTotal j = i.getTotalInvoiceCost(mapHolder.getProductMap(), mapHolder.getCustomerMap());
			System.out.printf("Invoice %s\n", i.getInvoiceCode());
			System.out.printf("==========================\n");
			System.out.printf("Customer Info\n  Company ID: %s\n  Name: %s\n  Primary Contact: %s\n  Address: %s\n",
					mapHolder.getCustomerMap().get(i.getCustomerCode()).getCode(),
					mapHolder.getCustomerMap().get(i.getCustomerCode()).getName(),
					mapHolder.getCustomerMap().get(i.getCustomerCode()).getPrimaryContact().getFullName(),
					mapHolder.getCustomerMap().get(i.getCustomerCode()).getAddress().ToString());
			System.out.printf("Salesperson\n  ID:%s\n  Name:%s\n", i.getSalespersonCode(),mapHolder.getPersonMap().get(i.getSalespersonCode()).getFullName() );
			System.out.printf("--------------------------\n%-10s%-77s%-11s%s\n", "Code", "Item", "Fees", "Total");
			for(Cost k: j.getListings()) {
				String Item = null;
				if (mapHolder.getProductMap().get(k.getItemID()).getType().equals("E")){
					Item = mapHolder.getProductMap().get(k.getItemID()).getName() 
							+ " (" + i.getProductAmountFromMap(k.getItemID()) 
							+ " units @ $" + 
							(((Equipment) mapHolder.getProductMap().get(k.getItemID())).getPricePerUnit())
							+ "/unit)";
				} else if (mapHolder.getProductMap().get(k.getItemID()).getType().equals("C")){
					Item = mapHolder.getProductMap().get(k.getItemID()).getName() 
							+ " (" + i.getProductAmountFromMap(k.getItemID()) 
							+ " hrs @ $" + 
							(((Consultation) mapHolder.getProductMap().get(k.getItemID())).getHourlyFee())
							+ "/hr)";
				} else if (mapHolder.getProductMap().get(k.getItemID()).getType().equals("L")){
					/*
					 * String[] Dates = i.getProductMap().get(k.getItemID()).split(",");
					 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 * formatter = formatter.withLocale(Locale.US); LocalDate date1 =
					 * LocalDate.parse(Dates[0], formatter); LocalDate date2 =
					 * LocalDate.parse(Dates[1], formatter); Double Length = (((double)
					 * (ChronoUnit.DAYS.between(date1, date2))));
					 */
					DateTimeFormatter formatter;
					String[] Dates = i.getProductMap().get(k.getItemID()).split(",");
					if (Dates[0].charAt(5)!='-' && Dates[0].charAt(7)!='-') {
					formatter = DateTimeFormatter.ofPattern("yyyy-M-d");	
					}else if (Dates[0].charAt(5)!='-') {
					formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
					}else if (Dates[0].charAt(7)!='-') {
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
					}else {
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
					}
					formatter = formatter.withLocale(Locale.US);
					LocalDate date1 = LocalDate.parse(Dates[0], formatter);
					if (Dates[1].charAt(5)!='-' && Dates[0].charAt(7)!='-') {
					formatter = DateTimeFormatter.ofPattern("yyyy-M-d");	
					}else if (Dates[1].charAt(5)!='-') {
					formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
					}else if (Dates[1].charAt(7)!='-') {
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
					}else {
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
					}
					formatter = formatter.withLocale(Locale.US);
					LocalDate date2 = LocalDate.parse(Dates[1], formatter);
					Double Length = (((double) (ChronoUnit.DAYS.between(date1, date2))));
					String len = Double.toString(Length);
					Item = mapHolder.getProductMap().get(k.getItemID()).getName() 
							+ " (" + len
							+ " days @ $" + 
							(((License) mapHolder.getProductMap().get(k.getItemID())).getAnnualLicenseFee())
							+ "/yr)";
				}

				System.out.printf("%-10s%-77s%-11.2f%.2f\n",k.getItemID(),Item,k.getFee(),k.getPrice());
			}
			System.out.printf("\n");
			System.out.printf("Subtotals%78s %5.2f    $ %.2f\n","$", j.getFees(),j.getPrice());
			System.out.printf("Compliance Fee%84s$ %.2f\n","",j.getComplianceFee());
			System.out.printf("Taxes%93s$ %.2f\n","",j.getTax());
			System.out.printf("Total%93s$ %.2f\n","",j.getTotalCost());
			System.out.printf("\n");
		}
	}
}