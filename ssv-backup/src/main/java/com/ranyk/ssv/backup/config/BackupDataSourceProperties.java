package com.ranyk.ssv.backup.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName:BackupDataSourceProperties
 * Description:数据源配置类
 *
 * @author ranyi
 * @date 2020-12-13 10:33
 * Version: V1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "ssv.backup.datasource")
public class BackupDataSourceProperties {

    private String host;
    private String userName;
    private String password;
    private String database;

}
