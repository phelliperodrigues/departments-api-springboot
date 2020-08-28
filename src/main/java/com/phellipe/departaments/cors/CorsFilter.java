package com.phellipe.departaments.cors;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter  {//implements Filter {

    private String origin = "http://localhost:4200";

//     @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");


        if ("OPTIONS".equals(req.getMethod()) && origin.equals(req.getHeader("Origin"))){
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, PATH, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            res.setHeader("Access-Control-Max-Age", "3600");

            res.setStatus(HttpServletResponse.SC_OK);
        }else {
            chain.doFilter(request, response);
        }

    }


//     @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

//     @Override
    public void destroy() {

    }
}
