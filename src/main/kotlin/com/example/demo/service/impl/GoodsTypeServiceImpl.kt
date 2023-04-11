package com.example.demo.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.demo.mapper.GoodsTypeMapper
import com.example.demo.pojo.GoodsType
import com.example.demo.service.GoodsTypeService
import org.springframework.stereotype.Service

@Service
class GoodsTypeServiceImpl: ServiceImpl<GoodsTypeMapper, GoodsType>(), GoodsTypeService {

}