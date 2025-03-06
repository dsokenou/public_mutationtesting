package de.sokenou.test.mutation.article.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal
import kotlin.test.Test

class PriceTest {

    @ParameterizedTest
    @CsvSource(
        "0, 0, 0",
        "0, 0.00, 0",
        "2.01, 2.01, 0",
        "-2.01, -2.01, 0",
        "0, 1, -1",
        "1, 0 , 1",
        "2.01, 2.02, -1",
        "2.02, 2.01, 1",
        "-2.01, -2.02, 1",
        "-2.02, -2.01, -1",
    )
    internal fun `it should be comparable`(value1: String, value2: String, expected: Int) {
        assertThat(Price.of(value1).compareTo(Price.of(value2))).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 0, true",
        "0, 0.00, true",
        "0, 0.0000, true",
        "2.01, 2.01, true",
        "2.01, 2.0100, true",
        "-2.01, -2.01, true",
        "0, 1, false",
        "1, 0 , false",
        "2.01, 2.02, false",
        "2.02, 2.01, false",
        "-2.01, -2.02, false",
        "-2.02, -2.01, false",
    )
    internal fun `it should implement equals`(value1: String, value2: String, expected: Boolean) {
        assertThat(Price.of(value1).equals(Price.of(value2))).isEqualTo(expected)
    }

    @Test
    internal fun `it should not be equals to other type of object`() {
        val number = "0.0"
        val price = Price.of(number)

        assertThat(price.equals(number)).isFalse
        assertThat(price.equals(0.0)).isFalse
        assertThat(price.equals(BigDecimal(0.0))).isFalse
        assertThat(price.equals(null)).isFalse
    }

    @Test
    internal fun `it should have same hash code if same`() {
        val number = "0.0"
        val price1 = Price.of(number)
        val price2 = Price.of(number)

        assertThat(price1.hashCode()).isEqualTo(price2.hashCode())
    }

    // TODO test also difference of hash codes if not same...

    @ParameterizedTest
    @ValueSource(strings = ["1.1110", "111.00111", "abc", "1.1.1"])
    internal fun `it should not accept invalid price values`(value: String) {
        assertThatThrownBy { Price.of(value) }.isInstanceOf(IllegalArgumentException::class.java)
    }
}