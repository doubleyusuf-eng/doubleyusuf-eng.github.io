package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object FirestoreRepository {

    // Offline lists seeded from SeedData
    private val _drugsList = MutableStateFlow<List<Drug>>(SeedDataDrugs.drugsList)
    val drugsList: StateFlow<List<Drug>> = _drugsList.asStateFlow()

    private val _algorithmsList = MutableStateFlow(SeedData.algorithms)
    val algorithmsList: StateFlow<List<Algorithm>> = _algorithmsList.asStateFlow()

    private val _articlesList = MutableStateFlow(SeedData.articles)
    val articlesList: StateFlow<List<Article>> = _articlesList.asStateFlow()

    private val _calculatorsList = MutableStateFlow(SeedData.calculators)
    val calculatorsList: StateFlow<List<Calculator>> = _calculatorsList.asStateFlow()

    private val _favoritesList = MutableStateFlow<Set<String>>(emptySet())
    val favoritesList: StateFlow<Set<String>> = _favoritesList.asStateFlow()

    // Get specific details
    fun getDrugById(drugId: String): Drug? {
        return _drugsList.value.find { it.drugId.lowercase() == drugId.lowercase() }
    }

    fun getAlgorithmById(id: String): Algorithm? {
        return _algorithmsList.value.find { it.algorithmId.lowercase() == id.lowercase() }
    }

    fun getArticleById(id: Int): Article? {
        return _articlesList.value.find { it.id == id }
    }

    fun getCalculatorBySlug(slug: String): Calculator? {
        return _calculatorsList.value.find { it.slug.lowercase() == slug.lowercase() }
    }

    // Favorites management
    fun isFavorite(itemId: String): Boolean {
        return _favoritesList.value.contains(itemId)
    }

    fun toggleFavorite(itemId: String) {
        val current = _favoritesList.value.toMutableSet()
        if (current.contains(itemId)) {
            current.remove(itemId)
        } else {
            current.add(itemId)
        }
        _favoritesList.value = current
    }

    // Global Search across all content types
    fun searchAll(query: String): SearchResults {
        if (query.isBlank()) return SearchResults()
        val q = query.lowercase()

        val matchedCalculators = _calculatorsList.value.filter {
            it.titleTr.lowercase().contains(q) || it.titleEn.lowercase().contains(q) ||
                    it.descriptionTr.lowercase().contains(q)
        }

        val matchedDrugs = _drugsList.value.filter {
            it.nameTr.lowercase().contains(q) || it.nameEn.lowercase().contains(q) ||
                    it.genericName.lowercase().contains(q) ||
                    it.brandNames.any { brand -> brand.lowercase().contains(q) } ||
                    it.drugClass?.lowercase()?.contains(q) == true
        }

        val matchedAlgorithms = _algorithmsList.value.filter {
            it.titleTr.lowercase().contains(q) || it.titleEn.lowercase().contains(q) ||
                    it.category.lowercase().contains(q)
        }

        val matchedArticles = _articlesList.value.filter {
            it.titleTr.lowercase().contains(q) || it.titleEn.lowercase().contains(q) ||
                    it.journal.lowercase().contains(q) ||
                    it.keyMessageTr.lowercase().contains(q)
        }

        return SearchResults(
            calculators = matchedCalculators,
            drugs = matchedDrugs,
            algorithms = matchedAlgorithms,
            articles = matchedArticles
        )
    }
}

data class SearchResults(
    val calculators: List<Calculator> = emptyList(),
    val drugs: List<Drug> = emptyList(),
    val algorithms: List<Algorithm> = emptyList(),
    val articles: List<Article> = emptyList()
)
