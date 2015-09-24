INSERT INTO inventory(total_items, product_id) VALUES (150,(select product_id from product WHERE product_name like 'WiFi-Router'));
INSERT INTO inventory(total_items, product_id) VALUES (200,(select product_id from product WHERE product_name like 'Electric Iron'));
INSERT INTO inventory(total_items, product_id) VALUES (500,(select product_id from product WHERE product_name like 'Mixer'));
INSERT INTO inventory(total_items, product_id) VALUES (1000,(select product_id from product WHERE product_name like 'Heater'));

