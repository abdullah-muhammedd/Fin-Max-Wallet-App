package fm.fm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInPage {
    //FX Components
    @FXML
    TextField user;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label error_user;
    @FXML
    Label error_pass;

    //Handling event
    public void LogIn(ActionEvent ignoredActionEvent) throws SQLException, ClassNotFoundException {
        Statement st = MainConnection.connectCreateStatement();
        try {
            String query = "select password from login where username = \"" + user.getText() + "\"";
            ResultSet resultSet = st.executeQuery(query);
            if (!resultSet.next()) {
                error_user.setOpacity(1);
            } else {
                if (resultSet.getString(1).equals(password.getText())) {
                    HelloApplication.MAINDATA.setMainData(user.getText());
                    HelloApplication.stage.setTitle("Fin Max");
                    HelloApplication.stage.setResizable(false);
                    HelloApplication.stage.setScene(HelloApplication.buildMainPage());
                    HelloApplication.stage.show();
                } else {
                    error_pass.setOpacity(1);
                }
            }
        } catch (Exception ignored) {
        }
    }

}
