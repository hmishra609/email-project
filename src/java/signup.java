
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class signup extends HttpServlet {
Connection con;
    PreparedStatement ps;
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 PrintWriter out = response.getWriter();
 ServletContext sc=request.getServletContext();
 con=(Connection)sc.getAttribute("con");
 HttpSession hs=request.getSession();
 
 String n=request.getParameter("name");
 hs.setAttribute("name",n);
 
 
 String un=request.getParameter("uname");
 String p=request.getParameter("pa");
       try  
    {
       ps=con.prepareStatement("insert into users(uname,name,pass) values(?,?,?)");     
       ps.setString(1,un);
       ps.setString(2,n);
       ps.setString(3,p);
       int i=ps.executeUpdate();
       if(i>0)
       {
           RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request, response);
            out.println("SignUp Successful");
       }
       else
       {
       System.out.println("error occured");
       }
     }
        catch(IOException | SQLException | ServletException r)
        {
            System.out.println("error is"+r);
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
