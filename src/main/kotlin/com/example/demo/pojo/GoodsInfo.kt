package com.example.demo.pojo

import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * 商品信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods_info")
data class GoodsInfo(
        var id: Long?,
        var goodsName: String,
        var goodsPrice: Float,
        var goodsIsDelete: Int? = 0,
        var goodsUnit: String = "500g",
        var goodsClick: String? = "0",
        var goodsBrief: String,
        var goodsInventory: Int,
        var goodsDesc: String,
        var goodsTypeId: Int
): Serializable{}