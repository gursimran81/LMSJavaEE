package com.gursimran;

import com.gursimran.db.jdbc;
import com.gursimran.model.user;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;


/**
 * Created by gursimransingh on 08/08/17.
 */
@WebServlet({"/LoginServlet", "/Login", "/Login.do"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        user user = new user();
        user.setEmail(email);
        user.setPassword(password);

        jdbc helper = new jdbc();
        helper.openConnection();
        boolean check = helper.loginUser(user);
        String hexhash = null;
        if (check){
            out.print("Login Successfull");
            String name = helper.getName(user);
            String passwordmd = user.getPassword();
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(passwordmd.getBytes("UTF-8"));
                hexhash = DatatypeConverter.printHexBinary(hash);
            }catch (Exception e){
                e.printStackTrace();
            }

            HttpSession session = request.getSession();
            session.setAttribute("name",name);
            session.setAttribute("email",user.getEmail());
            session.setAttribute("password",hexhash);

            out.println("<a href='Home' >GO TO HOME</a>");

//
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home");
//            requestDispatcher.include(request,response);
        }
        else {
            out.println("<b>Login is Failure..</b>");
        }

        helper.closeConnection();
    }
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) t
}
