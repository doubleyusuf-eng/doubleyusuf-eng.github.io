package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion
import com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion
import com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepAccessManager
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.ClinicalTeal
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.DeepNavy
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.SoftGold
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
fun BoardPrepQuizScreen(
    boardType: String,
    quizMode: String, // "STUDY" or "EXAM"
    packId: String? = null,
    category: String? = null,
    difficulty: String? = null,
    bookmarkedOnly: Boolean = false,
    unansweredOnly: Boolean = false,
    incorrectOnly: Boolean = false,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", Context.MODE_PRIVATE) }

    var questions by remember { mutableStateOf<List<Any>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var currentIndex by remember { mutableStateOf(0) }
    var isRevealed by remember { mutableStateOf(false) }

    // User inputs
    var selectedMtfAnswers by remember { mutableStateOf(mutableMapOf<Int, Boolean?>()) } // index to True/False/null
    var selectedSbaAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var checkedVivaPoints by remember { mutableStateOf(mutableSetOf<Int>()) }

    // Bookmarking state
    var isBookmarked by remember { mutableStateOf(false) }

    // Quiz Session State
    var isFinished by remember { mutableStateOf(false) }
    val sessionStrictScores = remember { mutableStateListOf<Boolean>() }
    val sessionMtfStatementsScores = remember { mutableStateListOf<Int>() }
    var activeBoardType by remember { mutableStateOf(boardType) }

    // Independent languages (Refinement 7)
    var activeQuestionLang by remember { mutableStateOf(prefs.getString("board_prep_question_lang", "en") ?: "en") }
    val activeExplanationLang = remember { prefs.getString("board_prep_explanation_lang", "both") ?: "both" }

    // Expandable states for VivaScenarioQuestion
    var showVivaExpectedPoints by remember { mutableStateOf(false) }
    var showVivaRedFlags by remember { mutableStateOf(false) }
    var showVivaFollowUp by remember { mutableStateOf(false) }
    var showVivaLearningPoints by remember { mutableStateOf(false) }
    var vivaExamMarkedCompleted by remember { mutableStateOf(false) }

    // Load questions on launch with combined filters
    LaunchedEffect(activeBoardType, packId, category, difficulty, bookmarkedOnly, unansweredOnly, incorrectOnly) {
        val unfilteredList = BoardPrepRepository.getQuestionsFiltered(
            context = context,
            boardType = if (activeBoardType == "BOOKMARKS" || activeBoardType == "ALL") null else activeBoardType,
            packId = packId,
            category = category,
            difficulty = difficulty,
            bookmarkedOnly = bookmarkedOnly || (activeBoardType == "BOOKMARKS"),
            unansweredOnly = unansweredOnly,
            incorrectOnly = incorrectOnly
        )
        val isPrem = BoardPrepAccessManager.isPremiumUser(context)
        questions = if (isPrem) {
            unfilteredList
        } else {
            unfilteredList.filter { q ->
                val qId = when (q) {
                    is EdaicMtfQuestion -> q.id
                    is SingleBestAnswerQuestion -> q.id
                    is VivaScenarioQuestion -> q.id
                    else -> ""
                }
                BoardPrepAccessManager.isBoardPrepDemoAllowed(qId)
            }
        }
        isLoading = false
    }

    // React to changes in currentIndex to refresh inputs and bookmark states
    LaunchedEffect(currentIndex, questions) {
        if (questions.isNotEmpty() && currentIndex < questions.size) {
            val q = questions[currentIndex]
            val qId = when (q) {
                is EdaicMtfQuestion -> q.id
                is SingleBestAnswerQuestion -> q.id
                is VivaScenarioQuestion -> q.id
                else -> ""
            }
            
            // Check bookmark status
            val progress = BoardPrepRepository.getUserProgress(context, qId)
            isBookmarked = progress?.bookmarked ?: false

            // Reset inputs
            isRevealed = false
            selectedMtfAnswers = mutableMapOf()
            selectedSbaAnswerIndex = null
            checkedVivaPoints = mutableSetOf()

            // Reset Viva states
            showVivaExpectedPoints = false
            showVivaRedFlags = false
            showVivaFollowUp = false
            showVivaLearningPoints = false
            vivaExamMarkedCompleted = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = when (activeBoardType) {
                            "EDAIC_PART_I" -> "EDAIC Part I Prep"
                            "ABA_BASIC" -> "ABA BASIC Review"
                            "ABA_ADVANCED" -> "ABA ADVANCED Review"
                            "EDAIC_VIVA" -> "Viva Scenarios"
                            "BOOKMARKS" -> if (prefs.getString("settings_language", "en") == "tr") "Favori Sorular" else "Bookmarked Questions"
                            else -> "Board Quiz"
                        },
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.Close, contentDescription = "Exit", tint = Color.White)
                    }
                },
                actions = {
                    if (questions.isNotEmpty() && currentIndex < questions.size && !isFinished) {
                        IconButton(
                            onClick = {
                                val q = questions[currentIndex]
                                val qId = when (q) {
                                    is EdaicMtfQuestion -> q.id
                                    is SingleBestAnswerQuestion -> q.id
                                    is VivaScenarioQuestion -> q.id
                                    else -> ""
                                }
                                scope.launch {
                                    val newBookmarkState = !isBookmarked
                                    BoardPrepRepository.toggleBookmark(context, qId, newBookmarkState)
                                    isBookmarked = newBookmarkState
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark",
                                tint = if (isBookmarked) SoftGold else Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { innerPadding ->
        val isTrQuestion = activeQuestionLang == "tr"

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DeepNavy, DeepNavy.copy(alpha = 0.85f))
                    )
                )
                .padding(innerPadding)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = SoftGold)
                }
            } else if (questions.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Info, contentDescription = "No questions", tint = Color.White.copy(alpha = 0.5f), modifier = Modifier.size(64.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    val emptyMessage = if (activeBoardType == "BOOKMARKS") {
                        if (isTrQuestion) "Henüz favori soru eklemediniz. Soru çözerken sağ üstteki yıldız ikonuna tıklayarak favorilerinize ekleyebilirsiniz."
                        else "You haven't bookmarked any questions yet. Bookmark questions by clicking the star icon at the top right while taking quizzes."
                    } else if (incorrectOnly) {
                        if (isTrQuestion) "Harika! Bu kategoride henüz yanlış cevapladığınız soru bulunmuyor."
                        else "Great! No incorrect questions found in this category yet."
                    } else {
                        if (isTrQuestion) "Soru paketi yüklenemedi. Lütfen ayarlardan güncellemeleri kontrol edin."
                        else "Failed to load question pack. Please check for updates in settings."
                    }
                    Text(
                        text = emptyMessage,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onBackClick, colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal)) {
                        Text(text = "Geri Dön")
                    }
                }
            } else if (isFinished) {
                // SESSION END SUMMARY SCREEN
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = if (isTrQuestion) "Sınav Sonucu" else "Quiz Complete!",
                            color = SoftGold,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isTrQuestion) "Katılımınız için teşekkürler. Performans özetiniz aşağıdadır." else "Thank you for completing this session. Here is your summary.",
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = TextAlign.Center,
                            fontSize = 13.sp
                        )
                    }

                    // Score Segment
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val total = sessionStrictScores.size
                                val strictCorrectCount = sessionStrictScores.count { it }
                                val percentage = if (total > 0) (strictCorrectCount.toFloat() / total * 100).toInt() else 0

                                Text(
                                    text = if (isTrQuestion) "Strict (Tam Doğruluk) Başarı Oranı" else "Strict Accuracy Rate",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "$percentage%",
                                    color = if (percentage >= 70) ClinicalTeal else SoftGold,
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$strictCorrectCount / $total " + (if (isTrQuestion) "Soruda Tam Uyum" else "Questions Strictly Correct"),
                                    color = Color.White,
                                    fontSize = 13.sp
                                )

                                if (activeBoardType == "EDAIC_PART_I") {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                                    Spacer(modifier = Modifier.height(16.dp))

                                    val totalMtfStatements = total * 5
                                    val correctMtfStatements = sessionMtfStatementsScores.sum()
                                    val mtfPercent = if (totalMtfStatements > 0) (correctMtfStatements.toFloat() / totalMtfStatements * 100).toInt() else 0

                                    Text(
                                        text = if (isTrQuestion) "Genel Önerme Başarı Oranı" else "Statement Accuracy Rate",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "$mtfPercent%",
                                        color = ClinicalTeal,
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "$correctMtfStatements / $totalMtfStatements " + (if (isTrQuestion) "Önerme Doğru" else "Statements Correct"),
                                        color = Color.White.copy(alpha = 0.8f),
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }

                    // Weak Topics (Focus Areas)
                    item {
                        val incorrectQuestions = questions.filterIndexed { index, _ -> !sessionStrictScores.getOrElse(index) { true } }
                        val weakTags = incorrectQuestions.flatMap { q ->
                            when (q) {
                                is EdaicMtfQuestion -> q.tags
                                is SingleBestAnswerQuestion -> q.tags
                                is VivaScenarioQuestion -> q.tags
                                else -> emptyList()
                            }
                        }.groupBy { it }.mapValues { it.value.size }.entries.sortedByDescending { it.value }.map { it.key }

                        if (weakTags.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.05f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.TrendingUp,
                                            contentDescription = "Focus Areas",
                                            tint = SoftGold,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = if (isTrQuestion) "Zayıf Konular (Zorlanılan Alanlar)" else "Weak Topics (Focus Areas)",
                                            color = SoftGold,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = if (isTrQuestion) "Bu konulardaki sorularda hatalar yaptınız:" else "You had incorrect answers in these tags:",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    FlowRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        weakTags.take(4).forEach { tag ->
                                            Box(
                                                modifier = Modifier
                                                    .background(Color.Red.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                                            ) {
                                                Text(text = tag, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Bookmarked Questions Shortcut
                    item {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = ClinicalTeal.copy(alpha = 0.1f)),
                            border = BorderStroke(1.dp, ClinicalTeal.copy(alpha = 0.3f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        val count = BoardPrepRepository.getBookmarkedQuestions(context).size
                                        if (count > 0) {
                                            activeBoardType = "BOOKMARKS"
                                            currentIndex = 0
                                            isFinished = false
                                            sessionStrictScores.clear()
                                            sessionMtfStatementsScores.clear()
                                        }
                                    }
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Bookmark, contentDescription = "Bookmarks", tint = SoftGold, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = if (isTrQuestion) "Favori Soruları Gözden Geçir" else "Review Bookmarked Questions",
                                            color = Color.White,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = if (isTrQuestion) "Kaydettiğiniz tüm favorileri doğrudan çözün." else "Direct shortcut to solve all bookmarked items.",
                                            color = Color.White.copy(alpha = 0.6f),
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                                Icon(Icons.Default.ArrowForward, contentDescription = "Go", tint = Color.White.copy(alpha = 0.6f), modifier = Modifier.size(18.dp))
                            }
                        }
                    }

                    // Incorrectly Answered Questions List
                    item {
                        val incorrectQuestions = questions.filterIndexed { index, _ -> !sessionStrictScores.getOrElse(index) { true } }
                        if (incorrectQuestions.isNotEmpty()) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = if (isTrQuestion) "Hatalı Cevaplanan Sorular" else "Incorrectly Answered Questions",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                                incorrectQuestions.forEachIndexed { idx, q ->
                                    val stem = when (q) {
                                        is EdaicMtfQuestion -> q.stem.get(isTrQuestion)
                                        is SingleBestAnswerQuestion -> q.stem.get(isTrQuestion)
                                        is VivaScenarioQuestion -> q.scenario.get(isTrQuestion)
                                        else -> ""
                                    }
                                    val typeLabel = when (q) {
                                        is EdaicMtfQuestion -> "EDAIC MTF"
                                        is SingleBestAnswerQuestion -> "Single Best Answer"
                                        is VivaScenarioQuestion -> "Viva Scenario"
                                        else -> ""
                                    }
                                    var isExpanded by remember { mutableStateOf(false) }

                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { isExpanded = !isExpanded }
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = "$typeLabel • Question #${questions.indexOf(q) + 1}",
                                                    color = SoftGold,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Icon(
                                                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                                    contentDescription = "Expand",
                                                    tint = Color.White.copy(alpha = 0.5f),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = stem,
                                                color = Color.White,
                                                fontSize = 13.sp,
                                                maxLines = if (isExpanded) 100 else 2
                                            )
                                            
                                            if (isExpanded) {
                                                Spacer(modifier = Modifier.height(8.dp))
                                                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                                                Spacer(modifier = Modifier.height(8.dp))
                                                
                                                val correctInfo = when (q) {
                                                    is EdaicMtfQuestion -> {
                                                        val trueLabels = q.statements.filter { it.correctAnswer }.map { it.label }
                                                        val falseLabels = q.statements.filter { !it.correctAnswer }.map { it.label }
                                                        (if (isTrQuestion) "Doğru Önermeler: " else "True Statements: ") + trueLabels.joinToString(", ") + "\n" + 
                                                        (if (isTrQuestion) "Yanlış Önermeler: " else "False Statements: ") + falseLabels.joinToString(", ")
                                                    }
                                                    is SingleBestAnswerQuestion -> {
                                                        val optText = q.options.getOrNull(q.correctAnswerIndex)?.get(isTrQuestion) ?: ""
                                                        (if (isTrQuestion) "Doğru Seçenek: " else "Correct Option: ") + "${('A'.code + q.correctAnswerIndex).toChar()} - $optText"
                                                    }
                                                    is VivaScenarioQuestion -> {
                                                        (if (isTrQuestion) "Beklenen klinik basamaklar." else "Contains expected clinical milestones.")
                                                    }
                                                    else -> ""
                                                }
                                                
                                                Text(
                                                    text = correctInfo,
                                                    color = ClinicalTeal,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Retry Buttons
                    item {
                        Button(
                            onClick = {
                                currentIndex = 0
                                isFinished = false
                                sessionStrictScores.clear()
                                sessionMtfStatementsScores.clear()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = if (isTrQuestion) "Tekrar Çöz" else "Restart Quiz")
                        }
                    }

                    item {
                        OutlinedButton(
                            onClick = onBackClick,
                            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = if (isTrQuestion) "Ana Ekrana Dön" else "Return to Hub", color = Color.White)
                        }
                    }
                }
            } else {
                // ACTIVE QUIZ INTERFACE
                val question = questions[currentIndex]

                Column(modifier = Modifier.fillMaxSize()) {
                    // Progress Indicator
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = (if (isTrQuestion) "Soru " else "Question ") + "${currentIndex + 1} / ${questions.size}",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            // On-screen bilingual toggle
                            Row(
                                modifier = Modifier
                                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                    .clickable {
                                        activeQuestionLang = if (activeQuestionLang == "en") "tr" else "en"
                                    }
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = if (activeQuestionLang == "en") "TR'ye Çevir" else "Switch to EN",
                                    color = SoftGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { (currentIndex + 1).toFloat() / questions.size },
                            modifier = Modifier.fillMaxWidth(),
                            color = ClinicalTeal,
                            trackColor = Color.White.copy(alpha = 0.15f)
                        )
                    }

                    // Content layout
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        // Stem Display
                        item {
                            val stemText = when (question) {
                                is EdaicMtfQuestion -> question.stem.get(isTrQuestion)
                                is SingleBestAnswerQuestion -> question.stem.get(isTrQuestion)
                                is VivaScenarioQuestion -> question.scenario.get(isTrQuestion)
                                else -> ""
                            }

                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    val category = when (question) {
                                        is EdaicMtfQuestion -> "${question.category} • ${question.topic}"
                                        is SingleBestAnswerQuestion -> "${question.category} • ${question.topic}"
                                        is VivaScenarioQuestion -> "${question.category} • ${question.topic}"
                                        else -> ""
                                    }
                                    Text(
                                        text = category,
                                        color = SoftGold,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = stemText,
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        lineHeight = 22.sp
                                    )
                                }
                            }
                        }

                        // Answer Selection Blocks
                        when (question) {
                            is EdaicMtfQuestion -> {
                                item {
                                    Text(
                                        text = if (isTrQuestion) "Lütfen her şıkkı Doğru (T) veya Yanlış (F) olarak işaretleyin:" else "Mark each statement as True (T) or False (F):",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                items(question.statements.size) { index ->
                                    val stmt = question.statements[index]
                                    val activeAnswer = selectedMtfAnswers[index]

                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f)),
                                        border = if (isRevealed) {
                                            val isUserCorrect = activeAnswer == stmt.correctAnswer
                                            BorderStroke(1.dp, if (isUserCorrect) ClinicalTeal else Color.Red)
                                        } else BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = "${stmt.label}) ${stmt.text.get(isTrQuestion)}",
                                                    color = Color.White,
                                                    fontSize = 13.sp,
                                                    modifier = Modifier.weight(1f)
                                                )

                                                Spacer(modifier = Modifier.width(8.dp))

                                                // True/False toggles
                                                Row(
                                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                                ) {
                                                    // True Button
                                                    Box(
                                                        modifier = Modifier
                                                            .background(
                                                                color = if (activeAnswer == true) ClinicalTeal else Color.White.copy(alpha = 0.08f),
                                                                shape = RoundedCornerShape(4.dp)
                                                            )
                                                            .clickable(enabled = !isRevealed) {
                                                                val updated = selectedMtfAnswers.toMutableMap()
                                                                updated[index] = true
                                                                selectedMtfAnswers = updated
                                                            }
                                                            .padding(horizontal = 10.dp, vertical = 6.dp)
                                                    ) {
                                                        Text(text = "T", color = if (activeAnswer == true) Color.White else Color.White.copy(alpha = 0.7f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                                    }

                                                    // False Button
                                                    Box(
                                                        modifier = Modifier
                                                            .background(
                                                                color = if (activeAnswer == false) Color.Red.copy(alpha = 0.8f) else Color.White.copy(alpha = 0.08f),
                                                                shape = RoundedCornerShape(4.dp)
                                                            )
                                                            .clickable(enabled = !isRevealed) {
                                                                val updated = selectedMtfAnswers.toMutableMap()
                                                                updated[index] = false
                                                                selectedMtfAnswers = updated
                                                            }
                                                            .padding(horizontal = 10.dp, vertical = 6.dp)
                                                    ) {
                                                        Text(text = "F", color = if (activeAnswer == false) Color.White else Color.White.copy(alpha = 0.7f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            is SingleBestAnswerQuestion -> {
                                items(question.options.size) { index ->
                                    val opt = question.options[index]
                                    val isSelected = selectedSbaAnswerIndex == index

                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (isSelected) ClinicalTeal.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.04f)
                                        ),
                                        border = if (isRevealed) {
                                            val isCorrect = index == question.correctAnswerIndex
                                            BorderStroke(
                                                width = 1.dp,
                                                color = if (isCorrect) ClinicalTeal else if (isSelected) Color.Red else Color.Transparent
                                            )
                                        } else {
                                            BorderStroke(1.dp, if (isSelected) ClinicalTeal else Color.White.copy(alpha = 0.05f))
                                        },
                                        modifier = Modifier.clickable(enabled = !isRevealed) {
                                            selectedSbaAnswerIndex = index
                                        }
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                selected = isSelected,
                                                onClick = { if (!isRevealed) selectedSbaAnswerIndex = index },
                                                colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal, unselectedColor = Color.White.copy(alpha = 0.6f)),
                                                enabled = !isRevealed
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "${('A'.code + index).toChar()}) ${opt.get(isTrQuestion)}",
                                                color = Color.White,
                                                fontSize = 13.sp
                                            )
                                        }
                                    }
                                }
                            }

                            is VivaScenarioQuestion -> {
                                // 1. Examiner Questions (always visible)
                                item {
                                    Text(
                                        text = if (isTrQuestion) "Sözlü Sınav Soruları:" else "Examiner Questions:",
                                        color = SoftGold,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                                items(question.examinerQuestions.size) { index ->
                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f)),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Default.QuestionAnswer, contentDescription = "Question", tint = ClinicalTeal, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(text = question.examinerQuestions[index].get(isTrQuestion), color = Color.White, fontSize = 13.sp)
                                        }
                                    }
                                }

                                if (quizMode == "EXAM" && !vivaExamMarkedCompleted) {
                                    item {
                                        Button(
                                            onClick = { vivaExamMarkedCompleted = true },
                                            colors = ButtonDefaults.buttonColors(containerColor = SoftGold),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                                        ) {
                                            Text(
                                                text = if (isTrQuestion) "Senaryoyu Tamamlandı Olarak İşaretle" else "Mark Scenario as Completed",
                                                color = DeepNavy,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                } else {
                                    // 2. Reveal Expected Answer Points (collapsible checklist)
                                    item {
                                        ExpandableCard(
                                            title = if (isTrQuestion) "Beklenen Cevap Maddeleri" else "Reveal Expected Answer Points",
                                            icon = Icons.Default.CheckCircle,
                                            iconColor = ClinicalTeal,
                                            isExpanded = showVivaExpectedPoints,
                                            onToggle = { showVivaExpectedPoints = !showVivaExpectedPoints }
                                        ) {
                                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                                Text(
                                                    text = if (isTrQuestion) "Kendi cevabınızı aşağıdaki maddelere göre değerlendirin:" else "Self-assess your performance by checking off items you covered:",
                                                    color = Color.White.copy(alpha = 0.7f),
                                                    fontSize = 11.sp,
                                                    modifier = Modifier.padding(bottom = 4.dp)
                                                )
                                                question.expectedAnswerPoints.forEachIndexed { index, point ->
                                                    val isChecked = checkedVivaPoints.contains(index)
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .background(Color.White.copy(alpha = 0.03f), RoundedCornerShape(6.dp))
                                                            .clickable {
                                                                val updated = checkedVivaPoints.toMutableSet()
                                                                if (isChecked) updated.remove(index) else updated.add(index)
                                                                checkedVivaPoints = updated
                                                            }
                                                            .padding(horizontal = 8.dp, vertical = 6.dp)
                                                    ) {
                                                        Checkbox(
                                                            checked = isChecked,
                                                            onCheckedChange = {
                                                                val updated = checkedVivaPoints.toMutableSet()
                                                                if (isChecked) updated.remove(index) else updated.add(index)
                                                                checkedVivaPoints = updated
                                                            },
                                                            colors = CheckboxDefaults.colors(checkedColor = ClinicalTeal, uncheckedColor = Color.White.copy(alpha = 0.5f))
                                                        )
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Text(text = point.get(isTrQuestion), color = Color.White, fontSize = 12.sp)
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // 3. Reveal Red Flags (collapsible danger warning box)
                                    if (question.redFlags.isNotEmpty()) {
                                        item {
                                            ExpandableCard(
                                                title = if (isTrQuestion) "Kritik Hatalar (Kırmızı Bayraklar)" else "Reveal Clinical Red Flags",
                                                icon = Icons.Default.Warning,
                                                iconColor = Color.Red,
                                                isExpanded = showVivaRedFlags,
                                                onToggle = { showVivaRedFlags = !showVivaRedFlags }
                                            ) {
                                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                                    question.redFlags.forEach { flag ->
                                                        Row(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .background(Color.Red.copy(alpha = 0.08f), RoundedCornerShape(6.dp))
                                                                .border(1.dp, Color.Red.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                                                                .padding(12.dp),
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Icon(Icons.Default.Dangerous, contentDescription = "Red Flag", tint = Color.Red, modifier = Modifier.size(16.dp))
                                                            Spacer(modifier = Modifier.width(8.dp))
                                                            Text(text = flag.get(isTrQuestion), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // 4. Reveal Follow-up Questions (collapsible list)
                                    if (question.followUpQuestions.isNotEmpty()) {
                                        item {
                                            ExpandableCard(
                                                title = if (isTrQuestion) "Takip Soruları" else "Reveal Follow-up Questions",
                                                icon = Icons.Default.QuestionAnswer,
                                                iconColor = SoftGold,
                                                isExpanded = showVivaFollowUp,
                                                onToggle = { showVivaFollowUp = !showVivaFollowUp }
                                            ) {
                                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                                    question.followUpQuestions.forEach { followQ ->
                                                        Row(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .background(Color.White.copy(alpha = 0.03f), RoundedCornerShape(6.dp))
                                                                .padding(10.dp),
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Icon(Icons.Default.ArrowForwardIos, contentDescription = "Arrow", tint = SoftGold, modifier = Modifier.size(10.dp))
                                                            Spacer(modifier = Modifier.width(8.dp))
                                                            Text(text = followQ.get(isTrQuestion), color = Color.White, fontSize = 12.sp)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // 5. Key Learning Point & Common Trap
                                    item {
                                        ExpandableCard(
                                            title = if (isTrQuestion) "Anahtar Öğrenim ve Tuzaklar" else "Reveal Clinical Pearls & Traps",
                                            icon = Icons.Default.Psychology,
                                            iconColor = ClinicalTeal,
                                            isExpanded = showVivaLearningPoints,
                                            onToggle = { showVivaLearningPoints = !showVivaLearningPoints }
                                        ) {
                                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                                // Key Learning Point
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(ClinicalTeal.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
                                                        .border(1.dp, ClinicalTeal.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                                        .padding(12.dp)
                                                ) {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Icon(Icons.Default.Lightbulb, contentDescription = "Learning", tint = ClinicalTeal, modifier = Modifier.size(16.dp))
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Text(
                                                            text = if (isTrQuestion) "Anahtar Öğrenme Noktası" else "Key Learning Point",
                                                            color = ClinicalTeal,
                                                            fontSize = 12.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.height(6.dp))
                                                    Text(text = question.keyLearningPoint.get(isTrQuestion), color = Color.White, fontSize = 12.sp)
                                                }
                                                
                                                // Common Trap
                                                question.commonTrap?.let { trap ->
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .background(SoftGold.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
                                                            .border(1.dp, SoftGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                                            .padding(12.dp)
                                                    ) {
                                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                                            Icon(Icons.Default.Dangerous, contentDescription = "Trap", tint = SoftGold, modifier = Modifier.size(16.dp))
                                                            Spacer(modifier = Modifier.width(6.dp))
                                                            Text(
                                                                text = if (isTrQuestion) "Sınav Tuzağı" else "Common Trap / Pearl",
                                                                color = SoftGold,
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(6.dp))
                                                        Text(text = trap.get(isTrQuestion), color = Color.White, fontSize = 12.sp)
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // 6. Self-Rating Bar (Poor, Borderline, Good, Excellent)
                                    item {
                                        Card(
                                            shape = RoundedCornerShape(16.dp),
                                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f)),
                                            border = BorderStroke(1.dp, SoftGold.copy(alpha = 0.3f)),
                                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(16.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = if (isTrQuestion) "Klinik Performansınızı Değerlendirin" else "Rate Your Clinical Performance",
                                                    color = SoftGold,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Spacer(modifier = Modifier.height(12.dp))
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                                ) {
                                                    // Poor Button
                                                    Button(
                                                        onClick = {
                                                            val rating = "POOR"
                                                            val qId = question.id
                                                            val strictCorrect = false
                                                            scope.launch {
                                                                val answersJson = Json.encodeToString(checkedVivaPoints.toList())
                                                                BoardPrepRepository.saveUserProgressWithRating(
                                                                    context = context,
                                                                    questionId = qId,
                                                                    selectedAnswersJson = answersJson,
                                                                    isCorrectStrict = strictCorrect,
                                                                    statementsCorrectCount = checkedVivaPoints.size,
                                                                    tags = question.tags,
                                                                    vivaSelfRating = rating
                                                                )
                                                                sessionStrictScores.add(strictCorrect)
                                                                sessionMtfStatementsScores.add(checkedVivaPoints.size)
                                                                if (currentIndex + 1 < questions.size) {
                                                                    currentIndex++
                                                                } else {
                                                                    isFinished = true
                                                                }
                                                            }
                                                        },
                                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                                        modifier = Modifier.weight(1f),
                                                        contentPadding = PaddingValues(vertical = 8.dp),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Text(text = if (isTrQuestion) "Zayıf" else "Poor", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                    
                                                    // Borderline Button
                                                    Button(
                                                        onClick = {
                                                            val rating = "BORDERLINE"
                                                            val qId = question.id
                                                            val strictCorrect = false
                                                            scope.launch {
                                                                val answersJson = Json.encodeToString(checkedVivaPoints.toList())
                                                                BoardPrepRepository.saveUserProgressWithRating(
                                                                    context = context,
                                                                    questionId = qId,
                                                                    selectedAnswersJson = answersJson,
                                                                    isCorrectStrict = strictCorrect,
                                                                    statementsCorrectCount = checkedVivaPoints.size,
                                                                    tags = question.tags,
                                                                    vivaSelfRating = rating
                                                                )
                                                                sessionStrictScores.add(strictCorrect)
                                                                sessionMtfStatementsScores.add(checkedVivaPoints.size)
                                                                if (currentIndex + 1 < questions.size) {
                                                                    currentIndex++
                                                                } else {
                                                                    isFinished = true
                                                                }
                                                            }
                                                        },
                                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)),
                                                        modifier = Modifier.weight(1f),
                                                        contentPadding = PaddingValues(vertical = 8.dp),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Text(text = if (isTrQuestion) "Sınırda" else "Borderline", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                    
                                                    // Good Button
                                                    Button(
                                                        onClick = {
                                                            val rating = "GOOD"
                                                            val qId = question.id
                                                            val strictCorrect = true
                                                            scope.launch {
                                                                val answersJson = Json.encodeToString(checkedVivaPoints.toList())
                                                                BoardPrepRepository.saveUserProgressWithRating(
                                                                    context = context,
                                                                    questionId = qId,
                                                                    selectedAnswersJson = answersJson,
                                                                    isCorrectStrict = strictCorrect,
                                                                    statementsCorrectCount = checkedVivaPoints.size,
                                                                    tags = question.tags,
                                                                    vivaSelfRating = rating
                                                                )
                                                                sessionStrictScores.add(strictCorrect)
                                                                sessionMtfStatementsScores.add(checkedVivaPoints.size)
                                                                if (currentIndex + 1 < questions.size) {
                                                                    currentIndex++
                                                                } else {
                                                                    isFinished = true
                                                                }
                                                            }
                                                        },
                                                        colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                                        modifier = Modifier.weight(1f),
                                                        contentPadding = PaddingValues(vertical = 8.dp),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Text(text = if (isTrQuestion) "İyi" else "Good", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                    
                                                    // Excellent Button
                                                    Button(
                                                        onClick = {
                                                            val rating = "EXCELLENT"
                                                            val qId = question.id
                                                            val strictCorrect = true
                                                            scope.launch {
                                                                val answersJson = Json.encodeToString(checkedVivaPoints.toList())
                                                                BoardPrepRepository.saveUserProgressWithRating(
                                                                    context = context,
                                                                    questionId = qId,
                                                                    selectedAnswersJson = answersJson,
                                                                    isCorrectStrict = strictCorrect,
                                                                    statementsCorrectCount = checkedVivaPoints.size,
                                                                    tags = question.tags,
                                                                    vivaSelfRating = rating
                                                                )
                                                                sessionStrictScores.add(strictCorrect)
                                                                sessionMtfStatementsScores.add(checkedVivaPoints.size)
                                                                if (currentIndex + 1 < questions.size) {
                                                                    currentIndex++
                                                                } else {
                                                                    isFinished = true
                                                                }
                                                            }
                                                        },
                                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBC02D)),
                                                        modifier = Modifier.weight(1f),
                                                        contentPadding = PaddingValues(vertical = 8.dp),
                                                        shape = RoundedCornerShape(8.dp)
                                                    ) {
                                                        Text(text = if (isTrQuestion) "Harika" else "Excellent", color = DeepNavy, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // REVEAL CONTENT: ANSWER KEY, EXPLANATIONS ORDER (Refinement 6)
                        if (isRevealed) {
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider(color = Color.White.copy(alpha = 0.15f))
                                Spacer(modifier = Modifier.height(16.dp))
                            }

                            // 1. Answer Key & 2. Your Answer & 3. Score
                            item {
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f))
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = if (isTrQuestion) "Cevap Değerlendirmesi" else "Answer Verification",
                                            color = SoftGold,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(bottom = 12.dp)
                                        )

                                        when (question) {
                                            is EdaicMtfQuestion -> {
                                                val statements = question.statements
                                                var strictCorrect = true
                                                var countCorrect = 0
                                                
                                                statements.forEachIndexed { idx, stmt ->
                                                    val userVal = selectedMtfAnswers[idx]
                                                    val correctVal = stmt.correctAnswer
                                                    val stmtCorrect = userVal == correctVal
                                                    if (!stmtCorrect) strictCorrect = false
                                                    if (stmtCorrect) countCorrect++

                                                    Row(
                                                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {
                                                        Text(text = "Statement ${stmt.label}:", color = Color.White, fontSize = 13.sp)
                                                        Row {
                                                            Text(text = if (isTrQuestion) "Doğru: " else "Key: ", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                                                            Text(text = if (correctVal) "True" else "False", color = if (correctVal) ClinicalTeal else Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                                            Spacer(modifier = Modifier.width(12.dp))
                                                            Text(text = if (isTrQuestion) "Cevabınız: " else "Yours: ", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                                                            Text(
                                                                text = if (userVal == null) "N/A" else if (userVal) "True" else "False",
                                                                color = if (stmtCorrect) ClinicalTeal else Color.Red,
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        }
                                                    }
                                                }

                                                Spacer(modifier = Modifier.height(12.dp))
                                                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                                                Spacer(modifier = Modifier.height(12.dp))

                                                // Display Strict & Statement levels (Refinement 5)
                                                Text(
                                                    text = "Strict Score: " + (if (strictCorrect) "CORRECT (All 5)" else "INCORRECT"),
                                                    color = if (strictCorrect) ClinicalTeal else Color.Red,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = "Per-Statement Score: $countCorrect / 5 Correct",
                                                    color = SoftGold,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }

                                            is SingleBestAnswerQuestion -> {
                                                val isCorrect = selectedSbaAnswerIndex == question.correctAnswerIndex
                                                val correctChar = ('A'.code + question.correctAnswerIndex).toChar()
                                                val userChar = selectedSbaAnswerIndex?.let { ('A'.code + it).toChar() } ?: "N/A"

                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(text = (if (isTrQuestion) "Doğru Anahtar: " else "Correct Answer: ") + correctChar, color = ClinicalTeal, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                                    Text(text = (if (isTrQuestion) "Sizin Cevabınız: " else "Your Answer: ") + userChar, color = if (isCorrect) ClinicalTeal else Color.Red, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                                }
                                                Spacer(modifier = Modifier.height(8.dp))
                                                Text(
                                                    text = "Score: " + (if (isCorrect) "CORRECT" else "INCORRECT"),
                                                    color = if (isCorrect) ClinicalTeal else Color.Red,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }

                                            is VivaScenarioQuestion -> {
                                                val total = question.expectedAnswerPoints.size
                                                val score = checkedVivaPoints.size
                                                val percent = if (total > 0) (score.toFloat() / total * 100).toInt() else 0
                                                
                                                Text(
                                                    text = if (isTrQuestion) "Sözlü Değerlendirme Puanı" else "Viva Evaluation Score",
                                                    color = Color.White,
                                                    fontSize = 14.sp
                                                )
                                                Text(
                                                    text = "$score / $total " + (if (isTrQuestion) "Kriter Karşılandı" else "Expected Points Covered") + " ($percent%)",
                                                    color = SoftGold,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            // 4. Explanation for each statement/option (Refinement 6)
                            item {
                                val isTrExplanation = activeExplanationLang == "tr" || activeExplanationLang == "both"
                                val isEnExplanation = activeExplanationLang == "en" || activeExplanationLang == "both"

                                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Text(
                                        text = if (isTrQuestion) "Şık / Önerme Açıklamaları" else "Itemized Explanations",
                                        color = SoftGold,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )

                                    when (question) {
                                        is EdaicMtfQuestion -> {
                                            question.statements.forEach { stmt ->
                                                Card(
                                                    shape = RoundedCornerShape(8.dp),
                                                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f))
                                                ) {
                                                    Column(modifier = Modifier.padding(12.dp)) {
                                                        Text(text = "Statement ${stmt.label}:", color = ClinicalTeal, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                                        Spacer(modifier = Modifier.height(4.dp))
                                                        if (isEnExplanation) {
                                                            Text(text = stmt.explanation.en, color = Color.White, fontSize = 12.sp)
                                                        }
                                                        if (isEnExplanation && isTrExplanation && activeExplanationLang == "both") {
                                                            Spacer(modifier = Modifier.height(4.dp))
                                                        }
                                                        if (isTrExplanation) {
                                                            Text(text = stmt.explanation.tr, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        is SingleBestAnswerQuestion -> {
                                            question.options.forEachIndexed { idx, opt ->
                                                val char = ('A'.code + idx).toChar()
                                                val isCorrect = idx == question.correctAnswerIndex
                                                
                                                Card(
                                                    shape = RoundedCornerShape(8.dp),
                                                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.04f))
                                                ) {
                                                    Column(modifier = Modifier.padding(12.dp)) {
                                                        Text(
                                                            text = "Option $char " + (if (isCorrect) "(Correct)" else ""),
                                                            color = if (isCorrect) ClinicalTeal else Color.White.copy(alpha = 0.5f),
                                                            fontSize = 12.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Spacer(modifier = Modifier.height(4.dp))
                                                        if (isEnExplanation) {
                                                            Text(text = question.optionExplanations[idx].en, color = Color.White, fontSize = 12.sp)
                                                        }
                                                        if (isEnExplanation && isTrExplanation && activeExplanationLang == "both") {
                                                            Spacer(modifier = Modifier.height(4.dp))
                                                        }
                                                        if (isTrExplanation) {
                                                            Text(text = question.optionExplanations[idx].tr, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        
                                        is VivaScenarioQuestion -> {
                                            // No itemized explanations for VIVAs, only overall
                                        }
                                    }
                                }
                            }

                            // 5. Overall Explanation
                            val overallEx = when (question) {
                                is EdaicMtfQuestion -> question.overallExplanation
                                is SingleBestAnswerQuestion -> question.overallExplanation
                                else -> null
                            }

                            overallEx?.let { ex ->
                                item {
                                    val isTrExplanation = activeExplanationLang == "tr" || activeExplanationLang == "both"
                                    val isEnExplanation = activeExplanationLang == "en" || activeExplanationLang == "both"

                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f))
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text(
                                                text = if (isTrQuestion) "Genel Açıklama" else "Overall Explanation",
                                                color = SoftGold,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(bottom = 8.dp)
                                            )
                                            if (isEnExplanation) {
                                                Text(text = ex.en, color = Color.White, fontSize = 13.sp, lineHeight = 18.sp)
                                            }
                                            if (isEnExplanation && isTrExplanation && activeExplanationLang == "both") {
                                                Spacer(modifier = Modifier.height(8.dp))
                                            }
                                            if (isTrExplanation) {
                                                Text(text = ex.tr, color = Color.White.copy(alpha = 0.85f), fontSize = 13.sp, lineHeight = 18.sp)
                                            }
                                        }
                                    }
                                }
                            }

                            // 6. Key Learning Point
                            val keyLearning = when (question) {
                                is EdaicMtfQuestion -> question.keyLearningPoint
                                is SingleBestAnswerQuestion -> question.keyLearningPoint
                                is VivaScenarioQuestion -> question.keyLearningPoint
                                else -> null
                            }

                            keyLearning?.let { klp ->
                                item {
                                    val isTrExplanation = activeExplanationLang == "tr" || activeExplanationLang == "both"
                                    val isEnExplanation = activeExplanationLang == "en" || activeExplanationLang == "both"

                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = ClinicalTeal.copy(alpha = 0.08f)),
                                        border = BorderStroke(1.dp, ClinicalTeal.copy(alpha = 0.4f))
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(Icons.Default.Lightbulb, contentDescription = "Learning", tint = ClinicalTeal, modifier = Modifier.size(18.dp))
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    text = if (isTrQuestion) "Anahtar Öğrenme Noktası" else "Key Learning Point",
                                                    color = ClinicalTeal,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                            if (isEnExplanation) {
                                                Text(text = klp.en, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                            }
                                            if (isEnExplanation && isTrExplanation && activeExplanationLang == "both") {
                                                Spacer(modifier = Modifier.height(8.dp))
                                            }
                                            if (isTrExplanation) {
                                                Text(text = klp.tr, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                            }
                                        }
                                    }
                                }
                            }

                            // 7. Common Trap / Exam Pearl
                            val commonTrap = when (question) {
                                is EdaicMtfQuestion -> question.commonTrap
                                is SingleBestAnswerQuestion -> question.commonTrap
                                is VivaScenarioQuestion -> question.commonTrap
                                else -> null
                            }

                            commonTrap?.let { trap ->
                                item {
                                    val isTrExplanation = activeExplanationLang == "tr" || activeExplanationLang == "both"
                                    val isEnExplanation = activeExplanationLang == "en" || activeExplanationLang == "both"

                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = SoftGold.copy(alpha = 0.08f)),
                                        border = BorderStroke(1.dp, SoftGold.copy(alpha = 0.4f))
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(Icons.Default.Psychology, contentDescription = "Trap", tint = SoftGold, modifier = Modifier.size(18.dp))
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    text = if (isTrQuestion) "Sınav Tuzağı / Püf Noktası" else "Common Trap / Exam Pearl",
                                                    color = SoftGold,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                            if (isEnExplanation) {
                                                Text(text = trap.en, color = Color.White, fontSize = 12.sp)
                                            }
                                            if (isEnExplanation && isTrExplanation && activeExplanationLang == "both") {
                                                Spacer(modifier = Modifier.height(8.dp))
                                            }
                                            if (isTrExplanation) {
                                                Text(text = trap.tr, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                                            }
                                        }
                                    }
                                }
                            }

                            // Source type & Copyright Safety disclaimer (Refinement 4)
                            item {
                                Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    val srcType = when (question) {
                                        is EdaicMtfQuestion -> question.sourceType
                                        is SingleBestAnswerQuestion -> question.sourceType
                                        is VivaScenarioQuestion -> question.sourceType
                                        else -> "ORIGINAL_BOARD_STYLE"
                                    }
                                    Text(
                                        text = "Source: $srcType • Copyright Safe",
                                        color = Color.White.copy(alpha = 0.4f),
                                        fontSize = 10.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    if (question !is VivaScenarioQuestion) {
                        // BOTTOM BUTTONS PANEL: SUBMIT / SHOW ANSWER / NEXT
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DeepNavy)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (quizMode == "EXAM") {
                                val isLastQuestion = currentIndex + 1 >= questions.size
                                Button(
                                    onClick = {
                                        val q = questions[currentIndex]
                                        var strictCorrect = false
                                        var statementsCount = 0
                                        val qId = when (q) {
                                            is EdaicMtfQuestion -> {
                                                var strict = true
                                                q.statements.forEachIndexed { idx, stmt ->
                                                    val userVal = selectedMtfAnswers[idx]
                                                    val stmtCorrect = userVal == stmt.correctAnswer
                                                    if (stmtCorrect) statementsCount++
                                                    else strict = false
                                                }
                                                strictCorrect = strict
                                                q.id
                                            }

                                            is SingleBestAnswerQuestion -> {
                                                strictCorrect = selectedSbaAnswerIndex == q.correctAnswerIndex
                                                statementsCount = if (strictCorrect) 1 else 0
                                                q.id
                                            }
                                            else -> ""
                                        }

                                        sessionStrictScores.add(strictCorrect)
                                        sessionMtfStatementsScores.add(statementsCount)

                                        // Save progress transactionally
                                        scope.launch {
                                            val answersJson = when (q) {
                                                is EdaicMtfQuestion -> {
                                                    val list = List(q.statements.size) { idx -> selectedMtfAnswers[idx] ?: false }
                                                    Json.encodeToString(list)
                                                }
                                                is SingleBestAnswerQuestion -> {
                                                    selectedSbaAnswerIndex?.toString() ?: "null"
                                                }
                                                else -> "[]"
                                            }

                                            val tags = when (q) {
                                                is EdaicMtfQuestion -> q.tags
                                                is SingleBestAnswerQuestion -> q.tags
                                                else -> emptyList()
                                            }

                                            BoardPrepRepository.saveUserProgress(
                                                context = context,
                                                questionId = qId,
                                                selectedAnswersJson = answersJson,
                                                isCorrectStrict = strictCorrect,
                                                statementsCorrectCount = statementsCount,
                                                tags = tags
                                            )
                                        }

                                        // Transition
                                        if (!isLastQuestion) {
                                            currentIndex++
                                        } else {
                                            isFinished = true
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = if (isLastQuestion) SoftGold else ClinicalTeal),
                                    modifier = Modifier.weight(1.5f),
                                    shape = RoundedCornerShape(8.dp),
                                    enabled = when (question) {
                                        is EdaicMtfQuestion -> true // Allow empty
                                        is SingleBestAnswerQuestion -> selectedSbaAnswerIndex != null
                                        else -> false
                                    }
                                ) {
                                    Text(
                                        text = if (isLastQuestion) {
                                            if (isTrQuestion) "Sınavı Bitir" else "Finish Exam"
                                        } else {
                                            if (isTrQuestion) "Sonraki Soru" else "Next Question"
                                        },
                                        color = if (isLastQuestion) DeepNavy else Color.White
                                    )
                                }
                            } else {
                                // STUDY Mode flow:
                                if (!isRevealed) {
                                    // Show Answer only in Study Mode (Refinement 1)
                                    OutlinedButton(
                                        onClick = { isRevealed = true },
                                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4f)),
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = if (isTrQuestion) "Cevabı Göster" else "Show Answer", color = Color.White)
                                    }

                                    Button(
                                        onClick = {
                                            isRevealed = true
                                            
                                            // Process scoring based on question type
                                            val q = questions[currentIndex]
                                            var strictCorrect = false
                                            var statementsCount = 0
                                            val qId = when (q) {
                                                is EdaicMtfQuestion -> {
                                                    var strict = true
                                                    q.statements.forEachIndexed { idx, stmt ->
                                                        val userVal = selectedMtfAnswers[idx]
                                                        val stmtCorrect = userVal == stmt.correctAnswer
                                                        if (stmtCorrect) statementsCount++
                                                        else strict = false
                                                    }
                                                    strictCorrect = strict
                                                    q.id
                                                }

                                                is SingleBestAnswerQuestion -> {
                                                    strictCorrect = selectedSbaAnswerIndex == q.correctAnswerIndex
                                                    statementsCount = if (strictCorrect) 1 else 0
                                                    q.id
                                                }

                                                is VivaScenarioQuestion -> {
                                                    // Self-assessment check count
                                                    strictCorrect = checkedVivaPoints.size >= q.expectedAnswerPoints.size * 0.7 // passing rate
                                                    statementsCount = checkedVivaPoints.size
                                                    q.id
                                                }
                                                else -> ""
                                            }

                                            sessionStrictScores.add(strictCorrect)
                                            sessionMtfStatementsScores.add(statementsCount)

                                            // Save progress transactionally (Refinement 5 & 9)
                                            scope.launch {
                                                val answersJson = when (q) {
                                                    is EdaicMtfQuestion -> {
                                                        val list = List(q.statements.size) { idx -> selectedMtfAnswers[idx] ?: false }
                                                        Json.encodeToString(list)
                                                    }
                                                    is SingleBestAnswerQuestion -> {
                                                        selectedSbaAnswerIndex?.toString() ?: "null"
                                                    }
                                                    is VivaScenarioQuestion -> {
                                                        Json.encodeToString(checkedVivaPoints.toList())
                                                    }
                                                    else -> "[]"
                                                }

                                                val tags = when (q) {
                                                    is EdaicMtfQuestion -> q.tags
                                                    is SingleBestAnswerQuestion -> q.tags
                                                    is VivaScenarioQuestion -> q.tags
                                                    else -> emptyList()
                                                }

                                                BoardPrepRepository.saveUserProgress(
                                                    context = context,
                                                    questionId = qId,
                                                    selectedAnswersJson = answersJson,
                                                    isCorrectStrict = strictCorrect,
                                                    statementsCorrectCount = statementsCount,
                                                    tags = tags
                                                )
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                        modifier = Modifier.weight(1.5f),
                                        shape = RoundedCornerShape(8.dp),
                                        enabled = when (question) {
                                            is EdaicMtfQuestion -> true // Allow submitting empty to show penalty
                                            is SingleBestAnswerQuestion -> selectedSbaAnswerIndex != null
                                            is VivaScenarioQuestion -> true
                                            else -> false
                                        }
                                    ) {
                                        Text(text = if (isTrQuestion) "Gönder" else "Submit")
                                    }
                                } else {
                                    // Revealed -> Next Question
                                    Button(
                                        onClick = {
                                            if (currentIndex + 1 < questions.size) {
                                                currentIndex++
                                            } else {
                                                isFinished = true
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = SoftGold),
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = if (currentIndex + 1 < questions.size) {
                                                if (isTrQuestion) "Sonraki Soru" else "Next Question"
                                            } else {
                                                if (isTrQuestion) "Sonuçları Gör" else "View Results"
                                            },
                                            color = DeepNavy,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
private fun ExpandableCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f)),
        border = BorderStroke(1.dp, if (isExpanded) iconColor.copy(alpha = 0.4f) else Color.White.copy(alpha = 0.05f)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f)
                )
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(12.dp))
                content()
            }
        }
    }
}



