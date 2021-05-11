DROP TABLE IF EXISTS enterprises;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS group_authorities;
DROP TABLE IF EXISTS group_members;

DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS suppliers;

DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_prices;

DROP TABLE IF EXISTS images_product;

DROP TABLE IF EXISTS line_items_warehouse;
DROP TABLE IF EXISTS warehouse_card_products;
DROP TABLE IF EXISTS warehouse_journal;
DROP TABLE IF EXISTS warehouse_cards;

DROP TABLE IF EXISTS warehouses;

DROP TABLE IF EXISTS transport_documents;
DROP TABLE IF EXISTS line_item_ddts;
DROP TABLE IF EXISTS transport_documents_line_itemddts;

DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS line_items_invoice;
DROP TABLE IF EXISTS invoices_line_items_invoice;

DROP TABLE IF EXISTS purchase_orders;
DROP TABLE IF EXISTS purchase_orders_line_items;
DROP TABLE IF EXISTS line_items_purchase_orders;

DROP TABLE IF EXISTS sales_orders;
DROP TABLE IF EXISTS line_items_sales_orders;
DROP TABLE IF EXISTS sales_orders_line_items;

DROP TABLE IF EXISTS accounts;

CREATE TABLE IF NOT EXISTS enterprises(
    id BIGINT  NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(255),
    price_list VARCHAR(255),

    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS users(
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,

    PRIMARY KEY(username)
);

CREATE TABLE authorities (
    username VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL,

    CONSTRAINT un_authoritues_01 UNIQUE (username,authority),
    CONSTRAINT fk_authorities_01 FOREIGN KEY(username) REFERENCES users(username)
);


CREATE TABLE groups (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 0),
    group_name VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE group_authorities (
    group_id BIGINT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_group_authorities_group FOREIGN KEY(group_id) REFERENCES groups(id)
);

CREATE TABLE group_members (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 0),
    username VARCHAR(50) NOT NULL,
    group_id BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_group_members_group FOREIGN KEY(group_id) REFERENCES groups(id)
);

CREATE TABLE IF NOT EXISTS address (
    id BIGINT NOT NULL AUTO_INCREMENT,
    city VARCHAR(50),
    street VARCHAR(50),
    house_number VARCHAR(10),
    zip_code VARCHAR(10),
    country VARCHAR(50),
    state VARCHAR(20),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS contacts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    cellular VARCHAR(20),
    landline_phone VARCHAR(20),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    customer_code VARCHAR(15),
    firstname VARCHAR(15),
    lastname VARCHAR(25),
    email VARCHAR(255) UNIQUE,
    username VARCHAR(255),
    password VARCHAR(255),
    fiscal_code VARCHAR(16) UNIQUE,
    description VARCHAR(255),
    address_id BIGINT,
    contact_id BIGINT,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),
    CONSTRAINT customers_fk_01 FOREIGN KEY (address_id) REFERENCES address(id),
    CONSTRAINT customers_fk_02 FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE IF NOT EXISTS suppliers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    supplier_code VARCHAR(15),
    name VARCHAR(255) NOT NULL,
    vat VARCHAR(15),
    legal_form VARCHAR(55),
    registered_office VARCHAR(255),
    address_id BIGINT,
    contact_id BIGINT,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT suppliers_fk_01 FOREIGN KEY (address_id) REFERENCES address(id),
    CONSTRAINT suppliers_fk_02 FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(254),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS images_product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    image_byte BLOB,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    subtitle VARCHAR(255),
    description VARCHAR(255),
    product_code VARCHAR(20) NOT NULL UNIQUE,
    price DECIMAL(12,4),
    image_id BIGINT,
    category_id BIGINT,
    version BIGINT DEFAULT 0,

    PRIMARY KEY (id),

    CONSTRAINT products_fk_01 FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT products_fk_02 FOREIGN KEY (image_id) REFERENCES images_product(id)
);

