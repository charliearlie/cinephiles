/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package xyz.cinephiles.servlets;

import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import cinephiles.data.interfaces.IMediaList;
import cinephiles.data.interfaces.IUserList;
import cinephiles.data.model.Media;
import cinephiles.data.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author charliearlie
 */
public class AddWatchlist extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            DAOFactory factory = new DAOFactoryDatabase();
            IMediaList mediaList = factory.getMediaList();
            IUserList userList = factory.getUserList();
            
            String imdbId = request.getParameter("id");
            int movieId = Integer.parseInt(imdbId.replace("t", ""));
            
            HttpSession session = request.getSession(false);
            User user = new User();
            if(session != null) {
                user = (User) session.getAttribute("user");
            }
            ArrayList<Media> watchList = mediaList.getWatchlist(user.getId());
            boolean inWatchlist = false;
            for(Media watch : watchList) {
                if(movieId == watch.getId()) {
                    inWatchlist = true;
                }
            }
            if(!inWatchlist) {
                mediaList.addToWatchlist(user.getId(), movieId);
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("view.jsp?id=" + imdbId);
            rd.forward(request, response);
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddWatchlist</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddWatchlist at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
