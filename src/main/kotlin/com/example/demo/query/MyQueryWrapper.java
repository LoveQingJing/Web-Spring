package com.example.demo.query;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author 净
 */
public class MyQueryWrapper<T> extends QueryWrapper<T> {
    @Override
    public String toString() {
        // 将QueryWrapper对象转换成字符串作为缓存key
        return JSONUtil.toJsonStr(this);
    }
}