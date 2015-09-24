INSERT INTO product (name, price, category) VALUES ('WiFi-Router', 1200, 'ELECTRONIC');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id) VALUES ('Black', 'WN1300N', '2014-05-23', (select id from product WHERE name='WiFi-Router'));

INSERT INTO product (name, price, category) VALUES ('Electric Iron', 800, 'HOME_APPLIANCE');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id) VALUES ('White', 'D300', '2014-02-20', (select id from product WHERE name='Electric Iron'));

INSERT INTO product (name, price, category) VALUES ('Mixer', 3000, 'HOME_APPLIANCE');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id) VALUES ('Mixer', 'NLS300', '2013-05-06', (select id from product WHERE name='Mixer'));

INSERT INTO product (name, price, category) VALUES ('Heater', 900, 'HOME_APPLIANCE');

INSERT INTO product_detail (color, model_number, manufacturing_date, product_id) VALUES ('Black', 'HT230', '2012-01-22', (select id from product WHERE name='Heater'));

