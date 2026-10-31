package org.hardware.util;

import org.mindrot.jbcrypt.BCrypt;

/** Password hashing and verification. Never store or compare plain-text passwords. */
public class PasswordUtil {
       //cost factor(10-12)
        private static final int BCRYPT_ROUNDS = 12;

        private PasswordUtil() {}

        public static String hash(String plainPassword) {
            if (plainPassword == null || plainPassword.isBlank()) {
                throw new IllegalArgumentException("Password cannot be empty");
            }
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
        }

        public static boolean verify(String plainPassword, String hashedPassword) {
            if (plainPassword == null || hashedPassword == null) return false;
            try {
                return BCrypt.checkpw(plainPassword, hashedPassword);
            } catch (IllegalArgumentException e) {
                // Malformed hash in DB
                return false;
            }
        }

        /** Basic password strength check used on user creation / change-password screens. */
        public static boolean isStrongEnough(String plainPassword) {
            if (plainPassword == null || plainPassword.length() < 8) return false;
            boolean hasUpper = plainPassword.chars().anyMatch(Character::isUpperCase);
            boolean hasLower = plainPassword.chars().anyMatch(Character::isLowerCase);
            boolean hasDigit = plainPassword.chars().anyMatch(Character::isDigit);
            return hasUpper && hasLower && hasDigit;
        }

}
