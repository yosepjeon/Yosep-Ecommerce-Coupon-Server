package com.yosep.domain.stock.value

import com.yosep.domain.coupon.error.InvalidStockValueException
import com.yosep.domain.coupon.value.Stock
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class StockTest {

    lateinit var stock: Stock

    @BeforeEach
    fun init() {
        stock = Stock(
            total = 30L,
            remain = 30L
        )
    }

    @Test
    fun 재고엔티티_프로퍼티_비교_성공() {
        // Given
        val total = 30L
        val remain = 29L

        val stock = Stock(
            total = total,
            remain = remain
        )

        // When & Then
        assertEquals(total, stock.total)
        assertEquals(remain, stock.remain)
    }

    @Test
    fun 재고엔티티_increaseTotal_성공() {
        // Given
        val value = 3L
        val total = 33L
        val remain = 33L

        // When
        stock.increaseTotal(value)

        // Then
        assertEquals(total, stock.total)
        assertEquals(remain, stock.remain)
    }

    @Test
    fun 재고엔티티_increaseRemain_성공() {
        // Given
        val value = 4L
        val remain = 34L

        // When
        stock.increaseRemain(value)

        // Then
        assertEquals(remain, stock.remain)
    }

    @Test
    fun 재고엔티티_decreaseTotal_성공() {
        // Given
        val value = 1L
        val remain = 29L
        val total = 29L

        // When
        stock.decreaseTotal(value)

        // Then
        assertEquals(total, stock.total)
        assertEquals(remain, stock.remain)
    }

    @Test
    fun 재고엔티티_decreaseRemain_성공() {
        // Given
        val value = 3L
        val remain = 27L

        // When
        stock.decreaseRemain(value)

        // Then
        assertEquals(remain, stock.remain)
    }

    @Test
    fun 재고엔티티_ensureValidStock_음수일경우_싪패() {
        // Given
        val value = -1L
        val stock = Stock(
            total = 30L,
            remain = 30L
        )

        // When & Then
        assertThrows(InvalidStockValueException::class.java) {
            ReflectionTestUtils.invokeMethod<Void>(stock, "ensureValidStock", value)
        }
    }

    @Test
    fun 재고엔티티_increase_decrease_연산들에서_value가_음수일경우_실패() {
        // Given
        val value = -1L

        // When & Then
        assertThrows(InvalidStockValueException::class.java) { stock.increaseTotal(value) }
        assertThrows(InvalidStockValueException::class.java) { stock.increaseRemain(value) }
        assertThrows(InvalidStockValueException::class.java) { stock.decreaseTotal(value) }
        assertThrows(InvalidStockValueException::class.java) { stock.decreaseRemain(value) }
    }
}