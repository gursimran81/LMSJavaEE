package com.gursimran;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gursimransingh on 08/08/17.
 */
@WebServlet(urlPatterns = "/Home")
public class HomeServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print("WELCOME HOME!!");
//
//        Cookie[] cookies = request.getCookies();
//        for (Cookie ck:cookies
//             ) {
//            printWriter.println(ck.getName()+ " Value->"+ck.getValue());
//
//        }

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");

        printWriter.println("Name->"+name);
        printWriter.println("Email->"+email);
         printWriter.println("Password(HASH)->"+password);
        }

    }


