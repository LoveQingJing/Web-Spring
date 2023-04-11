package com.example.demo.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.demo.mapper.GoodsInfoMapper
import com.example.demo.pojo.GoodsInfo
import com.example.demo.service.GoodsInfoService
import org.springframework.stereotype.Service

@Service
class GoodsInfoServiceImpl: ServiceImpl<GoodsInfoMapper, GoodsInfo>(), GoodsInfoService {
}