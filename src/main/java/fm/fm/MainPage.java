package fm.fm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainPage implements Initializable {

    //FX components
    @FXML
    LineChart balance_chart;
    @FXML
    Button logout_btn;
    @FXML
    Button history_btn;
    @FXML
    private
    TextField text_username;
    @FXML
    private
    TextField text_name;
    @FXML
    private
    TextField text_balance;

    //Handling events
    public void accountBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildMainPage());
        HelloApplication.stage.show();
    }

    public void depositBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildDepositPage());
        HelloApplication.stage.show();
    }

    public void withdrawBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildWithdrawPage());
        HelloApplication.stage.show();
    }

    public void aboutBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.stage.setScene(HelloApplication.buildAboutPage());
        HelloApplication.stage.show();
    }

    public void historyBtnOnAction() {
        balance_chart.setVisible(!balance_chart.isVisible());
    }

    public void logoutBtnOnAction() throws IOException {
        MainConnection.dropViews();
        HelloApplication.MAINDATA = new MainData();
        HelloApplication.stage.setScene(HelloApplication.buildLogInPage());
        HelloApplication.stage.show();

    }

    //Initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text_username.setText(HelloApplication.MAINDATA.getUSERNAME());
        text_name.setText(HelloApplication.MAINDATA.getNAME());
        text_balance.setText(HelloApplication.MAINDATA.getBALANCE());
        balance_chart.getData().add(HelloApplication.MAINDATA.getCHART());
    }
}