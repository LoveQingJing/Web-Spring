package com.example.demo.web

import cn.hutool.crypto.digest.DigestUtil
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils
import com.baomidou.mybatisplus.core.toolkit.StringUtils
import com.example.demo.pojo.UserInfo
import com.example.demo.query.MyQueryWrapper
import com.example.demo.resp.R
import com.example.demo.service.impl.UserInfoServiceImpl
import com.example.demo.utils.RedisUtil
import com.mifmif.common.regex.Generex
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userInfoServiceImpl: UserInfoServiceImpl

    @Autowired
    private lateinit var redisUtil: RedisUtil

    // 登录: 账号(包括手机号)密码登录
    @PostMapping("/login")
    fun login(@RequestBody userInfo: UserInfo, httpServletRequest: HttpServletRequest): R<UserInfo> {
        userInfo.password = DigestUtil.md5Hex(userInfo.password)
        val queryWrapper = MyQueryWrapper<UserInfo>()
        queryWrapper.eq("account", userInfo.account).and {
            it.eq("password", userInfo.password)
        }.or { it ->
            it.eq("phone", userInfo.account).and {
                it.eq("password", userInfo.password)
            }
        }
        val result: UserInfo? = userInfoServiceImpl.getOne(queryWrapper)
        if (ObjectUtils.isNotEmpty(result)) {
            httpServletRequest.session.setAttribute("user", result?.account)
            return R.success(data = result)
        }
        return R.failure(data = null)
    }


    // 验证码登录
    @PostMapping("/login/code")
    fun loginCode(@RequestParam("phone") phone: String, @RequestParam("code") code: String, httpServletRequest: HttpServletRequest): R<Boolean> {
        val codePhone = redisUtil.get(phone)
        if (codePhone != code) {
            return R.failure(msg = "验证码错误", data = false)
        }
        httpServletRequest.session.setAttribute("user", phone)
        return R.success(msg = "验证码正确", data = true)
    }

    // 登出
    @PostMapping("/logout")
    fun logout(httpServletRequest: HttpServletRequest): R<UserInfo> {
        val phone: String = httpServletRequest.session.getAttribute("user") as String;
        if (StringUtils.isNotEmpty(phone)) {
            httpServletRequest.session.removeAttribute("user")
            // 退出需要清空验证码
            redisUtil.delete(phone)
            return R.success()
        }
        return R.unknown()
    }

    // 注册
    @PostMapping("/register")
    fun register(@RequestBody userInfo: UserInfo, @RequestParam("code") code: String): R<UserInfo> {
        val phoneCode = userInfo.phone?.let {
            redisUtil.get(it)
        }
        // 校验验证码成不成功, 成功才能执行下边的操作
        if (code != phoneCode) {
            return R.failure(msg = "验证码错误", data = null)
        }
        userInfo.account = Generex("[1-9][0-9]{4,10}").random()
        userInfo.password = DigestUtil.md5Hex(userInfo.password)
        val result: Boolean = userInfoServiceImpl.save(userInfo)
        if (result) {
            return R.success(msg = "${userInfo.account}", data = null)
        }
        return R.failure(msg = "注册失败, 未知错误, 请联系管理员", data = null)
    }

    // 校验手机号是否注册, 或者校验账号是否存在 不存在则成功, 存在则失败
    @GetMapping("/check/account")
    fun checkAccount(@RequestParam account: String): R<UserInfo> {
        val queryWrapper = MyQueryWrapper<UserInfo>()
        queryWrapper.eq("account", account).or {
            it.eq("phone", account)
        }
        val result: UserInfo? = userInfoServiceImpl.getOne(queryWrapper)
        if (ObjectUtils.isNotEmpty(result)) {
            // 表示存在
            return R.success(data = result)
        }
        return R.failure()
    }
}
