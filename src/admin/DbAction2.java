package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tutionfeemanagement.DbAction;

public class DbAction2 {

    DbAction db = new DbAction();

    ObservableList<Admin> getAdmin() throws SQLException {

        ObservableList<Admin> adminList = FXCollections.observableArrayList();

        Connection con = db.getConnection();

        Statement statement = con.createStatement();

        String query = "select * from admin";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            int adminId = rs.getInt("admin_id");
            String adminName = rs.getString("admin_name");
            String adminPassword = rs.getString("password");

            Admin admin = new Admin(adminId, adminName, adminPassword);
            adminList.add(admin);
        }
        return adminList;
    }

    ObservableList<AccountsOfficer> getAccountsOfficer() throws SQLException {

        ObservableList<AccountsOfficer> accList = FXCollections.observableArrayList();

        Connection con = db.getConnection();

        Statement statement = con.createStatement();

        String query = "select * from accounts";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            int accId = rs.getInt("Acc_Officer_Id");
            String accName = rs.getString("Acc_Officer_Name");
            String accPassword = rs.getString("Acc_Officer_Password");

            AccountsOfficer accOfficer = new AccountsOfficer(accId, accName, accPassword);
            accList.add(accOfficer);
        }
        return accList;
    }

    void insertAdmin(Admin admin) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();

        String query = "insert into admin"
                + " values(" + admin.getAdminId() + ",'" + admin.getAdminName() + "','" + admin.getAdminPassword() + "')";

        statement.executeUpdate(query);

    }

    void insertAcc(AccountsOfficer acc) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();

        String query = "insert into accounts"
                + " values(" + acc.getAccountantId() + ",'" + acc.getAccountantName() + "','" + acc.getAccountantPassword() + "')";

        statement.executeUpdate(query);

    }

    void updateAdmin(Admin admin) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();

        String query = "update admin "
                + "set admin_name = '" + admin.getAdminName() + "', password ='" + admin.getAdminPassword() + "'where admin_id = " + admin.getAdminId();

        statement.executeUpdate(query);
    }

    void updateAcc(AccountsOfficer acc) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();

        String query = "update accounts "
                + "set Acc_Officer_Name = '" + acc.getAccountantName() + "', Acc_Officer_Password ='" + acc.getAccountantPassword() + "'where Acc_Officer_Id = " + acc.getAccountantId();

        statement.executeUpdate(query);
    }

    void deleteAdmin(ObservableList<Admin> selectedAdminList) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        for (Admin admin : selectedAdminList) {
            String query = "delete from admin where admin_id =" + admin.getAdminId();
            statement.executeUpdate(query);
        }
    }

    void deleteAcc(ObservableList<AccountsOfficer> selectedAccList) throws SQLException {
        Connection con = db.getConnection();
        Statement statement = con.createStatement();
        for (AccountsOfficer acc : selectedAccList) {
            String query = "delete from accounts where Acc_Officer_Id =" + acc.getAccountantId();
            statement.executeUpdate(query);
        }
    }

}
