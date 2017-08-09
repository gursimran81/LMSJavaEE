package com.gursimran.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gursimransingh on 09/08/17.
 */
@WebFilter(filterName = "RegisterFilter", urlPatterns = "/RegisterFilter")
public class RegisterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        String name = req.getParameter("txtName");
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        PrintWriter printWriter  = resp.getWriter();
        if (name.isEmpty() && email.isEmpty() && password.isEmpty()){
            printWriter.println("Enter something!!");
        }
        else {
            chain.doFilter(req, resp);
            printWriter.print("Now request is processing");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
