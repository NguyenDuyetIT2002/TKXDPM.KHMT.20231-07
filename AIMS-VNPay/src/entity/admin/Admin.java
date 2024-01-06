
package entity.admin;

import entity.user.User;

import java.sql.SQLException;
import java.sql.Statement;
public class Admin extends User {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    protected Statement stm;
    public Admin(int id, String name, String email, String address, String phone) {
        super(id, name, email, address, phone);
    }

    public Admin() throws SQLException {
    }

    public void CreateUser() {

    }

    public  void ReadUser() {

    }

    public void UpdateUser() {

    }

    public void DeleteUser() {

    }

    public void BanUser() {

    }
}
