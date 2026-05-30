package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.foundation.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.data.model.*
import com.anesthesiaclinic.anesthesiabriefs.data.repository.FirestoreRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import com.anesthesiaclinic.anesthesiabriefs.utils.TranslationHelper
import java.net.URL
import java.net.HttpURLConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorsListScreen(
    currentLanguage: String,
    onSelectCalculator: (String) -> Unit
) {
    val calculators by remember { mutableStateOf(FirestoreRepository.calculatorsList.value) }
    var searchQuery by remember { mutableStateOf("") }
    val isTurkish = currentLanguage == "tr"

    val filteredCalculators = remember(searchQuery, calculators) {
        if (searchQuery.isBlank()) {
            calculators
        } else {
            val q = searchQuery.lowercase()
            calculators.filter {
                it.titleTr.lowercase().contains(q) ||
                it.titleEn.lowercase().contains(q) ||
                it.descriptionTr.lowercase().contains(q) ||
                (it.descriptionEn ?: "").lowercase().contains(q) ||
                (it.formula ?: "").lowercase().contains(q)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmSand)
            .padding(16.dp)
    ) {
        Text(
            text = if (isTurkish) "Klinik Hesaplayıcılar" else "Clinical Calculators",
            style = MaterialTheme.typography.displayLarge,
            color = DeepNavy,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = if (isTurkish) "Anestezik dozlama, hemodinami ve solunum formülleri." else "Anesthetic dosing, hemodynamic, and respiratory formulas.",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondaryLight,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Premium Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(if (isTurkish) "Hesaplayıcı veya formül arayın..." else "Search calculator or formula...", color = TextSecondaryLight) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondaryLight) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = null, tint = TextSecondaryLight)
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Linen.copy(alpha = 0.5f),
                unfocusedContainerColor = Linen.copy(alpha = 0.2f),
                focusedBorderColor = DeepNavy,
                unfocusedBorderColor = BorderSand
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(filteredCalculators) { calc ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectCalculator(calc.slug) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(ClinicalTeal.copy(alpha = 0.15f))
                                .padding(8.dp)
                        ) {
                            Icon(Icons.Default.Calculate, contentDescription = null, tint = ClinicalTeal)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isTurkish) calc.titleTr else calc.titleEn,
                                style = MaterialTheme.typography.titleLarge,
                                color = DeepNavy,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isTurkish) calc.descriptionTr else (calc.descriptionEn ?: TranslationHelper.translate(calc.descriptionTr, currentLanguage)),
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextSecondaryLight,
                                maxLines = 2
                            )
                        }
                        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = DeepNavy)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlgorithmsListScreen(
    filter: String = "",
    currentLanguage: String,
    onSelectAlgorithm: (String) -> Unit
) {
    val algorithms by remember { mutableStateOf(FirestoreRepository.algorithmsList.value) }
    var searchQuery by remember { mutableStateOf("") }
    var activeFilter by remember { mutableStateOf(filter) }
    val isTurkish = currentLanguage == "tr"

    // Urgent and category filters
    var selectedCategory by remember { mutableStateOf("all") }
    var selectedUrgency by remember { mutableStateOf(if (filter == "emergency") "emergency" else "all") }

    val categories = listOf(
        "all" to if (isTurkish) "Tüm Kategoriler" else "All Categories",
        "scores" to if (isTurkish) "Preoperatif & Skorlama" else "Preop & Scoring",
        "airway" to if (isTurkish) "Havayolu & Solunum" else "Airway & Respiratory",
        "hemodynamics" to if (isTurkish) "Hemodinami & Ritim" else "Hemodynamics & Rhythm",
        "fluids" to if (isTurkish) "Sıvı & Transfüzyon" else "Fluids & Transfusion",
        "drugs" to if (isTurkish) "İlaç & Reversal" else "Drugs & Reversal",
        "critical" to if (isTurkish) "Kritik Krizler" else "Critical Crises"
    )

    val urgencies = listOf(
        "all" to if (isTurkish) "Tümü" else "All",
        "elective" to if (isTurkish) "Elektif" else "Elective",
        "elective_urgent" to if (isTurkish) "Elektif / Acil" else "Elective / Urgent",
        "urgent" to if (isTurkish) "Acil (Urgent)" else "Urgent",
        "urgent_emergency" to if (isTurkish) "Ciddi Acil" else "Urgent Emergency",
        "emergency" to if (isTurkish) "🚨 Kritik Aciller" else "🚨 Critical Only"
    )

    val filteredAlgorithms = remember(searchQuery, activeFilter, selectedCategory, selectedUrgency, algorithms) {
        var list = algorithms
        
        // 1. Filter by category
        if (selectedCategory != "all") {
            list = list.filter { it.category.lowercase() == selectedCategory.lowercase() }
        }
        
        // 2. Filter by urgency level
        if (selectedUrgency != "all") {
            list = list.filter { it.urgencyLevel.lowercase() == selectedUrgency.lowercase() }
        } else if (activeFilter == "emergency") {
            // Support deep link filter compat
            list = list.filter {
                it.urgencyLevel.lowercase() == "emergency" || it.urgencyLevel.lowercase() == "urgent_emergency"
            }
        }
        
        // 3. Search query
        if (searchQuery.isNotBlank()) {
            val q = searchQuery.lowercase()
            list = list.filter {
                it.titleTr.lowercase().contains(q) ||
                it.titleEn.lowercase().contains(q) ||
                it.category.lowercase().contains(q)
            }
        }
        list
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmSand)
            .padding(16.dp)
    ) {
        Text(
            text = if (isTurkish) "Karar Algoritmaları" else "Decision Algorithms",
            style = MaterialTheme.typography.displayLarge,
            color = DeepNavy,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = if (isTurkish) "Kritik durumlar ve komplikasyon yönetim ağaçları." else "Critical scenarios and complication management trees.",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondaryLight,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Premium Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(if (isTurkish) "Algoritma veya kategori arayın..." else "Search algorithm or category...", color = TextSecondaryLight) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondaryLight) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = null, tint = TextSecondaryLight)
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Linen.copy(alpha = 0.5f),
                unfocusedContainerColor = Linen.copy(alpha = 0.2f),
                focusedBorderColor = DeepNavy,
                unfocusedBorderColor = BorderSand
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            singleLine = true
        )

        // 1. Kategori Filtresi
        Text(
            text = if (isTurkish) "Klinik Kategoriler" else "Clinical Categories",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = DeepNavy,
            modifier = Modifier.padding(bottom = 6.dp, top = 2.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { (catId, label) ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedCategory == catId) DeepNavy else Linen)
                        .border(
                            width = 1.dp,
                            color = if (selectedCategory == catId) DeepNavy else BorderSand,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedCategory = catId }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = label,
                        color = if (selectedCategory == catId) Color.White else DeepNavy,
                        fontWeight = if (selectedCategory == catId) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // 2. Aciliyet Filtresi
        Text(
            text = if (isTurkish) "Klinik Aciliyet Seviyesi" else "Clinical Urgency Level",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = DeepNavy,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            urgencies.forEach { (urgId, label) ->
                val chipColor = when (urgId) {
                    "emergency" -> CriticalRed
                    "urgent_emergency" -> UrgentEmergencyOrange
                    "urgent" -> AlertAmber
                    "elective_urgent" -> ElectiveUrgentTeal
                    "elective" -> ClinicalTeal
                    else -> DeepNavy
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedUrgency == urgId) chipColor else Linen)
                        .border(
                            width = 1.dp,
                            color = if (selectedUrgency == urgId) chipColor else BorderSand,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            selectedUrgency = urgId
                            if (urgId != "emergency") {
                                activeFilter = ""
                            }
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = label,
                        color = if (selectedUrgency == urgId) Color.White else DeepNavy,
                        fontWeight = if (selectedUrgency == urgId) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Active filter chip / alert banner
        if (selectedUrgency == "emergency" || activeFilter == "emergency") {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CriticalRed.copy(alpha = 0.08f)),
                border = BorderStroke(1.dp, CriticalRed.copy(alpha = 0.3f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Warning, contentDescription = null, tint = CriticalRed, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isTurkish) "Kritik Acil Durum Algoritmaları Aktif" else "Critical Emergency Algorithms Active",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = CriticalRed
                        )
                    }
                    IconButton(
                        onClick = { 
                            selectedUrgency = "all"
                            activeFilter = "" 
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Filtreyi Temizle", tint = CriticalRed, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(filteredAlgorithms) { algo ->
                val (urgencyColor, urgencyLabel) = when (algo.urgencyLevel.lowercase()) {
                    "emergency" -> CriticalRed to (if (isTurkish) "🚨 KRİTİK ACİL" else "🚨 EMERGENCY")
                    "urgent_emergency" -> UrgentEmergencyOrange to (if (isTurkish) "CİDDİ ACİL" else "URGENT / EMERGENCY")
                    "urgent" -> AlertAmber to (if (isTurkish) "ACİL (URGENT)" else "URGENT")
                    "elective_urgent" -> ElectiveUrgentTeal to (if (isTurkish) "ELEKTİF / ACİL" else "ELECTIVE / URGENT")
                    "elective" -> SafeGreen to (if (isTurkish) "PLANLI (ELEKTİF)" else "ELECTIVE")
                    else -> SafeGreen to algo.urgencyLevel.uppercase()
                }

                val categoryLabel = when (algo.category.lowercase()) {
                    "scores" -> if (isTurkish) "Preoperatif & Skorlama" else "Preop & Scoring"
                    "airway" -> if (isTurkish) "Havayolu & Solunum" else "Airway & Respiratory"
                    "hemodynamics" -> if (isTurkish) "Hemodinami & Ritim" else "Hemodynamics & Rhythm"
                    "fluids" -> if (isTurkish) "Sıvı & Transfüzyon" else "Fluids & Transfusion"
                    "drugs" -> if (isTurkish) "İlaç & Reversal" else "Drugs & Reversal"
                    "critical" -> if (isTurkish) "Kritik Krizler" else "Critical Crises"
                    else -> algo.category.uppercase()
                }

                val isEmergency = algo.urgencyLevel.lowercase() == "emergency"
                val cardBg = if (isEmergency) Linen.copy(red = 1f, green = 0.94f, blue = 0.94f) else Linen
                val cardBorderColor = if (isEmergency) CriticalRed.copy(alpha = 0.4f) else BorderSand
                val cardBorderWidth = if (isEmergency) 1.5.dp else 1.dp

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    border = BorderStroke(cardBorderWidth, cardBorderColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectAlgorithm(algo.algorithmId) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(urgencyColor.copy(alpha = 0.15f))
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = if (isEmergency) Icons.Default.Dangerous else Icons.Default.AccountTree, 
                                contentDescription = null, 
                                tint = urgencyColor
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (isTurkish) algo.titleTr else algo.titleEn,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = DeepNavy,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f, fill = false)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(urgencyColor.copy(alpha = 0.15f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(urgencyLabel, color = urgencyColor, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = categoryLabel,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = ClinicalTeal,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("•", color = TextSecondaryLight, fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isTurkish) "Süre: ${algo.estimatedMinutes} dk" else "Time: ${algo.estimatedMinutes} min",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondaryLight
                                )
                            }
                        }
                        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = DeepNavy)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugsListScreen(
    currentLanguage: String,
    onSelectDrug: (String) -> Unit
) {
    val drugs by remember { mutableStateOf(FirestoreRepository.drugsList.value) }
    var searchQuery by remember { mutableStateOf("") }
    val isTurkish = currentLanguage == "tr"

    val filteredDrugs = remember(searchQuery, drugs) {
        if (searchQuery.isBlank()) {
            drugs
        } else {
            val q = searchQuery.lowercase()
            drugs.filter {
                it.nameTr.lowercase().contains(q) ||
                it.nameEn.lowercase().contains(q) ||
                it.genericName.lowercase().contains(q) ||
                it.brandNames.any { brand -> brand.lowercase().contains(q) } ||
                it.drugClass?.lowercase()?.contains(q) == true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmSand)
            .padding(16.dp)
    ) {
        Text(
            text = if (isTurkish) "İlaç Veri Tabanı" else "Drug Database",
            style = MaterialTheme.typography.displayLarge,
            color = DeepNavy,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = if (isTurkish) "Klinik olarak doğrulanmış, kapsamlı anestezi ilaç kartları." else "Clinically validated, comprehensive anesthesia drug monographs.",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondaryLight,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Premium Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(if (isTurkish) "İlaç ismi, etken madde veya sınıf arayın..." else "Search drug name, generic, or class...", color = TextSecondaryLight) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondaryLight) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = null, tint = TextSecondaryLight)
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Linen.copy(alpha = 0.5f),
                unfocusedContainerColor = Linen.copy(alpha = 0.2f),
                focusedBorderColor = DeepNavy,
                unfocusedBorderColor = BorderSand
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(filteredDrugs) { drug ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Linen),
                    border = BorderStroke(1.dp, BorderSand),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectDrug(drug.drugId) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SoftGold.copy(alpha = 0.15f))
                                .padding(8.dp)
                        ) {
                            Icon(Icons.Default.Medication, contentDescription = null, tint = SoftGold)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isTurkish) drug.nameTr else drug.nameEn,
                                style = MaterialTheme.typography.titleLarge,
                                color = DeepNavy,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(drug.genericName, style = MaterialTheme.typography.bodyLarge, color = TextSecondaryLight)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isTurkish) "Sınıf: ${drug.drugClass ?: "-"}" else "Class: ${TranslationHelper.translate(drug.drugClass ?: "-", currentLanguage)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = ClinicalTeal
                            )
                        }
                        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = DeepNavy)
                    }
                }
            }
        }
    }
}


