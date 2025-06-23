package de.sokenou.test.mutation.article.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ArticleSorterTest {

    @Test
    internal fun `it should sort articles`() {
        // TODO the list is already sorted thus sorting is not really tested
        val articleList = mutableListOf(
            createArticle("article 1"),
            createArticle("article 2"),
            createArticle("article 3"),
        )

        sortArticlesByName(articleList)

        Assertions.assertThat(articleList.map { it.name.value }).isEqualTo(
            mutableListOf(
                "article 1",
                "article 2",
                "article 3"
            )
        )
    }


}