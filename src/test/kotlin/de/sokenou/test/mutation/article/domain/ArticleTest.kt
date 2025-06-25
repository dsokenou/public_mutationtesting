package de.sokenou.test.mutation.article.domain

import de.sokenou.test.mutation.stock.domain.Amount
import de.sokenou.test.mutation.stock.domain.StockItem
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*

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
        "0.01,-0.01",
        "100001,100000",
        "2,0.00",
        "17,4",
        // TODO add additional tests for cover boundaries
//        "-0.01,-0.01",
//        "0.00,0.00",
//        "0.01,0.00",
//        "1,1"
    )
    
    internal fun `it should not accept invalid price as minimum and maximum price`(
        minimumPrice: String,
        maximumPrice: String
    ) {
        assertThatThrownBy {
            createArticleWithPrices(minimumPrice, maximumPrice)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `it should update article`() {
        val articleId = UUID.randomUUID()
        val article =
            createArticleFull("an old article name", "an old article description", "1.00", "2.00", articleId)
        val updatedArticle =
            createArticleFull("a new article name", "a new article description", "10.00", "15.00", articleId)

        article.updateWith(updatedArticle)

        // TODO do not compare article by insufficient equals function (should use deep compare instead)
        assertThat(article).isEqualTo(updatedArticle)
    }

    @Test
    internal fun `it should not update article with different ID`() {
        val article = createArticle()
        val updatedArticle = createArticle()

        assertThatThrownBy {
            article.updateWith(updatedArticle)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `it should be equal if ID is equal`() {
        val articleId = UUID.randomUUID()
        val article =
            createArticleFull("an old article name", "an old article description", "1.00", "2.00", articleId)
        val anotherArticle =
            createArticleFull("a new article name", "a new article description", "10.00", "15.00", articleId)

        assertThat(article).isEqualTo(anotherArticle)
    }

    @Test
    internal fun `it should be not equal if ID differ`() {
        val article = createArticle()
        val anotherArticle = createArticle()

        assertThat(article).isNotEqualTo(anotherArticle)
    }

    @Test
    internal fun `it should be not equal if other is not an article`() {
        val article = createArticle()

        assertThat(article).isNotEqualTo(article.id)
    }

    @Test
    internal fun `it calculate individual hashCode`() {
        val articleList = mutableListOf<Article>()
        for (i in 1..100) {
            articleList.add(createArticle())
        }
        val idList = articleList.map { it.hashCode() }.toSet()

        assertThat(idList).hasSameSizeAs(articleList)
    }

    @Test
    internal fun `it calculate same hashCode for article with same ID`() {
        val articleId = UUID.randomUUID()
        val article =
            createArticleFull("an old article name", "an old article description", "1.00", "2.00", articleId)
        val anotherArticle =
            createArticleFull("a new article name", "a new article description", "10.00", "15.00", articleId)

        assertThat(article.hashCode()).isEqualTo(anotherArticle.hashCode())
    }
    // TODO if hash code generation has complex conditions, it may be better to exclude them from mutation testing
}