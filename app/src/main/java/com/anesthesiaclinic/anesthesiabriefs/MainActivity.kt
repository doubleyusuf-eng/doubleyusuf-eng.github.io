package com.anesthesiaclinic.anesthesiabriefs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.anesthesiaclinic.anesthesiabriefs.data.repository.AuthRepository
import com.anesthesiaclinic.anesthesiabriefs.ui.screens.*
import com.anesthesiaclinic.anesthesiabriefs.ui.theme.*
import com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository
import com.anesthesiaclinic.anesthesiabriefs.data.sync.FirebaseSyncService
import com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPackManifest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.initialize(this)
        com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository.initialize(this)
        setContent {
            val context = androidx.compose.ui.platform.LocalContext.current
            val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", android.content.Context.MODE_PRIVATE) }
            
            var isDarkTheme by remember { mutableStateOf(false) }
            var currentLanguage by remember { mutableStateOf("en") }
            var currentUnitSystem by remember { mutableStateOf("metric") }

            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                isDarkTheme = prefs.getBoolean("settings_dark_theme", false)
                currentLanguage = prefs.getString("settings_language", "en") ?: "en"
                currentUnitSystem = prefs.getString("settings_unit_system", "metric") ?: "metric"
                AuthRepository.updateLanguage(currentLanguage)

                // Pre-seed Board Prep database if not seeded yet
                coroutineScope.launch {
                    val isSeeded = prefs.getBoolean("board_prep_seeded_v13", false)
                    if (!isSeeded) {
                        try {
                            val packs = listOf("edaic_part_i", "aba_basic", "aba_advanced", "viva_scenarios", "edaic_paper_a_pack_1", "edaic_paper_b_pack_1", "aba_basic_pack_1", "aba_advanced_pack_1", "edaic_viva_pack_1")
                            for (packId in packs) {
                                val manifest = BoardPrepPackManifest(
                                    packId = packId,
                                    titleEn = when (packId) {
                                        "edaic_paper_a_pack_1" -> "EDAIC Paper A Pack 1"
                                        "edaic_paper_b_pack_1" -> "EDAIC Paper B Pack 1"
                                        "aba_basic_pack_1" -> "ABA BASIC Pack 1"
                                        "aba_advanced_pack_1" -> "ABA ADVANCED Pack 1"
                                        "edaic_viva_pack_1" -> "EDAIC Viva Pack 1"
                                        "edaic_part_i" -> "EDAIC Part I Seed Pack"
                                        "aba_basic" -> "ABA BASIC Seed Pack"
                                        "aba_advanced" -> "ABA ADVANCED Seed Pack"
                                        "viva_scenarios" -> "EDAIC Viva Seed Pack"
                                        else -> "Local Seed Pack"
                                    },
                                    titleTr = when (packId) {
                                        "edaic_paper_a_pack_1" -> "EDAIC Paper A Paket 1"
                                        "edaic_paper_b_pack_1" -> "EDAIC Paper B Paket 1"
                                        "aba_basic_pack_1" -> "ABA BASIC Paket 1"
                                        "aba_advanced_pack_1" -> "ABA ADVANCED Paket 1"
                                        "edaic_viva_pack_1" -> "EDAIC Viva Paket 1"
                                        "edaic_part_i" -> "EDAIC Part I Tohum Paketi"
                                        "aba_basic" -> "ABA BASIC Tohum Paketi"
                                        "aba_advanced" -> "ABA ADVANCED Tohum Paketi"
                                        "viva_scenarios" -> "EDAIC Viva Tohum Paketi"
                                        else -> "Yerel Tohum Paketi"
                                    },
                                    boardType = when (packId) {
                                        "edaic_part_i" -> "EDAIC_PART_I"
                                        "edaic_paper_a_pack_1" -> "EDAIC_PART_I"
                                        "edaic_paper_b_pack_1" -> "EDAIC_PART_I"
                                        "aba_basic" -> "ABA_BASIC"
                                        "aba_basic_pack_1" -> "ABA_BASIC"
                                        "aba_advanced" -> "ABA_ADVANCED"
                                        "aba_advanced_pack_1" -> "ABA_ADVANCED"
                                        "viva_scenarios" -> "EDAIC_VIVA"
                                        "edaic_viva_pack_1" -> "EDAIC_VIVA"
                                        else -> "UNKNOWN"
                                    },
                                    version = when (packId) {
                                        "edaic_paper_a_pack_1" -> 2
                                        else -> 1
                                    },
                                    questionCount = when (packId) {
                                        "edaic_paper_a_pack_1" -> 20
                                        "edaic_paper_b_pack_1" -> 20
                                        "aba_basic_pack_1" -> 31
                                        "aba_advanced_pack_1" -> 32
                                        "edaic_viva_pack_1" -> 21
                                        else -> 2
                                    },
                                    languages = listOf("en", "tr"),
                                    isActive = true,
                                    isMandatory = true,
                                    storagePath = "",
                                    checksumSha256 = "",
                                    changelogEn = "Initial seed",
                                    changelogTr = "İlk tohum",
                                    createdAt = "2026-05-30T00:00:00Z",
                                    updatedAt = "2026-05-30T00:00:00Z"
                                )
                                val filename = when (packId) {
                                    "edaic_part_i" -> "seed_board_prep_edaic_part_i.json"
                                    "aba_basic" -> "seed_board_prep_aba_basic.json"
                                    "aba_advanced" -> "seed_board_prep_aba_advanced.json"
                                    "viva_scenarios" -> "seed_board_prep_viva_scenario.json"
                                    "edaic_paper_a_pack_1" -> "seed_board_prep_edaic_paper_a_pack_1.json"
                                    "edaic_paper_b_pack_1" -> "seed_board_prep_edaic_paper_b_pack_1.json"
                                    "aba_basic_pack_1" -> "seed_board_prep_aba_basic_pack_1.json"
                                    "aba_advanced_pack_1" -> "seed_board_prep_aba_advanced_pack_1.json"
                                    "edaic_viva_pack_1" -> "seed_board_prep_edaic_viva_pack_1.json"
                                    else -> throw IllegalArgumentException()
                                }
                                val jsonContent = context.assets.open(filename).bufferedReader().use { it.readText() }
                                val sha = FirebaseSyncService.calculateSha256(jsonContent)
                                val realManifest = manifest.copy(checksumSha256 = sha)
                                FirebaseSyncService.syncPack(context, realManifest, forceLocal = true)
                            }
                            prefs.edit().putBoolean("board_prep_seeded_v13", true).apply()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            val toggleTheme: (Boolean) -> Unit = { dark ->
                isDarkTheme = dark
                prefs.edit().putBoolean("settings_dark_theme", dark).apply()
            }

            val updateLanguage: (String) -> Unit = { lang ->
                currentLanguage = lang
                prefs.edit().putString("settings_language", lang).apply()
                AuthRepository.updateLanguage(lang)
            }

            val updateUnitSystem: (String) -> Unit = { system ->
                currentUnitSystem = system
                prefs.edit().putString("settings_unit_system", system).apply()
            }

            AnesthesiaBriefsTheme(darkTheme = isDarkTheme) {
                MainAppContent(
                    currentLanguage = currentLanguage,
                    isDarkTheme = isDarkTheme,
                    currentUnitSystem = currentUnitSystem,
                    onLanguageChange = updateLanguage,
                    onThemeChange = toggleTheme,
                    onUnitSystemChange = updateUnitSystem
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(
    currentLanguage: String,
    isDarkTheme: Boolean,
    currentUnitSystem: String,
    onLanguageChange: (String) -> Unit,
    onThemeChange: (Boolean) -> Unit,
    onUnitSystemChange: (String) -> Unit
) {
    val userProfileState = AuthRepository.currentUserProfile.collectAsState()
    val userProfile = userProfileState.value

    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = remember { context.getSharedPreferences("anesthesia_briefs_prefs", android.content.Context.MODE_PRIVATE) }
    var showDisclaimer by remember { mutableStateOf(prefs.getString("disclaimer_accepted_at", null) == null) }

    // Enforce disclaimer check
    LaunchedEffect(userProfile) {
        val savedAccepted = prefs.getString("disclaimer_accepted_at", null)
        if (savedAccepted != null && userProfile != null && userProfile.disclaimerAcceptedAt == null) {
            AuthRepository.setDisclaimerAccepted(savedAccepted)
        }
        showDisclaimer = prefs.getString("disclaimer_accepted_at", null) == null
    }

    if (showDisclaimer) {
        DisclaimerScreen(
            currentLanguage = currentLanguage,
            onLanguageChange = onLanguageChange,
            onAccept = {
                val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
                val dateStr = dateFormat.format(java.util.Date())
                prefs.edit().putString("disclaimer_accepted_at", dateStr).apply()
                AuthRepository.acceptDisclaimer()
                showDisclaimer = false
            }
        )
    } else {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        // Determine if bottom bar should be visible (only for main 5 tabs)
        val shouldShowBottomBar = currentDestination != null && (
            currentDestination == "home" ||
            currentDestination == "calculators" ||
            currentDestination.startsWith("algorithms") ||
            currentDestination == "drugs" ||
            currentDestination == "literature"
        )

        Scaffold(
            bottomBar = {
                if (shouldShowBottomBar) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 8.dp
                    ) {
                        NavigationBarItem(
                            selected = currentDestination == "home",
                            onClick = {
                                navController.navigate("home") {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                            label = { Text(if (currentLanguage == "tr") "Ana Sayfa" else "Home", fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DeepNavy,
                                selectedTextColor = DeepNavy,
                                indicatorColor = SoftGold.copy(alpha = 0.4f),
                                unselectedIconColor = TextSecondaryLight,
                                unselectedTextColor = TextSecondaryLight
                            )
                        )
                        NavigationBarItem(
                            selected = currentDestination == "calculators",
                            onClick = {
                                navController.navigate("calculators") {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.Calculate, contentDescription = "Calculators") },
                            label = { Text(if (currentLanguage == "tr") "Hesaplar" else "Calculators", fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DeepNavy,
                                selectedTextColor = DeepNavy,
                                indicatorColor = SoftGold.copy(alpha = 0.4f),
                                unselectedIconColor = TextSecondaryLight,
                                unselectedTextColor = TextSecondaryLight
                            )
                        )
                        NavigationBarItem(
                            selected = currentDestination?.startsWith("algorithms") == true,
                            onClick = {
                                navController.navigate("algorithms") {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.AccountTree, contentDescription = "Algorithms") },
                            label = { Text(if (currentLanguage == "tr") "Algoritmalar" else "Algorithms", fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DeepNavy,
                                selectedTextColor = DeepNavy,
                                indicatorColor = SoftGold.copy(alpha = 0.4f),
                                unselectedIconColor = TextSecondaryLight,
                                unselectedTextColor = TextSecondaryLight
                            )
                        )
                        NavigationBarItem(
                            selected = currentDestination == "drugs",
                            onClick = {
                                navController.navigate("drugs") {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.Medication, contentDescription = "Drugs") },
                            label = { Text(if (currentLanguage == "tr") "İlaçlar" else "Drugs", fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DeepNavy,
                                selectedTextColor = DeepNavy,
                                indicatorColor = SoftGold.copy(alpha = 0.4f),
                                unselectedIconColor = TextSecondaryLight,
                                unselectedTextColor = TextSecondaryLight
                            )
                        )
                        NavigationBarItem(
                            selected = currentDestination == "literature",
                            onClick = {
                                navController.navigate("literature") {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Literature") },
                            label = { Text(if (currentLanguage == "tr") "Literatür" else "Literature", fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DeepNavy,
                                selectedTextColor = DeepNavy,
                                indicatorColor = SoftGold.copy(alpha = 0.4f),
                                unselectedIconColor = TextSecondaryLight,
                                unselectedTextColor = TextSecondaryLight
                            )
                        )
                    }
                }
            },
            floatingActionButton = {
                if (shouldShowBottomBar) {
                    FloatingActionButton(
                        onClick = { navController.navigate("ai_assistant") },
                        containerColor = SoftGold,
                        contentColor = DeepNavy
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = "AI Assistant")
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") {
                    HomeScreen(
                        userName = userProfile?.fullName ?: "Doctor",
                        currentLanguage = currentLanguage,
                        onNavigateToCalculators = { navController.navigate("calculators") },
                        onNavigateToAlgorithms = { filter ->
                            if (filter != null) {
                                navController.navigate("algorithms?filter=$filter")
                            } else {
                                navController.navigate("algorithms")
                            }
                        },
                        onNavigateToDrugs = { navController.navigate("drugs") },
                        onNavigateToLiterature = { navController.navigate("literature") },
                        onNavigateToAiAssistant = { navController.navigate("ai_assistant") },
                        onSelectDrug = { drugId -> navController.navigate("drug_detail/$drugId") },
                        onSelectCalculator = { slug -> navController.navigate("calculator_detail/$slug") },
                        onNavigateToSettings = { navController.navigate("settings") },
                        onNavigateToProfile = { navController.navigate("profile") },
                        onNavigateToBoardPrep = { navController.navigate("board_prep_home") },
                        onNavigateToDailyClinicalPearls = { navController.navigate("daily_clinical_pearls") }
                    )
                }

                composable("calculators") {
                    CalculatorsListScreen(
                        currentLanguage = currentLanguage,
                        onSelectCalculator = { slug -> navController.navigate("calculator_detail/$slug") }
                    )
                }

                composable(
                    route = "calculator_detail/{slug}",
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backStackEntry ->
                    val slug = backStackEntry.arguments?.getString("slug") ?: "bmi"
                    CalculatorDetailScreen(
                        slug = slug,
                        currentLanguage = currentLanguage,
                        unitSystem = currentUnitSystem,
                        onBackClick = { navController.popBackStack() },
                        onAskAiContext = { query ->
                            val encoded = android.net.Uri.encode(query)
                            navController.navigate("ai_assistant?initialText=$encoded")
                        }
                    )
                }

                composable(
                    route = "algorithms?filter={filter}",
                    arguments = listOf(navArgument("filter") { type = NavType.StringType; defaultValue = ""; nullable = true })
                ) { backStackEntry ->
                    val filter = backStackEntry.arguments?.getString("filter") ?: ""
                    AlgorithmsListScreen(
                        filter = filter,
                        currentLanguage = currentLanguage,
                        onSelectAlgorithm = { id -> navController.navigate("algorithm_flow/$id") }
                    )
                }

                composable(
                    route = "algorithm_flow/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id") ?: "perioperative_hypotension"
                    AlgorithmFlowScreen(
                        algorithmId = id,
                        currentLanguage = currentLanguage,
                        onBackClick = { navController.popBackStack() },
                        onNavigateToDrug = { drugId -> navController.navigate("drug_detail/$drugId") },
                        onNavigateToCalculator = { slug -> navController.navigate("calculator_detail/$slug") },
                        onAskAiContext = { query ->
                            val encoded = android.net.Uri.encode(query)
                            navController.navigate("ai_assistant?initialText=$encoded")
                        }
                    )
                }

                composable("drugs") {
                    DrugsListScreen(
                        currentLanguage = currentLanguage,
                        onSelectDrug = { id -> navController.navigate("drug_detail/$id") }
                    )
                }

                composable(
                    route = "drug_detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id") ?: "propofol"
                    DrugDetailScreen(
                        drugId = id,
                        currentLanguage = currentLanguage,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable("literature") {
                    LiteratureListScreen(
                        currentLanguage = currentLanguage,
                        onSelectArticle = { id ->
                            // Can show standard alert dialog for literature details
                        }
                    )
                }

                composable(
                    route = "ai_assistant?initialText={initialText}",
                    arguments = listOf(navArgument("initialText") { type = NavType.StringType; defaultValue = ""; nullable = true })
                ) { backStackEntry ->
                    val initialText = backStackEntry.arguments?.getString("initialText") ?: ""
                    AiClinicalAssistantScreen(
                        currentLanguage = currentLanguage,
                        initialText = initialText,
                        onBackClick = { navController.popBackStack() },
                        onSelectDrug = { id -> navController.navigate("drug_detail/$id") },
                        onSelectCalculator = { slug -> navController.navigate("calculator_detail/$slug") },
                        onSelectAlgorithm = { id -> navController.navigate("algorithm_flow/$id") }
                    )
                }

                composable("settings") {
                    SettingsScreen(
                        currentLanguage = currentLanguage,
                        isDarkTheme = isDarkTheme,
                        unitSystem = currentUnitSystem,
                        onLanguageChange = onLanguageChange,
                        onThemeChange = onThemeChange,
                        onUnitSystemChange = onUnitSystemChange,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable("profile") {
                    ProfileScreen(
                        currentLanguage = currentLanguage,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable("board_prep_home") {
                    BoardPrepHomeScreen(
                        onNavigateToQuiz = { boardType, quizMode, packId, category, difficulty, bookmarkedOnly, unansweredOnly, incorrectOnly ->
                            val url = "board_prep_quiz/$boardType/$quizMode" +
                                    "?packId=${packId ?: ""}" +
                                    "&category=${category ?: ""}" +
                                    "&difficulty=${difficulty ?: ""}" +
                                    "&bookmarkedOnly=$bookmarkedOnly" +
                                    "&unansweredOnly=$unansweredOnly" +
                                    "&incorrectOnly=$incorrectOnly"
                            navController.navigate(url)
                        },
                        onNavigateToSettings = { navController.navigate("board_prep_settings") },
                        onNavigateToSpotNotes = { navController.navigate("board_prep_spot_notes") },
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable("board_prep_settings") {
                    BoardPrepSettingsScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable("board_prep_spot_notes") {
                    BoardPrepSpotNotesScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable("daily_clinical_pearls") {
                    DailyClinicalPearlsScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(
                    route = "board_prep_quiz/{boardType}/{quizMode}?packId={packId}&category={category}&difficulty={difficulty}&bookmarkedOnly={bookmarkedOnly}&unansweredOnly={unansweredOnly}&incorrectOnly={incorrectOnly}",
                    arguments = listOf(
                        navArgument("boardType") { type = NavType.StringType },
                        navArgument("quizMode") { type = NavType.StringType },
                        navArgument("packId") { type = NavType.StringType; nullable = true; defaultValue = "" },
                        navArgument("category") { type = NavType.StringType; nullable = true; defaultValue = "" },
                        navArgument("difficulty") { type = NavType.StringType; nullable = true; defaultValue = "" },
                        navArgument("bookmarkedOnly") { type = NavType.BoolType; defaultValue = false },
                        navArgument("unansweredOnly") { type = NavType.BoolType; defaultValue = false },
                        navArgument("incorrectOnly") { type = NavType.BoolType; defaultValue = false }
                    )
                ) { backStackEntry ->
                    val boardType = backStackEntry.arguments?.getString("boardType") ?: "EDAIC_PART_I"
                    val quizMode = backStackEntry.arguments?.getString("quizMode") ?: "STUDY"
                    val packId = backStackEntry.arguments?.getString("packId").let { if (it.isNullOrEmpty()) null else it }
                    val category = backStackEntry.arguments?.getString("category").let { if (it.isNullOrEmpty()) null else it }
                    val difficulty = backStackEntry.arguments?.getString("difficulty").let { if (it.isNullOrEmpty()) null else it }
                    val bookmarkedOnly = backStackEntry.arguments?.getBoolean("bookmarkedOnly") ?: false
                    val unansweredOnly = backStackEntry.arguments?.getBoolean("unansweredOnly") ?: false
                    val incorrectOnly = backStackEntry.arguments?.getBoolean("incorrectOnly") ?: false
                    
                    BoardPrepQuizScreen(
                        boardType = boardType,
                        quizMode = quizMode,
                        packId = packId,
                        category = category,
                        difficulty = difficulty,
                        bookmarkedOnly = bookmarkedOnly,
                        unansweredOnly = unansweredOnly,
                        incorrectOnly = incorrectOnly,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
