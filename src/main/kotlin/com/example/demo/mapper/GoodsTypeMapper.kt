package com.example.demo.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.example.demo.pojo.GoodsType
import org.apache.ibatis.annotations.Mapper


@Mapper
interface GoodsTypeMapper : BaseMapper<GoodsType> {
}