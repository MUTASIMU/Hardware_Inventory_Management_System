package org.hardware.util;

import org.hardware.model.User;

import java.util.Set;
/** Singleton holding the current authenticated session (single-user desktop app). */

public class SessionManager {

        private static User currentUser;
        private static Set<String> currentPermissions = Set.of();
        private static long lastActivityMillis = System.currentTimeMillis();

        private SessionManager() {}

        public static void login(User user, Set<String> permissions) {
            currentUser = user;
            currentPermissions = permissions;
            touch();
        }

        public static void logout() {
            currentUser = null;
            currentPermissions = Set.of();
        }

        public static User getCurrentUser() {
            return currentUser;
        }

        public static boolean isLoggedIn() {
            return currentUser != null;
        }

        public static boolean hasPermission(String permissionCode) {
            return currentUser != null && currentPermissions.contains(permissionCode);
        }

        public static void touch() {
            lastActivityMillis = System.currentTimeMillis();
        }

        public static boolean isSessionExpired(int timeoutMinutes) {
            long elapsed = System.currentTimeMillis() - lastActivityMillis;
            return elapsed > (timeoutMinutes * 60_000L);
        }

}
