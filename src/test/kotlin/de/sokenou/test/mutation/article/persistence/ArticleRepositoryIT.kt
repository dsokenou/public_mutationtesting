package de.sokenou.test.mutation.article.persistence

import com.ninjasquad.springmockk.MockkBean
import de.sokenou.test.mutation.article.domain.ArticleId
import de.sokenou.test.mutation.article.domain.ArticleRepository
import de.sokenou.test.mutation.article.domain.createArticle
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class ArticleRepositoryIT {

    @Autowired
    private lateinit var articleRepository: ArticleRepository

    @MockkBean
    private lateinit var articleJpaRepository: ArticleJpaRepository

    @Test
    internal fun `it should save an article`() {
        val article = createArticle(name = "my first article")
        val articleEntity = createArticleEntity(name = "my first article", id = article.id.value)
        every {
            articleJpaRepository.save(articleEntity)
        } returns articleEntity

        val savedArticle = articleRepository.save(article)

        verify { articleJpaRepository.save(articleEntity) }
        assertThat(savedArticle.id).isEqualTo(article.id)
    }

    @Test
    internal fun `it should find an article by id`() {
        val article = createArticle(name = "my second article")
        val articleEntity = createArticleEntity(name = "my second article", id = article.id.value)
        every {
            articleJpaRepository.save(articleEntity)
        } returns articleEntity
        every {
            articleJpaRepository.findByIdOrNull(articleEntity.id)
        } returns articleEntity

        val foundArticle = articleRepository.findByIdOrNull(article.id)

        verify { articleJpaRepository.findByIdOrNull(articleEntity.id) }
        assertThat(foundArticle).isNotNull
        assertThat(article.id).isEqualTo(foundArticle!!.id)
    }

    @Test
    internal fun `it return null if article is not found`() {
        val articleId = ArticleId.next()
        every {
            articleJpaRepository.findByIdOrNull(articleId.value)
        } returns null

        val foundArticle = articleRepository.findByIdOrNull(articleId)

        verify { articleJpaRepository.findByIdOrNull(articleId.value) }
        assertThat(foundArticle).isNull()
    }

    @Test
    internal fun `it should delete an article`() {
        val article = createArticle(name = "my first article")
        val articleEntity = createArticleEntity(name = "my first article", id = article.id.value)
        every {
            articleJpaRepository.save(articleEntity)
        } returns articleEntity
        justRun {
            articleJpaRepository.delete(articleEntity)
        }
        articleRepository.save(article)

        articleRepository.delete(article)

        // TODO this is the wrong assertion due to copy & paste error, should check if delete is called (not save) instead
        verify { articleJpaRepository.save(articleEntity) }
    }

}