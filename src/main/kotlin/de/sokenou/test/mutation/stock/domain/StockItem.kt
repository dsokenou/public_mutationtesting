package de.sokenou.test.mutation.stock.domain

import de.sokenou.test.mutation.article.domain.Article

class StockItem(val article: Article, private var _amount: Amount) {

    val amount: Amount
        get() = _amount

    fun increase(amount: Amount) {
        this._amount += amount
    }

    fun decrease(amount: Amount) {
        this._amount -= amount
    }
}
