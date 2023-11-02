package com.mycode.BS.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mycode.BS.dao.UserDao;
import com.mycode.BS.dao.UserDaoImpl;

public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        int userId = Integer.parseInt(id);

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");


        System.out.println("------------------------------------"+userId);

        UserDao ud = new UserDaoImpl();

        if(ud.update(userId, name, phone)){
            request.setAttribute("xiaoxi", "更新成功");
            request.getRequestDispatcher("/Searchall").forward(request, response);
        }else{
            response.sendRedirect("index.jsp");
        }
    }
}
