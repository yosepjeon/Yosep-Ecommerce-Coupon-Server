package com.yosep.domain.common.entity

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AutoIncPkEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
): Persistable<Long>, AuditingEntity() {

    override fun getId(): Long? {
        return id
    }

    override fun isNew(): Boolean {
        return id != 0L
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Serializable {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier as Serializable
        } else {
            (obj as AutoIncPkEntity).id
        }
    }

    val deleteTime: LocalDateTime = LocalDateTime.now()

    override fun hashCode() = Objects.hashCode(id)
}