package nadunandchaula.jobschedulesystem;

import javafx.scene.control.Alert;

import java.sql.*;

public class LoginModel {
    private static final String URL = "jdbc:mysql://localhost:3306/jobschedulesystem";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Database Error");
            alert.setContentText(String.valueOf(e));
            alert.showAndWait();
            e.printStackTrace();
            return null;
        }
    }

    boolean validateLogin(String userID, String password) {
        Connection conn = getConnection();
        if (conn == null) {
            return false;
        }
        try {
            String query = "SELECT * FROM login WHERE id = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login successful for user: " + userID);
                return true;
            } else {
                System.out.println("Login failed for user: " + userID);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }
}
