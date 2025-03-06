import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
public class DeleteEmployee extends HttpServlet { 
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, 
IOException { 
        res.setContentType("text/html"); 
        PrintWriter pw = res.getWriter(); 
        int eid = Integer.parseInt(req.getParameter("eid")); 
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
"system", "mouli"); 
            String sql = "DELETE FROM emp1 WHERE eid = ?"; 
            PreparedStatement st = con.prepareStatement(sql); 
            st.setInt(1, eid); 
            int r = st.executeUpdate(); 
            if (r > 0) { 
                pw.println("<h1>Employee with ID " + eid + " deleted successfully.</h1>"); 
            } else { 
                pw.println("<h1>No employee found with ID " + eid + " to delete.</h1>"); 
            } 
        } catch(Exception e) 
        {} 
    } 
}