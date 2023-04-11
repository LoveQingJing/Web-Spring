package com.example.demo.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.example.demo.pojo.GoodsInfo
import org.apache.ibatis.annotations.Mapper

@Mapper
interface GoodsInfoMapper: BaseMapper<GoodsInfo> {
}