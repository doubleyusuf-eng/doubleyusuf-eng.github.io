package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.window.Dialog
import com.anesthesiaclinic.anesthesiabriefs.data.model.BoardSpotNote
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.ClinicalTeal
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.DeepNavy
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.SoftGold

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
fun BoardPrepSpotNotesScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", Context.MODE_PRIVATE) }
    val isTurkish = remember { prefs.getString("settings_language", "en") == "tr" }

    var allNotes by remember { mutableStateOf<List<BoardSpotNote>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Filters
    var selectedExam by remember { mutableStateOf("ALL") }
    var selectedTopic by remember { mutableStateOf("ALL") }
    var searchQuery by remember { mutableStateOf("") }
    var showOnlyHighYield by remember { mutableStateOf(false) }

    // Random Mode & Dialog
    var random10Mode by remember { mutableStateOf(false) }
    var showRandomDialog by remember { mutableStateOf(false) }
    var randomSingleNote by remember { mutableStateOf<BoardSpotNote?>(null) }

    // Language Preference per Card
    var activeLang by remember { mutableStateOf(prefs.getString("settings_language", "en") ?: "en") }

    // Load data
    LaunchedEffect(Unit) {
        allNotes = BoardPrepRepository.getBoardSpotNotes(context)
        isLoading = false
    }

    // Compute distinct topics based on active exam
    val topicsList = remember(allNotes, selectedExam) {
        val filteredByExam = if (selectedExam == "ALL") allNotes else allNotes.filter { it.exam == selectedExam }
        listOf("ALL") + filteredByExam.map { it.topic }.distinct().sorted()
    }

    // Reset topic filter if it is no longer valid for the selected exam
    LaunchedEffect(selectedExam) {
        if (selectedTopic != "ALL" && selectedTopic !in topicsList) {
            selectedTopic = "ALL"
        }
    }

    // Filtered Notes List
    val filteredNotes = remember(allNotes, selectedExam, selectedTopic, searchQuery, showOnlyHighYield, random10Mode) {
        var list = allNotes
        if (selectedExam != "ALL") {
            list = list.filter { it.exam == selectedExam }
        }
        if (selectedTopic != "ALL") {
            list = list.filter { it.topic == selectedTopic }
        }
        if (searchQuery.isNotEmpty()) {
            val q = searchQuery.lowercase()
            list = list.filter {
                it.tr.lowercase().contains(q) ||
                it.en.lowercase().contains(q) ||
                it.topic.lowercase().contains(q) ||
                it.subtopic.lowercase().contains(q) ||
                it.tags.any { tag -> tag.lowercase().contains(q) }
            }
        }
        if (showOnlyHighYield) {
            list = list.filter { it.importance.lowercase() == "high" }
        }
        if (random10Mode) {
            list = list.shuffled().take(10)
        }
        list
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isTurkish) "Spot Bilgiler" else "Spot Notes Hub",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    // Language Switcher
                    IconButton(onClick = { activeLang = if (activeLang == "en") "tr" else "en" }) {
                        Icon(Icons.Default.Language, contentDescription = "Language", tint = SoftGold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { innerPadding ->
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
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    
                    // Search and Daily Review Action Header
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text(if (isTurkish) "Spot bilgilerde ara..." else "Search spot notes...", color = Color.White.copy(alpha = 0.4f)) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White.copy(alpha = 0.6f)) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = ClinicalTeal,
                                unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                                focusedContainerColor = Color.White.copy(alpha = 0.05f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.05f)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )

                        // Random Spot Selector
                        IconButton(
                            onClick = {
                                if (allNotes.isNotEmpty()) {
                                    randomSingleNote = allNotes.random()
                                    showRandomDialog = true
                                }
                            },
                            modifier = Modifier
                                .background(SoftGold.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                .border(1.dp, SoftGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        ) {
                            Icon(Icons.Default.Casino, contentDescription = "Random Spot", tint = SoftGold)
                        }
                    }

                    // Sınav (Exam) horizontal scroll filter
                    Text(
                        text = if (isTurkish) "Sınav Türü Seçin" else "Select Exam Type",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val exams = listOf(
                            "ALL" to if (isTurkish) "Tümü" else "All",
                            "EDAIC_PAPER_A" to "EDAIC Paper A",
                            "EDAIC_PAPER_B" to "EDAIC Paper B",
                            "ABA_BASIC" to "ABA BASIC",
                            "ABA_ADVANCED" to "ABA ADVANCED",
                            "EDAIC_VIVA" to "EDAIC Viva"
                        )
                        Box(modifier = Modifier.weight(1f)) {
                            var expandedExamMenu by remember { mutableStateOf(false) }
                            Button(
                                onClick = { expandedExamMenu = true },
                                colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal.copy(alpha = 0.2f)),
                                border = BorderStroke(1.dp, ClinicalTeal.copy(alpha = 0.4f)),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = exams.find { it.first == selectedExam }?.second ?: "All",
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
                                }
                            }
                            DropdownMenu(
                                expanded = expandedExamMenu,
                                onDismissRequest = { expandedExamMenu = false },
                                modifier = Modifier
                                    .background(DeepNavy)
                                    .border(1.dp, Color.White.copy(alpha = 0.1f))
                            ) {
                                exams.forEach { exam ->
                                    DropdownMenuItem(
                                        text = { Text(text = exam.second, color = Color.White) },
                                        onClick = {
                                            selectedExam = exam.first
                                            expandedExamMenu = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Topic & Quick Filter Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Topic Dropdown
                        Box(modifier = Modifier.weight(1f)) {
                            var expandedTopicMenu by remember { mutableStateOf(false) }
                            Button(
                                onClick = { expandedTopicMenu = true },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.05f)),
                                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (selectedTopic == "ALL") (if (isTurkish) "Tüm Konular" else "All Topics") else selectedTopic,
                                        color = Color.White,
                                        fontSize = 11.sp
                                    )
                                    Icon(Icons.Default.FilterList, contentDescription = null, tint = SoftGold, modifier = Modifier.size(16.dp))
                                }
                            }
                            DropdownMenu(
                                expanded = expandedTopicMenu,
                                onDismissRequest = { expandedTopicMenu = false },
                                modifier = Modifier
                                    .background(DeepNavy)
                                    .border(1.dp, Color.White.copy(alpha = 0.1f))
                            ) {
                                topicsList.forEach { topic ->
                                    DropdownMenuItem(
                                        text = { Text(text = if (topic == "ALL") (if (isTurkish) "Tüm Konular" else "All Topics") else topic, color = Color.White) },
                                        onClick = {
                                            selectedTopic = topic
                                            expandedTopicMenu = false
                                        }
                                    )
                                }
                            }
                        }

                        // High Yield filter chip
                        FilterChip(
                            selected = showOnlyHighYield,
                            onClick = { showOnlyHighYield = !showOnlyHighYield },
                            label = { Text(if (isTurkish) "Yüksek Verimli" else "High-Yield", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color.Red.copy(alpha = 0.2f),
                                selectedLabelColor = Color.Red,
                                containerColor = Color.White.copy(alpha = 0.04f),
                                labelColor = Color.White.copy(alpha = 0.6f)
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = showOnlyHighYield,
                                selectedBorderColor = Color.Red,
                                borderColor = Color.White.copy(alpha = 0.1f)
                            )
                        )

                        // Random 10 Mode toggle
                        FilterChip(
                            selected = random10Mode,
                            onClick = { random10Mode = !random10Mode },
                            label = { Text(if (isTurkish) "Rastgele 10" else "Random 10", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SoftGold.copy(alpha = 0.2f),
                                selectedLabelColor = SoftGold,
                                containerColor = Color.White.copy(alpha = 0.04f),
                                labelColor = Color.White.copy(alpha = 0.6f)
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = random10Mode,
                                selectedBorderColor = SoftGold,
                                borderColor = Color.White.copy(alpha = 0.1f)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Spot Notes list
                    if (filteredNotes.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = Color.White.copy(alpha = 0.3f), modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = if (isTurkish) "Kriterlere uygun spot bilgi bulunamadı." else "No spot notes match the selected criteria.",
                                color = Color.White.copy(alpha = 0.6f),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(bottom = 24.dp)
                        ) {
                            items(filteredNotes) { note ->
                                var cardLangState by remember { mutableStateOf<String?>(null) }
                                val activeCardLang = cardLangState ?: activeLang
                                val displayText = if (activeCardLang == "tr") note.tr else note.en

                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f)),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = if (note.importance.lowercase() == "high") Color.Red.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        // Header: Topic, Subtopic & Importance badge
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = "${note.exam.replace("_", " ")} • ${note.topic}",
                                                    color = SoftGold,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = note.subtopic,
                                                    color = Color.White.copy(alpha = 0.6f),
                                                    fontSize = 9.sp
                                                )
                                            }

                                            // High Yield indicator
                                            if (note.importance.lowercase() == "high") {
                                                Box(
                                                    modifier = Modifier
                                                        .background(Color.Red.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                                        .border(1.dp, Color.Red, RoundedCornerShape(6.dp))
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = Color.Red, modifier = Modifier.size(10.dp))
                                                        Spacer(modifier = Modifier.width(3.dp))
                                                        Text(
                                                            text = if (isTurkish) "YÜKSEK VERİM" else "HIGH YIELD",
                                                            color = Color.Red,
                                                            fontSize = 8.sp,
                                                            fontWeight = FontWeight.ExtraBold
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Note content
                                        Text(
                                            text = displayText,
                                            color = Color.White,
                                            fontSize = 13.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight.Normal
                                        )

                                        Spacer(modifier = Modifier.height(12.dp))

                                        // Footer: tags & language toggle
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            // Tags list
                                            FlowRow(
                                                modifier = Modifier.weight(1f),
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                verticalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                note.tags.take(3).forEach { tag ->
                                                    Box(
                                                        modifier = Modifier
                                                            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(4.dp))
                                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                                    ) {
                                                        Text(text = "#$tag", color = Color.White.copy(alpha = 0.5f), fontSize = 8.sp)
                                                    }
                                                }
                                            }

                                            // Simple Card Language switcher
                                            IconButton(
                                                onClick = {
                                                    cardLangState = if (activeCardLang == "en") "tr" else "en"
                                                },
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Translate,
                                                    contentDescription = "Translate card",
                                                    tint = ClinicalTeal,
                                                    modifier = Modifier.size(14.dp)
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

            // Random Single Spot Dialog Card
            if (showRandomDialog && randomSingleNote != null) {
                val rNote = randomSingleNote!!
                Dialog(onDismissRequest = { showRandomDialog = false }) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = DeepNavy),
                        border = BorderStroke(1.dp, SoftGold.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Casino, contentDescription = null, tint = SoftGold, modifier = Modifier.size(24.dp))
                                Text(
                                    text = if (isTurkish) "Günün Şanslı Bilgisi" else "Lucky Spot Note",
                                    color = SoftGold,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                IconButton(onClick = { showRandomDialog = false }, modifier = Modifier.size(24.dp)) {
                                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "${rNote.exam.replace("_", " ")} • ${rNote.topic}",
                                color = ClinicalTeal,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = rNote.subtopic,
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 10.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            // Display both languages directly for maximum high-yield learning!
                            Text(
                                text = rNote.en,
                                color = Color.White,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = rNote.tr,
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { randomSingleNote = allNotes.random() },
                                colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = if (isTurkish) "Bir Diğerini Göster" else "Show Another One")
                            }
                        }
                    }
                }
            }
        }
    }
}
