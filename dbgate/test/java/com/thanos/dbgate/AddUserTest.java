package com.thanos.dbgate;

import com.thanos.common.pojo.UserIdAllocMapper;
import com.thanos.common.pojo.UserMapper;
import com.thanos.common.user.UserInfo;
import com.thanos.dbgate.endpoint.UserAccountEndPoint;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/9/18.
 */
public class AddUserTest {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    public void testIdAlloc() {
        String osid = "Md587654321_Test01";
        UserIdAllocMapper uuidMapper = new UserIdAllocMapper();
        uuidMapper.setOsid(osid);
        UserAccountEndPoint.userUUidgenerate(uuidMapper);
        System.out.println("user id obtained: " + uuidMapper.getId());
    }

    public void testQueryUser() {
        //AccountDAO accountDAO = (AccountDAO) context.getBean("acountDao");
        UserMapper userInfo = new UserMapper();
        userInfo.setUuid("105002987");
        userInfo.setUserName("姜姜");
        userInfo.setNickName("阿龙");
        userInfo.setAvatarURL("http://www.lucy.com/111.png");
        userInfo.setPassword("8807201139love");
        userInfo.setPhoneNumber("13572952253");
        UserAccountEndPoint.registerAccount(userInfo);
        UserMapper data = UserAccountEndPoint.userLonginByPhone("13572952253", "8807201139love");
        assert data.getUserName().equals("姜姜");
    }

    public void testQuery() {
        List<String> userIds = new ArrayList();
        userIds.add("105002987");
        List<UserMapper> list = UserAccountEndPoint.queryUserByIds(userIds);
        ;
    }

    @Test
    public void getByCache() {
        List<String> userIds = new ArrayList();
        userIds.add("105002987");
        List<UserMapper> list = UserInfo.getUserInfo(userIds);
        List<UserMapper> list2 = UserInfo.getUserInfo(userIds);
        ;
    }


}
