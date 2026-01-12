import java.sql.*;
import java.util.Scanner;

public class SalaryInsert {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Basic: ");
        double basic = sc.nextDouble();

        System.out.print("Enter Bonus: ");
        double bonus = sc.nextDouble();

        System.out.print("Enter Overtime: ");
        double overtime = sc.nextDouble();

        System.out.print("Enter Tax: ");
        double tax = sc.nextDouble();

        double net = basic + bonus + overtime - tax;

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/salarydb",
                    "root",
                    "PASSWORD");

            String sql = "INSERT INTO salary VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, basic);
            ps.setDouble(4, bonus);
            ps.setDouble(5, overtime);
            ps.setDouble(6, tax);
            ps.setDouble(7, net);

            ps.executeUpdate();
            System.out.println("Salary inserted successfully!");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
