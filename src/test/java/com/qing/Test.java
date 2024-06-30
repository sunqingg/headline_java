package com.qing;

import com.qing.utils.JwtHelper;
import com.qing.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    private JwtHelper jwtHelper;

    @org.junit.jupiter.api.Test
    public void test1(){
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);
        String encrypt = MD5Util.encrypt("123456");
        System.out.println("encrypt = " + encrypt);
    }
}
