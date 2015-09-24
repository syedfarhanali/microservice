CREATE TABLE product (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  category varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  price double NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE product_detail (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  color varchar(255) DEFAULT NULL,
  manufacturing_date datetime DEFAULT NULL,
  model_number varchar(255) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_n0poneags4pq2e3u2mlv7h29n (product_id),
  CONSTRAINT FK_n0poneags4pq2e3u2mlv7h29n FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE inventory (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  quantity int(11) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_ce3rbi3bfstbvvyne34c1dvyv (product_id),
  CONSTRAINT FK_ce3rbi3bfstbvvyne34c1dvyv FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE payment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  payment_service_id bigint(20) DEFAULT NULL,
  payment_status varchar(255) DEFAULT NULL,
  transaction_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE logistic (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE vendor (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE customer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  city varchar(255) DEFAULT NULL,
  locality varchar(255) DEFAULT NULL,
  pin_code varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  customer_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_nydg76tqo86ue3m5wsoojho6b (customer_id),
  CONSTRAINT FK_nydg76tqo86ue3m5wsoojho6b FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE purchase_order (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  order_date datetime DEFAULT NULL,
  quantity int(11) NOT NULL,
  status varchar(255) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
  customer_id bigint(20) DEFAULT NULL,
  payment_id bigint(20) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  vendor_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_pydy51xipo4s9jgomassjv89g (address_id),
  KEY FK_c1dxn8aye2kau4m0bevoes052 (customer_id),
  KEY FK_j8qa6vorao7x93bnqrbtathnf (payment_id),
  KEY FK_mh283nd624s5s3emomtkar5wa (product_id),
  KEY FK_h5u6dtplan2sjm7xg83pqsvtm (vendor_id),
  CONSTRAINT FK_c1dxn8aye2kau4m0bevoes052 FOREIGN KEY (customer_id) REFERENCES customer (id),
  CONSTRAINT FK_h5u6dtplan2sjm7xg83pqsvtm FOREIGN KEY (vendor_id) REFERENCES vendor (id),
  CONSTRAINT FK_j8qa6vorao7x93bnqrbtathnf FOREIGN KEY (payment_id) REFERENCES payment (id),
  CONSTRAINT FK_mh283nd624s5s3emomtkar5wa FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT FK_pydy51xipo4s9jgomassjv89g FOREIGN KEY (address_id) REFERENCES address (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE shipment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  status varchar(255) DEFAULT NULL,
  logistic_id bigint(20) DEFAULT NULL,
  order_id bigint(20) DEFAULT NULL,
  product_id bigint(20) DEFAULT NULL,
  address_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_4onrm0du33ihqf1hbi6k8nlwk (logistic_id),
  KEY FK_p06cong2injx54ipykoegys3w (order_id),
  KEY FK_c6fhq3h113tllneo04pdj8ig5 (product_id),
  KEY FK_13ktmp5d89poxoda249wfop7n (address_id),
  CONSTRAINT FK_13ktmp5d89poxoda249wfop7n FOREIGN KEY (address_id) REFERENCES address (id),
  CONSTRAINT FK_4onrm0du33ihqf1hbi6k8nlwk FOREIGN KEY (logistic_id) REFERENCES logistic (id),
  CONSTRAINT FK_c6fhq3h113tllneo04pdj8ig5 FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT FK_p06cong2injx54ipykoegys3w FOREIGN KEY (order_id) REFERENCES purchase_order (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoice (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  payment_status varchar(255) DEFAULT NULL,
  total_price int(11) DEFAULT NULL,
  customer_id bigint(20) DEFAULT NULL,
  order_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_maw2941vbaqg8s9ct87j0j50x (customer_id),
  KEY FK_gnfabg6rvhoc3c9o4deqb1hn4 (order_id),
  CONSTRAINT FK_gnfabg6rvhoc3c9o4deqb1hn4 FOREIGN KEY (order_id) REFERENCES purchase_order (id),
  CONSTRAINT FK_maw2941vbaqg8s9ct87j0j50x FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








