package de.sokenou.test.mutation.article.domain

import java.util.*

data class ArticleId private constructor(val value: UUID) {

    companion object {

        fun next(): ArticleId = ArticleId(UUID.randomUUID())

        fun of(uuid: UUID) = ArticleId(uuid)

    }

}