// ==================== Live PubMed Central Integration & Gemini Monograph Generator ====================

suspend fun fetchRecentPubMedArticles(): List<Article> {
    return withContext(Dispatchers.IO) {
        try {
            // 1. Search PubMed Central for open access anesthesiology/anesthesia papers from the last 180 days (6 months)
            val searchUrl = URL("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=anesthesia[Title/Abstract]+AND+free+full+text[Filter]&reldate=180&retmode=json&retmax=6")
            val searchConn = searchUrl.openConnection() as HttpURLConnection
            searchConn.requestMethod = "GET"
            searchConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
            searchConn.connectTimeout = 6000
            searchConn.readTimeout = 6000
            
            if (searchConn.responseCode != 200) return@withContext emptyList()
            
            val searchResp = searchConn.inputStream.bufferedReader().use { it.readText() }
            val searchJson = Json.parseToJsonElement(searchResp) as JsonObject
            val esearchresult = searchJson["esearchresult"] as? JsonObject ?: return@withContext emptyList()
            val idlist = esearchresult["idlist"] as? JsonArray ?: return@withContext emptyList()
            
            if (idlist.isEmpty()) return@withContext emptyList()
            
            val pmids = idlist.map { it.jsonPrimitive.content }.joinToString(",")
            
            // 2. Fetch official summary data for these PMIDs
            val summaryUrl = URL("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esummary.fcgi?db=pubmed&id=$pmids&retmode=json")
            val summaryConn = summaryUrl.openConnection() as HttpURLConnection
            summaryConn.requestMethod = "GET"
            summaryConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
            summaryConn.connectTimeout = 6000
            summaryConn.readTimeout = 6000
            
            if (summaryConn.responseCode != 200) return@withContext emptyList()
            
            val summaryResp = summaryConn.inputStream.bufferedReader().use { it.readText() }
            val summaryJson = Json.parseToJsonElement(summaryResp) as JsonObject
            val result = summaryJson["result"] as? JsonObject ?: return@withContext emptyList()
            val uids = result["uids"] as? JsonArray ?: return@withContext emptyList()

            
            val fetchedArticles = uids.mapIndexed { index, uidJson ->
                val uid = uidJson.jsonPrimitive.content
                val doc = result[uid] as? JsonObject
                val title = doc?.get("title")?.jsonPrimitive?.content ?: "Bilinmeyen Başlık"
                val source = doc?.get("source")?.jsonPrimitive?.content ?: "PubMed Central"
                val pubdate = doc?.get("pubdate")?.jsonPrimitive?.content ?: "2026"
                
                val authorsArray = doc?.get("authors") as? JsonArray
                val authorsList = authorsArray?.mapNotNull {
                    val authObj = it as? JsonObject
                    authObj?.get("name")?.jsonPrimitive?.content
                } ?: emptyList()
                
                val articleIds = doc?.get("articleids") as? JsonArray
                val doi = articleIds?.firstOrNull {
                    val idObj = it as? JsonObject
                    idObj?.get("idtype")?.jsonPrimitive?.content == "doi"
                }?.let { (it as? JsonObject)?.get("value")?.jsonPrimitive?.content }
                
                // Clean PubMed titles (often end with brackets or punctuation)
                val cleanTitle = title.trimEnd('.')
                
                Article(
                    id = index + 100, // Dynamic IDs start at 100
                    pmid = uid,
                    doi = doi,
                    titleTr = cleanTitle,
                    titleEn = cleanTitle,
                    authors = authorsList,
                    journal = source,
                    publicationDate = pubdate,
                    keyMessageTr = "Monograf yükleniyor...", // Trigger state
                    studyDesignTr = "Yükleniyor...",
                    mainFindingsTr = "Yükleniyor...",
                    clinicalTakeawayTr = "Yükleniyor..."
                )
            }
            fetchedArticles
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

suspend fun generateMonographFromGemini(article: Article, key: String, currentLanguage: String): Article? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$key")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true
            conn.connectTimeout = 10000
            conn.readTimeout = 10000
            
            val isTr = currentLanguage == "tr"
            val prompt = if (isTr) {
                """
                    Aşağıdaki gerçek bilimsel anestezi makalesini analiz et ve bir anestezi uzmanı gözüyle bilimsel açıdan son derece doğru, Türkçe bir klinik monograf özeti hazırla.
                    Makale Bilgileri:
                    Başlık: ${article.titleEn}
                    Yazarlar: ${article.authors.joinToString(", ")}
                    Dergi: ${article.journal}
                    Yayın Tarihi: ${article.publicationDate}
                    PMID: ${article.pmid}
                    DOI: ${article.doi ?: "Bilinmiyor"}
                    
                    Lütfen yanıtını kesinlikle şu JSON formatında oluştur:
                    {
                      "keyMessageTr": "Makalenin en temel klinik mesajı (maksimum 2 cümle)",
                      "studyDesignTr": "Çalışmanın deseni (örn. Retrospektif kohort analizi, Çok merkezli prospektif çalışma, Randomize kontrollü vb.) ve hasta popülasyonu büyüklüğü",
                      "mainFindingsTr": "Çalışmanın temel sayısal, yüzdesel veya istatistiksel ana klinik bulguları",
                      "clinicalTakeawayTr": "Anestezi uzmanı veya klinisyen için pratik klinik öneri",
                      "limitationsTr": "Varsa çalışmanın en belirgin kısıtlılığı",
                      "practiceImpactTr": "Klinik uygulamaya ve monitör takibine doğrudan pratik yansıması"
                    }
                    Yanıt sadece ham JSON metni olmalıdır. Başka hiçbir açıklama, markdown veya işaret ekleme.
                """.trimIndent()
            } else {
                """
                    Analyze the following real scientific anesthesia paper and prepare a highly accurate scientific clinical monograph summary from the perspective of an anesthesiologist in English.
                    Paper Details:
                    Title: ${article.titleEn}
                    Authors: ${article.authors.joinToString(", ")}
                    Journal: ${article.journal}
                    Publication Date: ${article.publicationDate}
                    PMID: ${article.pmid}
                    DOI: ${article.doi ?: "Unknown"}
                    
                    Please construct your response strictly in this JSON format:
                    {
                      "keyMessageTr": "The fundamental clinical message of the paper (maximum 2 sentences)",
                      "studyDesignTr": "The design of the study (e.g. Retrospective cohort analysis, Prospective study, RCT, etc.) and size of patient population",
                      "mainFindingsTr": "The primary clinical findings, percentages, numbers, or statistical highlights of the study",
                      "clinicalTakeawayTr": "Practical clinical takeaway or recommendation for the anesthesiologist",
                      "limitationsTr": "The most significant limitation of the study if any",
                      "practiceImpactTr": "Direct practical impact on clinical practice or monitoring"
                    }
                    The response must be raw JSON only. Do not add any markdown, description, or headers.
                """.trimIndent()
            }
            
            val body = """{"contents": [{"parts": [{"text": ${Json.encodeToString(prompt)}}]}]}"""
            
            conn.outputStream.use { it.write(body.toByteArray(charset("utf-8"))) }
            
            if (conn.responseCode == 200) {
                val resp = conn.inputStream.bufferedReader().use { it.readText() }
                val root = Json.parseToJsonElement(resp) as JsonObject
                val candidates = root["candidates"] as? JsonArray
                val first = candidates?.firstOrNull() as? JsonObject
                val responseContent = first?.get("content") as? JsonObject
                val parts = responseContent?.get("parts") as? JsonArray
                val text = (parts?.firstOrNull() as? JsonObject)?.get("text")?.jsonPrimitive?.content ?: ""
                var rawJson = text.trim()
                
                if (rawJson.startsWith("```")) {
                    rawJson = rawJson.removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
                }
                
                val parsed = Json.parseToJsonElement(rawJson) as JsonObject
                
                article.copy(
                    keyMessageTr = parsed["keyMessageTr"]?.jsonPrimitive?.content ?: "",
                    studyDesignTr = parsed["studyDesignTr"]?.jsonPrimitive?.content ?: "",
                    mainFindingsTr = parsed["mainFindingsTr"]?.jsonPrimitive?.content ?: "",
                    clinicalTakeawayTr = parsed["clinicalTakeawayTr"]?.jsonPrimitive?.content ?: "",
                    limitationsTr = parsed["limitationsTr"]?.jsonPrimitive?.content,
                    practiceImpactTr = parsed["practiceImpactTr"]?.jsonPrimitive?.content
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiteratureListScreen(
    currentLanguage: String,
    onSelectArticle: (Int) -> Unit
) {
    var articlesList by remember { mutableStateOf<List<Article>>(emptyList()) }
    var isLoadingPubMed by remember { mutableStateOf(true) }
    var pubMedError by remember { mutableStateOf(false) }
    
    var selectedArticleForMonograph by remember { mutableStateOf<Article?>(null) }
    var loadingMonographId by remember { mutableStateOf<String?>(null) }
    var monographCache by remember { mutableStateOf(mapOf<String, Article>()) }

    val isTurkish = currentLanguage == "tr"
    fun t(tr: String, en: String): String = if (isTurkish) tr else en

    val context = androidx.compose.ui.platform.LocalContext.current
    val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
    val coroutineScope = rememberCoroutineScope()

    // 1. Load curated, physician-reviewed peer-reviewed scientific monographs directly (NYSORA Style)
    LaunchedEffect(Unit) {
        articlesList = FirestoreRepository.articlesList.value
        pubMedError = false
        isLoadingPubMed = false
    }

    // 2. Dynamic Gemini generator LaunchedEffect disabled to preserve quota for chat
    /*
    LaunchedEffect(selectedArticleForMonograph) {
        selectedArticleForMonograph?.let { article ->
            val pmid = article.pmid ?: ""
            if (pmid.isNotEmpty() && !monographCache.containsKey(pmid) && article.keyMessageTr == "Monograf yükleniyor...") {
                coroutineScope.launch {
                    loadingMonographId = pmid
                    val apiKey = com.anesthesiaclinic.anesthesiabriefs.utils.ApiKeyConfig.GEMINI_API_KEY
                    val generated = generateMonographFromGemini(article, apiKey, currentLanguage)
                    if (generated != null) {
                        monographCache = monographCache + (pmid to generated)
                    } else {
                        val errorArticle = article.copy(
                            keyMessageTr = t(
                                "Monograf yapay zeka tarafından hazırlanamadı. Lütfen internet bağlantınızı kontrol edip tekrar deneyin.",
                                "Monograph could not be generated by AI. Please check your connection and try again."
                            ),
                            studyDesignTr = "-",
                            mainFindingsTr = "-",
                            clinicalTakeawayTr = "-"
                        )
                        monographCache = monographCache + (pmid to errorArticle)
                    }
                    loadingMonographId = null
                }
            }
        }
    }
    */

    val activeArticle = selectedArticleForMonograph


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmSand)
    ) {
        // App header inside literature
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepNavy)
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ClinicalTeal.copy(alpha = 0.2f))
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = ClinicalTeal,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = t("Tıbbi Literatür", "Medical Literature"),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = t("Doğrulanmış Akademik Monograflar", "Verified Academic Monographs"),
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        if (isLoadingPubMed) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = ClinicalTeal)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = t(
                            "PubMed Central'dan son anestezi makaleleri çekiliyor...",
                            "Fetching recent anesthesia articles from PubMed Central..."
                        ),
                        color = TextSecondaryLight,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // Header info item
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Linen),
                        border = BorderStroke(1.dp, BorderSand)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(ClinicalTeal.copy(alpha = 0.12f))
                                    .padding(8.dp)
                            ) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = t("Hakemli Klinik Monograf Kütüphanesi", "Peer-Reviewed Clinical Monograph Library"),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                                Text(
                                    text = t(
                                        "Anestezi uzmanları tarafından incelenmiş, doğrulanmış güncel akademik makale ve kılavuz özetleri.",
                                        "Anesthesiologist-reviewed, verified high-fidelity monographs of recent academic papers and guidelines."
                                    ),
                                    fontSize = 11.sp,
                                    color = TextSecondaryLight
                                )
                            }
                        }
                    }
                }

                items(articlesList) { article ->
                    PubMedArticleCard(
                        article = article,
                        isTurkish = isTurkish,
                        onViewMonographClick = { selectedArticleForMonograph = article },
                        onViewPubMedClick = {
                            val url = article.sourceUrl ?: "https://pubmed.ncbi.nlm.nih.gov/${article.pmid}/"
                            try {
                                uriHandler.openUri(url)
                            } catch (e: Exception) {
                                // fallback
                            }
                        }
                    )
                }
            }
        }
    }

    // Academic Monograph Dialog
    activeArticle?.let { article ->
        AlertDialog(
            onDismissRequest = { selectedArticleForMonograph = null },
            confirmButton = {
                TextButton(onClick = { selectedArticleForMonograph = null }) {
                    Text(t("Kapat", "Close"), color = ClinicalTeal, fontWeight = FontWeight.Bold)
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified Scientific Data",
                        tint = ClinicalTeal,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = t("Akademik Monograf", "Academic Monograph"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepNavy
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (isTurkish) article.titleTr else article.titleEn,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepNavy,
                        lineHeight = 22.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    
                    Text(
                        text = "${article.authors.joinToString(", ")} — *${article.journal}* (${article.publicationDate})",
                        fontSize = 12.sp,
                        color = TextSecondaryLight
                    )
                    
                    if (!article.pmid.isNullOrBlank()) {
                        Text(
                            text = "PMID: ${article.pmid} | DOI: ${article.doi ?: ""}",
                            fontSize = 10.sp,
                            color = ClinicalTeal,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = BorderSand)

                    if (loadingMonographId == article.pmid) {
                        // AI analyzing progress spinner
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(color = ClinicalTeal, modifier = Modifier.size(36.dp))
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = t(
                                        "Yapay Zeka PubMed makalesini analiz ediyor ve Türkçe klinik monograf hazırlıyor...",
                                        "AI is analyzing the PubMed article and preparing a clinical monograph..."
                                    ),
                                    fontSize = 12.sp,
                                    color = TextSecondaryLight,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                        }
                    } else {
                        // Scientific impact stars
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Text(t("Bilimsel Etki Derecesi: ", "Scientific Impact Rating: "), fontSize = 11.sp, color = TextSecondaryLight)
                            repeat(5) { index ->
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = if (index < article.impactRating) SoftGold else BorderSand,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        // Clinical Monograph Sections
                        MonographDetailCard(
                            title = t("Anahtar Mesaj", "Key Message"),
                            content = TranslationHelper.translate(article.keyMessageTr, currentLanguage),
                            icon = Icons.Default.FactCheck,
                            accentColor = ClinicalTeal
                        )
                        
                        MonographDetailCard(
                            title = t("Çalışma Deseni", "Study Design"),
                            content = TranslationHelper.translate(article.studyDesignTr, currentLanguage),
                            icon = Icons.Default.Analytics,
                            accentColor = DeepNavy
                        )

                        MonographDetailCard(
                            title = t("Bulgular", "Main Findings"),
                            content = TranslationHelper.translate(article.mainFindingsTr, currentLanguage),
                            icon = Icons.Default.BarChart,
                            accentColor = DeepNavy
                        )

                        MonographDetailCard(
                            title = t("Klinik Çıkarım", "Clinical Takeaway"),
                            content = TranslationHelper.translate(article.clinicalTakeawayTr, currentLanguage),
                            icon = Icons.Default.Lightbulb,
                            accentColor = AlertAmber
                        )

                        article.limitationsTr?.let {
                            if (it.isNotBlank() && it != "-") {
                                MonographDetailCard(
                                    title = t("Kısıtlılıklar", "Limitations"),
                                    content = TranslationHelper.translate(it, currentLanguage),
                                    icon = Icons.Default.Warning,
                                    accentColor = CriticalRed
                                )
                            }
                        }

                        article.practiceImpactTr?.let {
                            if (it.isNotBlank() && it != "-") {
                                MonographDetailCard(
                                    title = t("Klinik Uygulamaya Etki", "Clinical Practice Impact"),
                                    content = TranslationHelper.translate(it, currentLanguage),
                                    icon = Icons.Default.TrendingUp,
                                    accentColor = SafeGreen
                                )
                            }
                        }
                    }
                }
            },
            shape = RoundedCornerShape(20.dp),
            containerColor = Linen
        )
    }
}

@Composable
fun PubMedArticleCard(
    article: Article,
    isTurkish: Boolean,
    onViewMonographClick: () -> Unit,
    onViewPubMedClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Linen),
        border = BorderStroke(1.dp, BorderSand)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: PubMed Source & Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(ClinicalTeal.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "PUBMED Central",
                            color = ClinicalTeal,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SafeGreen.copy(alpha = 0.15f))
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "OPEN ACCESS",
                            color = SafeGreen,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Text(
                    text = article.publicationDate,
                    fontSize = 11.sp,
                    color = TextSecondaryLight,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Article Title
            Text(
                text = if (isTurkish) article.titleTr else article.titleEn,
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavy,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Authors & Journal
            Text(
                text = article.authors.joinToString(", "),
                fontSize = 12.sp,
                color = TextSecondaryLight,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = ClinicalTeal,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = article.journal,
                    fontSize = 12.sp,
                    color = ClinicalTeal,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "PMID: ${article.pmid}",
                    fontSize = 11.sp,
                    color = TextSecondaryLight
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = BorderSand.copy(alpha = 0.5f))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onViewMonographClick,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, BorderSand),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepNavy),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isTurkish) "Monografı İncele" else "Read Monograph",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onViewPubMedClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ClinicalTeal),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Icon(Icons.Default.Launch, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isTurkish) "PubMed'de Oku" else "Read on PubMed",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun MonographDetailCard(
    title: String,
    content: String,
    icon: ImageVector,
    accentColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = WarmSand.copy(alpha = 0.3f)),
        border = BorderStroke(1.dp, BorderSand.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(accentColor.copy(alpha = 0.12f))
                        .padding(4.dp)
                ) {
                    Icon(imageVector = icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepNavy
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = content,
                fontSize = 12.sp,
                color = DeepNavy.copy(alpha = 0.85f),
                lineHeight = 16.sp
            )
        }
    }
}
