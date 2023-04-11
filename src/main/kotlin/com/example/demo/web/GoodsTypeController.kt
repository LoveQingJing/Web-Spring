package com.example.demo.web

import com.example.demo.pojo.GoodsType
import com.example.demo.resp.R
import com.example.demo.service.impl.GoodsTypeServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/goods/type")
class GoodsTypeController {

    @Autowired
    private lateinit var goodsTypeServiceImpl: GoodsTypeServiceImpl
    
    @GetMapping("/list")
    fun goodsTypeList(): R<List<GoodsType>> {
        val goodsTypeList: List<GoodsType> = goodsTypeServiceImpl.list()
        if(goodsTypeList.isEmpty()){
            return R.failure("获取数据失败", data = null)
        }
        return R.success(data = goodsTypeList)
    }


}