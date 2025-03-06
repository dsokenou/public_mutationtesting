package de.sokenou.test.mutation.article.domain

data class Article(
    val name: Name,
    val description: Description,
    val minimumPrice: Price = MINIMUM_PRICE,
    val maximumPrice: Price = MAXIMUM_PRICE,
    val id: ArticleId = ArticleId.next()
) {

    init {
        // TODO btw this condition may be optimized
        require(
            minimumPrice >= Price.ZERO && maximumPrice >= Price.ZERO && minimumPrice < maximumPrice
        ) {
            "minimum price and maximum price must not be negative and minimum price must be lower than maximum price"
        }
    }

    companion object {
        val MINIMUM_PRICE = Price.of("0")
        val MAXIMUM_PRICE = Price.of("10000")
    }

}
