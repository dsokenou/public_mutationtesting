package de.sokenou.test.mutation.stock.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AmountTest {

    @Test
    internal fun `it should not accept negative value`() {
        Assertions.assertThatThrownBy {
            Amount(-1)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `it should deliver value`() {
        // TODO mutation testing replaces int values with zero, thus a better test should be created
        val amount = Amount(0)

        assertThat(amount.value).isEqualTo(0)
    }

    @Test
    internal fun `it should add an Amount`() {
        val amount = Amount(0)

        assertThat(amount + Amount(100)).isEqualTo(Amount(100))
    }

    @Test
    internal fun `it should substrate an Amount`() {
        val amount = Amount(200)

        assertThat(amount - Amount(50)).isEqualTo(Amount(150))
    }

}