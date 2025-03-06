package de.sokenou.test.mutation.article.domain

interface ArticleRepository {

    fun save(article: Article): Article

    fun findById(id: ArticleId): Article?

}
