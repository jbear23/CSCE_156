use jbearden;

drop table if exists InvoiceItem;
drop table if exists Product;
drop table if exists Invoice;
drop table if exists Customer;
drop table if exists EmailAddress;
drop table if exists Person;
drop table if exists Address;

create table Address(
	addressId int not null primary key auto_increment,
    street varchar(255),
    city varchar(255),
    state varchar(255),
    zipCode varchar(255),
    country varchar(255)
);

create table Person (
	personId int not null primary key auto_increment,
    code varchar(255) not null,
    firstName varchar(255),
    lastName varchar(255),
    addressId int not null,
    foreign key (addressId) references Address (addressId)
);

create table EmailAddress (
	emailAddressId int not null primary key auto_increment,
    emailAddress varchar(255),
    personId int not null,
    foreign key (personId) references Person (personId)
);

create table Customer (
	customerId int not null primary key auto_increment,
    code varchar(255) not null,
    name varchar(255),
    type varchar(255),
    primaryContactId int not null,
    addressId int not null,
    foreign key (primaryContactId) references Person (personId),
    foreign key (addressId) references Address (addressId)
);

create table Invoice (
	invoiceId int not null primary key auto_increment,
    code varchar (255),
    salesPersonId int not null,
    customerId int not null,
    foreign key (salesPersonId) references Person (personId),
    foreign key (customerId) references Customer (customerId)
);

create table Product (
	productId int not null primary key auto_increment,
	code varchar(255) not null,
    name varchar(255),
    type varchar(255) not null,
    pricePerUnit double,
    serviceFee double,
    annualLicenseFee double,
    hourlyFee double,
    consultantPersonId int,
    invoiceItemId int,
    foreign key (consultantPersonId) references Person (personId)
);

create table InvoiceItem (
	invoiceItemId int not null primary key auto_increment,
    invoiceId int not null,
    productId int not null,
    productCount int,
    numHours int,
	startDate varchar(255),
    endDate varchar(255),
    foreign key (invoiceId) references Invoice (invoiceId),
    foreign key (productId) references Product (productId)
);

#person addresses
insert into Address (addressId, street, city, state, zipCode, country) values (1, "38 Reindahl Place", "Huaihua", "", "", "China");
insert into Address (addressId, street, city, state, zipCode, country) values (2, "09 Sage Plaza", "Dobczyce", "", "32-410", "Poland");
insert into Address (addressId, street, city, state, zipCode, country) values (3, "91212 Daystar Pass", "Bula", "", "4430", "Philippines");
insert into Address (addressId, street, city, state, zipCode, country) values (4, "3950 Erie Street", "Jingnao", "", "", "China");
insert into Address (addressId, street, city, state, zipCode, country) values (5, "65462 Harper Point", "Sa Kaeo", "", "45250", "Thailand");
insert into Address (addressId, street, city, state, zipCode, country) values (6, "7 Stone Corner Avenue", "Ariguani", "", "273017", "Colombia");
insert into Address (addressId, street, city, state, zipCode, country) values (7, "5 Fuller Way", "Tozeur", "", "", "Tunisia");
insert into Address (addressId, street, city, state, zipCode, country) values (8, "9 Hanson Center", "Mendez-Nunez", "", "4221", "Philippines");
insert into Address (addressId, street, city, state, zipCode, country) values (9, "835 1st Court", "Kirkton", "SCT", "KW10", "United Kingdom");
insert into Address (addressId, street, city, state, zipCode, country) values (10, "42 Sommers Court", "Kostelec nad Cernymi Lesy", "", "281 63", "Czech Republic");
insert into Address (addressId, street, city, state, zipCode, country) values (11, "493 Kings Court", "Dundbürd", "" , "", "Mongolia");
insert into Address (addressId, street, city, state, zipCode, country) values (12, "8557 Dwight Lane", "Kato Dhiminio", "", "", "Greece");
insert into Address (addressId, street, city, state, zipCode, country) values (13, "80029 Old Gate Lane", "Yangdi", "", "", "China");
insert into Address (addressId, street, city, state, zipCode, country) values (14, "387 Morrow Pass", "Dukuhpicung", "", "", "Indonesia");
insert into Address (addressId, street, city, state, zipCode, country) values (15, "7 Hoepker Park", "Matsue-shi", "", "690-1115", "Japan");
#customer addresses
insert into Address (addressId, street, city, state, zipCode, country) values (16, "7042 Cambridge Court", "Rungis", "A8", "94575 CEDEX 2", "France");
insert into Address (addressId, street, city, state, zipCode, country) values (17, "655 Superior Junction", "Zuogaiduoma", "", "", "China");
insert into Address (addressId, street, city, state, zipCode, country) values (18, "32600 Elmside Avenue", "Rio das Pedras", "", "13390-000", "Brazil");
insert into Address (addressId, street, city, state, zipCode, country) values (19, "5661 Vidon Court", "Tatsunocho-tominaga", "", "679-4324", "Japan");
insert into Address (addressId, street, city, state, zipCode, country) values (20, "5697 Everett Circle", "Sacramento", "CA", "94237", "United States");
insert into Address (addressId, street, city, state, zipCode, country) values (21, "5 Fallview Drive", "Maticmatic", "", "2430", "Philippines");
insert into Address (addressId, street, city, state, zipCode, country) values (22, "8 Green Street", "Neyagawa", "", "573-1191", "Japan");
insert into Address (addressId, street, city, state, zipCode, country) values (23, "424 Badeau Place", "Szczaniec", "", "66-225", "Poland");
insert into Address (addressId, street, city, state, zipCode, country) values (24, "4 Twin Pines Parkway", "Cimaragas", "", "", "Indonesia");
insert into Address (addressId, street, city, state, zipCode, country) values (25, "698 Division Court", "Okinawa Numero Uno", "", "", "Bolivia");

