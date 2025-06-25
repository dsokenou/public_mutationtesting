package de.sokenou.test.mutation.article.domain

import java.util.UUID

fun createArticle(name: String = "an article", description: String = "a description") =
    Article(Name(name), Description(description))

fun createArticleWithPrices(minimumPrice: String, maximumPrice: String) =
    Article(Name("an article"), Description("a description"), Price.of(minimumPrice), Price.of(maximumPrice))

fun createArticleFull(name: String, description: String, minimumPrice: String, maximumPrice: String, id: UUID) =
    Article(Name(name), Description(description), Price.of(minimumPrice), Price.of(maximumPrice), ArticleId.of(id))