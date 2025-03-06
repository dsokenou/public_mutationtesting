package de.sokenou.test.mutation.article.persistence

import com.ninjasquad.springmockk.MockkBean
import de.sokenou.test.mutation.article.domain.ArticleRepository
import de.sokenou.test.mutation.article.domain.createArticle
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

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


}