insert into Person (personId, code, firstName, lastName, addressId) values (1, "AAAAAAAA", "Phaedra", "de Castelain", 1);
insert into Person (personId, code, firstName, lastName, addressId) values (2, "AAAAAAAB", "Abram", "Churcher", 2);
insert into Person (personId, code, firstName, lastName, addressId) values (3, "AAAAAAAC", "Carlee", "Hurn", 3);
insert into Person (personId, code, firstName, lastName, addressId) values (4, "AAAAAAAD", "Jacob", "Glaserman", 4);
insert into Person (personId, code, firstName, lastName, addressId) values (5, "AAAAAAAE", "Ethlin", "Aris", 5);
insert into Person (personId, code, firstName, lastName, addressId) values (6, "AAAAAAAF", "Darci", "Guynemer", 6);
insert into Person (personId, code, firstName, lastName, addressId) values (7, "AAAAAAAG", "Ophelie", "Standage", 7);
insert into Person (personId, code, firstName, lastName, addressId) values (8, "AAAAAAAH", "Kirby", "Husby", 8);
insert into Person (personId, code, firstName, lastName, addressId) values (9, "AAAAAAAI", "Jonah", "Bingell", 9);
insert into Person (personId, code, firstName, lastName, addressId) values (10, "AAAAAAAJ", "Kermie", "Vogelein", 10);
insert into Person (personId, code, firstName, lastName, addressId) values (11, "AAAAAAAK", "Glyn", "Dri", 11);
insert into Person (personId, code, firstName, lastName, addressId) values (12, "AAAAAAAL", "Dot", "Coulman", 12);
insert into Person (personId, code, firstName, lastName, addressId) values (13, "AAAAAAAM", "Karrie", "Alvarado", 13);
insert into Person (personId, code, firstName, lastName, addressId) values (14, "AAAAAAAN", "Oralie", "Whelpdale", 14);
insert into Person (personId, code, firstName, lastName, addressId) values (15, "AAAAAAAO", "Florence", "Sedger", 15);

