package com.mycode.BS.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycode.BS.dao.UserDao;
import com.mycode.BS.dao.UserDaoImpl;
import com.mycode.BS.entity.User;

import java.io.IOException;

public class RegisteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name"); //获取jsp页面传过来的参数
        String phone = request.getParameter("phone");

        User user = new User(); //实例化一个对象，组装属性
        user.setName(name);
        user.setPhone(phone);

        UserDao ud = new UserDaoImpl();

        if(ud.register(user)){
            request.setAttribute("username", name);  //向request域中放置参数
            //request.setAttribute("xiaoxi", "注册成功");
            request.getRequestDispatcher("/success.jsp").forward(request, response);  //转发到登录页面
        }else{

            response.sendRedirect("index.jsp");//重定向到首页
        }
    }
}