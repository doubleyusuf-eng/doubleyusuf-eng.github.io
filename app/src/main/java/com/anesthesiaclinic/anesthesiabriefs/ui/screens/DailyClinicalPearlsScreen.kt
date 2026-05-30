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
import com.anesthesiaclinic.anesthesiabriefs.data.model.DailyClinicalPearl
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
fun DailyClinicalPearlsScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", Context.MODE_PRIVATE) }
    val isTurkish = remember { prefs.getString("settings_language", "en") == "tr" }

    var allPearls by remember { mutableStateOf<List<DailyClinicalPearl>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Filters
    var selectedCategory by remember { mutableStateOf("ALL") }
    var searchQuery by remember { mutableStateOf("") }
    var showOnlyHighYield by remember { mutableStateOf(false) }
    var showOnlyFavorites by remember { mutableStateOf(false) }

    // Random / Casino Modals & States
    var random10Mode by remember { mutableStateOf(false) }
    var showRandomDialog by remember { mutableStateOf(false) }
    var randomSinglePearl by remember { mutableStateOf<DailyClinicalPearl?>(null) }

    // Language Preferences
    var activeLang by remember { mutableStateOf(prefs.getString("settings_language", "en") ?: "en") }

    // Local Persistence for Favorites
    var favoriteIds by remember {
        mutableStateOf(prefs.getStringSet("favorite_clinical_pearls", emptySet()) ?: emptySet())
    }

    fun toggleFavorite(id: String) {
        val updated = favoriteIds.toMutableSet()
        if (updated.contains(id)) {
            updated.remove(id)
        } else {
            updated.add(id)
        }
        favoriteIds = updated
        prefs.edit().putStringSet("favorite_clinical_pearls", updated).apply()
    }

    // Load data
    LaunchedEffect(Unit) {
        allPearls = BoardPrepRepository.getDailyClinicalPearls(context)
        isLoading = false
    }

    // Translate category ID into user-friendly localized names
    fun getCategoryName(cat: String): String {
        return when (cat) {
            "ALL" -> if (isTurkish) "Tüm Kategoriler" else "All Categories"
            "AIRWAY_RESPIRATORY" -> if (isTurkish) "Solunum & Havayolu" else "Airway & Respiratory"
            "HEMODYNAMIC_CARDIOVASCULAR" -> if (isTurkish) "Hemodinami & Kardiyovasküler" else "Hemodynamics & Cardiovascular"
            "DRUGS_PHARMACOLOGY" -> if (isTurkish) "İlaçlar & Farmakoloji" else "Drugs & Pharmacology"
            "REGIONAL_PAIN_PONV" -> if (isTurkish) "Rejyonel, Ağrı & PONV" else "Regional, Pain & PONV"
            "OBSTETRIC_PEDIATRIC_SPECIAL" -> if (isTurkish) "Obstetrik, Pediatri & Özel Popülasyon" else "Obstetric, Pediatric & Special"
            "ICU_CRISIS_SAFETY_POSTOP" -> if (isTurkish) "Yoğun Bakım, Kriz & Güvenlik" else "ICU, Crisis & Safety"
            else -> cat.replace("_", " ")
        }
    }

    // Filtered Pearls list
    val filteredPearls = remember(allPearls, selectedCategory, searchQuery, showOnlyHighYield, showOnlyFavorites, random10Mode, favoriteIds) {
        var list = allPearls
        if (selectedCategory != "ALL") {
            list = list.filter { it.category == selectedCategory }
        }
        if (showOnlyFavorites) {
            list = list.filter { it.id in favoriteIds }
        }
        if (showOnlyHighYield) {
            list = list.filter { it.importance.lowercase() == "high" }
        }
        if (searchQuery.isNotEmpty()) {
            val q = searchQuery.lowercase()
            list = list.filter {
                it.tr.lowercase().contains(q) ||
                it.en.lowercase().contains(q) ||
                it.subcategory.lowercase().contains(q) ||
                it.clinicalUse.lowercase().contains(q) ||
                it.tags.any { tag -> tag.lowercase().contains(q) }
            }
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
                        text = if (isTurkish) "Günlük Klinik İpuçları" else "Daily Clinical Pearls",
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
                    // Global Language switch
                    IconButton(onClick = { activeLang = if (activeLang == "en") "tr" else "en" }) {
                        Icon(Icons.Default.Language, contentDescription = "Switch Language", tint = SoftGold)
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

                    // Search field and Random Pearl triggers
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
                            placeholder = { Text(if (isTurkish) "Klinik ipuçlarında ara..." else "Search clinical pearls...", color = Color.White.copy(alpha = 0.4f)) },
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

                        // Lucky Pearl Casino Trigger
                        IconButton(
                            onClick = {
                                if (allPearls.isNotEmpty()) {
                                    randomSinglePearl = allPearls.random()
                                    showRandomDialog = true
                                }
                            },
                            modifier = Modifier
                                .background(SoftGold.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                .border(1.dp, SoftGold.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        ) {
                            Icon(Icons.Default.Casino, contentDescription = "Lucky Clinical Pearl", tint = SoftGold)
                        }
                    }

                    // Category Selector Label
                    Text(
                        text = if (isTurkish) "Klinik Kategori" else "Clinical Category",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                    )

                    // Category dropdown menu selection
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val categories = listOf("ALL", "AIRWAY_RESPIRATORY", "HEMODYNAMIC_CARDIOVASCULAR", "DRUGS_PHARMACOLOGY", "REGIONAL_PAIN_PONV", "OBSTETRIC_PEDIATRIC_SPECIAL", "ICU_CRISIS_SAFETY_POSTOP")
                        Box(modifier = Modifier.weight(1f)) {
                            var expandedMenu by remember { mutableStateOf(false) }
                            Button(
                                onClick = { expandedMenu = true },
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
                                        text = getCategoryName(selectedCategory),
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
                                }
                            }
                            DropdownMenu(
                                expanded = expandedMenu,
                                onDismissRequest = { expandedMenu = false },
                                modifier = Modifier
                                    .background(DeepNavy)
                                    .border(1.dp, Color.White.copy(alpha = 0.1f))
                            ) {
                                categories.forEach { cat ->
                                    DropdownMenuItem(
                                        text = { Text(text = getCategoryName(cat), color = Color.White) },
                                        onClick = {
                                            selectedCategory = cat
                                            expandedMenu = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Toggles row (High-Yield, Favorites, Random 10)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // High Yield chip
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

                        // Favorites filter chip
                        FilterChip(
                            selected = showOnlyFavorites,
                            onClick = { showOnlyFavorites = !showOnlyFavorites },
                            label = { Text(if (isTurkish) "Favorilerim" else "My Favorites", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SoftGold.copy(alpha = 0.2f),
                                selectedLabelColor = SoftGold,
                                containerColor = Color.White.copy(alpha = 0.04f),
                                labelColor = Color.White.copy(alpha = 0.6f)
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = showOnlyFavorites,
                                selectedBorderColor = SoftGold,
                                borderColor = Color.White.copy(alpha = 0.1f)
                            )
                        )

                        // Random 10 Mode toggle
                        FilterChip(
                            selected = random10Mode,
                            onClick = { random10Mode = !random10Mode },
                            label = { Text(if (isTurkish) "Rastgele 10" else "Random 10", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ClinicalTeal.copy(alpha = 0.2f),
                                selectedLabelColor = ClinicalTeal,
                                containerColor = Color.White.copy(alpha = 0.04f),
                                labelColor = Color.White.copy(alpha = 0.6f)
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = random10Mode,
                                selectedBorderColor = ClinicalTeal,
                                borderColor = Color.White.copy(alpha = 0.1f)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Clinical Pearls items listing
                    if (filteredPearls.isEmpty()) {
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
                                text = if (isTurkish) "Kriterlere uygun klinik ipucu bulunamadı." else "No clinical pearls match the selected criteria.",
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
                            items(filteredPearls) { pearl ->
                                var cardLangState by remember { mutableStateOf<String?>(null) }
                                val activeCardLang = cardLangState ?: activeLang
                                val displayText = if (activeCardLang == "tr") pearl.tr else pearl.en
                                val isFav = pearl.id in favoriteIds

                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.06f)),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = if (pearl.importance.lowercase() == "high") Color.Red.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        // Header
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = getCategoryName(pearl.category) + " • " + pearl.subcategory,
                                                    color = SoftGold,
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = (if (isTurkish) "Klinik Kullanım: " else "Clinical Use: ") + pearl.clinicalUse,
                                                    color = Color.White.copy(alpha = 0.6f),
                                                    fontSize = 9.sp
                                                )
                                            }

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                // High-Yield badge
                                                if (pearl.importance.lowercase() == "high") {
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

                                                // Favorite Button
                                                Icon(
                                                    imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                                    contentDescription = "Favorite",
                                                    tint = if (isFav) SoftGold else Color.White.copy(alpha = 0.6f),
                                                    modifier = Modifier
                                                        .size(20.dp)
                                                        .clickable { toggleFavorite(pearl.id) }
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Pearl Text content
                                        Text(
                                            text = displayText,
                                            color = Color.White,
                                            fontSize = 13.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight.Normal
                                        )

                                        Spacer(modifier = Modifier.height(12.dp))

                                        // Tags & Card Translator
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            FlowRow(
                                                modifier = Modifier.weight(1f),
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                verticalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                pearl.tags.take(3).forEach { tag ->
                                                    Box(
                                                        modifier = Modifier
                                                            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(4.dp))
                                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                                    ) {
                                                        Text(text = "#$tag", color = Color.White.copy(alpha = 0.5f), fontSize = 8.sp)
                                                    }
                                                }
                                            }

                                            // Quick inline translator button
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

            // Casino Lucky Pearl Alert Dialog
            if (showRandomDialog && randomSinglePearl != null) {
                val rPearl = randomSinglePearl!!
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
                                    text = if (isTurkish) "Şanslı Klinik İpucu" else "Lucky Clinical Pearl",
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
                                text = getCategoryName(rPearl.category) + " • " + rPearl.subcategory,
                                color = ClinicalTeal,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = (if (isTurkish) "Klinik Kullanım: " else "Clinical Use: ") + rPearl.clinicalUse,
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 10.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            // Show both languages simultaneously for perfect bilingual reading experience!
                            Text(
                                text = rPearl.en,
                                color = Color.White,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = rPearl.tr,
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { randomSinglePearl = allPearls.random() },
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
