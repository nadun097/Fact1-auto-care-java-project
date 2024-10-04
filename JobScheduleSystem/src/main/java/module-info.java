module nadunandchaula.jobschedulesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.sql;


    opens nadunandchaula.jobschedulesystem to javafx.fxml;
    exports nadunandchaula.jobschedulesystem;
    opens nadunandchaula.jobschedulesystem.FrontPage to javafx.fxml;
    exports nadunandchaula.jobschedulesystem.FrontPage;
}