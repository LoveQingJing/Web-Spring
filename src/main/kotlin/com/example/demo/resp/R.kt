package com.example.demo.resp

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


/*
* 通用类型类
* code:
*   0 -> 未知
*   1 -> 成功
*   2 -> 失败
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
data class R<T>(var code: Int, var msg: String, var data: T?) {

    // 不传入参数, 未知
    constructor() : this(code = 0, msg = "unknown", data = null)

    // 成功
    constructor(code: Int, msg: String) : this(code, msg, data = null)

    companion object {
        // 未知
        fun <T> unknown(): R<T> {
            return R()
        }

        // 成功
        fun <T> success(): R<T> {
            return R(1, "success")
        }

        fun <T> success(msg: String, data: T?): R<T> {
            return R(1, msg, data)
        }

        fun <T> success(data: T?): R<T> {
            return R(1, "success", data)
        }

        // 失败
        fun <T> failure(): R<T> {
            return R(2, "failure")
        }

        fun <T> failure(data: T?): R<T> {
            return R(2, "failure", data)
        }

        fun <T> failure(msg: String, data: T?): R<T> {
            return R(2, msg, data)
        }
    }

}