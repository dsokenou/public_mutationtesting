package de.sokenou.test.mutation.stock.domain

import de.sokenou.test.mutation.article.domain.createArticle
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class StockItemTest {

    @Test
    internal fun `it should store article with amount`() {
        val article = createArticle()

        val stockItem = StockItem(article, Amount(100))

        assertThat(stockItem.amount).isEqualTo(Amount(100))
    }

    @Test
    internal fun `it should reduce amount of stock item`() {

    }

    @Test
    internal fun `it should add amount of stock item`() {

    }
}