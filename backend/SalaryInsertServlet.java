import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SalaryInsertServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        double basic = Double.parseDouble(request.getParameter("basic"));
        double bonus = Double.parseDouble(request.getParameter("bonus"));
        double overtime = Double.parseDouble(request.getParameter("overtime"));
        double tax = Double.parseDouble(request.getParameter("tax"));

        double net = basic + bonus + overtime - tax;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/salarydb",
                    "root",
                    "");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO salary(name,basic,bonus,overtime,tax,net) VALUES (?,?,?,?,?,?)");

            ps.setString(1, name);
            ps.setDouble(2, basic);
            ps.setDouble(3, bonus);
            ps.setDouble(4, overtime);
            ps.setDouble(5, tax);
            ps.setDouble(6, net);

            ps.executeUpdate();

            out.println("<h3>Salary Inserted Successfully</h3>");
            out.println("<a href='dashboard.html'>Back to Dashboard</a>");

            con.close();

        } catch (Exception e) {
            out.println(e);
        }
    }
}
