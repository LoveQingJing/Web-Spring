package com.example.demo.api

import com.example.demo.enums.EnumSmsType
import com.example.demo.resp.R
import com.example.demo.utils.SendSmsUtils
import com.example.demo.vo.Sms
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SmsController {

    @Autowired
    private lateinit var smsUtils: SendSmsUtils

    @PostMapping("/sendCode")
    fun sendSmsLogin(@RequestBody sms: Sms): R<Boolean> {
        when (sms.type) {
            "login" -> {
                return smsUtils.sendSmsCode(sms.phone, EnumSmsType.LOGIN)
            }
            "register" -> {
                return smsUtils.sendSmsCode(sms.phone, EnumSmsType.REGISTER)
            }
            else -> return R.unknown()
        }
    }

}