CREATE TABLE IF NOT EXISTS product_prices(
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_code VARCHAR(25) NOT NULL UNIQUE,
    price DECIMAL(12,4),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS warehouses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    version BIGINT DEFAULT 0,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS line_items_warehouse (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_code BIGINT NOT NULL,
    price DECIMAL(12,4) DEFAULT 0.0,
    quantity DOUBLE DEFAULT 0.0,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS warehouse_journal (
    id BIGINT NOT NULL AUTO_INCREMENT,
    warehouse_id BIGINT NOT NULL,
    movement_date DATE,
    causal VARCHAR(25),
    product_id BIGINT NOT NULL,
    document_type VARCHAR(25) NOT NULL,
    document_number VARCHAR(25) NOT NULL,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT warehouse_journal_fk_01 FOREIGN KEY(warehouse_id) REFERENCES warehouses(id)
);

CREATE TABLE IF NOT EXISTS warehouse_card_products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_code VARCHAR(15),
    sku VARCHAR(50),
    reorder_quantity INTEGER,
    unit VARCHAR(25),
    container VARCHAR(25),
    location VARCHAR(25),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE  TABLE IF NOT EXISTS warehouse_cards (
    id BIGINT NOT NULL AUTO_INCREMENT,
    movement_date DATE NOT NULL,
    warehouse_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    causal VARCHAR(25),
    document_type VARCHAR(25),
    document_number VARCHAR(25),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT warehouse_cards_fk_01 FOREIGN KEY(warehouse_id) REFERENCES warehouses(id),
    CONSTRAINT warehouse_cards_fk_02 FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS transport_documents (
    id BIGINT NOT NULL AUTO_INCREMENT,
    transferor_code VARCHAR(25),
    transferee_code VARCHAR(25),
    movement_date DATE,

    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),
);

CREATE TABLE IF NOT EXISTS line_item_ddts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(255),
    product_code VARCHAR(25),
    price DECIMAL(12,4),
    quantity INTEGER,
    unit_measure VARCHAR(15),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS transport_documents_line_itemddts (
    transport_document_id BIGINT NOT NULL,
    line_itemddt_id BIGINT NOT NULL,

    CONSTRAINT transport_document_lineitemddts_uq_01 UNIQUE (line_itemddt_id),
    CONSTRAINT transport_document_lineitemddts_fk_01 FOREIGN KEY (transport_document_id) REFERENCES transport_documents(id),
    CONSTRAINT transport_document_lineitemddts_fk_02 FOREIGN KEY (line_itemddt_id) REFERENCES line_item_ddts(id)
);

CREATE TABLE IF NOT EXISTS invoices (
    id BIGINT NOT NULL AUTO_INCREMENT,
    issuing_date DATE,
    transferor_code VARCHAR(25),
    transferee_code VARCHAR(25),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),
);

CREATE TABLE IF NOT EXISTS line_items_invoice (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(255),
    product_code VARCHAR(25),
    price DECIMAL(12,4),
    quantity INTEGER,
    unit_measure VARCHAR(15),
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS invoices_line_items_invoice (
    invoice_id BIGINT NOT NULL,
    line_item_invoice_id BIGINT NOT NULL,

    CONSTRAINT invoices_lineitems_uq_01 UNIQUE (line_item_invoice_id),
    CONSTRAINT invoices_lineitems_fk_01 FOREIGN KEY (invoice_id) REFERENCES invoices(id),
    CONSTRAINT invoices_lineitems_fk_02 FOREIGN KEY (line_item_invoice_id) REFERENCES line_items_invoice(id)
);

CREATE TABLE IF NOT EXISTS line_items_purchase_orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    price DECIMAL(12,4) DEFAULT 0.0,
    quantity DOUBLE DEFAULT 0.0,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT line_items_purchase_order_fk_01 FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS purchase_orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date_purchase TIMESTAMP,
    total_amount DOUBLE DEFAULT 0.0,
    state VARCHAR(25),
    customer_id BIGINT NOT NULL,
    version BIGINT,

    PRIMARY KEY(id),

    CONSTRAINT purchase_orders_fk_01 FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS purchase_orders_line_items (
    purchase_orders_id BIGINT NOT NULL,
    line_items_purchase_orders_id BIGINT NOT NULL,

    CONSTRAINT purchase_orders_lineitem_02 UNIQUE (line_items_purchase_orders_id),
    CONSTRAINT purchase_orders_lineitem_01 FOREIGN KEY (purchase_orders_id) REFERENCES purchase_orders(id),
    CONSTRAINT purchase_orders_lineitem_03 FOREIGN KEY (line_items_purchase_orders_id) REFERENCES line_items_purchase_orders(id)
);

CREATE TABLE IF NOT EXISTS line_items_sales_orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    price DECIMAL(12,4) DEFAULT 0.0,
    quantity DOUBLE DEFAULT 0.0,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT line_items_sales_orders_fk_01 FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS sales_orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date_sale TIMESTAMP,
    total_amount DOUBLE DEFAULT 0.0,
    state VARCHAR(25),
    supplier_id BIGINT NOT NULL,
    version BIGINT DEFAULT 0,

    PRIMARY KEY(id),

    CONSTRAINT sales_orders_fk_01 FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE IF NOT EXISTS sales_orders_line_items (
    sales_orders_id BIGINT NOT NULL,
    line_items_sales_order_id BIGINT NOT NULL,

    CONSTRAINT sales_orders_line_item_01 UNIQUE (line_items_sales_order_id),
    CONSTRAINT sales_orders_line_item_02 FOREIGN KEY (line_items_sales_order_id) REFERENCES line_items_sales_orders(id),
    CONSTRAINT sales_orders_line_item_03 FOREIGN KEY (sales_orders_id) REFERENCES sales_orders(id)
);

CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    balance DECIMAL(12,4),

    PRIMARY KEY(id)
);
