package de.sokenou.test.mutation.stock.domain

import org.assertj.core.api.Assertions
import kotlin.test.Test

class AmountTest {

    @Test
    internal fun `it should not accept negative value`() {
        Assertions.assertThatThrownBy {
            Amount(-1)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}