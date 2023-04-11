package com.example.demo.service.impl

import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.demo.mapper.UserInfoMapper
import com.example.demo.pojo.UserInfo
import com.example.demo.service.UserInfoService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class UserInfoServiceImpl : ServiceImpl<UserInfoMapper, UserInfo> (), UserInfoService{

    @Cacheable(key = "#queryWrapper", cacheNames = ["userInfo"])
    override fun getOne(queryWrapper: Wrapper<UserInfo>?): UserInfo? {
        return super<ServiceImpl>.getOne(queryWrapper)
    }

}