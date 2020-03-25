package life.majiang.community.community.service;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 10:03
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
             User dbUser = userMapper.findByAccountId(user.getAccountId());
             if(dbUser==null)
             {
                 //insert
                 user.setGmtCreate(System.currentTimeMillis());
                 user.setGmtModified(user.getGmtCreate());
                 userMapper.insert(user);
             }
             else{
                 //update
                 dbUser.setGmtModified(System.currentTimeMillis());
                 dbUser.setAvatarUrl(user.getAvatarUrl());
                 dbUser.setName(user.getName());
                 dbUser.setToken(user.getToken());
                 userMapper.update(dbUser);
             }
    }
}
