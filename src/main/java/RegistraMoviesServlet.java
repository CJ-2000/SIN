/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notes
 */
public class RegistraMoviesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            /*id proviene de un form en index.html*/
            String code = request.getParameter("codigo");
            Connection con = DataBaseConnection.initializeDatabase();
            Statement stmt =con.createStatement();            
            ResultSet rs= stmt.executeQuery("SELECT * FROM peliculas WHERE codigo = '" + code +"'");
            
            if (!rs.next() ) {
                /*Lanza una alerta en javascript*/
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Código incorrecto');");
                out.println("location='index.html';");
                out.println("</script>");
            } else {
                
                /* retorna respuesta: tabla */
                out.println("<table border = 1 width=50% height = 50%>");
                out.println("<tr><th>Id</th><th>Codigo</th><th>Pelicula</th><th>Detalle</th><tr>");

                do{
                   int n = rs.getInt("id");
                    String cod = rs.getString("codigo");
                    String nm = rs.getString("nombre");
                    String s = rs.getString("descripcion");
                    out.println("<tr><td>" + n + "</td><td>" + cod + "</td><td>" + nm +"</td><td>" + s + "</td><tr>");                    
                } while (rs.next());
                out.println("</table>");            
                out.println("</html></body>");
            }
            /* termina la conexión de BD */
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
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
