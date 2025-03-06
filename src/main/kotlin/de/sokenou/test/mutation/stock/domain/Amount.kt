package de.sokenou.test.mutation.stock.domain

data class Amount(val value: Int) {

    init {
        require(value >= 0) { "Amount must be non-negative" }
    }
}