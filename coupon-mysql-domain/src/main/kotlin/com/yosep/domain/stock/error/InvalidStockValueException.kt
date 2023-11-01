package com.yosep.domain.stock.error

import com.yosep.core.error.YosepErrorException

class InvalidStockValueException(override var message: String): YosepErrorException() {

}