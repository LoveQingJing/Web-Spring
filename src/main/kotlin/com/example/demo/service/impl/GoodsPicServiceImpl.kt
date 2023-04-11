package com.example.demo.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.demo.mapper.GoodsPicMapper
import com.example.demo.pojo.GoodsPic
import com.example.demo.service.GoodsPicService
import org.springframework.stereotype.Service

@Service
class GoodsPicServiceImpl: ServiceImpl<GoodsPicMapper, GoodsPic>(), GoodsPicService {
}