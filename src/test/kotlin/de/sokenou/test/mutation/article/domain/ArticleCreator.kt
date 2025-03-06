package de.sokenou.test.mutation.article.domain

fun createArticle(name: String = "an article", description: String = "a description") =
    Article(Name(name), Description(description))

fun createArticleWithPrices(minimumPrice: String, maximumPrice: String) =
    Article(Name("an article"), Description("a description"), Price.of(minimumPrice), Price.of(maximumPrice))
