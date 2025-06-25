package de.sokenou.test.mutation.article.domain

class Article(
    private var _name: Name,
    private var _description: Description,
    private var _minimumPrice: Price = MINIMUM_PRICE,
    private var _maximumPrice: Price = MAXIMUM_PRICE,
    val id: ArticleId = ArticleId.next()
) {

    val name: Name
        get() = _name
    val description: Description
        get() = _description
    val minimumPrice: Price
        get() = _minimumPrice
    val maximumPrice: Price
        get() = _maximumPrice

    init {
        // TODO btw this condition may be optimized
        require(
            minimumPrice >= Price.ZERO
                    && maximumPrice >= Price.ZERO
                    && minimumPrice < maximumPrice
        ) {
            "minimum price and maximum price must not be negative and minimum price must be lower than maximum price"
        }
    }

    fun updateWith(article: Article) {
        if (article.id != id) {
            throw IllegalArgumentException("IDs do not match")
        }
        updateFieldsWith(article)
    }

    private fun updateFieldsWith(article: Article) {
        this._name = article.name
        this._description = article.description
        this._minimumPrice = article.minimumPrice
        this._maximumPrice = article.maximumPrice
    }

    override fun equals(other: Any?): Boolean =
        other is Article
                && id == other.id

    // TODO this hashCode calculation is more complex than needed to demonstrate an equivalent mutation
    override fun hashCode(): Int = id.hashCode() - 1

    companion object {
        val MINIMUM_PRICE = Price.of("0")
        val MAXIMUM_PRICE = Price.of("10000")
    }

}
