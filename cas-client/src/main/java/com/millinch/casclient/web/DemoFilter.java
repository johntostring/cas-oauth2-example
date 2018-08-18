package com.millinch.casclient.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * This guy is busy, nothing left
 *
 * @author John Zhang
 */
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("========= DemoFilter ==========");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
