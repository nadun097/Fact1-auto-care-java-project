package nadunandchaula.jobschedulesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.MessagingException;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LoginController extends Thread {
    @FXML
    private PasswordField PasswordBox;
    @FXML
    private TextField textUserID;
    @FXML
    private Button loginBtn;
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String userID = textUserID.getText();
        String password = PasswordBox.getText();
        LoginModel loginmodel = new LoginModel();
        if (loginmodel.validateLogin(userID, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + userID + "!");
            try {
                sendLoginEmail(userID);
            } catch (MessagingException | SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Email Error", "Failed to send login email.");
            }
            // Close login form
            closeLoginForm();
            // Load front page
            try {
                loadFrontPage(event);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Loading Error", "Failed to load front page.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid User ID or Password.");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadFrontPage(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FrontView.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load FrontView.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void closeLoginForm() {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
    }
    private void sendLoginEmail(String userID) throws MessagingException, SQLException {
        LoginModel loginmodel = new LoginModel();
        Connection conn = loginmodel.getConnection();
        if (conn == null) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Unable to connect to the database.");
            return;
        }

        String messageQuery = "SELECT * FROM login WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(messageQuery);
        statement.setString(1, userID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String userName = resultSet.getString(1);
            String userEmail = resultSet.getString(5);

            if (userEmail != null && !userEmail.isEmpty()) {
                String subject = "Login Notification";
                String body = "Dear " + userName + ",\n\nYou have successfully logged in.\n\nBest regards,\nFact One Auto Care";
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EmailUtil.sendEmail(userEmail, subject, body);
            }
        }
        resultSet.close();
        statement.close();
        conn.close();
    }

    public void closeBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public static class EmailUtil {
        public static void sendEmail(String to, String subject, String body) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            final String username = "factoneautocare@gmail.com";
            final String password = "gtfk fpnj axvx pqgh ";

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);
                System.out.println("Email sent successfully!");
            } catch (MessagingException e) {
                throw new RuntimeException(e);

            }

        }

    }
}
