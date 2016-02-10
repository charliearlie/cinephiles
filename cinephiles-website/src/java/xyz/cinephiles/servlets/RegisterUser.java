/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package xyz.cinephiles.servlets;

import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import cinephiles.data.interfaces.IUserList;
import cinephiles.data.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author charliearlie
 */
public class RegisterUser extends HttpServlet {

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
        DAOFactory factory = new DAOFactoryDatabase();
        IUserList userList = factory.getUserList();

        try {
            //Retrieve data from register form
            String forename = request.getParameter("forename");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dob = request.getParameter("dateofbirth");

            //Split the dob fields for proper formatting
            String[] dobFields = dob.split("/");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, Integer.parseInt(dobFields[0]) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobFields[1]));
            cal.set(Calendar.YEAR, Integer.parseInt(dobFields[2]));
            Date dateOfBirth = cal.getTime();

            //Create the new user
            User newUser = new User(forename, surname, "password", dateOfBirth, email, new Date(), "Y");
            newUser.setPassword(password);

            //Add the user to the database
            userList.createUser(newUser);
            
            RequestDispatcher rd = request.getRequestDispatcher("registrationsuccessful.jsp");
            rd.forward(request, response);
            
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
