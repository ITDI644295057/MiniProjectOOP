package MiniProjectOOP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLDBCustomer {

    Connection con;
    Statement stmt;
    ResultSet rs;

    public Connection connect() {
        String url = "jdbc:mysql://localhost/studentdb";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            System.err.println("ติดต่อฐานข้อมูลไม่ได้ครับ");
            return null;
        }
    }//end method connect

    public ArrayList<Customer> selectQuery(String sql) {
        Customer cus;
        try {
            con = connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            ArrayList<Customer> CustomerList = new ArrayList<>();
            while (rs.next()) {
                cus = new Customer();
                cus.setId(rs.getString(1));
                cus.setName(rs.getString(2));
                cus.setSurname(rs.getString(3));
                cus.setRoomType(rs.getString(4));
                cus.setPeriod(rs.getString(5));
                cus.setDays(rs.getString(6));
                cus.setTotalPrice(rs.getString(7));
                CustomerList.add(cus);
            }
            return CustomerList;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            System.err.println("ดึงข้อมูลไม่ได้ครับ");
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                } else {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }//end method selectQuery

    public int CUD(String sql) {//create update delete
        int row = 0;
        try {
            con = connect();
            stmt = con.createStatement();
            row = stmt.executeUpdate(sql);
            return row;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            System.err.println("CUD ไม่ได้ครับ");
            return row;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                } else {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }//end method CUD

}//end class MySQLDBCustomer
