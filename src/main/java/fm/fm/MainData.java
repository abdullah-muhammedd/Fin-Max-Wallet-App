package fm.fm;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainData {

    //Data
    private final ArrayList<withdrawClass> withdrawList = new ArrayList<>();
    private final ArrayList<depositClass> depositList = new ArrayList<>();
    private String USERNAME;
    private String NAME;
    private String BALANCE;
    private XYChart.Series CHART;
    private String ID;

    //Linechart method used to create the line chart in the main page
    XYChart.Series lineChartData() {
        XYChart.Series series = new XYChart.Series();
        try {
            ResultSet resultSet = MainConnection.createChart();
            while (resultSet.next()) {
                series.getData().add(new XYChart.Data<>(String.valueOf(resultSet.getDate("date_")), resultSet.getDouble("value")));
            }
            series.setName("Change in balance per day");
        } catch (Exception ignored) {
        }
        return series;
    }

    //Setters and getters
    public String getUSERNAME() {
        return USERNAME;
    }

    private void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }


    public String getNAME() {
        return NAME;
    }

    private void setNAME() {
        this.NAME = (MainConnection.getData(USERNAME)[0] + " " +
                MainConnection.getData(USERNAME)[1] + " " +
                MainConnection.getData(USERNAME)[2]);
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    private void setBALANCE() {
        this.BALANCE = MainConnection.getData(USERNAME)[3];
    }

    public XYChart.Series getCHART() {
        return CHART;
    }

    public void setCHART(XYChart.Series CHART) {
        this.CHART = CHART;
    }

    private void setCHART() {
        this.CHART = lineChartData();
    }

    public String getID() {
        return ID;
    }

    private void setID() {
        this.ID = MainConnection.getData(USERNAME)[4];
    }


    //setMainData  method used to initialize the data according to the username on login
    public void setMainData(String USERNAME) throws SQLException, ClassNotFoundException {
        setUSERNAME(USERNAME);
        setNAME();
        setBALANCE();
        setID();
        MainConnection.createViews();
        setWithdrawList();
        setDepositList();
        setCHART();

    }

    //Withdraw list is the responsible for the withdrawals history table in the withdrawals page
    public ArrayList<withdrawClass> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = MainConnection.connectCreateStatement().executeQuery("select * from withdraw_table limit 30");
        while (resultSet.next()) {
            withdrawList.add(new withdrawClass(String.valueOf(resultSet.getDouble(1)), resultSet.getString(2)));
        }
    }

    //Withdraw list is the responsible for the Deposits history table in the Deposits page
    public ArrayList<depositClass> getDepositList() {
        return depositList;
    }

    public void setDepositList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = MainConnection.connectCreateStatement().executeQuery("select * from deposit_table limit 30");
        while (resultSet.next()) {
            depositList.add(new depositClass(String.valueOf(resultSet.getDouble(1)), resultSet.getString(2)));
        }
    }


    //Classes to be part of the withdrawal and deposit lists
    public class withdrawClass {
        private final SimpleStringProperty value;
        private final SimpleStringProperty date;

        public withdrawClass(String value, String date) {
            this.value = new SimpleStringProperty(value);
            this.date = new SimpleStringProperty(date);
        }

        public String getValue() {
            return value.get();
        }

        public void setValue(String value) {
            this.value.set(value);
        }

        public SimpleStringProperty valueProperty() {
            return value;
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }
    }

    public class depositClass {
        private final SimpleStringProperty value;
        private final SimpleStringProperty date;

        public depositClass(String value, String date) {
            this.value = new SimpleStringProperty(value);
            this.date = new SimpleStringProperty(date);
        }

        public String getValue() {
            return value.get();
        }

        public void setValue(String value) {
            this.value.set(value);
        }

        public SimpleStringProperty valueProperty() {
            return value;
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

    }


}
