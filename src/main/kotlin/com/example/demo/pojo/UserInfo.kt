package com.example.demo.pojo

import com.baomidou.mybatisplus.annotation.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable


/**
 * 用户基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
data class UserInfo(
        @TableId(type = IdType.AUTO)
        val id: Int? = null,
        @TableField("account")
        var account: String? = null,
        @TableField("password")
        var password: String,
        @TableField("phone")
        val phone: String? = null,
        @TableField("status")
        val status: Int = 1,
) : Serializable {

}