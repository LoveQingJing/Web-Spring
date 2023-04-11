package com.example.demo.pojo

import java.time.LocalDateTime

/**
 * 订单主信息
 */
data class OrderInfoHost(val id: Int, val orderNumbering: String, val isPay: Boolean = false, val amountCount: Float, val orderAddress: String, val createDateTime: LocalDateTime) {
}