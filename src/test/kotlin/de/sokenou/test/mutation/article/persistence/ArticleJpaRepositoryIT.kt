package de.sokenou.test.mutation.article.persistence

import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import kotlin.test.Test

@DataJpaTest
class ArticleJpaRepositoryIT {

    @Autowired
    private lateinit var articleJpaRepository: ArticleJpaRepository

    @Autowired
    private lateinit var transactionTemplate: TransactionTemplate

    @Test
    @Transactional(propagation = Propagation.NEVER)
    internal fun `it should save an article`() {
        val articleEntity = createArticleEntity()

        transactionTemplate.execute {
            articleJpaRepository.save(articleEntity)
        }

        transactionTemplate.execute {
            val article = articleJpaRepository.findByIdOrNull(articleEntity.id)

            assertThat(article).isNotNull
            assertThat(article!!.id).isEqualTo(article.id)
        }
    }

}