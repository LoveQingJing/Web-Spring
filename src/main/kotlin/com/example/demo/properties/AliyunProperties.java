package com.example.demo.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunProperties {

    public String accessKeyId;
    public String accessKeySecret;
    public String region;
    public TemplateCode templateCode;
    public String signName;

}
