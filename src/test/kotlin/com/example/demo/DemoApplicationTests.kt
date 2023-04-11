package com.example.demo

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.example.demo.enums.EnumSmsType
import com.example.demo.pojo.UserInfo
import com.example.demo.properties.AliyunProperties
import com.example.demo.query.MyQueryWrapper
import com.example.demo.service.impl.UserInfoServiceImpl
import com.example.demo.utils.SendSmsUtils
import lombok.extern.log4j.Log4j
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

    private val logger = LoggerFactory.getLogger(DemoApplicationTests::class.java)

    @Autowired
    val sendSmsUtils: SendSmsUtils? = null

    @Autowired
    private lateinit var userInfoServiceImpl: UserInfoServiceImpl
    @Test
    fun contextLoads() {

        val userInfo = UserInfo(phone = "17608461509", password = "123456")
        val wrapper = MyQueryWrapper<UserInfo>()
        wrapper.eq("phone", userInfo.phone)
        val userInfo1 = userInfoServiceImpl.getOne(wrapper)
        val userInfo2 =userInfoServiceImpl.getOne(wrapper)
        logger.info("user1 is from cache: {}", userInfo1)
        logger.info("user2 is from cache: {}", userInfo2)
        assert(userInfo1 == userInfo2)
        println(userInfo1 == userInfo2)

    }

}
