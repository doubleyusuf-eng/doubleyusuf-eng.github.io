package com.anesthesiaclinic.anesthesiabriefs

import com.anesthesiaclinic.anesthesiabriefs.data.repository.SeedDataAiResponses
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class SeedDataAiResponsesTest {

    @Test
    fun testDebugQueries() {
        val respTr = SeedDataAiResponses.getStructuredResponse("koroner stent cerrahisi", isEnglish = false)
        assertNotNull("TR Stent response should not be null", respTr)
        assertEquals("TR Stent safety level should be elective", "elective", respTr?.safetyLevel)
        println("TR STENT RESOLVED: ${respTr?.safetyLevel} -> ${respTr?.conversationalText?.take(100)}")

        val respEn = SeedDataAiResponses.getStructuredResponse("HELLP syndrome anesthesia", isEnglish = true)
        assertNotNull("EN HELLP response should not be null", respEn)
        assertEquals("EN HELLP safety level should be urgent", "urgent", respEn?.safetyLevel)
        println("EN HELLP RESOLVED: ${respEn?.safetyLevel} -> ${respEn?.conversationalText?.take(100)}")
        
        val respEnStent = SeedDataAiResponses.getStructuredResponse("coronary stent surgery", isEnglish = true)
        assertNotNull("EN Stent response should not be null", respEnStent)
        assertEquals("EN Stent safety level should be elective", "elective", respEnStent?.safetyLevel)
        println("EN STENT RESOLVED: ${respEnStent?.safetyLevel} -> ${respEnStent?.conversationalText?.take(100)}")

        // Package 8 Tests
        val respNeoResus = SeedDataAiResponses.getStructuredResponse("neonatal resüsitasyon", isEnglish = false)
        assertNotNull("TR Neonatal Resus response should not be null", respNeoResus)
        assertEquals("TR Neonatal Resus safety level should be emergency", "emergency", respNeoResus?.safetyLevel)
        println("TR NEO RESUS RESOLVED: ${respNeoResus?.safetyLevel} -> ${respNeoResus?.conversationalText?.take(100)}")

        val respFever = SeedDataAiResponses.getStructuredResponse("perioperatif ateş", isEnglish = false)
        assertNotNull("TR Fever response should not be null", respFever)
        assertEquals("TR Fever safety level should be urgent", "urgent", respFever?.safetyLevel)
        println("TR FEVER RESOLVED: ${respFever?.safetyLevel} -> ${respFever?.conversationalText?.take(100)}")

        val respLaDose = SeedDataAiResponses.getStructuredResponse("local anesthetic max dose", isEnglish = true)
        assertNotNull("EN LA Dose response should not be null", respLaDose)
        assertEquals("EN LA Dose safety level should be elective", "elective", respLaDose?.safetyLevel)
        println("EN LA DOSE RESOLVED: ${respLaDose?.safetyLevel} -> ${respLaDose?.conversationalText?.take(100)}")

        // Package 9 Tests
        val respAsthma = SeedDataAiResponses.getStructuredResponse("status astmatikus", isEnglish = false)
        assertNotNull("TR Status Astmatikus should not be null", respAsthma)
        assertEquals("TR Status Astmatikus safety level should be emergency", "emergency", respAsthma?.safetyLevel)
        println("TR ASTHMA RESOLVED: ${respAsthma?.safetyLevel} -> ${respAsthma?.conversationalText?.take(100)}")

        val respLvad = SeedDataAiResponses.getStructuredResponse("lvad hastası", isEnglish = false)
        assertNotNull("TR LVAD patient should not be null", respLvad)
        assertEquals("TR LVAD patient safety level should be elective", "elective", respLvad?.safetyLevel)
        println("TR LVAD RESOLVED: ${respLvad?.safetyLevel} -> ${respLvad?.conversationalText?.take(100)}")

        val respImpella = SeedDataAiResponses.getStructuredResponse("impella", isEnglish = false)
        assertNotNull("TR Impella should not be null", respImpella)
        assertEquals("TR Impella safety level should be urgent", "urgent", respImpella?.safetyLevel)
        println("TR IMPELLA RESOLVED: ${respImpella?.safetyLevel} -> ${respImpella?.conversationalText?.take(100)}")

        val respMask = SeedDataAiResponses.getStructuredResponse("zor maske ventilasyonu", isEnglish = false)
        assertNotNull("TR Zor Maske Ventilasyonu should not be null", respMask)
        assertEquals("TR Zor Maske Ventilasyonu safety level should be emergency", "emergency", respMask?.safetyLevel)
        println("TR MASK RESOLVED: ${respMask?.safetyLevel} -> ${respMask?.conversationalText?.take(100)}")

        // Package 10 Tests
        val respAbg = SeedDataAiResponses.getStructuredResponse("kan gazı", isEnglish = false)
        assertNotNull("TR ABG response should not be null", respAbg)
        assertEquals("TR ABG safety level should be elective", "elective", respAbg?.safetyLevel)
        println("TR ABG RESOLVED: ${respAbg?.safetyLevel} -> ${respAbg?.conversationalText?.take(100)}")

        val respAnion = SeedDataAiResponses.getStructuredResponse("anion gap", isEnglish = true)
        assertNotNull("EN Anion Gap response should not be null", respAnion)
        assertEquals("EN Anion Gap safety level should be elective", "elective", respAnion?.safetyLevel)
        println("EN ANION GAP RESOLVED: ${respAnion?.safetyLevel} -> ${respAnion?.conversationalText?.take(100)}")

        val respOliguria = SeedDataAiResponses.getStructuredResponse("oligüri", isEnglish = false)
        assertNotNull("TR Oliguria response should not be null", respOliguria)
        assertEquals("TR Oliguria safety level should be urgent", "urgent", respOliguria?.safetyLevel)
        println("TR OLIGURIA RESOLVED: ${respOliguria?.safetyLevel} -> ${respOliguria?.conversationalText?.take(100)}")

        val respPonv = SeedDataAiResponses.getStructuredResponse("ponv rescue", isEnglish = true)
        assertNotNull("EN PONV Rescue response should not be null", respPonv)
        assertEquals("EN PONV Rescue safety level should be elective", "elective", respPonv?.safetyLevel)
        println("EN PONV RESOLVED: ${respPonv?.safetyLevel} -> ${respPonv?.conversationalText?.take(100)}")

        val respPca = SeedDataAiResponses.getStructuredResponse("pca", isEnglish = false)
        assertNotNull("TR PCA response should not be null", respPca)
        assertEquals("TR PCA safety level should be elective", "elective", respPca?.safetyLevel)
        println("TR PCA RESOLVED: ${respPca?.safetyLevel} -> ${respPca?.conversationalText?.take(100)}")

        // Package 11 Tests
        val respWrongDose = SeedDataAiResponses.getStructuredResponse("yanlış doz", isEnglish = false)
        assertNotNull("TR Wrong Dose response should not be null", respWrongDose)
        assertEquals("TR Wrong Dose safety level should be urgent", "urgent", respWrongDose?.safetyLevel)
        println("TR WRONG DOSE RESOLVED: ${respWrongDose?.safetyLevel} -> ${respWrongDose?.conversationalText?.take(100)}")

        val respNorepiExtrav = SeedDataAiResponses.getStructuredResponse("noradrenalin ekstravazasyonu", isEnglish = false)
        assertNotNull("TR Norepinephrine Extravasation response should not be null", respNorepiExtrav)
        assertEquals("TR Norepinephrine Extravasation safety level should be urgent", "urgent", respNorepiExtrav?.safetyLevel)
        println("TR NOREPI EXTRAV RESOLVED: ${respNorepiExtrav?.safetyLevel} -> ${respNorepiExtrav?.conversationalText?.take(100)}")

        val respPris = SeedDataAiResponses.getStructuredResponse("pris", isEnglish = true)
        assertNotNull("EN PRIS response should not be null", respPris)
        assertEquals("EN PRIS safety level should be emergency", "emergency", respPris?.safetyLevel)
        println("EN PRIS RESOLVED: ${respPris?.safetyLevel} -> ${respPris?.conversationalText?.take(100)}")

        val respVancoFlushing = SeedDataAiResponses.getStructuredResponse("vankomisin kızarma", isEnglish = false)
        assertNotNull("TR Vancomycin Flushing response should not be null", respVancoFlushing)
        assertEquals("TR Vancomycin Flushing safety level should be urgent", "urgent", respVancoFlushing?.safetyLevel)
        println("TR VANCO FLUSHING RESOLVED: ${respVancoFlushing?.safetyLevel} -> ${respVancoFlushing?.conversationalText?.take(100)}")

        val respAwareness = SeedDataAiResponses.getStructuredResponse("intraoperatif farkındalık", isEnglish = false)
        assertNotNull("TR Awareness response should not be null", respAwareness)
        assertEquals("TR Awareness safety level should be elective", "elective", respAwareness?.safetyLevel)
        println("TR AWARENESS RESOLVED: ${respAwareness?.safetyLevel} -> ${respAwareness?.conversationalText?.take(100)}")

        val respFrail = SeedDataAiResponses.getStructuredResponse("kırılgan hasta", isEnglish = false)
        assertNotNull("TR Frail response should not be null", respFrail)
        assertEquals("TR Frail safety level should be elective", "elective", respFrail?.safetyLevel)
        println("TR FRAIL RESOLVED: ${respFrail?.safetyLevel} -> ${respFrail?.conversationalText?.take(100)}")

        // Package 12 Tests
        val respFuncCap = SeedDataAiResponses.getStructuredResponse("fonksiyonel kapasite", isEnglish = false)
        assertNotNull("TR Functional Capacity response should not be null", respFuncCap)
        assertEquals("TR Functional Capacity safety level should be elective", "elective", respFuncCap?.safetyLevel)
        println("TR FUNC CAP RESOLVED: ${respFuncCap?.safetyLevel} -> ${respFuncCap?.conversationalText?.take(100)}")

        val respDasi = SeedDataAiResponses.getStructuredResponse("dasi", isEnglish = false)
        assertNotNull("TR DASI response should not be null", respDasi)
        assertEquals("TR DASI safety level should be elective", "elective", respDasi?.safetyLevel)
        println("TR DASI RESOLVED: ${respDasi?.safetyLevel} -> ${respDasi?.conversationalText?.take(100)}")

        val respPostopTroponin = SeedDataAiResponses.getStructuredResponse("postop troponin", isEnglish = false)
        assertNotNull("TR Postop Troponin response should not be null", respPostopTroponin)
        assertEquals("TR Postop Troponin safety level should be urgent", "urgent", respPostopTroponin?.safetyLevel)
        println("TR POSTOP TROPONIN RESOLVED: ${respPostopTroponin?.safetyLevel} -> ${respPostopTroponin?.conversationalText?.take(100)}")

        val respGlp1 = SeedDataAiResponses.getStructuredResponse("glp-1", isEnglish = false)
        assertNotNull("TR GLP-1 response should not be null", respGlp1)
        assertEquals("TR GLP-1 safety level should be elective", "elective", respGlp1?.safetyLevel)
        println("TR GLP-1 RESOLVED: ${respGlp1?.safetyLevel} -> ${respGlp1?.conversationalText?.take(100)}")

        val respSglt2 = SeedDataAiResponses.getStructuredResponse("sglt2", isEnglish = false)
        assertNotNull("TR SGLT2 response should not be null", respSglt2)
        assertEquals("TR SGLT2 safety level should be elective", "elective", respSglt2?.safetyLevel)
        println("TR SGLT2 RESOLVED: ${respSglt2?.safetyLevel} -> ${respSglt2?.conversationalText?.take(100)}")

        val respEras = SeedDataAiResponses.getStructuredResponse("eras", isEnglish = false)
        assertNotNull("TR ERAS response should not be null", respEras)
        assertEquals("TR ERAS safety level should be elective", "elective", respEras?.safetyLevel)
        println("TR ERAS RESOLVED: ${respEras?.safetyLevel} -> ${respEras?.conversationalText?.take(100)}")

        // Package 13 Tests
        val respAirway = SeedDataAiResponses.getStructuredResponse("preoperatif havayolu", isEnglish = false)
        assertNotNull("TR Preoperative Airway response should not be null", respAirway)
        assertEquals("TR Preoperative Airway safety level should be elective", "elective", respAirway?.safetyLevel)
        println("TR AIRWAY RESOLVED: ${respAirway?.safetyLevel} -> ${respAirway?.conversationalText?.take(100)}")

        val respAwakeIntub = SeedDataAiResponses.getStructuredResponse("awake entübasyon", isEnglish = false)
        assertNotNull("TR Awake Intubation response should not be null", respAwakeIntub)
        assertEquals("TR Awake Intubation safety level should be elective", "elective", respAwakeIntub?.safetyLevel)
        println("TR AWAKE INTUB RESOLVED: ${respAwakeIntub?.safetyLevel} -> ${respAwakeIntub?.conversationalText?.take(100)}")

        val respEtco2 = SeedDataAiResponses.getStructuredResponse("etco2 yok", isEnglish = false)
        assertNotNull("TR Intubation Confirmation response should not be null", respEtco2)
        assertEquals("TR Intubation Confirmation safety level should be emergency", "emergency", respEtco2?.safetyLevel)
        println("TR ETCO2 RESOLVED: ${respEtco2?.safetyLevel} -> ${respEtco2?.conversationalText?.take(100)}")

        val respCuffLeak = SeedDataAiResponses.getStructuredResponse("cuff leak test", isEnglish = false)
        assertNotNull("TR Cuff Leak Test response should not be null", respCuffLeak)
        assertEquals("TR Cuff Leak Test safety level should be elective", "elective", respCuffLeak?.safetyLevel)
        println("TR CUFF LEAK RESOLVED: ${respCuffLeak?.safetyLevel} -> ${respCuffLeak?.conversationalText?.take(100)}")

        val respRegurg = SeedDataAiResponses.getStructuredResponse("regürjitasyon", isEnglish = false)
        assertNotNull("TR Regurgitation response should not be null", respRegurg)
        assertEquals("TR Regurgitation safety level should be emergency", "emergency", respRegurg?.safetyLevel)
        println("TR REGURG RESOLVED: ${respRegurg?.safetyLevel} -> ${respRegurg?.conversationalText?.take(100)}")

        // Package 14 Tests
        val respProtVent = SeedDataAiResponses.getStructuredResponse("akciğer koruyucu ventilasyon", isEnglish = false)
        assertNotNull("TR Protective Vent response should not be null", respProtVent)
        assertEquals("TR Protective Vent safety level should be routine", "routine", respProtVent?.safetyLevel)
        println("TR PROTECTIVE VENT RESOLVED: ${respProtVent?.safetyLevel} -> ${respProtVent?.conversationalText?.take(100)}")

        val respDriving = SeedDataAiResponses.getStructuredResponse("driving pressure", isEnglish = true)
        assertNotNull("EN Driving Pressure response should not be null", respDriving)
        assertEquals("EN Driving Pressure safety level should be routine", "routine", respDriving?.safetyLevel)
        println("EN DRIVING PRESSURE RESOLVED: ${respDriving?.safetyLevel} -> ${respDriving?.conversationalText?.take(100)}")

        val respAutoPeep = SeedDataAiResponses.getStructuredResponse("auto peep", isEnglish = true)
        assertNotNull("EN Auto Peep response should not be null", respAutoPeep)
        assertEquals("EN Auto Peep safety level should be urgent", "urgent", respAutoPeep?.safetyLevel)
        println("EN AUTO PEEP RESOLVED: ${respAutoPeep?.safetyLevel} -> ${respAutoPeep?.conversationalText?.take(100)}")

        val respOlv = SeedDataAiResponses.getStructuredResponse("olv hedefleri", isEnglish = false)
        assertNotNull("TR OLV basics response should not be null", respOlv)
        assertEquals("TR OLV basics safety level should be elective", "elective", respOlv?.safetyLevel)
        println("TR OLV BASICS RESOLVED: ${respOlv?.safetyLevel} -> ${respOlv?.conversationalText?.take(100)}")

        val respDlt = SeedDataAiResponses.getStructuredResponse("double lumen tube selection", isEnglish = true)
        assertNotNull("EN DLT selection response should not be null", respDlt)
        assertEquals("EN DLT selection safety level should be elective", "elective", respDlt?.safetyLevel)
        println("EN DLT SELECTION RESOLVED: ${respDlt?.safetyLevel} -> ${respDlt?.conversationalText?.take(100)}")

        val respVentAlarm = SeedDataAiResponses.getStructuredResponse("ventilatör alarmı", isEnglish = false)
        assertNotNull("TR Ventilator Alarm response should not be null", respVentAlarm)
        assertEquals("TR Ventilator Alarm safety level should be emergency", "emergency", respVentAlarm?.safetyLevel)
        println("TR VENT ALARM RESOLVED: ${respVentAlarm?.safetyLevel} -> ${respVentAlarm?.conversationalText?.take(100)}")

        // Package 15 Tests
        val respRegSafety = SeedDataAiResponses.getStructuredResponse("periferik sinir bloğu öncesi kontrol", isEnglish = false)
        assertNotNull("TR Regional Safety response should not be null", respRegSafety)
        assertEquals("TR Regional Safety safety level should be urgent", "urgent", respRegSafety?.safetyLevel)
        println("TR REGIONAL SAFETY RESOLVED: ${respRegSafety?.safetyLevel} -> ${respRegSafety?.conversationalText?.take(100)}")

        val respUsgBlock = SeedDataAiResponses.getStructuredResponse("ultrasound guided block", isEnglish = true)
        assertNotNull("EN USG Block response should not be null", respUsgBlock)
        assertEquals("EN USG Block safety level should be routine", "routine", respUsgBlock?.safetyLevel)
        println("EN USG BLOCK RESOLVED: ${respUsgBlock?.safetyLevel} -> ${respUsgBlock?.conversationalText?.take(100)}")

        val respInterscalene = SeedDataAiResponses.getStructuredResponse("interskalen blok", isEnglish = false)
        assertNotNull("TR Interscalene response should not be null", respInterscalene)
        assertEquals("TR Interscalene safety level should be urgent", "urgent", respInterscalene?.safetyLevel)
        println("TR INTERSCALENE RESOLVED: ${respInterscalene?.safetyLevel} -> ${respInterscalene?.conversationalText?.take(100)}")

        val respSupraclav = SeedDataAiResponses.getStructuredResponse("supraclavicular block", isEnglish = true)
        assertNotNull("EN Supraclavicular response should not be null", respSupraclav)
        assertEquals("EN Supraclavicular safety level should be urgent", "urgent", respSupraclav?.safetyLevel)
        println("EN SUPRACLAVICULAR RESOLVED: ${respSupraclav?.safetyLevel} -> ${respSupraclav?.conversationalText?.take(100)}")

        val respTapBlock = SeedDataAiResponses.getStructuredResponse("tap blok", isEnglish = false)
        assertNotNull("TR TAP Block response should not be null", respTapBlock)
        assertEquals("TR TAP Block safety level should be elective", "elective", respTapBlock?.safetyLevel)
        println("TR TAP BLOCK RESOLVED: ${respTapBlock?.safetyLevel} -> ${respTapBlock?.conversationalText?.take(100)}")

        val respSpinalHypotension = SeedDataAiResponses.getStructuredResponse("spinal sonrası hipotansiyon", isEnglish = false)
        assertNotNull("TR Spinal Hypotension should not be null", respSpinalHypotension)
        assertEquals("TR Spinal Hypotension safety level should be urgent", "urgent", respSpinalHypotension?.safetyLevel)
        println("TR SPINAL HYPOTENSION RESOLVED: ${respSpinalHypotension?.safetyLevel} -> ${respSpinalHypotension?.conversationalText?.take(100)}")

        val respAnticoagulant = SeedDataAiResponses.getStructuredResponse("antikoagülan blok", isEnglish = false)
        assertNotNull("TR Anticoagulant regional response should not be null", respAnticoagulant)
        assertEquals("TR Anticoagulant regional safety level should be urgent", "urgent", respAnticoagulant?.safetyLevel)
        println("TR ANTICOAGULANT REGIONAL RESOLVED: ${respAnticoagulant?.safetyLevel} -> ${respAnticoagulant?.conversationalText?.take(100)}")
    }

    @Test
    fun testLocalSearchEngineMatching() {
        var file = java.io.File("app/src/main/assets/local_qa_database.json")
        if (!file.exists()) {
            file = java.io.File("src/main/assets/local_qa_database.json")
        }
        val jsonString = file.readText()
        com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.initializeForTest(jsonString)
        
        // Assert the size of loaded database is 871
        assertEquals("Database should contain exactly 871 entries", 871, com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.getItemsCount())

        // Test 1: Exact search
        val match1 = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("spinal sonrası hipotansiyon", isEnglish = false)
        assertNotNull("Search for exact title should match", match1)
        assertEquals("Match ID should be spinal_hypotension", "spinal_hypotension", match1?.id)

        // Test 2: Typo tolerance search
        val match2 = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("malign hipertermi dozu", isEnglish = false)
        assertNotNull("Fuzzy typo search should match", match2)
        assertEquals("Match ID should be malignant_hyperthermia", "malignant_hyperthermia", match2?.id)

        // Test 2b: True fuzzy typo search in English
        val match2b = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("malingnant hypertermia", isEnglish = true)
        assertNotNull("True fuzzy typo search should match", match2b)
        assertEquals("Match ID should be malignant_hyperthermia", "malignant_hyperthermia", match2b?.id)

        // Test 3: Drug dosing query
        val match3 = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("propofol dozu nedir", isEnglish = false)
        assertNotNull("Drug dosing search should match", match3)
        assertEquals("Match ID should be drug_dose_propofol", "drug_dose_propofol", match3?.id)

        // Test 4: English greeting query
        val match4 = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("hello how are you", isEnglish = true)
        assertNotNull("English greeting search should match", match4)
        assertEquals("Match ID should be greeting_hello", "greeting_hello", match4?.id)

        // Test 5: Context-aware enriched follow-up query matching
        val enrichedQuery = "malignant hyperthermia why should i stop volatile agents?"
        val match5 = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch(enrichedQuery, isEnglish = true)
        assertNotNull("Enriched clinical follow-up should match", match5)
        org.junit.Assert.assertTrue(
            "Match ID should start with clinical_followup_malignant_hyperthermia_why",
            match5?.id?.startsWith("clinical_followup_malignant_hyperthermia_why") == true
        )

        println("LOCAL SEARCH ENGINE TESTS PASSED SUCCESSFULLY!")
    }

    @Test
    fun testDatabaseIntegrityAndSafetyFields() {
        var file = java.io.File("app/src/main/assets/local_qa_database.json")
        if (!file.exists()) {
            file = java.io.File("src/main/assets/local_qa_database.json")
        }
        val jsonString = file.readText()
        com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.initializeForTest(jsonString)

        val count = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.getItemsCount()
        org.junit.Assert.assertTrue("Database should contain items", count > 0)

        // 1. Verify safety disclaimers exist on critical clinical items
        val rsiMatch = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("rsi", isEnglish = false)
        assertNotNull("rsi should exist", rsiMatch)
        assertEquals("rsi safety level should be urgent", "urgent", rsiMatch?.safetyLevel)
        org.junit.Assert.assertTrue(
            "rsi should have Turkish notASubstituteWarning",
            rsiMatch?.tr?.notASubstituteWarning?.isNotEmpty() == true
        )
        org.junit.Assert.assertTrue(
            "rsi should have English notASubstituteWarning",
            rsiMatch?.en?.notASubstituteWarning?.isNotEmpty() == true
        )

        val airwayMatch = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("zor havayolu", isEnglish = false)
        assertNotNull("zor havayolu should exist", airwayMatch)
        assertEquals("airway safety level should be urgent", "urgent", airwayMatch?.safetyLevel)

        val cicoMatch = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("cico", isEnglish = false)
        assertNotNull("cico should exist", cicoMatch)
        assertEquals("cico safety level should be emergency", "emergency", cicoMatch?.safetyLevel)

        // 2. Verify clinical safety disclaimers are populated for drug dosing
        val propofolMatch = com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine.findBestMatch("propofol dozu", isEnglish = false)
        assertNotNull("propofol dozu should exist", propofolMatch)
        org.junit.Assert.assertTrue(
            "Propofol should have clinical substitute warnings",
            propofolMatch?.tr?.notASubstituteWarning?.contains("klinik") == true || propofolMatch?.tr?.notASubstituteWarning?.contains("geçmez") == true
        )

        println("DATABASE SAFETY INTEGRITY TESTS PASSED SUCCESSFULLY!")
    }
}

