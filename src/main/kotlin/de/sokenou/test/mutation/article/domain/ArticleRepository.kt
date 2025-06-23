package de.sokenou.test.mutation.article.domain

interface ArticleRepository {

    fun save(article: Article): Article

    fun findByIdOrNull(id: ArticleId): Article?

    fun delete(article: Article)
}
