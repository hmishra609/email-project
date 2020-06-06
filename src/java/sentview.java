
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class sentview extends HttpServlet {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException 
    {
        ServletContext sc=request.getServletContext();
   
        con=(Connection)sc.getAttribute("con");
        
        
        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("na");
       
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try  {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sentview</title>");            
            out.println("</head>");
            out.println("<body>");
            
//table
             ps=con.prepareStatement("select * from sent where frm=?");
             ps.setString(1,uname);
             rs=ps.executeQuery();
            
             out.println("<table border=1 width=50% height=50%>");
             out.println("<tr><th>TO</th><th>SUBJECT</th></tr>");
             out.println("<form action=\"trash\" method=\"get\">");
            while(rs.next())
            { 
                String id=rs.getString(1);
                String s1=rs.getString(2);
                String s2=rs.getString(4);
                String smsg=rs.getString(5);
                out.println("<tr><td><input type=\"checkbox\" name=\"ck\" value=\""+id+"\">"+s1+"</td><td><a href='message?msg="+smsg+"'>"+s2+"</a></td></tr>"); 
            }
            out.println("<input type=\"submit\" name=\"but\" value=\"DELETE\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
       
        }
        catch(SQLException t)
        {
            System.out.println(t);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
