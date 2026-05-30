package com.anesthesiaclinic.anesthesiabriefs.utils

import android.content.Context
import com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.Locale

@Serializable
data class LocalQaItem(
    val id: String,
    val category: String,
    val keywords: List<String> = emptyList(),
    val question_tr: String,
    val question_en: String,
    val safetyLevel: String = "routine",
    val tr: AiStructuredResponse,
    val en: AiStructuredResponse
)

object LocalSearchEngine {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private var qaDatabase: List<LocalQaItem> = emptyList()
    private var isInitialized = false

    private val STOP_WORDS = setOf(
        "ve", "bir", "nedir", "nelerdir", "nasil", "nasıl", "dozu", "hasta", "orani", "oranı", 
        "veya", "icin", "için", "olan", "göre", "gore", "ile", "de", "da", "mi", "mı", "mu", "mü",
        "ne", "zaman", "tedavi", "tedavisi", "ilac", "ilaç", "doz", "kullanilir", "kullanılır", 
        "the", "and", "what", "how", "is", "dose", "patient", "pressure", "rate", "of", "for", 
        "with", "or", "in", "to", "at", "on", "by", "an", "a", "limit", "dosing", "treatment"
    )

    fun initialize(context: Context) {
        if (isInitialized) return
        try {
            val db1 = try {
                context.assets.open("local_qa_database.json").use { inputStream ->
                    inputStream.bufferedReader().use { it.readText() }
                }.let { jsonConfig.decodeFromString<List<LocalQaItem>>(it) }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList<LocalQaItem>()
            }

            val db2 = try {
                context.assets.open("local_assistant_daily_life_pack_1.json").use { inputStream ->
                    inputStream.bufferedReader().use { it.readText() }
                }.let { jsonConfig.decodeFromString<List<LocalQaItem>>(it) }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList<LocalQaItem>()
            }

            qaDatabase = db1 + db2
            isInitialized = true
            println("LocalSearchEngine: Successfully initialized database with ${qaDatabase.size} Q&A items.")
        } catch (e: Exception) {
            e.printStackTrace()
            println("LocalSearchEngine: Error loading local Q&A database: ${e.localizedMessage}")
        }
    }

    fun initializeForTest(jsonString: String) {
        qaDatabase = jsonConfig.decodeFromString<List<LocalQaItem>>(jsonString)
        isInitialized = true
    }

    fun getItemsCount(): Int = qaDatabase.size

    // Clean query text into clean token words
    private fun tokenize(text: String): Set<String> {
        val lowercase = text.lowercase(Locale.ROOT)
        return lowercase.split(Regex("[^a-zA-Z0-9ğüşöçıİĞÜŞÖÇ]+"))
            .filter { it.isNotEmpty() && it.length > 1 }
            .toSet()
    }

    // Pure Kotlin Levenshtein Distance for typo tolerance
    private fun levenshteinDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }
        for (i in 0..s1.length) dp[i][0] = i
        for (j in 0..s2.length) dp[0][j] = j
        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,      // deletion
                    dp[i][j - 1] + 1,      // insertion
                    dp[i - 1][j - 1] + cost // substitution
                )
            }
        }
        return dp[s1.length][s2.length]
    }

    private fun isFuzzyMatch(w1: String, w2: String): Boolean {
        if (w1 == w2) return true
        if (w1.length < 4 || w2.length < 4) return false
        // Suffix tolerance for Turkish: if first 4 characters match perfectly
        if (w1.substring(0, 4) == w2.substring(0, 4)) return true
        val distance = levenshteinDistance(w1, w2)
        val maxLength = maxOf(w1.length, w2.length)
        val similarity = 1.0 - (distance.toDouble() / maxLength.toDouble())
        return similarity >= 0.78
    }

    fun findBestMatch(query: String, isEnglish: Boolean): LocalQaItem? {
        if (query.isBlank()) return null
        
        val queryTokens = tokenize(query)
        if (queryTokens.isEmpty()) return null

        val filteredQueryTokens = queryTokens.filter { it !in STOP_WORDS }
        val activeTokens = if (filteredQueryTokens.isNotEmpty()) filteredQueryTokens else queryTokens.toList()

        var bestMatch: LocalQaItem? = null
        var highestScore = 0.0

        for (item in qaDatabase) {
            var score = 0.0
            var matchedTokenCount = 0

            // Match query tokens against item keywords
            for (qToken in activeTokens) {
                var matchedKeyword = false
                for (kw in item.keywords) {
                    if (isFuzzyMatch(qToken, kw)) {
                        score += if (qToken == kw) 1.0 else 0.85
                        matchedKeyword = true
                        break
                    }
                }
                if (matchedKeyword) {
                    matchedTokenCount++
                }
            }

            if (matchedTokenCount == 0) continue

            // Jaccard similarity index
            val totalTokens = (activeTokens.size + item.keywords.size - matchedTokenCount).toDouble()
            var jaccard = if (totalTokens > 0) score / totalTokens else 0.0

            // 1. Exact drug ID / keyword match boosting
            val lowercaseId = item.id.lowercase(Locale.ROOT)
            for (qToken in queryTokens) {
                if (lowercaseId.contains(qToken)) {
                    jaccard += 0.40 // Substantial boost for direct matching term in ID
                }
            }

            // 2. Exact match in questions boosting
            val questionText = if (isEnglish) item.question_en.lowercase(Locale.ROOT) else item.question_tr.lowercase(Locale.ROOT)
            for (qToken in activeTokens) {
                if (questionText.contains(qToken)) {
                    jaccard += 0.15 // Fix: update jaccard directly to boost correct match
                }
            }

            // If the query is an exact substring of the question, give a massive boost
            val cleanQuery = query.lowercase(Locale.ROOT).trim()
            if (questionText.contains(cleanQuery) || cleanQuery.contains(questionText)) {
                jaccard += 0.60
            }

            if (jaccard > highestScore) {
                highestScore = jaccard
                bestMatch = item
            }
        }

        // Confidence threshold check (e.g. 0.30 or 30%)
        return if (highestScore >= 0.28) {
            println("LocalSearchEngine: Found best match ${bestMatch?.id} with score $highestScore")
            bestMatch
        } else {
            println("LocalSearchEngine: No match found. Highest score was $highestScore")
            null
        }
    }
}
