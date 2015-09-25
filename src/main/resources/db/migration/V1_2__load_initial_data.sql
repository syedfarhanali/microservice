INSERT INTO vendor (email, name) VALUES ('phillips@hotmail.com', 'Phillips India LTD');
INSERT INTO vendor (email, name) VALUES ('whirlpool@hotmail.com', 'Whirlpool India LTD');
INSERT INTO vendor (email, name) VALUES ('maharajas@hotmail.com', 'Maharaja India LTD');
INSERT INTO vendor (email, name) VALUES ('dlink@hotmail.com', 'Dlink India LTD');

INSERT INTO logistic (email, name) VALUES ('ekart@gmail.com', 'E-Kart Logistic LTD');


INSERT INTO product (name, price, category) VALUES ('WiFi-Router', 1200, 'ELECTRONIC');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id,vendor_id) VALUES ('Black', 'WN1300N', '2014-05-23', (select id from product WHERE name='WiFi-Router'),4);

INSERT INTO product (name, price, category) VALUES ('Electric Iron', 800, 'HOME_APPLIANCE');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id,vendor_id) VALUES ('White', 'D300', '2014-02-20', (select id from product WHERE name='Electric Iron'),1);

INSERT INTO product (name, price, category) VALUES ('Mixer', 3000, 'HOME_APPLIANCE');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id,vendor_id) VALUES ('Mixer', 'NLS300', '2013-05-06', (select id from product WHERE name='Mixer'),3);

INSERT INTO product (name, price, category) VALUES ('Heater', 900, 'HOME_APPLIANCE');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id,vendor_id) VALUES ('Black', 'HT230', '2012-01-22', (select id from product WHERE name='Heater'),2);


INSERT INTO inventory(quantity, product_id) VALUES (150,(select id from product WHERE name like 'WiFi-Router'));
INSERT INTO inventory(quantity, product_id) VALUES (200,(select id from product WHERE name like 'Electric Iron'));
INSERT INTO inventory(quantity, product_id) VALUES (500,(select id from product WHERE name like 'Mixer'));
INSERT INTO inventory(quantity, product_id) VALUES (1000,(select id from product WHERE name like 'Heater'));


INSERT INTO customer (email, name)  VALUES ('amit.shrivastava@talentica.com','Amit Shrivastava');
INSERT INTO customer (email, name)  VALUES ('john.hirsch@fidelis-inc.com','John Hirsch');
INSERT INTO customer (email, name)  VALUES ('kevin.ray@fidelis-inc.com','Kevin Ray');

INSERT INTO address (city, locality, pin_code, state, customer_id) VALUES ('Pune', 'Baner', '411045', 'Maharastra', 1);
INSERT INTO address (city, locality, pin_code, state, customer_id) VALUES ('Pune', 'Kalyani Nagar', '411048', 'Maharastra', 1);
