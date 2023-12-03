/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2023/11/29 21:21
 **/
package com.lizzy.demo.login.Service;

import com.lizzy.demo.login.entity.User;
import com.lizzy.demo.login.exception.ServiceException;
import com.lizzy.demo.login.mapper.LoginUserMappers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.rmi.ServerException;
import java.util.Random;

@Service//通过该注解将此对象注册为bean  ,将该类注入到spring的容器中
public class UserServices {
    @Resource
    LoginUserMappers userMappers;
    //增加
    public void insertUser(User user) {
    }
//验证用户身份是否合法
    public User login(User user) throws ServerException {
        //通过前端传送过来的User数据进行查询，如果查询不到则抛出异常
//        System.out.println("server"+user.getUsername()+user.getPassword()+user.getUserType());
        User dbUser = userMappers.selectByUsername(user.getUsername(),user.getUserType());
        if(dbUser == null){
            throw new ServerException("用户名或密码错误");//抛出自定义的异常之后被全局异常捕获器捕获
        }
        if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServerException("用户名或密码错误");
        }
        return  dbUser;
    }
    public User register(User user) {
        System.out.println(userMappers.selectByUsername(user.getUsername(),user.getUserType()));
        User dbuser = new User();
        Random random = new Random();
        int id = random.nextInt(999);
        for(int i = 0;i<=id;i++){
            if(id>99&&userMappers.selecetByUserID(id)==null){ break;
            }else{
                id = random.nextInt(999);
            }
        }

        dbuser.setUserID(id);
        dbuser.setUsername(user.getUsername());
        dbuser.setPassword(user.getPassword());
        dbuser.setUserType(user.getUserType());
        if(userMappers.selectByUsername(user.getUsername(),user.getUserType())==null){
            userMappers.insert(dbuser.getUsername(),dbuser.getUserType(),dbuser.getPassword(),dbuser.getUserID());

        }else{
            throw new ServiceException("该用户已存在");
        }return user;




    }

    public User selectByUsernameType(String username, Integer userType) {
        return userMappers.selectByUsername(username,userType);
    }
}
