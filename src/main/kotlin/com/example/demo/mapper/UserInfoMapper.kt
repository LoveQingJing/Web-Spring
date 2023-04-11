package com.example.demo.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.example.demo.pojo.UserInfo
import org.apache.ibatis.annotations.CacheNamespace
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserInfoMapper : BaseMapper<UserInfo> {
}