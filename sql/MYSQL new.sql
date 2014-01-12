CREATE TABLE AUDITENTRY
(
auditno INT NOT NULL AUTO_INCREMENT, 
auditsdate DATE, 
auditedate DATE,   
counductby varchar(300),
note TEXT,

CONSTRAINT auditentry_pk PRIMARY KEY(auditno)
);

create table AUDITITEMS(
auditno int,
branch varchar(30),
serialNo varchar(200),
Name varchar(100),
brand varchar(100),
model varchar(100),
status varchar(30),
CONSTRAINT audititems_pk primary key(auditno,serialNo)

);

CREATE TABLE BRANCH
(
branchid INT NOT NULL AUTO_INCREMENT,
type VARCHAR(200),
itmanager VARCHAR(200),
address VARCHAR(200),

CONSTRAINT branch_pk PRIMARY KEY(branchid)
);

CREATE TABLE INVENTORY 
(
inventoryid INT NOT NULL AUTO_INCREMENT,
serialno VARCHAR(200), 
name VARCHAR(50), 
brand VARCHAR(200), 
model VARCHAR(50), 
username VARCHAR(40), 
position VARCHAR(40), 
ram VARCHAR(200), 
processor VARCHAR(60), 
harddisk VARCHAR(60), 
type VARCHAR(200), 
front CHAR(1), 
parentserialno VARCHAR(200), 
status VARCHAR(20), 
datepurchased DATE, 
supplierid INT, 
branchid INT, 
ups VARCHAR(60), 
printer_type VARCHAR(60),
bstatus VARCHAR(10),

CONSTRAINT inventory_pk PRIMARY KEY(inventoryid),
CONSTRAINT inventory_unique UNIQUE(serialno)
);

CREATE TABLE INVENHISTORY
(
historyid INT NOT NULL AUTO_INCREMENT,
serialno VARCHAR(200),
event VARCHAR(200),
historydate DATE,

CONSTRAINT inven_history_pk PRIMARY KEY(historyid)
);

CREATE TABLE SUPPLIER 
(
supplierid INT NOT NULL AUTO_INCREMENT, 
companyname VARCHAR(200), 
phone VARCHAR(20),
type VARCHAR(10),
purchase INT,
email VARCHAR(60), 
address VARCHAR(200), 
duration INT,

CONSTRAINT supplier_pk PRIMARY KEY(supplierid)
);


CREATE TABLE MSUPPLIER 
(
supplierid INT NOT NULL AUTO_INCREMENT, 
companyname VARCHAR(200), 
phone VARCHAR(20),
email VARCHAR(60), 
address VARCHAR(200),
startingdate DATE,
duration VARCHAR(60),
serialno VARCHAR(200),
producttype VARCHAR(20),

CONSTRAINT msupplier_pk PRIMARY KEY(supplierid),
CONSTRAINT Msupplier_unique UNIQUE(serialno)
);

CREATE TABLE MESSAGE 
(	
messageid INT NOT NULL AUTO_INCREMENT, 
subject VARCHAR(200), 
ticketid INT, 
parentid INT, 
userid INT, 
message TEXT,
ctime TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT message_pk PRIMARY KEY(messageid)
);

CREATE TABLE REPLACEMENT 
(
replacementid INT NOT NULL AUTO_INCREMENT, 
reson TEXT, 
replacedate DATE, 
olduser VARCHAR(200), 
oldlocation INT, 
newlocation INT, 
newserialno VARCHAR(200), 
serialno VARCHAR(200), 
type VARCHAR(100),

CONSTRAINT replacement_pk PRIMARY KEY(replacementid),
CONSTRAINT replacement_unique UNIQUE(serialno)
);

CREATE TABLE TICKET 
(
ticketid INT NOT NULL AUTO_INCREMENT, 
subject TEXT, 
message TEXT, 
status VARCHAR(10), 
userid VARCHAR(20), 
priority VARCHAR(20),
highlight CHAR(1),
highlight char(1) DEFAULT NULL,
othighlight char(1) DEFAULT NULL,
ctime TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT ticket_pk PRIMARY KEY(ticketid),
CONSTRAINT ticket_highlight_check CHECK(highlight IN('Y','N'))
);

CREATE TABLE FEEDBACK
(
feedbackid INT NOT NULL AUTO_INCREMENT,
ticketid INT NOT NULL,
senderid INT NOT NULL,
receiverid INT NOT NULL,
message TEXT NOT NULL,
status VARCHAR(10),
highlight CHAR(1),

CONSTRAINT feedback_pk PRIMARY KEY(feedbackid),
CONSTRAINT feedback_highlight_check CHECK(highlight IN('Y','N'))
);

CREATE TABLE USERS
(
userid INT NOT NULL AUTO_INCREMENT,
roleid INT NOT NULL, 
email VARCHAR(200), 
phone VARCHAR(20), 
password VARCHAR(30), 
name VARCHAR(200),
username VARCHAR(50),

CONSTRAINT user_pk PRIMARY KEY(userid)
);

CREATE TABLE EMAIL
(
emailid INT AUTO_INCREMENT,
subject TEXT,
message TEXT,
sender VARCHAR(30),
reciver VARCHAR(30)

CONSTRAINT email_pk PRIMARY KEY(emailid)
);

