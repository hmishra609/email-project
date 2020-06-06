/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mayank Mishra
 */
public class resetp extends HttpServlet {
Connection con;
    PreparedStatement ps;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
String op=request.getParameter("op");
String np=request.getParameter("np");
String cp=request.getParameter("cp");
ServletContext sc=request.getServletContext();
con=(Connection)sc.getAttribute("con");
HttpSession session=request.getSession();
String username=(String)session.getAttribute("na");
String pass=(String)session.getAttribute("pa");
PrintWriter out=response.getWriter();



if(op.equals(pass)&&np.equals(cp))
{
try
{
ps=con.prepareStatement("update users set pass=? where uname=?");
    ps.setString(1,cp);
    ps.setString(2,username);
int i=ps.executeUpdate();
if(i>0)
{
RequestDispatcher rd=request.getRequestDispatcher("index.html");
rd.include(request, response);
out.print("password Updated.....please Signin again");
}
else
{
RequestDispatcher rd=request.getRequestDispatcher("home.html");
rd.include(request, response);
out.print("Some error occured");


}
    }
catch(SQLException r)
{
    System.out.println(r);
}
}
else
{
RequestDispatcher rd=request.getRequestDispatcher("home.html");
rd.include(request, response);
    
out.print("Incorrect password entered");

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
