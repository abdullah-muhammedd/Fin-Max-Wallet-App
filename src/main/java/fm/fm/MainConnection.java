package fm.fm;

import java.sql.*;

public class MainConnection {
    private static java.sql.Connection connection;

    //function connectCreateStatement Return an empty sql statement
    public static Statement connectCreateStatement() throws SQLException, ClassNotFoundException {
        final String username = "root";
        final String password = "1112";
        final String URL = "jdbc:mysql://localhost:3306/finmax";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, username, password);
        return connection.createStatement();
    }

    //getData Function return array of strings contain name and id of the user
    public static String[] getData(String USERNAME) {
        ResultSet resultSet;
        String[] str = new String[5];
        try {
            resultSet = connectCreateStatement().executeQuery("select * from account where id = (select id from login where username = \"" + USERNAME + "\")");
            resultSet.next();
            str[0] = resultSet.getString("f_name");
            str[1] = resultSet.getString("m_name");
            str[2] = resultSet.getString("l_name");
            str[3] = String.valueOf(resultSet.getDouble("balance"));
            str[4] = String.valueOf(resultSet.getInt("id"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    private static void createWithdraw(double val, String date, String ID, String BALANCE) {
        try {
            final String username = "root";
            final String password = "1112";
            final String URL = "jdbc:mysql://localhost:3306/finmax";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement pr = connection.prepareStatement(("INSERT INTO `finmax`.`withdraw` (user_id,value,date_) VALUES (" + Integer.parseInt(ID) + ", " + val + ", '" + date + "' )"));
            pr.executeUpdate();
            pr = connection.prepareStatement("update account set balance = '" + (Double.parseDouble(BALANCE) - val) + "' where id = " + Integer.parseInt(ID));
            pr.executeUpdate();
        } catch (SQLException ignored) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //    createDeposit Function Consist of two parts the first part is the public one,
    //    and it is take the value and the date from external resources and pass theme
    //    to the second part  the Private One which connect to the database and store the
    //    deposit value and change the balance value
    public static void createDeposit(double val, String date) {
        createDeposit(val, date, HelloApplication.MAINDATA.getID(), HelloApplication.MAINDATA.getBALANCE());
    }

    private static void createDeposit(double val, String date, String ID, String BALANCE) {
        try {
            final String username = "root";
            final String password = "1112";
            final String URL = "jdbc:mysql://localhost:3306/finmax";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement pr = connection.prepareStatement(("INSERT INTO `finmax`.`deposit` (user_id,value,date_) VALUES (" + Integer.parseInt(ID) + ", " + val + ", '" + date + "' )"));
            pr.executeUpdate();
            pr = connection.prepareStatement("update account set balance = '" + (Double.parseDouble(BALANCE) + val) + "' where id = " + Integer.parseInt(ID));
            pr.executeUpdate();
        } catch (SQLException ignored) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //    createChart Function Return a result set from the database which contains
    //    a view that contain (Deposit - Withdraw) grouped by date
    public static ResultSet createChart() {
        ResultSet resultSet = null;
        try {
            resultSet = connectCreateStatement().executeQuery("select * from WD_group order by date_ limit 7");
        } catch (SQLException | ClassNotFoundException ignored) {
        }
        return resultSet;
    }

    //    createDeposit Function Consist of two parts the first part is the public one,
    //    and it is take the value and the date from external resources and pass theme
    //    to the second part  the Private One which connect to the database and store the
    //    deposit value and change the balance value
    public static void createWithdraw(double val, String date) {
        createWithdraw(val, date, HelloApplication.MAINDATA.getID(), HelloApplication.MAINDATA.getBALANCE());
    }

    //This function create views that handle the data of the balance chart in the main
    // page so that we do not need to execute same statements every time we open the main page
    static void createViews() {
        try {
            final String username = "root";
            final String password = "1112";
            final String URL = "jdbc:mysql://localhost:3306/finmax";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement pr = connection.prepareStatement("create view deposit_group as select  sum(value) as value, date_ from deposit where user_id = " + Integer.parseInt(HelloApplication.MAINDATA.getID()) + " group by date_");
            pr.execute();
            pr = connection.prepareStatement("create view withdraw_group as select  sum(value)as value , date_ from withdraw where user_id =" + Integer.parseInt(HelloApplication.MAINDATA.getID()) + " group by date_");
            pr.execute();
            pr = connection.prepareStatement("create view WD_group as select (deposit_group.value - withdraw_group.value) as value , withdraw_group.date_ from withdraw_group , deposit_group where withdraw_group.date_ = deposit_group.date_");
            pr.execute();
            pr = connection.prepareStatement("create view withdraw_table as select value , date_ from withdraw where user_id = " + Integer.parseInt(HelloApplication.MAINDATA.getID()) + " order by date_");
            pr.execute();
            pr = connection.prepareStatement("create view deposit_table as select value , date_ from deposit where user_id = " + Integer.parseInt(HelloApplication.MAINDATA.getID()) + " order by date_");
            pr.execute();
        } catch (Exception ignored) {
        }
    }

    //This function drop views that handle the data of the balance chart in the main
    static void dropViews() {
        try {
            final String username = "root";
            final String password = "1112";
            final String URL = "jdbc:mysql://localhost:3306/finmax";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, username, password);
            PreparedStatement pr = connection.prepareStatement("drop view deposit_group ");
            pr.execute();
            pr = connection.prepareStatement("drop view withdraw_group ");
            pr.execute();
            pr = connection.prepareStatement(("drop view WD_group "));
            pr.execute();
            pr = connection.prepareStatement(("drop view withdraw_table"));
            pr.execute();
            pr = connection.prepareStatement(("drop view deposit_table "));
            pr.execute();
        } catch (Exception ignored) {
        }
    }


}

