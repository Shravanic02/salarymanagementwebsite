import java.sql.*;
import java.util.Scanner;

public class SalaryInsertUserInput {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/sms_db";
        String user = "root";
        String password = "1234567890";

        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ User input
            System.out.print("Enter Employee Name: ");
            String empName = sc.nextLine();

            System.out.print("Enter Basic Salary: ");
            double basic = sc.nextDouble();

            System.out.print("Enter Bonus: ");
            double bonus = sc.nextDouble();

            System.out.print("Enter Overtime: ");
            double overtime = sc.nextDouble();

            System.out.print("Enter Tax: ");
            double tax = sc.nextDouble();

            // 2️⃣ Net salary calculation
            double netSalary = basic + bonus + overtime - tax;

            // 3️⃣ DB Connection
            Connection con = DriverManager.getConnection(url, user, password);

            // 4️⃣ SQL Insert
            String sql = "INSERT INTO salary (emp_name, basic, bonus, overtime, tax, net_salary) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empName);
            ps.setDouble(2, basic);
            ps.setDouble(3, bonus);
            ps.setDouble(4, overtime);
            ps.setDouble(5, tax);
            ps.setDouble(6, netSalary);

            ps.executeUpdate();

            System.out.println("✅ Salary record inserted successfully!");
            System.out.println("Net Salary = " + netSalary);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
