package de.sokenou.test.mutation.article.persistence

import de.sokenou.test.mutation.article.domain.*
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.*

@Entity
data class ArticleEntity internal constructor(
    val name: String,
    val description: String,
    val minimumPrice: BigDecimal,
    val maximumPrice: BigDecimal,
    @Id
    val id: UUID
) {

    companion object {

        fun ArticleEntity.toDomain(): Article =
            Article(
                Name(name),
                Description(description),
                Price.of(minimumPrice),
                Price.of(maximumPrice),
                ArticleId.of(id)
            )

        fun Article.toEntity(): ArticleEntity =
            ArticleEntity(
                name.value,
                description.value,
                minimumPrice.value,
                maximumPrice.value,
                id.value
            )

    }

}