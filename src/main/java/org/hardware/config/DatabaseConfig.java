package org.hardware.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Central database configuration and connection pool.
 * Reads connection details from src/main/resources/db.properties,
 * falling back to sensible local defaults.
 */
public class DatabaseConfig {
        private static volatile HikariDataSource dataSource;
        private static final Object LOCK = new Object();

        private DatabaseConfig() {}

        public static HikariDataSource getDataSource() {
            if (dataSource == null) {
                synchronized (LOCK) {
                    if (dataSource == null) {
                        dataSource = build();
                    }
                }
            }
            return dataSource;
        }

        public static Connection getConnection() throws SQLException {
            return getDataSource().getConnection();
        }

        private static HikariDataSource build() {
            Properties props = new Properties();
            try (InputStream in = DatabaseConfig.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {
                if (in != null) {
                    props.load(in);
                }
            } catch (IOException e) {
                System.err.println("Could not load db.properties, using defaults: " + e.getMessage());
            }

            String host = props.getProperty("db.host", "localhost");
            String port = props.getProperty("db.port", "3306");
            String name = props.getProperty("db.name", "Hardware_inventory");
            String user = props.getProperty("db.user", "root");
            String pass = props.getProperty("db.password", "");
/**
 * useSSL=false->Disables Secure Sockets Layer(SSL) encryption for database connection.
 * serverTimezone=Africa/Kampala-> Explicitly tells JDBC driver which time zone the database server is running in.
 * allowPublicKeyRetrieval=true-> Allows the client to request the RSA public key from the MySQL server automatically.
 * characterEncoding=UTF-8-> Forces all data traveling between the app and the database to use the UTF-8 character format.
*/
            String url = String.format(
                    "jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=Africa/Kampala&allowPublicKeyRetrieval=true&characterEncoding=UTF-8",
                    host, port, name);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(pass);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(10000);
            config.setPoolName("DB-Pool");

            return new HikariDataSource(config);
        }

        public static void shutdown() {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }
