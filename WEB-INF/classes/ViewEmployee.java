import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
 
public class ViewEmployee extends HttpServlet { 
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, 
IOException { 
        res.setContentType("text/html"); 
        PrintWriter pw = res.getWriter(); 
        int eid = Integer.parseInt(req.getParameter("eid")); 
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
"system", "mouli"); 
            String sql = "select eid, ename, dname, email from emp1 WHERE eid = ?"; 
           PreparedStatement st = conn.prepareStatement(sql); 
            st.setInt(1, eid); 
            ResultSet rs = st.executeQuery(); 
            if (rs.next()) { 
                pw.println("<h1>Employee Details</h1>"); 
          pw.println("<table border='1' style='width: 50%; margin: 0 auto;'>"); 
            pw.println("<tr><td><b>Employee ID:</b></td><td>" + rs.getInt("eid") + "</td></tr>"); 
    pw.println("<tr><td><b>Employee Name:</b></td><td>" + rs.getString("ename") + "</td></tr>"); 
    pw.println("<tr><td><b>Department:</b></td><td>" + rs.getString("dname") + "</td></tr>"); 
     pw.println("<tr><td><b>Email:</b></td><td>" + rs.getString("email") + "</td></tr>"); 
                pw.println("</table>"); 
            } else { 
                pw.println("<h1>No employee found with ID " + eid + ".</h1>"); 
            } 
        } catch (Exception e) { 
            e.printStackTrace();     
        }  
    } 
} 