package de.sokenou.test.mutation.article.domain

fun sortArticlesByName(articleList: List<Article>) {
    articleList.sortedBy { it.name.value }
}