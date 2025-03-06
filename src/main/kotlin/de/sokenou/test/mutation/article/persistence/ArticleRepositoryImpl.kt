package de.sokenou.test.mutation.article.persistence

import de.sokenou.test.mutation.article.domain.Article
import de.sokenou.test.mutation.article.domain.ArticleId
import de.sokenou.test.mutation.article.domain.ArticleRepository
import de.sokenou.test.mutation.article.persistence.ArticleEntity.Companion.toDomain
import de.sokenou.test.mutation.article.persistence.ArticleEntity.Companion.toEntity
import org.springframework.stereotype.Component


@Component
class ArticleRepositoryImpl(val articleJpaRepository: ArticleJpaRepository) : ArticleRepository {

    override fun save(article: Article): Article {
        return articleJpaRepository.save(article.toEntity()).toDomain()
    }

    override fun findById(id: ArticleId): Article? {
        TODO("Not yet implemented")
    }
}