package de.sokenou.test.mutation.article.persistence

import de.sokenou.test.mutation.article.domain.Article
import java.math.BigDecimal
import java.util.*

fun createArticleEntity(
    name: String = "an article",
    description: String = "a description",
    id: UUID = UUID.randomUUID()
) =
    ArticleEntity(name, description, Article.MINIMUM_PRICE.value, Article.MAXIMUM_PRICE.value, id)

fun createArticleEntityWithPrices(mininumPrice: String, maximumPrice: String, id: UUID = UUID.randomUUID()) =
    ArticleEntity("an article", "a description", BigDecimal(mininumPrice), BigDecimal(maximumPrice), id)