package life.majiang.community.community.service;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users=userMapper.selectByExample(userExample);
             if(users.size()==0)
             {
                 //insert
                 user.setGmtCreate(System.currentTimeMillis());
                 user.setGmtModified(user.getGmtCreate());
                 userMapper.insert(user);
             }
             else{
                 User dbUser = users.get(0);

                 User updateUswer =new User();
                 updateUswer.setGmtModified(System.currentTimeMillis());
                 updateUswer.setAvatarUrl(user.getAvatarUrl());
                 updateUswer.setName(user.getName());
                 updateUswer.setToken(user.getToken());
                 UserExample example = new UserExample();
                 example.createCriteria().andIdEqualTo(dbUser.getId());
                 userMapper.updateByExampleSelective(updateUswer, example);
             }
    }
}
