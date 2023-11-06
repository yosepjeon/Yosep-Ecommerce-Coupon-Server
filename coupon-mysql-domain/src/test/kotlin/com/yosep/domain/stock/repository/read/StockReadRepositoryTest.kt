package com.yosep.domain.stock.repository.read

import com.yosep.domain.annotation.RepositoryTest
import com.yosep.domain.stock.entity.Stock
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles

@RepositoryTest
@ActiveProfiles("test")
class StockReadRepositoryTest(
    @Autowired
    var stockReadRepository: StockReadRepository
) {

    @Test
    fun stock_entity_동등성_테스트() {
        // Given
        val total = 30L
        val remain = 29L
        val stock1 = Stock(
            total = total,
            remain = remain
        )

        val stock2 = Stock(
            total = total,
            remain = remain
        )

        // When
        val createdStock1 = stockReadRepository.save(stock1)
        val createdStock2 = stockReadRepository.save(stock2)

        // Then
        assertNotEquals(createdStock1.id, createdStock2.id)
    }
}