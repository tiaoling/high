package com.xiaojiuwo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaojiuwo.models.User;

/**
 * Created by liuhaibao on 15/12/31.
 */
@Service
public class UsersService extends BaseService<User>{



    public List<User> findByName(String name){
        User user = new User();
        user.setName(name);
        return myBatisGeneralRepository.getSqlSession().selectList("com.xiaojiuwo.models.mapper.UsersMapper.findUsersByName", user);
    }
}
