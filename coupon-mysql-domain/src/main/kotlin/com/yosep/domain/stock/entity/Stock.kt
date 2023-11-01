package com.yosep.domain.stock.entity

import com.yosep.domain.common.entity.AutoIncPkEntity
import com.yosep.domain.stock.error.InvalidStockValueException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.lang.NonNull

@Entity
@Table(name = "yosep_coupon_stock")
class Stock(
    @NonNull
    @Column(nullable = false)
    var total: Long,

    @NonNull
    @Column(nullable = false)
    var remain: Long
): AutoIncPkEntity() {

    fun increaseTotal(value: Long) {
        ensureValidStock(value)

        val nextTotal = total + value
        val nextRemain = remain + value

        ensureValidStock(nextTotal)
        ensureValidStock(nextRemain)

        total = nextTotal
        remain = nextRemain
    }

    fun decreaseTotal(value: Long) {
        ensureValidStock(value)

        val nextTotal = total - value
        val nextRemain = remain - value

        ensureValidStock(nextTotal)
        ensureValidStock(nextRemain)

        total = nextTotal
        remain = nextRemain
    }

    fun increaseRemain(value: Long) {
        ensureValidStock(value)

        val nextRemain = remain + value

        ensureValidStock(nextRemain)
        remain = nextRemain
    }

    fun decreaseRemain(value: Long) {
        ensureValidStock(value)

        val nextRemain = remain - value

        ensureValidStock(nextRemain)
        remain = nextRemain
    }

    private fun ensureValidStock(value: Long) {
        if (value < 1) {
            throw InvalidStockValueException("value값은 1보다 같거나 커야합니다.")
        }
    }
}