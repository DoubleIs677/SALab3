package com.mycode.BS.filter;

import javax.servlet.*;
import java.io.IOException;



public class codeFilter implements Filter {

    public codeFilter(){
        System.out.println("过滤器构造");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8"); //将编码改为utf-8
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");

    }
}