insert into EmailAddress (emailAddressId, emailAddress, personId) values (1, "pdecastelain0@feedburner.com", 1);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (2, "achurcher1@businesswire.com", 2);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (3, "churn2@ucsd.edu", 3);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (4, "jglaserman3@imdb.com", 4);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (5, "earis4@skyrock.com", 5);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (6, "dguynemer5@opensource.org", 6);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (7, "ostandage6@cocolog-nifty.com", 7);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (8, "khusby7@spotify.com", 8);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (9, "kvogelein9@altervista.org", 10);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (10, "gdria@mit.edu", 11);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (11, "dcoulmanb@wisc.edu", 12);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (12, "owhelpdaled@wikimedia.org", 14);
insert into EmailAddress (emailAddressId, emailAddress, personId) values (13, "fsedgere@slate.com", 15);

insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (1, "BBBBBBBA", "Zulauf, Kuhic and Zulauf", "C", 2, 13);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (2, "BBBBBBBB", "Heidenreich-Cronen", "C", 2, 14);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (3, "BBBBBBBC", "Swaniawski-Schimmel", "C", 3, 15);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (4, "BBBBBBBD", "Wolff-Botsford", "G", 4, 16);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (5, "BBBBBBBE", "Stroman-Wolff", "C", 1, 17);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (6, "BBBBBBBF", "McCullough-Morissette", "G", 4, 21);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (7, "BBBBBBBG", "Heathcote, Abbott and Wolf", "C", 2, 22);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (8, "BBBBBBBH", "Ankunding Group", "G", 4, 23);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (9, "BBBBBBBI", "Pfannerstill Group", "G", 4, 24);
insert into Customer (customerId, code, name, type, primaryContactId, addressId) values (10, "BBBBBBBJ", "Bailey, Wunsch and Mueller", "G", 5, 25);


insert into Invoice (invoiceId, code, customerId, salesPersonId) values (1, "INVAAA", 1, 11);
insert into Invoice (invoiceId, code, customerId, salesPersonId) values (2, "INVBBB", 3, 10);
insert into Invoice (invoiceId, code, customerId, salesPersonId) values (3, "INVCCC", 4, 12);
insert into Invoice (invoiceId, code, customerId, salesPersonId) values (4, "INVDDD", 5, 9);

insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (1, "CCCCCCCA", "Digital Sound Mansion Setup", "C", null, null, null, 178.00, 6, 4);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (2, "CCCCCCCB", "Informatics Datacloning Training", "C", null, null, null, 41.00, 8, null);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (3, "CCCCCCCC", "Special 1024-Bit Elixir", "E", 760.00, null, null, null, null, 2);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (4, "CCCCCCCD", "Super Software Setup", "C", null, null, null, 97.00, 8, 3);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (5, "CCCCCCCE", "Digital Sound Mansion Disco Module", "E", 1325.00, null, null, null, null, 2);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (6, "CCCCCCCF", "Wonder Engine Hosting", "L", null, 0.00, 44633.06, null, null, 1);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (7, "CCCCCCCG", "Range Registration", "L", null, 490.00, 1900.00, null, null, 4);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (8, "CCCCCCCH", "Cinco SeisSiete Disc", "E", 2900.00, null, null, null, null, 2);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (9, "CCCCCCCI", "PseudoHydrolic Data Parser Setup", "C", null, null, null, 137.00, 6, null);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (10, "CCCCCCCJ", "Short Distance Service", "L", null, 855.00, 18957.00, null, null, 4);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (11, "CCCCCCCK", "Cinco Hypercube Media Player", "E", 165.00, null, null, null, null, 1);
insert into Product (productId, code, name, type, pricePerUnit, serviceFee, annualLicenseFee, hourlyFee, consultantPersonId, invoiceItemId)
	values (12, "CCCCCCCL", "Circuit Completion License", "L", null, 940.00, 13615.38, null, null, 3);
    
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (1, 6, null, null, "2014-11-13", "2015-01-01");
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (1, 11, 2, null, null, null);
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (2, 3, 1, null, null, null);
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (2, 5, 3, null, null, null);
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (2, 8, 4, null, null, null);
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (3, 4, 1, 3, null, null);
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (3, 12, null, null, "2010-01-31", "2011-09-01");
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (4, 1, 1, 2, null, null);
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (4, 7, null, null, "2014-01-01", "2018-12-31");
insert into InvoiceItem (invoiceId, productId, productCount, numHours, startDate, endDate) values (4, 10, null, null, "2009-12-01", "2011-12-01");