package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm
import com.anesthesiaclinic.anesthesiabriefs.data.model.AlgorithmNode
import com.anesthesiaclinic.anesthesiabriefs.data.repository.FirestoreRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import com.anesthesiaclinic.anesthesiabriefs.utils.TranslationHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlgorithmFlowScreen(
    algorithmId: String,
    currentLanguage: String,
    onBackClick: () -> Unit,
    onNavigateToDrug: (String) -> Unit,
    onNavigateToCalculator: (String) -> Unit,
    onAskAiContext: (String) -> Unit
) {
    val algorithm = remember(algorithmId) { FirestoreRepository.getAlgorithmById(algorithmId) } ?: return

    var currentNodeId by remember { mutableStateOf(algorithm.startNode) }
    val nodeHistory = remember { mutableStateListOf<String>() }
    val isTurkish = currentLanguage == "tr"

    val currentNode = remember(currentNodeId, algorithm) {
        algorithm.nodes[currentNodeId]
    }

    fun navigateToNode(nextId: String) {
        nodeHistory.add(currentNodeId)
        currentNodeId = nextId
    }

    fun navigateBackNode() {
        if (nodeHistory.isNotEmpty()) {
            val prev = nodeHistory.removeAt(nodeHistory.size - 1)
            currentNodeId = prev
        }
    }

    fun restartAlgorithm() {
        nodeHistory.clear()
        currentNodeId = algorithm.startNode
    }

    fun buildAlgorithmSummary(): String {
        val sb = StringBuilder()
        val title = if (isTurkish) algorithm.titleTr else algorithm.titleEn
        sb.append(if (isTurkish) "Algoritma Değerlendirmesi: " else "Algorithm Evaluation: ").append(title).append("\n\n")
        
        nodeHistory.forEachIndexed { index, nodeId ->
            val node = algorithm.nodes[nodeId]
            if (node != null) {
                val qText = if (isTurkish) node.textTr else (node.textEn ?: TranslationHelper.translate(node.textTr, currentLanguage))
                sb.append("${index + 1}. ").append(qText).append("\n")
                val nextNodeId = if (index + 1 < nodeHistory.size) nodeHistory[index + 1] else currentNodeId
                val chosenOpt = node.options.find { it.next == nextNodeId }
                if (chosenOpt != null) {
                    val ans = if (isTurkish) chosenOpt.labelTr else (chosenOpt.labelEn ?: TranslationHelper.translate(chosenOpt.labelTr, currentLanguage))
                    sb.append(if (isTurkish) "   → Cevap: " else "   → Answer: ").append(ans).append("\n")
                }
                sb.append("\n")
            }
        }
        
        val endNode = currentNode!!
        val endText = if (isTurkish) endNode.textTr else (endNode.textEn ?: TranslationHelper.translate(endNode.textTr, currentLanguage))
        sb.append(if (isTurkish) "🎯 Sonuç: " else "🎯 Conclusion: ").append(endText)
        return sb.toString()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(if (isTurkish) algorithm.titleTr else algorithm.titleEn, style = MaterialTheme.typography.titleLarge, color = Color.White)
                        Text(
                            text = if (isTurkish) "Tahmini Süre: ${algorithm.estimatedMinutes} dk | Seviye: ${algorithm.urgencyLevel.uppercase()}" else "Est. Time: ${algorithm.estimatedMinutes} min | Level: ${algorithm.urgencyLevel.uppercase()}",
                            fontSize = 11.sp,
                            color = TextSecondaryDark
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = if (isTurkish) "Geri" else "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { restartAlgorithm() }) {
                        Icon(Icons.Default.Refresh, contentDescription = if (isTurkish) "Sıfırla" else "Reset", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavy)
            )
        }
    ) { paddingValues ->
        if (currentNode == null) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text(if (isTurkish) "Algoritma düğümü bulunamadı." else "Algorithm node not found.", style = MaterialTheme.typography.bodyLarge)
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(WarmSand)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            val (urgencyColor, urgencyLabel, urgencyIcon) = when (algorithm.urgencyLevel.lowercase()) {
                "emergency" -> Triple(CriticalRed, if (isTurkish) "🚨 ACİL KRİZ ALGORİTMASI" else "🚨 EMERGENCY CRISIS ALGORITHM", Icons.Default.Dangerous)
                "urgent_emergency" -> Triple(UrgentEmergencyOrange, if (isTurkish) "⚠️ ACİLİYYETLİ ALGORİTMA (URGENT / EMERGENCY)" else "⚠️ URGENT / EMERGENCY ALGORITHM", Icons.Default.Warning)
                "urgent" -> Triple(AlertAmber, if (isTurkish) "⚠️ KLİNİK YÖNETİM ALGORİTMASI (URGENT)" else "⚠️ CLINICAL MANAGEMENT ALGORITHM (URGENT)", Icons.Default.Warning)
                "elective_urgent" -> Triple(ElectiveUrgentTeal, if (isTurkish) "📋 ELEKTİF / ACİL ALGORİTMA" else "📋 ELECTIVE / URGENT ALGORITHM", Icons.Default.Assignment)
                "elective" -> Triple(SafeGreen, if (isTurkish) "📋 ELEKTİF PLANLAMA ALGORİTMASI" else "📋 ELECTIVE PLANNING ALGORITHM", Icons.Default.CheckCircle)
                else -> Triple(SafeGreen, if (isTurkish) "📋 KLİNİK ALGORİTMA" else "📋 CLINICAL ALGORITHM", Icons.Default.CheckCircle)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(urgencyColor.copy(alpha = 0.1f))
                    .border(
                        width = 1.dp,
                        color = urgencyColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = urgencyIcon,
                        contentDescription = null,
                        tint = urgencyColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = urgencyLabel,
                        fontWeight = FontWeight.Bold,
                        color = urgencyColor,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Main Interactive Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Linen),
                border = BorderStroke(1.dp, BorderSand),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Step Counter
                    Text(
                        text = if (isTurkish) "ADIM ${nodeHistory.size + 1}" else "STEP ${nodeHistory.size + 1}",
                        style = MaterialTheme.typography.labelLarge,
                        color = ClinicalTeal,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Question/Action Text
                    Text(
                        text = if (isTurkish) currentNode.textTr else (currentNode.textEn ?: TranslationHelper.translate(currentNode.textTr, currentLanguage)),
                        style = MaterialTheme.typography.headlineMedium,
                        color = DeepNavy,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Choice Buttons (for questions)
                    if (currentNode.type == "question") {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            currentNode.options.forEach { opt ->
                                Button(
                                    onClick = { navigateToNode(opt.next) },
                                    colors = ButtonDefaults.buttonColors(containerColor = DeepNavy, contentColor = Color.White),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp)
                                ) {
                                    Text(if (isTurkish) opt.labelTr else (opt.labelEn ?: TranslationHelper.translate(opt.labelTr, currentLanguage)), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                }
                            }
                        }
                    } else {
                        // Action node (instructions) or End node
                        if (currentNode.next != null) {
                            Button(
                                onClick = { navigateToNode(currentNode.next!!) },
                                colors = ButtonDefaults.buttonColors(containerColor = SafeGreen, contentColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                             ) {
                                Text(if (isTurkish) "Devam Et" else "Continue", fontWeight = FontWeight.Bold)
                            }
                        } else if (currentNode.type == "end") {
                            // Discuss with AI Button
                            Button(
                                onClick = {
                                    val summary = buildAlgorithmSummary()
                                    onAskAiContext(summary)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SoftGold, contentColor = DeepNavy),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                            ) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(if (isTurkish) "Bu Sonucu AI ile Tartış" else "Discuss this Result with AI", fontWeight = FontWeight.Bold)
                            }
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            // Restart Flow Button
                            OutlinedButton(
                                onClick = { restartAlgorithm() },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepNavy),
                                border = BorderStroke(1.dp, BorderSand),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(if (isTurkish) "Yeniden Başlat" else "Restart Flow", fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Navigation Back within node history
                    if (nodeHistory.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        TextButton(onClick = { navigateBackNode() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (isTurkish) "Bir Önceki Adıma Dön" else "Return to Previous Step", fontWeight = FontWeight.Bold, color = DeepNavy)
                        }
                    }
                }
            }

            // Contextual Side Panels (Related components)
            if (currentNode.relatedDrugs.isNotEmpty() || currentNode.relatedCalculators.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = if (isTurkish) "İlişkili Referanslar" else "Related References",
                    style = MaterialTheme.typography.titleLarge,
                    color = DeepNavy,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Related Drugs
                currentNode.relatedDrugs.forEach { drugId ->
                    val drug = FirestoreRepository.getDrugById(drugId)
                    if (drug != null) {
                        RelatedItemRow(
                            title = if (isTurkish) "${drug.nameTr} Monografı" else "${drug.nameEn} Monograph",
                            icon = Icons.Default.Medication,
                            color = SoftGold
                        ) { onNavigateToDrug(drug.drugId) }
                    }
                }

                // Related Calculators
                currentNode.relatedCalculators.forEach { calcSlug ->
                    val calc = FirestoreRepository.getCalculatorBySlug(calcSlug)
                    if (calc != null) {
                        RelatedItemRow(
                            title = if (isTurkish) calc.titleTr else calc.titleEn,
                            icon = Icons.Default.Calculate,
                            color = ClinicalTeal
                        ) { onNavigateToCalculator(calc.slug) }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Safety Warning Banner
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7)),
                border = BorderStroke(1.dp, AlertAmber.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = AlertAmber, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isTurkish) "Acil durumlarda uygulamaya bağımlı kalmayın. Yerel acil çağrı ve kod protokollerini (Mavi Kod) derhal devreye sokun." else "Do not rely on the application in emergencies. Immediately activate local emergency call and code protocols (Blue Code).",
                        fontSize = 12.sp,
                        color = TextPrimaryLight
                    )
                }
            }
        }
    }
}


@Composable
fun RelatedItemRow(
    title: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Linen),
        border = BorderStroke(1.dp, BorderSand),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(title, style = MaterialTheme.typography.bodyLarge, color = DeepNavy, fontWeight = FontWeight.SemiBold)
            }
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = DeepNavy, modifier = Modifier.size(16.dp))
        }
    }
}
