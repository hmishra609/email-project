
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class NewServletListener implements ServletContextListener {
Connection con;
    @Override
    public void contextInitialized(ServletContextEvent e) {

        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/emailapp","root",null);
        
        }
        catch(ClassNotFoundException | SQLException r)
        {
        System.out.println(r);
        }
        ServletContext sc=e.getServletContext();
        sc.setAttribute("con",con);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
