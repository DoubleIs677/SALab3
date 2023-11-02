package com.mycode.BS.dao;


import com.mycode.BS.entity.User;

import java.util.List;

public interface UserDao {
    public boolean register(User user);
    public List<User> getUserAll();//返回用户信息集合
    public boolean delete(int id) ;//根据id删除用户
    public boolean update(int id,String name, String phone) ;
}
