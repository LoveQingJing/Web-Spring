package com.example.demo.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "template-code")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCode {

    public String login;

    public String register;

}
