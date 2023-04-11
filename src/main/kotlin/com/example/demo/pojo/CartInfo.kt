package com.example.demo.pojo

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * 购物车信息
 */
@TableName("cart_info")
data class CartInfo(
        @TableId("id")
        val id: Int,
        @TableField("user_info_id")
        val userInfoId: Int,
        @TableField("goods_info_id")
        val goodsInfoId: Int,
        @TableField("count")
        val count: Int
        ) {
}