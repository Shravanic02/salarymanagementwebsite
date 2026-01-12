import java.sql.*;

public class SalaryView {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/sms_db"; // apna DB name
        String user = "root"; // MySQL username
        String password = "1234567890"; // MySQL password

        try {
            // 1️⃣ Connect to DB
            Connection con = DriverManager.getConnection(url, user, password);

            // 2️⃣ Create statement
            Statement stmt = con.createStatement();

            // 3️⃣ Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM salary");

            // 4️⃣ Print header
            System.out.println(
                    "ID\tName\tBasic\tBonus\tOvertime\tTax\tNetSalary");

            // 5️⃣ Print data
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "\t" +
                                rs.getString("emp_name") + "\t" +
                                rs.getDouble("basic") + "\t" +
                                rs.getDouble("bonus") + "\t" +
                                rs.getDouble("overtime") + "\t" +
                                rs.getDouble("tax") + "\t" +
                                rs.getDouble("net_salary"));
            }

            // 6️⃣ Close connection
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
