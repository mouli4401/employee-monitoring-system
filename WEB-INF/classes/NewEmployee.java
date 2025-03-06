import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
public class NewEmployee extends HttpServlet { 
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, 
IOException { 
        res.setContentType("text/html"); 
        PrintWriter pw=res.getWriter(); 
        int eid = Integer.parseInt(req.getParameter("eid")); 
        String ename = req.getParameter("ename"); 
        String dname = req.getParameter("dname"); 
        String email = req.getParameter("email"); 
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
"system","mouli"); 
            String sql = "insert into emp1 (eid, ename, dname, email) VALUES (?, ?, ?, ?)"; 
            PreparedStatement st = conn.prepareStatement(sql); 
            st.setInt(1, eid); 
            st.setString(2, ename); 
            st.setString(3, dname); 
            st.setString(4, email); 
            int r = st.executeUpdate(); 
            if (r > 0) { 
                pw.println("<h1>Employee registered successfully.</h1> <br><br> <a href='http://localhost:9999/emp/main.html'>Home</a>"); 
            } else { 
                pw.println("<h1>Failed to register employee.</h1>"); 
            } 
        }catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
} 