package fm.fm;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DepositPage implements Initializable {

    //FX Components
    @FXML
    Button done;
    @FXML
    TextField value;
    @FXML
    DatePicker date;
    @FXML
    Label done_label;
    @FXML
    TableView table;
    @FXML
    TableColumn value_column;
    @FXML
    TableColumn date_column;


    //Handling events
    public void accountBtnOnAction(ActionEvent ignoredActionEvent) throws IOException {
        HelloApplication.MAINDATA.setBALANCE(MainConnection.getData(HelloApplication.MAINDATA.getUSERNAME())[3]);
        HelloApplication.MAINDATA.setCHART(HelloApplication.MAINDATA.lineChartData());
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

    public void doneBtnOnAction(ActionEvent ignoredActionEvent) {
        if (value.getText().isEmpty()) {
            return;
        }
        MainConnection.createDeposit(Double.parseDouble(value.getText()), date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        done_label.setOpacity(1);
    }

    //initializer action
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!HelloApplication.MAINDATA.getDepositList().isEmpty()) {
            table.setItems(FXCollections.observableList(HelloApplication.MAINDATA.getDepositList()));
            value_column.setCellValueFactory(new PropertyValueFactory<>("value"));
            date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        }
        done_label.setOpacity(0);
    }
}
