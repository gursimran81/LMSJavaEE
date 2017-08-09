package com.gursimran.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gursimransingh on 09/08/17.
 */
@WebFilter(filterName = "DecryptLoginFilter")
public class DecryptLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession httpSession = request.getSession();
        String heexPass = (String) httpSession.getAttribute("password");
        PrintWriter printWriter = resp.getWriter();

        printWriter.println("PASSWORD BEFORE PREPROCESSING OF DFILTER"+ heexPass);
        chain.doFilter(req, resp);
        printWriter.println("PASSWORD AFTER PREPROCESSING OF DFILTER");

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
