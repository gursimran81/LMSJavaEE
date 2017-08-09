package com.gursimran;

import com.gursimran.db.jdbc;
import com.gursimran.model.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gursimransingh on 08/08/17.
 */
@WebServlet({"/RegisterServlet","/Register.do"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
    //PRINTING
        PrintWriter out = response.getWriter();
    String name = request.getParameter("txtName");
    String email = request.getParameter("txtEmail");
    String password = request.getParameter("txtPassword");

    //INTO THE POJO
        user user = new user(name,email,password);
        jdbc helper = new jdbc();
        helper.openConnection();
        int check = helper.registerUser(user);
        if (check>0){
         out.print("REGISTER HO GAYAA!!!!"+"ID ="+user.getEmail());

            Cookie c1 = new Cookie("name",name);
            Cookie c2 = new Cookie("email",email);

            response.addCookie(c1);
            response.addCookie(c2);

            out.println("<a href='Home'>Enter Home</a>");
        }
        else {
            out.print("PANGA PE GAYA");
        }

        helper.closeConnection();
    }

}
