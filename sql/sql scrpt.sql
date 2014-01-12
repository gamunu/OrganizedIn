
CREATE TABLE PURCHASE(
purchaseid INT NOT NULL AUTO_INCREMENT,
invoiceno VARCHAR(30),
purdate DATETIME,
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
pdate DATETIME,
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

CREATE TABLE ORDER(
orderid INT NOT NULL AUTO_INCREMENT,
orderdate DATE,
branchid INT,

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

INSERT PURCHASE values(1, '2013-3-3', 'tv', 5, 'abance');
INSERT PAYMENT values(1, '2000.00', 'cash', '2013-3-3', 1);
INSERT GRN values(1, 'abance', 'desssss', 1, 1);

