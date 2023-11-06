package com.yosep.domain.stock.repository.read

import com.yosep.domain.stock.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockReadRepository: JpaRepository<Stock, Long> {
    
}