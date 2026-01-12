import java.sql.*;
import java.util.Scanner;

public class SalaryDelete {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sms_db";
        String user = "root";
        String password = "1234567890";

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM salary WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Salary record deleted successfully!");
            } else {
                System.out.println("No employee found with this ID.");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
