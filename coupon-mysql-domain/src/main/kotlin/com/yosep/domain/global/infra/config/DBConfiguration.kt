package com.yosep.domain.global.infra.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
class DBConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.yosep-read")
    fun yosepReadDatasourceProperties(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    @ConfigurationProperties("spring.datasource.yosep-write")
    fun yosepWriteDatasourceProperties(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    fun yosepReadDataSource(): DataSource {
        return HikariDataSource(yosepReadDatasourceProperties())
    }

    @Bean
    @Primary
    fun yosepWriteDataSource(): DataSource {
        return HikariDataSource(yosepWriteDatasourceProperties())
    }
}
