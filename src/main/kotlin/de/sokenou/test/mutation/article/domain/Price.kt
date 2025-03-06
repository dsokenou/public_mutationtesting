package de.sokenou.test.mutation.article.domain

import java.math.BigDecimal

class Price private constructor(val value: BigDecimal) : Comparable<Price> {

    override fun compareTo(other: Price): Int {
        return value.compareTo(other.value)
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Price
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {

        val ZERO = of("0")

        fun of(value: String): Price {
            try {
                return Price(BigDecimal(value).setScale(2))
            } catch (ex: ArithmeticException) {
                throw IllegalArgumentException(ex)
            }
        }

        fun of(value: BigDecimal): Price {
            return Price(value)
        }
    }
}

