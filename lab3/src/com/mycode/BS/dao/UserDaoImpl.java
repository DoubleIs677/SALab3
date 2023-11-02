package com.mycode.BS.dao;




import com.mycode.BS.entity.User;
import com.mycode.BS.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{


    @Override
    public boolean register(User user) {
        boolean flag = false;
        DBconn.init();
        int i =DBconn.addUpdDel("insert into t_user(uname,uphone) " +
                "values('"+user.getName()+"','"+user.getPhone()+"')");
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }

    @Override
    public List<User> getUserAll() {
        List<User> list = new ArrayList<User>();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from t_user");
            while(rs.next()){
                User user = new User();

                user.setId(rs.getInt("uid"));
                user.setName(rs.getString("uname"));
                user.setPhone(rs.getString("uphone"));

                list.add(user);
            }
            DBconn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        DBconn.init();
        String sql = "delete  from t_user where uid="+id;
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }

    @Override
    public boolean update(int id, String name, String phone) {
        boolean flag = false;
        DBconn.init();
        String sql ="update t_user set uname ='"+name
                +"' , uphone ='"+phone
                +"' where uid = "+id;
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;

    }


}