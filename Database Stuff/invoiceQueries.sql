#1
select p.code, p.firstName, p.lastName, em.emailAddress, a.street, a.city, a.state, a.zipCode, a.country
	from Person p
    left join EmailAddress em on p.personId = em.personId
    join Address a where p.addressId = a.addressId;

#2    
select p.firstName, p.lastName, em.emailAddress
	from Person p
    left join EmailAddress em on p.personId = em.personId;

#3    
insert into EmailAddress (emailAddressId, emailAddress, personId) values (14, "somethingAwesome@bestThingEver.com", 1);

#4
update EmailAddress set emailAddress = "somethingAwesomePt2@bestThingEver.com" where personId = 2;

#5 (STILL NEEDS WORK)
delete from Invoice
	where salespersonId = 2;
delete from Invoice
	where customerId = ?;
delete from EmailAddress
	where personId = 2;
delete from Customer
	where primaryContactId = 2;
delete from Person 
	where personId = 2;
    
#6
insert into Person (personId, code, firstName, lastName, addressId) values (16, "AAAAAAAP", "Alan", "Parish", null);

#7
select pr.name from Product pr left join InvoiceItem iv
	on pr.productId = iv.productId left join Invoice i
    on iv.invoiceId = i.invoiceId where i.invoiceId = 1;

#8
select i.code from Invoice i left join Customer c
	on i.customerId = c.customerId where c.customerId = 1;

#9
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, startDate, endDate, hourlyFee, consultantPersonId, invoiceItemId)
	values (13, "CCCCCCCM", "That Spectacular Software Training", "C", null, null, null, null, null, 150.00, 2, 4);

#10
insert into Invoice (invoiceId, code, customerId, salesPersonId) values (5, "INVEEE", 7, 11);

#11
update InvoiceItem set invoiceId = 2 where invoiceItemId = 1;
update InvoiceItem set productId = 4 where invoiceItemId = 1;

#12 STILL NEEDS WORK
select c.name, count(*) from (select * from Invoice i join Customer c
	on i.customerId = c.customerId) a;

#13 TOTAL NUMBER OF INVOICES FOR EACH SALESPERSON


#14 TOTAL NUMBER OF INVOICES THAT INCLUDE A PARTICULAR PRODUCT


#15 TOTAL COST OF ALL EQUIPMENT IN EACH INVOICE


#16 FIND ANY INVOICE(S) CONTAINING MULTIPLE INSTANCES OF THE SAME EQUIPMENT PRODUCT


#17 FIND WHERE SALESPERSON = PRIMARY CONTACT

