package com.example.demo.web

import cn.hutool.core.codec.Base64
import com.example.demo.pojo.GoodsInfo
import com.example.demo.pojo.GoodsPic
import com.example.demo.resp.R
import com.example.demo.service.impl.GoodsInfoServiceImpl
import com.example.demo.service.impl.GoodsPicServiceImpl
import com.example.demo.utils.splitStr
import com.example.demo.utils.splitString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException


@RestController
@RequestMapping("/admin")
class AdminController {

    @Autowired
    private lateinit var goodsInfoServiceImpl: GoodsInfoServiceImpl

    @Autowired
    private lateinit var goodsPicServiceImpl: GoodsPicServiceImpl

    private var base64ImageString: String = ""

    @PostMapping("/goods/image", consumes = ["multipart/form-data", "image/jpeg"])
    fun createGoods(@RequestParam("file") file: MultipartFile): R<Boolean> {
        if (file.isEmpty) {
            return R.failure()
        }
        return try {
            val mimeType = file.contentType
            // 转换为Base64编码
            val base64Image = Base64.encode(file.bytes)
            base64ImageString = "data:$mimeType;base64,$base64Image"
            R.success()
        } catch (e: IOException) {
            R.failure(msg = e.message.toString(), data = null)
        }
    }

    // 提交表单信息, 添加商品
    @PostMapping("/goods/info")
    fun createGoodsInfo(@RequestBody goodsInfo: GoodsInfo?): R<Boolean> {
        val result: Boolean = goodsInfoServiceImpl.save(goodsInfo)
        if (result) {
            val splitString: List<String> = base64ImageString.splitString()
            splitString.forEach {
                run {
                    val goodsPic = GoodsPic(goodsPicData = it.toByteArray(), goodsInfoId = goodsInfo?.id)
                    goodsPicServiceImpl.save(goodsPic)
                }
            }
            return R.success()
        }
        return R.failure()
    }



}