package com.gursimran.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

/**
 * Created by gursimransingh on 09/08/17.
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String passwordmd = req.getParameter("txtPassword");
        String hexhash=null;
        PrintWriter printWriter = resp.getWriter();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordmd.getBytes("UTF-8"));
            hexhash = DatatypeConverter.printHexBinary(hash);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (passwordmd.isEmpty()){
            printWriter.println("Enter Password!");
        }
        else {
        chain.doFilter(req, resp);
        printWriter.println("Password (Hash SHA-256):-> "+hexhash);

        }}

    public void init(FilterConfig config) throws ServletException {

    }

}
