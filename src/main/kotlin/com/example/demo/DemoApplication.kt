package com.example.demo

import com.example.demo.properties.AliyunProperties
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.redis.core.RedisTemplate
import java.util.Arrays

@SpringBootApplication
@MapperScan
@EnableCaching
class DemoApplication{

}


fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
