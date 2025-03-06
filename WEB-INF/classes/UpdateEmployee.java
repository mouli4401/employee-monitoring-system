import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
 
public class UpdateEmployee extends HttpServlet { 
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, 
IOException { 
        res.setContentType("text/html"); 
        PrintWriter pw = res.getWriter(); 
 
        int eid = Integer.parseInt(req.getParameter("eid")); 
        String ename = req.getParameter("ename"); 
        String dname = req.getParameter("dname"); 
        String email = req.getParameter("email"); 
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
"system", "mouli"); 
            String sql = "update emp1 set ename = ?, dname = ?, email = ? where eid = ?"; 
            PreparedStatement st = conn.prepareStatement(sql); 
            st.setString(1, ename); 
            st.setString(2, dname); 
            st.setString(3, email); 
            st.setInt(4, eid); 
            int r = st.executeUpdate(); 
            if (r > 0) { 
                pw.println("<h1>Employee details updated successfully.</h1>"); 
            } else { 
                pw.println("<h1>No employee found with ID " + eid + " to update.</h1>"); 
            } 
        } catch (Exception e) { 
     
        } 
    } 
} 