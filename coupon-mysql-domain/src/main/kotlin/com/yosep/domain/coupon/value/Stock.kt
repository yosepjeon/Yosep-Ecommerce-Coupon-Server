package com.yosep.domain.coupon.value

import com.yosep.domain.coupon.error.InvalidStockValueException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.springframework.lang.NonNull

@Embeddable
class Stock(
    @NonNull
    @Column(name = "total", nullable = false)
    var total: Long,

    @NonNull
    @Column(name = "remain", nullable = false)
    var remain: Long
) {

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