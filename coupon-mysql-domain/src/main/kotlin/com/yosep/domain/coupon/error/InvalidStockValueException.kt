package com.yosep.domain.coupon.error

import com.yosep.core.error.YosepErrorException

class InvalidStockValueException(override var message: String): YosepErrorException() {

}