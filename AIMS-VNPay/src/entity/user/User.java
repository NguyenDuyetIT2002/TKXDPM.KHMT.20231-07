package entity.user;

import controller.PlaceOrderController;
import entity.db.AIMSDB;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class User {

    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private  boolean ban;
    protected Statement stm;

    public User(int id, String name, String email, String address, String phone, boolean ban) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.ban = ban;
    }

    public User(int id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;    }

    public User() throws SQLException {
        stm = AIMSDB.getConnection().createStatement();
    }
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    public List getAllUser() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from User");
        ArrayList userList = new ArrayList<>();
        while (res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            String email = res.getString("email");
            String address = res.getString("address");
            String phone = res.getString("phone");
            boolean ban = res.getBoolean("ban");
            User user = new User(id, name, email, address, phone, ban);
//            LOGGER.info("Media" + media.quantity);
            userList.add(user);
        }
        return userList;
    }

    public void createUser(String name, String address, String phone, String email) {
        String insertUserSql = "INSERT INTO User "+"(name, email, address, phone, ban)" +" VALUES ('" +
                name + "', '" + email + "', '" + address + "', '" + phone  + "', 0 " +")";
        try {
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(insertUserSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, String name, String email, String address, String phone) {
        String updateUserSql = "UPDATE User SET " +
                "name = '" + name + "', " +
                "email = '" + email + "', " +
                "address = '" + address + "', " +
                "phone = '" + phone + "' " +
                "WHERE id = " + id;
        try {
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(updateUserSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String deleteUserSql = "DELETE FROM User WHERE id = " + id;
        try {
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(deleteUserSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void banUser(int id, boolean gt) {
        int result = !gt ? 1: 0;
        String banUserSql = "UPDATE User SET " +
                "ban = " +  result +
                " WHERE id = " + id;
        try {
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(banUserSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @return String
     */
    // override toString method
    @Override
    public String toString() {
        return "{" +
                "  username='" + name + "'" +
                ", email='" + email + "'" +
                ", address='" + address + "'" +
                ", phone='" + phone + "'" +
                "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    // getter and setter
    public String getName() {
        return this.name;
    }


    /**
     * @param name
     */
    public void setusername(String name) {
        this.name = name;
    }


    /**
     * @return String
     */
    public String getEmail() {
        return this.email;
    }


    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return String
     */
    public String getAddress() {
        return this.address;
    }


    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * @return String
     */
    public String getPhone() {
        return this.phone;
    }


    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }
}
