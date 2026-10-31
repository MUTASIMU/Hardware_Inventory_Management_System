-- ****************************************************************************
-- Hardware Inventory & Management System DB
-- ****************************************************************************

CREATE DATABASE  IF NOT EXISTS Hardware_inventory CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE Hardware_inventory;

-- SECURITY / ACCESS CONTROL

CREATE TABLE roles (
                       role_id         INT AUTO_INCREMENT PRIMARY KEY,
                       role_name       VARCHAR(50) NOT NULL UNIQUE,
                       description     VARCHAR(255),
                       created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE permissions (
                             permission_id   INT AUTO_INCREMENT PRIMARY KEY,
                             permission_code VARCHAR(100) NOT NULL UNIQUE,   -- e.g. PRODUCT_CREATE, SALE_VOID
                             module          VARCHAR(50) NOT NULL,
                             description     VARCHAR(255)
);

CREATE TABLE role_permissions (
                                  role_id         INT NOT NULL,
                                  permission_id   INT NOT NULL,
                                  PRIMARY KEY (role_id, permission_id),
                                  FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
                                  FOREIGN KEY (permission_id) REFERENCES permissions(permission_id) ON DELETE CASCADE
);

CREATE TABLE users (
                       user_id         INT AUTO_INCREMENT PRIMARY KEY,
                       username        VARCHAR(50) NOT NULL UNIQUE,
                       password_hash   VARCHAR(255) NOT NULL,
                       full_name       VARCHAR(100) NOT NULL,
                       email           VARCHAR(120) UNIQUE,
                       phone           VARCHAR(30),
                       role_id         INT NOT NULL,
                       settings_id     INT NULL,
                       is_active       TINYINT(1) NOT NULL DEFAULT 1,
                       must_change_password TINYINT(1) NOT NULL DEFAULT 0,
                       failed_login_attempts INT NOT NULL DEFAULT 0,
                       locked_until    DATETIME NULL,
                       last_login      DATETIME NULL,
                       created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (role_id) REFERENCES roles(role_id),
                       INDEX idx_users_role (role_id)
);

-- CATALOG: CATEGORIES / UNITS / PRODUCTS / SUPPLIERS / CUSTOMERS

CREATE TABLE categories (
                            category_id     INT AUTO_INCREMENT PRIMARY KEY,
                            category_name   VARCHAR(100) NOT NULL UNIQUE,
                            description     VARCHAR(255),
                            is_active       TINYINT(1) NOT NULL DEFAULT 1,
                            created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE units (
                       unit_id         INT AUTO_INCREMENT PRIMARY KEY,
                       unit_name       VARCHAR(50) NOT NULL UNIQUE,      -- e.g. Bag, Piece, Meter, Litre
                       unit_symbol     VARCHAR(10) NOT NULL,              -- e.g. bag, pc, m, L
                       is_active       TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE suppliers (
                           supplier_id     INT AUTO_INCREMENT PRIMARY KEY,
                           supplier_name   VARCHAR(150) NOT NULL,
                           contact_person  VARCHAR(100),
                           phone           VARCHAR(30),
                           email           VARCHAR(120),
                           address         VARCHAR(255),
                           tin_number      VARCHAR(50),
                           is_active       TINYINT(1) NOT NULL DEFAULT 1,
                           created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           INDEX idx_supplier_name (supplier_name)
);

CREATE TABLE customers (
                           customer_id     INT AUTO_INCREMENT PRIMARY KEY,
                           customer_name   VARCHAR(150) NOT NULL,
                           customer_type   ENUM('WALK_IN','REGISTERED') NOT NULL DEFAULT 'REGISTERED',
                           phone           VARCHAR(30),
                           email           VARCHAR(120),
                           address         VARCHAR(255),
                           credit_limit    DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                           is_active       TINYINT(1) NOT NULL DEFAULT 1,
                           created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           INDEX idx_customer_name (customer_name)
);

CREATE TABLE products (
                          product_id      INT AUTO_INCREMENT PRIMARY KEY,
                          sku             VARCHAR(50) NOT NULL UNIQUE,
                          product_name    VARCHAR(150) NOT NULL,
                          category_id     INT NOT NULL,
                          unit_id         INT NOT NULL,
                          description     VARCHAR(255),
                          cost_price      DECIMAL(14,2) NOT NULL DEFAULT 0.00,   -- last/avg purchase cost
                          selling_price   DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                          reorder_level   INT NOT NULL DEFAULT 10,
                          current_stock   INT NOT NULL DEFAULT 0,                -- denormalized, maintained via triggers/service
                          is_active       TINYINT(1) NOT NULL DEFAULT 1,
                          created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (category_id) REFERENCES categories(category_id),
                          FOREIGN KEY (unit_id) REFERENCES units(unit_id),
                          INDEX idx_product_name (product_name),
                          INDEX idx_product_category (category_id),
                          CONSTRAINT chk_cost_nonneg CHECK (cost_price >= 0),
                          CONSTRAINT chk_price_nonneg CHECK (selling_price >= 0),
                          CONSTRAINT chk_stock_nonneg CHECK (current_stock >= 0)
);

-- PURCHASING

CREATE TABLE purchases (
                           purchase_id     INT AUTO_INCREMENT PRIMARY KEY,
                           purchase_no     VARCHAR(30) NOT NULL UNIQUE,
                           supplier_id     INT NOT NULL,
                           user_id         INT NOT NULL,               -- who recorded it
                           purchase_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           subtotal        DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                           discount        DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                           total_amount    DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                           amount_paid     DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                           status          ENUM('PENDING','CONFIRMED','CANCELLED') NOT NULL DEFAULT 'PENDING',
                           notes           VARCHAR(255),
                           created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id),
                           FOREIGN KEY (user_id) REFERENCES users(user_id),
                           INDEX idx_purchase_date (purchase_date),
                           INDEX idx_purchase_supplier (supplier_id)
);

CREATE TABLE purchase_items (
                                purchase_item_id INT AUTO_INCREMENT PRIMARY KEY,
                                purchase_id     INT NOT NULL,
                                product_id      INT NOT NULL,
                                quantity        INT NOT NULL,
                                unit_cost       DECIMAL(14,2) NOT NULL,
                                line_total      DECIMAL(14,2) NOT NULL,
                                FOREIGN KEY (purchase_id) REFERENCES purchases(purchase_id) ON DELETE CASCADE,
                                FOREIGN KEY (product_id) REFERENCES products(product_id),
                                CONSTRAINT chk_purchase_qty_pos CHECK (quantity > 0)
);

-- SALES / POS

CREATE TABLE sales (
                       sale_id         INT AUTO_INCREMENT PRIMARY KEY,
                       sale_no         VARCHAR(30) NOT NULL UNIQUE,
                       customer_id     INT NULL,                       -- NULL allowed for walk-in without registration
                       user_id         INT NOT NULL,                    -- cashier
                       sale_date       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       sale_type       ENUM('CASH','CREDIT') NOT NULL DEFAULT 'CASH',
                       subtotal        DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                       discount        DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                       total_amount    DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                       amount_paid     DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                       balance_due     DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                       change_given    DECIMAL(14,2) NOT NULL DEFAULT 0.00,
                       status          ENUM('COMPLETED','CANCELLED') NOT NULL DEFAULT 'COMPLETED',
                       created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
                       FOREIGN KEY (user_id) REFERENCES users(user_id),
                       INDEX idx_sale_date (sale_date),
                       INDEX idx_sale_customer (customer_id),
                       CONSTRAINT chk_sale_total_nonneg CHECK (total_amount >= 0)
);

CREATE TABLE sale_items (
                            sale_item_id    INT AUTO_INCREMENT PRIMARY KEY,
                            sale_id         INT NOT NULL,
                            product_id      INT NOT NULL,
                            quantity        INT NOT NULL,
                            unit_price      DECIMAL(14,2) NOT NULL,
                            line_total      DECIMAL(14,2) NOT NULL,
                            FOREIGN KEY (sale_id) REFERENCES sales(sale_id) ON DELETE CASCADE,
                            FOREIGN KEY (product_id) REFERENCES products(product_id),
                            CONSTRAINT chk_sale_qty_pos CHECK (quantity > 0)
);

CREATE TABLE receipts (
                          receipt_id      INT AUTO_INCREMENT PRIMARY KEY,
                          receipt_no      VARCHAR(30) NOT NULL UNIQUE,
                          sale_id         INT NOT NULL,
                          generated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          file_path       VARCHAR(255),
                          FOREIGN KEY (sale_id) REFERENCES sales(sale_id)
);

-- CUSTOMER PAYMENTS / DEBTOR MANAGEMENT

CREATE TABLE customer_payments (
                                   payment_id      INT AUTO_INCREMENT PRIMARY KEY,
                                   payment_no      VARCHAR(30) NOT NULL UNIQUE,
                                   customer_id     INT NOT NULL,
                                   sale_id         INT NULL,                 -- optional link to a specific invoice
                                   user_id         INT NOT NULL,
                                   payment_date    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   amount          DECIMAL(14,2) NOT NULL,
                                   payment_method  ENUM('CASH','MOBILE_MONEY','BANK_TRANSFER','CHEQUE') NOT NULL DEFAULT 'CASH',
                                   notes           VARCHAR(255),
                                   FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
                                   FOREIGN KEY (sale_id) REFERENCES sales(sale_id),
                                   FOREIGN KEY (user_id) REFERENCES users(user_id),
                                   CONSTRAINT chk_payment_amount_pos CHECK (amount > 0)
);

-- STOCK MOVEMENTS / ADJUSTMENTS

CREATE TABLE stock_movements (
                                 movement_id     INT AUTO_INCREMENT PRIMARY KEY,
                                 product_id      INT NOT NULL,
                                 movement_type   ENUM('PURCHASE_IN','SALE_OUT','ADJUSTMENT_IN','ADJUSTMENT_OUT',
                          'DAMAGE_OUT','LOSS_OUT','COUNT_CORRECTION') NOT NULL,
                                 reference_type  ENUM('PURCHASE','SALE','ADJUSTMENT','STOCK_COUNT') NOT NULL,
                                 reference_id    INT NOT NULL,                 -- id of purchase/sale/adjustment
                                 quantity_change INT NOT NULL,                 -- positive = increase, negative = decrease
                                 stock_before    INT NOT NULL,
                                 stock_after     INT NOT NULL,
                                 user_id         INT NOT NULL,
                                 movement_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 notes           VARCHAR(255),
                                 FOREIGN KEY (product_id) REFERENCES products(product_id),
                                 FOREIGN KEY (user_id) REFERENCES users(user_id),
                                 INDEX idx_movement_product (product_id),
                                 INDEX idx_movement_date (movement_date)
);

CREATE TABLE stock_adjustments (
                                   adjustment_id   INT AUTO_INCREMENT PRIMARY KEY,
                                   adjustment_no   VARCHAR(30) NOT NULL UNIQUE,
                                   product_id      INT NOT NULL,
                                   adjustment_type ENUM('DAMAGE','LOSS','COUNT_CORRECTION','OTHER') NOT NULL,
                                   quantity_before INT NOT NULL,
                                   quantity_change INT NOT NULL,     -- can be negative
                                   quantity_after  INT NOT NULL,
                                   reason          VARCHAR(255) NOT NULL,
                                   user_id         INT NOT NULL,
                                   approved_by     INT NULL,
                                   adjustment_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (product_id) REFERENCES products(product_id),
                                   FOREIGN KEY (user_id) REFERENCES users(user_id),
                                   FOREIGN KEY (approved_by) REFERENCES users(user_id)
);

-- EXPENSES

CREATE TABLE expense_categories (
                                    expense_category_id INT AUTO_INCREMENT PRIMARY KEY,
                                    category_name   VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE expenses (
                          expense_id      INT AUTO_INCREMENT PRIMARY KEY,
                          expense_no      VARCHAR(30) NOT NULL UNIQUE,
                          expense_category_id INT NOT NULL,
                          description     VARCHAR(255) NOT NULL,
                          amount          DECIMAL(14,2) NOT NULL,
                          expense_date    DATE NOT NULL,
                          user_id         INT NOT NULL,
                          payment_method  ENUM('CASH','MOBILE_MONEY','BANK_TRANSFER') NOT NULL DEFAULT 'CASH',
                          created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (expense_category_id) REFERENCES expense_categories(expense_category_id),
                          FOREIGN KEY (user_id) REFERENCES users(user_id),
                          CONSTRAINT chk_expense_amount_pos CHECK (amount > 0)
);

-- AUDIT LOG

CREATE TABLE audit_logs (
                            audit_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id         INT NULL,
                            username        VARCHAR(50),
                            action          VARCHAR(100) NOT NULL,        -- e.g. LOGIN, CREATE_PRODUCT, VOID_SALE
                            entity_type     VARCHAR(50),
                            entity_id       INT,
                            details         TEXT,
                            ip_address      VARCHAR(45),
                            created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(user_id),
                            INDEX idx_audit_date (created_at),
                            INDEX idx_audit_user (user_id)
);

-- SETTINGS

CREATE TABLE settings (
                          settings_id     INT AUTO_INCREMENT PRIMARY KEY,
                          user_id         INT NOT NULL,
                          setting_key     VARCHAR(100),
                          setting_value   VARCHAR(500),
                          description     VARCHAR(255),
                          updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          CONSTRAINT FOREIGN KEY(user_id) REFERENCES users(user_id),
                          INDEX(setting_key)
);
