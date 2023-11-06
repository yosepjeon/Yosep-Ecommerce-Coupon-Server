package com.yosep.domain.global.infra

import org.springframework.core.env.Environment
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.stereotype.Component
import javax.sql.DataSource


@Component
class EntityManagerFactory {
    private val env: Environment? = null
    fun getEntityManger(
        dataSource: DataSource?,
        packageName: Array<String?>
    ): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource!!
        em.setPackagesToScan(*packageName)
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = env!!.getProperty("spring.jpa.hibernate.ddl-auto")
        properties["hibernate.dialect"] = env.getProperty("spring.jpa.database-platform")
        em.setJpaPropertyMap(properties)
        return em
    }
}
