package de.sokenou.test.mutation.article.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Description(@Column(name = "description") val value: String)