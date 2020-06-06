

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import javax.servlet.ServletContext;

/**
 *
 * @author Mayank Mishra
 */
public class servlet1 extends HttpServlet {
Connection con;
PreparedStatement ps,ps1;
ResultSet rs;
String name;
    @Override
public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
{
 ServletContext sc=request.getServletContext();
 con=(Connection)sc.getAttribute("con");

  
    
String n=request.getParameter("na");
String p=request.getParameter("pa");
String b=request.getParameter("but");
HttpSession s=request.getSession();
s.setAttribute("na",n);

s.setAttribute("pa",p);

try
{
    PrintWriter out=response.getWriter();
ps=con.prepareStatement("select * from users where uname=? and pass=?");
ps.setString(1,n);
ps.setString(2,p);

rs=ps.executeQuery();
int c=0;
while(rs.next())
{
 //here i m getting name of the email user   
name=rs.getString(3);
c++;
}
//here i m setting it in session
s.setAttribute("name",name);
if(c==0)
        
        {
        
        if(b.equals("SIGNUP"))
{               
                      RequestDispatcher rd=request.getRequestDispatcher("signup.html");
                            rd.forward(request,response);
}
        else
        {
RequestDispatcher rd2=request.getRequestDispatcher("index.html");
rd2.include(request,response);
out.println("Invalid UserId or password");
            
            
        }
}
else if(c!=0)
{
RequestDispatcher rd=request.getRequestDispatcher("home.html");
rd.forward(request,response);

if(b.equals("SIGNUP"))
{
RequestDispatcher rd1=request.getRequestDispatcher("signup.html");
rd1.include(request,response);
out.println("User doesnot exist please signup");
}
}        

} //try
catch(  IOException | SQLException | ServletException e)
{
System.out.println("Exception is"+e);

}

}
}

