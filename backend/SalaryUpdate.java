import java.sql.*;
import java.util.Scanner;

public class SalaryUpdate {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/yourdbname";
        String user = "root";
        String password = "yourpassword";

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Salary: ");
        double newSalary = sc.nextDouble();

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "UPDATE salary SET salary = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("No employee found with this ID.");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
