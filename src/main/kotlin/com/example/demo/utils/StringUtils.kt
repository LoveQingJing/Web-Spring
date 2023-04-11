package com.example.demo.utils

fun String.splitString(): List<String> = splitStr(this)

fun splitStr(str: String): List<String> {
    val resultList: MutableList<String> = ArrayList()
    val length = str.length
    val step = length / 10 // 计算每个等分的长度
    for (i in 0..9) {
        val start = i * step // 计算当前等分的起始位置
        val end = if (i == 9) length else (i + 1) * step // 计算当前等分的结束位置
        resultList.add(str.substring(start, end)) // 将当前等分添加到结果集中
    }
    return resultList
}