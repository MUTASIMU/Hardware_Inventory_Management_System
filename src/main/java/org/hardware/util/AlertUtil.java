package org.hardware.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
/** Consistent success / error / warning / confirmation dialogs across the app. */
public class AlertUtil {
        private AlertUtil() {}

        public static void success(String title, String message) {
            show(Alert.AlertType.INFORMATION, "Success", title, message);
        }

        public static void error(String title, String message) {
            show(Alert.AlertType.ERROR, "Error", title, message);
        }

        public static void warning(String title, String message) {
            show(Alert.AlertType.WARNING, "Warning", title, message);
        }

        public static boolean confirm(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Please Confirm");
            alert.setHeaderText(title);
            alert.setContentText(message);
            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
            alert.getButtonTypes().setAll(yes, no);
            Optional<ButtonType> result = alert.showAndWait();
            return result.isPresent() && result.get() == yes;
        }

        private static void show(Alert.AlertType type, String windowTitle, String header, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(windowTitle);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        }

}
