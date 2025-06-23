package de.sokenou.test.mutation.stock.domain

data class Amount(val value: Int) {

    init {
        require(value >= 0) { "Amount must be non-negative" }
    }

    operator fun plus(amount: Amount): Amount {
        return Amount(this.value + amount.value)
    }

    operator fun minus(amount: Amount): Amount {
        return Amount(this.value - amount.value)
    }
}