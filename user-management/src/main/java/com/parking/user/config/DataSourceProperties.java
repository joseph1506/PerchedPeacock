package com.parking.user.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource")
@NoArgsConstructor
@Getter
@Setter
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

}
