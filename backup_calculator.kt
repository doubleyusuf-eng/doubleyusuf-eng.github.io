Created At: 2026-05-26T22:00:57Z
Completed At: 2026-05-26T22:00:57Z
File Path: `file:///Users/yusufyilmaz/.gemini/antigravity/scratch/anesthesia-briefs/app/src/main/java/com/anesthesiaclinic/anesthesiabriefs/ui/screens/CalculatorDetailScreen.kt`
Total Lines: 2561
Total Bytes: 159952
Showing lines 26 to 100
The following code has been modified to include a line number before every line, in the format: <line_number>: <original_line>. Please note that any changes targeting the original code should remove the line number, colon, and leading space.
26: @Composable
27: fun CalculatorDetailScreen(
28:     slug: String,
29:     currentLanguage: String,
30:     onBackClick: () -> Unit,
31:     onAskAiContext: (String) -> Unit
32: ) {
33:     val calculator = remember(slug) { FirestoreRepository.getCalculatorBySlug(slug) } ?: return
34:     val isTurkish = currentLanguage == "tr"
35:     fun t(text: String): String = TranslationHelper.translate(text, currentLanguage)
36:     fun t(tr: String, en: String): String = if (isTurkish) tr else en
37: 
38: 
39: 
40:     // ----------------------------------------------------
41:     // Standard Calculator State Variables
42:     // ----------------------------------------------------
43:     var inputVal1 by remember { mutableStateOf("70.0") } // e.g. weight, sbp, GCS eye
44:     var inputVal2 by remember { mutableStateOf("170.0") } // e.g. height, dbp, GCS verbal
45:     var inputVal3 by remember { mutableStateOf("10.0") } // e.g. lowest Hb, GCS motor
46:     var selectedCategory by remember { mutableStateOf("adult_male") }
47: 
48:     var resultText by remember { mutableStateOf("") }
49:     var interpretationText by remember { mutableStateOf("") }
50:     var calculatedValue by remember { mutableStateOf(0.0) }
51:     var hasCalculated by remember { mutableStateOf(false) }
52: 
53:     var isFormulaExpanded by remember { mutableStateOf(false) }
54:     var isSourcesExpanded by remember { mutableStateOf(false) }
55: 
56:     // --------------------------------------------------
<truncated 623 bytes>
StateOf(0) }
68:     var plannedSurgeryHour by remember { mutableStateOf(14) }
69:     var plannedSurgeryMinute by remember { mutableStateOf(0) }
70:     var selectedNpoPatientGroup by remember { mutableStateOf("adult") }
71:     
72:     // NPO Risk Flags
73:     var npoDiabetes by remember { mutableStateOf(false) }
74:     var npoGastroparesis by remember { mutableStateOf(false) }
75:     var npoGerd by remember { mutableStateOf(false) }
76:     var npoObesity by remember { mutableStateOf(false) }
77:     var npoOpioids by remember { mutableStateOf(false) }
78:     var npoBowelObstruction by remember { mutableStateOf(false) }
79:     var npoPregnancy by remember { mutableStateOf(false) }
80: 
81:     // ----------------------------------------------------
82:     // Difficult Airway State Variables
83:     // ----------------------------------------------------
84:     var selectedMallampati by remember { mutableStateOf(1) }
85:     var mouthOpeningCm by remember { mutableStateOf(4.5) }
86:     var thyromentalDistanceCm by remember { mutableStateOf(7.5) }
87:     var isNeckMobilityLimited by remember { mutableStateOf(false) }
88:     var selectedUpperLipBiteClass by remember { mutableStateOf(1) }
89:     
90:     // Checklist secondary parameters
91:     var airwayPrevDifficult by remember { mutableStateOf(false) }
92:     var airwayOsa by remember { mutableStateOf(false) }
93:     var airwayObese by remember { mutableStateOf(false) }
94:     var airwayBeard by remember { mutableStateOf(false) }
95:     var airwayEdentulous by remember { mutableStateOf(false) }
96:     var airwayCervicalLimit by remember { mutableStateOf(false) }
97:     var airwayPregnancy by remember { mutableStateOf(false) }
98:     var airwayPathology by remember { mutableStateOf(false) }
99: 
100:     // ----------------------------------------------------
The above content does NOT show the entire file contents. If you need to view any lines of the file which were not shown to complete your task, call this tool again to view those lines.
