package MiniProjectOOP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLDBEmployee {

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

    public ArrayList<Employee> selectQuery(String sql) {
        Employee emp;
        try {
            con = connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            ArrayList<Employee> EmployeeList = new ArrayList<>();
            while (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getString(1));
                emp.setName(rs.getString(2));
                emp.setSurname(rs.getString(3));
                emp.setPosition(rs.getString(4));
                emp.setSalary(rs.getString(5));
                emp.setBonus(rs.getString(6));
                EmployeeList.add(emp);
            }
            return EmployeeList;
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

}//end class MySQLDBEmployee