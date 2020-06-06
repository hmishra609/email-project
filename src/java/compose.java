
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class compose extends HttpServlet 
{
    Connection con;
    PreparedStatement ps,ps1;
    ResultSet rs1;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc=request.getServletContext();
        PrintWriter out=response.getWriter();
        con=(Connection)sc.getAttribute("con");
        String too=request.getParameter("to");
      
        
        HttpSession session=request.getSession();
        String frm=(String)session.getAttribute("na");
        
        String s=request.getParameter("sub");
        String m=request.getParameter("msg");
        String b=request.getParameter("but");
                RequestDispatcher rd=request.getRequestDispatcher("compose.html");
                        rd.include(request,response);  
        try 
        {
                  if(b.equals("SAVE"))
                        {
                        ps1=con.prepareStatement("insert into draft(too,frm,sub,msg) values(?,?,?,?)");
                        ps1.setString(1,too);
                        ps1.setString(2,frm);
                        ps1.setString(3,s);
                        ps1.setString(4,m);
                        int j=ps1.executeUpdate();
                        
                        if(j>0)
                        {
                            out.println("Mail Saved as Draft");
                        }
                        else
                        {
                            out.println("database error");
                        }
                    }
            
       
                else
                {
                    ps1=con.prepareStatement("select uname from users where uname=?");
                    ps1.setString(1,too);
                   rs1=ps1.executeQuery();
                   int c=0;
                   while(rs1.next())
                   {
                   c++;
                   }
                   if(c==0)
                   {
                   out.println("invalid emailid...sending failed");
                   }
                   else
                   {
                        ps=con.prepareStatement("insert into sent(too,frm,sub,msg) values(?,?,?,?)");
                        ps.setString(1,too);
                        ps.setString(2,frm);
                        ps.setString(3,s);
                        ps.setString(4,m);
                        int i=ps.executeUpdate();
                  
                        if(i>0)
                        {
                       
        
                            out.println("Mail Sent");
                        }
                        else
                        {
                            out.println("database error");
                        }
            
                   }
        }
        }//try
        catch(Exception r)
        {
        System.out.println(r);
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
