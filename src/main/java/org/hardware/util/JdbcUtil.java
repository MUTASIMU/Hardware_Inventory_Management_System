package org.hardware.util;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** Small helpers shared by DAO implementations to reduce JDBC boilerplate. */
public class JdbcUtil {

        private JdbcUtil() {}

        public static void closeQuietly(AutoCloseable c) {
            if (c != null) {
                try { c.close(); } catch (Exception ignored) { }
            }
        }

        public static LocalDateTime toLocalDateTime(Timestamp ts) {
            return ts == null ? null : ts.toLocalDateTime();
        }

        public static Timestamp toTimestamp(LocalDateTime dt) {
            return dt == null ? null : Timestamp.valueOf(dt);
        }

        public static LocalDate toLocalDate(Date d) {
            return d == null ? null : d.toLocalDate();
        }

        public static Date toSqlDate(LocalDate d) {
            return d == null ? null : Date.valueOf(d);
        }

        public static Integer getNullableInt(ResultSet rs, String column) throws SQLException {
            int value = rs.getInt(column);
            return rs.wasNull() ? null : value;
        }

        /** Retrieves the auto-generated key from an INSERT PreparedStatement. */
        public static int getGeneratedId(PreparedStatement ps) throws SQLException {
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            throw new SQLException("No generated key returned");
        }

}
