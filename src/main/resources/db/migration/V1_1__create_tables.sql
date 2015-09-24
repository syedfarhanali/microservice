CREATE TABLE product (
  product_id bigint(20) NOT NULL AUTO_INCREMENT,
  color varchar(255) DEFAULT NULL,
  model_number varchar(255) DEFAULT NULL,
  product_name varchar(255) DEFAULT NULL,
  production_date datetime DEFAULT NULL,
  purchase_date datetime DEFAULT NULL,
  PRIMARY KEY (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE customer (
  customer_id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE address (
  address_id bigint(20) NOT NULL AUTO_INCREMENT,
  city varchar(255) DEFAULT NULL,
  locality varchar(255) DEFAULT NULL,
  pin_code varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  street varchar(255) DEFAULT NULL,
  customer_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (address_id),
  KEY FK_nydg76tqo86ue3m5wsoojho6b (customer_id),
  CONSTRAINT FK_nydg76tqo86ue3m5wsoojho6b FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE purchase_order (
  order_id bigint(20) NOT NULL AUTO_INCREMENT,
  order_date datetime DEFAULT NULL,
  order_status varchar(255) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
  customer_id bigint(20) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (order_id),
  KEY FK_qigvnreb2wbokr9mqqnr1b7k0 (address_id),
  KEY FK_puwshb8ptuseofvg0vd8iktd5 (customer_id),
  KEY FK_7ghvcfdmeokd433j50a20c9cy (product_id),
  CONSTRAINT FK_7ghvcfdmeokd433j50a20c9cy FOREIGN KEY (product_id) REFERENCES product (product_id),
  CONSTRAINT FK_puwshb8ptuseofvg0vd8iktd5 FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
  CONSTRAINT FK_qigvnreb2wbokr9mqqnr1b7k0 FOREIGN KEY (address_id) REFERENCES address (address_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoice (
  invoice_id bigint(20) NOT NULL AUTO_INCREMENT,
  payment_status varchar(255) DEFAULT NULL,
  total_price int(11) DEFAULT NULL,
  customer_id bigint(20) DEFAULT NULL,
  order_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (invoice_id),
  KEY FK_maw2941vbaqg8s9ct87j0j50x (customer_id),
  KEY FK_gnfabg6rvhoc3c9o4deqb1hn4 (order_id),
  CONSTRAINT FK_gnfabg6rvhoc3c9o4deqb1hn4 FOREIGN KEY (order_id) REFERENCES purchase_order (order_id),
  CONSTRAINT FK_maw2941vbaqg8s9ct87j0j50x FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE inventory (
  inventory_id bigint(20) NOT NULL AUTO_INCREMENT,
  total_items int(11) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (inventory_id),
  KEY FK_ce3rbi3bfstbvvyne34c1dvyv (product_id),
  CONSTRAINT FK_ce3rbi3bfstbvvyne34c1dvyv FOREIGN KEY (product_id) REFERENCES product (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE shipment (
  shipment_id bigint(20) NOT NULL AUTO_INCREMENT,
  delivery_status varchar(255) DEFAULT NULL,
  order_id bigint(20) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (shipment_id),
  KEY FK_p06cong2injx54ipykoegys3w (order_id),
  KEY FK_13ktmp5d89poxoda249wfop7n (address_id),
  KEY FK_c6fhq3h113tllneo04pdj8ig5 (product_id),
  CONSTRAINT FK_13ktmp5d89poxoda249wfop7n FOREIGN KEY (address_id) REFERENCES address (address_id),
  CONSTRAINT FK_c6fhq3h113tllneo04pdj8ig5 FOREIGN KEY (product_id) REFERENCES product (product_id),
  CONSTRAINT FK_p06cong2injx54ipykoegys3w FOREIGN KEY (order_id) REFERENCES purchase_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

