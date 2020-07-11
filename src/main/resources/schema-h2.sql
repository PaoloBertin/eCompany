DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS customer_role;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS warehouse;
DROP TABLE IF EXISTS lineitem;
DROP TABLE IF EXISTS purchase_orders;
DROP TABLE IF EXISTS lineitem;
DROP TABLE IF EXISTS purchase_orders_lineitems;

-- CREATE TABLE IF NOT EXISTS users(
--    username VARCHAR(256) NOT NULL,
--    password VARCHAR(256) NOT NULL,
--    enabled BOOLEAN NOT NULL,
    
--    PRIMARY KEY(username)
--);

-- CREATE TABLE authorities (
--    username VARCHAR(256) NOT NULL,
--    authority VARCHAR(256) NOT NULL,

--    CONSTRAINT un_authoritues_01 UNIQUE (username,authority),
--    CONSTRAINT fk_authorities_01 FOREIGN KEY(username) REFERENCES users(username)
--);

-- CREATE TABLE IF NOT EXISTS groups (
--    id BIGINT(20) NOT NULL AUTO_INCREMENT,
--    group_name varchar(256) not NULL,
    
--    PRIMARY KEY(id)
--);

--CREATE TABLE IF NOT EXISTS group_authorities (
--    group_id BIGINT(20) NOT NULL,
--    authority varchar(256) not null,
    
--    CONSTRAINT fk_group_authorities_group FOREIGN KEY(group_id) REFERENCES groups(id)
--);

--CREATE TABLE IF NOT EXISTS group_members (
--    id BIGINT NOT NULL AUTO_INCREMENT primary key,
--    username VARCHAR(256) NOT NULL,
--    group_id BIGINT(20) NOT NULL,
    
--    CONSTRAINT fk_group_members_group FOREIGN KEY(group_id) REFERENCES groups(id)
--);
CREATE TABLE IF NOT EXISTS address (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    city VARCHAR(50),
    street VARCHAR(50),
    house_number VARCHAR(10),
    zip_code VARCHAR(10),
    country VARCHAR(50),
    state VARCHAR(20),
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS contacts (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    cellular VARCHAR(20),
    landline_phone VARCHAR(20),
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customers (
    customerid BIGINT(20) NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(15),
    lastname VARCHAR(25),
    username VARCHAR(15) UNIQUE,
    password VARCHAR(25),
    email VARCHAR(25) UNIQUE,
    address_id BIGINT(20),
    contact_id BIGINT(20),
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(customerid),
    CONSTRAINT customers_fk_01 FOREIGN KEY (address_id) REFERENCES address(id),
    CONSTRAINT customers_fk_02 FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE IF NOT EXISTS role (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(25),
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customer_role (
    customerid BIGINT(20) NOT NULL,
    id BIGINT(20) NOT NULL,
    version BIGINT(20) DEFAULT 0,

    CONSTRAINT fk_customer_role_01 FOREIGN KEY (customerid) REFERENCES customers(customerid),
    CONSTRAINT fk_customer_role_02 FOREIGN KEY (id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS categories (
    categoryid BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(254),
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(categoryid)
);

CREATE TABLE IF NOT EXISTS products (
    productid BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(254),
    description VARCHAR(254),
    isbn VARCHAR(20) NOT NULL UNIQUE,
    price DECIMAL(12,4),
    image BLOB,
    categoryid BIGINT(20),
    version BIGINT(20) DEFAULT 0,
    
    PRIMARY KEY (productid),
    
    CONSTRAINT category_id_fk FOREIGN KEY (categoryid) REFERENCES categories(categoryid)
);

CREATE TABLE IF NOT EXISTS warehouse (
    warehouseid BIGINT(20) NOT NULL AUTO_INCREMENT,
    product_id BIGINT(20) NOT NULL,
    sku VARCHAR(50) NOT NULL,
    cost DECIMAL(12,4),
    unit VARCHAR(5),
    quantity INTEGER,
    reorder_quantity INTEGER,
    inventory_value DECIMAL(12,4),
    reorder BOOLEAN,
    container VARCHAR(15),
    location VARCHAR(15),
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY (warehouseid),

    CONSTRAINT warehouse_fk_01 FOREIGN KEY (product_id) REFERENCES products(productid)
);

CREATE TABLE IF NOT EXISTS purchase_orders (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    date_purchase TIMESTAMP,
    total_amount DOUBLE DEFAULT 0.0,
    state VARCHAR(25),
    customerid BIGINT(20) NOT NULL,
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT purchase_orders_fk_01 FOREIGN KEY (customerid) REFERENCES customers(customerid)
);

CREATE TABLE IF NOT EXISTS lineitem (
    lineitemid BIGINT(20) NOT NULL AUTO_INCREMENT,
    productid BIGINT(20) NOT NULL,
    quantity DOUBLE DEFAULT 0.0,
    version BIGINT(20) DEFAULT 0,

    PRIMARY KEY(lineitemid),
    
    CONSTRAINT line_item_fk FOREIGN KEY (productid) REFERENCES products(productid)
);

CREATE TABLE IF NOT EXISTS purchase_orders_lineitems (
    lineitems_lineitemid BIGINT(20) NOT NULL,
    purchase_orders_id BIGINT(20) NOT NULL,

    CONSTRAINT purchase_orders_lineitem_01 UNIQUE (lineitems_lineitemid),
    CONSTRAINT purchase_orders_lineitem_02 FOREIGN KEY (lineitems_lineitemid) REFERENCES lineitem(lineitemid),
    CONSTRAINT purchase_orders_lineitem_03 FOREIGN KEY (purchase_orders_id) REFERENCES purchase_orders(id)
);
