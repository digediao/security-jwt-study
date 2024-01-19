package com.zzz;

import com.zzz.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityJwtStudyApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test1() {
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void generatePassword() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
