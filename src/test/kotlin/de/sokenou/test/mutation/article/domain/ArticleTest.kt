package de.sokenou.test.mutation.article.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ArticleTest {

    @Test
    internal fun `it should have name and description`() {
        val name = "Article"
        val description = "Description"

        val article = createArticle(name, description)

        assertThat(article.name).isEqualTo(Name(name))
        assertThat(article.description).isEqualTo(Description(description))
    }

    @Test
    internal fun `it should have a generated id`() {
        val article = createArticle()

        assertThat(article.id).isNotNull
    }

    @Test
    internal fun `it should have a default minimum and maximum price`() {
        val article = createArticle()

        assertThat(article.minimumPrice).isEqualTo(Price.of("0"))
        assertThat(article.maximumPrice).isEqualTo(Price.of("10000"))
    }

    @ParameterizedTest
    @CsvSource(
        "0,7",
        "-0.0,0.01",
        "100000, 100001",
        "2,1000000",
        "17,34"
    )
    internal fun `it should accept valid price as minimum and maximum price`(
        minimumPrice: String,
        maximumPrice: String
    ) {
        val article = createArticleWithPrices(minimumPrice, maximumPrice)

        assertThat(article.minimumPrice).isEqualTo(Price.of(minimumPrice))
        assertThat(article.maximumPrice).isEqualTo(Price.of(maximumPrice))
    }

    @ParameterizedTest
    @CsvSource(
        "-0.01,0.01",
        "100001,100000",
        "2,0.00",
        "17,4",
        // TODO additional tests
//        "0.01,-0.01",
//        "-0.01,-0.01",
//        "0.00,0.00",
//        "0.01,0.00",
//        "1,1"
    )
    internal fun `it should not accept valid price as minimum and maximum price`(
        minimumPrice: String,
        maximumPrice: String
    ) {
        assertThatThrownBy {
            createArticleWithPrices(minimumPrice, maximumPrice)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

}