package com.example.demo.utils

import cn.hutool.core.util.RandomUtil
import cn.hutool.json.JSON
import cn.hutool.json.JSONObject
import cn.hutool.json.JSONUtil
import com.aliyun.auth.credentials.Credential
import com.aliyun.auth.credentials.provider.StaticCredentialProvider
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest
import com.example.demo.enums.EnumSmsType
import com.example.demo.properties.AliyunProperties
import com.example.demo.resp.R
import com.google.gson.Gson
import darabonba.core.client.ClientOverrideConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class SendSmsUtils {

    @Autowired
    val aliyunProperties: AliyunProperties? = null

    @Autowired
    val redisUtil: RedisUtil? = null

    fun getSmsCode(number: Int): String {
        val stringBuffer: StringBuffer = StringBuffer()
        for (i in 0 until number) {
            stringBuffer.append(RandomUtil.randomNumber())
        }
        return stringBuffer.toString();
    }

    fun sendSmsCode(phone: String, type: EnumSmsType): R<Boolean> {
        val provider = StaticCredentialProvider.create(
                Credential.builder()
                        .accessKeyId(aliyunProperties?.accessKeyId)
                        .accessKeySecret(aliyunProperties?.accessKeySecret)
                        .build()
        )

        val client = AsyncClient.builder()
                .region(aliyunProperties?.region) // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build()
        var sendSmsRequest: SendSmsRequest? = null
        val smsCode: String = getSmsCode(6)
        when (type) {
            EnumSmsType.LOGIN -> {
                sendSmsRequest = getSendSmsRequest(phone, aliyunProperties?.templateCode!!.login, smsCode)
            }
            EnumSmsType.REGISTER -> {
                sendSmsRequest = getSendSmsRequest(phone, aliyunProperties?.templateCode!!.register, smsCode)
            }
            else -> {
                return R.unknown()
            }
        }
        try {
            val resp = client.sendSms(sendSmsRequest).get()
            // 保存验证码信息地点
            redisUtil?.set(phone, smsCode, 5, TimeUnit.MINUTES)
            println(Gson().toJson(resp))
        } catch (e: Exception) {
            return R.failure(msg = e.message.toString(), data = false)
        }
        client.close()
        return R.success(data = true)
    }

    private fun getSendSmsRequest(phone: String, templateCode: String, code: String): SendSmsRequest {
        val json: JSON = JSONObject()
        json.putByPath("code", code)
        val toJsonStr = JSONUtil.toJsonStr(json)
        return SendSmsRequest.builder()
                .signName(aliyunProperties?.signName)
                .templateCode(templateCode)
                .phoneNumbers(phone)
                .templateParam(toJsonStr)
                .build()
    }
}

