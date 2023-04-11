package com.example.demo.pojo

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class GoodsPic(var goodsPicData: ByteArray, val goodsInfoId: Long?) {
}