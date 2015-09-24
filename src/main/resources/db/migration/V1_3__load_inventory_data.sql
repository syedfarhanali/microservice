INSERT INTO inventory(quantity, product_id) VALUES (150,(select id from product WHERE name like 'WiFi-Router'));
INSERT INTO inventory(quantity, product_id) VALUES (200,(select id from product WHERE name like 'Electric Iron'));
INSERT INTO inventory(quantity, product_id) VALUES (500,(select id from product WHERE name like 'Mixer'));
INSERT INTO inventory(quantity, product_id) VALUES (1000,(select id from product WHERE name like 'Heater'));

