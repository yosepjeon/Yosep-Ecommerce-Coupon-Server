package com.yosep.domain.stock.repository.write

import com.yosep.domain.stock.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockWriteRepository: JpaRepository<Stock, Long> {

}