CREATE TABLE USERHISTORY 
(
entryid INT NOT NULL AUTO_INCREMENT, 
logintime TIMESTAMP (6) WITH LOCAL TIME ZONE, 
logiouttime TIMESTAMP (6) WITH LOCAL TIME ZONE, 
userid INT,

CONSTRAINT userhistory_pk PRIMARY KEY(entryid)
);

CREATE TABLE WARRANTY 
(
warrantyid INT NOT NULL AUTO_INCREMENT, 
duration INT, 
expirydate DATE, 
serialno VARCHAR(30), 
producttype VARCHAR(30), 
supplierid INT,

CONSTRAINT warranty_pk PRIMARY KEY(warrantyid),
CONSTRAINT warranty_unique UNIQUE(serialno)
);

CREATE TABLE ROLES
(
roleid INT NOT NULL AUTO_INCREMENT,
rolename VARCHAR(50) NOT NULL,

CONSTRAINT roles_pk PRIMARY KEY(roleid)
);

CREATE TABLE PERMISSIONS
(
permid INT NOT NULL AUTO_INCREMENT,
permdesc VARCHAR(50) NOT NULL,

CONSTRAINT permissions_pk PRIMARY KEY(permid)
);

CREATE TABLE ROLEPERM
(
roleid INT NOT NULL,
permid INT NOT NULL
);
CREATE TABLE ORGEMAIL
(
emailid INT NOT NULL AUTO_INCREMENT,
subject TEXT,
message TEXT,
userto VARCHAR(30),

CONSTRAINT emailid_pk PRIMARY KEY(emailid)
);

CREATE TABLE PAYMENT(
paymentid INT NOT NULL AUTO_INCREMENT,
recieptno VARCHAR(30),
totamount DOUBLE,
type VARCHAR(50),
pdate DATETIME,
purchaseid INT,

CONSTRAINT pk_payment PRIMARY KEY(paymentid),
CONSTRAINT fk_payment FOREIGN KEY (purchaseid) REFERENCES PURCHASE(purchaseid)
);

CREATE TABLE ORDERS(
orderid INT NOT NULL AUTO_INCREMENT,
branchid INT,
orderdate DATE,
laptop VARCHAR(50),
pc VARCHAR(50),
printers VARCHAR(50),
others INT,
otherinfo VARCHAR(50),

CONSTRAINT pk_order PRIMARY KEY(orderid),
CONSTRAINT fk_order_branch FOREIGN KEY(branchid) REFERENCES BRANCH(branchid)
);

CREATE TABLE ORDERITEM(
itemid INT NOT NULL AUTO_INCREMENT,
type VARCHAR(30),
orderid INT,
quntity INT,

CONSTRAINT PRIMARY KEY(itemid),
CONSTRAINT fk_order FOREIGN KEY(orderid) REFERENCES ORDER(orderid)
);

CREATE TABLE SETTINGS(
settingsid INT NOT NULL AUTO_INCREMENT,
sname varchar(200) NOT NULL,
svalue TEXT,

CONSTRAINT settings_pk PRIMARY KEY(settingsid)
);

CREATE TABLE IANNOUNCEMENT(
iannouncementid INT NOT NULL AUTO_INCREMENT,
componentid VARCHAR(10),
subject VARCHAR(50),
targetGroup VARCHAR(100),
Announcement TEXT,
moddate DATE,
status VARCHAR(45) DEFAULT 'Available',
 
CONSTRAINT iannouncementid_pk PRIMARY KEY(iannouncementid),
CONSTRAINT componenetid_unique UNIQUE(componentid)
);

CREATE TABLE IMANUALNEW(
imanulnew INT NOT NULL AUTO_INCREMENT,
componentid varchar(10),
itemname varchar(30),
brand varchar(30),
model varchar(50),
manualname varchar(50),
filepath varchar(500),
moddate Date,
mstatus varchar(45) DEFAULT 'Available',

CONSTRAINT imanualnew_pk PRIMARY KEY(imanulnew),
CONSTRAINT componenetid_unique UNIQUE(componentid)
);

CREATE TABLE PURCHASE(
purchaseid INT NOT NULL AUTO_INCREMENT,
invoiceno VARCHAR(30),
purdate DATE,
item VARCHAR(100),
quantity INT,
supplier VARCHAR(100),
CONSTRAINT unique_purchase UNIQUE(invoiceno),
CONSTRAINT pk_purchase PRIMARY KEY(purchaseid)
);

CREATE TABLE PAYMENT(
paymentid INT NOT NULL AUTO_INCREMENT,
recieptno VARCHAR(30),
totamount DOUBLE,
type VARCHAR(50),
pdate DATE,
purchaseid INT,
CONSTRAINT pk_payment PRIMARY KEY(paymentid),
CONSTRAINT fk_payment FOREIGN KEY (purchaseid) REFERENCES PURCHASE(purchaseid)
);


CREATE TABLE GRN(
grnno INT NOT NULL AUTO_INCREMENT,
supplier VARCHAR(100),
grndes VARCHAR(100),
paymentid INT,
ordernum INT,
CONSTRAINT pk_grn PRIMARY KEY(grnno),
CONSTRAINT fk_grn FOREIGN KEY (paymentid) REFERENCES PAYMENT(paymentid)
);
