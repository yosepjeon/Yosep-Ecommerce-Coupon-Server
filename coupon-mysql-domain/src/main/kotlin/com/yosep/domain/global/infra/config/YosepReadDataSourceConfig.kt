package com.yosep.domain.global.infra.config

import com.yosep.domain.global.infra.EntityManagerFactory
import org.hibernate.cfg.AvailableSettings
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.SpringBeanContainer
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "yosepReadEntityManager",
    transactionManagerRef = "lmsReadTransactionManager",
    basePackages = ["com.yosep.domain.stock.repository.read", "com.yosep.domain.coupon.repository.read"]
)
class YosepReadDataSourceConfig(private val entityManagerFactory: EntityManagerFactory) {
    @Bean
    fun yosepReadEntityManager(
        @Qualifier("yosepReadDataSource") dataSource: DataSource?,
        beanFactory: ConfigurableListableBeanFactory?
    ): LocalContainerEntityManagerFactoryBean {
        val build = entityManagerFactory.getEntityManger(dataSource, LMS_READ_PACKAGE_NAME)
        build.jpaPropertyMap[AvailableSettings.BEAN_CONTAINER] = SpringBeanContainer(beanFactory!!)
        return build
    }

    @Bean
    fun lmsReadTransactionManager(
        @Qualifier("yosepReadEntityManager") entityManager: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = Objects.requireNonNull(entityManager.getObject())
        return transactionManager
    }

    companion object {
        private val LMS_READ_PACKAGE_NAME = arrayOf<String?>(
            "com.finda.credit.db.lmsdb.kcb.entity",
            "com.finda.credit.db.lmsdb.credit.entity",
            "com.finda.credit.db.lmsdb.common.entity",
            "com.finda.credit.db.lmsdb.quiz.entity"
        )
    }
}
