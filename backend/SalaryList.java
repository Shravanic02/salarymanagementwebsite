import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SalaryList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Salary List</title>");
        out.println("<style>");
        out.println("table{border-collapse:collapse;width:80%;margin:auto}");
        out.println("th,td{border:1px solid black;padding:10px;text-align:center}");
        out.println("th{background:#333;color:white}");
        out.println("</style></head><body>");

        out.println("<h2 style='text-align:center'>Salary Records</h2>");
        out.println("<table>");
        out.println(
                "<tr><th>ID</th><th>Name</th><th>Basic</th><th>Bonus</th><th>Overtime</th><th>Tax</th><th>Net Salary</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sms_db", "root", "1234567890");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM salary");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("emp_name") + "</td>");
                out.println("<td>" + rs.getDouble("basic") + "</td>");
                out.println("<td>" + rs.getDouble("bonus") + "</td>");
                out.println("<td>" + rs.getDouble("overtime") + "</td>");
                out.println("<td>" + rs.getDouble("tax") + "</td>");
                out.println("<td>" + rs.getDouble("net_salary") + "</td>");
                out.println("</tr>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<tr><td colspan='7'>" + e + "</td></tr>");
        }

        out.println("</table></body></html>");
    }
}
