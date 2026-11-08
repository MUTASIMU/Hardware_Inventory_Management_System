package org.hardware.util;

import org.hardware.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generates human-readable, sequential-looking document numbers such as
 * PO-20260815-0001, INV-20260815-0007, PAY-20260815-0002.
 * The numeric suffix is a per-run atomic counter combined with a random
 * component to keep numbers unique even across sessions without a DB round trip;
 * the DB UNIQUE constraint is the real guarantee of uniqueness, and the DAO
 * layer retries generation on a rare collision.
 */
public class DocumentNumberGenerator {
        private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
        private static final AtomicInteger COUNTER = new AtomicInteger(1);
        //Safety checks to handle midnight transitions while the app is running
        private static LocalDate lastGeneratedDate = LocalDate.now();
        //Static Initializer Block: Automatically queries the DB on app startup
        static{
         syncCounterFromDatabase();
         }
        private DocumentNumberGenerator() {}
        public static void setCounter(int highestSequence){
            COUNTER.set(highestSequence);
        }
/**
 * Queries the DB for the highest sales index from today and initializes the counter.
 */
        private static void syncCounterFromDatabase(){
            String today = LocalDate.now().format(DATE_FMT);
            String sql = "SELECT sale_no FROM sales WHERE sale_no LIKE ? ORDER BY sale_id DESC LIMIT 1";

                try(Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setString(1,"INV-" + today + "-%");
                    try(ResultSet rs = ps.executeQuery()){
                        if(rs.next()){
                            String lastSaleNo = rs.getString("sale_no");
                            String suffix = lastSaleNo.substring(lastSaleNo.lastIndexOf("-") + 1);
                            int lastLoggedSequence = Integer.parseInt(suffix);
                            //Resume seamlessly right after the last sequence Number
                            setCounter(lastLoggedSequence + 1);
                        }else{
                            //No sales today yet, start clean at 1
                            setCounter(1);
                        }
                    }
                }
             catch (SQLException e) {
                System.out.println("Could not sync sequence counter: " + e.getMessage());
                setCounter(1);//fall back safely
            }
        }
        /**
        * Main production sequence generation routine
        * */
        public static synchronized String generate(String prefix) {
            LocalDate today = LocalDate.now();
            //Midnight safety check: Rest the sequence to 1 when a new day arrives
            if(!today.isEqual(lastGeneratedDate)){
                setCounter(1);
                lastGeneratedDate = today;
            }
            //Get current date
            String datePart = today.format(DATE_FMT);
            //Get and increment the sequence number
            int sequenceNum = COUNTER.getAndIncrement();
            //Format the sequence  with leading zeros(4 digits wide)
            String sequencePart = String.format("%04d",sequenceNum);
            //Combine into final document format
            return String.format("%s-%s-%s", prefix.toUpperCase(), datePart, sequencePart);
        }

        public static String purchaseNo()   { return generate("PO"); }
        public static String saleNo()       { return generate("INV"); }
        public static String receiptNo()    { return generate("RCT"); }
        public static String paymentNo()    { return generate("PAY"); }
        public static String adjustmentNo() { return generate("ADJ"); }
        public static String expenseNo()    { return generate("EXP"); }
}
