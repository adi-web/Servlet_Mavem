/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject9;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admirportatile
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            
            PrintWriter out = response.getWriter();
            
             out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoadClassi</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Elenco classsi:</h1>");
            
         

            
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from class");
            ResultSet rs =ps.executeQuery();
            
         //   List<Classi> elencoClassi = new ArrayList();
            while (rs.next()) {
                String nomeClasse = rs.getString("section");
             ///   Classi cl = new Classi();
               // cl.setNomeClasse(nomeClasse);
               // elencoClassi.add(cl);
                out.println("<p>"+nomeClasse+"</p>");
            }
            
            
            ///String ret = new Gson().toJson(elencoClassi);
            
           // response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
          //  out.print(ret);
            out.flush();
            
            /*
            out.println("</body>");
            out.println("</html>");
            */
            
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private Connection getConnection() {
        try {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            String dbURL = "jdbc:mysql:// localhost:3306/"; 
            // Database name to access 
            String dbName = "fi_itis_meucci "; 
            String dbUsername = "mucaj"; 
            String dbPassword = "mucaj12345678"; 

            Class.forName(dbDriver); 
            Connection con = DriverManager.getConnection(dbURL + dbName + "?serverTimezone=UTC", 
                                                         dbUsername,  
                                                         dbPassword); 
            return con;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println("errore");
            return null;
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
