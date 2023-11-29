package com.yosep.domain.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class AuditingEntity {
    @CreatedBy
    @Column(name = "insert_operator", updatable = false)
    protected var insertOperator: String? = null

    @LastModifiedBy
    @Column(name = "update_operator")
    protected var updateOperator: String? = null

    @CreatedDate
    @Column(name = "insert_time", updatable = false)
    protected var insertTime: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "update_time")
    protected var updateTime: LocalDateTime? = null
}
